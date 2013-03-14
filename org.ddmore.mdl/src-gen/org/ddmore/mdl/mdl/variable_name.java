/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>variable name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.variable_name#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.variable_name#getSelector <em>Selector</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getvariable_name()
 * @model
 * @generated
 */
public interface variable_name extends EObject
{
  /**
   * Returns the value of the '<em><b>Identifier</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Identifier</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Identifier</em>' attribute list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getvariable_name_Identifier()
   * @model unique="false"
   * @generated
   */
  EList<String> getIdentifier();

  /**
   * Returns the value of the '<em><b>Selector</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.mdl.mdl.selector}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Selector</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Selector</em>' containment reference list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getvariable_name_Selector()
   * @model containment="true"
   * @generated
   */
  EList<selector> getSelector();

} // variable_name
