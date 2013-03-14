/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>any expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.any_expression#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.any_expression#getList <em>List</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.any_expression#getOde_list <em>Ode list</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.any_expression#getRandom_list <em>Random list</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getany_expression()
 * @model
 * @generated
 */
public interface any_expression extends EObject
{
  /**
   * Returns the value of the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression</em>' containment reference.
   * @see #setExpression(expression)
   * @see org.ddmore.mdl.mdl.MdlPackage#getany_expression_Expression()
   * @model containment="true"
   * @generated
   */
  expression getExpression();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.any_expression#getExpression <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' containment reference.
   * @see #getExpression()
   * @generated
   */
  void setExpression(expression value);

  /**
   * Returns the value of the '<em><b>List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>List</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>List</em>' containment reference.
   * @see #setList(list)
   * @see org.ddmore.mdl.mdl.MdlPackage#getany_expression_List()
   * @model containment="true"
   * @generated
   */
  list getList();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.any_expression#getList <em>List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>List</em>' containment reference.
   * @see #getList()
   * @generated
   */
  void setList(list value);

  /**
   * Returns the value of the '<em><b>Ode list</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ode list</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ode list</em>' containment reference.
   * @see #setOde_list(ode_list)
   * @see org.ddmore.mdl.mdl.MdlPackage#getany_expression_Ode_list()
   * @model containment="true"
   * @generated
   */
  ode_list getOde_list();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.any_expression#getOde_list <em>Ode list</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ode list</em>' containment reference.
   * @see #getOde_list()
   * @generated
   */
  void setOde_list(ode_list value);

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
   * @see org.ddmore.mdl.mdl.MdlPackage#getany_expression_Random_list()
   * @model containment="true"
   * @generated
   */
  random_list getRandom_list();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.any_expression#getRandom_list <em>Random list</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Random list</em>' containment reference.
   * @see #getRandom_list()
   * @generated
   */
  void setRandom_list(random_list value);

} // any_expression
