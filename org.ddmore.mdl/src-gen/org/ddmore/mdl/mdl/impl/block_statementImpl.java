/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.block_statement;
import org.ddmore.mdl.mdl.function_call;
import org.ddmore.mdl.mdl.statement;
import org.ddmore.mdl.mdl.variable_declaration;
import org.ddmore.mdl.mdl.verbatim_block;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>block statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.block_statementImpl#getVariable_declaration <em>Variable declaration</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.block_statementImpl#getFunction_call <em>Function call</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.block_statementImpl#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.block_statementImpl#getVerbatim_block <em>Verbatim block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class block_statementImpl extends MinimalEObjectImpl.Container implements block_statement
{
  /**
   * The cached value of the '{@link #getVariable_declaration() <em>Variable declaration</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariable_declaration()
   * @generated
   * @ordered
   */
  protected variable_declaration variable_declaration;

  /**
   * The cached value of the '{@link #getFunction_call() <em>Function call</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunction_call()
   * @generated
   * @ordered
   */
  protected function_call function_call;

  /**
   * The cached value of the '{@link #getStatement() <em>Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStatement()
   * @generated
   * @ordered
   */
  protected statement statement;

  /**
   * The cached value of the '{@link #getVerbatim_block() <em>Verbatim block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVerbatim_block()
   * @generated
   * @ordered
   */
  protected verbatim_block verbatim_block;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected block_statementImpl()
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
    return MdlPackage.Literals.BLOCK_STATEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public variable_declaration getVariable_declaration()
  {
    return variable_declaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVariable_declaration(variable_declaration newVariable_declaration, NotificationChain msgs)
  {
    variable_declaration oldVariable_declaration = variable_declaration;
    variable_declaration = newVariable_declaration;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.BLOCK_STATEMENT__VARIABLE_DECLARATION, oldVariable_declaration, newVariable_declaration);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVariable_declaration(variable_declaration newVariable_declaration)
  {
    if (newVariable_declaration != variable_declaration)
    {
      NotificationChain msgs = null;
      if (variable_declaration != null)
        msgs = ((InternalEObject)variable_declaration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.BLOCK_STATEMENT__VARIABLE_DECLARATION, null, msgs);
      if (newVariable_declaration != null)
        msgs = ((InternalEObject)newVariable_declaration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.BLOCK_STATEMENT__VARIABLE_DECLARATION, null, msgs);
      msgs = basicSetVariable_declaration(newVariable_declaration, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.BLOCK_STATEMENT__VARIABLE_DECLARATION, newVariable_declaration, newVariable_declaration));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public function_call getFunction_call()
  {
    return function_call;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFunction_call(function_call newFunction_call, NotificationChain msgs)
  {
    function_call oldFunction_call = function_call;
    function_call = newFunction_call;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL, oldFunction_call, newFunction_call);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFunction_call(function_call newFunction_call)
  {
    if (newFunction_call != function_call)
    {
      NotificationChain msgs = null;
      if (function_call != null)
        msgs = ((InternalEObject)function_call).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL, null, msgs);
      if (newFunction_call != null)
        msgs = ((InternalEObject)newFunction_call).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL, null, msgs);
      msgs = basicSetFunction_call(newFunction_call, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL, newFunction_call, newFunction_call));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public statement getStatement()
  {
    return statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStatement(statement newStatement, NotificationChain msgs)
  {
    statement oldStatement = statement;
    statement = newStatement;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.BLOCK_STATEMENT__STATEMENT, oldStatement, newStatement);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStatement(statement newStatement)
  {
    if (newStatement != statement)
    {
      NotificationChain msgs = null;
      if (statement != null)
        msgs = ((InternalEObject)statement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.BLOCK_STATEMENT__STATEMENT, null, msgs);
      if (newStatement != null)
        msgs = ((InternalEObject)newStatement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.BLOCK_STATEMENT__STATEMENT, null, msgs);
      msgs = basicSetStatement(newStatement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.BLOCK_STATEMENT__STATEMENT, newStatement, newStatement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public verbatim_block getVerbatim_block()
  {
    return verbatim_block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVerbatim_block(verbatim_block newVerbatim_block, NotificationChain msgs)
  {
    verbatim_block oldVerbatim_block = verbatim_block;
    verbatim_block = newVerbatim_block;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.BLOCK_STATEMENT__VERBATIM_BLOCK, oldVerbatim_block, newVerbatim_block);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVerbatim_block(verbatim_block newVerbatim_block)
  {
    if (newVerbatim_block != verbatim_block)
    {
      NotificationChain msgs = null;
      if (verbatim_block != null)
        msgs = ((InternalEObject)verbatim_block).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.BLOCK_STATEMENT__VERBATIM_BLOCK, null, msgs);
      if (newVerbatim_block != null)
        msgs = ((InternalEObject)newVerbatim_block).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.BLOCK_STATEMENT__VERBATIM_BLOCK, null, msgs);
      msgs = basicSetVerbatim_block(newVerbatim_block, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.BLOCK_STATEMENT__VERBATIM_BLOCK, newVerbatim_block, newVerbatim_block));
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
      case MdlPackage.BLOCK_STATEMENT__VARIABLE_DECLARATION:
        return basicSetVariable_declaration(null, msgs);
      case MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL:
        return basicSetFunction_call(null, msgs);
      case MdlPackage.BLOCK_STATEMENT__STATEMENT:
        return basicSetStatement(null, msgs);
      case MdlPackage.BLOCK_STATEMENT__VERBATIM_BLOCK:
        return basicSetVerbatim_block(null, msgs);
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
      case MdlPackage.BLOCK_STATEMENT__VARIABLE_DECLARATION:
        return getVariable_declaration();
      case MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL:
        return getFunction_call();
      case MdlPackage.BLOCK_STATEMENT__STATEMENT:
        return getStatement();
      case MdlPackage.BLOCK_STATEMENT__VERBATIM_BLOCK:
        return getVerbatim_block();
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
      case MdlPackage.BLOCK_STATEMENT__VARIABLE_DECLARATION:
        setVariable_declaration((variable_declaration)newValue);
        return;
      case MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL:
        setFunction_call((function_call)newValue);
        return;
      case MdlPackage.BLOCK_STATEMENT__STATEMENT:
        setStatement((statement)newValue);
        return;
      case MdlPackage.BLOCK_STATEMENT__VERBATIM_BLOCK:
        setVerbatim_block((verbatim_block)newValue);
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
      case MdlPackage.BLOCK_STATEMENT__VARIABLE_DECLARATION:
        setVariable_declaration((variable_declaration)null);
        return;
      case MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL:
        setFunction_call((function_call)null);
        return;
      case MdlPackage.BLOCK_STATEMENT__STATEMENT:
        setStatement((statement)null);
        return;
      case MdlPackage.BLOCK_STATEMENT__VERBATIM_BLOCK:
        setVerbatim_block((verbatim_block)null);
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
      case MdlPackage.BLOCK_STATEMENT__VARIABLE_DECLARATION:
        return variable_declaration != null;
      case MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL:
        return function_call != null;
      case MdlPackage.BLOCK_STATEMENT__STATEMENT:
        return statement != null;
      case MdlPackage.BLOCK_STATEMENT__VERBATIM_BLOCK:
        return verbatim_block != null;
    }
    return super.eIsSet(featureID);
  }

} //block_statementImpl
