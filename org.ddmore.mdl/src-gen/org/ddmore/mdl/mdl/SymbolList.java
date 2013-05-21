/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Symbol List</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.SymbolList#getSymbols <em>Symbols</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getSymbolList()
 * @model
 * @generated
 */
public interface SymbolList extends EObject
{
  /**
   * Returns the value of the '<em><b>Symbols</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.mdl.mdl.FullyQualifiedSymbolName}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symbols</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symbols</em>' containment reference list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getSymbolList_Symbols()
   * @model containment="true"
   * @generated
   */
  EList<FullyQualifiedSymbolName> getSymbols();

} // SymbolList
