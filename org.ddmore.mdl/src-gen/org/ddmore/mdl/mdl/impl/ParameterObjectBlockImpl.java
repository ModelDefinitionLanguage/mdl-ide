/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.ParameterObjectBlock;
import org.ddmore.mdl.mdl.PriorParametersBlock;
import org.ddmore.mdl.mdl.StructuralBlock;
import org.ddmore.mdl.mdl.VariabilityBlock;
import org.ddmore.mdl.mdl.VerbatimBlock;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Parameter Object Block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ParameterObjectBlockImpl#getStructuralBlock <em>Structural Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ParameterObjectBlockImpl#getVariabilityBlock <em>Variability Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ParameterObjectBlockImpl#getPriorBlock <em>Prior Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ParameterObjectBlockImpl#getVerbatimBlock <em>Verbatim Block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParameterObjectBlockImpl extends MinimalEObjectImpl.Container implements ParameterObjectBlock
{
  /**
   * The cached value of the '{@link #getStructuralBlock() <em>Structural Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStructuralBlock()
   * @generated
   * @ordered
   */
  protected StructuralBlock structuralBlock;

  /**
   * The cached value of the '{@link #getVariabilityBlock() <em>Variability Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariabilityBlock()
   * @generated
   * @ordered
   */
  protected VariabilityBlock variabilityBlock;

  /**
   * The cached value of the '{@link #getPriorBlock() <em>Prior Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPriorBlock()
   * @generated
   * @ordered
   */
  protected PriorParametersBlock priorBlock;

  /**
   * The cached value of the '{@link #getVerbatimBlock() <em>Verbatim Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVerbatimBlock()
   * @generated
   * @ordered
   */
  protected VerbatimBlock verbatimBlock;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ParameterObjectBlockImpl()
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
    return MdlPackage.Literals.PARAMETER_OBJECT_BLOCK;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StructuralBlock getStructuralBlock()
  {
    return structuralBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStructuralBlock(StructuralBlock newStructuralBlock, NotificationChain msgs)
  {
    StructuralBlock oldStructuralBlock = structuralBlock;
    structuralBlock = newStructuralBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.PARAMETER_OBJECT_BLOCK__STRUCTURAL_BLOCK, oldStructuralBlock, newStructuralBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStructuralBlock(StructuralBlock newStructuralBlock)
  {
    if (newStructuralBlock != structuralBlock)
    {
      NotificationChain msgs = null;
      if (structuralBlock != null)
        msgs = ((InternalEObject)structuralBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.PARAMETER_OBJECT_BLOCK__STRUCTURAL_BLOCK, null, msgs);
      if (newStructuralBlock != null)
        msgs = ((InternalEObject)newStructuralBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.PARAMETER_OBJECT_BLOCK__STRUCTURAL_BLOCK, null, msgs);
      msgs = basicSetStructuralBlock(newStructuralBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.PARAMETER_OBJECT_BLOCK__STRUCTURAL_BLOCK, newStructuralBlock, newStructuralBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VariabilityBlock getVariabilityBlock()
  {
    return variabilityBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVariabilityBlock(VariabilityBlock newVariabilityBlock, NotificationChain msgs)
  {
    VariabilityBlock oldVariabilityBlock = variabilityBlock;
    variabilityBlock = newVariabilityBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.PARAMETER_OBJECT_BLOCK__VARIABILITY_BLOCK, oldVariabilityBlock, newVariabilityBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVariabilityBlock(VariabilityBlock newVariabilityBlock)
  {
    if (newVariabilityBlock != variabilityBlock)
    {
      NotificationChain msgs = null;
      if (variabilityBlock != null)
        msgs = ((InternalEObject)variabilityBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.PARAMETER_OBJECT_BLOCK__VARIABILITY_BLOCK, null, msgs);
      if (newVariabilityBlock != null)
        msgs = ((InternalEObject)newVariabilityBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.PARAMETER_OBJECT_BLOCK__VARIABILITY_BLOCK, null, msgs);
      msgs = basicSetVariabilityBlock(newVariabilityBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.PARAMETER_OBJECT_BLOCK__VARIABILITY_BLOCK, newVariabilityBlock, newVariabilityBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PriorParametersBlock getPriorBlock()
  {
    return priorBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPriorBlock(PriorParametersBlock newPriorBlock, NotificationChain msgs)
  {
    PriorParametersBlock oldPriorBlock = priorBlock;
    priorBlock = newPriorBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.PARAMETER_OBJECT_BLOCK__PRIOR_BLOCK, oldPriorBlock, newPriorBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPriorBlock(PriorParametersBlock newPriorBlock)
  {
    if (newPriorBlock != priorBlock)
    {
      NotificationChain msgs = null;
      if (priorBlock != null)
        msgs = ((InternalEObject)priorBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.PARAMETER_OBJECT_BLOCK__PRIOR_BLOCK, null, msgs);
      if (newPriorBlock != null)
        msgs = ((InternalEObject)newPriorBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.PARAMETER_OBJECT_BLOCK__PRIOR_BLOCK, null, msgs);
      msgs = basicSetPriorBlock(newPriorBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.PARAMETER_OBJECT_BLOCK__PRIOR_BLOCK, newPriorBlock, newPriorBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VerbatimBlock getVerbatimBlock()
  {
    return verbatimBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVerbatimBlock(VerbatimBlock newVerbatimBlock, NotificationChain msgs)
  {
    VerbatimBlock oldVerbatimBlock = verbatimBlock;
    verbatimBlock = newVerbatimBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.PARAMETER_OBJECT_BLOCK__VERBATIM_BLOCK, oldVerbatimBlock, newVerbatimBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVerbatimBlock(VerbatimBlock newVerbatimBlock)
  {
    if (newVerbatimBlock != verbatimBlock)
    {
      NotificationChain msgs = null;
      if (verbatimBlock != null)
        msgs = ((InternalEObject)verbatimBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.PARAMETER_OBJECT_BLOCK__VERBATIM_BLOCK, null, msgs);
      if (newVerbatimBlock != null)
        msgs = ((InternalEObject)newVerbatimBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.PARAMETER_OBJECT_BLOCK__VERBATIM_BLOCK, null, msgs);
      msgs = basicSetVerbatimBlock(newVerbatimBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.PARAMETER_OBJECT_BLOCK__VERBATIM_BLOCK, newVerbatimBlock, newVerbatimBlock));
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
      case MdlPackage.PARAMETER_OBJECT_BLOCK__STRUCTURAL_BLOCK:
        return basicSetStructuralBlock(null, msgs);
      case MdlPackage.PARAMETER_OBJECT_BLOCK__VARIABILITY_BLOCK:
        return basicSetVariabilityBlock(null, msgs);
      case MdlPackage.PARAMETER_OBJECT_BLOCK__PRIOR_BLOCK:
        return basicSetPriorBlock(null, msgs);
      case MdlPackage.PARAMETER_OBJECT_BLOCK__VERBATIM_BLOCK:
        return basicSetVerbatimBlock(null, msgs);
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
      case MdlPackage.PARAMETER_OBJECT_BLOCK__STRUCTURAL_BLOCK:
        return getStructuralBlock();
      case MdlPackage.PARAMETER_OBJECT_BLOCK__VARIABILITY_BLOCK:
        return getVariabilityBlock();
      case MdlPackage.PARAMETER_OBJECT_BLOCK__PRIOR_BLOCK:
        return getPriorBlock();
      case MdlPackage.PARAMETER_OBJECT_BLOCK__VERBATIM_BLOCK:
        return getVerbatimBlock();
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
      case MdlPackage.PARAMETER_OBJECT_BLOCK__STRUCTURAL_BLOCK:
        setStructuralBlock((StructuralBlock)newValue);
        return;
      case MdlPackage.PARAMETER_OBJECT_BLOCK__VARIABILITY_BLOCK:
        setVariabilityBlock((VariabilityBlock)newValue);
        return;
      case MdlPackage.PARAMETER_OBJECT_BLOCK__PRIOR_BLOCK:
        setPriorBlock((PriorParametersBlock)newValue);
        return;
      case MdlPackage.PARAMETER_OBJECT_BLOCK__VERBATIM_BLOCK:
        setVerbatimBlock((VerbatimBlock)newValue);
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
      case MdlPackage.PARAMETER_OBJECT_BLOCK__STRUCTURAL_BLOCK:
        setStructuralBlock((StructuralBlock)null);
        return;
      case MdlPackage.PARAMETER_OBJECT_BLOCK__VARIABILITY_BLOCK:
        setVariabilityBlock((VariabilityBlock)null);
        return;
      case MdlPackage.PARAMETER_OBJECT_BLOCK__PRIOR_BLOCK:
        setPriorBlock((PriorParametersBlock)null);
        return;
      case MdlPackage.PARAMETER_OBJECT_BLOCK__VERBATIM_BLOCK:
        setVerbatimBlock((VerbatimBlock)null);
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
      case MdlPackage.PARAMETER_OBJECT_BLOCK__STRUCTURAL_BLOCK:
        return structuralBlock != null;
      case MdlPackage.PARAMETER_OBJECT_BLOCK__VARIABILITY_BLOCK:
        return variabilityBlock != null;
      case MdlPackage.PARAMETER_OBJECT_BLOCK__PRIOR_BLOCK:
        return priorBlock != null;
      case MdlPackage.PARAMETER_OBJECT_BLOCK__VERBATIM_BLOCK:
        return verbatimBlock != null;
    }
    return super.eIsSet(featureID);
  }

} //ParameterObjectBlockImpl
