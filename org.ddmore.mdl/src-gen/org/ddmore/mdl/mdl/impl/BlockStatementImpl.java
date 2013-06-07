/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.ConditionalStatement;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.SymbolDeclaration;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Block Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.BlockStatementImpl#getSymbol <em>Symbol</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.BlockStatementImpl#getFunctionCall <em>Function Call</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.BlockStatementImpl#getStatement <em>Statement</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BlockStatementImpl extends MinimalEObjectImpl.Container implements BlockStatement
{
  /**
   * The cached value of the '{@link #getSymbol() <em>Symbol</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSymbol()
   * @generated
   * @ordered
   */
  protected SymbolDeclaration symbol;

  /**
   * The cached value of the '{@link #getFunctionCall() <em>Function Call</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunctionCall()
   * @generated
   * @ordered
   */
  protected FunctionCall functionCall;

  /**
   * The cached value of the '{@link #getStatement() <em>Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStatement()
   * @generated
   * @ordered
   */
  protected ConditionalStatement statement;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected BlockStatementImpl()
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
  public SymbolDeclaration getSymbol()
  {
    return symbol;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSymbol(SymbolDeclaration newSymbol, NotificationChain msgs)
  {
    SymbolDeclaration oldSymbol = symbol;
    symbol = newSymbol;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.BLOCK_STATEMENT__SYMBOL, oldSymbol, newSymbol);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSymbol(SymbolDeclaration newSymbol)
  {
    if (newSymbol != symbol)
    {
      NotificationChain msgs = null;
      if (symbol != null)
        msgs = ((InternalEObject)symbol).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.BLOCK_STATEMENT__SYMBOL, null, msgs);
      if (newSymbol != null)
        msgs = ((InternalEObject)newSymbol).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.BLOCK_STATEMENT__SYMBOL, null, msgs);
      msgs = basicSetSymbol(newSymbol, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.BLOCK_STATEMENT__SYMBOL, newSymbol, newSymbol));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunctionCall getFunctionCall()
  {
    return functionCall;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFunctionCall(FunctionCall newFunctionCall, NotificationChain msgs)
  {
    FunctionCall oldFunctionCall = functionCall;
    functionCall = newFunctionCall;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL, oldFunctionCall, newFunctionCall);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFunctionCall(FunctionCall newFunctionCall)
  {
    if (newFunctionCall != functionCall)
    {
      NotificationChain msgs = null;
      if (functionCall != null)
        msgs = ((InternalEObject)functionCall).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL, null, msgs);
      if (newFunctionCall != null)
        msgs = ((InternalEObject)newFunctionCall).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL, null, msgs);
      msgs = basicSetFunctionCall(newFunctionCall, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL, newFunctionCall, newFunctionCall));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConditionalStatement getStatement()
  {
    return statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetStatement(ConditionalStatement newStatement, NotificationChain msgs)
  {
    ConditionalStatement oldStatement = statement;
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
  public void setStatement(ConditionalStatement newStatement)
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
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case MdlPackage.BLOCK_STATEMENT__SYMBOL:
        return basicSetSymbol(null, msgs);
      case MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL:
        return basicSetFunctionCall(null, msgs);
      case MdlPackage.BLOCK_STATEMENT__STATEMENT:
        return basicSetStatement(null, msgs);
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
      case MdlPackage.BLOCK_STATEMENT__SYMBOL:
        return getSymbol();
      case MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL:
        return getFunctionCall();
      case MdlPackage.BLOCK_STATEMENT__STATEMENT:
        return getStatement();
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
      case MdlPackage.BLOCK_STATEMENT__SYMBOL:
        setSymbol((SymbolDeclaration)newValue);
        return;
      case MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL:
        setFunctionCall((FunctionCall)newValue);
        return;
      case MdlPackage.BLOCK_STATEMENT__STATEMENT:
        setStatement((ConditionalStatement)newValue);
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
      case MdlPackage.BLOCK_STATEMENT__SYMBOL:
        setSymbol((SymbolDeclaration)null);
        return;
      case MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL:
        setFunctionCall((FunctionCall)null);
        return;
      case MdlPackage.BLOCK_STATEMENT__STATEMENT:
        setStatement((ConditionalStatement)null);
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
      case MdlPackage.BLOCK_STATEMENT__SYMBOL:
        return symbol != null;
      case MdlPackage.BLOCK_STATEMENT__FUNCTION_CALL:
        return functionCall != null;
      case MdlPackage.BLOCK_STATEMENT__STATEMENT:
        return statement != null;
    }
    return super.eIsSet(featureID);
  }

} //BlockStatementImpl
