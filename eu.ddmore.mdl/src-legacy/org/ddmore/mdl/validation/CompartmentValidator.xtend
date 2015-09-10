package org.ddmore.mdl.validation

import org.ddmore.mdl.mdl.DataInputBlock
import org.eclipse.xtext.validation.AbstractDeclarativeValidator
import org.eclipse.xtext.validation.Check

class DataInpurVariablesValidator extends AbstractDeclarativeValidator{


	@Check
	def checkCompartmentAttributes(DataInputBlock macroBlock){
		for(stmt : macroBlock.variables){
						
		}
	}
	
	
}