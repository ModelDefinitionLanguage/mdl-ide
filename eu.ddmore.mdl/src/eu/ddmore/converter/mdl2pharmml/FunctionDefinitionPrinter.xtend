package eu.ddmore.converter.mdl2pharmml

import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdl.utils.MdlUtils
import java.util.HashSet

class FunctionDefinitionPrinter {

	extension MdlUtils mclUtil = new MdlUtils
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
//	extension PharmMLConverterUtils pcu = new PharmMLConverterUtils
//	extension ListDefinitionProvider ldp = new ListDefinitionProvider

	static val functionDefinitions = #{
		'additiveError' -> '''
    <ct:FunctionDefinition xmlns="http://www.pharmml.org/pharmml/0.6/CommonTypes"
    	symbolType="real"
    	symbId="additiveError">
        <ct:Description>Constant or additive error model</ct:Description>
        <ct:FunctionArgument symbolType="real" symbId="additive"/>
        <ct:Definition>
            <Equation xmlns="http://www.pharmml.org/pharmml/0.6/Maths">
               <ct:SymbRef symbIdRef="additive"/>
            </Equation>
        </ct:Definition>
    </ct:FunctionDefinition>
		''',
		'proportionalError' -> '''
    <ct:FunctionDefinition xmlns="http://www.pharmml.org/pharmml/0.6/CommonTypes" 
    	symbolType="real"
    	symbId="proportionalError">
        <ct:Description>Proportional or constant CV (CVV)</ct:Description>
        <ct:FunctionArgument symbolType="real" symbId="proportional"/>
        <ct:FunctionArgument symbolType="real" symbId="f"/>
        <ct:Definition>
            <Equation xmlns="http://www.pharmml.org/pharmml/0.6/Maths">
                <Binop op="times">
                    <ct:SymbRef symbIdRef="proportional"/>
                    <ct:SymbRef symbIdRef="f"/>
                </Binop>
            </Equation>
        </ct:Definition>
    </ct:FunctionDefinition>
		''',
		'combinedError1' -> '''
    <ct:FunctionDefinition xmlns="http://www.pharmml.org/pharmml/0.6/CommonTypes"
    	symbId="combinedError1"
    	symbolType="real">
        <Description>Combined additive ad proportional for 1 epsilon</Description>
        <FunctionArgument symbolType="real" symbId="additive"/>
        <FunctionArgument symbolType="real" symbId="proportional"/>
        <FunctionArgument symbolType="real" symbId="f"/>
        <Definition>
            <Equation xmlns="http://www.pharmml.org/pharmml/0.6/Maths">
                <Binop op="plus">
                    <ct:SymbRef symbIdRef="additive"/>
                    <Binop op="times">
                        <ct:SymbRef symbIdRef="proportional"/>
                        <ct:SymbRef symbIdRef="f"/>
                    </Binop>
                </Binop>
            </Equation>
        </Definition>
    </ct:FunctionDefinition>
		''',
		'combinedError2' -> '''
    <!-- SQRT(PROP**2+ADD**2*F**2) -->
    <ct:FunctionDefinition xmlns="http://www.pharmml.org/pharmml/0.6/CommonTypes" 
    	symbolType="real"
    	symbId="combinedError2">
        <ct:Description>Combined additive ad proportional for 1 epsilon</ct:Description>
        <ct:FunctionArgument symbolType="real" symbId="additive"/>
        <ct:FunctionArgument symbolType="real" symbId="proportional"/>
        <ct:FunctionArgument symbolType="real" symbId="f"/>
        <ct:Definition>
            <Equation xmlns="http://www.pharmml.org/pharmml/0.6/Maths">
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
    <ct:FunctionDefinition xmlns="http://www.pharmml.org/pharmml/0.6/CommonTypes" 
    	symbolType="real"
    	symbId="combinedError2Log">
        <ct:Description>Combined additive ad proportional for 1 epsilon where prediction is log transformed</ct:Description>
        <ct:FunctionArgument symbolType="real" symbId="additive"/>
        <ct:FunctionArgument symbolType="real" symbId="proportional"/>
        <ct:FunctionArgument symbolType="real" symbId="f"/>
        <ct:Definition>
            <Equation xmlns="http://www.pharmml.org/pharmml/0.6/Maths">
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
    <ct:FunctionDefinition xmlns="http://www.pharmml.org/pharmml/0.6/CommonTypes"
    	symbolType="real"
    	symbId="powerError">
        <ct:Description>Power error model</ct:Description>
        <ct:FunctionArgument symbolType="real" symbId="proportional"/>
        <ct:FunctionArgument symbolType="real" symbId="power"/>
        <ct:FunctionArgument symbolType="real" symbId="f"/>
        <ct:Definition>
            <Equation xmlns="http://www.pharmml.org/pharmml/0.6/Maths">
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
    <ct:FunctionDefinition xmlns="http://www.pharmml.org/pharmml/0.6/CommonTypes"
    	symbId="combinedPowerError1"
    	symbolType="real">
        <ct:Description>Combined additive and power error model for 1 epsilon.</ct:Description>
        <ct:FunctionArgument symbolType="real" symbId="additive"/>
        <ct:FunctionArgument symbolType="real" symbId="proportional"/>
        <ct:FunctionArgument symbolType="real" symbId="power"/>
        <ct:FunctionArgument symbolType="real" symbId="f"/>
        <ct:Definition>
            <Equation xmlns="http://www.pharmml.org/pharmml/0.6/Maths">
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


	static val standardArgumentLookup = #{
		'additiveError' -> #{'additive' -> 'additive'},
		'proportionalError' -> #{'proportional' -> 'proportional', 'prediction' -> 'f'},
		'combinedError1' -> #{'additive' -> 'additive', 'proportional' -> 'proportional', 'prediction' -> 'f'},
		'combinedError2' -> #{'additive' -> 'additive', 'proportional' -> 'proportional', 'prediction' -> 'f'},
		'combinedError2Log' -> #{'additive' -> 'additive', 'proportional' -> 'proportional', 'prediction' -> 'f'},
		'powerError' -> #{'proportional' -> 'proportional', 'power' -> 'power', 'prediction' -> 'f'},
		'combinedPowerError1' -> #{'additive' -> 'additive', 'proportional' -> 'proportional', 'power' -> 'power', 'prediction' -> 'f'}
	}
	


//	def getPharmMLFuncDefn(BuiltinFunctionCall it){
//		var retVal = ''
//		val defn = standardErrorFunctionMappingTable.get(func)
//		if(defn != null){
//			// is a standard error function
//			val transFunc = argList.getArgumentEnumValue('trans') ?: 'none'
//			for(defnTrans : defn.keySet){
//				if(defnTrans.contains(transFunc)){
//					return functionDefinitions.get(defn.get(defnTrans))
//				}
//			}
//		}
//		retVal
//	}

	def getPharmMLFuncDefn(BuiltinFunctionCall it){
		functionDefinitions.get(standardErrorName)
	}

	def String getStandardErrorName(BuiltinFunctionCall it){
		val defn = standardErrorFunctionMappingTable.get(func)
		if(defn != null){
			// is a standard error function
			val transFunc = argList.getArgumentEnumValue('trans') ?: 'none'
			for(defnTrans : defn.keySet){
				if(defnTrans.contains(transFunc)){
					return defn.get(defnTrans)
				}
			}
		}
		return null
	}
	
//	def isStandardErrorArgument(BuiltinFunctionCall it, String name){
//		standardArgumentLookup.get(standardErrorName)?.containsKey(name) 
//	}

	def getStandardErrorArgument(BuiltinFunctionCall it, String name){
		standardArgumentLookup.get(standardErrorName)?.get(name) 
	}

	def print_FunctionDefinitions(MclObject it){
		val printed = new HashSet<String>
		'''
			«FOR o: mdlObservations»
				«switch(o){
					EquationTypeDefinition:{
						val rhsExpr = o.expression
						switch(rhsExpr){
							BuiltinFunctionCall:
								if(rhsExpr.isNamedArgFunction && !printed.contains(rhsExpr.func)){
									printed.add(rhsExpr.func)	
									'''
										«rhsExpr.pharmMLFuncDefn»
									'''
								}
						}
					}
				}»
			«ENDFOR»
		'''
	}
	
}