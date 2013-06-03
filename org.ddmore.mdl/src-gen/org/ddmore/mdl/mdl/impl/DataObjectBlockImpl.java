/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.DataObjectBlock;
import org.ddmore.mdl.mdl.FileBlock;
import org.ddmore.mdl.mdl.HeaderBlock;
import org.ddmore.mdl.mdl.ImportBlock;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.TargetBlock;

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
 *   <li>{@link org.ddmore.mdl.mdl.impl.DataObjectBlockImpl#getTargetBlock <em>Target Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.DataObjectBlockImpl#getImportBlock <em>Import Block</em>}</li>
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
   * The cached value of the '{@link #getTargetBlock() <em>Target Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetBlock()
   * @generated
   * @ordered
   */
  protected TargetBlock targetBlock;

  /**
   * The cached value of the '{@link #getImportBlock() <em>Import Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImportBlock()
   * @generated
   * @ordered
   */
  protected ImportBlock importBlock;

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
  public TargetBlock getTargetBlock()
  {
    return targetBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTargetBlock(TargetBlock newTargetBlock, NotificationChain msgs)
  {
    TargetBlock oldTargetBlock = targetBlock;
    targetBlock = newTargetBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_OBJECT_BLOCK__TARGET_BLOCK, oldTargetBlock, newTargetBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetBlock(TargetBlock newTargetBlock)
  {
    if (newTargetBlock != targetBlock)
    {
      NotificationChain msgs = null;
      if (targetBlock != null)
        msgs = ((InternalEObject)targetBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_OBJECT_BLOCK__TARGET_BLOCK, null, msgs);
      if (newTargetBlock != null)
        msgs = ((InternalEObject)newTargetBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_OBJECT_BLOCK__TARGET_BLOCK, null, msgs);
      msgs = basicSetTargetBlock(newTargetBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_OBJECT_BLOCK__TARGET_BLOCK, newTargetBlock, newTargetBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ImportBlock getImportBlock()
  {
    return importBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetImportBlock(ImportBlock newImportBlock, NotificationChain msgs)
  {
    ImportBlock oldImportBlock = importBlock;
    importBlock = newImportBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_OBJECT_BLOCK__IMPORT_BLOCK, oldImportBlock, newImportBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setImportBlock(ImportBlock newImportBlock)
  {
    if (newImportBlock != importBlock)
    {
      NotificationChain msgs = null;
      if (importBlock != null)
        msgs = ((InternalEObject)importBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_OBJECT_BLOCK__IMPORT_BLOCK, null, msgs);
      if (newImportBlock != null)
        msgs = ((InternalEObject)newImportBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_OBJECT_BLOCK__IMPORT_BLOCK, null, msgs);
      msgs = basicSetImportBlock(newImportBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_OBJECT_BLOCK__IMPORT_BLOCK, newImportBlock, newImportBlock));
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
      case MdlPackage.DATA_OBJECT_BLOCK__TARGET_BLOCK:
        return basicSetTargetBlock(null, msgs);
      case MdlPackage.DATA_OBJECT_BLOCK__IMPORT_BLOCK:
        return basicSetImportBlock(null, msgs);
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
      case MdlPackage.DATA_OBJECT_BLOCK__TARGET_BLOCK:
        return getTargetBlock();
      case MdlPackage.DATA_OBJECT_BLOCK__IMPORT_BLOCK:
        return getImportBlock();
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
      case MdlPackage.DATA_OBJECT_BLOCK__TARGET_BLOCK:
        setTargetBlock((TargetBlock)newValue);
        return;
      case MdlPackage.DATA_OBJECT_BLOCK__IMPORT_BLOCK:
        setImportBlock((ImportBlock)newValue);
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
      case MdlPackage.DATA_OBJECT_BLOCK__TARGET_BLOCK:
        setTargetBlock((TargetBlock)null);
        return;
      case MdlPackage.DATA_OBJECT_BLOCK__IMPORT_BLOCK:
        setImportBlock((ImportBlock)null);
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
      case MdlPackage.DATA_OBJECT_BLOCK__TARGET_BLOCK:
        return targetBlock != null;
      case MdlPackage.DATA_OBJECT_BLOCK__IMPORT_BLOCK:
        return importBlock != null;
    }
    return super.eIsSet(featureID);
  }

} //DataObjectBlockImpl
