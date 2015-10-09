package eu.ddmore.converter.mdl2pharmml

import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.utils.MclUtils
import eu.ddmore.mdl.validation.ListDefinitionProvider
import java.util.HashMap
import java.util.List
import org.eclipse.xtext.EcoreUtil2

import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString

class PKMacrosPrinter{
//	private static val MATH_NS = "http://www.pharmml.org/pharmml/0.6/Maths"; 
	
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder
	extension MclUtils mu = new MclUtils
	
	private val pk_types = newHashMap(
		'direct' -> "IV",
		'compartment' -> "Compartment",
		'distribution' -> "Peripheral",
		'input' -> "Absorption",
		'depot' -> "Absorption",
		'effect' -> "Effect",
		'transfer' -> "Transfer",
		'elimination' -> "Elimination"
	);
	
	private val pk_attrs = newHashMap(
		'modelCmt' -> "cmt",
		'finput' -> "p",
//		AttributeValidator::attr_tk0.name -> "Tk0",
		'tlag' -> "Tlag",
		'v' -> "V",
		'cl' -> "CL",
		'ktr' -> "Ktr",
		'mtt' -> "Mtt",
		'k'-> "k",
		'kt' -> "kt",
		'ka' -> "ka",
		'vm' -> "Vm",
		'km' -> "Km"//,
//		AttributeValidator::attr_keq.name -> "ke0"
	);
	
	def printCompartmentDefinitions(List<Statement> statements) {
		var defns = ''''''
		for (s : statements) {
			switch(s){
				ListDefinition:{
					if(s.list.getAttributeEnumValue('type') == 'compartment'){
						defns += '''<ct:Variable symbId="«s.name»" symbolType="real"/>
					'''
					}
				}
			}
//			if(s.variable != null && s.variable.list != null){
//				var type = s.variable.list.arguments.getAttribute(AttributeValidator::attr_type_macro.name);
//				if(type == PkMacroType::COMPARTMENT.toString){
//					defns += '''<ct:Variable symbId="«s.variable.name»" symbolType="real"/>
//					'''
//				}
//			}
		}
		return defns
	}
	
	def printMacros(List<Statement> statements) {
		var macros = ''''''
		for (s : statements) {
			switch(s){
				ListDefinition:{
					macros += s.print_PKMacros
				}
				AnonymousListStatement:
					macros += s.list.print_PKMacros
			}
		}
		'''
		<PKmacros>
			«macros»
		</PKmacros>
		'''

	}
	
	
//	val cmpNumMap = new HashMap<String, Integer>
//	int cmpNum = 0
	
//	def storeCompartment(ListDefinition it){
//		cmpNum += 1
//		cmpNumMap.put(name, cmpNum)
//	}
	
//	def getCompartmentNum(SymbolDefinition it){
//		cmpNumMap.get(name)
//	}
	
	def print_PKMacros(ListDefinition s){
		//Convert symbolName to 'amount' PharmML attribute
		var retVal = ''''''
		var content = '''''';
		var type = s.list.getAttributeEnumValue('type')
		if (type == 'effect')
			content = content + '''
				<Value argument="concentration"> 
					«s.symbolReference»
				</Value>
			'''
		else if(type == 'compartment' || type == 'distribution')
			content = content + '''
				<Value argument="amount"> 
					«s.symbolReference»
				</Value>
			'''
		var macroType = pk_types.get(type);
		if (macroType != null){
			content += type.print_PKAttributes(s.list);
			retVal += macroType.print_PKMacros(content);
		}
		if(type == 'transfer'){
			// because a transfer is also a compartment it means that we need to also
			// create a new compartment definition for it.
			retVal += s.printImplicitCompartment()
		}
		return retVal;
	}
	
	def printImplicitCompartment(ListDefinition decl)'''
		<Compartment>
			<Value argument="amount"> 
				«decl.symbolReference»
			</Value>
			«decl.list.getAttributeExpression('modelCmt').writeValue("cmt")»
		</Compartment>
	'''
	
	def print_PKMacros(AttributeList list){
		var retVal = ''''''
		if (list != null){
			var type = list.getAttributeEnumValue('type');
			var macroType = pk_types.get(type);
			if (macroType != null){
				var content = type.print_PKAttributes(list);
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

	
//	def getAmtId(SymbolReference it, MclObject dObj){
//		val symbDefn = ref
//		dObj.getDataMappingForDoseVariable(symbDefn)
//	}

	//Convert MDL PK macro attributes to PharmML, type here is an MDL macro type
	def print_PKAttributes(String type, AttributeList args){
		var res = "";
		val attrExpressions = new HashMap<String, CharSequence>();
		val mObj = EcoreUtil2.getContainerOfType(args, MclObject)
		switch(type){
			case('depot'),
			case('direct'):{
				val modelCmt = args.getAttributeExpression('modelCmt');
		//				if (modelCmt.equals("2"))
				attrExpressions.put("adm", modelCmt.writeAdm)
				val to = args.getAttributeExpression('to')
				if (to != null){
					var toCompartmentArgs = mObj.findCompartment(to as SymbolReference)
					if (toCompartmentArgs != null){
						var toCompartment_cmt = toCompartmentArgs.list.getAttributeExpression('modelCmt')
						attrExpressions.put("cmt", toCompartment_cmt.writeValue("cmt"))
					}
				}
			}
			case('elimination'):{
				val from = args.getAttributeExpression('from');
				val fromCompartmentArgs = mObj.findCompartment(from as SymbolReference);
				if (fromCompartmentArgs != null){
					val fromCompartmentCmt = fromCompartmentArgs.list.getAttributeExpression('modelCmt')
					attrExpressions.put("cmt", fromCompartmentCmt.writeValue("cmt"))
				}
			}
			case('distribution'):{
				attrExpressions.put("cmt", null); //skip cmt attribute in peripheral macro
				val modelCmt = args.getAttributeExpression('modelCmt');
				val from = args.getAttributeExpression('from');
				var kin = args.getAttributeExpression('kin');
				val kout = args.getAttributeExpression('kout');
				if (from != null && (kin != null || kout != null)){
					val fromCompartmentArgs = mObj.findCompartment(from as SymbolReference);
					if (fromCompartmentArgs != null){
						val fromCompartment_cmt = fromCompartmentArgs.list.getAttributeExpression('modelCmt');
						if (kin != null){
							val attr1 = "k" + fromCompartment_cmt.convertToString + modelCmt.convertToString;
							attrExpressions.put(attr1, kin.writeValue(attr1));
						}
						if (kout != null){
							val attr2 = "k" + modelCmt.convertToString + fromCompartment_cmt.convertToString;
							attrExpressions.put(attr2, kout.writeValue(attr2));
						}
					}
				}
			}
			case('transfer'):{
				attrExpressions.put("cmt", null); //skip cmt attribute in transfer macro
				val modelCmt = args.getAttributeExpression('modelCmt')
				if (modelCmt != null){ 
					attrExpressions.put("to", modelCmt.writeValue("to")) //""to".print_Attr_Value(modelCmt.print_ct_Value));
				}
				val from = args.getAttributeExpression('from')
				if (from != null){
					val fromCompartmentArgs = mObj.findCompartment(from as SymbolReference);
					if (fromCompartmentArgs != null){
						val fromCompartment_cmt = fromCompartmentArgs.list.getAttributeExpression('modelCmt');
						attrExpressions.put("from", fromCompartment_cmt.writeValue("from"))
					}
				}
			}
			case('effect'):{
				attrExpressions.put("cmt", null); //skip cmt attribute in transfer macro
				val from = args.getAttributeExpression('from');
				if (from != null){
					val fromCompartmentArgs = mObj.findCompartment(from as SymbolReference);
					if (fromCompartmentArgs != null){
						val fromCompartment_cmt = fromCompartmentArgs.list.getAttributeExpression('modelCmt');
						attrExpressions.put("cmt", fromCompartment_cmt.writeValue("cmt"))//"cmt".print_Attr_Value(fromCompartment_cmt.print_ct_Value));
					}
				}
			}
				
		}
//		//Custom mapping
//		//DEPOT, INPUT
//		if (type.equals('depot') || type.equals('direct')){
//			//type=depot|direct
////			val modelCmt = args.getAttributeExpression('modelCmt')
//			args.StoreCompartment
////				if (modelCmt.equals("2"))
//			attrExpressions.put("adm", modelCmt.writeAdm)
//			val to = args.getAttributeExpression('to')
//			if (to != null){
//				var toCompartmentArgs = mObj.findCompartment(to as SymbolReference)
//				if (toCompartmentArgs != null){
//					var toCompartment_cmt = toCompartmentArgs.list.getAttributeExpression('modelCmt')
//					attrExpressions.put("cmt", toCompartment_cmt.writeValue("cmt"))
//				}
//			}
//		}
//		if (type.equals('elimination')){
//			val from = args.getAttributeExpression('from');
//			val fromCompartmentArgs = mObj.findCompartment(from as SymbolReference);
//			if (fromCompartmentArgs != null){
//				val fromCompartmentCmt = fromCompartmentArgs.list.getAttributeExpression('modelCmt')
//				attrExpressions.put("cmt", fromCompartmentCmt.writeValue("cmt"))
//			}
//		}
//		//DISTRIBUTION
//		if (type == 'distribution'){
//			attrExpressions.put("cmt", null); //skip cmt attribute in peripheral macro
//			val modelCmt = args.getAttributeExpression('modelCmt');
//			val from = args.getAttributeExpression('from');
//			var kin = args.getAttributeExpression('kin');
//			val kout = args.getAttributeExpression('kout');
//			if (from != null && (kin != null || kout != null)){
//				val fromCompartmentArgs = mObj.findCompartment(from as SymbolReference);
//				if (fromCompartmentArgs != null){
//					val fromCompartment_cmt = fromCompartmentArgs.list.getAttributeExpression('modelCmt');
//					if (kin != null){
//						val attr1 = "k" + fromCompartment_cmt.convertToString + modelCmt.convertToString;
//						attrExpressions.put(attr1, kin.writeValue(attr1));
//					}
//					if (kout != null){
//						val attr2 = "k" + modelCmt.convertToString + fromCompartment_cmt.convertToString;
//						attrExpressions.put(attr2, kout.writeValue(attr2));
//					}
//				}
//			}
//		}
//		//TRANSFER 
//		if (type == 'transfer'){
//			attrExpressions.put("cmt", null); //skip cmt attribute in transfer macro
//			val modelCmt = args.getAttributeExpression('modelCmt')
//			if (modelCmt != null){ 
//				attrExpressions.put("to", modelCmt.writeValue("to")) //""to".print_Attr_Value(modelCmt.print_ct_Value));
//			}
//			val from = args.getAttributeExpression('from')
//			if (from != null){
//				val fromCompartmentArgs = mObj.findCompartment(from as SymbolReference);
//				if (fromCompartmentArgs != null){
//					val fromCompartment_cmt = fromCompartmentArgs.list.getAttributeExpression('modelCmt');
//					attrExpressions.put("from", fromCompartment_cmt.writeValue("from"))
//				}
//			}
//		}
//		//EFFECT
//		if (type == "effect"){
//			attrExpressions.put("cmt", null); //skip cmt attribute in transfer macro
//			val from = args.getAttributeExpression('from');
//			if (from != null){
//				val fromCompartmentArgs = mObj.findCompartment(from as SymbolReference);
//				if (fromCompartmentArgs != null){
//					val fromCompartment_cmt = fromCompartmentArgs.list.getAttributeExpression('modelCmt');
//					attrExpressions.put("cmt", fromCompartment_cmt.writeValue("cmt"))//"cmt".print_Attr_Value(fromCompartment_cmt.print_ct_Value));
//				}
//			}
//		}
		for (a: args.attributes){
			var String attrName = null;
			if (a.argumentName != null)
				attrName = pk_attrs.get(a.argumentName);
			if (attrName != null && !attrExpressions.containsKey(attrName)){
				attrExpressions.put(attrName, '''
					<Value argument="«attrName»">
						«a.expression.pharmMLExpr»
					</Value>
				''');
			}
		}
		for (expr: attrExpressions.entrySet){
			if (expr.value != null)
				res  = res + expr.value;
		}
		return res;
	} 	
	
	protected def findCompartment(MclObject mObj, SymbolReference to){
			for (s: mObj.mdlCompartmentStatements){
				switch(s){
					ListDefinition:{
						if(s.name == to.ref.name && s.list.getAttributeEnumValue('type') == 'compartment') return s
					}
						
				}
//									if (s.variable.name.equals(to) && s.variable.list != null){
//										var cmtMacroType = s.variable.list.arguments.getAttribute(AttributeValidator::attr_type_macro.name);
//										if (cmtMacroType.equals(PkMacroType::COMPARTMENT.toString))
//											return s.variable.list.arguments;
//									}
//								}
//							}
//						}
//					}
			}
		return null;
	}
	
//	protected def print_Attr_Value(String attrName, String value){
//		return '''
//		<Value argument="«attrName»">
//				<ct:Assign>
//					<Equation xmlns="«MATH_NS»">
//						«value»
//					</Equation>
//				</ct:Assign>
//			</Value>
//		'''
//	}

	def writeValue(Expression value, String arg)'''
		<Value argument="«arg»"> 
			«value.expressionAsAssignment»
		</Value>
	'''


	def writeAdm(Expression adm)'''
		<Value argument="adm"> 
			«adm.pharmMLExpr»
		</Value>
	'''
	
//	/*If data object variable with use=cmt exists and
//	  1) it has define attribute, assign adm=define[1] from the use=cmt definition
//	  2) it does not have define attribute, assign adm = modelCmt*/  
//	protected def print_adm(String modelCmt){
//		var adm = '''«"1".print_ct_Value»''';
//		//cmtVar is available from ReferenceResolver extension
//		if (cmtVar != null){
//			var define = cmtVar.list.arguments.getAttributeExpression(AttributeValidator::attr_define.name);
//			if (define != null){
//				var pairs = define.getAttributePairs(AttributeValidator::attr_modelCmt.name, AttributeValidator::attr_dataCmt.name);
//				if (pairs.size > 0)
//					adm = '''«pairs.get(0).value.print_Math_Expr»''';
//			} else adm = '''«modelCmt.print_ct_Value»''';
//		}
//		return '''
//			<Value argument="adm"> 
//				«adm»
//			</Value>
//		''';
//	}
}
