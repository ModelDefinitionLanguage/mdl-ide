package eu.ddmore.mdl.provider

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.type.BuiltinEnumTypeInfo
import eu.ddmore.mdl.type.SublistTypeInfo
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdllib.mdllib.Library
import eu.ddmore.mdllib.mdllib.SubListTypeDefinition
import java.util.ArrayList
import java.util.List
import org.eclipse.xtext.EcoreUtil2

class SublistDefinitionProvider {
	
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils

//	val Map<String, SublistTypeInfo> sublistDefns

//	new (){
//		sublistDefns = SublistDefinitionTable::instance.sublistDefns
//	}

	def SublistTypeInfo findSublistMatch(SubListExpression sle){
		val blk = EcoreUtil2.getContainerOfType(sle.eContainer, BlockStatement)
		val lib = EcoreUtil2.getContainerOfType(blk.blkId.eContainer, Library)
		// first look for subtype defns that have attributes that could possible math the subtype 
		val attNames = sle.attributeNames
		
		val candidateDefns = new ArrayList<SublistTypeInfo>
		lib.typeDefns.forEach[td|
			if(td instanceof SubListTypeDefinition){
				if(td.attributes.exists[at|
					attNames.exists[an| an == at.name] 
					]){
					candidateDefns.add(new SublistTypeInfo(td))
				} 
			}
		]
//		sublistDefns.values.filter(sl|
//			sl.attributes.exists(ad| 
//				attNames.exists(attName| 
//					ad.name == attName
//				)
//			)
//		)
		
//		val candidateDefns = sublistDefns.values.filter(sl|
//			sl.attributes.exists(ad| 
//				attNames.exists(attName| 
//					ad.name == attName
//				)
//			)
//		)
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

	def TypeInfo getSublistAttributeType(ValuePair vp){
		val attList = EcoreUtil2.getContainerOfType(vp.eContainer, SubListExpression)
		val subListDefn = attList.findSublistMatch
		if(subListDefn != null){
			val attDefn = subListDefn.attributes.findFirst[name == vp.argumentName]
			attDefn?.attType ?: TypeSystemProvider::UNDEFINED_TYPE
		}
		else TypeSystemProvider::UNDEFINED_TYPE
	}
	
	def TypeInfo getTypeOfAttributeBuiltinEnum(SubListExpression it, EnumExpression ee){
		val vp = ee.getOwningValuePair
		val enumValue = ee.enumValue
		val SublistTypeInfo sublistType = findSublistMatch
		val defnType = sublistType.attributes.findFirst[name == vp.argumentName]?.attType ?: TypeSystemProvider::UNDEFINED_TYPE
		switch(defnType){
			BuiltinEnumTypeInfo:
				if(defnType.containsValue(enumValue)) defnType else TypeSystemProvider::UNDEFINED_TYPE
			default:
				TypeSystemProvider::UNDEFINED_TYPE
		}
	}

}
