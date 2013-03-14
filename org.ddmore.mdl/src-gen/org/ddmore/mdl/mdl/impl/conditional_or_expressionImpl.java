/**
 */
package org.ddmore.mdl.mdl.impl;

import java.util.Collection;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.conditional_and_expression;
import org.ddmore.mdl.mdl.conditional_or_expression;

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
 * An implementation of the model object '<em><b>conditional or expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.conditional_or_expressionImpl#getConditional_and_expression <em>Conditional and expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.conditional_or_expressionImpl#getOperator <em>Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class conditional_or_expressionImpl extends MinimalEObjectImpl.Container implements conditional_or_expression
{
  /**
   * The cached value of the '{@link #getConditional_and_expression() <em>Conditional and expression</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConditional_and_expression()
   * @generated
   * @ordered
   */
  protected EList<conditional_and_expression> conditional_and_expression;

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
  protected conditional_or_expressionImpl()
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
    return MdlPackage.Literals.CONDITIONAL_OR_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<conditional_and_expression> getConditional_and_expression()
  {
    if (conditional_and_expression == null)
    {
      conditional_and_expression = new EObjectContainmentEList<conditional_and_expression>(conditional_and_expression.class, this, MdlPackage.CONDITIONAL_OR_EXPRESSION__CONDITIONAL_AND_EXPRESSION);
    }
    return conditional_and_expression;
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
      operator = new EDataTypeEList<String>(String.class, this, MdlPackage.CONDITIONAL_OR_EXPRESSION__OPERATOR);
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
      case MdlPackage.CONDITIONAL_OR_EXPRESSION__CONDITIONAL_AND_EXPRESSION:
        return ((InternalEList<?>)getConditional_and_expression()).basicRemove(otherEnd, msgs);
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
      case MdlPackage.CONDITIONAL_OR_EXPRESSION__CONDITIONAL_AND_EXPRESSION:
        return getConditional_and_expression();
      case MdlPackage.CONDITIONAL_OR_EXPRESSION__OPERATOR:
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
      case MdlPackage.CONDITIONAL_OR_EXPRESSION__CONDITIONAL_AND_EXPRESSION:
        getConditional_and_expression().clear();
        getConditional_and_expression().addAll((Collection<? extends conditional_and_expression>)newValue);
        return;
      case MdlPackage.CONDITIONAL_OR_EXPRESSION__OPERATOR:
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
      case MdlPackage.CONDITIONAL_OR_EXPRESSION__CONDITIONAL_AND_EXPRESSION:
        getConditional_and_expression().clear();
        return;
      case MdlPackage.CONDITIONAL_OR_EXPRESSION__OPERATOR:
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
      case MdlPackage.CONDITIONAL_OR_EXPRESSION__CONDITIONAL_AND_EXPRESSION:
        return conditional_and_expression != null && !conditional_and_expression.isEmpty();
      case MdlPackage.CONDITIONAL_OR_EXPRESSION__OPERATOR:
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
    result.append(" (operator: ");
    result.append(operator);
    result.append(')');
    return result.toString();
  }

} //conditional_or_expressionImpl
