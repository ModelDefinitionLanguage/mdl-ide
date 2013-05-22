/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.BlockBlock;
import org.ddmore.mdl.mdl.DiagBlock;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.ParameterDeclaration;
import org.ddmore.mdl.mdl.SameBlock;
import org.ddmore.mdl.mdl.VariabilityBlockStatement;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variability Block Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.VariabilityBlockStatementImpl#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.VariabilityBlockStatementImpl#getBlockBlock <em>Block Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.VariabilityBlockStatementImpl#getDiagBlock <em>Diag Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.VariabilityBlockStatementImpl#getSameBlock <em>Same Block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariabilityBlockStatementImpl extends MinimalEObjectImpl.Container implements VariabilityBlockStatement
{
  /**
   * The cached value of the '{@link #getParameter() <em>Parameter</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameter()
   * @generated
   * @ordered
   */
  protected ParameterDeclaration parameter;

  /**
   * The cached value of the '{@link #getBlockBlock() <em>Block Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBlockBlock()
   * @generated
   * @ordered
   */
  protected BlockBlock blockBlock;

  /**
   * The cached value of the '{@link #getDiagBlock() <em>Diag Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDiagBlock()
   * @generated
   * @ordered
   */
  protected DiagBlock diagBlock;

  /**
   * The cached value of the '{@link #getSameBlock() <em>Same Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSameBlock()
   * @generated
   * @ordered
   */
  protected SameBlock sameBlock;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected VariabilityBlockStatementImpl()
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
    return MdlPackage.Literals.VARIABILITY_BLOCK_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParameterDeclaration getParameter()
  {
    return parameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParameter(ParameterDeclaration newParameter, NotificationChain msgs)
  {
    ParameterDeclaration oldParameter = parameter;
    parameter = newParameter;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABILITY_BLOCK_STATEMENT__PARAMETER, oldParameter, newParameter);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParameter(ParameterDeclaration newParameter)
  {
    if (newParameter != parameter)
    {
      NotificationChain msgs = null;
      if (parameter != null)
        msgs = ((InternalEObject)parameter).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABILITY_BLOCK_STATEMENT__PARAMETER, null, msgs);
      if (newParameter != null)
        msgs = ((InternalEObject)newParameter).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABILITY_BLOCK_STATEMENT__PARAMETER, null, msgs);
      msgs = basicSetParameter(newParameter, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABILITY_BLOCK_STATEMENT__PARAMETER, newParameter, newParameter));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BlockBlock getBlockBlock()
  {
    return blockBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBlockBlock(BlockBlock newBlockBlock, NotificationChain msgs)
  {
    BlockBlock oldBlockBlock = blockBlock;
    blockBlock = newBlockBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK, oldBlockBlock, newBlockBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBlockBlock(BlockBlock newBlockBlock)
  {
    if (newBlockBlock != blockBlock)
    {
      NotificationChain msgs = null;
      if (blockBlock != null)
        msgs = ((InternalEObject)blockBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK, null, msgs);
      if (newBlockBlock != null)
        msgs = ((InternalEObject)newBlockBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK, null, msgs);
      msgs = basicSetBlockBlock(newBlockBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK, newBlockBlock, newBlockBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DiagBlock getDiagBlock()
  {
    return diagBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDiagBlock(DiagBlock newDiagBlock, NotificationChain msgs)
  {
    DiagBlock oldDiagBlock = diagBlock;
    diagBlock = newDiagBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK, oldDiagBlock, newDiagBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDiagBlock(DiagBlock newDiagBlock)
  {
    if (newDiagBlock != diagBlock)
    {
      NotificationChain msgs = null;
      if (diagBlock != null)
        msgs = ((InternalEObject)diagBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK, null, msgs);
      if (newDiagBlock != null)
        msgs = ((InternalEObject)newDiagBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK, null, msgs);
      msgs = basicSetDiagBlock(newDiagBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK, newDiagBlock, newDiagBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SameBlock getSameBlock()
  {
    return sameBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSameBlock(SameBlock newSameBlock, NotificationChain msgs)
  {
    SameBlock oldSameBlock = sameBlock;
    sameBlock = newSameBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABILITY_BLOCK_STATEMENT__SAME_BLOCK, oldSameBlock, newSameBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSameBlock(SameBlock newSameBlock)
  {
    if (newSameBlock != sameBlock)
    {
      NotificationChain msgs = null;
      if (sameBlock != null)
        msgs = ((InternalEObject)sameBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABILITY_BLOCK_STATEMENT__SAME_BLOCK, null, msgs);
      if (newSameBlock != null)
        msgs = ((InternalEObject)newSameBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.VARIABILITY_BLOCK_STATEMENT__SAME_BLOCK, null, msgs);
      msgs = basicSetSameBlock(newSameBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.VARIABILITY_BLOCK_STATEMENT__SAME_BLOCK, newSameBlock, newSameBlock));
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
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__PARAMETER:
        return basicSetParameter(null, msgs);
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK:
        return basicSetBlockBlock(null, msgs);
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK:
        return basicSetDiagBlock(null, msgs);
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__SAME_BLOCK:
        return basicSetSameBlock(null, msgs);
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
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__PARAMETER:
        return getParameter();
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK:
        return getBlockBlock();
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK:
        return getDiagBlock();
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__SAME_BLOCK:
        return getSameBlock();
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
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__PARAMETER:
        setParameter((ParameterDeclaration)newValue);
        return;
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK:
        setBlockBlock((BlockBlock)newValue);
        return;
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK:
        setDiagBlock((DiagBlock)newValue);
        return;
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__SAME_BLOCK:
        setSameBlock((SameBlock)newValue);
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
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__PARAMETER:
        setParameter((ParameterDeclaration)null);
        return;
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK:
        setBlockBlock((BlockBlock)null);
        return;
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK:
        setDiagBlock((DiagBlock)null);
        return;
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__SAME_BLOCK:
        setSameBlock((SameBlock)null);
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
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__PARAMETER:
        return parameter != null;
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK:
        return blockBlock != null;
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK:
        return diagBlock != null;
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT__SAME_BLOCK:
        return sameBlock != null;
    }
    return super.eIsSet(featureID);
  }

} //VariabilityBlockStatementImpl
