/**
 */
package org.ddmore.mdl.mdl.impl;

import java.util.Collection;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.conditional_expression;
import org.ddmore.mdl.mdl.expression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.expressionImpl#getConditional_expression <em>Conditional expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.expressionImpl#getString_expression <em>String expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class expressionImpl extends MinimalEObjectImpl.Container implements expression
{
  /**
   * The cached value of the '{@link #getConditional_expression() <em>Conditional expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConditional_expression()
   * @generated
   * @ordered
   */
  protected conditional_expression conditional_expression;

  /**
   * The cached value of the '{@link #getString_expression() <em>String expression</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getString_expression()
   * @generated
   * @ordered
   */
  protected EList<String> string_expression;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected expressionImpl()
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
  public conditional_expression getConditional_expression()
  {
    return conditional_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConditional_expression(conditional_expression newConditional_expression, NotificationChain msgs)
  {
    conditional_expression oldConditional_expression = conditional_expression;
    conditional_expression = newConditional_expression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.EXPRESSION__CONDITIONAL_EXPRESSION, oldConditional_expression, newConditional_expression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConditional_expression(conditional_expression newConditional_expression)
  {
    if (newConditional_expression != conditional_expression)
    {
      NotificationChain msgs = null;
      if (conditional_expression != null)
        msgs = ((InternalEObject)conditional_expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.EXPRESSION__CONDITIONAL_EXPRESSION, null, msgs);
      if (newConditional_expression != null)
        msgs = ((InternalEObject)newConditional_expression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.EXPRESSION__CONDITIONAL_EXPRESSION, null, msgs);
      msgs = basicSetConditional_expression(newConditional_expression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.EXPRESSION__CONDITIONAL_EXPRESSION, newConditional_expression, newConditional_expression));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getString_expression()
  {
    if (string_expression == null)
    {
      string_expression = new EDataTypeEList<String>(String.class, this, MdlPackage.EXPRESSION__STRING_EXPRESSION);
    }
    return string_expression;
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
        return basicSetConditional_expression(null, msgs);
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
        return getConditional_expression();
      case MdlPackage.EXPRESSION__STRING_EXPRESSION:
        return getString_expression();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case MdlPackage.EXPRESSION__CONDITIONAL_EXPRESSION:
        setConditional_expression((conditional_expression)newValue);
        return;
      case MdlPackage.EXPRESSION__STRING_EXPRESSION:
        getString_expression().clear();
        getString_expression().addAll((Collection<? extends String>)newValue);
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
        setConditional_expression((conditional_expression)null);
        return;
      case MdlPackage.EXPRESSION__STRING_EXPRESSION:
        getString_expression().clear();
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
        return conditional_expression != null;
      case MdlPackage.EXPRESSION__STRING_EXPRESSION:
        return string_expression != null && !string_expression.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (string_expression: ");
    result.append(string_expression);
    result.append(')');
    return result.toString();
  }

} //expressionImpl
