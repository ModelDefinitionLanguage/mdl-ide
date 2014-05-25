package eu.ddmore.converter.mdl2pharmml
import org.ddmore.mdl.mdl.SymbolDeclaration
import java.util.ArrayList
import org.ddmore.mdl.mdl.BlockStatement
import org.ddmore.mdl.mdl.ConditionalStatement
import java.util.HashMap
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.mdl.Arguments
import org.ddmore.mdl.mdl.Symbols
import org.ddmore.mdl.mdl.SameBlock
import org.ddmore.mdl.validation.DistributionValidator
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.ParameterObject
import static extension eu.ddmore.converter.mdl2pharmml.Constants.*
import eu.ddmore.converter.mdl2pharmml.domain.Piece

class ModelDefinitionPrinter {
	private var String mObjName;
	private var String pObjName;
	private var ModelObject mObj;
	private var ParameterObject pObj;

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
	def print_mdef_ModelDefinition(String mObjName, String pObjName){
		this.mObjName = mObjName;
		this.pObjName = pObjName;
		this.mObj = getModelObject(mObjName);
		this.pObj = getParamObject(pObjName);
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
	// I.c Covariate ModelF
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
		var statements = "";
		if (pObj != null){
			for (b: pObj.blocks){
				//Parameter object, STRUCTURAL
				if (b.structuralBlock != null){
					for (id: b.structuralBlock.parameters) 
						statements = statements + 
						'''<SimpleParameter symbId = "«id.symbolName.name»"/>
						'''
		  		}
		  		//ParameterObject, VARIABILITY
		  		if (b.variabilityBlock != null){
					for (st: b.variabilityBlock.statements){
						if (st.parameter != null)
							statements = statements + 
							'''<SimpleParameter symbId = "«st.parameter.symbolName.name»"/>
							'''
					} 
		  		}
		  		statements = statements + print_mdef_CollerationModel;
		  	}
		}
		if (mObj != null){
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
			val mainExpr = print_Math_LogicOr(s.expression, 0).toString;
			s.ifStatement.addConditionalSymbol(mainExpr, parent, symbols);
		}
		if (s.elseStatement != null){
			val dualExpr = print_DualExpression(s.expression).toString;
			s.elseStatement.addConditionalSymbol(dualExpr, parent, symbols);
		}		
		if (s.ifBlock != null){
			val mainExpr = print_Math_LogicOr(s.expression, 0).toString;
			for (b:s.ifBlock.statements)
				b.addConditionalSymbol(mainExpr, parent, symbols);
		}
		if (s.elseBlock != null){
			val dualExpr = print_DualExpression(s.expression).toString;
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
					var Piece piece = new Piece(parent, print_Math_Expr(s.symbol.expression.expression).toString, condition);
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
	
	protected def print_Pieces(String symbol, String initTag, ArrayList<Piece> pieces, boolean printType){
		var tag = initTag;
		if ((tag.indexOf("Variable") > 0) && deriv_vars.contains(symbol))
			tag = "ct:DerivativeVariable";
		'''	
		<«tag» symbId="«symbol»"«IF printType» symbolType="«TYPE_REAL»"«ENDIF»>
			«print_Pieces(pieces)»
		</«tag»>
		'''
	}
	
	//Define order in which symbols will eb translated to PharmML	
	protected def void defineOrderOfConditionalSymbols(ConditionalStatement s, HashMap<String, Integer> symbolOrders, Integer base){
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
	
	protected def void addOrderOfConditionalSymbol(BlockStatement s, HashMap<String, Integer> symbolOrders, Integer base, Integer order){
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
	
	protected def print_mdef_RandomVariable(SymbolDeclaration s)
	'''
		«IF s.randomList != null»
			<RandomVariable symbId="«s.symbolName.name»">
				«s.print_VariabilityReference»
				«print_uncert_Distribution(s.randomList)»
			</RandomVariable>
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
			«IF s.expression.expression != null»
				«var expr = s.expression.expression»
				«var classifiedVars = getReferences(expr)»
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
				«print_Assign(s.expression.expression)»
			«ENDIF»
		</General>
	'''	
	
	//TODO: add blkIdRef
	protected def print_VariabilityReference(SymbolDeclaration s)'''
		«IF s.randomList != null»
			«val level = getAttribute(s.randomList.arguments, AttributeValidator::attr_level.name)»
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
		if (st.symbol != null)
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
			</«tag»>
		«ENDIF»
		«IF st.statement != null»
			«st.statement.print_ConditionalStatement(tag)»
		«ENDIF»
		'''
	}
	
	/////////////////////////////
	// I.i CorrelationModel
	/////////////////////////////
	protected def print_mdef_CollerationModel(){
		var model = "";
		if (pObj != null){
			for (b: pObj.blocks){
				if (b.variabilityBlock != null){
					var statements = "";
					for (c: b.variabilityBlock.statements){
						if (c.parameter != null){
							//find random variable
							var randomVar = c.parameter.getRandomVariableByVariability;
							if (randomVar != null){
								statements = statements + randomVar.print_VariabilityReference;
							}
						}	
					}
					for (c: b.variabilityBlock.statements){
						if (c.diagBlock != null){
							statements = statements + print_mdef_Matrix(c.diagBlock.arguments, c.diagBlock.parameters);
						}
						if (c.matrixBlock != null){
							statements = statements + print_mdef_Matrix(c.matrixBlock.arguments, c.matrixBlock.parameters);
						}
						if (c.sameBlock != null){
							statements = statements + print_mdef_Same(c.sameBlock);
						}
					}
					model = model +
					'''
						«IF (statements.length > 0)»
							<Correlation>
								«statements»
							</Correlation>
						«ENDIF»
					'''				
				}
			}
		}
		'''«model»''';
	}

	protected def getGetRandomVariableByVariability(SymbolDeclaration v) {
		if (v.expression == null) return null;
		if (v.expression.list  == null) return null;
		var type = getAttribute(v.expression.list.arguments, AttributeValidator::attr_re_type.name);
		var attrName = DistributionValidator::attr_var.name;
		if (type.convertMatrixType.equals(MATRIX_STDEV))
			attrName = DistributionValidator::attr_sd.name;
		if (mObj != null){
			for (b: mObj.blocks){
				if (b.randomVariableDefinitionBlock != null){
					for (s: b.randomVariableDefinitionBlock.variables){
						if (s.randomList != null){
							var variance = getAttribute(s.randomList.arguments, attrName);	
							if (variance.equals(v.symbolName.name))
								return s;
						}
					}
				}
			}
		}
		return null;
	}
	
	protected def print_mdef_Matrix(Arguments arguments, Symbols parameters){
		if (parameters != null){
			var rowNames = "";
			for (symbol: parameters.symbols){
				if (symbol.symbolName != null){
					rowNames = rowNames + print_ct_SymbolRef(pObjName, symbol.symbolName.name);
				}
			}	
			val matrixType = getAttribute(arguments, AttributeValidator::attr_re_type.name);
			print_ct_Matrix(matrixType.convertMatrixType, rowNames, parameters);
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
				var Symbols parameters = null;
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
					print_ct_Matrix(matrixType.convertMatrixType, rowNames, parameters);
				}
			}
		}
	}
}


	
