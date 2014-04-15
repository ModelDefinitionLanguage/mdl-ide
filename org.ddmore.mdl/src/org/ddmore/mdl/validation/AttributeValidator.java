/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to validate MDL specifications: checks generic list attributes for various blocks
 */
package org.ddmore.mdl.validation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.impl.ArgumentsImpl;
import org.ddmore.mdl.mdl.impl.DataInputBlockImpl;
import org.ddmore.mdl.mdl.impl.DesignBlockImpl;
import org.ddmore.mdl.mdl.impl.DiagBlockImpl;
import org.ddmore.mdl.mdl.impl.EstimationBlockImpl;
import org.ddmore.mdl.mdl.impl.FileBlockImpl;
import org.ddmore.mdl.mdl.impl.ImportBlockImpl;
import org.ddmore.mdl.mdl.impl.InputVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.LibraryBlockImpl;
import org.ddmore.mdl.mdl.impl.MatrixBlockImpl;
import org.ddmore.mdl.mdl.impl.ObservationBlockImpl;
import org.ddmore.mdl.mdl.impl.OdeBlockImpl;
import org.ddmore.mdl.mdl.impl.SameBlockImpl;
import org.ddmore.mdl.mdl.impl.SimulationBlockImpl;
import org.ddmore.mdl.mdl.impl.StructuralBlockImpl;
import org.ddmore.mdl.mdl.impl.TargetBlockImpl;
import org.ddmore.mdl.mdl.impl.VariabilityBlockImpl;
import org.ddmore.mdl.types.DefaultValues;
import org.ddmore.mdl.types.MdlDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;

import com.google.inject.Inject;

public class AttributeValidator extends AbstractDeclarativeValidator{

	@Override
    @Inject
    public void register(EValidatorRegistrar registrar) {}
	
	public final static String MSG_ATTRIBUTE_UNKNOWN    = "Unknown attribute";
	public final static String MSG_ATTRIBUTE_MISSING    = "Required attribute is missing";
	public final static String MSG_ATTRIBUTE_DEFINED    = "Attribute defined more than once";
	public final static String MSG_ATTRIBUTE_WRONG_TYPE = "Type error";

	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	final public static Attribute attr_name = new Attribute("name", MdlDataType.TYPE_STRING, true, "");		
	final public static Attribute attr_req_value = new Attribute("value", MdlDataType.TYPE_REAL, true, "0");
	final public static Attribute attr_value = new Attribute("value", MdlDataType.TYPE_REAL, false, "0");

	final public static Attribute attr_hi = new Attribute("hi", MdlDataType.TYPE_REAL, false, "0");
	final public static Attribute attr_lo = new Attribute("lo", MdlDataType.TYPE_REAL, false, "1");
	
	final public static Attribute attr_fix = new Attribute("fix", MdlDataType.TYPE_BOOLEAN, false, "false");
	final public static Attribute attr_units = new Attribute("units", MdlDataType.TYPE_STRING, false, "kg");
	final public static Attribute attr_transform = new Attribute("transform", MdlDataType.TYPE_STRING, false, "");
	final public static Attribute attr_use = new Attribute("use", MdlDataType.TYPE_USE, false, DefaultValues.USE_VAR);
	final public static Attribute attr_level = new Attribute("level", MdlDataType.TYPE_NAT, false, "ID");

	final public static Attribute attr_req_cc_type = new Attribute("type", MdlDataType.TYPE_CC, true, DefaultValues.VARIABILITY_TYPE);
	final public static Attribute attr_cc_type = new Attribute("type", MdlDataType.TYPE_CC, false, DefaultValues.VARIABILITY_TYPE);
	final public static Attribute attr_re_type = new Attribute("type", MdlDataType.TYPE_RANDOM_EFFECT, false);
	
	final public static Attribute attr_mapping = new Attribute("mapping", MdlDataType.TYPE_REF, false);
	
	/*ESTIMATION*/
	final public static Attribute attr_likelihood = new Attribute("likelihood", MdlDataType.TYPE_EXPR, false);
	final public static Attribute attr_prediction = new Attribute("prediction", MdlDataType.TYPE_EXPR, false);
	final public static Attribute attr_ruv = new Attribute("ruv", MdlDataType.TYPE_EXPR, false);
	
	/*ODE*/
	final public static Attribute attr_req_deriv = new Attribute("deriv", MdlDataType.TYPE_EXPR, true, DefaultValues.VAR_NAME);	
	final public static Attribute attr_init = new Attribute("init", MdlDataType.TYPE_EXPR, false, "0");	
	final public static Attribute attr_x0 = new Attribute("x0", MdlDataType.TYPE_REAL, false);	
	final public static Attribute attr_wrt = new Attribute("wrt", MdlDataType.TYPE_REF, false, "TIME");	
	
	/*LIBRARY*/
	final public static Attribute attr_library = new Attribute("library", MdlDataType.TYPE_REF, false);
	final public static Attribute attr_req_model = new Attribute("model", MdlDataType.TYPE_NAT, true, "1");
	final public static Attribute attr_param = new Attribute("param", MdlDataType.TYPE_LIST, false);
	final public static Attribute attr_ncmt = new Attribute("ncmt", MdlDataType.TYPE_NAT, false); //number of compartments
	final public static Attribute attr_distribution = new Attribute("distribution", MdlDataType.TYPE_NAT, false);
	final public static Attribute attr_elimination = new Attribute("elimination", MdlDataType.TYPE_STRING, false);
	final public static Attribute attr_parameterization = new Attribute("parameterization", MdlDataType.TYPE_STRING, false);
	final public static Attribute attr_output = new Attribute("output", MdlDataType.TYPE_LIST, false);
	/* The "output" attribute is not an official MCL attribute but I think it is needed because
	 * it introduces new variables (important for validation) as opposed to the declarations in "param"
	 * attribute that allows any math expressions - which is ok for input but not for output.
	 * No syntactic distinction between in and out parameters (bad design!)	 */ 
		
	/*Data object*/
	/*DATA_INPUT_VARIABLES*/
	final public static Attribute attr_define = new Attribute("define", MdlDataType.TYPE_LIST, false);
	final public static Attribute attr_recode = new Attribute("recode", MdlDataType.TYPE_LIST, false);
	final public static Attribute attr_boundaries = new Attribute("boundaries", MdlDataType.TYPE_VECTOR_REAL, false);
	final public static Attribute attr_missing = new Attribute("missing", MdlDataType.TYPE_INT, false);
	//
	final public static Attribute attr_female = new Attribute("female", MdlDataType.TYPE_INT, false);
	final public static Attribute attr_male = new Attribute("male", MdlDataType.TYPE_INT, false);
	
	/*FILE*/
	final public static Attribute attr_req_source = new Attribute("source", MdlDataType.TYPE_STRING, true, DefaultValues.FILE_NAME);
	final public static Attribute attr_ignore = new Attribute("ignore", MdlDataType.TYPE_STRING, false);
	final public static Attribute attr_inputformat = new Attribute("inputformat", MdlDataType.TYPE_TARGET, false, DefaultValues.TARGET);
	final public static Attribute attr_delimeter = new Attribute("delimeter", MdlDataType.TYPE_STRING, false, ";");
	
	/*DESIGN*/
	final public static Attribute attr_design_source = new Attribute("source", MdlDataType.TYPE_REF, true, DefaultValues.VAR_NAME);
	final public static Attribute attr_interp = new Attribute("interp", MdlDataType.TYPE_INTERP, false);
	//final public static Attribute attr_idv = new Attribute("idv", MdlDataType.TYPE_IDV, false);

	/*All objects*/
	/*IMPORT*/
	final public static Attribute attr_req_target = new Attribute("target", MdlDataType.TYPE_TARGET, true, DefaultValues.TARGET);
	final public static Attribute attr_trans = new Attribute("trans", MdlDataType.TYPE_NAT, false);
	
	/*TARGET*/
	final public static Attribute attr_location = new Attribute("location", MdlDataType.TYPE_STRING, false);
	final public static Attribute attr_first = new Attribute("first", MdlDataType.TYPE_BOOLEAN, false);
	final public static Attribute attr_before = new Attribute("before", MdlDataType.TYPE_STRING, false);
	final public static Attribute attr_after = new Attribute("after", MdlDataType.TYPE_STRING, false);
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*Data object*/
	final public static List<Attribute> attrs_header = Arrays.asList(attr_req_cc_type, attr_define, attr_units, 
			attr_recode, attr_boundaries, attr_missing, attr_mapping, attr_female, attr_male);
	final public static List<Attribute> attrs_file = Arrays.asList(attr_req_source, attr_ignore, attr_inputformat, attr_delimeter);
	final public static List<Attribute> attrs_design = Arrays.asList(attr_design_source, attr_units, attr_interp /*,attr_idv*/);

	/*Parameter object*/
	final public static List<Attribute> attrs_structural = Arrays.asList(attr_req_value, attr_lo, attr_hi, attr_fix, attr_units, attr_transform);
	final public static List<Attribute> attrs_variability = Arrays.asList(attr_req_value, attr_re_type, attr_fix, attr_units, attr_transform);
	final public static List<Attribute> attrs_variability_subblock = Arrays.asList(attr_name, attr_re_type, attr_fix);
	
	/*Model object*/
	final public static List<Attribute> attrs_inputVariables = Arrays.asList(attr_value, attr_use, attr_units, attr_cc_type, attr_level);
	final public static List<Attribute> attrs_library = Arrays.asList(attr_library, attr_req_model, attr_ncmt, attr_param, attr_output, attr_distribution, attr_elimination, attr_parameterization);
	final public static List<Attribute> attrs_ode = Arrays.asList(attr_req_deriv, attr_init, attr_x0, attr_wrt);
	final public static List<Attribute> attrs_estimation = Arrays.asList(attr_cc_type, attr_likelihood, attr_prediction, attr_ruv);
	final public static List<Attribute> attrs_simulation = Arrays.asList();
	final public static List<Attribute> attrs_observation = Arrays.asList();

	/*All blocks*/
	final public static List<Attribute> attrs_import = Arrays.asList(attr_req_target, attr_name, attr_ncmt, attr_trans, attr_param, attr_output);
	final public static List<Attribute> attrs_target = Arrays.asList(attr_req_target, attr_location, attr_first, attr_before, attr_after);
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//TODO: find how to substitute with identifiers in grammar (statically)
	final public static HashMap<String, Attribute> allAttributes = new HashMap<String, Attribute>(){
		private static final long serialVersionUID = -4512048801509444272L;
		{
			/*Data object*/
			for (Attribute attr: attrs_header) put("DATA_INPUT_VARIABLES:" + attr.name, attr);
			for (Attribute attr: attrs_file) put("FILE:" + attr.name, attr);
			for (Attribute attr: attrs_design) put("DESIGN:" + attr.name, attr);
			/*Parameter object*/
			for (Attribute attr: attrs_structural) put("STRUCTURAL:" + attr.name, attr);
			for (Attribute attr: attrs_variability) put("VARIABILITY:" + attr.name, attr);
			for (Attribute attr: attrs_variability_subblock){
				put("matrix:" + attr.name, attr);
				put("diag:" + attr.name, attr);
				put("same:" + attr.name, attr);
			}
			/*Model object*/
			for (Attribute attr: attrs_inputVariables) put("MODEL_INPUT_VARIABLES:" + attr.name, attr);
			for (Attribute attr: attrs_library) put("LIBRARY:" + attr.name, attr);
			for (Attribute attr: attrs_ode) put("ODE:" + attr.name, attr);
			for (Attribute attr: attrs_estimation) put("ESTIMATION:" + attr.name, attr);
			for (Attribute attr: attrs_simulation) put("SIMULATION:" + attr.name, attr);
			for (Attribute attr: attrs_observation) put("OBSERVATION:" + attr.name, attr);
			/*All objects*/
			for (Attribute attr: attrs_import) put("IMPORT:" + attr.name, attr);
			for (Attribute attr: attrs_target) put("TARGET_CODE:" + attr.name, attr);
		}
	};
		
	public static List<Attribute> getAllAttributes(EObject obj){
		/*Data object*/
		if (obj instanceof DataInputBlockImpl) return attrs_header; 
		if (obj instanceof FileBlockImpl) return attrs_file; 
		if (obj instanceof DesignBlockImpl) return attrs_design; 
		/*Parameter object*/
		if (obj instanceof StructuralBlockImpl) return attrs_structural;
		if (obj instanceof VariabilityBlockImpl) return attrs_variability;
		if (obj instanceof MatrixBlockImpl || obj instanceof DiagBlockImpl || obj instanceof SameBlockImpl) return attrs_variability_subblock;
		/*Model object*/
		if (obj instanceof InputVariablesBlockImpl) return attrs_inputVariables;
		if (obj instanceof LibraryBlockImpl) return attrs_library;
		if (obj instanceof OdeBlockImpl) return attrs_ode; 
		if (obj instanceof EstimationBlockImpl) return attrs_estimation; 
		if (obj instanceof SimulationBlockImpl) return attrs_simulation; 
		if (obj instanceof ObservationBlockImpl) return attrs_observation; 
		/*All objects*/
		if (obj instanceof ImportBlockImpl) return attrs_import;
		if (obj instanceof TargetBlockImpl) return attrs_target;
		return null;
	}
	
	public static List<String> getRequiredAttributeNames(EObject obj){
		List<Attribute> attrs = getAllAttributes(obj);
		if (attrs != null) return Utils.getRequiredNames(attrs);
		return null;
	}	
	
	public static Attribute getAttributeById(String id){
        return allAttributes.get(id);
    }

	@Check
	public void checkRequiredArguments(Arguments args){
		EObject container = Utils.findListContainer(args.eContainer());
		if (container == null) return;
		if (isVariabilitySubblock(container, args)) return;
		String prefix = Utils.getBlockName(container) + ":";
		
		HashSet<String> argumentNames = new HashSet<String>();	
		for (Argument arg: args.getArguments()){
			if (!argumentNames.contains(arg.getArgumentName().getName())){
				argumentNames.add(arg.getArgumentName().getName());
			} else {
				warning(MSG_ATTRIBUTE_DEFINED + ": " + arg.getArgumentName().getName(), 
						MdlPackage.Literals.ARGUMENTS__ARGUMENTS, MSG_ATTRIBUTE_DEFINED, 
						prefix + arg.getArgumentName().getName());				
			}
		}
		//getRequiredAttributes contains lists of required attributes for each container
		for (String attrName: getRequiredAttributeNames(container)){
			if (!argumentNames.contains(attrName)) 
				warning(MSG_ATTRIBUTE_MISSING + ": " + attrName, 
				MdlPackage.Literals.ARGUMENTS__ARGUMENTS, MSG_ATTRIBUTE_MISSING, prefix + attrName);
		}
	}
	
	@Check
	public void checkAllArguments(Argument argument){
		EObject argContainer = argument.eContainer();	
		EObject container = Utils.findListContainer(argContainer.eContainer());
		if (container == null) return;

		if (!(argContainer instanceof ArgumentsImpl)) return;
		Arguments args = (Arguments)argContainer;
		if (isVariabilitySubblock(container, args)) return;
		
		List<Attribute> knownAttributes = getAllAttributes(container);
		if (knownAttributes != null){
			List<String> attributeNames = Utils.getAllNames(knownAttributes);
			if (!attributeNames.contains(argument.getArgumentName().getName())){
				warning(MSG_ATTRIBUTE_UNKNOWN + ": " + argument.getArgumentName().getName(), 
				MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME,
				MSG_ATTRIBUTE_UNKNOWN, argument.getArgumentName().getName());		
				return;
			}
			for (Attribute x: knownAttributes){
				if (x.name.equals(argument.getArgumentName().getName())){
					boolean isValid = MdlDataType.validateType(x.type, argument.getExpression());
					if (!isValid){
						warning(MSG_ATTRIBUTE_WRONG_TYPE + 
							": attribute \"" + argument.getArgumentName().getName() + "\" expects value of type " + x.type.name(), 
							MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME,
							MSG_ATTRIBUTE_WRONG_TYPE, argument.getArgumentName().getName());		
					}
				}
			}
		}			
	}
	
	//Do not validate parameters of variability subblocks
	private Boolean isVariabilitySubblock(EObject container, Arguments args){
		//Exclude content of Diag and Matrix check from attribute checks
		if (container instanceof MatrixBlockImpl){
			MatrixBlockImpl block = (MatrixBlockImpl)container;
			if (block.getParameters().equals(args)) return true;
		} 
		if(container instanceof DiagBlockImpl){
			DiagBlockImpl block = (DiagBlockImpl)container;
			if (block.getParameters().equals(args)) return true;
		}	
		//Same - content does not contain arguments
		return false;
	}
}
