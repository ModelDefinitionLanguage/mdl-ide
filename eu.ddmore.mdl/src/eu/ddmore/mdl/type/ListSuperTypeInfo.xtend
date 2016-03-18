package eu.ddmore.mdl.type

class ListSuperTypeInfo extends AbstractListTypeInfo{
	
	new(String name){
		super(name)
	}
	
	new(String name, PrimitiveType secondaryType){
		super(name, secondaryType)
	}
	override matchesList(AbstractListTypeInfo other) {
		name == other.listSuperType?.name
	}
	
	override getTypeName() {
		"ListSubtype:" + name
	}
	
	override getListSuperType() {
		this
	}
	
	
}