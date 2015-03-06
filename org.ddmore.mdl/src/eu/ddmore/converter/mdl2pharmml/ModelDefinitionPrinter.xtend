package eu.ddmore.converter.mdl2pharmml
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.ParameterObject
import static extension eu.ddmore.converter.mdl2pharmml.Constants.*
import org.ddmore.mdl.types.VariableType
import org.ddmore.mdl.mdl.IndividualVarType
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.MOGObject
import org.ddmore.mdl.types.DefaultValues
import org.ddmore.mdl.mdl.VariabilityType
import org.ddmore.mdl.validation.Utils
import org.ddmore.mdl.mdl.VectorExpression
import org.ddmore.mdl.mdl.Expression

class ModelDefinitionPrinter {
	protected extension DistributionPrinter distrPrinter = new DistributionPrinter();
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
		var objects = Utils::getMOGObjects(mog);
		var mObj = Utils::getModelObject(objects);
		var pObj = Utils::getParameterObject(objects);
		'''
		<ModelDefinition xmlns="«xmlns_mdef»">
			«print_mdef_VariabilityModel»
			«print_mdef_CovariateModel(mObj)»
			«print_mdef_ParameterModel(mObj, pObj)»
			«print_mdef_StructuralModel(mObj)»
			«print_mdef_ObservationModel(mObj)»
		</ModelDefinition>
		'''
	}

	/////////////////////////
	// I.b Variability Model
	/////////////////////////	
	protected def print_mdef_VariabilityModel(){
		var model = "";
		if (vm_err_vars.size() > 0){
			model = model + 
			'''
				<VariabilityModel blkId="vm_err" type="«VAR_TYPE_ERROR»">
					«FOR s: vm_err_vars»
						<Level symbId="«s»"/>
					«ENDFOR»
				</VariabilityModel>
			'''		
		}		
		if (vm_mdl_vars.size() > 0){
			model = model + 
			'''
				<VariabilityModel blkId="vm_mdl" type="«VAR_TYPE_PARAMETER»">
					«FOR s: vm_mdl_vars»
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
	
	//CovariateModel (transformation with reference)
	protected def print_mdef_CovariateModel(ModelObject mObj){
		var model = "";
		if (mObj != null){
			if (cm_vars.size() > 0){
				model = model +
				'''
				<CovariateModel blkId="cm">
					«FOR s: cm_vars»
						«val covType = s.getCovariateType(mObj)»
						<Covariate symbId="«s»">
							«var transformation = s.getCovariateTransformation(mObj)»	
							«IF transformation != null»
								<«covType»>
									<Transformation>
										«transformation.print_Math_Equation»
									</Transformation>
								</«covType»>
							«ELSE»
								<«covType»/>
							«ENDIF»
						</Covariate>
					«ENDFOR»
				</CovariateModel>
				'''
			}
		}
		return model;
	}	
	
	protected def getCovariateType(String covVar, ModelObject mObj){
		for (b: mObj.blocks){
			if (b.covariateBlock != null){
				for (s: b.covariateBlock.variables){
					if (s.list != null){
						if (s.symbolName != null && covVar.equals(s.symbolName.name)){
							var type = getAttribute(s.list.arguments, AttributeValidator::attr_type.name);
							if (type.length > 0)
								return type.substring(0, 1).toUpperCase() + type.substring(1);
						}
					}
				}
			}						
		}
		return VariableType::CC_CONTINUOUS;
	}	
	
	protected def getCovariateTransformation(String covVar, ModelObject mObj){
		if (mObj != null)
			for (b: mObj.blocks){
				if (b.covariateBlock != null){
					for (s: b.covariateBlock.variables){
						if (s.symbolName != null && covVar.equals(s.symbolName.name)){
							return s.expression;
						}
					}
				}					
			}
		return null;
	}	
	
	/////////////////////////////
	// I.d Parameter Model
	////////////////////////////	
		
	//Parameter object, STRUCTURAL + VARIABILITY -> ParameterModel - SimpleAttribute  
	//RANDOM_VARIABLES_DEFINITION -> ParameterModel - RandomVariable
	protected def print_mdef_ParameterModel(ModelObject mObj, ParameterObject pObj){		
		var statements = "";
		if (pObj != null){
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
					for (id: b.variabilityBlock.parameters){
						if (id.symbolName != null)
							statements = statements + 
							'''<SimpleParameter symbId = "«id.symbolName.name»"/>
							'''
					}
		  		}
		  	}
		}
		if (mObj != null){
			for (b: mObj.blocks){
				//Model object, GROUP_VARIABLES (covariate parameters)
				if (b.groupVariablesBlock != null){
					for (st: b.groupVariablesBlock.statements){
						if (st.variable != null){
							statements = statements + st.variable.print_BlockStatement("SimpleParameter", false);
						}							
					}
				}	
				//Model object, RANDOM_VARIABLES_DEFINITION
				if (b.randomVariableDefinitionBlock != null){
					if (b.randomVariableDefinitionBlock.arguments != null){
						var level = b.randomVariableDefinitionBlock.arguments.getAttribute(AttributeValidator::attr_block_level.name);
						if (level.length > 0){
							for (s: b.randomVariableDefinitionBlock.variables){
								if (s.symbolName != null)
									statements = statements + s.print_mdef_RandomVariable(level);
							} 
						}
					}
		  		}
		  		//Model object, INDIVIDUAL_VARIABLES
				if (b.individualVariablesBlock != null){
					for (s: b.individualVariablesBlock.variables){
						statements = statements + s.print_BlockStatement("IndividualParameter", false);
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
	
	protected def print_mdef_RandomVariable(SymbolDeclaration s, String level)
	'''
		«IF s.randomList != null && s.symbolName != null»
			<RandomVariable symbId="«s.symbolName.name»">
				«IF level.length > 0»
					«s.print_VariabilityReference(level)»
				«ENDIF»
				«print_uncert_Distribution(s.randomList)»
			</RandomVariable>
		«ENDIF»
	'''
	
	/////////////////////////
	// I.e Structural Model
	/////////////////////////
	
	//+ STRUCTURAL_PARAMETER -> <StructuralModel>
	protected def print_mdef_StructuralModel(ModelObject mObj){
		var model ="";
		if (mObj != null){
			var variables = "";
			for (b: mObj.blocks){
				if (b.modelPredictionBlock != null){
					for (st: b.modelPredictionBlock.statements){
						if (st.variable != null) {
							variables = variables + '''«st.variable.print_BlockStatement("ct:Variable", true)»''';
						} else 
							if (st.odeBlock != null){
								for (s: st.odeBlock.variables){
									variables = variables + '''«s.print_BlockStatement("ct:Variable", true)»''';	
								}
							} else {
								if (st.libraryBlock != null){
									for (s: st.libraryBlock.statements){
										variables = variables + s.expression.print_Math_FunctionCall;
									}
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
	
	//Print observation model declaration
	//TODO: where to get level for OBSERVATION?
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
		val type = s.list.arguments.getAttributeExpression(AttributeValidator::attr_type.name);
		if (type != null){
			if (type.toStr.equals(DefaultValues::VAR_CONTINUOUS)){
				var name = "";
				if (s.symbolName != null) 
					name = s.symbolName.name 
				else
					name = s.argumentName.toStr;
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
	
	protected def print_VariabilityReference(SymbolDeclaration s, String level){
		if (level.length > 0)
		'''
			<ct:VariabilityReference>
				«print_ct_SymbolRef(level)»
			</ct:VariabilityReference>
		'''
	}
	
	//+ returns distribution for the first declaration with a given name
	protected def defineDistribution(String ref, ModelObject mObj){
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
	
	protected def print_BlockStatement(SymbolDeclaration st, String initTag, Boolean printType){
		var tag = initTag;
		if (st.symbolName != null){
			if ((tag.indexOf("Variable") > 0) && deriv_vars.contains(st.symbolName.name))
				tag = "ct:DerivativeVariable";
			'''
			<«tag» symbId="«st.symbolName.name»"«IF printType» symbolType="«TYPE_REAL»"«ENDIF»>
				«IF st.expression != null»
					«print_Assign(st.expression)»
				«ENDIF»
				«IF st.list != null»
					«print_List(st.list)»
				«ENDIF»
			</«tag»>
			'''
		}
	}
	
	//Transform lists to PharmML
	def print_List(org.ddmore.mdl.mdl.List list){
		var assign = "";
		var res = "";
		//Categorical variables
		val type = list.arguments.getAttribute(AttributeValidator::attr_type.name);
		if (type.equals(VariableType::CC_CATEGORICAL)){
			val define = list.arguments.getAttributeExpression(AttributeValidator::attr_define.name);
			if (define.list != null) assign = '''«define.list.print_Categorical»'''
		} else {
		//Derivative variables	
			val deriv = list.arguments.getAttributeExpression(AttributeValidator::attr_deriv.name);
			if (deriv != null){
				assign = '''«deriv.print_Math_Expr»'''
				var independentVar = DefaultValues::INDEPENDENT_VAR;
				val independentVarExpr = list.arguments.getAttributeExpression(AttributeValidator::attr_wrt.name);
				if (independentVarExpr != null) independentVar = independentVarExpr.toStr; 
				res  = 	
				'''
					<ct:IndependentVariable>
						«independentVar.print_ct_SymbolRef»
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
		//Gaussian models
		if (type.equals(IndividualVarType::GENERAL.toString) || 
			type.equals(IndividualVarType::LINEAR.toString)){
			val trans = list.arguments.getAttribute(AttributeValidator::attr_trans.name);
			val ranEff = list.arguments.getAttributeExpression(AttributeValidator::attr_ranEff.name);
			var ranEffExpr = '''''';
			if (ranEff != null){
				if (ranEff.expression != null) ranEffExpr = '''«ranEff.expression.print_Math_Expr»'''
				if (ranEff.vector != null) ranEff.vector.print_ct_Vector;
			}
			var covariateContent = '''''';
			var popContent = '''''';
			var covariateType = "GeneralCovariate";
			if (type.equals(IndividualVarType::LINEAR.toString)) {
				covariateType = "LinearCovariate";		
				val pop = list.arguments.getAttributeExpression(AttributeValidator::attr_pop.name);
				if (pop != null) popContent = '''«pop.print_PopulationParameter»''';
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
			} else {
				val group = list.arguments.getAttributeExpression(AttributeValidator::attr_group.name);
				if (group != null) covariateContent = '''«group.print_Assign»''';
			}
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
	
	protected def print_PopulationParameter(AnyExpression pop)'''
		<PopulationParameter>
			«pop.print_Assign»
		</PopulationParameter>
	'''
	
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
	
	/////////////////////////////
	// I.i CorrelationModel
	/////////////////////////////
	protected def print_mdef_CollerationModel(ModelObject mObj){
		var model = "";
		if (mObj != null){
			for (b: mObj.blocks){
				if (b.randomVariableDefinitionBlock != null){	
					if (b.randomVariableDefinitionBlock.arguments != null){
						var level =	b.randomVariableDefinitionBlock.arguments.getAttribute(AttributeValidator::attr_block_level.name);	
						for (s: b.randomVariableDefinitionBlock.variables){
							if (s.symbolName.name != null && s.list != null){
								model = model + s.print_mdef_Correlation(level);	
							}
						}
					}
				}
			}
		}
		return model;
	}
	
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
			«s.print_VariabilityReference(level)»
			<Pairwise>
				«res»
			</Pairwise>
		</Correlation>	
		'''
	}
}


	
