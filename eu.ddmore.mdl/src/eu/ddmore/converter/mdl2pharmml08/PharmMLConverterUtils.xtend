package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.type.PrimitiveType
import eu.ddmore.mdl.type.TypeInfo

class PharmMLConverterUtils {
	
	def getPharmMLType(TypeInfo type){
		switch(type.underlyingType.theType){
			case(PrimitiveType.Int):
				"int"
			case(PrimitiveType.Real):
				"real"
			case(PrimitiveType.String):
				"string"
			case(PrimitiveType.Boolean):
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