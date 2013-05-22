/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.EstimateTask;
import org.ddmore.mdl.mdl.ExecuteTask;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.SimulateTask;
import org.ddmore.mdl.mdl.TaskFunctionBlock;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task Function Block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.TaskFunctionBlockImpl#getEstimateBlock <em>Estimate Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.TaskFunctionBlockImpl#getSimulateBlock <em>Simulate Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.TaskFunctionBlockImpl#getExecuteBlock <em>Execute Block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaskFunctionBlockImpl extends MinimalEObjectImpl.Container implements TaskFunctionBlock
{
  /**
   * The cached value of the '{@link #getEstimateBlock() <em>Estimate Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEstimateBlock()
   * @generated
   * @ordered
   */
  protected EstimateTask estimateBlock;

  /**
   * The cached value of the '{@link #getSimulateBlock() <em>Simulate Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSimulateBlock()
   * @generated
   * @ordered
   */
  protected SimulateTask simulateBlock;

  /**
   * The cached value of the '{@link #getExecuteBlock() <em>Execute Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExecuteBlock()
   * @generated
   * @ordered
   */
  protected ExecuteTask executeBlock;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TaskFunctionBlockImpl()
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
    return MdlPackage.Literals.TASK_FUNCTION_BLOCK;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EstimateTask getEstimateBlock()
  {
    return estimateBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEstimateBlock(EstimateTask newEstimateBlock, NotificationChain msgs)
  {
    EstimateTask oldEstimateBlock = estimateBlock;
    estimateBlock = newEstimateBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_FUNCTION_BLOCK__ESTIMATE_BLOCK, oldEstimateBlock, newEstimateBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEstimateBlock(EstimateTask newEstimateBlock)
  {
    if (newEstimateBlock != estimateBlock)
    {
      NotificationChain msgs = null;
      if (estimateBlock != null)
        msgs = ((InternalEObject)estimateBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_FUNCTION_BLOCK__ESTIMATE_BLOCK, null, msgs);
      if (newEstimateBlock != null)
        msgs = ((InternalEObject)newEstimateBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_FUNCTION_BLOCK__ESTIMATE_BLOCK, null, msgs);
      msgs = basicSetEstimateBlock(newEstimateBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_FUNCTION_BLOCK__ESTIMATE_BLOCK, newEstimateBlock, newEstimateBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimulateTask getSimulateBlock()
  {
    return simulateBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSimulateBlock(SimulateTask newSimulateBlock, NotificationChain msgs)
  {
    SimulateTask oldSimulateBlock = simulateBlock;
    simulateBlock = newSimulateBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_FUNCTION_BLOCK__SIMULATE_BLOCK, oldSimulateBlock, newSimulateBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSimulateBlock(SimulateTask newSimulateBlock)
  {
    if (newSimulateBlock != simulateBlock)
    {
      NotificationChain msgs = null;
      if (simulateBlock != null)
        msgs = ((InternalEObject)simulateBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_FUNCTION_BLOCK__SIMULATE_BLOCK, null, msgs);
      if (newSimulateBlock != null)
        msgs = ((InternalEObject)newSimulateBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_FUNCTION_BLOCK__SIMULATE_BLOCK, null, msgs);
      msgs = basicSetSimulateBlock(newSimulateBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_FUNCTION_BLOCK__SIMULATE_BLOCK, newSimulateBlock, newSimulateBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExecuteTask getExecuteBlock()
  {
    return executeBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExecuteBlock(ExecuteTask newExecuteBlock, NotificationChain msgs)
  {
    ExecuteTask oldExecuteBlock = executeBlock;
    executeBlock = newExecuteBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_FUNCTION_BLOCK__EXECUTE_BLOCK, oldExecuteBlock, newExecuteBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExecuteBlock(ExecuteTask newExecuteBlock)
  {
    if (newExecuteBlock != executeBlock)
    {
      NotificationChain msgs = null;
      if (executeBlock != null)
        msgs = ((InternalEObject)executeBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_FUNCTION_BLOCK__EXECUTE_BLOCK, null, msgs);
      if (newExecuteBlock != null)
        msgs = ((InternalEObject)newExecuteBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_FUNCTION_BLOCK__EXECUTE_BLOCK, null, msgs);
      msgs = basicSetExecuteBlock(newExecuteBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_FUNCTION_BLOCK__EXECUTE_BLOCK, newExecuteBlock, newExecuteBlock));
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
      case MdlPackage.TASK_FUNCTION_BLOCK__ESTIMATE_BLOCK:
        return basicSetEstimateBlock(null, msgs);
      case MdlPackage.TASK_FUNCTION_BLOCK__SIMULATE_BLOCK:
        return basicSetSimulateBlock(null, msgs);
      case MdlPackage.TASK_FUNCTION_BLOCK__EXECUTE_BLOCK:
        return basicSetExecuteBlock(null, msgs);
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
      case MdlPackage.TASK_FUNCTION_BLOCK__ESTIMATE_BLOCK:
        return getEstimateBlock();
      case MdlPackage.TASK_FUNCTION_BLOCK__SIMULATE_BLOCK:
        return getSimulateBlock();
      case MdlPackage.TASK_FUNCTION_BLOCK__EXECUTE_BLOCK:
        return getExecuteBlock();
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
      case MdlPackage.TASK_FUNCTION_BLOCK__ESTIMATE_BLOCK:
        setEstimateBlock((EstimateTask)newValue);
        return;
      case MdlPackage.TASK_FUNCTION_BLOCK__SIMULATE_BLOCK:
        setSimulateBlock((SimulateTask)newValue);
        return;
      case MdlPackage.TASK_FUNCTION_BLOCK__EXECUTE_BLOCK:
        setExecuteBlock((ExecuteTask)newValue);
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
      case MdlPackage.TASK_FUNCTION_BLOCK__ESTIMATE_BLOCK:
        setEstimateBlock((EstimateTask)null);
        return;
      case MdlPackage.TASK_FUNCTION_BLOCK__SIMULATE_BLOCK:
        setSimulateBlock((SimulateTask)null);
        return;
      case MdlPackage.TASK_FUNCTION_BLOCK__EXECUTE_BLOCK:
        setExecuteBlock((ExecuteTask)null);
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
      case MdlPackage.TASK_FUNCTION_BLOCK__ESTIMATE_BLOCK:
        return estimateBlock != null;
      case MdlPackage.TASK_FUNCTION_BLOCK__SIMULATE_BLOCK:
        return simulateBlock != null;
      case MdlPackage.TASK_FUNCTION_BLOCK__EXECUTE_BLOCK:
        return executeBlock != null;
    }
    return super.eIsSet(featureID);
  }

} //TaskFunctionBlockImpl
