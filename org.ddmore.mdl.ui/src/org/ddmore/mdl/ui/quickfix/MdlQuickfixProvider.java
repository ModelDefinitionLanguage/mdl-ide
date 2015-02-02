
package org.ddmore.mdl.ui.quickfix;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ddmore.mdl.domain.Attribute;
import org.ddmore.mdl.mdl.AdditiveExpression;
import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.ArgumentName;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.DataInputBlock;
import org.ddmore.mdl.mdl.DataObject;
import org.ddmore.mdl.mdl.DataObjectBlock;
import org.ddmore.mdl.mdl.DistributionType;
import org.ddmore.mdl.mdl.EnumType;
import org.ddmore.mdl.mdl.EstimationBlock;
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.GroupVariablesBlock;
import org.ddmore.mdl.mdl.GroupVariablesBlockStatement;
import org.ddmore.mdl.mdl.IndividualVariablesBlock;
import org.ddmore.mdl.mdl.LogicalExpression;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.MdlFactory;
import org.ddmore.mdl.mdl.MixtureBlock;
import org.ddmore.mdl.mdl.ModelObject;
import org.ddmore.mdl.mdl.ModelObjectBlock;
import org.ddmore.mdl.mdl.ModelPredictionBlock;
import org.ddmore.mdl.mdl.ModelPredictionBlockStatement;
import org.ddmore.mdl.mdl.MultiplicativeExpression;
import org.ddmore.mdl.mdl.ObservationBlock;
import org.ddmore.mdl.mdl.OdeBlock;
import org.ddmore.mdl.mdl.OrExpression;
import org.ddmore.mdl.mdl.OutputVariablesBlock;
import org.ddmore.mdl.mdl.ParameterObject;
import org.ddmore.mdl.mdl.ParameterObjectBlock;
import org.ddmore.mdl.mdl.PowerExpression;
import org.ddmore.mdl.mdl.Primary;
import org.ddmore.mdl.mdl.RandomList;
import org.ddmore.mdl.mdl.RandomVariableDefinitionBlock;
import org.ddmore.mdl.mdl.SimulationBlock;
import org.ddmore.mdl.mdl.StructuralBlock;
import org.ddmore.mdl.mdl.StructuralParametersBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.SymbolName;
import org.ddmore.mdl.mdl.TargetType;
import org.ddmore.mdl.mdl.UnaryExpression;
import org.ddmore.mdl.mdl.UseType;
import org.ddmore.mdl.mdl.VarType;
import org.ddmore.mdl.mdl.VariabilityBlock;
import org.ddmore.mdl.mdl.VariabilityParametersBlock;
import org.ddmore.mdl.mdl.VariableList;
import org.ddmore.mdl.mdl.impl.MclImpl;
import org.ddmore.mdl.mdl.impl.OutputVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.StructuralParametersBlockImpl;
import org.ddmore.mdl.mdl.impl.VariabilityParametersBlockImpl;
import org.ddmore.mdl.mdl.impl.VariableListImpl;
import org.ddmore.mdl.services.MdlGrammarAccess;
import org.ddmore.mdl.types.MdlDataType;
import org.ddmore.mdl.types.VariableType;
import org.ddmore.mdl.validation.AttributeValidator;
import org.ddmore.mdl.validation.DistributionValidator;
import org.ddmore.mdl.validation.MdlJavaValidator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;
import com.google.inject.Inject;


public class MdlQuickfixProvider extends DefaultQuickfixProvider {
	
	@Inject MdlGrammarAccess grammarAccess;    

	Attribute getAttributeById(String id){
		Map<String, Attribute> allAttributes = new HashMap<String, Attribute>();
		/*Data object*/
		for (Attribute attr: AttributeValidator.attrs_dataInput) 
			allAttributes.put(grammarAccess.getDataInputBlockAccess().
					getIdentifierDATA_INPUT_VARIABLESKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_dataDerived) 
			allAttributes.put(grammarAccess.getDataDerivedBlockAccess().
					getIdentifierDATA_DERIVED_VARIABLESKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		/*Parameter object*/
		for (Attribute attr: AttributeValidator.attrs_structural) 
			allAttributes.put(grammarAccess.getStructuralBlockAccess().
					getIdentifierSTRUCTURALKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_variability) 
			allAttributes.put(grammarAccess.getVariabilityBlockAccess().
					getIdentifierVARIABILITYKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		/*Model object*/
		for (Attribute attr: AttributeValidator.attrs_inputVariables) 
			allAttributes.put(grammarAccess.getInputVariablesBlockAccess().
					getIdentifierMODEL_INPUT_VARIABLESKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_individualVariables) 
			allAttributes.put(grammarAccess.getIndividualVariablesBlockAccess().
					getIdentifierINDIVIDUAL_VARIABLESKeyword_0_0().getValue()+ ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_ode) {
			allAttributes.put(grammarAccess.getOdeBlockAccess().
					getIdentifierODEKeyword_0_0_0().getValue() + ":" + attr.getName(), attr);
			allAttributes.put(grammarAccess.getOdeBlockAccess().
					getIdentifierDEQKeyword_0_0_1().getValue() + ":" + attr.getName(), attr);
		}
		for (Attribute attr: AttributeValidator.attrs_estimation) 
			allAttributes.put(grammarAccess.getEstimationBlockAccess().
					getIdentifierESTIMATIONKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_simulation) 
			allAttributes.put(grammarAccess.getSimulationBlockAccess().
					getIdentifierSIMULATIONKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_observation) 
			allAttributes.put(grammarAccess.getObservationBlockAccess().
					getIdentifierOBSERVATIONKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_structuralParams) 
			allAttributes.put(grammarAccess.getStructuralParametersBlockAccess().
					getIdentifierSTRUCTURAL_PARAMETERSKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_variabilityParams) 
			allAttributes.put(grammarAccess.getVariabilityParametersBlockAccess().
					getIdentifierVARIABILITY_PARAMETERSKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_randomVars) 
			allAttributes.put(grammarAccess.getRandomVariableDefinitionBlockAccess().
					getIdentifierRANDOM_VARIABLE_DEFINITIONKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		/*Design object*/
		for (Attribute attr: AttributeValidator.attrs_studyDesign)    
			allAttributes.put(grammarAccess.getStudyDesignBlockAccess().
					getIdentifierSTUDY_DESIGNKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_administration) 
			allAttributes.put(grammarAccess.getAdministrationBlockAccess().
					getIdentifierADMINISTRATIONKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_action)         
			allAttributes.put(grammarAccess.getActionBlockAccess().
					getIdentifierACTIONKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_sampling)       
			allAttributes.put(grammarAccess.getSamplingBlockAccess().
					getIdentifierSAMPLINGKeyword_0_0().getValue() + ":" + attr.getName() + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_designSpace)    
			allAttributes.put(grammarAccess.getDesignSpaceBlockAccess().
					getIdentifierDESIGN_SPACEKeyword_0_0().getValue() + ":" + attr.getName(), attr);
		for (Attribute attr: AttributeValidator.attrs_hyperSpace)     
			allAttributes.put(grammarAccess.getHyperSpaceBlockAccess().
					getIdentifierHYPER_SPACEKeyword_0_0().getValue() + ":" + attr.getName(), attr);
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
				args.getArguments().remove(element);
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
					args.getArguments().add(0, newArg);
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
				args.getArguments().remove(element);
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
				args.getArguments().remove(element);
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
							args.getArguments().add(0, newArg);
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
				args.getArguments().remove(element);
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
			case TYPE_PREAL:
			case TYPE_REAL:
			case TYPE_PROBABILITY: return createNumericExpression(value);
			case TYPE_REF: return createReferenceExpression(value);
			case TYPE_BOOLEAN: return createBooleanExpression(value);
			case TYPE_TARGET: return createTargetLanguageExpression(value);
			case TYPE_VAR_TYPE: return createVarTypeExpression(value);
			case TYPE_USE: return createUseExpression(value);
			case TYPE_DISTRIBUTION: return createDistributionExpression(value);
			default:
				return createStringExpression(value);
		}
	
	}
	
	
	private AnyExpression createVarTypeExpression(String value) {
		VarType tl = MdlFactory.eINSTANCE.createVarType();
		if (value.equals(VariableType.CC_CONTINUOUS)) {
			tl.setContinuous(value);
		} else {
			if (value.equals(VariableType.CC_CATEGORICAL)){
				tl.setCategorical(value);
			} else {
				if (value.equals(VariableType.CC_LIKELIHOOD)){
					tl.setLikelihood(value);
				} else {
					if (value.equals(VariableType.CC_M2LL)){
						tl.setM2LL(value);
					}
				}
			}
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
	
	private AnyExpression createDistributionExpression(String value) {
		DistributionType distribution = DistributionType.getByName(value);
		EnumType t = MdlFactory.eINSTANCE.createEnumType();
		t.setDistribution(distribution);
		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();		
		expr.setType(t);
		return expr;
	}
	
	AnyExpression createTargetLanguageExpression(String value){
		TargetType tl = TargetType.getByName(value);
		EnumType t = MdlFactory.eINSTANCE.createEnumType();
		t.setTarget(tl);
		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();		
		expr.setType(t);
		return expr;
	}
	
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

	Primary createPrimary(String value){
		Primary primary =  MdlFactory.eINSTANCE.createPrimary();
		primary.setExpression(createOrExpression(value));
		return primary;
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
			args.getArguments().add(attr);
		}
		list.setArguments(args);
		return list;
	}

	Argument createArgument(Attribute attribute){
		Argument attr = MdlFactory.eINSTANCE.createArgument();
		ArgumentName argName = MdlFactory.eINSTANCE.createArgumentName();
		argName.setName(attribute.getName());
		AnyExpression attrExpr = createTypedExpression(attribute);
		attr.setArgumentName(argName);
		attr.setExpression(attrExpr);
		return attr;
	}
	
	Argument createArgumentWithExpression(Attribute attribute, AnyExpression attrExpr){
		Argument attr = MdlFactory.eINSTANCE.createArgument();
		ArgumentName argName = MdlFactory.eINSTANCE.createArgumentName();
		argName.setName(attribute.getName());
		attr.setArgumentName(argName);
		attr.setExpression(attrExpr);
		return attr;
	}
	
	RandomList createRandomList(Attribute[] attributes){
		RandomList list = MdlFactory.eINSTANCE.createRandomList();	
		list.setIdentifier(grammarAccess.getRandomListAccess().getIdentifierTildeKeyword_0_0().getValue());
		Arguments args = MdlFactory.eINSTANCE.createArguments();				
		for (int i = 0; i < attributes.length; i++){
			Argument attr = createArgument(attributes[i]);
			args.getArguments().add(attr);
		}
		list.setArguments(args);
		return list;
	}
		
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Fix variable references
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
	//@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	@Fix(MdlJavaValidator.MSG_SYMBOL_UNKNOWN)
	public void removeVariable(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Remove symbol", "Remove symbol", "remove.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
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
				if (container instanceof OutputVariablesBlockImpl){
					OutputVariablesBlock outputBlock = (OutputVariablesBlock) container;
					outputBlock.getVariables().remove(ref);
					return;
				}
				if (container instanceof VariableListImpl){
					VariableList variableList = (VariableList) container;
					variableList.getIdentifiers().remove(ref);
					return;
				}
			}
		});
	}
	

	/////////////////////////////////////////////////////////////////////////////////////////////////
	//Insert variable declaration
	/////////////////////////////////////////////////////////////////////////////////////////////////
	ModelObject getModelObject(EObject element){
		Resource resource = element.eResource();
		for(EObject obj1: resource.getContents()) {
			if (obj1 instanceof MclImpl){
				Mcl mcl = (Mcl)obj1;
				for (MclObject obj: mcl.getObjects())
					if (obj.getModelObject() != null) return obj.getModelObject();
			}
		}
		return null;
	}
	
	DataObject getDataObject(EObject element){
		Resource resource = element.eResource();
		for(EObject obj1: resource.getContents()) {
			if (obj1 instanceof MclImpl){
				Mcl mcl = (Mcl)obj1;
				for (MclObject obj: mcl.getObjects())
					if (obj.getDataObject() != null) return obj.getDataObject();
			}
		}
		return null;
	}
	
	//DATA_INPUT_VARIABLES
	//@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	@Fix(MdlJavaValidator.MSG_SYMBOL_UNKNOWN)
	public void addVariableToInputVariables(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String blockName = grammarAccess.getDataInputBlockAccess().getIdentifierDATA_INPUT_VARIABLESKeyword_0_0().getValue();
		acceptor.accept(issue, "Add variable declaration to " + blockName, 
				"Add variable declaration to " + blockName, "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				DataObject obj = getDataObject(element);
				if (obj != null){
					for (DataObjectBlock block: obj.getBlocks()){
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
					obj.getBlocks().add(dataBlock); 
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
	@Fix(MdlJavaValidator.MSG_SYMBOL_UNKNOWN)
	public void addVariableToRandomVariables(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String blockName = grammarAccess.getRandomVariableDefinitionBlockAccess().getIdentifierRANDOM_VARIABLE_DEFINITIONKeyword_0_0().getValue();
		acceptor.accept(issue, "Add variable declaration to " + blockName, 
				"Add variable declaration to " + blockName, "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				ModelObject obj = getModelObject(element);
				if (obj != null){
					for (ModelObjectBlock block: obj.getBlocks()){
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
					obj.getBlocks().add(mdlBlock); 
				}
			}
		});
	}
	
	//GROUP_VARIABLES
	//@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	@Fix(MdlJavaValidator.MSG_SYMBOL_UNKNOWN)
	public void addVariableToGroupVariables(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String blockName = grammarAccess.getGroupVariablesBlockAccess().getIdentifierGROUP_VARIABLESKeyword_0_0().getValue();
		acceptor.accept(issue, "Add variable declaration to " + blockName, 
				"Add variable declaration to " + blockName, "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				ModelObject obj = getModelObject(element);
				if (obj != null){
					for (ModelObjectBlock block: obj.getBlocks()){
						if (block.getGroupVariablesBlock() != null){
							insertSymbolDeclaration(block.getGroupVariablesBlock(), issue.getData()[0]);
							return;
						}
					}
					GroupVariablesBlock block = MdlFactory.eINSTANCE.createGroupVariablesBlock();
					block.setIdentifier(blockName);
					insertSymbolDeclaration(block, issue.getData()[0]);
					ModelObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createModelObjectBlock();
					mdlBlock.setGroupVariablesBlock(block);
					obj.getBlocks().add(mdlBlock); 
				}
			}
		});
	}	
	void insertSymbolDeclaration(GroupVariablesBlock block, String varName){
		GroupVariablesBlockStatement groupSt =  MdlFactory.eINSTANCE.createGroupVariablesBlockStatement();
		groupSt.setVariable(createSymbolDeclaration(varName));
		block.getStatements().add(groupSt);
	}
	
	//MIXTURE
	//@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	@Fix(MdlJavaValidator.MSG_SYMBOL_UNKNOWN)
	public void addVariableToMixture(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String blockName = grammarAccess.getMixtureBlockAccess().getIdentifierMIXTUREKeyword_0_0().getValue();
		acceptor.accept(issue, "Add variable declaration to " + blockName, 
				"Add variable declaration to " + blockName, "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				ModelObject obj = getModelObject(element);
				if (obj != null){
					for (ModelObjectBlock block: obj.getBlocks()){
						if (block.getGroupVariablesBlock() != null){
							for (GroupVariablesBlockStatement st: block.getGroupVariablesBlock().getStatements()){
								if (st.getMixtureBlock() != null){
									st.getMixtureBlock().getVariables().add(createSymbolDeclaration(issue.getData()[0]));
									return;
								}
							}
							//create MIXTURE block
							block.getGroupVariablesBlock().getStatements().add(createGroupVariablesBlockStatement(issue.getData()[0]));
							return;
						}
					}
					GroupVariablesBlock block = MdlFactory.eINSTANCE.createGroupVariablesBlock();
					block.setIdentifier(grammarAccess.getGroupVariablesBlockAccess().getIdentifierGROUP_VARIABLESKeyword_0_0().getValue());
					block.getStatements().add(createGroupVariablesBlockStatement(issue.getData()[0]));
					ModelObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createModelObjectBlock();
					mdlBlock.setGroupVariablesBlock(block);
					obj.getBlocks().add(mdlBlock); 
				}
			}
		});
	}
	GroupVariablesBlockStatement createGroupVariablesBlockStatement(String varName)
	{
		MixtureBlock mix = MdlFactory.eINSTANCE.createMixtureBlock();
		mix.setIdentifier(grammarAccess.getMixtureBlockAccess().getIdentifierMIXTUREKeyword_0_0().getValue());
		mix.getVariables().add(createSymbolDeclaration(varName));
		GroupVariablesBlockStatement groupSt =  MdlFactory.eINSTANCE.createGroupVariablesBlockStatement();
		groupSt.setMixtureBlock(mix);
		return groupSt;
	}
	
	//INDIVIDUAL_VARIABLES
	//@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	@Fix(MdlJavaValidator.MSG_SYMBOL_UNKNOWN)
	public void addVariableToIndividualVariables(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String blockName = grammarAccess.getIndividualVariablesBlockAccess().getIdentifierINDIVIDUAL_VARIABLESKeyword_0_0().getValue();
		acceptor.accept(issue, "Add variable declaration to " + blockName, 
				"Add variable declaration to " + blockName, "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				ModelObject obj = getModelObject(element);
				if (obj != null){
					for (ModelObjectBlock block: obj.getBlocks()){
						if (block.getIndividualVariablesBlock() != null){
							block.getIndividualVariablesBlock().getVariables().add(createSymbolDeclaration(issue.getData()[0]));
							return;
						}
					}
					IndividualVariablesBlock block = MdlFactory.eINSTANCE.createIndividualVariablesBlock();
					block.setIdentifier(blockName);
					block.getVariables().add(createSymbolDeclaration(issue.getData()[0]));
					ModelObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createModelObjectBlock();
					mdlBlock.setIndividualVariablesBlock(block);
					obj.getBlocks().add(mdlBlock); 
				}
			}
		});
	}	
	
	//MODEL_PREDICTION
	//@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	@Fix(MdlJavaValidator.MSG_SYMBOL_UNKNOWN)
	public void addVariableToModelPrediction(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String blockName = grammarAccess.getModelPredictionBlockAccess().getIdentifierMODEL_PREDICTIONKeyword_0_0().getValue();
		acceptor.accept(issue, "Add variable declaration to " + blockName, 
				"Add variable declaration to " + blockName, "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				ModelObject obj = getModelObject(element);
				if (obj != null){
					for (ModelObjectBlock block: obj.getBlocks()){
						if (block.getModelPredictionBlock() != null){
							insertSymbolDeclaration(block.getModelPredictionBlock(), issue.getData()[0]);
							return;
						}
					}
					ModelPredictionBlock block = MdlFactory.eINSTANCE.createModelPredictionBlock();
					block.setIdentifier(blockName);
					insertSymbolDeclaration(block, issue.getData()[0]);
					ModelObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createModelObjectBlock();
					mdlBlock.setModelPredictionBlock(block);
					obj.getBlocks().add(mdlBlock); 
				}
			}
		});
	}	
	void insertSymbolDeclaration(ModelPredictionBlock block, String varName){
		ModelPredictionBlockStatement groupSt =  MdlFactory.eINSTANCE.createModelPredictionBlockStatement();
		groupSt.setVariable(createSymbolDeclaration(varName));
		block.getStatements().add(groupSt);
	}
	
	//ODE
	//@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	@Fix(MdlJavaValidator.MSG_SYMBOL_UNKNOWN)
	public void addVariableToOde(final Issue issue, IssueResolutionAcceptor acceptor) {
		String blockName = grammarAccess.getOdeBlockAccess().getIdentifierDEQKeyword_0_0_1().getValue();
		acceptor.accept(issue, "Add variable declaration to " + blockName, 
				"Add variable declaration to " + blockName, "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				ModelObject obj = getModelObject(element);
				if (obj != null){
					for (ModelObjectBlock block: obj.getBlocks()){
						if (block.getModelPredictionBlock() != null){
							for (ModelPredictionBlockStatement st: block.getModelPredictionBlock().getStatements()){
								if (st.getOdeBlock() != null){
									st.getOdeBlock().getVariables().add(createSymbolDeclaration(issue.getData()[0]));
									return;
								}
							}
							//create ODE block
							block.getModelPredictionBlock().getStatements().add(createModelPredictionBlockStatementOde(issue.getData()[0]));
							return;
						}
					}
					//create MODEL_PREDICATION block
					ModelPredictionBlock block = MdlFactory.eINSTANCE.createModelPredictionBlock();
					block.setIdentifier(grammarAccess.getModelPredictionBlockAccess().getIdentifierMODEL_PREDICTIONKeyword_0_0().getValue());
					block.getStatements().add(createModelPredictionBlockStatementOde(issue.getData()[0]));
					ModelObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createModelObjectBlock();
					mdlBlock.setModelPredictionBlock(block);
					obj.getBlocks().add(mdlBlock); 
				}
			}
		});
	}	
	ModelPredictionBlockStatement createModelPredictionBlockStatementOde(String varName){
		OdeBlock ode = MdlFactory.eINSTANCE.createOdeBlock();
		ode.setIdentifier(grammarAccess.getOdeBlockAccess().getIdentifierDEQKeyword_0_0_1().getValue());
		ode.getVariables().add(createSymbolDeclaration(varName));
		ModelPredictionBlockStatement mpSt =  MdlFactory.eINSTANCE.createModelPredictionBlockStatement();
		mpSt.setOdeBlock(ode);
		return mpSt;
	}	
	
	//OBSERVATION
	//@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	@Fix(MdlJavaValidator.MSG_SYMBOL_UNKNOWN)
	public void addVariableToObservation(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String blockName = grammarAccess.getObservationBlockAccess().getIdentifierOBSERVATIONKeyword_0_0().getValue();
		acceptor.accept(issue, "Add variable declaration to " + blockName, 
				"Add variable declaration to " + blockName, "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				ModelObject obj = getModelObject(element);
				if (obj != null){
					for (ModelObjectBlock block: obj.getBlocks()){
						if (block.getObservationBlock() != null){
							block.getObservationBlock().getVariables().add(createSymbolDeclaration(issue.getData()[0]));
							return;
						}
					}
					ObservationBlock block = MdlFactory.eINSTANCE.createObservationBlock();
					block.setIdentifier(blockName);
					block.getVariables().add(createSymbolDeclaration(issue.getData()[0]));
					ModelObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createModelObjectBlock();
					mdlBlock.setObservationBlock(block);
					obj.getBlocks().add(mdlBlock); 				
				}
			}
		});
	}	

	//SIMULATION
	//@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	@Fix(MdlJavaValidator.MSG_SYMBOL_UNKNOWN)
	public void addVariableToSimulation(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String blockName = grammarAccess.getSimulationBlockAccess().getIdentifierSIMULATIONKeyword_0_0().getValue();
		acceptor.accept(issue, "Add variable declaration to " + blockName, 
				"Add variable declaration to " + blockName, "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				ModelObject obj = getModelObject(element);
				if (obj != null){
					for (ModelObjectBlock block: obj.getBlocks()){
						if (block.getSimulationBlock() != null){
							block.getSimulationBlock().getVariables().add(createSymbolDeclaration(issue.getData()[0]));
							return;
						}
					}
					SimulationBlock block = MdlFactory.eINSTANCE.createSimulationBlock();
					block.setIdentifier(blockName);
					block.getVariables().add(createSymbolDeclaration(issue.getData()[0]));
					ModelObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createModelObjectBlock();
					mdlBlock.setSimulationBlock(block);
					obj.getBlocks().add(mdlBlock); 
				}
			}
		});
	}	

	//ESTIMATION
		//@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
		@Fix(MdlJavaValidator.MSG_SYMBOL_UNKNOWN)
		public void addVariableToEstimation(final Issue issue, IssueResolutionAcceptor acceptor) {
			final String blockName = grammarAccess.getEstimationBlockAccess().getIdentifierESTIMATIONKeyword_0_0().getValue();
			acceptor.accept(issue, "Add variable declaration to " + blockName, 
					"Add variable declaration to " + blockName, "add.png", new ISemanticModification() {
				public void apply(EObject element, IModificationContext context) {
					ModelObject obj = getModelObject(element);
					if (obj != null){
						for (ModelObjectBlock block: obj.getBlocks()){
							if (block.getEstimationBlock() != null){
								block.getEstimationBlock().getVariables().add(createSymbolDeclaration(issue.getData()[0]));
								return;
							}
						}
						EstimationBlock block = MdlFactory.eINSTANCE.createEstimationBlock();
						block.setIdentifier(blockName);
						block.getVariables().add(createSymbolDeclaration(issue.getData()[0]));
						ModelObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createModelObjectBlock();
						mdlBlock.setEstimationBlock(block);
						obj.getBlocks().add(mdlBlock); 
					}
				}
			});
		}	
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Fix parameteter references
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		ParameterObject getParameterObject(EObject element){
			Resource resource = element.eResource();
			for(EObject obj1: resource.getContents()) {
				if (obj1 instanceof MclImpl){
					Mcl mcl = (Mcl)obj1;
					for (MclObject obj: mcl.getObjects())
						if (obj.getParameterObject() != null) return obj.getParameterObject();
				}
			}
			return null;
		}


		//STRUCTURAL
		@Fix(MdlJavaValidator.MSG_SYMBOL_UNKNOWN)
		public void addVariableToStructural(final Issue issue, IssueResolutionAcceptor acceptor) {
			final String blockName = grammarAccess.getStructuralBlockAccess().getIdentifierSTRUCTURALKeyword_0_0().getValue();
			acceptor.accept(issue, "Add parameter declaration to " + blockName, 
					"Add parameter declaration to " + blockName, "add.png", new ISemanticModification() {
				public void apply(EObject element, IModificationContext context) {
					ParameterObject obj = getParameterObject(element);
					if (obj != null){
						for (ParameterObjectBlock block: obj.getBlocks()){
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
						obj.getBlocks().add(mdlBlock); 
					}
				}
			});
		}	
		
		//VARIABILITY
		@Fix(MdlJavaValidator.MSG_SYMBOL_UNKNOWN)
		public void addVariableToVariability(final Issue issue, IssueResolutionAcceptor acceptor) {
			final String blockName = grammarAccess.getVariabilityBlockAccess().getIdentifierVARIABILITYKeyword_0_0().getValue();
			acceptor.accept(issue, "Add parameter declaration to " + blockName, 
					"Add parameter declaration to " + blockName, "add.png", new ISemanticModification() {
				public void apply(EObject element, IModificationContext context) {
					ParameterObject obj = getParameterObject(element);
					if (obj != null){
						for (ParameterObjectBlock block: obj.getBlocks()){
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
						obj.getBlocks().add(mdlBlock); 
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
		Attribute[] attributes = {DistributionValidator.attr_type, DistributionValidator.attr_mean, 
				DistributionValidator.attr_var, DistributionValidator.attr_level};
		RandomList list = createRandomList(attributes);
		newSymbol.setRandomList(list);
		block.getVariables().add(newSymbol);
	}
	
	//PRIOR_PARAMETERS
	//block, diag, same
}
