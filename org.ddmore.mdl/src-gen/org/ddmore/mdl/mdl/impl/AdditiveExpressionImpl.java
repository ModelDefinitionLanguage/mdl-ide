/**
 */
package org.ddmore.mdl.mdl.impl;

import java.util.Collection;

import org.ddmore.mdl.mdl.AdditiveExpression;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.MultiplicativeExpression;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Additive Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.AdditiveExpressionImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.AdditiveExpressionImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.AdditiveExpressionImpl#getString <em>String</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AdditiveExpressionImpl extends MinimalEObjectImpl.Container implements AdditiveExpression
{
  /**
   * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpression()
   * @generated
   * @ordered
   */
  protected EList<MultiplicativeExpression> expression;

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
   * The cached value of the '{@link #getString() <em>String</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getString()
   * @generated
   * @ordered
   */
  protected EList<String> string;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AdditiveExpressionImpl()
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
    return MdlPackage.Literals.ADDITIVE_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<MultiplicativeExpression> getExpression()
  {
    if (expression == null)
    {
      expression = new EObjectContainmentEList<MultiplicativeExpression>(MultiplicativeExpression.class, this, MdlPackage.ADDITIVE_EXPRESSION__EXPRESSION);
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
      operator = new EDataTypeEList<String>(String.class, this, MdlPackage.ADDITIVE_EXPRESSION__OPERATOR);
    }
    return operator;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getString()
  {
    if (string == null)
    {
      string = new EDataTypeEList<String>(String.class, this, MdlPackage.ADDITIVE_EXPRESSION__STRING);
    }
    return string;
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
      case MdlPackage.ADDITIVE_EXPRESSION__EXPRESSION:
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
      case MdlPackage.ADDITIVE_EXPRESSION__EXPRESSION:
        return getExpression();
      case MdlPackage.ADDITIVE_EXPRESSION__OPERATOR:
        return getOperator();
      case MdlPackage.ADDITIVE_EXPRESSION__STRING:
        return getString();
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
      case MdlPackage.ADDITIVE_EXPRESSION__EXPRESSION:
        getExpression().clear();
        getExpression().addAll((Collection<? extends MultiplicativeExpression>)newValue);
        return;
      case MdlPackage.ADDITIVE_EXPRESSION__OPERATOR:
        getOperator().clear();
        getOperator().addAll((Collection<? extends String>)newValue);
        return;
      case MdlPackage.ADDITIVE_EXPRESSION__STRING:
        getString().clear();
        getString().addAll((Collection<? extends String>)newValue);
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
      case MdlPackage.ADDITIVE_EXPRESSION__EXPRESSION:
        getExpression().clear();
        return;
      case MdlPackage.ADDITIVE_EXPRESSION__OPERATOR:
        getOperator().clear();
        return;
      case MdlPackage.ADDITIVE_EXPRESSION__STRING:
        getString().clear();
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
      case MdlPackage.ADDITIVE_EXPRESSION__EXPRESSION:
        return expression != null && !expression.isEmpty();
      case MdlPackage.ADDITIVE_EXPRESSION__OPERATOR:
        return operator != null && !operator.isEmpty();
      case MdlPackage.ADDITIVE_EXPRESSION__STRING:
        return string != null && !string.isEmpty();
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
    result.append(" (operator: ");
    result.append(operator);
    result.append(", string: ");
    result.append(string);
    result.append(')');
    return result.toString();
  }

} //AdditiveExpressionImpl
