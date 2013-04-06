/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.conditional_expression;
import org.ddmore.mdl.mdl.conditional_or_expression;
import org.ddmore.mdl.mdl.expression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>conditional expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.conditional_expressionImpl#getConditional_or_expression <em>Conditional or expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.conditional_expressionImpl#getExpression1 <em>Expression1</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.conditional_expressionImpl#getExpression2 <em>Expression2</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class conditional_expressionImpl extends MinimalEObjectImpl.Container implements conditional_expression
{
  /**
   * The cached value of the '{@link #getConditional_or_expression() <em>Conditional or expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConditional_or_expression()
   * @generated
   * @ordered
   */
  protected conditional_or_expression conditional_or_expression;

  /**
   * The cached value of the '{@link #getExpression1() <em>Expression1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpression1()
   * @generated
   * @ordered
   */
  protected expression expression1;

  /**
   * The cached value of the '{@link #getExpression2() <em>Expression2</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpression2()
   * @generated
   * @ordered
   */
  protected expression expression2;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected conditional_expressionImpl()
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
    return MdlPackage.Literals.CONDITIONAL_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public conditional_or_expression getConditional_or_expression()
  {
    return conditional_or_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConditional_or_expression(conditional_or_expression newConditional_or_expression, NotificationChain msgs)
  {
    conditional_or_expression oldConditional_or_expression = conditional_or_expression;
    conditional_or_expression = newConditional_or_expression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.CONDITIONAL_EXPRESSION__CONDITIONAL_OR_EXPRESSION, oldConditional_or_expression, newConditional_or_expression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConditional_or_expression(conditional_or_expression newConditional_or_expression)
  {
    if (newConditional_or_expression != conditional_or_expression)
    {
      NotificationChain msgs = null;
      if (conditional_or_expression != null)
        msgs = ((InternalEObject)conditional_or_expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.CONDITIONAL_EXPRESSION__CONDITIONAL_OR_EXPRESSION, null, msgs);
      if (newConditional_or_expression != null)
        msgs = ((InternalEObject)newConditional_or_expression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.CONDITIONAL_EXPRESSION__CONDITIONAL_OR_EXPRESSION, null, msgs);
      msgs = basicSetConditional_or_expression(newConditional_or_expression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.CONDITIONAL_EXPRESSION__CONDITIONAL_OR_EXPRESSION, newConditional_or_expression, newConditional_or_expression));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression getExpression1()
  {
    return expression1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpression1(expression newExpression1, NotificationChain msgs)
  {
    expression oldExpression1 = expression1;
    expression1 = newExpression1;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION1, oldExpression1, newExpression1);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpression1(expression newExpression1)
  {
    if (newExpression1 != expression1)
    {
      NotificationChain msgs = null;
      if (expression1 != null)
        msgs = ((InternalEObject)expression1).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION1, null, msgs);
      if (newExpression1 != null)
        msgs = ((InternalEObject)newExpression1).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION1, null, msgs);
      msgs = basicSetExpression1(newExpression1, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION1, newExpression1, newExpression1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression getExpression2()
  {
    return expression2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpression2(expression newExpression2, NotificationChain msgs)
  {
    expression oldExpression2 = expression2;
    expression2 = newExpression2;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION2, oldExpression2, newExpression2);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpression2(expression newExpression2)
  {
    if (newExpression2 != expression2)
    {
      NotificationChain msgs = null;
      if (expression2 != null)
        msgs = ((InternalEObject)expression2).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION2, null, msgs);
      if (newExpression2 != null)
        msgs = ((InternalEObject)newExpression2).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION2, null, msgs);
      msgs = basicSetExpression2(newExpression2, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION2, newExpression2, newExpression2));
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
      case MdlPackage.CONDITIONAL_EXPRESSION__CONDITIONAL_OR_EXPRESSION:
        return basicSetConditional_or_expression(null, msgs);
      case MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION1:
        return basicSetExpression1(null, msgs);
      case MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION2:
        return basicSetExpression2(null, msgs);
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
      case MdlPackage.CONDITIONAL_EXPRESSION__CONDITIONAL_OR_EXPRESSION:
        return getConditional_or_expression();
      case MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION1:
        return getExpression1();
      case MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION2:
        return getExpression2();
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
      case MdlPackage.CONDITIONAL_EXPRESSION__CONDITIONAL_OR_EXPRESSION:
        setConditional_or_expression((conditional_or_expression)newValue);
        return;
      case MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION1:
        setExpression1((expression)newValue);
        return;
      case MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION2:
        setExpression2((expression)newValue);
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
      case MdlPackage.CONDITIONAL_EXPRESSION__CONDITIONAL_OR_EXPRESSION:
        setConditional_or_expression((conditional_or_expression)null);
        return;
      case MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION1:
        setExpression1((expression)null);
        return;
      case MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION2:
        setExpression2((expression)null);
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
      case MdlPackage.CONDITIONAL_EXPRESSION__CONDITIONAL_OR_EXPRESSION:
        return conditional_or_expression != null;
      case MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION1:
        return expression1 != null;
      case MdlPackage.CONDITIONAL_EXPRESSION__EXPRESSION2:
        return expression2 != null;
    }
    return super.eIsSet(featureID);
  }

} //conditional_expressionImpl
