package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.FunctionCall

class PKMacrosPrinter{
	private static val PKMacrosPrinter pkMacrosPrinter = new PKMacrosPrinter();
	protected new(){}
	public static def PKMacrosPrinter getInstance(){
		return pkMacrosPrinter;
	}
	
	def print_PKMacros(FunctionCall call) '''
	  <PKmacros>
	  </PKmacros>
	'''

	def print_Compartment(FunctionCall call)
	'''
	  	<Compartment>
	  	</Compartment>
	'''

	def print_IV(FunctionCall call)
	'''
	  	<IV>
	  	</IV>
	'''
	
	def print_Elimination(FunctionCall call)
	'''
	  	<Elimination>
	  	</Elimination>
	'''
}