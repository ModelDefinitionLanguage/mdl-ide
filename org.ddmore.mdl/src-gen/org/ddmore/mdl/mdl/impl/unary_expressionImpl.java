/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.function_call;
import org.ddmore.mdl.mdl.par_expression;
import org.ddmore.mdl.mdl.primary;
import org.ddmore.mdl.mdl.unary_expression;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>unary expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.unary_expressionImpl#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.unary_expressionImpl#getUnary_expression <em>Unary expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.unary_expressionImpl#getPar_expression <em>Par expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.unary_expressionImpl#getFunction_call <em>Function call</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.unary_expressionImpl#getPrimary <em>Primary</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class unary_expressionImpl extends MinimalEObjectImpl.Container implements unary_expression
{
  /**
   * The default value of the '{@link #getOperator() <em>Operator</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOperator()
   * @generated
   * @ordered
   */
  protected static final String OPERATOR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOperator() <em>Operator</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOperator()
   * @generated
   * @ordered
   */
  protected String operator = OPERATOR_EDEFAULT;

  /**
   * The cached value of the '{@link #getUnary_expression() <em>Unary expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnary_expression()
   * @generated
   * @ordered
   */
  protected unary_expression unary_expression;

  /**
   * The cached value of the '{@link #getPar_expression() <em>Par expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPar_expression()
   * @generated
   * @ordered
   */
  protected par_expression par_expression;

  /**
   * The cached value of the '{@link #getFunction_call() <em>Function call</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunction_call()
   * @generated
   * @ordered
   */
  protected function_call function_call;

  /**
   * The cached value of the '{@link #getPrimary() <em>Primary</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPrimary()
   * @generated
   * @ordered
   */
  protected primary primary;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected unary_expressionImpl()
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
    return MdlPackage.Literals.UNARY_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getOperator()
  {
    return operator;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOperator(String newOperator)
  {
    String oldOperator = operator;
    operator = newOperator;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.UNARY_EXPRESSION__OPERATOR, oldOperator, operator));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public unary_expression getUnary_expression()
  {
    return unary_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUnary_expression(unary_expression newUnary_expression, NotificationChain msgs)
  {
    unary_expression oldUnary_expression = unary_expression;
    unary_expression = newUnary_expression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.UNARY_EXPRESSION__UNARY_EXPRESSION, oldUnary_expression, newUnary_expression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnary_expression(unary_expression newUnary_expression)
  {
    if (newUnary_expression != unary_expression)
    {
      NotificationChain msgs = null;
      if (unary_expression != null)
        msgs = ((InternalEObject)unary_expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.UNARY_EXPRESSION__UNARY_EXPRESSION, null, msgs);
      if (newUnary_expression != null)
        msgs = ((InternalEObject)newUnary_expression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.UNARY_EXPRESSION__UNARY_EXPRESSION, null, msgs);
      msgs = basicSetUnary_expression(newUnary_expression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.UNARY_EXPRESSION__UNARY_EXPRESSION, newUnary_expression, newUnary_expression));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public par_expression getPar_expression()
  {
    return par_expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPar_expression(par_expression newPar_expression, NotificationChain msgs)
  {
    par_expression oldPar_expression = par_expression;
    par_expression = newPar_expression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.UNARY_EXPRESSION__PAR_EXPRESSION, oldPar_expression, newPar_expression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPar_expression(par_expression newPar_expression)
  {
    if (newPar_expression != par_expression)
    {
      NotificationChain msgs = null;
      if (par_expression != null)
        msgs = ((InternalEObject)par_expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.UNARY_EXPRESSION__PAR_EXPRESSION, null, msgs);
      if (newPar_expression != null)
        msgs = ((InternalEObject)newPar_expression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.UNARY_EXPRESSION__PAR_EXPRESSION, null, msgs);
      msgs = basicSetPar_expression(newPar_expression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.UNARY_EXPRESSION__PAR_EXPRESSION, newPar_expression, newPar_expression));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public function_call getFunction_call()
  {
    return function_call;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFunction_call(function_call newFunction_call, NotificationChain msgs)
  {
    function_call oldFunction_call = function_call;
    function_call = newFunction_call;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.UNARY_EXPRESSION__FUNCTION_CALL, oldFunction_call, newFunction_call);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFunction_call(function_call newFunction_call)
  {
    if (newFunction_call != function_call)
    {
      NotificationChain msgs = null;
      if (function_call != null)
        msgs = ((InternalEObject)function_call).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.UNARY_EXPRESSION__FUNCTION_CALL, null, msgs);
      if (newFunction_call != null)
        msgs = ((InternalEObject)newFunction_call).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.UNARY_EXPRESSION__FUNCTION_CALL, null, msgs);
      msgs = basicSetFunction_call(newFunction_call, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.UNARY_EXPRESSION__FUNCTION_CALL, newFunction_call, newFunction_call));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public primary getPrimary()
  {
    return primary;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPrimary(primary newPrimary, NotificationChain msgs)
  {
    primary oldPrimary = primary;
    primary = newPrimary;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.UNARY_EXPRESSION__PRIMARY, oldPrimary, newPrimary);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPrimary(primary newPrimary)
  {
    if (newPrimary != primary)
    {
      NotificationChain msgs = null;
      if (primary != null)
        msgs = ((InternalEObject)primary).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.UNARY_EXPRESSION__PRIMARY, null, msgs);
      if (newPrimary != null)
        msgs = ((InternalEObject)newPrimary).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.UNARY_EXPRESSION__PRIMARY, null, msgs);
      msgs = basicSetPrimary(newPrimary, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.UNARY_EXPRESSION__PRIMARY, newPrimary, newPrimary));
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
      case MdlPackage.UNARY_EXPRESSION__UNARY_EXPRESSION:
        return basicSetUnary_expression(null, msgs);
      case MdlPackage.UNARY_EXPRESSION__PAR_EXPRESSION:
        return basicSetPar_expression(null, msgs);
      case MdlPackage.UNARY_EXPRESSION__FUNCTION_CALL:
        return basicSetFunction_call(null, msgs);
      case MdlPackage.UNARY_EXPRESSION__PRIMARY:
        return basicSetPrimary(null, msgs);
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
      case MdlPackage.UNARY_EXPRESSION__OPERATOR:
        return getOperator();
      case MdlPackage.UNARY_EXPRESSION__UNARY_EXPRESSION:
        return getUnary_expression();
      case MdlPackage.UNARY_EXPRESSION__PAR_EXPRESSION:
        return getPar_expression();
      case MdlPackage.UNARY_EXPRESSION__FUNCTION_CALL:
        return getFunction_call();
      case MdlPackage.UNARY_EXPRESSION__PRIMARY:
        return getPrimary();
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
      case MdlPackage.UNARY_EXPRESSION__OPERATOR:
        setOperator((String)newValue);
        return;
      case MdlPackage.UNARY_EXPRESSION__UNARY_EXPRESSION:
        setUnary_expression((unary_expression)newValue);
        return;
      case MdlPackage.UNARY_EXPRESSION__PAR_EXPRESSION:
        setPar_expression((par_expression)newValue);
        return;
      case MdlPackage.UNARY_EXPRESSION__FUNCTION_CALL:
        setFunction_call((function_call)newValue);
        return;
      case MdlPackage.UNARY_EXPRESSION__PRIMARY:
        setPrimary((primary)newValue);
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
      case MdlPackage.UNARY_EXPRESSION__OPERATOR:
        setOperator(OPERATOR_EDEFAULT);
        return;
      case MdlPackage.UNARY_EXPRESSION__UNARY_EXPRESSION:
        setUnary_expression((unary_expression)null);
        return;
      case MdlPackage.UNARY_EXPRESSION__PAR_EXPRESSION:
        setPar_expression((par_expression)null);
        return;
      case MdlPackage.UNARY_EXPRESSION__FUNCTION_CALL:
        setFunction_call((function_call)null);
        return;
      case MdlPackage.UNARY_EXPRESSION__PRIMARY:
        setPrimary((primary)null);
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
      case MdlPackage.UNARY_EXPRESSION__OPERATOR:
        return OPERATOR_EDEFAULT == null ? operator != null : !OPERATOR_EDEFAULT.equals(operator);
      case MdlPackage.UNARY_EXPRESSION__UNARY_EXPRESSION:
        return unary_expression != null;
      case MdlPackage.UNARY_EXPRESSION__PAR_EXPRESSION:
        return par_expression != null;
      case MdlPackage.UNARY_EXPRESSION__FUNCTION_CALL:
        return function_call != null;
      case MdlPackage.UNARY_EXPRESSION__PRIMARY:
        return primary != null;
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

} //unary_expressionImpl
