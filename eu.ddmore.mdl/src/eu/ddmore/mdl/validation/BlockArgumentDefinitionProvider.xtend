package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BlockArguments
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.ForwardDeclaration
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.ValuePair
import java.util.HashSet
import java.util.HashMap
import java.util.Map

class BlockArgumentDefinitionProvider {
	
	static val OBJECT_ARG = "ObjectArg"
	
	// Arguments to objects and blocks go here.
	static val objArgVarDeclNames = new HashMap<String, Map<String, Map<String, Boolean> > > 
//	#{ 
//		OBJECT_ARG -> #{ MdlValidator::MDLOBJ -> #{ 'idv' -> false } }
//	}
	
	def getUnusedMandatoryObjVarDecl(BlockArguments it) {
		val parent = eContainer
		var String parentName = ""
		var String objectName = ""
		switch(parent){
			MclObject case objArgVarDeclNames.containsKey(OBJECT_ARG): {
				objectName = OBJECT_ARG
				parentName = parent.mdlObjType
			}
			BlockStatement case parent.eContainer instanceof MclObject && objArgVarDeclNames.containsKey((parent.eContainer as MclObject).mdlObjType): {
				objectName = (parent.eContainer as MclObject).mdlObjType
				parentName = parent.identifier
			}
		}
		val unused = new HashSet
		if(objArgVarDeclNames.containsKey(objectName) && objArgVarDeclNames.get(objectName).containsKey(parentName)){
			unused.addAll(objArgVarDeclNames.get(objectName).get(parentName).filter[key, mand| mand==true].keySet);
			for(arg : args){
				switch(arg){
					ForwardDeclaration case objArgVarDeclNames.get(objectName).get(parentName).containsKey(arg.declType):
						unused.remove(arg.declType) 
					// Argument case :  do nothing 
				}
			}
		} 
		unused
	}
	
	def isValidObjVarDecl(ForwardDeclaration it) {
		// we need to get the owner of the BlockArgument->BlockArguments rule
		val parent = eContainer.eContainer
		
		var String parentName = ""
		var String objectName = ""
		switch(parent){
			MclObject case objArgVarDeclNames.containsKey(OBJECT_ARG): {
				objectName = OBJECT_ARG
				parentName = parent.mdlObjType
			}
			BlockStatement case parent.eContainer instanceof MclObject && objArgVarDeclNames.containsKey((parent.eContainer as MclObject).mdlObjType): {
				objectName = (parent.eContainer as MclObject).mdlObjType 
				parentName = parent.identifier
			}
		}
		if(objArgVarDeclNames.containsKey(objectName) && objArgVarDeclNames.get(objectName).containsKey(parentName)){
			return objArgVarDeclNames.get(objectName).get(parentName).containsKey(declType)
		}
		false
	}
	

	// properties to blocks and MclObjects go here 
	static val blkArgPropNames = #{ 
		MdlValidator::MDLOBJ -> #{
			'RANDOM_VARIABLE_DEFINITION' -> #{ 'level' -> true }
		}//,
//		MdlValidator::PARAMOBJ -> #{
//			'VARIABILITY' -> #{ 'type' -> true }
//		},
//		MdlValidator::TASKOBJ -> #{ 
//			"ESTIMATE" -> #{ 'target' -> true },
//			"SIMULATE" -> #{ 'target' -> true },
//			"OPTIMISE" -> #{ 'target' -> true }
//		}
	}
	
	def getUnusedMandatoryPropertyArguments(BlockArguments it) {
		val parent = eContainer
		var String parentName = ""
		var String objectName = ""
		switch(parent){
			MclObject case blkArgPropNames.containsKey(OBJECT_ARG): {
				objectName = OBJECT_ARG
				parentName = parent.mdlObjType
			}
			BlockStatement case parent.eContainer instanceof MclObject && blkArgPropNames.containsKey((parent.eContainer as MclObject).mdlObjType): {
				objectName = (parent.eContainer as MclObject).mdlObjType 
				parentName = parent.identifier
			}
		}
		val unused = new HashSet
		if(blkArgPropNames.containsKey(objectName) && blkArgPropNames.get(objectName).containsKey(parentName)){
			unused.addAll(blkArgPropNames.get(objectName).get(parentName).filter[key, mand| mand==true].keySet);
			for(arg : args){
				switch(arg){
					ValuePair case blkArgPropNames.get(objectName).get(parentName).containsKey(arg.argumentName):
						unused.remove(arg.argumentName) 
					// Argument case :  do nothing 
				}
			}
		} 
		unused
	}
	
	def isValidBlkArgProperty(ValuePair it) {
		if(!(eContainer instanceof BlockArguments)) throw new IllegalArgumentException("Value pair expected to be owned by a BlockArgument")
		// we need to get the owner of the BlockArgument->BlockArguments rule
		val parent = eContainer.eContainer
		
		var String parentName = ""
		var String objectName = ""
		switch(parent){
			MclObject case blkArgPropNames.containsKey(OBJECT_ARG): {
				objectName = OBJECT_ARG
				parentName = parent.mdlObjType
			}
			BlockStatement case parent.eContainer instanceof MclObject
				 && blkArgPropNames.containsKey((parent.eContainer as MclObject).mdlObjType): {
				objectName = (parent.eContainer as MclObject).mdlObjType 
				parentName = parent.identifier
			}
		}
		if(blkArgPropNames.containsKey(objectName) && blkArgPropNames.get(objectName).containsKey(parentName)){
			return blkArgPropNames.get(objectName).get(parentName).containsKey(argumentName)
		}
		false
	}
	

}