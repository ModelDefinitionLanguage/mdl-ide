package eu.ddmore.mdl.provider

import java.util.ArrayList
import java.util.Collections
import java.util.List
import java.util.Map
import eu.ddmore.mdllib.mdllib.BlockDefinition
import java.util.HashMap

class BlockListDefinition {
//	extension ListDefinitionProvider ldp = new ListDefinitionProvider

	val String _key
	val Map<String, ListDefInfo> _listDefns
	val ListDefInfo _sglListDefn


	new(BlockDefinition bd){
		_key = bd.keyAttName
		if(bd.listType != null){
			_sglListDefn = new ListDefInfo(bd.listType)
			_listDefns = Collections::emptyMap
		}
		else{
			_sglListDefn = null
			_listDefns = new HashMap<String, ListDefInfo>
			bd.listTypeMappings.forEach[
				_listDefns.put(attDefn.name, new ListDefInfo(attType))
			]
		}
	}
	
//	new(String key, ListDefInfo sdl){
//		_key = key
//		_listDefns = Collections::emptyMap
//		_sglListDefn = sdl
//	}

	def List<ListDefInfo> getListDefns(){
		if(_sglListDefn != null){
			newArrayList(_sglListDefn)
		}
		else
			new ArrayList<ListDefInfo>(this._listDefns.values)
	}
	
	def getKey(){
		_key
	}

	def ListDefInfo getListDefnByValue(String attVal) {
		if(_sglListDefn != null){
			_sglListDefn
		}
		else _listDefns.get(attVal)
	}

}
