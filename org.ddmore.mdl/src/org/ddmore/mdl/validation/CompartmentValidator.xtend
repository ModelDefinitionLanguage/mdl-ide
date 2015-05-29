package org.ddmore.mdl.validation

import org.eclipse.xtext.validation.Check
import org.ddmore.mdl.mdl.PkMacroBlock
import org.eclipse.xtext.validation.AbstractDeclarativeValidator
import org.ddmore.mdl.mdl.List
import org.ddmore.mdl.mdl.SymbolDeclaration

class CompartmentValidator extends AbstractDeclarativeValidator{


	@Check
	def checkCompartmentAttributes(PkMacroBlock macroBlock){
		for(stmt : macroBlock.statements){
			if(stmt.variable != null){
				if(stmt.variable.list != null){
					stmt.variable.list.validateCompartmentList
					stmt.variable.validateNamedMacro(stmt.list)
				}
			}
			else if(stmt.list != null) {
				stmt.list.validateCompartmentList
			}
		}
	}
	
	def void validateNamedMacro(SymbolDeclaration declaration, List list){
		
	}

	
	def void getValidateCompartmentList(List list){
		
	}
	
}