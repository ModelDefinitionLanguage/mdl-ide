/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.AddList;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.ModelBlockStatement;
import org.ddmore.mdl.mdl.RemoveList;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Block Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelBlockStatementImpl#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelBlockStatementImpl#getAddList <em>Add List</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelBlockStatementImpl#getRemoveList <em>Remove List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelBlockStatementImpl extends MinimalEObjectImpl.Container implements ModelBlockStatement
{
  /**
   * The cached value of the '{@link #getStatement() <em>Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStatement()
   * @generated
   * @ordered
   */
  protected BlockStatement statement;

  /**
   * The cached value of the '{@link #getAddList() <em>Add List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAddList()
   * @generated
   * @ordered
   */
  protected AddList addList;

  /**
   * The cached value of the '{@link #getRemoveList() <em>Remove List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRemoveList()
   * @generated
   * @ordered
   */
  protected RemoveList removeList;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ModelBlockStatementImpl()
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
    return MdlPackage.Literals.MODEL_BLOCK_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BlockStatement getStatement()
  {
    return statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStatement(BlockStatement newStatement, NotificationChain msgs)
  {
    BlockStatement oldStatement = statement;
    statement = newStatement;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT, oldStatement, newStatement);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStatement(BlockStatement newStatement)
  {
    if (newStatement != statement)
    {
      NotificationChain msgs = null;
      if (statement != null)
        msgs = ((InternalEObject)statement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT, null, msgs);
      if (newStatement != null)
        msgs = ((InternalEObject)newStatement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT, null, msgs);
      msgs = basicSetStatement(newStatement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT, newStatement, newStatement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AddList getAddList()
  {
    return addList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAddList(AddList newAddList, NotificationChain msgs)
  {
    AddList oldAddList = addList;
    addList = newAddList;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_BLOCK_STATEMENT__ADD_LIST, oldAddList, newAddList);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAddList(AddList newAddList)
  {
    if (newAddList != addList)
    {
      NotificationChain msgs = null;
      if (addList != null)
        msgs = ((InternalEObject)addList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_BLOCK_STATEMENT__ADD_LIST, null, msgs);
      if (newAddList != null)
        msgs = ((InternalEObject)newAddList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_BLOCK_STATEMENT__ADD_LIST, null, msgs);
      msgs = basicSetAddList(newAddList, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_BLOCK_STATEMENT__ADD_LIST, newAddList, newAddList));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RemoveList getRemoveList()
  {
    return removeList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRemoveList(RemoveList newRemoveList, NotificationChain msgs)
  {
    RemoveList oldRemoveList = removeList;
    removeList = newRemoveList;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_BLOCK_STATEMENT__REMOVE_LIST, oldRemoveList, newRemoveList);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRemoveList(RemoveList newRemoveList)
  {
    if (newRemoveList != removeList)
    {
      NotificationChain msgs = null;
      if (removeList != null)
        msgs = ((InternalEObject)removeList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_BLOCK_STATEMENT__REMOVE_LIST, null, msgs);
      if (newRemoveList != null)
        msgs = ((InternalEObject)newRemoveList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_BLOCK_STATEMENT__REMOVE_LIST, null, msgs);
      msgs = basicSetRemoveList(newRemoveList, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_BLOCK_STATEMENT__REMOVE_LIST, newRemoveList, newRemoveList));
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
      case MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT:
        return basicSetStatement(null, msgs);
      case MdlPackage.MODEL_BLOCK_STATEMENT__ADD_LIST:
        return basicSetAddList(null, msgs);
      case MdlPackage.MODEL_BLOCK_STATEMENT__REMOVE_LIST:
        return basicSetRemoveList(null, msgs);
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
      case MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT:
        return getStatement();
      case MdlPackage.MODEL_BLOCK_STATEMENT__ADD_LIST:
        return getAddList();
      case MdlPackage.MODEL_BLOCK_STATEMENT__REMOVE_LIST:
        return getRemoveList();
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
      case MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT:
        setStatement((BlockStatement)newValue);
        return;
      case MdlPackage.MODEL_BLOCK_STATEMENT__ADD_LIST:
        setAddList((AddList)newValue);
        return;
      case MdlPackage.MODEL_BLOCK_STATEMENT__REMOVE_LIST:
        setRemoveList((RemoveList)newValue);
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
      case MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT:
        setStatement((BlockStatement)null);
        return;
      case MdlPackage.MODEL_BLOCK_STATEMENT__ADD_LIST:
        setAddList((AddList)null);
        return;
      case MdlPackage.MODEL_BLOCK_STATEMENT__REMOVE_LIST:
        setRemoveList((RemoveList)null);
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
      case MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT:
        return statement != null;
      case MdlPackage.MODEL_BLOCK_STATEMENT__ADD_LIST:
        return addList != null;
      case MdlPackage.MODEL_BLOCK_STATEMENT__REMOVE_LIST:
        return removeList != null;
    }
    return super.eIsSet(featureID);
  }

} //ModelBlockStatementImpl
