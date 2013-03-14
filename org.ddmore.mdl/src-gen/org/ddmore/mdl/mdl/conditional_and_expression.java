/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>conditional and expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.conditional_and_expression#getRelational_expression <em>Relational expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.conditional_and_expression#getOperator <em>Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getconditional_and_expression()
 * @model
 * @generated
 */
public interface conditional_and_expression extends EObject
{
  /**
   * Returns the value of the '<em><b>Relational expression</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.mdl.mdl.relational_expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Relational expression</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Relational expression</em>' containment reference list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getconditional_and_expression_Relational_expression()
   * @model containment="true"
   * @generated
   */
  EList<relational_expression> getRelational_expression();

  /**
   * Returns the value of the '<em><b>Operator</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Operator</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Operator</em>' attribute list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getconditional_and_expression_Operator()
   * @model unique="false"
   * @generated
   */
  EList<String> getOperator();

} // conditional_and_expression
