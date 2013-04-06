/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlFactory;
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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MdlPackageImpl extends EPackageImpl implements MdlPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mclEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mcl_objEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass model_objEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass param_objEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass data_objEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass task_objEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass tel_objEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass model_obj_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass individual_model_obj_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass model_prediction_obj_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass random_variable_definition_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass input_variables_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass structural_parameters_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variability_parameters_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass output_variables_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass group_variablesEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass observation_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass estimation_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass simulation_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass param_obj_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass structural_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variability_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass data_obj_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass header_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass file_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass task_obj_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parameters_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass data_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass model_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass model_block_statementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass library_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ode_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variability_block_contentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variability_block_statementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass block_subblockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass diag_subblockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variability_subblockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass file_block_contentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass file_block_statementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass inline_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass design_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass rsscript_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass inline_block_contentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass function_declarationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass function_bodyEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass function_subblockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass formal_argumentsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass function_callEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass block_statementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass verbatim_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass target_blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variable_declarationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass any_expressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass expressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass listEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ode_listEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass random_listEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass argumentsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass argumentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass statementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass par_expressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass conditional_expressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass conditional_or_expressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass conditional_and_expressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass relational_expressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass additive_expressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass multiplicative_expressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass power_expressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass unary_expressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass primaryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variable_nameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass selectorEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.ddmore.mdl.mdl.MdlPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private MdlPackageImpl()
  {
    super(eNS_URI, MdlFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link MdlPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static MdlPackage init()
  {
    if (isInited) return (MdlPackage)EPackage.Registry.INSTANCE.getEPackage(MdlPackage.eNS_URI);

    // Obtain or create and register package
    MdlPackageImpl theMdlPackage = (MdlPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MdlPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MdlPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theMdlPackage.createPackageContents();

    // Initialize created meta-data
    theMdlPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theMdlPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(MdlPackage.eNS_URI, theMdlPackage);
    return theMdlPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmcl()
  {
    return mclEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmcl_Objects()
  {
    return (EReference)mclEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmcl_obj()
  {
    return mcl_objEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmcl_obj_Model_obj()
  {
    return (EReference)mcl_objEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmcl_obj_Param_obj()
  {
    return (EReference)mcl_objEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmcl_obj_Data_obj()
  {
    return (EReference)mcl_objEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmcl_obj_Task_obj()
  {
    return (EReference)mcl_objEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmcl_obj_Tel_obj()
  {
    return (EReference)mcl_objEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmodel_obj()
  {
    return model_objEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmodel_obj_Identifier()
  {
    return (EAttribute)model_objEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodel_obj_Blocks()
  {
    return (EReference)model_objEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getparam_obj()
  {
    return param_objEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getparam_obj_Identifier()
  {
    return (EAttribute)param_objEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getparam_obj_Blocks()
  {
    return (EReference)param_objEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdata_obj()
  {
    return data_objEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdata_obj_Identifier()
  {
    return (EAttribute)data_objEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdata_obj_Blocks()
  {
    return (EReference)data_objEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass gettask_obj()
  {
    return task_objEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute gettask_obj_Identifier()
  {
    return (EAttribute)task_objEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettask_obj_Blocks()
  {
    return (EReference)task_objEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass gettel_obj()
  {
    return tel_objEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute gettel_obj_Identifier()
  {
    return (EAttribute)tel_objEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettel_obj_Blocks()
  {
    return (EReference)tel_objEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmodel_obj_block()
  {
    return model_obj_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodel_obj_block_Individual_model_obj_block()
  {
    return (EReference)model_obj_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodel_obj_block_Model_prediction_obj_block()
  {
    return (EReference)model_obj_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodel_obj_block_Random_variable_definition_block()
  {
    return (EReference)model_obj_blockEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodel_obj_block_Input_variables_block()
  {
    return (EReference)model_obj_blockEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodel_obj_block_Structural_parameters_block()
  {
    return (EReference)model_obj_blockEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodel_obj_block_Variability_parameters_block()
  {
    return (EReference)model_obj_blockEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodel_obj_block_Output_variables_block()
  {
    return (EReference)model_obj_blockEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodel_obj_block_Group_variables()
  {
    return (EReference)model_obj_blockEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodel_obj_block_Observation_block()
  {
    return (EReference)model_obj_blockEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodel_obj_block_Estimation_block()
  {
    return (EReference)model_obj_blockEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodel_obj_block_Simulation_block()
  {
    return (EReference)model_obj_blockEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getindividual_model_obj_block()
  {
    return individual_model_obj_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getindividual_model_obj_block_Identifier()
  {
    return (EAttribute)individual_model_obj_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getindividual_model_obj_block_Block()
  {
    return (EReference)individual_model_obj_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmodel_prediction_obj_block()
  {
    return model_prediction_obj_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmodel_prediction_obj_block_Identifier()
  {
    return (EAttribute)model_prediction_obj_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodel_prediction_obj_block_Block()
  {
    return (EReference)model_prediction_obj_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getrandom_variable_definition_block()
  {
    return random_variable_definition_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getrandom_variable_definition_block_Identifier()
  {
    return (EAttribute)random_variable_definition_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getrandom_variable_definition_block_Block()
  {
    return (EReference)random_variable_definition_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getinput_variables_block()
  {
    return input_variables_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getinput_variables_block_Indentifier()
  {
    return (EAttribute)input_variables_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getinput_variables_block_Block()
  {
    return (EReference)input_variables_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getstructural_parameters_block()
  {
    return structural_parameters_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getstructural_parameters_block_Identifier()
  {
    return (EAttribute)structural_parameters_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstructural_parameters_block_Block()
  {
    return (EReference)structural_parameters_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getvariability_parameters_block()
  {
    return variability_parameters_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvariability_parameters_block_Identifier()
  {
    return (EAttribute)variability_parameters_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvariability_parameters_block_Block()
  {
    return (EReference)variability_parameters_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getoutput_variables_block()
  {
    return output_variables_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getoutput_variables_block_Identifier()
  {
    return (EAttribute)output_variables_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getoutput_variables_block_Block()
  {
    return (EReference)output_variables_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getgroup_variables()
  {
    return group_variablesEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getgroup_variables_Identifier()
  {
    return (EAttribute)group_variablesEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getgroup_variables_Block()
  {
    return (EReference)group_variablesEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getobservation_block()
  {
    return observation_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getobservation_block_Identifier()
  {
    return (EAttribute)observation_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getobservation_block_Block()
  {
    return (EReference)observation_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getestimation_block()
  {
    return estimation_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getestimation_block_Identifier()
  {
    return (EAttribute)estimation_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getestimation_block_Block()
  {
    return (EReference)estimation_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getsimulation_block()
  {
    return simulation_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getsimulation_block_Identifier()
  {
    return (EAttribute)simulation_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getsimulation_block_Block()
  {
    return (EReference)simulation_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getparam_obj_block()
  {
    return param_obj_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getparam_obj_block_Structural_block()
  {
    return (EReference)param_obj_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getparam_obj_block_Variability_block()
  {
    return (EReference)param_obj_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getstructural_block()
  {
    return structural_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getstructural_block_Identifier()
  {
    return (EAttribute)structural_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstructural_block_Block()
  {
    return (EReference)structural_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getvariability_block()
  {
    return variability_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvariability_block_Identifier()
  {
    return (EAttribute)variability_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvariability_block_Block()
  {
    return (EReference)variability_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdata_obj_block()
  {
    return data_obj_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdata_obj_block_Header_block()
  {
    return (EReference)data_obj_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdata_obj_block_File_block()
  {
    return (EReference)data_obj_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getheader_block()
  {
    return header_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getheader_block_Identifier()
  {
    return (EAttribute)header_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getheader_block_Block()
  {
    return (EReference)header_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getfile_block()
  {
    return file_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getfile_block_Identifier()
  {
    return (EAttribute)file_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfile_block_Block()
  {
    return (EReference)file_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass gettask_obj_block()
  {
    return task_obj_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettask_obj_block_Function_declaration()
  {
    return (EReference)task_obj_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettask_obj_block_Parameters_block()
  {
    return (EReference)task_obj_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference gettask_obj_block_Data_block()
  {
    return (EReference)task_obj_blockEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getparameters_block()
  {
    return parameters_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getparameters_block_Identifier()
  {
    return (EAttribute)parameters_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getparameters_block_Block()
  {
    return (EReference)parameters_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdata_block()
  {
    return data_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdata_block_Identifier()
  {
    return (EAttribute)data_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdata_block_Block()
  {
    return (EReference)data_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmodel_block()
  {
    return model_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodel_block_Statements()
  {
    return (EReference)model_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmodel_block_statement()
  {
    return model_block_statementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodel_block_statement_Statement()
  {
    return (EReference)model_block_statementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodel_block_statement_Ode_block()
  {
    return (EReference)model_block_statementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmodel_block_statement_Library_block()
  {
    return (EReference)model_block_statementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getlibrary_block()
  {
    return library_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getlibrary_block_Identifier()
  {
    return (EAttribute)library_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getlibrary_block_Block()
  {
    return (EReference)library_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getode_block()
  {
    return ode_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getode_block_Identifier()
  {
    return (EAttribute)ode_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getode_block_Block()
  {
    return (EReference)ode_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getvariability_block_content()
  {
    return variability_block_contentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvariability_block_content_Blocks()
  {
    return (EReference)variability_block_contentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getvariability_block_statement()
  {
    return variability_block_statementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvariability_block_statement_Block_statement()
  {
    return (EReference)variability_block_statementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvariability_block_statement_Block_block()
  {
    return (EReference)variability_block_statementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvariability_block_statement_Diag_block()
  {
    return (EReference)variability_block_statementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getblock_subblock()
  {
    return block_subblockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getblock_subblock_Identifier()
  {
    return (EAttribute)block_subblockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getblock_subblock_Block()
  {
    return (EReference)block_subblockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdiag_subblock()
  {
    return diag_subblockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdiag_subblock_Identifier()
  {
    return (EAttribute)diag_subblockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdiag_subblock_Block()
  {
    return (EReference)diag_subblockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getvariability_subblock()
  {
    return variability_subblockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvariability_subblock_Arguments()
  {
    return (EReference)variability_subblockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getfile_block_content()
  {
    return file_block_contentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfile_block_content_Blocks()
  {
    return (EReference)file_block_contentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getfile_block_statement()
  {
    return file_block_statementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfile_block_statement_Statement()
  {
    return (EReference)file_block_statementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfile_block_statement_Inline_block()
  {
    return (EReference)file_block_statementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfile_block_statement_Design_block()
  {
    return (EReference)file_block_statementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfile_block_statement_Rsscript_block()
  {
    return (EReference)file_block_statementEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getinline_block()
  {
    return inline_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getinline_block_Identifier()
  {
    return (EAttribute)inline_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getinline_block_Block()
  {
    return (EReference)inline_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getdesign_block()
  {
    return design_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getdesign_block_Identifier()
  {
    return (EAttribute)design_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getdesign_block_Block()
  {
    return (EReference)design_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getrsscript_block()
  {
    return rsscript_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getrsscript_block_Identifier()
  {
    return (EAttribute)rsscript_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getrsscript_block_Block()
  {
    return (EReference)rsscript_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getinline_block_content()
  {
    return inline_block_contentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getinline_block_content_Identifiers()
  {
    return (EAttribute)inline_block_contentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getinline_block_content_Values()
  {
    return (EAttribute)inline_block_contentEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getfunction_declaration()
  {
    return function_declarationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getfunction_declaration_Identifier()
  {
    return (EAttribute)function_declarationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfunction_declaration_Formal_arguments()
  {
    return (EReference)function_declarationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfunction_declaration_Function_body()
  {
    return (EReference)function_declarationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getfunction_body()
  {
    return function_bodyEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfunction_body_Blocks()
  {
    return (EReference)function_bodyEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getfunction_subblock()
  {
    return function_subblockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getfunction_subblock_Identifier()
  {
    return (EAttribute)function_subblockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfunction_subblock_Estimate_defn()
  {
    return (EReference)function_subblockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfunction_subblock_Simulate_defn()
  {
    return (EReference)function_subblockEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getformal_arguments()
  {
    return formal_argumentsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getformal_arguments_Identifiers()
  {
    return (EAttribute)formal_argumentsEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getfunction_call()
  {
    return function_callEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getfunction_call_Funct_name()
  {
    return (EAttribute)function_callEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getfunction_call_Arguments()
  {
    return (EReference)function_callEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getblock()
  {
    return blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getblock_Statements()
  {
    return (EReference)blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getblock_statement()
  {
    return block_statementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getblock_statement_Variable_declaration()
  {
    return (EReference)block_statementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getblock_statement_Function_call()
  {
    return (EReference)block_statementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getblock_statement_Statement()
  {
    return (EReference)block_statementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getblock_statement_Verbatim_block()
  {
    return (EReference)block_statementEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getverbatim_block()
  {
    return verbatim_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getverbatim_block_Identifier()
  {
    return (EAttribute)verbatim_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getverbatim_block_Block()
  {
    return (EReference)verbatim_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getverbatim_block_External_code()
  {
    return (EAttribute)verbatim_blockEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass gettarget_block()
  {
    return target_blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute gettarget_block_Identifier()
  {
    return (EAttribute)target_blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute gettarget_block_External_code()
  {
    return (EAttribute)target_blockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getvariable_declaration()
  {
    return variable_declarationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvariable_declaration_Identifier()
  {
    return (EReference)variable_declarationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvariable_declaration_Expression()
  {
    return (EReference)variable_declarationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvariable_declaration_Random_list()
  {
    return (EReference)variable_declarationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getany_expression()
  {
    return any_expressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getany_expression_Expression()
  {
    return (EReference)any_expressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getany_expression_List()
  {
    return (EReference)any_expressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getany_expression_Ode_list()
  {
    return (EReference)any_expressionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getany_expression_Random_list()
  {
    return (EReference)any_expressionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getexpression()
  {
    return expressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getexpression_Conditional_expression()
  {
    return (EReference)expressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getexpression_String_expression()
  {
    return (EAttribute)expressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getlist()
  {
    return listEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getlist_Arguments()
  {
    return (EReference)listEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getode_list()
  {
    return ode_listEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getode_list_Arguments()
  {
    return (EReference)ode_listEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getrandom_list()
  {
    return random_listEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getrandom_list_Arguments()
  {
    return (EReference)random_listEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getarguments()
  {
    return argumentsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getarguments_Arguments()
  {
    return (EReference)argumentsEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getargument()
  {
    return argumentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getargument_Identifier()
  {
    return (EAttribute)argumentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getargument_Expression()
  {
    return (EReference)argumentEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getstatement()
  {
    return statementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstatement_Block()
  {
    return (EReference)statementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstatement_Par_expression()
  {
    return (EReference)statementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstatement_If_statement()
  {
    return (EReference)statementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getstatement_Else_statement()
  {
    return (EReference)statementEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getpar_expression()
  {
    return par_expressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getpar_expression_Expression()
  {
    return (EReference)par_expressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getconditional_expression()
  {
    return conditional_expressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getconditional_expression_Conditional_or_expression()
  {
    return (EReference)conditional_expressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getconditional_expression_Expression1()
  {
    return (EReference)conditional_expressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getconditional_expression_Expression2()
  {
    return (EReference)conditional_expressionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getconditional_or_expression()
  {
    return conditional_or_expressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getconditional_or_expression_Conditional_and_expression()
  {
    return (EReference)conditional_or_expressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getconditional_or_expression_Operator()
  {
    return (EAttribute)conditional_or_expressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getconditional_and_expression()
  {
    return conditional_and_expressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getconditional_and_expression_Relational_expression()
  {
    return (EReference)conditional_and_expressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getconditional_and_expression_Operator()
  {
    return (EAttribute)conditional_and_expressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getrelational_expression()
  {
    return relational_expressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getrelational_expression_Negation()
  {
    return (EAttribute)relational_expressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getrelational_expression_Boolean()
  {
    return (EAttribute)relational_expressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getrelational_expression_Additive_expression()
  {
    return (EReference)relational_expressionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getrelational_expression_Relational_op()
  {
    return (EAttribute)relational_expressionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getadditive_expression()
  {
    return additive_expressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getadditive_expression_Multiplicative_expression()
  {
    return (EReference)additive_expressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getadditive_expression_Additive_op()
  {
    return (EAttribute)additive_expressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getmultiplicative_expression()
  {
    return multiplicative_expressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getmultiplicative_expression_Power_expression()
  {
    return (EReference)multiplicative_expressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getmultiplicative_expression_Multiplicative_op()
  {
    return (EAttribute)multiplicative_expressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getpower_expression()
  {
    return power_expressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getpower_expression_Unary_expression()
  {
    return (EReference)power_expressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getpower_expression_Power_op()
  {
    return (EAttribute)power_expressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getunary_expression()
  {
    return unary_expressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getunary_expression_Operator()
  {
    return (EAttribute)unary_expressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getunary_expression_Unary_expression()
  {
    return (EReference)unary_expressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getunary_expression_Par_expression()
  {
    return (EReference)unary_expressionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getunary_expression_Function_call()
  {
    return (EReference)unary_expressionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getunary_expression_Primary()
  {
    return (EReference)unary_expressionEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getprimary()
  {
    return primaryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getprimary_Number()
  {
    return (EAttribute)primaryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getprimary_Identifier()
  {
    return (EReference)primaryEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getvariable_name()
  {
    return variable_nameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getvariable_name_Identifier()
  {
    return (EAttribute)variable_nameEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getvariable_name_Selector()
  {
    return (EReference)variable_nameEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getselector()
  {
    return selectorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getselector_Expression()
  {
    return (EReference)selectorEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MdlFactory getMdlFactory()
  {
    return (MdlFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    mclEClass = createEClass(MCL);
    createEReference(mclEClass, MCL__OBJECTS);

    mcl_objEClass = createEClass(MCL_OBJ);
    createEReference(mcl_objEClass, MCL_OBJ__MODEL_OBJ);
    createEReference(mcl_objEClass, MCL_OBJ__PARAM_OBJ);
    createEReference(mcl_objEClass, MCL_OBJ__DATA_OBJ);
    createEReference(mcl_objEClass, MCL_OBJ__TASK_OBJ);
    createEReference(mcl_objEClass, MCL_OBJ__TEL_OBJ);

    model_objEClass = createEClass(MODEL_OBJ);
    createEAttribute(model_objEClass, MODEL_OBJ__IDENTIFIER);
    createEReference(model_objEClass, MODEL_OBJ__BLOCKS);

    param_objEClass = createEClass(PARAM_OBJ);
    createEAttribute(param_objEClass, PARAM_OBJ__IDENTIFIER);
    createEReference(param_objEClass, PARAM_OBJ__BLOCKS);

    data_objEClass = createEClass(DATA_OBJ);
    createEAttribute(data_objEClass, DATA_OBJ__IDENTIFIER);
    createEReference(data_objEClass, DATA_OBJ__BLOCKS);

    task_objEClass = createEClass(TASK_OBJ);
    createEAttribute(task_objEClass, TASK_OBJ__IDENTIFIER);
    createEReference(task_objEClass, TASK_OBJ__BLOCKS);

    tel_objEClass = createEClass(TEL_OBJ);
    createEAttribute(tel_objEClass, TEL_OBJ__IDENTIFIER);
    createEReference(tel_objEClass, TEL_OBJ__BLOCKS);

    model_obj_blockEClass = createEClass(MODEL_OBJ_BLOCK);
    createEReference(model_obj_blockEClass, MODEL_OBJ_BLOCK__INDIVIDUAL_MODEL_OBJ_BLOCK);
    createEReference(model_obj_blockEClass, MODEL_OBJ_BLOCK__MODEL_PREDICTION_OBJ_BLOCK);
    createEReference(model_obj_blockEClass, MODEL_OBJ_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK);
    createEReference(model_obj_blockEClass, MODEL_OBJ_BLOCK__INPUT_VARIABLES_BLOCK);
    createEReference(model_obj_blockEClass, MODEL_OBJ_BLOCK__STRUCTURAL_PARAMETERS_BLOCK);
    createEReference(model_obj_blockEClass, MODEL_OBJ_BLOCK__VARIABILITY_PARAMETERS_BLOCK);
    createEReference(model_obj_blockEClass, MODEL_OBJ_BLOCK__OUTPUT_VARIABLES_BLOCK);
    createEReference(model_obj_blockEClass, MODEL_OBJ_BLOCK__GROUP_VARIABLES);
    createEReference(model_obj_blockEClass, MODEL_OBJ_BLOCK__OBSERVATION_BLOCK);
    createEReference(model_obj_blockEClass, MODEL_OBJ_BLOCK__ESTIMATION_BLOCK);
    createEReference(model_obj_blockEClass, MODEL_OBJ_BLOCK__SIMULATION_BLOCK);

    individual_model_obj_blockEClass = createEClass(INDIVIDUAL_MODEL_OBJ_BLOCK);
    createEAttribute(individual_model_obj_blockEClass, INDIVIDUAL_MODEL_OBJ_BLOCK__IDENTIFIER);
    createEReference(individual_model_obj_blockEClass, INDIVIDUAL_MODEL_OBJ_BLOCK__BLOCK);

    model_prediction_obj_blockEClass = createEClass(MODEL_PREDICTION_OBJ_BLOCK);
    createEAttribute(model_prediction_obj_blockEClass, MODEL_PREDICTION_OBJ_BLOCK__IDENTIFIER);
    createEReference(model_prediction_obj_blockEClass, MODEL_PREDICTION_OBJ_BLOCK__BLOCK);

    random_variable_definition_blockEClass = createEClass(RANDOM_VARIABLE_DEFINITION_BLOCK);
    createEAttribute(random_variable_definition_blockEClass, RANDOM_VARIABLE_DEFINITION_BLOCK__IDENTIFIER);
    createEReference(random_variable_definition_blockEClass, RANDOM_VARIABLE_DEFINITION_BLOCK__BLOCK);

    input_variables_blockEClass = createEClass(INPUT_VARIABLES_BLOCK);
    createEAttribute(input_variables_blockEClass, INPUT_VARIABLES_BLOCK__INDENTIFIER);
    createEReference(input_variables_blockEClass, INPUT_VARIABLES_BLOCK__BLOCK);

    structural_parameters_blockEClass = createEClass(STRUCTURAL_PARAMETERS_BLOCK);
    createEAttribute(structural_parameters_blockEClass, STRUCTURAL_PARAMETERS_BLOCK__IDENTIFIER);
    createEReference(structural_parameters_blockEClass, STRUCTURAL_PARAMETERS_BLOCK__BLOCK);

    variability_parameters_blockEClass = createEClass(VARIABILITY_PARAMETERS_BLOCK);
    createEAttribute(variability_parameters_blockEClass, VARIABILITY_PARAMETERS_BLOCK__IDENTIFIER);
    createEReference(variability_parameters_blockEClass, VARIABILITY_PARAMETERS_BLOCK__BLOCK);

    output_variables_blockEClass = createEClass(OUTPUT_VARIABLES_BLOCK);
    createEAttribute(output_variables_blockEClass, OUTPUT_VARIABLES_BLOCK__IDENTIFIER);
    createEReference(output_variables_blockEClass, OUTPUT_VARIABLES_BLOCK__BLOCK);

    group_variablesEClass = createEClass(GROUP_VARIABLES);
    createEAttribute(group_variablesEClass, GROUP_VARIABLES__IDENTIFIER);
    createEReference(group_variablesEClass, GROUP_VARIABLES__BLOCK);

    observation_blockEClass = createEClass(OBSERVATION_BLOCK);
    createEAttribute(observation_blockEClass, OBSERVATION_BLOCK__IDENTIFIER);
    createEReference(observation_blockEClass, OBSERVATION_BLOCK__BLOCK);

    estimation_blockEClass = createEClass(ESTIMATION_BLOCK);
    createEAttribute(estimation_blockEClass, ESTIMATION_BLOCK__IDENTIFIER);
    createEReference(estimation_blockEClass, ESTIMATION_BLOCK__BLOCK);

    simulation_blockEClass = createEClass(SIMULATION_BLOCK);
    createEAttribute(simulation_blockEClass, SIMULATION_BLOCK__IDENTIFIER);
    createEReference(simulation_blockEClass, SIMULATION_BLOCK__BLOCK);

    param_obj_blockEClass = createEClass(PARAM_OBJ_BLOCK);
    createEReference(param_obj_blockEClass, PARAM_OBJ_BLOCK__STRUCTURAL_BLOCK);
    createEReference(param_obj_blockEClass, PARAM_OBJ_BLOCK__VARIABILITY_BLOCK);

    structural_blockEClass = createEClass(STRUCTURAL_BLOCK);
    createEAttribute(structural_blockEClass, STRUCTURAL_BLOCK__IDENTIFIER);
    createEReference(structural_blockEClass, STRUCTURAL_BLOCK__BLOCK);

    variability_blockEClass = createEClass(VARIABILITY_BLOCK);
    createEAttribute(variability_blockEClass, VARIABILITY_BLOCK__IDENTIFIER);
    createEReference(variability_blockEClass, VARIABILITY_BLOCK__BLOCK);

    data_obj_blockEClass = createEClass(DATA_OBJ_BLOCK);
    createEReference(data_obj_blockEClass, DATA_OBJ_BLOCK__HEADER_BLOCK);
    createEReference(data_obj_blockEClass, DATA_OBJ_BLOCK__FILE_BLOCK);

    header_blockEClass = createEClass(HEADER_BLOCK);
    createEAttribute(header_blockEClass, HEADER_BLOCK__IDENTIFIER);
    createEReference(header_blockEClass, HEADER_BLOCK__BLOCK);

    file_blockEClass = createEClass(FILE_BLOCK);
    createEAttribute(file_blockEClass, FILE_BLOCK__IDENTIFIER);
    createEReference(file_blockEClass, FILE_BLOCK__BLOCK);

    task_obj_blockEClass = createEClass(TASK_OBJ_BLOCK);
    createEReference(task_obj_blockEClass, TASK_OBJ_BLOCK__FUNCTION_DECLARATION);
    createEReference(task_obj_blockEClass, TASK_OBJ_BLOCK__PARAMETERS_BLOCK);
    createEReference(task_obj_blockEClass, TASK_OBJ_BLOCK__DATA_BLOCK);

    parameters_blockEClass = createEClass(PARAMETERS_BLOCK);
    createEAttribute(parameters_blockEClass, PARAMETERS_BLOCK__IDENTIFIER);
    createEReference(parameters_blockEClass, PARAMETERS_BLOCK__BLOCK);

    data_blockEClass = createEClass(DATA_BLOCK);
    createEAttribute(data_blockEClass, DATA_BLOCK__IDENTIFIER);
    createEReference(data_blockEClass, DATA_BLOCK__BLOCK);

    model_blockEClass = createEClass(MODEL_BLOCK);
    createEReference(model_blockEClass, MODEL_BLOCK__STATEMENTS);

    model_block_statementEClass = createEClass(MODEL_BLOCK_STATEMENT);
    createEReference(model_block_statementEClass, MODEL_BLOCK_STATEMENT__STATEMENT);
    createEReference(model_block_statementEClass, MODEL_BLOCK_STATEMENT__ODE_BLOCK);
    createEReference(model_block_statementEClass, MODEL_BLOCK_STATEMENT__LIBRARY_BLOCK);

    library_blockEClass = createEClass(LIBRARY_BLOCK);
    createEAttribute(library_blockEClass, LIBRARY_BLOCK__IDENTIFIER);
    createEReference(library_blockEClass, LIBRARY_BLOCK__BLOCK);

    ode_blockEClass = createEClass(ODE_BLOCK);
    createEAttribute(ode_blockEClass, ODE_BLOCK__IDENTIFIER);
    createEReference(ode_blockEClass, ODE_BLOCK__BLOCK);

    variability_block_contentEClass = createEClass(VARIABILITY_BLOCK_CONTENT);
    createEReference(variability_block_contentEClass, VARIABILITY_BLOCK_CONTENT__BLOCKS);

    variability_block_statementEClass = createEClass(VARIABILITY_BLOCK_STATEMENT);
    createEReference(variability_block_statementEClass, VARIABILITY_BLOCK_STATEMENT__BLOCK_STATEMENT);
    createEReference(variability_block_statementEClass, VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK);
    createEReference(variability_block_statementEClass, VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK);

    block_subblockEClass = createEClass(BLOCK_SUBBLOCK);
    createEAttribute(block_subblockEClass, BLOCK_SUBBLOCK__IDENTIFIER);
    createEReference(block_subblockEClass, BLOCK_SUBBLOCK__BLOCK);

    diag_subblockEClass = createEClass(DIAG_SUBBLOCK);
    createEAttribute(diag_subblockEClass, DIAG_SUBBLOCK__IDENTIFIER);
    createEReference(diag_subblockEClass, DIAG_SUBBLOCK__BLOCK);

    variability_subblockEClass = createEClass(VARIABILITY_SUBBLOCK);
    createEReference(variability_subblockEClass, VARIABILITY_SUBBLOCK__ARGUMENTS);

    file_block_contentEClass = createEClass(FILE_BLOCK_CONTENT);
    createEReference(file_block_contentEClass, FILE_BLOCK_CONTENT__BLOCKS);

    file_block_statementEClass = createEClass(FILE_BLOCK_STATEMENT);
    createEReference(file_block_statementEClass, FILE_BLOCK_STATEMENT__STATEMENT);
    createEReference(file_block_statementEClass, FILE_BLOCK_STATEMENT__INLINE_BLOCK);
    createEReference(file_block_statementEClass, FILE_BLOCK_STATEMENT__DESIGN_BLOCK);
    createEReference(file_block_statementEClass, FILE_BLOCK_STATEMENT__RSSCRIPT_BLOCK);

    inline_blockEClass = createEClass(INLINE_BLOCK);
    createEAttribute(inline_blockEClass, INLINE_BLOCK__IDENTIFIER);
    createEReference(inline_blockEClass, INLINE_BLOCK__BLOCK);

    design_blockEClass = createEClass(DESIGN_BLOCK);
    createEAttribute(design_blockEClass, DESIGN_BLOCK__IDENTIFIER);
    createEReference(design_blockEClass, DESIGN_BLOCK__BLOCK);

    rsscript_blockEClass = createEClass(RSSCRIPT_BLOCK);
    createEAttribute(rsscript_blockEClass, RSSCRIPT_BLOCK__IDENTIFIER);
    createEReference(rsscript_blockEClass, RSSCRIPT_BLOCK__BLOCK);

    inline_block_contentEClass = createEClass(INLINE_BLOCK_CONTENT);
    createEAttribute(inline_block_contentEClass, INLINE_BLOCK_CONTENT__IDENTIFIERS);
    createEAttribute(inline_block_contentEClass, INLINE_BLOCK_CONTENT__VALUES);

    function_declarationEClass = createEClass(FUNCTION_DECLARATION);
    createEAttribute(function_declarationEClass, FUNCTION_DECLARATION__IDENTIFIER);
    createEReference(function_declarationEClass, FUNCTION_DECLARATION__FORMAL_ARGUMENTS);
    createEReference(function_declarationEClass, FUNCTION_DECLARATION__FUNCTION_BODY);

    function_bodyEClass = createEClass(FUNCTION_BODY);
    createEReference(function_bodyEClass, FUNCTION_BODY__BLOCKS);

    function_subblockEClass = createEClass(FUNCTION_SUBBLOCK);
    createEAttribute(function_subblockEClass, FUNCTION_SUBBLOCK__IDENTIFIER);
    createEReference(function_subblockEClass, FUNCTION_SUBBLOCK__ESTIMATE_DEFN);
    createEReference(function_subblockEClass, FUNCTION_SUBBLOCK__SIMULATE_DEFN);

    formal_argumentsEClass = createEClass(FORMAL_ARGUMENTS);
    createEAttribute(formal_argumentsEClass, FORMAL_ARGUMENTS__IDENTIFIERS);

    function_callEClass = createEClass(FUNCTION_CALL);
    createEAttribute(function_callEClass, FUNCTION_CALL__FUNCT_NAME);
    createEReference(function_callEClass, FUNCTION_CALL__ARGUMENTS);

    blockEClass = createEClass(BLOCK);
    createEReference(blockEClass, BLOCK__STATEMENTS);

    block_statementEClass = createEClass(BLOCK_STATEMENT);
    createEReference(block_statementEClass, BLOCK_STATEMENT__VARIABLE_DECLARATION);
    createEReference(block_statementEClass, BLOCK_STATEMENT__FUNCTION_CALL);
    createEReference(block_statementEClass, BLOCK_STATEMENT__STATEMENT);
    createEReference(block_statementEClass, BLOCK_STATEMENT__VERBATIM_BLOCK);

    verbatim_blockEClass = createEClass(VERBATIM_BLOCK);
    createEAttribute(verbatim_blockEClass, VERBATIM_BLOCK__IDENTIFIER);
    createEReference(verbatim_blockEClass, VERBATIM_BLOCK__BLOCK);
    createEAttribute(verbatim_blockEClass, VERBATIM_BLOCK__EXTERNAL_CODE);

    target_blockEClass = createEClass(TARGET_BLOCK);
    createEAttribute(target_blockEClass, TARGET_BLOCK__IDENTIFIER);
    createEAttribute(target_blockEClass, TARGET_BLOCK__EXTERNAL_CODE);

    variable_declarationEClass = createEClass(VARIABLE_DECLARATION);
    createEReference(variable_declarationEClass, VARIABLE_DECLARATION__IDENTIFIER);
    createEReference(variable_declarationEClass, VARIABLE_DECLARATION__EXPRESSION);
    createEReference(variable_declarationEClass, VARIABLE_DECLARATION__RANDOM_LIST);

    any_expressionEClass = createEClass(ANY_EXPRESSION);
    createEReference(any_expressionEClass, ANY_EXPRESSION__EXPRESSION);
    createEReference(any_expressionEClass, ANY_EXPRESSION__LIST);
    createEReference(any_expressionEClass, ANY_EXPRESSION__ODE_LIST);
    createEReference(any_expressionEClass, ANY_EXPRESSION__RANDOM_LIST);

    expressionEClass = createEClass(EXPRESSION);
    createEReference(expressionEClass, EXPRESSION__CONDITIONAL_EXPRESSION);
    createEAttribute(expressionEClass, EXPRESSION__STRING_EXPRESSION);

    listEClass = createEClass(LIST);
    createEReference(listEClass, LIST__ARGUMENTS);

    ode_listEClass = createEClass(ODE_LIST);
    createEReference(ode_listEClass, ODE_LIST__ARGUMENTS);

    random_listEClass = createEClass(RANDOM_LIST);
    createEReference(random_listEClass, RANDOM_LIST__ARGUMENTS);

    argumentsEClass = createEClass(ARGUMENTS);
    createEReference(argumentsEClass, ARGUMENTS__ARGUMENTS);

    argumentEClass = createEClass(ARGUMENT);
    createEAttribute(argumentEClass, ARGUMENT__IDENTIFIER);
    createEReference(argumentEClass, ARGUMENT__EXPRESSION);

    statementEClass = createEClass(STATEMENT);
    createEReference(statementEClass, STATEMENT__BLOCK);
    createEReference(statementEClass, STATEMENT__PAR_EXPRESSION);
    createEReference(statementEClass, STATEMENT__IF_STATEMENT);
    createEReference(statementEClass, STATEMENT__ELSE_STATEMENT);

    par_expressionEClass = createEClass(PAR_EXPRESSION);
    createEReference(par_expressionEClass, PAR_EXPRESSION__EXPRESSION);

    conditional_expressionEClass = createEClass(CONDITIONAL_EXPRESSION);
    createEReference(conditional_expressionEClass, CONDITIONAL_EXPRESSION__CONDITIONAL_OR_EXPRESSION);
    createEReference(conditional_expressionEClass, CONDITIONAL_EXPRESSION__EXPRESSION1);
    createEReference(conditional_expressionEClass, CONDITIONAL_EXPRESSION__EXPRESSION2);

    conditional_or_expressionEClass = createEClass(CONDITIONAL_OR_EXPRESSION);
    createEReference(conditional_or_expressionEClass, CONDITIONAL_OR_EXPRESSION__CONDITIONAL_AND_EXPRESSION);
    createEAttribute(conditional_or_expressionEClass, CONDITIONAL_OR_EXPRESSION__OPERATOR);

    conditional_and_expressionEClass = createEClass(CONDITIONAL_AND_EXPRESSION);
    createEReference(conditional_and_expressionEClass, CONDITIONAL_AND_EXPRESSION__RELATIONAL_EXPRESSION);
    createEAttribute(conditional_and_expressionEClass, CONDITIONAL_AND_EXPRESSION__OPERATOR);

    relational_expressionEClass = createEClass(RELATIONAL_EXPRESSION);
    createEAttribute(relational_expressionEClass, RELATIONAL_EXPRESSION__NEGATION);
    createEAttribute(relational_expressionEClass, RELATIONAL_EXPRESSION__BOOLEAN);
    createEReference(relational_expressionEClass, RELATIONAL_EXPRESSION__ADDITIVE_EXPRESSION);
    createEAttribute(relational_expressionEClass, RELATIONAL_EXPRESSION__RELATIONAL_OP);

    additive_expressionEClass = createEClass(ADDITIVE_EXPRESSION);
    createEReference(additive_expressionEClass, ADDITIVE_EXPRESSION__MULTIPLICATIVE_EXPRESSION);
    createEAttribute(additive_expressionEClass, ADDITIVE_EXPRESSION__ADDITIVE_OP);

    multiplicative_expressionEClass = createEClass(MULTIPLICATIVE_EXPRESSION);
    createEReference(multiplicative_expressionEClass, MULTIPLICATIVE_EXPRESSION__POWER_EXPRESSION);
    createEAttribute(multiplicative_expressionEClass, MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OP);

    power_expressionEClass = createEClass(POWER_EXPRESSION);
    createEReference(power_expressionEClass, POWER_EXPRESSION__UNARY_EXPRESSION);
    createEAttribute(power_expressionEClass, POWER_EXPRESSION__POWER_OP);

    unary_expressionEClass = createEClass(UNARY_EXPRESSION);
    createEAttribute(unary_expressionEClass, UNARY_EXPRESSION__OPERATOR);
    createEReference(unary_expressionEClass, UNARY_EXPRESSION__UNARY_EXPRESSION);
    createEReference(unary_expressionEClass, UNARY_EXPRESSION__PAR_EXPRESSION);
    createEReference(unary_expressionEClass, UNARY_EXPRESSION__FUNCTION_CALL);
    createEReference(unary_expressionEClass, UNARY_EXPRESSION__PRIMARY);

    primaryEClass = createEClass(PRIMARY);
    createEAttribute(primaryEClass, PRIMARY__NUMBER);
    createEReference(primaryEClass, PRIMARY__IDENTIFIER);

    variable_nameEClass = createEClass(VARIABLE_NAME);
    createEAttribute(variable_nameEClass, VARIABLE_NAME__IDENTIFIER);
    createEReference(variable_nameEClass, VARIABLE_NAME__SELECTOR);

    selectorEClass = createEClass(SELECTOR);
    createEReference(selectorEClass, SELECTOR__EXPRESSION);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(mclEClass, mcl.class, "mcl", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getmcl_Objects(), this.getmcl_obj(), null, "objects", null, 0, -1, mcl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(mcl_objEClass, mcl_obj.class, "mcl_obj", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getmcl_obj_Model_obj(), this.getmodel_obj(), null, "model_obj", null, 0, 1, mcl_obj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmcl_obj_Param_obj(), this.getparam_obj(), null, "param_obj", null, 0, 1, mcl_obj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmcl_obj_Data_obj(), this.getdata_obj(), null, "data_obj", null, 0, 1, mcl_obj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmcl_obj_Task_obj(), this.gettask_obj(), null, "task_obj", null, 0, 1, mcl_obj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmcl_obj_Tel_obj(), this.gettel_obj(), null, "tel_obj", null, 0, 1, mcl_obj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(model_objEClass, model_obj.class, "model_obj", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getmodel_obj_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, model_obj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmodel_obj_Blocks(), this.getmodel_obj_block(), null, "blocks", null, 0, -1, model_obj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(param_objEClass, param_obj.class, "param_obj", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getparam_obj_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, param_obj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getparam_obj_Blocks(), this.getparam_obj_block(), null, "blocks", null, 0, -1, param_obj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(data_objEClass, data_obj.class, "data_obj", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getdata_obj_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, data_obj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getdata_obj_Blocks(), this.getdata_obj_block(), null, "blocks", null, 0, -1, data_obj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(task_objEClass, task_obj.class, "task_obj", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(gettask_obj_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, task_obj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(gettask_obj_Blocks(), this.gettask_obj_block(), null, "blocks", null, 0, -1, task_obj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(tel_objEClass, tel_obj.class, "tel_obj", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(gettel_obj_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, tel_obj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(gettel_obj_Blocks(), this.getblock_statement(), null, "blocks", null, 0, -1, tel_obj.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(model_obj_blockEClass, model_obj_block.class, "model_obj_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getmodel_obj_block_Individual_model_obj_block(), this.getindividual_model_obj_block(), null, "individual_model_obj_block", null, 0, 1, model_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmodel_obj_block_Model_prediction_obj_block(), this.getmodel_prediction_obj_block(), null, "model_prediction_obj_block", null, 0, 1, model_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmodel_obj_block_Random_variable_definition_block(), this.getrandom_variable_definition_block(), null, "random_variable_definition_block", null, 0, 1, model_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmodel_obj_block_Input_variables_block(), this.getinput_variables_block(), null, "input_variables_block", null, 0, 1, model_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmodel_obj_block_Structural_parameters_block(), this.getstructural_parameters_block(), null, "structural_parameters_block", null, 0, 1, model_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmodel_obj_block_Variability_parameters_block(), this.getvariability_parameters_block(), null, "variability_parameters_block", null, 0, 1, model_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmodel_obj_block_Output_variables_block(), this.getoutput_variables_block(), null, "output_variables_block", null, 0, 1, model_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmodel_obj_block_Group_variables(), this.getgroup_variables(), null, "group_variables", null, 0, 1, model_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmodel_obj_block_Observation_block(), this.getobservation_block(), null, "observation_block", null, 0, 1, model_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmodel_obj_block_Estimation_block(), this.getestimation_block(), null, "estimation_block", null, 0, 1, model_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmodel_obj_block_Simulation_block(), this.getsimulation_block(), null, "simulation_block", null, 0, 1, model_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(individual_model_obj_blockEClass, individual_model_obj_block.class, "individual_model_obj_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getindividual_model_obj_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, individual_model_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getindividual_model_obj_block_Block(), this.getblock(), null, "block", null, 0, 1, individual_model_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(model_prediction_obj_blockEClass, model_prediction_obj_block.class, "model_prediction_obj_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getmodel_prediction_obj_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, model_prediction_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmodel_prediction_obj_block_Block(), this.getmodel_block(), null, "block", null, 0, 1, model_prediction_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(random_variable_definition_blockEClass, random_variable_definition_block.class, "random_variable_definition_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getrandom_variable_definition_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, random_variable_definition_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getrandom_variable_definition_block_Block(), this.getblock(), null, "block", null, 0, 1, random_variable_definition_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(input_variables_blockEClass, input_variables_block.class, "input_variables_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getinput_variables_block_Indentifier(), ecorePackage.getEString(), "indentifier", null, 0, 1, input_variables_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getinput_variables_block_Block(), this.getblock(), null, "block", null, 0, 1, input_variables_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(structural_parameters_blockEClass, structural_parameters_block.class, "structural_parameters_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getstructural_parameters_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, structural_parameters_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getstructural_parameters_block_Block(), this.getblock(), null, "block", null, 0, 1, structural_parameters_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variability_parameters_blockEClass, variability_parameters_block.class, "variability_parameters_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getvariability_parameters_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, variability_parameters_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getvariability_parameters_block_Block(), this.getblock(), null, "block", null, 0, 1, variability_parameters_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(output_variables_blockEClass, output_variables_block.class, "output_variables_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getoutput_variables_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, output_variables_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getoutput_variables_block_Block(), this.getblock(), null, "block", null, 0, 1, output_variables_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(group_variablesEClass, group_variables.class, "group_variables", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getgroup_variables_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, group_variables.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getgroup_variables_Block(), this.getblock(), null, "block", null, 0, 1, group_variables.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(observation_blockEClass, observation_block.class, "observation_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getobservation_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, observation_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getobservation_block_Block(), this.getblock(), null, "block", null, 0, 1, observation_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(estimation_blockEClass, estimation_block.class, "estimation_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getestimation_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, estimation_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getestimation_block_Block(), this.getblock(), null, "block", null, 0, 1, estimation_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(simulation_blockEClass, simulation_block.class, "simulation_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getsimulation_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, simulation_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getsimulation_block_Block(), this.getblock(), null, "block", null, 0, 1, simulation_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(param_obj_blockEClass, param_obj_block.class, "param_obj_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getparam_obj_block_Structural_block(), this.getstructural_block(), null, "structural_block", null, 0, 1, param_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getparam_obj_block_Variability_block(), this.getvariability_block(), null, "variability_block", null, 0, 1, param_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(structural_blockEClass, structural_block.class, "structural_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getstructural_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, structural_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getstructural_block_Block(), this.getblock(), null, "block", null, 0, 1, structural_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variability_blockEClass, variability_block.class, "variability_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getvariability_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, variability_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getvariability_block_Block(), this.getvariability_block_content(), null, "block", null, 0, 1, variability_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(data_obj_blockEClass, data_obj_block.class, "data_obj_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getdata_obj_block_Header_block(), this.getheader_block(), null, "header_block", null, 0, 1, data_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getdata_obj_block_File_block(), this.getfile_block(), null, "file_block", null, 0, 1, data_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(header_blockEClass, header_block.class, "header_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getheader_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, header_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getheader_block_Block(), this.getblock(), null, "block", null, 0, 1, header_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(file_blockEClass, file_block.class, "file_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getfile_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, file_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getfile_block_Block(), this.getfile_block_content(), null, "block", null, 0, 1, file_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(task_obj_blockEClass, task_obj_block.class, "task_obj_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(gettask_obj_block_Function_declaration(), this.getfunction_declaration(), null, "function_declaration", null, 0, 1, task_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(gettask_obj_block_Parameters_block(), this.getparameters_block(), null, "parameters_block", null, 0, 1, task_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(gettask_obj_block_Data_block(), this.getdata_block(), null, "data_block", null, 0, 1, task_obj_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(parameters_blockEClass, parameters_block.class, "parameters_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getparameters_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, parameters_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getparameters_block_Block(), this.getblock(), null, "block", null, 0, 1, parameters_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(data_blockEClass, data_block.class, "data_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getdata_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, data_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getdata_block_Block(), this.getblock(), null, "block", null, 0, 1, data_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(model_blockEClass, model_block.class, "model_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getmodel_block_Statements(), this.getmodel_block_statement(), null, "statements", null, 0, -1, model_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(model_block_statementEClass, model_block_statement.class, "model_block_statement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getmodel_block_statement_Statement(), this.getblock_statement(), null, "statement", null, 0, 1, model_block_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmodel_block_statement_Ode_block(), this.getode_block(), null, "ode_block", null, 0, 1, model_block_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getmodel_block_statement_Library_block(), this.getlibrary_block(), null, "library_block", null, 0, 1, model_block_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(library_blockEClass, library_block.class, "library_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getlibrary_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, library_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getlibrary_block_Block(), this.getblock(), null, "block", null, 0, 1, library_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(ode_blockEClass, ode_block.class, "ode_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getode_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, ode_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getode_block_Block(), this.getblock(), null, "block", null, 0, 1, ode_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variability_block_contentEClass, variability_block_content.class, "variability_block_content", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getvariability_block_content_Blocks(), this.getvariability_block_statement(), null, "blocks", null, 0, -1, variability_block_content.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variability_block_statementEClass, variability_block_statement.class, "variability_block_statement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getvariability_block_statement_Block_statement(), this.getblock_statement(), null, "block_statement", null, 0, 1, variability_block_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getvariability_block_statement_Block_block(), this.getblock_subblock(), null, "block_block", null, 0, 1, variability_block_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getvariability_block_statement_Diag_block(), this.getdiag_subblock(), null, "diag_block", null, 0, 1, variability_block_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(block_subblockEClass, block_subblock.class, "block_subblock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getblock_subblock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, block_subblock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getblock_subblock_Block(), this.getvariability_subblock(), null, "block", null, 0, 1, block_subblock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(diag_subblockEClass, diag_subblock.class, "diag_subblock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getdiag_subblock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, diag_subblock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getdiag_subblock_Block(), this.getvariability_subblock(), null, "block", null, 0, 1, diag_subblock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variability_subblockEClass, variability_subblock.class, "variability_subblock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getvariability_subblock_Arguments(), this.getarguments(), null, "arguments", null, 0, 1, variability_subblock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(file_block_contentEClass, file_block_content.class, "file_block_content", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getfile_block_content_Blocks(), this.getfile_block_statement(), null, "blocks", null, 0, -1, file_block_content.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(file_block_statementEClass, file_block_statement.class, "file_block_statement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getfile_block_statement_Statement(), this.getblock_statement(), null, "statement", null, 0, 1, file_block_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getfile_block_statement_Inline_block(), this.getinline_block(), null, "inline_block", null, 0, 1, file_block_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getfile_block_statement_Design_block(), this.getdesign_block(), null, "design_block", null, 0, 1, file_block_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getfile_block_statement_Rsscript_block(), this.getrsscript_block(), null, "rsscript_block", null, 0, 1, file_block_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(inline_blockEClass, inline_block.class, "inline_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getinline_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, inline_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getinline_block_Block(), this.getinline_block_content(), null, "block", null, 0, 1, inline_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(design_blockEClass, design_block.class, "design_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getdesign_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, design_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getdesign_block_Block(), this.getblock(), null, "block", null, 0, 1, design_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(rsscript_blockEClass, rsscript_block.class, "rsscript_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getrsscript_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, rsscript_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getrsscript_block_Block(), this.getblock(), null, "block", null, 0, 1, rsscript_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(inline_block_contentEClass, inline_block_content.class, "inline_block_content", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getinline_block_content_Identifiers(), ecorePackage.getEString(), "identifiers", null, 0, -1, inline_block_content.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getinline_block_content_Values(), ecorePackage.getEString(), "values", null, 0, -1, inline_block_content.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(function_declarationEClass, function_declaration.class, "function_declaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getfunction_declaration_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, function_declaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getfunction_declaration_Formal_arguments(), this.getformal_arguments(), null, "formal_arguments", null, 0, 1, function_declaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getfunction_declaration_Function_body(), this.getfunction_body(), null, "function_body", null, 0, 1, function_declaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(function_bodyEClass, function_body.class, "function_body", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getfunction_body_Blocks(), this.getfunction_subblock(), null, "blocks", null, 0, -1, function_body.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(function_subblockEClass, function_subblock.class, "function_subblock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getfunction_subblock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, function_subblock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getfunction_subblock_Estimate_defn(), this.getblock(), null, "estimate_defn", null, 0, 1, function_subblock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getfunction_subblock_Simulate_defn(), this.getblock(), null, "simulate_defn", null, 0, 1, function_subblock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(formal_argumentsEClass, formal_arguments.class, "formal_arguments", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getformal_arguments_Identifiers(), ecorePackage.getEString(), "identifiers", null, 0, -1, formal_arguments.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(function_callEClass, function_call.class, "function_call", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getfunction_call_Funct_name(), ecorePackage.getEString(), "funct_name", null, 0, 1, function_call.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getfunction_call_Arguments(), this.getarguments(), null, "arguments", null, 0, 1, function_call.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(blockEClass, block.class, "block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getblock_Statements(), this.getblock_statement(), null, "statements", null, 0, -1, block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(block_statementEClass, block_statement.class, "block_statement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getblock_statement_Variable_declaration(), this.getvariable_declaration(), null, "variable_declaration", null, 0, 1, block_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getblock_statement_Function_call(), this.getfunction_call(), null, "function_call", null, 0, 1, block_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getblock_statement_Statement(), this.getstatement(), null, "statement", null, 0, 1, block_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getblock_statement_Verbatim_block(), this.getverbatim_block(), null, "verbatim_block", null, 0, 1, block_statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(verbatim_blockEClass, verbatim_block.class, "verbatim_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getverbatim_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, verbatim_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getverbatim_block_Block(), this.gettarget_block(), null, "block", null, 0, 1, verbatim_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getverbatim_block_External_code(), ecorePackage.getEString(), "external_code", null, 0, 1, verbatim_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(target_blockEClass, target_block.class, "target_block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(gettarget_block_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, target_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(gettarget_block_External_code(), ecorePackage.getEString(), "external_code", null, 0, 1, target_block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variable_declarationEClass, variable_declaration.class, "variable_declaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getvariable_declaration_Identifier(), this.getvariable_name(), null, "identifier", null, 0, 1, variable_declaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getvariable_declaration_Expression(), this.getany_expression(), null, "expression", null, 0, 1, variable_declaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getvariable_declaration_Random_list(), this.getrandom_list(), null, "random_list", null, 0, 1, variable_declaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(any_expressionEClass, any_expression.class, "any_expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getany_expression_Expression(), this.getexpression(), null, "expression", null, 0, 1, any_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getany_expression_List(), this.getlist(), null, "list", null, 0, 1, any_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getany_expression_Ode_list(), this.getode_list(), null, "ode_list", null, 0, 1, any_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getany_expression_Random_list(), this.getrandom_list(), null, "random_list", null, 0, 1, any_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(expressionEClass, expression.class, "expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getexpression_Conditional_expression(), this.getconditional_expression(), null, "conditional_expression", null, 0, 1, expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getexpression_String_expression(), ecorePackage.getEString(), "string_expression", null, 0, -1, expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(listEClass, list.class, "list", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getlist_Arguments(), this.getarguments(), null, "arguments", null, 0, 1, list.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(ode_listEClass, ode_list.class, "ode_list", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getode_list_Arguments(), this.getarguments(), null, "arguments", null, 0, 1, ode_list.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(random_listEClass, random_list.class, "random_list", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getrandom_list_Arguments(), this.getarguments(), null, "arguments", null, 0, 1, random_list.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(argumentsEClass, arguments.class, "arguments", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getarguments_Arguments(), this.getargument(), null, "arguments", null, 0, -1, arguments.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(argumentEClass, argument.class, "argument", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getargument_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, argument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getargument_Expression(), this.getany_expression(), null, "expression", null, 0, 1, argument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(statementEClass, statement.class, "statement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getstatement_Block(), this.getblock(), null, "block", null, 0, 1, statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getstatement_Par_expression(), this.getpar_expression(), null, "par_expression", null, 0, 1, statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getstatement_If_statement(), this.getblock_statement(), null, "if_statement", null, 0, 1, statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getstatement_Else_statement(), this.getblock_statement(), null, "else_statement", null, 0, 1, statement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(par_expressionEClass, par_expression.class, "par_expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getpar_expression_Expression(), this.getexpression(), null, "expression", null, 0, 1, par_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(conditional_expressionEClass, conditional_expression.class, "conditional_expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getconditional_expression_Conditional_or_expression(), this.getconditional_or_expression(), null, "conditional_or_expression", null, 0, 1, conditional_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getconditional_expression_Expression1(), this.getexpression(), null, "expression1", null, 0, 1, conditional_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getconditional_expression_Expression2(), this.getexpression(), null, "expression2", null, 0, 1, conditional_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(conditional_or_expressionEClass, conditional_or_expression.class, "conditional_or_expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getconditional_or_expression_Conditional_and_expression(), this.getconditional_and_expression(), null, "conditional_and_expression", null, 0, -1, conditional_or_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getconditional_or_expression_Operator(), ecorePackage.getEString(), "operator", null, 0, -1, conditional_or_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(conditional_and_expressionEClass, conditional_and_expression.class, "conditional_and_expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getconditional_and_expression_Relational_expression(), this.getrelational_expression(), null, "relational_expression", null, 0, -1, conditional_and_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getconditional_and_expression_Operator(), ecorePackage.getEString(), "operator", null, 0, -1, conditional_and_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(relational_expressionEClass, relational_expression.class, "relational_expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getrelational_expression_Negation(), ecorePackage.getEString(), "negation", null, 0, 1, relational_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getrelational_expression_Boolean(), ecorePackage.getEString(), "boolean", null, 0, 1, relational_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getrelational_expression_Additive_expression(), this.getadditive_expression(), null, "additive_expression", null, 0, -1, relational_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getrelational_expression_Relational_op(), ecorePackage.getEString(), "relational_op", null, 0, -1, relational_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(additive_expressionEClass, additive_expression.class, "additive_expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getadditive_expression_Multiplicative_expression(), this.getmultiplicative_expression(), null, "multiplicative_expression", null, 0, -1, additive_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getadditive_expression_Additive_op(), ecorePackage.getEString(), "additive_op", null, 0, -1, additive_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(multiplicative_expressionEClass, multiplicative_expression.class, "multiplicative_expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getmultiplicative_expression_Power_expression(), this.getpower_expression(), null, "power_expression", null, 0, -1, multiplicative_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getmultiplicative_expression_Multiplicative_op(), ecorePackage.getEString(), "multiplicative_op", null, 0, -1, multiplicative_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(power_expressionEClass, power_expression.class, "power_expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getpower_expression_Unary_expression(), this.getunary_expression(), null, "unary_expression", null, 0, -1, power_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getpower_expression_Power_op(), ecorePackage.getEString(), "power_op", null, 0, -1, power_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(unary_expressionEClass, unary_expression.class, "unary_expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getunary_expression_Operator(), ecorePackage.getEString(), "operator", null, 0, 1, unary_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getunary_expression_Unary_expression(), this.getunary_expression(), null, "unary_expression", null, 0, 1, unary_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getunary_expression_Par_expression(), this.getpar_expression(), null, "par_expression", null, 0, 1, unary_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getunary_expression_Function_call(), this.getfunction_call(), null, "function_call", null, 0, 1, unary_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getunary_expression_Primary(), this.getprimary(), null, "primary", null, 0, 1, unary_expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(primaryEClass, primary.class, "primary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getprimary_Number(), ecorePackage.getEString(), "number", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getprimary_Identifier(), this.getvariable_name(), null, "identifier", null, 0, 1, primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variable_nameEClass, variable_name.class, "variable_name", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getvariable_name_Identifier(), ecorePackage.getEString(), "identifier", null, 0, -1, variable_name.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getvariable_name_Selector(), this.getselector(), null, "selector", null, 0, -1, variable_name.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(selectorEClass, selector.class, "selector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getselector_Expression(), this.getprimary(), null, "expression", null, 0, -1, selector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //MdlPackageImpl
