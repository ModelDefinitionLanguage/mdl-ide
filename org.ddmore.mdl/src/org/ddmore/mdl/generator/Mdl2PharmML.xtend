package org.ddmore.mdl.generator

import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.BlockStatement
import org.ddmore.mdl.mdl.ConditionalExpression
import org.ddmore.mdl.mdl.EstimateTask
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName
import org.ddmore.mdl.mdl.FunctionCall
import org.ddmore.mdl.mdl.InputVariablesBlock
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.MclObject
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.ModelObjectBlock
import org.ddmore.mdl.mdl.OrExpression
import org.ddmore.mdl.mdl.SimulateTask
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.TaskFunctionBody
import org.ddmore.mdl.mdl.TaskObject
import org.ddmore.mdl.mdl.UnaryExpression
import org.ddmore.mdl.mdl.AndExpression
import org.ddmore.mdl.mdl.MdlFactory
import org.ddmore.mdl.mdl.LogicalExpression
import org.ddmore.mdl.mdl.AdditiveExpression
import org.ddmore.mdl.mdl.MultiplicativeExpression
import org.ddmore.mdl.mdl.PowerExpression
import org.ddmore.mdl.mdl.FullyQualifiedArgumentName
import org.ddmore.mdl.mdl.Vector


class Mdl2PharmML extends Mdl2Nonmem{
	
	val	xsi="http://www.w3.org/2001/XMLSchema-instance"; 
	val xsi_schemaLocation="http://www.pharmml.org/2013/03/PharmML http://www.pharmml.org/2013/03/PharmML";
	val xmlns_pharmML="http://www.pharmml.org/2013/03/PharmML";
	val xmlns_math="http://www.pharmml.org/2013/03/Maths";
	val xmlns_ct="http://www.pharmml.org/2013/03/CommonTypes";
	val xmlns_mdef="http://www.pharmml.org/2013/03/ModelDefinition";
	val xmlns_mstep="http://www.pharmml.org/2013/03/ModellingSteps";
	val xmlns_design="http://www.pharmml.org/2013/03/TrialDesign";
	val xmlns_uncert="http://www.pharmml.org/2013/03/Uncertainty";
	
	val writtenVersion = "0.1";
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
	</ModelDefinition>
	'''
	/////////////////////
	//Function Definition
	/////////////////////
	def print_mdef_FunctionDefinition() { }

	/////////////////////
	//Variability Model
	/////////////////////	
	
	//+ TODO: derive from MDL
	def print_mdef_VariabilityModel()'''
	<VariabilityModel blkId="model" type="model">
		<Level symbId="indiv">
			<ct:Name>Individual  Variability</ct:Name>
		</Level>
	</VariabilityModel>
	<VariabilityModel blkId="obsErr" type="error">
		<Level  symbId="residual">
			<ct:Name>Residual  Error</ct:Name>
		</Level>
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
				if (_index > 0) {
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
					«print_Math_Equation(operator, paramName)»
				</Parameter> 
			«ENDIF»
		«ENDIF»
	'''
	////////////////////
	//Structural Model
	////////////////////
	
	//+ STRUCTURAL_PARAMETER -> <StructuralModel>
	def print_mdef_StructuralModel(){
		var structuralModel = "";
		var i = 1;
		var blockName = "p" + i;
		for(s: theta_vars.keySet) {
			val paramName = s as String;
			if (paramName != null){
				var varName = paramName;
				var _index = paramName.indexOf('_');
				if (_index > 0) {
					varName = paramName.substring(_index + 1);
					val idv = varName.findIndividualVariable;
					if (idv != null) structuralModel = structuralModel + varName.print_mdef_StructuralParameter(blockName);
				}			
			}
			i = i + 1;
		}
		'''
		«structuralModel.print_mdef_StructuralModel»
		'''
	}
	
	//ODE -> <StructuralModel>
	def print_mdef_StructuralModel(String parameters){
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
		«ENDIF»
		'''
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
		<Parameter symbId="pop_«s.identifier»"/>
		<Continuous>
			«IF s.expression != null»
				«IF s.expression.expression != null»
					«s.expression.expression.print_Math_RandomEffects»
				«ENDIF»
			«ENDIF»
		</Continuous>	
		'''
	
		//+ New version of PharmML - changed
		def print_Math_RandomEffects(Expression expr)'''
			«val randomVars = expr.findRandomVariable»
			«IF randomVars.size > 0»
				<RandomEffects>
					«FOR s: randomVars.keySet»
						«val paramName = s as String»
						«paramName.print_Math_RandomEffect»
					«ENDFOR»
				</RandomEffects>
			«ENDIF»
		'''
		
		def print_Math_RandomEffect(String paramName)'''
			<ct:SymbRef symbIdRef="«paramName»"/>
			«paramName.print_uncert_Distribution»
		'''
	
	/////////////////////
	//Error Model
	/////////////////////	
	def print_mdef_ErrorModel(String varName)'''
    <ErrorModel>
    	<ct:Assign>
            <Equation xmlns="«xmlns_math»">
                <FunctionCall>
                    <ct:SymbRef symbIdRef="constantErrorModel"/>
                    <FunctionArgument symbId="«varName»">
                        <ct:SymbRef symbIdRef="«varName»"/>
                    </FunctionArgument>
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
	def findIndividualVariable(String varName){
		//find paramName in INDIVIDUAL_VARIABLES
		for (MclObject o: mcl.objects){
			if (o.modelObject != null){
				for (ModelObjectBlock b: o.modelObject.blocks){
					if(b.individualVariablesBlock != null){
						for (BlockStatement st: b.individualVariablesBlock.statements){
							if (st.symbol != null){
								var name = st.symbol.identifier;
								if (name.equalsIgnoreCase(varName)){
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
	def defineDistribution(String name){
		//find paramName in RANDOM_VARIABLES_DEFINITION
		for (MclObject o: mcl.objects){
			if (o.modelObject != null){
				for (ModelObjectBlock b: o.modelObject.blocks){
					if(b.randomVariableDefinitionBlock != null){
						for (SymbolDeclaration s: b.randomVariableDefinitionBlock.variables){
							var variance = s.randomList.arguments.getAttribute("variance");
							if (variance.equalsIgnoreCase(name)){
								return s.randomList.arguments;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	//find a random variable in the expression	
	def findRandomVariable(Expression expr) { 
		var classifiedVars = expr.classifyReferences;
		var randomVars = classifiedVars.filter[k, v | v.equals("eps")];
		return randomVars;
	}
	
	def classifyReferences(Expression expr){
		var classifiedVars = newHashMap;
		var iterator = expr.eAllContents();
	    while (iterator.hasNext()){
	    	var obj = iterator.next();
	    	if (obj instanceof FullyQualifiedSymbolName){
	    		var ref = obj as FullyQualifiedSymbolName;
	    		var varName = ref.identifier;
	    		if (classifiedVars.get(varName) == null){
		    		if (theta_vars.get(varName) != null)
			    		classifiedVars.put(varName, "theta");
			    	if (eps_vars.get(varName) != null)
			    		classifiedVars.put(varName, "eps");
			    	if (eta_vars.get(varName) != null)
			    		classifiedVars.put(varName, "eta");	
	    		}
	    	}
	    }
	    return classifiedVars;
	}
	
	/////////////////////////////////////
	//Print expression
	/////////////////////////////////////

	//+
	def print_mdef_StructuralParameter(String varName, String blockName)'''
		<Parameter symbId = "«varName»">;
			<Var block="«blockName»" xmlns="«xmlns_math»" symbId="«varName»"/>
		</Parameter>
	'''
	
	//+
	def print_Math_Equation(String operator, String paramName)'''
		<Equation xmlns="«xmlns_math»" writtenVersion="«writtenVersion»">;
			«operator.print_Math_UniOp(paramName)» 
		</Equation>
	'''
	
	//+
	def print_Math_UniOp(String operator, String param)'''
		<Uniop op="«operator»">
			<Var symbId="«param»"/>
		</Uniop>
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
						«expr.primary.number.print_ct_Number»
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
	def print_ct_Number(String number){
		if ((number.indexOf('.') > 0) || (number.indexOf(",") > 0)){
			//real
			return '''<ct:Real>«number»</ct:Real>'''
		} else {//int
			return '''<ct:Int>«number»</ct:Int>'''
		}
	}
	
	//+ TODO: modify to print correctly any distribution
	def print_uncert_Distribution(String paramName)'''
		«var args = paramName.defineDistribution»
		«IF args != null»
			«var distrType = args.getAttribute("type")»
			«var mean = args.getAttributeExpression("mean")»
			«var variance = args.getAttributeExpression("variance")»
			«IF distrType.length > 0»
				<Distribution xmlns="«xmlns_uncert»" writtenVersion = "«writtenVersion»>";
					<«distrType»>
						<Mean>
							«IF mean != null»«mean.print_Math_Expr»«ENDIF»
						</Mean>
						<StdDev>
							«IF variance != null»«variance.print_Math_Expr»«ENDIF»
						</StrDev>
					</«distrType»>
				</Distribution>"
			«ENDIF»
		«ENDIF»
	'''		

	//////////////////////////////////////////////////////////
	//Common PharmML types
	//////////////////////////////////////////////////////////
	
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
		«IF e.type != null»
			«e.print_type»
		«ENDIF»
	'''

	//+
	def print_odeList(AnyExpression e) '''
		«var deriv = e.odeList.arguments.getAttributeExpression("deriv")»
		«IF deriv != null»«deriv.print_Math_Expr»«ENDIF»
	'''
	
	//+
	def print_type(AnyExpression e) ''''''
	
	//+ Translate depending on list attributes
	def	print_list(AnyExpression e) ''''''
	
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
	
		
	//- Print mapping for the input variables with use=idv (individual)
	def print_Math_Mapping(Mcl m){
		for (MclObject obj: m.objects){
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
	
		
	//-
	def print_msteps_ModelingSteps(MclObject o)'''
	<ModellingSteps>
		«IF o.modelObject != null»«o.modelObject.print_msteps_Variable»«ENDIF»
		«IF o.taskObject != null»«o.taskObject.print_msteps_ModelingStepsContent»«ENDIF»
	</ModellingSteps>
	'''
	
	//-
	def print_msteps_Variable(ModelObject obj)'''
	<Variable>
		«obj.print_ct_DataSet»
	</Variable>
	'''

	//-
	def print_ct_DataSet(ModelObject obj)'''
	<DataSet>
		«FOR b:obj.blocks»
			«IF b.inputVariablesBlock != null»
				«b.inputVariablesBlock.print_ct_Definition» 
			«ENDIF»
		«ENDFOR»
	</DataSet>	
	'''
	
	//-
	def print_ct_Definition(InputVariablesBlock block){
		'''
		<Definition></Definition>	
		'''
	}

	//-
	def print_msteps_ModelingStepsContent(TaskObject obj)'''
	«FOR b: obj.blocks»
		«IF b.functionDeclaration != null»
			«b.functionDeclaration.functionBody.print_msteps_ModelingStepsContent»
		«ENDIF»
	«ENDFOR»
	'''
	
	//-
	def print_msteps_ModelingStepsContent(TaskFunctionBody body)'''
		«FOR b: body.blocks»
			«IF b.estimateBlock != null»
				«b.estimateBlock.print_msteps_SimulationStep»
			«ENDIF»
			«IF b.simulateBlock != null»
				«b.simulateBlock.print_msteps_EstimationStep»
			«ENDIF»
		«ENDFOR»
	'''
	
	//-
	def print_msteps_EstimationStep(SimulateTask task)'''
	<SimulationStep>
	</SimulationStep>
	'''
	
	//-
	def print_msteps_SimulationStep(EstimateTask task)'''
	<EstimationStep>
	</EstimationStep>
	'''
	
	//-
	def print_msteps_ObjectiveDataSet(MclObject o, String content)'''
	<ObjectiveDataSet dataSetRef="">
		«content»
	</ObjectiveDataSet>
	'''
	
	
	//////////////////////////////////////////////////////////////////////
	//Helper functions
	//////////////////////////////////////////////////////////////////////

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