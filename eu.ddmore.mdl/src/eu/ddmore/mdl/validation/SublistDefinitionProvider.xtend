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
import java.util.Map

class SublistDefinitionProvider {
	
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils


	public static val INTERVENTION_SEQ_SUBLIST = "intSeqAtts"
	public static val SAMPLING_SEQ_SUBLIST = "sampSeqAtts"
	public static val FIX_EFF_SUBLIST = "fixEffAtts"
	public static val PRIOR_FORMAT_SUBLIST = "priorFormat"
	public static val COMPLEX_COMBINATION_SUBLIST = "cplxCombSublist"

	public static val COV_ATT = 'cov'
	public static val CATCOV_ATT = 'catCov'
	
	static val COV = new AttributeDefn('cov', true, MclTypeProvider::REAL_TYPE.makeReference)
	static val COEFF = new AttributeDefn('coeff', true, MclTypeProvider::REAL_TYPE.makeReference)
	static val CAT_COV = new AttributeDefn('catCov', true, MclTypeProvider::GENERIC_ENUM_VALUE_TYPE.makeReference)
	static val PRIOR_ELEMENT_TYPE_TYPE = new BuiltinEnumTypeInfo('priorElementType', #{'matrix', 'vector'})
	
	static val Map<String, SublistTypeInfo> sublistDefns = #{
		FIX_EFF_SUBLIST -> (new SublistTypeInfo(FIX_EFF_SUBLIST, #[COV, CAT_COV, COEFF], #[
																   	#{COEFF.name -> true, COV.name -> true},
																   	#{COEFF.name -> true, CAT_COV.name -> true}
																   ])),
		INTERVENTION_SEQ_SUBLIST -> (new SublistTypeInfo(INTERVENTION_SEQ_SUBLIST, #[
											new AttributeDefn("admin", true,	ListDefinitionProvider::ADMINISTRATION_TYPE.makeReference),
											new AttributeDefn("start", true, MclTypeProvider::REAL_TYPE),
											new AttributeDefn("end", true, MclTypeProvider::REAL_TYPE)],
											#[#{'admin' -> true, 'start' -> false, 'end' -> false}])),
		SAMPLING_SEQ_SUBLIST -> (new SublistTypeInfo(SAMPLING_SEQ_SUBLIST, #[
												new AttributeDefn("sample", true, ListDefinitionProvider::SAMPLING_TYPE.makeReference),
												new AttributeDefn("start", true, MclTypeProvider::REAL_TYPE),
												new AttributeDefn("end", true, MclTypeProvider::REAL_TYPE)],
												#[#{'sample' -> true, 'start' -> true, 'end' -> false}])),
		PRIOR_FORMAT_SUBLIST -> (new SublistTypeInfo(PRIOR_FORMAT_SUBLIST, #[new AttributeDefn("element", true, MclTypeProvider::STRING_TYPE),
												new AttributeDefn("type", true, PRIOR_ELEMENT_TYPE_TYPE)],
												#[#{'element' -> true, 'type' -> true}])),
		COMPLEX_COMBINATION_SUBLIST -> (new SublistTypeInfo(COMPLEX_COMBINATION_SUBLIST,
												#[new AttributeDefn("sample", true, ListDefinitionProvider::SAMPLING_TYPE.makeReference),
												new AttributeDefn("startTime", false, MclTypeProvider::REAL_TYPE)],
												#[#{'sample' -> true, 'startTime' -> false}]))
	}

	def static SublistTypeInfo getSublist(String name){
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
