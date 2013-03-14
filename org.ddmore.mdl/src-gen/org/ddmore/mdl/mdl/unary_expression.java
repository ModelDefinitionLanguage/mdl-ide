/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>unary expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.unary_expression#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.unary_expression#getUnary_expression <em>Unary expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.unary_expression#getPar_expression <em>Par expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.unary_expression#getFunction_call <em>Function call</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.unary_expression#getPrimary <em>Primary</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getunary_expression()
 * @model
 * @generated
 */
public interface unary_expression extends EObject
{
  /**
   * Returns the value of the '<em><b>Operator</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Operator</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Operator</em>' attribute.
   * @see #setOperator(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getunary_expression_Operator()
   * @model
   * @generated
   */
  String getOperator();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.unary_expression#getOperator <em>Operator</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Operator</em>' attribute.
   * @see #getOperator()
   * @generated
   */
  void setOperator(String value);

  /**
   * Returns the value of the '<em><b>Unary expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unary expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unary expression</em>' containment reference.
   * @see #setUnary_expression(unary_expression)
   * @see org.ddmore.mdl.mdl.MdlPackage#getunary_expression_Unary_expression()
   * @model containment="true"
   * @generated
   */
  unary_expression getUnary_expression();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.unary_expression#getUnary_expression <em>Unary expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unary expression</em>' containment reference.
   * @see #getUnary_expression()
   * @generated
   */
  void setUnary_expression(unary_expression value);

  /**
   * Returns the value of the '<em><b>Par expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Par expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Par expression</em>' containment reference.
   * @see #setPar_expression(par_expression)
   * @see org.ddmore.mdl.mdl.MdlPackage#getunary_expression_Par_expression()
   * @model containment="true"
   * @generated
   */
  par_expression getPar_expression();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.unary_expression#getPar_expression <em>Par expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Par expression</em>' containment reference.
   * @see #getPar_expression()
   * @generated
   */
  void setPar_expression(par_expression value);

  /**
   * Returns the value of the '<em><b>Function call</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function call</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function call</em>' containment reference.
   * @see #setFunction_call(function_call)
   * @see org.ddmore.mdl.mdl.MdlPackage#getunary_expression_Function_call()
   * @model containment="true"
   * @generated
   */
  function_call getFunction_call();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.unary_expression#getFunction_call <em>Function call</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Function call</em>' containment reference.
   * @see #getFunction_call()
   * @generated
   */
  void setFunction_call(function_call value);

  /**
   * Returns the value of the '<em><b>Primary</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Primary</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Primary</em>' containment reference.
   * @see #setPrimary(primary)
   * @see org.ddmore.mdl.mdl.MdlPackage#getunary_expression_Primary()
   * @model containment="true"
   * @generated
   */
  primary getPrimary();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.unary_expression#getPrimary <em>Primary</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Primary</em>' containment reference.
   * @see #getPrimary()
   * @generated
   */
  void setPrimary(primary value);

} // unary_expression
