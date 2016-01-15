package eu.ddmore.converter.mdl2pharmml

import eu.ddmore.mdl.mdl.MclObject
import org.eclipse.xtext.EcoreUtil2
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.validation.MdlValidator
import eu.ddmore.mdl.utils.MdlUtils

class MdlRootProvider {
	
	extension MdlUtils mu = new MdlUtils
	
	def getMdlObjectOfType(MclObject obj, String mdlType){
		val mcl = EcoreUtil2.getContainerOfType(obj, Mcl)
		mcl.objects.findFirst[mdlObjType == mdlType]
	}

	def getMdlObj(Mcl mdlRoot){
		mdlRoot.mogObject.getMdlObjectOfType(MdlValidator::MDLOBJ)
	}

	def getParamObj(Mcl mdlRoot){
		mdlRoot.mogObject.getMdlObjectOfType(MdlValidator::PARAMOBJ)
	}

	def getDataObj(Mcl mdlRoot){
		mdlRoot.mogObject.getMdlObjectOfType(MdlValidator::DATAOBJ)
	}

	def getTaskObj(Mcl mdlRoot){
		mdlRoot.mogObject.getMdlObjectOfType(MdlValidator::TASKOBJ)
	}

	def getDesignObj(Mcl mdlRoot){
		mdlRoot.mogObject.getMdlObjectOfType(MdlValidator::DESIGNOBJ)
	}

	def getPriorObj(Mcl mdlRoot){
		mdlRoot.mogObject.getMdlObjectOfType(MdlValidator::PRIOROBJ)
	}

}