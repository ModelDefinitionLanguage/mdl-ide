/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.any_expression;
import org.ddmore.mdl.mdl.random_list;
import org.ddmore.mdl.mdl.variable_declaration;
import org.ddmore.mdl.mdl.variable_name;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>variable declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.variable_declarationImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.variable_declarationImpl#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.variable_declarationImpl#getRandom_list <em>Random list</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class variable_declarationImpl extends MinimalEObjectImpl.Container implements variable_declaration
{
  /**
   * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdentifier()
   * @generated
   * @ordered
   */
  protected variable_name identifier;

  /**
   * The cached value of the '{@link #getExpression() <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExpression()
   * @generated
   * @ordered
   */
  protected any_expression expression;

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
  protected variable_declarationImpl()
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
    return MdlPackage.Literals.VARIABLE_DECLARATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public variable_name getIdentifier()
  {
    return identifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIdentifier(variable_name newIdentifier, NotificationChain msgs)
  {
    variable_name oldIdentifier = identifier;
    identifier = newIdentifier;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABLE_DECLARATION__IDENTIFIER, oldIdentifier, newIdentifier);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIdentifier(variable_name newIdentifier)
  {
    if (newIdentifier != identifier)
    {
      NotificationChain msgs = null;
      if (identifier != null)
        msgs = ((InternalEObject)identifier).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABLE_DECLARATION__IDENTIFIER, null, msgs);
      if (newIdentifier != null)
        msgs = ((InternalEObject)newIdentifier).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABLE_DECLARATION__IDENTIFIER, null, msgs);
      msgs = basicSetIdentifier(newIdentifier, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABLE_DECLARATION__IDENTIFIER, newIdentifier, newIdentifier));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public any_expression getExpression()
  {
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetExpression(any_expression newExpression, NotificationChain msgs)
  {
    any_expression oldExpression = expression;
    expression = newExpression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABLE_DECLARATION__EXPRESSION, oldExpression, newExpression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExpression(any_expression newExpression)
  {
    if (newExpression != expression)
    {
      NotificationChain msgs = null;
      if (expression != null)
        msgs = ((InternalEObject)expression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABLE_DECLARATION__EXPRESSION, null, msgs);
      if (newExpression != null)
        msgs = ((InternalEObject)newExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABLE_DECLARATION__EXPRESSION, null, msgs);
      msgs = basicSetExpression(newExpression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABLE_DECLARATION__EXPRESSION, newExpression, newExpression));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABLE_DECLARATION__RANDOM_LIST, oldRandom_list, newRandom_list);
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
        msgs = ((InternalEObject)random_list).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABLE_DECLARATION__RANDOM_LIST, null, msgs);
      if (newRandom_list != null)
        msgs = ((InternalEObject)newRandom_list).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABLE_DECLARATION__RANDOM_LIST, null, msgs);
      msgs = basicSetRandom_list(newRandom_list, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABLE_DECLARATION__RANDOM_LIST, newRandom_list, newRandom_list));
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
      case MdlPackage.VARIABLE_DECLARATION__IDENTIFIER:
        return basicSetIdentifier(null, msgs);
      case MdlPackage.VARIABLE_DECLARATION__EXPRESSION:
        return basicSetExpression(null, msgs);
      case MdlPackage.VARIABLE_DECLARATION__RANDOM_LIST:
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
      case MdlPackage.VARIABLE_DECLARATION__IDENTIFIER:
        return getIdentifier();
      case MdlPackage.VARIABLE_DECLARATION__EXPRESSION:
        return getExpression();
      case MdlPackage.VARIABLE_DECLARATION__RANDOM_LIST:
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
      case MdlPackage.VARIABLE_DECLARATION__IDENTIFIER:
        setIdentifier((variable_name)newValue);
        return;
      case MdlPackage.VARIABLE_DECLARATION__EXPRESSION:
        setExpression((any_expression)newValue);
        return;
      case MdlPackage.VARIABLE_DECLARATION__RANDOM_LIST:
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
      case MdlPackage.VARIABLE_DECLARATION__IDENTIFIER:
        setIdentifier((variable_name)null);
        return;
      case MdlPackage.VARIABLE_DECLARATION__EXPRESSION:
        setExpression((any_expression)null);
        return;
      case MdlPackage.VARIABLE_DECLARATION__RANDOM_LIST:
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
      case MdlPackage.VARIABLE_DECLARATION__IDENTIFIER:
        return identifier != null;
      case MdlPackage.VARIABLE_DECLARATION__EXPRESSION:
        return expression != null;
      case MdlPackage.VARIABLE_DECLARATION__RANDOM_LIST:
        return random_list != null;
    }
    return super.eIsSet(featureID);
  }

} //variable_declarationImpl
