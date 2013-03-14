package org.ddmore.mdl.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.additive_expression;
import org.ddmore.mdl.mdl.any_expression;
import org.ddmore.mdl.mdl.argument;
import org.ddmore.mdl.mdl.arguments;
import org.ddmore.mdl.mdl.block;
import org.ddmore.mdl.mdl.block_statement;
import org.ddmore.mdl.mdl.block_subblock;
import org.ddmore.mdl.mdl.conditional_and_expression;
import org.ddmore.mdl.mdl.conditional_expression;
import org.ddmore.mdl.mdl.conditional_or_expression;
import org.ddmore.mdl.mdl.data_block;
import org.ddmore.mdl.mdl.data_obj;
import org.ddmore.mdl.mdl.data_obj_block;
import org.ddmore.mdl.mdl.design_block;
import org.ddmore.mdl.mdl.diag_subblock;
import org.ddmore.mdl.mdl.estimation_block;
import org.ddmore.mdl.mdl.expression;
import org.ddmore.mdl.mdl.file_block;
import org.ddmore.mdl.mdl.file_block_content;
import org.ddmore.mdl.mdl.file_block_statement;
import org.ddmore.mdl.mdl.formal_arguments;
import org.ddmore.mdl.mdl.function_body;
import org.ddmore.mdl.mdl.function_call;
import org.ddmore.mdl.mdl.function_declaration;
import org.ddmore.mdl.mdl.function_subblock;
import org.ddmore.mdl.mdl.group_variables;
import org.ddmore.mdl.mdl.header_block;
import org.ddmore.mdl.mdl.individual_model_obj_block;
import org.ddmore.mdl.mdl.inline_block;
import org.ddmore.mdl.mdl.inline_block_content;
import org.ddmore.mdl.mdl.input_variables_block;
import org.ddmore.mdl.mdl.library_block;
import org.ddmore.mdl.mdl.list;
import org.ddmore.mdl.mdl.mcl;
import org.ddmore.mdl.mdl.mcl_obj;
import org.ddmore.mdl.mdl.model_block;
import org.ddmore.mdl.mdl.model_block_statement;
import org.ddmore.mdl.mdl.model_obj;
import org.ddmore.mdl.mdl.model_obj_block;
import org.ddmore.mdl.mdl.model_prediction_obj_block;
import org.ddmore.mdl.mdl.multiplicative_expression;
import org.ddmore.mdl.mdl.observation_block;
import org.ddmore.mdl.mdl.ode_block;
import org.ddmore.mdl.mdl.ode_list;
import org.ddmore.mdl.mdl.output_variables_block;
import org.ddmore.mdl.mdl.par_expression;
import org.ddmore.mdl.mdl.param_obj;
import org.ddmore.mdl.mdl.param_obj_block;
import org.ddmore.mdl.mdl.parameters_block;
import org.ddmore.mdl.mdl.power_expression;
import org.ddmore.mdl.mdl.primary;
import org.ddmore.mdl.mdl.random_list;
import org.ddmore.mdl.mdl.random_variable_definition_block;
import org.ddmore.mdl.mdl.relational_expression;
import org.ddmore.mdl.mdl.rsscript_block;
import org.ddmore.mdl.mdl.selector;
import org.ddmore.mdl.mdl.simulation_block;
import org.ddmore.mdl.mdl.statement;
import org.ddmore.mdl.mdl.structural_block;
import org.ddmore.mdl.mdl.structural_parameters_block;
import org.ddmore.mdl.mdl.target_block;
import org.ddmore.mdl.mdl.task_obj;
import org.ddmore.mdl.mdl.task_obj_block;
import org.ddmore.mdl.mdl.tel_obj;
import org.ddmore.mdl.mdl.unary_expression;
import org.ddmore.mdl.mdl.variability_block;
import org.ddmore.mdl.mdl.variability_block_content;
import org.ddmore.mdl.mdl.variability_block_statement;
import org.ddmore.mdl.mdl.variability_parameters_block;
import org.ddmore.mdl.mdl.variability_subblock;
import org.ddmore.mdl.mdl.variable_declaration;
import org.ddmore.mdl.mdl.variable_name;
import org.ddmore.mdl.mdl.verbatim_block;
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
			case MdlPackage.ADDITIVE_EXPRESSION:
				if(context == grammarAccess.getAdditive_expressionRule()) {
					sequence_additive_expression(context, (additive_expression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.ANY_EXPRESSION:
				if(context == grammarAccess.getAny_expressionRule()) {
					sequence_any_expression(context, (any_expression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.ARGUMENT:
				if(context == grammarAccess.getArgumentRule()) {
					sequence_argument(context, (argument) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.ARGUMENTS:
				if(context == grammarAccess.getArgumentsRule()) {
					sequence_arguments(context, (arguments) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.BLOCK:
				if(context == grammarAccess.getBlockRule()) {
					sequence_block(context, (block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.BLOCK_STATEMENT:
				if(context == grammarAccess.getBlock_statementRule()) {
					sequence_block_statement(context, (block_statement) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.BLOCK_SUBBLOCK:
				if(context == grammarAccess.getBlock_subblockRule()) {
					sequence_block_subblock(context, (block_subblock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.CONDITIONAL_AND_EXPRESSION:
				if(context == grammarAccess.getConditional_and_expressionRule()) {
					sequence_conditional_and_expression(context, (conditional_and_expression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.CONDITIONAL_EXPRESSION:
				if(context == grammarAccess.getConditional_expressionRule()) {
					sequence_conditional_expression(context, (conditional_expression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.CONDITIONAL_OR_EXPRESSION:
				if(context == grammarAccess.getConditional_or_expressionRule()) {
					sequence_conditional_or_expression(context, (conditional_or_expression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.DATA_BLOCK:
				if(context == grammarAccess.getData_blockRule()) {
					sequence_data_block(context, (data_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.DATA_OBJ:
				if(context == grammarAccess.getData_objRule()) {
					sequence_data_obj(context, (data_obj) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.DATA_OBJ_BLOCK:
				if(context == grammarAccess.getData_obj_blockRule()) {
					sequence_data_obj_block(context, (data_obj_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.DESIGN_BLOCK:
				if(context == grammarAccess.getDesign_blockRule()) {
					sequence_design_block(context, (design_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.DIAG_SUBBLOCK:
				if(context == grammarAccess.getDiag_subblockRule()) {
					sequence_diag_subblock(context, (diag_subblock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.ESTIMATION_BLOCK:
				if(context == grammarAccess.getEstimation_blockRule()) {
					sequence_estimation_block(context, (estimation_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.EXPRESSION:
				if(context == grammarAccess.getExpressionRule()) {
					sequence_expression(context, (expression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.FILE_BLOCK:
				if(context == grammarAccess.getFile_blockRule()) {
					sequence_file_block(context, (file_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.FILE_BLOCK_CONTENT:
				if(context == grammarAccess.getFile_block_contentRule()) {
					sequence_file_block_content(context, (file_block_content) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.FILE_BLOCK_STATEMENT:
				if(context == grammarAccess.getFile_block_statementRule()) {
					sequence_file_block_statement(context, (file_block_statement) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.FORMAL_ARGUMENTS:
				if(context == grammarAccess.getFormal_argumentsRule()) {
					sequence_formal_arguments(context, (formal_arguments) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.FUNCTION_BODY:
				if(context == grammarAccess.getFunction_bodyRule()) {
					sequence_function_body(context, (function_body) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.FUNCTION_CALL:
				if(context == grammarAccess.getFunction_callRule()) {
					sequence_function_call(context, (function_call) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.FUNCTION_DECLARATION:
				if(context == grammarAccess.getFunction_declarationRule()) {
					sequence_function_declaration(context, (function_declaration) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.FUNCTION_SUBBLOCK:
				if(context == grammarAccess.getFunction_subblockRule()) {
					sequence_function_subblock(context, (function_subblock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.GROUP_VARIABLES:
				if(context == grammarAccess.getGroup_variablesRule()) {
					sequence_group_variables(context, (group_variables) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.HEADER_BLOCK:
				if(context == grammarAccess.getHeader_blockRule()) {
					sequence_header_block(context, (header_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.INDIVIDUAL_MODEL_OBJ_BLOCK:
				if(context == grammarAccess.getIndividual_model_obj_blockRule()) {
					sequence_individual_model_obj_block(context, (individual_model_obj_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.INLINE_BLOCK:
				if(context == grammarAccess.getInline_blockRule()) {
					sequence_inline_block(context, (inline_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.INLINE_BLOCK_CONTENT:
				if(context == grammarAccess.getInline_block_contentRule()) {
					sequence_inline_block_content(context, (inline_block_content) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.INPUT_VARIABLES_BLOCK:
				if(context == grammarAccess.getInput_variables_blockRule()) {
					sequence_input_variables_block(context, (input_variables_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.LIBRARY_BLOCK:
				if(context == grammarAccess.getLibrary_blockRule()) {
					sequence_library_block(context, (library_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.LIST:
				if(context == grammarAccess.getListRule()) {
					sequence_list(context, (list) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MCL:
				if(context == grammarAccess.getMclRule()) {
					sequence_mcl(context, (mcl) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MCL_OBJ:
				if(context == grammarAccess.getMcl_objRule()) {
					sequence_mcl_obj(context, (mcl_obj) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MODEL_BLOCK:
				if(context == grammarAccess.getModel_blockRule()) {
					sequence_model_block(context, (model_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MODEL_BLOCK_STATEMENT:
				if(context == grammarAccess.getModel_block_statementRule()) {
					sequence_model_block_statement(context, (model_block_statement) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MODEL_OBJ:
				if(context == grammarAccess.getModel_objRule()) {
					sequence_model_obj(context, (model_obj) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MODEL_OBJ_BLOCK:
				if(context == grammarAccess.getModel_obj_blockRule()) {
					sequence_model_obj_block(context, (model_obj_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MODEL_PREDICTION_OBJ_BLOCK:
				if(context == grammarAccess.getModel_prediction_obj_blockRule()) {
					sequence_model_prediction_obj_block(context, (model_prediction_obj_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.MULTIPLICATIVE_EXPRESSION:
				if(context == grammarAccess.getMultiplicative_expressionRule()) {
					sequence_multiplicative_expression(context, (multiplicative_expression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.OBSERVATION_BLOCK:
				if(context == grammarAccess.getObservation_blockRule()) {
					sequence_observation_block(context, (observation_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.ODE_BLOCK:
				if(context == grammarAccess.getOde_blockRule()) {
					sequence_ode_block(context, (ode_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.ODE_LIST:
				if(context == grammarAccess.getOde_listRule()) {
					sequence_ode_list(context, (ode_list) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.OUTPUT_VARIABLES_BLOCK:
				if(context == grammarAccess.getOutput_variables_blockRule()) {
					sequence_output_variables_block(context, (output_variables_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.PAR_EXPRESSION:
				if(context == grammarAccess.getPar_expressionRule()) {
					sequence_par_expression(context, (par_expression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.PARAM_OBJ:
				if(context == grammarAccess.getParam_objRule()) {
					sequence_param_obj(context, (param_obj) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.PARAM_OBJ_BLOCK:
				if(context == grammarAccess.getParam_obj_blockRule()) {
					sequence_param_obj_block(context, (param_obj_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.PARAMETERS_BLOCK:
				if(context == grammarAccess.getParameters_blockRule()) {
					sequence_parameters_block(context, (parameters_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.POWER_EXPRESSION:
				if(context == grammarAccess.getPower_expressionRule()) {
					sequence_power_expression(context, (power_expression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.PRIMARY:
				if(context == grammarAccess.getPrimaryRule()) {
					sequence_primary(context, (primary) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.RANDOM_LIST:
				if(context == grammarAccess.getRandom_listRule()) {
					sequence_random_list(context, (random_list) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.RANDOM_VARIABLE_DEFINITION_BLOCK:
				if(context == grammarAccess.getRandom_variable_definition_blockRule()) {
					sequence_random_variable_definition_block(context, (random_variable_definition_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.RELATIONAL_EXPRESSION:
				if(context == grammarAccess.getRelational_expressionRule()) {
					sequence_relational_expression(context, (relational_expression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.RSSCRIPT_BLOCK:
				if(context == grammarAccess.getRsscript_blockRule()) {
					sequence_rsscript_block(context, (rsscript_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.SELECTOR:
				if(context == grammarAccess.getSelectorRule()) {
					sequence_selector(context, (selector) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.SIMULATION_BLOCK:
				if(context == grammarAccess.getSimulation_blockRule()) {
					sequence_simulation_block(context, (simulation_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.STATEMENT:
				if(context == grammarAccess.getStatementRule()) {
					sequence_statement(context, (statement) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.STRUCTURAL_BLOCK:
				if(context == grammarAccess.getStructural_blockRule()) {
					sequence_structural_block(context, (structural_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.STRUCTURAL_PARAMETERS_BLOCK:
				if(context == grammarAccess.getStructural_parameters_blockRule()) {
					sequence_structural_parameters_block(context, (structural_parameters_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.TARGET_BLOCK:
				if(context == grammarAccess.getTarget_blockRule()) {
					sequence_target_block(context, (target_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.TASK_OBJ:
				if(context == grammarAccess.getTask_objRule()) {
					sequence_task_obj(context, (task_obj) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.TASK_OBJ_BLOCK:
				if(context == grammarAccess.getTask_obj_blockRule()) {
					sequence_task_obj_block(context, (task_obj_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.TEL_OBJ:
				if(context == grammarAccess.getTel_objRule()) {
					sequence_tel_obj(context, (tel_obj) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.UNARY_EXPRESSION:
				if(context == grammarAccess.getUnary_expressionRule()) {
					sequence_unary_expression(context, (unary_expression) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.VARIABILITY_BLOCK:
				if(context == grammarAccess.getVariability_blockRule()) {
					sequence_variability_block(context, (variability_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.VARIABILITY_BLOCK_CONTENT:
				if(context == grammarAccess.getVariability_block_contentRule()) {
					sequence_variability_block_content(context, (variability_block_content) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.VARIABILITY_BLOCK_STATEMENT:
				if(context == grammarAccess.getVariability_block_statementRule()) {
					sequence_variability_block_statement(context, (variability_block_statement) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.VARIABILITY_PARAMETERS_BLOCK:
				if(context == grammarAccess.getVariability_parameters_blockRule()) {
					sequence_variability_parameters_block(context, (variability_parameters_block) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.VARIABILITY_SUBBLOCK:
				if(context == grammarAccess.getVariability_subblockRule()) {
					sequence_variability_subblock(context, (variability_subblock) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.VARIABLE_DECLARATION:
				if(context == grammarAccess.getVariable_declarationRule()) {
					sequence_variable_declaration(context, (variable_declaration) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.VARIABLE_NAME:
				if(context == grammarAccess.getVariable_nameRule()) {
					sequence_variable_name(context, (variable_name) semanticObject); 
					return; 
				}
				else break;
			case MdlPackage.VERBATIM_BLOCK:
				if(context == grammarAccess.getVerbatim_blockRule()) {
					sequence_verbatim_block(context, (verbatim_block) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (multiplicative_expression+=multiplicative_expression (additive_op+=additive_op multiplicative_expression+=multiplicative_expression)*)
	 */
	protected void sequence_additive_expression(EObject context, additive_expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (expression=expression | list=list | ode_list=ode_list | random_list=random_list)
	 */
	protected void sequence_any_expression(EObject context, any_expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((identifier=ID expression=any_expression) | expression=any_expression)
	 */
	protected void sequence_argument(EObject context, argument semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (arguments+=argument arguments+=argument*)
	 */
	protected void sequence_arguments(EObject context, arguments semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (statements+=block_statement*)
	 */
	protected void sequence_block(EObject context, block semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (variable_declaration=variable_declaration | function_call=function_call | statement=statement | verbatim_block=verbatim_block)
	 */
	protected void sequence_block_statement(EObject context, block_statement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='block' block=variability_subblock)
	 */
	protected void sequence_block_subblock(EObject context, block_subblock semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.BLOCK_SUBBLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.BLOCK_SUBBLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.BLOCK_SUBBLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.BLOCK_SUBBLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getBlock_subblockAccess().getIdentifierBlockKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getBlock_subblockAccess().getBlockVariability_subblockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (relational_expression+=relational_expression (operator+=and_op relational_expression+=relational_expression)*)
	 */
	protected void sequence_conditional_and_expression(EObject context, conditional_and_expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (conditional_or_expression=conditional_or_expression (expression1=expression expression2=expression)?)
	 */
	protected void sequence_conditional_expression(EObject context, conditional_expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (conditional_and_expression+=conditional_and_expression (operator+=or_op conditional_and_expression+=conditional_and_expression)*)
	 */
	protected void sequence_conditional_or_expression(EObject context, conditional_or_expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='DATA' block=block)
	 */
	protected void sequence_data_block(EObject context, data_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.DATA_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.DATA_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.DATA_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.DATA_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getData_blockAccess().getIdentifierDATAKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getData_blockAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (header_block=header_block | file_block=file_block)
	 */
	protected void sequence_data_obj_block(EObject context, data_obj_block semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=ID blocks+=data_obj_block*)
	 */
	protected void sequence_data_obj(EObject context, data_obj semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='DESIGN' block=block)
	 */
	protected void sequence_design_block(EObject context, design_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.DESIGN_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.DESIGN_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.DESIGN_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.DESIGN_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getDesign_blockAccess().getIdentifierDESIGNKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getDesign_blockAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='diag' block=variability_subblock)
	 */
	protected void sequence_diag_subblock(EObject context, diag_subblock semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.DIAG_SUBBLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.DIAG_SUBBLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.DIAG_SUBBLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.DIAG_SUBBLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getDiag_subblockAccess().getIdentifierDiagKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getDiag_subblockAccess().getBlockVariability_subblockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='ESTIMATION' block=block)
	 */
	protected void sequence_estimation_block(EObject context, estimation_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.ESTIMATION_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.ESTIMATION_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.ESTIMATION_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.ESTIMATION_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getEstimation_blockAccess().getIdentifierESTIMATIONKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getEstimation_blockAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (conditional_expression=conditional_expression | (string_expression+=STRING string_expression+=STRING*))
	 */
	protected void sequence_expression(EObject context, expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (blocks+=file_block_statement*)
	 */
	protected void sequence_file_block_content(EObject context, file_block_content semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='FILE' block=file_block_content)
	 */
	protected void sequence_file_block(EObject context, file_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.FILE_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.FILE_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.FILE_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.FILE_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getFile_blockAccess().getIdentifierFILEKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getFile_blockAccess().getBlockFile_block_contentParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (statement=block_statement | inline_block=inline_block | design_block=design_block | rsscript_block=rsscript_block)
	 */
	protected void sequence_file_block_statement(EObject context, file_block_statement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifiers+=ID identifiers+=ID*)
	 */
	protected void sequence_formal_arguments(EObject context, formal_arguments semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (blocks+=function_subblock*)
	 */
	protected void sequence_function_body(EObject context, function_body semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (funct_name=ID arguments=arguments)
	 */
	protected void sequence_function_call(EObject context, function_call semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.FUNCTION_CALL__FUNCT_NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.FUNCTION_CALL__FUNCT_NAME));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.FUNCTION_CALL__ARGUMENTS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getFunction_callAccess().getFunct_nameIDTerminalRuleCall_0_0(), semanticObject.getFunct_name());
		feeder.accept(grammarAccess.getFunction_callAccess().getArgumentsArgumentsParserRuleCall_2_0(), semanticObject.getArguments());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=ID formal_arguments=formal_arguments function_body=function_body)
	 */
	protected void sequence_function_declaration(EObject context, function_declaration semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.FUNCTION_DECLARATION__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.FUNCTION_DECLARATION__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.FUNCTION_DECLARATION__FORMAL_ARGUMENTS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.FUNCTION_DECLARATION__FORMAL_ARGUMENTS));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.FUNCTION_DECLARATION__FUNCTION_BODY) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.FUNCTION_DECLARATION__FUNCTION_BODY));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getFunction_declarationAccess().getIdentifierIDTerminalRuleCall_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getFunction_declarationAccess().getFormal_argumentsFormal_argumentsParserRuleCall_4_0(), semanticObject.getFormal_arguments());
		feeder.accept(grammarAccess.getFunction_declarationAccess().getFunction_bodyFunction_bodyParserRuleCall_6_0(), semanticObject.getFunction_body());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     ((identifier='ESTIMATE' estimate_defn=block) | (identifier='SIMULATE' simulate_defn=block))
	 */
	protected void sequence_function_subblock(EObject context, function_subblock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='GROUP_VARIABLES' block=block)
	 */
	protected void sequence_group_variables(EObject context, group_variables semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.GROUP_VARIABLES__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.GROUP_VARIABLES__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.GROUP_VARIABLES__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.GROUP_VARIABLES__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getGroup_variablesAccess().getIdentifierGROUP_VARIABLESKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getGroup_variablesAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='HEADER' block=block)
	 */
	protected void sequence_header_block(EObject context, header_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.HEADER_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.HEADER_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.HEADER_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.HEADER_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getHeader_blockAccess().getIdentifierHEADERKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getHeader_blockAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='INDIVIDUAL_VARIABLES' block=block)
	 */
	protected void sequence_individual_model_obj_block(EObject context, individual_model_obj_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.INDIVIDUAL_MODEL_OBJ_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.INDIVIDUAL_MODEL_OBJ_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.INDIVIDUAL_MODEL_OBJ_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.INDIVIDUAL_MODEL_OBJ_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getIndividual_model_obj_blockAccess().getIdentifierINDIVIDUAL_VARIABLESKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getIndividual_model_obj_blockAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifiers+=ID* (values+=NUMBER | values+='.')*)
	 */
	protected void sequence_inline_block_content(EObject context, inline_block_content semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='INLINE' block=inline_block_content)
	 */
	protected void sequence_inline_block(EObject context, inline_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.INLINE_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.INLINE_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.INLINE_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.INLINE_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getInline_blockAccess().getIdentifierINLINEKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getInline_blockAccess().getBlockInline_block_contentParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (indentifier='INPUT_VARIABLES' block=block)
	 */
	protected void sequence_input_variables_block(EObject context, input_variables_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.INPUT_VARIABLES_BLOCK__INDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.INPUT_VARIABLES_BLOCK__INDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.INPUT_VARIABLES_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.INPUT_VARIABLES_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getInput_variables_blockAccess().getIndentifierINPUT_VARIABLESKeyword_0_0(), semanticObject.getIndentifier());
		feeder.accept(grammarAccess.getInput_variables_blockAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='LIBRARY' block=block)
	 */
	protected void sequence_library_block(EObject context, library_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.LIBRARY_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.LIBRARY_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.LIBRARY_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.LIBRARY_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getLibrary_blockAccess().getIdentifierLIBRARYKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getLibrary_blockAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     arguments=arguments
	 */
	protected void sequence_list(EObject context, list semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.LIST__ARGUMENTS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.LIST__ARGUMENTS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getListAccess().getArgumentsArgumentsParserRuleCall_2_0(), semanticObject.getArguments());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     objects+=mcl_obj*
	 */
	protected void sequence_mcl(EObject context, mcl semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (model_obj=model_obj | param_obj=param_obj | data_obj=data_obj | task_obj=task_obj | tel_obj=tel_obj)
	 */
	protected void sequence_mcl_obj(EObject context, mcl_obj semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (statements+=model_block_statement*)
	 */
	protected void sequence_model_block(EObject context, model_block semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (statement=block_statement | ode_block=ode_block | library_block=library_block)
	 */
	protected void sequence_model_block_statement(EObject context, model_block_statement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         individual_model_obj_block=individual_model_obj_block | 
	 *         model_prediction_obj_block=model_prediction_obj_block | 
	 *         random_variable_definition_block=random_variable_definition_block | 
	 *         input_variables_block=input_variables_block | 
	 *         structural_parameters_block=structural_parameters_block | 
	 *         variability_parameters_block=variability_parameters_block | 
	 *         output_variables_block=output_variables_block | 
	 *         group_variables=group_variables | 
	 *         observation_block=observation_block | 
	 *         estimation_block=estimation_block | 
	 *         simulation_block=simulation_block
	 *     )
	 */
	protected void sequence_model_obj_block(EObject context, model_obj_block semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=ID blocks+=model_obj_block*)
	 */
	protected void sequence_model_obj(EObject context, model_obj semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='MODEL_PREDICTION' block=model_block)
	 */
	protected void sequence_model_prediction_obj_block(EObject context, model_prediction_obj_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.MODEL_PREDICTION_OBJ_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.MODEL_PREDICTION_OBJ_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.MODEL_PREDICTION_OBJ_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.MODEL_PREDICTION_OBJ_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getModel_prediction_obj_blockAccess().getIdentifierMODEL_PREDICTIONKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getModel_prediction_obj_blockAccess().getBlockModel_blockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (power_expression+=power_expression (multiplicative_op+=multiplicative_op power_expression+=power_expression)*)
	 */
	protected void sequence_multiplicative_expression(EObject context, multiplicative_expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='OBSERVATION' block=block)
	 */
	protected void sequence_observation_block(EObject context, observation_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.OBSERVATION_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.OBSERVATION_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.OBSERVATION_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.OBSERVATION_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getObservation_blockAccess().getIdentifierOBSERVATIONKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getObservation_blockAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='ODE' block=block)
	 */
	protected void sequence_ode_block(EObject context, ode_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.ODE_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.ODE_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.ODE_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.ODE_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getOde_blockAccess().getIdentifierODEKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getOde_blockAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     arguments=arguments
	 */
	protected void sequence_ode_list(EObject context, ode_list semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.ODE_LIST__ARGUMENTS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.ODE_LIST__ARGUMENTS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getOde_listAccess().getArgumentsArgumentsParserRuleCall_2_0(), semanticObject.getArguments());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='OUTPUT_VARIABLES' block=block)
	 */
	protected void sequence_output_variables_block(EObject context, output_variables_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.OUTPUT_VARIABLES_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.OUTPUT_VARIABLES_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.OUTPUT_VARIABLES_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.OUTPUT_VARIABLES_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getOutput_variables_blockAccess().getIdentifierOUTPUT_VARIABLESKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getOutput_variables_blockAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     expression=expression
	 */
	protected void sequence_par_expression(EObject context, par_expression semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.PAR_EXPRESSION__EXPRESSION) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.PAR_EXPRESSION__EXPRESSION));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getPar_expressionAccess().getExpressionExpressionParserRuleCall_1_0(), semanticObject.getExpression());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (structural_block=structural_block | variability_block=variability_block)
	 */
	protected void sequence_param_obj_block(EObject context, param_obj_block semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=ID blocks+=param_obj_block*)
	 */
	protected void sequence_param_obj(EObject context, param_obj semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='PARAMETERS' block=block)
	 */
	protected void sequence_parameters_block(EObject context, parameters_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.PARAMETERS_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.PARAMETERS_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.PARAMETERS_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.PARAMETERS_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getParameters_blockAccess().getIdentifierPARAMETERSKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getParameters_blockAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (unary_expression+=unary_expression (power_op+=power_op unary_expression+=unary_expression)*)
	 */
	protected void sequence_power_expression(EObject context, power_expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (literal=NUMBER | identifier=variable_name)
	 */
	protected void sequence_primary(EObject context, primary semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     arguments=arguments
	 */
	protected void sequence_random_list(EObject context, random_list semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.RANDOM_LIST__ARGUMENTS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.RANDOM_LIST__ARGUMENTS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getRandom_listAccess().getArgumentsArgumentsParserRuleCall_2_0(), semanticObject.getArguments());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='RANDOM_VARIABLE_DEFINITION' block=block)
	 */
	protected void sequence_random_variable_definition_block(EObject context, random_variable_definition_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.RANDOM_VARIABLE_DEFINITION_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.RANDOM_VARIABLE_DEFINITION_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.RANDOM_VARIABLE_DEFINITION_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.RANDOM_VARIABLE_DEFINITION_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getRandom_variable_definition_blockAccess().getIdentifierRANDOM_VARIABLE_DEFINITIONKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getRandom_variable_definition_blockAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         negation='!'? 
	 *         (boolean=BOOLEAN | (additive_expression+=additive_expression (relational_op+=relational_op additive_expression+=additive_expression)*))
	 *     )
	 */
	protected void sequence_relational_expression(EObject context, relational_expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='RSSCRIPT' block=block)
	 */
	protected void sequence_rsscript_block(EObject context, rsscript_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.RSSCRIPT_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.RSSCRIPT_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.RSSCRIPT_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.RSSCRIPT_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getRsscript_blockAccess().getIdentifierRSSCRIPTKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getRsscript_blockAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (expression+=primary expression+=primary*)
	 */
	protected void sequence_selector(EObject context, selector semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='SIMULATION' block=block)
	 */
	protected void sequence_simulation_block(EObject context, simulation_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.SIMULATION_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.SIMULATION_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.SIMULATION_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.SIMULATION_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getSimulation_blockAccess().getIdentifierSIMULATIONKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getSimulation_blockAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (block=block | (par_expression=par_expression if_statement=block_statement else_statement=block_statement?))
	 */
	protected void sequence_statement(EObject context, statement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='STRUCTURAL' block=block)
	 */
	protected void sequence_structural_block(EObject context, structural_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.STRUCTURAL_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.STRUCTURAL_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.STRUCTURAL_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.STRUCTURAL_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getStructural_blockAccess().getIdentifierSTRUCTURALKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getStructural_blockAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='STRUCTURAL_PARAMETERS' block=block)
	 */
	protected void sequence_structural_parameters_block(EObject context, structural_parameters_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.STRUCTURAL_PARAMETERS_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.STRUCTURAL_PARAMETERS_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.STRUCTURAL_PARAMETERS_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.STRUCTURAL_PARAMETERS_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getStructural_parameters_blockAccess().getIdentifierSTRUCTURAL_PARAMETERSKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getStructural_parameters_blockAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=target_language external_code=EXTERNAL_CODE)
	 */
	protected void sequence_target_block(EObject context, target_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.TARGET_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.TARGET_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.TARGET_BLOCK__EXTERNAL_CODE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.TARGET_BLOCK__EXTERNAL_CODE));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getTarget_blockAccess().getIdentifierTarget_languageParserRuleCall_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getTarget_blockAccess().getExternal_codeEXTERNAL_CODETerminalRuleCall_2_0(), semanticObject.getExternal_code());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (function_declaration=function_declaration | parameters_block=parameters_block | data_block=data_block)
	 */
	protected void sequence_task_obj_block(EObject context, task_obj_block semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=ID blocks+=task_obj_block*)
	 */
	protected void sequence_task_obj(EObject context, task_obj semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=ID blocks+=block_statement*)
	 */
	protected void sequence_tel_obj(EObject context, tel_obj semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((operator=unary_op unary_expression=unary_expression) | par_expression=par_expression | function_call=function_call | primary=primary)
	 */
	protected void sequence_unary_expression(EObject context, unary_expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (blocks+=variability_block_statement*)
	 */
	protected void sequence_variability_block_content(EObject context, variability_block_content semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (block_statement=block_statement | block_block=block_subblock | diag_block=diag_subblock)
	 */
	protected void sequence_variability_block_statement(EObject context, variability_block_statement semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='VARIABILITY' block=variability_block_content)
	 */
	protected void sequence_variability_block(EObject context, variability_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.VARIABILITY_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.VARIABILITY_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.VARIABILITY_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.VARIABILITY_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getVariability_blockAccess().getIdentifierVARIABILITYKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getVariability_blockAccess().getBlockVariability_block_contentParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='VARIABILITY_PARAMETERS' block=block)
	 */
	protected void sequence_variability_parameters_block(EObject context, variability_parameters_block semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.VARIABILITY_PARAMETERS_BLOCK__IDENTIFIER) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.VARIABILITY_PARAMETERS_BLOCK__IDENTIFIER));
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.VARIABILITY_PARAMETERS_BLOCK__BLOCK) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.VARIABILITY_PARAMETERS_BLOCK__BLOCK));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getVariability_parameters_blockAccess().getIdentifierVARIABILITY_PARAMETERSKeyword_0_0(), semanticObject.getIdentifier());
		feeder.accept(grammarAccess.getVariability_parameters_blockAccess().getBlockBlockParserRuleCall_1_0(), semanticObject.getBlock());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     arguments=arguments
	 */
	protected void sequence_variability_subblock(EObject context, variability_subblock semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, MdlPackage.Literals.VARIABILITY_SUBBLOCK__ARGUMENTS) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, MdlPackage.Literals.VARIABILITY_SUBBLOCK__ARGUMENTS));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getVariability_subblockAccess().getArgumentsArgumentsParserRuleCall_1_0(), semanticObject.getArguments());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (identifier=variable_name (expression=any_expression | random_list=random_list)?)
	 */
	protected void sequence_variable_declaration(EObject context, variable_declaration semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier+=ID selector+=selector? (identifier+=ID selector+=selector?)*)
	 */
	protected void sequence_variable_name(EObject context, variable_name semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (identifier='VERBATIM' (block=target_block | external_code=EXTERNAL_CODE))
	 */
	protected void sequence_verbatim_block(EObject context, verbatim_block semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
