package eu.ddmore.mdl.type

import eu.ddmore.mdl.MdlInjectorProvider
import eu.ddmore.mdl.provider.AttributeDefn
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class SubListTypeInfoTest {
	val static TEST_NAME = "Asublist" 
	val static String EXPECTED_TYPE_NAME = "Sublist:" + TEST_NAME
	val static TypeInfoClass EXPECTED_TYPE_CLASS = TypeInfoClass.Sublist
	
	var SublistTypeInfo testInstance
	var SublistTypeInfo equivTestInstance
	
	
	@Before
	def void setUp(){
		testInstance = new SublistTypeInfo(TEST_NAME, 
			#[ new AttributeDefn("att1", false, TypeSystemProvider::REAL_TYPE) ],
			#[ #{ "att1" -> true } ]
		)
		equivTestInstance = new SublistTypeInfo(TEST_NAME,
			#[ new AttributeDefn("att2", false, TypeSystemProvider::REAL_TYPE) ],
			#[ #{ "att2" -> true } ]
		)
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
	def void testIsCompatibleAltType(){
		assertFalse(this.testInstance.isCompatible(TypeSystemProvider::REAL_TYPE))
	}
	
	@Test
	def void testPrimitiveIsCompatibleWithList(){
		assertFalse(TypeSystemProvider::REAL_TYPE.isCompatible(this.testInstance))
	}
	
	@Test
	def void testIsCompatibleWithRefType(){
		assertTrue(this.testInstance.isCompatible(this.testInstance.makeReference))
		assertTrue(this.testInstance.isCompatible(this.equivTestInstance.makeReference))
	}
	
	
}