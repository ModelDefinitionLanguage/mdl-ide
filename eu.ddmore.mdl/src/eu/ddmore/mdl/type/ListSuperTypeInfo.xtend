package eu.ddmore.mdl.type

class ListSuperTypeInfo extends AbstractListTypeInfo{
	
	new(String name){
		super(name)
	}
	
	override matchesList(AbstractListTypeInfo other) {
		name == other.listSuperType?.name
	}
	
	override getSecondaryType() {
		PrimitiveType.Undefined
	}
	
	override getTypeName() {
		"ListSubtype:" + name
	}
	
	override getListSuperType() {
		this
	}
	
	
}