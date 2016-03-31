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
class GenericEnumTypeInfoTest {
	val static TEST_NAME = GenericEnumTypeInfo::GENERIC_ENUM_TYPE_NAME
	val static String EXPECTED_TYPE_NAME = "Enum:" + TEST_NAME
	val static TypeInfoClass EXPECTED_TYPE_CLASS = TypeInfoClass.Enum
	val static TEST_CATAGS = newHashSet("cat1", "cat2")
	
	var GenericEnumTypeInfo testInstance
	var GenericEnumTypeInfo equivTestInstance
	var EnumTypeInfo enumTestInstance
	
	@Before
	def void setUp(){
		testInstance = new GenericEnumTypeInfo()
		equivTestInstance = new GenericEnumTypeInfo()
		enumTestInstance = new EnumTypeInfo(TEST_NAME, TEST_CATAGS)
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
	def void testIsCompatibleWithEnumListType(){
		assertTrue(this.testInstance.isCompatible(this.testInstance))
	}
	
	@Test
	def void testIsCompatibleWithNull(){
		assertFalse(this.testInstance.isCompatible(null))
	}
	
	@Test
	def void testIsCompatibleWithEnumTypes(){
		assertTrue(this.testInstance.isCompatible(enumTestInstance))
		assertFalse(enumTestInstance.isCompatible(this.testInstance))
	}
	
	@Test
	def void testIsCompatibleWithPrimitveTypes(){
		assertFalse(this.testInstance.isCompatible(TypeSystemProvider::REAL_TYPE))
		assertFalse(this.testInstance.isCompatible(TypeSystemProvider::INT_TYPE))
	}
	
	@Test
	def void testIsCompatibleWithRefType(){
		assertTrue(this.testInstance.isCompatible(this.testInstance.makeReference))
		assertTrue(this.testInstance.isCompatible(this.equivTestInstance.makeReference))
	}
	
	
}