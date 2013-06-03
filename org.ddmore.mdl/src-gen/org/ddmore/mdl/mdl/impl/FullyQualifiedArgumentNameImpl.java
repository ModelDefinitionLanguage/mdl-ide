/**
 */
package org.ddmore.mdl.mdl.impl;

import java.util.Collection;

import org.ddmore.mdl.mdl.FullyQualifiedArgumentName;
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.Selector;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Fully Qualified Argument Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.FullyQualifiedArgumentNameImpl#getParent <em>Parent</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.FullyQualifiedArgumentNameImpl#getSelectors <em>Selectors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FullyQualifiedArgumentNameImpl extends MinimalEObjectImpl.Container implements FullyQualifiedArgumentName
{
  /**
   * The cached value of the '{@link #getParent() <em>Parent</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParent()
   * @generated
   * @ordered
   */
  protected FullyQualifiedSymbolName parent;

  /**
   * The cached value of the '{@link #getSelectors() <em>Selectors</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSelectors()
   * @generated
   * @ordered
   */
  protected EList<Selector> selectors;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FullyQualifiedArgumentNameImpl()
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
    return MdlPackage.Literals.FULLY_QUALIFIED_ARGUMENT_NAME;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FullyQualifiedSymbolName getParent()
  {
    return parent;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParent(FullyQualifiedSymbolName newParent, NotificationChain msgs)
  {
    FullyQualifiedSymbolName oldParent = parent;
    parent = newParent;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME__PARENT, oldParent, newParent);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParent(FullyQualifiedSymbolName newParent)
  {
    if (newParent != parent)
    {
      NotificationChain msgs = null;
      if (parent != null)
        msgs = ((InternalEObject)parent).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME__PARENT, null, msgs);
      if (newParent != null)
        msgs = ((InternalEObject)newParent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME__PARENT, null, msgs);
      msgs = basicSetParent(newParent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME__PARENT, newParent, newParent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Selector> getSelectors()
  {
    if (selectors == null)
    {
      selectors = new EObjectContainmentEList<Selector>(Selector.class, this, MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS);
    }
    return selectors;
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
      case MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME__PARENT:
        return basicSetParent(null, msgs);
      case MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS:
        return ((InternalEList<?>)getSelectors()).basicRemove(otherEnd, msgs);
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
      case MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME__PARENT:
        return getParent();
      case MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS:
        return getSelectors();
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
      case MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME__PARENT:
        setParent((FullyQualifiedSymbolName)newValue);
        return;
      case MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS:
        getSelectors().clear();
        getSelectors().addAll((Collection<? extends Selector>)newValue);
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
      case MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME__PARENT:
        setParent((FullyQualifiedSymbolName)null);
        return;
      case MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS:
        getSelectors().clear();
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
      case MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME__PARENT:
        return parent != null;
      case MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME__SELECTORS:
        return selectors != null && !selectors.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //FullyQualifiedArgumentNameImpl
