package eu.ddmore.mdl.type

import eu.ddmore.mdl.MdlInjectorProvider
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.MdlFactory
import eu.ddmore.mdl.type.EnumTypeInfo
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.junit.Assert.*
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.validation.TypeSystemValidator

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class TypeSystemProviderTest {
//	@Inject extension ParseHelper<Mcl>
//	@Inject extension ValidationTestHelper
	
	extension TypeSystemProvider th = new TypeSystemProvider 
	extension TypeSystemValidator tsv = new TypeSystemValidator

	@Test
	def void testTypeExpectedFuncCallExpression(){
		val funcCall = MdlFactory::eINSTANCE.createSymbolReference
		funcCall.ref = MdlFactory::eINSTANCE.createEquationDefinition
		funcCall.ref.name = 'ln'
		funcCall.typeFor.assertEquals(TypeSystemProvider::REAL_TYPE)
	}

	@Test
	def void testTypeUnknownFuncCallExpression(){
//		val funcCall = MdlFactory::eINSTANCE.createBuiltinFunctionCall
//		funcCall.func = 'foobar'
		val funcCall = MdlFactory::eINSTANCE.createSymbolReference
		funcCall.ref = MdlFactory::eINSTANCE.createEquationDefinition
		funcCall.ref.name = 'foobar'
		TypeSystemProvider::UNDEFINED_TYPE.assertEquals(funcCall.typeFor)
	}

	@Test
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