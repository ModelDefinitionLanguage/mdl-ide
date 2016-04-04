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
class CategoryListTypeInfoTest {
	val static TEST_NAME = "foo"
	val static TEST_ENUM_NAME = "foofoo"
	val static TEST_CATAGS = newHashSet("cat1", "cat2")
	val static TEST_ALT_CATAGS = newHashSet("cat11", "cat12")
	val static String EXPECTED_TYPE_NAME = "List:" + TEST_NAME
	val static TypeInfoClass EXPECTED_TYPE_CLASS = TypeInfoClass.List
	
	var CategoryListTypeInfo testInstance
	var CategoryListTypeInfo equivTestInstance
	var CategoryListTypeInfo equivDiffCatsTestInstance
	var CategoryListTypeInfo nonEquivTestInstance
	var CategoryTypeInfo equivEnumInstance
	var CategoryTypeInfo nonEquivEnumInstance
	var CategoryTypeInfo testUnderlyingEnum
	
	@Before
	def void setUp(){
		this.testUnderlyingEnum = new CategoryTypeInfo(TEST_ENUM_NAME, TEST_CATAGS)
		testInstance = new CategoryListTypeInfo(TEST_NAME, testUnderlyingEnum)
		equivTestInstance = new CategoryListTypeInfo(TEST_NAME, new CategoryTypeInfo(TEST_ENUM_NAME, TEST_CATAGS))
		this.nonEquivTestInstance = new CategoryListTypeInfo("bar", new CategoryTypeInfo(TEST_ENUM_NAME, TEST_CATAGS))
		equivDiffCatsTestInstance = new CategoryListTypeInfo(TEST_NAME)
		equivEnumInstance = new CategoryTypeInfo(TEST_ENUM_NAME, TEST_CATAGS)
		nonEquivEnumInstance = new CategoryTypeInfo("AltTestEnumList", TEST_ALT_CATAGS)
	}
	
	@Test
	def void testEqualsSameInstance(){
		assertEquals("same", testInstance, testInstance)
	}

	@Test
	def void testEqualsSameDiffInstance(){
		assertEquals("same", testInstance, this.equivTestInstance)
		assertNotEquals("not same", testInstance, this.nonEquivTestInstance)
		assertEquals("same", testInstance, this.equivDiffCatsTestInstance)
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
		assertNotEquals("not same", testInstance.hashCode, this.nonEquivTestInstance.hashCode)
		assertEquals("same", testInstance.hashCode, this.equivDiffCatsTestInstance.hashCode)
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
	def void testExpectedCategories(){
		assertEquals(testUnderlyingEnum, this.testInstance.underlyingEnum)
	}
	@Test
	def void testIsCompatible(){
		assertTrue(this.testInstance.isCompatible(this.testInstance))
		assertTrue(this.testInstance.isCompatible(this.equivTestInstance))
	}
	
	@Test
	def void testIsCompatibleWithEnumListType(){
		assertTrue(this.testInstance.isCompatible(this.testInstance))
		assertTrue(this.testInstance.isCompatible(this.equivEnumInstance))
		assertFalse(this.testInstance.isCompatible(this.nonEquivEnumInstance))
	}
	
	@Test
	def void testIsCompatibleWithNull(){
		assertFalse(this.testInstance.isCompatible(null))
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