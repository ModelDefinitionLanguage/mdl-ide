/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>function declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.function_declaration#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.function_declaration#getFormal_arguments <em>Formal arguments</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.function_declaration#getFunction_body <em>Function body</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getfunction_declaration()
 * @model
 * @generated
 */
public interface function_declaration extends EObject
{
  /**
   * Returns the value of the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Identifier</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Identifier</em>' attribute.
   * @see #setIdentifier(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getfunction_declaration_Identifier()
   * @model
   * @generated
   */
  String getIdentifier();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.function_declaration#getIdentifier <em>Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Identifier</em>' attribute.
   * @see #getIdentifier()
   * @generated
   */
  void setIdentifier(String value);

  /**
   * Returns the value of the '<em><b>Formal arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Formal arguments</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Formal arguments</em>' containment reference.
   * @see #setFormal_arguments(formal_arguments)
   * @see org.ddmore.mdl.mdl.MdlPackage#getfunction_declaration_Formal_arguments()
   * @model containment="true"
   * @generated
   */
  formal_arguments getFormal_arguments();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.function_declaration#getFormal_arguments <em>Formal arguments</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Formal arguments</em>' containment reference.
   * @see #getFormal_arguments()
   * @generated
   */
  void setFormal_arguments(formal_arguments value);

  /**
   * Returns the value of the '<em><b>Function body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function body</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function body</em>' containment reference.
   * @see #setFunction_body(function_body)
   * @see org.ddmore.mdl.mdl.MdlPackage#getfunction_declaration_Function_body()
   * @model containment="true"
   * @generated
   */
  function_body getFunction_body();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.function_declaration#getFunction_body <em>Function body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Function body</em>' containment reference.
   * @see #getFunction_body()
   * @generated
   */
  void setFunction_body(function_body value);

} // function_declaration
