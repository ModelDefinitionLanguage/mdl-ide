package eu.ddmore.mdl.provider

import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.FuncArguments
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.mdl.UnnamedArgument
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.mdllib.FunctionDefnBody
import eu.ddmore.mdllib.mdllib.NamedFuncArgs
import eu.ddmore.mdllib.mdllib.UnnamedFuncArgs
import java.util.ArrayList
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
	
	static abstract class FunctDefn{
		extension MdlLibUtils mlu = new MdlLibUtils

		val FunctionDefnBody funcBody

//		def int getNumArgs()
//		def TypeInfo getReturnType()

		new(FunctionDefnBody funcBody){
			this.funcBody = funcBody
		}

		def int getNumArgs(){
//			argTypes.size
			funcBody.funcSpec.argument.arguments.size
		}
		
		def TypeInfo getReturnType(){
			funcBody.funcSpec.returnType.typeInfo
		}
		
		def protected getFuncDefn(){
			funcBody
		}
		
		def abstract TypeInfo getArgumentType(String argName)
		
		def abstract boolean hasArgument(String argName)
		
	} 	

	static class SimpleFuncDefn extends FunctDefn{
		extension MdlLibUtils mlu = new MdlLibUtils

//		List<? extends TypeInfo> argTypes
//		TypeInfo returnType	 

		new(FunctionDefnBody funcBody){
			super(funcBody)
		}

		
		def List<? extends TypeInfo> getArgTypes(){
			val retVal = new ArrayList<TypeInfo>()
			funcDefn.funcSpec.argument.arguments.forEach[
				retVal.add(typeSpec.typeInfo)
			]
			retVal
		}
		
		override getArgumentType(String argName){
			TypeSystemProvider::UNDEFINED_TYPE
		}
		
		override hasArgument(String argName){
			false
		}
		
	}

	@Data @FinalFieldsConstructor
	static class FunctionArgument{
		TypeInfo expectedType
		boolean mandatory
	}

//	@Data @FinalFieldsConstructor
	static class NamedArgFuncDefn extends FunctDefn{
//		TypeInfo returnType
		val Map<String, TypeInfo> arguments
//		List<Map<String, Boolean>> signatures
		extension MdlLibUtils mlu = new MdlLibUtils

//		new(TypeInfo returnType, Map<String, FunctionArgument> arguments){
//			this.returnType = returnType
//			this.arguments = new HashMap<String, TypeInfo>
//			this.signatures = new ArrayList<Map<String, Boolean>>
//			val map = new HashMap<String, Boolean>
//			for(key : arguments.keySet){
//				map.put(key, arguments.get(key).mandatory)
//				this.arguments.put(key, arguments.get(key).expectedType)
//			}
//			this.signatures.add(map)
//		}

		new(FunctionDefnBody funcBody){
			super(funcBody)
			arguments = new HashMap<String, TypeInfo>
			val namedArgs = funcDefn.funcSpec.argument as NamedFuncArgs
			namedArgs.arguments.forEach[
				arguments.put(name, typeSpec.typeInfo)
			]
		}
		
//		def protected Map<String, TypeInfo> getArguments(){
//			val retVal = new HashMap<String, TypeInfo>
//			val namedArgs = funcDefn.funcSpec.argument as NamedFuncArgs
//			for(arg : namedArgs.arguments){
//				retVal.put(arg.name, arg.typeSpec.typeInfo)
//			}
//			retVal
//		}
		
		def List<Map<String, Boolean>> getSignatures(){
			val retVal = new ArrayList<Map<String, Boolean>>
			val funcArg = funcDefn.funcSpec.argument
			if(funcArg instanceof NamedFuncArgs){
				if(funcArg.sigLists.isEmpty){
					// no siglist so assume all args mandatory
					val sigMap = new HashMap<String, Boolean>
					for(arg : funcArg.arguments){
						sigMap.put(arg.name, true) 
					}
					retVal.add(sigMap)
				}
				else{
					for(sigList : funcArg.sigLists){
						val sigMap = new HashMap<String, Boolean>
						for(argRef : sigList.argRefs){
							sigMap.put(argRef.argRef.name, !argRef.optional) 
						}
						retVal.add(sigMap)
					}
				}
			}
			retVal
		}

		override getArgumentType(String argName){
			arguments.get(argName) ?: TypeSystemProvider::UNDEFINED_TYPE
		}

		override hasArgument(String argName){
			arguments.containsKey(argName)
		}
	}
	
//	val Map<String, List<? extends FunctDefn>> functDefns
//	val Map<String, Map<String, ? extends TypeInfo>> attEnumTypes

//	new(){
////		functDefns = BuiltinFunctionTable::getInstance.functDefns
//		attEnumTypes = new HashMap<String, Map<String, ? extends TypeInfo>>
//		buildEnumTypes
//	}
	
//	private def Map<String, ? extends FunctDefn> getFunctDefns(){
//		BuiltinFunctionTable::getInstance.functDefns
//	}
	
//	private def buildEnumTypes(){
//		for(blkName : functDefns.keySet){
//			val valueMap = new HashMap<String, BuiltinEnumTypeInfo>
//			attEnumTypes.put(blkName, valueMap)
//			val a = functDefns.get(blkName)
////			for(a : bd){
//				if(a instanceof NamedArgFuncDefn){
//					val nfd = a as NamedArgFuncDefn 
//					for(att : nfd.arguments.values){
//						if(att instanceof BuiltinEnumTypeInfo){
//							val builtinType = att as BuiltinEnumTypeInfo
//							for(v : builtinType.expectedValues){
//								valueMap.put(v, builtinType)
//							}
//						}
//					}
//				}
////			}
//		}
//		attEnumTypes
//	} 

//	def getTransformFunctionType(String fName){
//		val defns = functDefns.get(fName)
//		if(defns != null)
////			if(defns.size > 1)
////				TypeSystemProvider::UNDEFINED_TYPE
////			else
////				defns.head.returnType
//				defns.returnType
//		else
//			TypeSystemProvider::UNDEFINED_TYPE
//	}
	
	def TypeInfo getFunctionType(SymbolReference it){
		findFuncDefn?.returnType ?: TypeSystemProvider::UNDEFINED_TYPE
	}
	
	def getNamedArgumentType(ValuePair vp){
		val funcDefn = vp.parentFunction
		val defn = funcDefn.findFuncDefn
		switch(defn){
			NamedArgFuncDefn:
				defn.getArgumentType(vp.argumentName)
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
	
//	def isFunctionName(String name){
//		functDefns.containsKey(name)
//	}
	
	public def getFuncDefn(FunctionDefnBody fDefn){
		val argSpec = fDefn.funcSpec.argument
		switch(argSpec){
			UnnamedFuncArgs:
				new SimpleFuncDefn(fDefn)
			NamedFuncArgs:
				new NamedArgFuncDefn(fDefn)
			default: null
		}
	}


	public def findFuncDefn(SymbolReference fDefn){
		val ref = fDefn.ref
		if(ref instanceof FunctionDefnBody){
			ref.funcDefn
		}
		else null
//		functDefns.get(func)
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
		val funcDefn = findFuncDefn//functDefns.get(func)
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
		val vp = EcoreUtil2.getContainerOfType(ee, EnumPair)
//		val blockName = funct.func
//		val enumValue = ee.enumValue
//		val defnType = attEnumTypes.get(blockName)?.get(enumValue) ?: TypeSystemProvider::UNDEFINED_TYPE
//		defnType
		val fDefn = funct.findFuncDefn
		fDefn.getArgumentType(vp.argumentName)
	}

	def boolean isValidTransform(TransformedDefinition it){
		isValidTransformFunction(transform?.name)
	}

	def boolean isValidTransformFunction(String fName){
		if(fName != null) BuiltinFunctionTable::TRANSFORM_FUNCS.contains(fName)
		else false
	}

}
