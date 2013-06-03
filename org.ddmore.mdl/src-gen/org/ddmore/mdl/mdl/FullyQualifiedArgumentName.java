/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fully Qualified Argument Name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.FullyQualifiedArgumentName#getParent <em>Parent</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.FullyQualifiedArgumentName#getSelectors <em>Selectors</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getFullyQualifiedArgumentName()
 * @model
 * @generated
 */
public interface FullyQualifiedArgumentName extends EObject
{
  /**
   * Returns the value of the '<em><b>Parent</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parent</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parent</em>' containment reference.
   * @see #setParent(FullyQualifiedSymbolName)
   * @see org.ddmore.mdl.mdl.MdlPackage#getFullyQualifiedArgumentName_Parent()
   * @model containment="true"
   * @generated
   */
  FullyQualifiedSymbolName getParent();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.FullyQualifiedArgumentName#getParent <em>Parent</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parent</em>' containment reference.
   * @see #getParent()
   * @generated
   */
  void setParent(FullyQualifiedSymbolName value);

  /**
   * Returns the value of the '<em><b>Selectors</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.mdl.mdl.Selector}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Selectors</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Selectors</em>' containment reference list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getFullyQualifiedArgumentName_Selectors()
   * @model containment="true"
   * @generated
   */
  EList<Selector> getSelectors();

} // FullyQualifiedArgumentName
