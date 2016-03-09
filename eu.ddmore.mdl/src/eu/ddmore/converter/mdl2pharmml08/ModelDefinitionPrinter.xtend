package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.CategoricalDefinitionExpr
import eu.ddmore.mdl.mdl.CategoryValueReference
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.ListDefinitionTable
import eu.ddmore.mdl.provider.SublistDefinitionProvider
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.ArrayList
import java.util.Comparator
import java.util.HashMap
import java.util.List
import java.util.Map
import java.util.Set
import java.util.TreeMap
import org.eclipse.xtext.EcoreUtil2

import static eu.ddmore.converter.mdl2pharmml08.Constants.*

import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToInteger
import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString
import eu.ddmore.mdl.utils.BlockUtils

class ModelDefinitionPrinter {
	extension MdlUtils mu = new MdlUtils
	extension TypeSystemProvider tsp = new TypeSystemProvider
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension DistributionPrinter dp = new DistributionPrinter 
	extension PharmMLConverterUtils pcu = new PharmMLConverterUtils
	extension SublistDefinitionProvider sdp = new SublistDefinitionProvider
	extension FunctionDefinitionPrinter fdp = new FunctionDefinitionPrinter
	extension PKMacrosPrinter pkp = PKMacrosPrinter::INSTANCE
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	extension BlockUtils bu = new BlockUtils
	
	
	//////////////////////////////////////
	// I. Model Definition
	//////////////////////////////////////	
	def writeModelDefinition(MclObject mObj, MclObject pObj){
		'''
		<ModelDefinition xmlns="«xmlns_mdef»">
			«mObj.writeAllVariabilityModels»
			«IF !mObj.mdlCovariateDefns.isEmpty»
				«mObj.writeCovariateModel»
			«ENDIF»
			«mObj.writeParameterModel(pObj)»
			«IF !mObj.modelPredictionBlocks.isEmpty»
				«mObj.writeStructuralModel»
			«ENDIF»
			«IF !mObj.mdlObservations.isEmpty»
				«mObj.writeObservationModel»
			«ENDIF»
		</ModelDefinition>
		'''
	}

	def writeAllVariabilityModels(MclObject mObj){
		val vm_err_vars = new HashMap<String, Integer>
		val vm_mdl_vars = new HashMap<String, Integer>
		for(stmt : mObj.mdlVariabilityLevels){
			switch(stmt){
				ListDefinition:{
					if(stmt.firstAttributeList.getAttributeEnumValue('type') == 'parameter'){
						vm_mdl_vars.put(stmt.name, stmt.firstAttributeList.getAttributeExpression('level').convertToInteger)
					}
					else{
						vm_err_vars.put(stmt.name, stmt.firstAttributeList.getAttributeExpression('level').convertToInteger)
					}
				}
			}	
		}
		var model = "";
		if (vm_err_vars.size() > 0){
			model = model + vm_err_vars.writeVariabilityModel("vm_err", VAR_TYPE_ERROR);
		}		
		if (vm_mdl_vars.size() > 0){
			model = model + vm_mdl_vars.writeVariabilityModel("vm_mdl", VAR_TYPE_PARAMETER);
		}
		return model;
	}
	
	def writeVariabilityModel(Map<String, Integer> vars, String blkId, String varType){
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
    
    
	def writeCovariateModel(MclObject mObj){
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
			<PopulationParameter symbId = "«stmt.name»">
				«stmt.expression.writeAssignment»
			</PopulationParameter>
		«ELSE»
			<PopulationParameter symbId = "«stmt.name»"/>
		«ENDIF»
	'''
	
	def writeAssignment(Expression expr)'''
		<ct:Assign>
			«expr.pharmMLExpr»
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
			SymbolReference:
				functionCall.writeUncertmlDist
			default:
				''''''
		}
	}
		

//	/////////////////////////////
//	// I.d Parameter Model
//	////////////////////////////	
	def writeParameterModel(MclObject mObj, MclObject pObj)'''		
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
		var funcExpr = expression as SymbolReference
		var namedArgList = funcExpr.argList as NamedFuncArguments
		val trans = switch(it){
			TransformedDefinition:
				getPharmMLTransFunc(transform.name)
			default: null
		} 
		'''
		<IndividualParameter symbId="«name»">
			<StructuredModel>
				«IF trans!= null»
					<Transformation type="«trans»"/>
				«ENDIF»
				<GeneralCovariate>
					«namedArgList.getArgumentExpression('grp').writeAssignment»
				</GeneralCovariate>
				«namedArgList.getArgumentExpression('ranEff').writeRandomEffects»
			</StructuredModel>
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
		var funcExpr = expression as SymbolReference
		var namedArgList = funcExpr.argList as NamedFuncArguments 
		val fixEff = namedArgList.getArgumentExpression('fixEff') as VectorLiteral
		'''
		<IndividualParameter symbId="«name»">
			<StructuredModel>
				«IF namedArgList.getArgumentExpression('trans') != null»
					<Transformation type="«namedArgList.getArgumentExpression('trans').convertToString.getPharmMLTransFunc»" />
				«ENDIF»
				<LinearCovariate>
					<PopulationValue>
						«namedArgList.getArgumentExpression('pop').writeAssignment»
					</PopulationValue>
					«IF fixEff != null && !fixEff.expressions.isEmpty »
						«namedArgList.getArgumentExpression('fixEff').writeFixedEffects»
					«ENDIF»
				</LinearCovariate>
				«namedArgList.getArgumentExpression('ranEff').writeRandomEffects»
			</StructuredModel>
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
			SymbolReference case(expr.func == 'general'):
				writeGeneralIdv
			SymbolReference case(expr.func == 'linear'):
				writeLinearIdv
			default:
				writeExplicitIdv			
		}
	}
	
	def writeVariableDefinition(EquationDefinition stmt)'''
		<ct:Variable symbId="«stmt.name»" symbolType="«IF stmt.typeFor.isVector»ERROR!«ELSE»real«ENDIF»">
			«IF stmt.expression != null»
				«stmt.expression.writeAssignment»
			«ENDIF»
		</ct:Variable>
	'''
	
	
	def writeAssignZero()'''
		<ct:Assign>
			<ct:Int>0</ct:Int>
		</ct:Assign>	
	'''
	
	def writeDefaultWrt(EquationDefinition it)'''
		<ct:SymbRef symbIdRef="«name»"/>
	'''
	
	
	def writeDerivativeDefinition(ListDefinition stmt, EquationDefinition defaultWrt)'''
		<ct:DerivativeVariable symbId="«stmt.name»" symbolType="real">
			«stmt.firstAttributeList.getAttributeExpression("deriv").writeAssignment»
			<ct:IndependentVariable>
				«stmt.firstAttributeList.getAttributeExpression("wrt")?.pharmMLExpr ?: defaultWrt.writeDefaultWrt»
			</ct:IndependentVariable>
			<ct:InitialCondition>
				<ct:InitialValue>
					«stmt.firstAttributeList.getAttributeExpression("init")?.writeAssignment ?: writeAssignZero »
				</ct:InitialValue>
				<ct:InitialTime>
					«stmt.firstAttributeList.getAttributeExpression("x0")?.writeAssignment ?: writeAssignZero »
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
		val type = s.firstAttributeList.getAttributeEnumValue(ListDefinitionTable::OBS_TYPE_ATT)
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
		expr != null && expr instanceof SymbolReference
	}
	
	def isTransformedBothSides(EquationTypeDefinition definition){
		definition instanceof TransformedDefinition &&
		 definition.expression instanceof SymbolReference &&
		  (definition.expression as SymbolReference).getArgumentExpression('trans') != null
	}
	
	def isTransformedOnlyRhsSide(EquationTypeDefinition definition){
		definition instanceof EquationDefinition &&
		 definition.expression instanceof SymbolReference &&
		  (definition.expression as SymbolReference).getArgumentExpression('trans') != null
	}
	
	def writeContinuousObservation(EquationTypeDefinition definition, int idx){
		val rhsExpr = definition.expression
		if(rhsExpr instanceof SymbolReference){
			val predictionExpr = rhsExpr.getArgumentExpression('prediction')
			'''
				<ObservationModel blkId="om«idx»">
					<ContinuousData>
						«IF definition.isTransformedOnlyRhsSide»
							<ct:Variable symbolType="real" symbId="«predictionExpr.singleSymbolRef?.name ?: "ERROR!"»">
								<ct:Assign>
									<math:Uniop op="log">
										«predictionExpr.pharmMLExpr»
									</math:Uniop>
								</ct:Assign>
							</ct:Variable>
						«ENDIF»
						«IF isStandardErrorDefinition(definition.expression)»
							<Standard symbId="«definition.name»">
								«IF definition instanceof TransformedDefinition»
									<Transformation>«definition.pharmMLTransFunc»</Transformation>
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
	
	private def writeStandardErrorModel(SymbolReference it){
		'''
		<ErrorModel>
			<ct:Assign>
				<math:FunctionCall>
					<ct:SymbRef symbIdRef="«standardErrorName»"/>
					«FOR vp : getNamedArguments»
						«IF getStandardErrorArgument(vp.argumentName) != null»
							<math:FunctionArgument symbId="«getStandardErrorArgument(vp.argumentName)»">
								«IF !(vp.expression instanceof SymbolReference)»
									«vp.expression.pharmMLExpr»
								«ELSE»
									«vp.expression.pharmMLExpr»
								«ENDIF»
							</math:FunctionArgument>
						«ENDIF»
					«ENDFOR»
				</math:FunctionCall>
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
				val type = corrDefn.firstAttributeList.getAttributeEnumValue('type');
				if (type == 'corr' || type == 'cov'){
					val params = corrDefn.firstAttributeList.getAttributeExpression('parameter') as VectorLiteral
					val values = corrDefn.firstAttributeList.getAttributeExpression('value') as VectorLiteral
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
		return model;
	}
	
	def getLevel(MclObject mObj, SymbolDefinition randomVar){
		val randVar = mObj.findMdlSymbolDefn(randomVar.name)
		randVar.getRandomVarLevel
	}
	
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
				</Covariance>
			'''
		if (type == 'corr')
			res  = res + '''
				<CorrelationCoefficient>
					«value.expressionAsEquation»
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
	
	private def getInverseFunction(Expression linkFunction, Expression paramVar){
		switch(linkFunction){
			SymbolReference case linkFunction.func == "log": return '''
			<math:Uniop op="exp">
				«paramVar.pharmMLExpr»
			</math:Uniop>
			'''
			SymbolReference case linkFunction.func == "identity": return '''
				«paramVar.pharmMLExpr»
			'''
		}
	}
	
	private def print_mdef_CountObservations(ListDefinition s) {
		var name = s.name
		val linkFunction = s.firstAttributeList.getAttributeExpression('link');
		val distn = s.firstAttributeList.getAttributeExpression('distn');
		val paramVar = (distn as SymbolReference).getFunctionArgumentValue("lambda");
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
					<PopulationParameter symbId="«paramVar.convertToString»">
						<ct:Assign>
							«IF linkFunction != null»
								«getInverseFunction(linkFunction, paramVar)»
							«ELSE»
								«paramVar.pharmMLExpr»
							«ENDIF»
						</ct:Assign>
					</PopulationParameter>
				«ENDIF»
				<CountVariable symbId="«name»"/>
				<PMF transform="identity">
					«distn.writeUncertMlDistribution»
				</PMF>
				</CountData>
			</Discrete>
		'''
	}
	
	
	private def getSuccessCategory(SymbolReference it){
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
		val linkFunction = s.firstAttributeList.getAttributeExpression('link');
		val distn = s.firstAttributeList.getAttributeExpression('distn') as SymbolReference
		val paramVar = (distn as SymbolReference).getFunctionArgumentValue("probability")
		val categories = s.firstAttributeList.getAttributeExpression(ListDefinitionTable::OBS_TYPE_ATT);
		val catVals = categories.categories
		val catList = createCategoriesOrderedBySuccess(catVals.keySet, distn.successCategory)
		
		'''
			<Discrete>
				<CategoricalData ordered="no">
					«IF paramVar != null»
						<!-- Note that this parameter is local to this block, but uses the same name
							as the lambda argument.  --> 
						<PopulationParameter symbId="«paramVar.convertToString»">
							<ct:Assign>
								«IF linkFunction != null»
									«getInverseFunction(linkFunction, paramVar)»
								«ELSE»
									«paramVar.pharmMLExpr»
								«ENDIF»
							</ct:Assign>
						</PopulationParameter>
					«ENDIF»
					<ListOfCategories>
						«FOR cat : catList»
							<Category symbId="«cat»"/>
						«ENDFOR»
					</ListOfCategories>
					<CategoryVariable symbId="«name»"/>
					<PMF transform="identity">
						«printDiscreteDistribution(distn)»
					</PMF>
				</CategoricalData>
			</Discrete>
		'''
	}
	
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
		val categories = s.firstAttributeList.getAttributeExpression(ListDefinitionTable::OBS_TYPE_ATT);
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
		val haz = s.firstAttributeList.getAttributeExpression('hazard');
		val event = s.firstAttributeList.getAttributeEnumValue('event');
		val maxEvent = s.firstAttributeList.getAttributeExpression('maxEvent');
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
