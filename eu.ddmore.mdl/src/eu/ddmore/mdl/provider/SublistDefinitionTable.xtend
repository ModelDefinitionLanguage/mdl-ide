package eu.ddmore.mdl.provider

import eu.ddmore.mdl.type.BuiltinEnumTypeInfo
import eu.ddmore.mdl.type.SublistTypeInfo
import eu.ddmore.mdl.provider.ListDefinitionProvider.AttributeDefn
import java.util.Map
import eu.ddmore.mdl.type.TypeSystemProvider

class SublistDefinitionTable {
	public static val INTERVENTION_SEQ_SUBLIST = "intSeqAtts"
	public static val SAMPLING_SEQ_SUBLIST = "sampSeqAtts"
	public static val FIX_EFF_SUBLIST = "fixEffAtts"
	public static val PRIOR_FORMAT_SUBLIST = "priorFormat"
	public static val COMPLEX_COMBINATION_SUBLIST = "cplxCombSublist"

	public static val COV_ATT = 'cov'
	public static val CATCOV_ATT = 'catCov'
	
	val COV = new AttributeDefn('cov', true, TypeSystemProvider::REAL_TYPE.makeReference)
	val COEFF = new AttributeDefn('coeff', true, TypeSystemProvider::REAL_TYPE.makeReference)
	val CAT_COV = new AttributeDefn('catCov', true, TypeSystemProvider::GENERIC_ENUM_VALUE_TYPE.makeReference)
	val PRIOR_ELEMENT_TYPE_TYPE = new BuiltinEnumTypeInfo('priorElementType', #{'matrix', 'vector'})
	
	val Map<String, SublistTypeInfo> sublistDefns = #{
		FIX_EFF_SUBLIST -> (new SublistTypeInfo(FIX_EFF_SUBLIST, #[COV, CAT_COV, COEFF], #[
																   	#{COEFF.name -> true, COV.name -> true},
																   	#{COEFF.name -> true, CAT_COV.name -> true}
																   ])),
		INTERVENTION_SEQ_SUBLIST -> (new SublistTypeInfo(INTERVENTION_SEQ_SUBLIST, #[
											new AttributeDefn("admin", true,	ListDefinitionTable::ADMINISTRATION_TYPE.makeReference),
											new AttributeDefn("start", true, TypeSystemProvider::REAL_TYPE),
											new AttributeDefn("end", true, TypeSystemProvider::REAL_TYPE)],
											#[#{'admin' -> true, 'start' -> false, 'end' -> false}])),
		SAMPLING_SEQ_SUBLIST -> (new SublistTypeInfo(SAMPLING_SEQ_SUBLIST, #[
												new AttributeDefn("sample", true, ListDefinitionTable::SAMPLING_TYPE.makeReference),
												new AttributeDefn("start", true, TypeSystemProvider::REAL_TYPE),
												new AttributeDefn("end", true, TypeSystemProvider::REAL_TYPE)],
												#[#{'sample' -> true, 'start' -> true, 'end' -> false}])),
		PRIOR_FORMAT_SUBLIST -> (new SublistTypeInfo(PRIOR_FORMAT_SUBLIST, #[new AttributeDefn("element", true, TypeSystemProvider::STRING_TYPE),
												new AttributeDefn("type", true, PRIOR_ELEMENT_TYPE_TYPE)],
												#[#{'element' -> true, 'type' -> true}])),
		COMPLEX_COMBINATION_SUBLIST -> (new SublistTypeInfo(COMPLEX_COMBINATION_SUBLIST,
												#[new AttributeDefn("sample", true, ListDefinitionTable::SAMPLING_TYPE.makeReference),
												new AttributeDefn("startTime", false, TypeSystemProvider::REAL_TYPE)],
												#[#{'sample' -> true, 'startTime' -> false}]))
	}
	
	static var SublistDefinitionTable anInstance
	
	public static def getInstance(){
		if(anInstance == null){
			anInstance = new SublistDefinitionTable
		}
		anInstance
	}  	
	
	def SublistTypeInfo getSublist(String name){
		return sublistDefns.get(name)
	}

	def getSublistDefns(){
		sublistDefns
	}
}