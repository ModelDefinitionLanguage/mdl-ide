/**
 */
package org.ddmore.mdl.mdl.impl;

import java.util.Collection;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.inline_block_content;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>inline block content</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.inline_block_contentImpl#getIdentifiers <em>Identifiers</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.inline_block_contentImpl#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class inline_block_contentImpl extends MinimalEObjectImpl.Container implements inline_block_content
{
  /**
   * The cached value of the '{@link #getIdentifiers() <em>Identifiers</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdentifiers()
   * @generated
   * @ordered
   */
  protected EList<String> identifiers;

  /**
   * The cached value of the '{@link #getValues() <em>Values</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getValues()
   * @generated
   * @ordered
   */
  protected EList<String> values;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected inline_block_contentImpl()
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
    return MdlPackage.Literals.INLINE_BLOCK_CONTENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getIdentifiers()
  {
    if (identifiers == null)
    {
      identifiers = new EDataTypeEList<String>(String.class, this, MdlPackage.INLINE_BLOCK_CONTENT__IDENTIFIERS);
    }
    return identifiers;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getValues()
  {
    if (values == null)
    {
      values = new EDataTypeEList<String>(String.class, this, MdlPackage.INLINE_BLOCK_CONTENT__VALUES);
    }
    return values;
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
      case MdlPackage.INLINE_BLOCK_CONTENT__IDENTIFIERS:
        return getIdentifiers();
      case MdlPackage.INLINE_BLOCK_CONTENT__VALUES:
        return getValues();
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
      case MdlPackage.INLINE_BLOCK_CONTENT__IDENTIFIERS:
        getIdentifiers().clear();
        getIdentifiers().addAll((Collection<? extends String>)newValue);
        return;
      case MdlPackage.INLINE_BLOCK_CONTENT__VALUES:
        getValues().clear();
        getValues().addAll((Collection<? extends String>)newValue);
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
      case MdlPackage.INLINE_BLOCK_CONTENT__IDENTIFIERS:
        getIdentifiers().clear();
        return;
      case MdlPackage.INLINE_BLOCK_CONTENT__VALUES:
        getValues().clear();
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
      case MdlPackage.INLINE_BLOCK_CONTENT__IDENTIFIERS:
        return identifiers != null && !identifiers.isEmpty();
      case MdlPackage.INLINE_BLOCK_CONTENT__VALUES:
        return values != null && !values.isEmpty();
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
    result.append(" (identifiers: ");
    result.append(identifiers);
    result.append(", values: ");
    result.append(values);
    result.append(')');
    return result.toString();
  }

} //inline_block_contentImpl
