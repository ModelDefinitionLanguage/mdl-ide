/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.formal_arguments;
import org.ddmore.mdl.mdl.function_body;
import org.ddmore.mdl.mdl.function_declaration;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>function declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.function_declarationImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.function_declarationImpl#getFormal_arguments <em>Formal arguments</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.function_declarationImpl#getFunction_body <em>Function body</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class function_declarationImpl extends MinimalEObjectImpl.Container implements function_declaration
{
  /**
   * The default value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdentifier()
   * @generated
   * @ordered
   */
  protected static final String IDENTIFIER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdentifier()
   * @generated
   * @ordered
   */
  protected String identifier = IDENTIFIER_EDEFAULT;

  /**
   * The cached value of the '{@link #getFormal_arguments() <em>Formal arguments</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFormal_arguments()
   * @generated
   * @ordered
   */
  protected formal_arguments formal_arguments;

  /**
   * The cached value of the '{@link #getFunction_body() <em>Function body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunction_body()
   * @generated
   * @ordered
   */
  protected function_body function_body;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected function_declarationImpl()
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
    return MdlPackage.Literals.FUNCTION_DECLARATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getIdentifier()
  {
    return identifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIdentifier(String newIdentifier)
  {
    String oldIdentifier = identifier;
    identifier = newIdentifier;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.FUNCTION_DECLARATION__IDENTIFIER, oldIdentifier, identifier));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public formal_arguments getFormal_arguments()
  {
    return formal_arguments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFormal_arguments(formal_arguments newFormal_arguments, NotificationChain msgs)
  {
    formal_arguments oldFormal_arguments = formal_arguments;
    formal_arguments = newFormal_arguments;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.FUNCTION_DECLARATION__FORMAL_ARGUMENTS, oldFormal_arguments, newFormal_arguments);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFormal_arguments(formal_arguments newFormal_arguments)
  {
    if (newFormal_arguments != formal_arguments)
    {
      NotificationChain msgs = null;
      if (formal_arguments != null)
        msgs = ((InternalEObject)formal_arguments).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FUNCTION_DECLARATION__FORMAL_ARGUMENTS, null, msgs);
      if (newFormal_arguments != null)
        msgs = ((InternalEObject)newFormal_arguments).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FUNCTION_DECLARATION__FORMAL_ARGUMENTS, null, msgs);
      msgs = basicSetFormal_arguments(newFormal_arguments, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.FUNCTION_DECLARATION__FORMAL_ARGUMENTS, newFormal_arguments, newFormal_arguments));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public function_body getFunction_body()
  {
    return function_body;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFunction_body(function_body newFunction_body, NotificationChain msgs)
  {
    function_body oldFunction_body = function_body;
    function_body = newFunction_body;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.FUNCTION_DECLARATION__FUNCTION_BODY, oldFunction_body, newFunction_body);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFunction_body(function_body newFunction_body)
  {
    if (newFunction_body != function_body)
    {
      NotificationChain msgs = null;
      if (function_body != null)
        msgs = ((InternalEObject)function_body).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FUNCTION_DECLARATION__FUNCTION_BODY, null, msgs);
      if (newFunction_body != null)
        msgs = ((InternalEObject)newFunction_body).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FUNCTION_DECLARATION__FUNCTION_BODY, null, msgs);
      msgs = basicSetFunction_body(newFunction_body, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.FUNCTION_DECLARATION__FUNCTION_BODY, newFunction_body, newFunction_body));
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
      case MdlPackage.FUNCTION_DECLARATION__FORMAL_ARGUMENTS:
        return basicSetFormal_arguments(null, msgs);
      case MdlPackage.FUNCTION_DECLARATION__FUNCTION_BODY:
        return basicSetFunction_body(null, msgs);
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
      case MdlPackage.FUNCTION_DECLARATION__IDENTIFIER:
        return getIdentifier();
      case MdlPackage.FUNCTION_DECLARATION__FORMAL_ARGUMENTS:
        return getFormal_arguments();
      case MdlPackage.FUNCTION_DECLARATION__FUNCTION_BODY:
        return getFunction_body();
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
      case MdlPackage.FUNCTION_DECLARATION__IDENTIFIER:
        setIdentifier((String)newValue);
        return;
      case MdlPackage.FUNCTION_DECLARATION__FORMAL_ARGUMENTS:
        setFormal_arguments((formal_arguments)newValue);
        return;
      case MdlPackage.FUNCTION_DECLARATION__FUNCTION_BODY:
        setFunction_body((function_body)newValue);
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
      case MdlPackage.FUNCTION_DECLARATION__IDENTIFIER:
        setIdentifier(IDENTIFIER_EDEFAULT);
        return;
      case MdlPackage.FUNCTION_DECLARATION__FORMAL_ARGUMENTS:
        setFormal_arguments((formal_arguments)null);
        return;
      case MdlPackage.FUNCTION_DECLARATION__FUNCTION_BODY:
        setFunction_body((function_body)null);
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
      case MdlPackage.FUNCTION_DECLARATION__IDENTIFIER:
        return IDENTIFIER_EDEFAULT == null ? identifier != null : !IDENTIFIER_EDEFAULT.equals(identifier);
      case MdlPackage.FUNCTION_DECLARATION__FORMAL_ARGUMENTS:
        return formal_arguments != null;
      case MdlPackage.FUNCTION_DECLARATION__FUNCTION_BODY:
        return function_body != null;
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
    result.append(" (identifier: ");
    result.append(identifier);
    result.append(')');
    return result.toString();
  }

} //function_declarationImpl
