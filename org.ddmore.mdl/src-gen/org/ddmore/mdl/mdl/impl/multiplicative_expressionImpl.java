/**
 */
package org.ddmore.mdl.mdl.impl;

import java.util.Collection;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.multiplicative_expression;
import org.ddmore.mdl.mdl.power_expression;

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
 * An implementation of the model object '<em><b>multiplicative expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.multiplicative_expressionImpl#getPower_expression <em>Power expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.multiplicative_expressionImpl#getMultiplicative_op <em>Multiplicative op</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class multiplicative_expressionImpl extends MinimalEObjectImpl.Container implements multiplicative_expression
{
  /**
   * The cached value of the '{@link #getPower_expression() <em>Power expression</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPower_expression()
   * @generated
   * @ordered
   */
  protected EList<power_expression> power_expression;

  /**
   * The cached value of the '{@link #getMultiplicative_op() <em>Multiplicative op</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMultiplicative_op()
   * @generated
   * @ordered
   */
  protected EList<String> multiplicative_op;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected multiplicative_expressionImpl()
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
    return MdlPackage.Literals.MULTIPLICATIVE_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<power_expression> getPower_expression()
  {
    if (power_expression == null)
    {
      power_expression = new EObjectContainmentEList<power_expression>(power_expression.class, this, MdlPackage.MULTIPLICATIVE_EXPRESSION__POWER_EXPRESSION);
    }
    return power_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getMultiplicative_op()
  {
    if (multiplicative_op == null)
    {
      multiplicative_op = new EDataTypeEList<String>(String.class, this, MdlPackage.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OP);
    }
    return multiplicative_op;
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
      case MdlPackage.MULTIPLICATIVE_EXPRESSION__POWER_EXPRESSION:
        return ((InternalEList<?>)getPower_expression()).basicRemove(otherEnd, msgs);
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
      case MdlPackage.MULTIPLICATIVE_EXPRESSION__POWER_EXPRESSION:
        return getPower_expression();
      case MdlPackage.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OP:
        return getMultiplicative_op();
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
      case MdlPackage.MULTIPLICATIVE_EXPRESSION__POWER_EXPRESSION:
        getPower_expression().clear();
        getPower_expression().addAll((Collection<? extends power_expression>)newValue);
        return;
      case MdlPackage.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OP:
        getMultiplicative_op().clear();
        getMultiplicative_op().addAll((Collection<? extends String>)newValue);
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
      case MdlPackage.MULTIPLICATIVE_EXPRESSION__POWER_EXPRESSION:
        getPower_expression().clear();
        return;
      case MdlPackage.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OP:
        getMultiplicative_op().clear();
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
      case MdlPackage.MULTIPLICATIVE_EXPRESSION__POWER_EXPRESSION:
        return power_expression != null && !power_expression.isEmpty();
      case MdlPackage.MULTIPLICATIVE_EXPRESSION__MULTIPLICATIVE_OP:
        return multiplicative_op != null && !multiplicative_op.isEmpty();
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
    result.append(" (multiplicative_op: ");
    result.append(multiplicative_op);
    result.append(')');
    return result.toString();
  }

} //multiplicative_expressionImpl
