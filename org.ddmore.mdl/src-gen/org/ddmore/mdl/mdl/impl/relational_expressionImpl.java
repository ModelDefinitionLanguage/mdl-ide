/**
 */
package org.ddmore.mdl.mdl.impl;

import java.util.Collection;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.additive_expression;
import org.ddmore.mdl.mdl.relational_expression;

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
 * An implementation of the model object '<em><b>relational expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.relational_expressionImpl#getNegation <em>Negation</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.relational_expressionImpl#getBoolean <em>Boolean</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.relational_expressionImpl#getAdditive_expression <em>Additive expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.relational_expressionImpl#getRelational_op <em>Relational op</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class relational_expressionImpl extends MinimalEObjectImpl.Container implements relational_expression
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
   * The cached value of the '{@link #getAdditive_expression() <em>Additive expression</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAdditive_expression()
   * @generated
   * @ordered
   */
  protected EList<additive_expression> additive_expression;

  /**
   * The cached value of the '{@link #getRelational_op() <em>Relational op</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRelational_op()
   * @generated
   * @ordered
   */
  protected EList<String> relational_op;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected relational_expressionImpl()
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
    return MdlPackage.Literals.RELATIONAL_EXPRESSION;
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
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.RELATIONAL_EXPRESSION__NEGATION, oldNegation, negation));
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
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.RELATIONAL_EXPRESSION__BOOLEAN, oldBoolean, boolean_));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<additive_expression> getAdditive_expression()
  {
    if (additive_expression == null)
    {
      additive_expression = new EObjectContainmentEList<additive_expression>(additive_expression.class, this, MdlPackage.RELATIONAL_EXPRESSION__ADDITIVE_EXPRESSION);
    }
    return additive_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getRelational_op()
  {
    if (relational_op == null)
    {
      relational_op = new EDataTypeEList<String>(String.class, this, MdlPackage.RELATIONAL_EXPRESSION__RELATIONAL_OP);
    }
    return relational_op;
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
      case MdlPackage.RELATIONAL_EXPRESSION__ADDITIVE_EXPRESSION:
        return ((InternalEList<?>)getAdditive_expression()).basicRemove(otherEnd, msgs);
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
      case MdlPackage.RELATIONAL_EXPRESSION__NEGATION:
        return getNegation();
      case MdlPackage.RELATIONAL_EXPRESSION__BOOLEAN:
        return getBoolean();
      case MdlPackage.RELATIONAL_EXPRESSION__ADDITIVE_EXPRESSION:
        return getAdditive_expression();
      case MdlPackage.RELATIONAL_EXPRESSION__RELATIONAL_OP:
        return getRelational_op();
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
      case MdlPackage.RELATIONAL_EXPRESSION__NEGATION:
        setNegation((String)newValue);
        return;
      case MdlPackage.RELATIONAL_EXPRESSION__BOOLEAN:
        setBoolean((String)newValue);
        return;
      case MdlPackage.RELATIONAL_EXPRESSION__ADDITIVE_EXPRESSION:
        getAdditive_expression().clear();
        getAdditive_expression().addAll((Collection<? extends additive_expression>)newValue);
        return;
      case MdlPackage.RELATIONAL_EXPRESSION__RELATIONAL_OP:
        getRelational_op().clear();
        getRelational_op().addAll((Collection<? extends String>)newValue);
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
      case MdlPackage.RELATIONAL_EXPRESSION__NEGATION:
        setNegation(NEGATION_EDEFAULT);
        return;
      case MdlPackage.RELATIONAL_EXPRESSION__BOOLEAN:
        setBoolean(BOOLEAN_EDEFAULT);
        return;
      case MdlPackage.RELATIONAL_EXPRESSION__ADDITIVE_EXPRESSION:
        getAdditive_expression().clear();
        return;
      case MdlPackage.RELATIONAL_EXPRESSION__RELATIONAL_OP:
        getRelational_op().clear();
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
      case MdlPackage.RELATIONAL_EXPRESSION__NEGATION:
        return NEGATION_EDEFAULT == null ? negation != null : !NEGATION_EDEFAULT.equals(negation);
      case MdlPackage.RELATIONAL_EXPRESSION__BOOLEAN:
        return BOOLEAN_EDEFAULT == null ? boolean_ != null : !BOOLEAN_EDEFAULT.equals(boolean_);
      case MdlPackage.RELATIONAL_EXPRESSION__ADDITIVE_EXPRESSION:
        return additive_expression != null && !additive_expression.isEmpty();
      case MdlPackage.RELATIONAL_EXPRESSION__RELATIONAL_OP:
        return relational_op != null && !relational_op.isEmpty();
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
    result.append(", relational_op: ");
    result.append(relational_op);
    result.append(')');
    return result.toString();
  }

} //relational_expressionImpl
