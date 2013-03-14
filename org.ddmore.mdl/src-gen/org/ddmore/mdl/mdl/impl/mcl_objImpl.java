/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.data_obj;
import org.ddmore.mdl.mdl.mcl_obj;
import org.ddmore.mdl.mdl.model_obj;
import org.ddmore.mdl.mdl.param_obj;
import org.ddmore.mdl.mdl.task_obj;
import org.ddmore.mdl.mdl.tel_obj;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>mcl obj</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.mcl_objImpl#getModel_obj <em>Model obj</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.mcl_objImpl#getParam_obj <em>Param obj</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.mcl_objImpl#getData_obj <em>Data obj</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.mcl_objImpl#getTask_obj <em>Task obj</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.mcl_objImpl#getTel_obj <em>Tel obj</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class mcl_objImpl extends MinimalEObjectImpl.Container implements mcl_obj
{
  /**
   * The cached value of the '{@link #getModel_obj() <em>Model obj</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModel_obj()
   * @generated
   * @ordered
   */
  protected model_obj model_obj;

  /**
   * The cached value of the '{@link #getParam_obj() <em>Param obj</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParam_obj()
   * @generated
   * @ordered
   */
  protected param_obj param_obj;

  /**
   * The cached value of the '{@link #getData_obj() <em>Data obj</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getData_obj()
   * @generated
   * @ordered
   */
  protected data_obj data_obj;

  /**
   * The cached value of the '{@link #getTask_obj() <em>Task obj</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTask_obj()
   * @generated
   * @ordered
   */
  protected task_obj task_obj;

  /**
   * The cached value of the '{@link #getTel_obj() <em>Tel obj</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTel_obj()
   * @generated
   * @ordered
   */
  protected tel_obj tel_obj;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected mcl_objImpl()
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
    return MdlPackage.Literals.MCL_OBJ;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public model_obj getModel_obj()
  {
    return model_obj;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetModel_obj(model_obj newModel_obj, NotificationChain msgs)
  {
    model_obj oldModel_obj = model_obj;
    model_obj = newModel_obj;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJ__MODEL_OBJ, oldModel_obj, newModel_obj);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModel_obj(model_obj newModel_obj)
  {
    if (newModel_obj != model_obj)
    {
      NotificationChain msgs = null;
      if (model_obj != null)
        msgs = ((InternalEObject)model_obj).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJ__MODEL_OBJ, null, msgs);
      if (newModel_obj != null)
        msgs = ((InternalEObject)newModel_obj).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJ__MODEL_OBJ, null, msgs);
      msgs = basicSetModel_obj(newModel_obj, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJ__MODEL_OBJ, newModel_obj, newModel_obj));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public param_obj getParam_obj()
  {
    return param_obj;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParam_obj(param_obj newParam_obj, NotificationChain msgs)
  {
    param_obj oldParam_obj = param_obj;
    param_obj = newParam_obj;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJ__PARAM_OBJ, oldParam_obj, newParam_obj);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParam_obj(param_obj newParam_obj)
  {
    if (newParam_obj != param_obj)
    {
      NotificationChain msgs = null;
      if (param_obj != null)
        msgs = ((InternalEObject)param_obj).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJ__PARAM_OBJ, null, msgs);
      if (newParam_obj != null)
        msgs = ((InternalEObject)newParam_obj).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJ__PARAM_OBJ, null, msgs);
      msgs = basicSetParam_obj(newParam_obj, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJ__PARAM_OBJ, newParam_obj, newParam_obj));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public data_obj getData_obj()
  {
    return data_obj;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetData_obj(data_obj newData_obj, NotificationChain msgs)
  {
    data_obj oldData_obj = data_obj;
    data_obj = newData_obj;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJ__DATA_OBJ, oldData_obj, newData_obj);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setData_obj(data_obj newData_obj)
  {
    if (newData_obj != data_obj)
    {
      NotificationChain msgs = null;
      if (data_obj != null)
        msgs = ((InternalEObject)data_obj).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJ__DATA_OBJ, null, msgs);
      if (newData_obj != null)
        msgs = ((InternalEObject)newData_obj).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJ__DATA_OBJ, null, msgs);
      msgs = basicSetData_obj(newData_obj, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJ__DATA_OBJ, newData_obj, newData_obj));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public task_obj getTask_obj()
  {
    return task_obj;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTask_obj(task_obj newTask_obj, NotificationChain msgs)
  {
    task_obj oldTask_obj = task_obj;
    task_obj = newTask_obj;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJ__TASK_OBJ, oldTask_obj, newTask_obj);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTask_obj(task_obj newTask_obj)
  {
    if (newTask_obj != task_obj)
    {
      NotificationChain msgs = null;
      if (task_obj != null)
        msgs = ((InternalEObject)task_obj).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJ__TASK_OBJ, null, msgs);
      if (newTask_obj != null)
        msgs = ((InternalEObject)newTask_obj).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJ__TASK_OBJ, null, msgs);
      msgs = basicSetTask_obj(newTask_obj, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJ__TASK_OBJ, newTask_obj, newTask_obj));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public tel_obj getTel_obj()
  {
    return tel_obj;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTel_obj(tel_obj newTel_obj, NotificationChain msgs)
  {
    tel_obj oldTel_obj = tel_obj;
    tel_obj = newTel_obj;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJ__TEL_OBJ, oldTel_obj, newTel_obj);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTel_obj(tel_obj newTel_obj)
  {
    if (newTel_obj != tel_obj)
    {
      NotificationChain msgs = null;
      if (tel_obj != null)
        msgs = ((InternalEObject)tel_obj).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJ__TEL_OBJ, null, msgs);
      if (newTel_obj != null)
        msgs = ((InternalEObject)newTel_obj).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJ__TEL_OBJ, null, msgs);
      msgs = basicSetTel_obj(newTel_obj, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJ__TEL_OBJ, newTel_obj, newTel_obj));
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
      case MdlPackage.MCL_OBJ__MODEL_OBJ:
        return basicSetModel_obj(null, msgs);
      case MdlPackage.MCL_OBJ__PARAM_OBJ:
        return basicSetParam_obj(null, msgs);
      case MdlPackage.MCL_OBJ__DATA_OBJ:
        return basicSetData_obj(null, msgs);
      case MdlPackage.MCL_OBJ__TASK_OBJ:
        return basicSetTask_obj(null, msgs);
      case MdlPackage.MCL_OBJ__TEL_OBJ:
        return basicSetTel_obj(null, msgs);
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
      case MdlPackage.MCL_OBJ__MODEL_OBJ:
        return getModel_obj();
      case MdlPackage.MCL_OBJ__PARAM_OBJ:
        return getParam_obj();
      case MdlPackage.MCL_OBJ__DATA_OBJ:
        return getData_obj();
      case MdlPackage.MCL_OBJ__TASK_OBJ:
        return getTask_obj();
      case MdlPackage.MCL_OBJ__TEL_OBJ:
        return getTel_obj();
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
      case MdlPackage.MCL_OBJ__MODEL_OBJ:
        setModel_obj((model_obj)newValue);
        return;
      case MdlPackage.MCL_OBJ__PARAM_OBJ:
        setParam_obj((param_obj)newValue);
        return;
      case MdlPackage.MCL_OBJ__DATA_OBJ:
        setData_obj((data_obj)newValue);
        return;
      case MdlPackage.MCL_OBJ__TASK_OBJ:
        setTask_obj((task_obj)newValue);
        return;
      case MdlPackage.MCL_OBJ__TEL_OBJ:
        setTel_obj((tel_obj)newValue);
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
      case MdlPackage.MCL_OBJ__MODEL_OBJ:
        setModel_obj((model_obj)null);
        return;
      case MdlPackage.MCL_OBJ__PARAM_OBJ:
        setParam_obj((param_obj)null);
        return;
      case MdlPackage.MCL_OBJ__DATA_OBJ:
        setData_obj((data_obj)null);
        return;
      case MdlPackage.MCL_OBJ__TASK_OBJ:
        setTask_obj((task_obj)null);
        return;
      case MdlPackage.MCL_OBJ__TEL_OBJ:
        setTel_obj((tel_obj)null);
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
      case MdlPackage.MCL_OBJ__MODEL_OBJ:
        return model_obj != null;
      case MdlPackage.MCL_OBJ__PARAM_OBJ:
        return param_obj != null;
      case MdlPackage.MCL_OBJ__DATA_OBJ:
        return data_obj != null;
      case MdlPackage.MCL_OBJ__TASK_OBJ:
        return task_obj != null;
      case MdlPackage.MCL_OBJ__TEL_OBJ:
        return tel_obj != null;
    }
    return super.eIsSet(featureID);
  }

} //mcl_objImpl
