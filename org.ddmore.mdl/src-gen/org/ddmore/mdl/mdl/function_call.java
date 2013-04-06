/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>function call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.function_call#getFunct_name <em>Funct name</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.function_call#getArguments <em>Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getfunction_call()
 * @model
 * @generated
 */
public interface function_call extends EObject
{
  /**
   * Returns the value of the '<em><b>Funct name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Funct name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Funct name</em>' attribute.
   * @see #setFunct_name(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getfunction_call_Funct_name()
   * @model
   * @generated
   */
  String getFunct_name();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.function_call#getFunct_name <em>Funct name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Funct name</em>' attribute.
   * @see #getFunct_name()
   * @generated
   */
  void setFunct_name(String value);

  /**
   * Returns the value of the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Arguments</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arguments</em>' containment reference.
   * @see #setArguments(arguments)
   * @see org.ddmore.mdl.mdl.MdlPackage#getfunction_call_Arguments()
   * @model containment="true"
   * @generated
   */
  arguments getArguments();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.function_call#getArguments <em>Arguments</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Arguments</em>' containment reference.
   * @see #getArguments()
   * @generated
   */
  void setArguments(arguments value);

} // function_call
