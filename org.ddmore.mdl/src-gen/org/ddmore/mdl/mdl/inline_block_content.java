/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>inline block content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.inline_block_content#getIdentifiers <em>Identifiers</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.inline_block_content#getValues <em>Values</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getinline_block_content()
 * @model
 * @generated
 */
public interface inline_block_content extends EObject
{
  /**
   * Returns the value of the '<em><b>Identifiers</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Identifiers</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Identifiers</em>' attribute list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getinline_block_content_Identifiers()
   * @model unique="false"
   * @generated
   */
  EList<String> getIdentifiers();

  /**
   * Returns the value of the '<em><b>Values</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Values</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Values</em>' attribute list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getinline_block_content_Values()
   * @model unique="false"
   * @generated
   */
  EList<String> getValues();

} // inline_block_content
