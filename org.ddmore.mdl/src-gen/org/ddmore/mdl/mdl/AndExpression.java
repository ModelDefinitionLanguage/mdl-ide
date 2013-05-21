/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>And Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.AndExpression#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.AndExpression#getOperator <em>Operator</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getAndExpression()
 * @model
 * @generated
 */
public interface AndExpression extends EObject
{
  /**
   * Returns the value of the '<em><b>Expression</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.mdl.mdl.LogicalExpression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expression</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression</em>' containment reference list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getAndExpression_Expression()
   * @model containment="true"
   * @generated
   */
  EList<LogicalExpression> getExpression();

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
   * @see org.ddmore.mdl.mdl.MdlPackage#getAndExpression_Operator()
   * @model unique="false"
   * @generated
   */
  EList<String> getOperator();

} // AndExpression
