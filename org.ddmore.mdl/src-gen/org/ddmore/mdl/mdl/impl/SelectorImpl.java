/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.Selector;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Selector</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.SelectorImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.SelectorImpl#getSelector <em>Selector</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SelectorImpl extends MinimalEObjectImpl.Container implements Selector
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
   * The default value of the '{@link #getSelector() <em>Selector</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSelector()
   * @generated
   * @ordered
   */
  protected static final String SELECTOR_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSelector() <em>Selector</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSelector()
   * @generated
   * @ordered
   */
  protected String selector = SELECTOR_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected SelectorImpl()
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
    return MdlPackage.Literals.SELECTOR;
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
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.SELECTOR__IDENTIFIER, oldIdentifier, identifier));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSelector()
  {
    return selector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSelector(String newSelector)
  {
    String oldSelector = selector;
    selector = newSelector;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.SELECTOR__SELECTOR, oldSelector, selector));
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
      case MdlPackage.SELECTOR__IDENTIFIER:
        return getIdentifier();
      case MdlPackage.SELECTOR__SELECTOR:
        return getSelector();
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
      case MdlPackage.SELECTOR__IDENTIFIER:
        setIdentifier((String)newValue);
        return;
      case MdlPackage.SELECTOR__SELECTOR:
        setSelector((String)newValue);
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
      case MdlPackage.SELECTOR__IDENTIFIER:
        setIdentifier(IDENTIFIER_EDEFAULT);
        return;
      case MdlPackage.SELECTOR__SELECTOR:
        setSelector(SELECTOR_EDEFAULT);
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
      case MdlPackage.SELECTOR__IDENTIFIER:
        return IDENTIFIER_EDEFAULT == null ? identifier != null : !IDENTIFIER_EDEFAULT.equals(identifier);
      case MdlPackage.SELECTOR__SELECTOR:
        return SELECTOR_EDEFAULT == null ? selector != null : !SELECTOR_EDEFAULT.equals(selector);
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
    result.append(", selector: ");
    result.append(selector);
    result.append(')');
    return result.toString();
  }

} //SelectorImpl
