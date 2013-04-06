/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.block_statement;
import org.ddmore.mdl.mdl.library_block;
import org.ddmore.mdl.mdl.model_block_statement;
import org.ddmore.mdl.mdl.ode_block;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>model block statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.model_block_statementImpl#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.model_block_statementImpl#getOde_block <em>Ode block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.model_block_statementImpl#getLibrary_block <em>Library block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class model_block_statementImpl extends MinimalEObjectImpl.Container implements model_block_statement
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
   * The cached value of the '{@link #getOde_block() <em>Ode block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOde_block()
   * @generated
   * @ordered
   */
  protected ode_block ode_block;

  /**
   * The cached value of the '{@link #getLibrary_block() <em>Library block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLibrary_block()
   * @generated
   * @ordered
   */
  protected library_block library_block;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected model_block_statementImpl()
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
    return MdlPackage.Literals.MODEL_BLOCK_STATEMENT;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT, oldStatement, newStatement);
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
        msgs = ((InternalEObject)statement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT, null, msgs);
      if (newStatement != null)
        msgs = ((InternalEObject)newStatement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT, null, msgs);
      msgs = basicSetStatement(newStatement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT, newStatement, newStatement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ode_block getOde_block()
  {
    return ode_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOde_block(ode_block newOde_block, NotificationChain msgs)
  {
    ode_block oldOde_block = ode_block;
    ode_block = newOde_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_BLOCK_STATEMENT__ODE_BLOCK, oldOde_block, newOde_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOde_block(ode_block newOde_block)
  {
    if (newOde_block != ode_block)
    {
      NotificationChain msgs = null;
      if (ode_block != null)
        msgs = ((InternalEObject)ode_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_BLOCK_STATEMENT__ODE_BLOCK, null, msgs);
      if (newOde_block != null)
        msgs = ((InternalEObject)newOde_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_BLOCK_STATEMENT__ODE_BLOCK, null, msgs);
      msgs = basicSetOde_block(newOde_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_BLOCK_STATEMENT__ODE_BLOCK, newOde_block, newOde_block));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public library_block getLibrary_block()
  {
    return library_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLibrary_block(library_block newLibrary_block, NotificationChain msgs)
  {
    library_block oldLibrary_block = library_block;
    library_block = newLibrary_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_BLOCK_STATEMENT__LIBRARY_BLOCK, oldLibrary_block, newLibrary_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLibrary_block(library_block newLibrary_block)
  {
    if (newLibrary_block != library_block)
    {
      NotificationChain msgs = null;
      if (library_block != null)
        msgs = ((InternalEObject)library_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_BLOCK_STATEMENT__LIBRARY_BLOCK, null, msgs);
      if (newLibrary_block != null)
        msgs = ((InternalEObject)newLibrary_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_BLOCK_STATEMENT__LIBRARY_BLOCK, null, msgs);
      msgs = basicSetLibrary_block(newLibrary_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_BLOCK_STATEMENT__LIBRARY_BLOCK, newLibrary_block, newLibrary_block));
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
      case MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT:
        return basicSetStatement(null, msgs);
      case MdlPackage.MODEL_BLOCK_STATEMENT__ODE_BLOCK:
        return basicSetOde_block(null, msgs);
      case MdlPackage.MODEL_BLOCK_STATEMENT__LIBRARY_BLOCK:
        return basicSetLibrary_block(null, msgs);
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
      case MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT:
        return getStatement();
      case MdlPackage.MODEL_BLOCK_STATEMENT__ODE_BLOCK:
        return getOde_block();
      case MdlPackage.MODEL_BLOCK_STATEMENT__LIBRARY_BLOCK:
        return getLibrary_block();
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
      case MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT:
        setStatement((block_statement)newValue);
        return;
      case MdlPackage.MODEL_BLOCK_STATEMENT__ODE_BLOCK:
        setOde_block((ode_block)newValue);
        return;
      case MdlPackage.MODEL_BLOCK_STATEMENT__LIBRARY_BLOCK:
        setLibrary_block((library_block)newValue);
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
      case MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT:
        setStatement((block_statement)null);
        return;
      case MdlPackage.MODEL_BLOCK_STATEMENT__ODE_BLOCK:
        setOde_block((ode_block)null);
        return;
      case MdlPackage.MODEL_BLOCK_STATEMENT__LIBRARY_BLOCK:
        setLibrary_block((library_block)null);
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
      case MdlPackage.MODEL_BLOCK_STATEMENT__STATEMENT:
        return statement != null;
      case MdlPackage.MODEL_BLOCK_STATEMENT__ODE_BLOCK:
        return ode_block != null;
      case MdlPackage.MODEL_BLOCK_STATEMENT__LIBRARY_BLOCK:
        return library_block != null;
    }
    return super.eIsSet(featureID);
  }

} //model_block_statementImpl
