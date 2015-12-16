package eu.ddmore.mdl.validation

import eu.ddmore.mdl.type.MclTypeProvider
import eu.ddmore.mdl.type.MclTypeProvider.BuiltinEnumTypeInfo
import eu.ddmore.mdl.validation.BuiltinFunctionProvider.FunctDefn
import eu.ddmore.mdl.validation.BuiltinFunctionProvider.FunctionArgument
import eu.ddmore.mdl.validation.BuiltinFunctionProvider.NamedArgFuncDefn
import eu.ddmore.mdl.validation.BuiltinFunctionProvider.SimpleFuncDefn
import java.util.List
import java.util.Map

import static eu.ddmore.mdl.validation.SublistDefinitionProvider.*

class BuiltinFunctionTable {
	
	public static val TRANSFORM_FUNCS = #{ 'ln', 'logit', 'probit' }
	
	public static val TRANS_TYPE = new BuiltinEnumTypeInfo('transType', #{'none', 'ln', 'logit', 'probit'})
	
	public static val Map<String, List<? extends FunctDefn>> functDefns = #{
		'log' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE, MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'log2' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'log10' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'ln' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'probit' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'logit' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'invLogit' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'invProbit' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'factorial' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'lnFactorial' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'sin' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'cos' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'tan' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'sinh' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'cosh' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'tanh' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'floor' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'ceiling' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'min' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE, MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'max' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE, MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'abs' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'exp' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'seq' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE, MclTypeProvider::REAL_TYPE, MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_VECTOR_TYPE) ],
		'dseq' -> #[ new SimpleFuncDefn(#[MclTypeProvider::INT_TYPE, MclTypeProvider::INT_TYPE, MclTypeProvider::INT_TYPE], MclTypeProvider::INT_VECTOR_TYPE) ],
		'sqrt' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::REAL_TYPE) ],
		'sum' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE.makeVector], MclTypeProvider::REAL_TYPE) ],
		'mean' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE.makeVector], MclTypeProvider::REAL_TYPE) ],
		'median' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE.makeVector], MclTypeProvider::REAL_TYPE) ],
		'inverse' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_MATRIX_TYPE], MclTypeProvider::REAL_MATRIX_TYPE) ],
		'toInt' -> #[ new SimpleFuncDefn(#[MclTypeProvider::REAL_TYPE], MclTypeProvider::INT_TYPE ) ],
		
		'Normal' -> #[ new NamedArgFuncDefn(MclTypeProvider::PDF_TYPE, #{
						'mean' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'sd' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} ),
					new NamedArgFuncDefn(MclTypeProvider::PDF_TYPE, #{
						'mean' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'var' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} )
					],
		'LogNormal' -> #[ new NamedArgFuncDefn(MclTypeProvider::PDF_TYPE, #{
						'mean' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'sd' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} ),
					new NamedArgFuncDefn(MclTypeProvider::PDF_TYPE, #{
						'mean' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'var' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} )
					],
		'Bernoulli' -> #[ new NamedArgFuncDefn(MclTypeProvider::PMF_TYPE, #{
						'category' -> new FunctionArgument(MclTypeProvider::GENERIC_ENUM_VALUE_TYPE.makeReference, true),
						'probability' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} )
					],
		'Poisson' -> #[ new NamedArgFuncDefn(MclTypeProvider::PMF_TYPE, #{
						'lambda' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} )
					],
		'Binomial' -> #[ new NamedArgFuncDefn(MclTypeProvider::PMF_TYPE, #{
						'successCategory' -> new FunctionArgument(MclTypeProvider::GENERIC_ENUM_VALUE_TYPE.makeReference, true),
						'probabilityOfSuccess' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'numberOfTrials' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} )
					],
		'Gamma' -> #[ new NamedArgFuncDefn(MclTypeProvider::PDF_TYPE, #{
						'shape' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'scale' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} )
					],
		'NonParametric' -> #[ new NamedArgFuncDefn(MclTypeProvider::PDF_TYPE, #{
						'bins' -> new FunctionArgument(MclTypeProvider::REAL_TYPE.makeVector, true),
						'probability' -> new FunctionArgument(MclTypeProvider::REAL_TYPE.makeVector, true)
					} )
					],
		'MultiNonParametric' -> #[ new NamedArgFuncDefn(MclTypeProvider::PDF_TYPE.makeVector, #{
						'bins' -> new FunctionArgument(MclTypeProvider::REAL_MATRIX_TYPE, true),
						'probability' -> new FunctionArgument(MclTypeProvider::REAL_TYPE.makeVector, true)
					} )
					],
		'Empirical' -> #[ new NamedArgFuncDefn(MclTypeProvider::PDF_TYPE, #{
						'data' -> new FunctionArgument(MclTypeProvider::REAL_TYPE.makeVector, true),
						'probability' -> new FunctionArgument(MclTypeProvider::REAL_TYPE.makeVector, true)
					} )
					],
		'MultiEmpirical' -> #[ new NamedArgFuncDefn(MclTypeProvider::PDF_TYPE.makeVector, #{
						'data' -> new FunctionArgument(MclTypeProvider::REAL_TYPE.makeVector.makeVector, true),
						'probability' -> new FunctionArgument(MclTypeProvider::REAL_TYPE.makeVector, true)
					} )
					],
		'MultivariateNormal' -> #[
						new NamedArgFuncDefn(MclTypeProvider::PDF_TYPE.makeVector, #{
							'mean' -> new FunctionArgument(MclTypeProvider::REAL_TYPE.makeVector.makeVector, true),
							'cov' -> new FunctionArgument(MclTypeProvider::REAL_MATRIX_TYPE, true)
						} )
					],
		'matrix' -> #[
						new NamedArgFuncDefn(MclTypeProvider::REAL_MATRIX_TYPE, #{
							'vector' -> new FunctionArgument(MclTypeProvider::REAL_VECTOR_TYPE, true),
							'ncol' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
							'byRow' -> new FunctionArgument(MclTypeProvider::BOOLEAN_TYPE, true)
						} )
					],
		'linear' -> #[ new NamedArgFuncDefn(MclTypeProvider::REAL_TYPE, #{
						'trans' -> new FunctionArgument(TRANS_TYPE, false),
						'pop' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'fixEff' -> new FunctionArgument(getSublist(FIX_EFF_SUBLIST).makeVector, false),
						'ranEff' -> new FunctionArgument(MclTypeProvider::REAL_TYPE.makeVector, true)
					} )
					],
		'general' -> #[ new NamedArgFuncDefn(MclTypeProvider::REAL_TYPE, #{
						'trans' -> new FunctionArgument(TRANS_TYPE, false),
						'grp' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'ranEff' -> new FunctionArgument(MclTypeProvider::REAL_TYPE.makeVector, true)
					} )
					],
		'combinedError1' -> #[ new NamedArgFuncDefn(MclTypeProvider::REAL_TYPE, #{
						'trans' -> new FunctionArgument(TRANS_TYPE, false),
						'additive' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'proportional' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'prediction' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'eps' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} ) ],
		'combinedError2' -> #[ new NamedArgFuncDefn(MclTypeProvider::REAL_TYPE, #{
						'trans' -> new FunctionArgument(TRANS_TYPE, false),
						'additive' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'proportional' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'prediction' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'eps' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} ) ],
		'additiveError' -> #[ new NamedArgFuncDefn(MclTypeProvider::REAL_TYPE, #{
						'trans' -> new FunctionArgument(TRANS_TYPE, false),
						'additive' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'prediction' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'eps' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} ) ],
		'proportionalError' -> #[ new NamedArgFuncDefn(MclTypeProvider::REAL_TYPE, #{
						'trans' -> new FunctionArgument(TRANS_TYPE, false),
						'proportional' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'prediction' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true),
						'eps' -> new FunctionArgument(MclTypeProvider::REAL_TYPE, true)
					} ) ],
		'readVector' -> #[ new NamedArgFuncDefn(MclTypeProvider::REAL_TYPE.makeVector, #{
						'src' -> new FunctionArgument(ListDefinitionTable::PRIOR_SOURCE_TYPE, true),
						'element' -> new FunctionArgument(MclTypeProvider::STRING_TYPE, true)
					} ) ],
		'readMatrix' -> #[ new NamedArgFuncDefn(MclTypeProvider::REAL_MATRIX_TYPE, #{
						'src' -> new FunctionArgument(ListDefinitionTable::PRIOR_SOURCE_TYPE, true),
						'element' -> new FunctionArgument(MclTypeProvider::STRING_TYPE, true)
					} ) ]
	}


}