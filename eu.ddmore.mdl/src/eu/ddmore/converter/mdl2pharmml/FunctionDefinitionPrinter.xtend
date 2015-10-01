package eu.ddmore.converter.mdl2pharmml

import java.util.ArrayList
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.utils.MclUtils
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.validation.BuiltinFunctionProvider
import eu.ddmore.mdl.validation.ListDefinitionProvider

class FunctionDefinitionPrinter {

	extension MclUtils mclUtil = new MclUtils
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
	extension PharmMLConverterUtils pcu = new PharmMLConverterUtils
	extension ListDefinitionProvider ldp = new ListDefinitionProvider

	static val functionDefinitions = #{
		'additiveError' -> '''
    <ct:FunctionDefinition symbolType="real" symbId="additiveError">
        <ct:Description>Constant or additive error model</ct:Description>
        <ct:FunctionArgument symbolType="real" symbId="additive"/>
        <ct:Definition>
            <Equation xmlns="http://www.pharmml.org/2013/03/Maths">
               <ct:SymbRef symbIdRef="additive"/>
            </Equation>
        </ct:Definition>
    </ct:FunctionDefinition>
		''',
		'proportionalError' -> '''
    <ct:FunctionDefinition symbolType="real" symbId="proportionalError">
        <ct:Description>Proportional or constant CV (CVV)</ct:Description>
        <ct:FunctionArgument symbolType="real" symbId="proportional"/>
        <ct:FunctionArgument symbolType="real" symbId="f"/>
        <ct:Definition>
            <Equation xmlns="http://www.pharmml.org/2013/03/Maths">
                <Binop op="times">
                    <ct:SymbRef symbIdRef="proportional"/>
                    <ct:SymbRef symbIdRef="f"/>
                </Binop>
            </Equation>
        </ct:Definition>
    </ct:FunctionDefinition>
		''',
		'combinedError1' -> '''
    <ct:FunctionDefinition symbolType="real" symbId="combinedError1">
        <ct:Description>Combined additive ad proportional for 1 epsilon</ct:Description>
        <ct:FunctionArgument symbolType="real" symbId="additive"/>
        <ct:FunctionArgument symbolType="real" symbId="proportional"/>
        <ct:FunctionArgument symbolType="real" symbId="f"/>
        <ct:Definition>
            <Equation xmlns="http://www.pharmml.org/2013/03/Maths">
                <Binop op="plus">
                    <ct:SymbRef symbIdRef="additive"/>
                    <Binop op="times">
                        <ct:SymbRef symbIdRef="proportional"/>
                        <ct:SymbRef symbIdRef="f"/>
                    </Binop>
                </Binop>
            </Equation>
        </ct:Definition>
    </ct:FunctionDefinition>
		''',
		'combinedError2' -> '''
    <!-- SQRT(PROP**2+ADD**2*F**2) -->
    <ct:FunctionDefinition symbolType="real" symbId="combinedError2">
        <ct:Description>Combined additive ad proportional for 1 epsilon</ct:Description>
        <ct:FunctionArgument symbolType="real" symbId="additive"/>
        <ct:FunctionArgument symbolType="real" symbId="proportional"/>
        <ct:FunctionArgument symbolType="real" symbId="f"/>
        <ct:Definition>
            <Equation xmlns="http://www.pharmml.org/2013/03/Maths">
                <Uniop op="sqrt">
                    <Binop op="plus">
                        <Binop op="power">
                            <ct:SymbRef symbIdRef="proportional"/>
                            <ct:Int>2</ct:Int>
                        </Binop>
                        <Binop op="times">
                            <Binop op="power">
                                <ct:SymbRef symbIdRef="additive"/>
                                <ct:Int>2</ct:Int>
                            </Binop>
                            <Binop op="power">
                                <ct:SymbRef symbIdRef="f"/>
                                <ct:Int>2</ct:Int>
                            </Binop>
                        </Binop>
                    </Binop>
                </Uniop>
            </Equation>
        </ct:Definition>
    </ct:FunctionDefinition>
		''',
		'combinedError2Log' -> '''
    <!-- SQRT(PROP**2 + (ADD/F)**2) -->
    <ct:FunctionDefinition symbolType="real" symbId="combinedError2Log">
        <ct:Description>Combined additive ad proportional for 1 epsilon where prediction is log transformed</ct:Description>
        <ct:FunctionArgument symbolType="real" symbId="additive"/>
        <ct:FunctionArgument symbolType="real" symbId="proportional"/>
        <ct:FunctionArgument symbolType="real" symbId="f"/>
        <ct:Definition>
            <Equation xmlns="http://www.pharmml.org/2013/03/Maths">
                <Uniop op="sqrt">
                    <Binop op="plus">
                        <Binop op="times">
                            <ct:SymbRef symbIdRef="proportional"/>
                            <ct:SymbRef symbIdRef="proportional"/>
                        </Binop>
                        <Binop op="power">
                            <Binop op="divide">
                                <ct:SymbRef symbIdRef="additive"/>
                                <ct:SymbRef symbIdRef="f"/>
                            </Binop>
                            <ct:Int>2</ct:Int>
                        </Binop>
                    </Binop>
                </Uniop>
            </Equation>
        </ct:Definition>
    </ct:FunctionDefinition>
		''',
		'powerError' -> '''
    <ct:FunctionDefinition symbolType="real" symbId="powerError">
        <ct:Description>Power error model</ct:Description>
        <ct:FunctionArgument symbolType="real" symbId="proportional"/>
        <ct:FunctionArgument symbolType="real" symbId="power"/>
        <ct:FunctionArgument symbolType="real" symbId="f"/>
        <ct:Definition>
            <Equation xmlns="http://www.pharmml.org/2013/03/Maths">
                <Binop op="times">
                    <ct:SymbRef symbIdRef="proportional"/>
                    <Binop op="power">
                        <ct:SymbRef symbIdRef="f"/>
                        <ct:SymbRef symbIdRef="power"/>
                    </Binop>
                </Binop>
            </Equation>
        </ct:Definition>
    </ct:FunctionDefinition>
		''',
		'combinedPowerError1' -> '''
    <ct:FunctionDefinition symbolType="real" symbId="combinedPowerError1">
        <ct:Description>Combined additive and power error model for 1 epsilon.</ct:Description>
        <ct:FunctionArgument symbolType="real" symbId="additive"/>
        <ct:FunctionArgument symbolType="real" symbId="proportional"/>
        <ct:FunctionArgument symbolType="real" symbId="power"/>
        <ct:FunctionArgument symbolType="real" symbId="f"/>
        <ct:Definition>
            <Equation xmlns="http://www.pharmml.org/2013/03/Maths">
                <Binop op="plus">
                    <ct:SymbRef symbIdRef="additive"/>
                    <Binop op="times">
                        <ct:SymbRef symbIdRef="proportional"/>
                        <Binop op="power">
                            <ct:SymbRef symbIdRef="f"/>
                            <ct:SymbRef symbIdRef="power"/>
                        </Binop>
                    </Binop>
                </Binop>
            </Equation>
        </ct:Definition>
    </ct:FunctionDefinition>
		'''
	}

	static val ANY_TRANS = #{'ln', 'logit', 'probit', 'none'}
	static val LN_TRANS = #{'ln'}
	static val NOT_LN_TRANS = #{'logit', 'probit', 'none'}

	static val standardErrorFunctionMappingTable = #{
		'additiveError' -> #{ANY_TRANS -> 'additiveError'},
		'proportionalError' -> #{ANY_TRANS -> 'proportionalError'},
		'combinedError1' -> #{ANY_TRANS -> 'combinedError1'},
		'combinedError2' -> #{NOT_LN_TRANS -> 'combinedError2',
								LN_TRANS -> 'combinedError2Log'
							},
		'powerError' -> #{ANY_TRANS -> 'powerError'},
		'combinedPowerError1' -> #{ANY_TRANS -> 'combinedPowerError1'}
	}



	def getPharmMLFuncDefn(BuiltinFunctionCall it){
		var retVal = ''
		val defn = standardErrorFunctionMappingTable.get(func)
		if(defn != null){
			// is a standard error function
			val transFunc = argList.getAttributeEnumValue('trans')
			for(defnTrans : defn.keySet){
				if(defnTrans.contains(transFunc)){
					return functionDefinitions.get(defn.get(defnTrans))
				}
			}
		}
		retVal
	}


	def print_FunctionDefinitions(MclObject it){
		var res  = "";
		var printedFunctions = new ArrayList<String>();
		for (o: mdlObservations){
			switch(o){
				EquationDefinition:{
					val rhsExpr = o.expression
					switch(rhsExpr){
						BuiltinFunctionCall:
							if(rhsExpr.isNamedArgFunction){
								val funcDefn = rhsExpr.pharmMLFuncDefn
								res = res + funcDefn;
								printedFunctions.add(o.name);
							}
					}
				}
			}
		}
		'''«res»'''
	}
	
//	def print_ct_FunctionDefinition(BuiltinFunctionCall it)'''
//			<ct:FunctionDefinition xmlns="«Constants::xmlns_ct»" 
//				symbId="«func»" 
//				symbolType="«getFunctionType.pharmMLType»">
//				«FOR p: BuiltinFunctionProvider::standardFunctions.get(functName).defaultParamSet»
//					<FunctionArgument symbolType="«p.type.convertType»" symbId="«p.name»"/>
//				«ENDFOR»
//				«functName.print_FunctionDefinition»
//			</ct:FunctionDefinition>
//		'''	
//	
//	//combined2: sqrt(a^2  +  b^2*F^2)
//	//combined2log or combined3: sqrt(b^2 + (a/f)^2)
//	def print_FunctionDefinition(String functName)'''
//		<Definition>
//			<Equation xmlns="«Constants::xmlns_math»">
//				«IF (functName.equals(BuiltinFunctionProvider::funct_error_additive))»
//					<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_additive.name»"/>
//				«ENDIF»
//				«IF (functName.equals(BuiltinFunctionProvider::funct_error_prop))»
//					<Binop op="times">
//						<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_proportional.name»"/>
//						<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_f.name»"/>
//					</Binop>
//				«ENDIF»
//				«IF (functName.equals(BuiltinFunctionProvider::funct_error_combined1))»
//					<math:Binop op="plus">
//						<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_additive.name»"/>
//						<math:Binop op="times">
//							<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_proportional.name»"/>
//							<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_f.name»"/>
//						</math:Binop>
//					</math:Binop>
//				«ENDIF»
//				«IF (functName.equals(BuiltinFunctionProvider::funct_error_combined2))»
//					<math:Uniop op="sqrt">
//						<math:Binop op="plus">
//							<math:Binop op="times">
//								<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_additive.name»"/>
//								<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_additive.name»"/>
//							</math:Binop>
//							<math:Binop op="times">
//								<math:Binop op="times">
//									<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_proportional.name»"/>
//									<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_proportional.name»"/>
//								</math:Binop>
//								<math:Binop op="times">
//									<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_f.name»"/>
//									<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_f.name»"/>
//								</math:Binop>
//							</math:Binop>
//						</math:Binop>
//					</math:Uniop>
//				«ENDIF»
//				«IF (functName.equals(BuiltinFunctionProvider::funct_error_combined2log) || functName.equals(BuiltinFunctionProvider::funct_error_combined3))»
//					<math:Uniop op="sqrt">
//						<math:Binop op="plus">
//							<math:Binop op="times">
//								<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_proportional.name»"/>
//								<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_proportional.name»"/>
//							</math:Binop>
//							<math:Binop op="divide">
//								<math:Binop op="times">
//									<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_additive.name»"/>
//									<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_additive.name»"/>
//								</math:Binop>
//								<math:Binop op="times">
//									<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_f.name»"/>
//									<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_f.name»"/>
//								</math:Binop>
//							</math:Binop>
//						</math:Binop>
//					</math:Uniop>
//				«ENDIF»
//				«IF (functName.equals(BuiltinFunctionProvider::funct_error_power))»
//					<Binop op="times">
//						<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_proportional.name»"/>
//						<Binop op="power">
//							<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_f.name»"/>
//							<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_power.name»"/>
//						</Binop>
//					</Binop>
//				«ENDIF»
//				«IF (functName.equals(BuiltinFunctionProvider::funct_error_combinedPower1))»
//					<Binop op="plus">
//						<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_additive.name»"/>
//						<Binop op="times">
//							<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_proportional.name»"/>
//							<Binop op="power">
//								<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_f.name»"/>
//								<ct:SymbRef symbIdRef="«BuiltinFunctionProvider::param_error_power.name»"/>
//							</Binop>
//						</Binop>
//					</Binop>
//				«ENDIF»
//			</Equation>
//		</Definition>
//	'''
//	
//	def convertType(MdlDataType p){
//		if (p == MdlDataType::TYPE_INT)  return Constants::TYPE_INT;
//		if (p == MdlDataType::TYPE_REAL) return Constants::TYPE_REAL;
//		return Constants::TYPE_REAL
//	}
	
}