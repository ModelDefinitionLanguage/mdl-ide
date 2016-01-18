package eu.ddmore.mdl.provider

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.provider.ListDefinitionProvider.AttributeDefn
import eu.ddmore.mdl.type.BuiltinEnumTypeInfo
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import java.util.ArrayList
import java.util.Collections
import java.util.HashSet
import java.util.List
import java.util.Map

class PropertyDefinitionProvider {
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	
	val Map<String, List<AttributeDefn>> propertyDefns
	
	new(){
		propertyDefns = PropertyDefinitionTable::propertyDefns
	}

	def List<String> getAttributeNames(String blkName){
		val attNames = new ArrayList<String>
		val List<AttributeDefn> propDefns = propertyDefns.get(blkName)
		if(propDefns != null) {
			propDefns.forEach([AttributeDefn at|attNames.add(at.name)])
		}
		attNames
	}

	def matchingPropertyDefn(ValuePair it){
		propertyDefns.get(parentBlock.identifier)?.findFirst[AttributeDefn p|p.name == argumentName]
	}


	def TypeInfo getTypeForProperty(ValuePair it){
		matchingPropertyDefn?.attType ?: TypeSystemProvider::UNDEFINED_TYPE
	}


	def getTypeOfPropertyBuiltinEnum(EnumExpression ee){
		val blockName = ee.owningBlock.identifier
		val vp = ee.getOwningValuePair
		val enumValue = ee.enumValue
		val defnType = propertyDefns.get(blockName)?.findFirst[AttributeDefn p | p.name == vp.argumentName]?.attType ?: TypeSystemProvider::UNDEFINED_TYPE
		switch(defnType){
			BuiltinEnumTypeInfo:
				if(defnType.categories.exists[c|c == enumValue]) defnType else TypeSystemProvider::UNDEFINED_TYPE
			default:
				TypeSystemProvider::UNDEFINED_TYPE
		}
	}


	def isPropertyKnown(ValuePair it){
		matchingPropertyDefn != null
	}


	def isPropertyMandatory(ValuePair it){
		if(matchingPropertyDefn != null) matchingPropertyDefn.isMandatory else false
	}

	def unusedMandatoryProperties(BlockStatement it){
		val mandatoryProps = new HashSet<String>
		val List<AttributeDefn> defns = (propertyDefns.get(identifier) ?: Collections.emptyList)
		defns.filter[AttributeDefn ad| ad.isMandatory].forEach[AttributeDefn a|mandatoryProps.add(a.name)]
		for(ps : statements.filter[Statement s|s instanceof PropertyStatement]){
			for(a : (ps as PropertyStatement).properties){
				mandatoryProps.remove(a.argumentName)
			}
		}
		mandatoryProps
	}


	def getPropertyExpression(PropertyStatement it, String name){
		properties.findFirst[argumentName == name]?.expression
	}

	def getPropertyEnumValue(PropertyStatement it, String attName){
		val enumExp = getPropertyExpression(attName)
		switch(enumExp){
			EnumExpression:
				return enumExp.enumValue
			default: null
		}
	}
}
