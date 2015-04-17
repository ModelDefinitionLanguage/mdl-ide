package eu.ddmore.converter.mdl2pharmml
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.mdl.ModelObject
import static extension eu.ddmore.converter.mdl2pharmml.Constants.*
import org.ddmore.mdl.mdl.IndividualVarType
import org.ddmore.mdl.mdl.MOGObject
import org.ddmore.mdl.mdl.VariabilityType
import org.ddmore.mdl.validation.Utils
import org.ddmore.mdl.mdl.VectorExpression
import org.ddmore.mdl.mdl.Expression
import java.util.Comparator
import java.util.Map
import java.util.TreeMap
import java.util.ArrayList
import org.ddmore.mdl.mdl.Vector

class ModelDefinitionPrinter {
	protected extension DistributionPrinter distrPrinter = DistributionPrinter::getInstance();
	protected extension PKMacrosPrinter pkPrinter = null;
	protected extension MathPrinter mathPrinter = null;
	protected extension ReferenceResolver resolver = null;
	
	new(MathPrinter mathPrinter, ReferenceResolver resolver){
		this.mathPrinter = mathPrinter;
		this.resolver = resolver;
		pkPrinter = new PKMacrosPrinter(mathPrinter, resolver);
	}		
	
	//////////////////////////////////////
	// I. Model Definition
	//////////////////////////////////////	
	def print_mdef_ModelDefinition(MOGObject mog){
		var objects = Utils::getMOGObjects(mog);
		var mObj = Utils::getModelObject(objects);
		if (mObj == null) return '''''';
		'''
		<ModelDefinition xmlns="«xmlns_mdef»">
			«print_mdef_VariabilityModel»
			«mObj.print_mdef_CovariateModel»
			«mObj.print_mdef_ParameterModel»
			«mObj.print_mdef_StructuralModel»
			«mObj.print_mdef_ObservationModel»
		</ModelDefinition>
		'''
	}

	/////////////////////////
	// I.b Variability Model
	/////////////////////////	
	protected def print_mdef_VariabilityModel(){
		var model = "";
		if (vm_err_vars.size() > 0){
			model = model + vm_err_vars.print_mdef_VariabilityModel("vm_err", VAR_TYPE_ERROR);
		}		
		if (vm_mdl_vars.size() > 0){
			model = model + vm_mdl_vars.print_mdef_VariabilityModel("vm_mdl", VAR_TYPE_PARAMETER);
		}
		return model;
	}
	
	protected def print_mdef_VariabilityModel(Map<String, Integer> vars, String blkId, String varType){
		var model = "";
		if (vars.size() > 0){
			var bvc =  new ValueComparator(vars);
			var sorted_map = new TreeMap<String, Integer>(bvc);
			sorted_map.putAll(vars);
			var prev = "";
			var levels = "";
			for (s: sorted_map.entrySet){
				levels = levels +	'''
					«IF prev.length > 0»
						<Level referenceLevel="«IF s.value == 2»true«ELSE»false«ENDIF»" symbId="«s.key»">
							<ParentLevel>
								<ct:SymbRef symbIdRef="«prev»"/>
							</ParentLevel>
						</Level>
					«ELSE»
						<Level referenceLevel="«IF s.value == 2»true«ELSE»false«ENDIF»" symbId="«s.key»"/>
					«ENDIF»
				'''
				prev = s.key
			}			
			model = '''
				<VariabilityModel blkId="«blkId»" type="«varType»">
					«levels»
				</VariabilityModel>
			'''		
		}		
		return model;
	}
	
	protected def print_VariabilityReference(String level)'''
		<ct:VariabilityReference>
			«level.print_ct_SymbolRef»
		</ct:VariabilityReference>
	'''
    
	/////////////////////////
	// I.c Covariate Model
	/////////////////////////	
	//CovariateModel (transformation with reference)
	protected def print_mdef_CovariateModel(ModelObject mObj){
		var model = "";
		var skipped = new ArrayList<String>();
		//First print transformed covariates (and exclude them from the list to avoid double defintiion) 
		for (b: mObj.blocks){
			if (b.covariateBlock != null){
				for (s: b.covariateBlock.variables){
					var covName = s.symbolName.name;
					var covType = "Continuous";
					//Print transformed covariates
					var transformation = "";
					if (s.expression != null){
						var dependencies = Utils::getDependencies(s.expression);
						var isTransformed = false;
						var continue = true; //no 'break' command in xText
						for (v: dependencies){
							if (cm_vars.contains(v) && continue){
								isTransformed = true;
								covName = v;
								skipped.add(covName); 
								continue = false;
							} 
						}
						if (isTransformed){
							transformation =  '''
								<«covType»>
									<Transformation>
									    <TransformedCovariate symbId="«s.symbolName.name»"></TransformedCovariate>
										«s.expression.print_Math_Equation»
									</Transformation>
								</«covType»>
								'''
								skipped.add(s.symbolName.name);
							}
					}
					model = model + '''
					«IF transformation.length > 0»
						<Covariate symbId="«covName»">
							«transformation»
						</Covariate>
					«ENDIF»	
					'''
				}
			}
		}
		//Then print all remaining covariates
		for (b: mObj.blocks){
			if (b.covariateBlock != null){
				for (s: b.covariateBlock.variables){
					var covName = s.symbolName.name;
					if (!skipped.contains(covName)){
						var covType = "Continuous";
						//Print categorical covariates
						var categorical = "";
						if (s.list != null){
							var type = getAttributeExpression(s.list.arguments, AttributeValidator::attr_type.name);
							if (type.isCategorical){
								covType = "Categorical";
								categorical = '''«type.print_Categorical»''';
							}
						}
						model = '''
						<Covariate symbId="«covName»">
							«IF categorical.length > 0»
								«categorical»
							«ELSE»
								<«covType»/>
							«ENDIF»
						</Covariate>
						''' + model;
					}
				}
			}
		}
		if (model.length > 0){
			model = '''
				<CovariateModel blkId="cm">
					«model»
				</CovariateModel>
			'''
		}
		return model;
	}	
		
	/////////////////////////////
	// I.d Parameter Model
	////////////////////////////	
	protected def print_mdef_ParameterModel(ModelObject mObj){		
		var statements = "";
		if (mObj != null){
			for (b: mObj.blocks){
				//STRUCTURAL_PARAMETERS
				if (b.structuralParametersBlock != null){
					for (id: b.structuralParametersBlock.parameters) 
						if (id.symbolName != null)
							statements = statements + 
							'''<SimpleParameter symbId = "«id.symbolName.name»"/>
							'''
		  		}
		  		//VARIABILITY_PARAMETERS
		  		if (b.variabilityParametersBlock != null){
					for (id: b.variabilityParametersBlock.parameters){
						if (id.symbolName != null)
							statements = statements + 
							'''<SimpleParameter symbId = "«id.symbolName.name»"/>
							'''
					}
		  		}
		  		//GROUP_VARIABLES (covariate parameters)
				if (b.groupVariablesBlock != null){
					for (st: b.groupVariablesBlock.statements){
						if (st.variable != null){
							statements = statements + st.variable.print_SymbolDeclaration("SimpleParameter", false);
						}							
					}
				}	
		  	}
			for (b: mObj.blocks){
				//RANDOM_VARIABLES_DEFINITION
				if (b.randomVariableDefinitionBlock != null){
					if (b.randomVariableDefinitionBlock.arguments != null){
						var level = b.randomVariableDefinitionBlock.arguments.getAttribute(AttributeValidator::attr_level_ref.name);
						if (level.length > 0){
							for (s: b.randomVariableDefinitionBlock.variables){
								if (s.symbolName != null)
									statements = statements + s.print_mdef_RandomVariable(level);
							} 
						}
					}
		  		}
		  		//INDIVIDUAL_VARIABLES
				if (b.individualVariablesBlock != null){
					for (s: b.individualVariablesBlock.variables){
						statements = statements + s.print_SymbolDeclaration("IndividualParameter", false);
					} 
		  		}
		  	}
  		}
  		statements = statements + print_mdef_CollerationModel(mObj); 
	  	if (statements.length > 0){
			'''
				<ParameterModel blkId="pm">
					«statements»
				</ParameterModel>
			''';
		}
	}
	
	/////////////////////////////
	// I.d_1 CorrelationModel
	/////////////////////////////
	protected def print_mdef_CollerationModel(ModelObject mObj){
		var model = "";
		if (mObj != null){
			for (b: mObj.blocks){
				if (b.randomVariableDefinitionBlock != null){	
					if (b.randomVariableDefinitionBlock.arguments != null){
						var level =	b.randomVariableDefinitionBlock.arguments.getAttribute(AttributeValidator::attr_level_ref.name);	
						for (s: b.randomVariableDefinitionBlock.variables){
							if (s.symbolName.name != null && s.list != null){
								model = model + s.print_mdef_Correlation(level);	
							}
						}
					}
				}
				//TODO: possibly all these attributes will be moved to VARIABILITY_PARAMETERS block of the parameter object
				if (b.variabilityParametersBlock != null){	
					for (s: b.variabilityParametersBlock.parameters){
						if (s.symbolName.name != null && s.list != null){
							val matrixType = s.list.arguments.getAttribute(AttributeValidator::attr_type_randomEff.name);
							if (matrixType.length > 0){
								val values = s.list.arguments.getAttributeExpression(AttributeValidator::attr_value.name);
								val params = s.list.arguments.getAttributeExpression(AttributeValidator::attr_params.name);
								if (values != null && params != null && values.vector != null && params.vector != null){
									//TODO: link the definition to the level
									matrixType.print_mdef_Correlation_Matrix(values.vector, params.vector, null);
								}
							}
						}
					}
				}
				
			}
		}
		return model;
	}
	
	def print_mdef_Correlation_Matrix(String matrixType, Vector values, Vector params, String level)'''
		<Correlation deviationMatrixType="«matrixType.convertMatrixType»">
			«IF level != null»
				«level.print_VariabilityReference»
			«ENDIF»
            «values.print_ct_Matrix(params, "Any")»
		</Correlation>
	'''
	
	def print_mdef_Correlation(SymbolDeclaration s, String level){
		var res = ""
		val type = s.list.arguments.getAttribute(AttributeValidator::attr_type_randomEff.name);
		val rv1 = s.list.arguments.getAttribute(AttributeValidator::attr_rv1.name);
		val rv2 = s.list.arguments.getAttribute(AttributeValidator::attr_rv2.name);
		if (rv1.length > 0)
			res = res +
			'''
			<RandomVariable1>
				«rv1.print_ct_SymbolRef»
			</RandomVariable1>
			'''
		if (rv2.length > 0)
			res  = res +  
			'''
			<RandomVariable2>
				«rv2.print_ct_SymbolRef»
			</RandomVariable2>
			'''
		if (type.equals(VariabilityType::COV.toString)){
			res  = res + '''
			<Covariance>
				«s.symbolName.name.print_ct_SymbolRef»
			</Covariance>
			'''
		}
		if (type.equals(VariabilityType::CORR.toString)){
			res  = res + '''
			<CorrelationCoefficient>
				«s.symbolName.name.print_ct_SymbolRef»
			</CorrelationCoefficient>
			'''
		}
		if (res.length > 0 && level.length > 0)
		'''
		<Correlation>
			«level.print_VariabilityReference»
			<Pairwise>
				«res»
			</Pairwise>
		</Correlation>	
		'''
	}
	
	protected def print_mdef_RandomVariable(SymbolDeclaration s, String level)'''
		«IF s.randomList != null && s.symbolName != null»
			<RandomVariable symbId="«s.symbolName.name»">
				«IF level.length > 0»
					«level.print_VariabilityReference»
				«ENDIF»
				«print_uncert_Distribution(s.randomList)»
			</RandomVariable>
		«ENDIF»
	'''
	
	/////////////////////////
	// I.e Structural Model
	/////////////////////////
	protected def print_mdef_StructuralModel(ModelObject mObj){
		var model ="";
		if (mObj != null){
			//Start from PKMACRO / COMPARTMENT definitions
			var variables = "";
			for (b: mObj.blocks){
				if (b.modelPredictionBlock != null){
					for (st: b.modelPredictionBlock.statements){
						//MODEL_PREDICTION
						if (st.variable != null) 
							variables = variables + '''«st.variable.print_SymbolDeclaration("ct:Variable", true)»''';
						//ODE
						if (st.odeBlock != null){
							for (s: st.odeBlock.variables){
								if (s.list != null){
									if (s.list.arguments.getAttributeExpression(AttributeValidator::attr_deriv.name) != null){
										variables = variables + '''«s.print_SymbolDeclaration("ct:DerivativeVariable", true)»''';	
									}
								} else {
									variables = variables + '''«s.print_SymbolDeclaration("ct:Variable", true)»''';	
								}
							}
						}
						//LIBRARY
						if (st.libraryBlock != null){
							for (s: st.libraryBlock.statements)
								variables = variables + s.expression.print_Math_FunctionCall;
						} 
						//COMPARTMENT
						if (st.pkMacroBlock != null){
							for (s: st.pkMacroBlock.statements){
								if (s.variable != null)
									variables = variables + s.variable.print_PKMacros;
								if (s.list != null)
									variables = variables + s.list.print_PKMacros;
							}
						}
					}
				}
			}
			model = model + 
			'''
				«IF (variables.length > 0)»
					<StructuralModel blkId="sm">
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
	protected def print_mdef_ObservationModel(ModelObject mObj){
		var statements = "";
		if (mObj != null){
			for (b: mObj.blocks){
				if (b.observationBlock != null){
					for (st: b.observationBlock.variables){
						statements = statements + '''«st.print_mdef_ObservationModel»''';
					}
				}
			}
		}
		if (statements.length() > 0)
		'''
			«IF (statements.length > 0)»
				<ObservationModel blkId="om">
					«statements»
				</ObservationModel>
			«ENDIF»
		'''				
	}
	
	protected def print_mdef_ObservationModel(SymbolDeclaration s)'''
		«IF s.list != null»
			«s.print_mdef_StandardObservation»
		«ENDIF»
		«IF s.expression != null»
			«IF s.symbolName != null»
				<General symbId="«s.symbolName.name»">
					«s.expression.print_Assign»
				</General>
			«ENDIF»
		«ENDIF»
	'''	
	
	protected def print_mdef_StandardObservation(SymbolDeclaration s){
		if (s.list != null){
			val type = s.list.arguments.getAttributeExpression(AttributeValidator::attr_type.name);
			if (type.isContinuous){
				var name = "";
				if (s.symbolName != null) 
					name = s.symbolName.toStr 
				val error = s.list.arguments.getAttributeExpression(AttributeValidator::attr_error.name);
				val output = s.list.arguments.getAttribute(AttributeValidator::attr_prediction_ref.name);
				val eps = s.list.arguments.getAttribute(AttributeValidator::attr_eps.name);
				'''
					<Standard symbId="«name»">
						«IF output.length > 0»
							<Output>
								«output.print_ct_SymbolRef»
							</Output>
						«ENDIF»
						«IF error != null»
							<ErrorModel>
								«error.print_Assign»
							</ErrorModel>
						«ENDIF»
						«IF eps.length > 0»
							<ResidualError>
								«eps.print_ct_SymbolRef»
							</ResidualError>
						«ENDIF»
					</Standard>	
				'''
			}
		}
	}
	
	protected def print_SymbolDeclaration(SymbolDeclaration st, String tag, Boolean printType){
		if (st.symbolName != null)'''
			<«tag» symbId="«st.symbolName.name»"«IF printType» symbolType="«TYPE_REAL»"«ENDIF»>
				«IF st.expression != null»
					«st.expression.print_Assign»
				«ENDIF»
				«IF st.list != null»
					«st.list.print_List»
				«ENDIF» 
			</«tag»>
			'''
	}
	
	//Convert special types of lists to PharmML
	def print_List(org.ddmore.mdl.mdl.List list){
		var assign = "";
		var res = "";
		val type = list.arguments.getAttributeExpression(AttributeValidator::attr_type.name);
		if (type.isCategorical){
			//Categorical variables
			if (type.type.type.categories != null && type.type.type.categories.size > 0){
				assign = '''«type.print_Categorical»'''
			} else {
				val define = list.arguments.getAttributeExpression(AttributeValidator::attr_define.name);
				if (define.list != null) 
					assign = '''«define.list.print_Categorical»'''
			}
		} else {
			//Derivative variables	
			val deriv = list.arguments.getAttributeExpression(AttributeValidator::attr_deriv.name);
			if (deriv != null){
				assign = '''«deriv.print_Math_Expr»'''
				var wrtVar = independentVar;
				val wrtVarExpr = list.arguments.getAttributeExpression(AttributeValidator::attr_wrt.name);
				if (wrtVarExpr != null) wrtVar = wrtVarExpr.toStr; 
				res  = 	
				'''
					<ct:IndependentVariable>
						«wrtVar.print_ct_SymbolRef»
					</ct:IndependentVariable>
				'''	
				val initValue = list.arguments.getAttributeExpression(AttributeValidator::attr_init.name);
				val initTime = list.arguments.getAttributeExpression(AttributeValidator::attr_x0.name);
				var initValueRes = '''«AttributeValidator::attr_init.defaultValue.print_Assign»'''; 
				var initTimeRes = '''«AttributeValidator::attr_x0.defaultValue.print_Assign»'''; 
				if (initTime != null) initTimeRes = '''«initTime.print_Assign»'''; 
				if (initValue != null) initValueRes = '''«initValue.print_Assign»'''; 
				res = res + 
				'''
					<ct:InitialCondition>
						<ct:InitialValue>
							«initValueRes»
						</ct:InitialValue>
						<ct:InitialTime>
							«initTimeRes»
						</ct:InitialTime>
					</ct:InitialCondition>
				'''						
			}
		} 
		if (assign.length > 0) return 
		'''
			<ct:Assign>
				<Equation xmlns="«xmlns_math»">
					«assign»
				</Equation>
			</ct:Assign>	
		'''	+ res;
		if (type != null) {
			var modelType = type.toStr;
			//Gaussian models
			if (modelType.equals(IndividualVarType::GENERAL.toString) || modelType.equals(IndividualVarType::LINEAR.toString)){
				//Transformation
				val trans = list.arguments.getAttribute(AttributeValidator::attr_trans.name);
				//Covariate model
				var covariateContent = '''''';
				val cov = list.arguments.getAttributeExpression(AttributeValidator::attr_cov.name);
				val fixEff = list.arguments.getAttributeExpression(AttributeValidator::attr_fixEff.name);
				if (cov != null && fixEff != null){
					if (cov.vector!= null && fixEff.vector != null &&
						cov.vector.expression != null && fixEff.vector.expression != null)
						covariateContent = '''«print_Covariate(cov.vector.expression, fixEff.vector.expression)»'''
					else 
					if (cov.expression != null && fixEff.expression != null){
						covariateContent = '''«print_Covariate(cov.expression, fixEff.expression)»'''
					}	
				}	
				//Random effect
				val ranEff = list.arguments.getAttributeExpression(AttributeValidator::attr_ranEff.name);
				var ranEffExpr = '''''';
				if (ranEff != null){
					if (ranEff.expression != null) ranEffExpr = '''«ranEff.expression.print_Math_Expr»'''
					if (ranEff.vector != null) ranEff.vector.print_ct_Vector;
				}				
				//Population parameter
				var popContent = '''''';
				val pop = list.arguments.getAttributeExpression(AttributeValidator::attr_pop.name);
				if (pop != null) popContent = '''«pop.print_Assign»''';
				//General vs. linear model - differences
				var covariateType = "GeneralCovariate";
				if (modelType.equals(IndividualVarType::LINEAR.toString)) {
					covariateType = "LinearCovariate";	
					if (popContent != null) popContent = '''
						<PopulationParameter>
							«popContent»
						</PopulationParameter>
				''';
				}	
				//} else {
				//	val group = list.arguments.getAttributeExpression(AttributeValidator::attr_group.name);
				//	if (group != null) covariateContent = '''«group.print_Assign»''';
				//}
				return 
				'''
					<GaussianModel>
						«IF trans.length > 0»
							<Transformation>«trans»</Transformation>
						«ENDIF»
						«IF popContent.length > 0 || covariateContent.length > 0»
							<«covariateType»>
								«popContent»
								«covariateContent»
							</«covariateType»>
						«ENDIF»
						«IF ranEffExpr.length > 0»
							<RandomEffects>
								«ranEffExpr»
							</RandomEffects>
						«ENDIF»
					</GaussianModel>
				'''
			}
		}
	}
	
	protected def print_Covariate(VectorExpression cov, VectorExpression ranEff){
		var res = '''''';
		if (cov.expressions != null && ranEff.expressions != null){
			for (i: 0..cov.expressions.size){
				if (ranEff.expressions.size() > i){
					val covValue = cov.expressions.get(i);
					val ranEffValue = ranEff.expressions.get(i);
					res = res + print_Covariate(covValue, ranEffValue);
				}
			}
		}
		return res;
	}
	
	protected def print_Covariate(Expression cov, Expression ranEff)'''
		«IF cov != null»
			<Covariate>
				«cov.print_Math_Expr»
				<FixedEffect>
					«ranEff.print_Math_Expr»
				</FixedEffect>
			</Covariate>	
		«ENDIF»
	'''
}

//A helper class for sorting maps (according to their integer values) 
class ValueComparator implements Comparator<String> {
    var Map<String, Integer> base;
    new(Map<String, Integer> base) {
        this.base = base;
    }
    override compare(String a, String b) {
        if (base.get(a) >= base.get(b)) {
            return -1;
        } else {
            return 1;
        } 
    }
}


	
