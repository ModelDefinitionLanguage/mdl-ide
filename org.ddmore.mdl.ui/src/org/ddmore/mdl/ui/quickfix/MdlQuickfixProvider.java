
package org.ddmore.mdl.ui.quickfix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ddmore.mdl.domain.Attribute;
import org.ddmore.mdl.mdl.AdditiveExpression;
import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.ArgumentExpression;
import org.ddmore.mdl.mdl.ArgumentName;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.DataInputBlock;
import org.ddmore.mdl.mdl.DataObjectBlock;
import org.ddmore.mdl.mdl.DistributionName;
import org.ddmore.mdl.mdl.EnumType;
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.LogicalExpression;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.MdlFactory;
import org.ddmore.mdl.mdl.ModelObjectBlock;
import org.ddmore.mdl.mdl.MultiplicativeExpression;
import org.ddmore.mdl.mdl.OrExpression;
import org.ddmore.mdl.mdl.ParameterObjectBlock;
import org.ddmore.mdl.mdl.PowerExpression;
import org.ddmore.mdl.mdl.RandomList;
import org.ddmore.mdl.mdl.RandomVariableDefinitionBlock;
import org.ddmore.mdl.mdl.StructuralBlock;
import org.ddmore.mdl.mdl.StructuralParametersBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.SymbolName;
import org.ddmore.mdl.mdl.UnaryExpression;
import org.ddmore.mdl.mdl.UseType;
import org.ddmore.mdl.mdl.VarType;
import org.ddmore.mdl.mdl.VariabilityBlock;
import org.ddmore.mdl.mdl.VariabilityParametersBlock;
import org.ddmore.mdl.mdl.impl.StructuralParametersBlockImpl;
import org.ddmore.mdl.mdl.impl.SymbolNameImpl;
import org.ddmore.mdl.mdl.impl.VariabilityParametersBlockImpl;
import org.ddmore.mdl.services.MdlGrammarAccess;
import org.ddmore.mdl.types.DistributionType;
import org.ddmore.mdl.types.MdlDataType;
import org.ddmore.mdl.validation.AttributeValidator;
import org.ddmore.mdl.validation.DistributionValidator;
import org.ddmore.mdl.validation.MdlJavaValidator;
import org.ddmore.mdl.validation.Utils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;

import com.google.inject.Inject;
//import org.ddmore.mdl.mdl.OutputVariablesBlock;
//import org.ddmore.mdl.mdl.impl.OutputVariablesBlockImpl;

public class MdlQuickfixProvider extends DefaultQuickfixProvider {
	
	@Inject MdlGrammarAccess grammarAccess;    

	Attribute getAttributeById(String id){
		Map<String, Attribute> allAttributes = new HashMap<String, Attribute>();
		/*Data object*/
		for (Attribute attr: AttributeValidator.attrs_dataInput) 
			allAttributes.put(grammarAccess.getDataInputBlockAccess().
					getIdentifierDATA_INPUT_VARIABLESKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		/*Parameter object*/
		for (Attribute attr: AttributeValidator.attrs_structural) 
			allAttributes.put(grammarAccess.getStructuralBlockAccess().
					getIdentifierSTRUCTURALKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_variability) 
			allAttributes.put(grammarAccess.getVariabilityBlockAccess().
					getIdentifierVARIABILITYKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		/*Model object*/
		for (Attribute attr: AttributeValidator.attrs_covariateDef) 
			allAttributes.put(grammarAccess.getCovariateDefinitionBlockAccess().
					getIdentifierCOVARIATESKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_variabilityDef) 
			allAttributes.put(grammarAccess.getVariabilityDefinitionBlockAccess().
					getIdentifierVARIABILITY_LEVELSKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_individualVariables) 
			allAttributes.put(grammarAccess.getIndividualVariablesBlockAccess().
					getIdentifierINDIVIDUAL_VARIABLESKeyword_0_0().getValue()+ ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_ode) {
//			allAttributes.put(grammarAccess.getOdeBlockAccess().
//					getIdentifierODEKeyword_0_0_0().getValue() + ":" + attr.getName(), attr);
			allAttributes.put(grammarAccess.getOdeBlockAccess().
					getIdentifierDEQKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		}
//		for (Attribute attr: AttributeValidator.attrs_estimation) 
//			allAttributes.put(grammarAccess.getEstimationBlockAccess().
//					getIdentifierESTIMATIONKeyword_0_0().getValue() + ":" + attr.getName(), attr);
//		for (Attribute attr: AttributeValidator.attrs_simulation) 
//			allAttributes.put(grammarAccess.getSimulationBlockAccess().
//					getIdentifierSIMULATIONKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_observation) 
			allAttributes.put(grammarAccess.getObservationBlockAccess().
					getIdentifierOBSERVATIONKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_structuralParams) 
			allAttributes.put(grammarAccess.getStructuralParametersBlockAccess().
					getIdentifierSTRUCTURAL_PARAMETERSKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_variabilityParams) 
			allAttributes.put(grammarAccess.getVariabilityParametersBlockAccess().
					getIdentifierVARIABILITY_PARAMETERSKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		/*Design object*/
//		for (Attribute attr: AttributeValidator.attrs_studyDesign)    
//			allAttributes.put(grammarAccess.getStudyDesignBlockAccess().
//					getIdentifierSTUDY_DESIGNKeyword_0_0().getValue() + ":" + attr.getName(), attr);
//		for (Attribute attr: AttributeValidator.attrs_administration) 
//			allAttributes.put(grammarAccess.getAdministrationBlockAccess().
//					getIdentifierADMINISTRATIONKeyword_0_0().getValue() + ":" + attr.getName(), attr);
//		for (Attribute attr: AttributeValidator.attrs_action)         
//			allAttributes.put(grammarAccess.getActionBlockAccess().
//					getIdentifierACTIONKeyword_0_0().getValue() + ":" + attr.getName(), attr);
//		for (Attribute attr: AttributeValidator.attrs_sampling)       
//			allAttributes.put(grammarAccess.getSamplingBlockAccess().
//					getIdentifierSAMPLINGKeyword_0_0().getValue() + ":" + attr.getName() + attr.getName(), attr);
//		for (Attribute attr: AttributeValidator.attrs_designSpace)    
//			allAttributes.put(grammarAccess.getDesignSpaceBlockAccess().
//					getIdentifierDESIGN_SPACEKeyword_0_0().getValue() + ":" + attr.getName(), attr);
//		for (Attribute attr: AttributeValidator.attrs_hyperSpace)     
//			allAttributes.put(grammarAccess.getHyperSpaceBlockAccess().
//					getIdentifierHYPER_SPACEKeyword_0_0().getValue() + ":" + attr.getName(), attr);
        return allAttributes.get(id);
    }
	
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Fix attributes
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Fix(AttributeValidator.MSG_ATTRIBUTE_UNKNOWN)
	public void removeAttribute(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String attribute = issue.getData()[0];
		String description = "Remove attribute '" + attribute + "'";
		acceptor.accept(issue, description, description, "remove.png",new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				EObject container = element.eContainer();
				Arguments args = (Arguments)container;
				args.getNamedArguments().getArguments().remove(element);
			}
		});
	}
	
	@Fix(AttributeValidator.MSG_ATTRIBUTE_MISSING)
	public void addAttribute(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String attrId = issue.getData()[0];
		int index = attrId.indexOf(':');
		String attrName = (index > 0)? attrId.substring(index + 1): attrId;
		String description = "Insert attribute '" + attrName +"'";
		acceptor.accept(issue, description, description, "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				Attribute attribute = getAttributeById(attrId);
				if (attribute != null){
					Argument newArg = createArgument(attribute);
					Arguments args = (Arguments)element;
					args.getNamedArguments().getArguments().add(0, newArg);
				}
			} 
		});
	}
	
	@Fix(AttributeValidator.MSG_ATTRIBUTE_DEFINED)
	public void deleteDuplicateAttribute(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String attribute = issue.getData()[0];
		String description = "Remove attribute '" + attribute + "'";
		acceptor.accept(issue, description, description, "remove.png",new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				EObject container = element.eContainer();
				Arguments args = (Arguments)container;
				args.getNamedArguments().getArguments().remove(element);
			}
		});
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Fix distribution attributes
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Fix(DistributionValidator.MSG_DISTR_ATTRIBUTE_UNKNOWN)
	public void removeDistributionAttribute(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String attribute = issue.getData()[0];
		String description = "Remove attribute '" + attribute + "'";
		acceptor.accept(issue, description, description, "remove.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				EObject container = element.eContainer();
				Arguments args = (Arguments)container;
				args.getNamedArguments().getArguments().remove(element);
			}
		});
	}
	
	@Fix(DistributionValidator.MSG_DISTR_ATTRIBUTE_MISSING)
	public void addDistributionAttribute(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String attrId = issue.getData()[0];
		final String[] tokens = attrId.split(":");
		String description = "Insert attribute '" + tokens[1] +"'";
		acceptor.accept(issue, description, description, "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) { 
		        List<Attribute> recognizedAttrs = DistributionValidator.distr_attrs.get(tokens[0]);
		        if (recognizedAttrs != null)
		        	for (Attribute attr: recognizedAttrs){
		        		if (attr.getName().equals(tokens[1])) {
							Argument newArg = createArgument(attr);
							Arguments args = (Arguments)element;
							args.getNamedArguments().getArguments().add(0, newArg);
		        		}
			        }
			}
		});
	}
	
	@Fix(DistributionValidator.MSG_DISTR_ATTRIBUTE_DEFINED)
	public void deleteDuplicateDistributionAttribute(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String attribute = issue.getData()[0];
		String description = "Remove attribute '" + attribute + "'";
		acceptor.accept(issue, description, description, "remove.png",new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				EObject container = element.eContainer();
				Arguments args = (Arguments)container;
				args.getNamedArguments().getArguments().remove(element);
			}
		});
	}
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Automatically create EObjects
    //////////////////////////////////////////////////////////////////////////////////////////////////////////    
	
	AnyExpression createTypedExpression(Attribute attribute){
		MdlDataType type = attribute.getType();
		String value = attribute.getDefaultValue();
		switch (type){
			case TYPE_NAT: case TYPE_INT: case TYPE_REAL: case TYPE_PNAT: case TYPE_PREAL: 
				case TYPE_PROBABILITY: return createNumericExpression(value);
			case TYPE_REF: return createReferenceExpression(value);
			case TYPE_BOOLEAN: return createBooleanExpression(value);
//			case TYPE_TARGET: return createTargetLanguageExpression(value);
			case TYPE_VAR_TYPE: return createVarTypeExpression(value);
			case TYPE_USE: return createUseExpression(value);
			default:
				return createStringExpression(value);
		}	
	}
	
	private AnyExpression createVarTypeExpression(String value) {
		VarType tl = MdlFactory.eINSTANCE.createVarType();
		if (value.equals(grammarAccess.getVarTypeAccess().getContinuousContinuousKeyword_1_0().getValue())) {
			tl.setContinuous(value);
		} else {
			if (value.equals(grammarAccess.getVarTypeAccess().getCategoricalCategoricalKeyword_0_0_0().getValue())){
				tl.setCategorical(value);
			}
//			else {
//				if (value.equals(grammarAccess.getVarTypeAccess().getLikelihoodLikelihoodKeyword_2_0().getValue())){
//					tl.setLikelihood(value);
//				} else {
//					if (value.equals(grammarAccess.getVarTypeAccess().getM2LLM2LLKeyword_3_0().getValue())){
//						tl.setM2LL(value);
//					}
//				}
//			}
		}	
		EnumType t = MdlFactory.eINSTANCE.createEnumType();
		t.setType(tl);
		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();		
		expr.setType(t);
		return expr;
	}
	
	private AnyExpression createUseExpression(String value) {
		UseType tl = UseType.getByName(value);
		EnumType t = MdlFactory.eINSTANCE.createEnumType();
		t.setUse(tl);
		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();		
		expr.setType(t);
		return expr;
	}
	
//	AnyExpression createTargetLanguageExpression(String value){
//		TargetType tl = TargetType.getByName(value);
//		EnumType t = MdlFactory.eINSTANCE.createEnumType();
//		t.setTarget(tl);
//		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();		
//		expr.setType(t);
//		return expr;
//	}
	
	AnyExpression createBooleanExpression(String value){
		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();		
		Expression e = MdlFactory.eINSTANCE.createExpression();
		OrExpression or = MdlFactory.eINSTANCE.createOrExpression();
		AndExpression and = MdlFactory.eINSTANCE.createAndExpression();
		LogicalExpression logical = MdlFactory.eINSTANCE.createLogicalExpression();
		logical.setBoolean(value);
		and.getExpression().add(logical);
		or.getExpression().add(and);
		e.setExpression(or);
		expr.setExpression(e);
		return expr;
	}
	
	AnyExpression createNumericExpression(String value){
		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();		
		expr.setExpression(createNumberExpression(value));
		return expr;
	}
	
	Expression createNumberExpression(String value){
		Expression e = MdlFactory.eINSTANCE.createExpression();
		OrExpression or = createOrExpression(value);
		e.setExpression(or);
		return e;
	}

	OrExpression createOrExpression(String value){
		OrExpression or = MdlFactory.eINSTANCE.createOrExpression();
		AndExpression and = MdlFactory.eINSTANCE.createAndExpression();
		LogicalExpression logical = MdlFactory.eINSTANCE.createLogicalExpression();
		AdditiveExpression add =  MdlFactory.eINSTANCE.createAdditiveExpression();
		MultiplicativeExpression mult =  MdlFactory.eINSTANCE.createMultiplicativeExpression();
		PowerExpression power = MdlFactory.eINSTANCE.createPowerExpression();
		UnaryExpression unary = MdlFactory.eINSTANCE.createUnaryExpression();
		unary.setNumber(value);
		power.getExpression().add(unary);
		mult.getExpression().add(power);
		add.getExpression().add(mult);
		logical.setExpression1(add);
		and.getExpression().add(logical);
		or.getExpression().add(and);
		return or;
	}

	AnyExpression createReferenceExpression(String value){
		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();		
		Expression e = MdlFactory.eINSTANCE.createExpression();
		OrExpression or = MdlFactory.eINSTANCE.createOrExpression();
		AndExpression and = MdlFactory.eINSTANCE.createAndExpression();
		LogicalExpression logical = MdlFactory.eINSTANCE.createLogicalExpression();
		AdditiveExpression add =  MdlFactory.eINSTANCE.createAdditiveExpression();
		MultiplicativeExpression mult =  MdlFactory.eINSTANCE.createMultiplicativeExpression();
		PowerExpression power = MdlFactory.eINSTANCE.createPowerExpression();
		UnaryExpression unary = MdlFactory.eINSTANCE.createUnaryExpression();
		SymbolName symbName = MdlFactory.eINSTANCE.createSymbolName();
		symbName.setName(value);
		unary.setSymbol(symbName);
		power.getExpression().add(unary);
		mult.getExpression().add(power);
		add.getExpression().add(mult);
		logical.setExpression1(add);
		and.getExpression().add(logical);
		or.getExpression().add(and);
		e.setExpression(or);
		expr.setExpression(e);
		return expr;
	}
	
	AnyExpression createStringExpression(String value){
		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();		
		Expression e = MdlFactory.eINSTANCE.createExpression();
		OrExpression or = MdlFactory.eINSTANCE.createOrExpression();
		AndExpression and = MdlFactory.eINSTANCE.createAndExpression();
		LogicalExpression logical = MdlFactory.eINSTANCE.createLogicalExpression();
		AdditiveExpression add =  MdlFactory.eINSTANCE.createAdditiveExpression();
		add.setString(value);
		logical.setExpression1(add);
		and.getExpression().add(logical);
		or.getExpression().add(and);
		e.setExpression(or);
		expr.setExpression(e);
		return expr;
	}

	AnyExpression createListExpression(Attribute[] attributes){
		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();	
		org.ddmore.mdl.mdl.List list = createList(attributes);
		expr.setList(list);
		return expr;
	}

	org.ddmore.mdl.mdl.List createList(Attribute[] attributes){
		org.ddmore.mdl.mdl.List list = MdlFactory.eINSTANCE.createList();		
		Arguments args = MdlFactory.eINSTANCE.createArguments();				
		for (int i = 0; i< attributes.length; i++){
			Argument attr = createArgument(attributes[i]);
			args.getNamedArguments().getArguments().add(attr);
		}
		list.setArguments(args);
		return list;
	}

	Argument createArgument(Attribute attribute){
		Argument attr = MdlFactory.eINSTANCE.createArgument();
		ArgumentName argName = MdlFactory.eINSTANCE.createArgumentName();
		argName.setName(attribute.getName());
		AnyExpression attrExpr = createTypedExpression(attribute);
		ArgumentExpression argExpr = MdlFactory.eINSTANCE.createArgumentExpression();
		argExpr.setExpression(attrExpr);
		attr.setArgumentName(argName);
		attr.setExpression(argExpr);
		return attr;
	}
	
	Argument createArgumentWithExpression(Attribute attribute, AnyExpression attrExpr){
		Argument attr = MdlFactory.eINSTANCE.createArgument();
		ArgumentName argName = MdlFactory.eINSTANCE.createArgumentName();
		argName.setName(attribute.getName());
		attr.setArgumentName(argName);
		ArgumentExpression argExpr = MdlFactory.eINSTANCE.createArgumentExpression();
		argExpr.setExpression(attrExpr);
		attr.setExpression(argExpr);
		return attr;
	}
		
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Fix variable references
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	@Fix(MdlJavaValidator.MSG_UNRESOLVED_VARIABLE)
	public void removeVariable(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Remove symbol", "Remove symbol", "remove.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				if (element instanceof SymbolNameImpl){
					SymbolName ref = (SymbolName)element;
					EObject container = ref.eContainer();
					if (container instanceof StructuralParametersBlockImpl){
						StructuralParametersBlock structuralParametersBlock = (StructuralParametersBlock)container;
						structuralParametersBlock.getParameters().remove(element);
						return;
					} 
					if (container instanceof VariabilityParametersBlockImpl){
						VariabilityParametersBlock variabilityParametersBlock = (VariabilityParametersBlock)container;
						variabilityParametersBlock.getParameters().remove(element);
						return;
					} 
					/*
					if (container instanceof OutputVariablesBlockImpl){
						OutputVariablesBlock outputBlock = (OutputVariablesBlock) container;
						outputBlock.getVariables().remove(ref);
						return;
					}
					if (container instanceof VariableListImpl){
						VariableList variableList = (VariableList) container;
						variableList.getIdentifiers().remove(ref);
						return;
					}*/
				}
			}
		});
	}
	

	/////////////////////////////////////////////////////////////////////////////////////////////////
	//Insert variable declaration
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	//DATA_INPUT_VARIABLES
	//@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	@Fix(MdlJavaValidator.MSG_UNRESOLVED_VARIABLE)
	public void addVariableToInputVariables(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String blockName = grammarAccess.getDataInputBlockAccess().getIdentifierDATA_INPUT_VARIABLESKeyword_0_0().getValue();
		acceptor.accept(issue, "Add variable declaration to " + blockName, 
				"Add variable declaration to " + blockName, "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				MclObject mclObj = Utils.getMclObject(element);
				if (mclObj.getDataObject() != null){
					for (DataObjectBlock block: mclObj.getDataObject().getBlocks()){
						if (block.getDataInputBlock() != null){
							insertSymbolDeclaration(block.getDataInputBlock(), issue.getData()[0]);
							return;
						}
					}
					DataInputBlock block = MdlFactory.eINSTANCE.createDataInputBlock();
					block.setIdentifier(blockName);
					insertSymbolDeclaration(block, issue.getData()[0]);
					DataObjectBlock dataBlock =  MdlFactory.eINSTANCE.createDataObjectBlock();
					dataBlock.setDataInputBlock(block);
					mclObj.getDataObject().getBlocks().add(dataBlock); 
				}
			}
		});
	}
	
	void insertSymbolDeclaration(DataInputBlock block, String varName){
		SymbolDeclaration newSymbol = MdlFactory.eINSTANCE.createSymbolDeclaration();
		SymbolName symbName = MdlFactory.eINSTANCE.createSymbolName();
		symbName.setName(varName);
		newSymbol.setSymbolName(symbName);
		Attribute[] attributes = {AttributeValidator.attr_type, AttributeValidator.attr_units, AttributeValidator.attr_use};
		newSymbol.setList(createList(attributes));
		block.getVariables().add(newSymbol);
	}
	
	//RANDOM_VARIABLE_DEFINITION
	//@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	@Fix(MdlJavaValidator.MSG_UNRESOLVED_VARIABLE)
	public void addVariableToRandomVariables(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String blockName = grammarAccess.getRandomVariableDefinitionBlockAccess().getIdentifierRANDOM_VARIABLE_DEFINITIONKeyword_0_0().getValue();
		acceptor.accept(issue, "Add variable declaration to " + blockName, 
				"Add variable declaration to " + blockName, "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				MclObject mclObj = Utils.getMclObject(element);
				if (mclObj != null && mclObj.getModelObject() != null){
					for (ModelObjectBlock block: mclObj.getModelObject().getBlocks()){
						if (block.getRandomVariableDefinitionBlock() != null){
							insertRandomVariable(block.getRandomVariableDefinitionBlock(), issue.getData()[0]);
							return;
						}
					}
					RandomVariableDefinitionBlock block = MdlFactory.eINSTANCE.createRandomVariableDefinitionBlock();
					block.setIdentifier(blockName);
					insertRandomVariable(block, issue.getData()[0]);
					ModelObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createModelObjectBlock();
					mdlBlock.setRandomVariableDefinitionBlock(block);
					mclObj.getModelObject().getBlocks().add(mdlBlock); 
				}
			}
		});
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Fix parameter references
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//STRUCTURAL
		@Fix(MdlJavaValidator.MSG_UNRESOLVED_VARIABLE)
		public void addVariableToStructural(final Issue issue, IssueResolutionAcceptor acceptor) {
			final String blockName = grammarAccess.getStructuralBlockAccess().getIdentifierSTRUCTURALKeyword_0_0().getValue();
			acceptor.accept(issue, "Add parameter declaration to " + blockName, 
					"Add parameter declaration to " + blockName, "add.png", new ISemanticModification() {
				public void apply(EObject element, IModificationContext context) {
					MclObject mclObj = Utils.getMclObject(element);
					if (mclObj != null && mclObj.getParameterObject() != null){
						for (ParameterObjectBlock block: mclObj.getParameterObject().getBlocks()){
							if (block.getStructuralBlock() != null){
								block.getStructuralBlock().getParameters().add(createSymbolDeclaration(issue.getData()[0]));
								return;
							}
						}
						StructuralBlock block = MdlFactory.eINSTANCE.createStructuralBlock();
						block.setIdentifier(blockName);
						block.getParameters().add(createSymbolDeclaration(issue.getData()[0]));
						ParameterObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createParameterObjectBlock();
						mdlBlock.setStructuralBlock(block);
						mclObj.getParameterObject().getBlocks().add(mdlBlock); 
					}
				}
			});
		}	
		
		//VARIABILITY
		@Fix(MdlJavaValidator.MSG_UNRESOLVED_VARIABLE)
		public void addVariableToVariability(final Issue issue, IssueResolutionAcceptor acceptor) {
			final String blockName = grammarAccess.getVariabilityBlockAccess().getIdentifierVARIABILITYKeyword_0_0().getValue();
			acceptor.accept(issue, "Add parameter declaration to " + blockName, 
					"Add parameter declaration to " + blockName, "add.png", new ISemanticModification() {
				public void apply(EObject element, IModificationContext context) {
					MclObject mclObj = Utils.getMclObject(element);
					if (mclObj != null && mclObj.getParameterObject() != null){
						for (ParameterObjectBlock block: mclObj.getParameterObject().getBlocks()){
							if (block.getVariabilityBlock() != null){
								block.getVariabilityBlock().getParameters().add(createSymbolDeclaration(issue.getData()[0]));
								return;
							}
						}
						VariabilityBlock block = MdlFactory.eINSTANCE.createVariabilityBlock();
						block.setIdentifier(blockName);
						block.getParameters().add(createSymbolDeclaration(issue.getData()[0]));
						ParameterObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createParameterObjectBlock();
						mdlBlock.setVariabilityBlock(block);
						mclObj.getParameterObject().getBlocks().add(mdlBlock); 
					}
				}
			});
		}	
		

	SymbolDeclaration createSymbolDeclaration(String varName) {
		SymbolDeclaration newParam = MdlFactory.eINSTANCE.createSymbolDeclaration();
		SymbolName symbName = MdlFactory.eINSTANCE.createSymbolName();
		symbName.setName(varName);
		newParam.setSymbolName(symbName);		
		Attribute[] attributes = {AttributeValidator.attr_value};
		newParam.setList(createList(attributes));
		return newParam;
	}
	
	void insertRandomVariable(RandomVariableDefinitionBlock block, String varName){
		SymbolDeclaration newSymbol = MdlFactory.eINSTANCE.createSymbolDeclaration();
		SymbolName symbName = MdlFactory.eINSTANCE.createSymbolName();
		symbName.setName(varName);
		newSymbol.setSymbolName(symbName);
		RandomList list = createNormalDistribution();
		newSymbol.setRandomList(list);
		block.getVariables().add(newSymbol);
	}
	
	RandomList createNormalDistribution(){
		Attribute[] attributes = {DistributionValidator.attr_mean, DistributionValidator.attr_var};
		RandomList list = MdlFactory.eINSTANCE.createRandomList();	
		DistributionName type = MdlFactory.eINSTANCE.createDistributionName();
		type.setName(DistributionType.Normal.toString());
		list.setType(type);
		Arguments args = MdlFactory.eINSTANCE.createArguments();				
		for (int i = 0; i < attributes.length; i++){
			Argument attr = createArgument(attributes[i]);
			args.getNamedArguments().getArguments().add(attr);
		}
		list.setArguments(args);
		return list;
	}

}
