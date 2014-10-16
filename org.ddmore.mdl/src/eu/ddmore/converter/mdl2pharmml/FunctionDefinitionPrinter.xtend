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
		for (objectName: mog.objects){
			var container = objectName.eContainer;
			if (container instanceof MclObjectImpl){
				val o = container as MclObject;
				var iterator = o.eAllContents();
			    while (iterator.hasNext()){
			    	var obj = iterator.next();
			    	if (obj instanceof FunctionCallImpl){
			    		var functName = (obj as FunctionCall).identifier.name;
			    		functName.print_FunctionDefinition;
			    	}
			    }
		    }
		}
	}
	
	protected def print_FunctionDefinition(String functName){
		var definition = "";
		if (functName.equals(FunctionValidator::funct_error_combined1))
			definition = 
				'''
				<ct:FunctionDefinition  xmlns="http://www.pharmml.org/2013/03/CommonTypes" 
					symbId="«FunctionValidator::funct_error_combined1»" 
					symbolType="real">
					<FunctionArgument symbolType="«FunctionValidator::param_error_a.type.convertType»" symbId="«FunctionValidator::param_error_a.name»"/>
					<FunctionArgument symbolType="«FunctionValidator::param_error_b.type.convertType»" symbId="«FunctionValidator::param_error_b.name»"/>
					<FunctionArgument symbolType="«FunctionValidator::param_error_f.type.convertType»" symbId="«FunctionValidator::param_error_f.name»"/>
					<Definition>
						<math:Equation>
							<math:Binop op="plus">
								<ct:SymbRef symbIdRef="«FunctionValidator::param_error_a.name»"/>
								<math:Binop op="times">
									<ct:SymbRef symbIdRef="«FunctionValidator::param_error_b.name»"/>
									<ct:SymbRef symbIdRef="«FunctionValidator::param_error_f.name»"/>
								</math:Binop>
							</math:Binop>
						</math:Equation>
					</Definition>
				</ct:FunctionDefinition>
				'''	
	}
	
	protected def convertType(MdlDataType p){
		if (p == MdlDataType::TYPE_INT) return "int";
		if (p == MdlDataType::TYPE_REAL) return "real";
		return "real"
	}
}