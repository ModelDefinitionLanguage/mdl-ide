/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.block_statement;
import org.ddmore.mdl.mdl.design_block;
import org.ddmore.mdl.mdl.file_block_statement;
import org.ddmore.mdl.mdl.inline_block;
import org.ddmore.mdl.mdl.rsscript_block;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>file block statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.file_block_statementImpl#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.file_block_statementImpl#getInline_block <em>Inline block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.file_block_statementImpl#getDesign_block <em>Design block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.file_block_statementImpl#getRsscript_block <em>Rsscript block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class file_block_statementImpl extends MinimalEObjectImpl.Container implements file_block_statement
{
  /**
   * The cached value of the '{@link #getStatement() <em>Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStatement()
   * @generated
   * @ordered
   */
  protected block_statement statement;

  /**
   * The cached value of the '{@link #getInline_block() <em>Inline block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInline_block()
   * @generated
   * @ordered
   */
  protected inline_block inline_block;

  /**
   * The cached value of the '{@link #getDesign_block() <em>Design block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDesign_block()
   * @generated
   * @ordered
   */
  protected design_block design_block;

  /**
   * The cached value of the '{@link #getRsscript_block() <em>Rsscript block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRsscript_block()
   * @generated
   * @ordered
   */
  protected rsscript_block rsscript_block;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected file_block_statementImpl()
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
    return MdlPackage.Literals.FILE_BLOCK_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public block_statement getStatement()
  {
    return statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStatement(block_statement newStatement, NotificationChain msgs)
  {
    block_statement oldStatement = statement;
    statement = newStatement;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.FILE_BLOCK_STATEMENT__STATEMENT, oldStatement, newStatement);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStatement(block_statement newStatement)
  {
    if (newStatement != statement)
    {
      NotificationChain msgs = null;
      if (statement != null)
        msgs = ((InternalEObject)statement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FILE_BLOCK_STATEMENT__STATEMENT, null, msgs);
      if (newStatement != null)
        msgs = ((InternalEObject)newStatement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FILE_BLOCK_STATEMENT__STATEMENT, null, msgs);
      msgs = basicSetStatement(newStatement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.FILE_BLOCK_STATEMENT__STATEMENT, newStatement, newStatement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public inline_block getInline_block()
  {
    return inline_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetInline_block(inline_block newInline_block, NotificationChain msgs)
  {
    inline_block oldInline_block = inline_block;
    inline_block = newInline_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK, oldInline_block, newInline_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInline_block(inline_block newInline_block)
  {
    if (newInline_block != inline_block)
    {
      NotificationChain msgs = null;
      if (inline_block != null)
        msgs = ((InternalEObject)inline_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK, null, msgs);
      if (newInline_block != null)
        msgs = ((InternalEObject)newInline_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK, null, msgs);
      msgs = basicSetInline_block(newInline_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK, newInline_block, newInline_block));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public design_block getDesign_block()
  {
    return design_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDesign_block(design_block newDesign_block, NotificationChain msgs)
  {
    design_block oldDesign_block = design_block;
    design_block = newDesign_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK, oldDesign_block, newDesign_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDesign_block(design_block newDesign_block)
  {
    if (newDesign_block != design_block)
    {
      NotificationChain msgs = null;
      if (design_block != null)
        msgs = ((InternalEObject)design_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK, null, msgs);
      if (newDesign_block != null)
        msgs = ((InternalEObject)newDesign_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK, null, msgs);
      msgs = basicSetDesign_block(newDesign_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK, newDesign_block, newDesign_block));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public rsscript_block getRsscript_block()
  {
    return rsscript_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRsscript_block(rsscript_block newRsscript_block, NotificationChain msgs)
  {
    rsscript_block oldRsscript_block = rsscript_block;
    rsscript_block = newRsscript_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.FILE_BLOCK_STATEMENT__RSSCRIPT_BLOCK, oldRsscript_block, newRsscript_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRsscript_block(rsscript_block newRsscript_block)
  {
    if (newRsscript_block != rsscript_block)
    {
      NotificationChain msgs = null;
      if (rsscript_block != null)
        msgs = ((InternalEObject)rsscript_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FILE_BLOCK_STATEMENT__RSSCRIPT_BLOCK, null, msgs);
      if (newRsscript_block != null)
        msgs = ((InternalEObject)newRsscript_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FILE_BLOCK_STATEMENT__RSSCRIPT_BLOCK, null, msgs);
      msgs = basicSetRsscript_block(newRsscript_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.FILE_BLOCK_STATEMENT__RSSCRIPT_BLOCK, newRsscript_block, newRsscript_block));
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
      case MdlPackage.FILE_BLOCK_STATEMENT__STATEMENT:
        return basicSetStatement(null, msgs);
      case MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK:
        return basicSetInline_block(null, msgs);
      case MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK:
        return basicSetDesign_block(null, msgs);
      case MdlPackage.FILE_BLOCK_STATEMENT__RSSCRIPT_BLOCK:
        return basicSetRsscript_block(null, msgs);
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
      case MdlPackage.FILE_BLOCK_STATEMENT__STATEMENT:
        return getStatement();
      case MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK:
        return getInline_block();
      case MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK:
        return getDesign_block();
      case MdlPackage.FILE_BLOCK_STATEMENT__RSSCRIPT_BLOCK:
        return getRsscript_block();
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
      case MdlPackage.FILE_BLOCK_STATEMENT__STATEMENT:
        setStatement((block_statement)newValue);
        return;
      case MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK:
        setInline_block((inline_block)newValue);
        return;
      case MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK:
        setDesign_block((design_block)newValue);
        return;
      case MdlPackage.FILE_BLOCK_STATEMENT__RSSCRIPT_BLOCK:
        setRsscript_block((rsscript_block)newValue);
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
      case MdlPackage.FILE_BLOCK_STATEMENT__STATEMENT:
        setStatement((block_statement)null);
        return;
      case MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK:
        setInline_block((inline_block)null);
        return;
      case MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK:
        setDesign_block((design_block)null);
        return;
      case MdlPackage.FILE_BLOCK_STATEMENT__RSSCRIPT_BLOCK:
        setRsscript_block((rsscript_block)null);
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
      case MdlPackage.FILE_BLOCK_STATEMENT__STATEMENT:
        return statement != null;
      case MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK:
        return inline_block != null;
      case MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK:
        return design_block != null;
      case MdlPackage.FILE_BLOCK_STATEMENT__RSSCRIPT_BLOCK:
        return rsscript_block != null;
    }
    return super.eIsSet(featureID);
  }

} //file_block_statementImpl
