/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>variable declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.variable_declaration#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.variable_declaration#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.variable_declaration#getRandom_list <em>Random list</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getvariable_declaration()
 * @model
 * @generated
 */
public interface variable_declaration extends EObject
{
  /**
   * Returns the value of the '<em><b>Identifier</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Identifier</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Identifier</em>' containment reference.
   * @see #setIdentifier(variable_name)
   * @see org.ddmore.mdl.mdl.MdlPackage#getvariable_declaration_Identifier()
   * @model containment="true"
   * @generated
   */
  variable_name getIdentifier();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.variable_declaration#getIdentifier <em>Identifier</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Identifier</em>' containment reference.
   * @see #getIdentifier()
   * @generated
   */
  void setIdentifier(variable_name value);

  /**
   * Returns the value of the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression</em>' containment reference.
   * @see #setExpression(any_expression)
   * @see org.ddmore.mdl.mdl.MdlPackage#getvariable_declaration_Expression()
   * @model containment="true"
   * @generated
   */
  any_expression getExpression();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.variable_declaration#getExpression <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' containment reference.
   * @see #getExpression()
   * @generated
   */
  void setExpression(any_expression value);

  /**
   * Returns the value of the '<em><b>Random list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Random list</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Random list</em>' containment reference.
   * @see #setRandom_list(random_list)
   * @see org.ddmore.mdl.mdl.MdlPackage#getvariable_declaration_Random_list()
   * @model containment="true"
   * @generated
   */
  random_list getRandom_list();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.variable_declaration#getRandom_list <em>Random list</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Random list</em>' containment reference.
   * @see #getRandom_list()
   * @generated
   */
  void setRandom_list(random_list value);

} // variable_declaration
