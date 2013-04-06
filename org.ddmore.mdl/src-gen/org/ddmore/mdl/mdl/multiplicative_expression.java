/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>multiplicative expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.multiplicative_expression#getPower_expression <em>Power expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.multiplicative_expression#getMultiplicative_op <em>Multiplicative op</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getmultiplicative_expression()
 * @model
 * @generated
 */
public interface multiplicative_expression extends EObject
{
  /**
   * Returns the value of the '<em><b>Power expression</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.mdl.mdl.power_expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Power expression</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Power expression</em>' containment reference list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getmultiplicative_expression_Power_expression()
   * @model containment="true"
   * @generated
   */
  EList<power_expression> getPower_expression();

  /**
   * Returns the value of the '<em><b>Multiplicative op</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Multiplicative op</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Multiplicative op</em>' attribute list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getmultiplicative_expression_Multiplicative_op()
   * @model unique="false"
   * @generated
   */
  EList<String> getMultiplicative_op();

} // multiplicative_expression
