
package org.ddmore.mdl.ui.quickfix;

import java.awt.image.renderable.ParameterBlock;
import java.util.Iterator;

import org.ddmore.mdl.mdl.AdditiveExpression;
import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.ArgumentName;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.ConditionalExpression;
import org.ddmore.mdl.mdl.DataInputBlock;
import org.ddmore.mdl.mdl.DataObject;
import org.ddmore.mdl.mdl.DataObjectBlock;
import org.ddmore.mdl.mdl.DesignBlock;
import org.ddmore.mdl.mdl.DesignBlockStatement;
import org.ddmore.mdl.mdl.Distribution;
import org.ddmore.mdl.mdl.DistributionArgument; 
import org.ddmore.mdl.mdl.DistributionArguments;
import org.ddmore.mdl.mdl.EnumType;
import org.ddmore.mdl.mdl.EstimationBlock;
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.GroupVariablesBlock;
import org.ddmore.mdl.mdl.GroupVariablesBlockStatement;
import org.ddmore.mdl.mdl.ImportBlock;
import org.ddmore.mdl.mdl.ImportedFunction;
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
import org.ddmore.mdl.mdl.SymbolList;
import org.ddmore.mdl.mdl.SymbolModification;
import org.ddmore.mdl.mdl.SymbolName;
import org.ddmore.mdl.mdl.TargetLanguage;
import org.ddmore.mdl.mdl.UnaryExpression;
import org.ddmore.mdl.mdl.UseType;
import org.ddmore.mdl.mdl.VarType;
import org.ddmore.mdl.mdl.VariabilityBlock;
import org.ddmore.mdl.mdl.VariabilityBlockStatement;
import org.ddmore.mdl.mdl.VariabilityParametersBlock;
import org.ddmore.mdl.mdl.VariableList;
import org.ddmore.mdl.mdl.Vector;
import org.ddmore.mdl.mdl.impl.DesignBlockImpl;
import org.ddmore.mdl.mdl.impl.DesignBlockStatementImpl;
import org.ddmore.mdl.mdl.impl.ImportBlockImpl;
import org.ddmore.mdl.mdl.impl.MclImpl;
import org.ddmore.mdl.mdl.impl.OutputVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.ParameterBlockImpl;
import org.ddmore.mdl.mdl.impl.StructuralParametersBlockImpl;
import org.ddmore.mdl.mdl.impl.SymbolListImpl;
import org.ddmore.mdl.mdl.impl.SymbolModificationImpl;
import org.ddmore.mdl.mdl.impl.VariabilityParametersBlockImpl;
import org.ddmore.mdl.mdl.impl.VariableListImpl;
import org.ddmore.mdl.services.MdlGrammarAccess;
import org.ddmore.mdl.types.MdlDataType;
import org.ddmore.mdl.types.VariableType;
import org.ddmore.mdl.validation.Attribute;
import org.ddmore.mdl.validation.AttributeValidator;
import org.ddmore.mdl.validation.DistributionValidator;
import org.ddmore.mdl.validation.MdlJavaValidator;
import org.ddmore.mdl.validation.FunctionValidator;
import org.eclipse.emf.common.util.TreeIterator;
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
				Attribute attribute = AttributeValidator.getAttributeById(attrId);
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
				DistributionArguments args = (DistributionArguments)container;
				args.getArguments().remove(element);
			}
		});
	}
	
	@Fix(DistributionValidator.MSG_DISTR_ATTRIBUTE_MISSING)
	public void addDistributionAttribute(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String attrId = issue.getData()[0];
		int index = attrId.indexOf(':');
		String attrName = (index > 0)? attrId.substring(index + 1): attrId;
		String description = "Insert attribute '" + attrName +"'";
		acceptor.accept(issue, description, description, "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				Attribute attribute = DistributionValidator.getAttributeById(attrId);
				if (attribute != null) {
					DistributionArgument newArg = createDistributionArgument(attribute);
					DistributionArguments args = (DistributionArguments)element;
					args.getArguments().add(0, newArg);
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
				DistributionArguments args = (DistributionArguments)container;
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
		System.out.println("Creating use value: " + value);
		UseType tl = MdlFactory.eINSTANCE.createUseType();
		tl.setIdentifier(value);
		EnumType t = MdlFactory.eINSTANCE.createEnumType();
		t.setUse(tl);
		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();		
		expr.setType(t);
		return expr;
	}
	
	AnyExpression createTargetLanguageExpression(String value){
		TargetLanguage tl = MdlFactory.eINSTANCE.createTargetLanguage();
		tl.setIdentifier(value);
		EnumType t = MdlFactory.eINSTANCE.createEnumType();
		t.setTarget(tl);
		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();		
		expr.setType(t);
		return expr;
	}
	
	AnyExpression createBooleanExpression(String value){
		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();		
		Expression e = MdlFactory.eINSTANCE.createExpression();
		ConditionalExpression cond = MdlFactory.eINSTANCE.createConditionalExpression();
		OrExpression or = MdlFactory.eINSTANCE.createOrExpression();
		AndExpression and = MdlFactory.eINSTANCE.createAndExpression();
		LogicalExpression logical = MdlFactory.eINSTANCE.createLogicalExpression();
		logical.setBoolean(value);
		and.getExpression().add(logical);
		or.getExpression().add(and);
		cond.setExpression(or);
		e.setConditionalExpression(cond);
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
		ConditionalExpression cond = MdlFactory.eINSTANCE.createConditionalExpression();
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
		cond.setExpression(or);
		e.setConditionalExpression(cond);
		return e;
	}
	
	Primary createPrimary(String value){
		Primary primary =  MdlFactory.eINSTANCE.createPrimary();
		primary.setNumber(value);
		return primary;
	}
	
	AnyExpression createReferenceExpression(String value){
		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();		
		Expression e = MdlFactory.eINSTANCE.createExpression();
		ConditionalExpression cond = MdlFactory.eINSTANCE.createConditionalExpression();
		OrExpression or = MdlFactory.eINSTANCE.createOrExpression();
		AndExpression and = MdlFactory.eINSTANCE.createAndExpression();
		LogicalExpression logical = MdlFactory.eINSTANCE.createLogicalExpression();
		AdditiveExpression add =  MdlFactory.eINSTANCE.createAdditiveExpression();
		MultiplicativeExpression mult =  MdlFactory.eINSTANCE.createMultiplicativeExpression();
		PowerExpression power = MdlFactory.eINSTANCE.createPowerExpression();
		UnaryExpression unary = MdlFactory.eINSTANCE.createUnaryExpression();
		FullyQualifiedSymbolName symbol = MdlFactory.eINSTANCE.createFullyQualifiedSymbolName();
		SymbolName symbName = MdlFactory.eINSTANCE.createSymbolName();
		symbName.setName(value);
		symbol.setSymbol(symbName);
		unary.setSymbol(symbol);
		power.getExpression().add(unary);
		mult.getExpression().add(power);
		add.getExpression().add(mult);
		logical.setExpression1(add);
		and.getExpression().add(logical);
		or.getExpression().add(and);
		cond.setExpression(or);
		e.setConditionalExpression(cond);
		expr.setExpression(e);
		return expr;
	}
	
	AnyExpression createStringExpression(String value){
		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();		
		Expression e = MdlFactory.eINSTANCE.createExpression();
		ConditionalExpression cond = MdlFactory.eINSTANCE.createConditionalExpression();
		OrExpression or = MdlFactory.eINSTANCE.createOrExpression();
		AndExpression and = MdlFactory.eINSTANCE.createAndExpression();
		LogicalExpression logical = MdlFactory.eINSTANCE.createLogicalExpression();
		AdditiveExpression add =  MdlFactory.eINSTANCE.createAdditiveExpression();
		add.setString(value);
		logical.setExpression1(add);
		and.getExpression().add(logical);
		or.getExpression().add(and);
		cond.setExpression(or);
		e.setConditionalExpression(cond);
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
		list.setIdentifier(grammarAccess.getListAccess().getIdentifierListKeyword_0_0().getValue());
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
	
	//TODO: check expected type and create expression of the right type
	DistributionArgument createDistributionArgument(Attribute attribute){
		String attrName = attribute.getName();
		String attrValue = attribute.getDefaultValue();
		if (attrValue.length() == 0) attrValue = "0";
		DistributionArgument attr = MdlFactory.eINSTANCE.createDistributionArgument();
		ArgumentName argName = MdlFactory.eINSTANCE.createArgumentName();
		argName.setName(attrName);
		attr.setArgumentName(argName);
		if (attrName.equals(DistributionValidator.attr_type.getName())){
			Distribution distribution = MdlFactory.eINSTANCE.createDistribution();
			distribution.setIdentifier(attrValue);
			attr.setDistribution(distribution);
		}
		else {
			Primary primary = MdlFactory.eINSTANCE.createPrimary();
			try{
				Double.parseDouble(attrValue);
				primary.setNumber(attrValue);
			}
			catch(NumberFormatException e){
				if (attrValue.contains(" ") || attrValue.contains(", ") || attrValue.contains("; ")){
					//a list of values 1 2 3 or 1, 2, 3 or 1; 2; 3
					Vector vector = MdlFactory.eINSTANCE.createVector();
					String[] tokens = attrValue.split("(;|,|\\s)");
					for (int i = 0; i < tokens.length; i++){
						try{
							Primary value = createPrimary(tokens[i]);
							vector.getValues().add(value);
						} catch (Exception e1){
							//skip??
						}
					}
				} else {
					FullyQualifiedSymbolName ref = MdlFactory.eINSTANCE.createFullyQualifiedSymbolName();
					SymbolName symbName = MdlFactory.eINSTANCE.createSymbolName();
					symbName.setName(attrValue);
					ref.setSymbol(symbName);
					primary.setSymbol(ref);
				}
			}
			attr.setValue(primary);
		}
		return attr;
	}
	
	RandomList createRandomList(Attribute[] attributes){
		RandomList list = MdlFactory.eINSTANCE.createRandomList();	
		list.setIdentifier(grammarAccess.getRandomListAccess().getIdentifierTildeKeyword_0_0().getValue());
		DistributionArguments args = MdlFactory.eINSTANCE.createDistributionArguments();				
		for (int i = 0; i < attributes.length; i++){
			DistributionArgument attr = createDistributionArgument(attributes[i]);
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
				FullyQualifiedSymbolName ref = (FullyQualifiedSymbolName)element;
				EObject container = ref.eContainer();
				if (container instanceof SymbolModificationImpl){
					SymbolModification symbolModification = (SymbolModification) container;
					EObject container1 = container.eContainer();
					if (container1 instanceof ParameterBlockImpl){
						ParameterBlock block = (ParameterBlock)container1;
						block.getParameters().remove(symbolModification);
						return;
					}
				}	
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
				if (container instanceof DesignBlockStatementImpl){
					DesignBlockStatement designBlockStatement = (DesignBlockStatement) container;
					EObject container1 = container.eContainer();
					if (container1 instanceof DesignBlockImpl){
						DesignBlock block = (DesignBlock) container1;
						block.getStatements().remove(designBlockStatement);
						return;
					}
				}
				if (container instanceof SymbolListImpl){
					SymbolList symbolList = (SymbolList) container;
					symbolList.getSymbols().remove(ref);
					return;
				}
			}
		});
	}
	
	BlockStatement createBlockStatementSymbol(String varName){
		SymbolDeclaration newSymbol = MdlFactory.eINSTANCE.createSymbolDeclaration();
		SymbolName symbName = MdlFactory.eINSTANCE.createSymbolName();
		symbName.setName(varName);
		newSymbol.setSymbolName(symbName);
		BlockStatement blockSt = MdlFactory.eINSTANCE.createBlockStatement();
		blockSt.setSymbol(newSymbol);
		return blockSt;
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
		Attribute[] attributes = {AttributeValidator.attr_cc_type, AttributeValidator.attr_units, AttributeValidator.attr_use};
		AnyExpression expr = createListExpression(attributes);
		newSymbol.setExpression(expr);
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
		groupSt.setStatement(createBlockStatementSymbol(varName));
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
									st.getMixtureBlock().getStatements().add(createBlockStatementSymbol(issue.getData()[0]));
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
		mix.getStatements().add(createBlockStatementSymbol(varName));
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
							block.getIndividualVariablesBlock().getStatements().add(createBlockStatementSymbol(issue.getData()[0]));
							return;
						}
					}
					IndividualVariablesBlock block = MdlFactory.eINSTANCE.createIndividualVariablesBlock();
					block.setIdentifier(blockName);
					block.getStatements().add(createBlockStatementSymbol(issue.getData()[0]));
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
		groupSt.setStatement(createBlockStatementSymbol(varName));
		block.getStatements().add(groupSt);
	}
	
	//ODE
	//@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	@Fix(MdlJavaValidator.MSG_SYMBOL_UNKNOWN)
	public void addVariableToOde(final Issue issue, IssueResolutionAcceptor acceptor) {
		String blockName = grammarAccess.getOdeBlockAccess().getIdentifierODEKeyword_0_0().getValue();
		acceptor.accept(issue, "Add variable declaration to " + blockName, 
				"Add variable declaration to " + blockName, "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				ModelObject obj = getModelObject(element);
				if (obj != null){
					for (ModelObjectBlock block: obj.getBlocks()){
						if (block.getModelPredictionBlock() != null){
							for (ModelPredictionBlockStatement st: block.getModelPredictionBlock().getStatements()){
								if (st.getOdeBlock() != null){
									st.getOdeBlock().getStatements().add(createBlockStatementSymbol(issue.getData()[0]));
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
		ode.setIdentifier(grammarAccess.getOdeBlockAccess().getIdentifierODEKeyword_0_0().getValue());
		ode.getStatements().add(createBlockStatementSymbol(varName));
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
							block.getObservationBlock().getStatements().add(createBlockStatementSymbol(issue.getData()[0]));
							return;
						}
					}
					ObservationBlock block = MdlFactory.eINSTANCE.createObservationBlock();
					block.setIdentifier(blockName);
					block.getStatements().add(createBlockStatementSymbol(issue.getData()[0]));
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
							block.getSimulationBlock().getStatements().add(createBlockStatementSymbol(issue.getData()[0]));
							return;
						}
					}
					SimulationBlock block = MdlFactory.eINSTANCE.createSimulationBlock();
					block.setIdentifier(blockName);
					block.getStatements().add(createBlockStatementSymbol(issue.getData()[0]));
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
								block.getEstimationBlock().getStatements().add(createBlockStatementSymbol(issue.getData()[0]));
								return;
							}
						}
						EstimationBlock block = MdlFactory.eINSTANCE.createEstimationBlock();
						block.setIdentifier(blockName);
						block.getStatements().add(createBlockStatementSymbol(issue.getData()[0]));
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
								block.getVariabilityBlock().getStatements().add(createVariabilityBlockStatementParameter(issue.getData()[0]));
								return;
							}
						}
						VariabilityBlock block = MdlFactory.eINSTANCE.createVariabilityBlock();
						block.setIdentifier(blockName);
						block.getStatements().add(createVariabilityBlockStatementParameter(issue.getData()[0]));
						ParameterObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createParameterObjectBlock();
						mdlBlock.setVariabilityBlock(block);
						obj.getBlocks().add(mdlBlock); 
					}
				}
			});
		}	
		
	VariabilityBlockStatement createVariabilityBlockStatementParameter(String varName){
		VariabilityBlockStatement st = MdlFactory.eINSTANCE.createVariabilityBlockStatement();
		st.setParameter(createSymbolDeclaration(varName));
		return st;
	}	
			
	SymbolDeclaration createSymbolDeclaration(String varName) {
		SymbolDeclaration newParam = MdlFactory.eINSTANCE.createSymbolDeclaration();
		SymbolName symbName = MdlFactory.eINSTANCE.createSymbolName();
		symbName.setName(varName);
		newParam.setSymbolName(symbName);
		Attribute[] attributes = {AttributeValidator.attr_value};
		newParam.setExpression(createListExpression(attributes));
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
	
	//Insert imported function to IMPORT block
	@Fix(FunctionValidator.MSG_FUNCTION_UNKNOWN)
	public void addImportedFunction(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Declare function in IMPORT block", 
				"Declare function in IMPORT block", "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				Resource resource = element.eResource();
				TreeIterator<EObject> iterator = resource.getAllContents();
			    ImportBlock importBlock = null;
			    boolean isNewBlock = false;
				while (iterator.hasNext()){
			    	EObject obj = iterator.next();
			    	if (obj instanceof ImportBlockImpl){
			    		importBlock = (ImportBlock)obj;
			    	}
			    }
				if (importBlock == null){
					importBlock = MdlFactory.eINSTANCE.createImportBlock();
					importBlock.setIdentifier(grammarAccess.getImportBlockAccess().getIdentifierIMPORTKeyword_0_0().getValue());
					isNewBlock = true;
				}
				ImportedFunction importedFunct = createImportedFunction(element);
	    		importBlock.getFunctions().add(importedFunct);
	    		
	    		if (isNewBlock){
					ModelObject modelObj = getModelObject(element);
					ModelObjectBlock modelObjBlock = MdlFactory.eINSTANCE.createModelObjectBlock();
					modelObjBlock.setImportBlock(importBlock);
					modelObj.getBlocks().add(modelObjBlock);
	    		}
			}
		});
	}
	
	private ImportedFunction createImportedFunction(EObject element){
		ImportedFunction importedFunct = MdlFactory.eINSTANCE.createImportedFunction();
		FunctionCall funcCall = (FunctionCall)element;
		String funcName = funcCall.getIdentifier().getSymbol().getName(); 
		SymbolName symbName = MdlFactory.eINSTANCE.createSymbolName();
		symbName.setName(funcName);
		importedFunct.setFunctionName(symbName);

		//Create a list of parameters
		Iterator<Argument> argIterator = funcCall.getArguments().getArguments().iterator();
		org.ddmore.mdl.mdl.List paramList = MdlFactory.eINSTANCE.createList();	
		paramList.setIdentifier(grammarAccess.getListAccess().getIdentifierListKeyword_0_0().getValue());
		Arguments paramListArgs = MdlFactory.eINSTANCE.createArguments();		
		AnyExpression paramExpr = MdlFactory.eINSTANCE.createAnyExpression();		
	    int k = 1;
		while(argIterator.hasNext()){
	    	Argument x = argIterator.next(); 	
	    	String attrName = (x.getArgumentName() != null)? x.getArgumentName().getName(): "unnamedParam" + k++;
	    	Attribute attribute = new Attribute(attrName, MdlDataType.TYPE_STRING, false, "");
	    	Argument attr = createArgument(attribute);
    		paramListArgs.getArguments().add(attr);
	    }
		paramList.setArguments(paramListArgs);
		paramExpr.setList(paramList);
	
		Attribute[] attrs = {AttributeValidator.attr_req_target, AttributeValidator.attr_name, AttributeValidator.attr_param};
		AnyExpression[] attrValues = {createTargetLanguageExpression("NMTRAN_CODE"), createStringExpression(funcName), paramExpr};

		org.ddmore.mdl.mdl.List list = MdlFactory.eINSTANCE.createList();		
		list.setIdentifier(grammarAccess.getListAccess().getIdentifierListKeyword_0_0().getValue());

		Arguments args = MdlFactory.eINSTANCE.createArguments();				
		for (int i = 0; i < attrs.length; i++){
			Argument attr = createArgumentWithExpression(attrs[i], attrValues[i]);
			args.getArguments().add(attr);
		}
		list.setArguments(args);
		importedFunct.setList(list);	
		return importedFunct;
	}
}
