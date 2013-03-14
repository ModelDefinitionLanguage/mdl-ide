/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.data_obj_block;
import org.ddmore.mdl.mdl.file_block;
import org.ddmore.mdl.mdl.header_block;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>data obj block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.data_obj_blockImpl#getHeader_block <em>Header block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.data_obj_blockImpl#getFile_block <em>File block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class data_obj_blockImpl extends MinimalEObjectImpl.Container implements data_obj_block
{
  /**
   * The cached value of the '{@link #getHeader_block() <em>Header block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHeader_block()
   * @generated
   * @ordered
   */
  protected header_block header_block;

  /**
   * The cached value of the '{@link #getFile_block() <em>File block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFile_block()
   * @generated
   * @ordered
   */
  protected file_block file_block;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected data_obj_blockImpl()
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
    return MdlPackage.Literals.DATA_OBJ_BLOCK;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public header_block getHeader_block()
  {
    return header_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetHeader_block(header_block newHeader_block, NotificationChain msgs)
  {
    header_block oldHeader_block = header_block;
    header_block = newHeader_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_OBJ_BLOCK__HEADER_BLOCK, oldHeader_block, newHeader_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHeader_block(header_block newHeader_block)
  {
    if (newHeader_block != header_block)
    {
      NotificationChain msgs = null;
      if (header_block != null)
        msgs = ((InternalEObject)header_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_OBJ_BLOCK__HEADER_BLOCK, null, msgs);
      if (newHeader_block != null)
        msgs = ((InternalEObject)newHeader_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_OBJ_BLOCK__HEADER_BLOCK, null, msgs);
      msgs = basicSetHeader_block(newHeader_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_OBJ_BLOCK__HEADER_BLOCK, newHeader_block, newHeader_block));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public file_block getFile_block()
  {
    return file_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFile_block(file_block newFile_block, NotificationChain msgs)
  {
    file_block oldFile_block = file_block;
    file_block = newFile_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_OBJ_BLOCK__FILE_BLOCK, oldFile_block, newFile_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFile_block(file_block newFile_block)
  {
    if (newFile_block != file_block)
    {
      NotificationChain msgs = null;
      if (file_block != null)
        msgs = ((InternalEObject)file_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_OBJ_BLOCK__FILE_BLOCK, null, msgs);
      if (newFile_block != null)
        msgs = ((InternalEObject)newFile_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_OBJ_BLOCK__FILE_BLOCK, null, msgs);
      msgs = basicSetFile_block(newFile_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_OBJ_BLOCK__FILE_BLOCK, newFile_block, newFile_block));
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
      case MdlPackage.DATA_OBJ_BLOCK__HEADER_BLOCK:
        return basicSetHeader_block(null, msgs);
      case MdlPackage.DATA_OBJ_BLOCK__FILE_BLOCK:
        return basicSetFile_block(null, msgs);
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
      case MdlPackage.DATA_OBJ_BLOCK__HEADER_BLOCK:
        return getHeader_block();
      case MdlPackage.DATA_OBJ_BLOCK__FILE_BLOCK:
        return getFile_block();
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
      case MdlPackage.DATA_OBJ_BLOCK__HEADER_BLOCK:
        setHeader_block((header_block)newValue);
        return;
      case MdlPackage.DATA_OBJ_BLOCK__FILE_BLOCK:
        setFile_block((file_block)newValue);
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
      case MdlPackage.DATA_OBJ_BLOCK__HEADER_BLOCK:
        setHeader_block((header_block)null);
        return;
      case MdlPackage.DATA_OBJ_BLOCK__FILE_BLOCK:
        setFile_block((file_block)null);
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
      case MdlPackage.DATA_OBJ_BLOCK__HEADER_BLOCK:
        return header_block != null;
      case MdlPackage.DATA_OBJ_BLOCK__FILE_BLOCK:
        return file_block != null;
    }
    return super.eIsSet(featureID);
  }

} //data_obj_blockImpl
