package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.MOGObject
import org.ddmore.mdl.mdl.impl.MclObjectImpl
import org.ddmore.mdl.mdl.MclObject
import org.ddmore.mdl.mdl.impl.FunctionCallImpl
import org.ddmore.mdl.mdl.FunctionCall
import org.ddmore.mdl.validation.FunctionValidator
import org.ddmore.mdl.types.MdlDataType

class FunctionDefinitionPrinter {

	//////////////////////////////////////
	// I.a Function Definition
	//////////////////////////////////////

	def print_FunctionDefinitions(MOGObject mog){
		var res  = "";
		for (objectName: mog.objects){
			var container = objectName.eContainer;
			if (container instanceof MclObjectImpl){
				val o = container as MclObject;
				var iterator = o.eAllContents();
			    while (iterator.hasNext()){
			    	var obj = iterator.next();
			    	if (obj instanceof FunctionCallImpl){
			    		var functName = (obj as FunctionCall).identifier.name;
			    		res = res + '''«functName.print_ct_FunctionDefinition»''';
			    	}
			    }
		    }
		}
		'''«res»'''
	}
	
	protected def print_ct_FunctionDefinition(String functName){
		if (FunctionValidator::errorModels.contains(functName))
			'''
			<ct:FunctionDefinition xmlns="«Constants::xmlns_ct»" 
				symbId="«FunctionValidator::funct_error_combined1»" 
				symbolType="«FunctionValidator::standardFunctions.get(functName).type.convertType»">
				«FOR p: FunctionValidator::standardFunctions.get(functName).defaultParamSet»
				<FunctionArgument symbolType="«p.type.convertType»" symbId="«p.name»"/>
				«ENDFOR»
				«functName.print_FunctionDefinition»
			</ct:FunctionDefinition>
			'''	
	}
	
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
		return "real"
	}
	
}