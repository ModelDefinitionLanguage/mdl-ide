/**
 */
package org.ddmore.mdl.mdl.impl;

import java.util.Collection;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.power_expression;
import org.ddmore.mdl.mdl.unary_expression;

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
 * An implementation of the model object '<em><b>power expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.power_expressionImpl#getUnary_expression <em>Unary expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.power_expressionImpl#getPower_op <em>Power op</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class power_expressionImpl extends MinimalEObjectImpl.Container implements power_expression
{
  /**
   * The cached value of the '{@link #getUnary_expression() <em>Unary expression</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnary_expression()
   * @generated
   * @ordered
   */
  protected EList<unary_expression> unary_expression;

  /**
   * The cached value of the '{@link #getPower_op() <em>Power op</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPower_op()
   * @generated
   * @ordered
   */
  protected EList<String> power_op;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected power_expressionImpl()
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
    return MdlPackage.Literals.POWER_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<unary_expression> getUnary_expression()
  {
    if (unary_expression == null)
    {
      unary_expression = new EObjectContainmentEList<unary_expression>(unary_expression.class, this, MdlPackage.POWER_EXPRESSION__UNARY_EXPRESSION);
    }
    return unary_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getPower_op()
  {
    if (power_op == null)
    {
      power_op = new EDataTypeEList<String>(String.class, this, MdlPackage.POWER_EXPRESSION__POWER_OP);
    }
    return power_op;
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
      case MdlPackage.POWER_EXPRESSION__UNARY_EXPRESSION:
        return ((InternalEList<?>)getUnary_expression()).basicRemove(otherEnd, msgs);
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
      case MdlPackage.POWER_EXPRESSION__UNARY_EXPRESSION:
        return getUnary_expression();
      case MdlPackage.POWER_EXPRESSION__POWER_OP:
        return getPower_op();
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
      case MdlPackage.POWER_EXPRESSION__UNARY_EXPRESSION:
        getUnary_expression().clear();
        getUnary_expression().addAll((Collection<? extends unary_expression>)newValue);
        return;
      case MdlPackage.POWER_EXPRESSION__POWER_OP:
        getPower_op().clear();
        getPower_op().addAll((Collection<? extends String>)newValue);
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
      case MdlPackage.POWER_EXPRESSION__UNARY_EXPRESSION:
        getUnary_expression().clear();
        return;
      case MdlPackage.POWER_EXPRESSION__POWER_OP:
        getPower_op().clear();
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
      case MdlPackage.POWER_EXPRESSION__UNARY_EXPRESSION:
        return unary_expression != null && !unary_expression.isEmpty();
      case MdlPackage.POWER_EXPRESSION__POWER_OP:
        return power_op != null && !power_op.isEmpty();
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
    result.append(" (power_op: ");
    result.append(power_op);
    result.append(')');
    return result.toString();
  }

} //power_expressionImpl
