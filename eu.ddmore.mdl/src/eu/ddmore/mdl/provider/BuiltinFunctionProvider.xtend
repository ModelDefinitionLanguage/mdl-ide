package eu.ddmore.mdl.provider

import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.FuncArguments
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.mdl.UnnamedArgument
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.type.BuiltinEnumTypeInfo
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.MdlUtils
import java.util.Collections
import java.util.HashMap
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import org.eclipse.xtext.EcoreUtil2
import java.util.ArrayList

class BuiltinFunctionProvider {
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	extension MdlUtils mu = new MdlUtils
	
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
		Map<String, TypeInfo> arguments
		List<Map<String, Boolean>> signatures
		
		new(TypeInfo returnType, Map<String, FunctionArgument> arguments){
			this.returnType = returnType
			this.arguments = new HashMap<String, TypeInfo>
			this.signatures = new ArrayList<Map<String, Boolean>>
			for(key : arguments.keySet){
				val map = new HashMap<String, Boolean>
				map.put(key, true)
				this.signatures.add(map)
				this.arguments.put(key, arguments.get(key).expectedType)
			}
		}
		
		override int getNumArgs(){
			arguments.size
		}
		
		override TypeInfo getReturnType(){
			returnType
		}
	}
	
//	val Map<String, List<? extends FunctDefn>> functDefns
	val Map<String, Map<String, ? extends TypeInfo>> attEnumTypes

	new(){
//		functDefns = BuiltinFunctionTable::getInstance.functDefns
		attEnumTypes = new HashMap<String, Map<String, ? extends TypeInfo>>
		buildEnumTypes
	}
	
	private def Map<String, ? extends FunctDefn> getFunctDefns(){
		BuiltinFunctionTable::getInstance.functDefns
	}
	
	private def buildEnumTypes(){
		for(blkName : functDefns.keySet){
			val valueMap = new HashMap<String, BuiltinEnumTypeInfo>
			attEnumTypes.put(blkName, valueMap)
			val a = functDefns.get(blkName)
//			for(a : bd){
				if(a instanceof NamedArgFuncDefn){
					val nfd = a as NamedArgFuncDefn 
					for(att : nfd.arguments.values){
						if(att instanceof BuiltinEnumTypeInfo){
							val builtinType = att as BuiltinEnumTypeInfo
							for(v : builtinType.expectedValues){
								valueMap.put(v, builtinType)
							}
						}
					}
				}
//			}
		}
		attEnumTypes
	} 

	def getTransformFunctionType(String fName){
		val defns = functDefns.get(fName)
		if(defns != null)
//			if(defns.size > 1)
//				TypeSystemProvider::UNDEFINED_TYPE
//			else
//				defns.head.returnType
				defns.returnType
		else
			TypeSystemProvider::UNDEFINED_TYPE
	}
	
	def TypeInfo getFunctionType(SymbolReference it){
		findFuncDefn?.returnType ?: TypeSystemProvider::UNDEFINED_TYPE
	}
	
	def getNamedArgumentType(ValuePair vp){
		val funcDefn = vp.parentFunction
		val defn = funcDefn.findFuncDefn
		switch(defn){
			NamedArgFuncDefn:
				defn.arguments.get(vp.argumentName) ?: TypeSystemProvider::UNDEFINED_TYPE
			default:
				TypeSystemProvider::UNDEFINED_TYPE
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
				TypeSystemProvider::UNDEFINED_TYPE
		}
	}
	
	// precondition is that this is a list of NamedFuncDefns
	private def chooseBestMatchingArguments(NamedFuncArguments it, NamedArgFuncDefn availableDefns){
		var matchCount = 0;
		var Map<String, Boolean> bestMatch = availableDefns.signatures.head
		for(fd : availableDefns.signatures){
			var currMatchCount = 0
			for(vp : arguments){
				if(fd.containsKey(vp.argumentName)) currMatchCount++
			}
			if(currMatchCount > matchCount){
				bestMatch = fd
				matchCount = currMatchCount
			}
		}
		bestMatch
	}
	
	def isFunctionName(String name){
		functDefns.containsKey(name)
	}
	
	public def findFuncDefn(SymbolReference it){
		functDefns.get(func)
//		val availableDefns = functDefns.get(func)
//		var FunctDefn retVal = null
//		if(availableDefns != null){
//			val firstDefn = availableDefns
//			retVal = switch(firstDefn){
//				SimpleFuncDefn: firstDefn
//				NamedArgFuncDefn
//					case firstDefn.signatures.size == 1: firstDefn
//				NamedArgFuncDefn
//					case firstDefn.signatures.size > 1:{
//						if(argList == null) firstDefn
//						else if(argList instanceof NamedFuncArguments){
//							(argList as NamedFuncArguments).chooseBestMatchingArguments(firstDefn)
//						}
//						else null
//					}
//			}
//		}
//		retVal
	}
	
	// returns the set of mandatory arguments not set in a function or an
	// empty set if the function name is not recognised.
	def getMissingMandatoryArgumentNames(NamedFuncArguments it){
		val Set<String> mandatoryArgs = new HashSet<String>
		val funcDefn = functionCall.findFuncDefn
		if(funcDefn instanceof NamedArgFuncDefn){
			val signature = chooseBestMatchingArguments(funcDefn)
			// store all the mandatory argument names
			funcDefn.arguments.forEach[arg, fa| val mand = signature.get(arg) if(mand != null && mand) mandatoryArgs.add(arg) ]
			// remove those that are used
			arguments.forEach[vp| mandatoryArgs.remove(vp.argumentName)]
		}
		mandatoryArgs
	}
	
	private def getFunctionCall(NamedFuncArguments it){
		eContainer as SymbolReference
	}
	
	public def getFunctionCall(ValuePair it){
		eContainer.eContainer as SymbolReference
	}
	
	def isNamedArgFunction(SymbolReference it){
		val funcDefn = functDefns.get(func)
		funcDefn != null && funcDefn instanceof NamedArgFuncDefn
	}
	
	
	def getNamedArguments(SymbolReference it){
		val args = argList
		switch(args){
			NamedFuncArguments:	args.arguments
			default: Collections::emptyList
		}
	}
	
	def getArgumentExpression(SymbolReference it, String attName){
		val args = argList
		switch(args){
			NamedFuncArguments:
				args.getArgumentExpression(attName)
			default: null
		}
	}

	def getArgumentEnumValue(SymbolReference it, String attName){
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
	
	def isArgumentDuplicated(SymbolReference owningFunc, ValuePair it){
		val args = owningFunc.argList
		if(args instanceof NamedFuncArguments){
			return args.arguments.filter[a| a.argumentName == argumentName].size > 1
		}
		false
	}
	
	
	def TypeInfo getTypeOfFunctionBuiltinEnum(EnumExpression ee){
		val funct = EcoreUtil2.getContainerOfType(ee, SymbolReference)
		val blockName = funct.func
		val enumValue = ee.enumValue
		val defnType = attEnumTypes.get(blockName)?.get(enumValue) ?: TypeSystemProvider::UNDEFINED_TYPE
		defnType
	}

	def boolean isValidTransform(TransformedDefinition it){
		isValidTransformFunction(transform)
	}

	def boolean isValidTransformFunction(String fName){
		BuiltinFunctionTable::TRANSFORM_FUNCS.contains(fName)
	}

}
