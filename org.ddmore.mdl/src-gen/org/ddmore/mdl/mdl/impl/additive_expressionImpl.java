/**
 */
package org.ddmore.mdl.mdl.impl;

import java.util.Collection;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.additive_expression;
import org.ddmore.mdl.mdl.multiplicative_expression;

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
 * An implementation of the model object '<em><b>additive expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.additive_expressionImpl#getMultiplicative_expression <em>Multiplicative expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.additive_expressionImpl#getAdditive_op <em>Additive op</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class additive_expressionImpl extends MinimalEObjectImpl.Container implements additive_expression
{
  /**
   * The cached value of the '{@link #getMultiplicative_expression() <em>Multiplicative expression</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMultiplicative_expression()
   * @generated
   * @ordered
   */
  protected EList<multiplicative_expression> multiplicative_expression;

  /**
   * The cached value of the '{@link #getAdditive_op() <em>Additive op</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAdditive_op()
   * @generated
   * @ordered
   */
  protected EList<String> additive_op;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected additive_expressionImpl()
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
  public EList<multiplicative_expression> getMultiplicative_expression()
  {
    if (multiplicative_expression == null)
    {
      multiplicative_expression = new EObjectContainmentEList<multiplicative_expression>(multiplicative_expression.class, this, MdlPackage.ADDITIVE_EXPRESSION__MULTIPLICATIVE_EXPRESSION);
    }
    return multiplicative_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getAdditive_op()
  {
    if (additive_op == null)
    {
      additive_op = new EDataTypeEList<String>(String.class, this, MdlPackage.ADDITIVE_EXPRESSION__ADDITIVE_OP);
    }
    return additive_op;
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
      case MdlPackage.ADDITIVE_EXPRESSION__MULTIPLICATIVE_EXPRESSION:
        return ((InternalEList<?>)getMultiplicative_expression()).basicRemove(otherEnd, msgs);
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
      case MdlPackage.ADDITIVE_EXPRESSION__MULTIPLICATIVE_EXPRESSION:
        return getMultiplicative_expression();
      case MdlPackage.ADDITIVE_EXPRESSION__ADDITIVE_OP:
        return getAdditive_op();
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
      case MdlPackage.ADDITIVE_EXPRESSION__MULTIPLICATIVE_EXPRESSION:
        getMultiplicative_expression().clear();
        getMultiplicative_expression().addAll((Collection<? extends multiplicative_expression>)newValue);
        return;
      case MdlPackage.ADDITIVE_EXPRESSION__ADDITIVE_OP:
        getAdditive_op().clear();
        getAdditive_op().addAll((Collection<? extends String>)newValue);
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
      case MdlPackage.ADDITIVE_EXPRESSION__MULTIPLICATIVE_EXPRESSION:
        getMultiplicative_expression().clear();
        return;
      case MdlPackage.ADDITIVE_EXPRESSION__ADDITIVE_OP:
        getAdditive_op().clear();
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
      case MdlPackage.ADDITIVE_EXPRESSION__MULTIPLICATIVE_EXPRESSION:
        return multiplicative_expression != null && !multiplicative_expression.isEmpty();
      case MdlPackage.ADDITIVE_EXPRESSION__ADDITIVE_OP:
        return additive_op != null && !additive_op.isEmpty();
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
    result.append(" (additive_op: ");
    result.append(additive_op);
    result.append(')');
    return result.toString();
  }

} //additive_expressionImpl
