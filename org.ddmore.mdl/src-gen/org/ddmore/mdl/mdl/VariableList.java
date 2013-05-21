/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variable List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.VariableList#getIdentifiers <em>Identifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getVariableList()
 * @model
 * @generated
 */
public interface VariableList extends EObject
{
  /**
   * Returns the value of the '<em><b>Identifiers</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.mdl.mdl.FullyQualifiedSymbolName}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Identifiers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Identifiers</em>' containment reference list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getVariableList_Identifiers()
   * @model containment="true"
   * @generated
   */
  EList<FullyQualifiedSymbolName> getIdentifiers();

} // VariableList
