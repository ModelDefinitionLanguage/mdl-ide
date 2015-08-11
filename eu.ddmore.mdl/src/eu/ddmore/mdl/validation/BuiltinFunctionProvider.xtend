package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.type.MclTypeProvider
import eu.ddmore.mdl.type.MclTypeProvider.PrimitiveTypeInfo
import java.util.Map
import java.util.HashSet
import java.util.Set

import org.eclipse.xtend.lib.annotations.Data
import java.util.List

import static extension eu.ddmore.mdl.utils.DomainObjectModelUtils.*
import eu.ddmore.mdl.type.MclTypeProvider.TypeInfo

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
		PrimitiveTypeInfo expectedType
		boolean mandatory
	}

	static class NamedArgFuncDefn implements FunctDefn{
		PrimitiveTypeInfo returnType
		Map<String, FunctionArgument> arguments	 
		
		override int getNumArgs(){
			arguments.size
		}
		
		override PrimitiveTypeInfo getReturnType(){
			returnType
		}
	}
	
	private static val Map<String, List<? extends FunctDefn>> functDefns = #{
		'log' -> #[ new SimpleFuncDefn => [ numArgs = 2 returnType = MclTypeProvider::REAL_TYPE ] ],
		'ln' -> #[ new SimpleFuncDefn => [ numArgs = 1 returnType = MclTypeProvider::REAL_TYPE ] ],
		'abs' -> #[ new SimpleFuncDefn => [ numArgs = 1 returnType = MclTypeProvider::REAL_TYPE ] ],
		'exp' -> #[ new SimpleFuncDefn => [ numArgs = 1 returnType = MclTypeProvider::REAL_TYPE ] ],
		'seq' -> #[ new SimpleFuncDefn => [ numArgs = 3 returnType = MclTypeProvider::INT_VECTOR_TYPE ] ],
		'Normal' -> #[ new NamedArgFuncDefn => [ returnType = MclTypeProvider::PDF_TYPE arguments = #{
						'mean' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'sd' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} ],
					new NamedArgFuncDefn => [ returnType = MclTypeProvider::PDF_TYPE arguments = #{
						'mean' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'var' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} ]					 ],
		'linear' -> #[ new NamedArgFuncDefn => [ returnType = MclTypeProvider::REAL_TYPE arguments = #{
						'pop' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'fixEff' -> new FunctionArgument(MclTypeProvider::MAPPING_TYPE, false),
						'ranEff' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} ]					],
		'general' -> #[ new NamedArgFuncDefn => [ returnType = MclTypeProvider::REAL_TYPE arguments = #{
						'grp' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'ranEff' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} ] ],
		'combinedError1' -> #[ new NamedArgFuncDefn => [ returnType = MclTypeProvider::REAL_TYPE arguments = #{
						'additive' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'proportional' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'prediction' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'eps' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, false)
					} ] ],
		'combinedError2' -> #[ new NamedArgFuncDefn => [ returnType = MclTypeProvider::REAL_TYPE arguments = #{
						'additive' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'proportional' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'prediction' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'eps' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, false)
					} ] ]
	}

	
	def getTransformFunctionType(String fName){
		val defns = functDefns.get(fName)
		if(defns != null)
			if(defns.size > 1)
				MclTypeProvider::UNDEFINED_TYPE
			else
				defns.head.returnType
		else
			MclTypeProvider::UNDEFINED_TYPE
	}
	
	def getFunctionType(BuiltinFunctionCall it){
		findFuncDefn?.returnType ?: MclTypeProvider::UNDEFINED_TYPE
	}
	
	def getNamedArgumentType(ValuePair vp){
		val funcDefn = vp.parentFunction
		val defn = funcDefn.findFuncDefn
		switch(defn){
			NamedArgFuncDefn:
				defn.arguments.get(vp.argumentName)?.expectedType ?: MclTypeProvider::UNDEFINED_TYPE
			default:
				MclTypeProvider::UNDEFINED_TYPE
		}
	}
	
	// precindition is that this is a list of NamedFuncDefns
	private def chooseBestMatchingArguments(NamedFuncArguments it, List<? extends FunctDefn> availableDefns){
		var matchCount = 0;
		var FunctDefn bestMatch = availableDefns.head
		for(fd : availableDefns){
			var currMatchCount = 0
			val nfd = fd as NamedArgFuncDefn
			for(vp : arguments){
				if(nfd.arguments.containsKey(vp.argumentName)) currMatchCount++
			}
			if(currMatchCount > matchCount){
				bestMatch = fd
				matchCount = currMatchCount
			}
		}
		bestMatch
	}
	
	private def findFuncDefn(BuiltinFunctionCall it){
		val availableDefns = functDefns.get(func)
		var FunctDefn retVal = null
		if(availableDefns != null){
			val firstDefn = availableDefns.head
			retVal = switch(firstDefn){
				SimpleFuncDefn: firstDefn
				NamedArgFuncDefn
					case availableDefns.size == 1: firstDefn
					case availableDefns.size > 1:{
						if(argList == null) firstDefn
						else if(argList instanceof NamedFuncArguments){
							(argList as NamedFuncArguments).chooseBestMatchingArguments(availableDefns)
						}
						else null
					}
			}
		}
		retVal
	}
	
	// precondition - this is a unnamed function 	
	def checkUnnamedFunctionDefn(BuiltinFunctionCall it, (String) => void unkFuncErr, (String, int) => void incorrectNumArgsErr){
		val FunctDefn funcDefn = findFuncDefn
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
		val funcDefn = findFuncDefn
		if(funcDefn == null || !(funcDefn instanceof NamedArgFuncDefn)){
			unkFuncErr.apply(func)
		}
	}
	
	// returns the set of mandatory arguments not set in a function or an
	// empty set if the function name is not recognised.
	def getMissingMandatoryArgumentNames(NamedFuncArguments it){
		val Set<String> mandatoryArgs = new HashSet<String>
		val funcDefn = functionCall.findFuncDefn
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
		val funcDefn = functionCall.findFuncDefn
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