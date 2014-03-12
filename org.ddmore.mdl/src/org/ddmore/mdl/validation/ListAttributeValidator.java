/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to validate MDL specifications: checks generic list attributes for various blocks
 */
package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.impl.AnyExpressionImpl;
import org.ddmore.mdl.mdl.impl.ArgumentsImpl;
import org.ddmore.mdl.mdl.impl.BlockStatementImpl;
import org.ddmore.mdl.mdl.impl.DesignBlockStatementImpl;
import org.ddmore.mdl.mdl.impl.DiagBlockImpl;
import org.ddmore.mdl.mdl.impl.FileBlockStatementImpl;
import org.ddmore.mdl.mdl.impl.HeaderBlockImpl;
import org.ddmore.mdl.mdl.impl.ImportedFunctionImpl;
import org.ddmore.mdl.mdl.impl.InputVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.LibraryBlockImpl;
import org.ddmore.mdl.mdl.impl.ListImpl;
import org.ddmore.mdl.mdl.impl.MatrixBlockImpl;
import org.ddmore.mdl.mdl.impl.OdeBlockImpl;
import org.ddmore.mdl.mdl.impl.OdeListImpl;
import org.ddmore.mdl.mdl.impl.ParameterDeclarationImpl;
import org.ddmore.mdl.mdl.impl.SameBlockImpl;
import org.ddmore.mdl.mdl.impl.StructuralBlockImpl;
import org.ddmore.mdl.mdl.impl.SymbolDeclarationImpl;
import org.ddmore.mdl.mdl.impl.SymbolModificationImpl;
import org.ddmore.mdl.mdl.impl.TargetBlockImpl;
import org.ddmore.mdl.mdl.impl.VariabilityBlockStatementImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;

import com.google.inject.Inject;

public class ListAttributeValidator extends AbstractDeclarativeValidator{

	@Override
    @Inject
    public void register(EValidatorRegistrar registrar) {}
	
	public final static String MSG_ATTRIBUTE_UNKNOWN    = "Unknown attribute";
	public final static String MSG_ATTRIBUTE_MISSING    = "Required attribute is missing";
	public final static String MSG_ATTRIBUTE_DEFINED    = "Attribute defined more than once";
	public final static String MSG_ATTRIBUTE_WRONG_TYPE = "Type error";

	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	final static Attribute attr_name = new Attribute("name", DataType.TYPE_STRING, true);	
	
	final static Attribute attr_req_value = new Attribute("value", DataType.TYPE_REAL, true);

	final static Attribute attr_value = new Attribute("value", DataType.TYPE_REAL, false);
	final static Attribute attr_hi = new Attribute("hi", DataType.TYPE_REAL, false);
	final static Attribute attr_lo = new Attribute("lo", DataType.TYPE_REAL, false);
	
	final static Attribute attr_fix = new Attribute("fix", DataType.TYPE_BOOLEAN, false);
	final static Attribute attr_units = new Attribute("units", DataType.TYPE_STRING, false);
	final static Attribute attr_transform = new Attribute("transform", DataType.TYPE_STRING, false);
	final static Attribute attr_use = new Attribute("use", DataType.TYPE_USE, false);
	final static Attribute attr_level = new Attribute("level", DataType.TYPE_ID, false);

	final static Attribute attr_req_cc_type = new Attribute("type", DataType.TYPE_CC, true);
	final static Attribute attr_cc_type = new Attribute("type", DataType.TYPE_CC, false);
	final static Attribute attr_re_type = new Attribute("type", DataType.TYPE_RANDOM_EFFECT, false);

	/*ODE*/
	final static Attribute attr_req_deriv = new Attribute("deriv", DataType.TYPE_EXPR, true);	
	final static Attribute attr_init = new Attribute("init", DataType.TYPE_EXPR, false);	
	final static Attribute attr_x0 = new Attribute("x0", DataType.TYPE_REAL, false);	
	final static Attribute attr_wrt = new Attribute("wrt", DataType.TYPE_ID, false);	
	
	/*LIBRARY*/
	final static Attribute attr_library = new Attribute("library", DataType.TYPE_ID, false);
	final static Attribute attr_req_model = new Attribute("model", DataType.TYPE_NAT, true);
	final static Attribute attr_param = new Attribute("param", DataType.TYPE_LIST, false);
	final static Attribute attr_ncmt = new Attribute("ncmt", DataType.TYPE_NAT, false); //number of compartments
	final static Attribute attr_distribution = new Attribute("distribution", DataType.TYPE_NAT, false);
	final static Attribute attr_elimination = new Attribute("elimination", DataType.TYPE_STRING, false);
	final static Attribute attr_parameterization = new Attribute("parameterization", DataType.TYPE_STRING, false);
	/*
	 * The "output" attribute is not an official MCL attribute but I think it is needed because
	 * it introduces new variables (important for validation) as opposed to the declarations in "param"
	 * attribute that allows any math expressions - which is ok for input but not for output.
	 * No syntactic distinction between in and out parameters (bad design!)
	 */ 
	final static Attribute attr_output = new Attribute("output", DataType.TYPE_LIST, false);
		
	/*HEADER*/
	final static Attribute attr_define = new Attribute("define", DataType.TYPE_LIST, false);
	final static Attribute attr_recode = new Attribute("recode", DataType.TYPE_LIST, false);
	final static Attribute attr_boundaries = new Attribute("boundaries", DataType.TYPE_VECTOR_REAL, false);
	final static Attribute attr_missing = new Attribute("missing", DataType.TYPE_INT, false);
	
	/*FILE*/
	final static Attribute attr_req_source = new Attribute("source", DataType.TYPE_FILE, true);
	final static Attribute attr_ignore = new Attribute("ignore", DataType.TYPE_STRING, false);
	final static Attribute attr_inputformat = new Attribute("inputformat", DataType.TYPE_INPUT, false);
	
	/*DESIGN*/
	final static Attribute attr_design_source = new Attribute("source", DataType.TYPE_ID, true);
	final static Attribute attr_interp = new Attribute("interp", DataType.TYPE_ID, false);
	final static Attribute attr_idv = new Attribute("idv", DataType.TYPE_IDV, false);

	/*IMPORT*/
	final static Attribute attr_req_target = new Attribute("target", DataType.TYPE_TARGET, true);
	final static Attribute attr_trans = new Attribute("trans", DataType.TYPE_NAT, false);
	
	/*TARGET*/
	final static Attribute attr_location = new Attribute("location", DataType.TYPE_STRING, false);
	final static Attribute attr_first = new Attribute("first", DataType.TYPE_BOOLEAN, false);
	final static Attribute attr_before = new Attribute("before", DataType.TYPE_STRING, false);
	final static Attribute attr_after = new Attribute("after", DataType.TYPE_STRING, false);
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*Parameter object*/
	final static List<Attribute> attrs_structural = Arrays.asList(attr_req_value, attr_lo, attr_hi, attr_fix, attr_units, attr_transform);
	final static List<Attribute> attrs_variability = Arrays.asList(attr_req_value, attr_re_type, attr_fix, attr_units, attr_transform);
	final static List<Attribute> attrs_variability_subblock = Arrays.asList(attr_name, attr_re_type, attr_fix);
	
	/*Model object*/
	final static List<Attribute> attrs_inputVariables = Arrays.asList(attr_value, attr_use, attr_units, attr_cc_type, attr_level);
	final static List<Attribute> attrs_library = Arrays.asList(attr_library, attr_req_model, attr_ncmt, attr_param, attr_output, attr_distribution, attr_elimination, attr_parameterization);
	final static List<Attribute> attrs_ode = Arrays.asList(attr_req_deriv, attr_init, attr_x0, attr_wrt);
	
	//Data object
	final static List<Attribute> attrs_header = Arrays.asList(attr_req_cc_type, attr_define, attr_units, attr_recode, attr_boundaries, attr_missing);
	final static List<Attribute> attrs_file = Arrays.asList(attr_req_source, attr_ignore, attr_inputformat);
	final static List<Attribute> attrs_design = Arrays.asList(attr_design_source, attr_units, attr_interp, attr_idv);
	
	//All blocks
	final static List<Attribute> attrs_import = Arrays.asList(attr_req_target, attr_name, attr_ncmt, attr_trans, attr_param, attr_output);
	final static List<Attribute> attrs_target = Arrays.asList(attr_req_target, attr_location, attr_first, attr_before, attr_after);

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	ArrayList<String> getAllNames(List<Attribute> attrs){
		ArrayList<String> names = new ArrayList<String>();
		for (Attribute attr: attrs){
			names.add(attr.name);
		}
		return names;
	}

	ArrayList<String> getRequiredNames(List<Attribute> attrs){
		ArrayList<String> names = new ArrayList<String>();
		for (Attribute attr: attrs){
			if (attr.mandatory)
				names.add(attr.name);
		}
		return names;
	}

	List<Attribute> getAllAttributes(EObject obj){
		if (obj instanceof StructuralBlockImpl)
			return attrs_structural;
		if (obj instanceof VariabilityBlockStatementImpl)
			return attrs_variability;
		if (obj instanceof MatrixBlockImpl || obj instanceof DiagBlockImpl || obj instanceof SameBlockImpl)
			return attrs_variability_subblock;
		if (obj instanceof InputVariablesBlockImpl)
			return attrs_inputVariables;
		if (obj instanceof LibraryBlockImpl)
			return attrs_library;
		if (obj instanceof OdeBlockImpl)
			return attrs_ode; 
		if (obj instanceof HeaderBlockImpl)
			return attrs_header; 
		if (obj instanceof FileBlockStatementImpl)
			return attrs_file; 
		if (obj instanceof DesignBlockStatementImpl)
			return attrs_design; 
		if (obj instanceof ImportedFunctionImpl)
			return attrs_import;
		if (obj instanceof TargetBlockImpl)
			return attrs_target;
		return null;
	}
	
	List<String> getRequiredAttributeNames(EObject obj){
		if (obj instanceof StructuralBlockImpl)
			return getRequiredNames(attrs_structural);
		if (obj instanceof VariabilityBlockStatementImpl)
			return getRequiredNames(attrs_variability);
		if (obj instanceof MatrixBlockImpl || obj instanceof DiagBlockImpl || obj instanceof SameBlockImpl)
			return getRequiredNames(attrs_variability_subblock);
		if (obj instanceof InputVariablesBlockImpl)
			return getRequiredNames(attrs_inputVariables);
		if (obj instanceof LibraryBlockImpl)
			return getRequiredNames(attrs_library);
		if (obj instanceof OdeBlockImpl)
			return getRequiredNames(attrs_ode); 
		if (obj instanceof HeaderBlockImpl)
			return getRequiredNames(attrs_header); 
		if (obj instanceof FileBlockStatementImpl)
			return getRequiredNames(attrs_file); 
		if (obj instanceof DesignBlockStatementImpl)
			return getRequiredNames(attrs_design); 
		if (obj instanceof ImportedFunctionImpl)
			return getRequiredNames(attrs_import);
		if (obj instanceof TargetBlockImpl)
			return getRequiredNames(attrs_target);
		return null;
	}	

	@Check
	public void checkRequiredArguments(Arguments args){
		EObject container = args.eContainer();
		if (container instanceof ListImpl || container instanceof OdeListImpl)
			container = container.eContainer();
		if (container instanceof AnyExpressionImpl)
			container = container.eContainer();
		if (container instanceof ParameterDeclarationImpl ||
			container instanceof SymbolDeclarationImpl ||
			container instanceof SymbolModificationImpl)
			container = container.eContainer();
		if (container instanceof BlockStatementImpl)
			container = container.eContainer();
		
		//Exclude content of Diag and Matrix check from attribute checks
		if (container instanceof MatrixBlockImpl){
			MatrixBlockImpl block = (MatrixBlockImpl)container;
			if (block.getParameters().equals(args)) return;
		} 
		if(container instanceof DiagBlockImpl){
			DiagBlockImpl block = (DiagBlockImpl)container;
			if (block.getParameters().equals(args)) return;
		}		

		//getRequiredAttributes contains lists of required attributes for each container
		for (String attrName: getRequiredAttributeNames(container)){
			if (!Utils.containsAttribute(args, attrName)) warning(MSG_ATTRIBUTE_MISSING + ": " + attrName, 
				MdlPackage.Literals.ARGUMENTS__ARGUMENTS, MSG_ATTRIBUTE_MISSING, attrName);
		}
	}
	
	@Check
	public void checkAllArguments(Argument argument){
		EObject argContainer = argument.eContainer();	
		if (!(argContainer instanceof ArgumentsImpl)) return;
		EObject container = argContainer.eContainer();
		if (container instanceof ListImpl || container instanceof OdeListImpl)
			container = container.eContainer();
		if (container instanceof AnyExpressionImpl)
			container = container.eContainer();
		if (container instanceof ParameterDeclarationImpl ||
			container instanceof SymbolDeclarationImpl ||
			container instanceof SymbolModificationImpl)
			container = container.eContainer();
		if (container instanceof BlockStatementImpl)
			container = container.eContainer();
		
		Arguments args = (Arguments)argContainer;
		//Exclude content of Diag and Matrix check from attribute checks
		if (container instanceof MatrixBlockImpl){
			MatrixBlockImpl block = (MatrixBlockImpl)container;
			if (block.getParameters().equals(args)) return;
		} 
		if(container instanceof DiagBlockImpl){
			DiagBlockImpl block = (DiagBlockImpl)container;
			if (block.getParameters().equals(args)) return;
		}			
			
		List<Attribute> knownAttributes = getAllAttributes(container);
		if (knownAttributes != null){
			List<String> attributeNames = getAllNames(knownAttributes);
			if (!attributeNames.contains(argument.getArgumentName().getIdentifier())){
				warning(MSG_ATTRIBUTE_UNKNOWN + ": " + argument.getArgumentName().getIdentifier(), 
				MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME,
				MSG_ATTRIBUTE_UNKNOWN, argument.getArgumentName().getIdentifier());		
				return;
			}
			for (Attribute x: knownAttributes){
				if (x.name.equals(argument.getArgumentName().getIdentifier())){
					boolean isValid = DataType.validateType(x.type, argument.getExpression());
					if (!isValid){
						warning(MSG_ATTRIBUTE_WRONG_TYPE + 
							": attribute \"" + argument.getArgumentName().getIdentifier() + "\" expects value of type " + 
								x.type.name(), 
							MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME,
							MSG_ATTRIBUTE_WRONG_TYPE, argument.getArgumentName().getIdentifier());		
					}
				}
			}
		}			
	}
}
