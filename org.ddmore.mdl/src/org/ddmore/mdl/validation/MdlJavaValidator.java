/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to validate MDL specifications: checks references
 */
package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ddmore.mdl.domain.Variable;
import org.ddmore.mdl.mdl.*;
import org.ddmore.mdl.mdl.impl.OutputVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.SymbolDeclarationImpl;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.ComposedChecks;

@ComposedChecks(validators = { 
		AttributeValidator.class,
		PropertyValidator.class,
		FunctionValidator.class, 
		DistributionValidator.class,		
		UnitValidator.class,
		MOGValidator.class})
public class MdlJavaValidator extends AbstractMdlJavaValidator {

	public final static String MSG_VARIABLE_DEFINED = "A variable with such name already exists";
	public final static String MSG_OBJECT_DEFINED   = "An object with such name already exists";

	public final static String MSG_UNRESOLVED_VARIABLE          = "Unresolved reference: variable not declared";
	public final static String MSG_UNRESOLVED_ATTRIBUTE_REF     = "Unresolved reference to an attribute";
	public final static String MSG_UNRESOLVED_FUNC_ARGUMENT_REF = "Unresolved reference to a function output parameter";
	public final static String MSG_UNRESOLVED_EXTERNAL_VARIABLE = "Unresolved reference to an external variable";
	
	//List of declared variables per object
	Map<String, List<Variable>> declaredVariables = new HashMap<String, List<Variable>>();	

	//List of declared objects
	List<Variable> declaredObjects = new ArrayList<Variable>();	

	//Update the list of recognised variables
	@Check
	public void updateDeclaredVariableList(Mcl mcl){
		declaredVariables = Utils.getDeclaredVariables(mcl);
	}
	
	//Update the list of recognised objects
	@Check
	public void updateDeclaredObjectList(Mcl mcl){
		declaredObjects = Utils.getDeclaredObjects(mcl);
	}
	
	@Check
	public void checkVariableDeclarations(SymbolDeclaration s){
		if (s.getSymbolName() != null){
			ObjectName objName = Utils.getObjectName(s.getSymbolName());
			if (declaredVariables.containsKey(objName.getName())){
				if (Utils.isSymbolDeclaredMoreThanOnce(declaredVariables.get(objName.getName()), s.getSymbolName().getName())){
					warning(MSG_VARIABLE_DEFINED, 
							MdlPackage.Literals.SYMBOL_DECLARATION__SYMBOL_NAME,
							MSG_VARIABLE_DEFINED, s.getSymbolName().getName());
				}
			}
		}
	} 

	@Check
	public void checkObjectDeclarations(MclObject o){
		if (o.getObjectName() != null){
			if (Utils.isSymbolDeclaredMoreThanOnce(declaredObjects, o.getObjectName().getName())){
				warning(MSG_OBJECT_DEFINED, 
						MdlPackage.Literals.SYMBOL_DECLARATION__SYMBOL_NAME,
						MSG_OBJECT_DEFINED, o.getObjectName().getName());
			}
		}
	} 

	//Check that each variable is declared in the local object
	@Check
	public void checkReferenceToVariable(UnaryExpression u) {
		if (u.getSymbol() != null){
			//Skip transformation operators
			if (FunctionValidator.funct_standard1.contains(u.getSymbol().getName())) return;
			
			if (!(Utils.isSymbolDeclared(declaredVariables, u.getSymbol()))){
				warning(MSG_UNRESOLVED_VARIABLE, MdlPackage.Literals.UNARY_EXPRESSION__SYMBOL,
						MSG_UNRESOLVED_VARIABLE, u.getSymbol().getName());
			}
		}
	}

	//Check that each variable is declared in the local object
	@Check
	public void checkReferenceToVariable(SymbolName ref) {
		if (ref.eContainer() instanceof OutputVariablesBlockImpl)
			if (!(Utils.isSymbolDeclared(declaredVariables, ref))){
				warning(MSG_UNRESOLVED_VARIABLE, MdlPackage.Literals.SYMBOL_NAME__NAME,
						MSG_UNRESOLVED_VARIABLE, ref.getName());
			}
	}

	//Check references to attributes (varName.attrName)
	@Check
	public void checkReferenceToAttribute(FullyQualifiedArgumentName ref) {
		//Skip if the reference is to the function call
		if (checkReferenceToFuctionOutput(ref)) return;
		//Skip if the reference is to the imported object
		if (checkReferenceToImportedVariable(ref)) return;
		//Skip attribute declarations
		if (ref.eContainer() instanceof SymbolDeclarationImpl) return;
		
		List<Argument> args = Utils.getListArguments(ref);
	    if ((args.size() > 0) && !checkReferenceToAttribute(ref, args))
			warning(MSG_UNRESOLVED_ATTRIBUTE_REF, 
				MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
					MSG_UNRESOLVED_ATTRIBUTE_REF, ref.getParent().getName());
	}
	
	//Check references to list attributes
	private boolean checkReferenceToAttribute(FullyQualifiedArgumentName ref, List<Argument> arguments) {
		List <Argument> currArgs = arguments; 
		for (Selector x: ref.getSelectors()){
			if (currArgs != null){
				int index = -1;
				if (x.getSelector() != null){
					index = Integer.parseInt(x.getSelector());
					if (!((index >= 1) && (index < currArgs.size() + 1))) return false;
					index = 1;	
				}
				if (x.getArgumentName() != null){
					int i = 0;
					for (Argument arg: currArgs){
						if (arg.getArgumentName().getName().equals(x.getArgumentName().getName())){
							index = i + 1; break;
						}
						i++; 
					}
				}
				if (index > 0) {
					if (currArgs.get(index - 1).getExpression().getList() != null)
						if (arguments.get(index).getExpression().getList().getArguments() != null)
							currArgs = arguments.get(index).getExpression().getList().getArguments().getArguments();
				} else return false;
			} 
		}
		return true;
	}

	//Validate a fully qualified argument whose parent refers to a variable declared as a function 
	private boolean checkReferenceToFuctionOutput(FullyQualifiedArgumentName ref) {
		List<Variable> vars = Utils.getExternalLibraryVariables(ref);
		if (vars != null){
			List<String> params = new ArrayList<String>();
   			for (Variable var: vars)
   				params.add(var.getName());
    		ArgumentName paramRef = ref.getSelectors().get(0).getArgumentName();
	       	if (paramRef != null){
	       		if (!params.contains(paramRef.getName()))
	       			warning(MSG_UNRESOLVED_FUNC_ARGUMENT_REF + ": " + 
	       				paramRef.getName() + " is not in the reference set " + Utils.printList(params), 
	       				MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
	       				MSG_UNRESOLVED_FUNC_ARGUMENT_REF, ref.getParent().getName());
	       	} else {
	       		String selector = ref.getSelectors().get(0).getSelector();
	       		int index = Integer.parseInt(selector);
	       		if (index < 1 || index > params.size())
	       			warning(MSG_UNRESOLVED_FUNC_ARGUMENT_REF + ": " + 
	       				"wrong index [" + index + "]. " + 
	       				"Reference set " + Utils.printList(params) + " contains " + params.size() + " items.", 
	       				MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
	       				MSG_UNRESOLVED_FUNC_ARGUMENT_REF, ref.getParent().getName());
	       	}
	       	return true; //skip list attribute check
	    }
	    return false;
	}
	
	//Validate a fully qualified argument whose parent refers to a variable that represents an external object 
	private boolean checkReferenceToImportedVariable(FullyQualifiedArgumentName ref) {
		List<Variable> vars = Utils.getImportedVariables(ref);
		if (vars != null){
			List<String> params = new ArrayList<String>();
   			for (Variable var: vars)
   				params.add(var.getName());
			ArgumentName paramRef = ref.getSelectors().get(0).getArgumentName();
   			if (paramRef != null){
   				if (!params.contains(paramRef.getName()))
   					warning(MSG_UNRESOLVED_EXTERNAL_VARIABLE + ": " + 
   						paramRef.getName() + " is not in the reference set " + Utils.printList(params), 
   						MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
   						MSG_UNRESOLVED_EXTERNAL_VARIABLE, ref.getParent().getName());
   			} 
   			else 
   				warning(MSG_UNRESOLVED_EXTERNAL_VARIABLE + ": " + 
   					"External variables cannot be addressed by index", 
   					MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
   					MSG_UNRESOLVED_EXTERNAL_VARIABLE, ref.getParent().getName());
   			return true; //skip list attribute check
		}
		return false;
	}
	
}
