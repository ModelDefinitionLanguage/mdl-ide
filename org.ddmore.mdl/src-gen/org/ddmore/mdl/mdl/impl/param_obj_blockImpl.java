/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.param_obj_block;
import org.ddmore.mdl.mdl.structural_block;
import org.ddmore.mdl.mdl.variability_block;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>param obj block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.param_obj_blockImpl#getStructural_block <em>Structural block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.param_obj_blockImpl#getVariability_block <em>Variability block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class param_obj_blockImpl extends MinimalEObjectImpl.Container implements param_obj_block
{
  /**
   * The cached value of the '{@link #getStructural_block() <em>Structural block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStructural_block()
   * @generated
   * @ordered
   */
  protected structural_block structural_block;

  /**
   * The cached value of the '{@link #getVariability_block() <em>Variability block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariability_block()
   * @generated
   * @ordered
   */
  protected variability_block variability_block;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected param_obj_blockImpl()
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
    return MdlPackage.Literals.PARAM_OBJ_BLOCK;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public structural_block getStructural_block()
  {
    return structural_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStructural_block(structural_block newStructural_block, NotificationChain msgs)
  {
    structural_block oldStructural_block = structural_block;
    structural_block = newStructural_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.PARAM_OBJ_BLOCK__STRUCTURAL_BLOCK, oldStructural_block, newStructural_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStructural_block(structural_block newStructural_block)
  {
    if (newStructural_block != structural_block)
    {
      NotificationChain msgs = null;
      if (structural_block != null)
        msgs = ((InternalEObject)structural_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.PARAM_OBJ_BLOCK__STRUCTURAL_BLOCK, null, msgs);
      if (newStructural_block != null)
        msgs = ((InternalEObject)newStructural_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.PARAM_OBJ_BLOCK__STRUCTURAL_BLOCK, null, msgs);
      msgs = basicSetStructural_block(newStructural_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.PARAM_OBJ_BLOCK__STRUCTURAL_BLOCK, newStructural_block, newStructural_block));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public variability_block getVariability_block()
  {
    return variability_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVariability_block(variability_block newVariability_block, NotificationChain msgs)
  {
    variability_block oldVariability_block = variability_block;
    variability_block = newVariability_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.PARAM_OBJ_BLOCK__VARIABILITY_BLOCK, oldVariability_block, newVariability_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVariability_block(variability_block newVariability_block)
  {
    if (newVariability_block != variability_block)
    {
      NotificationChain msgs = null;
      if (variability_block != null)
        msgs = ((InternalEObject)variability_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.PARAM_OBJ_BLOCK__VARIABILITY_BLOCK, null, msgs);
      if (newVariability_block != null)
        msgs = ((InternalEObject)newVariability_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.PARAM_OBJ_BLOCK__VARIABILITY_BLOCK, null, msgs);
      msgs = basicSetVariability_block(newVariability_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.PARAM_OBJ_BLOCK__VARIABILITY_BLOCK, newVariability_block, newVariability_block));
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
      case MdlPackage.PARAM_OBJ_BLOCK__STRUCTURAL_BLOCK:
        return basicSetStructural_block(null, msgs);
      case MdlPackage.PARAM_OBJ_BLOCK__VARIABILITY_BLOCK:
        return basicSetVariability_block(null, msgs);
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
      case MdlPackage.PARAM_OBJ_BLOCK__STRUCTURAL_BLOCK:
        return getStructural_block();
      case MdlPackage.PARAM_OBJ_BLOCK__VARIABILITY_BLOCK:
        return getVariability_block();
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
      case MdlPackage.PARAM_OBJ_BLOCK__STRUCTURAL_BLOCK:
        setStructural_block((structural_block)newValue);
        return;
      case MdlPackage.PARAM_OBJ_BLOCK__VARIABILITY_BLOCK:
        setVariability_block((variability_block)newValue);
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
      case MdlPackage.PARAM_OBJ_BLOCK__STRUCTURAL_BLOCK:
        setStructural_block((structural_block)null);
        return;
      case MdlPackage.PARAM_OBJ_BLOCK__VARIABILITY_BLOCK:
        setVariability_block((variability_block)null);
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
      case MdlPackage.PARAM_OBJ_BLOCK__STRUCTURAL_BLOCK:
        return structural_block != null;
      case MdlPackage.PARAM_OBJ_BLOCK__VARIABILITY_BLOCK:
        return variability_block != null;
    }
    return super.eIsSet(featureID);
  }

} //param_obj_blockImpl
