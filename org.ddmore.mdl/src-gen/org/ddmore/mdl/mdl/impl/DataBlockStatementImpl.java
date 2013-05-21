/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.AcceptList;
import org.ddmore.mdl.mdl.DataBlockStatement;
import org.ddmore.mdl.mdl.DropList;
import org.ddmore.mdl.mdl.IgnoreList;
import org.ddmore.mdl.mdl.MdlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Block Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.DataBlockStatementImpl#getIgnoreList <em>Ignore List</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.DataBlockStatementImpl#getAcceptList <em>Accept List</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.DataBlockStatementImpl#getDropList <em>Drop List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataBlockStatementImpl extends MinimalEObjectImpl.Container implements DataBlockStatement
{
  /**
   * The cached value of the '{@link #getIgnoreList() <em>Ignore List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIgnoreList()
   * @generated
   * @ordered
   */
  protected IgnoreList ignoreList;

  /**
   * The cached value of the '{@link #getAcceptList() <em>Accept List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAcceptList()
   * @generated
   * @ordered
   */
  protected AcceptList acceptList;

  /**
   * The cached value of the '{@link #getDropList() <em>Drop List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDropList()
   * @generated
   * @ordered
   */
  protected DropList dropList;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DataBlockStatementImpl()
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
    return MdlPackage.Literals.DATA_BLOCK_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IgnoreList getIgnoreList()
  {
    return ignoreList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIgnoreList(IgnoreList newIgnoreList, NotificationChain msgs)
  {
    IgnoreList oldIgnoreList = ignoreList;
    ignoreList = newIgnoreList;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_BLOCK_STATEMENT__IGNORE_LIST, oldIgnoreList, newIgnoreList);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIgnoreList(IgnoreList newIgnoreList)
  {
    if (newIgnoreList != ignoreList)
    {
      NotificationChain msgs = null;
      if (ignoreList != null)
        msgs = ((InternalEObject)ignoreList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_BLOCK_STATEMENT__IGNORE_LIST, null, msgs);
      if (newIgnoreList != null)
        msgs = ((InternalEObject)newIgnoreList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_BLOCK_STATEMENT__IGNORE_LIST, null, msgs);
      msgs = basicSetIgnoreList(newIgnoreList, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_BLOCK_STATEMENT__IGNORE_LIST, newIgnoreList, newIgnoreList));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AcceptList getAcceptList()
  {
    return acceptList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAcceptList(AcceptList newAcceptList, NotificationChain msgs)
  {
    AcceptList oldAcceptList = acceptList;
    acceptList = newAcceptList;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_BLOCK_STATEMENT__ACCEPT_LIST, oldAcceptList, newAcceptList);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAcceptList(AcceptList newAcceptList)
  {
    if (newAcceptList != acceptList)
    {
      NotificationChain msgs = null;
      if (acceptList != null)
        msgs = ((InternalEObject)acceptList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_BLOCK_STATEMENT__ACCEPT_LIST, null, msgs);
      if (newAcceptList != null)
        msgs = ((InternalEObject)newAcceptList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_BLOCK_STATEMENT__ACCEPT_LIST, null, msgs);
      msgs = basicSetAcceptList(newAcceptList, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_BLOCK_STATEMENT__ACCEPT_LIST, newAcceptList, newAcceptList));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DropList getDropList()
  {
    return dropList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDropList(DropList newDropList, NotificationChain msgs)
  {
    DropList oldDropList = dropList;
    dropList = newDropList;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_BLOCK_STATEMENT__DROP_LIST, oldDropList, newDropList);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDropList(DropList newDropList)
  {
    if (newDropList != dropList)
    {
      NotificationChain msgs = null;
      if (dropList != null)
        msgs = ((InternalEObject)dropList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_BLOCK_STATEMENT__DROP_LIST, null, msgs);
      if (newDropList != null)
        msgs = ((InternalEObject)newDropList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.DATA_BLOCK_STATEMENT__DROP_LIST, null, msgs);
      msgs = basicSetDropList(newDropList, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.DATA_BLOCK_STATEMENT__DROP_LIST, newDropList, newDropList));
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
      case MdlPackage.DATA_BLOCK_STATEMENT__IGNORE_LIST:
        return basicSetIgnoreList(null, msgs);
      case MdlPackage.DATA_BLOCK_STATEMENT__ACCEPT_LIST:
        return basicSetAcceptList(null, msgs);
      case MdlPackage.DATA_BLOCK_STATEMENT__DROP_LIST:
        return basicSetDropList(null, msgs);
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
      case MdlPackage.DATA_BLOCK_STATEMENT__IGNORE_LIST:
        return getIgnoreList();
      case MdlPackage.DATA_BLOCK_STATEMENT__ACCEPT_LIST:
        return getAcceptList();
      case MdlPackage.DATA_BLOCK_STATEMENT__DROP_LIST:
        return getDropList();
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
      case MdlPackage.DATA_BLOCK_STATEMENT__IGNORE_LIST:
        setIgnoreList((IgnoreList)newValue);
        return;
      case MdlPackage.DATA_BLOCK_STATEMENT__ACCEPT_LIST:
        setAcceptList((AcceptList)newValue);
        return;
      case MdlPackage.DATA_BLOCK_STATEMENT__DROP_LIST:
        setDropList((DropList)newValue);
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
      case MdlPackage.DATA_BLOCK_STATEMENT__IGNORE_LIST:
        setIgnoreList((IgnoreList)null);
        return;
      case MdlPackage.DATA_BLOCK_STATEMENT__ACCEPT_LIST:
        setAcceptList((AcceptList)null);
        return;
      case MdlPackage.DATA_BLOCK_STATEMENT__DROP_LIST:
        setDropList((DropList)null);
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
      case MdlPackage.DATA_BLOCK_STATEMENT__IGNORE_LIST:
        return ignoreList != null;
      case MdlPackage.DATA_BLOCK_STATEMENT__ACCEPT_LIST:
        return acceptList != null;
      case MdlPackage.DATA_BLOCK_STATEMENT__DROP_LIST:
        return dropList != null;
    }
    return super.eIsSet(featureID);
  }

} //DataBlockStatementImpl
