package eu.ddmore.converter.mdl2pharmml

import eu.ddmore.converter.mdlprinting.MdlPrinter
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.mdl.PkMacroType
import org.ddmore.mdl.mdl.Arguments
import org.ddmore.mdl.mdl.List
import org.ddmore.mdl.mdl.SymbolDeclaration

class PKMacrosPrinter{
	extension MdlPrinter mdlPrinter = MdlPrinter::getInstance();
	protected extension MathPrinter mathPrinter = null;
	protected extension ReferenceResolver resolver = null;
	
	protected new(MathPrinter mathPrinter, ReferenceResolver resolver){
		this.mathPrinter = mathPrinter;
		this.resolver = resolver;	
	}
	
	//PKMACRO / COMPARTMENT block
	def print_PKMAcros(SymbolDeclaration s){
		//Convert symbolName to 'amount' PharmML attribute
		var content = "";
		if (s.symbolName != null){
			content = content + '''
				<Value argument="amount"> 
					«s.symbolName.print_ct_SymbolRef»
				</Value>
			'''
		}
		if (s.list != null){
			var macro = s.list.arguments.getAttribute(AttributeValidator::attr_type_macro.name);
			content = content + s.list.arguments.print_PKAttributes;
			return macro.print_PKMAcros(content);
		}
		return "";
	}
	
	def print_PKMAcros(List list){
		if (list != null){
			var macro = list.arguments.getAttribute(AttributeValidator::attr_type_macro.name);
			var content = list.arguments.print_PKAttributes;
			return macro.print_PKMAcros(content);
		}
		return "";
	}
	
	def print_PKMAcros(String type, String content){
		var macroType = type.substring(0, 1).toUpperCase() + type.substring(1);
		if (type.equals(PkMacroType::DIRECT.toString)) 
			macroType = "IV";
		'''
			<PKmacros>
			  	<«macroType»>
			  		«content»
			  	</«macroType»>
			</PKmacros>
		'''
	}
	
	//Convert MDL PK macro attributes to PharmML
	protected def print_PKAttributes(Arguments args){
		var res = "";
		if (args.namedArguments != null){
			for (a: args.namedArguments.arguments){
				var attrName = "";
				if (a.argumentName != null){
					attrName = a.argumentName.name;
					//type -> cmt
					if (a.argumentName.name.equals(AttributeValidator::attr_modelCmt.name))
						attrName = "cmt";
					if (a.argumentName.name.equals(AttributeValidator::attr_type_macro.name))
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
}