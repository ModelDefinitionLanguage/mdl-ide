/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.DesignBlock;
import org.ddmore.mdl.mdl.FileBlockStatement;
import org.ddmore.mdl.mdl.InlineBlock;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.RScriptBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>File Block Statement</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.FileBlockStatementImpl#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.FileBlockStatementImpl#getInlineBlock <em>Inline Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.FileBlockStatementImpl#getDesignBlock <em>Design Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.FileBlockStatementImpl#getRscriptBlock <em>Rscript Block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FileBlockStatementImpl extends MinimalEObjectImpl.Container implements FileBlockStatement
{
  /**
   * The cached value of the '{@link #getVariable() <em>Variable</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariable()
   * @generated
   * @ordered
   */
  protected SymbolDeclaration variable;

  /**
   * The cached value of the '{@link #getInlineBlock() <em>Inline Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInlineBlock()
   * @generated
   * @ordered
   */
  protected InlineBlock inlineBlock;

  /**
   * The cached value of the '{@link #getDesignBlock() <em>Design Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDesignBlock()
   * @generated
   * @ordered
   */
  protected DesignBlock designBlock;

  /**
   * The cached value of the '{@link #getRscriptBlock() <em>Rscript Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRscriptBlock()
   * @generated
   * @ordered
   */
  protected RScriptBlock rscriptBlock;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FileBlockStatementImpl()
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
  public SymbolDeclaration getVariable()
  {
    return variable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVariable(SymbolDeclaration newVariable, NotificationChain msgs)
  {
    SymbolDeclaration oldVariable = variable;
    variable = newVariable;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.FILE_BLOCK_STATEMENT__VARIABLE, oldVariable, newVariable);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVariable(SymbolDeclaration newVariable)
  {
    if (newVariable != variable)
    {
      NotificationChain msgs = null;
      if (variable != null)
        msgs = ((InternalEObject)variable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FILE_BLOCK_STATEMENT__VARIABLE, null, msgs);
      if (newVariable != null)
        msgs = ((InternalEObject)newVariable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FILE_BLOCK_STATEMENT__VARIABLE, null, msgs);
      msgs = basicSetVariable(newVariable, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.FILE_BLOCK_STATEMENT__VARIABLE, newVariable, newVariable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public InlineBlock getInlineBlock()
  {
    return inlineBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetInlineBlock(InlineBlock newInlineBlock, NotificationChain msgs)
  {
    InlineBlock oldInlineBlock = inlineBlock;
    inlineBlock = newInlineBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK, oldInlineBlock, newInlineBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInlineBlock(InlineBlock newInlineBlock)
  {
    if (newInlineBlock != inlineBlock)
    {
      NotificationChain msgs = null;
      if (inlineBlock != null)
        msgs = ((InternalEObject)inlineBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK, null, msgs);
      if (newInlineBlock != null)
        msgs = ((InternalEObject)newInlineBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK, null, msgs);
      msgs = basicSetInlineBlock(newInlineBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK, newInlineBlock, newInlineBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DesignBlock getDesignBlock()
  {
    return designBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDesignBlock(DesignBlock newDesignBlock, NotificationChain msgs)
  {
    DesignBlock oldDesignBlock = designBlock;
    designBlock = newDesignBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK, oldDesignBlock, newDesignBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDesignBlock(DesignBlock newDesignBlock)
  {
    if (newDesignBlock != designBlock)
    {
      NotificationChain msgs = null;
      if (designBlock != null)
        msgs = ((InternalEObject)designBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK, null, msgs);
      if (newDesignBlock != null)
        msgs = ((InternalEObject)newDesignBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK, null, msgs);
      msgs = basicSetDesignBlock(newDesignBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK, newDesignBlock, newDesignBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RScriptBlock getRscriptBlock()
  {
    return rscriptBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRscriptBlock(RScriptBlock newRscriptBlock, NotificationChain msgs)
  {
    RScriptBlock oldRscriptBlock = rscriptBlock;
    rscriptBlock = newRscriptBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.FILE_BLOCK_STATEMENT__RSCRIPT_BLOCK, oldRscriptBlock, newRscriptBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRscriptBlock(RScriptBlock newRscriptBlock)
  {
    if (newRscriptBlock != rscriptBlock)
    {
      NotificationChain msgs = null;
      if (rscriptBlock != null)
        msgs = ((InternalEObject)rscriptBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FILE_BLOCK_STATEMENT__RSCRIPT_BLOCK, null, msgs);
      if (newRscriptBlock != null)
        msgs = ((InternalEObject)newRscriptBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.FILE_BLOCK_STATEMENT__RSCRIPT_BLOCK, null, msgs);
      msgs = basicSetRscriptBlock(newRscriptBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.FILE_BLOCK_STATEMENT__RSCRIPT_BLOCK, newRscriptBlock, newRscriptBlock));
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
      case MdlPackage.FILE_BLOCK_STATEMENT__VARIABLE:
        return basicSetVariable(null, msgs);
      case MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK:
        return basicSetInlineBlock(null, msgs);
      case MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK:
        return basicSetDesignBlock(null, msgs);
      case MdlPackage.FILE_BLOCK_STATEMENT__RSCRIPT_BLOCK:
        return basicSetRscriptBlock(null, msgs);
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
      case MdlPackage.FILE_BLOCK_STATEMENT__VARIABLE:
        return getVariable();
      case MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK:
        return getInlineBlock();
      case MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK:
        return getDesignBlock();
      case MdlPackage.FILE_BLOCK_STATEMENT__RSCRIPT_BLOCK:
        return getRscriptBlock();
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
      case MdlPackage.FILE_BLOCK_STATEMENT__VARIABLE:
        setVariable((SymbolDeclaration)newValue);
        return;
      case MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK:
        setInlineBlock((InlineBlock)newValue);
        return;
      case MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK:
        setDesignBlock((DesignBlock)newValue);
        return;
      case MdlPackage.FILE_BLOCK_STATEMENT__RSCRIPT_BLOCK:
        setRscriptBlock((RScriptBlock)newValue);
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
      case MdlPackage.FILE_BLOCK_STATEMENT__VARIABLE:
        setVariable((SymbolDeclaration)null);
        return;
      case MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK:
        setInlineBlock((InlineBlock)null);
        return;
      case MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK:
        setDesignBlock((DesignBlock)null);
        return;
      case MdlPackage.FILE_BLOCK_STATEMENT__RSCRIPT_BLOCK:
        setRscriptBlock((RScriptBlock)null);
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
      case MdlPackage.FILE_BLOCK_STATEMENT__VARIABLE:
        return variable != null;
      case MdlPackage.FILE_BLOCK_STATEMENT__INLINE_BLOCK:
        return inlineBlock != null;
      case MdlPackage.FILE_BLOCK_STATEMENT__DESIGN_BLOCK:
        return designBlock != null;
      case MdlPackage.FILE_BLOCK_STATEMENT__RSCRIPT_BLOCK:
        return rscriptBlock != null;
    }
    return super.eIsSet(featureID);
  }

} //FileBlockStatementImpl
