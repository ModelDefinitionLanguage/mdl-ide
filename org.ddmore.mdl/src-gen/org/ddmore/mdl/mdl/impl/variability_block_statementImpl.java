/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.block_statement;
import org.ddmore.mdl.mdl.block_subblock;
import org.ddmore.mdl.mdl.diag_subblock;
import org.ddmore.mdl.mdl.variability_block_statement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>variability block statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.variability_block_statementImpl#getBlock_statement <em>Block statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.variability_block_statementImpl#getBlock_block <em>Block block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.variability_block_statementImpl#getDiag_block <em>Diag block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class variability_block_statementImpl extends MinimalEObjectImpl.Container implements variability_block_statement
{
  /**
   * The cached value of the '{@link #getBlock_statement() <em>Block statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBlock_statement()
   * @generated
   * @ordered
   */
  protected block_statement block_statement;

  /**
   * The cached value of the '{@link #getBlock_block() <em>Block block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBlock_block()
   * @generated
   * @ordered
   */
  protected block_subblock block_block;

  /**
   * The cached value of the '{@link #getDiag_block() <em>Diag block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDiag_block()
   * @generated
   * @ordered
   */
  protected diag_subblock diag_block;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected variability_block_statementImpl()
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
    return MdlPackage.Literals.VARIABILITY_BLOCK_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public block_statement getBlock_statement()
  {
    return block_statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBlock_statement(block_statement newBlock_statement, NotificationChain msgs)
  {
    block_statement oldBlock_statement = block_statement;
    block_statement = newBlock_statement;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_STATEMENT, oldBlock_statement, newBlock_statement);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBlock_statement(block_statement newBlock_statement)
  {
    if (newBlock_statement != block_statement)
    {
      NotificationChain msgs = null;
      if (block_statement != null)
        msgs = ((InternalEObject)block_statement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_STATEMENT, null, msgs);
      if (newBlock_statement != null)
        msgs = ((InternalEObject)newBlock_statement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_STATEMENT, null, msgs);
      msgs = basicSetBlock_statement(newBlock_statement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_STATEMENT, newBlock_statement, newBlock_statement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public block_subblock getBlock_block()
  {
    return block_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBlock_block(block_subblock newBlock_block, NotificationChain msgs)
  {
    block_subblock oldBlock_block = block_block;
    block_block = newBlock_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK, oldBlock_block, newBlock_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBlock_block(block_subblock newBlock_block)
  {
    if (newBlock_block != block_block)
    {
      NotificationChain msgs = null;
      if (block_block != null)
        msgs = ((InternalEObject)block_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK, null, msgs);
      if (newBlock_block != null)
        msgs = ((InternalEObject)newBlock_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK, null, msgs);
      msgs = basicSetBlock_block(newBlock_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK, newBlock_block, newBlock_block));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public diag_subblock getDiag_block()
  {
    return diag_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDiag_block(diag_subblock newDiag_block, NotificationChain msgs)
  {
    diag_subblock oldDiag_block = diag_block;
    diag_block = newDiag_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK, oldDiag_block, newDiag_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDiag_block(diag_subblock newDiag_block)
  {
    if (newDiag_block != diag_block)
    {
      NotificationChain msgs = null;
      if (diag_block != null)
        msgs = ((InternalEObject)diag_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK, null, msgs);
      if (newDiag_block != null)
        msgs = ((InternalEObject)newDiag_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK, null, msgs);
      msgs = basicSetDiag_block(newDiag_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK, newDiag_block, newDiag_block));
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
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_STATEMENT:
        return basicSetBlock_statement(null, msgs);
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK:
        return basicSetBlock_block(null, msgs);
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK:
        return basicSetDiag_block(null, msgs);
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
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_STATEMENT:
        return getBlock_statement();
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK:
        return getBlock_block();
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK:
        return getDiag_block();
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
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_STATEMENT:
        setBlock_statement((block_statement)newValue);
        return;
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK:
        setBlock_block((block_subblock)newValue);
        return;
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK:
        setDiag_block((diag_subblock)newValue);
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
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_STATEMENT:
        setBlock_statement((block_statement)null);
        return;
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK:
        setBlock_block((block_subblock)null);
        return;
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK:
        setDiag_block((diag_subblock)null);
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
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_STATEMENT:
        return block_statement != null;
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK:
        return block_block != null;
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK:
        return diag_block != null;
    }
    return super.eIsSet(featureID);
  }

} //variability_block_statementImpl
