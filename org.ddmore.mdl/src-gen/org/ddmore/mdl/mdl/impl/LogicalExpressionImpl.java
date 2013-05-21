/**
 */
package org.ddmore.mdl.mdl.impl;

import java.util.Collection;

import org.ddmore.mdl.mdl.AdditiveExpression;
import org.ddmore.mdl.mdl.LogicalExpression;
import org.ddmore.mdl.mdl.MdlPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Logical Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.LogicalExpressionImpl#getNegation <em>Negation</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.LogicalExpressionImpl#getBoolean <em>Boolean</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.LogicalExpressionImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.LogicalExpressionImpl#getOperator <em>Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LogicalExpressionImpl extends MinimalEObjectImpl.Container implements LogicalExpression
{
  /**
   * The default value of the '{@link #getNegation() <em>Negation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNegation()
   * @generated
   * @ordered
   */
  protected static final String NEGATION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNegation() <em>Negation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNegation()
   * @generated
   * @ordered
   */
  protected String negation = NEGATION_EDEFAULT;

  /**
   * The default value of the '{@link #getBoolean() <em>Boolean</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBoolean()
   * @generated
   * @ordered
   */
  protected static final String BOOLEAN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getBoolean() <em>Boolean</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBoolean()
   * @generated
   * @ordered
   */
  protected String boolean_ = BOOLEAN_EDEFAULT;

  /**
   * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpression()
   * @generated
   * @ordered
   */
  protected EList<AdditiveExpression> expression;

  /**
   * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOperator()
   * @generated
   * @ordered
   */
  protected EList<String> operator;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LogicalExpressionImpl()
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
    return MdlPackage.Literals.LOGICAL_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNegation()
  {
    return negation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNegation(String newNegation)
  {
    String oldNegation = negation;
    negation = newNegation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.LOGICAL_EXPRESSION__NEGATION, oldNegation, negation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getBoolean()
  {
    return boolean_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBoolean(String newBoolean)
  {
    String oldBoolean = boolean_;
    boolean_ = newBoolean;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.LOGICAL_EXPRESSION__BOOLEAN, oldBoolean, boolean_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<AdditiveExpression> getExpression()
  {
    if (expression == null)
    {
      expression = new EObjectContainmentEList<AdditiveExpression>(AdditiveExpression.class, this, MdlPackage.LOGICAL_EXPRESSION__EXPRESSION);
    }
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getOperator()
  {
    if (operator == null)
    {
      operator = new EDataTypeEList<String>(String.class, this, MdlPackage.LOGICAL_EXPRESSION__OPERATOR);
    }
    return operator;
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
      case MdlPackage.LOGICAL_EXPRESSION__EXPRESSION:
        return ((InternalEList<?>)getExpression()).basicRemove(otherEnd, msgs);
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
      case MdlPackage.LOGICAL_EXPRESSION__NEGATION:
        return getNegation();
      case MdlPackage.LOGICAL_EXPRESSION__BOOLEAN:
        return getBoolean();
      case MdlPackage.LOGICAL_EXPRESSION__EXPRESSION:
        return getExpression();
      case MdlPackage.LOGICAL_EXPRESSION__OPERATOR:
        return getOperator();
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
      case MdlPackage.LOGICAL_EXPRESSION__NEGATION:
        setNegation((String)newValue);
        return;
      case MdlPackage.LOGICAL_EXPRESSION__BOOLEAN:
        setBoolean((String)newValue);
        return;
      case MdlPackage.LOGICAL_EXPRESSION__EXPRESSION:
        getExpression().clear();
        getExpression().addAll((Collection<? extends AdditiveExpression>)newValue);
        return;
      case MdlPackage.LOGICAL_EXPRESSION__OPERATOR:
        getOperator().clear();
        getOperator().addAll((Collection<? extends String>)newValue);
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
      case MdlPackage.LOGICAL_EXPRESSION__NEGATION:
        setNegation(NEGATION_EDEFAULT);
        return;
      case MdlPackage.LOGICAL_EXPRESSION__BOOLEAN:
        setBoolean(BOOLEAN_EDEFAULT);
        return;
      case MdlPackage.LOGICAL_EXPRESSION__EXPRESSION:
        getExpression().clear();
        return;
      case MdlPackage.LOGICAL_EXPRESSION__OPERATOR:
        getOperator().clear();
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
      case MdlPackage.LOGICAL_EXPRESSION__NEGATION:
        return NEGATION_EDEFAULT == null ? negation != null : !NEGATION_EDEFAULT.equals(negation);
      case MdlPackage.LOGICAL_EXPRESSION__BOOLEAN:
        return BOOLEAN_EDEFAULT == null ? boolean_ != null : !BOOLEAN_EDEFAULT.equals(boolean_);
      case MdlPackage.LOGICAL_EXPRESSION__EXPRESSION:
        return expression != null && !expression.isEmpty();
      case MdlPackage.LOGICAL_EXPRESSION__OPERATOR:
        return operator != null && !operator.isEmpty();
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
    result.append(" (negation: ");
    result.append(negation);
    result.append(", boolean: ");
    result.append(boolean_);
    result.append(", operator: ");
    result.append(operator);
    result.append(')');
    return result.toString();
  }

} //LogicalExpressionImpl
