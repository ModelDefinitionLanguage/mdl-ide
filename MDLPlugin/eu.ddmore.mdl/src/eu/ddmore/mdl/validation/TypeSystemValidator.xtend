package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.AdditiveExpression
import eu.ddmore.mdl.mdl.AndExpression
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.CatValRefMapping
import eu.ddmore.mdl.mdl.CategoryValueDefinition
import eu.ddmore.mdl.mdl.CategoryValueReference
import eu.ddmore.mdl.mdl.ElseClause
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.mdl.IfExprPart
import eu.ddmore.mdl.mdl.IndexRange
import eu.ddmore.mdl.mdl.MappingPair
import eu.ddmore.mdl.mdl.MatrixElement
import eu.ddmore.mdl.mdl.MatrixLiteral
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.MultiplicativeExpression
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.OrExpression
import eu.ddmore.mdl.mdl.PWClause
import eu.ddmore.mdl.mdl.PiecewiseExpression
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
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.PropertyDefinitionProvider
import eu.ddmore.mdl.provider.SublistDefinitionProvider
import eu.ddmore.mdl.type.CategoryTypeInfo
import eu.ddmore.mdl.type.CategoryValueTypeInfo
import eu.ddmore.mdl.type.GeneralCategoryTypeInfo
import eu.ddmore.mdl.type.RandomVariableTypeInfo
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeInfoClass
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.type.VectorTypeInfo
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.TypeDefinitionProvider
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.FunctionDefnBody
import eu.ddmore.mdllib.mdllib.FunctionSpec
import eu.ddmore.mdllib.mdllib.MdlLibPackage
import eu.ddmore.mdllib.mdllib.TypeSpec
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class TypeSystemValidator extends AbstractMdlValidator {
	
	override register(EValidatorRegistrar registrar){}
	
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension TypeSystemProvider typeProvider = new TypeSystemProvider
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
	extension SublistDefinitionProvider subListProvider = new SublistDefinitionProvider
	extension PropertyDefinitionProvider propProvider = new PropertyDefinitionProvider
	extension MdlLibUtils mlu = new MdlLibUtils
	
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
	def validateElseCompatibleTypes(ElseClause e){
		checkExpectedReal(e.value, typeError(MdlPackage::eINSTANCE.elseClause_Value))
	}
		
	@Check
	def validateCompatibleTypes(PWClause e){
		checkExpectedBoolean(e.cond, typeError(MdlPackage::eINSTANCE.PWClause_Cond))
		checkExpectedReal(e.value, typeError(MdlPackage::eINSTANCE.PWClause_Value))
	}
		
	@Check
	def validateOtherwiseCompatibleTypes(PiecewiseExpression e){
		if(e.otherwise != null){
			checkExpectedReal(e.otherwise, typeError(MdlPackage::eINSTANCE.piecewiseExpression_Otherwise))
		}
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
		
//	@Check
//	def validateCompatibleTypes(EquationDefinition e){
//		// only check if there is an RHS to check 
//		if(e.expression != null)
//			if(e.isVector)
//				checkExpectedVector(e.expression, typeError(MdlPackage::eINSTANCE.equationTypeDefinition_Expression))
//			else if(e.isMatrix)
//				checkExpectedMatrix(e.expression, typeError(MdlPackage::eINSTANCE.equationTypeDefinition_Expression))
//			else
//				checkExpectedReal(e.expression, typeError(MdlPackage::eINSTANCE.equationTypeDefinition_Expression))
//	}
		
	@Check
	def validateCompatibleTypes(RandomVariableDefinition e){
		if(e.distn != null){
			val stmtType = e.typeFor
			if(stmtType instanceof RandomVariableTypeInfo){
				val distType = e.distn.typeFor
				if(distType.isCompatible(TypeSystemProvider::PDF_TYPE)){
					switch(stmtType.underlyingType.typeClass){
						case(TypeInfoClass.Matrix):
							checkExpectedAndExpression(TypeSystemProvider::PDF_TYPE.makeMatrix, e.distn, typeError(MdlPackage::eINSTANCE.randomVariableDefinition_Distn))
						case(TypeInfoClass.Vector):
							checkExpectedAndExpression(TypeSystemProvider::PDF_TYPE.makeVector, e.distn, typeError(MdlPackage::eINSTANCE.randomVariableDefinition_Distn))
						default:
							checkExpectedAndExpression(TypeSystemProvider::PDF_TYPE, e.distn, typeError(MdlPackage::eINSTANCE.randomVariableDefinition_Distn))
					}
				}
				else if(distType.isCompatible(TypeSystemProvider::PMF_TYPE)){
					switch(stmtType.rvType){
						case(TypeInfoClass.Matrix):
							checkExpectedAndExpression(TypeSystemProvider::PMF_TYPE.makeMatrix, e.distn, typeError(MdlPackage::eINSTANCE.randomVariableDefinition_Distn))
						case(TypeInfoClass.Vector):
							checkExpectedAndExpression(TypeSystemProvider::PMF_TYPE.makeVector, e.distn, typeError(MdlPackage::eINSTANCE.randomVariableDefinition_Distn))
						default:
							checkExpectedAndExpression(TypeSystemProvider::PMF_TYPE, e.distn, typeError(MdlPackage::eINSTANCE.randomVariableDefinition_Distn))
					}
				}
			}
		}
	}
		
	@Check
	def validateCompatibleTypes(TransformedDefinition e){
		checkExpectedRealTransform(e.transform, typeError(MdlPackage::eINSTANCE.transformedDefinition_Transform))
		checkExpectedReal(e.expression, typeError(MdlPackage::eINSTANCE.equationTypeDefinition_Expression))
	}
		
	@Check
	def validateCompatibleVectorElement(VectorElement e){
		val vect = e.eContainer
		if(vect instanceof VectorLiteral){
			val vectType = vect.typeFor
			val exprType = e.typeFor
			if(!vectType.isCompatibleElement(exprType)){
				error("Element type '" + exprType.typeName + "' is incompatible with vector type '" + vectType.typeName + "'.",
					MdlPackage.eINSTANCE.vectorElement_Element, MdlValidator::INCOMPATIBLE_TYPES, vectType.typeName)
			}			
		}
	}
	
	@Check
	def validateCompatibleMatrixElement(MatrixElement e){
		val vect = EcoreUtil2.getContainerOfType(e.eContainer, MatrixLiteral)
		if(vect != null){
			val vectType = vect.typeFor
			val exprType = e.typeFor
			if(!vectType.isCompatibleElement(exprType)){
				error("Cell type '" + exprType.typeName + "' is incompatible with matrix type '" + vectType.typeName + "'.",
					MdlPackage.eINSTANCE.matrixElement_Cell, MdlValidator::INCOMPATIBLE_TYPES, vectType.typeName)
			}			
		}
	}
	
	@Check
	def validateIndexSpecType(IndexRange it){
		if(begin != null)
			checkExpectedIntl(begin, [e, a|
								error("Index value must be an 'Int' type, but was '" + a.typeName + "'.",
								MdlPackage.eINSTANCE.indexRange_Begin, MdlValidator::INCOMPATIBLE_TYPES)
								])
		if(end != null)
			checkExpectedIntl(begin, [e, a|
								error("Index value must be an 'Int' type, but was '" + a.typeName + "'.",
								MdlPackage.eINSTANCE.indexRange_End, MdlValidator::INCOMPATIBLE_TYPES)
								])
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

	@Check
	def validateFunctionBodyAndReturnCompatible(FunctionSpec it){
		val expectedRtnType = returnType.typeInfo
		checkExpectedAndExpression(expectedRtnType, body, 
			[e, a|
					error("Expected function return type of '" + e.typeName + "' but expression was of type '" + a.typeName + "'.",
							MdlLibPackage.eINSTANCE.functionSpec_Body, MdlValidator::INCOMPATIBLE_TYPES, a.typeName)
			]
		)
	}

	@Check
	def validateTypeSpecWellFormed(TypeSpec it){
		if(typeName.name == TypeDefinitionProvider::FUNCTION_TYPE && functionSpec == null){
			error("You must define a function specification when using the type name 'Function'.",
					MdlLibPackage.eINSTANCE.typeSpec_TypeName, 
					MdlValidator::MALFORMED_TYPE_SPEC, typeName.name)
		}
		if(typeName.name != TypeDefinitionProvider::FUNCTION_TYPE && functionSpec != null){
			error("You must use the type name 'Function' to define a function specification.",
					MdlLibPackage.eINSTANCE.typeSpec_TypeName, 
					MdlValidator::MALFORMED_TYPE_SPEC, typeName.name)
		}
	}

	def checkExpectedBoolean(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(TypeSystemProvider::BOOLEAN_TYPE, exp, errorLambda)
	}
	
	def checkExpectedReal(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(TypeSystemProvider::REAL_TYPE, exp, errorLambda)
	}
	
	def checkExpectedVector(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(TypeSystemProvider.REAL_VECTOR_TYPE, exp, errorLambda)
	}
	
	def checkExpectedMatrix(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(TypeSystemProvider.REAL_MATRIX_TYPE, exp, errorLambda)
	}
	
	def checkExpectedIntl(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(TypeSystemProvider::INT_TYPE, exp, errorLambda)
	}
	
//	def checkExpectedPdf(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
//		checkExpectedAndExpression(TypeSystemProvider::PDF_TYPE, exp, errorLambda)
//	}
//
//	def checkExpectedPdfVector(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
//		checkExpectedAndExpression(TypeSystemProvider::PDF_TYPE.makeVector, exp, errorLambda)
//	}

	def checkExpectedRealTransform(FunctionDefnBody transform, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = transform.funcSpec.funcDefn?.returnType ?: TypeSystemProvider::UNDEFINED_TYPE
		val expectedType = TypeSystemProvider::REAL_TYPE
		if(actualType != expectedType)
			errorLambda.apply(expectedType, actualType ?: TypeSystemProvider::UNDEFINED_TYPE)
	}
	
	def checkExpectedString(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(TypeSystemProvider::STRING_TYPE, exp, errorLambda)
	}
	
	def checkExpectedCategoryValueMatches(CategoryTypeInfo catType, CategoryValueTypeInfo catValType, (TypeInfo, TypeInfo) => void errorLambda){
		if(catType != catValType.owningCategory)
		  // assume that linking has taken care of ensuring that the value is correct
			errorLambda.apply(catType, catValType)
	}
	
	def checkRelationalOp(Expression lhs, Expression rhs, (TypeInfo, TypeInfo) => void leftErrorLambda,
			(TypeInfo, TypeInfo) => void rightErrorLambda){
		val lhsType = lhs?.typeFor ?: TypeSystemProvider::UNDEFINED_TYPE
		val rhsType = rhs?.typeFor ?: TypeSystemProvider::UNDEFINED_TYPE
		if(lhsType.underlyingType instanceof CategoryTypeInfo && rhsType.underlyingType instanceof CategoryValueTypeInfo){
			checkExpectedCategoryValueMatches(lhsType.underlyingType as CategoryTypeInfo, rhsType.underlyingType as CategoryValueTypeInfo, leftErrorLambda)
		}
		else if(rhsType.underlyingType instanceof CategoryTypeInfo && lhsType.underlyingType instanceof CategoryValueTypeInfo){
			checkExpectedCategoryValueMatches(rhsType.underlyingType as CategoryTypeInfo, lhsType.underlyingType as CategoryValueTypeInfo, rightErrorLambda)
		}
		else if(lhsType.underlyingType instanceof CategoryTypeInfo || lhsType.underlyingType instanceof CategoryValueTypeInfo){
			checkExpectedAndExpression(lhsType.underlyingType, rhs, rightErrorLambda)
		}
		else{
			checkExpectedAndExpression(TypeSystemProvider::REAL_TYPE, lhs, leftErrorLambda)
			checkExpectedAndExpression(TypeSystemProvider::REAL_TYPE, rhs, rightErrorLambda)
		}
	}

	def checkMathsOp(Expression lhs, Expression rhs, (TypeInfo, TypeInfo) => void leftErrorLambda,
		(TypeInfo, TypeInfo) => void rightErrorLambda){
		checkExpectedAndExpression(TypeSystemProvider::REAL_TYPE, lhs, leftErrorLambda)
		checkExpectedAndExpression(TypeSystemProvider::REAL_TYPE, rhs, rightErrorLambda)
	}

	def checkBoolOp(Expression lhs, Expression rhs, (TypeInfo, TypeInfo) => void leftErrorLambda,
				(TypeInfo, TypeInfo) => void rightErrorLambda){
		checkExpectedAndExpression(TypeSystemProvider::BOOLEAN_TYPE, lhs, leftErrorLambda)
		checkExpectedAndExpression(TypeSystemProvider::BOOLEAN_TYPE, rhs, rightErrorLambda)
	}

	def checkUnaryOp(String feature, Expression operand, (TypeInfo, TypeInfo) => void errorLambda){
		if(feature == '!') checkExpectedAndExpression(TypeSystemProvider::BOOLEAN_TYPE, operand, errorLambda)
		else checkExpectedAndExpression(TypeSystemProvider::REAL_TYPE, operand, errorLambda)
	}
	
	
	def checkAsOperator(Expression lhs, Expression rhs,  (TypeInfo, TypeInfo) => void leftErrorLambda,
				(TypeInfo, TypeInfo) => void rightErrorLambda){
		checkExpectedAndExpression(TypeSystemProvider::INT_TYPE, lhs, leftErrorLambda)
		if(rhs?.typeFor ?: TypeSystemProvider::UNDEFINED_TYPE == TypeSystemProvider::MAPPING_TYPE){
			checkArgumentMatchesAndExpression(TypeSystemProvider::MAPPING_TYPE, rhs, rightErrorLambda)
		}
		else{
			checkArgumentMatchesAndExpression(TypeSystemProvider::REAL_TYPE.makeReference, rhs, rightErrorLambda)
		}
	}
	
	def checkWhenOperator(EnumPair at, CategoryValueReference lhs, Expression rhs,  (TypeInfo, TypeInfo) => void leftErrorLambda,
				(TypeInfo, TypeInfo) => void rightErrorLambda){
		checkExpectedEnumType(lhs, leftErrorLambda)
		if(rhs != null){
			validateCategoricalMappingType(at, rhs, rightErrorLambda)
//			val attList = at.eContainer as AttributeList
//			val listDefn = attList.matchingListDefn
//			var expectedType = listDefn?.getCategoryMappingType(at.attributeName) ?: UNDEFINED_TYPE
//			checkExpectedAndExpression(expectedType, rhs, rightErrorLambda)
		}
	}
	
	def checkWhenOperator(EnumPair at, CategoryValueDefinition catValDefn,  (TypeInfo, TypeInfo) => void leftErrorLambda,
				(TypeInfo, TypeInfo) => void rightErrorLambda){
		val actualType = catValDefn.typeFor
		if(actualType.typeClass != TypeInfoClass.CategoryValue){
			leftErrorLambda.apply(new GeneralCategoryTypeInfo, actualType)
		}
		if(catValDefn.mappedTo != null){
			validateCategoricalMappingType(at, catValDefn.mappedTo, rightErrorLambda)
//			val attList = at.eContainer as AttributeList
//			val listDefn = attList.matchingListDefn
//			var expectedType = listDefn?.getCategoryMappingType(at.attributeName) ?: UNDEFINED_TYPE
//			checkExpectedAndExpression(expectedType, catValDefn.mappedTo, rightErrorLambda)
		}
	}

	private  def void validateCategoricalMappingType(EnumPair at, Expression mappingExpr, (TypeInfo, TypeInfo) => void typeErrorLambda){
		val attList = at.eContainer as AttributeList
		val listDefn = attList.matchingListDefn
		if(listDefn.isCatMappingPossible(at.attributeName)){
			val expectedType = listDefn.catMappingType ?: TypeSystemProvider::UNDEFINED_TYPE
			checkArgumentMatchesAndExpression(expectedType, mappingExpr, typeErrorLambda)
		}
	}

	def dispatch void checkExpectedAndExpression(CategoryTypeInfo expectedType, Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = exp?.typeFor ?: TypeSystemProvider::UNDEFINED_TYPE
		if(!expectedType.isCompatible(actualType)){
			errorLambda.apply(expectedType, actualType)
		} 
	}
	
	def void checkExpectedEnumType(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = exp?.typeFor ?: TypeSystemProvider::UNDEFINED_TYPE
		if(actualType.typeClass != TypeInfoClass.Category){
			errorLambda.apply(new GeneralCategoryTypeInfo, actualType)
		}
	}
	
	def dispatch void checkExpectedAndExpression(TypeInfo expectedType, Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = exp?.typeFor ?: TypeSystemProvider::UNDEFINED_TYPE
		var testActual = actualType
//		if(expectedType.underlyingType instanceof VectorTypeInfo && !(actualType.underlyingType instanceof VectorTypeInfo)){
//			testActual = actualType.makeVector
//		}
		if(!expectedType.isCompatible(testActual)){
			errorLambda.apply(expectedType, actualType)
		} 
	}

	def checkArgumentMatchesAndExpression(TypeInfo expectedType, Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = exp?.typeFor ?: TypeSystemProvider::UNDEFINED_TYPE
		var testActual = actualType
		if(expectedType.underlyingType instanceof VectorTypeInfo && !(actualType.underlyingType instanceof VectorTypeInfo)){
			testActual = actualType.makeVector
		}
		if(!expectedType.isArgumentCompatible(testActual)){
			errorLambda.apply(expectedType, actualType)
		} 
	}

	def checkAttributeTyping(AttributeList attList, ValuePair at, (TypeInfo, TypeInfo) => void errorLambda){
		val listDefn = attList.matchingListDefn
		if(listDefn != null && at != null){
			val attType = listDefn.getAttributeType(at.attributeName)
			if(at instanceof ValuePair){
				checkArgumentMatchesAndExpression(attType, at.expression, errorLambda)				
			}
		}
	}


	def checkPropertyAttributeTyping(PropertyStatement stmt, ValuePair at, (TypeInfo, TypeInfo) => void errorLambda){
		val attType = at.typeForProperty
		if(at instanceof ValuePair){
			checkArgumentMatchesAndExpression(attType, at.expression, errorLambda)				
		}
	}


	def checkSublistAttributeTyping(SubListExpression it, ValuePair at, (TypeInfo, TypeInfo) => void errorLambda){
		val subListDefn = findSublistMatch

		if(subListDefn != null){
			val attDefn = subListDefn.attributes.findFirst[name == at.attributeName]
			checkArgumentMatchesAndExpression(attDefn.attType, at.expression, errorLambda)
		}
	}

	def checkNamedFunctionArgumentTyping(ValuePair at, (TypeInfo, TypeInfo) => void errorLambda){
		checkArgumentMatchesAndExpression(at.namedArgumentType, at.expression, errorLambda)				
	}

	def checkFunctionArgumentTyping(UnnamedArgument at, (TypeInfo, TypeInfo) => void errorLambda){
		checkArgumentMatchesAndExpression(at.unamedArgumentType, at.argument, errorLambda)				
	}

	
}