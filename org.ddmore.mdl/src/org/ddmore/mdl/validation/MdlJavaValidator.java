/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to validate MDL specifications: checks references
 */
package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.ddmore.mdl.domain.Variable;
import org.ddmore.mdl.mdl.*;
import org.ddmore.mdl.mdl.impl.FunctionCallStatementImpl;
import org.ddmore.mdl.mdl.impl.OutputVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.SymbolDeclarationImpl;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
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
	public final static String MSG_UNRESOLVED_FUNC_ARGUMENT_REF = "Unresolved reference to a function output parameter";
	public final static String MSG_UNRESOLVED_ATTRIBUTE_REF     = "Unresolved reference to an attribute";
	
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

	@Check
	public void checkReferenceToAttribute(FullyQualifiedArgumentName ref) {
		//Skip if the reference is to the symbol with assigned expression which is a function call
		if (checkReferenceToFuctionOutput(ref)) return;
		List<Argument> args = new LinkedList<Argument>();
		TreeIterator<EObject> iterator = ref.eResource().getAllContents();
	    while (iterator.hasNext()){
	    	EObject obj = iterator.next();
	    	if (obj instanceof SymbolDeclarationImpl){
	    		SymbolDeclaration s = (SymbolDeclaration) obj;
	    		if (s.getSymbolName() != null){
		    		if (s.getSymbolName().getName().equals(ref.getParent().getName())) {
		    			if (s.getList() != null){
		    				Arguments arguments = s.getList().getArguments();
		       				if (arguments != null)
			       				for (Argument x: arguments.getArguments())
	           						args.add(x);
		    			}
		    			if (s.getRandomList() != null){
		    				Arguments arguments = s.getRandomList().getArguments();
		       				if (arguments != null)
			       				for (Argument x: arguments.getArguments())
	           						args.add(x);
		    			}
		    		}
	    		}
	    	}
	    }
	    if ((args.size() > 0) && !checkReferenceToAttribute(ref, args))
			warning(MSG_UNRESOLVED_ATTRIBUTE_REF, 
				MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS,
					MSG_UNRESOLVED_ATTRIBUTE_REF, ref.getParent().getName());
	}
	
	//Check references to list attributes
	public boolean checkReferenceToAttribute(FullyQualifiedArgumentName ref, List<Argument> arguments) {
		//Skip attribute declarations
		if (ref.eContainer() instanceof SymbolDeclarationImpl) return true;
		
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
	public boolean checkReferenceToFuctionOutput(FullyQualifiedArgumentName ref) {
		TreeIterator<EObject> iterator = ref.eResource().getAllContents();
		ArrayList<String> params = new ArrayList<String>();
	    while (iterator.hasNext()){
	    	EObject obj = iterator.next();
	    	if (obj instanceof FunctionCallStatementImpl){
	    		FunctionCallStatement s = (FunctionCallStatement) obj;
	    		if (s.getSymbolName() != null && s.getSymbolName().getName().equals(ref.getParent().getName())) {	    			
	    			//Compare reference with returned variables of functions or libraries
	    			String functName = s.getExpression().getIdentifier().getName();
	    			if (FunctionValidator.libraries.contains(functName))
	    				params.addAll(FunctionValidator.standardFunctions.get(functName).getReturnedVariableNames(s.getExpression().getArguments()));
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
	    	}
	    }
	    return false;
	}
}
