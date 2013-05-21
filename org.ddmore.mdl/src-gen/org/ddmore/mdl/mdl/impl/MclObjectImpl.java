/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.DataObject;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.ModelObject;
import org.ddmore.mdl.mdl.ParameterObject;
import org.ddmore.mdl.mdl.TELObject;
import org.ddmore.mdl.mdl.TaskObject;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mcl Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.MclObjectImpl#getModelObject <em>Model Object</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.MclObjectImpl#getParameterObject <em>Parameter Object</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.MclObjectImpl#getDataObject <em>Data Object</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.MclObjectImpl#getTaskObject <em>Task Object</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.MclObjectImpl#getTelObject <em>Tel Object</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MclObjectImpl extends MinimalEObjectImpl.Container implements MclObject
{
  /**
   * The cached value of the '{@link #getModelObject() <em>Model Object</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelObject()
   * @generated
   * @ordered
   */
  protected ModelObject modelObject;

  /**
   * The cached value of the '{@link #getParameterObject() <em>Parameter Object</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameterObject()
   * @generated
   * @ordered
   */
  protected ParameterObject parameterObject;

  /**
   * The cached value of the '{@link #getDataObject() <em>Data Object</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataObject()
   * @generated
   * @ordered
   */
  protected DataObject dataObject;

  /**
   * The cached value of the '{@link #getTaskObject() <em>Task Object</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTaskObject()
   * @generated
   * @ordered
   */
  protected TaskObject taskObject;

  /**
   * The cached value of the '{@link #getTelObject() <em>Tel Object</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTelObject()
   * @generated
   * @ordered
   */
  protected TELObject telObject;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MclObjectImpl()
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
    return MdlPackage.Literals.MCL_OBJECT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelObject getModelObject()
  {
    return modelObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetModelObject(ModelObject newModelObject, NotificationChain msgs)
  {
    ModelObject oldModelObject = modelObject;
    modelObject = newModelObject;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJECT__MODEL_OBJECT, oldModelObject, newModelObject);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModelObject(ModelObject newModelObject)
  {
    if (newModelObject != modelObject)
    {
      NotificationChain msgs = null;
      if (modelObject != null)
        msgs = ((InternalEObject)modelObject).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJECT__MODEL_OBJECT, null, msgs);
      if (newModelObject != null)
        msgs = ((InternalEObject)newModelObject).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJECT__MODEL_OBJECT, null, msgs);
      msgs = basicSetModelObject(newModelObject, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJECT__MODEL_OBJECT, newModelObject, newModelObject));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParameterObject getParameterObject()
  {
    return parameterObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParameterObject(ParameterObject newParameterObject, NotificationChain msgs)
  {
    ParameterObject oldParameterObject = parameterObject;
    parameterObject = newParameterObject;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJECT__PARAMETER_OBJECT, oldParameterObject, newParameterObject);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParameterObject(ParameterObject newParameterObject)
  {
    if (newParameterObject != parameterObject)
    {
      NotificationChain msgs = null;
      if (parameterObject != null)
        msgs = ((InternalEObject)parameterObject).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJECT__PARAMETER_OBJECT, null, msgs);
      if (newParameterObject != null)
        msgs = ((InternalEObject)newParameterObject).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJECT__PARAMETER_OBJECT, null, msgs);
      msgs = basicSetParameterObject(newParameterObject, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJECT__PARAMETER_OBJECT, newParameterObject, newParameterObject));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DataObject getDataObject()
  {
    return dataObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDataObject(DataObject newDataObject, NotificationChain msgs)
  {
    DataObject oldDataObject = dataObject;
    dataObject = newDataObject;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJECT__DATA_OBJECT, oldDataObject, newDataObject);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDataObject(DataObject newDataObject)
  {
    if (newDataObject != dataObject)
    {
      NotificationChain msgs = null;
      if (dataObject != null)
        msgs = ((InternalEObject)dataObject).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJECT__DATA_OBJECT, null, msgs);
      if (newDataObject != null)
        msgs = ((InternalEObject)newDataObject).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJECT__DATA_OBJECT, null, msgs);
      msgs = basicSetDataObject(newDataObject, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJECT__DATA_OBJECT, newDataObject, newDataObject));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TaskObject getTaskObject()
  {
    return taskObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTaskObject(TaskObject newTaskObject, NotificationChain msgs)
  {
    TaskObject oldTaskObject = taskObject;
    taskObject = newTaskObject;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJECT__TASK_OBJECT, oldTaskObject, newTaskObject);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTaskObject(TaskObject newTaskObject)
  {
    if (newTaskObject != taskObject)
    {
      NotificationChain msgs = null;
      if (taskObject != null)
        msgs = ((InternalEObject)taskObject).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJECT__TASK_OBJECT, null, msgs);
      if (newTaskObject != null)
        msgs = ((InternalEObject)newTaskObject).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJECT__TASK_OBJECT, null, msgs);
      msgs = basicSetTaskObject(newTaskObject, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJECT__TASK_OBJECT, newTaskObject, newTaskObject));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TELObject getTelObject()
  {
    return telObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTelObject(TELObject newTelObject, NotificationChain msgs)
  {
    TELObject oldTelObject = telObject;
    telObject = newTelObject;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJECT__TEL_OBJECT, oldTelObject, newTelObject);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTelObject(TELObject newTelObject)
  {
    if (newTelObject != telObject)
    {
      NotificationChain msgs = null;
      if (telObject != null)
        msgs = ((InternalEObject)telObject).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJECT__TEL_OBJECT, null, msgs);
      if (newTelObject != null)
        msgs = ((InternalEObject)newTelObject).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MCL_OBJECT__TEL_OBJECT, null, msgs);
      msgs = basicSetTelObject(newTelObject, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MCL_OBJECT__TEL_OBJECT, newTelObject, newTelObject));
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
      case MdlPackage.MCL_OBJECT__MODEL_OBJECT:
        return basicSetModelObject(null, msgs);
      case MdlPackage.MCL_OBJECT__PARAMETER_OBJECT:
        return basicSetParameterObject(null, msgs);
      case MdlPackage.MCL_OBJECT__DATA_OBJECT:
        return basicSetDataObject(null, msgs);
      case MdlPackage.MCL_OBJECT__TASK_OBJECT:
        return basicSetTaskObject(null, msgs);
      case MdlPackage.MCL_OBJECT__TEL_OBJECT:
        return basicSetTelObject(null, msgs);
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
      case MdlPackage.MCL_OBJECT__MODEL_OBJECT:
        return getModelObject();
      case MdlPackage.MCL_OBJECT__PARAMETER_OBJECT:
        return getParameterObject();
      case MdlPackage.MCL_OBJECT__DATA_OBJECT:
        return getDataObject();
      case MdlPackage.MCL_OBJECT__TASK_OBJECT:
        return getTaskObject();
      case MdlPackage.MCL_OBJECT__TEL_OBJECT:
        return getTelObject();
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
      case MdlPackage.MCL_OBJECT__MODEL_OBJECT:
        setModelObject((ModelObject)newValue);
        return;
      case MdlPackage.MCL_OBJECT__PARAMETER_OBJECT:
        setParameterObject((ParameterObject)newValue);
        return;
      case MdlPackage.MCL_OBJECT__DATA_OBJECT:
        setDataObject((DataObject)newValue);
        return;
      case MdlPackage.MCL_OBJECT__TASK_OBJECT:
        setTaskObject((TaskObject)newValue);
        return;
      case MdlPackage.MCL_OBJECT__TEL_OBJECT:
        setTelObject((TELObject)newValue);
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
      case MdlPackage.MCL_OBJECT__MODEL_OBJECT:
        setModelObject((ModelObject)null);
        return;
      case MdlPackage.MCL_OBJECT__PARAMETER_OBJECT:
        setParameterObject((ParameterObject)null);
        return;
      case MdlPackage.MCL_OBJECT__DATA_OBJECT:
        setDataObject((DataObject)null);
        return;
      case MdlPackage.MCL_OBJECT__TASK_OBJECT:
        setTaskObject((TaskObject)null);
        return;
      case MdlPackage.MCL_OBJECT__TEL_OBJECT:
        setTelObject((TELObject)null);
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
      case MdlPackage.MCL_OBJECT__MODEL_OBJECT:
        return modelObject != null;
      case MdlPackage.MCL_OBJECT__PARAMETER_OBJECT:
        return parameterObject != null;
      case MdlPackage.MCL_OBJECT__DATA_OBJECT:
        return dataObject != null;
      case MdlPackage.MCL_OBJECT__TASK_OBJECT:
        return taskObject != null;
      case MdlPackage.MCL_OBJECT__TEL_OBJECT:
        return telObject != null;
    }
    return super.eIsSet(featureID);
  }

} //MclObjectImpl
