package eu.ddmore.mdl.type

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.MdlFactory
import eu.ddmore.mdl.validation.TypeSystemValidator
import eu.ddmore.mdllib.MdlLibInjectorProvider
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.Library
import eu.ddmore.mdllib.mdllib.ListTypeDefinition
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.junit.Assert.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlLibInjectorProvider))
class TypeSystemProviderTest {
	@Inject extension ParseHelper<Library>
	@Inject extension ValidationTestHelper

	val COMP_LIST_TYPE = new ListTypeInfo("Compartment", PrimitiveType.Real)

	var incompatible = false
	var Library testLibraryFixture
	
	extension TypeSystemProvider th = new TypeSystemProvider 
	extension TypeSystemValidator tsv = new TypeSystemValidator



	@Before
	def void setUp(){
		incompatible = false
		testLibraryFixture = '''
			_type Real _real;
			_type Int _int;
			_type cmtType _enum (depot, compartment, elimination, transfer, distribution, direct, effect);
			
			_list Compartment _alt Real
					_atts  type::cmtType, modelCmt::Int
					_sig (type, modelCmt?);
					
			_object mdlObj;
			
			_block COMPARTMENT (,) _statements (,) _listDefn, _anonList
				_list _key=type cmtType.compartment->Compartment;

			_container mdlObj _has COMPARTMENT;
						
			_func ln (x::Real) _returns ::Real;
		'''.parse
	}

	def createBlockDefinition(String blkName){
		testLibraryFixture.blockDefns.findFirst[
			name == blkName
		]
	}


	def createListDefinition(String lstName){
		testLibraryFixture.typeDefns.findFirst[
			if(it instanceof ListTypeDefinition){
				name == lstName
			}
			else false
		]
	}

	@Test
	def void testFixtureCorrect(){
		testLibraryFixture.assertNoErrors
	}

	@Test
	def void testTypeExpectedFuncCallExpression(){
		val funcCall = MdlFactory::eINSTANCE.createSymbolReference
		funcCall.ref = MdlFactory::eINSTANCE.createEquationDefinition
		funcCall.ref.name = 'ln'
		funcCall.typeFor.assertEquals(TypeSystemProvider::REAL_TYPE.makeReference)
	}

	@Ignore("This test is no longer relevant as the function call is now a valid symb ref an it will have a type.")
	def void testTypeUnknownFuncCallExpression(){
//		val funcCall = MdlFactory::eINSTANCE.createBuiltinFunctionCall
//		funcCall.func = 'foobar'
		val funcCall = MdlFactory::eINSTANCE.createSymbolReference
		funcCall.ref = MdlFactory::eINSTANCE.createEquationDefinition
		funcCall.ref.name = 'foobar'
		TypeSystemProvider::UNDEFINED_TYPE.assertEquals(funcCall.typeFor)
	}

	@Ignore("Not a meaningful test singe using external library.")
	def void testTypeDistributionFuncCallExpression(){
//		val funcCall = MdlFactory::eINSTANCE.createBuiltinFunctionCall
//		funcCall.func = 'Normal'
		val funcCall = MdlFactory::eINSTANCE.createSymbolReference
		funcCall.ref = MdlFactory::eINSTANCE.createEquationDefinition
		funcCall.ref.name = 'Normal'
		TypeSystemProvider::PDF_TYPE.assertEquals(funcCall.typeFor)
	}

	@Test
	def void testStringLiteralExpression(){
		val actual = MdlFactory::eINSTANCE.createStringLiteral
		actual.value = 'foobar'
		actual.typeFor.assertEquals(TypeSystemProvider::STRING_TYPE)
	}

	@Test
	def void testRealLiteralExpression(){
		val actual = MdlFactory::eINSTANCE.createRealLiteral
		actual.value = 1.0
		actual.typeFor.assertEquals(TypeSystemProvider::REAL_TYPE)
	}

	@Test
	def void testIntegerLiteralExpression(){
		val actual = MdlFactory::eINSTANCE.createIntegerLiteral
		actual.value = 1
		actual.typeFor.assertEquals(TypeSystemProvider::INT_TYPE)
	}

	@Test
	def void testBooleanLiteralExpression(){
		val actual = MdlFactory::eINSTANCE.createBooleanLiteral
		actual.isTrue = true
		actual.typeFor.assertEquals(TypeSystemProvider::BOOL_TYPE)
	}

	@Test
	def void testVectorLiteralExpression(){
		val actual = MdlFactory::eINSTANCE.createVectorLiteral
		actual.typeFor.assertEquals(TypeSystemProvider::REAL_VECTOR_TYPE)
	}

	@Test
	def void testRelationalOp(){
		val Expression actual = MdlFactory::eINSTANCE.createRealLiteral
		val Expression rhs = MdlFactory::eINSTANCE.createRealLiteral
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		actual.checkRelationalOp(rhs, errorFunc, errorFunc)
	}

	def createDummyEnumRef(String enumType, String enumValue){
		val actual = MdlFactory::eINSTANCE.createCategoryValueReference
		val lhsDefn = MdlFactory::eINSTANCE.createCategoryValueDefinition
		val enumDefn = MdlFactory::eINSTANCE.createEnumerationDefinition
		enumDefn.name = enumType
		val catDefnExpr = MdlFactory::eINSTANCE.createCategoricalDefinitionExpr
		catDefnExpr.categories.add(lhsDefn)
		enumDefn.catDefn = catDefnExpr
		lhsDefn.name = enumValue
		actual.ref = lhsDefn 
		actual
	}

	@Test
	def void testRelationalOpWithEnums(){
		val actual = createDummyEnumRef("tst", "tst1")
		val rhs = createDummyEnumRef("tst", "tst1") MdlFactory::eINSTANCE.createCategoryValueDefinition
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		actual.checkRelationalOp(rhs, errorFunc, errorFunc)
	}

	@Test
	def void testRelationalOpWithIncompatibleEnums(){
		val actual = createDummyEnumRef("tst", "tst1")
		val rhs = createDummyEnumRef("tst", "tst2") MdlFactory::eINSTANCE.createCategoryValueDefinition
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		val (TypeInfo, TypeInfo) => void failingErrorFunc = [e, a| e.assertEquals(new EnumTypeInfo("tst", #{"tst1"}).makeReference) a.assertEquals(new EnumTypeInfo("tst", #{"tst2"}).makeReference)]
		actual.checkRelationalOp(rhs, errorFunc, failingErrorFunc)
	}

	@Test
	def void testRelationalOpWithNumAndEnum(){
		val Expression actual = MdlFactory::eINSTANCE.createRealLiteral
		val rhs = createDummyEnumRef("tst", "tst2") MdlFactory::eINSTANCE.createCategoryValueDefinition
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		val (TypeInfo, TypeInfo) => void failingErrorFunc = [e, a| e.assertEquals(TypeSystemProvider::REAL_TYPE) a.assertEquals(new EnumTypeInfo("tst", #{"tst2"}).makeReference)]
		actual.checkRelationalOp(rhs, errorFunc, failingErrorFunc)
	}

	@Test
	def void testRelationalOpWithEnumandNum(){
		val actual = createDummyEnumRef("tst", "tst1")
		val rhs = MdlFactory::eINSTANCE.createRealLiteral
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		val (TypeInfo, TypeInfo) => void failingErrorFunc = [e, a| e.assertEquals(new EnumTypeInfo("tst", #{"tst1"}).makeReference) a.assertEquals(TypeSystemProvider::REAL_TYPE)]
		actual.checkRelationalOp(rhs, errorFunc, failingErrorFunc)
	}

	@Test
	def void testRelationalOpWithNullRhs(){
		val Expression actual = MdlFactory::eINSTANCE.createRealLiteral
		val Expression rhs = null
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| e.assertEquals(TypeSystemProvider::REAL_TYPE) a.assertEquals(TypeSystemProvider::UNDEFINED_TYPE)]
		val (TypeInfo, TypeInfo) => void failingErrorFunc = [e, a| a.assertEquals(TypeSystemProvider::UNDEFINED_TYPE)]
		actual.checkRelationalOp(rhs, errorFunc, failingErrorFunc)
	}

	@Test
	def void testMathsOp(){
		val Expression actual = MdlFactory::eINSTANCE.createRealLiteral
		val Expression rhs = MdlFactory::eINSTANCE.createRealLiteral
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		actual.checkMathsOp(rhs, errorFunc, errorFunc)
	}

	@Test
	def void testMathsOpReciprocalRef(){
		val value = MdlFactory::eINSTANCE.createRealLiteral
		val ref = MdlFactory::eINSTANCE.createSymbolReference
		val defn = MdlFactory::eINSTANCE.createEquationDefinition
		defn.name = "AReal"
		ref.ref = defn
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		value.checkMathsOp(ref, errorFunc, errorFunc)
		ref.checkMathsOp(value, errorFunc, errorFunc)
	}

	@Test
	def void testRefWithRealExpressionCompatible(){
		val value = MdlFactory::eINSTANCE.createRealLiteral
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		checkExpectedAndExpression(TypeSystemProvider::REAL_TYPE.makeReference, value, errorFunc)
	}

	@Test
	def void testRefWithRealRef(){
		val ref = MdlFactory::eINSTANCE.createSymbolReference
		val defn = MdlFactory::eINSTANCE.createEquationDefinition
		defn.name = "AReal"
		ref.ref = defn
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		checkExpectedAndExpression(TypeSystemProvider::REAL_TYPE.makeReference, ref, errorFunc)				
	}

	@Test
	def void testRefWithIntRef(){
		val ref = MdlFactory::eINSTANCE.createSymbolReference
		val defn = MdlFactory::eINSTANCE.createEquationDefinition
		defn.name = "AReal"
		val intLit = MdlFactory::eINSTANCE.createIntegerLiteral
		intLit.value = 1 
		defn.expression = intLit
		ref.ref = defn
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		checkExpectedAndExpression(TypeSystemProvider::REAL_TYPE.makeReference, ref, errorFunc)				
	}

	@Test
	def void testRealWithRef(){
		val ref = MdlFactory::eINSTANCE.createSymbolReference
		val defn = MdlFactory::eINSTANCE.createEquationDefinition
		defn.name = "AReal"
		ref.ref = defn
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		checkExpectedAndExpression(TypeSystemProvider::REAL_TYPE, ref, errorFunc)				
	}

	@Test
	def void testCompRefWithRealCompatible(){
		val testType = new ListTypeInfo("testType", PrimitiveType.Real)
		val value = MdlFactory::eINSTANCE.createRealLiteral
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		checkExpectedAndExpression(testType.makeReference, value, errorFunc)				
	}

	@Test
	def void testCompRefWithRealRefCompatible(){
		val testType = new ListTypeInfo("testType", PrimitiveType.Real)
		val ref = MdlFactory::eINSTANCE.createSymbolReference
		val defn = MdlFactory::eINSTANCE.createEquationDefinition
		defn.name = "AReal"
		ref.ref = defn
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		checkExpectedAndExpression(testType.makeReference, ref, errorFunc)				
	}

	@Test
	def void testCompatibleRealWithCompRef(){
		val ref = MdlFactory::eINSTANCE.createSymbolReference
		val defn = MdlFactory::eINSTANCE.createListDefinition
		defn.name = "AList"
		val attList = MdlFactory::eINSTANCE.createAttributeList 
		attList.attributes.add(MdlFactory::eINSTANCE.createEnumPair => [argumentName = "type"
									expression = buildEnum("compartment")
									])
		defn.list = attList 
		val blk = MdlFactory::eINSTANCE.createBlockStatement
//		val blkDefn = MdlLibFactory.eINSTANCE.createBlockDefinition
//		blkDefn.name = "COMPARTMENT"
		blk.blkId = createBlockDefinition("COMPARTMENT")
		val bdy = MdlFactory::eINSTANCE.createBlockStatementBody
		bdy.statements.add(defn)
		blk.body = bdy
		ref.ref = defn
		
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		checkExpectedAndExpression(TypeSystemProvider::REAL_TYPE, ref, errorFunc)				
	}

	def create result: MdlFactory::eINSTANCE.createEnumExpression buildEnum(String enumVal){
		result.enumValue = enumVal
	}


	@Test
	def void testArgumentRefWithRealIncompatible(){
		val value = MdlFactory::eINSTANCE.createRealLiteral
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a|  incompatible = true ]
		checkArgumentMatchesAndExpression(TypeSystemProvider::REAL_TYPE.makeReference, value, errorFunc)
		assertTrue("Incompatible", incompatible)
	}

	@Test
	def void testArgumentRefWithRealRef(){
		val ref = MdlFactory::eINSTANCE.createSymbolReference
		val defn = MdlFactory::eINSTANCE.createEquationDefinition
		defn.name = "AReal"
		ref.ref = defn
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		checkArgumentMatchesAndExpression(TypeSystemProvider::REAL_TYPE.makeReference, ref, errorFunc)				
	}

	@Test
	def void testArgumentRefWithIntRef(){
		val ref = MdlFactory::eINSTANCE.createSymbolReference
		val defn = MdlFactory::eINSTANCE.createEquationDefinition
		defn.name = "AReal"
		val intLit = MdlFactory::eINSTANCE.createIntegerLiteral
		intLit.value = 1 
		defn.expression = intLit
		ref.ref = defn
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		checkArgumentMatchesAndExpression(TypeSystemProvider::REAL_TYPE.makeReference, ref, errorFunc)				
	}

	@Test
	def void testArgumentRealWithRef(){
		val ref = MdlFactory::eINSTANCE.createSymbolReference
		val defn = MdlFactory::eINSTANCE.createEquationDefinition
		defn.name = "AReal"
		ref.ref = defn
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		checkArgumentMatchesAndExpression(TypeSystemProvider::REAL_TYPE, ref, errorFunc)				
	}

	@Test
	def void testArgumentCompRefWithRealIncompatible(){
		val value = MdlFactory::eINSTANCE.createRealLiteral
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| incompatible = true]
		checkArgumentMatchesAndExpression(COMP_LIST_TYPE.makeReference, value, errorFunc)				
		assertTrue("Incompatible", incompatible)
	}

	@Test
	def void testArgumentCompRefWithRealRefIncompatible(){
		val ref = MdlFactory::eINSTANCE.createSymbolReference
		val defn = MdlFactory::eINSTANCE.createEquationDefinition
		defn.name = "AReal"
		ref.ref = defn
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| incompatible = true]
		checkArgumentMatchesAndExpression(COMP_LIST_TYPE.makeReference, ref, errorFunc)				
		assertTrue("Incompatible", incompatible)
	}

	@Test
	def void testArgumentCompatibleRealWithCompRef(){
		val ref = MdlFactory::eINSTANCE.createSymbolReference
		val defn = MdlFactory::eINSTANCE.createListDefinition
		defn.name = "AList"
		val attList = MdlFactory::eINSTANCE.createAttributeList
		attList.attributes.add(MdlFactory::eINSTANCE.createEnumPair => [argumentName = "type"
									expression = buildEnum("compartment")
									])
		defn.list = attList
		val blk = MdlFactory::eINSTANCE.createBlockStatement
		val bdy = MdlFactory::eINSTANCE.createBlockStatementBody
		bdy.statements.add(defn)
		blk.blkId = createBlockDefinition("COMPARTMENT")
		blk.body = bdy
		ref.ref = defn
		
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		checkArgumentMatchesAndExpression(TypeSystemProvider::REAL_TYPE, ref, errorFunc)				
	}

	@Test
	def void testMathsOpWithString(){
		val Expression actual = MdlFactory::eINSTANCE.createRealLiteral
		val Expression rhs = MdlFactory::eINSTANCE.createStringLiteral
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| a.assertEquals(TypeSystemProvider::STRING_TYPE)]
		actual.checkMathsOp(rhs, errorFunc, errorFunc)
	}

	@Test
	def void testMathsOpWithNullRhs(){
		val Expression actual = MdlFactory::eINSTANCE.createRealLiteral
		val Expression rhs = null
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		val (TypeInfo, TypeInfo) => void failingErrorFunc = [e, a| a.assertEquals(TypeSystemProvider::UNDEFINED_TYPE)]
		actual.checkMathsOp(rhs, errorFunc, failingErrorFunc)
	}

	@Test
	def void testBoolOp(){
		val Expression actual = MdlFactory::eINSTANCE.createBooleanLiteral
		val Expression rhs = MdlFactory::eINSTANCE.createBooleanLiteral
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		actual.checkBoolOp(rhs, errorFunc, errorFunc)
	}

	@Test
	def void testBoolOpWithNullRhs(){
		val Expression actual = MdlFactory::eINSTANCE.createBooleanLiteral
		val Expression rhs = null
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		val (TypeInfo, TypeInfo) => void failingErrorFunc = [e, a| a.assertSame(TypeSystemProvider::UNDEFINED_TYPE)]
		actual.checkBoolOp(rhs, errorFunc, failingErrorFunc)
	}

	@Test
	def void testUnaryOpBool(){
		val Expression actual = MdlFactory::eINSTANCE.createBooleanLiteral
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		'!'.checkUnaryOp(actual, errorFunc)
	}

	@Test
	def void testUnaryOpBoolNullOperand(){
		val Expression actual = null
		val (TypeInfo, TypeInfo) => void failingErrorFunc = [e, a| a.assertSame(TypeSystemProvider::UNDEFINED_TYPE)]
		'!'.checkUnaryOp(actual, failingErrorFunc)
	}

	@Test
	def void testUnaryOpReal(){
		val Expression actual = MdlFactory::eINSTANCE.createRealLiteral
		val (TypeInfo, TypeInfo) => void errorFunc = [e, a| fail("should not call me!")]
		'+'.checkUnaryOp(actual, errorFunc)
	}

	@Test
	def void testUnaryOpRealNullOperand(){
		val Expression actual = null
		val (TypeInfo, TypeInfo) => void failingErrorFunc = [e, a| a.assertSame(TypeSystemProvider::UNDEFINED_TYPE)]
		'+'.checkUnaryOp(actual, failingErrorFunc)
	}

}