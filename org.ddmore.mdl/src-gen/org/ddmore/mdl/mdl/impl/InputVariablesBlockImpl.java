/**
 */
package org.ddmore.mdl.mdl.impl;

import java.util.Collection;

import org.ddmore.mdl.mdl.InputVariablesBlock;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.SymbolDeclaration;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Input Variables Block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.InputVariablesBlockImpl#getIndentifier <em>Indentifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.InputVariablesBlockImpl#getVariables <em>Variables</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InputVariablesBlockImpl extends MinimalEObjectImpl.Container implements InputVariablesBlock
{
  /**
   * The default value of the '{@link #getIndentifier() <em>Indentifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIndentifier()
   * @generated
   * @ordered
   */
  protected static final String INDENTIFIER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIndentifier() <em>Indentifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIndentifier()
   * @generated
   * @ordered
   */
  protected String indentifier = INDENTIFIER_EDEFAULT;

  /**
   * The cached value of the '{@link #getVariables() <em>Variables</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariables()
   * @generated
   * @ordered
   */
  protected EList<SymbolDeclaration> variables;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected InputVariablesBlockImpl()
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
    return MdlPackage.Literals.INPUT_VARIABLES_BLOCK;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getIndentifier()
  {
    return indentifier;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIndentifier(String newIndentifier)
  {
    String oldIndentifier = indentifier;
    indentifier = newIndentifier;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.INPUT_VARIABLES_BLOCK__INDENTIFIER, oldIndentifier, indentifier));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<SymbolDeclaration> getVariables()
  {
    if (variables == null)
    {
      variables = new EObjectContainmentEList<SymbolDeclaration>(SymbolDeclaration.class, this, MdlPackage.INPUT_VARIABLES_BLOCK__VARIABLES);
    }
    return variables;
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
      case MdlPackage.INPUT_VARIABLES_BLOCK__VARIABLES:
        return ((InternalEList<?>)getVariables()).basicRemove(otherEnd, msgs);
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
      case MdlPackage.INPUT_VARIABLES_BLOCK__INDENTIFIER:
        return getIndentifier();
      case MdlPackage.INPUT_VARIABLES_BLOCK__VARIABLES:
        return getVariables();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case MdlPackage.INPUT_VARIABLES_BLOCK__INDENTIFIER:
        setIndentifier((String)newValue);
        return;
      case MdlPackage.INPUT_VARIABLES_BLOCK__VARIABLES:
        getVariables().clear();
        getVariables().addAll((Collection<? extends SymbolDeclaration>)newValue);
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
      case MdlPackage.INPUT_VARIABLES_BLOCK__INDENTIFIER:
        setIndentifier(INDENTIFIER_EDEFAULT);
        return;
      case MdlPackage.INPUT_VARIABLES_BLOCK__VARIABLES:
        getVariables().clear();
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
      case MdlPackage.INPUT_VARIABLES_BLOCK__INDENTIFIER:
        return INDENTIFIER_EDEFAULT == null ? indentifier != null : !INDENTIFIER_EDEFAULT.equals(indentifier);
      case MdlPackage.INPUT_VARIABLES_BLOCK__VARIABLES:
        return variables != null && !variables.isEmpty();
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
    result.append(" (indentifier: ");
    result.append(indentifier);
    result.append(')');
    return result.toString();
  }

} //InputVariablesBlockImpl
