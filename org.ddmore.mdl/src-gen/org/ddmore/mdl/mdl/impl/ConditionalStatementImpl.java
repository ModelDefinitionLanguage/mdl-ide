/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.Block;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.ConditionalStatement;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.ParExpression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conditional Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ConditionalStatementImpl#getParExpression <em>Par Expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ConditionalStatementImpl#getIfStatement <em>If Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ConditionalStatementImpl#getIfBlock <em>If Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ConditionalStatementImpl#getElseStatement <em>Else Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ConditionalStatementImpl#getElseBlock <em>Else Block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConditionalStatementImpl extends MinimalEObjectImpl.Container implements ConditionalStatement
{
  /**
   * The cached value of the '{@link #getParExpression() <em>Par Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParExpression()
   * @generated
   * @ordered
   */
  protected ParExpression parExpression;

  /**
   * The cached value of the '{@link #getIfStatement() <em>If Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIfStatement()
   * @generated
   * @ordered
   */
  protected BlockStatement ifStatement;

  /**
   * The cached value of the '{@link #getIfBlock() <em>If Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIfBlock()
   * @generated
   * @ordered
   */
  protected Block ifBlock;

  /**
   * The cached value of the '{@link #getElseStatement() <em>Else Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElseStatement()
   * @generated
   * @ordered
   */
  protected BlockStatement elseStatement;

  /**
   * The cached value of the '{@link #getElseBlock() <em>Else Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElseBlock()
   * @generated
   * @ordered
   */
  protected Block elseBlock;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConditionalStatementImpl()
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
    return MdlPackage.Literals.CONDITIONAL_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParExpression getParExpression()
  {
    return parExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParExpression(ParExpression newParExpression, NotificationChain msgs)
  {
    ParExpression oldParExpression = parExpression;
    parExpression = newParExpression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.CONDITIONAL_STATEMENT__PAR_EXPRESSION, oldParExpression, newParExpression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParExpression(ParExpression newParExpression)
  {
    if (newParExpression != parExpression)
    {
      NotificationChain msgs = null;
      if (parExpression != null)
        msgs = ((InternalEObject)parExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.CONDITIONAL_STATEMENT__PAR_EXPRESSION, null, msgs);
      if (newParExpression != null)
        msgs = ((InternalEObject)newParExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.CONDITIONAL_STATEMENT__PAR_EXPRESSION, null, msgs);
      msgs = basicSetParExpression(newParExpression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.CONDITIONAL_STATEMENT__PAR_EXPRESSION, newParExpression, newParExpression));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BlockStatement getIfStatement()
  {
    return ifStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIfStatement(BlockStatement newIfStatement, NotificationChain msgs)
  {
    BlockStatement oldIfStatement = ifStatement;
    ifStatement = newIfStatement;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.CONDITIONAL_STATEMENT__IF_STATEMENT, oldIfStatement, newIfStatement);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIfStatement(BlockStatement newIfStatement)
  {
    if (newIfStatement != ifStatement)
    {
      NotificationChain msgs = null;
      if (ifStatement != null)
        msgs = ((InternalEObject)ifStatement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.CONDITIONAL_STATEMENT__IF_STATEMENT, null, msgs);
      if (newIfStatement != null)
        msgs = ((InternalEObject)newIfStatement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.CONDITIONAL_STATEMENT__IF_STATEMENT, null, msgs);
      msgs = basicSetIfStatement(newIfStatement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.CONDITIONAL_STATEMENT__IF_STATEMENT, newIfStatement, newIfStatement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Block getIfBlock()
  {
    return ifBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIfBlock(Block newIfBlock, NotificationChain msgs)
  {
    Block oldIfBlock = ifBlock;
    ifBlock = newIfBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.CONDITIONAL_STATEMENT__IF_BLOCK, oldIfBlock, newIfBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIfBlock(Block newIfBlock)
  {
    if (newIfBlock != ifBlock)
    {
      NotificationChain msgs = null;
      if (ifBlock != null)
        msgs = ((InternalEObject)ifBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.CONDITIONAL_STATEMENT__IF_BLOCK, null, msgs);
      if (newIfBlock != null)
        msgs = ((InternalEObject)newIfBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.CONDITIONAL_STATEMENT__IF_BLOCK, null, msgs);
      msgs = basicSetIfBlock(newIfBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.CONDITIONAL_STATEMENT__IF_BLOCK, newIfBlock, newIfBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BlockStatement getElseStatement()
  {
    return elseStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetElseStatement(BlockStatement newElseStatement, NotificationChain msgs)
  {
    BlockStatement oldElseStatement = elseStatement;
    elseStatement = newElseStatement;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.CONDITIONAL_STATEMENT__ELSE_STATEMENT, oldElseStatement, newElseStatement);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElseStatement(BlockStatement newElseStatement)
  {
    if (newElseStatement != elseStatement)
    {
      NotificationChain msgs = null;
      if (elseStatement != null)
        msgs = ((InternalEObject)elseStatement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.CONDITIONAL_STATEMENT__ELSE_STATEMENT, null, msgs);
      if (newElseStatement != null)
        msgs = ((InternalEObject)newElseStatement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.CONDITIONAL_STATEMENT__ELSE_STATEMENT, null, msgs);
      msgs = basicSetElseStatement(newElseStatement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.CONDITIONAL_STATEMENT__ELSE_STATEMENT, newElseStatement, newElseStatement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Block getElseBlock()
  {
    return elseBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetElseBlock(Block newElseBlock, NotificationChain msgs)
  {
    Block oldElseBlock = elseBlock;
    elseBlock = newElseBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.CONDITIONAL_STATEMENT__ELSE_BLOCK, oldElseBlock, newElseBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElseBlock(Block newElseBlock)
  {
    if (newElseBlock != elseBlock)
    {
      NotificationChain msgs = null;
      if (elseBlock != null)
        msgs = ((InternalEObject)elseBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.CONDITIONAL_STATEMENT__ELSE_BLOCK, null, msgs);
      if (newElseBlock != null)
        msgs = ((InternalEObject)newElseBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.CONDITIONAL_STATEMENT__ELSE_BLOCK, null, msgs);
      msgs = basicSetElseBlock(newElseBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.CONDITIONAL_STATEMENT__ELSE_BLOCK, newElseBlock, newElseBlock));
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
      case MdlPackage.CONDITIONAL_STATEMENT__PAR_EXPRESSION:
        return basicSetParExpression(null, msgs);
      case MdlPackage.CONDITIONAL_STATEMENT__IF_STATEMENT:
        return basicSetIfStatement(null, msgs);
      case MdlPackage.CONDITIONAL_STATEMENT__IF_BLOCK:
        return basicSetIfBlock(null, msgs);
      case MdlPackage.CONDITIONAL_STATEMENT__ELSE_STATEMENT:
        return basicSetElseStatement(null, msgs);
      case MdlPackage.CONDITIONAL_STATEMENT__ELSE_BLOCK:
        return basicSetElseBlock(null, msgs);
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
      case MdlPackage.CONDITIONAL_STATEMENT__PAR_EXPRESSION:
        return getParExpression();
      case MdlPackage.CONDITIONAL_STATEMENT__IF_STATEMENT:
        return getIfStatement();
      case MdlPackage.CONDITIONAL_STATEMENT__IF_BLOCK:
        return getIfBlock();
      case MdlPackage.CONDITIONAL_STATEMENT__ELSE_STATEMENT:
        return getElseStatement();
      case MdlPackage.CONDITIONAL_STATEMENT__ELSE_BLOCK:
        return getElseBlock();
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
      case MdlPackage.CONDITIONAL_STATEMENT__PAR_EXPRESSION:
        setParExpression((ParExpression)newValue);
        return;
      case MdlPackage.CONDITIONAL_STATEMENT__IF_STATEMENT:
        setIfStatement((BlockStatement)newValue);
        return;
      case MdlPackage.CONDITIONAL_STATEMENT__IF_BLOCK:
        setIfBlock((Block)newValue);
        return;
      case MdlPackage.CONDITIONAL_STATEMENT__ELSE_STATEMENT:
        setElseStatement((BlockStatement)newValue);
        return;
      case MdlPackage.CONDITIONAL_STATEMENT__ELSE_BLOCK:
        setElseBlock((Block)newValue);
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
      case MdlPackage.CONDITIONAL_STATEMENT__PAR_EXPRESSION:
        setParExpression((ParExpression)null);
        return;
      case MdlPackage.CONDITIONAL_STATEMENT__IF_STATEMENT:
        setIfStatement((BlockStatement)null);
        return;
      case MdlPackage.CONDITIONAL_STATEMENT__IF_BLOCK:
        setIfBlock((Block)null);
        return;
      case MdlPackage.CONDITIONAL_STATEMENT__ELSE_STATEMENT:
        setElseStatement((BlockStatement)null);
        return;
      case MdlPackage.CONDITIONAL_STATEMENT__ELSE_BLOCK:
        setElseBlock((Block)null);
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
      case MdlPackage.CONDITIONAL_STATEMENT__PAR_EXPRESSION:
        return parExpression != null;
      case MdlPackage.CONDITIONAL_STATEMENT__IF_STATEMENT:
        return ifStatement != null;
      case MdlPackage.CONDITIONAL_STATEMENT__IF_BLOCK:
        return ifBlock != null;
      case MdlPackage.CONDITIONAL_STATEMENT__ELSE_STATEMENT:
        return elseStatement != null;
      case MdlPackage.CONDITIONAL_STATEMENT__ELSE_BLOCK:
        return elseBlock != null;
    }
    return super.eIsSet(featureID);
  }

} //ConditionalStatementImpl
