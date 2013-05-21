/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.GroupVariablesBlockStatement;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.MixtureBlock;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Group Variables Block Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.GroupVariablesBlockStatementImpl#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.GroupVariablesBlockStatementImpl#getMixtureBlock <em>Mixture Block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GroupVariablesBlockStatementImpl extends MinimalEObjectImpl.Container implements GroupVariablesBlockStatement
{
  /**
   * The cached value of the '{@link #getStatement() <em>Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStatement()
   * @generated
   * @ordered
   */
  protected BlockStatement statement;

  /**
   * The cached value of the '{@link #getMixtureBlock() <em>Mixture Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMixtureBlock()
   * @generated
   * @ordered
   */
  protected MixtureBlock mixtureBlock;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GroupVariablesBlockStatementImpl()
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
    return MdlPackage.Literals.GROUP_VARIABLES_BLOCK_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BlockStatement getStatement()
  {
    return statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStatement(BlockStatement newStatement, NotificationChain msgs)
  {
    BlockStatement oldStatement = statement;
    statement = newStatement;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__STATEMENT, oldStatement, newStatement);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStatement(BlockStatement newStatement)
  {
    if (newStatement != statement)
    {
      NotificationChain msgs = null;
      if (statement != null)
        msgs = ((InternalEObject)statement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__STATEMENT, null, msgs);
      if (newStatement != null)
        msgs = ((InternalEObject)newStatement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__STATEMENT, null, msgs);
      msgs = basicSetStatement(newStatement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__STATEMENT, newStatement, newStatement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MixtureBlock getMixtureBlock()
  {
    return mixtureBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMixtureBlock(MixtureBlock newMixtureBlock, NotificationChain msgs)
  {
    MixtureBlock oldMixtureBlock = mixtureBlock;
    mixtureBlock = newMixtureBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__MIXTURE_BLOCK, oldMixtureBlock, newMixtureBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMixtureBlock(MixtureBlock newMixtureBlock)
  {
    if (newMixtureBlock != mixtureBlock)
    {
      NotificationChain msgs = null;
      if (mixtureBlock != null)
        msgs = ((InternalEObject)mixtureBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__MIXTURE_BLOCK, null, msgs);
      if (newMixtureBlock != null)
        msgs = ((InternalEObject)newMixtureBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__MIXTURE_BLOCK, null, msgs);
      msgs = basicSetMixtureBlock(newMixtureBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__MIXTURE_BLOCK, newMixtureBlock, newMixtureBlock));
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
      case MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__STATEMENT:
        return basicSetStatement(null, msgs);
      case MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__MIXTURE_BLOCK:
        return basicSetMixtureBlock(null, msgs);
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
      case MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__STATEMENT:
        return getStatement();
      case MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__MIXTURE_BLOCK:
        return getMixtureBlock();
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
      case MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__STATEMENT:
        setStatement((BlockStatement)newValue);
        return;
      case MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__MIXTURE_BLOCK:
        setMixtureBlock((MixtureBlock)newValue);
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
      case MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__STATEMENT:
        setStatement((BlockStatement)null);
        return;
      case MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__MIXTURE_BLOCK:
        setMixtureBlock((MixtureBlock)null);
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
      case MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__STATEMENT:
        return statement != null;
      case MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT__MIXTURE_BLOCK:
        return mixtureBlock != null;
    }
    return super.eIsSet(featureID);
  }

} //GroupVariablesBlockStatementImpl
