package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.NamedFuncArguments

class BuiltinFunctionValidationProvider {
	
	static class FuncArgDefn{
		int numArgs		 
	}
	
	static val functDefns = #{
		'log' -> ( new FuncArgDefn => [ numArgs = 2 ] ),
		'ln' -> ( new FuncArgDefn => [ numArgs = 1 ] ),
		'abs' -> ( new FuncArgDefn => [ numArgs = 1 ] ),
		'exp' -> ( new FuncArgDefn => [ numArgs = 1 ] )
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