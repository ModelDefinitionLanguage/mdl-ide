package eu.ddmore.mdl.provider

import eu.ddmore.mdl.type.TypeInfo
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor

@Data @FinalFieldsConstructor
class AttributeDefn{
	String name
//		String depAtt // other att that must be present
	boolean mandatory
	TypeInfo attType
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
