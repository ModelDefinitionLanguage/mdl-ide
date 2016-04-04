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
class PrimitiveTypeInfoTypePromotionTest {
	val static String EXPECTED_TYPE_NAME = "Real"
	val static TypeInfoClass EXPECTED_TYPE_CLASS = TypeInfoClass.Real
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
	def void testIsCompatibleWithUnderlyingTypes(){
		assertTrue(this.testInstance.isCompatible(TypeSystemProvider::REAL_TYPE))
		assertTrue(this.testInstance.isCompatible(TypeSystemProvider::INT_TYPE))
	}
	
	@Test
	def void testPrimitiveIsCompatibleWithList(){
		assertTrue(TypeSystemProvider::REAL_TYPE.isCompatible(this.testInstance))
		assertFalse(TypeSystemProvider::INT_TYPE.isCompatible(this.testInstance))
	}
	
	@Test
	def void testIsCompatibleWithRefType(){
		assertTrue(this.testInstance.isCompatible(this.testInstance.makeReference))
		assertTrue(this.testInstance.isCompatible(this.equivTestInstance.makeReference))
	}
	
	
}