package eu.ddmore.mdl.provider

import eu.ddmore.mdl.provider.BuiltinFunctionProvider.FunctDefn
import eu.ddmore.mdl.provider.BuiltinFunctionProvider.FunctionArgument
import eu.ddmore.mdl.provider.BuiltinFunctionProvider.NamedArgFuncDefn
import eu.ddmore.mdl.provider.BuiltinFunctionProvider.SimpleFuncDefn
import eu.ddmore.mdl.type.BuiltinEnumTypeInfo
import eu.ddmore.mdl.type.TypeSystemProvider
import java.util.Map

class BuiltinFunctionTable {
	
	public static val TRANSFORM_FUNCS = #{ 'ln', 'logit', 'probit' }
//	
//	public static val TRANS_TYPE = new BuiltinEnumTypeInfo('transType', #{'none', 'ln', 'logit', 'probit'})
	
//	val Map<String, ? extends FunctDefn> functDefns = #{
//		'log' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE, TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'log2' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'log10' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'ln' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'probit' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'logit' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'invLogit' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'invProbit' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'factorial' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'lnFactorial' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'sin' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'cos' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'tan' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'sinh' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'cosh' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'tanh' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'floor' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'ceiling' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'min' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE, TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'max' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE, TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'abs' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'exp' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'seq' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE, TypeSystemProvider::REAL_TYPE, TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_VECTOR_TYPE),
//		'dseq' -> new SimpleFuncDefn(#[TypeSystemProvider::INT_TYPE, TypeSystemProvider::INT_TYPE, TypeSystemProvider::INT_TYPE], TypeSystemProvider::INT_VECTOR_TYPE),
//		'sqrt' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::REAL_TYPE),
//		'sum' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE.makeVector], TypeSystemProvider::REAL_TYPE),
//		'mean' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE.makeVector], TypeSystemProvider::REAL_TYPE),
//		'median' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE.makeVector], TypeSystemProvider::REAL_TYPE),
//		'inverse' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_MATRIX_TYPE], TypeSystemProvider::REAL_MATRIX_TYPE),
//		'toInt' -> new SimpleFuncDefn(#[TypeSystemProvider::REAL_TYPE], TypeSystemProvider::INT_TYPE ),
//		
//		'Normal' -> new NamedArgFuncDefn(TypeSystemProvider::PDF_TYPE, #{
//						'mean' -> TypeSystemProvider::REAL_TYPE,
//						'sd' -> TypeSystemProvider::REAL_TYPE,
//						'var' -> TypeSystemProvider::REAL_TYPE
//					},
//						#[
//							#{ 'mean' -> true, 'sd' -> true },
//						 	#{ 'mean' -> true, 'var' -> true }
//						 ]
//						 ),
//		'LogNormal' -> new NamedArgFuncDefn(TypeSystemProvider::PDF_TYPE, #{
//						'mean' -> TypeSystemProvider::REAL_TYPE,
//						'sd' -> TypeSystemProvider::REAL_TYPE,
//						'var' -> TypeSystemProvider::REAL_TYPE
//					},
//						#[
//							#{ 'mean' -> true, 'sd' -> true },
//						 	#{ 'mean' -> true, 'var' -> true }
//						 ]
//					 )
//					,
//		'Bernoulli' -> new NamedArgFuncDefn(TypeSystemProvider::PMF_TYPE, #{
//						'category' -> new FunctionArgument(TypeSystemProvider::GENERIC_ENUM_VALUE_TYPE.makeReference, true),
//						'probability' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true)
//					} )
//					,
//		'Poisson' -> new NamedArgFuncDefn(TypeSystemProvider::PMF_TYPE, #{
//						'lambda' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true)
//					} )
//					,
//		'Binomial' -> new NamedArgFuncDefn(TypeSystemProvider::PMF_TYPE, #{
//						'successCategory' -> new FunctionArgument(TypeSystemProvider::GENERIC_ENUM_VALUE_TYPE.makeReference, true),
//						'probabilityOfSuccess' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true),
//						'numberOfTrials' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true)
//					} )
//					,
//		'Gamma' -> new NamedArgFuncDefn(TypeSystemProvider::PDF_TYPE, #{
//						'shape' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true),
//						'scale' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true)
//					} )
//					,
//		'NonParametric' -> new NamedArgFuncDefn(TypeSystemProvider::PDF_TYPE, #{
//						'bins' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE.makeVector, true),
//						'probability' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE.makeVector, true)
//					} )
//					,
//		'MultiNonParametric' -> new NamedArgFuncDefn(TypeSystemProvider::PDF_TYPE.makeVector, #{
//						'bins' -> new FunctionArgument(TypeSystemProvider::REAL_MATRIX_TYPE, true),
//						'probability' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE.makeVector, true)
//					} )
//					,
//		'Empirical' -> new NamedArgFuncDefn(TypeSystemProvider::PDF_TYPE, #{
//						'data' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE.makeVector, true),
//						'probability' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE.makeVector, true)
//					} )
//					,
//		'MultiEmpirical' -> new NamedArgFuncDefn(TypeSystemProvider::PDF_TYPE.makeVector, #{
//						'data' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE.makeVector.makeVector, true),
//						'probability' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE.makeVector, true)
//					} )
//					,
//		'MultivariateNormal' -> 
//						new NamedArgFuncDefn(TypeSystemProvider::PDF_TYPE.makeVector, #{
//							'mean' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE.makeVector.makeVector, true),
//							'cov' -> new FunctionArgument(TypeSystemProvider::REAL_MATRIX_TYPE, true)
//						} )
//					,
//		'matrix' -> 
//						new NamedArgFuncDefn(TypeSystemProvider::REAL_MATRIX_TYPE, #{
//							'vector' -> new FunctionArgument(TypeSystemProvider::REAL_VECTOR_TYPE, true),
//							'ncol' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true),
//							'byRow' -> new FunctionArgument(TypeSystemProvider::BOOLEAN_TYPE, true)
//						} )
//					,
//		'linear' -> new NamedArgFuncDefn(TypeSystemProvider::REAL_TYPE, #{
//						'trans' -> new FunctionArgument(TRANS_TYPE, false),
//						'pop' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true),
//						'fixEff' -> new FunctionArgument(SublistDefinitionTable::instance.getSublist(SublistDefinitionTable::FIX_EFF_SUBLIST).makeVector, false),
//						'ranEff' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE.makeVector, true)
//					} )
//					,
//		'general' -> new NamedArgFuncDefn(TypeSystemProvider::REAL_TYPE, #{
//						'trans' -> new FunctionArgument(TRANS_TYPE, false),
//						'grp' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true),
//						'ranEff' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE.makeVector, true)
//					} )
//					,
//		'combinedError1' -> new NamedArgFuncDefn(TypeSystemProvider::REAL_TYPE, #{
//						'trans' -> new FunctionArgument(TRANS_TYPE, false),
//						'additive' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true),
//						'proportional' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true),
//						'prediction' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true),
//						'eps' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true)
//					} ),
//		'combinedError2' -> new NamedArgFuncDefn(TypeSystemProvider::REAL_TYPE, #{
//						'trans' -> new FunctionArgument(TRANS_TYPE, false),
//						'additive' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true),
//						'proportional' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true),
//						'prediction' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true),
//						'eps' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true)
//					} ),
//		'additiveError' -> new NamedArgFuncDefn(TypeSystemProvider::REAL_TYPE, #{
//						'trans' -> new FunctionArgument(TRANS_TYPE, false),
//						'additive' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true),
//						'prediction' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true),
//						'eps' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true)
//					} ),
//		'proportionalError' -> new NamedArgFuncDefn(TypeSystemProvider::REAL_TYPE, #{
//						'trans' -> new FunctionArgument(TRANS_TYPE, false),
//						'proportional' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true),
//						'prediction' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true),
//						'eps' -> new FunctionArgument(TypeSystemProvider::REAL_TYPE, true)
//					} ),
//		'readVector' -> new NamedArgFuncDefn(TypeSystemProvider::REAL_TYPE.makeVector, #{
//						'src' -> new FunctionArgument(ListDefinitionTable::PRIOR_SOURCE_TYPE, true),
//						'element' -> new FunctionArgument(TypeSystemProvider::STRING_TYPE, true)
//					} ),
//		'readMatrix' -> new NamedArgFuncDefn(TypeSystemProvider::REAL_MATRIX_TYPE, #{
//						'src' -> new FunctionArgument(ListDefinitionTable::PRIOR_SOURCE_TYPE, true),
//						'element' -> new FunctionArgument(TypeSystemProvider::STRING_TYPE, true)
//					} )
//	}

//	private static var BuiltinFunctionTable anInstance
//
//	static def getInstance(){
//		if(anInstance == null){
//			anInstance = new BuiltinFunctionTable
//		}
//		anInstance
//	} 
//
//	def Map<String, ? extends FunctDefn> getFunctDefns(){
//		functDefns
//	} 

}