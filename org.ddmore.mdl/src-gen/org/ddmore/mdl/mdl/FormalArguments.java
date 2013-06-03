/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Formal Arguments</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.FormalArguments#getArguments <em>Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getFormalArguments()
 * @model
 * @generated
 */
public interface FormalArguments extends EObject
{
  /**
   * Returns the value of the '<em><b>Arguments</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.mdl.mdl.FormalArgument}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Arguments</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arguments</em>' containment reference list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getFormalArguments_Arguments()
   * @model containment="true"
   * @generated
   */
  EList<FormalArgument> getArguments();

} // FormalArguments
