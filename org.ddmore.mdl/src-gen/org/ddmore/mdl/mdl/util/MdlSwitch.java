/**
 */
package org.ddmore.mdl.mdl.util;

import org.ddmore.mdl.mdl.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.ddmore.mdl.mdl.MdlPackage
 * @generated
 */
public class MdlSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static MdlPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MdlSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = MdlPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case MdlPackage.MCL:
      {
        mcl mcl = (mcl)theEObject;
        T result = casemcl(mcl);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.MCL_OBJ:
      {
        mcl_obj mcl_obj = (mcl_obj)theEObject;
        T result = casemcl_obj(mcl_obj);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.MODEL_OBJ:
      {
        model_obj model_obj = (model_obj)theEObject;
        T result = casemodel_obj(model_obj);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.PARAM_OBJ:
      {
        param_obj param_obj = (param_obj)theEObject;
        T result = caseparam_obj(param_obj);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.DATA_OBJ:
      {
        data_obj data_obj = (data_obj)theEObject;
        T result = casedata_obj(data_obj);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.TASK_OBJ:
      {
        task_obj task_obj = (task_obj)theEObject;
        T result = casetask_obj(task_obj);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.TEL_OBJ:
      {
        tel_obj tel_obj = (tel_obj)theEObject;
        T result = casetel_obj(tel_obj);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.MODEL_OBJ_BLOCK:
      {
        model_obj_block model_obj_block = (model_obj_block)theEObject;
        T result = casemodel_obj_block(model_obj_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.INDIVIDUAL_MODEL_OBJ_BLOCK:
      {
        individual_model_obj_block individual_model_obj_block = (individual_model_obj_block)theEObject;
        T result = caseindividual_model_obj_block(individual_model_obj_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.MODEL_PREDICTION_OBJ_BLOCK:
      {
        model_prediction_obj_block model_prediction_obj_block = (model_prediction_obj_block)theEObject;
        T result = casemodel_prediction_obj_block(model_prediction_obj_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.RANDOM_VARIABLE_DEFINITION_BLOCK:
      {
        random_variable_definition_block random_variable_definition_block = (random_variable_definition_block)theEObject;
        T result = caserandom_variable_definition_block(random_variable_definition_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.INPUT_VARIABLES_BLOCK:
      {
        input_variables_block input_variables_block = (input_variables_block)theEObject;
        T result = caseinput_variables_block(input_variables_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.STRUCTURAL_PARAMETERS_BLOCK:
      {
        structural_parameters_block structural_parameters_block = (structural_parameters_block)theEObject;
        T result = casestructural_parameters_block(structural_parameters_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.VARIABILITY_PARAMETERS_BLOCK:
      {
        variability_parameters_block variability_parameters_block = (variability_parameters_block)theEObject;
        T result = casevariability_parameters_block(variability_parameters_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.OUTPUT_VARIABLES_BLOCK:
      {
        output_variables_block output_variables_block = (output_variables_block)theEObject;
        T result = caseoutput_variables_block(output_variables_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.GROUP_VARIABLES:
      {
        group_variables group_variables = (group_variables)theEObject;
        T result = casegroup_variables(group_variables);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.OBSERVATION_BLOCK:
      {
        observation_block observation_block = (observation_block)theEObject;
        T result = caseobservation_block(observation_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ESTIMATION_BLOCK:
      {
        estimation_block estimation_block = (estimation_block)theEObject;
        T result = caseestimation_block(estimation_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.SIMULATION_BLOCK:
      {
        simulation_block simulation_block = (simulation_block)theEObject;
        T result = casesimulation_block(simulation_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.PARAM_OBJ_BLOCK:
      {
        param_obj_block param_obj_block = (param_obj_block)theEObject;
        T result = caseparam_obj_block(param_obj_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.STRUCTURAL_BLOCK:
      {
        structural_block structural_block = (structural_block)theEObject;
        T result = casestructural_block(structural_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.VARIABILITY_BLOCK:
      {
        variability_block variability_block = (variability_block)theEObject;
        T result = casevariability_block(variability_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.DATA_OBJ_BLOCK:
      {
        data_obj_block data_obj_block = (data_obj_block)theEObject;
        T result = casedata_obj_block(data_obj_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.HEADER_BLOCK:
      {
        header_block header_block = (header_block)theEObject;
        T result = caseheader_block(header_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.FILE_BLOCK:
      {
        file_block file_block = (file_block)theEObject;
        T result = casefile_block(file_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.TASK_OBJ_BLOCK:
      {
        task_obj_block task_obj_block = (task_obj_block)theEObject;
        T result = casetask_obj_block(task_obj_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.PARAMETERS_BLOCK:
      {
        parameters_block parameters_block = (parameters_block)theEObject;
        T result = caseparameters_block(parameters_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.DATA_BLOCK:
      {
        data_block data_block = (data_block)theEObject;
        T result = casedata_block(data_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.MODEL_BLOCK:
      {
        model_block model_block = (model_block)theEObject;
        T result = casemodel_block(model_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.MODEL_BLOCK_STATEMENT:
      {
        model_block_statement model_block_statement = (model_block_statement)theEObject;
        T result = casemodel_block_statement(model_block_statement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.LIBRARY_BLOCK:
      {
        library_block library_block = (library_block)theEObject;
        T result = caselibrary_block(library_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ODE_BLOCK:
      {
        ode_block ode_block = (ode_block)theEObject;
        T result = caseode_block(ode_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.VARIABILITY_BLOCK_CONTENT:
      {
        variability_block_content variability_block_content = (variability_block_content)theEObject;
        T result = casevariability_block_content(variability_block_content);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT:
      {
        variability_block_statement variability_block_statement = (variability_block_statement)theEObject;
        T result = casevariability_block_statement(variability_block_statement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.BLOCK_SUBBLOCK:
      {
        block_subblock block_subblock = (block_subblock)theEObject;
        T result = caseblock_subblock(block_subblock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.DIAG_SUBBLOCK:
      {
        diag_subblock diag_subblock = (diag_subblock)theEObject;
        T result = casediag_subblock(diag_subblock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.VARIABILITY_SUBBLOCK:
      {
        variability_subblock variability_subblock = (variability_subblock)theEObject;
        T result = casevariability_subblock(variability_subblock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.FILE_BLOCK_CONTENT:
      {
        file_block_content file_block_content = (file_block_content)theEObject;
        T result = casefile_block_content(file_block_content);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.FILE_BLOCK_STATEMENT:
      {
        file_block_statement file_block_statement = (file_block_statement)theEObject;
        T result = casefile_block_statement(file_block_statement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.INLINE_BLOCK:
      {
        inline_block inline_block = (inline_block)theEObject;
        T result = caseinline_block(inline_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.DESIGN_BLOCK:
      {
        design_block design_block = (design_block)theEObject;
        T result = casedesign_block(design_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.RSSCRIPT_BLOCK:
      {
        rsscript_block rsscript_block = (rsscript_block)theEObject;
        T result = casersscript_block(rsscript_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.INLINE_BLOCK_CONTENT:
      {
        inline_block_content inline_block_content = (inline_block_content)theEObject;
        T result = caseinline_block_content(inline_block_content);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.FUNCTION_DECLARATION:
      {
        function_declaration function_declaration = (function_declaration)theEObject;
        T result = casefunction_declaration(function_declaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.FUNCTION_BODY:
      {
        function_body function_body = (function_body)theEObject;
        T result = casefunction_body(function_body);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.FUNCTION_SUBBLOCK:
      {
        function_subblock function_subblock = (function_subblock)theEObject;
        T result = casefunction_subblock(function_subblock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.FORMAL_ARGUMENTS:
      {
        formal_arguments formal_arguments = (formal_arguments)theEObject;
        T result = caseformal_arguments(formal_arguments);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.FUNCTION_CALL:
      {
        function_call function_call = (function_call)theEObject;
        T result = casefunction_call(function_call);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.BLOCK:
      {
        block block = (block)theEObject;
        T result = caseblock(block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.BLOCK_STATEMENT:
      {
        block_statement block_statement = (block_statement)theEObject;
        T result = caseblock_statement(block_statement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.VERBATIM_BLOCK:
      {
        verbatim_block verbatim_block = (verbatim_block)theEObject;
        T result = caseverbatim_block(verbatim_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.TARGET_BLOCK:
      {
        target_block target_block = (target_block)theEObject;
        T result = casetarget_block(target_block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.VARIABLE_DECLARATION:
      {
        variable_declaration variable_declaration = (variable_declaration)theEObject;
        T result = casevariable_declaration(variable_declaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ANY_EXPRESSION:
      {
        any_expression any_expression = (any_expression)theEObject;
        T result = caseany_expression(any_expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.EXPRESSION:
      {
        expression expression = (expression)theEObject;
        T result = caseexpression(expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.LIST:
      {
        list list = (list)theEObject;
        T result = caselist(list);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ODE_LIST:
      {
        ode_list ode_list = (ode_list)theEObject;
        T result = caseode_list(ode_list);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.RANDOM_LIST:
      {
        random_list random_list = (random_list)theEObject;
        T result = caserandom_list(random_list);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ARGUMENTS:
      {
        arguments arguments = (arguments)theEObject;
        T result = casearguments(arguments);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ARGUMENT:
      {
        argument argument = (argument)theEObject;
        T result = caseargument(argument);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.STATEMENT:
      {
        statement statement = (statement)theEObject;
        T result = casestatement(statement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.PAR_EXPRESSION:
      {
        par_expression par_expression = (par_expression)theEObject;
        T result = casepar_expression(par_expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.CONDITIONAL_EXPRESSION:
      {
        conditional_expression conditional_expression = (conditional_expression)theEObject;
        T result = caseconditional_expression(conditional_expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.CONDITIONAL_OR_EXPRESSION:
      {
        conditional_or_expression conditional_or_expression = (conditional_or_expression)theEObject;
        T result = caseconditional_or_expression(conditional_or_expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.CONDITIONAL_AND_EXPRESSION:
      {
        conditional_and_expression conditional_and_expression = (conditional_and_expression)theEObject;
        T result = caseconditional_and_expression(conditional_and_expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.RELATIONAL_EXPRESSION:
      {
        relational_expression relational_expression = (relational_expression)theEObject;
        T result = caserelational_expression(relational_expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ADDITIVE_EXPRESSION:
      {
        additive_expression additive_expression = (additive_expression)theEObject;
        T result = caseadditive_expression(additive_expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.MULTIPLICATIVE_EXPRESSION:
      {
        multiplicative_expression multiplicative_expression = (multiplicative_expression)theEObject;
        T result = casemultiplicative_expression(multiplicative_expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.POWER_EXPRESSION:
      {
        power_expression power_expression = (power_expression)theEObject;
        T result = casepower_expression(power_expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.UNARY_EXPRESSION:
      {
        unary_expression unary_expression = (unary_expression)theEObject;
        T result = caseunary_expression(unary_expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.PRIMARY:
      {
        primary primary = (primary)theEObject;
        T result = caseprimary(primary);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.VARIABLE_NAME:
      {
        variable_name variable_name = (variable_name)theEObject;
        T result = casevariable_name(variable_name);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.SELECTOR:
      {
        selector selector = (selector)theEObject;
        T result = caseselector(selector);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mcl</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mcl</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemcl(mcl object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>mcl obj</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>mcl obj</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemcl_obj(mcl_obj object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>model obj</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>model obj</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemodel_obj(model_obj object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>param obj</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>param obj</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseparam_obj(param_obj object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>data obj</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>data obj</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedata_obj(data_obj object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>task obj</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>task obj</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casetask_obj(task_obj object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>tel obj</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>tel obj</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casetel_obj(tel_obj object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>model obj block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>model obj block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemodel_obj_block(model_obj_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>individual model obj block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>individual model obj block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseindividual_model_obj_block(individual_model_obj_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>model prediction obj block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>model prediction obj block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemodel_prediction_obj_block(model_prediction_obj_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>random variable definition block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>random variable definition block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caserandom_variable_definition_block(random_variable_definition_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>input variables block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>input variables block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseinput_variables_block(input_variables_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>structural parameters block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>structural parameters block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casestructural_parameters_block(structural_parameters_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>variability parameters block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>variability parameters block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casevariability_parameters_block(variability_parameters_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>output variables block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>output variables block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseoutput_variables_block(output_variables_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>group variables</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>group variables</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casegroup_variables(group_variables object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>observation block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>observation block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseobservation_block(observation_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>estimation block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>estimation block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseestimation_block(estimation_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>simulation block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>simulation block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casesimulation_block(simulation_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>param obj block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>param obj block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseparam_obj_block(param_obj_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>structural block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>structural block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casestructural_block(structural_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>variability block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>variability block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casevariability_block(variability_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>data obj block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>data obj block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedata_obj_block(data_obj_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>header block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>header block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseheader_block(header_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>file block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>file block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefile_block(file_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>task obj block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>task obj block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casetask_obj_block(task_obj_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>parameters block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>parameters block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseparameters_block(parameters_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>data block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>data block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedata_block(data_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>model block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>model block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemodel_block(model_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>model block statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>model block statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemodel_block_statement(model_block_statement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>library block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>library block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caselibrary_block(library_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ode block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ode block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseode_block(ode_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>variability block content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>variability block content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casevariability_block_content(variability_block_content object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>variability block statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>variability block statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casevariability_block_statement(variability_block_statement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>block subblock</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>block subblock</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseblock_subblock(block_subblock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>diag subblock</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>diag subblock</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casediag_subblock(diag_subblock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>variability subblock</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>variability subblock</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casevariability_subblock(variability_subblock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>file block content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>file block content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefile_block_content(file_block_content object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>file block statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>file block statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefile_block_statement(file_block_statement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>inline block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>inline block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseinline_block(inline_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>design block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>design block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casedesign_block(design_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>rsscript block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>rsscript block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casersscript_block(rsscript_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>inline block content</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>inline block content</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseinline_block_content(inline_block_content object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>function declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>function declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefunction_declaration(function_declaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>function body</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>function body</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefunction_body(function_body object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>function subblock</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>function subblock</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefunction_subblock(function_subblock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>formal arguments</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>formal arguments</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseformal_arguments(formal_arguments object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>function call</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>function call</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casefunction_call(function_call object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseblock(block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>block statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>block statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseblock_statement(block_statement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>verbatim block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>verbatim block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseverbatim_block(verbatim_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>target block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>target block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casetarget_block(target_block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>variable declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>variable declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casevariable_declaration(variable_declaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>any expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>any expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseany_expression(any_expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseexpression(expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>list</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>list</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caselist(list object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>ode list</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>ode list</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseode_list(ode_list object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>random list</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>random list</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caserandom_list(random_list object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>arguments</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>arguments</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casearguments(arguments object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>argument</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>argument</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseargument(argument object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casestatement(statement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>par expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>par expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casepar_expression(par_expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>conditional expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>conditional expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseconditional_expression(conditional_expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>conditional or expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>conditional or expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseconditional_or_expression(conditional_or_expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>conditional and expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>conditional and expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseconditional_and_expression(conditional_and_expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>relational expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>relational expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caserelational_expression(relational_expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>additive expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>additive expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseadditive_expression(additive_expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>multiplicative expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>multiplicative expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casemultiplicative_expression(multiplicative_expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>power expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>power expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casepower_expression(power_expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>unary expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>unary expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseunary_expression(unary_expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>primary</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>primary</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseprimary(primary object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>variable name</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>variable name</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casevariable_name(variable_name object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>selector</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>selector</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseselector(selector object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //MdlSwitch
