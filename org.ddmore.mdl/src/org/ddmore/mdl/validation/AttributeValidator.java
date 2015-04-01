/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to validate MDL specifications: checks generic list attributes for various blocks
 */
package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.ddmore.mdl.domain.Attribute;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.UseType;
import org.ddmore.mdl.mdl.VariabilityType;
import org.ddmore.mdl.mdl.impl.*;
import org.ddmore.mdl.types.DefaultValues;
import org.ddmore.mdl.types.MdlDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;

import com.google.inject.Inject;

import eu.ddmore.converter.mdlprinting.MdlPrinter;

public class AttributeValidator extends AbstractDeclarativeValidator{

	@Override
    @Inject
    public void register(EValidatorRegistrar registrar) {}
	
	public final static String MSG_ATTRIBUTE_UNKNOWN     = "Unknown attribute";
	public final static String MSG_ATTRIBUTE_MISSING     = "Required attribute is missing";
	public final static String MSG_ATTRIBUTE_DEFINED     = "Attribute defined more than once";
	public final static String MSG_ATTRIBUTE_WRONG_VALUE = "Incompatible attribute values";
	public final static String MSG_ATTRIBUTE_WRONG_TYPE  = "Type error";
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	/*Data object*/
	/*DATA_INPUT_VARIABLES*/
	final public static Attribute attr_type = new Attribute("type", MdlDataType.TYPE_VAR_TYPE, false, DefaultValues.CC_CONTINUOUS);
	final public static Attribute attr_define = new Attribute("define", MdlDataType.TYPE_LIST, false);
	final public static Attribute attr_use = new Attribute("use", MdlDataType.TYPE_USE, false, UseType.ID.toString());
	final public static Attribute attr_cmpt = new Attribute("cmpt", MdlDataType.TYPE_EXPR, false);
	final public static Attribute attr_prediction = new Attribute("prediction", MdlDataType.TYPE_EXPR, false);

	//final public static Attribute attr_administration_ref = new Attribute("administration", MdlDataType.TYPE_REF_DERIV, false);

	/*Parameter object*/
	//STRUCTURAL
	final public static Attribute attr_value = new Attribute("value", MdlDataType.TYPE_REAL, true, "0");
	final public static Attribute attr_hi = new Attribute("hi", MdlDataType.TYPE_REAL, false, "0");
	final public static Attribute attr_lo = new Attribute("lo", MdlDataType.TYPE_REAL, false, "1");
	final public static Attribute attr_fix = new Attribute("fix", MdlDataType.TYPE_BOOLEAN, false, "false");
	final public static Attribute attr_units = new Attribute("units", MdlDataType.TYPE_STRING, false, "kg");

	//VARIABILITY
	//final public static Attribute attr_values = new Attribute("values", MdlDataType.TYPE_VECTOR_REAL, true, "[]");
	//final public static Attribute attr_params = new Attribute("params", MdlDataType.TYPE_VECTOR_REF, true, "[]");
	final public static Attribute attr_type_randomEff = new Attribute("type", MdlDataType.TYPE_RANDOM_EFFECT, false, VariabilityType.SD.toString());
	
	/*Model object*/
	/*COVARIATE*/
	
	/*VARIABILITY*/
	final public static Attribute attr_level = new Attribute("level", MdlDataType.TYPE_NAT, true, "1"); 
	final public static Attribute attr_type_level = new Attribute("type", MdlDataType.TYPE_LEVEL, false); 
	
	//RANDOM_VARIABLES_DEFINITION
	final public static Attribute attr_rv1 = new Attribute("rv1", MdlDataType.TYPE_REF, true);
	final public static Attribute attr_rv2 = new Attribute("rv2", MdlDataType.TYPE_REF, true);
	
	/*OBSERVATION*/
	final public static Attribute attr_type_continuous = new Attribute("type", MdlDataType.TYPE_CONTINUOUS, true, DefaultValues.CC_CONTINUOUS);
	final public static Attribute attr_error = new Attribute("error", MdlDataType.TYPE_EXPR, true);
	final public static Attribute attr_eps = new Attribute("eps", MdlDataType.TYPE_REF, true);
	final public static Attribute attr_prediction_ref = new Attribute("prediction", MdlDataType.TYPE_REF, true);

	/*ESTIMATION*/
	final public static Attribute attr_ruv = new Attribute("ruv", MdlDataType.TYPE_EXPR, false);
	
	/*ODE*/
	final public static Attribute attr_deriv = new Attribute("deriv", MdlDataType.TYPE_EXPR, true, DefaultValues.VAR_NAME);	
	final public static Attribute attr_init = new Attribute("init", MdlDataType.TYPE_EXPR, false, "0");	
	final public static Attribute attr_x0 = new Attribute("x0", MdlDataType.TYPE_REAL, false, "0");	
	final public static Attribute attr_wrt = new Attribute("wrt", MdlDataType.TYPE_REF, false, DefaultValues.INDEPENDENT_VAR);	
	
	/*INDIVIDUAL_VARIABLES*/
	final public static Attribute attr_g_type = new Attribute("type", MdlDataType.TYPE_INDIVIDUAL_VAR, true);
	final public static Attribute attr_ranEff = new Attribute("ranEff", MdlDataType.TYPE_REF, true);
	final public static Attribute attr_fixEff = new Attribute("fixEff", 
			Arrays.asList(MdlDataType.TYPE_REF, MdlDataType.TYPE_VECTOR_REF), false);
	final public static Attribute attr_trans = new Attribute("trans", MdlDataType.TYPE_TRANS, false);
	final public static Attribute attr_pop = new Attribute("pop", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_cov = new Attribute("cov", 
			Arrays.asList(MdlDataType.TYPE_REF, MdlDataType.TYPE_VECTOR_REF), false);
	final public static Attribute attr_group = new Attribute("group", MdlDataType.TYPE_REF, false);
	
	/*PKMACRO*/
	final public static Attribute attr_macro = new Attribute("macro", MdlDataType.TYPE_PK_MACRO, true);
	final public static Attribute attr_type_nat = new Attribute("type", MdlDataType.TYPE_NAT, false);
	final public static Attribute attr_volume = new Attribute("volume", MdlDataType.TYPE_REF, false);	
	final public static Attribute attr_amount_ref = new Attribute("amount", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_concentration = new Attribute("concentration", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_target = new Attribute("target", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_from = new Attribute("from", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_to = new Attribute("to", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_tlag = new Attribute("tlag", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_k = new Attribute("k", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_ka = new Attribute("ka", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_kt = new Attribute("kt", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_p = new Attribute("p", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_param = new Attribute("param", MdlDataType.TYPE_REF, false);	
	/*
	final public static Attribute attr_finput = new Attribute("finput", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_foutput = new Attribute("foutput", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_kin = new Attribute("kin", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_kout = new Attribute("kout", MdlDataType.TYPE_REAL, false);
	final public static Attribute attr_thalf = new Attribute("thalf", MdlDataType.TYPE_REAL, false);
	final public static Attribute attr_tk0 = new Attribute("tk0", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_cl = new Attribute("cl", MdlDataType.TYPE_REAL, false);
	final public static Attribute attr_rin = new Attribute("rin", MdlDataType.TYPE_REAL, false); 
	final public static Attribute attr_isinput = new Attribute("isinput", MdlDataType.TYPE_BOOLEAN, false); 
	final public static Attribute attr_central= new Attribute("central", MdlDataType.TYPE_BOOLEAN, false);
	final public static Attribute attr_emax = new Attribute("emax", MdlDataType.TYPE_REAL, false); 
	final public static Attribute attr_vmax = new Attribute("vmax", MdlDataType.TYPE_REAL, false); 
	final public static Attribute attr_c50 = new Attribute("c50", MdlDataType.TYPE_REAL, false); 
	final public static Attribute attr_hill = new Attribute("hill", MdlDataType.TYPE_REAL, false); 
	*/
			
	/*Design object*/
	/*ADMINISTRATION*/
	final public static Attribute attr_trial_type = new Attribute("type", MdlDataType.TYPE_TRIAL, false);
	final public static Attribute attr_start = new Attribute("start", MdlDataType.TYPE_INT, false);
	final public static Attribute attr_end = new Attribute("end", MdlDataType.TYPE_INT, false);
	final public static Attribute attr_administration = new Attribute("administration", 
			MdlDataType.TYPE_INT, false);
	final public static Attribute attr_amount = new Attribute("amount", 
			Arrays.asList(MdlDataType.TYPE_NAT, MdlDataType.TYPE_VECTOR_NAT), false);
	final public static Attribute attr_amountBSA = new Attribute("amountBSA", 
			Arrays.asList(MdlDataType.TYPE_NAT, MdlDataType.TYPE_VECTOR_NAT), false);
	final public static Attribute attr_doseTime = new Attribute("doseTime", 
			Arrays.asList(MdlDataType.TYPE_NAT, MdlDataType.TYPE_VECTOR_NAT), false);
	final public static Attribute attr_duration = new Attribute("duration", MdlDataType.TYPE_NAT, false);
	final public static Attribute attr_steadyState = new Attribute("steadyState", MdlDataType.TYPE_BOOLEAN, false);
	final public static Attribute attr_interval = new Attribute("interval", 
			Arrays.asList(MdlDataType.TYPE_PREAL, MdlDataType.TYPE_VECTOR_PREAL), false);
	final public static Attribute attr_combination = new Attribute("combination", MdlDataType.TYPE_VECTOR_REF, false);
	final public static Attribute attr_treatment = new Attribute("treatment", MdlDataType.TYPE_REF, false);
	
	/*ACTION*/
	final public static Attribute attr_reset = new Attribute("reset", MdlDataType.TYPE_LIST, false);
	
	/*SAMPLING*/
	final public static Attribute attr_samplingTime = new Attribute("samplingTime", 
			Arrays.asList(MdlDataType.TYPE_NAT, MdlDataType.TYPE_VECTOR_NAT), false);
	final public static Attribute attr_outcome = new Attribute("outcome", MdlDataType.TYPE_STRING, false);
	final public static Attribute attr_bql = new Attribute("bql", MdlDataType.TYPE_REAL, false);
	
	/*STUDY_DESIGN (ARMS)*/
	final public static Attribute attr_name_ref = new Attribute("name", MdlDataType.TYPE_REF, true);		
	final public static Attribute attr_groupSize = new Attribute("groupSize", MdlDataType.TYPE_INT, false);
	final public static Attribute attr_inteventionSequence = new Attribute("inteventionSequence", MdlDataType.TYPE_LIST, false);
	final public static Attribute attr_occasionValue = new Attribute("occasionValue", MdlDataType.TYPE_LIST, false);
	final public static Attribute attr_samplingSequence = new Attribute("samplingSequence", MdlDataType.TYPE_LIST, false);
	
	/*DESIGN_SPACE, HYPER_SPACE*/
	final public static Attribute attr_admTime = new Attribute("admTime", MdlDataType.TYPE_RANDOM_LIST, false);
	final public static Attribute attr_numberSamples = new Attribute("numberSamples", MdlDataType.TYPE_RANDOM_LIST, false);
	final public static Attribute attr_min = new Attribute("min", MdlDataType.TYPE_RANDOM_LIST, false);
	final public static Attribute attr_max = new Attribute("max", MdlDataType.TYPE_RANDOM_LIST, false);

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Attribute sets
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*Data object*/
	final public static List<Attribute> attrs_dataInput = Arrays.asList(attr_type, attr_define, attr_use, attr_cmpt, attr_prediction, attr_units);
	
	/*Parameter object*/
	final public static List<Attribute> attrs_structural = Arrays.asList(attr_value, attr_lo, attr_hi, attr_fix, attr_units);
	final public static List<Attribute> attrs_variability = Arrays.asList(attr_value, attr_type_randomEff, attr_fix, attr_lo, attr_hi, attr_units);
	
	/*Model object*/
	final public static List<Attribute> attrs_randomVars = Arrays.asList(attr_type_randomEff, attr_rv1, attr_rv2);
	
	final public static List<Attribute> attrs_covariateDef   = Arrays.asList(attr_type, attr_units);
	final public static List<Attribute> attrs_variabilityDef = Arrays.asList(attr_level, attr_type_level, attr_units);
	
	final public static List<Attribute> attrs_ode = Arrays.asList(attr_deriv, attr_init, attr_x0, attr_wrt);
	final public static List<Attribute> attrs_estimation = Arrays.asList(attr_type, attr_prediction, attr_ruv);
	final public static List<Attribute> attrs_simulation = Arrays.asList();
	final public static List<Attribute> attrs_observation = Arrays.asList(attr_type_continuous, attr_error, 
			attr_eps, attr_prediction_ref, attr_trans);
	final public static List<Attribute> attrs_structuralParams = Arrays.asList(attr_units);
	final public static List<Attribute> attrs_variabilityParams = Arrays.asList(attr_units);
	final public static List<Attribute> attrs_individualVariables = Arrays.asList(attr_g_type, attr_trans, attr_pop, attr_cov, attr_fixEff, attr_ranEff, attr_group);
	final public static List<Attribute> attrs_pkMacro = Arrays.asList(
			attr_macro, attr_type_nat, attr_amount_ref, attr_volume, attr_from, attr_to, 
			attr_target, attr_k, attr_ka, attr_kt, attr_p, attr_tlag, attr_param);	
	
	/*Design object*/
	final public static List<Attribute> attrs_administration = Arrays.asList(attr_trial_type, attr_administration, attr_amount, attr_amountBSA, attr_doseTime, attr_start, 
		attr_end, attr_steadyState, attr_interval, attr_treatment, attr_combination, attr_duration);
	final public static List<Attribute> attrs_action = Arrays.asList(attr_start, attr_end, attr_reset);
	final public static List<Attribute> attrs_sampling = Arrays.asList(attr_trial_type, attr_start, attr_end, attr_samplingTime, attr_interval, attr_outcome, attr_bql, attr_combination);
	final public static List<Attribute> attrs_studyDesign = Arrays.asList(attr_groupSize, attr_inteventionSequence, attr_occasionValue, attr_samplingSequence, attr_cov);
	final public static List<Attribute> attrs_designSpace = Arrays.asList(attr_name_ref, attr_admTime, attr_numberSamples);
	final public static List<Attribute> attrs_hyperSpace = Arrays.asList(attr_name_ref, attr_min, attr_max, attr_admTime, attr_numberSamples);
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Returns recognised attributes for list containers
	public static List<Attribute> getListAttributes(EObject obj){
		/*Data object*/
		if (obj instanceof DataInputBlockImpl) return attrs_dataInput; 
		/*Parameter object*/
		if (obj instanceof StructuralBlockImpl) return attrs_structural;
		if (obj instanceof VariabilityBlockImpl) return attrs_variability;
		/*Model object*/
		if (obj instanceof CovariateDefinitionBlockImpl) return attrs_covariateDef;
		if (obj instanceof VariabilityDefinitionBlockImpl) return attrs_variabilityDef;
		if (obj instanceof IndividualVariablesBlockImpl) return attrs_individualVariables;
		if (obj instanceof OdeBlockImpl) return attrs_ode; 
		if (obj instanceof PkMacroBlockImpl) return attrs_pkMacro; 
		if (obj instanceof EstimationBlockImpl) return attrs_estimation; 
		if (obj instanceof SimulationBlockImpl) return attrs_simulation; 
		if (obj instanceof ObservationBlockImpl) return attrs_observation; 
		if (obj instanceof StructuralParametersBlockImpl) return attrs_structuralParams;
		if (obj instanceof VariabilityParametersBlockImpl) return attrs_variabilityParams;
		if (obj instanceof RandomVariableDefinitionBlockImpl) return attrs_randomVars;
		/*Design object*/
		if (obj instanceof StudyDesignBlockImpl) return attrs_studyDesign;  
		if (obj instanceof AdministrationBlockImpl) return attrs_administration;
		if (obj instanceof ActionBlockImpl) return attrs_action;        
		if (obj instanceof SamplingBlockImpl) return attrs_sampling;      
		if (obj instanceof DesignSpaceBlockImpl) return attrs_designSpace;   
		if (obj instanceof HyperSpaceBlockImpl) return attrs_hyperSpace; 
		return null;
	}	
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Validate list attributes
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Check
	public void checkRequiredArguments(Arguments args){
		//Do not enforce required attributes for blocks to nested lists in this block
		EObject container = findAttributeContainer(args.eContainer());
		if (skipAttributeValidation(container, args)) return;

		List<Attribute> knownAttributes = getListAttributes(container);
		if (knownAttributes != null){
			String prefix = Utils.getBlockName(container) + ":";		
			List<String> argumentNames = Utils.getArgumentNames(args);	
			checkRequiredAttributes(args, argumentNames, knownAttributes, prefix);
		}
	}
	
	@Check
	public void checkAllArguments(Argument argument){
		EObject namedArgsContainer = argument.eContainer();
		EObject argsContainer = namedArgsContainer.eContainer();
		EObject blockContainer = findAttributeContainer(argsContainer);
		Arguments args = (Arguments)argsContainer;
		if (skipAttributeValidation(blockContainer, args)) return;
		List<Attribute> knownAttributes = getListAttributes(blockContainer);
		if (knownAttributes != null){
			//Check that the attribute name is valid
			List<String> attributeNames = new ArrayList<String>();
			attributeNames.addAll(Utils.getAllNames(knownAttributes));
			checkDefinedAttributes(argument, args, knownAttributes, attributeNames);
			checkDefinedOnce(args);
		}
	}
	

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*Block attributes*/
	final public static Attribute attr_block_level = new Attribute("level", MdlDataType.TYPE_REF, true, DefaultValues.LEVEL);
	final public static List<Attribute> attrs_block_randomVars = Arrays.asList(attr_block_level);

	//Returns recognised attributes for blocks
	public static List<Attribute> getBlockAttributes(EObject obj){
		if (obj instanceof RandomVariableDefinitionBlockImpl) return attrs_block_randomVars; 
		return null;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//Validate block attributes
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Check
	public void checkRequiredBlockArguments(Arguments args){
		EObject container = args.eContainer();
		List<Attribute> knownAttributes = getBlockAttributes(container);
		if (knownAttributes != null){
			String prefix = Utils.getBlockName(container) + ":";		
			List<String> argumentNames = Utils.getArgumentNames(args);	
			checkRequiredAttributes(args, argumentNames, knownAttributes, prefix);
		}
	}
	
	@Check
	public void checkAllBlockArguments(Argument argument){
		EObject namedArgsContainer = argument.eContainer();
		EObject argsContainer = namedArgsContainer.eContainer();
		//NamedArguments -> Arguments -> Block
		EObject blockContainer = argsContainer.eContainer();
		List<Attribute> knownAttributes = getBlockAttributes(blockContainer);
		if (knownAttributes != null){
			Arguments args = (Arguments)argsContainer;
			List<String> attributeNames = new ArrayList<String>();
			attributeNames.addAll(Utils.getAllNames(knownAttributes));
			checkDefinedAttributes(argument, args, knownAttributes, attributeNames);
			checkDefinedOnce(args);
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////

	//Checks that all required attributes are specified
	private void checkRequiredAttributes(Arguments args, List<String> argumentNames, List<Attribute> attributes, String prefix){
		for (Attribute attr: attributes){
			if (attr.isMandatory()){
				if (!argumentNames.contains(attr.getName())) {
					if (attr.getDependency() != null){
						//Require attributes only of dependency condition holds
						if (argumentNames.contains(attr.getDependency().getAttrName())){
							String value = MdlPrinter.getInstance().getAttribute(args, attr.getDependency().getAttrName());
							if (attr.getDependency().containsValue(value)) {
								warning(MSG_ATTRIBUTE_MISSING + ": " + attr.getName(), 
										MdlPackage.Literals.ARGUMENTS__NAMED_ARGUMENTS, MSG_ATTRIBUTE_MISSING, prefix + attr.getName());
							}
						}
					} else {
						warning(MSG_ATTRIBUTE_MISSING + ": " + attr.getName(), 
							MdlPackage.Literals.ARGUMENTS__NAMED_ARGUMENTS, MSG_ATTRIBUTE_MISSING, prefix + attr.getName());
					}
				}
			}
		}
	}
	
	//Check that each defined argument is from the recognized set of list attributes
	private void checkDefinedAttributes(Argument argument, Arguments args , List<Attribute> knownAttributes, List<String> attributeNames){
		if (!attributeNames.contains(argument.getArgumentName().getName())){
			warning(MSG_ATTRIBUTE_UNKNOWN + ": " + argument.getArgumentName().getName(), 
			MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME,
			MSG_ATTRIBUTE_UNKNOWN, argument.getArgumentName().getName());		
		}
		for (Attribute x: knownAttributes){
			if (x.getName().equals(argument.getArgumentName().getName())){
				boolean isValid = false;
				for (MdlDataType type: x.getTypes())
					isValid = isValid || MdlDataType.validateType(type, argument.getExpression());
				if (!isValid){
					warning(MSG_ATTRIBUTE_WRONG_TYPE + 
						": attribute \"" + argument.getArgumentName().getName() + "\" expects value of type " + 
							Utils.printList(x.getTypeNames()), 
						MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME,
						MSG_ATTRIBUTE_WRONG_TYPE, argument.getArgumentName().getName());
				}
				//Check dependency conditions for optional attributes
				if (!x.isMandatory() && (x.getDependency() != null)){
					String value = MdlPrinter.getInstance().getAttribute(args, x.getDependency().getAttrName());
					if (!x.getDependency().getValues().contains(value)){
						warning(MSG_ATTRIBUTE_WRONG_VALUE + 
								": attribute \"" + x.getName() + "\" expects the attribute \"" + 
								x.getDependency().getAttrName() + "\" have of the following values: " +
								Utils.printList(x.getDependency().getValues()), 
								MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME,
								MSG_ATTRIBUTE_WRONG_VALUE, x.getName());
					}
					
				}
			}
		}				
	}
	
	//Check that each attribute is defined once
	private void checkDefinedOnce(Arguments args){
		HashSet<String> argumentNames = new HashSet<String>();	
		if (args.getNamedArguments() != null){
			for (Argument arg: args.getNamedArguments().getArguments()){
				if (!argumentNames.contains(arg.getArgumentName().getName())){
					argumentNames.add(arg.getArgumentName().getName());
				} else {
					warning(MSG_ATTRIBUTE_DEFINED + ": " + arg.getArgumentName().getName(), 
							MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME, MSG_ATTRIBUTE_DEFINED, 
							arg.getArgumentName().getName());				
				}
			}		
		}
	}	
	
	//Do not validate arguments in distributions (this is the job of DistributionValidator) 
	//In nested lists and in functions (this is the job of FunctionValidator)
	private Boolean skipAttributeValidation(EObject container, Arguments args){
		if (container == null) return true;
		//Skip attributes of distributions, functions
		EObject container1 = args.eContainer();
		if (container1 instanceof RandomListImpl || 
			container1 instanceof FunctionCallImpl) return true;
		//Skip everything apart from attributes for symbol declarations
		EObject container2 = container1.eContainer();
		if (container2 instanceof SymbolDeclarationImpl) return false;
		return true;
	}
	
	//Look for the parent block containing lists with attributes
	public static EObject findAttributeContainer(EObject obj){
		EObject container = obj;
		while (!isAttributeContainer(container)){
			if (container instanceof MclObjectImpl) return null;
			container = container.eContainer();
		}
		return container;
	}	
	
	//Determines the container block for a given object
	private static Boolean isAttributeContainer(EObject obj){
		return (
			//Data object	
			obj instanceof DataInputBlockImpl ||
			obj instanceof DataDerivedBlockImpl ||
			//Model object
			obj instanceof CovariateDefinitionBlockImpl ||
			obj instanceof VariabilityDefinitionBlockImpl ||
			obj instanceof IndividualVariablesBlockImpl ||
			obj instanceof LibraryBlockImpl ||
			obj instanceof OdeBlockImpl || 
			obj instanceof PkMacroBlockImpl ||
			obj instanceof EstimationBlockImpl ||
			obj instanceof SimulationBlockImpl ||
			obj instanceof ObservationBlockImpl ||
			obj instanceof StructuralParametersBlockImpl || 
			obj instanceof VariabilityParametersBlockImpl ||
			obj instanceof RandomVariableDefinitionBlockImpl ||
			//Parameter object
			obj instanceof StructuralBlockImpl ||
			obj instanceof VariabilityBlockImpl ||
			//Design object
			obj instanceof StudyDesignBlockImpl ||
			obj instanceof AdministrationBlockImpl || 
			obj instanceof ActionBlockImpl ||
			obj instanceof SamplingBlockImpl ||
			obj instanceof DesignSpaceBlockImpl ||
			obj instanceof HyperSpaceBlockImpl);
	}
}
