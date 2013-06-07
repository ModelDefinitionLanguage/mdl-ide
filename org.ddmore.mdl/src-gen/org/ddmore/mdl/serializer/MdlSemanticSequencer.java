package org.ddmore.mdl.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.ddmore.mdl.mdl.AcceptList;
import org.ddmore.mdl.mdl.AddList;
import org.ddmore.mdl.mdl.AdditiveExpression;
import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.Block;
import org.ddmore.mdl.mdl.BlockBlock;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.Categorical;
import org.ddmore.mdl.mdl.ConditionalExpression;
import org.ddmore.mdl.mdl.ConditionalStatement;
import org.ddmore.mdl.mdl.Continuous;
import org.ddmore.mdl.mdl.Covariate;
import org.ddmore.mdl.mdl.DataBlock;
import org.ddmore.mdl.mdl.DataBlockStatement;
import org.ddmore.mdl.mdl.DataObject;
import org.ddmore.mdl.mdl.DataObjectBlock;
import org.ddmore.mdl.mdl.DesignBlock;
import org.ddmore.mdl.mdl.DesignBlockStatement;
import org.ddmore.mdl.mdl.DiagBlock;
import org.ddmore.mdl.mdl.Distribution;
import org.ddmore.mdl.mdl.DropList;
import org.ddmore.mdl.mdl.EnumType;
import org.ddmore.mdl.mdl.EstimateTask;
import org.ddmore.mdl.mdl.EstimationBlock;
import org.ddmore.mdl.mdl.ExecuteTask;
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.FileBlock;
import org.ddmore.mdl.mdl.FileBlockStatement;
import org.ddmore.mdl.mdl.FormalArgument;
import org.ddmore.mdl.mdl.FormalArguments;
import org.ddmore.mdl.mdl.FullyQualifiedArgumentName;
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.FunctionCallStatement;
import org.ddmore.mdl.mdl.GroupVariablesBlock;
import org.ddmore.mdl.mdl.GroupVariablesBlockStatement;
import org.ddmore.mdl.mdl.HeaderBlock;
import org.ddmore.mdl.mdl.IgnoreList;
import org.ddmore.mdl.mdl.ImportBlock;
import org.ddmore.mdl.mdl.ImportedFunction;
import org.ddmore.mdl.mdl.IndividualVariablesBlock;
import org.ddmore.mdl.mdl.InlineBlock;
import org.ddmore.mdl.mdl.InputVariablesBlock;
import org.ddmore.mdl.mdl.LevelType;
import org.ddmore.mdl.mdl.LibraryBlock;
import org.ddmore.mdl.mdl.Likelyhood;
import org.ddmore.mdl.mdl.List;
import org.ddmore.mdl.mdl.LogicalExpression;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.Missing;
import org.ddmore.mdl.mdl.MixtureBlock;
import org.ddmore.mdl.mdl.ModelBlock;
import org.ddmore.mdl.mdl.ModelBlockStatement;
import org.ddmore.mdl.mdl.ModelObject;
import org.ddmore.mdl.mdl.ModelObjectBlock;
import org.ddmore.mdl.mdl.ModelPredictionBlock;
import org.ddmore.mdl.mdl.ModelPredictionBlockStatement;
import org.ddmore.mdl.mdl.MultiplicativeExpression;
import org.ddmore.mdl.mdl.ObjectName;
import org.ddmore.mdl.mdl.ObservationBlock;
import org.ddmore.mdl.mdl.OdeBlock;
import org.ddmore.mdl.mdl.OdeList;
import org.ddmore.mdl.mdl.OrExpression;
import org.ddmore.mdl.mdl.OutputVariablesBlock;
import org.ddmore.mdl.mdl.ParExpression;
import org.ddmore.mdl.mdl.ParameterBlock;
import org.ddmore.mdl.mdl.ParameterDeclaration;
import org.ddmore.mdl.mdl.ParameterObject;
import org.ddmore.mdl.mdl.ParameterObjectBlock;
import org.ddmore.mdl.mdl.PowerExpression;
import org.ddmore.mdl.mdl.Primary;
import org.ddmore.mdl.mdl.PriorParametersBlock;
import org.ddmore.mdl.mdl.RScriptBlock;
import org.ddmore.mdl.mdl.RScriptBlockStatement;
import org.ddmore.mdl.mdl.RandomList;
import org.ddmore.mdl.mdl.RandomVariableDefinitionBlock;
import org.ddmore.mdl.mdl.RemoveList;
import org.ddmore.mdl.mdl.SameBlock;
import org.ddmore.mdl.mdl.Selector;
import org.ddmore.mdl.mdl.SimulateTask;
import org.ddmore.mdl.mdl.SimulationBlock;
import org.ddmore.mdl.mdl.StructuralBlock;
import org.ddmore.mdl.mdl.StructuralParametersBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.SymbolList;
import org.ddmore.mdl.mdl.SymbolModification;
import org.ddmore.mdl.mdl.TELObject;
import org.ddmore.mdl.mdl.TargetBlock;
import org.ddmore.mdl.mdl.TargetLanguage;
import org.ddmore.mdl.mdl.TaskFunctionBlock;
import org.ddmore.mdl.mdl.TaskFunctionBody;
import org.ddmore.mdl.mdl.TaskFunctionDeclaration;
import org.ddmore.mdl.mdl.TaskFunctionStatement;
import org.ddmore.mdl.mdl.TaskObject;
import org.ddmore.mdl.mdl.TaskObjectBlock;
import org.ddmore.mdl.mdl.UnaryExpression;
import org.ddmore.mdl.mdl.VariabilityBlock;
import org.ddmore.mdl.mdl.VariabilityBlockStatement;
import org.ddmore.mdl.mdl.VariabilityParametersBlock;
import org.ddmore.mdl.mdl.VariableList;
import org.ddmore.mdl.mdl.Vector;
import org.ddmore.mdl.services.MdlGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class MdlSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private MdlGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == MdlPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case MdlPackage.ACCEPT_LIST:
				if(context == grammarAccess.getAcceptListRule()) {
					sequence_AcceptList(context, (AcceptList) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.ADD_LIST:
				if(context == grammarAccess.getAddListRule()) {
					sequence_AddList(context, (AddList) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.ADDITIVE_EXPRESSION:
				if(context == grammarAccess.getAdditiveExpressionRule()) {
					sequence_AdditiveExpression(context, (AdditiveExpression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.AND_EXPRESSION:
				if(context == grammarAccess.getAndExpressionRule()) {
					sequence_AndExpression(context, (AndExpression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.ANY_EXPRESSION:
				if(context == grammarAccess.getAnyExpressionRule()) {
					sequence_AnyExpression(context, (AnyExpression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.ARGUMENT:
				if(context == grammarAccess.getArgumentRule()) {
					sequence_Argument(context, (Argument) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.ARGUMENTS:
				if(context == grammarAccess.getArgumentsRule()) {
					sequence_Arguments(context, (Arguments) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.BLOCK:
				if(context == grammarAccess.getBlockRule()) {
					sequence_Block(context, (Block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.BLOCK_BLOCK:
				if(context == grammarAccess.getBlockBlockRule()) {
					sequence_BlockBlock(context, (BlockBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.BLOCK_STATEMENT:
				if(context == grammarAccess.getBlockStatementRule()) {
					sequence_BlockStatement(context, (BlockStatement) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.CATEGORICAL:
				if(context == grammarAccess.getCategoricalRule()) {
					sequence_Categorical(context, (Categorical) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.CONDITIONAL_EXPRESSION:
				if(context == grammarAccess.getConditionalExpressionRule()) {
					sequence_ConditionalExpression(context, (ConditionalExpression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.CONDITIONAL_STATEMENT:
				if(context == grammarAccess.getConditionalStatementRule()) {
					sequence_ConditionalStatement(context, (ConditionalStatement) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.CONTINUOUS:
				if(context == grammarAccess.getContinuousRule()) {
					sequence_Continuous(context, (Continuous) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.COVARIATE:
				if(context == grammarAccess.getCovariateRule()) {
					sequence_Covariate(context, (Covariate) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.DATA_BLOCK:
				if(context == grammarAccess.getDataBlockRule()) {
					sequence_DataBlock(context, (DataBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.DATA_BLOCK_STATEMENT:
				if(context == grammarAccess.getDataBlockStatementRule()) {
					sequence_DataBlockStatement(context, (DataBlockStatement) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.DATA_OBJECT:
				if(context == grammarAccess.getDataObjectRule()) {
					sequence_DataObject(context, (DataObject) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.DATA_OBJECT_BLOCK:
				if(context == grammarAccess.getDataObjectBlockRule()) {
					sequence_DataObjectBlock(context, (DataObjectBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.DESIGN_BLOCK:
				if(context == grammarAccess.getDesignBlockRule()) {
					sequence_DesignBlock(context, (DesignBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.DESIGN_BLOCK_STATEMENT:
				if(context == grammarAccess.getDesignBlockStatementRule()) {
					sequence_DesignBlockStatement(context, (DesignBlockStatement) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.DIAG_BLOCK:
				if(context == grammarAccess.getDiagBlockRule()) {
					sequence_DiagBlock(context, (DiagBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.DISTRIBUTION:
				if(context == grammarAccess.getDistributionRule()) {
					sequence_Distribution(context, (Distribution) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.DROP_LIST:
				if(context == grammarAccess.getDropListRule()) {
					sequence_DropList(context, (DropList) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.ENUM_TYPE:
				if(context == grammarAccess.getEnumTypeRule()) {
					sequence_EnumType(context, (EnumType) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.ESTIMATE_TASK:
				if(context == grammarAccess.getEstimateTaskRule()) {
					sequence_EstimateTask(context, (EstimateTask) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.ESTIMATION_BLOCK:
				if(context == grammarAccess.getEstimationBlockRule()) {
					sequence_EstimationBlock(context, (EstimationBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.EXECUTE_TASK:
				if(context == grammarAccess.getExecuteTaskRule()) {
					sequence_ExecuteTask(context, (ExecuteTask) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.EXPRESSION:
				if(context == grammarAccess.getExpressionRule()) {
					sequence_Expression(context, (Expression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.FILE_BLOCK:
				if(context == grammarAccess.getFileBlockRule()) {
					sequence_FileBlock(context, (FileBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.FILE_BLOCK_STATEMENT:
				if(context == grammarAccess.getFileBlockStatementRule()) {
					sequence_FileBlockStatement(context, (FileBlockStatement) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.FORMAL_ARGUMENT:
				if(context == grammarAccess.getFormalArgumentRule()) {
					sequence_FormalArgument(context, (FormalArgument) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.FORMAL_ARGUMENTS:
				if(context == grammarAccess.getFormalArgumentsRule()) {
					sequence_FormalArguments(context, (FormalArguments) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME:
				if(context == grammarAccess.getFullyQualifiedArgumentNameRule()) {
					sequence_FullyQualifiedArgumentName(context, (FullyQualifiedArgumentName) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.FULLY_QUALIFIED_SYMBOL_NAME:
				if(context == grammarAccess.getFullyQualifiedSymbolNameRule()) {
					sequence_FullyQualifiedSymbolName(context, (FullyQualifiedSymbolName) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.FUNCTION_CALL:
				if(context == grammarAccess.getFunctionCallRule()) {
					sequence_FunctionCall(context, (FunctionCall) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.FUNCTION_CALL_STATEMENT:
				if(context == grammarAccess.getFunctionCallStatementRule()) {
					sequence_FunctionCallStatement(context, (FunctionCallStatement) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.GROUP_VARIABLES_BLOCK:
				if(context == grammarAccess.getGroupVariablesBlockRule()) {
					sequence_GroupVariablesBlock(context, (GroupVariablesBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT:
				if(context == grammarAccess.getGroupVariablesBlockStatementRule()) {
					sequence_GroupVariablesBlockStatement(context, (GroupVariablesBlockStatement) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.HEADER_BLOCK:
				if(context == grammarAccess.getHeaderBlockRule()) {
					sequence_HeaderBlock(context, (HeaderBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.IGNORE_LIST:
				if(context == grammarAccess.getIgnoreListRule()) {
					sequence_IgnoreList(context, (IgnoreList) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.IMPORT_BLOCK:
				if(context == grammarAccess.getImportBlockRule()) {
					sequence_ImportBlock(context, (ImportBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.IMPORTED_FUNCTION:
				if(context == grammarAccess.getImportedFunctionRule()) {
					sequence_ImportedFunction(context, (ImportedFunction) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.INDIVIDUAL_VARIABLES_BLOCK:
				if(context == grammarAccess.getIndividualVariablesBlockRule()) {
					sequence_IndividualVariablesBlock(context, (IndividualVariablesBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.INLINE_BLOCK:
				if(context == grammarAccess.getInlineBlockRule()) {
					sequence_InlineBlock(context, (InlineBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.INPUT_VARIABLES_BLOCK:
				if(context == grammarAccess.getInputVariablesBlockRule()) {
					sequence_InputVariablesBlock(context, (InputVariablesBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.LEVEL_TYPE:
				if(context == grammarAccess.getLevelTypeRule()) {
					sequence_LevelType(context, (LevelType) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.LIBRARY_BLOCK:
				if(context == grammarAccess.getLibraryBlockRule()) {
					sequence_LibraryBlock(context, (LibraryBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.LIKELYHOOD:
				if(context == grammarAccess.getLikelyhoodRule()) {
					sequence_Likelyhood(context, (Likelyhood) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.LIST:
				if(context == grammarAccess.getListRule()) {
					sequence_List(context, (List) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.LOGICAL_EXPRESSION:
				if(context == grammarAccess.getLogicalExpressionRule()) {
					sequence_LogicalExpression(context, (LogicalExpression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MCL:
				if(context == grammarAccess.getMclRule()) {
					sequence_Mcl(context, (Mcl) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MCL_OBJECT:
				if(context == grammarAccess.getMclObjectRule()) {
					sequence_MclObject(context, (MclObject) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MISSING:
				if(context == grammarAccess.getMissingRule()) {
					sequence_Missing(context, (Missing) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MIXTURE_BLOCK:
				if(context == grammarAccess.getMixtureBlockRule()) {
					sequence_MixtureBlock(context, (MixtureBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MODEL_BLOCK:
				if(context == grammarAccess.getModelBlockRule()) {
					sequence_ModelBlock(context, (ModelBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MODEL_BLOCK_STATEMENT:
				if(context == grammarAccess.getModelBlockStatementRule()) {
					sequence_ModelBlockStatement(context, (ModelBlockStatement) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MODEL_OBJECT:
				if(context == grammarAccess.getModelObjectRule()) {
					sequence_ModelObject(context, (ModelObject) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MODEL_OBJECT_BLOCK:
				if(context == grammarAccess.getModelObjectBlockRule()) {
					sequence_ModelObjectBlock(context, (ModelObjectBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MODEL_PREDICTION_BLOCK:
				if(context == grammarAccess.getModelPredictionBlockRule()) {
					sequence_ModelPredictionBlock(context, (ModelPredictionBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT:
				if(context == grammarAccess.getModelPredictionBlockStatementRule()) {
					sequence_ModelPredictionBlockStatement(context, (ModelPredictionBlockStatement) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MULTIPLICATIVE_EXPRESSION:
				if(context == grammarAccess.getMultiplicativeExpressionRule()) {
					sequence_MultiplicativeExpression(context, (MultiplicativeExpression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.OBJECT_NAME:
				if(context == grammarAccess.getObjectNameRule()) {
					sequence_ObjectName(context, (ObjectName) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.OBSERVATION_BLOCK:
				if(context == grammarAccess.getObservationBlockRule()) {
					sequence_ObservationBlock(context, (ObservationBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.ODE_BLOCK:
				if(context == grammarAccess.getOdeBlockRule()) {
					sequence_OdeBlock(context, (OdeBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.ODE_LIST:
				if(context == grammarAccess.getOdeListRule()) {
					sequence_OdeList(context, (OdeList) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.OR_EXPRESSION:
				if(context == grammarAccess.getOrExpressionRule()) {
					sequence_OrExpression(context, (OrExpression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.OUTPUT_VARIABLES_BLOCK:
				if(context == grammarAccess.getOutputVariablesBlockRule()) {
					sequence_OutputVariablesBlock(context, (OutputVariablesBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.PAR_EXPRESSION:
				if(context == grammarAccess.getParExpressionRule()) {
					sequence_ParExpression(context, (ParExpression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.PARAMETER_BLOCK:
				if(context == grammarAccess.getParameterBlockRule()) {
					sequence_ParameterBlock(context, (ParameterBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.PARAMETER_DECLARATION:
				if(context == grammarAccess.getParameterDeclarationRule()) {
					sequence_ParameterDeclaration(context, (ParameterDeclaration) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.PARAMETER_OBJECT:
				if(context == grammarAccess.getParameterObjectRule()) {
					sequence_ParameterObject(context, (ParameterObject) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.PARAMETER_OBJECT_BLOCK:
				if(context == grammarAccess.getParameterObjectBlockRule()) {
					sequence_ParameterObjectBlock(context, (ParameterObjectBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.POWER_EXPRESSION:
				if(context == grammarAccess.getPowerExpressionRule()) {
					sequence_PowerExpression(context, (PowerExpression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.PRIMARY:
				if(context == grammarAccess.getPrimaryRule()) {
					sequence_Primary(context, (Primary) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.PRIOR_PARAMETERS_BLOCK:
				if(context == grammarAccess.getPriorParametersBlockRule()) {
					sequence_PriorParametersBlock(context, (PriorParametersBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.RSCRIPT_BLOCK:
				if(context == grammarAccess.getRScriptBlockRule()) {
					sequence_RScriptBlock(context, (RScriptBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.RSCRIPT_BLOCK_STATEMENT:
				if(context == grammarAccess.getRScriptBlockStatementRule()) {
					sequence_RScriptBlockStatement(context, (RScriptBlockStatement) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.RANDOM_LIST:
				if(context == grammarAccess.getRandomListRule()) {
					sequence_RandomList(context, (RandomList) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.RANDOM_VARIABLE_DEFINITION_BLOCK:
				if(context == grammarAccess.getRandomVariableDefinitionBlockRule()) {
					sequence_RandomVariableDefinitionBlock(context, (RandomVariableDefinitionBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.REMOVE_LIST:
				if(context == grammarAccess.getRemoveListRule()) {
					sequence_RemoveList(context, (RemoveList) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.SAME_BLOCK:
				if(context == grammarAccess.getSameBlockRule()) {
					sequence_SameBlock(context, (SameBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.SELECTOR:
				if(context == grammarAccess.getSelectorRule()) {
					sequence_Selector(context, (Selector) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.SIMULATE_TASK:
				if(context == grammarAccess.getSimulateTaskRule()) {
					sequence_SimulateTask(context, (SimulateTask) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.SIMULATION_BLOCK:
				if(context == grammarAccess.getSimulationBlockRule()) {
					sequence_SimulationBlock(context, (SimulationBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.STRUCTURAL_BLOCK:
				if(context == grammarAccess.getStructuralBlockRule()) {
					sequence_StructuralBlock(context, (StructuralBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.STRUCTURAL_PARAMETERS_BLOCK:
				if(context == grammarAccess.getStructuralParametersBlockRule()) {
					sequence_StructuralParametersBlock(context, (StructuralParametersBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.SYMBOL_DECLARATION:
				if(context == grammarAccess.getSymbolDeclarationRule()) {
					sequence_SymbolDeclaration(context, (SymbolDeclaration) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.SYMBOL_LIST:
				if(context == grammarAccess.getSymbolListRule()) {
					sequence_SymbolList(context, (SymbolList) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.SYMBOL_MODIFICATION:
				if(context == grammarAccess.getSymbolModificationRule()) {
					sequence_SymbolModification(context, (SymbolModification) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.TEL_OBJECT:
				if(context == grammarAccess.getTELObjectRule()) {
					sequence_TELObject(context, (TELObject) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.TARGET_BLOCK:
				if(context == grammarAccess.getTargetBlockRule()) {
					sequence_TargetBlock(context, (TargetBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.TARGET_LANGUAGE:
				if(context == grammarAccess.getTargetLanguageRule()) {
					sequence_TargetLanguage(context, (TargetLanguage) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.TASK_FUNCTION_BLOCK:
				if(context == grammarAccess.getTaskFunctionBlockRule()) {
					sequence_TaskFunctionBlock(context, (TaskFunctionBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.TASK_FUNCTION_BODY:
				if(context == grammarAccess.getTaskFunctionBodyRule()) {
					sequence_TaskFunctionBody(context, (TaskFunctionBody) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.TASK_FUNCTION_DECLARATION:
				if(context == grammarAccess.getTaskFunctionDeclarationRule()) {
					sequence_TaskFunctionDeclaration(context, (TaskFunctionDeclaration) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.TASK_FUNCTION_STATEMENT:
				if(context == grammarAccess.getTaskFunctionStatementRule()) {
					sequence_TaskFunctionStatement(context, (TaskFunctionStatement) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.TASK_OBJECT:
				if(context == grammarAccess.getTaskObjectRule()) {
					sequence_TaskObject(context, (TaskObject) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.TASK_OBJECT_BLOCK:
				if(context == grammarAccess.getTaskObjectBlockRule()) {
					sequence_TaskObjectBlock(context, (TaskObjectBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.UNARY_EXPRESSION:
				if(context == grammarAccess.getUnaryExpressionRule()) {
					sequence_UnaryExpression(context, (UnaryExpression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.VARIABILITY_BLOCK:
				if(context == grammarAccess.getVariabilityBlockRule()) {
					sequence_VariabilityBlock(context, (VariabilityBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.VARIABILITY_BLOCK_STATEMENT:
				if(context == grammarAccess.getVariabilityBlockStatementRule()) {
					sequence_VariabilityBlockStatement(context, (VariabilityBlockStatement) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.VARIABILITY_PARAMETERS_BLOCK:
				if(context == grammarAccess.getVariabilityParametersBlockRule()) {
					sequence_VariabilityParametersBlock(context, (VariabilityParametersBlock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.VARIABLE_LIST:
				if(context == grammarAccess.getVariableListRule()) {
					sequence_VariableList(context, (VariableList) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.VECTOR:
				if(context == grammarAccess.getVectorRule()) {
					sequence_Vector(context, (Vector) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (identifier='ACCEPT' expression=AndExpression)
	 */
	protected void sequence_AcceptList(EObject context, AcceptList semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.ACCEPT_LIST__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.ACCEPT_LIST__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.ACCEPT_LIST__EXPRESSION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.ACCEPT_LIST__EXPRESSION));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAcceptListAccess().getIdentifierACCEPTKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getAcceptListAccess().getExpressionAndExpressionParserRuleCall_4_0(), semanticObject.getExpression());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='ADD' list=SymbolList)
	 */
	protected void sequence_AddList(EObject context, AddList semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.ADD_LIST__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.ADD_LIST__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.ADD_LIST__LIST) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.ADD_LIST__LIST));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getAddListAccess().getIdentifierADDKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getAddListAccess().getListSymbolListParserRuleCall_2_0(), semanticObject.getList());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (expression+=MultiplicativeExpression (operator+=AdditiveOperator expression+=MultiplicativeExpression)*) | 
	 *         (string+=STRING (operator+=AdditiveOperator string+=STRING)*)
	 *     )
	 */
	protected void sequence_AdditiveExpression(EObject context, AdditiveExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (expression+=LogicalExpression (operator+=LogicalAndOperator expression+=LogicalExpression)*)
	 */
	protected void sequence_AndExpression(EObject context, AndExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (expression=Expression | list=List | odeList=OdeList | type=EnumType)
	 */
	protected void sequence_AnyExpression(EObject context, AnyExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((identifier=ID expression=AnyExpression) | expression=AnyExpression)
	 */
	protected void sequence_Argument(EObject context, Argument semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (arguments+=Argument arguments+=Argument*)
	 */
	protected void sequence_Arguments(EObject context, Arguments semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='matrix' arguments=Arguments parameters=Arguments?)
	 */
	protected void sequence_BlockBlock(EObject context, BlockBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (symbol=SymbolDeclaration | functionCall=FunctionCall | statement=ConditionalStatement)
	 */
	protected void sequence_BlockStatement(EObject context, BlockStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (statements+=BlockStatement*)
	 */
	protected void sequence_Block(EObject context, Block semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='categorical' arguments=Arguments?)
	 */
	protected void sequence_Categorical(EObject context, Categorical semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (expression=OrExpression (expression1=Expression expression2=Expression)?)
	 */
	protected void sequence_ConditionalExpression(EObject context, ConditionalExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (parExpression=ParExpression (ifStatement=BlockStatement | ifBlock=Block) (elseStatement=BlockStatement | elseBlock=Block)?)
	 */
	protected void sequence_ConditionalStatement(EObject context, ConditionalStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     identifier='continuous'
	 */
	protected void sequence_Continuous(EObject context, Continuous semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.CONTINUOUS__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.CONTINUOUS__IDENTIFIER));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getContinuousAccess().getIdentifierContinuousKeyword_0(), semanticObject.getIdentifier());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     identifier='covariate'
	 */
	protected void sequence_Covariate(EObject context, Covariate semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.COVARIATE__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.COVARIATE__IDENTIFIER));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getCovariateAccess().getIdentifierCovariateKeyword_0(), semanticObject.getIdentifier());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (ignoreList=IgnoreList | acceptList=AcceptList | dropList=DropList)
	 */
	protected void sequence_DataBlockStatement(EObject context, DataBlockStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='DATA' statements+=DataBlockStatement*)
	 */
	protected void sequence_DataBlock(EObject context, DataBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (headerBlock=HeaderBlock | fileBlock=FileBlock | targetBlock=TargetBlock | importBlock=ImportBlock)
	 */
	protected void sequence_DataObjectBlock(EObject context, DataObjectBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=ObjectName blocks+=DataObjectBlock*)
	 */
	protected void sequence_DataObject(EObject context, DataObject semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=FullyQualifiedSymbolName arguments=VariableList? expression=AnyExpression)
	 */
	protected void sequence_DesignBlockStatement(EObject context, DesignBlockStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='DESIGN' statements+=DesignBlockStatement*)
	 */
	protected void sequence_DesignBlock(EObject context, DesignBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='diag' arguments=Arguments parameters=Arguments?)
	 */
	protected void sequence_DiagBlock(EObject context, DiagBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='Normal' | identifier='Binomial' | identifier='Poisson' | identifier='Student_T' | identifier='MVNormal')
	 */
	protected void sequence_Distribution(EObject context, Distribution semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='DROP' list=SymbolList)
	 */
	protected void sequence_DropList(EObject context, DropList semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.DROP_LIST__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.DROP_LIST__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.DROP_LIST__LIST) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.DROP_LIST__LIST));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getDropListAccess().getIdentifierDROPKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getDropListAccess().getListSymbolListParserRuleCall_2_0(), semanticObject.getList());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         categorical=Categorical | 
	 *         continuous=Continuous | 
	 *         covariate=Covariate | 
	 *         distribution=Distribution | 
	 *         level=LevelType | 
	 *         likelyhood=Likelyhood | 
	 *         missing=Missing | 
	 *         target=TargetLanguage
	 *     )
	 */
	protected void sequence_EnumType(EObject context, EnumType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='ESTIMATE' statements+=TaskFunctionStatement*)
	 */
	protected void sequence_EstimateTask(EObject context, EstimateTask semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='ESTIMATION' statements+=BlockStatement*)
	 */
	protected void sequence_EstimationBlock(EObject context, EstimationBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='EXECUTE' statements+=TaskFunctionStatement*)
	 */
	protected void sequence_ExecuteTask(EObject context, ExecuteTask semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     conditionalExpression=ConditionalExpression
	 */
	protected void sequence_Expression(EObject context, Expression semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.EXPRESSION__CONDITIONAL_EXPRESSION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.EXPRESSION__CONDITIONAL_EXPRESSION));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getExpressionAccess().getConditionalExpressionConditionalExpressionParserRuleCall_0(), semanticObject.getConditionalExpression());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (variable=SymbolDeclaration | inlineBlock=InlineBlock | designBlock=DesignBlock | rscriptBlock=RScriptBlock)
	 */
	protected void sequence_FileBlockStatement(EObject context, FileBlockStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='FILE' statements+=FileBlockStatement*)
	 */
	protected void sequence_FileBlock(EObject context, FileBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     identifier=ID
	 */
	protected void sequence_FormalArgument(EObject context, FormalArgument semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.FORMAL_ARGUMENT__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.FORMAL_ARGUMENT__IDENTIFIER));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getFormalArgumentAccess().getIdentifierIDTerminalRuleCall_0(), semanticObject.getIdentifier());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (arguments+=FormalArgument arguments+=FormalArgument*)
	 */
	protected void sequence_FormalArguments(EObject context, FormalArguments semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (parent=FullyQualifiedSymbolName selectors+=Selector+)
	 */
	protected void sequence_FullyQualifiedArgumentName(EObject context, FullyQualifiedArgumentName semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (object=[ObjectName|ID]? identifier=ID)
	 */
	protected void sequence_FullyQualifiedSymbolName(EObject context, FullyQualifiedSymbolName semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=ID expression=FunctionCall)
	 */
	protected void sequence_FunctionCallStatement(EObject context, FunctionCallStatement semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.FUNCTION_CALL_STATEMENT__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.FUNCTION_CALL_STATEMENT__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.FUNCTION_CALL_STATEMENT__EXPRESSION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.FUNCTION_CALL_STATEMENT__EXPRESSION));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getFunctionCallStatementAccess().getIdentifierIDTerminalRuleCall_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getFunctionCallStatementAccess().getExpressionFunctionCallParserRuleCall_2_0(), semanticObject.getExpression());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=FullyQualifiedSymbolName arguments=Arguments)
	 */
	protected void sequence_FunctionCall(EObject context, FunctionCall semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.FUNCTION_CALL__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.FUNCTION_CALL__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getFunctionCallAccess().getIdentifierFullyQualifiedSymbolNameParserRuleCall_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getFunctionCallAccess().getArgumentsArgumentsParserRuleCall_2_0(), semanticObject.getArguments());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (statement=BlockStatement | mixtureBlock=MixtureBlock)
	 */
	protected void sequence_GroupVariablesBlockStatement(EObject context, GroupVariablesBlockStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='GROUP_VARIABLES' statements+=GroupVariablesBlockStatement*)
	 */
	protected void sequence_GroupVariablesBlock(EObject context, GroupVariablesBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='HEADER' variables+=SymbolModification*)
	 */
	protected void sequence_HeaderBlock(EObject context, HeaderBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='IGNORE' expression=OrExpression)
	 */
	protected void sequence_IgnoreList(EObject context, IgnoreList semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.IGNORE_LIST__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.IGNORE_LIST__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.IGNORE_LIST__EXPRESSION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.IGNORE_LIST__EXPRESSION));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getIgnoreListAccess().getIdentifierIGNOREKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getIgnoreListAccess().getExpressionOrExpressionParserRuleCall_4_0(), semanticObject.getExpression());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='IMPORT' functions+=ImportedFunction)
	 */
	protected void sequence_ImportBlock(EObject context, ImportBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=ID list=List)
	 */
	protected void sequence_ImportedFunction(EObject context, ImportedFunction semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.IMPORTED_FUNCTION__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.IMPORTED_FUNCTION__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.IMPORTED_FUNCTION__LIST) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.IMPORTED_FUNCTION__LIST));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getImportedFunctionAccess().getIdentifierIDTerminalRuleCall_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getImportedFunctionAccess().getListListParserRuleCall_1_1_0(), semanticObject.getList());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='INDIVIDUAL_VARIABLES' statements+=BlockStatement*)
	 */
	protected void sequence_IndividualVariablesBlock(EObject context, IndividualVariablesBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='INLINE' variables+=FullyQualifiedSymbolName* (values+=NUMBER | values+='.')*)
	 */
	protected void sequence_InlineBlock(EObject context, InlineBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (indentifier='INPUT_VARIABLES' variables+=SymbolDeclaration*)
	 */
	protected void sequence_InputVariablesBlock(EObject context, InputVariablesBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='mdv' | identifier='id' | identifier='dv' | identifier='idv')
	 */
	protected void sequence_LevelType(EObject context, LevelType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='LIBRARY' statements+=FunctionCallStatement*)
	 */
	protected void sequence_LibraryBlock(EObject context, LibraryBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     identifier='LIKELIHOOD'
	 */
	protected void sequence_Likelyhood(EObject context, Likelyhood semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.LIKELYHOOD__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.LIKELYHOOD__IDENTIFIER));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getLikelyhoodAccess().getIdentifierLIKELIHOODKeyword_0(), semanticObject.getIdentifier());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='list' arguments=Arguments)
	 */
	protected void sequence_List(EObject context, List semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.LIST__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.LIST__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.LIST__ARGUMENTS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.LIST__ARGUMENTS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getListAccess().getIdentifierListKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getListAccess().getArgumentsArgumentsParserRuleCall_2_0(), semanticObject.getArguments());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (negation='!'? (boolean=BOOLEAN | (expression+=AdditiveExpression (operator+=LogicalOperator expression+=AdditiveExpression)*)))
	 */
	protected void sequence_LogicalExpression(EObject context, LogicalExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (modelObject=ModelObject | parameterObject=ParameterObject | dataObject=DataObject | taskObject=TaskObject | telObject=TELObject)
	 */
	protected void sequence_MclObject(EObject context, MclObject semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     objects+=MclObject*
	 */
	protected void sequence_Mcl(EObject context, Mcl semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     identifier='MISSING'
	 */
	protected void sequence_Missing(EObject context, Missing semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.MISSING__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.MISSING__IDENTIFIER));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getMissingAccess().getIdentifierMISSINGKeyword_0(), semanticObject.getIdentifier());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='MIXTURE' statements+=BlockStatement*)
	 */
	protected void sequence_MixtureBlock(EObject context, MixtureBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (statement=BlockStatement | addList=AddList | removeList=RemoveList)
	 */
	protected void sequence_ModelBlockStatement(EObject context, ModelBlockStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='MODEL' statements+=ModelBlockStatement*)
	 */
	protected void sequence_ModelBlock(EObject context, ModelBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         individualVariablesBlock=IndividualVariablesBlock | 
	 *         modelPredictionBlock=ModelPredictionBlock | 
	 *         randomVariableDefinitionBlock=RandomVariableDefinitionBlock | 
	 *         inputVariablesBlock=InputVariablesBlock | 
	 *         structuralParametersBlock=StructuralParametersBlock | 
	 *         variabilityParametersBlock=VariabilityParametersBlock | 
	 *         outputVariablesBlock=OutputVariablesBlock | 
	 *         groupVariablesBlock=GroupVariablesBlock | 
	 *         observationBlock=ObservationBlock | 
	 *         estimationBlock=EstimationBlock | 
	 *         simulationBlock=SimulationBlock | 
	 *         targetBlock=TargetBlock | 
	 *         importBlock=ImportBlock
	 *     )
	 */
	protected void sequence_ModelObjectBlock(EObject context, ModelObjectBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=ObjectName blocks+=ModelObjectBlock*)
	 */
	protected void sequence_ModelObject(EObject context, ModelObject semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (statement=BlockStatement | odeBlock=OdeBlock | libraryBlock=LibraryBlock)
	 */
	protected void sequence_ModelPredictionBlockStatement(EObject context, ModelPredictionBlockStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='MODEL_PREDICTION' statements+=ModelPredictionBlockStatement*)
	 */
	protected void sequence_ModelPredictionBlock(EObject context, ModelPredictionBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (expression+=PowerExpression (operator+=MultiplicativeOperator expression+=PowerExpression)*)
	 */
	protected void sequence_MultiplicativeExpression(EObject context, MultiplicativeExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     name=ID
	 */
	protected void sequence_ObjectName(EObject context, ObjectName semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.OBJECT_NAME__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.OBJECT_NAME__NAME));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getObjectNameAccess().getNameIDTerminalRuleCall_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='OBSERVATION' statements+=BlockStatement*)
	 */
	protected void sequence_ObservationBlock(EObject context, ObservationBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='ODE' statements+=BlockStatement*)
	 */
	protected void sequence_OdeBlock(EObject context, OdeBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='ode' arguments=Arguments)
	 */
	protected void sequence_OdeList(EObject context, OdeList semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.ODE_LIST__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.ODE_LIST__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.ODE_LIST__ARGUMENTS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.ODE_LIST__ARGUMENTS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getOdeListAccess().getIdentifierOdeKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getOdeListAccess().getArgumentsArgumentsParserRuleCall_2_0(), semanticObject.getArguments());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (expression+=AndExpression (operator+=LogicalOrOperator expression+=AndExpression)*)
	 */
	protected void sequence_OrExpression(EObject context, OrExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='OUTPUT_VARIABLES' variables+=FullyQualifiedSymbolName*)
	 */
	protected void sequence_OutputVariablesBlock(EObject context, OutputVariablesBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     expression=Expression
	 */
	protected void sequence_ParExpression(EObject context, ParExpression semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.PAR_EXPRESSION__EXPRESSION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.PAR_EXPRESSION__EXPRESSION));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getParExpressionAccess().getExpressionExpressionParserRuleCall_1_0(), semanticObject.getExpression());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='PARAMETER' parameters+=SymbolModification*)
	 */
	protected void sequence_ParameterBlock(EObject context, ParameterBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=ID list=List)
	 */
	protected void sequence_ParameterDeclaration(EObject context, ParameterDeclaration semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.PARAMETER_DECLARATION__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.PARAMETER_DECLARATION__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.PARAMETER_DECLARATION__LIST) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.PARAMETER_DECLARATION__LIST));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getParameterDeclarationAccess().getIdentifierIDTerminalRuleCall_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getParameterDeclarationAccess().getListListParserRuleCall_1_1_0(), semanticObject.getList());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         structuralBlock=StructuralBlock | 
	 *         variabilityBlock=VariabilityBlock | 
	 *         priorBlock=PriorParametersBlock | 
	 *         targetBlock=TargetBlock | 
	 *         importBlock=ImportBlock
	 *     )
	 */
	protected void sequence_ParameterObjectBlock(EObject context, ParameterObjectBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=ObjectName blocks+=ParameterObjectBlock*)
	 */
	protected void sequence_ParameterObject(EObject context, ParameterObject semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (expression+=UnaryExpression (operator+=PowerOperator expression+=UnaryExpression)*)
	 */
	protected void sequence_PowerExpression(EObject context, PowerExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (functionCall=FunctionCall | number=NUMBER | symbol=FullyQualifiedSymbolName | attribute=FullyQualifiedArgumentName | vector=Vector)
	 */
	protected void sequence_Primary(EObject context, Primary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='PRIOR_PARAMETERS' statements+=BlockStatement*)
	 */
	protected void sequence_PriorParametersBlock(EObject context, PriorParametersBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=ID (value=STRING | object=ObjectName))
	 */
	protected void sequence_RScriptBlockStatement(EObject context, RScriptBlockStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='RSCRIPT' variables+=RScriptBlockStatement*)
	 */
	protected void sequence_RScriptBlock(EObject context, RScriptBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='~' arguments=Arguments)
	 */
	protected void sequence_RandomList(EObject context, RandomList semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.RANDOM_LIST__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.RANDOM_LIST__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.RANDOM_LIST__ARGUMENTS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.RANDOM_LIST__ARGUMENTS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getRandomListAccess().getIdentifierTildeKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getRandomListAccess().getArgumentsArgumentsParserRuleCall_2_0(), semanticObject.getArguments());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='RANDOM_VARIABLE_DEFINITION' variables+=SymbolDeclaration*)
	 */
	protected void sequence_RandomVariableDefinitionBlock(EObject context, RandomVariableDefinitionBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='REMOVE' list=SymbolList)
	 */
	protected void sequence_RemoveList(EObject context, RemoveList semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.REMOVE_LIST__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.REMOVE_LIST__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.REMOVE_LIST__LIST) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.REMOVE_LIST__LIST));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getRemoveListAccess().getIdentifierREMOVEKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getRemoveListAccess().getListSymbolListParserRuleCall_2_0(), semanticObject.getList());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='same' arguments=Arguments parameters=FormalArguments?)
	 */
	protected void sequence_SameBlock(EObject context, SameBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=ID | selector=NUMBER)
	 */
	protected void sequence_Selector(EObject context, Selector semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='SIMULATE' statements+=TaskFunctionStatement*)
	 */
	protected void sequence_SimulateTask(EObject context, SimulateTask semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='SIMULATION' statements+=BlockStatement*)
	 */
	protected void sequence_SimulationBlock(EObject context, SimulationBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='STRUCTURAL' parameters+=ParameterDeclaration*)
	 */
	protected void sequence_StructuralBlock(EObject context, StructuralBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='STRUCTURAL_PARAMETERS' parameters+=FullyQualifiedSymbolName*)
	 */
	protected void sequence_StructuralParametersBlock(EObject context, StructuralParametersBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((identifier=ID (expression=AnyExpression? | randomList=RandomList)) | (function=ID identifier=ID randomList=RandomList))
	 */
	protected void sequence_SymbolDeclaration(EObject context, SymbolDeclaration semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='list' symbols+=FullyQualifiedSymbolName symbols+=FullyQualifiedSymbolName*)
	 */
	protected void sequence_SymbolList(EObject context, SymbolList semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=FullyQualifiedSymbolName list=List?)
	 */
	protected void sequence_SymbolModification(EObject context, SymbolModification semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=ObjectName statements+=BlockStatement*)
	 */
	protected void sequence_TELObject(EObject context, TELObject semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='TARGET_CODE' arguments=Arguments? externalCode=EXTERNAL_CODE)
	 */
	protected void sequence_TargetBlock(EObject context, TargetBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         identifier='NMTRAN_CODE' | 
	 *         identifier='MLXTRAN_CODE' | 
	 *         identifier='PML_CODE' | 
	 *         identifier='BUGS_CODE' | 
	 *         identifier='R_CODE' | 
	 *         identifier='MATLAB_CODE'
	 *     )
	 */
	protected void sequence_TargetLanguage(EObject context, TargetLanguage semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (estimateBlock=EstimateTask | simulateBlock=SimulateTask | executeBlock=ExecuteTask)
	 */
	protected void sequence_TaskFunctionBlock(EObject context, TaskFunctionBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (blocks+=TaskFunctionBlock*)
	 */
	protected void sequence_TaskFunctionBody(EObject context, TaskFunctionBody semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=ID formalArguments=FormalArguments functionBody=TaskFunctionBody)
	 */
	protected void sequence_TaskFunctionDeclaration(EObject context, TaskFunctionDeclaration semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.TASK_FUNCTION_DECLARATION__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.TASK_FUNCTION_DECLARATION__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.TASK_FUNCTION_DECLARATION__FORMAL_ARGUMENTS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.TASK_FUNCTION_DECLARATION__FORMAL_ARGUMENTS));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.TASK_FUNCTION_DECLARATION__FUNCTION_BODY) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.TASK_FUNCTION_DECLARATION__FUNCTION_BODY));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getTaskFunctionDeclarationAccess().getIdentifierIDTerminalRuleCall_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getTaskFunctionDeclarationAccess().getFormalArgumentsFormalArgumentsParserRuleCall_4_0(), semanticObject.getFormalArguments());
		feeder.accept(grammarAccess.getTaskFunctionDeclarationAccess().getFunctionBodyTaskFunctionBodyParserRuleCall_6_0(), semanticObject.getFunctionBody());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (statement=BlockStatement | targetBlock=TargetBlock)
	 */
	protected void sequence_TaskFunctionStatement(EObject context, TaskFunctionStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         functionDeclaration=TaskFunctionDeclaration | 
	 *         parameterBlock=ParameterBlock | 
	 *         dataBlock=DataBlock | 
	 *         modelBlock=ModelBlock | 
	 *         targetBlock=TargetBlock | 
	 *         importBlock=ImportBlock
	 *     )
	 */
	protected void sequence_TaskObjectBlock(EObject context, TaskObjectBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=ObjectName blocks+=TaskObjectBlock*)
	 */
	protected void sequence_TaskObject(EObject context, TaskObject semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((operator=UnaryOperator expression=UnaryExpression) | parExpression=ParExpression | primary=Primary)
	 */
	protected void sequence_UnaryExpression(EObject context, UnaryExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (parameter=ParameterDeclaration | blockBlock=BlockBlock | diagBlock=DiagBlock | sameBlock=SameBlock)
	 */
	protected void sequence_VariabilityBlockStatement(EObject context, VariabilityBlockStatement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='VARIABILITY' statements+=VariabilityBlockStatement*)
	 */
	protected void sequence_VariabilityBlock(EObject context, VariabilityBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='VARIABILITY_PARAMETERS' parameters+=FullyQualifiedSymbolName*)
	 */
	protected void sequence_VariabilityParametersBlock(EObject context, VariabilityParametersBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifiers+=FullyQualifiedSymbolName identifiers+=FullyQualifiedSymbolName*)
	 */
	protected void sequence_VariableList(EObject context, VariableList semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='c' values+=Expression values+=Expression*)
	 */
	protected void sequence_Vector(EObject context, Vector semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
