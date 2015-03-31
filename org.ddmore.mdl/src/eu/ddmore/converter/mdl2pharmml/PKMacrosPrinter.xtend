package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.FunctionCall
import eu.ddmore.converter.mdlprinting.MdlPrinter
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.mdl.PkMacroType
import org.ddmore.mdl.mdl.Arguments
import org.ddmore.mdl.mdl.List

class PKMacrosPrinter{
	extension MdlPrinter mdlPrinter = MdlPrinter::getInstance();
	protected extension MathPrinter mathPrinter = null;
	protected extension ReferenceResolver resolver = null;
	
	protected new(MathPrinter mathPrinter, ReferenceResolver resolver){
		this.mathPrinter = mathPrinter;
		this.resolver = resolver;	
	}
	
	//PKMACRO block
	def print_PKMAcros(List list){
		var res = "";
		if (list != null){
			var macro = list.arguments.getAttribute(AttributeValidator::attr_macro.name);
			var content = list.arguments.print_PKAttributes;
			switch(macro){
				case PkMacroType::COMPARTMENT.toString: 
					res = '''
						<Compartment>
							«content»
						</Compartment>
					'''
				case PkMacroType::IV.toString: res  = '''
						<IV>
							«content»
						</IV>
					'''
				case PkMacroType::ELIMINATION.toString: res = '''
						<Elimination>
							«content»
						</Elimination>
					'''
				case PkMacroType::ORAL.toString: res = '''
						<Oral>
							«content»
						</Oral>
					'''
				case PkMacroType::PERIPHERAL.toString: res = '''
						<Periferal>
							«content»
						</Periferal>
					'''
			}
		}	
		'''
			<PKmacros>
			  	«res»
			</PKmacros>
		'''
	}
	
	//Convert MDL PK macro attributes to PharmML
	protected def print_PKAttributes(Arguments args){
		var res = "";
		if (args.namedArguments != null){
			for (a: args.namedArguments){
				var attrName = "";
				if (a.argumentName != null){
					attrName = a.argumentName.name;
					//type -> cmt
					if (a.argumentName.name.equals(AttributeValidator::attr_type_nat.name))
						attrName = "cmt";
					if (a.argumentName.name.equals(AttributeValidator::attr_macro.name))
						attrName = "#SKIP#";	
				}				
				if (!attrName.equals("#SKIP#")){
					res = res + '''
						<Value«IF attrName.length > 0» argument="«attrName»"«ENDIF»> 
							«a.expression.print_Math_Expr»
						</Value>
					'''
				}
			}
		}
		return res;
	} 
	
	///////////////////////////////////////////
	//PK library
	///////////////////////////////////////////
	def print_PKMacros(FunctionCall call){ 
		var res = "";
		if (res.length > 0)
		res = res + '''
			<PKmacros>
			  	«res»
			</PKmacros>
		'''
		return res;
	}
}