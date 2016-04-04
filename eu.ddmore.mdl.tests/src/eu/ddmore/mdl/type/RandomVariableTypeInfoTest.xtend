package eu.ddmore.mdl.type

import eu.ddmore.mdl.MdlInjectorProvider
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.runner.RunWith
import org.junit.Test
import org.junit.Before
import static org.junit.Assert.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class RandomVariableTypeInfoTest {
	val static String EXPECTED_TYPE_NAME = "RV:Real"
	val static TypeInfoClass EXPECTED_TYPE_CLASS = TypeInfoClass.RandomVariable
	
	var RandomVariableTypeInfo testInstance
	var RandomVariableTypeInfo equivTestInstance
	var RandomVariableTypeInfo diffTestInstance 
	var RandomVariableTypeInfo diffCatTestInstance 
	
	
	@Before
	def void setUp(){
		testInstance = new RandomVariableTypeInfo(TypeSystemProvider::REAL_TYPE)
		equivTestInstance = new RandomVariableTypeInfo(TypeSystemProvider::REAL_TYPE)
		diffTestInstance = new RandomVariableTypeInfo(TypeSystemProvider::INT_TYPE)
		diffCatTestInstance = new RandomVariableTypeInfo(new GeneralCategoryTypeInfo)
	}
	
	@Test
	def void testEqualsSameInstance(){
		assertEquals("same", testInstance, testInstance)
	}

	@Test
	def void testEqualsSameDiffInstance(){
		assertEquals("same", testInstance, this.equivTestInstance)
	}
	
	@Test
	def void testEqualsNotSame(){
		assertNotEquals("not same", testInstance, this.diffTestInstance)
		assertNotEquals("not same", testInstance, this.diffCatTestInstance)
	}
	
	@Test
	def void testNotEqualsNull(){
		assertFalse("not same as null", testInstance == null)
	}
	
	@Test
	def void testHashCodeSameInstance(){
		assertEquals("same", testInstance.hashCode, testInstance.hashCode)
	}

	@Test
	def void testHashCodeSameDiffInstance(){
		assertEquals("same", testInstance.hashCode, this.equivTestInstance.hashCode)
	}
	
	
	@Test
	def void testHashCodeNotSame(){
		assertNotEquals("not same", testInstance.hashCode, this.diffTestInstance.hashCode)
		assertNotEquals("not same", testInstance.hashCode, this.diffCatTestInstance.hashCode)
	}
	
	@Test
	def void testExpectedTypeName(){
		assertEquals(EXPECTED_TYPE_NAME, this.testInstance.typeName)
	}
	
	@Test
	def void testExpectedTypeClass(){
		assertEquals(EXPECTED_TYPE_CLASS, this.testInstance.typeClass)
	}
	
	@Test
	def void testIsCompatible(){
		assertTrue(this.testInstance.isCompatible(this.testInstance))
		assertTrue(this.testInstance.isCompatible(this.equivTestInstance))
		// uses type promotion
		assertTrue(this.testInstance.isCompatible(this.diffTestInstance))
		assertFalse(this.testInstance.isCompatible(this.diffCatTestInstance))
	}
	
	@Test
	def void testIsCompatibleWithNull(){
		assertFalse(this.testInstance.isCompatible(null))
	}
	
	@Test
	def void testIsCompatibleAltType(){
		assertTrue(this.testInstance.isCompatible(TypeSystemProvider::REAL_TYPE))
		assertTrue(this.testInstance.isCompatible(TypeSystemProvider::INT_TYPE))
		assertFalse(this.testInstance.isCompatible(TypeSystemProvider::STRING_TYPE))
		assertFalse(this.testInstance.isCompatible(TypeSystemProvider::BOOLEAN_TYPE))
	}
	
	@Test
	def void testPrimitiveIsCompatibleWith(){
		assertTrue(TypeSystemProvider::REAL_TYPE.isCompatible(this.testInstance))
		assertFalse(TypeSystemProvider::INT_TYPE.isCompatible(this.testInstance))
		assertTrue(TypeSystemProvider::INT_TYPE.isCompatible(this.diffTestInstance))
		assertFalse(TypeSystemProvider::STRING_TYPE.isCompatible(this.testInstance))
		assertFalse(TypeSystemProvider::BOOLEAN_TYPE.isCompatible(this.testInstance))
	}
	
	@Test
	def void testPrimitiveIsCompatibleWithRefs(){
		assertTrue(TypeSystemProvider::REAL_TYPE.isCompatible(this.testInstance.makeReference))
		assertFalse(TypeSystemProvider::INT_TYPE.isCompatible(this.testInstance.makeReference))
		assertTrue(TypeSystemProvider::INT_TYPE.isCompatible(this.diffTestInstance.makeReference))
		assertFalse(TypeSystemProvider::STRING_TYPE.isCompatible(this.testInstance.makeReference))
		assertFalse(TypeSystemProvider::BOOLEAN_TYPE.isCompatible(this.testInstance.makeReference))
	}
	
	@Test
	def void testIsCompatibleWithRefType(){
		assertTrue(this.testInstance.isCompatible(this.testInstance.makeReference))
		assertTrue(this.testInstance.isCompatible(this.equivTestInstance.makeReference))
		assertTrue(this.testInstance.isCompatible(TypeSystemProvider::REAL_TYPE.makeReference))
		assertTrue(this.testInstance.isCompatible(TypeSystemProvider::INT_TYPE.makeReference))
		assertFalse(this.testInstance.isCompatible(TypeSystemProvider::STRING_TYPE.makeReference))
		assertFalse(this.testInstance.isCompatible(TypeSystemProvider::BOOLEAN_TYPE.makeReference))
	}
	
	
}