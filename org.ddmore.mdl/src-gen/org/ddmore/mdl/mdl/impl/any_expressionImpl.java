/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.any_expression;
import org.ddmore.mdl.mdl.expression;
import org.ddmore.mdl.mdl.list;
import org.ddmore.mdl.mdl.ode_list;
import org.ddmore.mdl.mdl.random_list;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>any expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.any_expressionImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.any_expressionImpl#getList <em>List</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.any_expressionImpl#getOde_list <em>Ode list</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.any_expressionImpl#getRandom_list <em>Random list</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class any_expressionImpl extends MinimalEObjectImpl.Container implements any_expression
{
  /**
   * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpression()
   * @generated
   * @ordered
   */
  protected expression expression;

  /**
   * The cached value of the '{@link #getList() <em>List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getList()
   * @generated
   * @ordered
   */
  protected list list;

  /**
   * The cached value of the '{@link #getOde_list() <em>Ode list</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOde_list()
   * @generated
   * @ordered
   */
  protected ode_list ode_list;

  /**
   * The cached value of the '{@link #getRandom_list() <em>Random list</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRandom_list()
   * @generated
   * @ordered
   */
  protected random_list random_list;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected any_expressionImpl()
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
    return MdlPackage.Literals.ANY_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public expression getExpression()
  {
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpression(expression newExpression, NotificationChain msgs)
  {
    expression oldExpression = expression;
    expression = newExpression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.ANY_EXPRESSION__EXPRESSION, oldExpression, newExpression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpression(expression newExpression)
  {
    if (newExpression != expression)
    {
      NotificationChain msgs = null;
      if (expression != null)
        msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ANY_EXPRESSION__EXPRESSION, null, msgs);
      if (newExpression != null)
        msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ANY_EXPRESSION__EXPRESSION, null, msgs);
      msgs = basicSetExpression(newExpression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.ANY_EXPRESSION__EXPRESSION, newExpression, newExpression));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public list getList()
  {
    return list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetList(list newList, NotificationChain msgs)
  {
    list oldList = list;
    list = newList;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.ANY_EXPRESSION__LIST, oldList, newList);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setList(list newList)
  {
    if (newList != list)
    {
      NotificationChain msgs = null;
      if (list != null)
        msgs = ((InternalEObject)list).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ANY_EXPRESSION__LIST, null, msgs);
      if (newList != null)
        msgs = ((InternalEObject)newList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ANY_EXPRESSION__LIST, null, msgs);
      msgs = basicSetList(newList, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.ANY_EXPRESSION__LIST, newList, newList));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ode_list getOde_list()
  {
    return ode_list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOde_list(ode_list newOde_list, NotificationChain msgs)
  {
    ode_list oldOde_list = ode_list;
    ode_list = newOde_list;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.ANY_EXPRESSION__ODE_LIST, oldOde_list, newOde_list);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOde_list(ode_list newOde_list)
  {
    if (newOde_list != ode_list)
    {
      NotificationChain msgs = null;
      if (ode_list != null)
        msgs = ((InternalEObject)ode_list).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ANY_EXPRESSION__ODE_LIST, null, msgs);
      if (newOde_list != null)
        msgs = ((InternalEObject)newOde_list).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ANY_EXPRESSION__ODE_LIST, null, msgs);
      msgs = basicSetOde_list(newOde_list, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.ANY_EXPRESSION__ODE_LIST, newOde_list, newOde_list));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public random_list getRandom_list()
  {
    return random_list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRandom_list(random_list newRandom_list, NotificationChain msgs)
  {
    random_list oldRandom_list = random_list;
    random_list = newRandom_list;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.ANY_EXPRESSION__RANDOM_LIST, oldRandom_list, newRandom_list);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRandom_list(random_list newRandom_list)
  {
    if (newRandom_list != random_list)
    {
      NotificationChain msgs = null;
      if (random_list != null)
        msgs = ((InternalEObject)random_list).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ANY_EXPRESSION__RANDOM_LIST, null, msgs);
      if (newRandom_list != null)
        msgs = ((InternalEObject)newRandom_list).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ANY_EXPRESSION__RANDOM_LIST, null, msgs);
      msgs = basicSetRandom_list(newRandom_list, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.ANY_EXPRESSION__RANDOM_LIST, newRandom_list, newRandom_list));
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
      case MdlPackage.ANY_EXPRESSION__EXPRESSION:
        return basicSetExpression(null, msgs);
      case MdlPackage.ANY_EXPRESSION__LIST:
        return basicSetList(null, msgs);
      case MdlPackage.ANY_EXPRESSION__ODE_LIST:
        return basicSetOde_list(null, msgs);
      case MdlPackage.ANY_EXPRESSION__RANDOM_LIST:
        return basicSetRandom_list(null, msgs);
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
      case MdlPackage.ANY_EXPRESSION__EXPRESSION:
        return getExpression();
      case MdlPackage.ANY_EXPRESSION__LIST:
        return getList();
      case MdlPackage.ANY_EXPRESSION__ODE_LIST:
        return getOde_list();
      case MdlPackage.ANY_EXPRESSION__RANDOM_LIST:
        return getRandom_list();
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
      case MdlPackage.ANY_EXPRESSION__EXPRESSION:
        setExpression((expression)newValue);
        return;
      case MdlPackage.ANY_EXPRESSION__LIST:
        setList((list)newValue);
        return;
      case MdlPackage.ANY_EXPRESSION__ODE_LIST:
        setOde_list((ode_list)newValue);
        return;
      case MdlPackage.ANY_EXPRESSION__RANDOM_LIST:
        setRandom_list((random_list)newValue);
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
      case MdlPackage.ANY_EXPRESSION__EXPRESSION:
        setExpression((expression)null);
        return;
      case MdlPackage.ANY_EXPRESSION__LIST:
        setList((list)null);
        return;
      case MdlPackage.ANY_EXPRESSION__ODE_LIST:
        setOde_list((ode_list)null);
        return;
      case MdlPackage.ANY_EXPRESSION__RANDOM_LIST:
        setRandom_list((random_list)null);
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
      case MdlPackage.ANY_EXPRESSION__EXPRESSION:
        return expression != null;
      case MdlPackage.ANY_EXPRESSION__LIST:
        return list != null;
      case MdlPackage.ANY_EXPRESSION__ODE_LIST:
        return ode_list != null;
      case MdlPackage.ANY_EXPRESSION__RANDOM_LIST:
        return random_list != null;
    }
    return super.eIsSet(featureID);
  }

} //any_expressionImpl
