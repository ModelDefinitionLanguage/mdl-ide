/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.LibraryBlock;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.ModelPredictionBlockStatement;
import org.ddmore.mdl.mdl.OdeBlock;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Prediction Block Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelPredictionBlockStatementImpl#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelPredictionBlockStatementImpl#getOdeBlock <em>Ode Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.ModelPredictionBlockStatementImpl#getLibraryBlock <em>Library Block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModelPredictionBlockStatementImpl extends MinimalEObjectImpl.Container implements ModelPredictionBlockStatement
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
   * The cached value of the '{@link #getOdeBlock() <em>Ode Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOdeBlock()
   * @generated
   * @ordered
   */
  protected OdeBlock odeBlock;

  /**
   * The cached value of the '{@link #getLibraryBlock() <em>Library Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLibraryBlock()
   * @generated
   * @ordered
   */
  protected LibraryBlock libraryBlock;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ModelPredictionBlockStatementImpl()
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
    return MdlPackage.Literals.MODEL_PREDICTION_BLOCK_STATEMENT;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__STATEMENT, oldStatement, newStatement);
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
        msgs = ((InternalEObject)statement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__STATEMENT, null, msgs);
      if (newStatement != null)
        msgs = ((InternalEObject)newStatement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__STATEMENT, null, msgs);
      msgs = basicSetStatement(newStatement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__STATEMENT, newStatement, newStatement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OdeBlock getOdeBlock()
  {
    return odeBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOdeBlock(OdeBlock newOdeBlock, NotificationChain msgs)
  {
    OdeBlock oldOdeBlock = odeBlock;
    odeBlock = newOdeBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__ODE_BLOCK, oldOdeBlock, newOdeBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOdeBlock(OdeBlock newOdeBlock)
  {
    if (newOdeBlock != odeBlock)
    {
      NotificationChain msgs = null;
      if (odeBlock != null)
        msgs = ((InternalEObject)odeBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__ODE_BLOCK, null, msgs);
      if (newOdeBlock != null)
        msgs = ((InternalEObject)newOdeBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__ODE_BLOCK, null, msgs);
      msgs = basicSetOdeBlock(newOdeBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__ODE_BLOCK, newOdeBlock, newOdeBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LibraryBlock getLibraryBlock()
  {
    return libraryBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLibraryBlock(LibraryBlock newLibraryBlock, NotificationChain msgs)
  {
    LibraryBlock oldLibraryBlock = libraryBlock;
    libraryBlock = newLibraryBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__LIBRARY_BLOCK, oldLibraryBlock, newLibraryBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLibraryBlock(LibraryBlock newLibraryBlock)
  {
    if (newLibraryBlock != libraryBlock)
    {
      NotificationChain msgs = null;
      if (libraryBlock != null)
        msgs = ((InternalEObject)libraryBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__LIBRARY_BLOCK, null, msgs);
      if (newLibraryBlock != null)
        msgs = ((InternalEObject)newLibraryBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__LIBRARY_BLOCK, null, msgs);
      msgs = basicSetLibraryBlock(newLibraryBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__LIBRARY_BLOCK, newLibraryBlock, newLibraryBlock));
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
      case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__STATEMENT:
        return basicSetStatement(null, msgs);
      case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__ODE_BLOCK:
        return basicSetOdeBlock(null, msgs);
      case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__LIBRARY_BLOCK:
        return basicSetLibraryBlock(null, msgs);
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
      case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__STATEMENT:
        return getStatement();
      case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__ODE_BLOCK:
        return getOdeBlock();
      case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__LIBRARY_BLOCK:
        return getLibraryBlock();
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
      case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__STATEMENT:
        setStatement((BlockStatement)newValue);
        return;
      case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__ODE_BLOCK:
        setOdeBlock((OdeBlock)newValue);
        return;
      case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__LIBRARY_BLOCK:
        setLibraryBlock((LibraryBlock)newValue);
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
      case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__STATEMENT:
        setStatement((BlockStatement)null);
        return;
      case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__ODE_BLOCK:
        setOdeBlock((OdeBlock)null);
        return;
      case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__LIBRARY_BLOCK:
        setLibraryBlock((LibraryBlock)null);
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
      case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__STATEMENT:
        return statement != null;
      case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__ODE_BLOCK:
        return odeBlock != null;
      case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT__LIBRARY_BLOCK:
        return libraryBlock != null;
    }
    return super.eIsSet(featureID);
  }

} //ModelPredictionBlockStatementImpl
