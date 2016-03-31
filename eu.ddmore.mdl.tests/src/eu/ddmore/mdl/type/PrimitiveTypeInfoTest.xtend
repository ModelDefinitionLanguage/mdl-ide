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
class PrimitiveTypeInfoTest {
	val static String EXPECTED_TYPE_NAME = "Pmf"
	val static TypeInfoClass EXPECTED_TYPE_CLASS = TypeInfoClass.Pmf
	var PrimitiveTypeInfo testInstance
	var PrimitiveTypeInfo equivTestInstance
	
	
	@Before
	def void setUp(){
		testInstance = new PrimitiveTypeInfo(EXPECTED_TYPE_CLASS)
		equivTestInstance = new PrimitiveTypeInfo(EXPECTED_TYPE_CLASS)
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
	}
	
	@Test
	def void testIsCompatibleWithNull(){
		assertFalse(this.testInstance.isCompatible(null))
	}
	
	@Test
	def void testIsCompatibleWithOtherPrimitiveTypes(){
		assertTrue(this.testInstance.isCompatible(this.testInstance))
		assertTrue(this.testInstance.isCompatible(this.equivTestInstance))
		assertFalse(this.testInstance.isCompatible(TypeSystemProvider::REAL_TYPE))
		assertFalse(this.testInstance.isCompatible(TypeSystemProvider::DERIV_TYPE))
		assertFalse(this.testInstance.isCompatible(TypeSystemProvider::INT_TYPE))
		assertFalse(this.testInstance.isCompatible(TypeSystemProvider::STRING_TYPE))
		assertFalse(this.testInstance.isCompatible(TypeSystemProvider::PDF_TYPE))
		assertFalse(this.testInstance.isCompatible(TypeSystemProvider::BOOLEAN_TYPE))
		assertFalse(this.testInstance.isCompatible(TypeSystemProvider::UNDEFINED_TYPE))
	}
	
	@Test
	def void testPrimitiveIsCompatibleReciprocal(){
		assertTrue(this.equivTestInstance.isCompatible(this.testInstance))
		assertFalse(TypeSystemProvider::REAL_TYPE.isCompatible(this.testInstance))
		assertFalse(TypeSystemProvider::DERIV_TYPE.isCompatible(this.testInstance))
		assertFalse(TypeSystemProvider::INT_TYPE.isCompatible(this.testInstance))
		assertFalse(TypeSystemProvider::STRING_TYPE.isCompatible(this.testInstance))
		assertFalse(TypeSystemProvider::BOOLEAN_TYPE.isCompatible(this.testInstance))
		assertFalse(TypeSystemProvider::PDF_TYPE.isCompatible(this.testInstance))
		assertFalse(TypeSystemProvider::UNDEFINED_TYPE.isCompatible(this.testInstance))
	}
	
	@Test
	def void testIsCompatibleWithRefType(){
		assertTrue(this.testInstance.isCompatible(this.testInstance.makeReference))
		assertTrue(this.testInstance.isCompatible(this.equivTestInstance.makeReference))
	}
	
	
}