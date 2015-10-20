package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.type.MclTypeProvider
import eu.ddmore.mdl.type.MclTypeProvider.BuiltinEnumTypeInfo
import eu.ddmore.mdl.validation.ListDefinitionProvider.AttributeDefn
import java.util.ArrayList
import java.util.Collections
import java.util.HashSet
import java.util.List

import static extension eu.ddmore.mdl.utils.DomainObjectModelUtils.*
import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString

class PropertyDefinitionProvider {
	
//	static val TARGET_TYPE = new BuiltinEnumTypeInfo('target', #{'monolix', 'nonmem' })
//	static val TARGET_ATT = new AttributeDefn('target', null, true, TARGET_TYPE)
//	static val VERSION_ATT = new AttributeDefn('version', null, false, MclTypeProvider::STRING_TYPE)
	static val ALGO_TYPE = new BuiltinEnumTypeInfo('estAlgo', #{'saem', 'foce', 'fo'})
	static val ALGO_ATT = new AttributeDefn('algo', null, true, ALGO_TYPE)
//	static val EST_OP_TYPE = new BuiltinEnumTypeInfo('estOp', #{'fim', 'estPop', 'estIndiv' })
//	static val EST_OP_ATT = new AttributeDefn('operation', null, false, EST_OP_TYPE)
	static val SOLVER_TYPE = new BuiltinEnumTypeInfo('solver', #{'stiff', 'nonStiff' })
	static val SOLVER_ATT = new AttributeDefn('solver', null, false, SOLVER_TYPE)
	
	static val propertyDefns = #{
//		BlockDefinitionProvider::ESTIMATE_BLK -> #[TARGET_ATT, EST_OP_ATT, VERSION_ATT, ALGO_ATT],
//		BlockDefinitionProvider::SIMULATE_BLK -> #[SOLVER_ATT, VERSION_ATT, TARGET_ATT]
		BlockDefinitionProvider::ESTIMATE_BLK -> #[ALGO_ATT],
		BlockDefinitionProvider::SIMULATE_BLK -> #[SOLVER_ATT]
	}

	def List<String> getAttributeNames(String blkName){
		val attNames = new ArrayList<String>
		val propDefns = propertyDefns.get(blkName)
		if(propDefns != null){
			propDefns.forEach([at|attNames.add(at.name)])
		}
		attNames
	}

	def matchingPropertyDefn(ValuePair it){
		propertyDefns.get(parentBlock.identifier)?.findFirst[p|p.name == argumentName]
	}


	def getTypeForProperty(ValuePair it){
		matchingPropertyDefn?.attType ?: MclTypeProvider::UNDEFINED_TYPE
	}


	def getTypeOfPropertyBuiltinEnum(EnumExpression ee){
		val blockName = ee.owningBlock.identifier
		val vp = ee.getOwningValuePair
		val enumValue = ee.convertToString
		val defnType = propertyDefns.get(blockName)?.findFirst[name == vp.argumentName]?.attType ?: MclTypeProvider::UNDEFINED_TYPE
		switch(defnType){
			BuiltinEnumTypeInfo:
				if(defnType.categories.exists[c|c == enumValue]) defnType else MclTypeProvider::UNDEFINED_TYPE
			default:
				MclTypeProvider::UNDEFINED_TYPE
		}
	}


	def isPropertyKnown(ValuePair it){
		matchingPropertyDefn != null
	}


	def isPropertyMandatory(ValuePair it){
		matchingPropertyDefn?.isMandatory
	}

	def unusedMandatoryProperties(BlockStatement it){
		val mandatoryProps = new HashSet<String>
		val defns = (propertyDefns.get(identifier) ?: Collections.emptyList)
		defns.filter[ad| ad.isMandatory].forEach[a|mandatoryProps.add(a.name)]
		for(ps : statements.filter[s|s instanceof PropertyStatement]){
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