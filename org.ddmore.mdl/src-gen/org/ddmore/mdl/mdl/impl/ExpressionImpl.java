/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.ConditionalExpression;
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.MdlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ExpressionImpl#getConditionalExpression <em>Conditional Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExpressionImpl extends MinimalEObjectImpl.Container implements Expression
{
  /**
   * The cached value of the '{@link #getConditionalExpression() <em>Conditional Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConditionalExpression()
   * @generated
   * @ordered
   */
  protected ConditionalExpression conditionalExpression;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ExpressionImpl()
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
    return MdlPackage.Literals.EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConditionalExpression getConditionalExpression()
  {
    return conditionalExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConditionalExpression(ConditionalExpression newConditionalExpression, NotificationChain msgs)
  {
    ConditionalExpression oldConditionalExpression = conditionalExpression;
    conditionalExpression = newConditionalExpression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.EXPRESSION__CONDITIONAL_EXPRESSION, oldConditionalExpression, newConditionalExpression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConditionalExpression(ConditionalExpression newConditionalExpression)
  {
    if (newConditionalExpression != conditionalExpression)
    {
      NotificationChain msgs = null;
      if (conditionalExpression != null)
        msgs = ((InternalEObject)conditionalExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.EXPRESSION__CONDITIONAL_EXPRESSION, null, msgs);
      if (newConditionalExpression != null)
        msgs = ((InternalEObject)newConditionalExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.EXPRESSION__CONDITIONAL_EXPRESSION, null, msgs);
      msgs = basicSetConditionalExpression(newConditionalExpression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.EXPRESSION__CONDITIONAL_EXPRESSION, newConditionalExpression, newConditionalExpression));
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
      case MdlPackage.EXPRESSION__CONDITIONAL_EXPRESSION:
        return basicSetConditionalExpression(null, msgs);
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
      case MdlPackage.EXPRESSION__CONDITIONAL_EXPRESSION:
        return getConditionalExpression();
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
      case MdlPackage.EXPRESSION__CONDITIONAL_EXPRESSION:
        setConditionalExpression((ConditionalExpression)newValue);
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
      case MdlPackage.EXPRESSION__CONDITIONAL_EXPRESSION:
        setConditionalExpression((ConditionalExpression)null);
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
      case MdlPackage.EXPRESSION__CONDITIONAL_EXPRESSION:
        return conditionalExpression != null;
    }
    return super.eIsSet(featureID);
  }

} //ExpressionImpl
