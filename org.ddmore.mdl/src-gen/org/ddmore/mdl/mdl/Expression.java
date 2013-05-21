/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.Expression#getConditionalExpression <em>Conditional Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getExpression()
 * @model
 * @generated
 */
public interface Expression extends EObject
{
  /**
   * Returns the value of the '<em><b>Conditional Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Conditional Expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Conditional Expression</em>' containment reference.
   * @see #setConditionalExpression(ConditionalExpression)
   * @see org.ddmore.mdl.mdl.MdlPackage#getExpression_ConditionalExpression()
   * @model containment="true"
   * @generated
   */
  ConditionalExpression getConditionalExpression();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.Expression#getConditionalExpression <em>Conditional Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Conditional Expression</em>' containment reference.
   * @see #getConditionalExpression()
   * @generated
   */
  void setConditionalExpression(ConditionalExpression value);

} // Expression
