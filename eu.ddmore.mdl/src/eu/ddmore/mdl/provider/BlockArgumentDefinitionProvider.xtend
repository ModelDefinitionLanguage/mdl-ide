package eu.ddmore.mdl.provider

import eu.ddmore.mdl.mdl.BlockArguments
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.utils.MdlLibUtils
import org.eclipse.xtext.EcoreUtil2

class BlockArgumentDefinitionProvider {
	
//	extension BlockUtils bu = new BlockUtils/
	extension MdlLibUtils mlu = new MdlLibUtils
	
//	static val OBJECT_ARG = "ObjectArg"
	
	// Block arguments can no longer contain forward declarations.
	// Arguments to objects and blocks go here.
//	static val objArgVarDeclNames = new HashMap<String, Map<String, Map<String, Boolean> > > 
//	#{ 
//		OBJECT_ARG -> #{ MdlValidator::MDLOBJ -> #{ 'idv' -> false } }
//	}
	
//	def getUnusedMandatoryObjVarDecl(BlockArguments it) {
//		val parent = eContainer
//		var String parentName = ""
//		var String objectName = ""
//		switch(parent){
//			MclObject case objArgVarDeclNames.containsKey(OBJECT_ARG): {
//				objectName = OBJECT_ARG
//				parentName = parent.mdlObjType
//			}
//			BlockStatement case parent.eContainer instanceof MclObject && objArgVarDeclNames.containsKey((parent.eContainer as MclObject).mdlObjType): {
//				objectName = (parent.eContainer as MclObject).mdlObjType
//				parentName = parent.identifier
//			}
//		}
//		val unused = new HashSet
//		if(objArgVarDeclNames.containsKey(objectName) && objArgVarDeclNames.get(objectName).containsKey(parentName)){
//			unused.addAll(objArgVarDeclNames.get(objectName).get(parentName).filter[key, mand| mand==true].keySet);
//			for(arg : args){
//				switch(arg){
//					ForwardDeclaration case objArgVarDeclNames.get(objectName).get(parentName).containsKey(arg.declType):
//						unused.remove(arg.declType) 
//					// Argument case :  do nothing 
//				}
//			}
//		} 
//		unused
//	}
	
//	def isValidObjVarDecl(ForwardDeclaration it) {
//		// we need to get the owner of the BlockArgument->BlockArguments rule
//		val parent = eContainer.eContainer
//		
//		var String parentName = ""
//		var String objectName = ""
//		switch(parent){
//			MclObject case objArgVarDeclNames.containsKey(OBJECT_ARG): {
//				objectName = OBJECT_ARG
//				parentName = parent.mdlObjType
//			}
//			BlockStatement case parent.eContainer instanceof MclObject && objArgVarDeclNames.containsKey((parent.eContainer as MclObject).mdlObjType): {
//				objectName = (parent.eContainer as MclObject).mdlObjType 
//				parentName = parent.identifier
//			}
//		}
//		if(objArgVarDeclNames.containsKey(objectName) && objArgVarDeclNames.get(objectName).containsKey(parentName)){
//			return objArgVarDeclNames.get(objectName).get(parentName).containsKey(declType)
//		}
//		false
//	}
	

	// properties to blocks and MclObjects go here 
//	static val blkArgPropNames = #{ 
//		MdlValidator::MDLOBJ -> #{
//			'RANDOM_VARIABLE_DEFINITION' -> #{ 'level' -> true }
//		}//,
////		MdlValidator::PARAMOBJ -> #{
////			'VARIABILITY' -> #{ 'type' -> true }
////		},
////		MdlValidator::TASKOBJ -> #{ 
////			"ESTIMATE" -> #{ 'target' -> true },
////			"SIMULATE" -> #{ 'target' -> true },
////			"OPTIMISE" -> #{ 'target' -> true }
////		}
//	}
	
	def getUnusedMandatoryPropertyArguments(BlockArguments it) {
		val parent = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
//		var String parentName = ""
//		var String objectName = ""
//		switch(parent){
////			MclObject case blkArgPropNames.containsKey(OBJECT_ARG): {
////				objectName = OBJECT_ARG
////				parentName = parent.mdlObjType
////			}
//			BlockStatement case parent.eContainer instanceof MclObject && blkArgPropNames.containsKey((parent.eContainer as MclObject).mdlObjType): {
//				objectName = (parent.eContainer as MclObject).mdlObjType 
//				parentName = parent.identifier
//			}
//		}
		val unused = parent.blkId.mandatoryArgumentNames
//		if(blkArgPropNames.containsKey(objectName) && blkArgPropNames.get(objectName).containsKey(parentName)){
//			unused.addAll(blkArgPropNames.get(objectName).get(parentName).filter[key, mand| mand==true].keySet);
			for(arg : args){
				if(arg instanceof ValuePair){
					unused.remove(arg.argumentName)
				}
			}
//		} 
		unused
	}
	
	def isValidBlkArgProperty(ValuePair vp) {
		if(!(vp.eContainer instanceof BlockArguments)) throw new IllegalArgumentException("Value pair expected to be owned by a BlockArgument")
		// we need to get the owner of the BlockArgument->BlockArguments rule
		val parent = vp.eContainer.eContainer
		
//		var String parentName = ""
//		var String objectName = ""
//		switch(parent){
//			MclObject case blkArgPropNames.containsKey(OBJECT_ARG): {
//				objectName = OBJECT_ARG
//				parentName = parent.mdlObjType
//			}
		if(parent instanceof BlockStatement){
			parent.blkId.arguments.exists[
				name == vp.argumentName
			]
		}
	}
	

}