package eu.ddmore.mdl.type

class ListTypeInfo extends AbstractListTypeInfo{
	val ListSuperTypeInfo superType
	
	new(String name){
		this(name, TypeInfoClass.Undefined, null)
	}
	
	new(String name, TypeInfoClass secondaryType){
		this(name, secondaryType, null)
	}
	
	new(String name, TypeInfo superType){
		this(name, TypeInfoClass.Undefined, superType)
	}
	
	new(String name, TypeInfoClass secondaryType, TypeInfo superType){
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
	
	def getSuperType(){
		this.superType
	}
	
	override getListSuperType() {
		this.superType
	}
	
	override getTypeName(){
		"List:" + name
	}
	
	override matchesList(AbstractListTypeInfo other) {
		other != null
		 	&& (name == other.name
				|| (listSuperType != null
					&& listSuperType == other.listSuperType)) 
	}
}

