/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.EnumType;
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.List;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.OdeList;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Any Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.AnyExpressionImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.AnyExpressionImpl#getList <em>List</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.AnyExpressionImpl#getOdeList <em>Ode List</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.AnyExpressionImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AnyExpressionImpl extends MinimalEObjectImpl.Container implements AnyExpression
{
  /**
   * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpression()
   * @generated
   * @ordered
   */
  protected Expression expression;

  /**
   * The cached value of the '{@link #getList() <em>List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getList()
   * @generated
   * @ordered
   */
  protected List list;

  /**
   * The cached value of the '{@link #getOdeList() <em>Ode List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOdeList()
   * @generated
   * @ordered
   */
  protected OdeList odeList;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected EnumType type;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AnyExpressionImpl()
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
  public Expression getExpression()
  {
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpression(Expression newExpression, NotificationChain msgs)
  {
    Expression oldExpression = expression;
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
  public void setExpression(Expression newExpression)
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
  public List getList()
  {
    return list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetList(List newList, NotificationChain msgs)
  {
    List oldList = list;
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
  public void setList(List newList)
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
  public OdeList getOdeList()
  {
    return odeList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOdeList(OdeList newOdeList, NotificationChain msgs)
  {
    OdeList oldOdeList = odeList;
    odeList = newOdeList;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.ANY_EXPRESSION__ODE_LIST, oldOdeList, newOdeList);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOdeList(OdeList newOdeList)
  {
    if (newOdeList != odeList)
    {
      NotificationChain msgs = null;
      if (odeList != null)
        msgs = ((InternalEObject)odeList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ANY_EXPRESSION__ODE_LIST, null, msgs);
      if (newOdeList != null)
        msgs = ((InternalEObject)newOdeList).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ANY_EXPRESSION__ODE_LIST, null, msgs);
      msgs = basicSetOdeList(newOdeList, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.ANY_EXPRESSION__ODE_LIST, newOdeList, newOdeList));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EnumType getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetType(EnumType newType, NotificationChain msgs)
  {
    EnumType oldType = type;
    type = newType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.ANY_EXPRESSION__TYPE, oldType, newType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(EnumType newType)
  {
    if (newType != type)
    {
      NotificationChain msgs = null;
      if (type != null)
        msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ANY_EXPRESSION__TYPE, null, msgs);
      if (newType != null)
        msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ANY_EXPRESSION__TYPE, null, msgs);
      msgs = basicSetType(newType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.ANY_EXPRESSION__TYPE, newType, newType));
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
        return basicSetOdeList(null, msgs);
      case MdlPackage.ANY_EXPRESSION__TYPE:
        return basicSetType(null, msgs);
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
        return getOdeList();
      case MdlPackage.ANY_EXPRESSION__TYPE:
        return getType();
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
        setExpression((Expression)newValue);
        return;
      case MdlPackage.ANY_EXPRESSION__LIST:
        setList((List)newValue);
        return;
      case MdlPackage.ANY_EXPRESSION__ODE_LIST:
        setOdeList((OdeList)newValue);
        return;
      case MdlPackage.ANY_EXPRESSION__TYPE:
        setType((EnumType)newValue);
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
        setExpression((Expression)null);
        return;
      case MdlPackage.ANY_EXPRESSION__LIST:
        setList((List)null);
        return;
      case MdlPackage.ANY_EXPRESSION__ODE_LIST:
        setOdeList((OdeList)null);
        return;
      case MdlPackage.ANY_EXPRESSION__TYPE:
        setType((EnumType)null);
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
        return odeList != null;
      case MdlPackage.ANY_EXPRESSION__TYPE:
        return type != null;
    }
    return super.eIsSet(featureID);
  }

} //AnyExpressionImpl
