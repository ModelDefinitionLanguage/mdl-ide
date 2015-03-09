package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.FunctionCall
import org.ddmore.mdl.mdl.SymbolDeclaration
import eu.ddmore.converter.mdlprinting.MdlPrinter
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.mdl.PkMacroType
import org.ddmore.mdl.mdl.Arguments

class PKMacrosPrinter{
	extension MdlPrinter mdlPrinter = MdlPrinter::getInstance();
	protected extension MathPrinter mathPrinter = null;
	protected extension ReferenceResolver resolver = null;
	
	protected new(MathPrinter mathPrinter, ReferenceResolver resolver){
		this.mathPrinter = mathPrinter;
		this.resolver = resolver;	
	}
	
	//PKMACRO block
	def print_PKMAcros(SymbolDeclaration s){
		var res = "";
		if (s.symbolName != null && s.list != null){
			var macro = s.list.arguments.getAttribute(AttributeValidator::attr_macro.name);
			switch(macro){
				case PkMacroType::COMPARTMENT: res  = res + s.list.arguments.print_Compartment
				case PkMacroType::IV: res  = res + s.list.arguments.print_IV
			}
		}	
		if (res.length > 0)
		'''
		<PKmacros>
		  	«res»
		</PKmacros>
		'''
	}
	
	def print_Compartment(Arguments args){
		var res = "";
		var cmt = args.getAttribute(AttributeValidator::attr_type_nat.name);	
		cmt.length == 0 cmt = "1";
		res = res + '''
			<Value argument="cmt">
				«cmt.print_ct_Value»
			</Value>
		'''
		var amount = args.getAttribute(AttributeValidator::attr_amount_ref.name);	
		if (amount.length > 0){
			res = res + '''
				<Value argument="amount">
					«amount.print_ct_SymbolRef»
				</Value>
			'''
		}
		var volume = args.getAttribute(AttributeValidator::attr_volume.name);	
		if (volume.length > 0){
			res = res + '''
				<Value argument="volume">
					«volume.print_ct_SymbolRef»
				</Value>
			'''
		}
		if (res.length > 0) 
		res = '''
		  	<Compartment>
		  		«res»
		  	</Compartment>
		'''
		return res;
	}

	def print_IV(Arguments args){
		var res = "";
		var cmt = args.getAttribute(AttributeValidator::attr_type_nat.name);	
		cmt.length == 0 cmt = "1";
		res = res + '''
			<Value argument="cmt">
				«cmt.print_ct_Value»
			</Value>
		'''
		var p = args.getAttribute(AttributeValidator::attr_p.name);	
		if (p.length > 0){
			res = res + '''
				<Value argument="p">
					«p.print_ct_SymbolRef»
				</Value>
			'''
		}
		var tlag = args.getAttribute(AttributeValidator::attr_tlag.name);	
		if (tlag.length > 0){
			res = res + '''
				<Value argument="tlag">
					«tlag.print_ct_SymbolRef»
				</Value>
			'''
		}
		if (res.length > 0) 
			res = res + '''
	  			<IV>
	  				«res»
	  			</IV>
			'''
		return res;
	}
	
	def print_Elimination(Arguments args){
		var res = "";
		var cmt = args.getAttribute(AttributeValidator::attr_type_nat.name);	
		cmt.length == 0 cmt = "1";
		res = res + '''
			<Value argument="cmt">
				«cmt.print_ct_Value»
			</Value>
		'''
		var k = args.getAttribute(AttributeValidator::attr_k.name);	
		if (k.length > 0){
			res = res + '''
				<Value argument="k">
					«k.print_ct_SymbolRef»
				</Value>
			'''
		}
		if (res.length > 0) 
			res = res + '''
	  			<Elimination>
	  				«res»
	  			</Elimination>
			'''
		return res;
	}
	
	///////////////////////////////////////////
	//PK library
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