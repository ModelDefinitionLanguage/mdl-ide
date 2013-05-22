/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.EstimationBlock;
import org.ddmore.mdl.mdl.GroupVariablesBlock;
import org.ddmore.mdl.mdl.IndividualVariablesBlock;
import org.ddmore.mdl.mdl.InputVariablesBlock;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.ModelObjectBlock;
import org.ddmore.mdl.mdl.ModelPredictionBlock;
import org.ddmore.mdl.mdl.ObservationBlock;
import org.ddmore.mdl.mdl.OutputVariablesBlock;
import org.ddmore.mdl.mdl.RandomVariableDefinitionBlock;
import org.ddmore.mdl.mdl.SimulationBlock;
import org.ddmore.mdl.mdl.StructuralParametersBlock;
import org.ddmore.mdl.mdl.VariabilityParametersBlock;
import org.ddmore.mdl.mdl.VerbatimBlock;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Object Block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelObjectBlockImpl#getIndividualVariablesBlock <em>Individual Variables Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelObjectBlockImpl#getModelPredictionBlock <em>Model Prediction Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelObjectBlockImpl#getRandomVariableDefinitionBlock <em>Random Variable Definition Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelObjectBlockImpl#getInputVariablesBlock <em>Input Variables Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelObjectBlockImpl#getStructuralParametersBlock <em>Structural Parameters Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelObjectBlockImpl#getVariabilityParametersBlock <em>Variability Parameters Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelObjectBlockImpl#getOutputVariablesBlock <em>Output Variables Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelObjectBlockImpl#getGroupVariablesBlock <em>Group Variables Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelObjectBlockImpl#getObservationBlock <em>Observation Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelObjectBlockImpl#getEstimationBlock <em>Estimation Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelObjectBlockImpl#getSimulationBlock <em>Simulation Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelObjectBlockImpl#getVerbatimBlock <em>Verbatim Block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelObjectBlockImpl extends MinimalEObjectImpl.Container implements ModelObjectBlock
{
  /**
   * The cached value of the '{@link #getIndividualVariablesBlock() <em>Individual Variables Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIndividualVariablesBlock()
   * @generated
   * @ordered
   */
  protected IndividualVariablesBlock individualVariablesBlock;

  /**
   * The cached value of the '{@link #getModelPredictionBlock() <em>Model Prediction Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelPredictionBlock()
   * @generated
   * @ordered
   */
  protected ModelPredictionBlock modelPredictionBlock;

  /**
   * The cached value of the '{@link #getRandomVariableDefinitionBlock() <em>Random Variable Definition Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRandomVariableDefinitionBlock()
   * @generated
   * @ordered
   */
  protected RandomVariableDefinitionBlock randomVariableDefinitionBlock;

  /**
   * The cached value of the '{@link #getInputVariablesBlock() <em>Input Variables Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInputVariablesBlock()
   * @generated
   * @ordered
   */
  protected InputVariablesBlock inputVariablesBlock;

  /**
   * The cached value of the '{@link #getStructuralParametersBlock() <em>Structural Parameters Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStructuralParametersBlock()
   * @generated
   * @ordered
   */
  protected StructuralParametersBlock structuralParametersBlock;

  /**
   * The cached value of the '{@link #getVariabilityParametersBlock() <em>Variability Parameters Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariabilityParametersBlock()
   * @generated
   * @ordered
   */
  protected VariabilityParametersBlock variabilityParametersBlock;

  /**
   * The cached value of the '{@link #getOutputVariablesBlock() <em>Output Variables Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOutputVariablesBlock()
   * @generated
   * @ordered
   */
  protected OutputVariablesBlock outputVariablesBlock;

  /**
   * The cached value of the '{@link #getGroupVariablesBlock() <em>Group Variables Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroupVariablesBlock()
   * @generated
   * @ordered
   */
  protected GroupVariablesBlock groupVariablesBlock;

  /**
   * The cached value of the '{@link #getObservationBlock() <em>Observation Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getObservationBlock()
   * @generated
   * @ordered
   */
  protected ObservationBlock observationBlock;

  /**
   * The cached value of the '{@link #getEstimationBlock() <em>Estimation Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEstimationBlock()
   * @generated
   * @ordered
   */
  protected EstimationBlock estimationBlock;

  /**
   * The cached value of the '{@link #getSimulationBlock() <em>Simulation Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSimulationBlock()
   * @generated
   * @ordered
   */
  protected SimulationBlock simulationBlock;

  /**
   * The cached value of the '{@link #getVerbatimBlock() <em>Verbatim Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVerbatimBlock()
   * @generated
   * @ordered
   */
  protected VerbatimBlock verbatimBlock;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ModelObjectBlockImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return MdlPackage.Literals.MODEL_OBJECT_BLOCK;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IndividualVariablesBlock getIndividualVariablesBlock()
  {
    return individualVariablesBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIndividualVariablesBlock(IndividualVariablesBlock newIndividualVariablesBlock, NotificationChain msgs)
  {
    IndividualVariablesBlock oldIndividualVariablesBlock = individualVariablesBlock;
    individualVariablesBlock = newIndividualVariablesBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__INDIVIDUAL_VARIABLES_BLOCK, oldIndividualVariablesBlock, newIndividualVariablesBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIndividualVariablesBlock(IndividualVariablesBlock newIndividualVariablesBlock)
  {
    if (newIndividualVariablesBlock != individualVariablesBlock)
    {
      NotificationChain msgs = null;
      if (individualVariablesBlock != null)
        msgs = ((InternalEObject)individualVariablesBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__INDIVIDUAL_VARIABLES_BLOCK, null, msgs);
      if (newIndividualVariablesBlock != null)
        msgs = ((InternalEObject)newIndividualVariablesBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__INDIVIDUAL_VARIABLES_BLOCK, null, msgs);
      msgs = basicSetIndividualVariablesBlock(newIndividualVariablesBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__INDIVIDUAL_VARIABLES_BLOCK, newIndividualVariablesBlock, newIndividualVariablesBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelPredictionBlock getModelPredictionBlock()
  {
    return modelPredictionBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetModelPredictionBlock(ModelPredictionBlock newModelPredictionBlock, NotificationChain msgs)
  {
    ModelPredictionBlock oldModelPredictionBlock = modelPredictionBlock;
    modelPredictionBlock = newModelPredictionBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__MODEL_PREDICTION_BLOCK, oldModelPredictionBlock, newModelPredictionBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModelPredictionBlock(ModelPredictionBlock newModelPredictionBlock)
  {
    if (newModelPredictionBlock != modelPredictionBlock)
    {
      NotificationChain msgs = null;
      if (modelPredictionBlock != null)
        msgs = ((InternalEObject)modelPredictionBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__MODEL_PREDICTION_BLOCK, null, msgs);
      if (newModelPredictionBlock != null)
        msgs = ((InternalEObject)newModelPredictionBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__MODEL_PREDICTION_BLOCK, null, msgs);
      msgs = basicSetModelPredictionBlock(newModelPredictionBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__MODEL_PREDICTION_BLOCK, newModelPredictionBlock, newModelPredictionBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RandomVariableDefinitionBlock getRandomVariableDefinitionBlock()
  {
    return randomVariableDefinitionBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRandomVariableDefinitionBlock(RandomVariableDefinitionBlock newRandomVariableDefinitionBlock, NotificationChain msgs)
  {
    RandomVariableDefinitionBlock oldRandomVariableDefinitionBlock = randomVariableDefinitionBlock;
    randomVariableDefinitionBlock = newRandomVariableDefinitionBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK, oldRandomVariableDefinitionBlock, newRandomVariableDefinitionBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRandomVariableDefinitionBlock(RandomVariableDefinitionBlock newRandomVariableDefinitionBlock)
  {
    if (newRandomVariableDefinitionBlock != randomVariableDefinitionBlock)
    {
      NotificationChain msgs = null;
      if (randomVariableDefinitionBlock != null)
        msgs = ((InternalEObject)randomVariableDefinitionBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK, null, msgs);
      if (newRandomVariableDefinitionBlock != null)
        msgs = ((InternalEObject)newRandomVariableDefinitionBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK, null, msgs);
      msgs = basicSetRandomVariableDefinitionBlock(newRandomVariableDefinitionBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK, newRandomVariableDefinitionBlock, newRandomVariableDefinitionBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public InputVariablesBlock getInputVariablesBlock()
  {
    return inputVariablesBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetInputVariablesBlock(InputVariablesBlock newInputVariablesBlock, NotificationChain msgs)
  {
    InputVariablesBlock oldInputVariablesBlock = inputVariablesBlock;
    inputVariablesBlock = newInputVariablesBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__INPUT_VARIABLES_BLOCK, oldInputVariablesBlock, newInputVariablesBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInputVariablesBlock(InputVariablesBlock newInputVariablesBlock)
  {
    if (newInputVariablesBlock != inputVariablesBlock)
    {
      NotificationChain msgs = null;
      if (inputVariablesBlock != null)
        msgs = ((InternalEObject)inputVariablesBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__INPUT_VARIABLES_BLOCK, null, msgs);
      if (newInputVariablesBlock != null)
        msgs = ((InternalEObject)newInputVariablesBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__INPUT_VARIABLES_BLOCK, null, msgs);
      msgs = basicSetInputVariablesBlock(newInputVariablesBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__INPUT_VARIABLES_BLOCK, newInputVariablesBlock, newInputVariablesBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StructuralParametersBlock getStructuralParametersBlock()
  {
    return structuralParametersBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStructuralParametersBlock(StructuralParametersBlock newStructuralParametersBlock, NotificationChain msgs)
  {
    StructuralParametersBlock oldStructuralParametersBlock = structuralParametersBlock;
    structuralParametersBlock = newStructuralParametersBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__STRUCTURAL_PARAMETERS_BLOCK, oldStructuralParametersBlock, newStructuralParametersBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStructuralParametersBlock(StructuralParametersBlock newStructuralParametersBlock)
  {
    if (newStructuralParametersBlock != structuralParametersBlock)
    {
      NotificationChain msgs = null;
      if (structuralParametersBlock != null)
        msgs = ((InternalEObject)structuralParametersBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__STRUCTURAL_PARAMETERS_BLOCK, null, msgs);
      if (newStructuralParametersBlock != null)
        msgs = ((InternalEObject)newStructuralParametersBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__STRUCTURAL_PARAMETERS_BLOCK, null, msgs);
      msgs = basicSetStructuralParametersBlock(newStructuralParametersBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__STRUCTURAL_PARAMETERS_BLOCK, newStructuralParametersBlock, newStructuralParametersBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VariabilityParametersBlock getVariabilityParametersBlock()
  {
    return variabilityParametersBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVariabilityParametersBlock(VariabilityParametersBlock newVariabilityParametersBlock, NotificationChain msgs)
  {
    VariabilityParametersBlock oldVariabilityParametersBlock = variabilityParametersBlock;
    variabilityParametersBlock = newVariabilityParametersBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__VARIABILITY_PARAMETERS_BLOCK, oldVariabilityParametersBlock, newVariabilityParametersBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVariabilityParametersBlock(VariabilityParametersBlock newVariabilityParametersBlock)
  {
    if (newVariabilityParametersBlock != variabilityParametersBlock)
    {
      NotificationChain msgs = null;
      if (variabilityParametersBlock != null)
        msgs = ((InternalEObject)variabilityParametersBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__VARIABILITY_PARAMETERS_BLOCK, null, msgs);
      if (newVariabilityParametersBlock != null)
        msgs = ((InternalEObject)newVariabilityParametersBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__VARIABILITY_PARAMETERS_BLOCK, null, msgs);
      msgs = basicSetVariabilityParametersBlock(newVariabilityParametersBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__VARIABILITY_PARAMETERS_BLOCK, newVariabilityParametersBlock, newVariabilityParametersBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OutputVariablesBlock getOutputVariablesBlock()
  {
    return outputVariablesBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOutputVariablesBlock(OutputVariablesBlock newOutputVariablesBlock, NotificationChain msgs)
  {
    OutputVariablesBlock oldOutputVariablesBlock = outputVariablesBlock;
    outputVariablesBlock = newOutputVariablesBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__OUTPUT_VARIABLES_BLOCK, oldOutputVariablesBlock, newOutputVariablesBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOutputVariablesBlock(OutputVariablesBlock newOutputVariablesBlock)
  {
    if (newOutputVariablesBlock != outputVariablesBlock)
    {
      NotificationChain msgs = null;
      if (outputVariablesBlock != null)
        msgs = ((InternalEObject)outputVariablesBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__OUTPUT_VARIABLES_BLOCK, null, msgs);
      if (newOutputVariablesBlock != null)
        msgs = ((InternalEObject)newOutputVariablesBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__OUTPUT_VARIABLES_BLOCK, null, msgs);
      msgs = basicSetOutputVariablesBlock(newOutputVariablesBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__OUTPUT_VARIABLES_BLOCK, newOutputVariablesBlock, newOutputVariablesBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupVariablesBlock getGroupVariablesBlock()
  {
    return groupVariablesBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetGroupVariablesBlock(GroupVariablesBlock newGroupVariablesBlock, NotificationChain msgs)
  {
    GroupVariablesBlock oldGroupVariablesBlock = groupVariablesBlock;
    groupVariablesBlock = newGroupVariablesBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__GROUP_VARIABLES_BLOCK, oldGroupVariablesBlock, newGroupVariablesBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGroupVariablesBlock(GroupVariablesBlock newGroupVariablesBlock)
  {
    if (newGroupVariablesBlock != groupVariablesBlock)
    {
      NotificationChain msgs = null;
      if (groupVariablesBlock != null)
        msgs = ((InternalEObject)groupVariablesBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__GROUP_VARIABLES_BLOCK, null, msgs);
      if (newGroupVariablesBlock != null)
        msgs = ((InternalEObject)newGroupVariablesBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__GROUP_VARIABLES_BLOCK, null, msgs);
      msgs = basicSetGroupVariablesBlock(newGroupVariablesBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__GROUP_VARIABLES_BLOCK, newGroupVariablesBlock, newGroupVariablesBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ObservationBlock getObservationBlock()
  {
    return observationBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetObservationBlock(ObservationBlock newObservationBlock, NotificationChain msgs)
  {
    ObservationBlock oldObservationBlock = observationBlock;
    observationBlock = newObservationBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__OBSERVATION_BLOCK, oldObservationBlock, newObservationBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setObservationBlock(ObservationBlock newObservationBlock)
  {
    if (newObservationBlock != observationBlock)
    {
      NotificationChain msgs = null;
      if (observationBlock != null)
        msgs = ((InternalEObject)observationBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__OBSERVATION_BLOCK, null, msgs);
      if (newObservationBlock != null)
        msgs = ((InternalEObject)newObservationBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__OBSERVATION_BLOCK, null, msgs);
      msgs = basicSetObservationBlock(newObservationBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__OBSERVATION_BLOCK, newObservationBlock, newObservationBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EstimationBlock getEstimationBlock()
  {
    return estimationBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEstimationBlock(EstimationBlock newEstimationBlock, NotificationChain msgs)
  {
    EstimationBlock oldEstimationBlock = estimationBlock;
    estimationBlock = newEstimationBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__ESTIMATION_BLOCK, oldEstimationBlock, newEstimationBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEstimationBlock(EstimationBlock newEstimationBlock)
  {
    if (newEstimationBlock != estimationBlock)
    {
      NotificationChain msgs = null;
      if (estimationBlock != null)
        msgs = ((InternalEObject)estimationBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__ESTIMATION_BLOCK, null, msgs);
      if (newEstimationBlock != null)
        msgs = ((InternalEObject)newEstimationBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__ESTIMATION_BLOCK, null, msgs);
      msgs = basicSetEstimationBlock(newEstimationBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__ESTIMATION_BLOCK, newEstimationBlock, newEstimationBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimulationBlock getSimulationBlock()
  {
    return simulationBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSimulationBlock(SimulationBlock newSimulationBlock, NotificationChain msgs)
  {
    SimulationBlock oldSimulationBlock = simulationBlock;
    simulationBlock = newSimulationBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__SIMULATION_BLOCK, oldSimulationBlock, newSimulationBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSimulationBlock(SimulationBlock newSimulationBlock)
  {
    if (newSimulationBlock != simulationBlock)
    {
      NotificationChain msgs = null;
      if (simulationBlock != null)
        msgs = ((InternalEObject)simulationBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__SIMULATION_BLOCK, null, msgs);
      if (newSimulationBlock != null)
        msgs = ((InternalEObject)newSimulationBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__SIMULATION_BLOCK, null, msgs);
      msgs = basicSetSimulationBlock(newSimulationBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__SIMULATION_BLOCK, newSimulationBlock, newSimulationBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VerbatimBlock getVerbatimBlock()
  {
    return verbatimBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVerbatimBlock(VerbatimBlock newVerbatimBlock, NotificationChain msgs)
  {
    VerbatimBlock oldVerbatimBlock = verbatimBlock;
    verbatimBlock = newVerbatimBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__VERBATIM_BLOCK, oldVerbatimBlock, newVerbatimBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVerbatimBlock(VerbatimBlock newVerbatimBlock)
  {
    if (newVerbatimBlock != verbatimBlock)
    {
      NotificationChain msgs = null;
      if (verbatimBlock != null)
        msgs = ((InternalEObject)verbatimBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__VERBATIM_BLOCK, null, msgs);
      if (newVerbatimBlock != null)
        msgs = ((InternalEObject)newVerbatimBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJECT_BLOCK__VERBATIM_BLOCK, null, msgs);
      msgs = basicSetVerbatimBlock(newVerbatimBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJECT_BLOCK__VERBATIM_BLOCK, newVerbatimBlock, newVerbatimBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case MdlPackage.MODEL_OBJECT_BLOCK__INDIVIDUAL_VARIABLES_BLOCK:
        return basicSetIndividualVariablesBlock(null, msgs);
      case MdlPackage.MODEL_OBJECT_BLOCK__MODEL_PREDICTION_BLOCK:
        return basicSetModelPredictionBlock(null, msgs);
      case MdlPackage.MODEL_OBJECT_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK:
        return basicSetRandomVariableDefinitionBlock(null, msgs);
      case MdlPackage.MODEL_OBJECT_BLOCK__INPUT_VARIABLES_BLOCK:
        return basicSetInputVariablesBlock(null, msgs);
      case MdlPackage.MODEL_OBJECT_BLOCK__STRUCTURAL_PARAMETERS_BLOCK:
        return basicSetStructuralParametersBlock(null, msgs);
      case MdlPackage.MODEL_OBJECT_BLOCK__VARIABILITY_PARAMETERS_BLOCK:
        return basicSetVariabilityParametersBlock(null, msgs);
      case MdlPackage.MODEL_OBJECT_BLOCK__OUTPUT_VARIABLES_BLOCK:
        return basicSetOutputVariablesBlock(null, msgs);
      case MdlPackage.MODEL_OBJECT_BLOCK__GROUP_VARIABLES_BLOCK:
        return basicSetGroupVariablesBlock(null, msgs);
      case MdlPackage.MODEL_OBJECT_BLOCK__OBSERVATION_BLOCK:
        return basicSetObservationBlock(null, msgs);
      case MdlPackage.MODEL_OBJECT_BLOCK__ESTIMATION_BLOCK:
        return basicSetEstimationBlock(null, msgs);
      case MdlPackage.MODEL_OBJECT_BLOCK__SIMULATION_BLOCK:
        return basicSetSimulationBlock(null, msgs);
      case MdlPackage.MODEL_OBJECT_BLOCK__VERBATIM_BLOCK:
        return basicSetVerbatimBlock(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case MdlPackage.MODEL_OBJECT_BLOCK__INDIVIDUAL_VARIABLES_BLOCK:
        return getIndividualVariablesBlock();
      case MdlPackage.MODEL_OBJECT_BLOCK__MODEL_PREDICTION_BLOCK:
        return getModelPredictionBlock();
      case MdlPackage.MODEL_OBJECT_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK:
        return getRandomVariableDefinitionBlock();
      case MdlPackage.MODEL_OBJECT_BLOCK__INPUT_VARIABLES_BLOCK:
        return getInputVariablesBlock();
      case MdlPackage.MODEL_OBJECT_BLOCK__STRUCTURAL_PARAMETERS_BLOCK:
        return getStructuralParametersBlock();
      case MdlPackage.MODEL_OBJECT_BLOCK__VARIABILITY_PARAMETERS_BLOCK:
        return getVariabilityParametersBlock();
      case MdlPackage.MODEL_OBJECT_BLOCK__OUTPUT_VARIABLES_BLOCK:
        return getOutputVariablesBlock();
      case MdlPackage.MODEL_OBJECT_BLOCK__GROUP_VARIABLES_BLOCK:
        return getGroupVariablesBlock();
      case MdlPackage.MODEL_OBJECT_BLOCK__OBSERVATION_BLOCK:
        return getObservationBlock();
      case MdlPackage.MODEL_OBJECT_BLOCK__ESTIMATION_BLOCK:
        return getEstimationBlock();
      case MdlPackage.MODEL_OBJECT_BLOCK__SIMULATION_BLOCK:
        return getSimulationBlock();
      case MdlPackage.MODEL_OBJECT_BLOCK__VERBATIM_BLOCK:
        return getVerbatimBlock();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case MdlPackage.MODEL_OBJECT_BLOCK__INDIVIDUAL_VARIABLES_BLOCK:
        setIndividualVariablesBlock((IndividualVariablesBlock)newValue);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__MODEL_PREDICTION_BLOCK:
        setModelPredictionBlock((ModelPredictionBlock)newValue);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK:
        setRandomVariableDefinitionBlock((RandomVariableDefinitionBlock)newValue);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__INPUT_VARIABLES_BLOCK:
        setInputVariablesBlock((InputVariablesBlock)newValue);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__STRUCTURAL_PARAMETERS_BLOCK:
        setStructuralParametersBlock((StructuralParametersBlock)newValue);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__VARIABILITY_PARAMETERS_BLOCK:
        setVariabilityParametersBlock((VariabilityParametersBlock)newValue);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__OUTPUT_VARIABLES_BLOCK:
        setOutputVariablesBlock((OutputVariablesBlock)newValue);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__GROUP_VARIABLES_BLOCK:
        setGroupVariablesBlock((GroupVariablesBlock)newValue);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__OBSERVATION_BLOCK:
        setObservationBlock((ObservationBlock)newValue);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__ESTIMATION_BLOCK:
        setEstimationBlock((EstimationBlock)newValue);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__SIMULATION_BLOCK:
        setSimulationBlock((SimulationBlock)newValue);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__VERBATIM_BLOCK:
        setVerbatimBlock((VerbatimBlock)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case MdlPackage.MODEL_OBJECT_BLOCK__INDIVIDUAL_VARIABLES_BLOCK:
        setIndividualVariablesBlock((IndividualVariablesBlock)null);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__MODEL_PREDICTION_BLOCK:
        setModelPredictionBlock((ModelPredictionBlock)null);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK:
        setRandomVariableDefinitionBlock((RandomVariableDefinitionBlock)null);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__INPUT_VARIABLES_BLOCK:
        setInputVariablesBlock((InputVariablesBlock)null);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__STRUCTURAL_PARAMETERS_BLOCK:
        setStructuralParametersBlock((StructuralParametersBlock)null);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__VARIABILITY_PARAMETERS_BLOCK:
        setVariabilityParametersBlock((VariabilityParametersBlock)null);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__OUTPUT_VARIABLES_BLOCK:
        setOutputVariablesBlock((OutputVariablesBlock)null);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__GROUP_VARIABLES_BLOCK:
        setGroupVariablesBlock((GroupVariablesBlock)null);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__OBSERVATION_BLOCK:
        setObservationBlock((ObservationBlock)null);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__ESTIMATION_BLOCK:
        setEstimationBlock((EstimationBlock)null);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__SIMULATION_BLOCK:
        setSimulationBlock((SimulationBlock)null);
        return;
      case MdlPackage.MODEL_OBJECT_BLOCK__VERBATIM_BLOCK:
        setVerbatimBlock((VerbatimBlock)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case MdlPackage.MODEL_OBJECT_BLOCK__INDIVIDUAL_VARIABLES_BLOCK:
        return individualVariablesBlock != null;
      case MdlPackage.MODEL_OBJECT_BLOCK__MODEL_PREDICTION_BLOCK:
        return modelPredictionBlock != null;
      case MdlPackage.MODEL_OBJECT_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK:
        return randomVariableDefinitionBlock != null;
      case MdlPackage.MODEL_OBJECT_BLOCK__INPUT_VARIABLES_BLOCK:
        return inputVariablesBlock != null;
      case MdlPackage.MODEL_OBJECT_BLOCK__STRUCTURAL_PARAMETERS_BLOCK:
        return structuralParametersBlock != null;
      case MdlPackage.MODEL_OBJECT_BLOCK__VARIABILITY_PARAMETERS_BLOCK:
        return variabilityParametersBlock != null;
      case MdlPackage.MODEL_OBJECT_BLOCK__OUTPUT_VARIABLES_BLOCK:
        return outputVariablesBlock != null;
      case MdlPackage.MODEL_OBJECT_BLOCK__GROUP_VARIABLES_BLOCK:
        return groupVariablesBlock != null;
      case MdlPackage.MODEL_OBJECT_BLOCK__OBSERVATION_BLOCK:
        return observationBlock != null;
      case MdlPackage.MODEL_OBJECT_BLOCK__ESTIMATION_BLOCK:
        return estimationBlock != null;
      case MdlPackage.MODEL_OBJECT_BLOCK__SIMULATION_BLOCK:
        return simulationBlock != null;
      case MdlPackage.MODEL_OBJECT_BLOCK__VERBATIM_BLOCK:
        return verbatimBlock != null;
    }
    return super.eIsSet(featureID);
  }

} //ModelObjectBlockImpl
