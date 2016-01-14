package eu.ddmore.converter.mdl2pharmml

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.CategoricalDefinitionExpr
import eu.ddmore.mdl.mdl.CategoryValueReference
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.SymbolDefinition
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.ListDefinitionTable
import eu.ddmore.mdl.provider.SublistDefinitionProvider
import java.util.ArrayList
import java.util.Comparator
import java.util.HashMap
import java.util.List
import java.util.Map
import java.util.Set
import java.util.TreeMap
import org.eclipse.xtext.EcoreUtil2

import static eu.ddmore.converter.mdl2pharmml.Constants.*

import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToInteger
import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString
import eu.ddmore.mdl.utils.MdlUtils

class ModelDefinitionPrinter {
	extension MdlUtils mu = new MdlUtils
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension DistributionPrinter dp = new DistributionPrinter 
	extension PharmMLConverterUtils pcu = new PharmMLConverterUtils
	extension SublistDefinitionProvider sdp = new SublistDefinitionProvider
	extension FunctionDefinitionPrinter fdp = new FunctionDefinitionPrinter
	extension PKMacrosPrinter pkp = PKMacrosPrinter::INSTANCE
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	
	
	//////////////////////////////////////
	// I. Model Definition
	//////////////////////////////////////	
	def print_mdef_ModelDefinition(MclObject mObj, MclObject pObj){
		'''
		<ModelDefinition xmlns="«xmlns_mdef»">
			«mObj.print_mdef_VariabilityModel»
			«IF !mObj.mdlCovariateDefns.isEmpty»
				«mObj.print_mdef_CovariateModel»
			«ENDIF»
			«mObj.print_mdef_ParameterModel(pObj)»
			«IF !mObj.modelPredictionBlocks.isEmpty»
				«mObj.writeStructuralModel»
			«ENDIF»
			«IF !mObj.mdlObservations.isEmpty»
				«mObj.writeObservationModel»
			«ENDIF»
		</ModelDefinition>
		'''
	}

	/////////////////////////
	// I.b Variability Model
	/////////////////////////	
	def print_mdef_VariabilityModel(MclObject mObj){
		val vm_err_vars = new HashMap<String, Integer>
		val vm_mdl_vars = new HashMap<String, Integer>
		for(stmt : mObj.mdlVariabilityLevels){
			switch(stmt){
				ListDefinition:{
					if(stmt.list.getAttributeEnumValue('type') == 'parameter'){
						vm_mdl_vars.put(stmt.name, stmt.list.getAttributeExpression('level').convertToInteger)
					}
					else{
						vm_err_vars.put(stmt.name, stmt.list.getAttributeExpression('level').convertToInteger)
					}
				}
			}	
		}
		var model = "";
		if (vm_err_vars.size() > 0){
			model = model + vm_err_vars.print_mdef_VariabilityModel("vm_err", VAR_TYPE_ERROR);
		}		
		if (vm_mdl_vars.size() > 0){
			model = model + vm_mdl_vars.print_mdef_VariabilityModel("vm_mdl", VAR_TYPE_PARAMETER);
		}
		return model;
	}
	
	def print_mdef_VariabilityModel(Map<String, Integer> vars, String blkId, String varType){
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
	
    def getCategoryDefinitions(Expression expr){
    	val retVal = new ArrayList<String>
    	switch(expr){
    		CategoricalDefinitionExpr:
    			expr.categories.forEach[retVal.add(name)]
    	}
    	retVal
    }
    
    
	/////////////////////////
	// I.c Covariate Model
	/////////////////////////	
	//CovariateModel (transformation with reference)
	def print_mdef_CovariateModel(MclObject mObj){
		var model = "";
		var skipped = new ArrayList<SymbolDefinition>();
		val covDefns = mObj.mdlCovariateDefns
		//First print transformed covariates (and exclude them from the list to avoid double defintiion)
		for(s : covDefns){
			switch(s){
				EquationDefinition case(s.expression != null):{
					var transformation = "";
					var dependencies = s.expression.getCovariateDependencies;
					var SymbolDefinition transformedCov = null 
					var continue = true; //no 'break' command in xText
					for (v: dependencies){
						if (covDefns.exists[it == v] && continue){
							transformedCov = v
							skipped.add(v); 
							continue = false;
						} 
					}
					if (transformedCov != null){
						transformation =  '''
							<Continuous>
								<Transformation>
								    <TransformedCovariate symbId="«s.name»"/>
									«s.expression.expressionAsEquation»
								</Transformation>
							</Continuous>
							'''
							skipped.add(s);
					}
					model = model + '''
					«IF transformation.length > 0»
						<Covariate symbId="«transformedCov.name»">
							«transformation»
						</Covariate>
					«ENDIF»	
					'''
				}
			}
		} 
		//Then print all remaining covariates
		for(s : mObj.mdlCovariateDefns){
			switch(s){
				EquationDefinition:{
					if (!skipped.contains(s)){
						model = model + '''
						<Covariate symbId="«s.name»">
							<Continuous/>
						</Covariate>
						'''
					}
				}
				EnumerationDefinition:{
					if (!skipped.contains(s)){
						model = model + '''
						<Covariate symbId="«s.name»">
							<Categorical>
								«FOR c : s.catDefn.getCategoryDefinitions»
									<Category catId="«c»"/>
								«ENDFOR»
							</Categorical>
						</Covariate>
						'''
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
		
		
	def writeSimpleParameter(EquationDefinition stmt)'''
		«IF stmt.expression != null»
			<SimpleParameter symbId = "«stmt.name»">
				«stmt.expression.writeAssignment»
			</SimpleParameter>
		«ELSE»
			<SimpleParameter symbId = "«stmt.name»"/>
		«ENDIF»
	'''
	
	def writeAssignment(Expression expr)'''
		<ct:Assign>
			<Equation xmlns="«xmlns_math»">
				«expr.pharmMLExpr»
			</Equation>
		</ct:Assign>
	'''
		
	def writeRandomVariable(RandomVariableDefinition stmt, SymbolReference level)'''
		<RandomVariable symbId="«stmt.name»">
			<ct:VariabilityReference>
				«level.pharmMLExpr»
			</ct:VariabilityReference>
			«writeUncertMlDistribution(stmt.distn)»
		</RandomVariable>
	'''
		
	def writeUncertMlDistribution(Expression functionCall){
		switch(functionCall){
			BuiltinFunctionCall:
				functionCall.writeUncertmlDist
			default:
				''''''
		}
	}
		

//	/////////////////////////////
//	// I.d Parameter Model
//	////////////////////////////	
	def print_mdef_ParameterModel(MclObject mObj, MclObject pObj)'''		
		<ParameterModel blkId="pm">
			«IF mObj != null»
				«FOR b: mObj.blocks»
«««				//STRUCTURAL_PARAMETERS
					«IF b.identifier == BlockDefinitionTable::MDL_STRUCT_PARAMS»
						«FOR stmt: b.getNonBlockStatements»
							«switch(stmt){
								EquationDefinition:
									writeSimpleParameter(stmt)
							}»
						«ENDFOR» 
			  		«ENDIF»
«««				//VARIABILITY_PARAMETERS
					«IF b.identifier == BlockDefinitionTable::MDL_VAR_PARAMS»
						«FOR stmt: b.getNonBlockStatements»
							«switch(stmt){
								EquationDefinition:
									writeSimpleParameter(stmt)
							}»
						«ENDFOR» 
			  		«ENDIF»
«««		  		GROUP_VARIABLES (covariate parameters)
					«IF b.identifier == BlockDefinitionTable::MDL_GRP_PARAMS»
						«FOR stmt: b.getNonBlockStatements»
							«switch(stmt){
								EquationDefinition:
									writeSimpleParameter(stmt)
							}»
						«ENDFOR» 
			  		«ENDIF»
«««				//RANDOM_VARIABLES_DEFINITION
					«IF b.identifier == BlockDefinitionTable::MDL_RND_VARS»
						«FOR stmt: b.getNonBlockStatements»
							«switch(stmt){
								RandomVariableDefinition:{
									writeRandomVariable(stmt, b.getVarLevel)
								}
							}»
						«ENDFOR» 
					«ENDIF»
«««		  		//INDIVIDUAL_VARIABLES
					«IF b.identifier == BlockDefinitionTable::MDL_INDIV_PARAMS»
						«FOR stmt: b.getNonBlockStatements»
							«switch(stmt){
								EquationTypeDefinition:
									writeIndividualParameter(stmt)
							}»
						«ENDFOR» 
					«ENDIF»
				«ENDFOR»
	  		«ENDIF»
			«print_mdef_CollerationModel(mObj, pObj)»
		</ParameterModel>
  	'''
	
	def writeGeneralIdv(EquationTypeDefinition it){
		var funcExpr = expression as BuiltinFunctionCall
		var namedArgList = funcExpr.argList as NamedFuncArguments
		val trans = switch(it){
			TransformedDefinition:
				getPharmMLTransFunc(transform)
			default: null
		} 
		'''
		<IndividualParameter symbId="«name»">
			<GaussianModel>
				«IF trans!= null»
					<Transformation>«trans»</Transformation>
				«ENDIF»
				<GeneralCovariate>
					«namedArgList.getArgumentExpression('grp').writeAssignment»
				</GeneralCovariate>
				«namedArgList.getArgumentExpression('ranEff').writeRandomEffects»
			</GaussianModel>
		</IndividualParameter>
		''' 
	}
	
	def writeFixedEffects(Expression expr){
		val it = expr as VectorLiteral
		'''
		«FOR el : expressions»
			<Covariate>
				«((el as VectorElement).element as SubListExpression).writeFixedEffectCovariate»
				<FixedEffect>
					«((el as VectorElement).element as SubListExpression).writeFixedEffectCoefficient»
				</FixedEffect>
			</Covariate>
		«ENDFOR»
		'''
		
	}
	
	def writeFixedEffectCovariate(SubListExpression it){
		val cov = getAttributeExpression('cov')
		if(cov != null){
			'''
			«cov.pharmMLExpr»
			'''
		}
		else{
			val catCov = getAttributeExpression('catCov')
			'''
			«catCov.getEnumType.symbolReference»
			'''
		}
	}
	
	def writeFixedEffectCoefficient(SubListExpression it){
		val catCov = getAttributeExpression('catCov')
		'''
		«getAttributeExpression('coeff')?.pharmMLExpr»
		«IF catCov != null»
			<Category catId="«catCov.getEnumValue.name»"/>
		«ENDIF»
		'''
	}
	
	def getEnumType(Expression expr){
		switch(expr){
			CategoryValueReference:{
				EcoreUtil2.getContainerOfType(expr.ref, SymbolDefinition)
			}
			default: null
		}
	}
	
	def getEnumValue(Expression expr){
		switch(expr){
			CategoryValueReference:	expr.ref
			default: null
		}
	}
	
	
	def writeRandomEffects(Expression expr)'''
		«IF expr instanceof VectorLiteral»
			«FOR e : (expr as VectorLiteral).expressions»
				<RandomEffects>
					«IF (e as VectorElement).element instanceof SymbolReference»
						«(e as VectorElement).element.pharmMLExpr»
					«ELSE»
						<ERROR!>
					«ENDIF»
				</RandomEffects>
			«ENDFOR»
		«ENDIF»
		'''
	
	def writeLinearIdv(EquationTypeDefinition it){
		var funcExpr = expression as BuiltinFunctionCall
		var namedArgList = funcExpr.argList as NamedFuncArguments 
		val fixEff = namedArgList.getArgumentExpressionAsVector('fixEff') as VectorLiteral
		'''
		<IndividualParameter symbId="«name»">
			<GaussianModel>
				«IF namedArgList.getArgumentExpression('trans') != null»
					<Transformation>«namedArgList.getArgumentExpression('trans').convertToString.getPharmMLTransFunc»</Transformation>
				«ENDIF»
				<LinearCovariate>
					<PopulationParameter>
						«namedArgList.getArgumentExpression('pop').writeAssignment»
					</PopulationParameter>
					«IF fixEff != null && !fixEff.expressions.isEmpty »
						«namedArgList.getArgumentExpressionAsVector('fixEff').writeFixedEffects»
					«ENDIF»
				</LinearCovariate>
				«namedArgList.getArgumentExpressionAsVector('ranEff').writeRandomEffects»
			</GaussianModel>
		</IndividualParameter>
		''' 
	}
	
	def writeExplicitIdv(EquationTypeDefinition it)'''
		<IndividualParameter symbId="«name»">
			«expression.writeAssignment»
		</IndividualParameter>
	''' 
	
	// assume definition has a RHS
	def writeIndividualParameter(EquationTypeDefinition it){
		val expr = it.expression
		switch(expr){
			BuiltinFunctionCall case(expr.func == 'general'):
				writeGeneralIdv
			BuiltinFunctionCall case(expr.func == 'linear'):
				writeLinearIdv
			default:
				writeExplicitIdv			
		}
	}
	
	def writeVariableDefinition(EquationDefinition stmt)'''
		<ct:Variable symbId="«stmt.name»" symbolType="«IF stmt.isVector»ERROR!«ELSE»real«ENDIF»">
			«IF stmt.expression != null»
				«stmt.expression.writeAssignment»
			«ENDIF»
		</ct:Variable>
	'''
	
	
	def writeAssignZero()'''
		<ct:Assign>
			<Equation xmlns="«xmlns_math»">
				<ct:Int>0</ct:Int>
			</Equation>
		</ct:Assign>	
	'''
	
	def writeDefaultWrt(EquationDefinition it)'''
		<ct:SymbRef symbIdRef="«name»"/>
	'''
	
	
	def writeDerivativeDefinition(ListDefinition stmt, EquationDefinition defaultWrt)'''
		<ct:DerivativeVariable symbId="«stmt.name»" symbolType="real">
			«stmt.list.getAttributeExpression("deriv").writeAssignment»
			<ct:IndependentVariable>
				«stmt.list.getAttributeExpression("wrt")?.pharmMLExpr ?: defaultWrt.writeDefaultWrt»
			</ct:IndependentVariable>
			<ct:InitialCondition>
				<ct:InitialValue>
					«stmt.list.getAttributeExpression("init")?.writeAssignment ?: writeAssignZero »
				</ct:InitialValue>
				<ct:InitialTime>
					«stmt.list.getAttributeExpression("x0")?.writeAssignment ?: writeAssignZero »
				</ct:InitialTime>
			</ct:InitialCondition>
		</ct:DerivativeVariable>
	'''
	
	
	def CharSequence writeModelPredictionBlock(MclObject mdlObject, BlockStatementBody currBlkBody)'''
		«FOR stmt : currBlkBody.statements»
			«switch(stmt){
				BlockStatement:
					if(stmt.identifier == BlockDefinitionTable::MDL_DEQ_BLK && stmt.body instanceof BlockStatementBody){
						'''
						«mdlObject.writeModelPredictionBlock(stmt.body as BlockStatementBody)»
						'''
					}
				ListDefinition:
					'''
					«stmt.writeDerivativeDefinition(mdlObject.mdlIdv)»
					'''
				EquationDefinition:
					'''
					«stmt.writeVariableDefinition»
					'''
				default:
					'''
					<ERROR!>
					'''
			}»
		«ENDFOR»
	'''
	
	def writeStructuralModel(MclObject mdlObject){
		'''
			<StructuralModel blkId="sm">
				«FOR blk : mdlObject.modelPredictionBlocks»
					«IF blk.body instanceof BlockStatementBody»
						«mdlObject.writeModelPredictionBlock(blk.body as BlockStatementBody)»
					«ENDIF»
				«ENDFOR»
				«IF !mdlObject.mdlCompartmentStatements.isEmpty»
					«mdlObject.mdlCompartmentStatements.writeCompartmentMacros»
				«ENDIF»
			</StructuralModel>
		'''
	}
	
	def writeCompartmentMacros(List<Statement> stmts){
		'''
		«stmts.printCompartmentDefinitions»
		«stmts.printMacros»
		'''
	}
	
	
	def writeObservationModel(MclObject mdlObject){
		var idx = 0
		'''«FOR stmt : mdlObject.mdlObservations»
			«switch(stmt){
				EquationTypeDefinition:
					'''
					«writeContinuousObservation(stmt, idx += 1)»
					'''
				ListDefinition:
					'''
					«writeDiscreteObservations(stmt, idx += 1)»
					'''
				default:{
					idx += 1
					''''''
				}
			}»
		«ENDFOR»
		'''
	}
	
	def writeDiscreteObservations(ListDefinition s, int idx) {
		val type = s.list.getAttributeEnumValue(ListDefinitionTable::OBS_TYPE_ATT)
		'''
		<ObservationModel blkId="om«idx»">
			«switch type{
				case ListDefinitionTable::COUNT_OBS_VALUE:
					s.print_mdef_CountObservations
				case ListDefinitionTable::DISCRETE_OBS_VALUE:
					s.print_mdef_DiscreteObservations
				case ListDefinitionTable::CATEGORICAL_OBS_VALUE:
					s.print_mdef_CategoricalObservations
				case ListDefinitionTable::TTE_OBS_VALUE:
					s.print_mdef_TimeToEventObservations
				default: ''''''
			}» 
		</ObservationModel>
		'''
	}
	
	def isStandardErrorDefinition(Expression expr){
		expr != null && expr instanceof BuiltinFunctionCall
	}
	
	def isTransformedBothSides(EquationTypeDefinition definition){
		definition instanceof TransformedDefinition &&
		 definition.expression instanceof BuiltinFunctionCall &&
		  (definition.expression as BuiltinFunctionCall).getArgumentExpression('trans') != null
	}
	
	def isTransformedOnlyRhsSide(EquationTypeDefinition definition){
		definition instanceof EquationDefinition &&
		 definition.expression instanceof BuiltinFunctionCall &&
		  (definition.expression as BuiltinFunctionCall).getArgumentExpression('trans') != null
	}
	
	def writeContinuousObservation(EquationTypeDefinition definition, int idx){
		val rhsExpr = definition.expression
		if(rhsExpr instanceof BuiltinFunctionCall){
			val predictionExpr = rhsExpr.getArgumentExpression('prediction')
			'''
				<ObservationModel blkId="om«idx»">
					<ContinuousData>
						«IF definition.isTransformedOnlyRhsSide»
							<ct:Variable symbolType="real" symbId="«predictionExpr.singleSymbolRef?.name ?: "ERROR!"»">
								<ct:Assign>
									<math:Equation>
										<math:Uniop op="log">
											«predictionExpr.pharmMLExpr»
										</math:Uniop>
									</math:Equation>
								</ct:Assign>
							</ct:Variable>
						«ENDIF»
						«IF isStandardErrorDefinition(definition.expression)»
							<Standard symbId="«definition.name»">
								«IF definition instanceof TransformedDefinition»
									<Transformation>«definition.transform.pharmMLTransFunc»</Transformation>
								«ENDIF»
								<Output>
									«IF definition.isTransformedOnlyRhsSide»
										«predictionExpr.singleSymbolRef?.localSymbolReference ?: "ERROR!"»
									«ELSE»
										«predictionExpr.pharmMLExpr»
									«ENDIF»
								</Output>
								«writeStandardErrorModel(rhsExpr)»
								<ResidualError>
									«rhsExpr.getArgumentExpression('eps').pharmMLExpr»
								</ResidualError>
							</Standard>
						«ENDIF»
					</ContinuousData>
				</ObservationModel>
			'''
		}
		else{
			'''
				<ObservationModel blkId="om«idx»">
					<ContinuousData>
						<General symbId="«definition.name»">
							«definition.expression.expressionAsAssignment»
						</General>
					</ContinuousData>
				</ObservationModel>
			'''
		}
	} 
	
	private def writeStandardErrorModel(BuiltinFunctionCall it){
		'''
		<ErrorModel>
			<ct:Assign>
				<Equation xmlns="«xmlns_math»">
					<FunctionCall>
						<ct:SymbRef symbIdRef="«standardErrorName»"/>
						«FOR vp : getNamedArguments»
							«IF getStandardErrorArgument(vp.argumentName) != null»
								<FunctionArgument symbId="«getStandardErrorArgument(vp.argumentName)»">
									«IF !(vp.expression instanceof SymbolReference)»
										<Equation xmlns="«xmlns_math»">
											«vp.expression.pharmMLExpr»
										</Equation>
									«ELSE»
										«vp.expression.pharmMLExpr»
									«ENDIF»
								</FunctionArgument>
							«ENDIF»
						«ENDFOR»
					</FunctionCall>
				</Equation>
			</ct:Assign>
		</ErrorModel>
		'''
	}
	
//	/////////////////////////////
//	// I.d_1 CorrelationModel
//	/////////////////////////////
	def print_mdef_CollerationModel(MclObject mObj, MclObject pObj){
		var model = "";
			for (s: pObj.getParamCorrelations){
				val corrDefn = s as ListDefinition
				val type = corrDefn.list.getAttributeEnumValue('type');
				if (type == 'corr' || type == 'cov'){
					val params = corrDefn.list.getAttributeExpression('parameter') as VectorLiteral
					val values = corrDefn.list.getAttributeExpression('value') as VectorLiteral
					var k = 0;
					for(i : 1 .. params.expressions.size - 1){
						for(j : 0 .. i -1){
							val rv1 = params.expressions.get(j).vectorElementAsSymbolReference
							val rv2 = params.expressions.get(i).vectorElementAsSymbolReference
							if (k < values.expressions.size){
								var value = values.expressions.get(k) as VectorElement;
								k = k + 1;
								val level = mObj.getLevel(rv1.ref);
								model += print_mdef_Correlation(type, level, rv1, rv2, value.element)
							}							
						}
					}
				}
			} 
//					for (i: 1..params.vector.expression.expressions.size - 1){
//						for (j: 0..i - 1){
//							var rv1 = params.vector.expression.expressions.get(j);
//							var rv2 = params.vector.expression.expressions.get(i);
//							if (k < values.vector.expression.expressions.size){
//								var value = values.vector.expression.expressions.get(k);
//								k = k + 1;
//								var level = mObj.getLevel(rv1.toStr);
//								model = model + type.print_mdef_Correlation(level, rv1, rv2, value);
//							}
//						}
//					}
//		}
		return model;
	}
	
	def getLevel(MclObject mObj, SymbolDefinition randomVar){
		val randVar = mObj.findMdlSymbolDefn(randomVar.name)
		randVar.getRandomVarLevel
//			for (b: mObj.mdlVariabilityLevels){
//				
//				if (b.randomVariableDefinitionBlock != null){	
//					if (b.randomVariableDefinitionBlock.arguments != null){
//						var level =	b.randomVariableDefinitionBlock.arguments.getAttribute(AttributeValidator::attr_level_ref.name);	
//						for (s: b.randomVariableDefinitionBlock.variables){
//							if (s.name.equals(randomVar)){
//								return level;	
//							}
//						}
//					}
//				}
//			}
//		return null;
	}
	
//	/*
//	def print_mdef_Correlation_Matrix(String matrixType, Vector values, Vector params, String level)'''
//		<Correlation deviationMatrixType="«matrixType.convertMatrixType»">
//			«IF level != null»
//				«level.print_VariabilityReference»
//			«ENDIF»
//            «values.print_ct_Matrix(params, "Any")»
//		</Correlation>
//	'''
//	*/
//	
	def print_mdef_Correlation(String type, SymbolReference level, SymbolReference rv1, SymbolReference rv2, Expression value){
		var res = '''
			<RandomVariable1>
				«rv1.localSymbolReference»
			</RandomVariable1>
			<RandomVariable2>
				«rv2.localSymbolReference»
			</RandomVariable2>
		'''
		if (type == 'cov')
			res  = res + '''
				<Covariance>
					«value.expressionAsEquation»
«««					<Equation xmlns="«xmlns_math»">
«««						«value.print_Math_Expr»
«««					</Equation>
				</Covariance>
			'''
		if (type == 'corr')
			res  = res + '''
				<CorrelationCoefficient>
					«value.expressionAsEquation»
«««					<Equation xmlns="«xmlns_math»">
«««						«value.print_Math_Expr»
«««					</Equation>
				</CorrelationCoefficient>
			'''
		'''
			<Correlation>
				«IF level != null»
					<ct:VariabilityReference>
						«level.symbolReference»
					</ct:VariabilityReference>
				«ENDIF»
				<Pairwise>
					«res»
				</Pairwise>
			</Correlation>	
		'''
	}
//	
//	def print_mdef_RandomVariable(SymbolDeclaration s, String level)'''
//		«IF s.randomList != null && s.name != null»
//			<RandomVariable symbId="«s.name»">
//				«IF level.length > 0»
//					«level.print_VariabilityReference»
//				«ENDIF»
//				«print_uncert_Distribution(s.randomList)»
//			</RandomVariable>
//		«ENDIF»
//	'''
//	
//	/////////////////////////
//	// I.e Structural Model
//	/////////////////////////
//	def print_mdef_StructuralModel(ModelObject mObj){
//		var model ="";
//		if (mObj != null){
//			var variables = "";
//			var macros = "";
//			for (b: mObj.blocks){
//				if (b.modelPredictionBlock != null){
//					for (st: b.modelPredictionBlock.statements){
//						//MODEL_PREDICTION
//						if (st.variable != null){
//								if (st.variable.list != null){
//									if (st.variable.list.arguments.getAttributeExpression(AttributeValidator::attr_deriv.name) != null){
//										variables = variables + '''«st.variable.print_SymbolDeclaration("ct:DerivativeVariable", true)»''';	
//									}
//								} else {
//									variables = variables + '''«st.variable.print_SymbolDeclaration("ct:Variable", true)»''';	
//								}
//						} 
////							variables = variables + '''«st.variable.print_SymbolDeclaration("ct:Variable", true)»''';
//						//ODE
//						if (st.odeBlock != null){
//							for (s: st.odeBlock.variables){
//								if (s.list != null){
//									if (s.list.arguments.getAttributeExpression(AttributeValidator::attr_deriv.name) != null){
//										variables = variables + '''«s.print_SymbolDeclaration("ct:DerivativeVariable", true)»''';	
//									}
//								} else {
//									variables = variables + '''«s.print_SymbolDeclaration("ct:Variable", true)»''';	
//								}
//							}
//						}
//						//LIBRARY
////						if (st.libraryBlock != null){
////							for (s: st.libraryBlock.statements)
////								variables = variables + s.expression.print_Math_FunctionCall;
////						} 
//						//COMPARTMENT
//						if (st.pkMacroBlock != null){
//							macros += st.pkMacroBlock.statements.printCompartmentDefinitions
//							macros += st.pkMacroBlock.statements.printMacros
////							macros = macros + '''
////			<PKmacros>
////		'''
////							
////							for (s: st.pkMacroBlock.statements){
////								if (s.variable != null)
////									macros = macros + s.variable.print_PKMacros;
////								if (s.list != null)
////									macros = macros + s.list.print_PKMacros;
////							}
////							macros = macros + '''
////			</PKmacros>
////		'''
//							 
//						}
//					}
//				}
//			}
//			model = model + 
//			'''
//				«IF (variables.length > 0)»
//					<StructuralModel blkId="sm">
//						«IF (variables.length > 0)»«variables»«ENDIF»
//						«IF (macros.length > 0)»«macros»«ENDIF»
//					</StructuralModel>
//				«ENDIF»
//			'''
//		}
//		return model;
//	}
//		
//	/////////////////////////////
//	// I.f Observation Model
//	/////////////////////////////
//	def print_mdef_ObservationModel(ModelObject mObj){
//		var res = "";
//		if (mObj != null){
//			for (b: mObj.blocks){
//				if (b.observationBlock != null){
//					for (st: b.observationBlock.variables){
//						var observation = st.print_mdef_ObservationModel;
//						var idx = 1 as int;
//						val omBlkId = resolver.getReferenceBlock(st.name)
//						if (observation.length >0 )
//							res = res + '''
//								<ObservationModel blkId="«omBlkId»">
//									«observation»
//								</ObservationModel>
//							''';
//							idx = idx + 1;
//						}
//				}
//			}
//		}
//		return res;
//	}
//	
//	def print_mdef_ObservationModel(SymbolDeclaration s){
//		var retVal = ''''''
//		if(s.list != null){
//			val type = s.list.arguments.getAttribute(AttributeValidator::attr_type.name)
//			switch type{
//				case CONTINUOUS_OBS: retVal = s.print_mdef_StandardObservation.toString
//				case COUNT_OBS: retVal = s.print_mdef_CountObservations.toString
//				case DISCRETE_OBS: retVal = s.print_mdef_DiscreteObservations.toString
//				case CATEGORICAL_OBS: retVal = s.print_mdef_CategoricalObservations.toString
//				case TTE_OBS: retVal = s.print_mdef_TimeToEventObservations.toString
//			} 
//		}
//		else{
//			retVal = s.print_mdef_ExplicitObservation.toString
//		}
//		return retVal
//	}
//	
//	private def print_mdef_ExplicitObservation(SymbolDeclaration s)'''
//		«IF s.name != null && s.expression != null»
//			<ContinuousData>
//				<General symbId="«s.name»">
//					«s.expression.print_Assign»
//				</General>
//			</ContinuousData>
//		«ENDIF»
//	'''	
	
	private def getInverseFunction(Expression linkFunction, Expression paramVar){
		switch(linkFunction){
			BuiltinFunctionCall case linkFunction.func == "log": return '''
			<math:Uniop op="exp">
				«paramVar.pharmMLExpr»
			</math:Uniop>
			'''
			BuiltinFunctionCall case linkFunction.func == "identity": return '''
				«paramVar.pharmMLExpr»
			'''
		}
	}
	
//	def getFunctionArgument(RandomList distn, String argName){
//		if(distn.arguments.namedArguments != null){
//			for(Argument arg : distn.arguments.namedArguments.arguments){
//				if(arg.argumentName.argName == argName)	
//					return arg.expression
//			}
//		}
//		else if(!distn.arguments.unnamedArguments.arguments.isEmpty)
//			return distn.arguments.unnamedArguments.arguments.get(0)
//	}
//		
//	def isReference(ArgumentExpression expr){
//		var retVal = false
//		if(expr != null && expr.expression != null && expr.expression.expression != null
//			&& expr.expression.expression.expression != null)
//			retVal = MdlDataType::isReference(expr.expression.expression.expression)
//			
//		retVal
//	}	
//	
	private def print_mdef_CountObservations(ListDefinition s) {
		var name = s.name
		val linkFunction = s.list.getAttributeExpression('link');
		val distn = s.list.getAttributeExpression('distn');
		val paramVar = (distn as BuiltinFunctionCall).getFunctionArgumentValue("lambda");
//		var String tmpParamVar = null;
//		if(paramVar ){
//			tmpParamVar = paramVar.toStr
//		}
		'''
			<Discrete>
				<CountData>
				«IF paramVar != null»
					<!-- Note that this parameter is local to this block, but uses the same name
						as the lambda argument. --> 
					<SimpleParameter symbId="«paramVar.convertToString»">
					<ct:Assign>
						<math:Equation>
						«IF linkFunction != null»
							«getInverseFunction(linkFunction, paramVar)»
						«ELSE»
							«paramVar.pharmMLExpr»
						«ENDIF»
						</math:Equation>
					</ct:Assign>
				</SimpleParameter>
				«ENDIF»
				<CountVariable symbId="«name»"/>
				<PMF linkFunction="identity">
					«distn.writeUncertMlDistribution»
				</PMF>
				</CountData>
			</Discrete>
		'''
	}
	
	
	private def getSuccessCategory(BuiltinFunctionCall it){
		switch(func){
			case "Bernoulli":
				getArgumentExpression('category')
			case "Binomial":
				getArgumentExpression('successCategory')
		}?.convertToString
	}
	
	
	private def createCategoriesOrderedBySuccess(Set<String> categories, String successCategory){
		val retVal = new ArrayList<String>(categories.size)
		retVal.add(successCategory)
		retVal.addAll(categories.filter[it != successCategory])
		retVal
	}
	
	private def print_mdef_DiscreteObservations(ListDefinition s) {
		var name = s.name
		val linkFunction = s.list.getAttributeExpression('link');
		val distn = s.list.getAttributeExpression('distn') as BuiltinFunctionCall
		val paramVar = (distn as BuiltinFunctionCall).getFunctionArgumentValue("probability")
		val categories = s.list.getAttributeExpression(ListDefinitionTable::OBS_TYPE_ATT);
		val catVals = categories.categories
		val catList = createCategoriesOrderedBySuccess(catVals.keySet, distn.successCategory)
		
		'''
			<Discrete>
				<CategoricalData ordered="no">
					«IF paramVar != null»
						<!-- Note that this parameter is local to this block, but uses the same name
							as the lambda argument.  --> 
						<SimpleParameter symbId="«paramVar.convertToString»">
							<ct:Assign>
								<math:Equation>
									«IF linkFunction != null»
										«getInverseFunction(linkFunction, paramVar)»
									«ELSE»
										«paramVar.pharmMLExpr»
									«ENDIF»
								</math:Equation>
							</ct:Assign>
						</SimpleParameter>
					«ENDIF»
					<ListOfCategories>
						«FOR cat : catList»
							<Category symbId="«cat»"/>
						«ENDFOR»
					</ListOfCategories>
					<CategoryVariable symbId="«name»"/>
					<PMF linkFunction="identity">
						«printDiscreteDistribution(distn)»
					</PMF>
				</CategoricalData>
			</Discrete>
		'''
	}
	
	
//	private def getCategoryNames(Expression categories){
//		val listCats = new ArrayList<String>
//		val catVals = new HashMap<String, Expression>
//		switch(categories){
//			EnumExpression:{
//				val catDefnExpr = categories.catDefn as CategoricalDefinitionExpr
//				catDefnExpr.categories.forEach[
//					listCats.add(name)
//					catVals.put(name, mappedTo)
//				]
//			}
//		}
//		listCats
//	}
	
	private def getCategories(Expression categories){
		val catVals = new HashMap<String, Expression>
		switch(categories){
			EnumExpression:{
				val catDefnExpr = categories.catDefn as CategoricalDefinitionExpr
				catDefnExpr.categories.forEach[
					catVals.put(name, mappedTo)
				]
			}
		}
		catVals
	}
	
	private def print_mdef_CategoricalObservations(ListDefinition s) {
//			val define = column.list.getAttributeExpression(ListDefinitionTable::USE_ATT);
//			// get an EnumExpression here - use this to get the categories.
//			switch(define){
//				EnumExpression:{
//					val catDefnExpr = define.catDefn as CategoricalDefinitionExpr
//					for(catVal : catDefnExpr.categories){
//					res = res + '''
//						<ds:Map modelSymbol="«catVal.name»" dataSymbol="«catVal.mappedTo.convertToString»"/>
//						'''
//					}
//				}
//			}
		val categories = s.list.getAttributeExpression(ListDefinitionTable::OBS_TYPE_ATT);
//		val listCats = new ArrayList<String>
//		val catVals = new HashMap<String, Expression>
//		switch(categories){
//			EnumExpression:{
//				val catDefnExpr = categories.catDefn as CategoricalDefinitionExpr
//				catDefnExpr.categories.forEach[
//					listCats.add(name)
//					catVals.put(name, mappedTo)
//				]
//			}
//		}
		val catVals = categories.categories
		'''
			<Discrete>
				<CategoricalData>
					<ListOfCategories>
						«FOR cat : catVals.keySet»
							<Category symbId="«cat»"/>
						«ENDFOR»
					</ListOfCategories>
					<CategoryVariable symbId="«s.name»"/>
					«FOR cat : catVals.keySet»
						<ProbabilityAssignment>
							<Probability linkFunction="identity">
								<math:LogicBinop op="eq">
									<ct:SymbRef symbIdRef="«s.name»"/>
									<ct:SymbRef symbIdRef="«cat»"/>
								</math:LogicBinop>
							</Probability>
							«catVals.get(cat).expressionAsAssignment»
						</ProbabilityAssignment>
					«ENDFOR»
				</CategoricalData>
			</Discrete>
		'''
	}


	private def print_mdef_TimeToEventObservations(ListDefinition s) {
		var name = s.name
		val haz = s.list.getAttributeExpression('hazard');
		val event = s.list.getAttributeEnumValue('event');
		val maxEvent = s.list.getAttributeExpression('maxEvent');
		'''
			<Discrete>
				<TimeToEventData>
					<EventVariable symbId="«name»"/>
					<HazardFunction symbId="«haz.convertToString»">
						<ct:Assign>
							«haz.pharmMLExpr»
						</ct:Assign>
					</HazardFunction>
					«IF event != null»
						<Censoring censoringType="«event.getEventType»"/>
					«ENDIF»
					«IF maxEvent != null»
						<MaximumNumberEvents>
							«maxEvent.expressionAsAssignment»
						</MaximumNumberEvents>
					«ENDIF»
				</TimeToEventData>
			</Discrete>
		'''
	}
	
	def getEventType(String eventType){
		switch(eventType){
			case 'exact': '''rightCensored'''
			case 'intervalCensored': '''intervalCensored'''
			default: ''''''
		}
	}


//	private def print_mdef_StandardObservation(SymbolDeclaration s){
//		var name = s.name
//		val error = s.list.arguments.getAttributeExpression(AttributeValidator::attr_error.name);
//		val prediction = s.list.arguments.getAttribute(AttributeValidator::attr_prediction_ref.name);
//		val eps = s.list.arguments.getAttribute(AttributeValidator::attr_eps.name);
//		val transfn = s.list.arguments.getAttribute(AttributeValidator::attr_trans.name);
//		'''
//			<ContinuousData>
//				<Standard symbId="«name»">
//					«IF transfn.length > 0»
//						<Transformation>
//							«transfn»
//						</Transformation>
//					«ENDIF»
//					«IF prediction.length > 0»
//						<Output>
//							«prediction.print_ct_SymbolRef»
//						</Output>
//					«ENDIF»
//					«IF error != null»
//						<ErrorModel>
//							«error.print_Assign»
//						</ErrorModel>
//					«ENDIF»
//					«IF eps.length > 0»
//						<ResidualError>
//							«eps.print_ct_SymbolRef»
//						</ResidualError>
//					«ENDIF»
//				</Standard>
//			</ContinuousData>
//		'''
//	}
//	
//	def print_SymbolDeclaration(EquationDefinition st, String tag, Boolean printType){
//		val expr = st.expression
//		if (st.name != null)'''
//			<«tag» symbId="«st.name»"«IF printType» symbolType="«TYPE_REAL»"«ENDIF»>
//			«IF expr != null»
//				«switch(expr){
//					BuiltinFunctionCall:
//						expr.print_List
//					default:
//						expr.writeAssignment
//				}
//				»
//				
//			«ENDIF»
//			</«tag»>
//			'''
//	}
//	
//	//Convert special types of lists to PharmML
//	def print_List(BuiltinFunctionCall list){
//		var assign = "";
//		var res = "";
//		val type = list.func;
//		if (type.isCategorical){
//			//Categorical variables
//			if (type.type.type.categories != null && type.type.type.categories.size > 0){
//				assign = '''«type.print_Categorical»'''
//			} else {
//				val define = list.arguments.getAttributeExpression(AttributeValidator::attr_define.name);
//				if (define.list != null) 
//					assign = '''«define.list.print_Categorical»'''
//			}
//		} else {
//			//Derivative variables	
//			val deriv = list.arguments.getAttributeExpression(AttributeValidator::attr_deriv.name);
//			if (deriv != null){
//				assign = '''«deriv.print_Math_Expr»'''
//				var wrtVar = independentVar;
//				val wrtVarExpr = list.arguments.getAttributeExpression(AttributeValidator::attr_wrt.name);
//				if (wrtVarExpr != null) wrtVar = wrtVarExpr.toStr; 
//				res  = 	
//				'''
//					<ct:IndependentVariable>
//						«wrtVar.print_ct_SymbolRef»
//					</ct:IndependentVariable>
//				'''	
//				val initValue = list.arguments.getAttributeExpression(AttributeValidator::attr_init.name);
//				val initTime = list.arguments.getAttributeExpression(AttributeValidator::attr_x0.name);
//				var initValueRes = '''«AttributeValidator::attr_init.defaultValue.print_Assign»'''; 
//				var initTimeRes = '''«AttributeValidator::attr_x0.defaultValue.print_Assign»'''; 
//				if (initTime != null) initTimeRes = '''«initTime.print_Assign»'''; 
//				if (initValue != null) initValueRes = '''«initValue.print_Assign»'''; 
//				res = res + 
//				'''
//					<ct:InitialCondition>
//						<ct:InitialValue>
//							«initValueRes»
//						</ct:InitialValue>
//						<ct:InitialTime>
//							«initTimeRes»
//						</ct:InitialTime>
//					</ct:InitialCondition>
//				'''						
//			}
//		} 
//		if (assign.length > 0) return 
//		'''
//			<ct:Assign>
//				<Equation xmlns="«xmlns_math»">
//					«assign»
//				</Equation>
//			</ct:Assign>	
//		'''	+ res;
//		if (type != null) {
//			var modelType = type.toStr;
//			//Gaussian models
//			if (modelType.equals(IndividualVarType::GENERAL.toString) || modelType.equals(IndividualVarType::LINEAR.toString)){
//				//Transformation
//				val trans = list.arguments.getAttribute(AttributeValidator::attr_trans.name);
//				//Covariate model
//				var covariateContent = '''''';
//				val fixEffList = list.arguments.getAttributeExpression(AttributeValidator::attr_fixEff.name);
//				if (fixEffList != null){
//					var pairs = fixEffList.getAttributePairs(AttributeValidator::attr_coeff.name, AttributeValidator::attr_cov.name);
//					for (pair: pairs)
//						covariateContent = covariateContent + '''«print_Covariate(pair.key.expression, pair.value.expression)»'''		
//				}	
//				//Random effect
//				val ranEff = list.arguments.getAttributeExpression(AttributeValidator::attr_ranEff.name);
//				var ranEffExpr = '''''';
//				if (ranEff != null){
//					if (ranEff.expression != null) ranEffExpr = '''
//						<RandomEffects>
//							«ranEff.expression.print_Math_Expr»
//						</RandomEffects>
//						'''	
//					if (ranEff.vector != null && ranEff.vector.expression.expressions != null) {
//						for (expr: ranEff.vector.expression.expressions)
//							ranEffExpr = ranEffExpr + '''
//							<RandomEffects>
//								«expr.print_Math_Expr»
//							</RandomEffects>
//							''';
//					}
//				}				
//				//Population parameter
//				var popContent = '''''';
//				val pop = list.arguments.getAttributeExpression(AttributeValidator::attr_pop.name);
//				if (pop != null) popContent = '''«pop.print_Assign»''';
//				//General vs. linear model - differences
//				var covariateType = "GeneralCovariate";
//				if (modelType.equals(IndividualVarType::LINEAR.toString)) {
//					covariateType = "LinearCovariate";	
//					if (popContent != null) popContent = '''
//						<PopulationParameter>
//							«popContent»
//						</PopulationParameter>
//				''';
//				}	
//				return 
//				'''
//					<GaussianModel>
//						«IF trans.length > 0»
//							<Transformation>«trans»</Transformation>
//						«ENDIF»
//						«IF popContent.length > 0 || covariateContent.length > 0»
//							<«covariateType»>
//								«popContent»
//								«covariateContent»
//							</«covariateType»>
//						«ENDIF»
//						«ranEffExpr»
//					</GaussianModel>
//				'''
//			}
//		}
//	}
//	
//	def print_Covariate(Expression coeff, Expression cov)'''
//		«IF coeff != null && cov != null»
//			<Covariate>
//				«cov.print_Math_Expr»
//				<FixedEffect>
//					«coeff.print_Math_Expr»
//				</FixedEffect>
//			</Covariate>	
//		«ENDIF»
//	'''
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
