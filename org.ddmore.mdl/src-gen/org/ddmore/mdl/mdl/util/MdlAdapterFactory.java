/**
 */
package org.ddmore.mdl.mdl.util;

import org.ddmore.mdl.mdl.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.ddmore.mdl.mdl.MdlPackage
 * @generated
 */
public class MdlAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static MdlPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MdlAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = MdlPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MdlSwitch<Adapter> modelSwitch =
    new MdlSwitch<Adapter>()
    {
      @Override
      public Adapter casemcl(mcl object)
      {
        return createmclAdapter();
      }
      @Override
      public Adapter casemcl_obj(mcl_obj object)
      {
        return createmcl_objAdapter();
      }
      @Override
      public Adapter casemodel_obj(model_obj object)
      {
        return createmodel_objAdapter();
      }
      @Override
      public Adapter caseparam_obj(param_obj object)
      {
        return createparam_objAdapter();
      }
      @Override
      public Adapter casedata_obj(data_obj object)
      {
        return createdata_objAdapter();
      }
      @Override
      public Adapter casetask_obj(task_obj object)
      {
        return createtask_objAdapter();
      }
      @Override
      public Adapter casetel_obj(tel_obj object)
      {
        return createtel_objAdapter();
      }
      @Override
      public Adapter casemodel_obj_block(model_obj_block object)
      {
        return createmodel_obj_blockAdapter();
      }
      @Override
      public Adapter caseindividual_model_obj_block(individual_model_obj_block object)
      {
        return createindividual_model_obj_blockAdapter();
      }
      @Override
      public Adapter casemodel_prediction_obj_block(model_prediction_obj_block object)
      {
        return createmodel_prediction_obj_blockAdapter();
      }
      @Override
      public Adapter caserandom_variable_definition_block(random_variable_definition_block object)
      {
        return createrandom_variable_definition_blockAdapter();
      }
      @Override
      public Adapter caseinput_variables_block(input_variables_block object)
      {
        return createinput_variables_blockAdapter();
      }
      @Override
      public Adapter casestructural_parameters_block(structural_parameters_block object)
      {
        return createstructural_parameters_blockAdapter();
      }
      @Override
      public Adapter casevariability_parameters_block(variability_parameters_block object)
      {
        return createvariability_parameters_blockAdapter();
      }
      @Override
      public Adapter caseoutput_variables_block(output_variables_block object)
      {
        return createoutput_variables_blockAdapter();
      }
      @Override
      public Adapter casegroup_variables(group_variables object)
      {
        return creategroup_variablesAdapter();
      }
      @Override
      public Adapter caseobservation_block(observation_block object)
      {
        return createobservation_blockAdapter();
      }
      @Override
      public Adapter caseestimation_block(estimation_block object)
      {
        return createestimation_blockAdapter();
      }
      @Override
      public Adapter casesimulation_block(simulation_block object)
      {
        return createsimulation_blockAdapter();
      }
      @Override
      public Adapter caseparam_obj_block(param_obj_block object)
      {
        return createparam_obj_blockAdapter();
      }
      @Override
      public Adapter casestructural_block(structural_block object)
      {
        return createstructural_blockAdapter();
      }
      @Override
      public Adapter casevariability_block(variability_block object)
      {
        return createvariability_blockAdapter();
      }
      @Override
      public Adapter casedata_obj_block(data_obj_block object)
      {
        return createdata_obj_blockAdapter();
      }
      @Override
      public Adapter caseheader_block(header_block object)
      {
        return createheader_blockAdapter();
      }
      @Override
      public Adapter casefile_block(file_block object)
      {
        return createfile_blockAdapter();
      }
      @Override
      public Adapter casetask_obj_block(task_obj_block object)
      {
        return createtask_obj_blockAdapter();
      }
      @Override
      public Adapter caseparameters_block(parameters_block object)
      {
        return createparameters_blockAdapter();
      }
      @Override
      public Adapter casedata_block(data_block object)
      {
        return createdata_blockAdapter();
      }
      @Override
      public Adapter casemodel_block(model_block object)
      {
        return createmodel_blockAdapter();
      }
      @Override
      public Adapter casemodel_block_statement(model_block_statement object)
      {
        return createmodel_block_statementAdapter();
      }
      @Override
      public Adapter caselibrary_block(library_block object)
      {
        return createlibrary_blockAdapter();
      }
      @Override
      public Adapter caseode_block(ode_block object)
      {
        return createode_blockAdapter();
      }
      @Override
      public Adapter casevariability_block_content(variability_block_content object)
      {
        return createvariability_block_contentAdapter();
      }
      @Override
      public Adapter casevariability_block_statement(variability_block_statement object)
      {
        return createvariability_block_statementAdapter();
      }
      @Override
      public Adapter caseblock_subblock(block_subblock object)
      {
        return createblock_subblockAdapter();
      }
      @Override
      public Adapter casediag_subblock(diag_subblock object)
      {
        return creatediag_subblockAdapter();
      }
      @Override
      public Adapter casevariability_subblock(variability_subblock object)
      {
        return createvariability_subblockAdapter();
      }
      @Override
      public Adapter casefile_block_content(file_block_content object)
      {
        return createfile_block_contentAdapter();
      }
      @Override
      public Adapter casefile_block_statement(file_block_statement object)
      {
        return createfile_block_statementAdapter();
      }
      @Override
      public Adapter caseinline_block(inline_block object)
      {
        return createinline_blockAdapter();
      }
      @Override
      public Adapter casedesign_block(design_block object)
      {
        return createdesign_blockAdapter();
      }
      @Override
      public Adapter casersscript_block(rsscript_block object)
      {
        return creatersscript_blockAdapter();
      }
      @Override
      public Adapter caseinline_block_content(inline_block_content object)
      {
        return createinline_block_contentAdapter();
      }
      @Override
      public Adapter casefunction_declaration(function_declaration object)
      {
        return createfunction_declarationAdapter();
      }
      @Override
      public Adapter casefunction_body(function_body object)
      {
        return createfunction_bodyAdapter();
      }
      @Override
      public Adapter casefunction_subblock(function_subblock object)
      {
        return createfunction_subblockAdapter();
      }
      @Override
      public Adapter caseformal_arguments(formal_arguments object)
      {
        return createformal_argumentsAdapter();
      }
      @Override
      public Adapter casefunction_call(function_call object)
      {
        return createfunction_callAdapter();
      }
      @Override
      public Adapter caseblock(block object)
      {
        return createblockAdapter();
      }
      @Override
      public Adapter caseblock_statement(block_statement object)
      {
        return createblock_statementAdapter();
      }
      @Override
      public Adapter caseverbatim_block(verbatim_block object)
      {
        return createverbatim_blockAdapter();
      }
      @Override
      public Adapter casetarget_block(target_block object)
      {
        return createtarget_blockAdapter();
      }
      @Override
      public Adapter casevariable_declaration(variable_declaration object)
      {
        return createvariable_declarationAdapter();
      }
      @Override
      public Adapter caseany_expression(any_expression object)
      {
        return createany_expressionAdapter();
      }
      @Override
      public Adapter caseexpression(expression object)
      {
        return createexpressionAdapter();
      }
      @Override
      public Adapter caselist(list object)
      {
        return createlistAdapter();
      }
      @Override
      public Adapter caseode_list(ode_list object)
      {
        return createode_listAdapter();
      }
      @Override
      public Adapter caserandom_list(random_list object)
      {
        return createrandom_listAdapter();
      }
      @Override
      public Adapter casearguments(arguments object)
      {
        return createargumentsAdapter();
      }
      @Override
      public Adapter caseargument(argument object)
      {
        return createargumentAdapter();
      }
      @Override
      public Adapter casestatement(statement object)
      {
        return createstatementAdapter();
      }
      @Override
      public Adapter casepar_expression(par_expression object)
      {
        return createpar_expressionAdapter();
      }
      @Override
      public Adapter caseconditional_expression(conditional_expression object)
      {
        return createconditional_expressionAdapter();
      }
      @Override
      public Adapter caseconditional_or_expression(conditional_or_expression object)
      {
        return createconditional_or_expressionAdapter();
      }
      @Override
      public Adapter caseconditional_and_expression(conditional_and_expression object)
      {
        return createconditional_and_expressionAdapter();
      }
      @Override
      public Adapter caserelational_expression(relational_expression object)
      {
        return createrelational_expressionAdapter();
      }
      @Override
      public Adapter caseadditive_expression(additive_expression object)
      {
        return createadditive_expressionAdapter();
      }
      @Override
      public Adapter casemultiplicative_expression(multiplicative_expression object)
      {
        return createmultiplicative_expressionAdapter();
      }
      @Override
      public Adapter casepower_expression(power_expression object)
      {
        return createpower_expressionAdapter();
      }
      @Override
      public Adapter caseunary_expression(unary_expression object)
      {
        return createunary_expressionAdapter();
      }
      @Override
      public Adapter caseprimary(primary object)
      {
        return createprimaryAdapter();
      }
      @Override
      public Adapter casevariable_name(variable_name object)
      {
        return createvariable_nameAdapter();
      }
      @Override
      public Adapter caseselector(selector object)
      {
        return createselectorAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.mcl <em>mcl</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.mcl
   * @generated
   */
  public Adapter createmclAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.mcl_obj <em>mcl obj</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.mcl_obj
   * @generated
   */
  public Adapter createmcl_objAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.model_obj <em>model obj</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.model_obj
   * @generated
   */
  public Adapter createmodel_objAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.param_obj <em>param obj</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.param_obj
   * @generated
   */
  public Adapter createparam_objAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.data_obj <em>data obj</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.data_obj
   * @generated
   */
  public Adapter createdata_objAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.task_obj <em>task obj</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.task_obj
   * @generated
   */
  public Adapter createtask_objAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.tel_obj <em>tel obj</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.tel_obj
   * @generated
   */
  public Adapter createtel_objAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.model_obj_block <em>model obj block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.model_obj_block
   * @generated
   */
  public Adapter createmodel_obj_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.individual_model_obj_block <em>individual model obj block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.individual_model_obj_block
   * @generated
   */
  public Adapter createindividual_model_obj_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.model_prediction_obj_block <em>model prediction obj block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.model_prediction_obj_block
   * @generated
   */
  public Adapter createmodel_prediction_obj_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.random_variable_definition_block <em>random variable definition block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.random_variable_definition_block
   * @generated
   */
  public Adapter createrandom_variable_definition_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.input_variables_block <em>input variables block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.input_variables_block
   * @generated
   */
  public Adapter createinput_variables_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.structural_parameters_block <em>structural parameters block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.structural_parameters_block
   * @generated
   */
  public Adapter createstructural_parameters_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.variability_parameters_block <em>variability parameters block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.variability_parameters_block
   * @generated
   */
  public Adapter createvariability_parameters_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.output_variables_block <em>output variables block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.output_variables_block
   * @generated
   */
  public Adapter createoutput_variables_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.group_variables <em>group variables</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.group_variables
   * @generated
   */
  public Adapter creategroup_variablesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.observation_block <em>observation block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.observation_block
   * @generated
   */
  public Adapter createobservation_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.estimation_block <em>estimation block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.estimation_block
   * @generated
   */
  public Adapter createestimation_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.simulation_block <em>simulation block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.simulation_block
   * @generated
   */
  public Adapter createsimulation_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.param_obj_block <em>param obj block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.param_obj_block
   * @generated
   */
  public Adapter createparam_obj_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.structural_block <em>structural block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.structural_block
   * @generated
   */
  public Adapter createstructural_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.variability_block <em>variability block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.variability_block
   * @generated
   */
  public Adapter createvariability_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.data_obj_block <em>data obj block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.data_obj_block
   * @generated
   */
  public Adapter createdata_obj_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.header_block <em>header block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.header_block
   * @generated
   */
  public Adapter createheader_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.file_block <em>file block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.file_block
   * @generated
   */
  public Adapter createfile_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.task_obj_block <em>task obj block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.task_obj_block
   * @generated
   */
  public Adapter createtask_obj_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.parameters_block <em>parameters block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.parameters_block
   * @generated
   */
  public Adapter createparameters_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.data_block <em>data block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.data_block
   * @generated
   */
  public Adapter createdata_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.model_block <em>model block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.model_block
   * @generated
   */
  public Adapter createmodel_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.model_block_statement <em>model block statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.model_block_statement
   * @generated
   */
  public Adapter createmodel_block_statementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.library_block <em>library block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.library_block
   * @generated
   */
  public Adapter createlibrary_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ode_block <em>ode block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ode_block
   * @generated
   */
  public Adapter createode_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.variability_block_content <em>variability block content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.variability_block_content
   * @generated
   */
  public Adapter createvariability_block_contentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.variability_block_statement <em>variability block statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.variability_block_statement
   * @generated
   */
  public Adapter createvariability_block_statementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.block_subblock <em>block subblock</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.block_subblock
   * @generated
   */
  public Adapter createblock_subblockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.diag_subblock <em>diag subblock</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.diag_subblock
   * @generated
   */
  public Adapter creatediag_subblockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.variability_subblock <em>variability subblock</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.variability_subblock
   * @generated
   */
  public Adapter createvariability_subblockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.file_block_content <em>file block content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.file_block_content
   * @generated
   */
  public Adapter createfile_block_contentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.file_block_statement <em>file block statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.file_block_statement
   * @generated
   */
  public Adapter createfile_block_statementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.inline_block <em>inline block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.inline_block
   * @generated
   */
  public Adapter createinline_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.design_block <em>design block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.design_block
   * @generated
   */
  public Adapter createdesign_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.rsscript_block <em>rsscript block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.rsscript_block
   * @generated
   */
  public Adapter creatersscript_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.inline_block_content <em>inline block content</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.inline_block_content
   * @generated
   */
  public Adapter createinline_block_contentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.function_declaration <em>function declaration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.function_declaration
   * @generated
   */
  public Adapter createfunction_declarationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.function_body <em>function body</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.function_body
   * @generated
   */
  public Adapter createfunction_bodyAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.function_subblock <em>function subblock</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.function_subblock
   * @generated
   */
  public Adapter createfunction_subblockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.formal_arguments <em>formal arguments</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.formal_arguments
   * @generated
   */
  public Adapter createformal_argumentsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.function_call <em>function call</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.function_call
   * @generated
   */
  public Adapter createfunction_callAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.block <em>block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.block
   * @generated
   */
  public Adapter createblockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.block_statement <em>block statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.block_statement
   * @generated
   */
  public Adapter createblock_statementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.verbatim_block <em>verbatim block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.verbatim_block
   * @generated
   */
  public Adapter createverbatim_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.target_block <em>target block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.target_block
   * @generated
   */
  public Adapter createtarget_blockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.variable_declaration <em>variable declaration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.variable_declaration
   * @generated
   */
  public Adapter createvariable_declarationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.any_expression <em>any expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.any_expression
   * @generated
   */
  public Adapter createany_expressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.expression <em>expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.expression
   * @generated
   */
  public Adapter createexpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.list <em>list</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.list
   * @generated
   */
  public Adapter createlistAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ode_list <em>ode list</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ode_list
   * @generated
   */
  public Adapter createode_listAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.random_list <em>random list</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.random_list
   * @generated
   */
  public Adapter createrandom_listAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.arguments <em>arguments</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.arguments
   * @generated
   */
  public Adapter createargumentsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.argument <em>argument</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.argument
   * @generated
   */
  public Adapter createargumentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.statement <em>statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.statement
   * @generated
   */
  public Adapter createstatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.par_expression <em>par expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.par_expression
   * @generated
   */
  public Adapter createpar_expressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.conditional_expression <em>conditional expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.conditional_expression
   * @generated
   */
  public Adapter createconditional_expressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.conditional_or_expression <em>conditional or expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.conditional_or_expression
   * @generated
   */
  public Adapter createconditional_or_expressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.conditional_and_expression <em>conditional and expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.conditional_and_expression
   * @generated
   */
  public Adapter createconditional_and_expressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.relational_expression <em>relational expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.relational_expression
   * @generated
   */
  public Adapter createrelational_expressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.additive_expression <em>additive expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.additive_expression
   * @generated
   */
  public Adapter createadditive_expressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.multiplicative_expression <em>multiplicative expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.multiplicative_expression
   * @generated
   */
  public Adapter createmultiplicative_expressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.power_expression <em>power expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.power_expression
   * @generated
   */
  public Adapter createpower_expressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.unary_expression <em>unary expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.unary_expression
   * @generated
   */
  public Adapter createunary_expressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.primary <em>primary</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.primary
   * @generated
   */
  public Adapter createprimaryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.variable_name <em>variable name</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.variable_name
   * @generated
   */
  public Adapter createvariable_nameAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.selector <em>selector</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.selector
   * @generated
   */
  public Adapter createselectorAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //MdlAdapterFactory
