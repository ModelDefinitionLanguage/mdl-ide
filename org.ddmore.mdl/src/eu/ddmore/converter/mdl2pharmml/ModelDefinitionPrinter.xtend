package eu.ddmore.converter.mdl2pharmml
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.BlockStatement
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.mdl.Arguments
import org.ddmore.mdl.mdl.SameBlock
import org.ddmore.mdl.validation.DistributionValidator
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.ParameterObject
import static extension eu.ddmore.converter.mdl2pharmml.Constants.*
import java.util.HashSet
import org.ddmore.mdl.validation.Utils
import org.ddmore.mdl.types.VariableType
import org.ddmore.mdl.mdl.IndividualVarType
import java.util.List
import org.ddmore.mdl.mdl.Vector
import org.ddmore.mdl.mdl.Primary
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.MOGObject

class ModelDefinitionPrinter {
	private var ModelObject mObj;
	private var ParameterObject pObj;
	private var String mObjName;
	private var String pObjName;

	protected extension DistributionPrinter = new DistributionPrinter();
	protected extension MathPrinter mathPrinter = null;
	protected extension ReferenceResolver resolver = null;
	
	new(MathPrinter mathPrinter, ReferenceResolver resolver){
		this.mathPrinter = mathPrinter;
		this.resolver = resolver;
	}		
	
	//////////////////////////////////////
	// I. Model Definition
	//////////////////////////////////////
	
	//Print <ModelDefinition>
	def print_mdef_ModelDefinition(MOGObject mog){
		this.mObj = mog.getModelObject.modelObject;
		this.pObj = mog.getParameterObject.parameterObject;
		this.mObjName = mog.getModelObject.objectName.name;
		this.pObjName = mog.getParameterObject.objectName.name;
		
		'''
		<ModelDefinition xmlns="«xmlns_mdef»">
			«print_mdef_VariabilityModel»
			«print_mdef_CovariateModel»
			«print_mdef_ParameterModel»
			«print_mdef_StructuralModel»
			«print_mdef_ObservationModel»
		</ModelDefinition>
		'''
	}
	//////////////////////////////////////
	// I.a Function Definition (not used) - call from here if needed
	//////////////////////////////////////

	/////////////////////////
	// I.b Variability Model
	/////////////////////////	
	
	protected def print_mdef_VariabilityModel(){
		var model = "";
		var errorVars = vm_err_vars.get(mObjName);
		if (errorVars != null){
			model = model + 
			'''
				<VariabilityModel blkId="vm_err.«mObjName»" type="«VAR_TYPE_ERROR»">
					«FOR s: errorVars»
						<Level symbId="«s»"/>
					«ENDFOR»
				</VariabilityModel>
			'''		
		}		
		var mdlVars = vm_mdl_vars.get(mObjName);
		if (mdlVars != null){
			model = model + 
			'''
				<VariabilityModel blkId="vm_mdl.«mObjName»" type="«VAR_TYPE_PARAMETER»">
					«FOR s: mdlVars»
						<Level symbId="«s»"/>
					«ENDFOR»
				</VariabilityModel>
			'''		
		}
		return model;
	}
    
	/////////////////////////
	// I.c Covariate Model
	/////////////////////////
	
	//INDIVIDUAL_VARIABLES, use=covariate -> CovariateModel (transformation with reference)
	//GROUP_VARIABLES -> ParameterModel, SimpleParameter + expression (see I.d)
	protected def print_mdef_CovariateModel(){
		var model = "";
		if (mObj != null){
			var covariateVars = cm_vars.get(mObjName);
			if (covariateVars != null){
				model = model +
				'''
				<CovariateModel blkId="cm.«mObjName»">
					«FOR s: covariateVars»
						<Covariate symbId="«s»">
							<Continuous>
								<Transformation>
									<math:Equation>
										«print_ct_SymbolRef(s)»
									</math:Equation>
								</Transformation>
							</Continuous>	
						</Covariate>
					«ENDFOR»
				</CovariateModel>
				'''
			}
		}
		return model;
	}	

	/////////////////////////////
	// I.d Parameter Model
	////////////////////////////	
		
	//Parameter object, STRUCTURAL + VARIABILITY -> ParameterModel, SimpleAttribute  
	//RANDOM_VARIABBLES_DEFINITION -> ParameterModel, RandomVariable
	protected def print_mdef_ParameterModel(){		
		var model = "";
		if (pObj != null){
			var statements = "";
			for (b: pObj.blocks){
				//Parameter object, STRUCTURAL
				if (b.structuralBlock != null){
					for (id: b.structuralBlock.parameters) 
						if (id.symbolName != null)
							statements = statements + 
							'''<SimpleParameter symbId = "«id.symbolName.name»"/>
							'''
		  		}
		  		//ParameterObject, VARIABILITY
		  		if (b.variabilityBlock != null){
					for (st: b.variabilityBlock.statements){
						if (st.parameter != null)
							if (st.parameter.symbolName != null)
								statements = statements + 
								'''<SimpleParameter symbId = "«st.parameter.symbolName.name»"/>
								'''
					}
					statements = statements + print_mdef_CollerationModel; 
		  		}
		  		
		  	}
		  	if (statements.length > 0){
		  		model = model + 
				'''
					<ParameterModel blkId="pm.«pObjName»">
						«statements»
					</ParameterModel>
				''';
			}
		}
		if (mObj != null){
			var statements = "";
			for (b: mObj.blocks){
				//Model object, GROUP_VARIABLES (covariate parameters)
				if (b.groupVariablesBlock != null){
					for (st: b.groupVariablesBlock.statements){
						if (st.statement != null){
							statements = statements + st.statement.print_BlockStatement("SimpleParameter", false);
						}							
					}
				}	
				//Model object, RANDOM_VARIABLES_DEFINITION
				if (b.randomVariableDefinitionBlock != null){
					for (s: b.randomVariableDefinitionBlock.variables){
						if (s.symbolName != null)
							if (isIndependentVariable(s.symbolName.name))
								statements = statements + s.print_mdef_RandomVariable;
					} 
		  		}
		  		//Model object, INDIVIDUAL_VARIABLES
				if (b.individualVariablesBlock != null){
					for (s: b.individualVariablesBlock.statements){
						statements = statements + s.print_BlockStatement("IndividualParameter", false);
					} 
		  		}
		  	}
	  		if (statements.length > 0){
		  		model = model + 
				'''
					<ParameterModel blkId="pm.«mObjName»">
						«statements»
					</ParameterModel>
				''';
			}
  		}
  		return model;
	}
	
	protected def print_mdef_RandomVariable(SymbolDeclaration s)
	'''
		«IF s.randomList != null»
			«IF s.symbolName != null»
				<RandomVariable symbId="«s.symbolName.name»">
					«s.print_VariabilityReference»
					«print_uncert_Distribution(s.randomList)»
				</RandomVariable>
			«ENDIF»
		«ENDIF»
	'''
	
	/////////////////////////
	// I.e Structural Model
	/////////////////////////
	
	//+ STRUCTURAL_PARAMETER -> <StructuralModel>
	protected def print_mdef_StructuralModel(){
		var model ="";
		if (mObj != null){
			var variables = "";
			for (b: mObj.blocks){
				if (b.modelPredictionBlock != null){
					for (st: b.modelPredictionBlock.statements){
						if (st.statement != null) {
							variables = variables + '''«st.statement.print_BlockStatement("ct:Variable", true)»''';
						} else 
							if (st.odeBlock != null){
								for (s: st.odeBlock.statements){
									variables = variables + '''«s.print_BlockStatement("ct:Variable", true)»''';	
								}
							}
					}
				}
			}
			model = model + 
			'''
				«IF (variables.length > 0)»
					<StructuralModel blkId="sm.«mObjName»">
						«IF (variables.length > 0)»«variables»«ENDIF»
					</StructuralModel>
				«ENDIF»
			'''
		}
		return model;
	}
		
	/////////////////////////////
	// I.f Observation Model
	/////////////////////////////
	protected def print_mdef_ObservationModel(){
		var model = "";
		if (mObj != null){
			var statements = "";
			for (b: mObj.blocks){
				if (b.observationBlock != null){
					for (st: b.observationBlock.statements){
						statements = statements + '''«st.print_mdef_ObservationModel»''';
					}
				}
			}
			model = model +
			'''
				«IF (statements.length > 0)»
					<ObservationModel blkId="om.«mObjName»">
						«statements»
					</ObservationModel>
				«ENDIF»
			'''				
		}
		return model;
	}
	
	//Print splitting random variables and simple parameters, needed for ObservationModel 
	protected def print_mdef_ObservationModel(BlockStatement st)'''
		«IF st.symbol != null»
			«st.symbol.print_mdef_ObservationModel»
		«ENDIF»
		«IF st.statement != null»
		«ENDIF»
	'''
	//TODO print conditionally defined observation models «st.statement.print_ConditionalStatement(tag)»
		
	//Print observation model declaration
	protected def print_mdef_ObservationModel(SymbolDeclaration s)'''
		«IF s.expression != null»
			«var classifiedVars = getReferences(s.expression)»
			«IF classifiedVars.size > 0»
				«FOR ss: classifiedVars.entrySet.filter[x | x.value.equals("random")]»
					«val ref = ss.key.defineDistribution»
					«IF ref != null»
						«ref.print_mdef_RandomVariable»
					«ENDIF»
				«ENDFOR»
			«ENDIF»
			«IF s.symbolName != null»
				<General symbId="«s.symbolName.name»">
					«print_Assign(s.expression)»
				</General>
			«ENDIF»
		«ENDIF»
	'''	
	
	//TODO: add blkIdRef
	protected def print_VariabilityReference(SymbolDeclaration s)'''
		«IF s.randomList != null»
			«val level = s.randomList.arguments.getAttribute(AttributeValidator::attr_level.name)»
			«IF level.length > 0»
				<ct:VariabilityReference>
					«print_ct_SymbolRef(level)»
				</ct:VariabilityReference>
			«ENDIF»
		«ENDIF»
	'''
	
	/////////////////////
	// I.g Error Model
	/////////////////////
	//For named arguments - reorder and match declaration!	
	/*def print_mdef_ErrorModel(Expression expr)'''
    <ErrorModel>
    	«IF expr != null»
    		«print_Assign(expr)»
    	«ENDIF»
    </ErrorModel>
	'''*/
		
	//+			
	/*def print_InitialCondition(SymbolDeclaration s)'''
		«IF s.expression != null»
			«IF s.expression.odeList != null»
				«var init = getAttributeExpression(s.expression.odeList.arguments, AttributeValidator::attr_init.name)»
				«IF init != null»
					«IF init.expression != null»
						<InitialCondition symbID="«s.name»">
							«print_Math_Expr(init.expression)»
						</InitialCondition>
					«ENDIF»	
				«ENDIF»
			«ENDIF»		
		«ENDIF»
	'''*/
	
	//+ returns distribution for the first declaration with a given name
	protected def defineDistribution(String ref){
		//find paramName in RANDOM_VARIABLES_DEFINITION
		if (mObj != null){
			for (b: mObj.blocks){
				if(b.randomVariableDefinitionBlock != null){
					for (s: b.randomVariableDefinitionBlock.variables){
						if (s.symbolName != null)
							if (s.symbolName.name.equals(ref))
								return s;
					}
				}
			}
		}
		return null;
	}
	
	protected def print_BlockStatement(BlockStatement st, String initTag, Boolean printType){
		var tag = initTag;
		if (st.symbol != null && st.symbol.symbolName != null)
			if ((tag.indexOf("Variable") > 0) && deriv_vars.contains(st.symbol.symbolName.name))
				tag = "ct:DerivativeVariable";
		'''
		«IF st.symbol != null»
			<«tag» symbId="«st.symbol.symbolName.name»"«IF printType» symbolType="«TYPE_REAL»"«ENDIF»>
				«IF st.symbol.expression != null»
					«IF st.symbol.expression != null»
						«print_Assign(st.symbol.expression)»
					«ENDIF»
				«ENDIF»
				«IF st.symbol.list != null»
					«IF st.symbol.list != null»
						«print_List(st.symbol.list)»
					«ENDIF»
				«ENDIF»
			</«tag»>
		«ENDIF»
		«IF st.statement != null»
			«st.statement.print_ConditionalStatement(tag)»
		«ENDIF»
		'''
	}
	
	//Transform lists to PharmML
	def print_List(org.ddmore.mdl.mdl.List list){
		var assign = "";
		//Categorical variables
		val type = list.arguments.getAttribute(AttributeValidator::attr_cc_type.name);
		if (type.equals(VariableType::CC_CATEGORICAL)){
			val define = list.arguments.getAttributeExpression(AttributeValidator::attr_define.name);
			if (define.list != null){
				assign = '''«define.list.print_Categorical»'''
			}
		} else {
		//Derivative variables	
			val deriv = list.arguments.getAttributeExpression(AttributeValidator::attr_req_deriv.name);
			if (deriv != null)
				assign = '''«deriv.print_Math_Expr»'''
		} 
		if (assign.length > 0) return
		'''
			<ct:Assign>
				<Equation xmlns="«xmlns_math»">
					«assign»
				</Equation>
			</ct:Assign>	
		'''	
		//Gaussian models
		if (type.equals(IndividualVarType::GAUSSIAN.toString) || 
			type.equals(IndividualVarType::LINEAR.toString)){
			val trans = list.arguments.getAttribute(AttributeValidator::attr_trans.name);
			val ranEff = list.arguments.getAttributeExpression(AttributeValidator::attr_ranEff.name);
			var ranEffExpr = '''''';
			if (ranEff != null){
				if (ranEff.expression != null) 
					ranEffExpr = '''«ranEff.expression.print_Math_Expr»'''
				if (ranEff.vector != null) ranEff.vector.print_ct_Vector;
			}
			var covariateContent = '''''';
			var popContent = '''''';
			var covariateType = "GeneralCovariate";
			if (type.equals(IndividualVarType::LINEAR.toString)) {
				covariateType = "LinearCovariate";		
				val pop = list.arguments.getAttributeExpression(AttributeValidator::attr_pop.name);
				if (pop != null)
					popContent = '''«pop.print_PopulationParameter»''';
				val cov = list.arguments.getAttributeExpression(AttributeValidator::attr_cov.name);
				val fixEff = list.arguments.getAttributeExpression(AttributeValidator::attr_fixEff.name);
				if (cov != null && fixEff != null)
					covariateContent = '''«print_Covariate(cov.vector, fixEff.vector)»''';
			} else {
				val group = list.arguments.getAttributeExpression(AttributeValidator::attr_group.name);
				if (group != null)
					covariateContent = '''«group.print_Assign»''';
			}
			return 
			'''
			<GaussianModel>
				«IF trans.length > 0»
					<Transformation>«trans»</Transformation>
				«ENDIF»
				<«covariateType»>
					«popContent»
					«covariateContent»
				</«covariateType»>
				«IF ranEffExpr.length > 0»
					<RandomEFfect>
						«ranEffExpr»
					<RandomEffect>
				«ENDIF»
			</GaussianModel>
			'''
		}
	}
	
	protected def print_PopulationParameter(AnyExpression pop)'''
		<PopulationParameter>
			«pop.print_Assign»
		</PopulationParameter>
	'''
	
	protected def print_Covariate(Vector cov, Vector ranEff){
		var res = '''''';
		for (i: 0..cov.values.size){
			if (ranEff.values.size() > i){
				val covValue = cov.values.get(i);
				val ranEffValue = ranEff.values.get(i);
				res = res + print_Covariate(covValue, ranEffValue);
			}
		}
		return res;
	}
	
	protected def print_Covariate(Primary cov, Primary ranEff)'''
		«IF cov != null»
			<Covariate>
				«cov.print_Math_Primary»
				<FixedEffect>
					«ranEff.print_Math_Primary»
				</FixedEffect>
			</Covariate>	
		«ENDIF»
	'''
	
	/////////////////////////////
	// I.i CorrelationModel
	/////////////////////////////
	protected def print_mdef_CollerationModel(){
		var model = "";
		if (pObj != null){
			for (b: pObj.blocks){
				if (b.variabilityBlock != null){			
					for (c: b.variabilityBlock.statements){
						var matrix = 
						'''
							«IF c.matrixBlock != null && c.matrixBlock.parameters != null»
								«var argumentNames = Utils::getArgumentNames(c.matrixBlock.parameters)»
								«print_mdef_Correlation_VarRef(c.matrixBlock.arguments, argumentNames)»
								«print_mdef_Matrix(c.matrixBlock.arguments, c.matrixBlock.parameters)»
							«ENDIF»
							«IF c.diagBlock != null && c.diagBlock.parameters != null»
								«print_mdef_Correlation_VarRef(c.diagBlock.arguments, Utils::getArgumentNames(c.diagBlock.parameters))»
								«print_mdef_Matrix(c.diagBlock.arguments, c.diagBlock.parameters)»
							«ENDIF»
							«IF c.sameBlock != null»
								«print_mdef_Correlation_VarRef(c.sameBlock.arguments, Utils::getSymbolNames(c.sameBlock.parameters))»
								«print_mdef_Same(c.sameBlock)»
							«ENDIF»
						'''
						if (matrix.length > 0)
							model = model +
							'''
								<Correlation>
									«matrix»
								</Correlation>
							'''				
					}
				}
			}
		}
		return model;
	}
	
	protected def print_mdef_Correlation_VarRef(Arguments arguments, List<String> paramNames){
		if (paramNames != null){
			var varRef = "";
			var randomVars = new HashSet<String>();
			val matrixType = arguments.getAttribute(AttributeValidator::attr_re_type.name);
			for (paramName: paramNames){
				var s = paramName.getRandomVariableByVariability(matrixType);
				if (s != null){
					if (s.symbolName != null){
						if (s.randomList != null){
							val level = getAttribute(s.randomList.arguments, AttributeValidator::attr_level.name);
							if (level.length > 0 && !randomVars.contains(level)){
								varRef = varRef + 
								'''
									<ct:VariabilityReference>
										«print_ct_SymbolRef(level)»
									</ct:VariabilityReference>
								'''
								randomVars.add(level);
							}
						}
					}
				}	
			}
			return varRef;
		}
	}
	
	protected def print_mdef_Matrix(Arguments arguments, Arguments parameters){
		if (parameters != null){
			var rowNames = "";
			val matrixType = arguments.getAttribute(AttributeValidator::attr_re_type.name);
			for (symbol: parameters.arguments){
				if (symbol.argumentName != null){
					rowNames = rowNames + print_ct_SymbolRef(pObjName, symbol.argumentName.name);
				}
			}
			return print_ct_Matrix(matrixType.convertMatrixType, rowNames, parameters, false);
		}
	}
	
	protected def print_mdef_Same(SameBlock same){
		if (same.parameters != null){
			var rowNames = "";
			for (symbol: same.parameters.symbolNames){
				if (symbol != null){
					rowNames = rowNames + print_ct_SymbolRef(pObjName, symbol.name);
				}
			}	
			val sameName = getAttribute(same.arguments, AttributeValidator::attr_name.name);
			if (sameName.length > 0){
				var Arguments parameters = null;
				var String matrixType = null;
				if (pObj != null){
					for (b: pObj.blocks){
						if (b.variabilityBlock != null){
							for (c: b.variabilityBlock.statements){
								if (c.diagBlock != null){
									val name = getAttribute(c.diagBlock.arguments, AttributeValidator::attr_name.name);
									if (name.equals(sameName)) {
										parameters = c.diagBlock.parameters;
										matrixType = getAttribute(c.diagBlock.arguments, AttributeValidator::attr_re_type.name);
									}
								}
								if (c.matrixBlock != null){
									val name = getAttribute(c.matrixBlock.arguments, AttributeValidator::attr_name.name);
									if (name.equals(sameName)) {
										parameters = c.matrixBlock.parameters;
										matrixType = getAttribute(c.matrixBlock.arguments, AttributeValidator::attr_re_type.name);
									}
								}
							}
						}
					}
				}	
				if (parameters != null){
					return print_ct_Matrix(matrixType.convertMatrixType, rowNames, parameters, true);
				}
			}
		}
	}
	
	protected def getRandomVariableByVariability(String varName, String type) {
		var attrName = DistributionValidator::attr_var.name;
		if (type.convertMatrixType.equals(MATRIX_STDEV))
			attrName = DistributionValidator::attr_sd.name;
		if (mObj != null){
			for (b: mObj.blocks){
				if (b.randomVariableDefinitionBlock != null){
					for (s: b.randomVariableDefinitionBlock.variables){
						if (s.randomList != null){
							var variance = getAttribute(s.randomList.arguments, attrName);	
							if (variance.equals(varName))
								return s;
						}
					}
				}
			}
		}
		return null;
	}
}


	
