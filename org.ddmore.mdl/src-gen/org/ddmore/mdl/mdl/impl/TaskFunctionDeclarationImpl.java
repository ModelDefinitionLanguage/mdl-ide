/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.FormalArguments;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.TaskFunctionBody;
import org.ddmore.mdl.mdl.TaskFunctionDeclaration;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task Function Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.TaskFunctionDeclarationImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.TaskFunctionDeclarationImpl#getFormalArguments <em>Formal Arguments</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.TaskFunctionDeclarationImpl#getFunctionBody <em>Function Body</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaskFunctionDeclarationImpl extends MinimalEObjectImpl.Container implements TaskFunctionDeclaration
{
  /**
   * The default value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdentifier()
   * @generated
   * @ordered
   */
  protected static final String IDENTIFIER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdentifier()
   * @generated
   * @ordered
   */
  protected String identifier = IDENTIFIER_EDEFAULT;

  /**
   * The cached value of the '{@link #getFormalArguments() <em>Formal Arguments</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFormalArguments()
   * @generated
   * @ordered
   */
  protected FormalArguments formalArguments;

  /**
   * The cached value of the '{@link #getFunctionBody() <em>Function Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunctionBody()
   * @generated
   * @ordered
   */
  protected TaskFunctionBody functionBody;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TaskFunctionDeclarationImpl()
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
    return MdlPackage.Literals.TASK_FUNCTION_DECLARATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getIdentifier()
  {
    return identifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIdentifier(String newIdentifier)
  {
    String oldIdentifier = identifier;
    identifier = newIdentifier;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_FUNCTION_DECLARATION__IDENTIFIER, oldIdentifier, identifier));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FormalArguments getFormalArguments()
  {
    return formalArguments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFormalArguments(FormalArguments newFormalArguments, NotificationChain msgs)
  {
    FormalArguments oldFormalArguments = formalArguments;
    formalArguments = newFormalArguments;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_FUNCTION_DECLARATION__FORMAL_ARGUMENTS, oldFormalArguments, newFormalArguments);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFormalArguments(FormalArguments newFormalArguments)
  {
    if (newFormalArguments != formalArguments)
    {
      NotificationChain msgs = null;
      if (formalArguments != null)
        msgs = ((InternalEObject)formalArguments).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_FUNCTION_DECLARATION__FORMAL_ARGUMENTS, null, msgs);
      if (newFormalArguments != null)
        msgs = ((InternalEObject)newFormalArguments).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_FUNCTION_DECLARATION__FORMAL_ARGUMENTS, null, msgs);
      msgs = basicSetFormalArguments(newFormalArguments, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_FUNCTION_DECLARATION__FORMAL_ARGUMENTS, newFormalArguments, newFormalArguments));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TaskFunctionBody getFunctionBody()
  {
    return functionBody;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFunctionBody(TaskFunctionBody newFunctionBody, NotificationChain msgs)
  {
    TaskFunctionBody oldFunctionBody = functionBody;
    functionBody = newFunctionBody;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_FUNCTION_DECLARATION__FUNCTION_BODY, oldFunctionBody, newFunctionBody);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFunctionBody(TaskFunctionBody newFunctionBody)
  {
    if (newFunctionBody != functionBody)
    {
      NotificationChain msgs = null;
      if (functionBody != null)
        msgs = ((InternalEObject)functionBody).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_FUNCTION_DECLARATION__FUNCTION_BODY, null, msgs);
      if (newFunctionBody != null)
        msgs = ((InternalEObject)newFunctionBody).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_FUNCTION_DECLARATION__FUNCTION_BODY, null, msgs);
      msgs = basicSetFunctionBody(newFunctionBody, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_FUNCTION_DECLARATION__FUNCTION_BODY, newFunctionBody, newFunctionBody));
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
      case MdlPackage.TASK_FUNCTION_DECLARATION__FORMAL_ARGUMENTS:
        return basicSetFormalArguments(null, msgs);
      case MdlPackage.TASK_FUNCTION_DECLARATION__FUNCTION_BODY:
        return basicSetFunctionBody(null, msgs);
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
      case MdlPackage.TASK_FUNCTION_DECLARATION__IDENTIFIER:
        return getIdentifier();
      case MdlPackage.TASK_FUNCTION_DECLARATION__FORMAL_ARGUMENTS:
        return getFormalArguments();
      case MdlPackage.TASK_FUNCTION_DECLARATION__FUNCTION_BODY:
        return getFunctionBody();
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
      case MdlPackage.TASK_FUNCTION_DECLARATION__IDENTIFIER:
        setIdentifier((String)newValue);
        return;
      case MdlPackage.TASK_FUNCTION_DECLARATION__FORMAL_ARGUMENTS:
        setFormalArguments((FormalArguments)newValue);
        return;
      case MdlPackage.TASK_FUNCTION_DECLARATION__FUNCTION_BODY:
        setFunctionBody((TaskFunctionBody)newValue);
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
      case MdlPackage.TASK_FUNCTION_DECLARATION__IDENTIFIER:
        setIdentifier(IDENTIFIER_EDEFAULT);
        return;
      case MdlPackage.TASK_FUNCTION_DECLARATION__FORMAL_ARGUMENTS:
        setFormalArguments((FormalArguments)null);
        return;
      case MdlPackage.TASK_FUNCTION_DECLARATION__FUNCTION_BODY:
        setFunctionBody((TaskFunctionBody)null);
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
      case MdlPackage.TASK_FUNCTION_DECLARATION__IDENTIFIER:
        return IDENTIFIER_EDEFAULT == null ? identifier != null : !IDENTIFIER_EDEFAULT.equals(identifier);
      case MdlPackage.TASK_FUNCTION_DECLARATION__FORMAL_ARGUMENTS:
        return formalArguments != null;
      case MdlPackage.TASK_FUNCTION_DECLARATION__FUNCTION_BODY:
        return functionBody != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (identifier: ");
    result.append(identifier);
    result.append(')');
    return result.toString();
  }

} //TaskFunctionDeclarationImpl
