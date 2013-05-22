/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Any Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.AnyExpression#getExpression <em>Expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.AnyExpression#getList <em>List</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.AnyExpression#getOdeList <em>Ode List</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.AnyExpression#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getAnyExpression()
 * @model
 * @generated
 */
public interface AnyExpression extends EObject
{
  /**
   * Returns the value of the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression</em>' containment reference.
   * @see #setExpression(Expression)
   * @see org.ddmore.mdl.mdl.MdlPackage#getAnyExpression_Expression()
   * @model containment="true"
   * @generated
   */
  Expression getExpression();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.AnyExpression#getExpression <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' containment reference.
   * @see #getExpression()
   * @generated
   */
  void setExpression(Expression value);

  /**
   * Returns the value of the '<em><b>List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>List</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>List</em>' containment reference.
   * @see #setList(List)
   * @see org.ddmore.mdl.mdl.MdlPackage#getAnyExpression_List()
   * @model containment="true"
   * @generated
   */
  List getList();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.AnyExpression#getList <em>List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>List</em>' containment reference.
   * @see #getList()
   * @generated
   */
  void setList(List value);

  /**
   * Returns the value of the '<em><b>Ode List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ode List</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ode List</em>' containment reference.
   * @see #setOdeList(OdeList)
   * @see org.ddmore.mdl.mdl.MdlPackage#getAnyExpression_OdeList()
   * @model containment="true"
   * @generated
   */
  OdeList getOdeList();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.AnyExpression#getOdeList <em>Ode List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ode List</em>' containment reference.
   * @see #getOdeList()
   * @generated
   */
  void setOdeList(OdeList value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' containment reference.
   * @see #setType(EnumType)
   * @see org.ddmore.mdl.mdl.MdlPackage#getAnyExpression_Type()
   * @model containment="true"
   * @generated
   */
  EnumType getType();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.AnyExpression#getType <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' containment reference.
   * @see #getType()
   * @generated
   */
  void setType(EnumType value);

} // AnyExpression
