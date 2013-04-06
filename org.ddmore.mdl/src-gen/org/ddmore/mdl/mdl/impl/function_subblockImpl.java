/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.block;
import org.ddmore.mdl.mdl.function_subblock;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>function subblock</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.function_subblockImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.function_subblockImpl#getEstimate_defn <em>Estimate defn</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.function_subblockImpl#getSimulate_defn <em>Simulate defn</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class function_subblockImpl extends MinimalEObjectImpl.Container implements function_subblock
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
   * The cached value of the '{@link #getEstimate_defn() <em>Estimate defn</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEstimate_defn()
   * @generated
   * @ordered
   */
  protected block estimate_defn;

  /**
   * The cached value of the '{@link #getSimulate_defn() <em>Simulate defn</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSimulate_defn()
   * @generated
   * @ordered
   */
  protected block simulate_defn;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected function_subblockImpl()
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
    return MdlPackage.Literals.FUNCTION_SUBBLOCK;
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
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.FUNCTION_SUBBLOCK__IDENTIFIER, oldIdentifier, identifier));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public block getEstimate_defn()
  {
    return estimate_defn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEstimate_defn(block newEstimate_defn, NotificationChain msgs)
  {
    block oldEstimate_defn = estimate_defn;
    estimate_defn = newEstimate_defn;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.FUNCTION_SUBBLOCK__ESTIMATE_DEFN, oldEstimate_defn, newEstimate_defn);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEstimate_defn(block newEstimate_defn)
  {
    if (newEstimate_defn != estimate_defn)
    {
      NotificationChain msgs = null;
      if (estimate_defn != null)
        msgs = ((InternalEObject)estimate_defn).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FUNCTION_SUBBLOCK__ESTIMATE_DEFN, null, msgs);
      if (newEstimate_defn != null)
        msgs = ((InternalEObject)newEstimate_defn).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FUNCTION_SUBBLOCK__ESTIMATE_DEFN, null, msgs);
      msgs = basicSetEstimate_defn(newEstimate_defn, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.FUNCTION_SUBBLOCK__ESTIMATE_DEFN, newEstimate_defn, newEstimate_defn));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public block getSimulate_defn()
  {
    return simulate_defn;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSimulate_defn(block newSimulate_defn, NotificationChain msgs)
  {
    block oldSimulate_defn = simulate_defn;
    simulate_defn = newSimulate_defn;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.FUNCTION_SUBBLOCK__SIMULATE_DEFN, oldSimulate_defn, newSimulate_defn);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSimulate_defn(block newSimulate_defn)
  {
    if (newSimulate_defn != simulate_defn)
    {
      NotificationChain msgs = null;
      if (simulate_defn != null)
        msgs = ((InternalEObject)simulate_defn).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FUNCTION_SUBBLOCK__SIMULATE_DEFN, null, msgs);
      if (newSimulate_defn != null)
        msgs = ((InternalEObject)newSimulate_defn).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FUNCTION_SUBBLOCK__SIMULATE_DEFN, null, msgs);
      msgs = basicSetSimulate_defn(newSimulate_defn, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.FUNCTION_SUBBLOCK__SIMULATE_DEFN, newSimulate_defn, newSimulate_defn));
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
      case MdlPackage.FUNCTION_SUBBLOCK__ESTIMATE_DEFN:
        return basicSetEstimate_defn(null, msgs);
      case MdlPackage.FUNCTION_SUBBLOCK__SIMULATE_DEFN:
        return basicSetSimulate_defn(null, msgs);
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
      case MdlPackage.FUNCTION_SUBBLOCK__IDENTIFIER:
        return getIdentifier();
      case MdlPackage.FUNCTION_SUBBLOCK__ESTIMATE_DEFN:
        return getEstimate_defn();
      case MdlPackage.FUNCTION_SUBBLOCK__SIMULATE_DEFN:
        return getSimulate_defn();
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
      case MdlPackage.FUNCTION_SUBBLOCK__IDENTIFIER:
        setIdentifier((String)newValue);
        return;
      case MdlPackage.FUNCTION_SUBBLOCK__ESTIMATE_DEFN:
        setEstimate_defn((block)newValue);
        return;
      case MdlPackage.FUNCTION_SUBBLOCK__SIMULATE_DEFN:
        setSimulate_defn((block)newValue);
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
      case MdlPackage.FUNCTION_SUBBLOCK__IDENTIFIER:
        setIdentifier(IDENTIFIER_EDEFAULT);
        return;
      case MdlPackage.FUNCTION_SUBBLOCK__ESTIMATE_DEFN:
        setEstimate_defn((block)null);
        return;
      case MdlPackage.FUNCTION_SUBBLOCK__SIMULATE_DEFN:
        setSimulate_defn((block)null);
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
      case MdlPackage.FUNCTION_SUBBLOCK__IDENTIFIER:
        return IDENTIFIER_EDEFAULT == null ? identifier != null : !IDENTIFIER_EDEFAULT.equals(identifier);
      case MdlPackage.FUNCTION_SUBBLOCK__ESTIMATE_DEFN:
        return estimate_defn != null;
      case MdlPackage.FUNCTION_SUBBLOCK__SIMULATE_DEFN:
        return simulate_defn != null;
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

} //function_subblockImpl
