package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.type.MclTypeProvider
import eu.ddmore.mdl.type.MclTypeProvider.TypeInfo
import java.util.Map
import java.util.HashSet
import java.util.Set

import org.eclipse.xtend.lib.annotations.Data

class BuiltinFunctionProvider {
	
	interface FunctDefn{
		def int getNumArgs()
		def TypeInfo getReturnType()
	} 	

	static class SimpleFuncDefn implements FunctDefn{
		int numArgs
		TypeInfo returnType	 

		override int getNumArgs(){
			numArgs
		}
		
		override TypeInfo getReturnType(){
			returnType
		}
	}

	@Data
	static class FunctionArgument{
		TypeInfo expectedType
		boolean mandatory
	}

	static class NamedArgFuncDefn implements FunctDefn{
		TypeInfo returnType
		Map<String, FunctionArgument> arguments	 
		
		override int getNumArgs(){
			arguments.size
		}
		
		override TypeInfo getReturnType(){
			returnType
		}
	}
	
	static val functDefns = #{
		'log' -> ( new SimpleFuncDefn => [ numArgs = 2 returnType = MclTypeProvider::REAL_TYPE ] ),
		'ln' -> ( new SimpleFuncDefn => [ numArgs = 1 returnType = MclTypeProvider::REAL_TYPE ] ),
		'abs' -> ( new SimpleFuncDefn => [ numArgs = 1 returnType = MclTypeProvider::REAL_TYPE ] ),
		'exp' -> ( new SimpleFuncDefn => [ numArgs = 1 returnType = MclTypeProvider::REAL_TYPE ] ),
		'Normal' -> ( new NamedArgFuncDefn => [ returnType = MclTypeProvider::PDF_TYPE arguments = #{
						'mean' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'sd' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} ] ),
		'linear' -> ( new NamedArgFuncDefn => [ returnType = MclTypeProvider::REAL_TYPE arguments = #{
						'pop' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'fixEff' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, false),
						'ranEff' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} ] ),
		'general' -> ( new NamedArgFuncDefn => [ returnType = MclTypeProvider::REAL_TYPE arguments = #{
						'grp' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'ranEff' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} ] ),
		'combinedError1' -> ( new NamedArgFuncDefn => [ returnType = MclTypeProvider::REAL_TYPE arguments = #{
						'additive' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'proportional' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'prediction' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'eps' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, false)
					} ] ),
		'combinedError2' -> ( new NamedArgFuncDefn => [ returnType = MclTypeProvider::REAL_TYPE arguments = #{
						'additive' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'proportional' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'prediction' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'eps' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, false)
					} ] )
	}

	
	def getFunctionType(String name){
		functDefns.get(name)?.returnType ?: MclTypeProvider::UNDEFINED_TYPE
	}
	
	// precondition - this is a unnamed function 	
	def checkUnnamedFunctionDefn(BuiltinFunctionCall it, (String) => void unkFuncErr, (String, int) => void incorrectNumArgsErr){
		val funcDefn = functDefns.get(func)
		if(funcDefn == null || !(funcDefn instanceof SimpleFuncDefn)){
			unkFuncErr.apply(func)
		}
		else {
			val funcArgList = argList
			switch(funcArgList){
				case funcArgList == null && funcDefn.numArgs != 0:
					incorrectNumArgsErr.apply(func, funcDefn.numArgs)
				UnnamedFuncArguments case funcArgList.args.size != funcDefn.numArgs:
					incorrectNumArgsErr.apply(func, funcDefn.numArgs)
				NamedFuncArguments: void
			}
		}
	}
	
	def checkNamedFunctionDefn(BuiltinFunctionCall it, (String) => void unkFuncErr){
		val funcDefn = functDefns.get(func)
		if(funcDefn == null || !(funcDefn instanceof NamedArgFuncDefn)){
			unkFuncErr.apply(func)
		}
	}
	
	// returns the set of mandatory arguments not set in a function or an
	// empty set if the function name is not recognised.
	def getMissingMandatoryArgumentNames(NamedFuncArguments it){
		val Set<String> mandatoryArgs = new HashSet<String>
		val funcDefn = functDefns.get(functionCall.func)
		if(funcDefn != null && funcDefn instanceof NamedArgFuncDefn){
			// store all the mandatory argument names
			(funcDefn as NamedArgFuncDefn).arguments.forEach[arg, fa| if(fa.isMandatory) mandatoryArgs.add(arg) ]
			// remove those that are used
			arguments.forEach[vp| mandatoryArgs.remove(vp.argumentName)]
		}
		mandatoryArgs
	}
	
	
	private def getFunctionCall(NamedFuncArguments it){
		eContainer as BuiltinFunctionCall
	}
	
	private def getFunctionCall(ValuePair it){
		eContainer.eContainer as BuiltinFunctionCall
	}
	
	// The validator should check on a per argument basis is an argument name is valid.
	def checkNamedArguments(ValuePair it, (String) => void unkArgError, (String) => void duplicateArgError){
		// assume that this is a named argument to a function
		val funcDefn = functDefns.get(functionCall.func)
		if(funcDefn != null && funcDefn instanceof NamedArgFuncDefn){
			val namedFuncDefn = funcDefn as NamedArgFuncDefn
			if(!namedFuncDefn.arguments.containsKey(argumentName)){
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