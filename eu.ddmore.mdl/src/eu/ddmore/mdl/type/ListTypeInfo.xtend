package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.Data

@Data
class ListTypeInfo extends AbstractListTypeInfo{
	val ListSuperTypeInfo superType
	
	new(String name){
		this(name, PrimitiveType.Undefined, null)
	}
	
	new(String name, PrimitiveType secondaryType){
		this(name, secondaryType, null)
	}
	
	new(String name, TypeInfo superType){
		this(name, PrimitiveType.Undefined, superType)
	}
	
	new(String name, PrimitiveType secondaryType, TypeInfo superType){
		super(name, secondaryType)
		if(superType != null){
			if(superType instanceof ListSuperTypeInfo){
				this.superType = superType
			}
			else{
				this.superType = null
				throw new IllegalArgumentException("Expect list supertype")
			}
		}
		else this.superType = null
	}
	
//	override getSecondaryType(){
//		secondaryType
//	}
	
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

