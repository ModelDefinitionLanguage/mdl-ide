/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.target_block;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>target block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.target_blockImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.target_blockImpl#getExternal_code <em>External code</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class target_blockImpl extends MinimalEObjectImpl.Container implements target_block
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
   * The default value of the '{@link #getExternal_code() <em>External code</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExternal_code()
   * @generated
   * @ordered
   */
  protected static final String EXTERNAL_CODE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getExternal_code() <em>External code</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExternal_code()
   * @generated
   * @ordered
   */
  protected String external_code = EXTERNAL_CODE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected target_blockImpl()
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
    return MdlPackage.Literals.TARGET_BLOCK;
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
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TARGET_BLOCK__IDENTIFIER, oldIdentifier, identifier));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getExternal_code()
  {
    return external_code;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExternal_code(String newExternal_code)
  {
    String oldExternal_code = external_code;
    external_code = newExternal_code;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TARGET_BLOCK__EXTERNAL_CODE, oldExternal_code, external_code));
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
      case MdlPackage.TARGET_BLOCK__IDENTIFIER:
        return getIdentifier();
      case MdlPackage.TARGET_BLOCK__EXTERNAL_CODE:
        return getExternal_code();
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
      case MdlPackage.TARGET_BLOCK__IDENTIFIER:
        setIdentifier((String)newValue);
        return;
      case MdlPackage.TARGET_BLOCK__EXTERNAL_CODE:
        setExternal_code((String)newValue);
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
      case MdlPackage.TARGET_BLOCK__IDENTIFIER:
        setIdentifier(IDENTIFIER_EDEFAULT);
        return;
      case MdlPackage.TARGET_BLOCK__EXTERNAL_CODE:
        setExternal_code(EXTERNAL_CODE_EDEFAULT);
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
      case MdlPackage.TARGET_BLOCK__IDENTIFIER:
        return IDENTIFIER_EDEFAULT == null ? identifier != null : !IDENTIFIER_EDEFAULT.equals(identifier);
      case MdlPackage.TARGET_BLOCK__EXTERNAL_CODE:
        return EXTERNAL_CODE_EDEFAULT == null ? external_code != null : !EXTERNAL_CODE_EDEFAULT.equals(external_code);
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
    result.append(", external_code: ");
    result.append(external_code);
    result.append(')');
    return result.toString();
  }

} //target_blockImpl
