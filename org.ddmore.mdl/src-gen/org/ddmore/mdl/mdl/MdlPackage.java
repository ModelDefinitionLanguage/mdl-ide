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
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.MclImpl <em>Mcl</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.MclImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getMcl()
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
   * The number of structural features of the '<em>Mcl</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MCL_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.MclObjectImpl <em>Mcl Object</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.MclObjectImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getMclObject()
   * @generated
   */
  int MCL_OBJECT = 1;

  /**
   * The feature id for the '<em><b>Model Object</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MCL_OBJECT__MODEL_OBJECT = 0;

  /**
   * The feature id for the '<em><b>Parameter Object</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MCL_OBJECT__PARAMETER_OBJECT = 1;

  /**
   * The feature id for the '<em><b>Data Object</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MCL_OBJECT__DATA_OBJECT = 2;

  /**
   * The feature id for the '<em><b>Task Object</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MCL_OBJECT__TASK_OBJECT = 3;

  /**
   * The feature id for the '<em><b>Tel Object</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MCL_OBJECT__TEL_OBJECT = 4;

  /**
   * The number of structural features of the '<em>Mcl Object</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MCL_OBJECT_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ModelObjectImpl <em>Model Object</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ModelObjectImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getModelObject()
   * @generated
   */
  int MODEL_OBJECT = 2;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJECT__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Blocks</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJECT__BLOCKS = 1;

  /**
   * The number of structural features of the '<em>Model Object</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJECT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ParameterObjectImpl <em>Parameter Object</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ParameterObjectImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getParameterObject()
   * @generated
   */
  int PARAMETER_OBJECT = 3;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_OBJECT__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Blocks</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_OBJECT__BLOCKS = 1;

  /**
   * The number of structural features of the '<em>Parameter Object</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_OBJECT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.DataObjectImpl <em>Data Object</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.DataObjectImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDataObject()
   * @generated
   */
  int DATA_OBJECT = 4;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_OBJECT__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Blocks</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_OBJECT__BLOCKS = 1;

  /**
   * The number of structural features of the '<em>Data Object</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_OBJECT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.TaskObjectImpl <em>Task Object</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.TaskObjectImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getTaskObject()
   * @generated
   */
  int TASK_OBJECT = 5;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_OBJECT__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Blocks</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_OBJECT__BLOCKS = 1;

  /**
   * The number of structural features of the '<em>Task Object</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_OBJECT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.TELObjectImpl <em>TEL Object</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.TELObjectImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getTELObject()
   * @generated
   */
  int TEL_OBJECT = 6;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEL_OBJECT__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEL_OBJECT__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>TEL Object</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TEL_OBJECT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ModelObjectBlockImpl <em>Model Object Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ModelObjectBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getModelObjectBlock()
   * @generated
   */
  int MODEL_OBJECT_BLOCK = 7;

  /**
   * The feature id for the '<em><b>Individual Variables Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJECT_BLOCK__INDIVIDUAL_VARIABLES_BLOCK = 0;

  /**
   * The feature id for the '<em><b>Model Prediction Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJECT_BLOCK__MODEL_PREDICTION_BLOCK = 1;

  /**
   * The feature id for the '<em><b>Random Variable Definition Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJECT_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK = 2;

  /**
   * The feature id for the '<em><b>Input Variables Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJECT_BLOCK__INPUT_VARIABLES_BLOCK = 3;

  /**
   * The feature id for the '<em><b>Structural Parameters Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJECT_BLOCK__STRUCTURAL_PARAMETERS_BLOCK = 4;

  /**
   * The feature id for the '<em><b>Variability Parameters Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJECT_BLOCK__VARIABILITY_PARAMETERS_BLOCK = 5;

  /**
   * The feature id for the '<em><b>Output Variables Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJECT_BLOCK__OUTPUT_VARIABLES_BLOCK = 6;

  /**
   * The feature id for the '<em><b>Group Variables Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJECT_BLOCK__GROUP_VARIABLES_BLOCK = 7;

  /**
   * The feature id for the '<em><b>Observation Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJECT_BLOCK__OBSERVATION_BLOCK = 8;

  /**
   * The feature id for the '<em><b>Estimation Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJECT_BLOCK__ESTIMATION_BLOCK = 9;

  /**
   * The feature id for the '<em><b>Simulation Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJECT_BLOCK__SIMULATION_BLOCK = 10;

  /**
   * The feature id for the '<em><b>Verbatim Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJECT_BLOCK__VERBATIM_BLOCK = 11;

  /**
   * The number of structural features of the '<em>Model Object Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OBJECT_BLOCK_FEATURE_COUNT = 12;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.IndividualVariablesBlockImpl <em>Individual Variables Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.IndividualVariablesBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getIndividualVariablesBlock()
   * @generated
   */
  int INDIVIDUAL_VARIABLES_BLOCK = 8;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INDIVIDUAL_VARIABLES_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INDIVIDUAL_VARIABLES_BLOCK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Individual Variables Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INDIVIDUAL_VARIABLES_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ModelPredictionBlockImpl <em>Model Prediction Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ModelPredictionBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getModelPredictionBlock()
   * @generated
   */
  int MODEL_PREDICTION_BLOCK = 9;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_PREDICTION_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_PREDICTION_BLOCK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Model Prediction Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_PREDICTION_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.RandomVariableDefinitionBlockImpl <em>Random Variable Definition Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.RandomVariableDefinitionBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getRandomVariableDefinitionBlock()
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
   * The feature id for the '<em><b>Variables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANDOM_VARIABLE_DEFINITION_BLOCK__VARIABLES = 1;

  /**
   * The number of structural features of the '<em>Random Variable Definition Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANDOM_VARIABLE_DEFINITION_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.InputVariablesBlockImpl <em>Input Variables Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.InputVariablesBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getInputVariablesBlock()
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
   * The feature id for the '<em><b>Variables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INPUT_VARIABLES_BLOCK__VARIABLES = 1;

  /**
   * The number of structural features of the '<em>Input Variables Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INPUT_VARIABLES_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.StructuralParametersBlockImpl <em>Structural Parameters Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.StructuralParametersBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getStructuralParametersBlock()
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
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRUCTURAL_PARAMETERS_BLOCK__PARAMETERS = 1;

  /**
   * The number of structural features of the '<em>Structural Parameters Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRUCTURAL_PARAMETERS_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.VariabilityParametersBlockImpl <em>Variability Parameters Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.VariabilityParametersBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getVariabilityParametersBlock()
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
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_PARAMETERS_BLOCK__PARAMETERS = 1;

  /**
   * The number of structural features of the '<em>Variability Parameters Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_PARAMETERS_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.OutputVariablesBlockImpl <em>Output Variables Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.OutputVariablesBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getOutputVariablesBlock()
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
   * The feature id for the '<em><b>Variables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_VARIABLES_BLOCK__VARIABLES = 1;

  /**
   * The number of structural features of the '<em>Output Variables Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OUTPUT_VARIABLES_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.GroupVariablesBlockImpl <em>Group Variables Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.GroupVariablesBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getGroupVariablesBlock()
   * @generated
   */
  int GROUP_VARIABLES_BLOCK = 15;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_VARIABLES_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_VARIABLES_BLOCK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Group Variables Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_VARIABLES_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ObservationBlockImpl <em>Observation Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ObservationBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getObservationBlock()
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
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OBSERVATION_BLOCK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Observation Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OBSERVATION_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.EstimationBlockImpl <em>Estimation Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.EstimationBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getEstimationBlock()
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
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTIMATION_BLOCK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Estimation Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTIMATION_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.SimulationBlockImpl <em>Simulation Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.SimulationBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getSimulationBlock()
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
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMULATION_BLOCK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Simulation Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMULATION_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ParameterObjectBlockImpl <em>Parameter Object Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ParameterObjectBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getParameterObjectBlock()
   * @generated
   */
  int PARAMETER_OBJECT_BLOCK = 19;

  /**
   * The feature id for the '<em><b>Structural Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_OBJECT_BLOCK__STRUCTURAL_BLOCK = 0;

  /**
   * The feature id for the '<em><b>Variability Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_OBJECT_BLOCK__VARIABILITY_BLOCK = 1;

  /**
   * The feature id for the '<em><b>Prior Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_OBJECT_BLOCK__PRIOR_BLOCK = 2;

  /**
   * The feature id for the '<em><b>Verbatim Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_OBJECT_BLOCK__VERBATIM_BLOCK = 3;

  /**
   * The number of structural features of the '<em>Parameter Object Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_OBJECT_BLOCK_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.StructuralBlockImpl <em>Structural Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.StructuralBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getStructuralBlock()
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
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRUCTURAL_BLOCK__PARAMETERS = 1;

  /**
   * The number of structural features of the '<em>Structural Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRUCTURAL_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.VariabilityBlockImpl <em>Variability Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.VariabilityBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getVariabilityBlock()
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
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_BLOCK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Variability Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.PriorParametersBlockImpl <em>Prior Parameters Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.PriorParametersBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getPriorParametersBlock()
   * @generated
   */
  int PRIOR_PARAMETERS_BLOCK = 22;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIOR_PARAMETERS_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIOR_PARAMETERS_BLOCK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Prior Parameters Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIOR_PARAMETERS_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.DataObjectBlockImpl <em>Data Object Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.DataObjectBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDataObjectBlock()
   * @generated
   */
  int DATA_OBJECT_BLOCK = 23;

  /**
   * The feature id for the '<em><b>Header Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_OBJECT_BLOCK__HEADER_BLOCK = 0;

  /**
   * The feature id for the '<em><b>File Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_OBJECT_BLOCK__FILE_BLOCK = 1;

  /**
   * The feature id for the '<em><b>Verbatim Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_OBJECT_BLOCK__VERBATIM_BLOCK = 2;

  /**
   * The number of structural features of the '<em>Data Object Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_OBJECT_BLOCK_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.HeaderBlockImpl <em>Header Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.HeaderBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getHeaderBlock()
   * @generated
   */
  int HEADER_BLOCK = 24;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Variables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_BLOCK__VARIABLES = 1;

  /**
   * The number of structural features of the '<em>Header Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int HEADER_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.FileBlockImpl <em>File Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.FileBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getFileBlock()
   * @generated
   */
  int FILE_BLOCK = 25;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>File Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.TaskObjectBlockImpl <em>Task Object Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.TaskObjectBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getTaskObjectBlock()
   * @generated
   */
  int TASK_OBJECT_BLOCK = 26;

  /**
   * The feature id for the '<em><b>Function Declaration</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_OBJECT_BLOCK__FUNCTION_DECLARATION = 0;

  /**
   * The feature id for the '<em><b>Parameter Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_OBJECT_BLOCK__PARAMETER_BLOCK = 1;

  /**
   * The feature id for the '<em><b>Data Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_OBJECT_BLOCK__DATA_BLOCK = 2;

  /**
   * The feature id for the '<em><b>Model Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_OBJECT_BLOCK__MODEL_BLOCK = 3;

  /**
   * The feature id for the '<em><b>Verbatim Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_OBJECT_BLOCK__VERBATIM_BLOCK = 4;

  /**
   * The number of structural features of the '<em>Task Object Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_OBJECT_BLOCK_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ParameterBlockImpl <em>Parameter Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ParameterBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getParameterBlock()
   * @generated
   */
  int PARAMETER_BLOCK = 27;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_BLOCK__PARAMETERS = 1;

  /**
   * The number of structural features of the '<em>Parameter Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.DataBlockImpl <em>Data Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.DataBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDataBlock()
   * @generated
   */
  int DATA_BLOCK = 28;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_BLOCK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Data Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.DataBlockStatementImpl <em>Data Block Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.DataBlockStatementImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDataBlockStatement()
   * @generated
   */
  int DATA_BLOCK_STATEMENT = 29;

  /**
   * The feature id for the '<em><b>Ignore List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_BLOCK_STATEMENT__IGNORE_LIST = 0;

  /**
   * The feature id for the '<em><b>Accept List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_BLOCK_STATEMENT__ACCEPT_LIST = 1;

  /**
   * The feature id for the '<em><b>Drop List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_BLOCK_STATEMENT__DROP_LIST = 2;

  /**
   * The number of structural features of the '<em>Data Block Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DATA_BLOCK_STATEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.IgnoreListImpl <em>Ignore List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.IgnoreListImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getIgnoreList()
   * @generated
   */
  int IGNORE_LIST = 30;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IGNORE_LIST__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IGNORE_LIST__EXPRESSION = 1;

  /**
   * The number of structural features of the '<em>Ignore List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IGNORE_LIST_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.AcceptListImpl <em>Accept List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.AcceptListImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getAcceptList()
   * @generated
   */
  int ACCEPT_LIST = 31;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACCEPT_LIST__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACCEPT_LIST__EXPRESSION = 1;

  /**
   * The number of structural features of the '<em>Accept List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ACCEPT_LIST_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.DropListImpl <em>Drop List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.DropListImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDropList()
   * @generated
   */
  int DROP_LIST = 32;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DROP_LIST__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DROP_LIST__LIST = 1;

  /**
   * The number of structural features of the '<em>Drop List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DROP_LIST_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ModelBlockImpl <em>Model Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ModelBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getModelBlock()
   * @generated
   */
  int MODEL_BLOCK = 33;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_BLOCK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Model Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ModelBlockStatementImpl <em>Model Block Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ModelBlockStatementImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getModelBlockStatement()
   * @generated
   */
  int MODEL_BLOCK_STATEMENT = 34;

  /**
   * The feature id for the '<em><b>Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_BLOCK_STATEMENT__STATEMENT = 0;

  /**
   * The feature id for the '<em><b>Add List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_BLOCK_STATEMENT__ADD_LIST = 1;

  /**
   * The feature id for the '<em><b>Remove List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_BLOCK_STATEMENT__REMOVE_LIST = 2;

  /**
   * The number of structural features of the '<em>Model Block Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_BLOCK_STATEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.AddListImpl <em>Add List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.AddListImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getAddList()
   * @generated
   */
  int ADD_LIST = 35;

  /**
   * The feature id for the '<em><b>List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADD_LIST__LIST = 0;

  /**
   * The number of structural features of the '<em>Add List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADD_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.RemoveListImpl <em>Remove List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.RemoveListImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getRemoveList()
   * @generated
   */
  int REMOVE_LIST = 36;

  /**
   * The feature id for the '<em><b>List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REMOVE_LIST__LIST = 0;

  /**
   * The number of structural features of the '<em>Remove List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REMOVE_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.SymbolListImpl <em>Symbol List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.SymbolListImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getSymbolList()
   * @generated
   */
  int SYMBOL_LIST = 37;

  /**
   * The feature id for the '<em><b>Symbols</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYMBOL_LIST__SYMBOLS = 0;

  /**
   * The number of structural features of the '<em>Symbol List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYMBOL_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ModelPredictionBlockStatementImpl <em>Model Prediction Block Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ModelPredictionBlockStatementImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getModelPredictionBlockStatement()
   * @generated
   */
  int MODEL_PREDICTION_BLOCK_STATEMENT = 38;

  /**
   * The feature id for the '<em><b>Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_PREDICTION_BLOCK_STATEMENT__STATEMENT = 0;

  /**
   * The feature id for the '<em><b>Ode Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_PREDICTION_BLOCK_STATEMENT__ODE_BLOCK = 1;

  /**
   * The feature id for the '<em><b>Library Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_PREDICTION_BLOCK_STATEMENT__LIBRARY_BLOCK = 2;

  /**
   * The number of structural features of the '<em>Model Prediction Block Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_PREDICTION_BLOCK_STATEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.LibraryBlockImpl <em>Library Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.LibraryBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getLibraryBlock()
   * @generated
   */
  int LIBRARY_BLOCK = 39;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_BLOCK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Library Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.OdeBlockImpl <em>Ode Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.OdeBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getOdeBlock()
   * @generated
   */
  int ODE_BLOCK = 40;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ODE_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ODE_BLOCK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Ode Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ODE_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.GroupVariablesBlockStatementImpl <em>Group Variables Block Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.GroupVariablesBlockStatementImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getGroupVariablesBlockStatement()
   * @generated
   */
  int GROUP_VARIABLES_BLOCK_STATEMENT = 41;

  /**
   * The feature id for the '<em><b>Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_VARIABLES_BLOCK_STATEMENT__STATEMENT = 0;

  /**
   * The feature id for the '<em><b>Mixture Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_VARIABLES_BLOCK_STATEMENT__MIXTURE_BLOCK = 1;

  /**
   * The number of structural features of the '<em>Group Variables Block Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int GROUP_VARIABLES_BLOCK_STATEMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.MixtureBlockImpl <em>Mixture Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.MixtureBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getMixtureBlock()
   * @generated
   */
  int MIXTURE_BLOCK = 42;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MIXTURE_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MIXTURE_BLOCK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Mixture Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MIXTURE_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.VariabilityBlockStatementImpl <em>Variability Block Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.VariabilityBlockStatementImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getVariabilityBlockStatement()
   * @generated
   */
  int VARIABILITY_BLOCK_STATEMENT = 43;

  /**
   * The feature id for the '<em><b>Parameter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_BLOCK_STATEMENT__PARAMETER = 0;

  /**
   * The feature id for the '<em><b>Block Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK = 1;

  /**
   * The feature id for the '<em><b>Diag Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK = 2;

  /**
   * The feature id for the '<em><b>Same Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_BLOCK_STATEMENT__SAME_BLOCK = 3;

  /**
   * The number of structural features of the '<em>Variability Block Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABILITY_BLOCK_STATEMENT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.BlockBlockImpl <em>Block Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.BlockBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getBlockBlock()
   * @generated
   */
  int BLOCK_BLOCK = 44;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_BLOCK__ARGUMENTS = 1;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_BLOCK__PARAMETERS = 2;

  /**
   * The number of structural features of the '<em>Block Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_BLOCK_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.DiagBlockImpl <em>Diag Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.DiagBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDiagBlock()
   * @generated
   */
  int DIAG_BLOCK = 45;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAG_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAG_BLOCK__ARGUMENTS = 1;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAG_BLOCK__PARAMETERS = 2;

  /**
   * The number of structural features of the '<em>Diag Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DIAG_BLOCK_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.SameBlockImpl <em>Same Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.SameBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getSameBlock()
   * @generated
   */
  int SAME_BLOCK = 46;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SAME_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SAME_BLOCK__ARGUMENTS = 1;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SAME_BLOCK__PARAMETERS = 2;

  /**
   * The number of structural features of the '<em>Same Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SAME_BLOCK_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.FileBlockStatementImpl <em>File Block Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.FileBlockStatementImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getFileBlockStatement()
   * @generated
   */
  int FILE_BLOCK_STATEMENT = 47;

  /**
   * The feature id for the '<em><b>Variable</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK_STATEMENT__VARIABLE = 0;

  /**
   * The feature id for the '<em><b>Inline Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK_STATEMENT__INLINE_BLOCK = 1;

  /**
   * The feature id for the '<em><b>Design Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK_STATEMENT__DESIGN_BLOCK = 2;

  /**
   * The feature id for the '<em><b>Rscript Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK_STATEMENT__RSCRIPT_BLOCK = 3;

  /**
   * The number of structural features of the '<em>File Block Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FILE_BLOCK_STATEMENT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.InlineBlockImpl <em>Inline Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.InlineBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getInlineBlock()
   * @generated
   */
  int INLINE_BLOCK = 48;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INLINE_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Variables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INLINE_BLOCK__VARIABLES = 1;

  /**
   * The feature id for the '<em><b>Values</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INLINE_BLOCK__VALUES = 2;

  /**
   * The number of structural features of the '<em>Inline Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INLINE_BLOCK_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.DesignBlockImpl <em>Design Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.DesignBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDesignBlock()
   * @generated
   */
  int DESIGN_BLOCK = 49;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BLOCK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Design Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.DesignBlockStatementImpl <em>Design Block Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.DesignBlockStatementImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDesignBlockStatement()
   * @generated
   */
  int DESIGN_BLOCK_STATEMENT = 50;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BLOCK_STATEMENT__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BLOCK_STATEMENT__ARGUMENTS = 1;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BLOCK_STATEMENT__EXPRESSION = 2;

  /**
   * The number of structural features of the '<em>Design Block Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DESIGN_BLOCK_STATEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.VariableListImpl <em>Variable List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.VariableListImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getVariableList()
   * @generated
   */
  int VARIABLE_LIST = 51;

  /**
   * The feature id for the '<em><b>Identifiers</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_LIST__IDENTIFIERS = 0;

  /**
   * The number of structural features of the '<em>Variable List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.RScriptBlockImpl <em>RScript Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.RScriptBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getRScriptBlock()
   * @generated
   */
  int RSCRIPT_BLOCK = 52;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RSCRIPT_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Variables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RSCRIPT_BLOCK__VARIABLES = 1;

  /**
   * The number of structural features of the '<em>RScript Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RSCRIPT_BLOCK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.RScriptBlockStatementImpl <em>RScript Block Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.RScriptBlockStatementImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getRScriptBlockStatement()
   * @generated
   */
  int RSCRIPT_BLOCK_STATEMENT = 53;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RSCRIPT_BLOCK_STATEMENT__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RSCRIPT_BLOCK_STATEMENT__VALUE = 1;

  /**
   * The feature id for the '<em><b>Object</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RSCRIPT_BLOCK_STATEMENT__OBJECT = 2;

  /**
   * The number of structural features of the '<em>RScript Block Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RSCRIPT_BLOCK_STATEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.TaskFunctionDeclarationImpl <em>Task Function Declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.TaskFunctionDeclarationImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getTaskFunctionDeclaration()
   * @generated
   */
  int TASK_FUNCTION_DECLARATION = 54;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_FUNCTION_DECLARATION__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Formal Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_FUNCTION_DECLARATION__FORMAL_ARGUMENTS = 1;

  /**
   * The feature id for the '<em><b>Function Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_FUNCTION_DECLARATION__FUNCTION_BODY = 2;

  /**
   * The number of structural features of the '<em>Task Function Declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_FUNCTION_DECLARATION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.TaskFunctionBodyImpl <em>Task Function Body</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.TaskFunctionBodyImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getTaskFunctionBody()
   * @generated
   */
  int TASK_FUNCTION_BODY = 55;

  /**
   * The feature id for the '<em><b>Blocks</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_FUNCTION_BODY__BLOCKS = 0;

  /**
   * The number of structural features of the '<em>Task Function Body</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_FUNCTION_BODY_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.TaskFunctionBlockImpl <em>Task Function Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.TaskFunctionBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getTaskFunctionBlock()
   * @generated
   */
  int TASK_FUNCTION_BLOCK = 56;

  /**
   * The feature id for the '<em><b>Estimate Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_FUNCTION_BLOCK__ESTIMATE_BLOCK = 0;

  /**
   * The feature id for the '<em><b>Simulate Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_FUNCTION_BLOCK__SIMULATE_BLOCK = 1;

  /**
   * The feature id for the '<em><b>Execute Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_FUNCTION_BLOCK__EXECUTE_BLOCK = 2;

  /**
   * The number of structural features of the '<em>Task Function Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TASK_FUNCTION_BLOCK_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.EstimateTaskImpl <em>Estimate Task</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.EstimateTaskImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getEstimateTask()
   * @generated
   */
  int ESTIMATE_TASK = 57;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTIMATE_TASK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTIMATE_TASK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Estimate Task</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ESTIMATE_TASK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.SimulateTaskImpl <em>Simulate Task</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.SimulateTaskImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getSimulateTask()
   * @generated
   */
  int SIMULATE_TASK = 58;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMULATE_TASK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMULATE_TASK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Simulate Task</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SIMULATE_TASK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ExecuteTaskImpl <em>Execute Task</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ExecuteTaskImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getExecuteTask()
   * @generated
   */
  int EXECUTE_TASK = 59;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXECUTE_TASK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXECUTE_TASK__STATEMENTS = 1;

  /**
   * The number of structural features of the '<em>Execute Task</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXECUTE_TASK_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.FormalArgumentsImpl <em>Formal Arguments</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.FormalArgumentsImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getFormalArguments()
   * @generated
   */
  int FORMAL_ARGUMENTS = 60;

  /**
   * The feature id for the '<em><b>Identifiers</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORMAL_ARGUMENTS__IDENTIFIERS = 0;

  /**
   * The number of structural features of the '<em>Formal Arguments</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FORMAL_ARGUMENTS_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.FunctionCallImpl <em>Function Call</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.FunctionCallImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getFunctionCall()
   * @generated
   */
  int FUNCTION_CALL = 61;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL__ARGUMENTS = 1;

  /**
   * The number of structural features of the '<em>Function Call</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FUNCTION_CALL_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.BlockStatementImpl <em>Block Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.BlockStatementImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getBlockStatement()
   * @generated
   */
  int BLOCK_STATEMENT = 62;

  /**
   * The feature id for the '<em><b>Symbol</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_STATEMENT__SYMBOL = 0;

  /**
   * The feature id for the '<em><b>Function Call</b></em>' containment reference.
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
   * The feature id for the '<em><b>Verbatim Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_STATEMENT__VERBATIM_BLOCK = 3;

  /**
   * The number of structural features of the '<em>Block Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_STATEMENT_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.VerbatimBlockImpl <em>Verbatim Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.VerbatimBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getVerbatimBlock()
   * @generated
   */
  int VERBATIM_BLOCK = 63;

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
   * The feature id for the '<em><b>External Code</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERBATIM_BLOCK__EXTERNAL_CODE = 2;

  /**
   * The number of structural features of the '<em>Verbatim Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VERBATIM_BLOCK_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.TargetBlockImpl <em>Target Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.TargetBlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getTargetBlock()
   * @generated
   */
  int TARGET_BLOCK = 64;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TARGET_BLOCK__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TARGET_BLOCK__ARGUMENTS = 1;

  /**
   * The feature id for the '<em><b>External Code</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TARGET_BLOCK__EXTERNAL_CODE = 2;

  /**
   * The number of structural features of the '<em>Target Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TARGET_BLOCK_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.SymbolModificationImpl <em>Symbol Modification</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.SymbolModificationImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getSymbolModification()
   * @generated
   */
  int SYMBOL_MODIFICATION = 65;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYMBOL_MODIFICATION__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYMBOL_MODIFICATION__LIST = 1;

  /**
   * The number of structural features of the '<em>Symbol Modification</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYMBOL_MODIFICATION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ParameterDeclarationImpl <em>Parameter Declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ParameterDeclarationImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getParameterDeclaration()
   * @generated
   */
  int PARAMETER_DECLARATION = 66;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_DECLARATION__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_DECLARATION__LIST = 1;

  /**
   * The number of structural features of the '<em>Parameter Declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PARAMETER_DECLARATION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.SymbolDeclarationImpl <em>Symbol Declaration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.SymbolDeclarationImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getSymbolDeclaration()
   * @generated
   */
  int SYMBOL_DECLARATION = 67;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYMBOL_DECLARATION__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYMBOL_DECLARATION__EXPRESSION = 1;

  /**
   * The feature id for the '<em><b>Random List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYMBOL_DECLARATION__RANDOM_LIST = 2;

  /**
   * The feature id for the '<em><b>Function</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYMBOL_DECLARATION__FUNCTION = 3;

  /**
   * The number of structural features of the '<em>Symbol Declaration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SYMBOL_DECLARATION_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.EnumTypeImpl <em>Enum Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.EnumTypeImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getEnumType()
   * @generated
   */
  int ENUM_TYPE = 68;

  /**
   * The feature id for the '<em><b>Categorical</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_TYPE__CATEGORICAL = 0;

  /**
   * The feature id for the '<em><b>Continuous</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_TYPE__CONTINUOUS = 1;

  /**
   * The feature id for the '<em><b>Covariate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_TYPE__COVARIATE = 2;

  /**
   * The feature id for the '<em><b>Distribution</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_TYPE__DISTRIBUTION = 3;

  /**
   * The feature id for the '<em><b>Level</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_TYPE__LEVEL = 4;

  /**
   * The feature id for the '<em><b>Likelyhood</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_TYPE__LIKELYHOOD = 5;

  /**
   * The feature id for the '<em><b>Missing</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_TYPE__MISSING = 6;

  /**
   * The number of structural features of the '<em>Enum Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_TYPE_FEATURE_COUNT = 7;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.MissingImpl <em>Missing</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.MissingImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getMissing()
   * @generated
   */
  int MISSING = 69;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MISSING__IDENTIFIER = 0;

  /**
   * The number of structural features of the '<em>Missing</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MISSING_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.LikelyhoodImpl <em>Likelyhood</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.LikelyhoodImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getLikelyhood()
   * @generated
   */
  int LIKELYHOOD = 70;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIKELYHOOD__IDENTIFIER = 0;

  /**
   * The number of structural features of the '<em>Likelyhood</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIKELYHOOD_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.LevelTypeImpl <em>Level Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.LevelTypeImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getLevelType()
   * @generated
   */
  int LEVEL_TYPE = 71;

  /**
   * The feature id for the '<em><b>Mdv</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEVEL_TYPE__MDV = 0;

  /**
   * The feature id for the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEVEL_TYPE__ID = 1;

  /**
   * The feature id for the '<em><b>Dv</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEVEL_TYPE__DV = 2;

  /**
   * The feature id for the '<em><b>Idv</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEVEL_TYPE__IDV = 3;

  /**
   * The number of structural features of the '<em>Level Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LEVEL_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.CategoricalImpl <em>Categorical</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.CategoricalImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getCategorical()
   * @generated
   */
  int CATEGORICAL = 72;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORICAL__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORICAL__ARGUMENTS = 1;

  /**
   * The number of structural features of the '<em>Categorical</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CATEGORICAL_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ContinuousImpl <em>Continuous</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ContinuousImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getContinuous()
   * @generated
   */
  int CONTINUOUS = 73;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTINUOUS__IDENTIFIER = 0;

  /**
   * The number of structural features of the '<em>Continuous</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONTINUOUS_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.CovariateImpl <em>Covariate</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.CovariateImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getCovariate()
   * @generated
   */
  int COVARIATE = 74;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COVARIATE__IDENTIFIER = 0;

  /**
   * The number of structural features of the '<em>Covariate</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COVARIATE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.DistributionImpl <em>Distribution</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.DistributionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDistribution()
   * @generated
   */
  int DISTRIBUTION = 75;

  /**
   * The feature id for the '<em><b>Normal</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DISTRIBUTION__NORMAL = 0;

  /**
   * The feature id for the '<em><b>Binomial</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DISTRIBUTION__BINOMIAL = 1;

  /**
   * The feature id for the '<em><b>Poisson</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DISTRIBUTION__POISSON = 2;

  /**
   * The feature id for the '<em><b>Student t</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DISTRIBUTION__STUDENT_T = 3;

  /**
   * The feature id for the '<em><b>Mvnormal</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DISTRIBUTION__MVNORMAL = 4;

  /**
   * The number of structural features of the '<em>Distribution</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int DISTRIBUTION_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.AnyExpressionImpl <em>Any Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.AnyExpressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getAnyExpression()
   * @generated
   */
  int ANY_EXPRESSION = 76;

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
   * The feature id for the '<em><b>Ode List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_EXPRESSION__ODE_LIST = 2;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_EXPRESSION__TYPE = 3;

  /**
   * The number of structural features of the '<em>Any Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ANY_EXPRESSION_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ExpressionImpl <em>Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ExpressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getExpression()
   * @generated
   */
  int EXPRESSION = 77;

  /**
   * The feature id for the '<em><b>Conditional Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__CONDITIONAL_EXPRESSION = 0;

  /**
   * The number of structural features of the '<em>Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ListImpl <em>List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ListImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getList()
   * @generated
   */
  int LIST = 78;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST__ARGUMENTS = 0;

  /**
   * The number of structural features of the '<em>List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.OdeListImpl <em>Ode List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.OdeListImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getOdeList()
   * @generated
   */
  int ODE_LIST = 79;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ODE_LIST__ARGUMENTS = 0;

  /**
   * The number of structural features of the '<em>Ode List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ODE_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.RandomListImpl <em>Random List</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.RandomListImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getRandomList()
   * @generated
   */
  int RANDOM_LIST = 80;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANDOM_LIST__ARGUMENTS = 0;

  /**
   * The number of structural features of the '<em>Random List</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int RANDOM_LIST_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ArgumentsImpl <em>Arguments</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ArgumentsImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getArguments()
   * @generated
   */
  int ARGUMENTS = 81;

  /**
   * The feature id for the '<em><b>Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARGUMENTS__ARGUMENTS = 0;

  /**
   * The number of structural features of the '<em>Arguments</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARGUMENTS_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ArgumentImpl <em>Argument</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ArgumentImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getArgument()
   * @generated
   */
  int ARGUMENT = 82;

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
   * The number of structural features of the '<em>Argument</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ARGUMENT_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ConditionalStatementImpl <em>Conditional Statement</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ConditionalStatementImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getConditionalStatement()
   * @generated
   */
  int CONDITIONAL_STATEMENT = 83;

  /**
   * The feature id for the '<em><b>Par Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_STATEMENT__PAR_EXPRESSION = 0;

  /**
   * The feature id for the '<em><b>If Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_STATEMENT__IF_STATEMENT = 1;

  /**
   * The feature id for the '<em><b>If Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_STATEMENT__IF_BLOCK = 2;

  /**
   * The feature id for the '<em><b>Else Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_STATEMENT__ELSE_STATEMENT = 3;

  /**
   * The feature id for the '<em><b>Else Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_STATEMENT__ELSE_BLOCK = 4;

  /**
   * The number of structural features of the '<em>Conditional Statement</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_STATEMENT_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.BlockImpl <em>Block</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.BlockImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getBlock()
   * @generated
   */
  int BLOCK = 84;

  /**
   * The feature id for the '<em><b>Statements</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK__STATEMENTS = 0;

  /**
   * The number of structural features of the '<em>Block</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BLOCK_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ParExpressionImpl <em>Par Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ParExpressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getParExpression()
   * @generated
   */
  int PAR_EXPRESSION = 85;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PAR_EXPRESSION__EXPRESSION = 0;

  /**
   * The number of structural features of the '<em>Par Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PAR_EXPRESSION_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ConditionalExpressionImpl <em>Conditional Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ConditionalExpressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getConditionalExpression()
   * @generated
   */
  int CONDITIONAL_EXPRESSION = 86;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EXPRESSION__EXPRESSION = 0;

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
   * The number of structural features of the '<em>Conditional Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONDITIONAL_EXPRESSION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.OrExpressionImpl <em>Or Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.OrExpressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getOrExpression()
   * @generated
   */
  int OR_EXPRESSION = 87;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_EXPRESSION__EXPRESSION = 0;

  /**
   * The feature id for the '<em><b>Operator</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_EXPRESSION__OPERATOR = 1;

  /**
   * The number of structural features of the '<em>Or Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OR_EXPRESSION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.AndExpressionImpl <em>And Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.AndExpressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getAndExpression()
   * @generated
   */
  int AND_EXPRESSION = 88;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AND_EXPRESSION__EXPRESSION = 0;

  /**
   * The feature id for the '<em><b>Operator</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AND_EXPRESSION__OPERATOR = 1;

  /**
   * The number of structural features of the '<em>And Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AND_EXPRESSION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.LogicalExpressionImpl <em>Logical Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.LogicalExpressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getLogicalExpression()
   * @generated
   */
  int LOGICAL_EXPRESSION = 89;

  /**
   * The feature id for the '<em><b>Negation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOGICAL_EXPRESSION__NEGATION = 0;

  /**
   * The feature id for the '<em><b>Boolean</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOGICAL_EXPRESSION__BOOLEAN = 1;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOGICAL_EXPRESSION__EXPRESSION = 2;

  /**
   * The feature id for the '<em><b>Operator</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOGICAL_EXPRESSION__OPERATOR = 3;

  /**
   * The number of structural features of the '<em>Logical Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOGICAL_EXPRESSION_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.AdditiveExpressionImpl <em>Additive Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.AdditiveExpressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getAdditiveExpression()
   * @generated
   */
  int ADDITIVE_EXPRESSION = 90;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADDITIVE_EXPRESSION__EXPRESSION = 0;

  /**
   * The feature id for the '<em><b>Operator</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADDITIVE_EXPRESSION__OPERATOR = 1;

  /**
   * The feature id for the '<em><b>String</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADDITIVE_EXPRESSION__STRING = 2;

  /**
   * The number of structural features of the '<em>Additive Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADDITIVE_EXPRESSION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.MultiplicativeExpressionImpl <em>Multiplicative Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.MultiplicativeExpressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getMultiplicativeExpression()
   * @generated
   */
  int MULTIPLICATIVE_EXPRESSION = 91;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MULTIPLICATIVE_EXPRESSION__EXPRESSION = 0;

  /**
   * The feature id for the '<em><b>Operator</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MULTIPLICATIVE_EXPRESSION__OPERATOR = 1;

  /**
   * The number of structural features of the '<em>Multiplicative Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MULTIPLICATIVE_EXPRESSION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.PowerExpressionImpl <em>Power Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.PowerExpressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getPowerExpression()
   * @generated
   */
  int POWER_EXPRESSION = 92;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POWER_EXPRESSION__EXPRESSION = 0;

  /**
   * The feature id for the '<em><b>Operator</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POWER_EXPRESSION__OPERATOR = 1;

  /**
   * The number of structural features of the '<em>Power Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int POWER_EXPRESSION_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.UnaryExpressionImpl <em>Unary Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.UnaryExpressionImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getUnaryExpression()
   * @generated
   */
  int UNARY_EXPRESSION = 93;

  /**
   * The feature id for the '<em><b>Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPRESSION__OPERATOR = 0;

  /**
   * The feature id for the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPRESSION__EXPRESSION = 1;

  /**
   * The feature id for the '<em><b>Par Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPRESSION__PAR_EXPRESSION = 2;

  /**
   * The feature id for the '<em><b>Primary</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPRESSION__PRIMARY = 3;

  /**
   * The number of structural features of the '<em>Unary Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNARY_EXPRESSION_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.PrimaryImpl <em>Primary</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.PrimaryImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getPrimary()
   * @generated
   */
  int PRIMARY = 94;

  /**
   * The feature id for the '<em><b>Function Call</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__FUNCTION_CALL = 0;

  /**
   * The feature id for the '<em><b>Number</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__NUMBER = 1;

  /**
   * The feature id for the '<em><b>Symbol</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__SYMBOL = 2;

  /**
   * The feature id for the '<em><b>Attribute</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__ATTRIBUTE = 3;

  /**
   * The feature id for the '<em><b>Vector</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY__VECTOR = 4;

  /**
   * The number of structural features of the '<em>Primary</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMARY_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.VectorImpl <em>Vector</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.VectorImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getVector()
   * @generated
   */
  int VECTOR = 95;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VECTOR__IDENTIFIER = 0;

  /**
   * The feature id for the '<em><b>Values</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VECTOR__VALUES = 1;

  /**
   * The number of structural features of the '<em>Vector</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VECTOR_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.FullyQualifiedSymbolNameImpl <em>Fully Qualified Symbol Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.FullyQualifiedSymbolNameImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getFullyQualifiedSymbolName()
   * @generated
   */
  int FULLY_QUALIFIED_SYMBOL_NAME = 96;

  /**
   * The feature id for the '<em><b>Object</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FULLY_QUALIFIED_SYMBOL_NAME__OBJECT = 0;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FULLY_QUALIFIED_SYMBOL_NAME__IDENTIFIER = 1;

  /**
   * The number of structural features of the '<em>Fully Qualified Symbol Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FULLY_QUALIFIED_SYMBOL_NAME_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.FullyQualifiedArgumentNameImpl <em>Fully Qualified Argument Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.FullyQualifiedArgumentNameImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getFullyQualifiedArgumentName()
   * @generated
   */
  int FULLY_QUALIFIED_ARGUMENT_NAME = 97;

  /**
   * The feature id for the '<em><b>Parent</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FULLY_QUALIFIED_ARGUMENT_NAME__PARENT = 0;

  /**
   * The feature id for the '<em><b>Identifier</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FULLY_QUALIFIED_ARGUMENT_NAME__IDENTIFIER = 1;

  /**
   * The number of structural features of the '<em>Fully Qualified Argument Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FULLY_QUALIFIED_ARGUMENT_NAME_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.ddmore.mdl.mdl.impl.ObjectNameImpl <em>Object Name</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.ddmore.mdl.mdl.impl.ObjectNameImpl
   * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getObjectName()
   * @generated
   */
  int OBJECT_NAME = 98;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OBJECT_NAME__NAME = 0;

  /**
   * The number of structural features of the '<em>Object Name</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OBJECT_NAME_FEATURE_COUNT = 1;


  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.Mcl <em>Mcl</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mcl</em>'.
   * @see org.ddmore.mdl.mdl.Mcl
   * @generated
   */
  EClass getMcl();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.Mcl#getObjects <em>Objects</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Objects</em>'.
   * @see org.ddmore.mdl.mdl.Mcl#getObjects()
   * @see #getMcl()
   * @generated
   */
  EReference getMcl_Objects();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.MclObject <em>Mcl Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mcl Object</em>'.
   * @see org.ddmore.mdl.mdl.MclObject
   * @generated
   */
  EClass getMclObject();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.MclObject#getModelObject <em>Model Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Model Object</em>'.
   * @see org.ddmore.mdl.mdl.MclObject#getModelObject()
   * @see #getMclObject()
   * @generated
   */
  EReference getMclObject_ModelObject();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.MclObject#getParameterObject <em>Parameter Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameter Object</em>'.
   * @see org.ddmore.mdl.mdl.MclObject#getParameterObject()
   * @see #getMclObject()
   * @generated
   */
  EReference getMclObject_ParameterObject();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.MclObject#getDataObject <em>Data Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Data Object</em>'.
   * @see org.ddmore.mdl.mdl.MclObject#getDataObject()
   * @see #getMclObject()
   * @generated
   */
  EReference getMclObject_DataObject();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.MclObject#getTaskObject <em>Task Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Task Object</em>'.
   * @see org.ddmore.mdl.mdl.MclObject#getTaskObject()
   * @see #getMclObject()
   * @generated
   */
  EReference getMclObject_TaskObject();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.MclObject#getTelObject <em>Tel Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Tel Object</em>'.
   * @see org.ddmore.mdl.mdl.MclObject#getTelObject()
   * @see #getMclObject()
   * @generated
   */
  EReference getMclObject_TelObject();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ModelObject <em>Model Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model Object</em>'.
   * @see org.ddmore.mdl.mdl.ModelObject
   * @generated
   */
  EClass getModelObject();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelObject#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.ModelObject#getIdentifier()
   * @see #getModelObject()
   * @generated
   */
  EReference getModelObject_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.ModelObject#getBlocks <em>Blocks</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Blocks</em>'.
   * @see org.ddmore.mdl.mdl.ModelObject#getBlocks()
   * @see #getModelObject()
   * @generated
   */
  EReference getModelObject_Blocks();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ParameterObject <em>Parameter Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter Object</em>'.
   * @see org.ddmore.mdl.mdl.ParameterObject
   * @generated
   */
  EClass getParameterObject();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ParameterObject#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.ParameterObject#getIdentifier()
   * @see #getParameterObject()
   * @generated
   */
  EReference getParameterObject_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.ParameterObject#getBlocks <em>Blocks</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Blocks</em>'.
   * @see org.ddmore.mdl.mdl.ParameterObject#getBlocks()
   * @see #getParameterObject()
   * @generated
   */
  EReference getParameterObject_Blocks();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.DataObject <em>Data Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Data Object</em>'.
   * @see org.ddmore.mdl.mdl.DataObject
   * @generated
   */
  EClass getDataObject();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.DataObject#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.DataObject#getIdentifier()
   * @see #getDataObject()
   * @generated
   */
  EReference getDataObject_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.DataObject#getBlocks <em>Blocks</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Blocks</em>'.
   * @see org.ddmore.mdl.mdl.DataObject#getBlocks()
   * @see #getDataObject()
   * @generated
   */
  EReference getDataObject_Blocks();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.TaskObject <em>Task Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Task Object</em>'.
   * @see org.ddmore.mdl.mdl.TaskObject
   * @generated
   */
  EClass getTaskObject();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.TaskObject#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.TaskObject#getIdentifier()
   * @see #getTaskObject()
   * @generated
   */
  EReference getTaskObject_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.TaskObject#getBlocks <em>Blocks</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Blocks</em>'.
   * @see org.ddmore.mdl.mdl.TaskObject#getBlocks()
   * @see #getTaskObject()
   * @generated
   */
  EReference getTaskObject_Blocks();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.TELObject <em>TEL Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>TEL Object</em>'.
   * @see org.ddmore.mdl.mdl.TELObject
   * @generated
   */
  EClass getTELObject();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.TELObject#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.TELObject#getIdentifier()
   * @see #getTELObject()
   * @generated
   */
  EReference getTELObject_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.TELObject#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.TELObject#getStatements()
   * @see #getTELObject()
   * @generated
   */
  EReference getTELObject_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ModelObjectBlock <em>Model Object Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model Object Block</em>'.
   * @see org.ddmore.mdl.mdl.ModelObjectBlock
   * @generated
   */
  EClass getModelObjectBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelObjectBlock#getIndividualVariablesBlock <em>Individual Variables Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Individual Variables Block</em>'.
   * @see org.ddmore.mdl.mdl.ModelObjectBlock#getIndividualVariablesBlock()
   * @see #getModelObjectBlock()
   * @generated
   */
  EReference getModelObjectBlock_IndividualVariablesBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelObjectBlock#getModelPredictionBlock <em>Model Prediction Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Model Prediction Block</em>'.
   * @see org.ddmore.mdl.mdl.ModelObjectBlock#getModelPredictionBlock()
   * @see #getModelObjectBlock()
   * @generated
   */
  EReference getModelObjectBlock_ModelPredictionBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelObjectBlock#getRandomVariableDefinitionBlock <em>Random Variable Definition Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Random Variable Definition Block</em>'.
   * @see org.ddmore.mdl.mdl.ModelObjectBlock#getRandomVariableDefinitionBlock()
   * @see #getModelObjectBlock()
   * @generated
   */
  EReference getModelObjectBlock_RandomVariableDefinitionBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelObjectBlock#getInputVariablesBlock <em>Input Variables Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Input Variables Block</em>'.
   * @see org.ddmore.mdl.mdl.ModelObjectBlock#getInputVariablesBlock()
   * @see #getModelObjectBlock()
   * @generated
   */
  EReference getModelObjectBlock_InputVariablesBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelObjectBlock#getStructuralParametersBlock <em>Structural Parameters Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Structural Parameters Block</em>'.
   * @see org.ddmore.mdl.mdl.ModelObjectBlock#getStructuralParametersBlock()
   * @see #getModelObjectBlock()
   * @generated
   */
  EReference getModelObjectBlock_StructuralParametersBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelObjectBlock#getVariabilityParametersBlock <em>Variability Parameters Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Variability Parameters Block</em>'.
   * @see org.ddmore.mdl.mdl.ModelObjectBlock#getVariabilityParametersBlock()
   * @see #getModelObjectBlock()
   * @generated
   */
  EReference getModelObjectBlock_VariabilityParametersBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelObjectBlock#getOutputVariablesBlock <em>Output Variables Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Output Variables Block</em>'.
   * @see org.ddmore.mdl.mdl.ModelObjectBlock#getOutputVariablesBlock()
   * @see #getModelObjectBlock()
   * @generated
   */
  EReference getModelObjectBlock_OutputVariablesBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelObjectBlock#getGroupVariablesBlock <em>Group Variables Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Group Variables Block</em>'.
   * @see org.ddmore.mdl.mdl.ModelObjectBlock#getGroupVariablesBlock()
   * @see #getModelObjectBlock()
   * @generated
   */
  EReference getModelObjectBlock_GroupVariablesBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelObjectBlock#getObservationBlock <em>Observation Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Observation Block</em>'.
   * @see org.ddmore.mdl.mdl.ModelObjectBlock#getObservationBlock()
   * @see #getModelObjectBlock()
   * @generated
   */
  EReference getModelObjectBlock_ObservationBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelObjectBlock#getEstimationBlock <em>Estimation Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Estimation Block</em>'.
   * @see org.ddmore.mdl.mdl.ModelObjectBlock#getEstimationBlock()
   * @see #getModelObjectBlock()
   * @generated
   */
  EReference getModelObjectBlock_EstimationBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelObjectBlock#getSimulationBlock <em>Simulation Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Simulation Block</em>'.
   * @see org.ddmore.mdl.mdl.ModelObjectBlock#getSimulationBlock()
   * @see #getModelObjectBlock()
   * @generated
   */
  EReference getModelObjectBlock_SimulationBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelObjectBlock#getVerbatimBlock <em>Verbatim Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Verbatim Block</em>'.
   * @see org.ddmore.mdl.mdl.ModelObjectBlock#getVerbatimBlock()
   * @see #getModelObjectBlock()
   * @generated
   */
  EReference getModelObjectBlock_VerbatimBlock();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.IndividualVariablesBlock <em>Individual Variables Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Individual Variables Block</em>'.
   * @see org.ddmore.mdl.mdl.IndividualVariablesBlock
   * @generated
   */
  EClass getIndividualVariablesBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.IndividualVariablesBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.IndividualVariablesBlock#getIdentifier()
   * @see #getIndividualVariablesBlock()
   * @generated
   */
  EAttribute getIndividualVariablesBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.IndividualVariablesBlock#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.IndividualVariablesBlock#getStatements()
   * @see #getIndividualVariablesBlock()
   * @generated
   */
  EReference getIndividualVariablesBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ModelPredictionBlock <em>Model Prediction Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model Prediction Block</em>'.
   * @see org.ddmore.mdl.mdl.ModelPredictionBlock
   * @generated
   */
  EClass getModelPredictionBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.ModelPredictionBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.ModelPredictionBlock#getIdentifier()
   * @see #getModelPredictionBlock()
   * @generated
   */
  EAttribute getModelPredictionBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.ModelPredictionBlock#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.ModelPredictionBlock#getStatements()
   * @see #getModelPredictionBlock()
   * @generated
   */
  EReference getModelPredictionBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.RandomVariableDefinitionBlock <em>Random Variable Definition Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Random Variable Definition Block</em>'.
   * @see org.ddmore.mdl.mdl.RandomVariableDefinitionBlock
   * @generated
   */
  EClass getRandomVariableDefinitionBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.RandomVariableDefinitionBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.RandomVariableDefinitionBlock#getIdentifier()
   * @see #getRandomVariableDefinitionBlock()
   * @generated
   */
  EAttribute getRandomVariableDefinitionBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.RandomVariableDefinitionBlock#getVariables <em>Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variables</em>'.
   * @see org.ddmore.mdl.mdl.RandomVariableDefinitionBlock#getVariables()
   * @see #getRandomVariableDefinitionBlock()
   * @generated
   */
  EReference getRandomVariableDefinitionBlock_Variables();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.InputVariablesBlock <em>Input Variables Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Input Variables Block</em>'.
   * @see org.ddmore.mdl.mdl.InputVariablesBlock
   * @generated
   */
  EClass getInputVariablesBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.InputVariablesBlock#getIndentifier <em>Indentifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Indentifier</em>'.
   * @see org.ddmore.mdl.mdl.InputVariablesBlock#getIndentifier()
   * @see #getInputVariablesBlock()
   * @generated
   */
  EAttribute getInputVariablesBlock_Indentifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.InputVariablesBlock#getVariables <em>Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variables</em>'.
   * @see org.ddmore.mdl.mdl.InputVariablesBlock#getVariables()
   * @see #getInputVariablesBlock()
   * @generated
   */
  EReference getInputVariablesBlock_Variables();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.StructuralParametersBlock <em>Structural Parameters Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Structural Parameters Block</em>'.
   * @see org.ddmore.mdl.mdl.StructuralParametersBlock
   * @generated
   */
  EClass getStructuralParametersBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.StructuralParametersBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.StructuralParametersBlock#getIdentifier()
   * @see #getStructuralParametersBlock()
   * @generated
   */
  EAttribute getStructuralParametersBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.StructuralParametersBlock#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see org.ddmore.mdl.mdl.StructuralParametersBlock#getParameters()
   * @see #getStructuralParametersBlock()
   * @generated
   */
  EReference getStructuralParametersBlock_Parameters();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.VariabilityParametersBlock <em>Variability Parameters Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Variability Parameters Block</em>'.
   * @see org.ddmore.mdl.mdl.VariabilityParametersBlock
   * @generated
   */
  EClass getVariabilityParametersBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.VariabilityParametersBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.VariabilityParametersBlock#getIdentifier()
   * @see #getVariabilityParametersBlock()
   * @generated
   */
  EAttribute getVariabilityParametersBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.VariabilityParametersBlock#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see org.ddmore.mdl.mdl.VariabilityParametersBlock#getParameters()
   * @see #getVariabilityParametersBlock()
   * @generated
   */
  EReference getVariabilityParametersBlock_Parameters();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.OutputVariablesBlock <em>Output Variables Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Output Variables Block</em>'.
   * @see org.ddmore.mdl.mdl.OutputVariablesBlock
   * @generated
   */
  EClass getOutputVariablesBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.OutputVariablesBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.OutputVariablesBlock#getIdentifier()
   * @see #getOutputVariablesBlock()
   * @generated
   */
  EAttribute getOutputVariablesBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.OutputVariablesBlock#getVariables <em>Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variables</em>'.
   * @see org.ddmore.mdl.mdl.OutputVariablesBlock#getVariables()
   * @see #getOutputVariablesBlock()
   * @generated
   */
  EReference getOutputVariablesBlock_Variables();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.GroupVariablesBlock <em>Group Variables Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Group Variables Block</em>'.
   * @see org.ddmore.mdl.mdl.GroupVariablesBlock
   * @generated
   */
  EClass getGroupVariablesBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.GroupVariablesBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.GroupVariablesBlock#getIdentifier()
   * @see #getGroupVariablesBlock()
   * @generated
   */
  EAttribute getGroupVariablesBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.GroupVariablesBlock#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.GroupVariablesBlock#getStatements()
   * @see #getGroupVariablesBlock()
   * @generated
   */
  EReference getGroupVariablesBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ObservationBlock <em>Observation Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Observation Block</em>'.
   * @see org.ddmore.mdl.mdl.ObservationBlock
   * @generated
   */
  EClass getObservationBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.ObservationBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.ObservationBlock#getIdentifier()
   * @see #getObservationBlock()
   * @generated
   */
  EAttribute getObservationBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.ObservationBlock#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.ObservationBlock#getStatements()
   * @see #getObservationBlock()
   * @generated
   */
  EReference getObservationBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.EstimationBlock <em>Estimation Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Estimation Block</em>'.
   * @see org.ddmore.mdl.mdl.EstimationBlock
   * @generated
   */
  EClass getEstimationBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.EstimationBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.EstimationBlock#getIdentifier()
   * @see #getEstimationBlock()
   * @generated
   */
  EAttribute getEstimationBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.EstimationBlock#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.EstimationBlock#getStatements()
   * @see #getEstimationBlock()
   * @generated
   */
  EReference getEstimationBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.SimulationBlock <em>Simulation Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Simulation Block</em>'.
   * @see org.ddmore.mdl.mdl.SimulationBlock
   * @generated
   */
  EClass getSimulationBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.SimulationBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.SimulationBlock#getIdentifier()
   * @see #getSimulationBlock()
   * @generated
   */
  EAttribute getSimulationBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.SimulationBlock#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.SimulationBlock#getStatements()
   * @see #getSimulationBlock()
   * @generated
   */
  EReference getSimulationBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ParameterObjectBlock <em>Parameter Object Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter Object Block</em>'.
   * @see org.ddmore.mdl.mdl.ParameterObjectBlock
   * @generated
   */
  EClass getParameterObjectBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ParameterObjectBlock#getStructuralBlock <em>Structural Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Structural Block</em>'.
   * @see org.ddmore.mdl.mdl.ParameterObjectBlock#getStructuralBlock()
   * @see #getParameterObjectBlock()
   * @generated
   */
  EReference getParameterObjectBlock_StructuralBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ParameterObjectBlock#getVariabilityBlock <em>Variability Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Variability Block</em>'.
   * @see org.ddmore.mdl.mdl.ParameterObjectBlock#getVariabilityBlock()
   * @see #getParameterObjectBlock()
   * @generated
   */
  EReference getParameterObjectBlock_VariabilityBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ParameterObjectBlock#getPriorBlock <em>Prior Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Prior Block</em>'.
   * @see org.ddmore.mdl.mdl.ParameterObjectBlock#getPriorBlock()
   * @see #getParameterObjectBlock()
   * @generated
   */
  EReference getParameterObjectBlock_PriorBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ParameterObjectBlock#getVerbatimBlock <em>Verbatim Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Verbatim Block</em>'.
   * @see org.ddmore.mdl.mdl.ParameterObjectBlock#getVerbatimBlock()
   * @see #getParameterObjectBlock()
   * @generated
   */
  EReference getParameterObjectBlock_VerbatimBlock();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.StructuralBlock <em>Structural Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Structural Block</em>'.
   * @see org.ddmore.mdl.mdl.StructuralBlock
   * @generated
   */
  EClass getStructuralBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.StructuralBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.StructuralBlock#getIdentifier()
   * @see #getStructuralBlock()
   * @generated
   */
  EAttribute getStructuralBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.StructuralBlock#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see org.ddmore.mdl.mdl.StructuralBlock#getParameters()
   * @see #getStructuralBlock()
   * @generated
   */
  EReference getStructuralBlock_Parameters();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.VariabilityBlock <em>Variability Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Variability Block</em>'.
   * @see org.ddmore.mdl.mdl.VariabilityBlock
   * @generated
   */
  EClass getVariabilityBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.VariabilityBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.VariabilityBlock#getIdentifier()
   * @see #getVariabilityBlock()
   * @generated
   */
  EAttribute getVariabilityBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.VariabilityBlock#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.VariabilityBlock#getStatements()
   * @see #getVariabilityBlock()
   * @generated
   */
  EReference getVariabilityBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.PriorParametersBlock <em>Prior Parameters Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Prior Parameters Block</em>'.
   * @see org.ddmore.mdl.mdl.PriorParametersBlock
   * @generated
   */
  EClass getPriorParametersBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.PriorParametersBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.PriorParametersBlock#getIdentifier()
   * @see #getPriorParametersBlock()
   * @generated
   */
  EAttribute getPriorParametersBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.PriorParametersBlock#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.PriorParametersBlock#getStatements()
   * @see #getPriorParametersBlock()
   * @generated
   */
  EReference getPriorParametersBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.DataObjectBlock <em>Data Object Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Data Object Block</em>'.
   * @see org.ddmore.mdl.mdl.DataObjectBlock
   * @generated
   */
  EClass getDataObjectBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.DataObjectBlock#getHeaderBlock <em>Header Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Header Block</em>'.
   * @see org.ddmore.mdl.mdl.DataObjectBlock#getHeaderBlock()
   * @see #getDataObjectBlock()
   * @generated
   */
  EReference getDataObjectBlock_HeaderBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.DataObjectBlock#getFileBlock <em>File Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>File Block</em>'.
   * @see org.ddmore.mdl.mdl.DataObjectBlock#getFileBlock()
   * @see #getDataObjectBlock()
   * @generated
   */
  EReference getDataObjectBlock_FileBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.DataObjectBlock#getVerbatimBlock <em>Verbatim Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Verbatim Block</em>'.
   * @see org.ddmore.mdl.mdl.DataObjectBlock#getVerbatimBlock()
   * @see #getDataObjectBlock()
   * @generated
   */
  EReference getDataObjectBlock_VerbatimBlock();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.HeaderBlock <em>Header Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Header Block</em>'.
   * @see org.ddmore.mdl.mdl.HeaderBlock
   * @generated
   */
  EClass getHeaderBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.HeaderBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.HeaderBlock#getIdentifier()
   * @see #getHeaderBlock()
   * @generated
   */
  EAttribute getHeaderBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.HeaderBlock#getVariables <em>Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variables</em>'.
   * @see org.ddmore.mdl.mdl.HeaderBlock#getVariables()
   * @see #getHeaderBlock()
   * @generated
   */
  EReference getHeaderBlock_Variables();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.FileBlock <em>File Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>File Block</em>'.
   * @see org.ddmore.mdl.mdl.FileBlock
   * @generated
   */
  EClass getFileBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.FileBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.FileBlock#getIdentifier()
   * @see #getFileBlock()
   * @generated
   */
  EAttribute getFileBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.FileBlock#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.FileBlock#getStatements()
   * @see #getFileBlock()
   * @generated
   */
  EReference getFileBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.TaskObjectBlock <em>Task Object Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Task Object Block</em>'.
   * @see org.ddmore.mdl.mdl.TaskObjectBlock
   * @generated
   */
  EClass getTaskObjectBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.TaskObjectBlock#getFunctionDeclaration <em>Function Declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function Declaration</em>'.
   * @see org.ddmore.mdl.mdl.TaskObjectBlock#getFunctionDeclaration()
   * @see #getTaskObjectBlock()
   * @generated
   */
  EReference getTaskObjectBlock_FunctionDeclaration();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.TaskObjectBlock#getParameterBlock <em>Parameter Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameter Block</em>'.
   * @see org.ddmore.mdl.mdl.TaskObjectBlock#getParameterBlock()
   * @see #getTaskObjectBlock()
   * @generated
   */
  EReference getTaskObjectBlock_ParameterBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.TaskObjectBlock#getDataBlock <em>Data Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Data Block</em>'.
   * @see org.ddmore.mdl.mdl.TaskObjectBlock#getDataBlock()
   * @see #getTaskObjectBlock()
   * @generated
   */
  EReference getTaskObjectBlock_DataBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.TaskObjectBlock#getModelBlock <em>Model Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Model Block</em>'.
   * @see org.ddmore.mdl.mdl.TaskObjectBlock#getModelBlock()
   * @see #getTaskObjectBlock()
   * @generated
   */
  EReference getTaskObjectBlock_ModelBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.TaskObjectBlock#getVerbatimBlock <em>Verbatim Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Verbatim Block</em>'.
   * @see org.ddmore.mdl.mdl.TaskObjectBlock#getVerbatimBlock()
   * @see #getTaskObjectBlock()
   * @generated
   */
  EReference getTaskObjectBlock_VerbatimBlock();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ParameterBlock <em>Parameter Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter Block</em>'.
   * @see org.ddmore.mdl.mdl.ParameterBlock
   * @generated
   */
  EClass getParameterBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.ParameterBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.ParameterBlock#getIdentifier()
   * @see #getParameterBlock()
   * @generated
   */
  EAttribute getParameterBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.ParameterBlock#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see org.ddmore.mdl.mdl.ParameterBlock#getParameters()
   * @see #getParameterBlock()
   * @generated
   */
  EReference getParameterBlock_Parameters();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.DataBlock <em>Data Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Data Block</em>'.
   * @see org.ddmore.mdl.mdl.DataBlock
   * @generated
   */
  EClass getDataBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.DataBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.DataBlock#getIdentifier()
   * @see #getDataBlock()
   * @generated
   */
  EAttribute getDataBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.DataBlock#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.DataBlock#getStatements()
   * @see #getDataBlock()
   * @generated
   */
  EReference getDataBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.DataBlockStatement <em>Data Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Data Block Statement</em>'.
   * @see org.ddmore.mdl.mdl.DataBlockStatement
   * @generated
   */
  EClass getDataBlockStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.DataBlockStatement#getIgnoreList <em>Ignore List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ignore List</em>'.
   * @see org.ddmore.mdl.mdl.DataBlockStatement#getIgnoreList()
   * @see #getDataBlockStatement()
   * @generated
   */
  EReference getDataBlockStatement_IgnoreList();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.DataBlockStatement#getAcceptList <em>Accept List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Accept List</em>'.
   * @see org.ddmore.mdl.mdl.DataBlockStatement#getAcceptList()
   * @see #getDataBlockStatement()
   * @generated
   */
  EReference getDataBlockStatement_AcceptList();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.DataBlockStatement#getDropList <em>Drop List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Drop List</em>'.
   * @see org.ddmore.mdl.mdl.DataBlockStatement#getDropList()
   * @see #getDataBlockStatement()
   * @generated
   */
  EReference getDataBlockStatement_DropList();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.IgnoreList <em>Ignore List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ignore List</em>'.
   * @see org.ddmore.mdl.mdl.IgnoreList
   * @generated
   */
  EClass getIgnoreList();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.IgnoreList#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.IgnoreList#getIdentifier()
   * @see #getIgnoreList()
   * @generated
   */
  EAttribute getIgnoreList_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.IgnoreList#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.IgnoreList#getExpression()
   * @see #getIgnoreList()
   * @generated
   */
  EReference getIgnoreList_Expression();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.AcceptList <em>Accept List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Accept List</em>'.
   * @see org.ddmore.mdl.mdl.AcceptList
   * @generated
   */
  EClass getAcceptList();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.AcceptList#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.AcceptList#getIdentifier()
   * @see #getAcceptList()
   * @generated
   */
  EAttribute getAcceptList_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.AcceptList#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.AcceptList#getExpression()
   * @see #getAcceptList()
   * @generated
   */
  EReference getAcceptList_Expression();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.DropList <em>Drop List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Drop List</em>'.
   * @see org.ddmore.mdl.mdl.DropList
   * @generated
   */
  EClass getDropList();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.DropList#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.DropList#getIdentifier()
   * @see #getDropList()
   * @generated
   */
  EAttribute getDropList_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.DropList#getList <em>List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>List</em>'.
   * @see org.ddmore.mdl.mdl.DropList#getList()
   * @see #getDropList()
   * @generated
   */
  EReference getDropList_List();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ModelBlock <em>Model Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model Block</em>'.
   * @see org.ddmore.mdl.mdl.ModelBlock
   * @generated
   */
  EClass getModelBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.ModelBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.ModelBlock#getIdentifier()
   * @see #getModelBlock()
   * @generated
   */
  EAttribute getModelBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.ModelBlock#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.ModelBlock#getStatements()
   * @see #getModelBlock()
   * @generated
   */
  EReference getModelBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ModelBlockStatement <em>Model Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model Block Statement</em>'.
   * @see org.ddmore.mdl.mdl.ModelBlockStatement
   * @generated
   */
  EClass getModelBlockStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelBlockStatement#getStatement <em>Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Statement</em>'.
   * @see org.ddmore.mdl.mdl.ModelBlockStatement#getStatement()
   * @see #getModelBlockStatement()
   * @generated
   */
  EReference getModelBlockStatement_Statement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelBlockStatement#getAddList <em>Add List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Add List</em>'.
   * @see org.ddmore.mdl.mdl.ModelBlockStatement#getAddList()
   * @see #getModelBlockStatement()
   * @generated
   */
  EReference getModelBlockStatement_AddList();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelBlockStatement#getRemoveList <em>Remove List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Remove List</em>'.
   * @see org.ddmore.mdl.mdl.ModelBlockStatement#getRemoveList()
   * @see #getModelBlockStatement()
   * @generated
   */
  EReference getModelBlockStatement_RemoveList();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.AddList <em>Add List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Add List</em>'.
   * @see org.ddmore.mdl.mdl.AddList
   * @generated
   */
  EClass getAddList();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.AddList#getList <em>List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>List</em>'.
   * @see org.ddmore.mdl.mdl.AddList#getList()
   * @see #getAddList()
   * @generated
   */
  EReference getAddList_List();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.RemoveList <em>Remove List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Remove List</em>'.
   * @see org.ddmore.mdl.mdl.RemoveList
   * @generated
   */
  EClass getRemoveList();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.RemoveList#getList <em>List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>List</em>'.
   * @see org.ddmore.mdl.mdl.RemoveList#getList()
   * @see #getRemoveList()
   * @generated
   */
  EReference getRemoveList_List();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.SymbolList <em>Symbol List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Symbol List</em>'.
   * @see org.ddmore.mdl.mdl.SymbolList
   * @generated
   */
  EClass getSymbolList();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.SymbolList#getSymbols <em>Symbols</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Symbols</em>'.
   * @see org.ddmore.mdl.mdl.SymbolList#getSymbols()
   * @see #getSymbolList()
   * @generated
   */
  EReference getSymbolList_Symbols();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ModelPredictionBlockStatement <em>Model Prediction Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model Prediction Block Statement</em>'.
   * @see org.ddmore.mdl.mdl.ModelPredictionBlockStatement
   * @generated
   */
  EClass getModelPredictionBlockStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelPredictionBlockStatement#getStatement <em>Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Statement</em>'.
   * @see org.ddmore.mdl.mdl.ModelPredictionBlockStatement#getStatement()
   * @see #getModelPredictionBlockStatement()
   * @generated
   */
  EReference getModelPredictionBlockStatement_Statement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelPredictionBlockStatement#getOdeBlock <em>Ode Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ode Block</em>'.
   * @see org.ddmore.mdl.mdl.ModelPredictionBlockStatement#getOdeBlock()
   * @see #getModelPredictionBlockStatement()
   * @generated
   */
  EReference getModelPredictionBlockStatement_OdeBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ModelPredictionBlockStatement#getLibraryBlock <em>Library Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Library Block</em>'.
   * @see org.ddmore.mdl.mdl.ModelPredictionBlockStatement#getLibraryBlock()
   * @see #getModelPredictionBlockStatement()
   * @generated
   */
  EReference getModelPredictionBlockStatement_LibraryBlock();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.LibraryBlock <em>Library Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Library Block</em>'.
   * @see org.ddmore.mdl.mdl.LibraryBlock
   * @generated
   */
  EClass getLibraryBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.LibraryBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.LibraryBlock#getIdentifier()
   * @see #getLibraryBlock()
   * @generated
   */
  EAttribute getLibraryBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.LibraryBlock#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.LibraryBlock#getStatements()
   * @see #getLibraryBlock()
   * @generated
   */
  EReference getLibraryBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.OdeBlock <em>Ode Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ode Block</em>'.
   * @see org.ddmore.mdl.mdl.OdeBlock
   * @generated
   */
  EClass getOdeBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.OdeBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.OdeBlock#getIdentifier()
   * @see #getOdeBlock()
   * @generated
   */
  EAttribute getOdeBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.OdeBlock#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.OdeBlock#getStatements()
   * @see #getOdeBlock()
   * @generated
   */
  EReference getOdeBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.GroupVariablesBlockStatement <em>Group Variables Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Group Variables Block Statement</em>'.
   * @see org.ddmore.mdl.mdl.GroupVariablesBlockStatement
   * @generated
   */
  EClass getGroupVariablesBlockStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.GroupVariablesBlockStatement#getStatement <em>Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Statement</em>'.
   * @see org.ddmore.mdl.mdl.GroupVariablesBlockStatement#getStatement()
   * @see #getGroupVariablesBlockStatement()
   * @generated
   */
  EReference getGroupVariablesBlockStatement_Statement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.GroupVariablesBlockStatement#getMixtureBlock <em>Mixture Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Mixture Block</em>'.
   * @see org.ddmore.mdl.mdl.GroupVariablesBlockStatement#getMixtureBlock()
   * @see #getGroupVariablesBlockStatement()
   * @generated
   */
  EReference getGroupVariablesBlockStatement_MixtureBlock();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.MixtureBlock <em>Mixture Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mixture Block</em>'.
   * @see org.ddmore.mdl.mdl.MixtureBlock
   * @generated
   */
  EClass getMixtureBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.MixtureBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.MixtureBlock#getIdentifier()
   * @see #getMixtureBlock()
   * @generated
   */
  EAttribute getMixtureBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.MixtureBlock#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.MixtureBlock#getStatements()
   * @see #getMixtureBlock()
   * @generated
   */
  EReference getMixtureBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.VariabilityBlockStatement <em>Variability Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Variability Block Statement</em>'.
   * @see org.ddmore.mdl.mdl.VariabilityBlockStatement
   * @generated
   */
  EClass getVariabilityBlockStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.VariabilityBlockStatement#getParameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameter</em>'.
   * @see org.ddmore.mdl.mdl.VariabilityBlockStatement#getParameter()
   * @see #getVariabilityBlockStatement()
   * @generated
   */
  EReference getVariabilityBlockStatement_Parameter();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.VariabilityBlockStatement#getBlockBlock <em>Block Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block Block</em>'.
   * @see org.ddmore.mdl.mdl.VariabilityBlockStatement#getBlockBlock()
   * @see #getVariabilityBlockStatement()
   * @generated
   */
  EReference getVariabilityBlockStatement_BlockBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.VariabilityBlockStatement#getDiagBlock <em>Diag Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Diag Block</em>'.
   * @see org.ddmore.mdl.mdl.VariabilityBlockStatement#getDiagBlock()
   * @see #getVariabilityBlockStatement()
   * @generated
   */
  EReference getVariabilityBlockStatement_DiagBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.VariabilityBlockStatement#getSameBlock <em>Same Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Same Block</em>'.
   * @see org.ddmore.mdl.mdl.VariabilityBlockStatement#getSameBlock()
   * @see #getVariabilityBlockStatement()
   * @generated
   */
  EReference getVariabilityBlockStatement_SameBlock();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.BlockBlock <em>Block Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Block Block</em>'.
   * @see org.ddmore.mdl.mdl.BlockBlock
   * @generated
   */
  EClass getBlockBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.BlockBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.BlockBlock#getIdentifier()
   * @see #getBlockBlock()
   * @generated
   */
  EAttribute getBlockBlock_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.BlockBlock#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.BlockBlock#getArguments()
   * @see #getBlockBlock()
   * @generated
   */
  EReference getBlockBlock_Arguments();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.BlockBlock#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameters</em>'.
   * @see org.ddmore.mdl.mdl.BlockBlock#getParameters()
   * @see #getBlockBlock()
   * @generated
   */
  EReference getBlockBlock_Parameters();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.DiagBlock <em>Diag Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Diag Block</em>'.
   * @see org.ddmore.mdl.mdl.DiagBlock
   * @generated
   */
  EClass getDiagBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.DiagBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.DiagBlock#getIdentifier()
   * @see #getDiagBlock()
   * @generated
   */
  EAttribute getDiagBlock_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.DiagBlock#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.DiagBlock#getArguments()
   * @see #getDiagBlock()
   * @generated
   */
  EReference getDiagBlock_Arguments();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.DiagBlock#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameters</em>'.
   * @see org.ddmore.mdl.mdl.DiagBlock#getParameters()
   * @see #getDiagBlock()
   * @generated
   */
  EReference getDiagBlock_Parameters();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.SameBlock <em>Same Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Same Block</em>'.
   * @see org.ddmore.mdl.mdl.SameBlock
   * @generated
   */
  EClass getSameBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.SameBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.SameBlock#getIdentifier()
   * @see #getSameBlock()
   * @generated
   */
  EAttribute getSameBlock_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.SameBlock#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.SameBlock#getArguments()
   * @see #getSameBlock()
   * @generated
   */
  EReference getSameBlock_Arguments();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.SameBlock#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parameters</em>'.
   * @see org.ddmore.mdl.mdl.SameBlock#getParameters()
   * @see #getSameBlock()
   * @generated
   */
  EReference getSameBlock_Parameters();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.FileBlockStatement <em>File Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>File Block Statement</em>'.
   * @see org.ddmore.mdl.mdl.FileBlockStatement
   * @generated
   */
  EClass getFileBlockStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.FileBlockStatement#getVariable <em>Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Variable</em>'.
   * @see org.ddmore.mdl.mdl.FileBlockStatement#getVariable()
   * @see #getFileBlockStatement()
   * @generated
   */
  EReference getFileBlockStatement_Variable();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.FileBlockStatement#getInlineBlock <em>Inline Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Inline Block</em>'.
   * @see org.ddmore.mdl.mdl.FileBlockStatement#getInlineBlock()
   * @see #getFileBlockStatement()
   * @generated
   */
  EReference getFileBlockStatement_InlineBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.FileBlockStatement#getDesignBlock <em>Design Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Design Block</em>'.
   * @see org.ddmore.mdl.mdl.FileBlockStatement#getDesignBlock()
   * @see #getFileBlockStatement()
   * @generated
   */
  EReference getFileBlockStatement_DesignBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.FileBlockStatement#getRscriptBlock <em>Rscript Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Rscript Block</em>'.
   * @see org.ddmore.mdl.mdl.FileBlockStatement#getRscriptBlock()
   * @see #getFileBlockStatement()
   * @generated
   */
  EReference getFileBlockStatement_RscriptBlock();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.InlineBlock <em>Inline Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Inline Block</em>'.
   * @see org.ddmore.mdl.mdl.InlineBlock
   * @generated
   */
  EClass getInlineBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.InlineBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.InlineBlock#getIdentifier()
   * @see #getInlineBlock()
   * @generated
   */
  EAttribute getInlineBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.InlineBlock#getVariables <em>Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variables</em>'.
   * @see org.ddmore.mdl.mdl.InlineBlock#getVariables()
   * @see #getInlineBlock()
   * @generated
   */
  EReference getInlineBlock_Variables();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.InlineBlock#getValues <em>Values</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Values</em>'.
   * @see org.ddmore.mdl.mdl.InlineBlock#getValues()
   * @see #getInlineBlock()
   * @generated
   */
  EAttribute getInlineBlock_Values();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.DesignBlock <em>Design Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Design Block</em>'.
   * @see org.ddmore.mdl.mdl.DesignBlock
   * @generated
   */
  EClass getDesignBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.DesignBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.DesignBlock#getIdentifier()
   * @see #getDesignBlock()
   * @generated
   */
  EAttribute getDesignBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.DesignBlock#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.DesignBlock#getStatements()
   * @see #getDesignBlock()
   * @generated
   */
  EReference getDesignBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.DesignBlockStatement <em>Design Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Design Block Statement</em>'.
   * @see org.ddmore.mdl.mdl.DesignBlockStatement
   * @generated
   */
  EClass getDesignBlockStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.DesignBlockStatement#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.DesignBlockStatement#getIdentifier()
   * @see #getDesignBlockStatement()
   * @generated
   */
  EReference getDesignBlockStatement_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.DesignBlockStatement#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.DesignBlockStatement#getArguments()
   * @see #getDesignBlockStatement()
   * @generated
   */
  EReference getDesignBlockStatement_Arguments();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.DesignBlockStatement#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.DesignBlockStatement#getExpression()
   * @see #getDesignBlockStatement()
   * @generated
   */
  EReference getDesignBlockStatement_Expression();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.VariableList <em>Variable List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Variable List</em>'.
   * @see org.ddmore.mdl.mdl.VariableList
   * @generated
   */
  EClass getVariableList();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.VariableList#getIdentifiers <em>Identifiers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Identifiers</em>'.
   * @see org.ddmore.mdl.mdl.VariableList#getIdentifiers()
   * @see #getVariableList()
   * @generated
   */
  EReference getVariableList_Identifiers();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.RScriptBlock <em>RScript Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>RScript Block</em>'.
   * @see org.ddmore.mdl.mdl.RScriptBlock
   * @generated
   */
  EClass getRScriptBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.RScriptBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.RScriptBlock#getIdentifier()
   * @see #getRScriptBlock()
   * @generated
   */
  EAttribute getRScriptBlock_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.RScriptBlock#getVariables <em>Variables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Variables</em>'.
   * @see org.ddmore.mdl.mdl.RScriptBlock#getVariables()
   * @see #getRScriptBlock()
   * @generated
   */
  EReference getRScriptBlock_Variables();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.RScriptBlockStatement <em>RScript Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>RScript Block Statement</em>'.
   * @see org.ddmore.mdl.mdl.RScriptBlockStatement
   * @generated
   */
  EClass getRScriptBlockStatement();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.RScriptBlockStatement#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.RScriptBlockStatement#getIdentifier()
   * @see #getRScriptBlockStatement()
   * @generated
   */
  EAttribute getRScriptBlockStatement_Identifier();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.RScriptBlockStatement#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.ddmore.mdl.mdl.RScriptBlockStatement#getValue()
   * @see #getRScriptBlockStatement()
   * @generated
   */
  EAttribute getRScriptBlockStatement_Value();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.RScriptBlockStatement#getObject <em>Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Object</em>'.
   * @see org.ddmore.mdl.mdl.RScriptBlockStatement#getObject()
   * @see #getRScriptBlockStatement()
   * @generated
   */
  EReference getRScriptBlockStatement_Object();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.TaskFunctionDeclaration <em>Task Function Declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Task Function Declaration</em>'.
   * @see org.ddmore.mdl.mdl.TaskFunctionDeclaration
   * @generated
   */
  EClass getTaskFunctionDeclaration();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.TaskFunctionDeclaration#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.TaskFunctionDeclaration#getIdentifier()
   * @see #getTaskFunctionDeclaration()
   * @generated
   */
  EAttribute getTaskFunctionDeclaration_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.TaskFunctionDeclaration#getFormalArguments <em>Formal Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Formal Arguments</em>'.
   * @see org.ddmore.mdl.mdl.TaskFunctionDeclaration#getFormalArguments()
   * @see #getTaskFunctionDeclaration()
   * @generated
   */
  EReference getTaskFunctionDeclaration_FormalArguments();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.TaskFunctionDeclaration#getFunctionBody <em>Function Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function Body</em>'.
   * @see org.ddmore.mdl.mdl.TaskFunctionDeclaration#getFunctionBody()
   * @see #getTaskFunctionDeclaration()
   * @generated
   */
  EReference getTaskFunctionDeclaration_FunctionBody();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.TaskFunctionBody <em>Task Function Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Task Function Body</em>'.
   * @see org.ddmore.mdl.mdl.TaskFunctionBody
   * @generated
   */
  EClass getTaskFunctionBody();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.TaskFunctionBody#getBlocks <em>Blocks</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Blocks</em>'.
   * @see org.ddmore.mdl.mdl.TaskFunctionBody#getBlocks()
   * @see #getTaskFunctionBody()
   * @generated
   */
  EReference getTaskFunctionBody_Blocks();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.TaskFunctionBlock <em>Task Function Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Task Function Block</em>'.
   * @see org.ddmore.mdl.mdl.TaskFunctionBlock
   * @generated
   */
  EClass getTaskFunctionBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.TaskFunctionBlock#getEstimateBlock <em>Estimate Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Estimate Block</em>'.
   * @see org.ddmore.mdl.mdl.TaskFunctionBlock#getEstimateBlock()
   * @see #getTaskFunctionBlock()
   * @generated
   */
  EReference getTaskFunctionBlock_EstimateBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.TaskFunctionBlock#getSimulateBlock <em>Simulate Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Simulate Block</em>'.
   * @see org.ddmore.mdl.mdl.TaskFunctionBlock#getSimulateBlock()
   * @see #getTaskFunctionBlock()
   * @generated
   */
  EReference getTaskFunctionBlock_SimulateBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.TaskFunctionBlock#getExecuteBlock <em>Execute Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Execute Block</em>'.
   * @see org.ddmore.mdl.mdl.TaskFunctionBlock#getExecuteBlock()
   * @see #getTaskFunctionBlock()
   * @generated
   */
  EReference getTaskFunctionBlock_ExecuteBlock();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.EstimateTask <em>Estimate Task</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Estimate Task</em>'.
   * @see org.ddmore.mdl.mdl.EstimateTask
   * @generated
   */
  EClass getEstimateTask();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.EstimateTask#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.EstimateTask#getIdentifier()
   * @see #getEstimateTask()
   * @generated
   */
  EAttribute getEstimateTask_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.EstimateTask#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.EstimateTask#getStatements()
   * @see #getEstimateTask()
   * @generated
   */
  EReference getEstimateTask_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.SimulateTask <em>Simulate Task</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Simulate Task</em>'.
   * @see org.ddmore.mdl.mdl.SimulateTask
   * @generated
   */
  EClass getSimulateTask();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.SimulateTask#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.SimulateTask#getIdentifier()
   * @see #getSimulateTask()
   * @generated
   */
  EAttribute getSimulateTask_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.SimulateTask#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.SimulateTask#getStatements()
   * @see #getSimulateTask()
   * @generated
   */
  EReference getSimulateTask_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ExecuteTask <em>Execute Task</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Execute Task</em>'.
   * @see org.ddmore.mdl.mdl.ExecuteTask
   * @generated
   */
  EClass getExecuteTask();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.ExecuteTask#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.ExecuteTask#getIdentifier()
   * @see #getExecuteTask()
   * @generated
   */
  EAttribute getExecuteTask_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.ExecuteTask#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.ExecuteTask#getStatements()
   * @see #getExecuteTask()
   * @generated
   */
  EReference getExecuteTask_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.FormalArguments <em>Formal Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Formal Arguments</em>'.
   * @see org.ddmore.mdl.mdl.FormalArguments
   * @generated
   */
  EClass getFormalArguments();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.FormalArguments#getIdentifiers <em>Identifiers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Identifiers</em>'.
   * @see org.ddmore.mdl.mdl.FormalArguments#getIdentifiers()
   * @see #getFormalArguments()
   * @generated
   */
  EAttribute getFormalArguments_Identifiers();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.FunctionCall <em>Function Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Function Call</em>'.
   * @see org.ddmore.mdl.mdl.FunctionCall
   * @generated
   */
  EClass getFunctionCall();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.FunctionCall#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.FunctionCall#getIdentifier()
   * @see #getFunctionCall()
   * @generated
   */
  EReference getFunctionCall_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.FunctionCall#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.FunctionCall#getArguments()
   * @see #getFunctionCall()
   * @generated
   */
  EReference getFunctionCall_Arguments();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.BlockStatement <em>Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Block Statement</em>'.
   * @see org.ddmore.mdl.mdl.BlockStatement
   * @generated
   */
  EClass getBlockStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.BlockStatement#getSymbol <em>Symbol</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symbol</em>'.
   * @see org.ddmore.mdl.mdl.BlockStatement#getSymbol()
   * @see #getBlockStatement()
   * @generated
   */
  EReference getBlockStatement_Symbol();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.BlockStatement#getFunctionCall <em>Function Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function Call</em>'.
   * @see org.ddmore.mdl.mdl.BlockStatement#getFunctionCall()
   * @see #getBlockStatement()
   * @generated
   */
  EReference getBlockStatement_FunctionCall();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.BlockStatement#getStatement <em>Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Statement</em>'.
   * @see org.ddmore.mdl.mdl.BlockStatement#getStatement()
   * @see #getBlockStatement()
   * @generated
   */
  EReference getBlockStatement_Statement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.BlockStatement#getVerbatimBlock <em>Verbatim Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Verbatim Block</em>'.
   * @see org.ddmore.mdl.mdl.BlockStatement#getVerbatimBlock()
   * @see #getBlockStatement()
   * @generated
   */
  EReference getBlockStatement_VerbatimBlock();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.VerbatimBlock <em>Verbatim Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Verbatim Block</em>'.
   * @see org.ddmore.mdl.mdl.VerbatimBlock
   * @generated
   */
  EClass getVerbatimBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.VerbatimBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.VerbatimBlock#getIdentifier()
   * @see #getVerbatimBlock()
   * @generated
   */
  EAttribute getVerbatimBlock_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.VerbatimBlock#getBlock <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.VerbatimBlock#getBlock()
   * @see #getVerbatimBlock()
   * @generated
   */
  EReference getVerbatimBlock_Block();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.VerbatimBlock#getExternalCode <em>External Code</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>External Code</em>'.
   * @see org.ddmore.mdl.mdl.VerbatimBlock#getExternalCode()
   * @see #getVerbatimBlock()
   * @generated
   */
  EAttribute getVerbatimBlock_ExternalCode();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.TargetBlock <em>Target Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Target Block</em>'.
   * @see org.ddmore.mdl.mdl.TargetBlock
   * @generated
   */
  EClass getTargetBlock();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.TargetBlock#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.TargetBlock#getIdentifier()
   * @see #getTargetBlock()
   * @generated
   */
  EAttribute getTargetBlock_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.TargetBlock#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.TargetBlock#getArguments()
   * @see #getTargetBlock()
   * @generated
   */
  EReference getTargetBlock_Arguments();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.TargetBlock#getExternalCode <em>External Code</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>External Code</em>'.
   * @see org.ddmore.mdl.mdl.TargetBlock#getExternalCode()
   * @see #getTargetBlock()
   * @generated
   */
  EAttribute getTargetBlock_ExternalCode();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.SymbolModification <em>Symbol Modification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Symbol Modification</em>'.
   * @see org.ddmore.mdl.mdl.SymbolModification
   * @generated
   */
  EClass getSymbolModification();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.SymbolModification#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.SymbolModification#getIdentifier()
   * @see #getSymbolModification()
   * @generated
   */
  EReference getSymbolModification_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.SymbolModification#getList <em>List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>List</em>'.
   * @see org.ddmore.mdl.mdl.SymbolModification#getList()
   * @see #getSymbolModification()
   * @generated
   */
  EReference getSymbolModification_List();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ParameterDeclaration <em>Parameter Declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter Declaration</em>'.
   * @see org.ddmore.mdl.mdl.ParameterDeclaration
   * @generated
   */
  EClass getParameterDeclaration();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.ParameterDeclaration#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.ParameterDeclaration#getIdentifier()
   * @see #getParameterDeclaration()
   * @generated
   */
  EAttribute getParameterDeclaration_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ParameterDeclaration#getList <em>List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>List</em>'.
   * @see org.ddmore.mdl.mdl.ParameterDeclaration#getList()
   * @see #getParameterDeclaration()
   * @generated
   */
  EReference getParameterDeclaration_List();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.SymbolDeclaration <em>Symbol Declaration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Symbol Declaration</em>'.
   * @see org.ddmore.mdl.mdl.SymbolDeclaration
   * @generated
   */
  EClass getSymbolDeclaration();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.SymbolDeclaration#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.SymbolDeclaration#getIdentifier()
   * @see #getSymbolDeclaration()
   * @generated
   */
  EAttribute getSymbolDeclaration_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.SymbolDeclaration#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.SymbolDeclaration#getExpression()
   * @see #getSymbolDeclaration()
   * @generated
   */
  EReference getSymbolDeclaration_Expression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.SymbolDeclaration#getRandomList <em>Random List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Random List</em>'.
   * @see org.ddmore.mdl.mdl.SymbolDeclaration#getRandomList()
   * @see #getSymbolDeclaration()
   * @generated
   */
  EReference getSymbolDeclaration_RandomList();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.SymbolDeclaration#getFunction <em>Function</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Function</em>'.
   * @see org.ddmore.mdl.mdl.SymbolDeclaration#getFunction()
   * @see #getSymbolDeclaration()
   * @generated
   */
  EAttribute getSymbolDeclaration_Function();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.EnumType <em>Enum Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enum Type</em>'.
   * @see org.ddmore.mdl.mdl.EnumType
   * @generated
   */
  EClass getEnumType();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.EnumType#getCategorical <em>Categorical</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Categorical</em>'.
   * @see org.ddmore.mdl.mdl.EnumType#getCategorical()
   * @see #getEnumType()
   * @generated
   */
  EReference getEnumType_Categorical();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.EnumType#getContinuous <em>Continuous</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Continuous</em>'.
   * @see org.ddmore.mdl.mdl.EnumType#getContinuous()
   * @see #getEnumType()
   * @generated
   */
  EReference getEnumType_Continuous();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.EnumType#getCovariate <em>Covariate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Covariate</em>'.
   * @see org.ddmore.mdl.mdl.EnumType#getCovariate()
   * @see #getEnumType()
   * @generated
   */
  EReference getEnumType_Covariate();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.EnumType#getDistribution <em>Distribution</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Distribution</em>'.
   * @see org.ddmore.mdl.mdl.EnumType#getDistribution()
   * @see #getEnumType()
   * @generated
   */
  EReference getEnumType_Distribution();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.EnumType#getLevel <em>Level</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Level</em>'.
   * @see org.ddmore.mdl.mdl.EnumType#getLevel()
   * @see #getEnumType()
   * @generated
   */
  EReference getEnumType_Level();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.EnumType#getLikelyhood <em>Likelyhood</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Likelyhood</em>'.
   * @see org.ddmore.mdl.mdl.EnumType#getLikelyhood()
   * @see #getEnumType()
   * @generated
   */
  EReference getEnumType_Likelyhood();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.EnumType#getMissing <em>Missing</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Missing</em>'.
   * @see org.ddmore.mdl.mdl.EnumType#getMissing()
   * @see #getEnumType()
   * @generated
   */
  EReference getEnumType_Missing();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.Missing <em>Missing</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Missing</em>'.
   * @see org.ddmore.mdl.mdl.Missing
   * @generated
   */
  EClass getMissing();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.Missing#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.Missing#getIdentifier()
   * @see #getMissing()
   * @generated
   */
  EAttribute getMissing_Identifier();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.Likelyhood <em>Likelyhood</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Likelyhood</em>'.
   * @see org.ddmore.mdl.mdl.Likelyhood
   * @generated
   */
  EClass getLikelyhood();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.Likelyhood#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.Likelyhood#getIdentifier()
   * @see #getLikelyhood()
   * @generated
   */
  EAttribute getLikelyhood_Identifier();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.LevelType <em>Level Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Level Type</em>'.
   * @see org.ddmore.mdl.mdl.LevelType
   * @generated
   */
  EClass getLevelType();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.LevelType#getMdv <em>Mdv</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Mdv</em>'.
   * @see org.ddmore.mdl.mdl.LevelType#getMdv()
   * @see #getLevelType()
   * @generated
   */
  EAttribute getLevelType_Mdv();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.LevelType#getId <em>Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Id</em>'.
   * @see org.ddmore.mdl.mdl.LevelType#getId()
   * @see #getLevelType()
   * @generated
   */
  EAttribute getLevelType_Id();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.LevelType#getDv <em>Dv</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Dv</em>'.
   * @see org.ddmore.mdl.mdl.LevelType#getDv()
   * @see #getLevelType()
   * @generated
   */
  EAttribute getLevelType_Dv();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.LevelType#getIdv <em>Idv</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Idv</em>'.
   * @see org.ddmore.mdl.mdl.LevelType#getIdv()
   * @see #getLevelType()
   * @generated
   */
  EAttribute getLevelType_Idv();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.Categorical <em>Categorical</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Categorical</em>'.
   * @see org.ddmore.mdl.mdl.Categorical
   * @generated
   */
  EClass getCategorical();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.Categorical#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.Categorical#getIdentifier()
   * @see #getCategorical()
   * @generated
   */
  EAttribute getCategorical_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.Categorical#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.Categorical#getArguments()
   * @see #getCategorical()
   * @generated
   */
  EReference getCategorical_Arguments();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.Continuous <em>Continuous</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Continuous</em>'.
   * @see org.ddmore.mdl.mdl.Continuous
   * @generated
   */
  EClass getContinuous();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.Continuous#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.Continuous#getIdentifier()
   * @see #getContinuous()
   * @generated
   */
  EAttribute getContinuous_Identifier();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.Covariate <em>Covariate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Covariate</em>'.
   * @see org.ddmore.mdl.mdl.Covariate
   * @generated
   */
  EClass getCovariate();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.Covariate#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.Covariate#getIdentifier()
   * @see #getCovariate()
   * @generated
   */
  EAttribute getCovariate_Identifier();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.Distribution <em>Distribution</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Distribution</em>'.
   * @see org.ddmore.mdl.mdl.Distribution
   * @generated
   */
  EClass getDistribution();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.Distribution#getNormal <em>Normal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Normal</em>'.
   * @see org.ddmore.mdl.mdl.Distribution#getNormal()
   * @see #getDistribution()
   * @generated
   */
  EAttribute getDistribution_Normal();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.Distribution#getBinomial <em>Binomial</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Binomial</em>'.
   * @see org.ddmore.mdl.mdl.Distribution#getBinomial()
   * @see #getDistribution()
   * @generated
   */
  EAttribute getDistribution_Binomial();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.Distribution#getPoisson <em>Poisson</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Poisson</em>'.
   * @see org.ddmore.mdl.mdl.Distribution#getPoisson()
   * @see #getDistribution()
   * @generated
   */
  EAttribute getDistribution_Poisson();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.Distribution#getStudent_t <em>Student t</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Student t</em>'.
   * @see org.ddmore.mdl.mdl.Distribution#getStudent_t()
   * @see #getDistribution()
   * @generated
   */
  EAttribute getDistribution_Student_t();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.Distribution#getMvnormal <em>Mvnormal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Mvnormal</em>'.
   * @see org.ddmore.mdl.mdl.Distribution#getMvnormal()
   * @see #getDistribution()
   * @generated
   */
  EAttribute getDistribution_Mvnormal();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.AnyExpression <em>Any Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Any Expression</em>'.
   * @see org.ddmore.mdl.mdl.AnyExpression
   * @generated
   */
  EClass getAnyExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.AnyExpression#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.AnyExpression#getExpression()
   * @see #getAnyExpression()
   * @generated
   */
  EReference getAnyExpression_Expression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.AnyExpression#getList <em>List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>List</em>'.
   * @see org.ddmore.mdl.mdl.AnyExpression#getList()
   * @see #getAnyExpression()
   * @generated
   */
  EReference getAnyExpression_List();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.AnyExpression#getOdeList <em>Ode List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Ode List</em>'.
   * @see org.ddmore.mdl.mdl.AnyExpression#getOdeList()
   * @see #getAnyExpression()
   * @generated
   */
  EReference getAnyExpression_OdeList();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.AnyExpression#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.ddmore.mdl.mdl.AnyExpression#getType()
   * @see #getAnyExpression()
   * @generated
   */
  EReference getAnyExpression_Type();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.Expression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.Expression
   * @generated
   */
  EClass getExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.Expression#getConditionalExpression <em>Conditional Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Conditional Expression</em>'.
   * @see org.ddmore.mdl.mdl.Expression#getConditionalExpression()
   * @see #getExpression()
   * @generated
   */
  EReference getExpression_ConditionalExpression();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.List <em>List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>List</em>'.
   * @see org.ddmore.mdl.mdl.List
   * @generated
   */
  EClass getList();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.List#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.List#getArguments()
   * @see #getList()
   * @generated
   */
  EReference getList_Arguments();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.OdeList <em>Ode List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ode List</em>'.
   * @see org.ddmore.mdl.mdl.OdeList
   * @generated
   */
  EClass getOdeList();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.OdeList#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.OdeList#getArguments()
   * @see #getOdeList()
   * @generated
   */
  EReference getOdeList_Arguments();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.RandomList <em>Random List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Random List</em>'.
   * @see org.ddmore.mdl.mdl.RandomList
   * @generated
   */
  EClass getRandomList();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.RandomList#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.RandomList#getArguments()
   * @see #getRandomList()
   * @generated
   */
  EReference getRandomList_Arguments();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.Arguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.Arguments
   * @generated
   */
  EClass getArguments();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.Arguments#getArguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Arguments</em>'.
   * @see org.ddmore.mdl.mdl.Arguments#getArguments()
   * @see #getArguments()
   * @generated
   */
  EReference getArguments_Arguments();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.Argument <em>Argument</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Argument</em>'.
   * @see org.ddmore.mdl.mdl.Argument
   * @generated
   */
  EClass getArgument();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.Argument#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.Argument#getIdentifier()
   * @see #getArgument()
   * @generated
   */
  EAttribute getArgument_Identifier();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.Argument#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.Argument#getExpression()
   * @see #getArgument()
   * @generated
   */
  EReference getArgument_Expression();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ConditionalStatement <em>Conditional Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Conditional Statement</em>'.
   * @see org.ddmore.mdl.mdl.ConditionalStatement
   * @generated
   */
  EClass getConditionalStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ConditionalStatement#getParExpression <em>Par Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Par Expression</em>'.
   * @see org.ddmore.mdl.mdl.ConditionalStatement#getParExpression()
   * @see #getConditionalStatement()
   * @generated
   */
  EReference getConditionalStatement_ParExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ConditionalStatement#getIfStatement <em>If Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>If Statement</em>'.
   * @see org.ddmore.mdl.mdl.ConditionalStatement#getIfStatement()
   * @see #getConditionalStatement()
   * @generated
   */
  EReference getConditionalStatement_IfStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ConditionalStatement#getIfBlock <em>If Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>If Block</em>'.
   * @see org.ddmore.mdl.mdl.ConditionalStatement#getIfBlock()
   * @see #getConditionalStatement()
   * @generated
   */
  EReference getConditionalStatement_IfBlock();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ConditionalStatement#getElseStatement <em>Else Statement</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Else Statement</em>'.
   * @see org.ddmore.mdl.mdl.ConditionalStatement#getElseStatement()
   * @see #getConditionalStatement()
   * @generated
   */
  EReference getConditionalStatement_ElseStatement();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ConditionalStatement#getElseBlock <em>Else Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Else Block</em>'.
   * @see org.ddmore.mdl.mdl.ConditionalStatement#getElseBlock()
   * @see #getConditionalStatement()
   * @generated
   */
  EReference getConditionalStatement_ElseBlock();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.Block <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Block</em>'.
   * @see org.ddmore.mdl.mdl.Block
   * @generated
   */
  EClass getBlock();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.Block#getStatements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Statements</em>'.
   * @see org.ddmore.mdl.mdl.Block#getStatements()
   * @see #getBlock()
   * @generated
   */
  EReference getBlock_Statements();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ParExpression <em>Par Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Par Expression</em>'.
   * @see org.ddmore.mdl.mdl.ParExpression
   * @generated
   */
  EClass getParExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ParExpression#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.ParExpression#getExpression()
   * @see #getParExpression()
   * @generated
   */
  EReference getParExpression_Expression();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ConditionalExpression <em>Conditional Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Conditional Expression</em>'.
   * @see org.ddmore.mdl.mdl.ConditionalExpression
   * @generated
   */
  EClass getConditionalExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ConditionalExpression#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.ConditionalExpression#getExpression()
   * @see #getConditionalExpression()
   * @generated
   */
  EReference getConditionalExpression_Expression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ConditionalExpression#getExpression1 <em>Expression1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression1</em>'.
   * @see org.ddmore.mdl.mdl.ConditionalExpression#getExpression1()
   * @see #getConditionalExpression()
   * @generated
   */
  EReference getConditionalExpression_Expression1();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.ConditionalExpression#getExpression2 <em>Expression2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression2</em>'.
   * @see org.ddmore.mdl.mdl.ConditionalExpression#getExpression2()
   * @see #getConditionalExpression()
   * @generated
   */
  EReference getConditionalExpression_Expression2();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.OrExpression <em>Or Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Or Expression</em>'.
   * @see org.ddmore.mdl.mdl.OrExpression
   * @generated
   */
  EClass getOrExpression();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.OrExpression#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.OrExpression#getExpression()
   * @see #getOrExpression()
   * @generated
   */
  EReference getOrExpression_Expression();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.OrExpression#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Operator</em>'.
   * @see org.ddmore.mdl.mdl.OrExpression#getOperator()
   * @see #getOrExpression()
   * @generated
   */
  EAttribute getOrExpression_Operator();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.AndExpression <em>And Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>And Expression</em>'.
   * @see org.ddmore.mdl.mdl.AndExpression
   * @generated
   */
  EClass getAndExpression();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.AndExpression#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.AndExpression#getExpression()
   * @see #getAndExpression()
   * @generated
   */
  EReference getAndExpression_Expression();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.AndExpression#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Operator</em>'.
   * @see org.ddmore.mdl.mdl.AndExpression#getOperator()
   * @see #getAndExpression()
   * @generated
   */
  EAttribute getAndExpression_Operator();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.LogicalExpression <em>Logical Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Logical Expression</em>'.
   * @see org.ddmore.mdl.mdl.LogicalExpression
   * @generated
   */
  EClass getLogicalExpression();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.LogicalExpression#getNegation <em>Negation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Negation</em>'.
   * @see org.ddmore.mdl.mdl.LogicalExpression#getNegation()
   * @see #getLogicalExpression()
   * @generated
   */
  EAttribute getLogicalExpression_Negation();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.LogicalExpression#getBoolean <em>Boolean</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Boolean</em>'.
   * @see org.ddmore.mdl.mdl.LogicalExpression#getBoolean()
   * @see #getLogicalExpression()
   * @generated
   */
  EAttribute getLogicalExpression_Boolean();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.LogicalExpression#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.LogicalExpression#getExpression()
   * @see #getLogicalExpression()
   * @generated
   */
  EReference getLogicalExpression_Expression();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.LogicalExpression#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Operator</em>'.
   * @see org.ddmore.mdl.mdl.LogicalExpression#getOperator()
   * @see #getLogicalExpression()
   * @generated
   */
  EAttribute getLogicalExpression_Operator();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.AdditiveExpression <em>Additive Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Additive Expression</em>'.
   * @see org.ddmore.mdl.mdl.AdditiveExpression
   * @generated
   */
  EClass getAdditiveExpression();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.AdditiveExpression#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.AdditiveExpression#getExpression()
   * @see #getAdditiveExpression()
   * @generated
   */
  EReference getAdditiveExpression_Expression();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.AdditiveExpression#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Operator</em>'.
   * @see org.ddmore.mdl.mdl.AdditiveExpression#getOperator()
   * @see #getAdditiveExpression()
   * @generated
   */
  EAttribute getAdditiveExpression_Operator();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.AdditiveExpression#getString <em>String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>String</em>'.
   * @see org.ddmore.mdl.mdl.AdditiveExpression#getString()
   * @see #getAdditiveExpression()
   * @generated
   */
  EAttribute getAdditiveExpression_String();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.MultiplicativeExpression <em>Multiplicative Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Multiplicative Expression</em>'.
   * @see org.ddmore.mdl.mdl.MultiplicativeExpression
   * @generated
   */
  EClass getMultiplicativeExpression();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.MultiplicativeExpression#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.MultiplicativeExpression#getExpression()
   * @see #getMultiplicativeExpression()
   * @generated
   */
  EReference getMultiplicativeExpression_Expression();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.MultiplicativeExpression#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Operator</em>'.
   * @see org.ddmore.mdl.mdl.MultiplicativeExpression#getOperator()
   * @see #getMultiplicativeExpression()
   * @generated
   */
  EAttribute getMultiplicativeExpression_Operator();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.PowerExpression <em>Power Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Power Expression</em>'.
   * @see org.ddmore.mdl.mdl.PowerExpression
   * @generated
   */
  EClass getPowerExpression();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.PowerExpression#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.PowerExpression#getExpression()
   * @see #getPowerExpression()
   * @generated
   */
  EReference getPowerExpression_Expression();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.PowerExpression#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Operator</em>'.
   * @see org.ddmore.mdl.mdl.PowerExpression#getOperator()
   * @see #getPowerExpression()
   * @generated
   */
  EAttribute getPowerExpression_Operator();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.UnaryExpression <em>Unary Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Unary Expression</em>'.
   * @see org.ddmore.mdl.mdl.UnaryExpression
   * @generated
   */
  EClass getUnaryExpression();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.UnaryExpression#getOperator <em>Operator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Operator</em>'.
   * @see org.ddmore.mdl.mdl.UnaryExpression#getOperator()
   * @see #getUnaryExpression()
   * @generated
   */
  EAttribute getUnaryExpression_Operator();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.UnaryExpression#getExpression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Expression</em>'.
   * @see org.ddmore.mdl.mdl.UnaryExpression#getExpression()
   * @see #getUnaryExpression()
   * @generated
   */
  EReference getUnaryExpression_Expression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.UnaryExpression#getParExpression <em>Par Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Par Expression</em>'.
   * @see org.ddmore.mdl.mdl.UnaryExpression#getParExpression()
   * @see #getUnaryExpression()
   * @generated
   */
  EReference getUnaryExpression_ParExpression();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.UnaryExpression#getPrimary <em>Primary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Primary</em>'.
   * @see org.ddmore.mdl.mdl.UnaryExpression#getPrimary()
   * @see #getUnaryExpression()
   * @generated
   */
  EReference getUnaryExpression_Primary();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.Primary <em>Primary</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Primary</em>'.
   * @see org.ddmore.mdl.mdl.Primary
   * @generated
   */
  EClass getPrimary();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.Primary#getFunctionCall <em>Function Call</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Function Call</em>'.
   * @see org.ddmore.mdl.mdl.Primary#getFunctionCall()
   * @see #getPrimary()
   * @generated
   */
  EReference getPrimary_FunctionCall();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.Primary#getNumber <em>Number</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Number</em>'.
   * @see org.ddmore.mdl.mdl.Primary#getNumber()
   * @see #getPrimary()
   * @generated
   */
  EAttribute getPrimary_Number();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.Primary#getSymbol <em>Symbol</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Symbol</em>'.
   * @see org.ddmore.mdl.mdl.Primary#getSymbol()
   * @see #getPrimary()
   * @generated
   */
  EReference getPrimary_Symbol();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.Primary#getAttribute <em>Attribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Attribute</em>'.
   * @see org.ddmore.mdl.mdl.Primary#getAttribute()
   * @see #getPrimary()
   * @generated
   */
  EReference getPrimary_Attribute();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.Primary#getVector <em>Vector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Vector</em>'.
   * @see org.ddmore.mdl.mdl.Primary#getVector()
   * @see #getPrimary()
   * @generated
   */
  EReference getPrimary_Vector();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.Vector <em>Vector</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Vector</em>'.
   * @see org.ddmore.mdl.mdl.Vector
   * @generated
   */
  EClass getVector();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.Vector#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.Vector#getIdentifier()
   * @see #getVector()
   * @generated
   */
  EAttribute getVector_Identifier();

  /**
   * Returns the meta object for the containment reference list '{@link org.ddmore.mdl.mdl.Vector#getValues <em>Values</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Values</em>'.
   * @see org.ddmore.mdl.mdl.Vector#getValues()
   * @see #getVector()
   * @generated
   */
  EReference getVector_Values();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.FullyQualifiedSymbolName <em>Fully Qualified Symbol Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Fully Qualified Symbol Name</em>'.
   * @see org.ddmore.mdl.mdl.FullyQualifiedSymbolName
   * @generated
   */
  EClass getFullyQualifiedSymbolName();

  /**
   * Returns the meta object for the reference '{@link org.ddmore.mdl.mdl.FullyQualifiedSymbolName#getObject <em>Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Object</em>'.
   * @see org.ddmore.mdl.mdl.FullyQualifiedSymbolName#getObject()
   * @see #getFullyQualifiedSymbolName()
   * @generated
   */
  EReference getFullyQualifiedSymbolName_Object();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.FullyQualifiedSymbolName#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.FullyQualifiedSymbolName#getIdentifier()
   * @see #getFullyQualifiedSymbolName()
   * @generated
   */
  EAttribute getFullyQualifiedSymbolName_Identifier();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.FullyQualifiedArgumentName <em>Fully Qualified Argument Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Fully Qualified Argument Name</em>'.
   * @see org.ddmore.mdl.mdl.FullyQualifiedArgumentName
   * @generated
   */
  EClass getFullyQualifiedArgumentName();

  /**
   * Returns the meta object for the containment reference '{@link org.ddmore.mdl.mdl.FullyQualifiedArgumentName#getParent <em>Parent</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Parent</em>'.
   * @see org.ddmore.mdl.mdl.FullyQualifiedArgumentName#getParent()
   * @see #getFullyQualifiedArgumentName()
   * @generated
   */
  EReference getFullyQualifiedArgumentName_Parent();

  /**
   * Returns the meta object for the attribute list '{@link org.ddmore.mdl.mdl.FullyQualifiedArgumentName#getIdentifier <em>Identifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Identifier</em>'.
   * @see org.ddmore.mdl.mdl.FullyQualifiedArgumentName#getIdentifier()
   * @see #getFullyQualifiedArgumentName()
   * @generated
   */
  EAttribute getFullyQualifiedArgumentName_Identifier();

  /**
   * Returns the meta object for class '{@link org.ddmore.mdl.mdl.ObjectName <em>Object Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Object Name</em>'.
   * @see org.ddmore.mdl.mdl.ObjectName
   * @generated
   */
  EClass getObjectName();

  /**
   * Returns the meta object for the attribute '{@link org.ddmore.mdl.mdl.ObjectName#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.ddmore.mdl.mdl.ObjectName#getName()
   * @see #getObjectName()
   * @generated
   */
  EAttribute getObjectName_Name();

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
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.MclImpl <em>Mcl</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.MclImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getMcl()
     * @generated
     */
    EClass MCL = eINSTANCE.getMcl();

    /**
     * The meta object literal for the '<em><b>Objects</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MCL__OBJECTS = eINSTANCE.getMcl_Objects();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.MclObjectImpl <em>Mcl Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.MclObjectImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getMclObject()
     * @generated
     */
    EClass MCL_OBJECT = eINSTANCE.getMclObject();

    /**
     * The meta object literal for the '<em><b>Model Object</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MCL_OBJECT__MODEL_OBJECT = eINSTANCE.getMclObject_ModelObject();

    /**
     * The meta object literal for the '<em><b>Parameter Object</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MCL_OBJECT__PARAMETER_OBJECT = eINSTANCE.getMclObject_ParameterObject();

    /**
     * The meta object literal for the '<em><b>Data Object</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MCL_OBJECT__DATA_OBJECT = eINSTANCE.getMclObject_DataObject();

    /**
     * The meta object literal for the '<em><b>Task Object</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MCL_OBJECT__TASK_OBJECT = eINSTANCE.getMclObject_TaskObject();

    /**
     * The meta object literal for the '<em><b>Tel Object</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MCL_OBJECT__TEL_OBJECT = eINSTANCE.getMclObject_TelObject();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ModelObjectImpl <em>Model Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ModelObjectImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getModelObject()
     * @generated
     */
    EClass MODEL_OBJECT = eINSTANCE.getModelObject();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJECT__IDENTIFIER = eINSTANCE.getModelObject_Identifier();

    /**
     * The meta object literal for the '<em><b>Blocks</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJECT__BLOCKS = eINSTANCE.getModelObject_Blocks();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ParameterObjectImpl <em>Parameter Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ParameterObjectImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getParameterObject()
     * @generated
     */
    EClass PARAMETER_OBJECT = eINSTANCE.getParameterObject();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETER_OBJECT__IDENTIFIER = eINSTANCE.getParameterObject_Identifier();

    /**
     * The meta object literal for the '<em><b>Blocks</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETER_OBJECT__BLOCKS = eINSTANCE.getParameterObject_Blocks();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.DataObjectImpl <em>Data Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.DataObjectImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDataObject()
     * @generated
     */
    EClass DATA_OBJECT = eINSTANCE.getDataObject();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_OBJECT__IDENTIFIER = eINSTANCE.getDataObject_Identifier();

    /**
     * The meta object literal for the '<em><b>Blocks</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_OBJECT__BLOCKS = eINSTANCE.getDataObject_Blocks();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.TaskObjectImpl <em>Task Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.TaskObjectImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getTaskObject()
     * @generated
     */
    EClass TASK_OBJECT = eINSTANCE.getTaskObject();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TASK_OBJECT__IDENTIFIER = eINSTANCE.getTaskObject_Identifier();

    /**
     * The meta object literal for the '<em><b>Blocks</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TASK_OBJECT__BLOCKS = eINSTANCE.getTaskObject_Blocks();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.TELObjectImpl <em>TEL Object</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.TELObjectImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getTELObject()
     * @generated
     */
    EClass TEL_OBJECT = eINSTANCE.getTELObject();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TEL_OBJECT__IDENTIFIER = eINSTANCE.getTELObject_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TEL_OBJECT__STATEMENTS = eINSTANCE.getTELObject_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ModelObjectBlockImpl <em>Model Object Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ModelObjectBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getModelObjectBlock()
     * @generated
     */
    EClass MODEL_OBJECT_BLOCK = eINSTANCE.getModelObjectBlock();

    /**
     * The meta object literal for the '<em><b>Individual Variables Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJECT_BLOCK__INDIVIDUAL_VARIABLES_BLOCK = eINSTANCE.getModelObjectBlock_IndividualVariablesBlock();

    /**
     * The meta object literal for the '<em><b>Model Prediction Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJECT_BLOCK__MODEL_PREDICTION_BLOCK = eINSTANCE.getModelObjectBlock_ModelPredictionBlock();

    /**
     * The meta object literal for the '<em><b>Random Variable Definition Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJECT_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK = eINSTANCE.getModelObjectBlock_RandomVariableDefinitionBlock();

    /**
     * The meta object literal for the '<em><b>Input Variables Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJECT_BLOCK__INPUT_VARIABLES_BLOCK = eINSTANCE.getModelObjectBlock_InputVariablesBlock();

    /**
     * The meta object literal for the '<em><b>Structural Parameters Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJECT_BLOCK__STRUCTURAL_PARAMETERS_BLOCK = eINSTANCE.getModelObjectBlock_StructuralParametersBlock();

    /**
     * The meta object literal for the '<em><b>Variability Parameters Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJECT_BLOCK__VARIABILITY_PARAMETERS_BLOCK = eINSTANCE.getModelObjectBlock_VariabilityParametersBlock();

    /**
     * The meta object literal for the '<em><b>Output Variables Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJECT_BLOCK__OUTPUT_VARIABLES_BLOCK = eINSTANCE.getModelObjectBlock_OutputVariablesBlock();

    /**
     * The meta object literal for the '<em><b>Group Variables Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJECT_BLOCK__GROUP_VARIABLES_BLOCK = eINSTANCE.getModelObjectBlock_GroupVariablesBlock();

    /**
     * The meta object literal for the '<em><b>Observation Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJECT_BLOCK__OBSERVATION_BLOCK = eINSTANCE.getModelObjectBlock_ObservationBlock();

    /**
     * The meta object literal for the '<em><b>Estimation Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJECT_BLOCK__ESTIMATION_BLOCK = eINSTANCE.getModelObjectBlock_EstimationBlock();

    /**
     * The meta object literal for the '<em><b>Simulation Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJECT_BLOCK__SIMULATION_BLOCK = eINSTANCE.getModelObjectBlock_SimulationBlock();

    /**
     * The meta object literal for the '<em><b>Verbatim Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_OBJECT_BLOCK__VERBATIM_BLOCK = eINSTANCE.getModelObjectBlock_VerbatimBlock();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.IndividualVariablesBlockImpl <em>Individual Variables Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.IndividualVariablesBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getIndividualVariablesBlock()
     * @generated
     */
    EClass INDIVIDUAL_VARIABLES_BLOCK = eINSTANCE.getIndividualVariablesBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INDIVIDUAL_VARIABLES_BLOCK__IDENTIFIER = eINSTANCE.getIndividualVariablesBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INDIVIDUAL_VARIABLES_BLOCK__STATEMENTS = eINSTANCE.getIndividualVariablesBlock_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ModelPredictionBlockImpl <em>Model Prediction Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ModelPredictionBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getModelPredictionBlock()
     * @generated
     */
    EClass MODEL_PREDICTION_BLOCK = eINSTANCE.getModelPredictionBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MODEL_PREDICTION_BLOCK__IDENTIFIER = eINSTANCE.getModelPredictionBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_PREDICTION_BLOCK__STATEMENTS = eINSTANCE.getModelPredictionBlock_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.RandomVariableDefinitionBlockImpl <em>Random Variable Definition Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.RandomVariableDefinitionBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getRandomVariableDefinitionBlock()
     * @generated
     */
    EClass RANDOM_VARIABLE_DEFINITION_BLOCK = eINSTANCE.getRandomVariableDefinitionBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RANDOM_VARIABLE_DEFINITION_BLOCK__IDENTIFIER = eINSTANCE.getRandomVariableDefinitionBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RANDOM_VARIABLE_DEFINITION_BLOCK__VARIABLES = eINSTANCE.getRandomVariableDefinitionBlock_Variables();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.InputVariablesBlockImpl <em>Input Variables Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.InputVariablesBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getInputVariablesBlock()
     * @generated
     */
    EClass INPUT_VARIABLES_BLOCK = eINSTANCE.getInputVariablesBlock();

    /**
     * The meta object literal for the '<em><b>Indentifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INPUT_VARIABLES_BLOCK__INDENTIFIER = eINSTANCE.getInputVariablesBlock_Indentifier();

    /**
     * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INPUT_VARIABLES_BLOCK__VARIABLES = eINSTANCE.getInputVariablesBlock_Variables();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.StructuralParametersBlockImpl <em>Structural Parameters Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.StructuralParametersBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getStructuralParametersBlock()
     * @generated
     */
    EClass STRUCTURAL_PARAMETERS_BLOCK = eINSTANCE.getStructuralParametersBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute STRUCTURAL_PARAMETERS_BLOCK__IDENTIFIER = eINSTANCE.getStructuralParametersBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STRUCTURAL_PARAMETERS_BLOCK__PARAMETERS = eINSTANCE.getStructuralParametersBlock_Parameters();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.VariabilityParametersBlockImpl <em>Variability Parameters Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.VariabilityParametersBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getVariabilityParametersBlock()
     * @generated
     */
    EClass VARIABILITY_PARAMETERS_BLOCK = eINSTANCE.getVariabilityParametersBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VARIABILITY_PARAMETERS_BLOCK__IDENTIFIER = eINSTANCE.getVariabilityParametersBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABILITY_PARAMETERS_BLOCK__PARAMETERS = eINSTANCE.getVariabilityParametersBlock_Parameters();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.OutputVariablesBlockImpl <em>Output Variables Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.OutputVariablesBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getOutputVariablesBlock()
     * @generated
     */
    EClass OUTPUT_VARIABLES_BLOCK = eINSTANCE.getOutputVariablesBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute OUTPUT_VARIABLES_BLOCK__IDENTIFIER = eINSTANCE.getOutputVariablesBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OUTPUT_VARIABLES_BLOCK__VARIABLES = eINSTANCE.getOutputVariablesBlock_Variables();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.GroupVariablesBlockImpl <em>Group Variables Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.GroupVariablesBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getGroupVariablesBlock()
     * @generated
     */
    EClass GROUP_VARIABLES_BLOCK = eINSTANCE.getGroupVariablesBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute GROUP_VARIABLES_BLOCK__IDENTIFIER = eINSTANCE.getGroupVariablesBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GROUP_VARIABLES_BLOCK__STATEMENTS = eINSTANCE.getGroupVariablesBlock_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ObservationBlockImpl <em>Observation Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ObservationBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getObservationBlock()
     * @generated
     */
    EClass OBSERVATION_BLOCK = eINSTANCE.getObservationBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute OBSERVATION_BLOCK__IDENTIFIER = eINSTANCE.getObservationBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OBSERVATION_BLOCK__STATEMENTS = eINSTANCE.getObservationBlock_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.EstimationBlockImpl <em>Estimation Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.EstimationBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getEstimationBlock()
     * @generated
     */
    EClass ESTIMATION_BLOCK = eINSTANCE.getEstimationBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ESTIMATION_BLOCK__IDENTIFIER = eINSTANCE.getEstimationBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ESTIMATION_BLOCK__STATEMENTS = eINSTANCE.getEstimationBlock_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.SimulationBlockImpl <em>Simulation Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.SimulationBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getSimulationBlock()
     * @generated
     */
    EClass SIMULATION_BLOCK = eINSTANCE.getSimulationBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SIMULATION_BLOCK__IDENTIFIER = eINSTANCE.getSimulationBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SIMULATION_BLOCK__STATEMENTS = eINSTANCE.getSimulationBlock_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ParameterObjectBlockImpl <em>Parameter Object Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ParameterObjectBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getParameterObjectBlock()
     * @generated
     */
    EClass PARAMETER_OBJECT_BLOCK = eINSTANCE.getParameterObjectBlock();

    /**
     * The meta object literal for the '<em><b>Structural Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETER_OBJECT_BLOCK__STRUCTURAL_BLOCK = eINSTANCE.getParameterObjectBlock_StructuralBlock();

    /**
     * The meta object literal for the '<em><b>Variability Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETER_OBJECT_BLOCK__VARIABILITY_BLOCK = eINSTANCE.getParameterObjectBlock_VariabilityBlock();

    /**
     * The meta object literal for the '<em><b>Prior Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETER_OBJECT_BLOCK__PRIOR_BLOCK = eINSTANCE.getParameterObjectBlock_PriorBlock();

    /**
     * The meta object literal for the '<em><b>Verbatim Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETER_OBJECT_BLOCK__VERBATIM_BLOCK = eINSTANCE.getParameterObjectBlock_VerbatimBlock();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.StructuralBlockImpl <em>Structural Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.StructuralBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getStructuralBlock()
     * @generated
     */
    EClass STRUCTURAL_BLOCK = eINSTANCE.getStructuralBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute STRUCTURAL_BLOCK__IDENTIFIER = eINSTANCE.getStructuralBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference STRUCTURAL_BLOCK__PARAMETERS = eINSTANCE.getStructuralBlock_Parameters();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.VariabilityBlockImpl <em>Variability Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.VariabilityBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getVariabilityBlock()
     * @generated
     */
    EClass VARIABILITY_BLOCK = eINSTANCE.getVariabilityBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VARIABILITY_BLOCK__IDENTIFIER = eINSTANCE.getVariabilityBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABILITY_BLOCK__STATEMENTS = eINSTANCE.getVariabilityBlock_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.PriorParametersBlockImpl <em>Prior Parameters Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.PriorParametersBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getPriorParametersBlock()
     * @generated
     */
    EClass PRIOR_PARAMETERS_BLOCK = eINSTANCE.getPriorParametersBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIOR_PARAMETERS_BLOCK__IDENTIFIER = eINSTANCE.getPriorParametersBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIOR_PARAMETERS_BLOCK__STATEMENTS = eINSTANCE.getPriorParametersBlock_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.DataObjectBlockImpl <em>Data Object Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.DataObjectBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDataObjectBlock()
     * @generated
     */
    EClass DATA_OBJECT_BLOCK = eINSTANCE.getDataObjectBlock();

    /**
     * The meta object literal for the '<em><b>Header Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_OBJECT_BLOCK__HEADER_BLOCK = eINSTANCE.getDataObjectBlock_HeaderBlock();

    /**
     * The meta object literal for the '<em><b>File Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_OBJECT_BLOCK__FILE_BLOCK = eINSTANCE.getDataObjectBlock_FileBlock();

    /**
     * The meta object literal for the '<em><b>Verbatim Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_OBJECT_BLOCK__VERBATIM_BLOCK = eINSTANCE.getDataObjectBlock_VerbatimBlock();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.HeaderBlockImpl <em>Header Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.HeaderBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getHeaderBlock()
     * @generated
     */
    EClass HEADER_BLOCK = eINSTANCE.getHeaderBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute HEADER_BLOCK__IDENTIFIER = eINSTANCE.getHeaderBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference HEADER_BLOCK__VARIABLES = eINSTANCE.getHeaderBlock_Variables();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.FileBlockImpl <em>File Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.FileBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getFileBlock()
     * @generated
     */
    EClass FILE_BLOCK = eINSTANCE.getFileBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FILE_BLOCK__IDENTIFIER = eINSTANCE.getFileBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILE_BLOCK__STATEMENTS = eINSTANCE.getFileBlock_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.TaskObjectBlockImpl <em>Task Object Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.TaskObjectBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getTaskObjectBlock()
     * @generated
     */
    EClass TASK_OBJECT_BLOCK = eINSTANCE.getTaskObjectBlock();

    /**
     * The meta object literal for the '<em><b>Function Declaration</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TASK_OBJECT_BLOCK__FUNCTION_DECLARATION = eINSTANCE.getTaskObjectBlock_FunctionDeclaration();

    /**
     * The meta object literal for the '<em><b>Parameter Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TASK_OBJECT_BLOCK__PARAMETER_BLOCK = eINSTANCE.getTaskObjectBlock_ParameterBlock();

    /**
     * The meta object literal for the '<em><b>Data Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TASK_OBJECT_BLOCK__DATA_BLOCK = eINSTANCE.getTaskObjectBlock_DataBlock();

    /**
     * The meta object literal for the '<em><b>Model Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TASK_OBJECT_BLOCK__MODEL_BLOCK = eINSTANCE.getTaskObjectBlock_ModelBlock();

    /**
     * The meta object literal for the '<em><b>Verbatim Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TASK_OBJECT_BLOCK__VERBATIM_BLOCK = eINSTANCE.getTaskObjectBlock_VerbatimBlock();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ParameterBlockImpl <em>Parameter Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ParameterBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getParameterBlock()
     * @generated
     */
    EClass PARAMETER_BLOCK = eINSTANCE.getParameterBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PARAMETER_BLOCK__IDENTIFIER = eINSTANCE.getParameterBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETER_BLOCK__PARAMETERS = eINSTANCE.getParameterBlock_Parameters();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.DataBlockImpl <em>Data Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.DataBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDataBlock()
     * @generated
     */
    EClass DATA_BLOCK = eINSTANCE.getDataBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DATA_BLOCK__IDENTIFIER = eINSTANCE.getDataBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_BLOCK__STATEMENTS = eINSTANCE.getDataBlock_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.DataBlockStatementImpl <em>Data Block Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.DataBlockStatementImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDataBlockStatement()
     * @generated
     */
    EClass DATA_BLOCK_STATEMENT = eINSTANCE.getDataBlockStatement();

    /**
     * The meta object literal for the '<em><b>Ignore List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_BLOCK_STATEMENT__IGNORE_LIST = eINSTANCE.getDataBlockStatement_IgnoreList();

    /**
     * The meta object literal for the '<em><b>Accept List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_BLOCK_STATEMENT__ACCEPT_LIST = eINSTANCE.getDataBlockStatement_AcceptList();

    /**
     * The meta object literal for the '<em><b>Drop List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DATA_BLOCK_STATEMENT__DROP_LIST = eINSTANCE.getDataBlockStatement_DropList();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.IgnoreListImpl <em>Ignore List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.IgnoreListImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getIgnoreList()
     * @generated
     */
    EClass IGNORE_LIST = eINSTANCE.getIgnoreList();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute IGNORE_LIST__IDENTIFIER = eINSTANCE.getIgnoreList_Identifier();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IGNORE_LIST__EXPRESSION = eINSTANCE.getIgnoreList_Expression();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.AcceptListImpl <em>Accept List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.AcceptListImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getAcceptList()
     * @generated
     */
    EClass ACCEPT_LIST = eINSTANCE.getAcceptList();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ACCEPT_LIST__IDENTIFIER = eINSTANCE.getAcceptList_Identifier();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ACCEPT_LIST__EXPRESSION = eINSTANCE.getAcceptList_Expression();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.DropListImpl <em>Drop List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.DropListImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDropList()
     * @generated
     */
    EClass DROP_LIST = eINSTANCE.getDropList();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DROP_LIST__IDENTIFIER = eINSTANCE.getDropList_Identifier();

    /**
     * The meta object literal for the '<em><b>List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DROP_LIST__LIST = eINSTANCE.getDropList_List();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ModelBlockImpl <em>Model Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ModelBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getModelBlock()
     * @generated
     */
    EClass MODEL_BLOCK = eINSTANCE.getModelBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MODEL_BLOCK__IDENTIFIER = eINSTANCE.getModelBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_BLOCK__STATEMENTS = eINSTANCE.getModelBlock_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ModelBlockStatementImpl <em>Model Block Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ModelBlockStatementImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getModelBlockStatement()
     * @generated
     */
    EClass MODEL_BLOCK_STATEMENT = eINSTANCE.getModelBlockStatement();

    /**
     * The meta object literal for the '<em><b>Statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_BLOCK_STATEMENT__STATEMENT = eINSTANCE.getModelBlockStatement_Statement();

    /**
     * The meta object literal for the '<em><b>Add List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_BLOCK_STATEMENT__ADD_LIST = eINSTANCE.getModelBlockStatement_AddList();

    /**
     * The meta object literal for the '<em><b>Remove List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_BLOCK_STATEMENT__REMOVE_LIST = eINSTANCE.getModelBlockStatement_RemoveList();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.AddListImpl <em>Add List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.AddListImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getAddList()
     * @generated
     */
    EClass ADD_LIST = eINSTANCE.getAddList();

    /**
     * The meta object literal for the '<em><b>List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ADD_LIST__LIST = eINSTANCE.getAddList_List();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.RemoveListImpl <em>Remove List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.RemoveListImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getRemoveList()
     * @generated
     */
    EClass REMOVE_LIST = eINSTANCE.getRemoveList();

    /**
     * The meta object literal for the '<em><b>List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference REMOVE_LIST__LIST = eINSTANCE.getRemoveList_List();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.SymbolListImpl <em>Symbol List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.SymbolListImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getSymbolList()
     * @generated
     */
    EClass SYMBOL_LIST = eINSTANCE.getSymbolList();

    /**
     * The meta object literal for the '<em><b>Symbols</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SYMBOL_LIST__SYMBOLS = eINSTANCE.getSymbolList_Symbols();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ModelPredictionBlockStatementImpl <em>Model Prediction Block Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ModelPredictionBlockStatementImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getModelPredictionBlockStatement()
     * @generated
     */
    EClass MODEL_PREDICTION_BLOCK_STATEMENT = eINSTANCE.getModelPredictionBlockStatement();

    /**
     * The meta object literal for the '<em><b>Statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_PREDICTION_BLOCK_STATEMENT__STATEMENT = eINSTANCE.getModelPredictionBlockStatement_Statement();

    /**
     * The meta object literal for the '<em><b>Ode Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_PREDICTION_BLOCK_STATEMENT__ODE_BLOCK = eINSTANCE.getModelPredictionBlockStatement_OdeBlock();

    /**
     * The meta object literal for the '<em><b>Library Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MODEL_PREDICTION_BLOCK_STATEMENT__LIBRARY_BLOCK = eINSTANCE.getModelPredictionBlockStatement_LibraryBlock();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.LibraryBlockImpl <em>Library Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.LibraryBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getLibraryBlock()
     * @generated
     */
    EClass LIBRARY_BLOCK = eINSTANCE.getLibraryBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIBRARY_BLOCK__IDENTIFIER = eINSTANCE.getLibraryBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIBRARY_BLOCK__STATEMENTS = eINSTANCE.getLibraryBlock_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.OdeBlockImpl <em>Ode Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.OdeBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getOdeBlock()
     * @generated
     */
    EClass ODE_BLOCK = eINSTANCE.getOdeBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ODE_BLOCK__IDENTIFIER = eINSTANCE.getOdeBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ODE_BLOCK__STATEMENTS = eINSTANCE.getOdeBlock_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.GroupVariablesBlockStatementImpl <em>Group Variables Block Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.GroupVariablesBlockStatementImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getGroupVariablesBlockStatement()
     * @generated
     */
    EClass GROUP_VARIABLES_BLOCK_STATEMENT = eINSTANCE.getGroupVariablesBlockStatement();

    /**
     * The meta object literal for the '<em><b>Statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GROUP_VARIABLES_BLOCK_STATEMENT__STATEMENT = eINSTANCE.getGroupVariablesBlockStatement_Statement();

    /**
     * The meta object literal for the '<em><b>Mixture Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference GROUP_VARIABLES_BLOCK_STATEMENT__MIXTURE_BLOCK = eINSTANCE.getGroupVariablesBlockStatement_MixtureBlock();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.MixtureBlockImpl <em>Mixture Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.MixtureBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getMixtureBlock()
     * @generated
     */
    EClass MIXTURE_BLOCK = eINSTANCE.getMixtureBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MIXTURE_BLOCK__IDENTIFIER = eINSTANCE.getMixtureBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MIXTURE_BLOCK__STATEMENTS = eINSTANCE.getMixtureBlock_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.VariabilityBlockStatementImpl <em>Variability Block Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.VariabilityBlockStatementImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getVariabilityBlockStatement()
     * @generated
     */
    EClass VARIABILITY_BLOCK_STATEMENT = eINSTANCE.getVariabilityBlockStatement();

    /**
     * The meta object literal for the '<em><b>Parameter</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABILITY_BLOCK_STATEMENT__PARAMETER = eINSTANCE.getVariabilityBlockStatement_Parameter();

    /**
     * The meta object literal for the '<em><b>Block Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK = eINSTANCE.getVariabilityBlockStatement_BlockBlock();

    /**
     * The meta object literal for the '<em><b>Diag Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK = eINSTANCE.getVariabilityBlockStatement_DiagBlock();

    /**
     * The meta object literal for the '<em><b>Same Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABILITY_BLOCK_STATEMENT__SAME_BLOCK = eINSTANCE.getVariabilityBlockStatement_SameBlock();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.BlockBlockImpl <em>Block Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.BlockBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getBlockBlock()
     * @generated
     */
    EClass BLOCK_BLOCK = eINSTANCE.getBlockBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BLOCK_BLOCK__IDENTIFIER = eINSTANCE.getBlockBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BLOCK_BLOCK__ARGUMENTS = eINSTANCE.getBlockBlock_Arguments();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BLOCK_BLOCK__PARAMETERS = eINSTANCE.getBlockBlock_Parameters();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.DiagBlockImpl <em>Diag Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.DiagBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDiagBlock()
     * @generated
     */
    EClass DIAG_BLOCK = eINSTANCE.getDiagBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DIAG_BLOCK__IDENTIFIER = eINSTANCE.getDiagBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DIAG_BLOCK__ARGUMENTS = eINSTANCE.getDiagBlock_Arguments();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DIAG_BLOCK__PARAMETERS = eINSTANCE.getDiagBlock_Parameters();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.SameBlockImpl <em>Same Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.SameBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getSameBlock()
     * @generated
     */
    EClass SAME_BLOCK = eINSTANCE.getSameBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SAME_BLOCK__IDENTIFIER = eINSTANCE.getSameBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SAME_BLOCK__ARGUMENTS = eINSTANCE.getSameBlock_Arguments();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SAME_BLOCK__PARAMETERS = eINSTANCE.getSameBlock_Parameters();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.FileBlockStatementImpl <em>File Block Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.FileBlockStatementImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getFileBlockStatement()
     * @generated
     */
    EClass FILE_BLOCK_STATEMENT = eINSTANCE.getFileBlockStatement();

    /**
     * The meta object literal for the '<em><b>Variable</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILE_BLOCK_STATEMENT__VARIABLE = eINSTANCE.getFileBlockStatement_Variable();

    /**
     * The meta object literal for the '<em><b>Inline Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILE_BLOCK_STATEMENT__INLINE_BLOCK = eINSTANCE.getFileBlockStatement_InlineBlock();

    /**
     * The meta object literal for the '<em><b>Design Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILE_BLOCK_STATEMENT__DESIGN_BLOCK = eINSTANCE.getFileBlockStatement_DesignBlock();

    /**
     * The meta object literal for the '<em><b>Rscript Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FILE_BLOCK_STATEMENT__RSCRIPT_BLOCK = eINSTANCE.getFileBlockStatement_RscriptBlock();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.InlineBlockImpl <em>Inline Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.InlineBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getInlineBlock()
     * @generated
     */
    EClass INLINE_BLOCK = eINSTANCE.getInlineBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INLINE_BLOCK__IDENTIFIER = eINSTANCE.getInlineBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference INLINE_BLOCK__VARIABLES = eINSTANCE.getInlineBlock_Variables();

    /**
     * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INLINE_BLOCK__VALUES = eINSTANCE.getInlineBlock_Values();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.DesignBlockImpl <em>Design Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.DesignBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDesignBlock()
     * @generated
     */
    EClass DESIGN_BLOCK = eINSTANCE.getDesignBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DESIGN_BLOCK__IDENTIFIER = eINSTANCE.getDesignBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DESIGN_BLOCK__STATEMENTS = eINSTANCE.getDesignBlock_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.DesignBlockStatementImpl <em>Design Block Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.DesignBlockStatementImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDesignBlockStatement()
     * @generated
     */
    EClass DESIGN_BLOCK_STATEMENT = eINSTANCE.getDesignBlockStatement();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DESIGN_BLOCK_STATEMENT__IDENTIFIER = eINSTANCE.getDesignBlockStatement_Identifier();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DESIGN_BLOCK_STATEMENT__ARGUMENTS = eINSTANCE.getDesignBlockStatement_Arguments();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference DESIGN_BLOCK_STATEMENT__EXPRESSION = eINSTANCE.getDesignBlockStatement_Expression();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.VariableListImpl <em>Variable List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.VariableListImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getVariableList()
     * @generated
     */
    EClass VARIABLE_LIST = eINSTANCE.getVariableList();

    /**
     * The meta object literal for the '<em><b>Identifiers</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABLE_LIST__IDENTIFIERS = eINSTANCE.getVariableList_Identifiers();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.RScriptBlockImpl <em>RScript Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.RScriptBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getRScriptBlock()
     * @generated
     */
    EClass RSCRIPT_BLOCK = eINSTANCE.getRScriptBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RSCRIPT_BLOCK__IDENTIFIER = eINSTANCE.getRScriptBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RSCRIPT_BLOCK__VARIABLES = eINSTANCE.getRScriptBlock_Variables();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.RScriptBlockStatementImpl <em>RScript Block Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.RScriptBlockStatementImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getRScriptBlockStatement()
     * @generated
     */
    EClass RSCRIPT_BLOCK_STATEMENT = eINSTANCE.getRScriptBlockStatement();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RSCRIPT_BLOCK_STATEMENT__IDENTIFIER = eINSTANCE.getRScriptBlockStatement_Identifier();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute RSCRIPT_BLOCK_STATEMENT__VALUE = eINSTANCE.getRScriptBlockStatement_Value();

    /**
     * The meta object literal for the '<em><b>Object</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RSCRIPT_BLOCK_STATEMENT__OBJECT = eINSTANCE.getRScriptBlockStatement_Object();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.TaskFunctionDeclarationImpl <em>Task Function Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.TaskFunctionDeclarationImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getTaskFunctionDeclaration()
     * @generated
     */
    EClass TASK_FUNCTION_DECLARATION = eINSTANCE.getTaskFunctionDeclaration();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TASK_FUNCTION_DECLARATION__IDENTIFIER = eINSTANCE.getTaskFunctionDeclaration_Identifier();

    /**
     * The meta object literal for the '<em><b>Formal Arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TASK_FUNCTION_DECLARATION__FORMAL_ARGUMENTS = eINSTANCE.getTaskFunctionDeclaration_FormalArguments();

    /**
     * The meta object literal for the '<em><b>Function Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TASK_FUNCTION_DECLARATION__FUNCTION_BODY = eINSTANCE.getTaskFunctionDeclaration_FunctionBody();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.TaskFunctionBodyImpl <em>Task Function Body</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.TaskFunctionBodyImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getTaskFunctionBody()
     * @generated
     */
    EClass TASK_FUNCTION_BODY = eINSTANCE.getTaskFunctionBody();

    /**
     * The meta object literal for the '<em><b>Blocks</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TASK_FUNCTION_BODY__BLOCKS = eINSTANCE.getTaskFunctionBody_Blocks();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.TaskFunctionBlockImpl <em>Task Function Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.TaskFunctionBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getTaskFunctionBlock()
     * @generated
     */
    EClass TASK_FUNCTION_BLOCK = eINSTANCE.getTaskFunctionBlock();

    /**
     * The meta object literal for the '<em><b>Estimate Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TASK_FUNCTION_BLOCK__ESTIMATE_BLOCK = eINSTANCE.getTaskFunctionBlock_EstimateBlock();

    /**
     * The meta object literal for the '<em><b>Simulate Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TASK_FUNCTION_BLOCK__SIMULATE_BLOCK = eINSTANCE.getTaskFunctionBlock_SimulateBlock();

    /**
     * The meta object literal for the '<em><b>Execute Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TASK_FUNCTION_BLOCK__EXECUTE_BLOCK = eINSTANCE.getTaskFunctionBlock_ExecuteBlock();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.EstimateTaskImpl <em>Estimate Task</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.EstimateTaskImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getEstimateTask()
     * @generated
     */
    EClass ESTIMATE_TASK = eINSTANCE.getEstimateTask();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ESTIMATE_TASK__IDENTIFIER = eINSTANCE.getEstimateTask_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ESTIMATE_TASK__STATEMENTS = eINSTANCE.getEstimateTask_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.SimulateTaskImpl <em>Simulate Task</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.SimulateTaskImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getSimulateTask()
     * @generated
     */
    EClass SIMULATE_TASK = eINSTANCE.getSimulateTask();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SIMULATE_TASK__IDENTIFIER = eINSTANCE.getSimulateTask_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SIMULATE_TASK__STATEMENTS = eINSTANCE.getSimulateTask_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ExecuteTaskImpl <em>Execute Task</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ExecuteTaskImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getExecuteTask()
     * @generated
     */
    EClass EXECUTE_TASK = eINSTANCE.getExecuteTask();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXECUTE_TASK__IDENTIFIER = eINSTANCE.getExecuteTask_Identifier();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXECUTE_TASK__STATEMENTS = eINSTANCE.getExecuteTask_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.FormalArgumentsImpl <em>Formal Arguments</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.FormalArgumentsImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getFormalArguments()
     * @generated
     */
    EClass FORMAL_ARGUMENTS = eINSTANCE.getFormalArguments();

    /**
     * The meta object literal for the '<em><b>Identifiers</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FORMAL_ARGUMENTS__IDENTIFIERS = eINSTANCE.getFormalArguments_Identifiers();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.FunctionCallImpl <em>Function Call</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.FunctionCallImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getFunctionCall()
     * @generated
     */
    EClass FUNCTION_CALL = eINSTANCE.getFunctionCall();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_CALL__IDENTIFIER = eINSTANCE.getFunctionCall_Identifier();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FUNCTION_CALL__ARGUMENTS = eINSTANCE.getFunctionCall_Arguments();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.BlockStatementImpl <em>Block Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.BlockStatementImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getBlockStatement()
     * @generated
     */
    EClass BLOCK_STATEMENT = eINSTANCE.getBlockStatement();

    /**
     * The meta object literal for the '<em><b>Symbol</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BLOCK_STATEMENT__SYMBOL = eINSTANCE.getBlockStatement_Symbol();

    /**
     * The meta object literal for the '<em><b>Function Call</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BLOCK_STATEMENT__FUNCTION_CALL = eINSTANCE.getBlockStatement_FunctionCall();

    /**
     * The meta object literal for the '<em><b>Statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BLOCK_STATEMENT__STATEMENT = eINSTANCE.getBlockStatement_Statement();

    /**
     * The meta object literal for the '<em><b>Verbatim Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BLOCK_STATEMENT__VERBATIM_BLOCK = eINSTANCE.getBlockStatement_VerbatimBlock();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.VerbatimBlockImpl <em>Verbatim Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.VerbatimBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getVerbatimBlock()
     * @generated
     */
    EClass VERBATIM_BLOCK = eINSTANCE.getVerbatimBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VERBATIM_BLOCK__IDENTIFIER = eINSTANCE.getVerbatimBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VERBATIM_BLOCK__BLOCK = eINSTANCE.getVerbatimBlock_Block();

    /**
     * The meta object literal for the '<em><b>External Code</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VERBATIM_BLOCK__EXTERNAL_CODE = eINSTANCE.getVerbatimBlock_ExternalCode();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.TargetBlockImpl <em>Target Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.TargetBlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getTargetBlock()
     * @generated
     */
    EClass TARGET_BLOCK = eINSTANCE.getTargetBlock();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TARGET_BLOCK__IDENTIFIER = eINSTANCE.getTargetBlock_Identifier();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TARGET_BLOCK__ARGUMENTS = eINSTANCE.getTargetBlock_Arguments();

    /**
     * The meta object literal for the '<em><b>External Code</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TARGET_BLOCK__EXTERNAL_CODE = eINSTANCE.getTargetBlock_ExternalCode();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.SymbolModificationImpl <em>Symbol Modification</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.SymbolModificationImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getSymbolModification()
     * @generated
     */
    EClass SYMBOL_MODIFICATION = eINSTANCE.getSymbolModification();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SYMBOL_MODIFICATION__IDENTIFIER = eINSTANCE.getSymbolModification_Identifier();

    /**
     * The meta object literal for the '<em><b>List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SYMBOL_MODIFICATION__LIST = eINSTANCE.getSymbolModification_List();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ParameterDeclarationImpl <em>Parameter Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ParameterDeclarationImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getParameterDeclaration()
     * @generated
     */
    EClass PARAMETER_DECLARATION = eINSTANCE.getParameterDeclaration();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PARAMETER_DECLARATION__IDENTIFIER = eINSTANCE.getParameterDeclaration_Identifier();

    /**
     * The meta object literal for the '<em><b>List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PARAMETER_DECLARATION__LIST = eINSTANCE.getParameterDeclaration_List();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.SymbolDeclarationImpl <em>Symbol Declaration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.SymbolDeclarationImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getSymbolDeclaration()
     * @generated
     */
    EClass SYMBOL_DECLARATION = eINSTANCE.getSymbolDeclaration();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SYMBOL_DECLARATION__IDENTIFIER = eINSTANCE.getSymbolDeclaration_Identifier();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SYMBOL_DECLARATION__EXPRESSION = eINSTANCE.getSymbolDeclaration_Expression();

    /**
     * The meta object literal for the '<em><b>Random List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SYMBOL_DECLARATION__RANDOM_LIST = eINSTANCE.getSymbolDeclaration_RandomList();

    /**
     * The meta object literal for the '<em><b>Function</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SYMBOL_DECLARATION__FUNCTION = eINSTANCE.getSymbolDeclaration_Function();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.EnumTypeImpl <em>Enum Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.EnumTypeImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getEnumType()
     * @generated
     */
    EClass ENUM_TYPE = eINSTANCE.getEnumType();

    /**
     * The meta object literal for the '<em><b>Categorical</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUM_TYPE__CATEGORICAL = eINSTANCE.getEnumType_Categorical();

    /**
     * The meta object literal for the '<em><b>Continuous</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUM_TYPE__CONTINUOUS = eINSTANCE.getEnumType_Continuous();

    /**
     * The meta object literal for the '<em><b>Covariate</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUM_TYPE__COVARIATE = eINSTANCE.getEnumType_Covariate();

    /**
     * The meta object literal for the '<em><b>Distribution</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUM_TYPE__DISTRIBUTION = eINSTANCE.getEnumType_Distribution();

    /**
     * The meta object literal for the '<em><b>Level</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUM_TYPE__LEVEL = eINSTANCE.getEnumType_Level();

    /**
     * The meta object literal for the '<em><b>Likelyhood</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUM_TYPE__LIKELYHOOD = eINSTANCE.getEnumType_Likelyhood();

    /**
     * The meta object literal for the '<em><b>Missing</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ENUM_TYPE__MISSING = eINSTANCE.getEnumType_Missing();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.MissingImpl <em>Missing</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.MissingImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getMissing()
     * @generated
     */
    EClass MISSING = eINSTANCE.getMissing();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MISSING__IDENTIFIER = eINSTANCE.getMissing_Identifier();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.LikelyhoodImpl <em>Likelyhood</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.LikelyhoodImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getLikelyhood()
     * @generated
     */
    EClass LIKELYHOOD = eINSTANCE.getLikelyhood();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LIKELYHOOD__IDENTIFIER = eINSTANCE.getLikelyhood_Identifier();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.LevelTypeImpl <em>Level Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.LevelTypeImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getLevelType()
     * @generated
     */
    EClass LEVEL_TYPE = eINSTANCE.getLevelType();

    /**
     * The meta object literal for the '<em><b>Mdv</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LEVEL_TYPE__MDV = eINSTANCE.getLevelType_Mdv();

    /**
     * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LEVEL_TYPE__ID = eINSTANCE.getLevelType_Id();

    /**
     * The meta object literal for the '<em><b>Dv</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LEVEL_TYPE__DV = eINSTANCE.getLevelType_Dv();

    /**
     * The meta object literal for the '<em><b>Idv</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LEVEL_TYPE__IDV = eINSTANCE.getLevelType_Idv();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.CategoricalImpl <em>Categorical</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.CategoricalImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getCategorical()
     * @generated
     */
    EClass CATEGORICAL = eINSTANCE.getCategorical();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CATEGORICAL__IDENTIFIER = eINSTANCE.getCategorical_Identifier();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CATEGORICAL__ARGUMENTS = eINSTANCE.getCategorical_Arguments();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ContinuousImpl <em>Continuous</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ContinuousImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getContinuous()
     * @generated
     */
    EClass CONTINUOUS = eINSTANCE.getContinuous();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONTINUOUS__IDENTIFIER = eINSTANCE.getContinuous_Identifier();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.CovariateImpl <em>Covariate</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.CovariateImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getCovariate()
     * @generated
     */
    EClass COVARIATE = eINSTANCE.getCovariate();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COVARIATE__IDENTIFIER = eINSTANCE.getCovariate_Identifier();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.DistributionImpl <em>Distribution</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.DistributionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getDistribution()
     * @generated
     */
    EClass DISTRIBUTION = eINSTANCE.getDistribution();

    /**
     * The meta object literal for the '<em><b>Normal</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DISTRIBUTION__NORMAL = eINSTANCE.getDistribution_Normal();

    /**
     * The meta object literal for the '<em><b>Binomial</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DISTRIBUTION__BINOMIAL = eINSTANCE.getDistribution_Binomial();

    /**
     * The meta object literal for the '<em><b>Poisson</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DISTRIBUTION__POISSON = eINSTANCE.getDistribution_Poisson();

    /**
     * The meta object literal for the '<em><b>Student t</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DISTRIBUTION__STUDENT_T = eINSTANCE.getDistribution_Student_t();

    /**
     * The meta object literal for the '<em><b>Mvnormal</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute DISTRIBUTION__MVNORMAL = eINSTANCE.getDistribution_Mvnormal();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.AnyExpressionImpl <em>Any Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.AnyExpressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getAnyExpression()
     * @generated
     */
    EClass ANY_EXPRESSION = eINSTANCE.getAnyExpression();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANY_EXPRESSION__EXPRESSION = eINSTANCE.getAnyExpression_Expression();

    /**
     * The meta object literal for the '<em><b>List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANY_EXPRESSION__LIST = eINSTANCE.getAnyExpression_List();

    /**
     * The meta object literal for the '<em><b>Ode List</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANY_EXPRESSION__ODE_LIST = eINSTANCE.getAnyExpression_OdeList();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ANY_EXPRESSION__TYPE = eINSTANCE.getAnyExpression_Type();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ExpressionImpl <em>Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ExpressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getExpression()
     * @generated
     */
    EClass EXPRESSION = eINSTANCE.getExpression();

    /**
     * The meta object literal for the '<em><b>Conditional Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION__CONDITIONAL_EXPRESSION = eINSTANCE.getExpression_ConditionalExpression();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ListImpl <em>List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ListImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getList()
     * @generated
     */
    EClass LIST = eINSTANCE.getList();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LIST__ARGUMENTS = eINSTANCE.getList_Arguments();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.OdeListImpl <em>Ode List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.OdeListImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getOdeList()
     * @generated
     */
    EClass ODE_LIST = eINSTANCE.getOdeList();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ODE_LIST__ARGUMENTS = eINSTANCE.getOdeList_Arguments();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.RandomListImpl <em>Random List</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.RandomListImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getRandomList()
     * @generated
     */
    EClass RANDOM_LIST = eINSTANCE.getRandomList();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference RANDOM_LIST__ARGUMENTS = eINSTANCE.getRandomList_Arguments();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ArgumentsImpl <em>Arguments</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ArgumentsImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getArguments()
     * @generated
     */
    EClass ARGUMENTS = eINSTANCE.getArguments();

    /**
     * The meta object literal for the '<em><b>Arguments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARGUMENTS__ARGUMENTS = eINSTANCE.getArguments_Arguments();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ArgumentImpl <em>Argument</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ArgumentImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getArgument()
     * @generated
     */
    EClass ARGUMENT = eINSTANCE.getArgument();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ARGUMENT__IDENTIFIER = eINSTANCE.getArgument_Identifier();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ARGUMENT__EXPRESSION = eINSTANCE.getArgument_Expression();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ConditionalStatementImpl <em>Conditional Statement</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ConditionalStatementImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getConditionalStatement()
     * @generated
     */
    EClass CONDITIONAL_STATEMENT = eINSTANCE.getConditionalStatement();

    /**
     * The meta object literal for the '<em><b>Par Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_STATEMENT__PAR_EXPRESSION = eINSTANCE.getConditionalStatement_ParExpression();

    /**
     * The meta object literal for the '<em><b>If Statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_STATEMENT__IF_STATEMENT = eINSTANCE.getConditionalStatement_IfStatement();

    /**
     * The meta object literal for the '<em><b>If Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_STATEMENT__IF_BLOCK = eINSTANCE.getConditionalStatement_IfBlock();

    /**
     * The meta object literal for the '<em><b>Else Statement</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_STATEMENT__ELSE_STATEMENT = eINSTANCE.getConditionalStatement_ElseStatement();

    /**
     * The meta object literal for the '<em><b>Else Block</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_STATEMENT__ELSE_BLOCK = eINSTANCE.getConditionalStatement_ElseBlock();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.BlockImpl <em>Block</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.BlockImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getBlock()
     * @generated
     */
    EClass BLOCK = eINSTANCE.getBlock();

    /**
     * The meta object literal for the '<em><b>Statements</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference BLOCK__STATEMENTS = eINSTANCE.getBlock_Statements();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ParExpressionImpl <em>Par Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ParExpressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getParExpression()
     * @generated
     */
    EClass PAR_EXPRESSION = eINSTANCE.getParExpression();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PAR_EXPRESSION__EXPRESSION = eINSTANCE.getParExpression_Expression();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ConditionalExpressionImpl <em>Conditional Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ConditionalExpressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getConditionalExpression()
     * @generated
     */
    EClass CONDITIONAL_EXPRESSION = eINSTANCE.getConditionalExpression();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_EXPRESSION__EXPRESSION = eINSTANCE.getConditionalExpression_Expression();

    /**
     * The meta object literal for the '<em><b>Expression1</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_EXPRESSION__EXPRESSION1 = eINSTANCE.getConditionalExpression_Expression1();

    /**
     * The meta object literal for the '<em><b>Expression2</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONDITIONAL_EXPRESSION__EXPRESSION2 = eINSTANCE.getConditionalExpression_Expression2();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.OrExpressionImpl <em>Or Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.OrExpressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getOrExpression()
     * @generated
     */
    EClass OR_EXPRESSION = eINSTANCE.getOrExpression();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OR_EXPRESSION__EXPRESSION = eINSTANCE.getOrExpression_Expression();

    /**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute OR_EXPRESSION__OPERATOR = eINSTANCE.getOrExpression_Operator();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.AndExpressionImpl <em>And Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.AndExpressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getAndExpression()
     * @generated
     */
    EClass AND_EXPRESSION = eINSTANCE.getAndExpression();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference AND_EXPRESSION__EXPRESSION = eINSTANCE.getAndExpression_Expression();

    /**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute AND_EXPRESSION__OPERATOR = eINSTANCE.getAndExpression_Operator();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.LogicalExpressionImpl <em>Logical Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.LogicalExpressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getLogicalExpression()
     * @generated
     */
    EClass LOGICAL_EXPRESSION = eINSTANCE.getLogicalExpression();

    /**
     * The meta object literal for the '<em><b>Negation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LOGICAL_EXPRESSION__NEGATION = eINSTANCE.getLogicalExpression_Negation();

    /**
     * The meta object literal for the '<em><b>Boolean</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LOGICAL_EXPRESSION__BOOLEAN = eINSTANCE.getLogicalExpression_Boolean();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LOGICAL_EXPRESSION__EXPRESSION = eINSTANCE.getLogicalExpression_Expression();

    /**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LOGICAL_EXPRESSION__OPERATOR = eINSTANCE.getLogicalExpression_Operator();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.AdditiveExpressionImpl <em>Additive Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.AdditiveExpressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getAdditiveExpression()
     * @generated
     */
    EClass ADDITIVE_EXPRESSION = eINSTANCE.getAdditiveExpression();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ADDITIVE_EXPRESSION__EXPRESSION = eINSTANCE.getAdditiveExpression_Expression();

    /**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ADDITIVE_EXPRESSION__OPERATOR = eINSTANCE.getAdditiveExpression_Operator();

    /**
     * The meta object literal for the '<em><b>String</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ADDITIVE_EXPRESSION__STRING = eINSTANCE.getAdditiveExpression_String();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.MultiplicativeExpressionImpl <em>Multiplicative Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.MultiplicativeExpressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getMultiplicativeExpression()
     * @generated
     */
    EClass MULTIPLICATIVE_EXPRESSION = eINSTANCE.getMultiplicativeExpression();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference MULTIPLICATIVE_EXPRESSION__EXPRESSION = eINSTANCE.getMultiplicativeExpression_Expression();

    /**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MULTIPLICATIVE_EXPRESSION__OPERATOR = eINSTANCE.getMultiplicativeExpression_Operator();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.PowerExpressionImpl <em>Power Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.PowerExpressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getPowerExpression()
     * @generated
     */
    EClass POWER_EXPRESSION = eINSTANCE.getPowerExpression();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference POWER_EXPRESSION__EXPRESSION = eINSTANCE.getPowerExpression_Expression();

    /**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute POWER_EXPRESSION__OPERATOR = eINSTANCE.getPowerExpression_Operator();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.UnaryExpressionImpl <em>Unary Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.UnaryExpressionImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getUnaryExpression()
     * @generated
     */
    EClass UNARY_EXPRESSION = eINSTANCE.getUnaryExpression();

    /**
     * The meta object literal for the '<em><b>Operator</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute UNARY_EXPRESSION__OPERATOR = eINSTANCE.getUnaryExpression_Operator();

    /**
     * The meta object literal for the '<em><b>Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference UNARY_EXPRESSION__EXPRESSION = eINSTANCE.getUnaryExpression_Expression();

    /**
     * The meta object literal for the '<em><b>Par Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference UNARY_EXPRESSION__PAR_EXPRESSION = eINSTANCE.getUnaryExpression_ParExpression();

    /**
     * The meta object literal for the '<em><b>Primary</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference UNARY_EXPRESSION__PRIMARY = eINSTANCE.getUnaryExpression_Primary();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.PrimaryImpl <em>Primary</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.PrimaryImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getPrimary()
     * @generated
     */
    EClass PRIMARY = eINSTANCE.getPrimary();

    /**
     * The meta object literal for the '<em><b>Function Call</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__FUNCTION_CALL = eINSTANCE.getPrimary_FunctionCall();

    /**
     * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PRIMARY__NUMBER = eINSTANCE.getPrimary_Number();

    /**
     * The meta object literal for the '<em><b>Symbol</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__SYMBOL = eINSTANCE.getPrimary_Symbol();

    /**
     * The meta object literal for the '<em><b>Attribute</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__ATTRIBUTE = eINSTANCE.getPrimary_Attribute();

    /**
     * The meta object literal for the '<em><b>Vector</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PRIMARY__VECTOR = eINSTANCE.getPrimary_Vector();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.VectorImpl <em>Vector</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.VectorImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getVector()
     * @generated
     */
    EClass VECTOR = eINSTANCE.getVector();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VECTOR__IDENTIFIER = eINSTANCE.getVector_Identifier();

    /**
     * The meta object literal for the '<em><b>Values</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VECTOR__VALUES = eINSTANCE.getVector_Values();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.FullyQualifiedSymbolNameImpl <em>Fully Qualified Symbol Name</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.FullyQualifiedSymbolNameImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getFullyQualifiedSymbolName()
     * @generated
     */
    EClass FULLY_QUALIFIED_SYMBOL_NAME = eINSTANCE.getFullyQualifiedSymbolName();

    /**
     * The meta object literal for the '<em><b>Object</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FULLY_QUALIFIED_SYMBOL_NAME__OBJECT = eINSTANCE.getFullyQualifiedSymbolName_Object();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FULLY_QUALIFIED_SYMBOL_NAME__IDENTIFIER = eINSTANCE.getFullyQualifiedSymbolName_Identifier();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.FullyQualifiedArgumentNameImpl <em>Fully Qualified Argument Name</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.FullyQualifiedArgumentNameImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getFullyQualifiedArgumentName()
     * @generated
     */
    EClass FULLY_QUALIFIED_ARGUMENT_NAME = eINSTANCE.getFullyQualifiedArgumentName();

    /**
     * The meta object literal for the '<em><b>Parent</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FULLY_QUALIFIED_ARGUMENT_NAME__PARENT = eINSTANCE.getFullyQualifiedArgumentName_Parent();

    /**
     * The meta object literal for the '<em><b>Identifier</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FULLY_QUALIFIED_ARGUMENT_NAME__IDENTIFIER = eINSTANCE.getFullyQualifiedArgumentName_Identifier();

    /**
     * The meta object literal for the '{@link org.ddmore.mdl.mdl.impl.ObjectNameImpl <em>Object Name</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.ddmore.mdl.mdl.impl.ObjectNameImpl
     * @see org.ddmore.mdl.mdl.impl.MdlPackageImpl#getObjectName()
     * @generated
     */
    EClass OBJECT_NAME = eINSTANCE.getObjectName();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute OBJECT_NAME__NAME = eINSTANCE.getObjectName_Name();

  }

} //MdlPackage
