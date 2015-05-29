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
import org.ddmore.mdl.mdl.SymbolName

class PKMacrosPrinter{
	extension MdlPrinter mdlPrinter = MdlPrinter::getInstance();
	protected extension MathPrinter mathPrinter = null;
	protected extension ReferenceResolver resolver = null;
	private static val MATH_NS = "http://www.pharmml.org/pharmml/0.6/Maths"; 
	
	protected new(MathPrinter mathPrinter, ReferenceResolver resolver){
		this.mathPrinter = mathPrinter;
		this.resolver = resolver;	
	}
		
	private val pk_types = newHashMap(
		PkMacroType::DIRECT.toString -> "IV",
		PkMacroType::COMPARTMENT.toString -> "Compartment",
		PkMacroType::DISTRIBUTION.toString -> "Peripheral",
		PkMacroType::INPUT.toString -> "Absorption",
		PkMacroType::DEPOT.toString -> "Absorption",
		PkMacroType::EFFECT.toString -> "Effect",
		PkMacroType::TRANSFER.toString -> "Transfer",
		PkMacroType::ELIMINATION.toString -> "Elimination"
	);
	
	private val pk_attrs = newHashMap(
		AttributeValidator::attr_modelCmt.name -> "cmt",
		AttributeValidator::attr_finput.name -> "p",
		AttributeValidator::attr_tk0.name -> "Tk0",
		AttributeValidator::attr_tlag.name -> "Tlag",
		AttributeValidator::attr_v.name -> "V",
		AttributeValidator::attr_cl.name -> "CL",
		AttributeValidator::attr_ktr.name -> "Ktr",
		AttributeValidator::attr_mtt.name -> "Mtt",
		AttributeValidator::attr_k.name -> "k",
		AttributeValidator::attr_kt.name -> "kt",
		AttributeValidator::attr_ka.name -> "ka",
		AttributeValidator::attr_vm.name -> "Vm",
		AttributeValidator::attr_km.name -> "Km",
		AttributeValidator::attr_keq.name -> "ke0"
	);
	
	def print_PKMacros(SymbolDeclaration s){
		//Convert symbolName to 'amount' PharmML attribute
		var retVal = ''''''
		if (s.list != null){
			var content = "";
			var type = s.list.arguments.getAttribute(AttributeValidator::attr_type_macro.name);
			if (s.symbolName != null){
				if (type.equals(PkMacroType::EFFECT.toString))
					content = content + '''
						<Value argument="concentration"> 
							«s.symbolName.print_ct_SymbolRef»
						</Value>
					'''
				else if(type == PkMacroType::COMPARTMENT.toString)
					content = content + '''
						<Value argument="amount"> 
							«s.symbolName.print_ct_SymbolRef»
						</Value>
					'''
			}
			var macroType = pk_types.get(type);
			if (macroType != null){
				content = content + type.print_PKAttributes(s.list.arguments);
				retVal = macroType.print_PKMacros(content).toString;
			}
			if(type == PkMacroType::TRANSFER.toString){
				// because a transfer is also a compartment it means that we need to also
				// create a new compartment definition for it.
					retVal = retVal + s.symbolName.printImplicitCompartment(s.list.arguments)
			}
		}
		return retVal;
	}
	
	def printImplicitCompartment(SymbolName name, Arguments args)'''
		<Compartment>
			<Value argument="amount"> 
				«name.print_ct_SymbolRef»
			</Value>
			«"cmt".print_Attr_Value(args.getAttribute(AttributeValidator::attr_modelCmt.name).print_ct_Value)»
		</Compartment>
	'''
	
	def print_PKMacros(List list){
		var retVal = ''''''
		if (list != null){
			var type = list.arguments.getAttribute(AttributeValidator::attr_type_macro.name);
			var macroType = pk_types.get(type);
			if (macroType != null){
				var content = type.print_PKAttributes(list.arguments);
				retVal = macroType.print_PKMacros(content).toString;
			}
		}
		return retVal;
	}
	
	//macroType is a PharmML macro
	def print_PKMacros(String macroType, String content)'''
			  	<«macroType»>
			  		«content»
			  	</«macroType»>
		'''

	
	//Convert MDL PK macro attributes to PharmML, type here is an MDL macro type
	protected def print_PKAttributes(String type, Arguments args){
		var res = "";
		if (args.namedArguments != null){
			var attrExpressions = new HashMap<String, String>();
			//Custom mapping
			//DEPOT, INPUT
			if (type.equals(PkMacroType::DEPOT.toString) || type.equals(PkMacroType::DIRECT.toString)){
				//type=depot|direct
				val modelCmt = args.getAttribute(AttributeValidator::attr_modelCmt.name);
//				if (modelCmt.equals("2"))
				attrExpressions.put("adm", modelCmt.print_adm);
				val to = args.getAttribute(AttributeValidator::attr_to.name);
				if (to.length > 0){
					var mObj = Utils::getMclObject(args);
					if (mObj != null && mObj.modelObject != null){
						var toCompartmentArgs = mObj.modelObject.findCompartment(to);
						if (toCompartmentArgs != null){
							var toCompartment_cmt = toCompartmentArgs.getAttribute(AttributeValidator::attr_modelCmt.name);
							if (toCompartment_cmt.length > 0)
								attrExpressions.put("cmt", "cmt".print_Attr_Value(toCompartment_cmt.print_ct_Value));
						}
					}
				}
			}
			if (type.equals(PkMacroType::ELIMINATION.toString)){
				val from = args.getAttribute(AttributeValidator::attr_from.name);
				if (from.length > 0){
					var mObj = Utils::getMclObject(args);
					if (mObj != null && mObj.modelObject != null){
						var fromCompartmentArgs = mObj.modelObject.findCompartment(from);
						if (fromCompartmentArgs != null){
							var fromCompartmentCmt = fromCompartmentArgs.getAttribute(AttributeValidator::attr_modelCmt.name);
							if (fromCompartmentCmt.length > 0)
								attrExpressions.put("cmt", "cmt".print_Attr_Value(fromCompartmentCmt.print_ct_Value));
						}
					}
				}
			}
			//DISTRIBUTION
			if (type.equals(PkMacroType::DISTRIBUTION.toString)){
				attrExpressions.put("cmt", null); //skip cmt attribute in peripheral macro
				val modelCmt = args.getAttribute(AttributeValidator::attr_modelCmt.name);
				val from = args.getAttribute(AttributeValidator::attr_from.name);
				var kin = args.getAttributeExpression(AttributeValidator::attr_kin.name);
				var kout = args.getAttributeExpression(AttributeValidator::attr_kout.name);
				if (from.length > 0 && (kin != null || kout != null)){
					var mObj = Utils::getMclObject(args);
					if (mObj != null && mObj.modelObject != null){
						var fromCompartmentArgs = mObj.modelObject.findCompartment(from);
						if (fromCompartmentArgs != null){
							var fromCompartment_cmt = fromCompartmentArgs.getAttribute(AttributeValidator::attr_modelCmt.name);
							if (fromCompartment_cmt.length > 0){
								if (kin != null){
									var attr1 = "k" + fromCompartment_cmt + modelCmt;
									attrExpressions.put(attr1, attr1.print_Attr_Value(kin.print_Math_Expr.toString));
								}
								if (kout != null){
									var attr2 = "k" + modelCmt + fromCompartment_cmt;
									attrExpressions.put(attr2, attr2.print_Attr_Value(kout.print_Math_Expr.toString));
								}
							}
						}
					}
				}
			}
			//TRANSFER 
			if (type.equals(PkMacroType::TRANSFER.toString)){
				attrExpressions.put("cmt", null); //skip cmt attribute in transfer macro
				val modelCmt = args.getAttribute(AttributeValidator::attr_modelCmt.name);
				if (modelCmt.length > 0){ 
					attrExpressions.put("to", "to".print_Attr_Value(modelCmt.print_ct_Value));
				}
				val from = args.getAttribute(AttributeValidator::attr_from.name);
				if (from.length > 0){
					var mObj = Utils::getMclObject(args);
					if (mObj != null && mObj.modelObject != null){
						var fromCompartmentArgs = mObj.modelObject.findCompartment(from);
						if (fromCompartmentArgs != null){
							var fromCompartment_cmt = fromCompartmentArgs.getAttribute(AttributeValidator::attr_modelCmt.name);
							if (fromCompartment_cmt.length > 0){
								attrExpressions.put("from", "from".print_Attr_Value(fromCompartment_cmt.print_ct_Value));
							}
						}
					}
				}
			}
			//EFFECT
			if (type.equals(PkMacroType::EFFECT.toString)){
				attrExpressions.put("cmt", null); //skip cmt attribute in transfer macro
				val from = args.getAttribute(AttributeValidator::attr_from.name);
				if (from.length > 0){
					var mObj = Utils::getMclObject(args);
					if (mObj != null && mObj.modelObject != null){
						var fromCompartmentArgs = mObj.modelObject.findCompartment(from);
						if (fromCompartmentArgs != null){
							var fromCompartment_cmt = fromCompartmentArgs.getAttribute(AttributeValidator::attr_modelCmt.name);
							if (fromCompartment_cmt.length > 0){
								attrExpressions.put("cmt", "cmt".print_Attr_Value(fromCompartment_cmt.print_ct_Value));
							}
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
			for (expr: attrExpressions.entrySet){
				if (expr.value != null)
					res  = res + expr.value;
			}
		}
		return res;
	} 	
	
	protected def Arguments findCompartment(ModelObject mObj, String to){
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
	
	protected def print_Attr_Value(String attrName, String value){
		return '''
		<Value argument="«attrName»">
				<ct:Assign>
					<Equation xmlns="«MATH_NS»">
						«value»
					</Equation>
				</ct:Assign>
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