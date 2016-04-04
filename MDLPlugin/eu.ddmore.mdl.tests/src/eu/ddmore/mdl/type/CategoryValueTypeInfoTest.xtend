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
class CategoryValueTypeInfoTest {
	val static TEST_NAME = "foo"
	val static TEST_CAT_VAL = "cat1"
	val static ALT_TEST_CAT_VAL = "cat2"
	val static TEST_CATAGS = newHashSet(TEST_CAT_VAL, ALT_TEST_CAT_VAL)
//	val static TEST_ALT_CATAGS = newHashSet("cat11", "cat12")
	val static String EXPECTED_TYPE_NAME = "Category:" + TEST_NAME + ":" + TEST_CAT_VAL
	val static TypeInfoClass EXPECTED_TYPE_CLASS = TypeInfoClass.CategoryValue
	
	var CategoryTypeInfo testOwningCatTypeInstance
	var CategoryValueTypeInfo testInstance
	var CategoryValueTypeInfo equivTestInstance
	var CategoryValueTypeInfo equivDiffCatsTestInstance
	var CategoryValueTypeInfo nonEquivTestInstance
	
	@Before
	def void setUp(){
		this.testOwningCatTypeInstance = new CategoryTypeInfo(TEST_NAME, TEST_CATAGS)
		testInstance = new CategoryValueTypeInfo(this.testOwningCatTypeInstance, TEST_CAT_VAL)
		equivTestInstance = new CategoryValueTypeInfo(this.testOwningCatTypeInstance, TEST_CAT_VAL)
		this.nonEquivTestInstance = new CategoryValueTypeInfo(new CategoryTypeInfo("other", TEST_CATAGS), TEST_CAT_VAL)
		equivDiffCatsTestInstance = new CategoryValueTypeInfo(this.testOwningCatTypeInstance, ALT_TEST_CAT_VAL)
	}
	
	@Test
	def void testEqualsSameInstance(){
		assertEquals("same", testInstance, testInstance)
	}

	@Test
	def void testEqualsSameDiffInstance(){
		assertEquals("same", testInstance, this.equivTestInstance)
		assertNotEquals("not same", testInstance, this.nonEquivTestInstance)
		assertNotEquals("not same", testInstance, this.equivDiffCatsTestInstance)
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
		assertNotEquals("same", testInstance.hashCode, this.equivDiffCatsTestInstance.hashCode)
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
	def void testIsCompatibleWithCategoryType(){
		assertFalse(this.testInstance.isCompatible(this.testOwningCatTypeInstance))
		assertFalse(this.testOwningCatTypeInstance.isCompatible(this.testInstance))
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