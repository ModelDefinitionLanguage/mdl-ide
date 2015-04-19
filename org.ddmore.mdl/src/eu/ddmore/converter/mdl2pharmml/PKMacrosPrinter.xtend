package eu.ddmore.converter.mdl2pharmml

import eu.ddmore.converter.mdlprinting.MdlPrinter
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.mdl.PkMacroType
import org.ddmore.mdl.mdl.Arguments
import org.ddmore.mdl.mdl.List
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.validation.Utils
import org.ddmore.mdl.mdl.ModelObject
import java.util.HashMap

class PKMacrosPrinter{
	extension MdlPrinter mdlPrinter = MdlPrinter::getInstance();
	protected extension MathPrinter mathPrinter = null;
	protected extension ReferenceResolver resolver = null;
	
	protected new(MathPrinter mathPrinter, ReferenceResolver resolver){
		this.mathPrinter = mathPrinter;
		this.resolver = resolver;	
	}
		
	private val pk_types = newHashMap(
		PkMacroType::DIRECT.toString -> "IV",
		PkMacroType::COMPARTMENT.toString -> "Compartment",
		PkMacroType::DISTRIBUTION.toString -> "Peripheral",
		PkMacroType::DEPOT.toString -> "Absorption",
		PkMacroType::TRANSFER.toString -> "Transfer",
		PkMacroType::ELIMINATION.toString -> "Elimination"
	);
	
	private val pk_attrs = newHashMap(
		AttributeValidator::attr_modelCmt.name -> "cmt",
		AttributeValidator::attr_finput.name -> "p",
		AttributeValidator::attr_tk0.name -> "Tk0",
		AttributeValidator::attr_tlag.name -> "Tlag",
		AttributeValidator::attr_v.name -> "v",
		AttributeValidator::attr_cl.name -> "Cl",
		AttributeValidator::attr_ktr.name -> "Ktr",
		AttributeValidator::attr_mtt.name -> "Mtt",
		AttributeValidator::attr_k.name -> "k",
		AttributeValidator::attr_ka.name -> "ka",
		AttributeValidator::attr_vm.name -> "Vm",
		AttributeValidator::attr_km.name -> "Km"
	);
	
	def print_PKMacros(SymbolDeclaration s){
		//Convert symbolName to 'amount' PharmML attribute
		if (s.list != null){
			var content = "";
			if (s.symbolName != null){
				content = content + '''
					<Value argument="amount"> 
						«s.symbolName.print_ct_SymbolRef»
					</Value>
				'''
			}
			var type = s.list.arguments.getAttribute(AttributeValidator::attr_type_macro.name);
			var macroType = pk_types.get(type);
			if (macroType != null){
				content = content + type.print_PKAttributes(s.list.arguments);
				return macroType.print_PKMacros(content);
			}
		}
		return "";
	}
	
	def print_PKMacros(List list){
		if (list != null){
			var type = list.arguments.getAttribute(AttributeValidator::attr_type_macro.name);
			var macroType = pk_types.get(type);
			if (macroType != null){
				var content = type.print_PKAttributes(list.arguments);
				return macroType.print_PKMacros(content);
			}
		}
		return "";
	}
	
	//macroType is a PharmML macro
	def print_PKMacros(String macroType, String content){
		'''
			<PKmacros>
			  	<«macroType»>
			  		«content»
			  	</«macroType»>
			</PKmacros>
		'''
	}
	
	//Convert MDL PK macro attributes to PharmML, type here is an MDL macro type
	protected def print_PKAttributes(String type, Arguments args){
		var res = "";
		if (args.namedArguments != null){
			var attrExpressions = new HashMap<String, String>();
			if (type.equals(PkMacroType::DEPOT.toString) || type.equals(PkMacroType::DIRECT.toString)){
				//Custom mapping
				//type=depot|direct && modelCmt=2 -> adm=1, cmt=1 or from context
				val modelCmt = args.getAttribute(AttributeValidator::attr_modelCmt.name);
				val to = args.getAttribute(AttributeValidator::attr_to.name);
				if (modelCmt.equals("2"))
					attrExpressions.put("adm", modelCmt.print_adm);
				if (to.length > 0){
					var mObj = Utils::getMclObject(args);
					if (mObj != null && mObj.modelObject != null){
						var toCompartmentArgs = mObj.modelObject.getToCompartment(to);
						if (toCompartmentArgs != null){
							var toCompartment_cmt = toCompartmentArgs.getAttribute(AttributeValidator::attr_modelCmt.name);
							if (toCompartment_cmt.length > 0)
								attrExpressions.put("cmt", "cmt".print_Value(toCompartment_cmt.print_ct_Value));
							var toCompartment_ka = toCompartmentArgs.getAttribute(AttributeValidator::attr_ka.name);
							if (toCompartment_ka.length > 0)
								attrExpressions.put("ka", "ka".print_Value(toCompartment_ka.print_ct_Value));	
						}
					}
				}
			}
			for (a: args.namedArguments.arguments){
				var String attrName = null;
				if (a.argumentName != null)
					attrName = pk_attrs.get(a.argumentName.name);
				if (attrName != null && !attrExpressions.containsKey(attrName)){
					attrExpressions.put(attrName, '''
						<Value argument="«attrName»"> 
							«a.expression.print_Math_Expr»
						</Value>
					''');
				}
			}
			for (expr: attrExpressions.entrySet)
				res  = res + expr.value;
		}
		return res;
	} 	
	
	protected def Arguments getToCompartment(ModelObject mObj, String to){
		if (to.length > 0){
			for (b: mObj.blocks){
				if (b.modelPredictionBlock != null){
					for (st: b.modelPredictionBlock.statements){
					if (st.pkMacroBlock != null){
							for (s: st.pkMacroBlock.statements){
								if (s.variable != null){
									if (s.variable.symbolName.name.equals(to) && s.variable.list != null){
										var cmtMacroType = s.variable.list.arguments.getAttribute(AttributeValidator::attr_type_macro.name);
										if (cmtMacroType.equals(PkMacroType::COMPARTMENT.toString))
											return s.variable.list.arguments;
									}
								}
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	protected def print_Value(String attrName, String value){
		return '''
			<Value argument="«attrName»"> 
				«value.print_ct_Value»
			</Value>
		'''
	}
	
	/*If data object variable with use=cmt exists and
	  1) it has define attribute, assign adm=define[1] from the use=cmt definition
	  2) it does not have define attribute, assign adm = modelCmt*/  
	protected def print_adm(String modelCmt){
		var adm = '''«"1".print_ct_Value»''';
		//cmtVar is available from ReferenceResolver extension
		if (cmtVar != null){
			var define = cmtVar.list.arguments.getAttributeExpression(AttributeValidator::attr_define.name);
			if (define != null){
				var pairs = define.getAttributePairs(AttributeValidator::attr_modelCmt.name, AttributeValidator::attr_dataCmt.name);
				if (pairs.size > 0)
					adm = '''«pairs.get(0).value.print_Math_Expr»''';
			} else adm = '''«modelCmt.print_ct_Value»''';
		}
		return '''
			<Value argument="adm"> 
				«adm»
			</Value>
		''';
	}
}