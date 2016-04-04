package eu.ddmore.mdl.provider

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.type.BuiltinEnumTypeInfo
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.MdlLibUtils
import java.util.ArrayList
import java.util.HashSet
import java.util.List

class PropertyDefinitionProvider {
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	extension MdlLibUtils mlu = new MdlLibUtils
	
//	val Map<String, List<AttributeDefn>> propertyDefns
	
//	new(){
//		propertyDefns = PropertyDefinitionTable::propertyDefns
//	}


//	private def List<PropertyReference> getPropertyDefns(BlockDefinition it){
//		propRefs ?: Collections::emptyList
//	}

	def List<String> getAttributeNames(BlockStatement blkName){
		val attNames = new ArrayList<String>
		val propDefns = blkName.blkId.propRefs
		propDefns.forEach([attNames.add(propRef.name)])
		
//		val List<AttributeDefn> propDefns = propertyDefns.get(blkName)
//		if(propDefns != null) {
//			propDefns.forEach([AttributeDefn at|attNames.add(at.name)])
//		}
		attNames
	}

	def matchingPropertyDefn(ValuePair it){
		parentBlock.blkId.propRefs.findFirst[p|p.propRef.name == argumentName]
	}


	def TypeInfo getTypeForProperty(ValuePair it){
		matchingPropertyDefn?.propRef.propType.typeInfo ?: TypeSystemProvider::UNDEFINED_TYPE
	}


	def getTypeOfPropertyBuiltinEnum(EnumExpression ee){
		val blockName = ee.owningBlock.blkId
		val vp = ee.getOwningValuePair
		val enumValue = ee.enumValue
		val defnType = blockName.propRefs.findFirst[p | p.propRef.name == vp.argumentName]?.propRef.propType.typeInfo ?: TypeSystemProvider::UNDEFINED_TYPE
		switch(defnType){
			BuiltinEnumTypeInfo:
				if(defnType.containsValue(enumValue)) defnType else TypeSystemProvider::UNDEFINED_TYPE
			default:
				TypeSystemProvider::UNDEFINED_TYPE
		}
	}


	def isPropertyKnown(ValuePair it){
		matchingPropertyDefn != null
	}


	def isPropertyMandatory(ValuePair it){
		if(matchingPropertyDefn != null) !matchingPropertyDefn.isOptional else false
	}

	def unusedMandatoryProperties(BlockStatement it){
		val mandatoryProps = new HashSet<String>
		val defns = blkId.propRefs
		defns.filter[ad| !ad.isOptional].forEach[a|mandatoryProps.add(a.propRef.name)]
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
