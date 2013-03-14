/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>conditional expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.conditional_expression#getConditional_or_expression <em>Conditional or expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.conditional_expression#getExpression1 <em>Expression1</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.conditional_expression#getExpression2 <em>Expression2</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getconditional_expression()
 * @model
 * @generated
 */
public interface conditional_expression extends EObject
{
  /**
   * Returns the value of the '<em><b>Conditional or expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Conditional or expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Conditional or expression</em>' containment reference.
   * @see #setConditional_or_expression(conditional_or_expression)
   * @see org.ddmore.mdl.mdl.MdlPackage#getconditional_expression_Conditional_or_expression()
   * @model containment="true"
   * @generated
   */
  conditional_or_expression getConditional_or_expression();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.conditional_expression#getConditional_or_expression <em>Conditional or expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Conditional or expression</em>' containment reference.
   * @see #getConditional_or_expression()
   * @generated
   */
  void setConditional_or_expression(conditional_or_expression value);

  /**
   * Returns the value of the '<em><b>Expression1</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expression1</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression1</em>' containment reference.
   * @see #setExpression1(expression)
   * @see org.ddmore.mdl.mdl.MdlPackage#getconditional_expression_Expression1()
   * @model containment="true"
   * @generated
   */
  expression getExpression1();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.conditional_expression#getExpression1 <em>Expression1</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression1</em>' containment reference.
   * @see #getExpression1()
   * @generated
   */
  void setExpression1(expression value);

  /**
   * Returns the value of the '<em><b>Expression2</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expression2</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression2</em>' containment reference.
   * @see #setExpression2(expression)
   * @see org.ddmore.mdl.mdl.MdlPackage#getconditional_expression_Expression2()
   * @model containment="true"
   * @generated
   */
  expression getExpression2();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.conditional_expression#getExpression2 <em>Expression2</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression2</em>' containment reference.
   * @see #getExpression2()
   * @generated
   */
  void setExpression2(expression value);

} // conditional_expression
