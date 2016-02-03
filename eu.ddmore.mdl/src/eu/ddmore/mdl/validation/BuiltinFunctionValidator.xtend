package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.FuncArguments
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.mdllib.mdllib.FunctionDefnBody
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdl.provider.BuiltinFunctionProvider.FunctDefn
import eu.ddmore.mdl.provider.BuiltinFunctionProvider.NamedArgFuncDefn
import eu.ddmore.mdl.provider.BuiltinFunctionProvider.SimpleFuncDefn
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.AbstractDeclarativeValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class BuiltinFunctionValidator extends AbstractDeclarativeValidator{

	override register(EValidatorRegistrar registrar){}

//	extension MdlUtils mu = new MdlUtils	
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider

	@Check
	def validateFunctionCall(SymbolReference sr){
		val ref = sr.ref
		if(ref instanceof FunctionDefnBody){
			if(sr.argList == null || sr.argList instanceof UnnamedFuncArguments){
				ref.checkUnnamedFunctionDefn(sr.argList,
					[fName| error("Simple function '" + fName + "' is not recognised.",
	//					MdlPackage.eINSTANCE.builtinFunctionCall_Func, MdlValidator::UNRECOGNIZED_FUNCTION_NAME, fName)],
						MdlPackage.eINSTANCE.symbolReference_Ref, MdlValidator::UNRECOGNIZED_FUNCTION_NAME, fName)],
						 [fName, eArgNum | error("Function '" + fName + "' has the wrong number of arguments. Expected " + eArgNum + ".",
						MdlPackage.eINSTANCE.symbolReference_ArgList, MdlValidator::INCORRECT_NUM_FUNC_ARGS, fName)]
						)
			}
			else{
				ref.checkNamedFunctionDefn(
					[fName| error("Named argument function '" + fName + "' is not recognised.",
	//					MdlPackage.eINSTANCE.builtinFunctionCall_Func, MdlValidator::UNRECOGNIZED_FUNCTION_NAME, fName)]
						MdlPackage.eINSTANCE.symbolReference_ArgList, MdlValidator::UNRECOGNIZED_FUNCTION_NAME, fName)]
						)
			}
		}
		else if(sr.argList != null){
			// this looks like a function but is not linked to a library function so this func call is invalid
			if(sr.argList instanceof UnnamedFuncArguments){
				error("Simple function '" + ref.name + "' is not recognised.",
						MdlPackage.eINSTANCE.symbolReference_Ref, MdlValidator::UNRECOGNIZED_FUNCTION_NAME, ref.name)
			}
			else{
				error("Named argument function '" + ref.name + "' is not recognised.",
						MdlPackage.eINSTANCE.symbolReference_ArgList, MdlValidator::UNRECOGNIZED_FUNCTION_NAME, ref.name)
			}
		}
	}

	@Check
	def validateFunctionArgument(ValuePair it){
		val parentFunc = EcoreUtil2.getContainerOfType(eContainer, SymbolReference)
		if(parentFunc != null){
			val ref = parentFunc.ref
			if(ref instanceof FunctionDefnBody){
				if(eContainer instanceof NamedFuncArguments){
					checkNamedArguments(
						[fName| error("Unrecognised argument '" + argumentName + "'.",
						MdlPackage.eINSTANCE.valuePair_ArgumentName, MdlValidator::UNRECOGNIZED_FUNCTION_ARGUMENT_NAME, fName)],
						[fName| error("Function argument '" + argumentName + "' occurs more than once.",
						MdlPackage.eINSTANCE.valuePair_ArgumentName, MdlValidator::MULTIPLE_IDENTICAL_FUNC_ARG, fName)]
					)
				}
			}
		}
	}

	@Check
	def validateNamedFunctionArguments(NamedFuncArguments it){
		val parentFunc = EcoreUtil2.getContainerOfType(eContainer, SymbolReference)
		if(parentFunc != null)
			missingMandatoryArgumentNames.forEach[arg, mand| error("mandatory argument '" + arg + "' is missing.",
						MdlPackage.eINSTANCE.namedFuncArguments_Arguments, MdlValidator::MANDATORY_NAMED_FUNC_ARG_MISSING, arg) ]
	}

	// The validator should check on a per argument basis is an argument name is valid.
	def checkNamedArguments(ValuePair it, (String) => void unkArgError, (String) => void duplicateArgError){
		val ref = functionCall.ref
		if(ref instanceof FunctionDefnBody){
			// assume that this is a named argument to a function
			val funcDefn = ref.funcDefn
			if(funcDefn != null && funcDefn instanceof NamedArgFuncDefn){
				val namedFuncDefn = funcDefn as NamedArgFuncDefn
				if(!namedFuncDefn.hasArgument(argumentName)){
					unkArgError.apply(argumentName)
				}
				else{
					var instanceCount = 0
					for(arg : (functionCall.argList as NamedFuncArguments).arguments)
						// count args of same name
						if(arg.argumentName == argumentName) instanceCount++
						
					if(instanceCount > 1) duplicateArgError.apply(argumentName) 
				}
				
			}
		}
	}

	// precondition - this is a unnamed function 	
	def checkUnnamedFunctionDefn(FunctionDefnBody ref, FuncArguments argList, (String) => void unkFuncErr, (String, int) => void incorrectNumArgsErr){
		val FunctDefn funcDefn = ref.funcDefn
		if(funcDefn == null || !(funcDefn instanceof SimpleFuncDefn)){
			unkFuncErr.apply(ref.name)
		}
		else {
			val funcArgList = argList
			switch(funcArgList){
				case funcArgList == null && funcDefn.numArgs != 0:
					incorrectNumArgsErr.apply(ref.name, funcDefn.numArgs)
				UnnamedFuncArguments case funcArgList.args.size != funcDefn.numArgs:
					incorrectNumArgsErr.apply(ref.name, funcDefn.numArgs)
				NamedFuncArguments: void
			}
		}
	}
	
	def checkNamedFunctionDefn(FunctionDefnBody ref, (String) => void unkFuncErr){
		val funcDefn = ref.funcDefn
		if(funcDefn == null || !(funcDefn instanceof NamedArgFuncDefn)){
			unkFuncErr.apply(ref.name)
		}
	}
	
}