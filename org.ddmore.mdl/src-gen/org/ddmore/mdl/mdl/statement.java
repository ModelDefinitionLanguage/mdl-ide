/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.statement#getBlock <em>Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.statement#getPar_expression <em>Par expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.statement#getIf_statement <em>If statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.statement#getElse_statement <em>Else statement</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getstatement()
 * @model
 * @generated
 */
public interface statement extends EObject
{
  /**
   * Returns the value of the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Block</em>' containment reference.
   * @see #setBlock(block)
   * @see org.ddmore.mdl.mdl.MdlPackage#getstatement_Block()
   * @model containment="true"
   * @generated
   */
  block getBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.statement#getBlock <em>Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Block</em>' containment reference.
   * @see #getBlock()
   * @generated
   */
  void setBlock(block value);

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
   * @see org.ddmore.mdl.mdl.MdlPackage#getstatement_Par_expression()
   * @model containment="true"
   * @generated
   */
  par_expression getPar_expression();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.statement#getPar_expression <em>Par expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Par expression</em>' containment reference.
   * @see #getPar_expression()
   * @generated
   */
  void setPar_expression(par_expression value);

  /**
   * Returns the value of the '<em><b>If statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>If statement</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>If statement</em>' containment reference.
   * @see #setIf_statement(block_statement)
   * @see org.ddmore.mdl.mdl.MdlPackage#getstatement_If_statement()
   * @model containment="true"
   * @generated
   */
  block_statement getIf_statement();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.statement#getIf_statement <em>If statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>If statement</em>' containment reference.
   * @see #getIf_statement()
   * @generated
   */
  void setIf_statement(block_statement value);

  /**
   * Returns the value of the '<em><b>Else statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Else statement</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Else statement</em>' containment reference.
   * @see #setElse_statement(block_statement)
   * @see org.ddmore.mdl.mdl.MdlPackage#getstatement_Else_statement()
   * @model containment="true"
   * @generated
   */
  block_statement getElse_statement();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.statement#getElse_statement <em>Else statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Else statement</em>' containment reference.
   * @see #getElse_statement()
   * @generated
   */
  void setElse_statement(block_statement value);

} // statement
