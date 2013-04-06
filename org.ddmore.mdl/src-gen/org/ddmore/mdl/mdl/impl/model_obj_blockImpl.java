/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.estimation_block;
import org.ddmore.mdl.mdl.group_variables;
import org.ddmore.mdl.mdl.individual_model_obj_block;
import org.ddmore.mdl.mdl.input_variables_block;
import org.ddmore.mdl.mdl.model_obj_block;
import org.ddmore.mdl.mdl.model_prediction_obj_block;
import org.ddmore.mdl.mdl.observation_block;
import org.ddmore.mdl.mdl.output_variables_block;
import org.ddmore.mdl.mdl.random_variable_definition_block;
import org.ddmore.mdl.mdl.simulation_block;
import org.ddmore.mdl.mdl.structural_parameters_block;
import org.ddmore.mdl.mdl.variability_parameters_block;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>model obj block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.model_obj_blockImpl#getIndividual_model_obj_block <em>Individual model obj block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.model_obj_blockImpl#getModel_prediction_obj_block <em>Model prediction obj block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.model_obj_blockImpl#getRandom_variable_definition_block <em>Random variable definition block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.model_obj_blockImpl#getInput_variables_block <em>Input variables block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.model_obj_blockImpl#getStructural_parameters_block <em>Structural parameters block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.model_obj_blockImpl#getVariability_parameters_block <em>Variability parameters block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.model_obj_blockImpl#getOutput_variables_block <em>Output variables block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.model_obj_blockImpl#getGroup_variables <em>Group variables</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.model_obj_blockImpl#getObservation_block <em>Observation block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.model_obj_blockImpl#getEstimation_block <em>Estimation block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.model_obj_blockImpl#getSimulation_block <em>Simulation block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class model_obj_blockImpl extends MinimalEObjectImpl.Container implements model_obj_block
{
  /**
   * The cached value of the '{@link #getIndividual_model_obj_block() <em>Individual model obj block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIndividual_model_obj_block()
   * @generated
   * @ordered
   */
  protected individual_model_obj_block individual_model_obj_block;

  /**
   * The cached value of the '{@link #getModel_prediction_obj_block() <em>Model prediction obj block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModel_prediction_obj_block()
   * @generated
   * @ordered
   */
  protected model_prediction_obj_block model_prediction_obj_block;

  /**
   * The cached value of the '{@link #getRandom_variable_definition_block() <em>Random variable definition block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRandom_variable_definition_block()
   * @generated
   * @ordered
   */
  protected random_variable_definition_block random_variable_definition_block;

  /**
   * The cached value of the '{@link #getInput_variables_block() <em>Input variables block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInput_variables_block()
   * @generated
   * @ordered
   */
  protected input_variables_block input_variables_block;

  /**
   * The cached value of the '{@link #getStructural_parameters_block() <em>Structural parameters block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStructural_parameters_block()
   * @generated
   * @ordered
   */
  protected structural_parameters_block structural_parameters_block;

  /**
   * The cached value of the '{@link #getVariability_parameters_block() <em>Variability parameters block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariability_parameters_block()
   * @generated
   * @ordered
   */
  protected variability_parameters_block variability_parameters_block;

  /**
   * The cached value of the '{@link #getOutput_variables_block() <em>Output variables block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOutput_variables_block()
   * @generated
   * @ordered
   */
  protected output_variables_block output_variables_block;

  /**
   * The cached value of the '{@link #getGroup_variables() <em>Group variables</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGroup_variables()
   * @generated
   * @ordered
   */
  protected group_variables group_variables;

  /**
   * The cached value of the '{@link #getObservation_block() <em>Observation block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getObservation_block()
   * @generated
   * @ordered
   */
  protected observation_block observation_block;

  /**
   * The cached value of the '{@link #getEstimation_block() <em>Estimation block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEstimation_block()
   * @generated
   * @ordered
   */
  protected estimation_block estimation_block;

  /**
   * The cached value of the '{@link #getSimulation_block() <em>Simulation block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSimulation_block()
   * @generated
   * @ordered
   */
  protected simulation_block simulation_block;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected model_obj_blockImpl()
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
    return MdlPackage.Literals.MODEL_OBJ_BLOCK;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public individual_model_obj_block getIndividual_model_obj_block()
  {
    return individual_model_obj_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIndividual_model_obj_block(individual_model_obj_block newIndividual_model_obj_block, NotificationChain msgs)
  {
    individual_model_obj_block oldIndividual_model_obj_block = individual_model_obj_block;
    individual_model_obj_block = newIndividual_model_obj_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__INDIVIDUAL_MODEL_OBJ_BLOCK, oldIndividual_model_obj_block, newIndividual_model_obj_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIndividual_model_obj_block(individual_model_obj_block newIndividual_model_obj_block)
  {
    if (newIndividual_model_obj_block != individual_model_obj_block)
    {
      NotificationChain msgs = null;
      if (individual_model_obj_block != null)
        msgs = ((InternalEObject)individual_model_obj_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__INDIVIDUAL_MODEL_OBJ_BLOCK, null, msgs);
      if (newIndividual_model_obj_block != null)
        msgs = ((InternalEObject)newIndividual_model_obj_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__INDIVIDUAL_MODEL_OBJ_BLOCK, null, msgs);
      msgs = basicSetIndividual_model_obj_block(newIndividual_model_obj_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__INDIVIDUAL_MODEL_OBJ_BLOCK, newIndividual_model_obj_block, newIndividual_model_obj_block));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public model_prediction_obj_block getModel_prediction_obj_block()
  {
    return model_prediction_obj_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetModel_prediction_obj_block(model_prediction_obj_block newModel_prediction_obj_block, NotificationChain msgs)
  {
    model_prediction_obj_block oldModel_prediction_obj_block = model_prediction_obj_block;
    model_prediction_obj_block = newModel_prediction_obj_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__MODEL_PREDICTION_OBJ_BLOCK, oldModel_prediction_obj_block, newModel_prediction_obj_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModel_prediction_obj_block(model_prediction_obj_block newModel_prediction_obj_block)
  {
    if (newModel_prediction_obj_block != model_prediction_obj_block)
    {
      NotificationChain msgs = null;
      if (model_prediction_obj_block != null)
        msgs = ((InternalEObject)model_prediction_obj_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__MODEL_PREDICTION_OBJ_BLOCK, null, msgs);
      if (newModel_prediction_obj_block != null)
        msgs = ((InternalEObject)newModel_prediction_obj_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__MODEL_PREDICTION_OBJ_BLOCK, null, msgs);
      msgs = basicSetModel_prediction_obj_block(newModel_prediction_obj_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__MODEL_PREDICTION_OBJ_BLOCK, newModel_prediction_obj_block, newModel_prediction_obj_block));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public random_variable_definition_block getRandom_variable_definition_block()
  {
    return random_variable_definition_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRandom_variable_definition_block(random_variable_definition_block newRandom_variable_definition_block, NotificationChain msgs)
  {
    random_variable_definition_block oldRandom_variable_definition_block = random_variable_definition_block;
    random_variable_definition_block = newRandom_variable_definition_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK, oldRandom_variable_definition_block, newRandom_variable_definition_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRandom_variable_definition_block(random_variable_definition_block newRandom_variable_definition_block)
  {
    if (newRandom_variable_definition_block != random_variable_definition_block)
    {
      NotificationChain msgs = null;
      if (random_variable_definition_block != null)
        msgs = ((InternalEObject)random_variable_definition_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK, null, msgs);
      if (newRandom_variable_definition_block != null)
        msgs = ((InternalEObject)newRandom_variable_definition_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK, null, msgs);
      msgs = basicSetRandom_variable_definition_block(newRandom_variable_definition_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK, newRandom_variable_definition_block, newRandom_variable_definition_block));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public input_variables_block getInput_variables_block()
  {
    return input_variables_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetInput_variables_block(input_variables_block newInput_variables_block, NotificationChain msgs)
  {
    input_variables_block oldInput_variables_block = input_variables_block;
    input_variables_block = newInput_variables_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__INPUT_VARIABLES_BLOCK, oldInput_variables_block, newInput_variables_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInput_variables_block(input_variables_block newInput_variables_block)
  {
    if (newInput_variables_block != input_variables_block)
    {
      NotificationChain msgs = null;
      if (input_variables_block != null)
        msgs = ((InternalEObject)input_variables_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__INPUT_VARIABLES_BLOCK, null, msgs);
      if (newInput_variables_block != null)
        msgs = ((InternalEObject)newInput_variables_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__INPUT_VARIABLES_BLOCK, null, msgs);
      msgs = basicSetInput_variables_block(newInput_variables_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__INPUT_VARIABLES_BLOCK, newInput_variables_block, newInput_variables_block));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public structural_parameters_block getStructural_parameters_block()
  {
    return structural_parameters_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStructural_parameters_block(structural_parameters_block newStructural_parameters_block, NotificationChain msgs)
  {
    structural_parameters_block oldStructural_parameters_block = structural_parameters_block;
    structural_parameters_block = newStructural_parameters_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__STRUCTURAL_PARAMETERS_BLOCK, oldStructural_parameters_block, newStructural_parameters_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStructural_parameters_block(structural_parameters_block newStructural_parameters_block)
  {
    if (newStructural_parameters_block != structural_parameters_block)
    {
      NotificationChain msgs = null;
      if (structural_parameters_block != null)
        msgs = ((InternalEObject)structural_parameters_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__STRUCTURAL_PARAMETERS_BLOCK, null, msgs);
      if (newStructural_parameters_block != null)
        msgs = ((InternalEObject)newStructural_parameters_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__STRUCTURAL_PARAMETERS_BLOCK, null, msgs);
      msgs = basicSetStructural_parameters_block(newStructural_parameters_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__STRUCTURAL_PARAMETERS_BLOCK, newStructural_parameters_block, newStructural_parameters_block));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public variability_parameters_block getVariability_parameters_block()
  {
    return variability_parameters_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVariability_parameters_block(variability_parameters_block newVariability_parameters_block, NotificationChain msgs)
  {
    variability_parameters_block oldVariability_parameters_block = variability_parameters_block;
    variability_parameters_block = newVariability_parameters_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__VARIABILITY_PARAMETERS_BLOCK, oldVariability_parameters_block, newVariability_parameters_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVariability_parameters_block(variability_parameters_block newVariability_parameters_block)
  {
    if (newVariability_parameters_block != variability_parameters_block)
    {
      NotificationChain msgs = null;
      if (variability_parameters_block != null)
        msgs = ((InternalEObject)variability_parameters_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__VARIABILITY_PARAMETERS_BLOCK, null, msgs);
      if (newVariability_parameters_block != null)
        msgs = ((InternalEObject)newVariability_parameters_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__VARIABILITY_PARAMETERS_BLOCK, null, msgs);
      msgs = basicSetVariability_parameters_block(newVariability_parameters_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__VARIABILITY_PARAMETERS_BLOCK, newVariability_parameters_block, newVariability_parameters_block));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public output_variables_block getOutput_variables_block()
  {
    return output_variables_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOutput_variables_block(output_variables_block newOutput_variables_block, NotificationChain msgs)
  {
    output_variables_block oldOutput_variables_block = output_variables_block;
    output_variables_block = newOutput_variables_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__OUTPUT_VARIABLES_BLOCK, oldOutput_variables_block, newOutput_variables_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOutput_variables_block(output_variables_block newOutput_variables_block)
  {
    if (newOutput_variables_block != output_variables_block)
    {
      NotificationChain msgs = null;
      if (output_variables_block != null)
        msgs = ((InternalEObject)output_variables_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__OUTPUT_VARIABLES_BLOCK, null, msgs);
      if (newOutput_variables_block != null)
        msgs = ((InternalEObject)newOutput_variables_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__OUTPUT_VARIABLES_BLOCK, null, msgs);
      msgs = basicSetOutput_variables_block(newOutput_variables_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__OUTPUT_VARIABLES_BLOCK, newOutput_variables_block, newOutput_variables_block));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public group_variables getGroup_variables()
  {
    return group_variables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetGroup_variables(group_variables newGroup_variables, NotificationChain msgs)
  {
    group_variables oldGroup_variables = group_variables;
    group_variables = newGroup_variables;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__GROUP_VARIABLES, oldGroup_variables, newGroup_variables);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGroup_variables(group_variables newGroup_variables)
  {
    if (newGroup_variables != group_variables)
    {
      NotificationChain msgs = null;
      if (group_variables != null)
        msgs = ((InternalEObject)group_variables).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__GROUP_VARIABLES, null, msgs);
      if (newGroup_variables != null)
        msgs = ((InternalEObject)newGroup_variables).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__GROUP_VARIABLES, null, msgs);
      msgs = basicSetGroup_variables(newGroup_variables, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__GROUP_VARIABLES, newGroup_variables, newGroup_variables));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public observation_block getObservation_block()
  {
    return observation_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetObservation_block(observation_block newObservation_block, NotificationChain msgs)
  {
    observation_block oldObservation_block = observation_block;
    observation_block = newObservation_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__OBSERVATION_BLOCK, oldObservation_block, newObservation_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setObservation_block(observation_block newObservation_block)
  {
    if (newObservation_block != observation_block)
    {
      NotificationChain msgs = null;
      if (observation_block != null)
        msgs = ((InternalEObject)observation_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__OBSERVATION_BLOCK, null, msgs);
      if (newObservation_block != null)
        msgs = ((InternalEObject)newObservation_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__OBSERVATION_BLOCK, null, msgs);
      msgs = basicSetObservation_block(newObservation_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__OBSERVATION_BLOCK, newObservation_block, newObservation_block));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public estimation_block getEstimation_block()
  {
    return estimation_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEstimation_block(estimation_block newEstimation_block, NotificationChain msgs)
  {
    estimation_block oldEstimation_block = estimation_block;
    estimation_block = newEstimation_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__ESTIMATION_BLOCK, oldEstimation_block, newEstimation_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEstimation_block(estimation_block newEstimation_block)
  {
    if (newEstimation_block != estimation_block)
    {
      NotificationChain msgs = null;
      if (estimation_block != null)
        msgs = ((InternalEObject)estimation_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__ESTIMATION_BLOCK, null, msgs);
      if (newEstimation_block != null)
        msgs = ((InternalEObject)newEstimation_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__ESTIMATION_BLOCK, null, msgs);
      msgs = basicSetEstimation_block(newEstimation_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__ESTIMATION_BLOCK, newEstimation_block, newEstimation_block));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public simulation_block getSimulation_block()
  {
    return simulation_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSimulation_block(simulation_block newSimulation_block, NotificationChain msgs)
  {
    simulation_block oldSimulation_block = simulation_block;
    simulation_block = newSimulation_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__SIMULATION_BLOCK, oldSimulation_block, newSimulation_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSimulation_block(simulation_block newSimulation_block)
  {
    if (newSimulation_block != simulation_block)
    {
      NotificationChain msgs = null;
      if (simulation_block != null)
        msgs = ((InternalEObject)simulation_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__SIMULATION_BLOCK, null, msgs);
      if (newSimulation_block != null)
        msgs = ((InternalEObject)newSimulation_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_OBJ_BLOCK__SIMULATION_BLOCK, null, msgs);
      msgs = basicSetSimulation_block(newSimulation_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_OBJ_BLOCK__SIMULATION_BLOCK, newSimulation_block, newSimulation_block));
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
      case MdlPackage.MODEL_OBJ_BLOCK__INDIVIDUAL_MODEL_OBJ_BLOCK:
        return basicSetIndividual_model_obj_block(null, msgs);
      case MdlPackage.MODEL_OBJ_BLOCK__MODEL_PREDICTION_OBJ_BLOCK:
        return basicSetModel_prediction_obj_block(null, msgs);
      case MdlPackage.MODEL_OBJ_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK:
        return basicSetRandom_variable_definition_block(null, msgs);
      case MdlPackage.MODEL_OBJ_BLOCK__INPUT_VARIABLES_BLOCK:
        return basicSetInput_variables_block(null, msgs);
      case MdlPackage.MODEL_OBJ_BLOCK__STRUCTURAL_PARAMETERS_BLOCK:
        return basicSetStructural_parameters_block(null, msgs);
      case MdlPackage.MODEL_OBJ_BLOCK__VARIABILITY_PARAMETERS_BLOCK:
        return basicSetVariability_parameters_block(null, msgs);
      case MdlPackage.MODEL_OBJ_BLOCK__OUTPUT_VARIABLES_BLOCK:
        return basicSetOutput_variables_block(null, msgs);
      case MdlPackage.MODEL_OBJ_BLOCK__GROUP_VARIABLES:
        return basicSetGroup_variables(null, msgs);
      case MdlPackage.MODEL_OBJ_BLOCK__OBSERVATION_BLOCK:
        return basicSetObservation_block(null, msgs);
      case MdlPackage.MODEL_OBJ_BLOCK__ESTIMATION_BLOCK:
        return basicSetEstimation_block(null, msgs);
      case MdlPackage.MODEL_OBJ_BLOCK__SIMULATION_BLOCK:
        return basicSetSimulation_block(null, msgs);
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
      case MdlPackage.MODEL_OBJ_BLOCK__INDIVIDUAL_MODEL_OBJ_BLOCK:
        return getIndividual_model_obj_block();
      case MdlPackage.MODEL_OBJ_BLOCK__MODEL_PREDICTION_OBJ_BLOCK:
        return getModel_prediction_obj_block();
      case MdlPackage.MODEL_OBJ_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK:
        return getRandom_variable_definition_block();
      case MdlPackage.MODEL_OBJ_BLOCK__INPUT_VARIABLES_BLOCK:
        return getInput_variables_block();
      case MdlPackage.MODEL_OBJ_BLOCK__STRUCTURAL_PARAMETERS_BLOCK:
        return getStructural_parameters_block();
      case MdlPackage.MODEL_OBJ_BLOCK__VARIABILITY_PARAMETERS_BLOCK:
        return getVariability_parameters_block();
      case MdlPackage.MODEL_OBJ_BLOCK__OUTPUT_VARIABLES_BLOCK:
        return getOutput_variables_block();
      case MdlPackage.MODEL_OBJ_BLOCK__GROUP_VARIABLES:
        return getGroup_variables();
      case MdlPackage.MODEL_OBJ_BLOCK__OBSERVATION_BLOCK:
        return getObservation_block();
      case MdlPackage.MODEL_OBJ_BLOCK__ESTIMATION_BLOCK:
        return getEstimation_block();
      case MdlPackage.MODEL_OBJ_BLOCK__SIMULATION_BLOCK:
        return getSimulation_block();
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
      case MdlPackage.MODEL_OBJ_BLOCK__INDIVIDUAL_MODEL_OBJ_BLOCK:
        setIndividual_model_obj_block((individual_model_obj_block)newValue);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__MODEL_PREDICTION_OBJ_BLOCK:
        setModel_prediction_obj_block((model_prediction_obj_block)newValue);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK:
        setRandom_variable_definition_block((random_variable_definition_block)newValue);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__INPUT_VARIABLES_BLOCK:
        setInput_variables_block((input_variables_block)newValue);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__STRUCTURAL_PARAMETERS_BLOCK:
        setStructural_parameters_block((structural_parameters_block)newValue);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__VARIABILITY_PARAMETERS_BLOCK:
        setVariability_parameters_block((variability_parameters_block)newValue);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__OUTPUT_VARIABLES_BLOCK:
        setOutput_variables_block((output_variables_block)newValue);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__GROUP_VARIABLES:
        setGroup_variables((group_variables)newValue);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__OBSERVATION_BLOCK:
        setObservation_block((observation_block)newValue);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__ESTIMATION_BLOCK:
        setEstimation_block((estimation_block)newValue);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__SIMULATION_BLOCK:
        setSimulation_block((simulation_block)newValue);
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
      case MdlPackage.MODEL_OBJ_BLOCK__INDIVIDUAL_MODEL_OBJ_BLOCK:
        setIndividual_model_obj_block((individual_model_obj_block)null);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__MODEL_PREDICTION_OBJ_BLOCK:
        setModel_prediction_obj_block((model_prediction_obj_block)null);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK:
        setRandom_variable_definition_block((random_variable_definition_block)null);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__INPUT_VARIABLES_BLOCK:
        setInput_variables_block((input_variables_block)null);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__STRUCTURAL_PARAMETERS_BLOCK:
        setStructural_parameters_block((structural_parameters_block)null);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__VARIABILITY_PARAMETERS_BLOCK:
        setVariability_parameters_block((variability_parameters_block)null);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__OUTPUT_VARIABLES_BLOCK:
        setOutput_variables_block((output_variables_block)null);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__GROUP_VARIABLES:
        setGroup_variables((group_variables)null);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__OBSERVATION_BLOCK:
        setObservation_block((observation_block)null);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__ESTIMATION_BLOCK:
        setEstimation_block((estimation_block)null);
        return;
      case MdlPackage.MODEL_OBJ_BLOCK__SIMULATION_BLOCK:
        setSimulation_block((simulation_block)null);
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
      case MdlPackage.MODEL_OBJ_BLOCK__INDIVIDUAL_MODEL_OBJ_BLOCK:
        return individual_model_obj_block != null;
      case MdlPackage.MODEL_OBJ_BLOCK__MODEL_PREDICTION_OBJ_BLOCK:
        return model_prediction_obj_block != null;
      case MdlPackage.MODEL_OBJ_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK:
        return random_variable_definition_block != null;
      case MdlPackage.MODEL_OBJ_BLOCK__INPUT_VARIABLES_BLOCK:
        return input_variables_block != null;
      case MdlPackage.MODEL_OBJ_BLOCK__STRUCTURAL_PARAMETERS_BLOCK:
        return structural_parameters_block != null;
      case MdlPackage.MODEL_OBJ_BLOCK__VARIABILITY_PARAMETERS_BLOCK:
        return variability_parameters_block != null;
      case MdlPackage.MODEL_OBJ_BLOCK__OUTPUT_VARIABLES_BLOCK:
        return output_variables_block != null;
      case MdlPackage.MODEL_OBJ_BLOCK__GROUP_VARIABLES:
        return group_variables != null;
      case MdlPackage.MODEL_OBJ_BLOCK__OBSERVATION_BLOCK:
        return observation_block != null;
      case MdlPackage.MODEL_OBJ_BLOCK__ESTIMATION_BLOCK:
        return estimation_block != null;
      case MdlPackage.MODEL_OBJ_BLOCK__SIMULATION_BLOCK:
        return simulation_block != null;
    }
    return super.eIsSet(featureID);
  }

} //model_obj_blockImpl
