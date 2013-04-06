/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.block;
import org.ddmore.mdl.mdl.block_statement;
import org.ddmore.mdl.mdl.par_expression;
import org.ddmore.mdl.mdl.statement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.statementImpl#getBlock <em>Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.statementImpl#getPar_expression <em>Par expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.statementImpl#getIf_statement <em>If statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.statementImpl#getElse_statement <em>Else statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class statementImpl extends MinimalEObjectImpl.Container implements statement
{
  /**
   * The cached value of the '{@link #getBlock() <em>Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBlock()
   * @generated
   * @ordered
   */
  protected block block;

  /**
   * The cached value of the '{@link #getPar_expression() <em>Par expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPar_expression()
   * @generated
   * @ordered
   */
  protected par_expression par_expression;

  /**
   * The cached value of the '{@link #getIf_statement() <em>If statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIf_statement()
   * @generated
   * @ordered
   */
  protected block_statement if_statement;

  /**
   * The cached value of the '{@link #getElse_statement() <em>Else statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElse_statement()
   * @generated
   * @ordered
   */
  protected block_statement else_statement;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected statementImpl()
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
    return MdlPackage.Literals.STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public block getBlock()
  {
    return block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBlock(block newBlock, NotificationChain msgs)
  {
    block oldBlock = block;
    block = newBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.STATEMENT__BLOCK, oldBlock, newBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBlock(block newBlock)
  {
    if (newBlock != block)
    {
      NotificationChain msgs = null;
      if (block != null)
        msgs = ((InternalEObject)block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.STATEMENT__BLOCK, null, msgs);
      if (newBlock != null)
        msgs = ((InternalEObject)newBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.STATEMENT__BLOCK, null, msgs);
      msgs = basicSetBlock(newBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.STATEMENT__BLOCK, newBlock, newBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public par_expression getPar_expression()
  {
    return par_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPar_expression(par_expression newPar_expression, NotificationChain msgs)
  {
    par_expression oldPar_expression = par_expression;
    par_expression = newPar_expression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.STATEMENT__PAR_EXPRESSION, oldPar_expression, newPar_expression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPar_expression(par_expression newPar_expression)
  {
    if (newPar_expression != par_expression)
    {
      NotificationChain msgs = null;
      if (par_expression != null)
        msgs = ((InternalEObject)par_expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.STATEMENT__PAR_EXPRESSION, null, msgs);
      if (newPar_expression != null)
        msgs = ((InternalEObject)newPar_expression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.STATEMENT__PAR_EXPRESSION, null, msgs);
      msgs = basicSetPar_expression(newPar_expression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.STATEMENT__PAR_EXPRESSION, newPar_expression, newPar_expression));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public block_statement getIf_statement()
  {
    return if_statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIf_statement(block_statement newIf_statement, NotificationChain msgs)
  {
    block_statement oldIf_statement = if_statement;
    if_statement = newIf_statement;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.STATEMENT__IF_STATEMENT, oldIf_statement, newIf_statement);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIf_statement(block_statement newIf_statement)
  {
    if (newIf_statement != if_statement)
    {
      NotificationChain msgs = null;
      if (if_statement != null)
        msgs = ((InternalEObject)if_statement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.STATEMENT__IF_STATEMENT, null, msgs);
      if (newIf_statement != null)
        msgs = ((InternalEObject)newIf_statement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.STATEMENT__IF_STATEMENT, null, msgs);
      msgs = basicSetIf_statement(newIf_statement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.STATEMENT__IF_STATEMENT, newIf_statement, newIf_statement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public block_statement getElse_statement()
  {
    return else_statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetElse_statement(block_statement newElse_statement, NotificationChain msgs)
  {
    block_statement oldElse_statement = else_statement;
    else_statement = newElse_statement;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.STATEMENT__ELSE_STATEMENT, oldElse_statement, newElse_statement);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElse_statement(block_statement newElse_statement)
  {
    if (newElse_statement != else_statement)
    {
      NotificationChain msgs = null;
      if (else_statement != null)
        msgs = ((InternalEObject)else_statement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.STATEMENT__ELSE_STATEMENT, null, msgs);
      if (newElse_statement != null)
        msgs = ((InternalEObject)newElse_statement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.STATEMENT__ELSE_STATEMENT, null, msgs);
      msgs = basicSetElse_statement(newElse_statement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.STATEMENT__ELSE_STATEMENT, newElse_statement, newElse_statement));
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
      case MdlPackage.STATEMENT__BLOCK:
        return basicSetBlock(null, msgs);
      case MdlPackage.STATEMENT__PAR_EXPRESSION:
        return basicSetPar_expression(null, msgs);
      case MdlPackage.STATEMENT__IF_STATEMENT:
        return basicSetIf_statement(null, msgs);
      case MdlPackage.STATEMENT__ELSE_STATEMENT:
        return basicSetElse_statement(null, msgs);
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
      case MdlPackage.STATEMENT__BLOCK:
        return getBlock();
      case MdlPackage.STATEMENT__PAR_EXPRESSION:
        return getPar_expression();
      case MdlPackage.STATEMENT__IF_STATEMENT:
        return getIf_statement();
      case MdlPackage.STATEMENT__ELSE_STATEMENT:
        return getElse_statement();
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
      case MdlPackage.STATEMENT__BLOCK:
        setBlock((block)newValue);
        return;
      case MdlPackage.STATEMENT__PAR_EXPRESSION:
        setPar_expression((par_expression)newValue);
        return;
      case MdlPackage.STATEMENT__IF_STATEMENT:
        setIf_statement((block_statement)newValue);
        return;
      case MdlPackage.STATEMENT__ELSE_STATEMENT:
        setElse_statement((block_statement)newValue);
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
      case MdlPackage.STATEMENT__BLOCK:
        setBlock((block)null);
        return;
      case MdlPackage.STATEMENT__PAR_EXPRESSION:
        setPar_expression((par_expression)null);
        return;
      case MdlPackage.STATEMENT__IF_STATEMENT:
        setIf_statement((block_statement)null);
        return;
      case MdlPackage.STATEMENT__ELSE_STATEMENT:
        setElse_statement((block_statement)null);
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
      case MdlPackage.STATEMENT__BLOCK:
        return block != null;
      case MdlPackage.STATEMENT__PAR_EXPRESSION:
        return par_expression != null;
      case MdlPackage.STATEMENT__IF_STATEMENT:
        return if_statement != null;
      case MdlPackage.STATEMENT__ELSE_STATEMENT:
        return else_statement != null;
    }
    return super.eIsSet(featureID);
  }

} //statementImpl
