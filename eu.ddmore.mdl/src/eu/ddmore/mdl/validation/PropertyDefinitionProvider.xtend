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
import java.util.Map

import eu.ddmore.mdl.type.MclTypeProvider.TypeInfo
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.utils.DomainObjectModelUtils

class PropertyDefinitionProvider {
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	
//	static val TARGET_TYPE = new BuiltinEnumTypeInfo('target', #{'monolix', 'nonmem' })
//	static val TARGET_ATT = new AttributeDefn('target', null, true, TARGET_TYPE)
//	static val VERSION_ATT = new AttributeDefn('version', null, false, MclTypeProvider::STRING_TYPE)
	static val ALGO_TYPE = new BuiltinEnumTypeInfo('estAlgo', #{'saem', 'foce', 'fo', 'focei'})
	static val ALGO_ATT = new AttributeDefn('algo', true, ALGO_TYPE)
//	static val EST_OP_TYPE = new BuiltinEnumTypeInfo('estOp', #{'fim', 'estPop', 'estIndiv' })
//	static val EST_OP_ATT = new AttributeDefn('operation', null, false, EST_OP_TYPE)
	static val SOLVER_TYPE = new BuiltinEnumTypeInfo('solver', #{'stiff', 'nonStiff' })
	static val SOLVER_ATT = new AttributeDefn('solver', false, SOLVER_TYPE)
	static val ARM_SIZE_ATT = new AttributeDefn('armSize', false, MclTypeProvider::INT_TYPE)
	static val TOTAL_SIZE_ATT = new AttributeDefn('totalSize', false, MclTypeProvider::INT_TYPE)
	static val NUM_SAMPLES_ATT = new AttributeDefn('numberSamples', false, MclTypeProvider::INT_TYPE)
	static val TOTAL_COST_ATT = new AttributeDefn('totalCost', false, MclTypeProvider::REAL_TYPE)
	static val NUM_ARMS_ATT = new AttributeDefn('numberArms', false, MclTypeProvider::INT_TYPE)

	static val Map<String, List<AttributeDefn>> propertyDefns = #{ 
		BlockDefinitionProvider::ESTIMATE_BLK -> #[ALGO_ATT],
		BlockDefinitionProvider::SIMULATE_BLK -> #[SOLVER_ATT],
		BlockDefinitionProvider::DES_STUDY_DESIGN -> #[ARM_SIZE_ATT, TOTAL_SIZE_ATT, NUM_SAMPLES_ATT, TOTAL_COST_ATT, NUM_ARMS_ATT]
	}
	
/* the following was commented out inside propertyDefns declaration
 * 		BlockDefinitionProvider::ESTIMATE_BLK -> #[TARGET_ATT, EST_OP_ATT, VERSION_ATT, ALGO_ATT],
* 		BlockDefinitionProvider::SIMULATE_BLK -> #[SOLVER_ATT, VERSION_ATT, TARGET_ATT]
*/
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
		matchingPropertyDefn?.attType ?: MclTypeProvider::UNDEFINED_TYPE
	}


	def getTypeOfPropertyBuiltinEnum(EnumExpression ee){
		val blockName = ee.owningBlock.identifier
		val vp = ee.getOwningValuePair
		val enumValue = ee.enumValue
		val defnType = propertyDefns.get(blockName)?.findFirst[AttributeDefn p | p.name == vp.argumentName]?.attType ?: MclTypeProvider::UNDEFINED_TYPE
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
