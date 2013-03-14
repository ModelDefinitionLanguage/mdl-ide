/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.data_block;
import org.ddmore.mdl.mdl.function_declaration;
import org.ddmore.mdl.mdl.parameters_block;
import org.ddmore.mdl.mdl.task_obj_block;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>task obj block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.task_obj_blockImpl#getFunction_declaration <em>Function declaration</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.task_obj_blockImpl#getParameters_block <em>Parameters block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.task_obj_blockImpl#getData_block <em>Data block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class task_obj_blockImpl extends MinimalEObjectImpl.Container implements task_obj_block
{
  /**
   * The cached value of the '{@link #getFunction_declaration() <em>Function declaration</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunction_declaration()
   * @generated
   * @ordered
   */
  protected function_declaration function_declaration;

  /**
   * The cached value of the '{@link #getParameters_block() <em>Parameters block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameters_block()
   * @generated
   * @ordered
   */
  protected parameters_block parameters_block;

  /**
   * The cached value of the '{@link #getData_block() <em>Data block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getData_block()
   * @generated
   * @ordered
   */
  protected data_block data_block;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected task_obj_blockImpl()
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
    return MdlPackage.Literals.TASK_OBJ_BLOCK;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public function_declaration getFunction_declaration()
  {
    return function_declaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFunction_declaration(function_declaration newFunction_declaration, NotificationChain msgs)
  {
    function_declaration oldFunction_declaration = function_declaration;
    function_declaration = newFunction_declaration;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJ_BLOCK__FUNCTION_DECLARATION, oldFunction_declaration, newFunction_declaration);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFunction_declaration(function_declaration newFunction_declaration)
  {
    if (newFunction_declaration != function_declaration)
    {
      NotificationChain msgs = null;
      if (function_declaration != null)
        msgs = ((InternalEObject)function_declaration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJ_BLOCK__FUNCTION_DECLARATION, null, msgs);
      if (newFunction_declaration != null)
        msgs = ((InternalEObject)newFunction_declaration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJ_BLOCK__FUNCTION_DECLARATION, null, msgs);
      msgs = basicSetFunction_declaration(newFunction_declaration, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJ_BLOCK__FUNCTION_DECLARATION, newFunction_declaration, newFunction_declaration));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public parameters_block getParameters_block()
  {
    return parameters_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParameters_block(parameters_block newParameters_block, NotificationChain msgs)
  {
    parameters_block oldParameters_block = parameters_block;
    parameters_block = newParameters_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJ_BLOCK__PARAMETERS_BLOCK, oldParameters_block, newParameters_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParameters_block(parameters_block newParameters_block)
  {
    if (newParameters_block != parameters_block)
    {
      NotificationChain msgs = null;
      if (parameters_block != null)
        msgs = ((InternalEObject)parameters_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJ_BLOCK__PARAMETERS_BLOCK, null, msgs);
      if (newParameters_block != null)
        msgs = ((InternalEObject)newParameters_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJ_BLOCK__PARAMETERS_BLOCK, null, msgs);
      msgs = basicSetParameters_block(newParameters_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJ_BLOCK__PARAMETERS_BLOCK, newParameters_block, newParameters_block));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public data_block getData_block()
  {
    return data_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetData_block(data_block newData_block, NotificationChain msgs)
  {
    data_block oldData_block = data_block;
    data_block = newData_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJ_BLOCK__DATA_BLOCK, oldData_block, newData_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setData_block(data_block newData_block)
  {
    if (newData_block != data_block)
    {
      NotificationChain msgs = null;
      if (data_block != null)
        msgs = ((InternalEObject)data_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJ_BLOCK__DATA_BLOCK, null, msgs);
      if (newData_block != null)
        msgs = ((InternalEObject)newData_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJ_BLOCK__DATA_BLOCK, null, msgs);
      msgs = basicSetData_block(newData_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJ_BLOCK__DATA_BLOCK, newData_block, newData_block));
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
      case MdlPackage.TASK_OBJ_BLOCK__FUNCTION_DECLARATION:
        return basicSetFunction_declaration(null, msgs);
      case MdlPackage.TASK_OBJ_BLOCK__PARAMETERS_BLOCK:
        return basicSetParameters_block(null, msgs);
      case MdlPackage.TASK_OBJ_BLOCK__DATA_BLOCK:
        return basicSetData_block(null, msgs);
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
      case MdlPackage.TASK_OBJ_BLOCK__FUNCTION_DECLARATION:
        return getFunction_declaration();
      case MdlPackage.TASK_OBJ_BLOCK__PARAMETERS_BLOCK:
        return getParameters_block();
      case MdlPackage.TASK_OBJ_BLOCK__DATA_BLOCK:
        return getData_block();
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
      case MdlPackage.TASK_OBJ_BLOCK__FUNCTION_DECLARATION:
        setFunction_declaration((function_declaration)newValue);
        return;
      case MdlPackage.TASK_OBJ_BLOCK__PARAMETERS_BLOCK:
        setParameters_block((parameters_block)newValue);
        return;
      case MdlPackage.TASK_OBJ_BLOCK__DATA_BLOCK:
        setData_block((data_block)newValue);
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
      case MdlPackage.TASK_OBJ_BLOCK__FUNCTION_DECLARATION:
        setFunction_declaration((function_declaration)null);
        return;
      case MdlPackage.TASK_OBJ_BLOCK__PARAMETERS_BLOCK:
        setParameters_block((parameters_block)null);
        return;
      case MdlPackage.TASK_OBJ_BLOCK__DATA_BLOCK:
        setData_block((data_block)null);
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
      case MdlPackage.TASK_OBJ_BLOCK__FUNCTION_DECLARATION:
        return function_declaration != null;
      case MdlPackage.TASK_OBJ_BLOCK__PARAMETERS_BLOCK:
        return parameters_block != null;
      case MdlPackage.TASK_OBJ_BLOCK__DATA_BLOCK:
        return data_block != null;
    }
    return super.eIsSet(featureID);
  }

} //task_obj_blockImpl
