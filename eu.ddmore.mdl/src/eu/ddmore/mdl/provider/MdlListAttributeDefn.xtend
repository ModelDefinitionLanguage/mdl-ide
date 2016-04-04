package eu.ddmore.mdl.provider

import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.mdllib.ListAttributeDefn

class MdlListAttributeDefn{
	extension MdlLibUtils mlu = new MdlLibUtils
	
	val ListAttributeDefn _lad;
	val TypeInfo _attType


	new(ListAttributeDefn lad){
		_lad = lad
		_attType = lad.attType.typeInfo
	}
	
	
	def String getName(){
		_lad.name
	}
	
//	def boolean isMandatory(){
//		!_lad.isOptional
//	}
	
	
	def TypeInfo getAttType(){
		_attType
	}
	
//		TypeInfo catMappingType
//		boolean catMappingMandatory
	
//		new(String name, String depAtt, boolean mand, TypeInfo attType){
//			this(name, depAtt, mand, attType, null, false)
//		}
	
//		new(String name, boolean mand, TypeInfo attType){
//			this(name, mand, attType, null, false)
//		}
	
//		def isCatMappingMandatory(){
//			return catMappingType != null && catMappingMandatory
//		}
//		
//		def isCatMappingForbidden(){
//			return catMappingType == null || (catMappingType != null && !catMappingMandatory)
//		}
//		
//		def isCatMappingPossible(){
//			return catMappingType != null
//		}
}
