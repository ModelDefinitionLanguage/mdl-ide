package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.RandomVariable
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.MclObject
import java.util.ArrayList
import org.ddmore.mdl.mdl.BlockStatement
import org.ddmore.mdl.mdl.ConditionalStatement
import java.util.HashMap
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.mdl.MatrixBlock
import org.ddmore.mdl.types.RandomEffectType

class Mdl2PharmML{
	extension Constants dataType = new Constants();

	Mcl mcl = null;
	var DistributionPrinter distrPrinter = null;
	var MathPrinter mathPrinter = null;
	var ReferenceResolver resolver = null;

	//Print file name and analyse all MCL objects in the source file
  	def convertToPharmML(Mcl m){
  		mcl = m;  
  		resolver = new ReferenceResolver(mcl); 
		mathPrinter = new MathPrinter(resolver);
  		distrPrinter = new DistributionPrinter();

		val msPrinter = new ModellingStepsPrinter(mcl, mathPrinter, resolver);  		
		'''
		<?xml version="1.0" encoding="UTF-8"?>
		<PharmML 
			«print_PharmML_NameSpaces»
			writtenVersion="«writtenVersion»">
			<ct:Name>"«mathPrinter.fileName(m)»"</ct:Name>
			«print_mdef_IndependentVariables»
			«print_mdef_ModelDefinition»
			«print_mdef_CollerationModel»
			«msPrinter.print_msteps_ModellingSteps»
		</PharmML>
		'''
		//print_design_TrialDesign
	}
	
	//+ Print PharmML namespaces
	def print_PharmML_NameSpaces()
		'''
		xmlns:xsi="«xsi»" 
		xmlns="«xmlns_pharmML»"
		xsi:schemaLocation="«xsi_schemaLocation»"
		xmlns:math="«xmlns_math»"
		xmlns:ct="«xmlns_ct»"
		xmlns:ds="«xmlns_ds»"
		xmlns:mdef="«xmlns_mdef»"
		xmlns:mstep="«xmlns_mstep»"
		xmlns:design="«xmlns_design»"
		'''
	
	//////////////////////////////////////
	// I. Model Definition
	//////////////////////////////////////
	
	//+ convertToPharmML MCL objects
	protected def print_mdef_ModelDefinition()'''
	<ModelDefinition xmlns="«xmlns_mdef»">
		«print_mdef_VariabilityModel»
		«print_mdef_CovariateModel»
		«print_mdef_ParameterModel»
		«print_mdef_StructuralModel»
		«print_mdef_ObservationModel»
	</ModelDefinition>
	'''
	
	//////////////////////////////////////
	//IndependentVariables
	//////////////////////////////////////
	protected def print_mdef_IndependentVariables()'''
		«FOR s: resolver.ind_vars»
			<IndependentVariable symbId="«s»"/>
		«ENDFOR»
	'''
	//////////////////////////////////////
	// I.a Function Definition (not used) - call from here if needed
	//////////////////////////////////////

	/////////////////////////
	// I.b Variability Model
	/////////////////////////	
	
	protected def print_mdef_VariabilityModel(){
		var model = "";
		for (o: mcl.objects){
			if (o.modelObject != null){
				var errorVars = resolver.vm_err_vars.get(o.objectName.name);
				if (errorVars != null){
					model = model + 
					'''
						<VariabilityModel blkId="vm_err.«o.objectName.name»" type = "«VAR_TYPE_ERROR»">
							«FOR s: errorVars»
								<Level symbId="«s»"/>
							«ENDFOR»
						</VariabilityModel>
					'''		
				}
				
				var mdlVars = resolver.vm_mdl_vars.get(o.objectName.name);
				if (mdlVars != null){
					model = model + 
					'''
						<VariabilityModel blkId="vm_mdl.«o.objectName.name»" type = "VAR_TYPE_PARAMETER">
							«FOR s: mdlVars»
								<Level symbId="«s»"/>
							«ENDFOR»
						</VariabilityModel>
					'''		
				}
			}
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
		for (o: mcl.objects){
			if (o.modelObject != null){
				var covariateVars = resolver.cm_vars.get(o.objectName.name);
				if (covariateVars != null){
					model = model +
					'''
					<CovariateModel blkId="cm.«o.objectName.name»">
						«FOR s: covariateVars»
							«s.print_mdef_CovariateModel»
						«ENDFOR»
					</CovariateModel>
					'''
				}
			}
		}
		return model;
	}	

	//Keep maps of known PharmML variables per block to use it in blkRefId	
	protected def print_mdef_CovariateModel(String symbId)'''
		<Covariate symbId="«symbId»">
			<Continuous>
				<Transformation>
					<math:Equation>
						<ct:SymbRef symbIdRef="«symbId»"/>
					</math:Equation>
				</Transformation>
			</Continuous>	
		</Covariate>
	'''	
			
	/////////////////////////////
	// I.d Parameter Model
	////////////////////////////	
		
	//Parameter object, STRUCTURAL + VARIABILITY -> ParameterModel, SimpleAttribute  
	//RANDOM_VARIABBLES_DEFINITION -> ParameterModel, RandomVariable
	protected def print_mdef_ParameterModel(){		
		var model = "";
		for (o: mcl.objects){
			var statements = "";
			if (o.parameterObject != null){
				for (b: o.parameterObject.blocks){
					//Parameter object, STRUCTURAL
					if (b.structuralBlock != null){
						for (id: b.structuralBlock.parameters) 
							statements = statements + '''<SimpleParameter symbId = "«id.symbolName.name»"/>'''
			  		}
			  		//ParameterObject, VARIABILITY
			  		if (b.variabilityBlock != null){
						for (st: b.variabilityBlock.statements){
							if (st.parameter != null)
								statements = statements + '''<SimpleParameter symbId = "«st.parameter.symbolName.name»"/>'''
						} 
			  		}
			  	}
			}
			if (o.modelObject != null){
				for (b: o.modelObject.blocks){
					//Model object, GROUP_VARIABLES (covariate parameters)
					if (b.groupVariablesBlock != null){
						for (st: b.groupVariablesBlock.statements){
							if (st.statement != null){
								st.statement.print_BlockStatement("SimpleParameter", false);
							}							
						}
					}	
					//Model object, RANDOM_VARIABLES_DEFINITION
					if (b.randomVariableDefinitionBlock != null){
						for (s: b.randomVariableDefinitionBlock.variables){
							if (resolver.isIndependentVariable(s.symbolName.name))
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
			}
	  		if (statements.length > 0){
		  		model = model + 
				'''
					<ParameterModel blkId="pm.«o.objectName.name»">
						«statements»
					</ParameterModel>
				''';
			}
  		}
  		return model;
	}
	
	protected def print_ConditionalStatement(ConditionalStatement s, String tag){
		var symbols = new HashMap<String, ArrayList<Piece>>();
		var symbolOrders = new HashMap<String, Integer>();
		var Piece parent = null;
		s.prepareConditionalSymbols(parent, symbols);
		s.defineOrderOfConditionalSymbols(symbolOrders, 0);
		var max  = 0;
		for (o: symbolOrders.entrySet){
			if (max < o.value) max = o.value;
		}
		var model = "";
		for (i: 0..max){
			for (o: symbolOrders.entrySet){
				if (i == o.value) {//print a symbol declaration with this number
					val ArrayList<Piece> pieces = symbols.get(o.key);
					if (pieces != null)
						model = model + o.key.print_Pieces(tag, pieces, true);
				}
			}	
		}
		return model;
	}	
	
	
	protected def prepareConditionalSymbols(ConditionalStatement s, Piece parent, HashMap<String, ArrayList<Piece>> symbols){
	 	if (s.ifStatement != null){
			val mainExpr = mathPrinter.print_Math_LogicOr(s.expression, 0).toString;
			s.ifStatement.addConditionalSymbol(mainExpr, parent, symbols);
		}
		if (s.elseStatement != null){
			val dualExpr = mathPrinter.print_DualExpression(s.expression).toString;
			s.elseStatement.addConditionalSymbol(dualExpr, parent, symbols);
		}		
		if (s.ifBlock != null){
			val mainExpr = mathPrinter.print_Math_LogicOr(s.expression, 0).toString;
			for (b:s.ifBlock.statements)
				b.addConditionalSymbol(mainExpr, parent, symbols);
		}
		if (s.elseBlock != null){
			val dualExpr = mathPrinter.print_DualExpression(s.expression).toString;
			for (b:s.elseBlock.statements)
				b.addConditionalSymbol(dualExpr, parent, symbols);
		}
	}	
	 
	protected def void addConditionalSymbol(BlockStatement s, String condition, Piece parent, HashMap<String, ArrayList<Piece>> symbols){
		if (s.symbol != null){
			if (s.symbol.expression != null){
				if (s.symbol.expression.expression != null){
					var pieces = symbols.get(s.symbol.symbolName.name); 
					if (pieces == null) pieces = new ArrayList<Piece>();
					var Piece piece = new Piece(parent, mathPrinter.print_Math_Expr(s.symbol.expression.expression).toString, condition);
					pieces.add(piece);
					symbols.put(s.symbol.symbolName.name, pieces);
				}
			}
		}	
		if (s.statement != null){//nested conditional statement
			var Piece newParent = new Piece(parent, null, condition);
			s.statement.prepareConditionalSymbols(newParent, symbols);
		}
	}
	
	def print_Pieces(String symbol, String tag, ArrayList<Piece> pieces, boolean printType)'''
	<«tag» symbId="«symbol»"«IF printType» symbolType="«TYPE_REAL»"«ENDIF»>
		«mathPrinter.print_Pieces(pieces)»
	</«tag»>
	'''
	
	//Define order in which symbols will eb translated to PharmML	
	def void defineOrderOfConditionalSymbols(ConditionalStatement s, HashMap<String, Integer> symbolOrders, Integer base){
		if (s.ifStatement != null){
			s.ifStatement.addOrderOfConditionalSymbol(symbolOrders, base, 0);
		}
		if (s.elseStatement != null){
			s.elseStatement.addOrderOfConditionalSymbol(symbolOrders, base, 0);
		}		
		if (s.ifBlock != null){
			var i = 0;
			for (b:s.ifBlock.statements){
				b.addOrderOfConditionalSymbol(symbolOrders, base, i);
				i = i + 1;
			}
		}
		if (s.elseBlock != null){
			var i = 0;
			for (b:s.elseBlock.statements){
				b.addOrderOfConditionalSymbol(symbolOrders, base, i);
				i = i + 1;
			}
		}
	}	
	
	def void addOrderOfConditionalSymbol(BlockStatement s, HashMap<String, Integer> symbolOrders, Integer base, Integer order){
		if (s.symbol != null){
			var prev = symbolOrders.get(s.symbol.symbolName.name); 
			if (prev == null) prev = 0;
			if (prev <= base + order)
				symbolOrders.put(s.symbol.symbolName.name, base + order);
		}	
		if (s.statement != null){//nested conditional statement
			s.statement.defineOrderOfConditionalSymbols(symbolOrders, base + order);
		}
	}	
	
	def print_mdef_RandomVariable(RandomVariable s)'''
		<RandomVariable symbId="«s.symbolName.name»">
			«s.print_VariabilityReference»
			«distrPrinter.print_uncert_Distribution(s.randomList)»
		</RandomVariable>
	'''
	
	/////////////////////////
	// I.e Structural Model
	/////////////////////////
	
	//+ STRUCTURAL_PARAMETER -> <StructuralModel>
	def print_mdef_StructuralModel(){
		var model ="";
		for (o: mcl.objects){
			if (o.modelObject != null){
				var variables = "";
				for (b: o.modelObject.blocks){
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
						<StructuralModel blkId="sm.«o.objectName.name»">
							«IF (variables.length > 0)»«variables»«ENDIF»
						</StructuralModel>
					«ENDIF»
				'''
			}
		}
		return model;
	}
		
	/////////////////////////////
	// I.f Observation Model
	/////////////////////////////
	def print_mdef_ObservationModel(){
		var model = "";
		for (o: mcl.objects){
			if (o.modelObject != null){
				var statements = "";
				for (b: o.modelObject.blocks){
					if (b.observationBlock != null){
						for (st: b.observationBlock.statements){
							statements = statements + '''«st.print_mdef_ObservationModel»''';
						}
					}
				}
				model = model +
				'''
					«IF (statements.length > 0)»
						<ObservationModel blkId="om.«o.objectName.name»">
							«statements»
						</ObservationModel>
					«ENDIF»
				'''				
			}
		}
		return model;
	}
	
	//Print splitting random variables and simple parameters, needed for ObservationModel 
	def print_mdef_ObservationModel(BlockStatement st)'''
		«IF st.symbol != null»
			«st.symbol.print_mdef_ObservationModel»
		«ENDIF»
		«IF st.statement != null»
		«ENDIF»
	'''
	//TODO print conditionally defined observation models «st.statement.print_ConditionalStatement(tag)»
		
	//Print observation model declaration
	def print_mdef_ObservationModel(SymbolDeclaration s)'''
		«IF s.expression != null»
			«IF s.expression.expression != null»
				«var expr = s.expression.expression»
				«var classifiedVars = resolver.getReferences(expr)»
				«IF classifiedVars.size > 0»
					«FOR ss: classifiedVars.entrySet.filter[x | x.value.equals("random")]»
						«val ref = ss.key.defineDistribution»
						«IF ref != null»
							«ref.print_mdef_RandomVariable»
						«ENDIF»
					«ENDFOR»
				«ENDIF»
			«ENDIF»
		«ENDIF»
		<General symbId="«s.symbolName.name»">
			«IF s.expression.expression != null»
				«mathPrinter.print_Assign(s.expression.expression)»
			«ENDIF»
		</General>
	'''	
	
	//
	def print_VariabilityReference(RandomVariable s)'''
		«val level = mathPrinter.getAttribute(s.randomList.arguments, AttributeValidator::attr_level.name)»
		«IF level.length > 0»
			<ct:VariabilityReference>
				<ct:SymbRef symbIdRef="«level»"/>
			</ct:VariabilityReference>
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
	
	//+ returns distribution for the first declaration with a given variance
	def defineDistribution(String ref){
		//find paramName in RANDOM_VARIABLES_DEFINITION
		for (MclObject o: mcl.objects){
			if (o.modelObject != null){
				for (b: o.modelObject.blocks){
					if(b.randomVariableDefinitionBlock != null){
						for (s: b.randomVariableDefinitionBlock.variables){
							if (s.symbolName.name.equals(ref))
								return s;
						}
					}
				}
			}
		}
		return null;
	}
	
	def print_BlockStatement(BlockStatement st, String tag, Boolean printType)'''
		«IF st.symbol != null»
			<«tag» symbId="«st.symbol.symbolName.name»"«IF printType» symbolType="«TYPE_REAL»"«ENDIF»>
				«IF st.symbol.expression != null»
					«IF st.symbol.expression != null»
						«mathPrinter.print_Assign(st.symbol.expression)»
					«ENDIF»
				«ENDIF»
			</«tag»>
		«ENDIF»
		«IF st.statement != null»
			«st.statement.print_ConditionalStatement(tag)»
		«ENDIF»
	'''
	
	/////////////////////////////
	// I.i CorrelationModel
	/////////////////////////////
	def print_mdef_CollerationModel(){
		var model = "";
		for (o: mcl.objects){
			if (o.parameterObject != null){
				for (b: o.parameterObject.blocks){
					if (b.variabilityBlock != null){
						var statements = "";
						for (c: b.variabilityBlock.statements){
							if (c.diagBlock != null){
								//TODO process diag blocks
							}
							if (c.matrixBlock != null){
								statements = statements + print_mdef_Matrix(o.objectName.name, c.matrixBlock);
							}
							if (c.sameBlock != null){
							}
						}
						model = model +
						'''
							«IF (statements.length > 0)»
								<Correlation blkId="c.«o.objectName.name»">
									«statements»
								</Correlation>
							«ENDIF»
						'''				
					}
				}
			}
		}
		'''«model»''';
	}	
	
	def print_mdef_Matrix(String objName, MatrixBlock matrix){
		val matrixType = mathPrinter.getAttribute(matrix.arguments, AttributeValidator::attr_re_type.name);
		var rowNames = "";
		if (matrix.parameters != null){
			for (symbol: matrix.parameters.symbols){
				if (symbol.symbolName != null){
					//TODO: should be reference!
					rowNames = rowNames + mathPrinter.print_ct_SymbolRef(symbol.symbolName.name);
				}
			}	
			'''
				<Matrix matrixType="«matrixType.getMatrixType»">
					<ct:RowNames>
						«rowNames»
					</ct:RowNames>
					«FOR i: 0..matrix.parameters.symbols.size - 1»
						«val symbol = matrix.parameters.symbols.get(i)»
						«IF symbol.symbolName != null»
							<MatrixRow>
						«ENDIF»
							«mathPrinter.print_Math_Expr(symbol.expression)»
						«IF i < matrix.parameters.symbols.size - 1»
							«IF matrix.parameters.symbols.get(i+1).symbolName != null»
								</MatrixRow>
							«ENDIF»
						«ELSE»
							</MatrixRow>
						«ENDIF»
					«ENDFOR»	
				</Matrix>
			'''			
		}
	}
	
	def getMatrixType(String matrixType){
		if (matrixType.equals(RandomEffectType::RE_VAR))
			return "CovMatrix";
		if (matrixType.equals(RandomEffectType::RE_SD))
			return "StDevCorrMatrix";
		return "CovMatrix";	
	}
}