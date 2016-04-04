package eu.ddmore.converter.mdl2pharmml

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.mdl.util.MdlSwitch
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.PropertyDefinitionProvider
import eu.ddmore.mdl.provider.SublistDefinitionProvider
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.type.VectorTypeInfo

class VectorAttributeRewrite extends MdlSwitch<Boolean> {
	
	extension TypeSystemProvider tsp = new TypeSystemProvider
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension SublistDefinitionProvider sdp = new SublistDefinitionProvider
	extension PropertyDefinitionProvider pdp = new PropertyDefinitionProvider
	
	// this assumes that the AST is valid 
	override caseValuePair(ValuePair it){
		if(argumentType instanceof VectorTypeInfo){
			if(!(expression.typeFor instanceof VectorTypeInfo)){
				val fact = MdlPackage.eINSTANCE.mdlFactory
				val vect = fact.createVectorLiteral
				val el = fact.createVectorElement
				el.element = expression
				vect.expressions.add(el)
				expression = vect
				Boolean::TRUE
			}
		}
		
	}
	
	
	private def TypeInfo getArgumentType(ValuePair it){
		val parent = eContainer
		switch(parent){
			NamedFuncArguments:
				namedArgumentType
			AttributeList:
				attributeType
			SubListExpression:
				sublistAttributeType
			PropertyStatement:
				typeForProperty
			default:
				TypeSystemProvider::UNDEFINED_TYPE
		}
	}
	
}