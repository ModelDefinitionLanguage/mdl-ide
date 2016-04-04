package eu.ddmore.converter.mdl2pharmml

import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeInfoClass

class PharmMLConverterUtils {
	
	def getPharmMLType(TypeInfo type){
		switch(type.underlyingType.typeClass){
			case(TypeInfoClass.Int):
				"int"
			case(TypeInfoClass.Real):
				"real"
			case(TypeInfoClass.String):
				"string"
			case(TypeInfoClass.Boolean):
				"boolean"
			default:
				"undefined"
		}
	}
	
	def getPharmMLTransFunc(TransformedDefinition mclName){
		getPharmMLTransFunc(mclName.transform.name)
	}
	def getPharmMLTransFunc(String mclName){
		switch(mclName){
			case('ln'): 'log'
			default:
				mclName
		}
	}
	
	
}