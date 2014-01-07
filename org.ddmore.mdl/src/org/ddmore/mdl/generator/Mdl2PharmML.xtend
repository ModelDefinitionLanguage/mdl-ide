package org.ddmore.mdl.generator

import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.BlockStatement
import org.ddmore.mdl.mdl.ConditionalExpression
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName
import org.ddmore.mdl.mdl.FunctionCall
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.MclObject
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.ModelObjectBlock
import org.ddmore.mdl.mdl.OrExpression
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.UnaryExpression
import org.ddmore.mdl.mdl.AndExpression
import org.ddmore.mdl.mdl.MdlFactory
import org.ddmore.mdl.mdl.LogicalExpression
import org.ddmore.mdl.mdl.AdditiveExpression
import org.ddmore.mdl.mdl.MultiplicativeExpression
import org.ddmore.mdl.mdl.PowerExpression
import org.ddmore.mdl.mdl.FullyQualifiedArgumentName
import org.ddmore.mdl.mdl.Vector
import org.ddmore.mdl.mdl.List
import java.io.FileReader
import java.io.BufferedReader
import java.util.ArrayList
import java.io.FileNotFoundException
import java.io.File

class Mdl2PharmML extends MdlPrinting{
	
	val	xsi="http://www.w3.org/2001/XMLSchema-instance"; 
	val xsi_schemaLocation="http://www.pharmml.org/2013/03/PharmML http://www.pharmml.org/2013/03/PharmML";
	val xmlns_pharmML="http://www.pharmml.org/2013/03/PharmML";
	val xmlns_math="http://www.pharmml.org/2013/03/Maths";
	val xmlns_ct="http://www.pharmml.org/2013/03/CommonTypes";
	val xmlns_mdef="http://www.pharmml.org/2013/03/ModelDefinition";
	val xmlns_mstep="http://www.pharmml.org/2013/03/ModellingSteps";
	val xmlns_design="http://www.pharmml.org/2013/03/TrialDesign";
	val xmlns_uncert="http://www.pharmml.org/2013/03/Uncertainty";
	
	val TYPE_INT = "int";
	val TYPE_REAL = "real";
	val writtenVersion = "0.1";
	val level_map = newArrayList("residual", "indiv");
	
	var Mcl mcl = null;
	//Print file name and analyse all MCL objects in the source file
  	def convertToPharmML(Mcl m){
  		mcl = m;
  		
  		//Create a map of variables
  		m.prepareCollections;
  		
  		var version = "1.002";
  		var date = "20.11.2013";
		val info = "mdl2pharmML " + version + " beta, last modification " + date + " Natallia Kokash (natallia.kokash@gmail.com)";  
		var printIndependent = m.isIndependentVariableDefined; 
		'''
		«info.print_XS_Comment»
		<?xml version="1.0" encoding="UTF-8"?>
		<PharmML 
			«print_PharmML_NameSpaces»
			writtenVersion="«writtenVersion»">
			<ct:Name">«m.eResource.fileName»"</ct:Name>
			«IF printIndependent»
				<IndependentVariable symbID="t"/>
			«ENDIF»
  			«print_mdef_ModelDefinition»
		</PharmML>
		'''
	}
	
	//+ Print PharmML namespaces
	def print_PharmML_NameSpaces()
		'''
		xmlns:xsi="«xsi»" 
		xmlns="«xmlns_pharmML»"
		xsi:schemaLocation="«xsi_schemaLocation»"
		xmlns:math="«xmlns_math»"
		xmlns:ct="«xmlns_ct»"
		xmlns:mdef="«xmlns_mdef»"
		xmlns:mstep="«xmlns_mstep»"
		xmlns:design="«xmlns_design»"
		xmlns:uncert="«xmlns_uncert»"
		'''
	
	//+ convertToPharmML MCL objects
	def print_mdef_ModelDefinition()'''
	<ModelDefinition xmlns="«xmlns_mdef»">
		«print_mdef_FunctionDefinition»
		«print_mdef_VariabilityModel»
		«print_mdef_ParameterModel»
		«print_mdef_StructuralModel»
		«print_mdef_CovariateModel»
		«print_mdef_ObservationModel»
		«print_msteps_ModellingSteps»
	</ModelDefinition>
	'''
	/////////////////////
	//Function Definition
	/////////////////////
	def print_mdef_FunctionDefinition() { 
		//It is not used as in MDL we do not have user defined function
	}
	
	//Generate function definition from a math expression like a + b*f
	def print_mdef_FunctionDefinition(Expression expr) { 
		var arguments = newHashSet;
		var iterator = expr.eAllContents();
	    while (iterator.hasNext()){
	    	var obj = iterator.next();
	    	if (obj instanceof FullyQualifiedSymbolName){
	    		var ref = obj as FullyQualifiedSymbolName;
	    		arguments.add(ref.toStr);
	    	}
	    }
		'''
		<FunctionDefinition xmlns:ct="«xmlns_ct»"
			symbId="combinedErrorModel" symbolType="real">
			«FOR arg: arguments»
				<FunctionArgument symbId="«arg»" symbolType="«TYPE_REAL»"/>
			«ENDFOR»
			<Definition>
				«expr.print_Math_Equation»
			</Definition>
		</FunctionDefinition>
		'''
	}

	/////////////////////
	//Variability Model
	/////////////////////	
	
	//residual - 1, indiv - 2
	def print_mdef_VariabilityModel(){
		var model = '''''';
		for (e: level_vars.entrySet){
			if (e.value.equals("1"))
				model = model + '''«print_mdef_VariabilityModel("obsErr", "error", level_map.get(0))»''';
			if (e.value.equals("2"))	
				model = model + '''«print_mdef_VariabilityModel("model", "model", level_map.get(1))»''';
		}
		'''«model»''';
	}
	
	
	//+ TODO: derive from MDL
	def print_mdef_VariabilityModel(String blkId, String type, String symbId)	
	'''	
	<VariabilityModel blkId="«blkId»" type="«type»">
		<Level symbId="«symbId»/">
	</VariabilityModel>
	'''	
		
	/////////////////////////////
	//Parameter Model
	////////////////////////////	
		
	//+ INDIVIDUAL_VARIABLES -> <ParameterModel>  
	def print_mdef_ParameterModel(){
		var parameterModel = "";
		var i = 1;
		var blockName = "p" + i;
		for(s: theta_vars.keySet) {
			val paramName = s as String;
			if (paramName != null){
				var varName = paramName;
				var operator = "";
				val _index = paramName.indexOf('_');
				if (_index > -1) {
					varName = paramName.substring(_index + 1);
					var idv = varName.findIndividualVariable;
					if (idv != null) operator = idv.defineTransformationOperator;
				}			
				parameterModel = parameterModel + 
				'''
				<ParameterModel id="«blockName»">
					«print_mdef_ModelParameter(paramName, varName, blockName, operator)»
				</ParameterModel>
				'''
			}
			i = i + 1;
		}
		'''
		«parameterModel»
		'''
	}
	
	//+ TODO: old version of PharmML, update!
	def print_mdef_ModelParameter(String paramName, String varName, String blockName, String operator)'''
		«IF paramName != null»
			<Parameter symbId = "«paramName»"/>
			«IF varName != null»
				<Parameter symbId = "omega_«varName»"/>
				<Parameter symbId = "«varName»"«IF operator != null»«IF operator.length > 0» transformation="«operator»">«ENDIF»«ENDIF» 
					<Equation xmlns="«xmlns_math»" writtenVersion="«writtenVersion»">;
						<Uniop op="«operator»">
							<Var symbId="«paramName»"/>
						</Uniop>
					</Equation>
				</Parameter> 
			«ENDIF»
		«ENDIF»
	'''

	////////////////////
	//Structural Model
	////////////////////
	
	//+ STRUCTURAL_PARAMETER -> <StructuralModel>
	def print_mdef_StructuralModel(){
		var parameters = "";
		var i = 1;
		var blockName = "p" + i;
		for(s: theta_vars.keySet) {
			val paramName = s as String;
			if (paramName != null){
				var varName = paramName;
				var _index = paramName.indexOf('_');
				if (_index > -1) {
					varName = paramName.substring(_index + 1);
					val idv = varName.findIndividualVariable;
					if (idv != null) parameters = parameters + varName.print_mdef_Parameter(blockName);
				}			
			}
			i = i + 1;
		}
		var variables = "";
		var initial = "";
		for (o: mcl.objects){
			if (o.modelObject != null){
				for (b: o.modelObject.blocks){
					if (b.modelPredictionBlock != null){
						for (st: b.modelPredictionBlock.statements){
							if (st.odeBlock != null){
								for (s: st.odeBlock.statements){
									if (s.symbol != null){
										variables = variables + '''«s.symbol.print_ct_VariableDefinitionType»''';
										initial = initial + '''«s.symbol.print_InitialCondition»''';
									}
								}
							}
						}
					}
				}
			}
		}
		'''
		«IF (parameters.length > 0) || (variables.length > 0)»
			<StructuralModel id="main">
				«IF (parameters.length > 0)»«parameters»«ENDIF»
				«IF (variables.length > 0)»«variables»«ENDIF»
				«IF (initial.length > 0)»«initial»«ENDIF»
			</StructuralModel>
		«ENDIF»'''
	}
	
	/////////////////////////
	//Covariate Model
	/////////////////////////
	//GROUP_VARIABLES -> CovariateModel
	def print_mdef_CovariateModel(){
		var model = "";
		for (o: mcl.objects){
			if (o.modelObject != null){
				for (b: o.modelObject.blocks){
					if (b.groupVariablesBlock != null){
						for (st: b.groupVariablesBlock.statements){
							if (st.statement != null){
								if (st.statement.symbol != null){
									model = model + '''«st.statement.symbol.print_ct_Covariate»''';
								}
							}
						}
					}
				}
			}
		}
		'''
		«IF (model.length > 0)»
			<CovariateModel>
				«model»
			</CovariateModel>
		«ENDIF»
		'''
	}
	
	def print_ct_Covariate(SymbolDeclaration s) 
		'''
		<SimpleParameter  symbId="pop_«s.identifier»"/>
		<SimpleParameter  symbId="omega_«s.identifier»"/>
		<Covariate symbId="«s.identifier»">
			<Continuous>
				«s.expression.print_Math_Expr»
			</Continuous>	
		</Covariate>
		'''	
	
	/////////////////////////////
	//Observation Model
	/////////////////////////////
	def print_mdef_ObservationModel(){
		var model = "";
		for (o: mcl.objects){
			if (o.modelObject != null){
				for (b: o.modelObject.blocks){
					if (b.observationBlock != null){
						for (st: b.observationBlock.statements){
							if (st.symbol != null){
								model = model + '''«st.symbol.print_mdef_ObservationModel»''';
							}
						}
					}
				}
			}
		}
		'''
		«IF (model.length > 0)»
			<ObservationModel>
				«model»
			</ObservationModel>
		«ENDIF»
		'''
	}
	
	//Note: here we print an expression in Random Effect, in the example its value is used (attribute value)
	def print_mdef_ObservationModel(SymbolDeclaration s)
		'''
		«IF s.expression != null»
			«IF s.expression.expression != null»
				«s.expression.expression.print_mdef_ObservationModel»
			«ENDIF»
		«ENDIF»
		<General symbId="«s.identifier»"/>
			«IF s.expression.expression != null»
				<ct:Assign>
					«s.expression.expression.print_Math_Equation»
				</ct:Assign>
			«ENDIF»
		</General>
		'''
	
		//DV = 1 = eps = residual, ID = 2 = eta = indiv
		//+ New version of PharmML - changed
		def print_mdef_ObservationModel(Expression expr)'''
			«var classifiedVars = expr.classifyReferences»
			«var simpleVars = classifiedVars.filter[k, v | v.equals("other")]»
			«IF simpleVars.size > 0»
				«FOR s: simpleVars.keySet»
					«val ref = s as String»
					<SimpleParameter symbId="«ref»"/>
				«ENDFOR»
			«ENDIF»	
			«var randomVars = classifiedVars.filter[k, v | v.equals("eps")]»
			«IF randomVars.size > 0»
				«FOR s: randomVars.keySet»
					«val ref = s as String»
					<RandomVariable symbIdRef="«ref»">
						<ct:VariabilityReference>
							<ct:SymbRef symbIdRef="«level_map.get(0)»"/>
						</ct:VariabilityReference>
						«ref.print_uncert_Distribution»
					</RandomVariable>
				«ENDFOR»
			«ENDIF»
		'''
	
			/* 
			«var errorVars = classifiedVars.filter[k, v | v.equals("theta")]»
			«IF errorVars.size > 0»
				«FOR s: errorVars.keySet»
					«val ref = s as String»
					«ref.print_mdef_ErrorModel»
				«ENDFOR»
			«ENDIF»	*/	
			
	/////////////////////
	//Error Model
	/////////////////////
	//For named arguments - reorder and match declaration!	
	def print_mdef_ErrorModel(FunctionCall call)'''
    <ErrorModel>
    	<ct:Assign>
            <Equation xmlns="«xmlns_math»">
                <FunctionCall>
                    <ct:SymbRef symbIdRef="«call.identifier.identifier»"/
                    «FOR arg: call.arguments.arguments»
                    <FunctionArgument «IF arg.identifier!= null» symbId="«arg.identifier»"«ENDIF»>
                        «arg.expression.print_Math_Expr»
                    </FunctionArgument>
                    «ENDFOR»
                </FunctionCall>
            </Equation>
        </ct:Assign>
    </ErrorModel>
	'''
		
	
	def print_InitialCondition(SymbolDeclaration s)'''
		«IF s.expression != null»
			«IF s.expression.odeList != null»
				«var init = s.expression.odeList.arguments.getAttributeExpression("init")»
				«IF init != null»
					«IF init.expression != null»
						<InitialCondition symbID="«s.identifier»">
							«init.expression.print_Math_Expr»
						</InitialCondition>
					«ENDIF»	
				«ENDIF»
			«ENDIF»		
		«ENDIF»
	'''
	
	//+ Finds definition of a variable with a given name
	def findIndividualVariable(FullyQualifiedSymbolName ref){
		//find paramName in INDIVIDUAL_VARIABLES
		for (MclObject o: mcl.objects){
			if (o.modelObject != null){
				if ((ref.object == null) || o.identifier.name.equalsIgnoreCase(ref.object.name))
					for (ModelObjectBlock b: o.modelObject.blocks){
						if(b.individualVariablesBlock != null){
							for (BlockStatement st: b.individualVariablesBlock.statements){
								if (st.symbol != null){
									var name = st.symbol.identifier;
									if (name.equalsIgnoreCase(ref.identifier)){
										return st.symbol;
									}
								}
							}
						}
					}
			}
		}
		return null;
	}
	
	//+ Finds definition of a variable with a given full name
	def findIndividualVariable(String fullName){
		return fullName.toFullyQualifiedSymbolName.findIndividualVariable;
	}
	
	def toFullyQualifiedSymbolName(String fullName){
		var ref = MdlFactory::eINSTANCE.createFullyQualifiedSymbolName;
		val _index = fullName.indexOf('$');
		var varName = fullName;
		if (_index > 0) {
			varName = fullName.substring(_index + 1);
			val objName = fullName.substring(0, _index);
			val object = MdlFactory::eINSTANCE.createObjectName; 
			object.setName(objName);
			ref.setObject(object);
		}
		ref.setIdentifier(varName);		
		return ref;
	}
	
	//+ Find a transformation operator
	def defineTransformationOperator(SymbolDeclaration idv){
		if (idv.function != null){
			//explicit definition in the form funct(ID)=expr;
			return idv.function;
		} else {
			/*if (st.symbol.expression != null){
			//TODO: Parse expr to detect exp(x) function?
			}*/
			return "ln";
		}
	}
	
	//+ returns distribution for the first declaration with a given variance
	def defineDistribution(String ref){
		//find paramName in RANDOM_VARIABLES_DEFINITION
		for (MclObject o: mcl.objects){
			if (o.modelObject != null){
				for (b: o.modelObject.blocks){
					if(b.randomVariableDefinitionBlock != null){
						for (SymbolDeclaration s: b.randomVariableDefinitionBlock.variables){
							//var variance = s.randomList.arguments.getAttribute("variance");
							if (s.identifier.equalsIgnoreCase(ref)){
								return s.randomList.arguments;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	//+ For each reference, define its purpose
	def classifyReferences(Expression expr){
		var classifiedVars = newHashMap;
		var iterator = expr.eAllContents();
	    while (iterator.hasNext()){
	    	var obj = iterator.next();
	    	if (obj instanceof FullyQualifiedSymbolName){
	    		var ref = obj as FullyQualifiedSymbolName;
	    		if (classifiedVars.get(ref.toStr) == null){
		    		if (theta_vars.get(ref.toStr) != null)
			    		classifiedVars.put(ref.toStr, "theta")
			    	else 
			    	{	
			    		if (eps_vars.get(ref.toStr) != null)
			    			classifiedVars.put(ref.toStr, "eps")
			    		else 
			    		{	
			    			if (eta_vars.get(ref.toStr) != null)
			    				classifiedVars.put(ref.toStr, "eta")
			    			else 
			    				classifiedVars.put(ref.toStr, "other")
			    		}
			    	}
	    		}
	    	}
	    }
	    return classifiedVars;
	}

	
	/////////////////////////////////////
	//Print expression
	/////////////////////////////////////

	//+
	def print_mdef_Parameter(String varName, String blockName)'''
		<Parameter symbId = "«varName»">;
			<Var block="«blockName»" xmlns="«xmlns_math»" symbId="«varName»"/>
		</Parameter>
	'''
	
	//+
	def print_Math_Equation(Expression expr)'''
		<Equation xmlns="«xmlns_math»" writtenVersion="«writtenVersion»">;
			«expr.print_Math_Expr»
		</Equation>"
	'''
	
	//+
	def print_Math_Expr(Expression expr)'''
		«expr.conditionalExpression.print_Math_LogicOp» 
	'''
	
	//+
	def print_Math_LogicOp(ConditionalExpression expr)'''
		«IF expr.expression1 != null»
			<Piecewise>
				«expr.expression1.print_Math_LogicOpPiece(expr.expression)»
				«expr.expression2.print_Math_LogicOpPiece(expr.expression.dualExpression)»
			</Piecewise>
		«ELSE»
			«expr.expression.print_Math_LogicOr(0)»
		«ENDIF»
	'''
		
	
	//+
	def print_Math_LogicOpPiece(Expression expr, OrExpression condition)'''
		<Piece>
			«expr.print_Math_Expr»
			<Condition>
				«condition.print_Math_LogicOr(0)»
			</Condition>
		</Piece>
	'''
	
	//+
	def print_Math_LogicOr(OrExpression expr, int startIndex){
		if (expr.expression != null){
			if (startIndex < expr.operator.size-1){
				return 
				'''
				<LogicBinop op="or">
					«expr.expression.get(startIndex).print_Math_LogicAnd(0)»
					«expr.print_Math_LogicOr(startIndex + 1)»
				</LogicBinop>
				'''
			} else {
				return '''«expr.expression.get(startIndex).print_Math_LogicAnd(0)»'''
			}
		}
		return ''''''
	}

	//+
	def print_Math_LogicAnd(AndExpression expr, int startIndex){
		if (expr.expression != null){
			if (startIndex < expr.operator.size-1){
				return 
				'''
				<LogicBinop op="and">
					«expr.expression.get(startIndex).print_Math_LogicOp(0)»
					«expr.print_Math_LogicAnd(startIndex + 1)»
				</LogicBinop>
				'''
			} else {
				return '''«expr.expression.get(startIndex).print_Math_LogicOp(0)»'''
			}
		}
		return ''''''
	}
	
	//MDL: no XOR operator
	
	//+
	def print_Math_LogicOp(LogicalExpression expr, int startIndex){
		if (expr.expression != null){
			if (startIndex < expr.operator.size-1){
				return 
				'''
				<LogicBinop op="«expr.operator.get(startIndex + 1).convertOperator»">
					«expr.expression.get(startIndex).print_Math_AddOp(0)»
					«expr.print_Math_LogicOp(startIndex + 1)»
				</LogicBinop>
				'''
			} else {
				return '''«expr.expression.get(startIndex).print_Math_AddOp(0)»'''
			}
		}
		return ''''''
	}
	
	//+
	def print_Math_AddOp(AdditiveExpression expr, int startIndex) { 
		if (expr.expression != null){
			if (startIndex < expr.operator.size-1){
				return 
				'''
				<Binop op="«expr.operator.get(startIndex + 1).convertOperator»">
					«expr.expression.get(startIndex).print_Math_MultOp(0)»
					«expr.print_Math_AddOp(startIndex + 1)»
				</Binop>
				'''
			} else {
				return '''«expr.expression.get(startIndex).print_Math_MultOp(0)»'''
			}
		} else {
			if (expr.string != null){
				var res = "";
				for (s: expr.string){
					res = res + s; 
				}
				return 
				'''<ct:String>«res»</ct:String>'''
			}
		}
		return ''''''	
		
	}
	
	//+
	def print_Math_MultOp(MultiplicativeExpression expr, int startIndex) { 
		if (expr.expression != null){
			if (startIndex < expr.operator.size-1){
				return 
				'''
				<Binop op="«expr.operator.get(startIndex + 1).convertOperator»">
					«expr.expression.get(startIndex).print_Math_PowerOp(0)»
					«expr.print_Math_MultOp(startIndex + 1)»
				</Binop>
				'''
			} else {
				return '''«expr.expression.get(startIndex).print_Math_PowerOp(0)»'''
			}
		}
		return ''''''		
	}
	
	//+
	def print_Math_PowerOp(PowerExpression expr, int startIndex) { 
			if (expr.expression != null){
			if (startIndex < expr.operator.size-1){
				return 
				'''
				<Binop op="«expr.operator.get(startIndex + 1).convertOperator»">
					«expr.expression.get(startIndex).print_Math_UniOp»
					«expr.print_Math_PowerOp(startIndex + 1)»
				</Binop>
				'''
			} else {
				return '''«expr.expression.get(startIndex).print_Math_UniOp»'''
			}
		}
		return ''''''				
	}
	
	//+
	def print_Math_BinOp(String operator, Expression expr1, Expression expr2)'''
		<Binop op="«operator»">
			«expr1.print_Math_Equation»
			«expr2.print_Math_Equation»
		</Binop>
	'''	
	
	//+
	def print_Math_BinOp(String operator, UnaryExpression expr1, UnaryExpression expr2)'''
		<Binop op="«operator»">
			«expr1.print_Math_UniOp»
			«expr2.print_Math_UniOp»
		</Binop>
	'''
	
	//+
	def print_Math_UniOp(UnaryExpression expr)'''
		«IF expr.operator != null»
			<Uniop op="«expr.operator»">
				«expr.expression.print_Math_UniOp»
			</Uniop>
		«ELSE»
			«IF expr.parExpression != null»
				«expr.parExpression.expression.print_Math_Expr»
			«ELSE»
				«IF expr.primary != null»
					«IF expr.primary.functionCall != null»
						«expr.primary.functionCall.print_Math_FunctionCall»
					«ENDIF»
					«IF expr.primary.number != null»
						«expr.primary.number.print_ct_Value»
					«ENDIF»
					«IF expr.primary.symbol !=null»
						«expr.primary.symbol.print_ct_SymbolRef»
					«ENDIF»
					«IF expr.primary.attribute !=null»
						«expr.primary.attribute.print_ct_SymbolRef»
					«ENDIF»
					«IF expr.primary.vector != null»
						//expr.primary.vector
					«ENDIF»
				«ENDIF»	
			«ENDIF»
		«ENDIF»
	'''
	
	//+
	def print_ct_Vector(Vector vector)'''
		<ct:Vector>
			«FOR v: vector.values»
				«v.print_Math_Expr»
			«ENDFOR»
		</ct:Vector>
	'''
	
	//+
	def print_ct_SymbolRef(FullyQualifiedSymbolName ref)'''
		<ct:SymbRef«IF ref.object != null» blkIdRef="«ref.object»"«ENDIF» symbIdRef="«ref.identifier»"/>
	'''
	
	//+ TODO: How to print attributes?
	def print_ct_SymbolRef(FullyQualifiedArgumentName ref)'''
		<Description>MDL reference to an attribute «ref.toStr»</Description>
		<ct:SymbRef «IF ref.parent != null»«IF ref.parent.object != null»blkIdRef="«ref.parent.object»"«ENDIF»«ENDIF» 
			symbIdRef="«ref.parent.identifier».«ref.toStr»"/>
	'''
	
	//+
	def print_ct_Value(String value){
		try{        			
        	if (value.indexOf(".") > -1){
        			Double::parseDouble(value);
					return '''<ct:Double>«value»</ct:Double>''';
        		} else {
	       			Integer::parseInt(value);
    	   			return '''<ct:Int>«value»</ct:Int>''';	
        		}
        }
		catch (NumberFormatException e) {
			return '''<ct:String>«value»</ct:String>''';
		}
	}
	
	
	
	//type: int, string, etc.
	def print_ct_Value(String value, String type)'''
		<ct:«type»>«value»</ct:«type»>
	'''
	
	
	//+ TODO: modify to print correctly any distribution
	def print_uncert_Distribution(String ref)'''
		«var args = ref.defineDistribution»
		«IF args != null»
			«var distrType = args.getAttribute("type")»
			«var mean = args.getAttributeExpression("mean")»
			«var variance = args.getAttributeExpression("variance")»			
			«IF (distrType.length > 0) && distrType.equalsIgnoreCase('Normal')»
				<NormalDistribution xmlns="«xmlns_uncert»" writtenVersion = "«writtenVersion»>";
					<Mean>
						«IF mean != null»«mean.print_Math_Expr»«ENDIF»
					</Mean>
					<StdDev>
						«IF variance != null»«variance.print_Math_Expr»«ENDIF»
					</StdDev>
				</NormalDistribution>"
			«ENDIF»
		«ENDIF»
	'''		
	/////////////////////////////////
	//Modeling Steps
	/////////////////////////////////
	def print_msteps_ModellingSteps()
	'''
	<ModellingSteps>
		«FOR o: mcl.objects»
			«IF o.modelObject != null»«o.modelObject.print_ct_DataSet»«ENDIF»
		«ENDFOR»
		«print_msteps_EstimationStep»
		«print_msteps_SimulationStep»
		«print_msteps_StepDependencies»
		«print_msteps_ObjectiveDataSet»
	</ModellingSteps>
	'''
	
	//+ Print data set
	def print_ct_DataSet(ModelObject obj){
		var names = new ArrayList<String>();
		var types = new ArrayList<String>();
		for (b: obj.blocks){
			if (b.inputVariablesBlock != null){
				if (b.inputVariablesBlock.variables != null){
					for (s: b.inputVariablesBlock.variables){
						//var valueType = s.getVarType;
						names.add(s.identifier);
						//types.add(valueType);
						types.add(TYPE_INT);
					} 
				}
			}
		}
		var fileName = mcl.getDataSource;
		if (fileName.length > 0){
			fileName.print_ds_DataSet(names, types);
		}
	}
	
	//+ Read data from the source file
	// May need to skip first line (repeated column names) 
	// TODO: Do we need to check actual types against types deduced from MDL???
	def print_ds_DataSet(String fileName, ArrayList<String> names, ArrayList<String> types){
		var table = '''''';	
		var BufferedReader fileReader = null;
		var modelPath = mcl.eResource.getURI.toPlatformString(true);
		var file = new File(modelPath);
		var dataPath = file.getParent + "\\" + fileName;		
		try{
			//First try the path as it is			
			fileReader = new BufferedReader(new FileReader(dataPath));
		}		
		catch(FileNotFoundException e){
			//If not found, try to look in the folder "data"
			dataPath = file.getParent + "\\data\\" + fileName;
			try{
				fileReader = new BufferedReader(new FileReader(dataPath));
			}
			catch(FileNotFoundException e1){
				//If file is not ready to read, print a link (old PharmML format)
				val dotIndex = fileName.indexOf('.');
				var fileExtension = "";
				if (dotIndex > 0) fileExtension = fileName.substring(dotIndex + 1);		
				table =  
				'''				
					<Description>Source file not found («dataPath»)!</Description>
					<ExternalSource url="file=///«fileName»" format="«fileExtension»"/>	
				''';
			}
		}
		var definition = '''''';
		for (i: 0..names.size-1){
			definition = definition + 
				'''
					<ds:Column columnId="«names.get(i)»" valueType="«types.get(i)»" columnNum="«i»"/>
				''';
		}
		if (fileReader != null){
			if (fileReader.ready()){ 
				var line = "";
				while ((line = fileReader.readLine()) != null) {
		        	val atoms = line.split("\\s{1,}|,|;").iterator;
		        	var row = "";
		        	for (i : 0..atoms.size - 1){
						row = row + atoms.next.print_ct_Value;
		        	}
		        	table = table +  
		        	'''
		        	<Row>
		        		«row»
		        	</Row>
			        ''';
		        }
		    	fileReader.close();			
			}			
		}
		'''
		<DataSet>
			«IF definition.length > 0»
			<ds:Definition>
				«definition»
			</ds:Definition>
			«ENDIF»
			«IF table.length > 0»
			<ds:Table>	
				«table»
			</ds:Table>
			«ENDIF»
		</DataSet>	
		'''
	}
	
	//+
	def print_msteps_EstimationStep()'''
	<SimulationStep>		
		<Description>MDL source?</Description>
		«print_InitialValues»	
	</SimulationStep>
	'''
	
	//+
	def print_msteps_SimulationStep()'''
	<EstimationStep>
		<Description>MDL source?</Description>
		«print_InitialValues»
	</EstimationStep>
	'''
	
	def print_InitialValues() { }
	
	//+
	def print_msteps_StepDependencies()'''
	<StepDependencies>
		<Description>MDL source?</Description>
	</StepDependencies>
	'''
	
	//+
	def print_msteps_ObjectiveDataSet()'''
	<ObjectiveDataSet dataSetRef="">
	</ObjectiveDataSet>
	'''
	
	//- Print mapping for the input variables with use=idv (individual)
	def print_Math_Mapping(){
		for (MclObject obj: mcl.objects){
			if (obj.modelObject != null){
				for (ModelObjectBlock block: obj.modelObject.blocks){
					if (block.inputVariablesBlock != null){
						for (SymbolDeclaration s: block.inputVariablesBlock.variables){
							if (s.expression != null){
								if (s.expression.list != null){
									var use = s.expression.list.arguments.getAttribute("use");
									if (use.equalsIgnoreCase("idv")){
										val varName = s.identifier;
										return '''
										<Mapping columnName="«varName»">
											<Var xmlns="«xmlns_math»" symbId="t"/>
										</Mapping>
                						'''
                					}
								}
							}
						}
					}	
				}
			}
		}
	}	
	
	///////////////////////////
	//Mathematical expressions
	///////////////////////////
	
	//+ PharmML.ct_Annotation
	def print_XS_Comment(String text)'''
		<!--«text»-->
	'''
	
	//+ PharmML.ct_Annotation
	def print_ct_AnnotationType(String text)'''
	<Description>«text»</Description>
	'''

	//+ MDL.variable_declaration
	def print_ct_VariableDefinitionType(SymbolDeclaration v)'''
	<Variable symbId = "«v.identifier»"«IF v.expression.odeList != null» symbolType="derivative" independentVar="t"«ENDIF»>
		«v.identifier.print_ct_AnnotationType»
		«v.expression.print_Math_Expr»
	</Variable>
	'''
	
	//+
	def print_Math_Expr(AnyExpression e)'''
		«IF e.expression != null»
			«e.expression.print_Math_Equation»
		«ENDIF»
		«IF e.list != null»
			«e.print_list»
		«ENDIF»	
		«IF e.odeList != null»
			«e.print_odeList»
		«ENDIF»	
	'''

	//+
	def print_odeList(AnyExpression e) '''
		«var deriv = e.odeList.arguments.getAttributeExpression("deriv")»
		«IF deriv != null»«deriv.print_Math_Expr»«ENDIF»
	'''
	
	def print_Categorical(List categories)'''
		<Categorical>
		«FOR c: categories.arguments.arguments»
			<Category>
				«c.identifier»
			</Category>
		«ENDFOR»
		</Categorical>
	'''
		
	//+ Translate depending on list attributes
	def	print_list(AnyExpression e){
		if (e.list != null){
			val args  = e.list.arguments;
			val type = args.getAttribute("type");
			val define =  args.getAttributeExpression("define");
			if (type.equals("categorical") && (define.list != null)){
				define.list.print_Categorical;
			}
		}		
	} 
	
	//+
	def print_Math_FunctionCall(FunctionCall call)'''
		<math:FunctionCall>
			«call.identifier.print_ct_SymbolRef»
			«FOR arg: call.arguments.arguments»
				«arg.print_Math_FunctionArgument»
			«ENDFOR»
		</math:FunctionCall>
	'''
	
	//+
	def print_Math_FunctionArgument(Argument arg)'''
	<FunctionArgument «IF arg.identifier != null»symbId="«arg.identifier»"«ENDIF»>
		«arg.expression.print_Math_Expr»
	</FunctionArgument>
	'''
	
	
	//////////////////////////////////////////////////////////////////////
	//Helper functions
	//////////////////////////////////////////////////////////////////////
	
	//When typing is supported, rewrite this
	def getVarType(FullyQualifiedSymbolName ref){
		return TYPE_INT;
	}
	
	//When typing is supported, rewrite this
	def getVarType(SymbolDeclaration s){
		return TYPE_INT;
	}

	//+ Negation of the expression
	def dualExpression(OrExpression expr){
		var dualAnd = MdlFactory::eINSTANCE.createAndExpression;
		var iterator = expr.expression.iterator();
		while (iterator.hasNext){// x || y -> !x && !y 
			var andExpression = iterator.next;
			for (andAtom: andExpression.expression){
				var logicalExpr = MdlFactory::eINSTANCE.createLogicalExpression();
				if (andAtom.expression != null){
					var iterator1 = andAtom.expression.iterator();
					var operatorIterator1 = andAtom.operator.iterator();
					if (iterator1.hasNext)
						logicalExpr.expression.add(iterator1.next);
					while (iterator1.hasNext && operatorIterator1.hasNext){
						logicalExpr.operator.add(operatorIterator1.next.getDualOperator);
						logicalExpr.expression.add(iterator1.next);
					}
				} else {
					if (andAtom.boolean != null){
						logicalExpr.setBoolean(andAtom.boolean);
						if (andAtom.negation == null){
							logicalExpr.setNegation("!");
						}
					}
				}
				dualAnd.expression.add(logicalExpr);					
			}
		}
		var dualOr = MdlFactory::eINSTANCE.createOrExpression;
		dualOr.expression.add(dualAnd);
		return dualOr;
	}
	
	//+
	def getDualOperator(String operator){
		switch (operator){
			case "<": ">"
			case ">": "<"
			case "<=": ">="
			case ">=": "<="
			case "==": "!="
			case "!=": "=="
			default: operator
		}
	}
	
	//+
	override convertOperator(String operator){
		switch (operator){
			case "<": "lt"
			case ">": "gt"
			case "<=": "le"
			case ">=": "ge"
			case "==": "ne"
			case "!=": "eq"
			case "+": "plus"
			case "-": "minus"
			case "*": "times"
			case "/": "divide"
			case "^": "power"
			default: operator
		}
	}
	
	//+ Return true if an input variable with use=idv (individual) is defined
	def isIndependentVariableDefined(Mcl m){
		for (obj: m.objects){
			if (obj.modelObject != null){
				for (ModelObjectBlock block: obj.modelObject.blocks){
					if (block.inputVariablesBlock != null){
						for (SymbolDeclaration s: block.inputVariablesBlock.variables){
							if (s.expression != null){
								if (s.expression.list != null){
									var use = s.expression.list.arguments.getAttribute("use");
									if (use.equalsIgnoreCase("idv")){
										return true;
                					}
								}
							}
						}
					}	
				}
			}
		}
		return false;
	}
}