/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>power expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.power_expression#getUnary_expression <em>Unary expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.power_expression#getPower_op <em>Power op</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getpower_expression()
 * @model
 * @generated
 */
public interface power_expression extends EObject
{
  /**
   * Returns the value of the '<em><b>Unary expression</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.mdl.mdl.unary_expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unary expression</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unary expression</em>' containment reference list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getpower_expression_Unary_expression()
   * @model containment="true"
   * @generated
   */
  EList<unary_expression> getUnary_expression();

  /**
   * Returns the value of the '<em><b>Power op</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Power op</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Power op</em>' attribute list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getpower_expression_Power_op()
   * @model unique="false"
   * @generated
   */
  EList<String> getPower_op();

} // power_expression
