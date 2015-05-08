package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.MOGObject
import org.ddmore.mdl.mdl.impl.FunctionCallImpl
import org.ddmore.mdl.mdl.FunctionCall
import org.ddmore.mdl.validation.FunctionValidator
import org.ddmore.mdl.types.MdlDataType
import org.ddmore.mdl.validation.Utils
import java.util.ArrayList

class FunctionDefinitionPrinter {

	//////////////////////////////////////
	// I.a Function Definition
	//////////////////////////////////////

	def print_FunctionDefinitions(MOGObject mog){
		var res  = "";
		var printedFunctions = new ArrayList<String>();
		for (o: Utils::getMOGObjects(mog)){
			var iterator = o.eAllContents();
			while (iterator.hasNext()){
			   	var obj = iterator.next();
				if (obj instanceof FunctionCallImpl){
					var functName = (obj as FunctionCall).identifier.name;
					if (!printedFunctions.contains(functName)){
						res = res + '''«functName.print_ct_FunctionDefinition»''';
						printedFunctions.add(functName);
					}
				}
			}
		}
		'''«res»'''
	}
	
	protected def print_ct_FunctionDefinition(String functName){
		if (FunctionValidator::errorModels.contains(functName))'''
			<ct:FunctionDefinition xmlns="«Constants::xmlns_ct»" 
				symbId="«functName»" 
				symbolType="«FunctionValidator::standardFunctions.get(functName).type.convertType»">
				«FOR p: FunctionValidator::standardFunctions.get(functName).defaultParamSet»
					<FunctionArgument symbolType="«p.type.convertType»" symbId="«p.name»"/>
				«ENDFOR»
				«functName.print_FunctionDefinition»
			</ct:FunctionDefinition>
		'''	
	}
	
	//combined2: sqrt(a^2  +  b^2*F^2)
	//combined2log or combined3: sqrt(b^2 + (a/f)^2)
	protected def print_FunctionDefinition(String functName)'''
		<Definition>
			<Equation xmlns="«Constants::xmlns_math»">
				«IF (functName.equals(FunctionValidator::funct_error_additive))»
					<ct:SymbRef symbIdRef="«FunctionValidator::param_error_additive.name»"/>
				«ENDIF»
				«IF (functName.equals(FunctionValidator::funct_error_prop))»
					<Binop op="times">
						<ct:SymbRef symbIdRef="«FunctionValidator::param_error_proportional.name»"/>
						<ct:SymbRef symbIdRef="«FunctionValidator::param_error_f.name»"/>
					</Binop>
				«ENDIF»
				«IF (functName.equals(FunctionValidator::funct_error_combined1))»
					<math:Binop op="plus">
						<ct:SymbRef symbIdRef="«FunctionValidator::param_error_additive.name»"/>
						<math:Binop op="times">
							<ct:SymbRef symbIdRef="«FunctionValidator::param_error_proportional.name»"/>
							<ct:SymbRef symbIdRef="«FunctionValidator::param_error_f.name»"/>
						</math:Binop>
					</math:Binop>
				«ENDIF»
				«IF (functName.equals(FunctionValidator::funct_error_combined2))»
					<math:Uniop op="sqrt">
						<math:Binop op="plus">
							<math:Binop op="times">
								<ct:SymbRef symbIdRef="«FunctionValidator::param_error_additive.name»"/>
								<ct:SymbRef symbIdRef="«FunctionValidator::param_error_additive.name»"/>
							</math:Binop>
							<math:Binop op="times">
								<math:Binop op="times">
									<ct:SymbRef symbIdRef="«FunctionValidator::param_error_proportional.name»"/>
									<ct:SymbRef symbIdRef="«FunctionValidator::param_error_proportional.name»"/>
								</math:Binop>
								<math:Binop op="times">
									<ct:SymbRef symbIdRef="«FunctionValidator::param_error_f.name»"/>
									<ct:SymbRef symbIdRef="«FunctionValidator::param_error_f.name»"/>
								</math:Binop>
							</math:Binop>
						</math:Binop>
					</math:Uniop>
				«ENDIF»
				«IF (functName.equals(FunctionValidator::funct_error_combined2log) || functName.equals(FunctionValidator::funct_error_combined3))»
					<math:Uniop op="sqrt">
						<math:Binop op="plus">
							<math:Binop op="times">
								<ct:SymbRef symbIdRef="«FunctionValidator::param_error_proportional.name»"/>
								<ct:SymbRef symbIdRef="«FunctionValidator::param_error_proportional.name»"/>
							</math:Binop>
							<math:Binop op="divide">
								<math:Binop op="times">
									<ct:SymbRef symbIdRef="«FunctionValidator::param_error_additive.name»"/>
									<ct:SymbRef symbIdRef="«FunctionValidator::param_error_additive.name»"/>
								</math:Binop>
								<math:Binop op="times">
									<ct:SymbRef symbIdRef="«FunctionValidator::param_error_f.name»"/>
									<ct:SymbRef symbIdRef="«FunctionValidator::param_error_f.name»"/>
								</math:Binop>
							</math:Binop>
						</math:Binop>
					</math:Uniop>
				«ENDIF»
				«IF (functName.equals(FunctionValidator::funct_error_power))»
					<Binop op="times">
						<ct:SymbRef symbIdRef="«FunctionValidator::param_error_proportional.name»"/>
						<Binop op="power">
							<ct:SymbRef symbIdRef="«FunctionValidator::param_error_f.name»"/>
							<ct:SymbRef symbIdRef="«FunctionValidator::param_error_power.name»"/>
						</Binop>
					</Binop>
				«ENDIF»
				«IF (functName.equals(FunctionValidator::funct_error_combinedPower1))»
					<Binop op="plus">
						<ct:SymbRef symbIdRef="«FunctionValidator::param_error_additive.name»"/>
						<Binop op="times">
							<ct:SymbRef symbIdRef="«FunctionValidator::param_error_proportional.name»"/>
							<Binop op="power">
								<ct:SymbRef symbIdRef="«FunctionValidator::param_error_f.name»"/>
								<ct:SymbRef symbIdRef="«FunctionValidator::param_error_power.name»"/>
							</Binop>
						</Binop>
					</Binop>
				«ENDIF»
			</Equation>
		</Definition>
	'''
	
	protected def convertType(MdlDataType p){
		if (p == MdlDataType::TYPE_INT)  return Constants::TYPE_INT;
		if (p == MdlDataType::TYPE_REAL) return Constants::TYPE_REAL;
		return Constants::TYPE_REAL
	}
	
}