package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.type.MclTypeProvider
import eu.ddmore.mdl.type.MclTypeProvider.SublistTypeInfo
import eu.ddmore.mdl.validation.ListDefinitionProvider.AttributeDefn
import java.util.ArrayList
import java.util.List
import eu.ddmore.mdl.type.MclTypeProvider.BuiltinEnumTypeInfo
import eu.ddmore.mdl.type.MclTypeProvider.TypeInfo
import eu.ddmore.mdl.mdl.EnumExpression

import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString
import eu.ddmore.mdl.utils.DomainObjectModelUtils

class SublistDefinitionProvider {
	
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils


	public static val INTERVENTION_SEQ_SUBLIST = "intSeqAtts"
	public static val SAMPLING_SEQ_SUBLIST = "sampSeqAtts"
	public static val FIX_EFF_SUBLIST = "fixEffAtts"
	
	
	static val COV = new AttributeDefn('cov', true, MclTypeProvider::REAL_TYPE.makeReference)
	static val COEFF = new AttributeDefn('coeff', true, MclTypeProvider::REAL_TYPE.makeReference)
	static val CAT_COV = new AttributeDefn('catCov', true, MclTypeProvider::GENERIC_ENUM_VALUE_TYPE.makeReference)
	
	static val sublistDefns = #{
		FIX_EFF_SUBLIST -> (new SublistTypeInfo(FIX_EFF_SUBLIST, #[COV, CAT_COV, COEFF], #[
																   	#{COEFF.name -> true, COV.name -> true},
																   	#{COEFF.name -> true, CAT_COV.name -> true}
																   ])),
		INTERVENTION_SEQ_SUBLIST -> (new SublistTypeInfo(INTERVENTION_SEQ_SUBLIST, #[new AttributeDefn("interventionList", true, ListDefinitionProvider::ADMINISTRATION_TYPE.makeReference.makeVector),
											new AttributeDefn("start", true, MclTypeProvider::REAL_TYPE.makeVector),
											new AttributeDefn("end", true, MclTypeProvider::REAL_TYPE.makeVector)],
											#[#{'interventionList' -> true, 'start' -> false, 'end' -> false}])),
		SAMPLING_SEQ_SUBLIST -> (new SublistTypeInfo(SAMPLING_SEQ_SUBLIST, #[new AttributeDefn("samplingList", true, ListDefinitionProvider::SAMPLING_TYPE.makeReference.makeVector),
												new AttributeDefn("start", true, MclTypeProvider::REAL_TYPE.makeVector),
												new AttributeDefn("end", true, MclTypeProvider::REAL_TYPE.makeVector)],
												#[#{'samplingList' -> true, 'start' -> true, 'end' -> false}]))
	}

	def static getSublist(String name){
		return sublistDefns.get(name)
	}

	def SublistTypeInfo findSublistMatch(SubListExpression sle){
		// first look for subtype defns that have attributes that could possible math the subtype 
		val attNames = sle.attributeNames
		val candidateDefns = sublistDefns.values.filter(sl|
			sl.attributes.exists(ad| 
				attNames.exists(attName| 
					ad.name == attName
				)
			)
		)
		// now go through the candidates and find the first one that matches the
		// an argument set.
		for(candDefn : candidateDefns){
			for(nameSet : candDefn.nameSets){
				val iter = attNames.iterator
				var isCandidate = true
				while(iter.hasNext && isCandidate){
					val at = iter.next
					if(!nameSet.containsKey(at)){
						isCandidate = false
					}
				}
				val nameSetIter = nameSet.keySet.iterator
				while(nameSetIter.hasNext && isCandidate){
					val expectedName = nameSetIter.next
					if(attNames.findFirst[it == expectedName ] == null && nameSet.get(expectedName)){
						isCandidate = false
					}
				}
				if(isCandidate) return candDefn
			}
		}
		null
	}

	def List<String> getAttributeNames(SubListExpression sle){
		val attNames = new ArrayList<String>
		sle.attributes.forEach([at|attNames.add(at.argumentName)])
		attNames
	}

	def getAttributeExpression(SubListExpression sle, String name){
		sle.attributes.findFirst[argumentName == name]?.expression
	}

	def getAttributeEnumValue(SubListExpression sle, String name){
		val e = sle.getAttributeExpression(name)
		switch(e){
			
		}
	}

	def TypeInfo getTypeOfAttributeBuiltinEnum(SubListExpression it, EnumExpression ee){
		val vp = ee.getOwningValuePair
		val enumValue = ee.convertToString
		val SublistTypeInfo sublistType = findSublistMatch
		val defnType = sublistType.attributes.findFirst[name == vp.argumentName]?.attType ?: MclTypeProvider::UNDEFINED_TYPE
		switch(defnType){
			BuiltinEnumTypeInfo:
				if(defnType.categories.exists[c|c == enumValue]) defnType else MclTypeProvider::UNDEFINED_TYPE
			default:
				MclTypeProvider::UNDEFINED_TYPE
		}
	}

}
