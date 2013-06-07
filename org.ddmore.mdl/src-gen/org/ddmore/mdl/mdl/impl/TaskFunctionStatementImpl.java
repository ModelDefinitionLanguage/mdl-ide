/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.TargetBlock;
import org.ddmore.mdl.mdl.TaskFunctionStatement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task Function Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.TaskFunctionStatementImpl#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.TaskFunctionStatementImpl#getTargetBlock <em>Target Block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaskFunctionStatementImpl extends MinimalEObjectImpl.Container implements TaskFunctionStatement
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
   * The cached value of the '{@link #getTargetBlock() <em>Target Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetBlock()
   * @generated
   * @ordered
   */
  protected TargetBlock targetBlock;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TaskFunctionStatementImpl()
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
    return MdlPackage.Literals.TASK_FUNCTION_STATEMENT;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_FUNCTION_STATEMENT__STATEMENT, oldStatement, newStatement);
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
        msgs = ((InternalEObject)statement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_FUNCTION_STATEMENT__STATEMENT, null, msgs);
      if (newStatement != null)
        msgs = ((InternalEObject)newStatement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_FUNCTION_STATEMENT__STATEMENT, null, msgs);
      msgs = basicSetStatement(newStatement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_FUNCTION_STATEMENT__STATEMENT, newStatement, newStatement));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_FUNCTION_STATEMENT__TARGET_BLOCK, oldTargetBlock, newTargetBlock);
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
        msgs = ((InternalEObject)targetBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_FUNCTION_STATEMENT__TARGET_BLOCK, null, msgs);
      if (newTargetBlock != null)
        msgs = ((InternalEObject)newTargetBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_FUNCTION_STATEMENT__TARGET_BLOCK, null, msgs);
      msgs = basicSetTargetBlock(newTargetBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_FUNCTION_STATEMENT__TARGET_BLOCK, newTargetBlock, newTargetBlock));
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
      case MdlPackage.TASK_FUNCTION_STATEMENT__STATEMENT:
        return basicSetStatement(null, msgs);
      case MdlPackage.TASK_FUNCTION_STATEMENT__TARGET_BLOCK:
        return basicSetTargetBlock(null, msgs);
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
      case MdlPackage.TASK_FUNCTION_STATEMENT__STATEMENT:
        return getStatement();
      case MdlPackage.TASK_FUNCTION_STATEMENT__TARGET_BLOCK:
        return getTargetBlock();
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
      case MdlPackage.TASK_FUNCTION_STATEMENT__STATEMENT:
        setStatement((BlockStatement)newValue);
        return;
      case MdlPackage.TASK_FUNCTION_STATEMENT__TARGET_BLOCK:
        setTargetBlock((TargetBlock)newValue);
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
      case MdlPackage.TASK_FUNCTION_STATEMENT__STATEMENT:
        setStatement((BlockStatement)null);
        return;
      case MdlPackage.TASK_FUNCTION_STATEMENT__TARGET_BLOCK:
        setTargetBlock((TargetBlock)null);
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
      case MdlPackage.TASK_FUNCTION_STATEMENT__STATEMENT:
        return statement != null;
      case MdlPackage.TASK_FUNCTION_STATEMENT__TARGET_BLOCK:
        return targetBlock != null;
    }
    return super.eIsSet(featureID);
  }

} //TaskFunctionStatementImpl
