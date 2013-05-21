/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.DataObjectBlock;
import org.ddmore.mdl.mdl.FileBlock;
import org.ddmore.mdl.mdl.HeaderBlock;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.VerbatimBlock;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Object Block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.DataObjectBlockImpl#getHeaderBlock <em>Header Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.DataObjectBlockImpl#getFileBlock <em>File Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.DataObjectBlockImpl#getVerbatimBlock <em>Verbatim Block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataObjectBlockImpl extends MinimalEObjectImpl.Container implements DataObjectBlock
{
  /**
   * The cached value of the '{@link #getHeaderBlock() <em>Header Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHeaderBlock()
   * @generated
   * @ordered
   */
  protected HeaderBlock headerBlock;

  /**
   * The cached value of the '{@link #getFileBlock() <em>File Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFileBlock()
   * @generated
   * @ordered
   */
  protected FileBlock fileBlock;

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
  protected DataObjectBlockImpl()
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
    return MdlPackage.Literals.DATA_OBJECT_BLOCK;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HeaderBlock getHeaderBlock()
  {
    return headerBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetHeaderBlock(HeaderBlock newHeaderBlock, NotificationChain msgs)
  {
    HeaderBlock oldHeaderBlock = headerBlock;
    headerBlock = newHeaderBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_OBJECT_BLOCK__HEADER_BLOCK, oldHeaderBlock, newHeaderBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHeaderBlock(HeaderBlock newHeaderBlock)
  {
    if (newHeaderBlock != headerBlock)
    {
      NotificationChain msgs = null;
      if (headerBlock != null)
        msgs = ((InternalEObject)headerBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_OBJECT_BLOCK__HEADER_BLOCK, null, msgs);
      if (newHeaderBlock != null)
        msgs = ((InternalEObject)newHeaderBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_OBJECT_BLOCK__HEADER_BLOCK, null, msgs);
      msgs = basicSetHeaderBlock(newHeaderBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_OBJECT_BLOCK__HEADER_BLOCK, newHeaderBlock, newHeaderBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FileBlock getFileBlock()
  {
    return fileBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFileBlock(FileBlock newFileBlock, NotificationChain msgs)
  {
    FileBlock oldFileBlock = fileBlock;
    fileBlock = newFileBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_OBJECT_BLOCK__FILE_BLOCK, oldFileBlock, newFileBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFileBlock(FileBlock newFileBlock)
  {
    if (newFileBlock != fileBlock)
    {
      NotificationChain msgs = null;
      if (fileBlock != null)
        msgs = ((InternalEObject)fileBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_OBJECT_BLOCK__FILE_BLOCK, null, msgs);
      if (newFileBlock != null)
        msgs = ((InternalEObject)newFileBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_OBJECT_BLOCK__FILE_BLOCK, null, msgs);
      msgs = basicSetFileBlock(newFileBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_OBJECT_BLOCK__FILE_BLOCK, newFileBlock, newFileBlock));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_OBJECT_BLOCK__VERBATIM_BLOCK, oldVerbatimBlock, newVerbatimBlock);
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
        msgs = ((InternalEObject)verbatimBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_OBJECT_BLOCK__VERBATIM_BLOCK, null, msgs);
      if (newVerbatimBlock != null)
        msgs = ((InternalEObject)newVerbatimBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_OBJECT_BLOCK__VERBATIM_BLOCK, null, msgs);
      msgs = basicSetVerbatimBlock(newVerbatimBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_OBJECT_BLOCK__VERBATIM_BLOCK, newVerbatimBlock, newVerbatimBlock));
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
      case MdlPackage.DATA_OBJECT_BLOCK__HEADER_BLOCK:
        return basicSetHeaderBlock(null, msgs);
      case MdlPackage.DATA_OBJECT_BLOCK__FILE_BLOCK:
        return basicSetFileBlock(null, msgs);
      case MdlPackage.DATA_OBJECT_BLOCK__VERBATIM_BLOCK:
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
      case MdlPackage.DATA_OBJECT_BLOCK__HEADER_BLOCK:
        return getHeaderBlock();
      case MdlPackage.DATA_OBJECT_BLOCK__FILE_BLOCK:
        return getFileBlock();
      case MdlPackage.DATA_OBJECT_BLOCK__VERBATIM_BLOCK:
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
      case MdlPackage.DATA_OBJECT_BLOCK__HEADER_BLOCK:
        setHeaderBlock((HeaderBlock)newValue);
        return;
      case MdlPackage.DATA_OBJECT_BLOCK__FILE_BLOCK:
        setFileBlock((FileBlock)newValue);
        return;
      case MdlPackage.DATA_OBJECT_BLOCK__VERBATIM_BLOCK:
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
      case MdlPackage.DATA_OBJECT_BLOCK__HEADER_BLOCK:
        setHeaderBlock((HeaderBlock)null);
        return;
      case MdlPackage.DATA_OBJECT_BLOCK__FILE_BLOCK:
        setFileBlock((FileBlock)null);
        return;
      case MdlPackage.DATA_OBJECT_BLOCK__VERBATIM_BLOCK:
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
      case MdlPackage.DATA_OBJECT_BLOCK__HEADER_BLOCK:
        return headerBlock != null;
      case MdlPackage.DATA_OBJECT_BLOCK__FILE_BLOCK:
        return fileBlock != null;
      case MdlPackage.DATA_OBJECT_BLOCK__VERBATIM_BLOCK:
        return verbatimBlock != null;
    }
    return super.eIsSet(featureID);
  }

} //DataObjectBlockImpl
