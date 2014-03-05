/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to validate MDL specifications: checks generic list attributes for various blocks
 */
package org.ddmore.mdl.validation;

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
import org.ddmore.mdl.mdl.impl.RandomVariableDefinitionBlockImpl;
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
	
	public final static String MSG_ATTRIBUTE_UNKNOWN = "Unknown attribute";
	public final static String MSG_ATTRIBUTE_MISSING = "Required attribute is missing";
	
	//Parameter object
	final static List<String> attr_structural = Arrays.asList("value", "lo", "hi", "fix", "units", "transform");
	final static List<String> attr_req_structural = Arrays.asList("value");

	final static List<String> attr_variability = Arrays.asList("value", "type", "fix", "units", "transform");
	final static List<String> attr_req_variability = Arrays.asList("value");

	final static List<String> attr_variability_subblock = Arrays.asList("name", "type", "fix");
	final static List<String> attr_req_variability_subblock = Arrays.asList("name"); //for "same" block

	//Model object
	final static List<String> attr_inputVariables = Arrays.asList("value", "use", "units", "type", "level");
	
	final static List<String> attr_req_random = Arrays.asList("type");//, "mean", "variance");
	
	final static List<String> attr_library = Arrays.asList("model", "ncmt", "param", "input", "output",
			"distribution", "elimination", "parameterization", "trans");
	final static List<String> attr_req_library = Arrays.asList("model");
	
	final static List<String> attr_ode = Arrays.asList("deriv", "init", "x0", "wrt");
	final static List<String> attr_req_ode = Arrays.asList("deriv");	
	
	//Data object
	final static List<String> attr_header = Arrays.asList("type", "define", "units", "recode", "boundaries", "missing");
	final static List<String> attr_req_header = Arrays.asList("type");

	final static List<String> attr_file = Arrays.asList("source", "ignore", "inputformat");
	final static List<String> attr_req_file = Arrays.asList("source");

	final static List<String> attr_design = Arrays.asList("source", "units", "interp", "idv");
	final static List<String> attr_req_design = Arrays.asList("source");
	
	//All blocks
	final static List<String> attr_import = Arrays.asList("target", "name", "ncmt", "trans", "param", "input", "output");
	final static List<String> attr_req_import = Arrays.asList("target", "param");

	final static List<String> attr_target = Arrays.asList("target", "location", "first", "before", "after");
	final static List<String> attr_req_target = Arrays.asList("target");
	
	List<String> getAllAttributes(EObject obj){
		if (obj instanceof StructuralBlockImpl)
			return attr_structural;
		if (obj instanceof VariabilityBlockStatementImpl)
			return attr_variability;
		if (obj instanceof MatrixBlockImpl || obj instanceof DiagBlockImpl || obj instanceof SameBlockImpl)
			return attr_variability_subblock;
		if (obj instanceof InputVariablesBlockImpl)
			return attr_inputVariables;
		if (obj instanceof LibraryBlockImpl)
			return attr_library;
		if (obj instanceof OdeBlockImpl)
			return  attr_ode; 
		if (obj instanceof HeaderBlockImpl)
			return attr_header; 
		if (obj instanceof FileBlockStatementImpl)
			return attr_file; 
		if (obj instanceof DesignBlockStatementImpl)
			return attr_design; 
		if (obj instanceof ImportedFunctionImpl)
			return attr_import;
		if (obj instanceof TargetBlockImpl)
			return attr_target;
		return null;
	}
	
	List<String> getRequiredAttributes(EObject obj){
		if (obj instanceof StructuralBlockImpl)
			return attr_req_structural;
		if (obj instanceof VariabilityBlockStatementImpl)
			return attr_req_variability;
		if (obj instanceof SameBlockImpl)
			return attr_req_variability_subblock;
		if (obj instanceof RandomVariableDefinitionBlockImpl)
			return attr_req_random;
		if (obj instanceof LibraryBlockImpl)
			return attr_req_library;
		if (obj instanceof OdeBlockImpl)
			return attr_req_ode; 
		if (obj instanceof HeaderBlockImpl)
			return attr_req_header; 
		if (obj instanceof FileBlockStatementImpl)
			return attr_req_file; 
		if (obj instanceof DesignBlockStatementImpl)
			return attr_req_design; 
		if (obj instanceof ImportedFunctionImpl)
			return attr_req_import;
		if (obj instanceof TargetBlockImpl)
			return attr_req_target;
		List<String> other = Arrays.asList();
		return other;
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
		for (String attrName: getRequiredAttributes(container)){
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
			
		List<String> knownAttributes = getAllAttributes(container);
		if (knownAttributes != null)
			if (!knownAttributes.contains(argument.getIdentifier()))
				warning(MSG_ATTRIBUTE_UNKNOWN + ": " + argument.getIdentifier(), 
				MdlPackage.Literals.ARGUMENT__IDENTIFIER,
				MSG_ATTRIBUTE_UNKNOWN, argument.getIdentifier());			
	}
}
