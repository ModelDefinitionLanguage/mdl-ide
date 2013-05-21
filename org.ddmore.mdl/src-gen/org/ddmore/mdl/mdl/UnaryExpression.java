/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unary Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.UnaryExpression#getOperator <em>Operator</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.UnaryExpression#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.UnaryExpression#getParExpression <em>Par Expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.UnaryExpression#getPrimary <em>Primary</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getUnaryExpression()
 * @model
 * @generated
 */
public interface UnaryExpression extends EObject
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
   * @see org.ddmore.mdl.mdl.MdlPackage#getUnaryExpression_Operator()
   * @model
   * @generated
   */
  String getOperator();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.UnaryExpression#getOperator <em>Operator</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Operator</em>' attribute.
   * @see #getOperator()
   * @generated
   */
  void setOperator(String value);

  /**
   * Returns the value of the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression</em>' containment reference.
   * @see #setExpression(UnaryExpression)
   * @see org.ddmore.mdl.mdl.MdlPackage#getUnaryExpression_Expression()
   * @model containment="true"
   * @generated
   */
  UnaryExpression getExpression();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.UnaryExpression#getExpression <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' containment reference.
   * @see #getExpression()
   * @generated
   */
  void setExpression(UnaryExpression value);

  /**
   * Returns the value of the '<em><b>Par Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Par Expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Par Expression</em>' containment reference.
   * @see #setParExpression(ParExpression)
   * @see org.ddmore.mdl.mdl.MdlPackage#getUnaryExpression_ParExpression()
   * @model containment="true"
   * @generated
   */
  ParExpression getParExpression();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.UnaryExpression#getParExpression <em>Par Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Par Expression</em>' containment reference.
   * @see #getParExpression()
   * @generated
   */
  void setParExpression(ParExpression value);

  /**
   * Returns the value of the '<em><b>Primary</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Primary</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Primary</em>' containment reference.
   * @see #setPrimary(Primary)
   * @see org.ddmore.mdl.mdl.MdlPackage#getUnaryExpression_Primary()
   * @model containment="true"
   * @generated
   */
  Primary getPrimary();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.UnaryExpression#getPrimary <em>Primary</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Primary</em>' containment reference.
   * @see #getPrimary()
   * @generated
   */
  void setPrimary(Primary value);

} // UnaryExpression
