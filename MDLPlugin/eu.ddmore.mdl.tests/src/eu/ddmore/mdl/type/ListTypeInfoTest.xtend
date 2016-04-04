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
class ListTypeInfoTest {
	val static String EXPECTED_TYPE_NAME = "List:A list"
	val static TypeInfoClass EXPECTED_TYPE_CLASS = TypeInfoClass.List
	
	var ListTypeInfo testInstance
	var ListSuperTypeInfo superTestInstance
	var ListTypeInfo equivTestInstance
	var ListTypeInfo equivSuperTestInstance
	var ListTypeInfo equivAltTestInstance 
	
	
	@Before
	def void setUp(){
		testInstance = new ListTypeInfo("A list")
		superTestInstance = new ListSuperTypeInfo("Super list")
		equivTestInstance = new ListTypeInfo("A list")
		equivSuperTestInstance = new ListTypeInfo("A list", this.superTestInstance)
		equivAltTestInstance = new ListTypeInfo("A list", TypeInfoClass.Real)
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
	def void testEqualsSameDiffInstanceDiffAlt(){
		assertEquals("same", testInstance, this.equivAltTestInstance)
	}
	
	@Test
	def void testHashCodeSameDiffInstanceDiffAlt(){
		assertEquals("same", testInstance.hashCode, this.equivAltTestInstance.hashCode)
	}
	
	@Test
	def void testEqualsSameDiffInstanceDiffSuper(){
		assertEquals("same", testInstance, this.equivSuperTestInstance)
	}
	
	@Test
	def void testHashCodeSameDiffInstanceDiffSuper(){
		assertEquals("same", testInstance.hashCode, this.equivSuperTestInstance.hashCode)
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
	def void testMatchesList(){
		assertTrue(this.testInstance.matchesList(this.testInstance))
		assertTrue(this.testInstance.matchesList(this.equivTestInstance))
		assertTrue(this.testInstance.matchesList(this.equivSuperTestInstance))
		assertTrue(this.testInstance.matchesList(this.equivAltTestInstance))
	}
	
	@Test
	def void testMatchesListWithNull(){
		assertFalse(this.testInstance.matchesList(null))
	}
	
	@Test
	def void testMatchesListSuperList(){
		assertTrue(this.superTestInstance.matchesList(this.equivSuperTestInstance))
		assertTrue(this.equivSuperTestInstance.matchesList(this.superTestInstance))
	}
	
	@Test
	def void testIsCompatible(){
		assertTrue(this.testInstance.isCompatible(this.testInstance))
		assertTrue(this.testInstance.isCompatible(this.equivTestInstance))
		assertTrue(this.testInstance.isCompatible(this.equivSuperTestInstance))
		assertTrue(this.testInstance.isCompatible(this.equivAltTestInstance))
	}
	
	@Test
	def void testIsCompatibleWithNull(){
		assertFalse(this.testInstance.isCompatible(null))
	}
	
	@Test
	def void testIsCompatibleSuperList(){
		assertTrue(this.superTestInstance.isCompatible(this.equivSuperTestInstance))
		assertTrue(this.equivSuperTestInstance.isCompatible(this.superTestInstance))
	}
	
	@Test
	def void testIsCompatibleAltType(){
		assertFalse(this.testInstance.isCompatible(TypeSystemProvider::REAL_TYPE))
		assertFalse(this.equivSuperTestInstance.isCompatible(TypeSystemProvider::REAL_TYPE))
		assertTrue(this.equivAltTestInstance.isCompatible(TypeSystemProvider::REAL_TYPE))
		assertTrue(this.equivAltTestInstance.isCompatible(TypeSystemProvider::INT_TYPE))
		assertFalse(this.equivAltTestInstance.isCompatible(TypeSystemProvider::STRING_TYPE))
	}
	
	@Test
	def void testPrimitiveIsCompatibleWithList(){
		assertFalse(TypeSystemProvider::REAL_TYPE.isCompatible(this.testInstance))
		assertFalse(TypeSystemProvider::REAL_TYPE.isCompatible(this.equivSuperTestInstance))
		assertTrue(TypeSystemProvider::REAL_TYPE.isCompatible(this.equivAltTestInstance))
		assertTrue(TypeSystemProvider::REAL_TYPE.isCompatible(this.equivAltTestInstance.makeReference))
		assertFalse(TypeSystemProvider::STRING_TYPE.isCompatible(this.equivAltTestInstance))
		assertFalse(TypeSystemProvider::INT_TYPE.isCompatible(this.equivAltTestInstance))
	}
	
	@Test
	def void testIsCompatibleWithRefType(){
		assertTrue(this.testInstance.isCompatible(this.testInstance.makeReference))
		assertTrue(this.testInstance.isCompatible(this.equivTestInstance.makeReference))
	}
	
	
}