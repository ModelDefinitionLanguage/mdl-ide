package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.LibraryTestHelper
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclListAttributeValidationTest {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	

	@Test
	def void testValidAttributes(){
		val mcl = '''bar = dataObj {
			DATA_INPUT_VARIABLES{
				ID : { use is id }
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testMissingMandatoryAttribute(){
		val mcl = '''bar = dataObj {
			DECLARED_VARIABLES{ Y }
			
			DATA_INPUT_VARIABLES{
				ID : { variable = Y }
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.attributeList,
			MdlValidator::MANDATORY_LIST_KEY_ATT_MISSING,
			"mandatory key attribute is missing in list"
		)
	}

	@Ignore  // language currently doesn't have any cases that use this.
	def void testMissingAttributeWithDep(){
		val mcl = '''bar = dataObj {
			DECLARED_VARIABLES{ Y }
			
			DATA_INPUT_VARIABLES{
				aCov : { use is covariate, categorical with {male, female} }
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		//@TODO: Implement the correct validation to check that categories are not defined.
		mcl.assertError(MdlPackage::eINSTANCE.attributeList,
			MdlValidator::MANDATORY_LIST_ATT_MISSING,
			"categories are not mapped to data"
		)
	}

	@Ignore // @TODO: Write a test for dep attributes.
	def void testMissingDependentAttribute(){
		val mcl = '''bar = dataObj {
			DECLARED_VARIABLES{ Y }
			
			DATA_INPUT_VARIABLES{
				aCov : { use is covariate, categories with {male when 1, female when 2} }
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.attributeList,
			MdlValidator::MANDATORY_LIST_ATT_MISSING,
			"mandatory attribute 'categories'"
		)
	}

	@Test
	def void testDataDerivedVariablesAttributes(){
		val mcl = '''bar = dataObj {
			DECLARED_VARIABLES{ D }
			
			DATA_INPUT_VARIABLES{
				AMT : { use is amt, variable = D }
				TIME : { use is idv }
			}
			DATA_DERIVED_VARIABLES{
				DT : { use is doseTime, idvColumn = TIME, amtColumn = AMT }
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidSrcColumn(){
		val mcl = '''bar = dataObj {
			DECLARED_VARIABLES{ D }
			
			DATA_INPUT_VARIABLES{
				CMT : { use is cmt }
				AMT : { use is amt, define = { 0 in CMT as D } }
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInvalidUnrecognisedKeyValue(){
		val mcl = '''bar = dataObj {
			DECLARED_VARIABLES{ D }
			
			DATA_INPUT_VARIABLES{
				CMT : { use is cm }
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.attributeList,
			MdlValidator::LIST_KEY_VAL_UNRECOGNISED,
			"Attribute list key value 'cm' is not recognised."
		);
	}

	@Test
	def void testValidSrcDvColumn(){
		val mcl = '''bar = dataObj {
			DECLARED_VARIABLES{ D }
			
			DATA_INPUT_VARIABLES{
				CMT : { use is dvid }
				AMT : { use is dv, define = { 0 in CMT as D } }
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInvalidSrcColumn1(){
		val mcl = '''bar = dataObj {
			DECLARED_VARIABLES{ D }
			
			DATA_INPUT_VARIABLES{
				CMT : { use is covariate }
				AMT : { use is amt, define = { 0 in CMT as D } }
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mappingPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected source column of type 'List:Cmt', but was 'List:Covariate'.")
	}

	@Test
	def void testInvalidSrcColumn2(){
		val mcl = '''bar = dataObj {
			DECLARED_VARIABLES{ D }
			
			DATA_INPUT_VARIABLES{
				CMT : { use is dvid }
				AMT : { use is amt, define = { 0 in CMT as D } }
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mappingPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected source column of type 'List:Cmt', but was 'List:Dvid'.")
	}

	@Test
	def void testInvalidSrcDvColumn3(){
		val mcl = '''bar = dataObj {
			DECLARED_VARIABLES{ D }
			
			DATA_INPUT_VARIABLES{
				CMT : { use is covariate }
				AMT : { use is dv, define = { 0 in CMT as D } }
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.mappingPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected source column of type 'List:Dvid', but was 'List:Covariate'.")
	}

	@Test
	def void testUnexpectedAttributeCombination(){
		val mcl = '''bar = dataObj {
			DECLARED_VARIABLES{ D }
			
			DATA_INPUT_VARIABLES{
				CMT : { use is cmt }
				AMT : { use is amt, variable = D, define = { 0 in CMT as D } }
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::UNRECOGNIZED_LIST_ATT,
			"attribute 'variable' is not recognised in this context"
		)
	}

	@Test
	def void testMissingMandatoryAttributeWhenMoreThanOneAttSet(){
		val mcl = '''bar = dataObj {
			DECLARED_VARIABLES{ D }
			
			DATA_INPUT_VARIABLES{
				CMT : { use is cmt }
				AMT : { use is amt }
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.attributeList,
			MdlValidator::MANDATORY_LIST_ATT_MISSING,
			"mandatory attribute 'variable'"
		)
	}

	@Test
	def void testInvalidDuplicateAttribute(){
		val mcl = '''bar = dataObj {
			
			DATA_INPUT_VARIABLES{
				CMT : { use is cmt, use is cmt }
			}
			
			SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::DUPLICATE_ATTRIBUTE_NAME,
			"List attribute 'use' is used more than once."
		)
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::DUPLICATE_ATTRIBUTE_NAME,
			"List attribute 'use' is used more than once."
		)
	}

	@Test  
	def void testAnonymousCompartmentAttributesOK(){
		val mcl = '''bar = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
				ID : { type is parameter, level=1 }
			}
			
			STRUCTURAL_PARAMETERS{
				KA
				ALAG1
				F1
				V
				CL
			}
			
			MODEL_PREDICTION{
	  			COMPARTMENT {
					INPUT_KA : {type is depot, modelCmt=1, to=CENTRAL, ka=KA, tlag=ALAG1, finput=F1}
					CENTRAL  : {type is compartment, modelCmt=2}
                    	    ::  {type is elimination, modelCmt=2, from=CENTRAL, v=V, cl=CL}
   				}# end COMPARTMENT
				CONC=CENTRAL/V
			} # end MODEL_PREDICTION
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test  
	def void testInvalidAnonysation(){
		val mcl = '''bar = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
				ID : { type is parameter, level=1 }
			}
			
			STRUCTURAL_PARAMETERS{
				KA
				ALAG1
				F1
				V
				CL
			}
			
			MODEL_PREDICTION{
	  			COMPARTMENT {
					:: {type is compartment, modelCmt=2}
   				}# end COMPARTMENT
			} # end MODEL_PREDICTION
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.anonymousListStatement,
			MdlValidator::LIST_NOT_NAMED,
			"a list with this key cannot be anonymous in this context"
		)
	}

	@Test  
	def void testInvalidListNaming(){
		val mcl = '''bar = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
				ID : { type is parameter, level=1 }
			}
			
			STRUCTURAL_PARAMETERS{
				KA
				ALAG1
				F1
				V
				CL
			}
			
			MODEL_PREDICTION{
	  			COMPARTMENT {
					INPUT_KA : {type is depot, modelCmt=1, to=CENTRAL, ka=KA, tlag=ALAG1, finput=F1}
					 CENTRAL : {type is compartment, modelCmt=2}
                     FOO :  {type is elimination, modelCmt=2, from=CENTRAL, v=V, cl=CL}
   				}# end COMPARTMENT
				CONC=CENTRAL/V
			} # end MODEL_PREDICTION
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.listDefinition,
			MdlValidator::LIST_NOT_ANONYMOUS//,
//			"a list with this key cannot have a name in this context"
		)
	}

	@Test
	def void testAnonymousCompartmentAttributesNoKey(){
		val mcl = '''bar = mdlObj {
			IDV{ T }
			
			VARIABILITY_LEVELS{
				ID : { type is idv, level=1 }
			}
			
			STRUCTURAL_PARAMETERS{
				KA
				ALAG1
				F1
				V
				CL
			}
			
			MODEL_PREDICTION{
	  			COMPARTMENT {
					INPUT_KA:   {type is depot, modelCmt=1, output=CENTRAL, ka=KA, tlag=ALAG1, finput=F1}
					CENTRAL:    {type is compartment, modelCmt=2}
                    	::		{modelCmt=2, input=CENTRAL, v=V, cl=CL}
   				}# end COMPARTMENT
				CONC=CENTRAL/V
			} # end MODEL_PREDICTION
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.attributeList,
			MdlValidator::MANDATORY_LIST_KEY_ATT_MISSING,
			"mandatory key attribute is missing in list"
		)
	}


	@Test
	def void testUnrecognizedAttribute(){
		val mcl = '''bar = mdlObj(idv T) {
			VARIABILITY_LEVELS{
				ID : { type is idv, level=1 }
			}
			
			STRUCTURAL_PARAMETERS{
				KA
				ALAG1
				F1
				V
				CL
			}
			
			MODEL_PREDICTION{
	  			COMPARTMENT {
					INPUT_KA:   {type is depot, blahblah=1, to=CENTRAL, ka=KA, tlag=ALAG1, finput=F1}
					CENTRAL:    {type is compartment, modelCmt=2}
                    	:: {modelCmt=2, from=CENTRAL, v=V, cl=CL}
   				}# end COMPARTMENT
				CONC=CENTRAL/V
			} # end MODEL_PREDICTION
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::UNRECOGNIZED_LIST_ATT,
			"attribute 'blahblah' is not recognised in this context"
		)
	}

	@Test
	def void testUnrecognizedAttribute2(){
		val mcl = '''
		foo = dataObj {
			DATA_INPUT_VARIABLES{  foo : { use is ignore } }

			SOURCE{
				foo2 : { file="aFile", inputformat is nonmem }
			}
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::UNRECOGNIZED_LIST_ATT,
			"attribute 'inputformat' is not recognised in this context"
		)
	}

	@Test
	def void testInValidMissingWithCats(){
//		val mcl = '''
//		foo = mdlObj{
//			IDV{T}
//			
//			VARIABILITY_LEVELS{
//			}
//
//			MODEL_PREDICTION{
//			}# end MODEL_PREDICTION
//			
//			OBSERVATION{
//				PAIN : { type is categorical }
//			}
//		}
//		'''.parse
		val mcl = '''
		foo = dataObj {
			DATA_INPUT_VARIABLES{
				foo : { use is catCov }
			}

			SOURCE{
				foo2 : { file="aFile", inputformat is nonmem }
			}
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.enumPair,
			MdlValidator::INVALID_CATEGORY_DEFINITION,
			"Category definition is missing."
		)
	}

	@Test
	def void testValidTTE(){
		val mcl = '''
		foo = mdlObj{
			IDV{T}

			VARIABILITY_LEVELS{
			}

			MODEL_PREDICTION{
				HAZ = 1
			}# end MODEL_PREDICTION
			
			OBSERVATION{
				#Y : {type is tte, hazard = HAZ, event is intervalCensored }
				Y : {type is tte, hazard = HAZ }
			}# end ESTIMATION
		}
		'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInvalidAttributeWithUnexpectedCatDefn(){
		val mcl = '''
		foo = dataObj {
			DATA_INPUT_VARIABLES{  foo : { use is ignore } }

			SOURCE{
				foo : { file="aFile", inputFormat is nonmemFormat withCategories { hello when 0, goodbye when 2 } }
			}
		} # end of model object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.enumPair,
			MdlValidator::INVALID_CATEGORY_DEFINITION,
			"Unexpected category definition."
		)
	}

	@Test
	def void testValidSubListDefinition(){
		val mcl = '''
foo = mdlObj {
	IDV{T}

   COVARIATES{
   	  WT
   }# end COVARIATES

   VARIABILITY_LEVELS{
      ID: {type is parameter, level=2}
   }# end VARIABILITY_LEVELS

   STRUCTURAL_PARAMETERS{
      POP_CL
      POP_VC
      POP_Q
      POP_VP
      POP_KA
      POP_TLAG
      POP_BETA_CL_WT
      POP_BETA_V_WT
      RUV_PROP
      RUV_ADD
   }# end STRUCTURAL_PARAMETERS

   VARIABILITY_PARAMETERS{
      PPV_CL
      PPV_VC
      PPV_Q
      PPV_VP
      PPV_KA
      PPV_TLAG
      RUV_EPS1
   }# end VARIABILITY_PARAMETERS

   RANDOM_VARIABLE_DEFINITION (level=ID) {
      eta_PPV_CL ~ Normal(mean=0, sd=PPV_CL)
   }# end RANDOM_VARIABLE_DEFINITION (level=ID)

   INDIVIDUAL_VARIABLES{
      CL = linear(pop = POP_CL, fixEff = [{coeff=POP_BETA_CL_WT, cov=WT}] , ranEff = [eta_PPV_CL])
   }# end INDIVIDUAL_VARIABLES
} 
		'''.parse
		
		mcl.assertNoErrors
	}


	@Test
	def void testInvalidSubListDefinition(){
		val mcl = '''
foo = mdlObj {
   COVARIATES{
   	  WT
   }# end COVARIATES

   VARIABILITY_LEVELS{
      ID: {type is parameter, level=2}
   }# end VARIABILITY_LEVELS

   STRUCTURAL_PARAMETERS{
      POP_CL
      POP_VC
      POP_Q
      POP_VP
      POP_KA
      POP_TLAG
      POP_BETA_CL_WT
      POP_BETA_V_WT
      RUV_PROP
      RUV_ADD
   }# end STRUCTURAL_PARAMETERS

   VARIABILITY_PARAMETERS{
      PPV_CL
      PPV_VC
      PPV_Q
      PPV_VP
      PPV_KA
      PPV_TLAG
      RUV_EPS1
   }# end VARIABILITY_PARAMETERS

   RANDOM_VARIABLE_DEFINITION (level=ID) {
      eta_PPV_CL ~ Normal(mean=0, sd=PPV_CL)
   }# end RANDOM_VARIABLE_DEFINITION (level=ID)

   INDIVIDUAL_VARIABLES{
      CL = linear(pop = POP_CL, fixEff = [{foobar=POP_BETA_CL_WT, covariate=WT}] , ranEff = [eta_PPV_CL])
   }# end INDIVIDUAL_VARIABLES
} 
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.assignPair,
			MdlValidator::INCOMPATIBLE_TYPES,
			"argument 'fixEff' expected value of type 'vector:Sublist:fixEffAtts' but was 'vector:Undefined'."
		)
//		mcl.assertError(MdlPackage::eINSTANCE.enumPair,
//			MdlValidator::MANDATORY_LIST_ATT_MISSING,
//			"mandatory attribute 'categories' is missing"
//		)
	}

}