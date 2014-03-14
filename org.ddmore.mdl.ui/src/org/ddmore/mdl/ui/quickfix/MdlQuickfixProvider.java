
package org.ddmore.mdl.ui.quickfix;

import java.awt.image.renderable.ParameterBlock;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.ddmore.mdl.mdl.AdditiveExpression;
import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.ArgumentName;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.Categorical;
import org.ddmore.mdl.mdl.ConditionalExpression;
import org.ddmore.mdl.mdl.Continuous;
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
import org.ddmore.mdl.mdl.HeaderBlock;
import org.ddmore.mdl.mdl.ImportBlock;
import org.ddmore.mdl.mdl.ImportedFunction;
import org.ddmore.mdl.mdl.IndividualVariablesBlock;
import org.ddmore.mdl.mdl.InlineBlock;
import org.ddmore.mdl.mdl.InputVariablesBlock;
import org.ddmore.mdl.mdl.Likelyhood;
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
import org.ddmore.mdl.mdl.ParameterDeclaration;
import org.ddmore.mdl.mdl.ParameterObject;
import org.ddmore.mdl.mdl.ParameterObjectBlock;
import org.ddmore.mdl.mdl.PowerExpression;
import org.ddmore.mdl.mdl.Primary;
import org.ddmore.mdl.mdl.RandomList;
import org.ddmore.mdl.mdl.RandomVariable;
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
import org.ddmore.mdl.mdl.VariabilityBlock;
import org.ddmore.mdl.mdl.VariabilityBlockStatement;
import org.ddmore.mdl.mdl.VariabilityParametersBlock;
import org.ddmore.mdl.mdl.VariableList;
import org.ddmore.mdl.mdl.Vector;
import org.ddmore.mdl.mdl.impl.DesignBlockImpl;
import org.ddmore.mdl.mdl.impl.DesignBlockStatementImpl;
import org.ddmore.mdl.mdl.impl.HeaderBlockImpl;
import org.ddmore.mdl.mdl.impl.ImportBlockImpl;
import org.ddmore.mdl.mdl.impl.InlineBlockImpl;
import org.ddmore.mdl.mdl.impl.MclImpl;
import org.ddmore.mdl.mdl.impl.OutputVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.ParameterBlockImpl;
import org.ddmore.mdl.mdl.impl.StructuralParametersBlockImpl;
import org.ddmore.mdl.mdl.impl.SymbolListImpl;
import org.ddmore.mdl.mdl.impl.SymbolModificationImpl;
import org.ddmore.mdl.mdl.impl.VariabilityParametersBlockImpl;
import org.ddmore.mdl.mdl.impl.VariableListImpl;
import org.ddmore.mdl.validation.ListAttributeValidator;
import org.ddmore.mdl.validation.MdlJavaValidator;
import org.ddmore.mdl.validation.FunctionCallValidator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;


public class MdlQuickfixProvider extends DefaultQuickfixProvider {

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Fix attributes
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Fix(ListAttributeValidator.MSG_ATTRIBUTE_UNKNOWN)
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
	
	@Fix(ListAttributeValidator.MSG_ATTRIBUTE_MISSING)
	public void addAttribute(final Issue issue, IssueResolutionAcceptor acceptor) {
		final String attrName = issue.getData()[0];
		String description = "Insert attribute '" + attrName +"'";
		acceptor.accept(issue, description, description, "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				String value  = defaultAttributeValues.get(attrName);
				if (value == null) value = "";
				Argument newArg = createArgument(attrName, value);
				Arguments args = (Arguments)element;
				args.getArguments().add(0, newArg);
			} 
		});
	}
	
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Automatically create EObjects - default values and type selection
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
	private static final HashMap<String, String> defaultAttributeValues = new HashMap<String, String>(){
		private static final long serialVersionUID = -1395941477845816149L;
		{
            put("value", "0");
            put("mean", "0");
            put("init", "0");
            put("model", "1");
            put("fix", "false");
            put("variance", "VarName");
            put("deriv", "VarName");
            put("type", "continuous");
            put("inputformat", "NONMEM");
            put("target", "NMTRAN_CODE");
            put("wrt", "TIME");
            put("source", "[fileName]");
		}
    };
    
    final static List<String> boolean_values = Arrays.asList("true", "false");
    final static List<String> target_language_values = Arrays.asList("NMTRAN_CODE", "MLXTRAN_CODE", "PML_CODE", "BUGS_CODE", "R_CODE", "MATLAB_CODE");
    final static List<String> continuous_values = Arrays.asList("continuous");
    final static List<String> var_values = Arrays.asList("VarName");
    
    final static List<String> categorical_values = Arrays.asList("categorical");
    final static List<String> covariate_values = Arrays.asList("covariate");
    final static List<String> distribution_values = Arrays.asList("Normal", "Binomial", "Poisson", "Student_T", "MVNormal");
    final static List<String> use_values = Arrays.asList("mdv", "id", "dv", "idv", "dvid", "amt");
    final static List<String> likelihood_values = Arrays.asList("likelihood");
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Automatically create EObjects
    //////////////////////////////////////////////////////////////////////////////////////////////////////////    
	AnyExpression createAttributeExpression(String attrName, String value){
		try{
			Float.parseFloat(value);
			return createNumericExpression(value);
		} catch(Exception e){}
        
		if (var_values.contains(value))
			return createReferenceExpression(value);
		if (boolean_values.contains(value))
			return createBooleanExpression(value);
		if (target_language_values.contains(value))
			return createTargetLanguageExpression(value);
		if (continuous_values.contains(value))
			return createContinuousExpression(value);
		if (categorical_values.contains(value))
			return createCategoricalExpression(value);
		if (use_values.contains(value))
			return createUseExpression(value);
		if (likelihood_values.contains(value))
			return createLikelyhoodExpression(value);
		return createStringExpression(value);
	}
	
	private AnyExpression createCategoricalExpression(String value) {
		Categorical tl = MdlFactory.eINSTANCE.createCategorical();
		tl.setIdentifier(value);
		EnumType t = MdlFactory.eINSTANCE.createEnumType();
		t.setCategorical(tl);
		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();		
		expr.setType(t);
		return expr;
	}
	
	private AnyExpression createUseExpression(String value) {
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
	
	AnyExpression createLikelyhoodExpression(String value){
		Likelyhood tl = MdlFactory.eINSTANCE.createLikelyhood();
		tl.setIdentifier(value);
		EnumType t = MdlFactory.eINSTANCE.createEnumType();
		t.setLikelyhood(tl);
		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();		
		expr.setType(t);
		return expr;
	}
	
	AnyExpression createContinuousExpression(String value){
		Continuous cnt = MdlFactory.eINSTANCE.createContinuous();
		cnt.setIdentifier(value);
		EnumType t = MdlFactory.eINSTANCE.createEnumType();
		t.setContinuous(cnt);
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
	
	AnyExpression createListExpression(String[] attrNames, String[] attrValues){
		AnyExpression expr = MdlFactory.eINSTANCE.createAnyExpression();	
		org.ddmore.mdl.mdl.List list = createList(attrNames, attrValues);
		expr.setList(list);
		return expr;
	}
	
	org.ddmore.mdl.mdl.List createList(String[] attrNames, String[] attrValues){
		org.ddmore.mdl.mdl.List list = MdlFactory.eINSTANCE.createList();		
		list.setIdentifier("list");
		Arguments args = MdlFactory.eINSTANCE.createArguments();				
		for (int i = 0; i< attrNames.length; i++){
			Argument attr = createArgument(attrNames[i], attrValues[i]);
			args.getArguments().add(attr);
		}
		list.setArguments(args);
		return list;
	}
	
	Argument createArgument(String attrName, String attrValue){
		Argument attr = MdlFactory.eINSTANCE.createArgument();
		ArgumentName argName = MdlFactory.eINSTANCE.createArgumentName();
		argName.setName(attrName);
		AnyExpression attrExpr = createAttributeExpression(attrName, attrValue);

		attr.setArgumentName(argName);
		attr.setExpression(attrExpr);
		return attr;
	}
	
	Argument createArgumentWithExpression(String attrName, AnyExpression attrExpr){
		Argument attr = MdlFactory.eINSTANCE.createArgument();
		ArgumentName argName = MdlFactory.eINSTANCE.createArgumentName();
		argName.setName(attrName);
		attr.setArgumentName(argName);
		attr.setExpression(attrExpr);
		return attr;
	}
	
	//TODO: test!
	DistributionArgument createDistributionArgument(String attrName, String attrValue){
		DistributionArgument attr = MdlFactory.eINSTANCE.createDistributionArgument();
		ArgumentName argName = MdlFactory.eINSTANCE.createArgumentName();
		argName.setName(attrName);
		attr.setArgumentName(argName);
		if (attrName.equals("type")){
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
					if (attrValue.contains(".")){//attribute
						//TODO finish on demand
					} else {//variable
						FullyQualifiedSymbolName ref = MdlFactory.eINSTANCE.createFullyQualifiedSymbolName();
						SymbolName symbName = MdlFactory.eINSTANCE.createSymbolName();
						symbName.setName(attrValue);
						ref.setSymbol(symbName);
						primary.setSymbol(ref);
					}
				}
			}
			attr.setValue(primary);
		}
		return attr;
	}
	
	RandomList createRandomList(String[] attrNames, String[] attrValues){
		RandomList list = MdlFactory.eINSTANCE.createRandomList();	
		list.setIdentifier("~");
		DistributionArguments args = MdlFactory.eINSTANCE.createDistributionArguments();				
		for (int i = 0; i< attrNames.length; i++){
			DistributionArgument attr = createDistributionArgument(attrNames[i], attrValues[i]);
			args.getArguments().add(attr);
		}
		list.setArguments(args);
		return list;
	}
		
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Fix variable references
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	public void removeVariable(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Remove variable", "Remove variable", "remove.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				FullyQualifiedSymbolName ref = (FullyQualifiedSymbolName)element;
				EObject container = ref.eContainer();
				if (container instanceof SymbolModificationImpl){
					SymbolModification symbolModification = (SymbolModification) container;
					EObject container1 = container.eContainer();
					if (container1 instanceof HeaderBlockImpl){
						HeaderBlock block = (HeaderBlock) container1;
						block.getVariables().remove(symbolModification);
						return;
					}
				}					
				if (container instanceof OutputVariablesBlockImpl){
					OutputVariablesBlock outputBlock = (OutputVariablesBlock) container;
					outputBlock.getVariables().remove(ref);
					return;
				}
				if (container instanceof InlineBlockImpl){
					InlineBlock inlineBlock = (InlineBlock) container;
					inlineBlock.getVariables().remove(ref);
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
	
	//INPUT_VARIABLES
	@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	public void addVariableToInputVariables(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Add variable declaration to INPUT_VARIABLES", 
				"Add variable declaration to INPUT_VARIABLES", "add.png", new ISemanticModification() {
			public void apply(EObject element, IModificationContext context) {
				ModelObject obj = getModelObject(element);
				if (obj != null){
					for (ModelObjectBlock block: obj.getBlocks()){
						if (block.getInputVariablesBlock() != null){
							insertSymbolDeclaration(block.getInputVariablesBlock(), issue.getData()[0]);
							return;
						}
					}
					InputVariablesBlock block = MdlFactory.eINSTANCE.createInputVariablesBlock();
					insertSymbolDeclaration(block, issue.getData()[0]);
					ModelObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createModelObjectBlock();
					mdlBlock.setInputVariablesBlock(block);
					obj.getBlocks().add(mdlBlock); 
				}
			}
		});
	}
	
	void insertSymbolDeclaration(InputVariablesBlock block, String varName){
		SymbolDeclaration newSymbol = MdlFactory.eINSTANCE.createSymbolDeclaration();
		SymbolName symbName = MdlFactory.eINSTANCE.createSymbolName();
		symbName.setName(varName);
		newSymbol.setSymbolName(symbName);
		String[] attrNames = {"type", "units", "use"};
		String[] attrValues = {"continuous", "kg", "covariate"};
		AnyExpression expr = createListExpression(attrNames, attrValues);
		newSymbol.setExpression(expr);
		block.getVariables().add(newSymbol);
	}
	
	//RANDOM_VARIABLE_DEFINITION
	@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	public void addVariableToRandomVariables(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Add variable declaration to RANDOM_VARIABLE_DEFINITION", 
				"Add variable declaration to RANDOM_VARIABLE_DEFINITION", "add.png", new ISemanticModification() {
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
					insertRandomVariable(block, issue.getData()[0]);
					ModelObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createModelObjectBlock();
					mdlBlock.setRandomVariableDefinitionBlock(block);
					obj.getBlocks().add(mdlBlock); 
				}
			}
		});
	}
	
	void insertRandomVariable(RandomVariableDefinitionBlock block, String varName){
		RandomVariable newSymbol = MdlFactory.eINSTANCE.createRandomVariable();
		SymbolName symbName = MdlFactory.eINSTANCE.createSymbolName();
		symbName.setName(varName);
		newSymbol.setSymbolName(symbName);
		//~ (type=Normal, mean=0, variance=PPV_STATUS,level=ID)
		String[] attrNames = {"type", "mean", "variance", "level"};
		String[] attrValues = {"Normal", "0", "VarName", "ID"};
		RandomList list = createRandomList(attrNames, attrValues);
		newSymbol.setRandomList(list);
		block.getVariables().add(newSymbol);
	}
	
	//GROUP_VARIABLES
	@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	public void addVariableToGroupVariables(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Add variable declaration to GROUP_VARIABLES", 
				"Add variable declaration to GROUP_VARIABLES", "add.png", new ISemanticModification() {
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
	@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	public void addVariableToMixture(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Add variable declaration to MIXTURE", 
				"Add variable declaration to MIXTURE", "add.png", new ISemanticModification() {
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
		mix.setIdentifier("MIXTURE");
		mix.getStatements().add(createBlockStatementSymbol(varName));
		GroupVariablesBlockStatement groupSt =  MdlFactory.eINSTANCE.createGroupVariablesBlockStatement();
		groupSt.setMixtureBlock(mix);
		return groupSt;
	}
	
	//INDIVIDUAL_VARIABLES
	@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	public void addVariableToIndividualVariables(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Add variable declaration to INDIVIDUAL_VARIABLES", 
				"Add variable declaration to INDIVIDUAL_VARIABLES", "add.png", new ISemanticModification() {
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
					block.getStatements().add(createBlockStatementSymbol(issue.getData()[0]));
					ModelObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createModelObjectBlock();
					mdlBlock.setIndividualVariablesBlock(block);
					obj.getBlocks().add(mdlBlock); 
				}
			}
		});
	}	
	
	//MODEL_PREDICTION
	@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	public void addVariableToModelPrediction(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Add variable declaration to MODEL_PREDICTION", 
				"Add variable declaration to MODEL_PREDICTION", "add.png", new ISemanticModification() {
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
	@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	public void addVariableToOde(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Add variable declaration to ODE", 
				"Add variable declaration to ODE", "add.png", new ISemanticModification() {
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
		ode.setIdentifier("ODE");
		ode.getStatements().add(createBlockStatementSymbol(varName));
		ModelPredictionBlockStatement mpSt =  MdlFactory.eINSTANCE.createModelPredictionBlockStatement();
		mpSt.setOdeBlock(ode);
		return mpSt;
	}	
	
	//OBSERVATION
	@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	public void addVariableToObservation(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Add variable declaration to OBSERVATION", 
				"Add variable declaration to OBSERVATION", "add.png", new ISemanticModification() {
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
					block.getStatements().add(createBlockStatementSymbol(issue.getData()[0]));
					ModelObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createModelObjectBlock();
					mdlBlock.setObservationBlock(block);
					obj.getBlocks().add(mdlBlock); 				
				}
			}
		});
	}	

	//SIMULATION
	@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
	public void addVariableToSimulation(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Add variable declaration to SIMULATION", 
				"Add variable declaration to SIMULATION", "add.png", new ISemanticModification() {
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
					block.getStatements().add(createBlockStatementSymbol(issue.getData()[0]));
					ModelObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createModelObjectBlock();
					mdlBlock.setSimulationBlock(block);
					obj.getBlocks().add(mdlBlock); 
				}
			}
		});
	}	

	//ESTIMATION
		@Fix(MdlJavaValidator.MSG_VARIABLE_UNKNOWN)
		public void addVariableToEstimation(final Issue issue, IssueResolutionAcceptor acceptor) {
			acceptor.accept(issue, "Add variable declaration to ESTIMATION", 
					"Add variable declaration to ESTIMATION", "add.png", new ISemanticModification() {
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

	@Fix(MdlJavaValidator.MSG_PARAMETER_UNKNOWN)
	public void removeParameter(final Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, "Remove parameter", "Remove parameter", "remove.png", new ISemanticModification() {
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
			}
		});
	}
		//STRUCTURAL
		@Fix(MdlJavaValidator.MSG_PARAMETER_UNKNOWN)
		public void addVariableToStructural(final Issue issue, IssueResolutionAcceptor acceptor) {
			acceptor.accept(issue, "Add parameter declaration to STRUCTURAL", 
					"Add variable declaration to STRUCTURAL", "add.png", new ISemanticModification() {
				public void apply(EObject element, IModificationContext context) {
					ParameterObject obj = getParameterObject(element);
					if (obj != null){
						for (ParameterObjectBlock block: obj.getBlocks()){
							if (block.getStructuralBlock() != null){
								block.getStructuralBlock().getParameters().add(createParameterDeclaration(issue.getData()[0]));
								return;
							}
						}
						StructuralBlock block = MdlFactory.eINSTANCE.createStructuralBlock();
						block.getParameters().add(createParameterDeclaration(issue.getData()[0]));
						ParameterObjectBlock mdlBlock =  MdlFactory.eINSTANCE.createParameterObjectBlock();
						mdlBlock.setStructuralBlock(block);
						obj.getBlocks().add(mdlBlock); 
					}
				}
			});
		}	
		
		//VARIABILITY
		@Fix(MdlJavaValidator.MSG_PARAMETER_UNKNOWN)
		public void addVariableToVariability(final Issue issue, IssueResolutionAcceptor acceptor) {
			acceptor.accept(issue, "Add parameter declaration to VARIABILITY", 
					"Add variable declaration to VARIABILITY", "add.png", new ISemanticModification() {
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
		st.setParameter(createParameterDeclaration(varName));
		return st;
	}	
			
	ParameterDeclaration createParameterDeclaration(String varName) {
		ParameterDeclaration newParam = MdlFactory.eINSTANCE
				.createParameterDeclaration();
		SymbolName symbName = MdlFactory.eINSTANCE.createSymbolName();
		symbName.setName(varName);
		newParam.setSymbolName(symbName);
		String[] attrNames = { "value" };
		String[] attrValues = { "0" };
		newParam.setList(createList(attrNames, attrValues));
		return newParam;
	}
	
	//PRIOR_PARAMETERS
	//block, diag, same
	
	//Insert imported function to IMPORT block
	@Fix(FunctionCallValidator.MSG_FUNCTION_UNKNOWN)
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
		paramList.setIdentifier("list");
		Arguments paramListArgs = MdlFactory.eINSTANCE.createArguments();		
		AnyExpression paramExpr = MdlFactory.eINSTANCE.createAnyExpression();		
	    int k = 1;
		while(argIterator.hasNext()){
	    	Argument x = argIterator.next(); 	
	    	Argument attr = null;
	    	if (x.getArgumentName() != null){
	    		attr = createArgument(x.getArgumentName().getName(), "");
	    	} else {
	    		attr = createArgument("unnamedParam" + k++, "");
	    	}
    		paramListArgs.getArguments().add(attr);
	    }
		paramList.setArguments(paramListArgs);
		paramExpr.setList(paramList);
	
		String[] attrNames = {"target", "name", "param"};
		AnyExpression[] attrValues = {createTargetLanguageExpression("NMTRAN_CODE"), 
				createStringExpression(funcName), paramExpr};

		org.ddmore.mdl.mdl.List list = MdlFactory.eINSTANCE.createList();		
		list.setIdentifier("list");

		Arguments args = MdlFactory.eINSTANCE.createArguments();				
		for (int i = 0; i < attrNames.length; i++){
			Argument attr = createArgumentWithExpression(attrNames[i], attrValues[i]);
			args.getArguments().add(attr);
		}
		list.setArguments(args);
		importedFunct.setList(list);	
		return importedFunct;
	}
}
