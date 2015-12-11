package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.AdditiveExpression
import eu.ddmore.mdl.mdl.AndExpression
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.CatValRefMapping
import eu.ddmore.mdl.mdl.CategoryValueDefinition
import eu.ddmore.mdl.mdl.ElseClause
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.IfExprPart
import eu.ddmore.mdl.mdl.MappingPair
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.MultiplicativeExpression
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.OrExpression
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.mdl.UnaryExpression
import eu.ddmore.mdl.mdl.UnnamedArgument
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.type.MclTypeProvider
import eu.ddmore.mdl.type.MclTypeProvider.TypeInfo
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar
import eu.ddmore.mdl.utils.DomainObjectModelUtils

class TypeSystemValidator extends AbstractMdlValidator {
	
	override register(EValidatorRegistrar registrar){}
	
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension MclTypeProvider typeProvider = new MclTypeProvider
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	
	// Type handling	
	private def (TypeInfo, TypeInfo) => void typeError(EStructuralFeature feature){ 
		[expectedType, actualType |error("Expected " + expectedType.typeName + " type, but was " + actualType.typeName + ".", feature, MdlValidator::INCOMPATIBLE_TYPES, expectedType.typeName) ]
	}
	
			
	@Check
	def validateCompatibleTypes(AndExpression e){
		checkBoolOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.andExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.andExpression_RightOperand))
	}
	
	@Check
	def validateCompatibleTypes(OrExpression e){
		checkBoolOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.orExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.orExpression_RightOperand))
	}
	
	@Check
	def validateCompatibleTypes(EqualityExpression e){
		checkRelationalOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.equalityExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.equalityExpression_RightOperand))
	}
		
	@Check
	def validateCompatibleTypes(RelationalExpression e){
		checkRelationalOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.relationalExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.relationalExpression_RightOperand))
	}
		
	@Check
	def validateCompatibleTypes(AdditiveExpression e){
		checkMathsOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.additiveExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.additiveExpression_RightOperand))
	}
		
	@Check
	def validateCompatibleTypes(MultiplicativeExpression e){
		checkMathsOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.multiplicativeExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.multiplicativeExpression_RightOperand))
	}
		
	@Check
	def validateCompatibleTypes(UnaryExpression e){
		checkUnaryOp(e.feature, e.operand, typeError(MdlPackage::eINSTANCE.unaryExpression_Operand))
	}
		
	@Check
	def validateCompatibleTypes(IfExprPart e){
		checkExpectedBoolean(e.cond, typeError(MdlPackage::eINSTANCE.ifExprPart_Cond))
		checkExpectedReal(e.value, typeError(MdlPackage::eINSTANCE.ifExprPart_Value))
	}
		
	@Check
	def checkElseCompatibleTypes(ElseClause e){
		checkExpectedReal(e.other, typeError(MdlPackage::eINSTANCE.elseClause_Other))
	}
		
		
	@Check
	def validateCompatibleTypes(MappingPair e){
		checkAsOperator(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.mappingPair_LeftOperand),
			typeError(MdlPackage::eINSTANCE.mappingPair_RightOperand))
	}
		
	@Check
	def validateCompatibleTypes(CatValRefMapping e){
		var parentAt = EcoreUtil2.getContainerOfType(e, EnumPair)
		if(parentAt != null)
			checkWhenOperator(parentAt, e.catRef, e.mappedTo, typeError(MdlPackage::eINSTANCE.catValRefMapping_CatRef),
				typeError(MdlPackage::eINSTANCE.catValRefMapping_MappedTo)
			)
	}
		
	@Check
	def validateCompatibleTypes(CategoryValueDefinition e){
		var parentAt = EcoreUtil2.getContainerOfType(e, EnumPair)
		if(parentAt != null)
			checkWhenOperator(parentAt, e, typeError(MdlPackage::eINSTANCE.categoryValueDefinition_Name),
				typeError(MdlPackage::eINSTANCE.categoryValueDefinition_MappedTo)
			)
	}
		
	@Check
	def validateCompatibleTypes(EquationDefinition e){
		// only check if there is an RHS to check 
		if(e.expression != null)
			if(e.isVector)
				checkExpectedVector(e.expression, typeError(MdlPackage::eINSTANCE.equationTypeDefinition_Expression))
			else if(e.isMatrix)
				checkExpectedMatrix(e.expression, typeError(MdlPackage::eINSTANCE.equationTypeDefinition_Expression))
			else
				checkExpectedReal(e.expression, typeError(MdlPackage::eINSTANCE.equationTypeDefinition_Expression))
	}
		
	@Check
	def validateCompatibleTypes(RandomVariableDefinition e){
		if(e.distn != null){
			if(e.isVector)
				checkExpectedPdfVector(e.distn, typeError(MdlPackage::eINSTANCE.randomVariableDefinition_Distn))
			else 
				checkExpectedPdf(e.distn, typeError(MdlPackage::eINSTANCE.randomVariableDefinition_Distn))
		}
	}
		
	@Check
	def validateCompatibleTypes(TransformedDefinition e){
		checkExpectedRealTransform(e.transform, typeError(MdlPackage::eINSTANCE.transformedDefinition_Transform))
		checkExpectedReal(e.expression, typeError(MdlPackage::eINSTANCE.equationTypeDefinition_Expression))
	}
		
	@Check
	def validateCompatibleVectorElement(VectorElement e){
		if(e.eContainer instanceof VectorLiteral){
			val vect = e.eContainer as VectorLiteral
			val vectType = vect.typeFor
			val exprType = e.typeFor
			if(!vectType.isCompatibleElement(exprType)){
				error("Element type '" + exprType.typeName + "' is incompatible with vector type '" + vectType.typeName + "'.",
					MdlPackage.eINSTANCE.vectorElement_Element, MdlValidator::INCOMPATIBLE_TYPES, vectType.typeName)
			}			
		}
	}
	
	
	@Check
	def validUnnamedFuctionArgumentType(UnnamedArgument it){
		if(eContainer instanceof UnnamedFuncArguments){
			checkFunctionArgumentTyping([e, a|
				error("argument '" + (funcArgNum + 1) + "' expected value of type '" + e.typeName + "' but was '" + a.typeName + "'.",
						MdlPackage.eINSTANCE.unnamedArgument_Argument, MdlValidator::INCOMPATIBLE_TYPES, a.typeName)
			])
		}
	}
	
	@Check
	def validateListAttributeTypes(ValuePair vp){
		val parent = vp.eContainer
		switch(parent){
			AttributeList:
				parent.checkAttributeTyping(vp, [e, a|
					error("attribute '" + vp.attributeName + "' expected value of type '" + e.typeName + "' but was '" + a.typeName + "'.",
							MdlPackage.eINSTANCE.valuePair_Expression, MdlValidator::INCOMPATIBLE_TYPES, a.typeName)
				])
			NamedFuncArguments:
				vp.checkNamedFunctionArgumentTyping([e, a|
					error("argument '" + vp.attributeName + "' expected value of type '" + e.typeName + "' but was '" + a.typeName + "'.",
							MdlPackage.eINSTANCE.valuePair_Expression, MdlValidator::INCOMPATIBLE_TYPES, a.typeName)
				])
			SubListExpression:
				parent.checkSublistAttributeTyping(vp, [e, a|
					error("attribute '" + vp.attributeName + "' expected value of type '" + e.typeName + "' but was '" + a.typeName + "'.",
							MdlPackage.eINSTANCE.valuePair_Expression, MdlValidator::INCOMPATIBLE_TYPES, a.typeName)
				])
			PropertyStatement:
				parent.checkPropertyAttributeTyping(vp, [e, a|
					error("property '" + vp.attributeName + "' expected value of type '" + e.typeName + "' but was '" + a.typeName + "'.",
							MdlPackage.eINSTANCE.valuePair_Expression, MdlValidator::INCOMPATIBLE_TYPES, a.typeName)
				])
		}
	}

	
}