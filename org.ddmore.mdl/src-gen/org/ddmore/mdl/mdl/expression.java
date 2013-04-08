/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.expression#getConditional_expression <em>Conditional expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.expression#getString_expression <em>String expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getexpression()
 * @model
 * @generated
 */
public interface expression extends EObject
{
  /**
   * Returns the value of the '<em><b>Conditional expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Conditional expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Conditional expression</em>' containment reference.
   * @see #setConditional_expression(conditional_expression)
   * @see org.ddmore.mdl.mdl.MdlPackage#getexpression_Conditional_expression()
   * @model containment="true"
   * @generated
   */
  conditional_expression getConditional_expression();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.expression#getConditional_expression <em>Conditional expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Conditional expression</em>' containment reference.
   * @see #getConditional_expression()
   * @generated
   */
  void setConditional_expression(conditional_expression value);

  /**
   * Returns the value of the '<em><b>String expression</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>String expression</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>String expression</em>' attribute list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getexpression_String_expression()
   * @model unique="false"
   * @generated
   */
  EList<String> getString_expression();

} // expression
