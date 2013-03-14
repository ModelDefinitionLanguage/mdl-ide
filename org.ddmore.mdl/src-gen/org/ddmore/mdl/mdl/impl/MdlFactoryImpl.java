/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MdlFactoryImpl extends EFactoryImpl implements MdlFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static MdlFactory init()
  {
    try
    {
      MdlFactory theMdlFactory = (MdlFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.ddmore.org/mdl/Mdl"); 
      if (theMdlFactory != null)
      {
        return theMdlFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new MdlFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MdlFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case MdlPackage.MCL: return createmcl();
      case MdlPackage.MCL_OBJ: return createmcl_obj();
      case MdlPackage.MODEL_OBJ: return createmodel_obj();
      case MdlPackage.PARAM_OBJ: return createparam_obj();
      case MdlPackage.DATA_OBJ: return createdata_obj();
      case MdlPackage.TASK_OBJ: return createtask_obj();
      case MdlPackage.TEL_OBJ: return createtel_obj();
      case MdlPackage.MODEL_OBJ_BLOCK: return createmodel_obj_block();
      case MdlPackage.INDIVIDUAL_MODEL_OBJ_BLOCK: return createindividual_model_obj_block();
      case MdlPackage.MODEL_PREDICTION_OBJ_BLOCK: return createmodel_prediction_obj_block();
      case MdlPackage.RANDOM_VARIABLE_DEFINITION_BLOCK: return createrandom_variable_definition_block();
      case MdlPackage.INPUT_VARIABLES_BLOCK: return createinput_variables_block();
      case MdlPackage.STRUCTURAL_PARAMETERS_BLOCK: return createstructural_parameters_block();
      case MdlPackage.VARIABILITY_PARAMETERS_BLOCK: return createvariability_parameters_block();
      case MdlPackage.OUTPUT_VARIABLES_BLOCK: return createoutput_variables_block();
      case MdlPackage.GROUP_VARIABLES: return creategroup_variables();
      case MdlPackage.OBSERVATION_BLOCK: return createobservation_block();
      case MdlPackage.ESTIMATION_BLOCK: return createestimation_block();
      case MdlPackage.SIMULATION_BLOCK: return createsimulation_block();
      case MdlPackage.PARAM_OBJ_BLOCK: return createparam_obj_block();
      case MdlPackage.STRUCTURAL_BLOCK: return createstructural_block();
      case MdlPackage.VARIABILITY_BLOCK: return createvariability_block();
      case MdlPackage.DATA_OBJ_BLOCK: return createdata_obj_block();
      case MdlPackage.HEADER_BLOCK: return createheader_block();
      case MdlPackage.FILE_BLOCK: return createfile_block();
      case MdlPackage.TASK_OBJ_BLOCK: return createtask_obj_block();
      case MdlPackage.PARAMETERS_BLOCK: return createparameters_block();
      case MdlPackage.DATA_BLOCK: return createdata_block();
      case MdlPackage.MODEL_BLOCK: return createmodel_block();
      case MdlPackage.MODEL_BLOCK_STATEMENT: return createmodel_block_statement();
      case MdlPackage.LIBRARY_BLOCK: return createlibrary_block();
      case MdlPackage.ODE_BLOCK: return createode_block();
      case MdlPackage.VARIABILITY_BLOCK_CONTENT: return createvariability_block_content();
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT: return createvariability_block_statement();
      case MdlPackage.BLOCK_SUBBLOCK: return createblock_subblock();
      case MdlPackage.DIAG_SUBBLOCK: return creatediag_subblock();
      case MdlPackage.VARIABILITY_SUBBLOCK: return createvariability_subblock();
      case MdlPackage.FILE_BLOCK_CONTENT: return createfile_block_content();
      case MdlPackage.FILE_BLOCK_STATEMENT: return createfile_block_statement();
      case MdlPackage.INLINE_BLOCK: return createinline_block();
      case MdlPackage.DESIGN_BLOCK: return createdesign_block();
      case MdlPackage.RSSCRIPT_BLOCK: return creatersscript_block();
      case MdlPackage.INLINE_BLOCK_CONTENT: return createinline_block_content();
      case MdlPackage.FUNCTION_DECLARATION: return createfunction_declaration();
      case MdlPackage.FUNCTION_BODY: return createfunction_body();
      case MdlPackage.FUNCTION_SUBBLOCK: return createfunction_subblock();
      case MdlPackage.FORMAL_ARGUMENTS: return createformal_arguments();
      case MdlPackage.FUNCTION_CALL: return createfunction_call();
      case MdlPackage.BLOCK: return createblock();
      case MdlPackage.BLOCK_STATEMENT: return createblock_statement();
      case MdlPackage.VERBATIM_BLOCK: return createverbatim_block();
      case MdlPackage.TARGET_BLOCK: return createtarget_block();
      case MdlPackage.VARIABLE_DECLARATION: return createvariable_declaration();
      case MdlPackage.ANY_EXPRESSION: return createany_expression();
      case MdlPackage.EXPRESSION: return createexpression();
      case MdlPackage.LIST: return createlist();
      case MdlPackage.ODE_LIST: return createode_list();
      case MdlPackage.RANDOM_LIST: return createrandom_list();
      case MdlPackage.ARGUMENTS: return createarguments();
      case MdlPackage.ARGUMENT: return createargument();
      case MdlPackage.STATEMENT: return createstatement();
      case MdlPackage.PAR_EXPRESSION: return createpar_expression();
      case MdlPackage.CONDITIONAL_EXPRESSION: return createconditional_expression();
      case MdlPackage.CONDITIONAL_OR_EXPRESSION: return createconditional_or_expression();
      case MdlPackage.CONDITIONAL_AND_EXPRESSION: return createconditional_and_expression();
      case MdlPackage.RELATIONAL_EXPRESSION: return createrelational_expression();
      case MdlPackage.ADDITIVE_EXPRESSION: return createadditive_expression();
      case MdlPackage.MULTIPLICATIVE_EXPRESSION: return createmultiplicative_expression();
      case MdlPackage.POWER_EXPRESSION: return createpower_expression();
      case MdlPackage.UNARY_EXPRESSION: return createunary_expression();
      case MdlPackage.PRIMARY: return createprimary();
      case MdlPackage.VARIABLE_NAME: return createvariable_name();
      case MdlPackage.SELECTOR: return createselector();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mcl createmcl()
  {
    mclImpl mcl = new mclImpl();
    return mcl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public mcl_obj createmcl_obj()
  {
    mcl_objImpl mcl_obj = new mcl_objImpl();
    return mcl_obj;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public model_obj createmodel_obj()
  {
    model_objImpl model_obj = new model_objImpl();
    return model_obj;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public param_obj createparam_obj()
  {
    param_objImpl param_obj = new param_objImpl();
    return param_obj;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public data_obj createdata_obj()
  {
    data_objImpl data_obj = new data_objImpl();
    return data_obj;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public task_obj createtask_obj()
  {
    task_objImpl task_obj = new task_objImpl();
    return task_obj;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public tel_obj createtel_obj()
  {
    tel_objImpl tel_obj = new tel_objImpl();
    return tel_obj;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public model_obj_block createmodel_obj_block()
  {
    model_obj_blockImpl model_obj_block = new model_obj_blockImpl();
    return model_obj_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public individual_model_obj_block createindividual_model_obj_block()
  {
    individual_model_obj_blockImpl individual_model_obj_block = new individual_model_obj_blockImpl();
    return individual_model_obj_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public model_prediction_obj_block createmodel_prediction_obj_block()
  {
    model_prediction_obj_blockImpl model_prediction_obj_block = new model_prediction_obj_blockImpl();
    return model_prediction_obj_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public random_variable_definition_block createrandom_variable_definition_block()
  {
    random_variable_definition_blockImpl random_variable_definition_block = new random_variable_definition_blockImpl();
    return random_variable_definition_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public input_variables_block createinput_variables_block()
  {
    input_variables_blockImpl input_variables_block = new input_variables_blockImpl();
    return input_variables_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public structural_parameters_block createstructural_parameters_block()
  {
    structural_parameters_blockImpl structural_parameters_block = new structural_parameters_blockImpl();
    return structural_parameters_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public variability_parameters_block createvariability_parameters_block()
  {
    variability_parameters_blockImpl variability_parameters_block = new variability_parameters_blockImpl();
    return variability_parameters_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public output_variables_block createoutput_variables_block()
  {
    output_variables_blockImpl output_variables_block = new output_variables_blockImpl();
    return output_variables_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public group_variables creategroup_variables()
  {
    group_variablesImpl group_variables = new group_variablesImpl();
    return group_variables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public observation_block createobservation_block()
  {
    observation_blockImpl observation_block = new observation_blockImpl();
    return observation_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public estimation_block createestimation_block()
  {
    estimation_blockImpl estimation_block = new estimation_blockImpl();
    return estimation_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public simulation_block createsimulation_block()
  {
    simulation_blockImpl simulation_block = new simulation_blockImpl();
    return simulation_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public param_obj_block createparam_obj_block()
  {
    param_obj_blockImpl param_obj_block = new param_obj_blockImpl();
    return param_obj_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public structural_block createstructural_block()
  {
    structural_blockImpl structural_block = new structural_blockImpl();
    return structural_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public variability_block createvariability_block()
  {
    variability_blockImpl variability_block = new variability_blockImpl();
    return variability_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public data_obj_block createdata_obj_block()
  {
    data_obj_blockImpl data_obj_block = new data_obj_blockImpl();
    return data_obj_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public header_block createheader_block()
  {
    header_blockImpl header_block = new header_blockImpl();
    return header_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public file_block createfile_block()
  {
    file_blockImpl file_block = new file_blockImpl();
    return file_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public task_obj_block createtask_obj_block()
  {
    task_obj_blockImpl task_obj_block = new task_obj_blockImpl();
    return task_obj_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public parameters_block createparameters_block()
  {
    parameters_blockImpl parameters_block = new parameters_blockImpl();
    return parameters_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public data_block createdata_block()
  {
    data_blockImpl data_block = new data_blockImpl();
    return data_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public model_block createmodel_block()
  {
    model_blockImpl model_block = new model_blockImpl();
    return model_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public model_block_statement createmodel_block_statement()
  {
    model_block_statementImpl model_block_statement = new model_block_statementImpl();
    return model_block_statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public library_block createlibrary_block()
  {
    library_blockImpl library_block = new library_blockImpl();
    return library_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ode_block createode_block()
  {
    ode_blockImpl ode_block = new ode_blockImpl();
    return ode_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public variability_block_content createvariability_block_content()
  {
    variability_block_contentImpl variability_block_content = new variability_block_contentImpl();
    return variability_block_content;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public variability_block_statement createvariability_block_statement()
  {
    variability_block_statementImpl variability_block_statement = new variability_block_statementImpl();
    return variability_block_statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public block_subblock createblock_subblock()
  {
    block_subblockImpl block_subblock = new block_subblockImpl();
    return block_subblock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public diag_subblock creatediag_subblock()
  {
    diag_subblockImpl diag_subblock = new diag_subblockImpl();
    return diag_subblock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public variability_subblock createvariability_subblock()
  {
    variability_subblockImpl variability_subblock = new variability_subblockImpl();
    return variability_subblock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public file_block_content createfile_block_content()
  {
    file_block_contentImpl file_block_content = new file_block_contentImpl();
    return file_block_content;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public file_block_statement createfile_block_statement()
  {
    file_block_statementImpl file_block_statement = new file_block_statementImpl();
    return file_block_statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public inline_block createinline_block()
  {
    inline_blockImpl inline_block = new inline_blockImpl();
    return inline_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_block createdesign_block()
  {
    design_blockImpl design_block = new design_blockImpl();
    return design_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public rsscript_block creatersscript_block()
  {
    rsscript_blockImpl rsscript_block = new rsscript_blockImpl();
    return rsscript_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public inline_block_content createinline_block_content()
  {
    inline_block_contentImpl inline_block_content = new inline_block_contentImpl();
    return inline_block_content;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public function_declaration createfunction_declaration()
  {
    function_declarationImpl function_declaration = new function_declarationImpl();
    return function_declaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public function_body createfunction_body()
  {
    function_bodyImpl function_body = new function_bodyImpl();
    return function_body;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public function_subblock createfunction_subblock()
  {
    function_subblockImpl function_subblock = new function_subblockImpl();
    return function_subblock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public formal_arguments createformal_arguments()
  {
    formal_argumentsImpl formal_arguments = new formal_argumentsImpl();
    return formal_arguments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public function_call createfunction_call()
  {
    function_callImpl function_call = new function_callImpl();
    return function_call;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public block createblock()
  {
    blockImpl block = new blockImpl();
    return block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public block_statement createblock_statement()
  {
    block_statementImpl block_statement = new block_statementImpl();
    return block_statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public verbatim_block createverbatim_block()
  {
    verbatim_blockImpl verbatim_block = new verbatim_blockImpl();
    return verbatim_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public target_block createtarget_block()
  {
    target_blockImpl target_block = new target_blockImpl();
    return target_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public variable_declaration createvariable_declaration()
  {
    variable_declarationImpl variable_declaration = new variable_declarationImpl();
    return variable_declaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public any_expression createany_expression()
  {
    any_expressionImpl any_expression = new any_expressionImpl();
    return any_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression createexpression()
  {
    expressionImpl expression = new expressionImpl();
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public list createlist()
  {
    listImpl list = new listImpl();
    return list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ode_list createode_list()
  {
    ode_listImpl ode_list = new ode_listImpl();
    return ode_list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public random_list createrandom_list()
  {
    random_listImpl random_list = new random_listImpl();
    return random_list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public arguments createarguments()
  {
    argumentsImpl arguments = new argumentsImpl();
    return arguments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public argument createargument()
  {
    argumentImpl argument = new argumentImpl();
    return argument;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public statement createstatement()
  {
    statementImpl statement = new statementImpl();
    return statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public par_expression createpar_expression()
  {
    par_expressionImpl par_expression = new par_expressionImpl();
    return par_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public conditional_expression createconditional_expression()
  {
    conditional_expressionImpl conditional_expression = new conditional_expressionImpl();
    return conditional_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public conditional_or_expression createconditional_or_expression()
  {
    conditional_or_expressionImpl conditional_or_expression = new conditional_or_expressionImpl();
    return conditional_or_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public conditional_and_expression createconditional_and_expression()
  {
    conditional_and_expressionImpl conditional_and_expression = new conditional_and_expressionImpl();
    return conditional_and_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public relational_expression createrelational_expression()
  {
    relational_expressionImpl relational_expression = new relational_expressionImpl();
    return relational_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public additive_expression createadditive_expression()
  {
    additive_expressionImpl additive_expression = new additive_expressionImpl();
    return additive_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public multiplicative_expression createmultiplicative_expression()
  {
    multiplicative_expressionImpl multiplicative_expression = new multiplicative_expressionImpl();
    return multiplicative_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public power_expression createpower_expression()
  {
    power_expressionImpl power_expression = new power_expressionImpl();
    return power_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public unary_expression createunary_expression()
  {
    unary_expressionImpl unary_expression = new unary_expressionImpl();
    return unary_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public primary createprimary()
  {
    primaryImpl primary = new primaryImpl();
    return primary;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public variable_name createvariable_name()
  {
    variable_nameImpl variable_name = new variable_nameImpl();
    return variable_name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public selector createselector()
  {
    selectorImpl selector = new selectorImpl();
    return selector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MdlPackage getMdlPackage()
  {
    return (MdlPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static MdlPackage getPackage()
  {
    return MdlPackage.eINSTANCE;
  }

} //MdlFactoryImpl
