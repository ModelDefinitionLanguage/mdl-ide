/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>additive expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.additive_expression#getMultiplicative_expression <em>Multiplicative expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.additive_expression#getAdditive_op <em>Additive op</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getadditive_expression()
 * @model
 * @generated
 */
public interface additive_expression extends EObject
{
  /**
   * Returns the value of the '<em><b>Multiplicative expression</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.mdl.mdl.multiplicative_expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Multiplicative expression</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Multiplicative expression</em>' containment reference list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getadditive_expression_Multiplicative_expression()
   * @model containment="true"
   * @generated
   */
  EList<multiplicative_expression> getMultiplicative_expression();

  /**
   * Returns the value of the '<em><b>Additive op</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Additive op</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Additive op</em>' attribute list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getadditive_expression_Additive_op()
   * @model unique="false"
   * @generated
   */
  EList<String> getAdditive_op();

} // additive_expression
