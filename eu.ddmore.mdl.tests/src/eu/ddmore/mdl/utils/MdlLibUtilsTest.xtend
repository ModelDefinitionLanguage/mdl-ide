package eu.ddmore.mdl.utils

import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdllib.mdllib.MdlLibFactory
import eu.ddmore.mdllib.mdllib.TypeClass
import eu.ddmore.mdllib.mdllib.TypeSpec
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.assertEquals
import eu.ddmore.mdl.type.RandomVariableTypeInfo

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MdlLibUtilsTest {

	extension MdlLibUtils mlu = new MdlLibUtils

	private def createRealTypeSpec(){
		val spec = MdlLibFactory.eINSTANCE.createTypeSpec
		val realTypeDefn = MdlLibFactory.eINSTANCE.createTypeDefinition
		realTypeDefn.name = 'Real' 
		realTypeDefn.typeClass = TypeClass.REAL
		spec.typeName = realTypeDefn
		spec
	}


	private def createIntTypeSpec(){
		val spec = MdlLibFactory.eINSTANCE.createTypeSpec
		val realTypeDefn = MdlLibFactory.eINSTANCE.createTypeDefinition
		realTypeDefn.name = 'Int' 
		realTypeDefn.typeClass = TypeClass.INT
		spec.typeName = realTypeDefn
		spec
	}

	private def createVectorTypeSpec(TypeSpec elementSpec){
		val spec = MdlLibFactory.eINSTANCE.createTypeSpec
		val typeDefn = MdlLibFactory.eINSTANCE.createTypeDefinition
		typeDefn.name = 'Vector' 
		typeDefn.typeClass = TypeClass.VECTOR
		spec.typeName = typeDefn
		if(elementSpec != null){
			spec.elementType =  elementSpec
		}
		spec
	}

	private def createReferenceTypeSpec(TypeSpec elementSpec){
		val spec = MdlLibFactory.eINSTANCE.createTypeSpec
		val typeDefn = MdlLibFactory.eINSTANCE.createTypeDefinition
		typeDefn.name = 'Reference' 
		typeDefn.typeClass = TypeClass.REFERENCE
		spec.typeName = typeDefn
		if(elementSpec != null){
			spec.elementType =  elementSpec
		}
		spec
	}

	private def createRandomVariableTypeSpec(TypeSpec elementSpec){
		val spec = MdlLibFactory.eINSTANCE.createTypeSpec
		val typeDefn = MdlLibFactory.eINSTANCE.createTypeDefinition
		typeDefn.name = 'RandomVariable' 
		typeDefn.typeClass = TypeClass.RV
		spec.typeName = typeDefn
		if(elementSpec != null){
			spec.elementType =  elementSpec
		}
		spec
	}

	private def createMatrixTypeSpec(TypeSpec cellSpec){
		val spec = MdlLibFactory.eINSTANCE.createTypeSpec
		val typeDefn = MdlLibFactory.eINSTANCE.createTypeDefinition
		typeDefn.name = 'Matrix' 
		typeDefn.typeClass = TypeClass.MATRIX
		spec.typeName = typeDefn
		if(cellSpec != null){
			spec.cellType =  cellSpec
		}
		spec
	}

	private def createStringTypeSpec(){
		val spec = MdlLibFactory.eINSTANCE.createTypeSpec
		val realTypeDefn = MdlLibFactory.eINSTANCE.createTypeDefinition
		realTypeDefn.name = 'String' 
		realTypeDefn.typeClass = TypeClass.STRING
		spec.typeName = realTypeDefn
		spec
	}


	@Test
	def void testRealType(){
		val spec = createRealTypeSpec
		val actualTypeInfo = spec.typeInfo
		assertEquals("expectedType", TypeSystemProvider::REAL_TYPE, actualTypeInfo)
	}

	@Test
	def void testIntType(){
		val actualTypeInfo = createIntTypeSpec.typeInfo
		assertEquals("expectedType", TypeSystemProvider::INT_TYPE, actualTypeInfo)
	}

	@Test
	def void testStringType(){
		val actualTypeInfo = createStringTypeSpec.typeInfo
		assertEquals("expectedType", TypeSystemProvider::STRING_TYPE, actualTypeInfo)
	}

	
	@Test
	def void testBooleanType(){
		val spec = MdlLibFactory.eINSTANCE.createTypeSpec
		val realTypeDefn = MdlLibFactory.eINSTANCE.createTypeDefinition
		realTypeDefn.name = 'Boolean' 
		realTypeDefn.typeClass = TypeClass.BOOLEAN
		spec.typeName = realTypeDefn
		val actualTypeInfo = spec.typeInfo
		assertEquals("expectedType", TypeSystemProvider::BOOLEAN_TYPE, actualTypeInfo)
	}
	
	@Test
	def void testVectorDefaultType(){
		val actualTypeInfo = createVectorTypeSpec(null).typeInfo
		assertEquals("expectedType", TypeSystemProvider::REAL_VECTOR_TYPE, actualTypeInfo)
	}

	@Test
	def void testVectorRealType(){
		val actualTypeInfo = createVectorTypeSpec(createRealTypeSpec).typeInfo
		assertEquals("expectedType", TypeSystemProvider::REAL_VECTOR_TYPE, actualTypeInfo)
	}

	@Test
	def void testVectorIntType(){
		val actualTypeInfo = createVectorTypeSpec(createIntTypeSpec).typeInfo
		assertEquals("expectedType", TypeSystemProvider::INT_VECTOR_TYPE, actualTypeInfo)
	}

	@Test
	def void testVectorStringType(){
		val actualTypeInfo = createVectorTypeSpec(createStringTypeSpec).typeInfo
		assertEquals("expectedType", TypeSystemProvider::STRING_TYPE.makeVector, actualTypeInfo)
	}


	@Test
	def void testVectorOfVectorDefaultType(){
		val actualTypeInfo = createVectorTypeSpec(createVectorTypeSpec(null)).typeInfo
		assertEquals("expectedType", TypeSystemProvider::REAL_TYPE.makeVector.makeVector, actualTypeInfo)
	}

	@Test
	def void testVectorOfVectorRealType(){
		val actualTypeInfo = createVectorTypeSpec(createVectorTypeSpec(createRealTypeSpec)).typeInfo
		assertEquals("expectedType", TypeSystemProvider::REAL_TYPE.makeVector.makeVector, actualTypeInfo)
	}

	@Test
	def void testVectorOfVectorStringType(){
		val actualTypeInfo = createVectorTypeSpec(createVectorTypeSpec(createStringTypeSpec)).typeInfo
		assertEquals("expectedType", TypeSystemProvider::STRING_TYPE.makeVector.makeVector, actualTypeInfo)
	}

	@Test
	def void testMatrixDefaultType(){
		val actualTypeInfo = createMatrixTypeSpec(null).typeInfo
		assertEquals("expectedType", TypeSystemProvider::REAL_MATRIX_TYPE, actualTypeInfo)
	}

	@Test
	def void testMatrixRealType(){
		val actualTypeInfo = createMatrixTypeSpec(createRealTypeSpec).typeInfo
		assertEquals("expectedType", TypeSystemProvider::REAL_MATRIX_TYPE, actualTypeInfo)
	}

	@Test
	def void testMatrixIntType(){
		val actualTypeInfo = createMatrixTypeSpec(createIntTypeSpec).typeInfo
		assertEquals("expectedType", TypeSystemProvider::INT_TYPE.makeMatrix, actualTypeInfo)
	}

	@Test
	def void testMatrixStringType(){
		val actualTypeInfo = createMatrixTypeSpec(createStringTypeSpec).typeInfo
		assertEquals("expectedType", TypeSystemProvider::STRING_TYPE.makeMatrix, actualTypeInfo)
	}

	@Test
	def void testMatrixOfVectorStringType(){
		val actualTypeInfo = createMatrixTypeSpec(createVectorTypeSpec(createStringTypeSpec)).typeInfo
		assertEquals("expectedType", TypeSystemProvider::STRING_TYPE.makeVector.makeMatrix, actualTypeInfo)
	}

	@Test
	def void testMatrixOfMatrixStringType(){
		val actualTypeInfo = createMatrixTypeSpec(createMatrixTypeSpec(createStringTypeSpec)).typeInfo
		assertEquals("expectedType", TypeSystemProvider::STRING_TYPE.makeMatrix.makeMatrix, actualTypeInfo)
	}

	@Test
	def void testReferenceType(){
		val actualTypeInfo = createReferenceTypeSpec(createStringTypeSpec).typeInfo
		assertEquals("expectedType", TypeSystemProvider::STRING_TYPE.makeReference, actualTypeInfo)
	}

	@Test
	def void testReferenceDefaultType(){
		val actualTypeInfo = createReferenceTypeSpec(null).typeInfo
		assertEquals("expectedType", TypeSystemProvider::REAL_TYPE.makeReference, actualTypeInfo)
	}

	@Test
	def void testRandomVariableRealType(){
		val actualTypeInfo = createRandomVariableTypeSpec(createRealTypeSpec).typeInfo
		assertEquals("expectedType", new RandomVariableTypeInfo(TypeSystemProvider::REAL_TYPE), actualTypeInfo)
	}

	
	@Test
	def void testRandomVariableIntType(){
		val actualTypeInfo = createRandomVariableTypeSpec(createIntTypeSpec).typeInfo
		assertEquals("expectedType", new RandomVariableTypeInfo(TypeSystemProvider::INT_TYPE), actualTypeInfo)
	}

	@Test
	def void testRandomVariableDefaultType(){
		val actualTypeInfo = createRandomVariableTypeSpec(null).typeInfo
		assertEquals("expectedType", new RandomVariableTypeInfo(TypeSystemProvider::REAL_TYPE), actualTypeInfo)
	}

}