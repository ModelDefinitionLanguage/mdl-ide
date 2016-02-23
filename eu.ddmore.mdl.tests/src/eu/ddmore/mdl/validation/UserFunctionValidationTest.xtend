package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.LibraryTestHelper
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import org.eclipse.xtext.diagnostics.Diagnostic
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import eu.ddmore.mdllib.mdllib.MdlLibPackage
import org.junit.Ignore

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class UserFunctionValidationTest {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	

	@Test
	def void testInvalidTypeMismatchInFuncBody(){
		val mcl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::Function(arg1::Int, arg2::Real, arg3::String)::Real is
			# the function can contain only a single expression
		    arg2 * arg1 + arg3 # return type is Real
	}

} # end of model object
'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.additiveExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was ref:String.")
	}
	
	@Test
	def void testInvalidDangingRefInFuncBody(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::Function(arg1::Int, arg2::Real, arg3::String)::Real is
			# the function can contain only a single expression
		    arg2 * arg1 + arg4 # return type is Real
	}

} # end of model object
'''.parse
		
		mdl.assertNoErrors(Diagnostic::SYNTAX_DIAGNOSTIC)
		mdl.assertError(MdlPackage::eINSTANCE.symbolReference, Diagnostic::LINKING_DIAGNOSTIC)
	}
	
	@Ignore("Not supported yet")
	def void testInvalidRefenceVarOutsideFunction(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::Function(arg1::Int, arg2::Real, arg3::String)::Real is
			# the function can contain only a single expression
		    arg2 * arg1 + foo # return type is Real
	}
	
	MODEL_PREDICTION{
		foo::Real
	}

} # end of model object
'''.parse
		
		mdl.assertNoErrors(Diagnostic::SYNTAX_DIAGNOSTIC)
		mdl.assertError(MdlPackage::eINSTANCE.symbolReference, Diagnostic::LINKING_DIAGNOSTIC)
	}
	
	@Test
	def void testInvalidReturnAndExpressionTypesInconsistent(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::Function(arg1::Int, arg2::Real, arg3::String)::Real is
			# the function can contain only a single expression
		    arg3 # return type is Real
	}
} # end of model object
'''.parse
		
		mdl.assertNoErrors(Diagnostic::SYNTAX_DIAGNOSTIC)
		mdl.assertError(MdlLibPackage::eINSTANCE.functionSpec,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected function return type of 'Real' but expression was of type 'ref:String'.")
	}
	
	@Test
	def void testValidReturnTypeHonoursIntegerPromotion(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::Function(arg1::Int, arg2::Real, arg3::String)::Real is
			# the function can contain only a single expression
		    arg1
	}
} # end of model object
'''.parse
		
		mdl.assertNoErrors
	}
	
	@Test
	def void testValidReturnsMatrix(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::Function(arg1::Int, arg2::Real, arg3::String)::Matrix[[::Real]] is
			# the function can contain only a single expression
		    [[arg1;arg2]]
	}
} # end of model object
'''.parse
		
		mdl.assertNoErrors
	}
	
	@Test
	def void testValidReturnsVector(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::Function(arg1::Int, arg2::Real, arg3::String)::Vector[::Real] is
			# the function can contain only a single expression
		    [ arg1, arg2 ]
	}
} # end of model object
'''.parse
		
		mdl.assertNoErrors
	}
	
	@Test
	def void testValidTakesVectorArgument(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::Function(arg1::Vector[::Boolean])::Boolean is
			# the function can contain only a single expression
		    arg1[1]
	}
} # end of model object
'''.parse
		
		mdl.assertNoErrors
	}
	
	@Test
	def void testValidTakesMatrixArgument(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::Function(arg1::Matrix[[::String]])::String is
			# the function can contain only a single expression
		    arg1[1,1]
	}
	
} # end of model object
'''.parse
		
		mdl.assertNoErrors
	}
	
	@Test
	def void testInvalidWrongTypeNameForFunctionSpec(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::Real(arg1::Int, arg2::Real, arg3::String)::Real is
			# the function can contain only a single expression
		    arg1
	}
} # end of model object
'''.parse
		
		mdl.assertError(MdlLibPackage::eINSTANCE.typeSpec,
			MdlValidator::MALFORMED_TYPE_SPEC,
			"You must use the type name 'Function' to define a function specification.")
	}
	

	@Test
	def void testInvalidFunctionTypeNameButNoFunctionSpec(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::Function
	}
} # end of model object
'''.parse
		
		mdl.assertError(MdlLibPackage::eINSTANCE.typeSpec,
			MdlValidator::MALFORMED_TYPE_SPEC,
			"You must define a function specification when using the type name 'Function'.")
	}

	@Test
	def void testValidReturnType(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::Function(arg1::Int, arg2::Real)::Real is
			# the function can contain only a single expression
		    arg2 * arg1  # return type is Real
	}
	
	MODEL_PREDICTION{
		# refer to a user defined function as a normal function
		Z = userFunc(1, ln(2.0)) + 22.2
	}

	VARIABILITY_LEVELS{
		ID : { level=2, type is parameter }
	} 
} # end of model object
'''.parse
		
		mdl.assertNoErrors
	}

	@Test
	def void testValidCallNoArgs(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::Function()::Real is
			# the function can contain only a single expression
		    pi
	}
	
	MODEL_PREDICTION{
		# refer to a user defined function as a normal function
		Z = userFunc() + 22.2
	}

	VARIABILITY_LEVELS{
		ID : { level=2, type is parameter }
	} 
} # end of model object
'''.parse
		
		mdl.assertNoErrors
	}

	@Test
	def void testInvalidReturnType(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::Function()::String is
			# the function can contain only a single expression
		    "A String"
	}
	
	MODEL_PREDICTION{
		# refer to a user defined function as a normal function
		Z = userFunc() + 22.2
	}

	VARIABILITY_LEVELS{
		ID : { level=2, type is parameter }
	} 
} # end of model object
'''.parse
		
		mdl.assertError(MdlPackage::eINSTANCE.additiveExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was ref:String.")
	}

	@Test
	def void testInvalidIncorrectArgNums(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::Function()::Real is
			# the function can contain only a single expression
		    21
	}
	
	MODEL_PREDICTION{
		# refer to a user defined function as a normal function
		Z = userFunc(1)
	}

	VARIABILITY_LEVELS{
		ID : { level=2, type is parameter }
	} 
} # end of model object
'''.parse
		
		mdl.assertError(MdlPackage::eINSTANCE.symbolReference,
			MdlValidator::INCORRECT_NUM_FUNC_ARGS,
			"Function 'userFunc' has the wrong number of arguments. Expected 0.")
	}

}