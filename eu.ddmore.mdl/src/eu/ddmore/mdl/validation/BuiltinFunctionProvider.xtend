package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.type.MclTypeProvider.TypeInfo
import eu.ddmore.mdl.type.MclTypeProvider
import java.util.Map

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

	static class NamedArgFuncDefn implements FunctDefn{
		TypeInfo returnType
		Map<String, TypeInfo> arguments	 
		
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
						'mean' -> MclTypeProvider::REAL_TYPE, 'sd' -> MclTypeProvider::REAL_TYPE
					} ] ),
		'linear' -> ( new NamedArgFuncDefn => [ returnType = MclTypeProvider::REAL_TYPE arguments = #{
						'pop' -> MclTypeProvider::REAL_TYPE, 'fixEff' -> MclTypeProvider::REAL_TYPE, 'ranEff' -> MclTypeProvider::REAL_TYPE
					} ] ),
		'general' -> ( new NamedArgFuncDefn => [ returnType = MclTypeProvider::REAL_TYPE arguments = #{
						'grp' -> MclTypeProvider::REAL_TYPE, 'ranEff' -> MclTypeProvider::REAL_TYPE
					} ] ),
		'combinedError1' -> ( new NamedArgFuncDefn => [ returnType = MclTypeProvider::REAL_TYPE arguments = #{
						'additive' -> MclTypeProvider::REAL_TYPE, 'proportional' -> MclTypeProvider::REAL_TYPE,
						'prediction' -> MclTypeProvider::REAL_TYPE, 'eps' -> MclTypeProvider::REAL_TYPE
					} ] ),
		'combinedError2' -> ( new NamedArgFuncDefn => [ returnType = MclTypeProvider::REAL_TYPE arguments = #{
						'additive' -> MclTypeProvider::REAL_TYPE, 'proportional' -> MclTypeProvider::REAL_TYPE,
						'prediction' -> MclTypeProvider::REAL_TYPE, 'eps' -> MclTypeProvider::REAL_TYPE
					} ] )
	}

//	static val functionTypeTable = #{
//		'ln' -> REAL_TYPE,
//		'abs' -> REAL_TYPE,
//		'exp' -> REAL_TYPE,
//		'Normal' -> PDF_TYPE,
//		'linear' -> REAL_TYPE,
//		'general' -> REAL_TYPE,
//		'combinedError1' -> REAL_TYPE,
//		'combinedError2' -> REAL_TYPE
//	}

	
	def getFunctionType(String name){
		functDefns.get(name)?.returnType ?: MclTypeProvider::UNDEFINED_TYPE
	}
	
	def checkFunctionDefn(BuiltinFunctionCall it, (String) => void unkFuncErr, (String, int) => void incorrectNumArgsErr){
		if(!(argList instanceof NamedFuncArguments)){
		val funcDefn = functDefns.get(func)
		if(funcDefn == null){
			unkFuncErr.apply(func)
		}
		else {
			val funcArgList = argList
			switch(funcArgList){
				case funcArgList == null && funcDefn.numArgs != 0:
					incorrectNumArgsErr.apply(func, funcDefn.numArgs)
				UnnamedFuncArguments case funcArgList.args.size != funcDefn.numArgs:
					incorrectNumArgsErr.apply(func, funcDefn.numArgs)
				NamedFuncArguments: void // do nothing for the moment
			}
		}
		}
	}
	
}