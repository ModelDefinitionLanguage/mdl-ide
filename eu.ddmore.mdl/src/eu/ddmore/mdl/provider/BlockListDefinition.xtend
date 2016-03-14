package eu.ddmore.mdl.provider

import java.util.ArrayList
import java.util.Collections
import java.util.List
import java.util.Map

class BlockListDefinition {
	val String _key
	val Map<String, ListDefInfo> _listDefns
	val ListDefInfo _sglListDefn


	new(String key, Map<String, ListDefInfo> _ld){
		_key = key
		_listDefns = _ld
		_sglListDefn = null
	}
	
	new(String key, ListDefInfo sdl){
		_key = key
		_listDefns = Collections::emptyMap
		_sglListDefn = sdl
	}

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
