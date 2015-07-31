package eu.ddmore.mdl.type

import eu.ddmore.mdl.MdlInjectorProvider
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.MdlFactory
import eu.ddmore.mdl.type.MclTypeProvider.PrimitiveType
import eu.ddmore.mdl.type.MclTypeProvider.TypeInfo
import eu.ddmore.mdl.type.MclTypeProvider.TypeProperty
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith

import static extension org.junit.Assert.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclTypeProviderTest {
//	@Inject extension ParseHelper<Mcl>
//	@Inject extension ValidationTestHelper
	
	extension MclTypeProvider th = new MclTypeProvider 

//	static val STRING_TYPE = new TypeInfo(PrimitiveType.String, TypeProperty.None)
//	static val REAL_TYPE = new TypeInfo(PrimitiveType.Real, TypeProperty.None)
//	static val VECTOR_TYPE = new TypeInfo(PrimitiveType.Real, TypeProperty.Vector)
//	static val BOOL_TYPE = new TypeInfo(PrimitiveType.Boolean, TypeProperty.None)
//	static val PDF_TYPE = new TypeInfo(PrimitiveType.Pdf, TypeProperty.None)

	
	@Test
	def void testTypeExpectedFuncCallExpression(){
		val funcCall = MdlFactory::eINSTANCE.createBuiltinFunctionCall
		funcCall.func = 'ln'
		funcCall.typeFor.assertEquals(new TypeInfo(PrimitiveType.Real, TypeProperty.None))
	}

	@Test
	def void testTypeUnknownFuncCallExpression(){
		val funcCall = MdlFactory::eINSTANCE.createBuiltinFunctionCall
		funcCall.func = 'foobar'
		MclTypeProvider::UNDEFINED_TYPE.assertEquals(funcCall.typeFor)
	}

	@Test
	def void testTypeDistributionFuncCallExpression(){
		val funcCall = MdlFactory::eINSTANCE.createBuiltinFunctionCall
		funcCall.func = 'Normal'
		MclTypeProvider::PDF_TYPE.assertEquals(funcCall.typeFor)
	}

	@Test
	def void testStringLiteralExpression(){
		val actual = MdlFactory::eINSTANCE.createStringLiteral
		actual.value = 'foobar'
		actual.typeFor.assertEquals(MclTypeProvider::STRING_TYPE)
	}

	@Test
	def void testNumberLiteralExpression(){
		val actual = MdlFactory::eINSTANCE.createNumberLiteral
		actual.value = 'foobar'
		actual.typeFor.assertEquals(MclTypeProvider::REAL_TYPE)
	}

	@Test
	def void testBooleanLiteralExpression(){
		val actual = MdlFactory::eINSTANCE.createBooleanLiteral
		actual.isTrue = true
		actual.typeFor.assertEquals(MclTypeProvider::BOOL_TYPE)
	}

	@Test
	def void testVectorLiteralExpression(){
		val actual = MdlFactory::eINSTANCE.createVectorLiteral
		actual.typeFor.assertEquals(MclTypeProvider::VECTOR_TYPE)
	}

	@Test
	def void testRelationalOp(){
		val Expression actual = MdlFactory::eINSTANCE.createNumberLiteral
		val Expression rhs = MdlFactory::eINSTANCE.createNumberLiteral
		val (PrimitiveType, PrimitiveType) => void errorFunc = [e, a| fail("should not call me!")]
		actual.checkRelationalOp(rhs, errorFunc, errorFunc)
	}

	@Test
	def void testRelationalOpWithEnums(){
		val actual = MdlFactory::eINSTANCE.createCategoryReference
		val lhsDefn = MdlFactory::eINSTANCE.createCategoryDefinition
		lhsDefn.name = "tst1"
		actual.ref = lhsDefn 
		val rhs = MdlFactory::eINSTANCE.createCategoryReference
		val rhsDefn = MdlFactory::eINSTANCE.createCategoryDefinition
		rhsDefn.name = "tst2"
		rhs.ref=rhsDefn
		val (PrimitiveType, PrimitiveType) => void errorFunc = [e, a| fail("should not call me!")]
		actual.checkRelationalOp(rhs, errorFunc, errorFunc)
	}

	@Test
	def void testRelationalOpWithNumAndEnum(){
		val Expression actual = MdlFactory::eINSTANCE.createNumberLiteral
		val rhs = MdlFactory::eINSTANCE.createCategoryReference
		val rhsDefn = MdlFactory::eINSTANCE.createCategoryDefinition
		rhsDefn.name = "tst2"
		rhs.ref = rhsDefn
		val (PrimitiveType, PrimitiveType) => void errorFunc = [e, a| fail("should not call me!")]
		val (PrimitiveType, PrimitiveType) => void failingErrorFunc = [e, a| e.assertSame(PrimitiveType.Real) a.assertSame(PrimitiveType.Enum)]
		actual.checkRelationalOp(rhs, failingErrorFunc, errorFunc)
	}

	@Test
	def void testRelationalOpWithEnumandNum(){
		val actual = MdlFactory::eINSTANCE.createCategoryReference
		val lhsDefn = MdlFactory::eINSTANCE.createCategoryDefinition
		lhsDefn.name = "tst1"
		actual.ref = lhsDefn 
		val rhs = MdlFactory::eINSTANCE.createNumberLiteral
		val (PrimitiveType, PrimitiveType) => void errorFunc = [e, a| fail("should not call me!")]
		val (PrimitiveType, PrimitiveType) => void failingErrorFunc = [e, a| e.assertSame(PrimitiveType.Enum) a.assertSame(PrimitiveType.Real)]
		actual.checkRelationalOp(rhs, failingErrorFunc, errorFunc)
	}

	@Test
	def void testRelationalOpWithNullRhs(){
		val Expression actual = MdlFactory::eINSTANCE.createNumberLiteral
		val Expression rhs = null
		val (PrimitiveType, PrimitiveType) => void errorFunc = [e, a| e.assertSame(PrimitiveType.Real) a.assertSame(PrimitiveType.Undefined)]
		val (PrimitiveType, PrimitiveType) => void failingErrorFunc = [e, a| a.assertSame(PrimitiveType.Undefined)]
		actual.checkRelationalOp(rhs, errorFunc, failingErrorFunc)
	}

	@Test
	def void testMathsOp(){
		val Expression actual = MdlFactory::eINSTANCE.createNumberLiteral
		val Expression rhs = MdlFactory::eINSTANCE.createNumberLiteral
		val (PrimitiveType, PrimitiveType) => void errorFunc = [e, a| fail("should not call me!")]
		actual.checkMathsOp(rhs, errorFunc, errorFunc)
	}

	@Test
	def void testMathsOpWithString(){
		val Expression actual = MdlFactory::eINSTANCE.createNumberLiteral
		val Expression rhs = MdlFactory::eINSTANCE.createStringLiteral
		val (PrimitiveType, PrimitiveType) => void errorFunc = [e, a| a.assertSame(PrimitiveType.String)]
		actual.checkMathsOp(rhs, errorFunc, errorFunc)
	}

	@Test
	def void testMathsOpWithNullRhs(){
		val Expression actual = MdlFactory::eINSTANCE.createNumberLiteral
		val Expression rhs = null
		val (PrimitiveType, PrimitiveType) => void errorFunc = [e, a| fail("should not call me!")]
		val (PrimitiveType, PrimitiveType) => void failingErrorFunc = [e, a| a.assertSame(PrimitiveType.Undefined)]
		actual.checkMathsOp(rhs, errorFunc, failingErrorFunc)
	}

	@Test
	def void testBoolOp(){
		val Expression actual = MdlFactory::eINSTANCE.createBooleanLiteral
		val Expression rhs = MdlFactory::eINSTANCE.createBooleanLiteral
		val (PrimitiveType, PrimitiveType) => void errorFunc = [e, a| fail("should not call me!")]
		actual.checkBoolOp(rhs, errorFunc, errorFunc)
	}

	@Test
	def void testBoolOpWithNullRhs(){
		val Expression actual = MdlFactory::eINSTANCE.createBooleanLiteral
		val Expression rhs = null
		val (PrimitiveType, PrimitiveType) => void errorFunc = [e, a| fail("should not call me!")]
		val (PrimitiveType, PrimitiveType) => void failingErrorFunc = [e, a| a.assertSame(PrimitiveType.Undefined)]
		actual.checkBoolOp(rhs, errorFunc, failingErrorFunc)
	}

	@Test
	def void testUnaryOpBool(){
		val Expression actual = MdlFactory::eINSTANCE.createBooleanLiteral
		val (PrimitiveType, PrimitiveType) => void errorFunc = [e, a| fail("should not call me!")]
		'!'.checkUnaryOp(actual, errorFunc)
	}

	@Test
	def void testUnaryOpBoolNullOperand(){
		val Expression actual = null
		val (PrimitiveType, PrimitiveType) => void failingErrorFunc = [e, a| a.assertSame(PrimitiveType.Undefined)]
		'!'.checkUnaryOp(actual, failingErrorFunc)
	}

	@Test
	def void testUnaryOpReal(){
		val Expression actual = MdlFactory::eINSTANCE.createNumberLiteral
		val (PrimitiveType, PrimitiveType) => void errorFunc = [e, a| fail("should not call me!")]
		'+'.checkUnaryOp(actual, errorFunc)
	}

	@Test
	def void testUnaryOpRealNullOperand(){
		val Expression actual = null
		val (PrimitiveType, PrimitiveType) => void failingErrorFunc = [e, a| a.assertSame(PrimitiveType.Undefined)]
		'+'.checkUnaryOp(actual, failingErrorFunc)
	}

}