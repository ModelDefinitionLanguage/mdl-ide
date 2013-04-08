/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>relational expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.relational_expression#getNegation <em>Negation</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.relational_expression#getBoolean <em>Boolean</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.relational_expression#getAdditive_expression <em>Additive expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.relational_expression#getRelational_op <em>Relational op</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getrelational_expression()
 * @model
 * @generated
 */
public interface relational_expression extends EObject
{
  /**
   * Returns the value of the '<em><b>Negation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Negation</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Negation</em>' attribute.
   * @see #setNegation(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getrelational_expression_Negation()
   * @model
   * @generated
   */
  String getNegation();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.relational_expression#getNegation <em>Negation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Negation</em>' attribute.
   * @see #getNegation()
   * @generated
   */
  void setNegation(String value);

  /**
   * Returns the value of the '<em><b>Boolean</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Boolean</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Boolean</em>' attribute.
   * @see #setBoolean(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getrelational_expression_Boolean()
   * @model
   * @generated
   */
  String getBoolean();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.relational_expression#getBoolean <em>Boolean</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Boolean</em>' attribute.
   * @see #getBoolean()
   * @generated
   */
  void setBoolean(String value);

  /**
   * Returns the value of the '<em><b>Additive expression</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.mdl.mdl.additive_expression}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Additive expression</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Additive expression</em>' containment reference list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getrelational_expression_Additive_expression()
   * @model containment="true"
   * @generated
   */
  EList<additive_expression> getAdditive_expression();

  /**
   * Returns the value of the '<em><b>Relational op</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Relational op</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Relational op</em>' attribute list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getrelational_expression_Relational_op()
   * @model unique="false"
   * @generated
   */
  EList<String> getRelational_op();

} // relational_expression
