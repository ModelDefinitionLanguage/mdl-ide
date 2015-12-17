package eu.ddmore.mdl.provider

import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.FuncArguments
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.mdl.UnnamedArgument
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.type.MclTypeProvider
import eu.ddmore.mdl.type.MclTypeProvider.BuiltinEnumTypeInfo
import eu.ddmore.mdl.type.MclTypeProvider.TypeInfo
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import java.util.Collections
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import org.eclipse.xtext.EcoreUtil2

class BuiltinFunctionProvider {
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	
	interface FunctDefn{
		def int getNumArgs()
		def TypeInfo getReturnType()
	} 	

	@Data @FinalFieldsConstructor
	static class SimpleFuncDefn implements FunctDefn{
		List<? extends TypeInfo> argTypes
		TypeInfo returnType	 

		override int getNumArgs(){
			argTypes.size
		}
		
		override TypeInfo getReturnType(){
			returnType
		}
	}

	@Data @FinalFieldsConstructor
	static class FunctionArgument{
		TypeInfo expectedType
		boolean mandatory
	}

	@Data @FinalFieldsConstructor
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
	
	val Map<String, List<? extends FunctDefn>> functDefns
	val Map<String, Map<String, ? extends TypeInfo>> attEnumTypes

	new(){
		functDefns = BuiltinFunctionTable::functDefns
		attEnumTypes = new HashMap<String, Map<String, ? extends TypeInfo>>
		buildEnumTypes
	}
	
	private def buildEnumTypes(){
		for(blkName : functDefns.keySet){
			val valueMap = new HashMap<String, BuiltinEnumTypeInfo>
			attEnumTypes.put(blkName, valueMap)
			val bd = functDefns.get(blkName)
			for(a : bd){
				if(a instanceof NamedArgFuncDefn){
					val nfd = a as NamedArgFuncDefn 
				for(att : nfd.arguments.values){
					if(att.expectedType instanceof BuiltinEnumTypeInfo){
						val builtinType = att.expectedType as BuiltinEnumTypeInfo
						for(v : builtinType.expectedValues){
							valueMap.put(v, builtinType)
						}
					}
				}
				}
			}
		}
		attEnumTypes
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
	
	def TypeInfo getFunctionType(BuiltinFunctionCall it){
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
	
	def getUnamedArgumentType(UnnamedArgument vp){
		val funcDefn = vp.parentFunction
		val argIdx = vp.funcArgNum
		val defn = funcDefn.findFuncDefn
		switch(defn){
			SimpleFuncDefn case argIdx >= 0 && argIdx < defn.numArgs:
				defn.argTypes.get(argIdx)
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
	
	def isNamedArgFunction(BuiltinFunctionCall it){
		val funcDefn = functDefns.get(func)
		funcDefn != null && funcDefn.head instanceof NamedArgFuncDefn
	}
	
	
	def getNamedArguments(BuiltinFunctionCall it){
		val args = argList
		switch(args){
			NamedFuncArguments:	args.arguments
			default: Collections::emptyList
		}
	}
	
	def getArgumentExpression(BuiltinFunctionCall it, String attName){
		val args = argList
		switch(args){
			NamedFuncArguments:
				args.getArgumentExpression(attName)
			default: null
		}
	}

	def getArgumentEnumValue(BuiltinFunctionCall it, String attName){
		val args = argList
		switch(args){
			NamedFuncArguments:
				args.getArgumentEnumValue(attName)
			default: null
		}
	}

	def getArgumentExpression(NamedFuncArguments it, String attName){
		arguments.findFirst[argumentName == attName]?.expression
	}

	def getArgumentEnumValue(FuncArguments args, String argName){
		switch(args){
			NamedFuncArguments:{
				val enumExp = args.getArgumentExpression(argName)
				switch(enumExp){
					EnumExpression:
						return enumExp.enumValue
					default: null
				}
			}
			default: null
		}
	}
	
	def isArgumentDuplicated(BuiltinFunctionCall owningFunc, ValuePair it){
		val args = owningFunc.argList
		if(args instanceof NamedFuncArguments){
			return args.arguments.filter[a| a.argumentName == argumentName].size > 1
		}
		false
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

	def TypeInfo getTypeOfFunctionBuiltinEnum(EnumExpression ee){
		val funct = EcoreUtil2.getContainerOfType(ee, BuiltinFunctionCall)
		val blockName = funct.func
		val enumValue = ee.enumValue
		val defnType = attEnumTypes.get(blockName)?.get(enumValue) ?: MclTypeProvider::UNDEFINED_TYPE
		defnType
	}

	def boolean isValidTransform(TransformedDefinition it){
		isValidTransformFunction(transform)
	}

	def boolean isValidTransformFunction(String fName){
		BuiltinFunctionTable::TRANSFORM_FUNCS.contains(fName)
	}

}
