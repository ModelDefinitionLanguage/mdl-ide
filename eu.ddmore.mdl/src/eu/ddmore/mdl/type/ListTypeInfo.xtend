package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.Data

@Data
class ListTypeInfo extends AbstractListTypeInfo{
	val PrimitiveType secondaryType
	val ListSuperTypeInfo superType
	
	new(String name){
		super(name)
		this.secondaryType = PrimitiveType.Undefined
		this.superType = null 
	}
	
	new(String name, PrimitiveType secondaryType){
		super(name)
		this.secondaryType = secondaryType 
		this.superType = null 
	}
	
	new(String name, ListSuperTypeInfo superType){
		super(name)
		this.secondaryType = PrimitiveType.Undefined
		this.superType = superType 
	}
	
	new(String name, PrimitiveType secondaryType, ListSuperTypeInfo superType){
		super(name)
		this.secondaryType = secondaryType 
		this.superType = superType 
	}
	
	override getSecondaryType(){
		secondaryType
	}
	
	override getListSuperType() {
		this.superType
	}
	
	override getTypeName(){
		"List:" + name
	}
	
	override matchesList(AbstractListTypeInfo other) {
		name == other.name
			|| (listSuperType != null
				&& listSuperType == other.listSuperType) 
	}
}

