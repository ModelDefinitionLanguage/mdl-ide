/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.ddmore.mdl.mdl.MdlFactory
 * @model kind="package"
 * @generated
 */
public interface MdlPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "mdl";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.ddmore.org/mdl/Mdl";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "mdl";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  MdlPackage eINSTANCE = org.ddmore.mdl.mdl.impl.MdlPackageImpl.init();

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.mclImpl <em>mcl</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.mclImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getmcl()
   * @generated
   */
  int MCL = 0;

  /**
   * The feature id for the '<em><b>Objects</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MCL__OBJECTS = 0;

  /**
   * The number of structural features of the '<em>mcl</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MCL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.mcl_objImpl <em>mcl obj</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.mcl_objImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getmcl_obj()
   * @generated
   */
  int MCL_OBJ = 1;

  /**
   * The feature id for the '<em><b>Model obj</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MCL_OBJ__MODEL_OBJ = 0;

  /**
   * The feature id for the '<em><b>Param obj</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MCL_OBJ__PARAM_OBJ = 1;

  /**
   * The feature id for the '<em><b>Data obj</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MCL_OBJ__DATA_OBJ = 2;

  /**
   * The feature id for the '<em><b>Task obj</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MCL_OBJ__TASK_OBJ = 3;

  /**
   * The feature id for the '<em><b>Tel obj</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MCL_OBJ__TEL_OBJ = 4;

  /**
   * The number of structural features of the '<em>mcl obj</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MCL_OBJ_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.model_objImpl <em>model obj</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.model_objImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getmodel_obj()
   * @generated
   */
  int MODEL_OBJ = 2;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJ__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Blocks</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJ__BLOCKS = 1;

  /**
   * The number of structural features of the '<em>model obj</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJ_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.param_objImpl <em>param obj</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.param_objImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getparam_obj()
   * @generated
   */
  int PARAM_OBJ = 3;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_OBJ__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Blocks</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_OBJ__BLOCKS = 1;

  /**
   * The number of structural features of the '<em>param obj</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_OBJ_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.data_objImpl <em>data obj</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.data_objImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getdata_obj()
   * @generated
   */
  int DATA_OBJ = 4;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_OBJ__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Blocks</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_OBJ__BLOCKS = 1;

  /**
   * The number of structural features of the '<em>data obj</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_OBJ_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.task_objImpl <em>task obj</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.task_objImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#gettask_obj()
   * @generated
   */
  int TASK_OBJ = 5;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_OBJ__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Blocks</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_OBJ__BLOCKS = 1;

  /**
   * The number of structural features of the '<em>task obj</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_OBJ_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.tel_objImpl <em>tel obj</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.tel_objImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#gettel_obj()
   * @generated
   */
  int TEL_OBJ = 6;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEL_OBJ__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Blocks</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEL_OBJ__BLOCKS = 1;

  /**
   * The number of structural features of the '<em>tel obj</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEL_OBJ_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.model_obj_blockImpl <em>model obj block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.model_obj_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getmodel_obj_block()
   * @generated
   */
  int MODEL_OBJ_BLOCK = 7;

  /**
   * The feature id for the '<em><b>Individual model obj block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJ_BLOCK__INDIVIDUAL_MODEL_OBJ_BLOCK = 0;

  /**
   * The feature id for the '<em><b>Model prediction obj block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJ_BLOCK__MODEL_PREDICTION_OBJ_BLOCK = 1;

  /**
   * The feature id for the '<em><b>Random variable definition block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJ_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK = 2;

  /**
   * The feature id for the '<em><b>Input variables block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJ_BLOCK__INPUT_VARIABLES_BLOCK = 3;

  /**
   * The feature id for the '<em><b>Structural parameters block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJ_BLOCK__STRUCTURAL_PARAMETERS_BLOCK = 4;

  /**
   * The feature id for the '<em><b>Variability parameters block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJ_BLOCK__VARIABILITY_PARAMETERS_BLOCK = 5;

  /**
   * The feature id for the '<em><b>Output variables block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJ_BLOCK__OUTPUT_VARIABLES_BLOCK = 6;

  /**
   * The feature id for the '<em><b>Group variables</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJ_BLOCK__GROUP_VARIABLES = 7;

  /**
   * The feature id for the '<em><b>Observation block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJ_BLOCK__OBSERVATION_BLOCK = 8;

  /**
   * The feature id for the '<em><b>Estimation block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJ_BLOCK__ESTIMATION_BLOCK = 9;

  /**
   * The feature id for the '<em><b>Simulation block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJ_BLOCK__SIMULATION_BLOCK = 10;

  /**
   * The number of structural features of the '<em>model obj block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJ_BLOCK_FEATURE_COUNT = 11;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.individual_model_obj_blockImpl <em>individual model obj block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.individual_model_obj_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getindividual_model_obj_block()
   * @generated
   */
  int INDIVIDUAL_MODEL_OBJ_BLOCK = 8;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INDIVIDUAL_MODEL_OBJ_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INDIVIDUAL_MODEL_OBJ_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>individual model obj block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INDIVIDUAL_MODEL_OBJ_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.model_prediction_obj_blockImpl <em>model prediction obj block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.model_prediction_obj_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getmodel_prediction_obj_block()
   * @generated
   */
  int MODEL_PREDICTION_OBJ_BLOCK = 9;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_PREDICTION_OBJ_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_PREDICTION_OBJ_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>model prediction obj block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_PREDICTION_OBJ_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.random_variable_definition_blockImpl <em>random variable definition block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.random_variable_definition_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getrandom_variable_definition_block()
   * @generated
   */
  int RANDOM_VARIABLE_DEFINITION_BLOCK = 10;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANDOM_VARIABLE_DEFINITION_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANDOM_VARIABLE_DEFINITION_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>random variable definition block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANDOM_VARIABLE_DEFINITION_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.input_variables_blockImpl <em>input variables block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.input_variables_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getinput_variables_block()
   * @generated
   */
  int INPUT_VARIABLES_BLOCK = 11;

  /**
   * The feature id for the '<em><b>Indentifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INPUT_VARIABLES_BLOCK__INDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INPUT_VARIABLES_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>input variables block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INPUT_VARIABLES_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.structural_parameters_blockImpl <em>structural parameters block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.structural_parameters_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getstructural_parameters_block()
   * @generated
   */
  int STRUCTURAL_PARAMETERS_BLOCK = 12;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRUCTURAL_PARAMETERS_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRUCTURAL_PARAMETERS_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>structural parameters block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRUCTURAL_PARAMETERS_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.variability_parameters_blockImpl <em>variability parameters block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.variability_parameters_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getvariability_parameters_block()
   * @generated
   */
  int VARIABILITY_PARAMETERS_BLOCK = 13;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_PARAMETERS_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_PARAMETERS_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>variability parameters block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_PARAMETERS_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.output_variables_blockImpl <em>output variables block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.output_variables_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getoutput_variables_block()
   * @generated
   */
  int OUTPUT_VARIABLES_BLOCK = 14;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_VARIABLES_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_VARIABLES_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>output variables block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_VARIABLES_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.group_variablesImpl <em>group variables</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.group_variablesImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getgroup_variables()
   * @generated
   */
  int GROUP_VARIABLES = 15;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_VARIABLES__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_VARIABLES__BLOCK = 1;

  /**
   * The number of structural features of the '<em>group variables</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_VARIABLES_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.observation_blockImpl <em>observation block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.observation_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getobservation_block()
   * @generated
   */
  int OBSERVATION_BLOCK = 16;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OBSERVATION_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OBSERVATION_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>observation block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OBSERVATION_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.estimation_blockImpl <em>estimation block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.estimation_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getestimation_block()
   * @generated
   */
  int ESTIMATION_BLOCK = 17;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTIMATION_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTIMATION_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>estimation block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTIMATION_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.simulation_blockImpl <em>simulation block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.simulation_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getsimulation_block()
   * @generated
   */
  int SIMULATION_BLOCK = 18;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMULATION_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMULATION_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>simulation block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMULATION_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.param_obj_blockImpl <em>param obj block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.param_obj_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getparam_obj_block()
   * @generated
   */
  int PARAM_OBJ_BLOCK = 19;

  /**
   * The feature id for the '<em><b>Structural block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_OBJ_BLOCK__STRUCTURAL_BLOCK = 0;

  /**
   * The feature id for the '<em><b>Variability block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_OBJ_BLOCK__VARIABILITY_BLOCK = 1;

  /**
   * The number of structural features of the '<em>param obj block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAM_OBJ_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.structural_blockImpl <em>structural block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.structural_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getstructural_block()
   * @generated
   */
  int STRUCTURAL_BLOCK = 20;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRUCTURAL_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRUCTURAL_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>structural block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRUCTURAL_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.variability_blockImpl <em>variability block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.variability_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getvariability_block()
   * @generated
   */
  int VARIABILITY_BLOCK = 21;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>variability block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.data_obj_blockImpl <em>data obj block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.data_obj_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getdata_obj_block()
   * @generated
   */
  int DATA_OBJ_BLOCK = 22;

  /**
   * The feature id for the '<em><b>Header block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_OBJ_BLOCK__HEADER_BLOCK = 0;

  /**
   * The feature id for the '<em><b>File block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_OBJ_BLOCK__FILE_BLOCK = 1;

  /**
   * The number of structural features of the '<em>data obj block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_OBJ_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.header_blockImpl <em>header block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.header_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getheader_block()
   * @generated
   */
  int HEADER_BLOCK = 23;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>header block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.file_blockImpl <em>file block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.file_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getfile_block()
   * @generated
   */
  int FILE_BLOCK = 24;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>file block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.task_obj_blockImpl <em>task obj block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.task_obj_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#gettask_obj_block()
   * @generated
   */
  int TASK_OBJ_BLOCK = 25;

  /**
   * The feature id for the '<em><b>Function declaration</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_OBJ_BLOCK__FUNCTION_DECLARATION = 0;

  /**
   * The feature id for the '<em><b>Parameters block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_OBJ_BLOCK__PARAMETERS_BLOCK = 1;

  /**
   * The feature id for the '<em><b>Data block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_OBJ_BLOCK__DATA_BLOCK = 2;

  /**
   * The number of structural features of the '<em>task obj block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_OBJ_BLOCK_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.parameters_blockImpl <em>parameters block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.parameters_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getparameters_block()
   * @generated
   */
  int PARAMETERS_BLOCK = 26;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETERS_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETERS_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>parameters block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETERS_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.data_blockImpl <em>data block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.data_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getdata_block()
   * @generated
   */
  int DATA_BLOCK = 27;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>data block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.model_blockImpl <em>model block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.model_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getmodel_block()
   * @generated
   */
  int MODEL_BLOCK = 28;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_BLOCK__STATEMENTS = 0;

  /**
   * The number of structural features of the '<em>model block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_BLOCK_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.model_block_statementImpl <em>model block statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.model_block_statementImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getmodel_block_statement()
   * @generated
   */
  int MODEL_BLOCK_STATEMENT = 29;

  /**
   * The feature id for the '<em><b>Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_BLOCK_STATEMENT__STATEMENT = 0;

  /**
   * The feature id for the '<em><b>Ode block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_BLOCK_STATEMENT__ODE_BLOCK = 1;

  /**
   * The feature id for the '<em><b>Library block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_BLOCK_STATEMENT__LIBRARY_BLOCK = 2;

  /**
   * The number of structural features of the '<em>model block statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_BLOCK_STATEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.library_blockImpl <em>library block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.library_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getlibrary_block()
   * @generated
   */
  int LIBRARY_BLOCK = 30;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>library block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ode_blockImpl <em>ode block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ode_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getode_block()
   * @generated
   */
  int ODE_BLOCK = 31;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ODE_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ODE_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>ode block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ODE_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.variability_block_contentImpl <em>variability block content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.variability_block_contentImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getvariability_block_content()
   * @generated
   */
  int VARIABILITY_BLOCK_CONTENT = 32;

  /**
   * The feature id for the '<em><b>Blocks</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_BLOCK_CONTENT__BLOCKS = 0;

  /**
   * The number of structural features of the '<em>variability block content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_BLOCK_CONTENT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.variability_block_statementImpl <em>variability block statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.variability_block_statementImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getvariability_block_statement()
   * @generated
   */
  int VARIABILITY_BLOCK_STATEMENT = 33;

  /**
   * The feature id for the '<em><b>Block statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_BLOCK_STATEMENT__BLOCK_STATEMENT = 0;

  /**
   * The feature id for the '<em><b>Block block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK = 1;

  /**
   * The feature id for the '<em><b>Diag block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK = 2;

  /**
   * The number of structural features of the '<em>variability block statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_BLOCK_STATEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.block_subblockImpl <em>block subblock</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.block_subblockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getblock_subblock()
   * @generated
   */
  int BLOCK_SUBBLOCK = 34;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_SUBBLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_SUBBLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>block subblock</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_SUBBLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.diag_subblockImpl <em>diag subblock</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.diag_subblockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getdiag_subblock()
   * @generated
   */
  int DIAG_SUBBLOCK = 35;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAG_SUBBLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAG_SUBBLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>diag subblock</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAG_SUBBLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.variability_subblockImpl <em>variability subblock</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.variability_subblockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getvariability_subblock()
   * @generated
   */
  int VARIABILITY_SUBBLOCK = 36;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_SUBBLOCK__ARGUMENTS = 0;

  /**
   * The number of structural features of the '<em>variability subblock</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_SUBBLOCK_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.file_block_contentImpl <em>file block content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.file_block_contentImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getfile_block_content()
   * @generated
   */
  int FILE_BLOCK_CONTENT = 37;

  /**
   * The feature id for the '<em><b>Blocks</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK_CONTENT__BLOCKS = 0;

  /**
   * The number of structural features of the '<em>file block content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK_CONTENT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.file_block_statementImpl <em>file block statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.file_block_statementImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getfile_block_statement()
   * @generated
   */
  int FILE_BLOCK_STATEMENT = 38;

  /**
   * The feature id for the '<em><b>Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK_STATEMENT__STATEMENT = 0;

  /**
   * The feature id for the '<em><b>Inline block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK_STATEMENT__INLINE_BLOCK = 1;

  /**
   * The feature id for the '<em><b>Design block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK_STATEMENT__DESIGN_BLOCK = 2;

  /**
   * The feature id for the '<em><b>Rsscript block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK_STATEMENT__RSSCRIPT_BLOCK = 3;

  /**
   * The number of structural features of the '<em>file block statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK_STATEMENT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.inline_blockImpl <em>inline block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.inline_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getinline_block()
   * @generated
   */
  int INLINE_BLOCK = 39;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INLINE_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INLINE_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>inline block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INLINE_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.design_blockImpl <em>design block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.design_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getdesign_block()
   * @generated
   */
  int DESIGN_BLOCK = 40;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>design block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.rsscript_blockImpl <em>rsscript block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.rsscript_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getrsscript_block()
   * @generated
   */
  int RSSCRIPT_BLOCK = 41;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RSSCRIPT_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RSSCRIPT_BLOCK__BLOCK = 1;

  /**
   * The number of structural features of the '<em>rsscript block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RSSCRIPT_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.inline_block_contentImpl <em>inline block content</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.inline_block_contentImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getinline_block_content()
   * @generated
   */
  int INLINE_BLOCK_CONTENT = 42;

  /**
   * The feature id for the '<em><b>Identifiers</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INLINE_BLOCK_CONTENT__IDENTIFIERS = 0;

  /**
   * The feature id for the '<em><b>Values</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INLINE_BLOCK_CONTENT__VALUES = 1;

  /**
   * The number of structural features of the '<em>inline block content</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INLINE_BLOCK_CONTENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.function_declarationImpl <em>function declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.function_declarationImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getfunction_declaration()
   * @generated
   */
  int FUNCTION_DECLARATION = 43;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_DECLARATION__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Formal arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_DECLARATION__FORMAL_ARGUMENTS = 1;

  /**
   * The feature id for the '<em><b>Function body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_DECLARATION__FUNCTION_BODY = 2;

  /**
   * The number of structural features of the '<em>function declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_DECLARATION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.function_bodyImpl <em>function body</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.function_bodyImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getfunction_body()
   * @generated
   */
  int FUNCTION_BODY = 44;

  /**
   * The feature id for the '<em><b>Blocks</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_BODY__BLOCKS = 0;

  /**
   * The number of structural features of the '<em>function body</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_BODY_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.function_subblockImpl <em>function subblock</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.function_subblockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getfunction_subblock()
   * @generated
   */
  int FUNCTION_SUBBLOCK = 45;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_SUBBLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Estimate defn</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_SUBBLOCK__ESTIMATE_DEFN = 1;

  /**
   * The feature id for the '<em><b>Simulate defn</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_SUBBLOCK__SIMULATE_DEFN = 2;

  /**
   * The number of structural features of the '<em>function subblock</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_SUBBLOCK_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.formal_argumentsImpl <em>formal arguments</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.formal_argumentsImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getformal_arguments()
   * @generated
   */
  int FORMAL_ARGUMENTS = 46;

  /**
   * The feature id for the '<em><b>Identifiers</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORMAL_ARGUMENTS__IDENTIFIERS = 0;

  /**
   * The number of structural features of the '<em>formal arguments</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORMAL_ARGUMENTS_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.function_callImpl <em>function call</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.function_callImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getfunction_call()
   * @generated
   */
  int FUNCTION_CALL = 47;

  /**
   * The feature id for the '<em><b>Funct name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL__FUNCT_NAME = 0;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL__ARGUMENTS = 1;

  /**
   * The number of structural features of the '<em>function call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.blockImpl <em>block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getblock()
   * @generated
   */
  int BLOCK = 48;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK__STATEMENTS = 0;

  /**
   * The number of structural features of the '<em>block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.block_statementImpl <em>block statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.block_statementImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getblock_statement()
   * @generated
   */
  int BLOCK_STATEMENT = 49;

  /**
   * The feature id for the '<em><b>Variable declaration</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_STATEMENT__VARIABLE_DECLARATION = 0;

  /**
   * The feature id for the '<em><b>Function call</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_STATEMENT__FUNCTION_CALL = 1;

  /**
   * The feature id for the '<em><b>Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_STATEMENT__STATEMENT = 2;

  /**
   * The feature id for the '<em><b>Verbatim block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_STATEMENT__VERBATIM_BLOCK = 3;

  /**
   * The number of structural features of the '<em>block statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_STATEMENT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.verbatim_blockImpl <em>verbatim block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.verbatim_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getverbatim_block()
   * @generated
   */
  int VERBATIM_BLOCK = 50;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERBATIM_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERBATIM_BLOCK__BLOCK = 1;

  /**
   * The feature id for the '<em><b>External code</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERBATIM_BLOCK__EXTERNAL_CODE = 2;

  /**
   * The number of structural features of the '<em>verbatim block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERBATIM_BLOCK_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.target_blockImpl <em>target block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.target_blockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#gettarget_block()
   * @generated
   */
  int TARGET_BLOCK = 51;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TARGET_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>External code</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TARGET_BLOCK__EXTERNAL_CODE = 1;

  /**
   * The number of structural features of the '<em>target block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TARGET_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.variable_declarationImpl <em>variable declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.variable_declarationImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getvariable_declaration()
   * @generated
   */
  int VARIABLE_DECLARATION = 52;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_DECLARATION__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_DECLARATION__EXPRESSION = 1;

  /**
   * The feature id for the '<em><b>Random list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_DECLARATION__RANDOM_LIST = 2;

  /**
   * The number of structural features of the '<em>variable declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_DECLARATION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.any_expressionImpl <em>any expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.any_expressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getany_expression()
   * @generated
   */
  int ANY_EXPRESSION = 53;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_EXPRESSION__EXPRESSION = 0;

  /**
   * The feature id for the '<em><b>List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_EXPRESSION__LIST = 1;

  /**
   * The feature id for the '<em><b>Ode list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_EXPRESSION__ODE_LIST = 2;

  /**
   * The feature id for the '<em><b>Random list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_EXPRESSION__RANDOM_LIST = 3;

  /**
   * The number of structural features of the '<em>any expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_EXPRESSION_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.expressionImpl <em>expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.expressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getexpression()
   * @generated
   */
  int EXPRESSION = 54;

  /**
   * The feature id for the '<em><b>Conditional expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__CONDITIONAL_EXPRESSION = 0;

  /**
   * The feature id for the '<em><b>String expression</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__STRING_EXPRESSION = 1;

  /**
   * The number of structural features of the '<em>expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.listImpl <em>list</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.listImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getlist()
   * @generated
   */
  int LIST = 55;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST__ARGUMENTS = 0;

  /**
   * The number of structural features of the '<em>list</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ode_listImpl <em>ode list</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ode_listImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getode_list()
   * @generated
   */
  int ODE_LIST = 56;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ODE_LIST__ARGUMENTS = 0;

  /**
   * The number of structural features of the '<em>ode list</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ODE_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.random_listImpl <em>random list</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.random_listImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getrandom_list()
   * @generated
   */
  int RANDOM_LIST = 57;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANDOM_LIST__ARGUMENTS = 0;

  /**
   * The number of structural features of the '<em>random list</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANDOM_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.argumentsImpl <em>arguments</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.argumentsImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getarguments()
   * @generated
   */
  int ARGUMENTS = 58;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARGUMENTS__ARGUMENTS = 0;

  /**
   * The number of structural features of the '<em>arguments</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARGUMENTS_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.argumentImpl <em>argument</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.argumentImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getargument()
   * @generated
   */
  int ARGUMENT = 59;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARGUMENT__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARGUMENT__EXPRESSION = 1;

  /**
   * The number of structural features of the '<em>argument</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARGUMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.statementImpl <em>statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.statementImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getstatement()
   * @generated
   */
  int STATEMENT = 60;

  /**
   * The feature id for the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT__BLOCK = 0;

  /**
   * The feature id for the '<em><b>Par expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT__PAR_EXPRESSION = 1;

  /**
   * The feature id for the '<em><b>If statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT__IF_STATEMENT = 2;

  /**
   * The feature id for the '<em><b>Else statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT__ELSE_STATEMENT = 3;

  /**
   * The number of structural features of the '<em>statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATEMENT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.par_expressionImpl <em>par expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.par_expressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getpar_expression()
   * @generated
   */
  int PAR_EXPRESSION = 61;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PAR_EXPRESSION__EXPRESSION = 0;

  /**
   * The number of structural features of the '<em>par expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PAR_EXPRESSION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.conditional_expressionImpl <em>conditional expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.conditional_expressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getconditional_expression()
   * @generated
   */
  int CONDITIONAL_EXPRESSION = 62;

  /**
   * The feature id for the '<em><b>Conditional or expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EXPRESSION__CONDITIONAL_OR_EXPRESSION = 0;

  /**
   * The feature id for the '<em><b>Expression1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EXPRESSION__EXPRESSION1 = 1;

  /**
   * The feature id for the '<em><b>Expression2</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EXPRESSION__EXPRESSION2 = 2;

  /**
   * The number of structural features of the '<em>conditional expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EXPRESSION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.conditional_or_expressionImpl <em>conditional or expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.conditional_or_expressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getconditional_or_expression()
   * @generated
   */
  int CONDITIONAL_OR_EXPRESSION = 63;

  /**
   * The feature id for the '<em><b>Conditional and expression</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_OR_EXPRESSION__CONDITIONAL_AND_EXPRESSION = 0;

  /**
   * The feature id for the '<em><b>Operator</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_OR_EXPRESSION__OPERATOR = 1;

  /**
   * The number of structural features of the '<em>conditional or expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_OR_EXPRESSION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.conditional_and_expressionImpl <em>conditional and expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.conditional_and_expressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getconditional_and_expression()
   * @generated
   */
  int CONDITIONAL_AND_EXPRESSION = 64;

  /**
   * The feature id for the '<em><b>Relational expression</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_AND_EXPRESSION__RELATIONAL_EXPRESSION = 0;

  /**
   * The feature id for the '<em><b>Operator</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_AND_EXPRESSION__OPERATOR = 1;

  /**
   * The number of structural features of the '<em>conditional and expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_AND_EXPRESSION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.relational_expressionImpl <em>relational expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.relational_expressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getrelational_expression()
   * @generated
   */
  int RELATIONAL_EXPRESSION = 65;

  /**
   * The feature id for the '<em><b>Negation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATIONAL_EXPRESSION__NEGATION = 0;

  /**
   * The feature id for the '<em><b>Boolean</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATIONAL_EXPRESSION__BOOLEAN = 1;

  /**
   * The feature id for the '<em><b>Additive expression</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATIONAL_EXPRESSION__ADDITIVE_EXPRESSION = 2;

  /**
   * The feature id for the '<em><b>Relational op</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATIONAL_EXPRESSION__RELATIONAL_OP = 3;

  /**
   * The number of structural features of the '<em>relational expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RELATIONAL_EXPRESSION_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.additive_expressionImpl <em>additive expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.additive_expressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getadditive_expression()
   * @generated
   */
  int ADDITIVE_EXPRESSION = 66;

  /**
   * The feature id for the '<em><b>Multiplicative expression</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADDITIVE_EXPRESSION__MULTIPLICATIVE_EXPRESSION = 0;

  /**
   * The feature id for the '<em><b>Additive op</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADDITIVE_EXPRESSION__ADDITIVE_OP = 1;

  /**
   * The number of structural features of the '<em>additive expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADDITIVE_EXPRESSION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.multiplicative_expressionImpl <em>multiplicative expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.multiplicative_expressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getmultiplicative_expression()
   * @generated
   */
  int MULTIPLICATIVE_EXPRESSION = 67;

  /**
   * The feature id for the '<em><b>Power expression</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MULTIPLICATIVE_EXPRESSION__POWER_EXPRESSION = 0;

  /**
   * The feature id for the '<em><b>Multiplicative op</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OP = 1;

  /**
   * The number of structural features of the '<em>multiplicative expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MULTIPLICATIVE_EXPRESSION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.power_expressionImpl <em>power expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.power_expressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getpower_expression()
   * @generated
   */
  int POWER_EXPRESSION = 68;

  /**
   * The feature id for the '<em><b>Unary expression</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POWER_EXPRESSION__UNARY_EXPRESSION = 0;

  /**
   * The feature id for the '<em><b>Power op</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POWER_EXPRESSION__POWER_OP = 1;

  /**
   * The number of structural features of the '<em>power expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POWER_EXPRESSION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.unary_expressionImpl <em>unary expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.unary_expressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getunary_expression()
   * @generated
   */
  int UNARY_EXPRESSION = 69;

  /**
   * The feature id for the '<em><b>Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPRESSION__OPERATOR = 0;

  /**
   * The feature id for the '<em><b>Unary expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPRESSION__UNARY_EXPRESSION = 1;

  /**
   * The feature id for the '<em><b>Par expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPRESSION__PAR_EXPRESSION = 2;

  /**
   * The feature id for the '<em><b>Function call</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPRESSION__FUNCTION_CALL = 3;

  /**
   * The feature id for the '<em><b>Primary</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPRESSION__PRIMARY = 4;

  /**
   * The number of structural features of the '<em>unary expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPRESSION_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.primaryImpl <em>primary</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.primaryImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getprimary()
   * @generated
   */
  int PRIMARY = 70;

  /**
   * The feature id for the '<em><b>Literal</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__LITERAL = 0;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__IDENTIFIER = 1;

  /**
   * The number of structural features of the '<em>primary</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.variable_nameImpl <em>variable name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.variable_nameImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getvariable_name()
   * @generated
   */
  int VARIABLE_NAME = 71;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_NAME__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Selector</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_NAME__SELECTOR = 1;

  /**
   * The number of structural features of the '<em>variable name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_NAME_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.selectorImpl <em>selector</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.selectorImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getselector()
   * @generated
   */
  int SELECTOR = 72;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTOR__EXPRESSION = 0;

  /**
   * The number of structural features of the '<em>selector</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SELECTOR_FEATURE_COUNT = 1;


  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.mcl <em>mcl</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mcl</em>'.
   * @see org.ddmore.mdl.mdl.mcl
   * @generated
   */
  EClass getmcl();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.mcl#getObjects <em>Objects</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Objects</em>'.
   * @see org.ddmore.mdl.mdl.mcl#getObjects()
   * @see #getmcl()
   * @generated
   */
  EReference getmcl_Objects();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.mcl_obj <em>mcl obj</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>mcl obj</em>'.
   * @see org.ddmore.mdl.mdl.mcl_obj
   * @generated
   */
  EClass getmcl_obj();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.mcl_obj#getModel_obj <em>Model obj</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Model obj</em>'.
   * @see org.ddmore.mdl.mdl.mcl_obj#getModel_obj()
   * @see #getmcl_obj()
   * @generated
   */
  EReference getmcl_obj_Model_obj();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.mcl_obj#getParam_obj <em>Param obj</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Param obj</em>'.
   * @see org.ddmore.mdl.mdl.mcl_obj#getParam_obj()
   * @see #getmcl_obj()
   * @generated
   */
  EReference getmcl_obj_Param_obj();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.mcl_obj#getData_obj <em>Data obj</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Data obj</em>'.
   * @see org.ddmore.mdl.mdl.mcl_obj#getData_obj()
   * @see #getmcl_obj()
   * @generated
   */
  EReference getmcl_obj_Data_obj();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.mcl_obj#getTask_obj <em>Task obj</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Task obj</em>'.
   * @see org.ddmore.mdl.mdl.mcl_obj#getTask_obj()
   * @see #getmcl_obj()
   * @generated
   */
  EReference getmcl_obj_Task_obj();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.mcl_obj#getTel_obj <em>Tel obj</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Tel obj</em>'.
   * @see org.ddmore.mdl.mdl.mcl_obj#getTel_obj()
   * @see #getmcl_obj()
   * @generated
   */
  EReference getmcl_obj_Tel_obj();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.model_obj <em>model obj</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>model obj</em>'.
   * @see org.ddmore.mdl.mdl.model_obj
   * @generated
   */
  EClass getmodel_obj();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.model_obj#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.model_obj#getIdentifier()
   * @see #getmodel_obj()
   * @generated
   */
  EAttribute getmodel_obj_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.model_obj#getBlocks <em>Blocks</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Blocks</em>'.
   * @see org.ddmore.mdl.mdl.model_obj#getBlocks()
   * @see #getmodel_obj()
   * @generated
   */
  EReference getmodel_obj_Blocks();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.param_obj <em>param obj</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>param obj</em>'.
   * @see org.ddmore.mdl.mdl.param_obj
   * @generated
   */
  EClass getparam_obj();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.param_obj#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.param_obj#getIdentifier()
   * @see #getparam_obj()
   * @generated
   */
  EAttribute getparam_obj_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.param_obj#getBlocks <em>Blocks</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Blocks</em>'.
   * @see org.ddmore.mdl.mdl.param_obj#getBlocks()
   * @see #getparam_obj()
   * @generated
   */
  EReference getparam_obj_Blocks();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.data_obj <em>data obj</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>data obj</em>'.
   * @see org.ddmore.mdl.mdl.data_obj
   * @generated
   */
  EClass getdata_obj();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.data_obj#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.data_obj#getIdentifier()
   * @see #getdata_obj()
   * @generated
   */
  EAttribute getdata_obj_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.data_obj#getBlocks <em>Blocks</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Blocks</em>'.
   * @see org.ddmore.mdl.mdl.data_obj#getBlocks()
   * @see #getdata_obj()
   * @generated
   */
  EReference getdata_obj_Blocks();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.task_obj <em>task obj</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>task obj</em>'.
   * @see org.ddmore.mdl.mdl.task_obj
   * @generated
   */
  EClass gettask_obj();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.task_obj#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.task_obj#getIdentifier()
   * @see #gettask_obj()
   * @generated
   */
  EAttribute gettask_obj_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.task_obj#getBlocks <em>Blocks</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Blocks</em>'.
   * @see org.ddmore.mdl.mdl.task_obj#getBlocks()
   * @see #gettask_obj()
   * @generated
   */
  EReference gettask_obj_Blocks();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.tel_obj <em>tel obj</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>tel obj</em>'.
   * @see org.ddmore.mdl.mdl.tel_obj
   * @generated
   */
  EClass gettel_obj();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.tel_obj#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.tel_obj#getIdentifier()
   * @see #gettel_obj()
   * @generated
   */
  EAttribute gettel_obj_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.tel_obj#getBlocks <em>Blocks</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Blocks</em>'.
   * @see org.ddmore.mdl.mdl.tel_obj#getBlocks()
   * @see #gettel_obj()
   * @generated
   */
  EReference gettel_obj_Blocks();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.model_obj_block <em>model obj block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>model obj block</em>'.
   * @see org.ddmore.mdl.mdl.model_obj_block
   * @generated
   */
  EClass getmodel_obj_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.model_obj_block#getIndividual_model_obj_block <em>Individual model obj block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Individual model obj block</em>'.
   * @see org.ddmore.mdl.mdl.model_obj_block#getIndividual_model_obj_block()
   * @see #getmodel_obj_block()
   * @generated
   */
  EReference getmodel_obj_block_Individual_model_obj_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.model_obj_block#getModel_prediction_obj_block <em>Model prediction obj block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Model prediction obj block</em>'.
   * @see org.ddmore.mdl.mdl.model_obj_block#getModel_prediction_obj_block()
   * @see #getmodel_obj_block()
   * @generated
   */
  EReference getmodel_obj_block_Model_prediction_obj_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.model_obj_block#getRandom_variable_definition_block <em>Random variable definition block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Random variable definition block</em>'.
   * @see org.ddmore.mdl.mdl.model_obj_block#getRandom_variable_definition_block()
   * @see #getmodel_obj_block()
   * @generated
   */
  EReference getmodel_obj_block_Random_variable_definition_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.model_obj_block#getInput_variables_block <em>Input variables block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Input variables block</em>'.
   * @see org.ddmore.mdl.mdl.model_obj_block#getInput_variables_block()
   * @see #getmodel_obj_block()
   * @generated
   */
  EReference getmodel_obj_block_Input_variables_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.model_obj_block#getStructural_parameters_block <em>Structural parameters block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Structural parameters block</em>'.
   * @see org.ddmore.mdl.mdl.model_obj_block#getStructural_parameters_block()
   * @see #getmodel_obj_block()
   * @generated
   */
  EReference getmodel_obj_block_Structural_parameters_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.model_obj_block#getVariability_parameters_block <em>Variability parameters block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Variability parameters block</em>'.
   * @see org.ddmore.mdl.mdl.model_obj_block#getVariability_parameters_block()
   * @see #getmodel_obj_block()
   * @generated
   */
  EReference getmodel_obj_block_Variability_parameters_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.model_obj_block#getOutput_variables_block <em>Output variables block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Output variables block</em>'.
   * @see org.ddmore.mdl.mdl.model_obj_block#getOutput_variables_block()
   * @see #getmodel_obj_block()
   * @generated
   */
  EReference getmodel_obj_block_Output_variables_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.model_obj_block#getGroup_variables <em>Group variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Group variables</em>'.
   * @see org.ddmore.mdl.mdl.model_obj_block#getGroup_variables()
   * @see #getmodel_obj_block()
   * @generated
   */
  EReference getmodel_obj_block_Group_variables();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.model_obj_block#getObservation_block <em>Observation block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Observation block</em>'.
   * @see org.ddmore.mdl.mdl.model_obj_block#getObservation_block()
   * @see #getmodel_obj_block()
   * @generated
   */
  EReference getmodel_obj_block_Observation_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.model_obj_block#getEstimation_block <em>Estimation block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Estimation block</em>'.
   * @see org.ddmore.mdl.mdl.model_obj_block#getEstimation_block()
   * @see #getmodel_obj_block()
   * @generated
   */
  EReference getmodel_obj_block_Estimation_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.model_obj_block#getSimulation_block <em>Simulation block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Simulation block</em>'.
   * @see org.ddmore.mdl.mdl.model_obj_block#getSimulation_block()
   * @see #getmodel_obj_block()
   * @generated
   */
  EReference getmodel_obj_block_Simulation_block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.individual_model_obj_block <em>individual model obj block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>individual model obj block</em>'.
   * @see org.ddmore.mdl.mdl.individual_model_obj_block
   * @generated
   */
  EClass getindividual_model_obj_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.individual_model_obj_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.individual_model_obj_block#getIdentifier()
   * @see #getindividual_model_obj_block()
   * @generated
   */
  EAttribute getindividual_model_obj_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.individual_model_obj_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.individual_model_obj_block#getBlock()
   * @see #getindividual_model_obj_block()
   * @generated
   */
  EReference getindividual_model_obj_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.model_prediction_obj_block <em>model prediction obj block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>model prediction obj block</em>'.
   * @see org.ddmore.mdl.mdl.model_prediction_obj_block
   * @generated
   */
  EClass getmodel_prediction_obj_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.model_prediction_obj_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.model_prediction_obj_block#getIdentifier()
   * @see #getmodel_prediction_obj_block()
   * @generated
   */
  EAttribute getmodel_prediction_obj_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.model_prediction_obj_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.model_prediction_obj_block#getBlock()
   * @see #getmodel_prediction_obj_block()
   * @generated
   */
  EReference getmodel_prediction_obj_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.random_variable_definition_block <em>random variable definition block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>random variable definition block</em>'.
   * @see org.ddmore.mdl.mdl.random_variable_definition_block
   * @generated
   */
  EClass getrandom_variable_definition_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.random_variable_definition_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.random_variable_definition_block#getIdentifier()
   * @see #getrandom_variable_definition_block()
   * @generated
   */
  EAttribute getrandom_variable_definition_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.random_variable_definition_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.random_variable_definition_block#getBlock()
   * @see #getrandom_variable_definition_block()
   * @generated
   */
  EReference getrandom_variable_definition_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.input_variables_block <em>input variables block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>input variables block</em>'.
   * @see org.ddmore.mdl.mdl.input_variables_block
   * @generated
   */
  EClass getinput_variables_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.input_variables_block#getIndentifier <em>Indentifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Indentifier</em>'.
   * @see org.ddmore.mdl.mdl.input_variables_block#getIndentifier()
   * @see #getinput_variables_block()
   * @generated
   */
  EAttribute getinput_variables_block_Indentifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.input_variables_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.input_variables_block#getBlock()
   * @see #getinput_variables_block()
   * @generated
   */
  EReference getinput_variables_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.structural_parameters_block <em>structural parameters block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>structural parameters block</em>'.
   * @see org.ddmore.mdl.mdl.structural_parameters_block
   * @generated
   */
  EClass getstructural_parameters_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.structural_parameters_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.structural_parameters_block#getIdentifier()
   * @see #getstructural_parameters_block()
   * @generated
   */
  EAttribute getstructural_parameters_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.structural_parameters_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.structural_parameters_block#getBlock()
   * @see #getstructural_parameters_block()
   * @generated
   */
  EReference getstructural_parameters_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.variability_parameters_block <em>variability parameters block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>variability parameters block</em>'.
   * @see org.ddmore.mdl.mdl.variability_parameters_block
   * @generated
   */
  EClass getvariability_parameters_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.variability_parameters_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.variability_parameters_block#getIdentifier()
   * @see #getvariability_parameters_block()
   * @generated
   */
  EAttribute getvariability_parameters_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.variability_parameters_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.variability_parameters_block#getBlock()
   * @see #getvariability_parameters_block()
   * @generated
   */
  EReference getvariability_parameters_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.output_variables_block <em>output variables block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>output variables block</em>'.
   * @see org.ddmore.mdl.mdl.output_variables_block
   * @generated
   */
  EClass getoutput_variables_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.output_variables_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.output_variables_block#getIdentifier()
   * @see #getoutput_variables_block()
   * @generated
   */
  EAttribute getoutput_variables_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.output_variables_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.output_variables_block#getBlock()
   * @see #getoutput_variables_block()
   * @generated
   */
  EReference getoutput_variables_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.group_variables <em>group variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>group variables</em>'.
   * @see org.ddmore.mdl.mdl.group_variables
   * @generated
   */
  EClass getgroup_variables();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.group_variables#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.group_variables#getIdentifier()
   * @see #getgroup_variables()
   * @generated
   */
  EAttribute getgroup_variables_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.group_variables#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.group_variables#getBlock()
   * @see #getgroup_variables()
   * @generated
   */
  EReference getgroup_variables_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.observation_block <em>observation block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>observation block</em>'.
   * @see org.ddmore.mdl.mdl.observation_block
   * @generated
   */
  EClass getobservation_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.observation_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.observation_block#getIdentifier()
   * @see #getobservation_block()
   * @generated
   */
  EAttribute getobservation_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.observation_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.observation_block#getBlock()
   * @see #getobservation_block()
   * @generated
   */
  EReference getobservation_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.estimation_block <em>estimation block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>estimation block</em>'.
   * @see org.ddmore.mdl.mdl.estimation_block
   * @generated
   */
  EClass getestimation_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.estimation_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.estimation_block#getIdentifier()
   * @see #getestimation_block()
   * @generated
   */
  EAttribute getestimation_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.estimation_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.estimation_block#getBlock()
   * @see #getestimation_block()
   * @generated
   */
  EReference getestimation_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.simulation_block <em>simulation block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>simulation block</em>'.
   * @see org.ddmore.mdl.mdl.simulation_block
   * @generated
   */
  EClass getsimulation_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.simulation_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.simulation_block#getIdentifier()
   * @see #getsimulation_block()
   * @generated
   */
  EAttribute getsimulation_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.simulation_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.simulation_block#getBlock()
   * @see #getsimulation_block()
   * @generated
   */
  EReference getsimulation_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.param_obj_block <em>param obj block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>param obj block</em>'.
   * @see org.ddmore.mdl.mdl.param_obj_block
   * @generated
   */
  EClass getparam_obj_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.param_obj_block#getStructural_block <em>Structural block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Structural block</em>'.
   * @see org.ddmore.mdl.mdl.param_obj_block#getStructural_block()
   * @see #getparam_obj_block()
   * @generated
   */
  EReference getparam_obj_block_Structural_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.param_obj_block#getVariability_block <em>Variability block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Variability block</em>'.
   * @see org.ddmore.mdl.mdl.param_obj_block#getVariability_block()
   * @see #getparam_obj_block()
   * @generated
   */
  EReference getparam_obj_block_Variability_block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.structural_block <em>structural block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>structural block</em>'.
   * @see org.ddmore.mdl.mdl.structural_block
   * @generated
   */
  EClass getstructural_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.structural_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.structural_block#getIdentifier()
   * @see #getstructural_block()
   * @generated
   */
  EAttribute getstructural_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.structural_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.structural_block#getBlock()
   * @see #getstructural_block()
   * @generated
   */
  EReference getstructural_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.variability_block <em>variability block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>variability block</em>'.
   * @see org.ddmore.mdl.mdl.variability_block
   * @generated
   */
  EClass getvariability_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.variability_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.variability_block#getIdentifier()
   * @see #getvariability_block()
   * @generated
   */
  EAttribute getvariability_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.variability_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.variability_block#getBlock()
   * @see #getvariability_block()
   * @generated
   */
  EReference getvariability_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.data_obj_block <em>data obj block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>data obj block</em>'.
   * @see org.ddmore.mdl.mdl.data_obj_block
   * @generated
   */
  EClass getdata_obj_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.data_obj_block#getHeader_block <em>Header block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Header block</em>'.
   * @see org.ddmore.mdl.mdl.data_obj_block#getHeader_block()
   * @see #getdata_obj_block()
   * @generated
   */
  EReference getdata_obj_block_Header_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.data_obj_block#getFile_block <em>File block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>File block</em>'.
   * @see org.ddmore.mdl.mdl.data_obj_block#getFile_block()
   * @see #getdata_obj_block()
   * @generated
   */
  EReference getdata_obj_block_File_block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.header_block <em>header block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>header block</em>'.
   * @see org.ddmore.mdl.mdl.header_block
   * @generated
   */
  EClass getheader_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.header_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.header_block#getIdentifier()
   * @see #getheader_block()
   * @generated
   */
  EAttribute getheader_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.header_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.header_block#getBlock()
   * @see #getheader_block()
   * @generated
   */
  EReference getheader_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.file_block <em>file block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>file block</em>'.
   * @see org.ddmore.mdl.mdl.file_block
   * @generated
   */
  EClass getfile_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.file_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.file_block#getIdentifier()
   * @see #getfile_block()
   * @generated
   */
  EAttribute getfile_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.file_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.file_block#getBlock()
   * @see #getfile_block()
   * @generated
   */
  EReference getfile_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.task_obj_block <em>task obj block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>task obj block</em>'.
   * @see org.ddmore.mdl.mdl.task_obj_block
   * @generated
   */
  EClass gettask_obj_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.task_obj_block#getFunction_declaration <em>Function declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function declaration</em>'.
   * @see org.ddmore.mdl.mdl.task_obj_block#getFunction_declaration()
   * @see #gettask_obj_block()
   * @generated
   */
  EReference gettask_obj_block_Function_declaration();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.task_obj_block#getParameters_block <em>Parameters block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameters block</em>'.
   * @see org.ddmore.mdl.mdl.task_obj_block#getParameters_block()
   * @see #gettask_obj_block()
   * @generated
   */
  EReference gettask_obj_block_Parameters_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.task_obj_block#getData_block <em>Data block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Data block</em>'.
   * @see org.ddmore.mdl.mdl.task_obj_block#getData_block()
   * @see #gettask_obj_block()
   * @generated
   */
  EReference gettask_obj_block_Data_block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.parameters_block <em>parameters block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>parameters block</em>'.
   * @see org.ddmore.mdl.mdl.parameters_block
   * @generated
   */
  EClass getparameters_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.parameters_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.parameters_block#getIdentifier()
   * @see #getparameters_block()
   * @generated
   */
  EAttribute getparameters_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.parameters_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.parameters_block#getBlock()
   * @see #getparameters_block()
   * @generated
   */
  EReference getparameters_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.data_block <em>data block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>data block</em>'.
   * @see org.ddmore.mdl.mdl.data_block
   * @generated
   */
  EClass getdata_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.data_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.data_block#getIdentifier()
   * @see #getdata_block()
   * @generated
   */
  EAttribute getdata_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.data_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.data_block#getBlock()
   * @see #getdata_block()
   * @generated
   */
  EReference getdata_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.model_block <em>model block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>model block</em>'.
   * @see org.ddmore.mdl.mdl.model_block
   * @generated
   */
  EClass getmodel_block();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.model_block#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.model_block#getStatements()
   * @see #getmodel_block()
   * @generated
   */
  EReference getmodel_block_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.model_block_statement <em>model block statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>model block statement</em>'.
   * @see org.ddmore.mdl.mdl.model_block_statement
   * @generated
   */
  EClass getmodel_block_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.model_block_statement#getStatement <em>Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Statement</em>'.
   * @see org.ddmore.mdl.mdl.model_block_statement#getStatement()
   * @see #getmodel_block_statement()
   * @generated
   */
  EReference getmodel_block_statement_Statement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.model_block_statement#getOde_block <em>Ode block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ode block</em>'.
   * @see org.ddmore.mdl.mdl.model_block_statement#getOde_block()
   * @see #getmodel_block_statement()
   * @generated
   */
  EReference getmodel_block_statement_Ode_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.model_block_statement#getLibrary_block <em>Library block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Library block</em>'.
   * @see org.ddmore.mdl.mdl.model_block_statement#getLibrary_block()
   * @see #getmodel_block_statement()
   * @generated
   */
  EReference getmodel_block_statement_Library_block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.library_block <em>library block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>library block</em>'.
   * @see org.ddmore.mdl.mdl.library_block
   * @generated
   */
  EClass getlibrary_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.library_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.library_block#getIdentifier()
   * @see #getlibrary_block()
   * @generated
   */
  EAttribute getlibrary_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.library_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.library_block#getBlock()
   * @see #getlibrary_block()
   * @generated
   */
  EReference getlibrary_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ode_block <em>ode block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ode block</em>'.
   * @see org.ddmore.mdl.mdl.ode_block
   * @generated
   */
  EClass getode_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.ode_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.ode_block#getIdentifier()
   * @see #getode_block()
   * @generated
   */
  EAttribute getode_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ode_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.ode_block#getBlock()
   * @see #getode_block()
   * @generated
   */
  EReference getode_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.variability_block_content <em>variability block content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>variability block content</em>'.
   * @see org.ddmore.mdl.mdl.variability_block_content
   * @generated
   */
  EClass getvariability_block_content();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.variability_block_content#getBlocks <em>Blocks</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Blocks</em>'.
   * @see org.ddmore.mdl.mdl.variability_block_content#getBlocks()
   * @see #getvariability_block_content()
   * @generated
   */
  EReference getvariability_block_content_Blocks();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.variability_block_statement <em>variability block statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>variability block statement</em>'.
   * @see org.ddmore.mdl.mdl.variability_block_statement
   * @generated
   */
  EClass getvariability_block_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.variability_block_statement#getBlock_statement <em>Block statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block statement</em>'.
   * @see org.ddmore.mdl.mdl.variability_block_statement#getBlock_statement()
   * @see #getvariability_block_statement()
   * @generated
   */
  EReference getvariability_block_statement_Block_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.variability_block_statement#getBlock_block <em>Block block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block block</em>'.
   * @see org.ddmore.mdl.mdl.variability_block_statement#getBlock_block()
   * @see #getvariability_block_statement()
   * @generated
   */
  EReference getvariability_block_statement_Block_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.variability_block_statement#getDiag_block <em>Diag block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Diag block</em>'.
   * @see org.ddmore.mdl.mdl.variability_block_statement#getDiag_block()
   * @see #getvariability_block_statement()
   * @generated
   */
  EReference getvariability_block_statement_Diag_block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.block_subblock <em>block subblock</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>block subblock</em>'.
   * @see org.ddmore.mdl.mdl.block_subblock
   * @generated
   */
  EClass getblock_subblock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.block_subblock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.block_subblock#getIdentifier()
   * @see #getblock_subblock()
   * @generated
   */
  EAttribute getblock_subblock_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.block_subblock#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.block_subblock#getBlock()
   * @see #getblock_subblock()
   * @generated
   */
  EReference getblock_subblock_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.diag_subblock <em>diag subblock</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>diag subblock</em>'.
   * @see org.ddmore.mdl.mdl.diag_subblock
   * @generated
   */
  EClass getdiag_subblock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.diag_subblock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.diag_subblock#getIdentifier()
   * @see #getdiag_subblock()
   * @generated
   */
  EAttribute getdiag_subblock_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.diag_subblock#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.diag_subblock#getBlock()
   * @see #getdiag_subblock()
   * @generated
   */
  EReference getdiag_subblock_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.variability_subblock <em>variability subblock</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>variability subblock</em>'.
   * @see org.ddmore.mdl.mdl.variability_subblock
   * @generated
   */
  EClass getvariability_subblock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.variability_subblock#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.variability_subblock#getArguments()
   * @see #getvariability_subblock()
   * @generated
   */
  EReference getvariability_subblock_Arguments();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.file_block_content <em>file block content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>file block content</em>'.
   * @see org.ddmore.mdl.mdl.file_block_content
   * @generated
   */
  EClass getfile_block_content();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.file_block_content#getBlocks <em>Blocks</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Blocks</em>'.
   * @see org.ddmore.mdl.mdl.file_block_content#getBlocks()
   * @see #getfile_block_content()
   * @generated
   */
  EReference getfile_block_content_Blocks();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.file_block_statement <em>file block statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>file block statement</em>'.
   * @see org.ddmore.mdl.mdl.file_block_statement
   * @generated
   */
  EClass getfile_block_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.file_block_statement#getStatement <em>Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Statement</em>'.
   * @see org.ddmore.mdl.mdl.file_block_statement#getStatement()
   * @see #getfile_block_statement()
   * @generated
   */
  EReference getfile_block_statement_Statement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.file_block_statement#getInline_block <em>Inline block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Inline block</em>'.
   * @see org.ddmore.mdl.mdl.file_block_statement#getInline_block()
   * @see #getfile_block_statement()
   * @generated
   */
  EReference getfile_block_statement_Inline_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.file_block_statement#getDesign_block <em>Design block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Design block</em>'.
   * @see org.ddmore.mdl.mdl.file_block_statement#getDesign_block()
   * @see #getfile_block_statement()
   * @generated
   */
  EReference getfile_block_statement_Design_block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.file_block_statement#getRsscript_block <em>Rsscript block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rsscript block</em>'.
   * @see org.ddmore.mdl.mdl.file_block_statement#getRsscript_block()
   * @see #getfile_block_statement()
   * @generated
   */
  EReference getfile_block_statement_Rsscript_block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.inline_block <em>inline block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>inline block</em>'.
   * @see org.ddmore.mdl.mdl.inline_block
   * @generated
   */
  EClass getinline_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.inline_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.inline_block#getIdentifier()
   * @see #getinline_block()
   * @generated
   */
  EAttribute getinline_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.inline_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.inline_block#getBlock()
   * @see #getinline_block()
   * @generated
   */
  EReference getinline_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.design_block <em>design block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>design block</em>'.
   * @see org.ddmore.mdl.mdl.design_block
   * @generated
   */
  EClass getdesign_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.design_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.design_block#getIdentifier()
   * @see #getdesign_block()
   * @generated
   */
  EAttribute getdesign_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.design_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.design_block#getBlock()
   * @see #getdesign_block()
   * @generated
   */
  EReference getdesign_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.rsscript_block <em>rsscript block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>rsscript block</em>'.
   * @see org.ddmore.mdl.mdl.rsscript_block
   * @generated
   */
  EClass getrsscript_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.rsscript_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.rsscript_block#getIdentifier()
   * @see #getrsscript_block()
   * @generated
   */
  EAttribute getrsscript_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.rsscript_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.rsscript_block#getBlock()
   * @see #getrsscript_block()
   * @generated
   */
  EReference getrsscript_block_Block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.inline_block_content <em>inline block content</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>inline block content</em>'.
   * @see org.ddmore.mdl.mdl.inline_block_content
   * @generated
   */
  EClass getinline_block_content();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.inline_block_content#getIdentifiers <em>Identifiers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Identifiers</em>'.
   * @see org.ddmore.mdl.mdl.inline_block_content#getIdentifiers()
   * @see #getinline_block_content()
   * @generated
   */
  EAttribute getinline_block_content_Identifiers();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.inline_block_content#getValues <em>Values</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Values</em>'.
   * @see org.ddmore.mdl.mdl.inline_block_content#getValues()
   * @see #getinline_block_content()
   * @generated
   */
  EAttribute getinline_block_content_Values();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.function_declaration <em>function declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>function declaration</em>'.
   * @see org.ddmore.mdl.mdl.function_declaration
   * @generated
   */
  EClass getfunction_declaration();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.function_declaration#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.function_declaration#getIdentifier()
   * @see #getfunction_declaration()
   * @generated
   */
  EAttribute getfunction_declaration_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.function_declaration#getFormal_arguments <em>Formal arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Formal arguments</em>'.
   * @see org.ddmore.mdl.mdl.function_declaration#getFormal_arguments()
   * @see #getfunction_declaration()
   * @generated
   */
  EReference getfunction_declaration_Formal_arguments();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.function_declaration#getFunction_body <em>Function body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function body</em>'.
   * @see org.ddmore.mdl.mdl.function_declaration#getFunction_body()
   * @see #getfunction_declaration()
   * @generated
   */
  EReference getfunction_declaration_Function_body();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.function_body <em>function body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>function body</em>'.
   * @see org.ddmore.mdl.mdl.function_body
   * @generated
   */
  EClass getfunction_body();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.function_body#getBlocks <em>Blocks</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Blocks</em>'.
   * @see org.ddmore.mdl.mdl.function_body#getBlocks()
   * @see #getfunction_body()
   * @generated
   */
  EReference getfunction_body_Blocks();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.function_subblock <em>function subblock</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>function subblock</em>'.
   * @see org.ddmore.mdl.mdl.function_subblock
   * @generated
   */
  EClass getfunction_subblock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.function_subblock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.function_subblock#getIdentifier()
   * @see #getfunction_subblock()
   * @generated
   */
  EAttribute getfunction_subblock_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.function_subblock#getEstimate_defn <em>Estimate defn</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Estimate defn</em>'.
   * @see org.ddmore.mdl.mdl.function_subblock#getEstimate_defn()
   * @see #getfunction_subblock()
   * @generated
   */
  EReference getfunction_subblock_Estimate_defn();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.function_subblock#getSimulate_defn <em>Simulate defn</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Simulate defn</em>'.
   * @see org.ddmore.mdl.mdl.function_subblock#getSimulate_defn()
   * @see #getfunction_subblock()
   * @generated
   */
  EReference getfunction_subblock_Simulate_defn();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.formal_arguments <em>formal arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>formal arguments</em>'.
   * @see org.ddmore.mdl.mdl.formal_arguments
   * @generated
   */
  EClass getformal_arguments();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.formal_arguments#getIdentifiers <em>Identifiers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Identifiers</em>'.
   * @see org.ddmore.mdl.mdl.formal_arguments#getIdentifiers()
   * @see #getformal_arguments()
   * @generated
   */
  EAttribute getformal_arguments_Identifiers();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.function_call <em>function call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>function call</em>'.
   * @see org.ddmore.mdl.mdl.function_call
   * @generated
   */
  EClass getfunction_call();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.function_call#getFunct_name <em>Funct name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Funct name</em>'.
   * @see org.ddmore.mdl.mdl.function_call#getFunct_name()
   * @see #getfunction_call()
   * @generated
   */
  EAttribute getfunction_call_Funct_name();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.function_call#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.function_call#getArguments()
   * @see #getfunction_call()
   * @generated
   */
  EReference getfunction_call_Arguments();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.block <em>block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>block</em>'.
   * @see org.ddmore.mdl.mdl.block
   * @generated
   */
  EClass getblock();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.block#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.block#getStatements()
   * @see #getblock()
   * @generated
   */
  EReference getblock_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.block_statement <em>block statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>block statement</em>'.
   * @see org.ddmore.mdl.mdl.block_statement
   * @generated
   */
  EClass getblock_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.block_statement#getVariable_declaration <em>Variable declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Variable declaration</em>'.
   * @see org.ddmore.mdl.mdl.block_statement#getVariable_declaration()
   * @see #getblock_statement()
   * @generated
   */
  EReference getblock_statement_Variable_declaration();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.block_statement#getFunction_call <em>Function call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function call</em>'.
   * @see org.ddmore.mdl.mdl.block_statement#getFunction_call()
   * @see #getblock_statement()
   * @generated
   */
  EReference getblock_statement_Function_call();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.block_statement#getStatement <em>Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Statement</em>'.
   * @see org.ddmore.mdl.mdl.block_statement#getStatement()
   * @see #getblock_statement()
   * @generated
   */
  EReference getblock_statement_Statement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.block_statement#getVerbatim_block <em>Verbatim block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Verbatim block</em>'.
   * @see org.ddmore.mdl.mdl.block_statement#getVerbatim_block()
   * @see #getblock_statement()
   * @generated
   */
  EReference getblock_statement_Verbatim_block();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.verbatim_block <em>verbatim block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>verbatim block</em>'.
   * @see org.ddmore.mdl.mdl.verbatim_block
   * @generated
   */
  EClass getverbatim_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.verbatim_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.verbatim_block#getIdentifier()
   * @see #getverbatim_block()
   * @generated
   */
  EAttribute getverbatim_block_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.verbatim_block#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.verbatim_block#getBlock()
   * @see #getverbatim_block()
   * @generated
   */
  EReference getverbatim_block_Block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.verbatim_block#getExternal_code <em>External code</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>External code</em>'.
   * @see org.ddmore.mdl.mdl.verbatim_block#getExternal_code()
   * @see #getverbatim_block()
   * @generated
   */
  EAttribute getverbatim_block_External_code();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.target_block <em>target block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>target block</em>'.
   * @see org.ddmore.mdl.mdl.target_block
   * @generated
   */
  EClass gettarget_block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.target_block#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.target_block#getIdentifier()
   * @see #gettarget_block()
   * @generated
   */
  EAttribute gettarget_block_Identifier();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.target_block#getExternal_code <em>External code</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>External code</em>'.
   * @see org.ddmore.mdl.mdl.target_block#getExternal_code()
   * @see #gettarget_block()
   * @generated
   */
  EAttribute gettarget_block_External_code();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.variable_declaration <em>variable declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>variable declaration</em>'.
   * @see org.ddmore.mdl.mdl.variable_declaration
   * @generated
   */
  EClass getvariable_declaration();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.variable_declaration#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.variable_declaration#getIdentifier()
   * @see #getvariable_declaration()
   * @generated
   */
  EReference getvariable_declaration_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.variable_declaration#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.variable_declaration#getExpression()
   * @see #getvariable_declaration()
   * @generated
   */
  EReference getvariable_declaration_Expression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.variable_declaration#getRandom_list <em>Random list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Random list</em>'.
   * @see org.ddmore.mdl.mdl.variable_declaration#getRandom_list()
   * @see #getvariable_declaration()
   * @generated
   */
  EReference getvariable_declaration_Random_list();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.any_expression <em>any expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>any expression</em>'.
   * @see org.ddmore.mdl.mdl.any_expression
   * @generated
   */
  EClass getany_expression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.any_expression#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.any_expression#getExpression()
   * @see #getany_expression()
   * @generated
   */
  EReference getany_expression_Expression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.any_expression#getList <em>List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>List</em>'.
   * @see org.ddmore.mdl.mdl.any_expression#getList()
   * @see #getany_expression()
   * @generated
   */
  EReference getany_expression_List();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.any_expression#getOde_list <em>Ode list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ode list</em>'.
   * @see org.ddmore.mdl.mdl.any_expression#getOde_list()
   * @see #getany_expression()
   * @generated
   */
  EReference getany_expression_Ode_list();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.any_expression#getRandom_list <em>Random list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Random list</em>'.
   * @see org.ddmore.mdl.mdl.any_expression#getRandom_list()
   * @see #getany_expression()
   * @generated
   */
  EReference getany_expression_Random_list();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.expression <em>expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>expression</em>'.
   * @see org.ddmore.mdl.mdl.expression
   * @generated
   */
  EClass getexpression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.expression#getConditional_expression <em>Conditional expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Conditional expression</em>'.
   * @see org.ddmore.mdl.mdl.expression#getConditional_expression()
   * @see #getexpression()
   * @generated
   */
  EReference getexpression_Conditional_expression();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.expression#getString_expression <em>String expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>String expression</em>'.
   * @see org.ddmore.mdl.mdl.expression#getString_expression()
   * @see #getexpression()
   * @generated
   */
  EAttribute getexpression_String_expression();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.list <em>list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>list</em>'.
   * @see org.ddmore.mdl.mdl.list
   * @generated
   */
  EClass getlist();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.list#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.list#getArguments()
   * @see #getlist()
   * @generated
   */
  EReference getlist_Arguments();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ode_list <em>ode list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>ode list</em>'.
   * @see org.ddmore.mdl.mdl.ode_list
   * @generated
   */
  EClass getode_list();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ode_list#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.ode_list#getArguments()
   * @see #getode_list()
   * @generated
   */
  EReference getode_list_Arguments();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.random_list <em>random list</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>random list</em>'.
   * @see org.ddmore.mdl.mdl.random_list
   * @generated
   */
  EClass getrandom_list();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.random_list#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.random_list#getArguments()
   * @see #getrandom_list()
   * @generated
   */
  EReference getrandom_list_Arguments();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.arguments <em>arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>arguments</em>'.
   * @see org.ddmore.mdl.mdl.arguments
   * @generated
   */
  EClass getarguments();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.arguments#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.arguments#getArguments()
   * @see #getarguments()
   * @generated
   */
  EReference getarguments_Arguments();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.argument <em>argument</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>argument</em>'.
   * @see org.ddmore.mdl.mdl.argument
   * @generated
   */
  EClass getargument();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.argument#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.argument#getIdentifier()
   * @see #getargument()
   * @generated
   */
  EAttribute getargument_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.argument#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.argument#getExpression()
   * @see #getargument()
   * @generated
   */
  EReference getargument_Expression();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.statement <em>statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>statement</em>'.
   * @see org.ddmore.mdl.mdl.statement
   * @generated
   */
  EClass getstatement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.statement#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.statement#getBlock()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_Block();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.statement#getPar_expression <em>Par expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Par expression</em>'.
   * @see org.ddmore.mdl.mdl.statement#getPar_expression()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_Par_expression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.statement#getIf_statement <em>If statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>If statement</em>'.
   * @see org.ddmore.mdl.mdl.statement#getIf_statement()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_If_statement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.statement#getElse_statement <em>Else statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Else statement</em>'.
   * @see org.ddmore.mdl.mdl.statement#getElse_statement()
   * @see #getstatement()
   * @generated
   */
  EReference getstatement_Else_statement();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.par_expression <em>par expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>par expression</em>'.
   * @see org.ddmore.mdl.mdl.par_expression
   * @generated
   */
  EClass getpar_expression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.par_expression#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.par_expression#getExpression()
   * @see #getpar_expression()
   * @generated
   */
  EReference getpar_expression_Expression();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.conditional_expression <em>conditional expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>conditional expression</em>'.
   * @see org.ddmore.mdl.mdl.conditional_expression
   * @generated
   */
  EClass getconditional_expression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.conditional_expression#getConditional_or_expression <em>Conditional or expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Conditional or expression</em>'.
   * @see org.ddmore.mdl.mdl.conditional_expression#getConditional_or_expression()
   * @see #getconditional_expression()
   * @generated
   */
  EReference getconditional_expression_Conditional_or_expression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.conditional_expression#getExpression1 <em>Expression1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression1</em>'.
   * @see org.ddmore.mdl.mdl.conditional_expression#getExpression1()
   * @see #getconditional_expression()
   * @generated
   */
  EReference getconditional_expression_Expression1();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.conditional_expression#getExpression2 <em>Expression2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression2</em>'.
   * @see org.ddmore.mdl.mdl.conditional_expression#getExpression2()
   * @see #getconditional_expression()
   * @generated
   */
  EReference getconditional_expression_Expression2();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.conditional_or_expression <em>conditional or expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>conditional or expression</em>'.
   * @see org.ddmore.mdl.mdl.conditional_or_expression
   * @generated
   */
  EClass getconditional_or_expression();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.conditional_or_expression#getConditional_and_expression <em>Conditional and expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Conditional and expression</em>'.
   * @see org.ddmore.mdl.mdl.conditional_or_expression#getConditional_and_expression()
   * @see #getconditional_or_expression()
   * @generated
   */
  EReference getconditional_or_expression_Conditional_and_expression();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.conditional_or_expression#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Operator</em>'.
   * @see org.ddmore.mdl.mdl.conditional_or_expression#getOperator()
   * @see #getconditional_or_expression()
   * @generated
   */
  EAttribute getconditional_or_expression_Operator();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.conditional_and_expression <em>conditional and expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>conditional and expression</em>'.
   * @see org.ddmore.mdl.mdl.conditional_and_expression
   * @generated
   */
  EClass getconditional_and_expression();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.conditional_and_expression#getRelational_expression <em>Relational expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Relational expression</em>'.
   * @see org.ddmore.mdl.mdl.conditional_and_expression#getRelational_expression()
   * @see #getconditional_and_expression()
   * @generated
   */
  EReference getconditional_and_expression_Relational_expression();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.conditional_and_expression#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Operator</em>'.
   * @see org.ddmore.mdl.mdl.conditional_and_expression#getOperator()
   * @see #getconditional_and_expression()
   * @generated
   */
  EAttribute getconditional_and_expression_Operator();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.relational_expression <em>relational expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>relational expression</em>'.
   * @see org.ddmore.mdl.mdl.relational_expression
   * @generated
   */
  EClass getrelational_expression();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.relational_expression#getNegation <em>Negation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Negation</em>'.
   * @see org.ddmore.mdl.mdl.relational_expression#getNegation()
   * @see #getrelational_expression()
   * @generated
   */
  EAttribute getrelational_expression_Negation();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.relational_expression#getBoolean <em>Boolean</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Boolean</em>'.
   * @see org.ddmore.mdl.mdl.relational_expression#getBoolean()
   * @see #getrelational_expression()
   * @generated
   */
  EAttribute getrelational_expression_Boolean();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.relational_expression#getAdditive_expression <em>Additive expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Additive expression</em>'.
   * @see org.ddmore.mdl.mdl.relational_expression#getAdditive_expression()
   * @see #getrelational_expression()
   * @generated
   */
  EReference getrelational_expression_Additive_expression();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.relational_expression#getRelational_op <em>Relational op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Relational op</em>'.
   * @see org.ddmore.mdl.mdl.relational_expression#getRelational_op()
   * @see #getrelational_expression()
   * @generated
   */
  EAttribute getrelational_expression_Relational_op();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.additive_expression <em>additive expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>additive expression</em>'.
   * @see org.ddmore.mdl.mdl.additive_expression
   * @generated
   */
  EClass getadditive_expression();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.additive_expression#getMultiplicative_expression <em>Multiplicative expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Multiplicative expression</em>'.
   * @see org.ddmore.mdl.mdl.additive_expression#getMultiplicative_expression()
   * @see #getadditive_expression()
   * @generated
   */
  EReference getadditive_expression_Multiplicative_expression();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.additive_expression#getAdditive_op <em>Additive op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Additive op</em>'.
   * @see org.ddmore.mdl.mdl.additive_expression#getAdditive_op()
   * @see #getadditive_expression()
   * @generated
   */
  EAttribute getadditive_expression_Additive_op();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.multiplicative_expression <em>multiplicative expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>multiplicative expression</em>'.
   * @see org.ddmore.mdl.mdl.multiplicative_expression
   * @generated
   */
  EClass getmultiplicative_expression();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.multiplicative_expression#getPower_expression <em>Power expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Power expression</em>'.
   * @see org.ddmore.mdl.mdl.multiplicative_expression#getPower_expression()
   * @see #getmultiplicative_expression()
   * @generated
   */
  EReference getmultiplicative_expression_Power_expression();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.multiplicative_expression#getMultiplicative_op <em>Multiplicative op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Multiplicative op</em>'.
   * @see org.ddmore.mdl.mdl.multiplicative_expression#getMultiplicative_op()
   * @see #getmultiplicative_expression()
   * @generated
   */
  EAttribute getmultiplicative_expression_Multiplicative_op();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.power_expression <em>power expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>power expression</em>'.
   * @see org.ddmore.mdl.mdl.power_expression
   * @generated
   */
  EClass getpower_expression();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.power_expression#getUnary_expression <em>Unary expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Unary expression</em>'.
   * @see org.ddmore.mdl.mdl.power_expression#getUnary_expression()
   * @see #getpower_expression()
   * @generated
   */
  EReference getpower_expression_Unary_expression();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.power_expression#getPower_op <em>Power op</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Power op</em>'.
   * @see org.ddmore.mdl.mdl.power_expression#getPower_op()
   * @see #getpower_expression()
   * @generated
   */
  EAttribute getpower_expression_Power_op();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.unary_expression <em>unary expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>unary expression</em>'.
   * @see org.ddmore.mdl.mdl.unary_expression
   * @generated
   */
  EClass getunary_expression();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.unary_expression#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operator</em>'.
   * @see org.ddmore.mdl.mdl.unary_expression#getOperator()
   * @see #getunary_expression()
   * @generated
   */
  EAttribute getunary_expression_Operator();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.unary_expression#getUnary_expression <em>Unary expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Unary expression</em>'.
   * @see org.ddmore.mdl.mdl.unary_expression#getUnary_expression()
   * @see #getunary_expression()
   * @generated
   */
  EReference getunary_expression_Unary_expression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.unary_expression#getPar_expression <em>Par expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Par expression</em>'.
   * @see org.ddmore.mdl.mdl.unary_expression#getPar_expression()
   * @see #getunary_expression()
   * @generated
   */
  EReference getunary_expression_Par_expression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.unary_expression#getFunction_call <em>Function call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function call</em>'.
   * @see org.ddmore.mdl.mdl.unary_expression#getFunction_call()
   * @see #getunary_expression()
   * @generated
   */
  EReference getunary_expression_Function_call();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.unary_expression#getPrimary <em>Primary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Primary</em>'.
   * @see org.ddmore.mdl.mdl.unary_expression#getPrimary()
   * @see #getunary_expression()
   * @generated
   */
  EReference getunary_expression_Primary();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.primary <em>primary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>primary</em>'.
   * @see org.ddmore.mdl.mdl.primary
   * @generated
   */
  EClass getprimary();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.primary#getLiteral <em>Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Literal</em>'.
   * @see org.ddmore.mdl.mdl.primary#getLiteral()
   * @see #getprimary()
   * @generated
   */
  EAttribute getprimary_Literal();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.primary#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.primary#getIdentifier()
   * @see #getprimary()
   * @generated
   */
  EReference getprimary_Identifier();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.variable_name <em>variable name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>variable name</em>'.
   * @see org.ddmore.mdl.mdl.variable_name
   * @generated
   */
  EClass getvariable_name();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.variable_name#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.variable_name#getIdentifier()
   * @see #getvariable_name()
   * @generated
   */
  EAttribute getvariable_name_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.variable_name#getSelector <em>Selector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Selector</em>'.
   * @see org.ddmore.mdl.mdl.variable_name#getSelector()
   * @see #getvariable_name()
   * @generated
   */
  EReference getvariable_name_Selector();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.selector <em>selector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>selector</em>'.
   * @see org.ddmore.mdl.mdl.selector
   * @generated
   */
  EClass getselector();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.selector#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.selector#getExpression()
   * @see #getselector()
   * @generated
   */
  EReference getselector_Expression();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  MdlFactory getMdlFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.mclImpl <em>mcl</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.mclImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getmcl()
     * @generated
     */
    EClass MCL = eINSTANCE.getmcl();

    /**
     * The meta object literal for the '<em><b>Objects</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MCL__OBJECTS = eINSTANCE.getmcl_Objects();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.mcl_objImpl <em>mcl obj</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.mcl_objImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getmcl_obj()
     * @generated
     */
    EClass MCL_OBJ = eINSTANCE.getmcl_obj();

    /**
     * The meta object literal for the '<em><b>Model obj</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MCL_OBJ__MODEL_OBJ = eINSTANCE.getmcl_obj_Model_obj();

    /**
     * The meta object literal for the '<em><b>Param obj</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MCL_OBJ__PARAM_OBJ = eINSTANCE.getmcl_obj_Param_obj();

    /**
     * The meta object literal for the '<em><b>Data obj</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MCL_OBJ__DATA_OBJ = eINSTANCE.getmcl_obj_Data_obj();

    /**
     * The meta object literal for the '<em><b>Task obj</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MCL_OBJ__TASK_OBJ = eINSTANCE.getmcl_obj_Task_obj();

    /**
     * The meta object literal for the '<em><b>Tel obj</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MCL_OBJ__TEL_OBJ = eINSTANCE.getmcl_obj_Tel_obj();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.model_objImpl <em>model obj</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.model_objImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getmodel_obj()
     * @generated
     */
    EClass MODEL_OBJ = eINSTANCE.getmodel_obj();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MODEL_OBJ__IDENTIFIER = eINSTANCE.getmodel_obj_Identifier();

    /**
     * The meta object literal for the '<em><b>Blocks</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJ__BLOCKS = eINSTANCE.getmodel_obj_Blocks();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.param_objImpl <em>param obj</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.param_objImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getparam_obj()
     * @generated
     */
    EClass PARAM_OBJ = eINSTANCE.getparam_obj();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PARAM_OBJ__IDENTIFIER = eINSTANCE.getparam_obj_Identifier();

    /**
     * The meta object literal for the '<em><b>Blocks</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAM_OBJ__BLOCKS = eINSTANCE.getparam_obj_Blocks();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.data_objImpl <em>data obj</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.data_objImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getdata_obj()
     * @generated
     */
    EClass DATA_OBJ = eINSTANCE.getdata_obj();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATA_OBJ__IDENTIFIER = eINSTANCE.getdata_obj_Identifier();

    /**
     * The meta object literal for the '<em><b>Blocks</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_OBJ__BLOCKS = eINSTANCE.getdata_obj_Blocks();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.task_objImpl <em>task obj</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.task_objImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#gettask_obj()
     * @generated
     */
    EClass TASK_OBJ = eINSTANCE.gettask_obj();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TASK_OBJ__IDENTIFIER = eINSTANCE.gettask_obj_Identifier();

    /**
     * The meta object literal for the '<em><b>Blocks</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TASK_OBJ__BLOCKS = eINSTANCE.gettask_obj_Blocks();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.tel_objImpl <em>tel obj</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.tel_objImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#gettel_obj()
     * @generated
     */
    EClass TEL_OBJ = eINSTANCE.gettel_obj();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TEL_OBJ__IDENTIFIER = eINSTANCE.gettel_obj_Identifier();

    /**
     * The meta object literal for the '<em><b>Blocks</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TEL_OBJ__BLOCKS = eINSTANCE.gettel_obj_Blocks();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.model_obj_blockImpl <em>model obj block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.model_obj_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getmodel_obj_block()
     * @generated
     */
    EClass MODEL_OBJ_BLOCK = eINSTANCE.getmodel_obj_block();

    /**
     * The meta object literal for the '<em><b>Individual model obj block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJ_BLOCK__INDIVIDUAL_MODEL_OBJ_BLOCK = eINSTANCE.getmodel_obj_block_Individual_model_obj_block();

    /**
     * The meta object literal for the '<em><b>Model prediction obj block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJ_BLOCK__MODEL_PREDICTION_OBJ_BLOCK = eINSTANCE.getmodel_obj_block_Model_prediction_obj_block();

    /**
     * The meta object literal for the '<em><b>Random variable definition block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJ_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK = eINSTANCE.getmodel_obj_block_Random_variable_definition_block();

    /**
     * The meta object literal for the '<em><b>Input variables block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJ_BLOCK__INPUT_VARIABLES_BLOCK = eINSTANCE.getmodel_obj_block_Input_variables_block();

    /**
     * The meta object literal for the '<em><b>Structural parameters block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJ_BLOCK__STRUCTURAL_PARAMETERS_BLOCK = eINSTANCE.getmodel_obj_block_Structural_parameters_block();

    /**
     * The meta object literal for the '<em><b>Variability parameters block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJ_BLOCK__VARIABILITY_PARAMETERS_BLOCK = eINSTANCE.getmodel_obj_block_Variability_parameters_block();

    /**
     * The meta object literal for the '<em><b>Output variables block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJ_BLOCK__OUTPUT_VARIABLES_BLOCK = eINSTANCE.getmodel_obj_block_Output_variables_block();

    /**
     * The meta object literal for the '<em><b>Group variables</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJ_BLOCK__GROUP_VARIABLES = eINSTANCE.getmodel_obj_block_Group_variables();

    /**
     * The meta object literal for the '<em><b>Observation block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJ_BLOCK__OBSERVATION_BLOCK = eINSTANCE.getmodel_obj_block_Observation_block();

    /**
     * The meta object literal for the '<em><b>Estimation block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJ_BLOCK__ESTIMATION_BLOCK = eINSTANCE.getmodel_obj_block_Estimation_block();

    /**
     * The meta object literal for the '<em><b>Simulation block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJ_BLOCK__SIMULATION_BLOCK = eINSTANCE.getmodel_obj_block_Simulation_block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.individual_model_obj_blockImpl <em>individual model obj block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.individual_model_obj_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getindividual_model_obj_block()
     * @generated
     */
    EClass INDIVIDUAL_MODEL_OBJ_BLOCK = eINSTANCE.getindividual_model_obj_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INDIVIDUAL_MODEL_OBJ_BLOCK__IDENTIFIER = eINSTANCE.getindividual_model_obj_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INDIVIDUAL_MODEL_OBJ_BLOCK__BLOCK = eINSTANCE.getindividual_model_obj_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.model_prediction_obj_blockImpl <em>model prediction obj block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.model_prediction_obj_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getmodel_prediction_obj_block()
     * @generated
     */
    EClass MODEL_PREDICTION_OBJ_BLOCK = eINSTANCE.getmodel_prediction_obj_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MODEL_PREDICTION_OBJ_BLOCK__IDENTIFIER = eINSTANCE.getmodel_prediction_obj_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_PREDICTION_OBJ_BLOCK__BLOCK = eINSTANCE.getmodel_prediction_obj_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.random_variable_definition_blockImpl <em>random variable definition block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.random_variable_definition_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getrandom_variable_definition_block()
     * @generated
     */
    EClass RANDOM_VARIABLE_DEFINITION_BLOCK = eINSTANCE.getrandom_variable_definition_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RANDOM_VARIABLE_DEFINITION_BLOCK__IDENTIFIER = eINSTANCE.getrandom_variable_definition_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RANDOM_VARIABLE_DEFINITION_BLOCK__BLOCK = eINSTANCE.getrandom_variable_definition_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.input_variables_blockImpl <em>input variables block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.input_variables_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getinput_variables_block()
     * @generated
     */
    EClass INPUT_VARIABLES_BLOCK = eINSTANCE.getinput_variables_block();

    /**
     * The meta object literal for the '<em><b>Indentifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INPUT_VARIABLES_BLOCK__INDENTIFIER = eINSTANCE.getinput_variables_block_Indentifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INPUT_VARIABLES_BLOCK__BLOCK = eINSTANCE.getinput_variables_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.structural_parameters_blockImpl <em>structural parameters block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.structural_parameters_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getstructural_parameters_block()
     * @generated
     */
    EClass STRUCTURAL_PARAMETERS_BLOCK = eINSTANCE.getstructural_parameters_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute STRUCTURAL_PARAMETERS_BLOCK__IDENTIFIER = eINSTANCE.getstructural_parameters_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STRUCTURAL_PARAMETERS_BLOCK__BLOCK = eINSTANCE.getstructural_parameters_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.variability_parameters_blockImpl <em>variability parameters block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.variability_parameters_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getvariability_parameters_block()
     * @generated
     */
    EClass VARIABILITY_PARAMETERS_BLOCK = eINSTANCE.getvariability_parameters_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VARIABILITY_PARAMETERS_BLOCK__IDENTIFIER = eINSTANCE.getvariability_parameters_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABILITY_PARAMETERS_BLOCK__BLOCK = eINSTANCE.getvariability_parameters_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.output_variables_blockImpl <em>output variables block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.output_variables_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getoutput_variables_block()
     * @generated
     */
    EClass OUTPUT_VARIABLES_BLOCK = eINSTANCE.getoutput_variables_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute OUTPUT_VARIABLES_BLOCK__IDENTIFIER = eINSTANCE.getoutput_variables_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OUTPUT_VARIABLES_BLOCK__BLOCK = eINSTANCE.getoutput_variables_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.group_variablesImpl <em>group variables</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.group_variablesImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getgroup_variables()
     * @generated
     */
    EClass GROUP_VARIABLES = eINSTANCE.getgroup_variables();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GROUP_VARIABLES__IDENTIFIER = eINSTANCE.getgroup_variables_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GROUP_VARIABLES__BLOCK = eINSTANCE.getgroup_variables_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.observation_blockImpl <em>observation block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.observation_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getobservation_block()
     * @generated
     */
    EClass OBSERVATION_BLOCK = eINSTANCE.getobservation_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute OBSERVATION_BLOCK__IDENTIFIER = eINSTANCE.getobservation_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OBSERVATION_BLOCK__BLOCK = eINSTANCE.getobservation_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.estimation_blockImpl <em>estimation block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.estimation_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getestimation_block()
     * @generated
     */
    EClass ESTIMATION_BLOCK = eINSTANCE.getestimation_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ESTIMATION_BLOCK__IDENTIFIER = eINSTANCE.getestimation_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ESTIMATION_BLOCK__BLOCK = eINSTANCE.getestimation_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.simulation_blockImpl <em>simulation block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.simulation_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getsimulation_block()
     * @generated
     */
    EClass SIMULATION_BLOCK = eINSTANCE.getsimulation_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SIMULATION_BLOCK__IDENTIFIER = eINSTANCE.getsimulation_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SIMULATION_BLOCK__BLOCK = eINSTANCE.getsimulation_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.param_obj_blockImpl <em>param obj block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.param_obj_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getparam_obj_block()
     * @generated
     */
    EClass PARAM_OBJ_BLOCK = eINSTANCE.getparam_obj_block();

    /**
     * The meta object literal for the '<em><b>Structural block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAM_OBJ_BLOCK__STRUCTURAL_BLOCK = eINSTANCE.getparam_obj_block_Structural_block();

    /**
     * The meta object literal for the '<em><b>Variability block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAM_OBJ_BLOCK__VARIABILITY_BLOCK = eINSTANCE.getparam_obj_block_Variability_block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.structural_blockImpl <em>structural block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.structural_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getstructural_block()
     * @generated
     */
    EClass STRUCTURAL_BLOCK = eINSTANCE.getstructural_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute STRUCTURAL_BLOCK__IDENTIFIER = eINSTANCE.getstructural_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STRUCTURAL_BLOCK__BLOCK = eINSTANCE.getstructural_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.variability_blockImpl <em>variability block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.variability_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getvariability_block()
     * @generated
     */
    EClass VARIABILITY_BLOCK = eINSTANCE.getvariability_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VARIABILITY_BLOCK__IDENTIFIER = eINSTANCE.getvariability_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABILITY_BLOCK__BLOCK = eINSTANCE.getvariability_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.data_obj_blockImpl <em>data obj block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.data_obj_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getdata_obj_block()
     * @generated
     */
    EClass DATA_OBJ_BLOCK = eINSTANCE.getdata_obj_block();

    /**
     * The meta object literal for the '<em><b>Header block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_OBJ_BLOCK__HEADER_BLOCK = eINSTANCE.getdata_obj_block_Header_block();

    /**
     * The meta object literal for the '<em><b>File block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_OBJ_BLOCK__FILE_BLOCK = eINSTANCE.getdata_obj_block_File_block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.header_blockImpl <em>header block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.header_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getheader_block()
     * @generated
     */
    EClass HEADER_BLOCK = eINSTANCE.getheader_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute HEADER_BLOCK__IDENTIFIER = eINSTANCE.getheader_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference HEADER_BLOCK__BLOCK = eINSTANCE.getheader_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.file_blockImpl <em>file block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.file_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getfile_block()
     * @generated
     */
    EClass FILE_BLOCK = eINSTANCE.getfile_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FILE_BLOCK__IDENTIFIER = eINSTANCE.getfile_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILE_BLOCK__BLOCK = eINSTANCE.getfile_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.task_obj_blockImpl <em>task obj block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.task_obj_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#gettask_obj_block()
     * @generated
     */
    EClass TASK_OBJ_BLOCK = eINSTANCE.gettask_obj_block();

    /**
     * The meta object literal for the '<em><b>Function declaration</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TASK_OBJ_BLOCK__FUNCTION_DECLARATION = eINSTANCE.gettask_obj_block_Function_declaration();

    /**
     * The meta object literal for the '<em><b>Parameters block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TASK_OBJ_BLOCK__PARAMETERS_BLOCK = eINSTANCE.gettask_obj_block_Parameters_block();

    /**
     * The meta object literal for the '<em><b>Data block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TASK_OBJ_BLOCK__DATA_BLOCK = eINSTANCE.gettask_obj_block_Data_block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.parameters_blockImpl <em>parameters block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.parameters_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getparameters_block()
     * @generated
     */
    EClass PARAMETERS_BLOCK = eINSTANCE.getparameters_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PARAMETERS_BLOCK__IDENTIFIER = eINSTANCE.getparameters_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETERS_BLOCK__BLOCK = eINSTANCE.getparameters_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.data_blockImpl <em>data block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.data_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getdata_block()
     * @generated
     */
    EClass DATA_BLOCK = eINSTANCE.getdata_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATA_BLOCK__IDENTIFIER = eINSTANCE.getdata_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_BLOCK__BLOCK = eINSTANCE.getdata_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.model_blockImpl <em>model block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.model_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getmodel_block()
     * @generated
     */
    EClass MODEL_BLOCK = eINSTANCE.getmodel_block();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_BLOCK__STATEMENTS = eINSTANCE.getmodel_block_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.model_block_statementImpl <em>model block statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.model_block_statementImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getmodel_block_statement()
     * @generated
     */
    EClass MODEL_BLOCK_STATEMENT = eINSTANCE.getmodel_block_statement();

    /**
     * The meta object literal for the '<em><b>Statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_BLOCK_STATEMENT__STATEMENT = eINSTANCE.getmodel_block_statement_Statement();

    /**
     * The meta object literal for the '<em><b>Ode block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_BLOCK_STATEMENT__ODE_BLOCK = eINSTANCE.getmodel_block_statement_Ode_block();

    /**
     * The meta object literal for the '<em><b>Library block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_BLOCK_STATEMENT__LIBRARY_BLOCK = eINSTANCE.getmodel_block_statement_Library_block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.library_blockImpl <em>library block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.library_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getlibrary_block()
     * @generated
     */
    EClass LIBRARY_BLOCK = eINSTANCE.getlibrary_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIBRARY_BLOCK__IDENTIFIER = eINSTANCE.getlibrary_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIBRARY_BLOCK__BLOCK = eINSTANCE.getlibrary_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ode_blockImpl <em>ode block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ode_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getode_block()
     * @generated
     */
    EClass ODE_BLOCK = eINSTANCE.getode_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ODE_BLOCK__IDENTIFIER = eINSTANCE.getode_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ODE_BLOCK__BLOCK = eINSTANCE.getode_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.variability_block_contentImpl <em>variability block content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.variability_block_contentImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getvariability_block_content()
     * @generated
     */
    EClass VARIABILITY_BLOCK_CONTENT = eINSTANCE.getvariability_block_content();

    /**
     * The meta object literal for the '<em><b>Blocks</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABILITY_BLOCK_CONTENT__BLOCKS = eINSTANCE.getvariability_block_content_Blocks();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.variability_block_statementImpl <em>variability block statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.variability_block_statementImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getvariability_block_statement()
     * @generated
     */
    EClass VARIABILITY_BLOCK_STATEMENT = eINSTANCE.getvariability_block_statement();

    /**
     * The meta object literal for the '<em><b>Block statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABILITY_BLOCK_STATEMENT__BLOCK_STATEMENT = eINSTANCE.getvariability_block_statement_Block_statement();

    /**
     * The meta object literal for the '<em><b>Block block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK = eINSTANCE.getvariability_block_statement_Block_block();

    /**
     * The meta object literal for the '<em><b>Diag block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK = eINSTANCE.getvariability_block_statement_Diag_block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.block_subblockImpl <em>block subblock</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.block_subblockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getblock_subblock()
     * @generated
     */
    EClass BLOCK_SUBBLOCK = eINSTANCE.getblock_subblock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BLOCK_SUBBLOCK__IDENTIFIER = eINSTANCE.getblock_subblock_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BLOCK_SUBBLOCK__BLOCK = eINSTANCE.getblock_subblock_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.diag_subblockImpl <em>diag subblock</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.diag_subblockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getdiag_subblock()
     * @generated
     */
    EClass DIAG_SUBBLOCK = eINSTANCE.getdiag_subblock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DIAG_SUBBLOCK__IDENTIFIER = eINSTANCE.getdiag_subblock_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DIAG_SUBBLOCK__BLOCK = eINSTANCE.getdiag_subblock_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.variability_subblockImpl <em>variability subblock</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.variability_subblockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getvariability_subblock()
     * @generated
     */
    EClass VARIABILITY_SUBBLOCK = eINSTANCE.getvariability_subblock();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABILITY_SUBBLOCK__ARGUMENTS = eINSTANCE.getvariability_subblock_Arguments();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.file_block_contentImpl <em>file block content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.file_block_contentImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getfile_block_content()
     * @generated
     */
    EClass FILE_BLOCK_CONTENT = eINSTANCE.getfile_block_content();

    /**
     * The meta object literal for the '<em><b>Blocks</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILE_BLOCK_CONTENT__BLOCKS = eINSTANCE.getfile_block_content_Blocks();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.file_block_statementImpl <em>file block statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.file_block_statementImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getfile_block_statement()
     * @generated
     */
    EClass FILE_BLOCK_STATEMENT = eINSTANCE.getfile_block_statement();

    /**
     * The meta object literal for the '<em><b>Statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILE_BLOCK_STATEMENT__STATEMENT = eINSTANCE.getfile_block_statement_Statement();

    /**
     * The meta object literal for the '<em><b>Inline block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILE_BLOCK_STATEMENT__INLINE_BLOCK = eINSTANCE.getfile_block_statement_Inline_block();

    /**
     * The meta object literal for the '<em><b>Design block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILE_BLOCK_STATEMENT__DESIGN_BLOCK = eINSTANCE.getfile_block_statement_Design_block();

    /**
     * The meta object literal for the '<em><b>Rsscript block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILE_BLOCK_STATEMENT__RSSCRIPT_BLOCK = eINSTANCE.getfile_block_statement_Rsscript_block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.inline_blockImpl <em>inline block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.inline_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getinline_block()
     * @generated
     */
    EClass INLINE_BLOCK = eINSTANCE.getinline_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INLINE_BLOCK__IDENTIFIER = eINSTANCE.getinline_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INLINE_BLOCK__BLOCK = eINSTANCE.getinline_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.design_blockImpl <em>design block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.design_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getdesign_block()
     * @generated
     */
    EClass DESIGN_BLOCK = eINSTANCE.getdesign_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DESIGN_BLOCK__IDENTIFIER = eINSTANCE.getdesign_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DESIGN_BLOCK__BLOCK = eINSTANCE.getdesign_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.rsscript_blockImpl <em>rsscript block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.rsscript_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getrsscript_block()
     * @generated
     */
    EClass RSSCRIPT_BLOCK = eINSTANCE.getrsscript_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RSSCRIPT_BLOCK__IDENTIFIER = eINSTANCE.getrsscript_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RSSCRIPT_BLOCK__BLOCK = eINSTANCE.getrsscript_block_Block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.inline_block_contentImpl <em>inline block content</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.inline_block_contentImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getinline_block_content()
     * @generated
     */
    EClass INLINE_BLOCK_CONTENT = eINSTANCE.getinline_block_content();

    /**
     * The meta object literal for the '<em><b>Identifiers</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INLINE_BLOCK_CONTENT__IDENTIFIERS = eINSTANCE.getinline_block_content_Identifiers();

    /**
     * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INLINE_BLOCK_CONTENT__VALUES = eINSTANCE.getinline_block_content_Values();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.function_declarationImpl <em>function declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.function_declarationImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getfunction_declaration()
     * @generated
     */
    EClass FUNCTION_DECLARATION = eINSTANCE.getfunction_declaration();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_DECLARATION__IDENTIFIER = eINSTANCE.getfunction_declaration_Identifier();

    /**
     * The meta object literal for the '<em><b>Formal arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_DECLARATION__FORMAL_ARGUMENTS = eINSTANCE.getfunction_declaration_Formal_arguments();

    /**
     * The meta object literal for the '<em><b>Function body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_DECLARATION__FUNCTION_BODY = eINSTANCE.getfunction_declaration_Function_body();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.function_bodyImpl <em>function body</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.function_bodyImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getfunction_body()
     * @generated
     */
    EClass FUNCTION_BODY = eINSTANCE.getfunction_body();

    /**
     * The meta object literal for the '<em><b>Blocks</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_BODY__BLOCKS = eINSTANCE.getfunction_body_Blocks();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.function_subblockImpl <em>function subblock</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.function_subblockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getfunction_subblock()
     * @generated
     */
    EClass FUNCTION_SUBBLOCK = eINSTANCE.getfunction_subblock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_SUBBLOCK__IDENTIFIER = eINSTANCE.getfunction_subblock_Identifier();

    /**
     * The meta object literal for the '<em><b>Estimate defn</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_SUBBLOCK__ESTIMATE_DEFN = eINSTANCE.getfunction_subblock_Estimate_defn();

    /**
     * The meta object literal for the '<em><b>Simulate defn</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_SUBBLOCK__SIMULATE_DEFN = eINSTANCE.getfunction_subblock_Simulate_defn();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.formal_argumentsImpl <em>formal arguments</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.formal_argumentsImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getformal_arguments()
     * @generated
     */
    EClass FORMAL_ARGUMENTS = eINSTANCE.getformal_arguments();

    /**
     * The meta object literal for the '<em><b>Identifiers</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FORMAL_ARGUMENTS__IDENTIFIERS = eINSTANCE.getformal_arguments_Identifiers();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.function_callImpl <em>function call</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.function_callImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getfunction_call()
     * @generated
     */
    EClass FUNCTION_CALL = eINSTANCE.getfunction_call();

    /**
     * The meta object literal for the '<em><b>Funct name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FUNCTION_CALL__FUNCT_NAME = eINSTANCE.getfunction_call_Funct_name();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_CALL__ARGUMENTS = eINSTANCE.getfunction_call_Arguments();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.blockImpl <em>block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getblock()
     * @generated
     */
    EClass BLOCK = eINSTANCE.getblock();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BLOCK__STATEMENTS = eINSTANCE.getblock_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.block_statementImpl <em>block statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.block_statementImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getblock_statement()
     * @generated
     */
    EClass BLOCK_STATEMENT = eINSTANCE.getblock_statement();

    /**
     * The meta object literal for the '<em><b>Variable declaration</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BLOCK_STATEMENT__VARIABLE_DECLARATION = eINSTANCE.getblock_statement_Variable_declaration();

    /**
     * The meta object literal for the '<em><b>Function call</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BLOCK_STATEMENT__FUNCTION_CALL = eINSTANCE.getblock_statement_Function_call();

    /**
     * The meta object literal for the '<em><b>Statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BLOCK_STATEMENT__STATEMENT = eINSTANCE.getblock_statement_Statement();

    /**
     * The meta object literal for the '<em><b>Verbatim block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BLOCK_STATEMENT__VERBATIM_BLOCK = eINSTANCE.getblock_statement_Verbatim_block();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.verbatim_blockImpl <em>verbatim block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.verbatim_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getverbatim_block()
     * @generated
     */
    EClass VERBATIM_BLOCK = eINSTANCE.getverbatim_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VERBATIM_BLOCK__IDENTIFIER = eINSTANCE.getverbatim_block_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VERBATIM_BLOCK__BLOCK = eINSTANCE.getverbatim_block_Block();

    /**
     * The meta object literal for the '<em><b>External code</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VERBATIM_BLOCK__EXTERNAL_CODE = eINSTANCE.getverbatim_block_External_code();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.target_blockImpl <em>target block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.target_blockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#gettarget_block()
     * @generated
     */
    EClass TARGET_BLOCK = eINSTANCE.gettarget_block();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TARGET_BLOCK__IDENTIFIER = eINSTANCE.gettarget_block_Identifier();

    /**
     * The meta object literal for the '<em><b>External code</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TARGET_BLOCK__EXTERNAL_CODE = eINSTANCE.gettarget_block_External_code();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.variable_declarationImpl <em>variable declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.variable_declarationImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getvariable_declaration()
     * @generated
     */
    EClass VARIABLE_DECLARATION = eINSTANCE.getvariable_declaration();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABLE_DECLARATION__IDENTIFIER = eINSTANCE.getvariable_declaration_Identifier();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABLE_DECLARATION__EXPRESSION = eINSTANCE.getvariable_declaration_Expression();

    /**
     * The meta object literal for the '<em><b>Random list</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABLE_DECLARATION__RANDOM_LIST = eINSTANCE.getvariable_declaration_Random_list();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.any_expressionImpl <em>any expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.any_expressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getany_expression()
     * @generated
     */
    EClass ANY_EXPRESSION = eINSTANCE.getany_expression();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANY_EXPRESSION__EXPRESSION = eINSTANCE.getany_expression_Expression();

    /**
     * The meta object literal for the '<em><b>List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANY_EXPRESSION__LIST = eINSTANCE.getany_expression_List();

    /**
     * The meta object literal for the '<em><b>Ode list</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANY_EXPRESSION__ODE_LIST = eINSTANCE.getany_expression_Ode_list();

    /**
     * The meta object literal for the '<em><b>Random list</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANY_EXPRESSION__RANDOM_LIST = eINSTANCE.getany_expression_Random_list();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.expressionImpl <em>expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.expressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getexpression()
     * @generated
     */
    EClass EXPRESSION = eINSTANCE.getexpression();

    /**
     * The meta object literal for the '<em><b>Conditional expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION__CONDITIONAL_EXPRESSION = eINSTANCE.getexpression_Conditional_expression();

    /**
     * The meta object literal for the '<em><b>String expression</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPRESSION__STRING_EXPRESSION = eINSTANCE.getexpression_String_expression();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.listImpl <em>list</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.listImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getlist()
     * @generated
     */
    EClass LIST = eINSTANCE.getlist();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIST__ARGUMENTS = eINSTANCE.getlist_Arguments();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ode_listImpl <em>ode list</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ode_listImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getode_list()
     * @generated
     */
    EClass ODE_LIST = eINSTANCE.getode_list();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ODE_LIST__ARGUMENTS = eINSTANCE.getode_list_Arguments();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.random_listImpl <em>random list</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.random_listImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getrandom_list()
     * @generated
     */
    EClass RANDOM_LIST = eINSTANCE.getrandom_list();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RANDOM_LIST__ARGUMENTS = eINSTANCE.getrandom_list_Arguments();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.argumentsImpl <em>arguments</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.argumentsImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getarguments()
     * @generated
     */
    EClass ARGUMENTS = eINSTANCE.getarguments();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARGUMENTS__ARGUMENTS = eINSTANCE.getarguments_Arguments();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.argumentImpl <em>argument</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.argumentImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getargument()
     * @generated
     */
    EClass ARGUMENT = eINSTANCE.getargument();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ARGUMENT__IDENTIFIER = eINSTANCE.getargument_Identifier();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARGUMENT__EXPRESSION = eINSTANCE.getargument_Expression();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.statementImpl <em>statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.statementImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getstatement()
     * @generated
     */
    EClass STATEMENT = eINSTANCE.getstatement();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATEMENT__BLOCK = eINSTANCE.getstatement_Block();

    /**
     * The meta object literal for the '<em><b>Par expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATEMENT__PAR_EXPRESSION = eINSTANCE.getstatement_Par_expression();

    /**
     * The meta object literal for the '<em><b>If statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATEMENT__IF_STATEMENT = eINSTANCE.getstatement_If_statement();

    /**
     * The meta object literal for the '<em><b>Else statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STATEMENT__ELSE_STATEMENT = eINSTANCE.getstatement_Else_statement();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.par_expressionImpl <em>par expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.par_expressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getpar_expression()
     * @generated
     */
    EClass PAR_EXPRESSION = eINSTANCE.getpar_expression();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PAR_EXPRESSION__EXPRESSION = eINSTANCE.getpar_expression_Expression();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.conditional_expressionImpl <em>conditional expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.conditional_expressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getconditional_expression()
     * @generated
     */
    EClass CONDITIONAL_EXPRESSION = eINSTANCE.getconditional_expression();

    /**
     * The meta object literal for the '<em><b>Conditional or expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_EXPRESSION__CONDITIONAL_OR_EXPRESSION = eINSTANCE.getconditional_expression_Conditional_or_expression();

    /**
     * The meta object literal for the '<em><b>Expression1</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_EXPRESSION__EXPRESSION1 = eINSTANCE.getconditional_expression_Expression1();

    /**
     * The meta object literal for the '<em><b>Expression2</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_EXPRESSION__EXPRESSION2 = eINSTANCE.getconditional_expression_Expression2();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.conditional_or_expressionImpl <em>conditional or expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.conditional_or_expressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getconditional_or_expression()
     * @generated
     */
    EClass CONDITIONAL_OR_EXPRESSION = eINSTANCE.getconditional_or_expression();

    /**
     * The meta object literal for the '<em><b>Conditional and expression</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_OR_EXPRESSION__CONDITIONAL_AND_EXPRESSION = eINSTANCE.getconditional_or_expression_Conditional_and_expression();

    /**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONDITIONAL_OR_EXPRESSION__OPERATOR = eINSTANCE.getconditional_or_expression_Operator();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.conditional_and_expressionImpl <em>conditional and expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.conditional_and_expressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getconditional_and_expression()
     * @generated
     */
    EClass CONDITIONAL_AND_EXPRESSION = eINSTANCE.getconditional_and_expression();

    /**
     * The meta object literal for the '<em><b>Relational expression</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_AND_EXPRESSION__RELATIONAL_EXPRESSION = eINSTANCE.getconditional_and_expression_Relational_expression();

    /**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONDITIONAL_AND_EXPRESSION__OPERATOR = eINSTANCE.getconditional_and_expression_Operator();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.relational_expressionImpl <em>relational expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.relational_expressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getrelational_expression()
     * @generated
     */
    EClass RELATIONAL_EXPRESSION = eINSTANCE.getrelational_expression();

    /**
     * The meta object literal for the '<em><b>Negation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RELATIONAL_EXPRESSION__NEGATION = eINSTANCE.getrelational_expression_Negation();

    /**
     * The meta object literal for the '<em><b>Boolean</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RELATIONAL_EXPRESSION__BOOLEAN = eINSTANCE.getrelational_expression_Boolean();

    /**
     * The meta object literal for the '<em><b>Additive expression</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RELATIONAL_EXPRESSION__ADDITIVE_EXPRESSION = eINSTANCE.getrelational_expression_Additive_expression();

    /**
     * The meta object literal for the '<em><b>Relational op</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RELATIONAL_EXPRESSION__RELATIONAL_OP = eINSTANCE.getrelational_expression_Relational_op();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.additive_expressionImpl <em>additive expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.additive_expressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getadditive_expression()
     * @generated
     */
    EClass ADDITIVE_EXPRESSION = eINSTANCE.getadditive_expression();

    /**
     * The meta object literal for the '<em><b>Multiplicative expression</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ADDITIVE_EXPRESSION__MULTIPLICATIVE_EXPRESSION = eINSTANCE.getadditive_expression_Multiplicative_expression();

    /**
     * The meta object literal for the '<em><b>Additive op</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ADDITIVE_EXPRESSION__ADDITIVE_OP = eINSTANCE.getadditive_expression_Additive_op();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.multiplicative_expressionImpl <em>multiplicative expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.multiplicative_expressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getmultiplicative_expression()
     * @generated
     */
    EClass MULTIPLICATIVE_EXPRESSION = eINSTANCE.getmultiplicative_expression();

    /**
     * The meta object literal for the '<em><b>Power expression</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MULTIPLICATIVE_EXPRESSION__POWER_EXPRESSION = eINSTANCE.getmultiplicative_expression_Power_expression();

    /**
     * The meta object literal for the '<em><b>Multiplicative op</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OP = eINSTANCE.getmultiplicative_expression_Multiplicative_op();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.power_expressionImpl <em>power expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.power_expressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getpower_expression()
     * @generated
     */
    EClass POWER_EXPRESSION = eINSTANCE.getpower_expression();

    /**
     * The meta object literal for the '<em><b>Unary expression</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POWER_EXPRESSION__UNARY_EXPRESSION = eINSTANCE.getpower_expression_Unary_expression();

    /**
     * The meta object literal for the '<em><b>Power op</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POWER_EXPRESSION__POWER_OP = eINSTANCE.getpower_expression_Power_op();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.unary_expressionImpl <em>unary expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.unary_expressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getunary_expression()
     * @generated
     */
    EClass UNARY_EXPRESSION = eINSTANCE.getunary_expression();

    /**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute UNARY_EXPRESSION__OPERATOR = eINSTANCE.getunary_expression_Operator();

    /**
     * The meta object literal for the '<em><b>Unary expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference UNARY_EXPRESSION__UNARY_EXPRESSION = eINSTANCE.getunary_expression_Unary_expression();

    /**
     * The meta object literal for the '<em><b>Par expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference UNARY_EXPRESSION__PAR_EXPRESSION = eINSTANCE.getunary_expression_Par_expression();

    /**
     * The meta object literal for the '<em><b>Function call</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference UNARY_EXPRESSION__FUNCTION_CALL = eINSTANCE.getunary_expression_Function_call();

    /**
     * The meta object literal for the '<em><b>Primary</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference UNARY_EXPRESSION__PRIMARY = eINSTANCE.getunary_expression_Primary();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.primaryImpl <em>primary</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.primaryImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getprimary()
     * @generated
     */
    EClass PRIMARY = eINSTANCE.getprimary();

    /**
     * The meta object literal for the '<em><b>Literal</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__LITERAL = eINSTANCE.getprimary_Literal();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__IDENTIFIER = eINSTANCE.getprimary_Identifier();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.variable_nameImpl <em>variable name</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.variable_nameImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getvariable_name()
     * @generated
     */
    EClass VARIABLE_NAME = eINSTANCE.getvariable_name();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VARIABLE_NAME__IDENTIFIER = eINSTANCE.getvariable_name_Identifier();

    /**
     * The meta object literal for the '<em><b>Selector</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABLE_NAME__SELECTOR = eINSTANCE.getvariable_name_Selector();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.selectorImpl <em>selector</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.selectorImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getselector()
     * @generated
     */
    EClass SELECTOR = eINSTANCE.getselector();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SELECTOR__EXPRESSION = eINSTANCE.getselector_Expression();

  }

} //MdlPackage
