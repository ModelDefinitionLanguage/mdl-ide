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
//import org.ddmore.mdl.mdl.impl.OutputVariablesBlockImpl;
import org.eclipse.emf.ecore.EReference;
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
			ObjectName objName = Utils.getObjectName(s);
			if (declaredVariables.containsKey(objName.getName())){
				if (Utils.isSymbolDeclaredMoreThanOnce(declaredVariables.get(objName.getName()), s.getSymbolName().getName())){
					error(MSG_VARIABLE_DEFINED, 
							MdlPackage.Literals.SYMBOL_DECLARATION__SYMBOL_NAME,
							MSG_VARIABLE_DEFINED, s.getSymbolName().getName());
				}
			}
		}
	} 
	
	@Check
	public void checkVariableDeclarations(Category s){
		if (s.getCategoryName() != null){
			ObjectName objName = Utils.getObjectName(s);
			if (declaredVariables.containsKey(objName.getName())){
				if (Utils.isSymbolDeclaredMoreThanOnce(declaredVariables.get(objName.getName()), s.getCategoryName().getName())){
					error(MSG_VARIABLE_DEFINED, 
							MdlPackage.Literals.CATEGORY__CATEGORY_NAME,
							MSG_VARIABLE_DEFINED, s.getCategoryName().getName());
				}
			}
		}
	} 

	@Check
	public void checkObjectDeclarations(MclObject o){
		if (o.getObjectName() != null){
			if (Utils.isSymbolDeclaredMoreThanOnce(declaredObjects, o.getObjectName().getName())){
				warning(MSG_OBJECT_DEFINED, 
						MdlPackage.Literals.MCL_OBJECT__OBJECT_NAME,
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
				error(MSG_UNRESOLVED_VARIABLE, MdlPackage.Literals.UNARY_EXPRESSION__SYMBOL,
						MSG_UNRESOLVED_VARIABLE, u.getSymbol().getName());
			}
		}
	}

	//Check that each variable is declared in the local object
	//@Check
	/*public void checkReferenceToVariable(SymbolName ref) {
		if (ref.eContainer() instanceof OutputVariablesBlockImpl)
			if (!(Utils.isSymbolDeclared(declaredVariables, ref))){
				warning(MSG_UNRESOLVED_VARIABLE, MdlPackage.Literals.SYMBOL_NAME__NAME,
						MSG_UNRESOLVED_VARIABLE, ref.getName());
			}
	}*/

	
	//Validate references to variables in external blocks; 
	@Check
	public void checkReferenceToImportedVariable(MappingBlockStatement st) {
		//Validate references to local variables that define aliases for objects 
		if (!(Utils.isSymbolDeclared(declaredVariables, st.getObj1().getParent())))
			warning(MSG_UNRESOLVED_VARIABLE, MdlPackage.Literals.MAPPING_BLOCK_STATEMENT__OBJ1, MSG_UNRESOLVED_VARIABLE, 
				st.getObj1().getParent().getName());
		if (!(Utils.isSymbolDeclared(declaredVariables, st.getObj2().getParent())))
			warning(MSG_UNRESOLVED_VARIABLE, MdlPackage.Literals.MAPPING_BLOCK_STATEMENT__OBJ2, MSG_UNRESOLVED_VARIABLE, 
				st.getObj2().getParent().getName());
		//Validate references to imported object variables 
		List<Variable> vars1 = Utils.getImportedVariablesByObjectAlias(st.getObj1().getParent());
		List<Variable> vars2 = Utils.getImportedVariablesByObjectAlias(st.getObj2().getParent());
		if (st.getObj1().getSymbolName() != null && st.getObj2().getSymbolName() != null){
			checkImportedVariable(vars1, st.getObj1().getSymbolName(), MdlPackage.Literals.MAPPING_BLOCK_STATEMENT__OBJ1);
			checkImportedVariable(vars2, st.getObj2().getSymbolName(), MdlPackage.Literals.MAPPING_BLOCK_STATEMENT__OBJ2);
		}
	}
	
	private void checkImportedVariable(List<Variable> vars, SymbolName ref, EReference literal){
		List<String> varNames = new ArrayList<String>();
   		if (vars != null) for (Variable var: vars) varNames.add(var.getName());
   		if (!varNames.contains(ref.getName()))
   			warning(MSG_UNRESOLVED_EXTERNAL_VARIABLE + ": " + 
   				ref.getName() + " is not in the set of recognized variables " + Utils.printList(varNames), 
   				literal, MSG_UNRESOLVED_EXTERNAL_VARIABLE, ref.getName());
	}	
}
