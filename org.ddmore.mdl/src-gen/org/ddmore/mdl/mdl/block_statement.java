/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>block statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.block_statement#getVariable_declaration <em>Variable declaration</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.block_statement#getFunction_call <em>Function call</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.block_statement#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.block_statement#getVerbatim_block <em>Verbatim block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getblock_statement()
 * @model
 * @generated
 */
public interface block_statement extends EObject
{
  /**
   * Returns the value of the '<em><b>Variable declaration</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variable declaration</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variable declaration</em>' containment reference.
   * @see #setVariable_declaration(variable_declaration)
   * @see org.ddmore.mdl.mdl.MdlPackage#getblock_statement_Variable_declaration()
   * @model containment="true"
   * @generated
   */
  variable_declaration getVariable_declaration();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.block_statement#getVariable_declaration <em>Variable declaration</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Variable declaration</em>' containment reference.
   * @see #getVariable_declaration()
   * @generated
   */
  void setVariable_declaration(variable_declaration value);

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
   * @see org.ddmore.mdl.mdl.MdlPackage#getblock_statement_Function_call()
   * @model containment="true"
   * @generated
   */
  function_call getFunction_call();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.block_statement#getFunction_call <em>Function call</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Function call</em>' containment reference.
   * @see #getFunction_call()
   * @generated
   */
  void setFunction_call(function_call value);

  /**
   * Returns the value of the '<em><b>Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Statement</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Statement</em>' containment reference.
   * @see #setStatement(statement)
   * @see org.ddmore.mdl.mdl.MdlPackage#getblock_statement_Statement()
   * @model containment="true"
   * @generated
   */
  statement getStatement();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.block_statement#getStatement <em>Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Statement</em>' containment reference.
   * @see #getStatement()
   * @generated
   */
  void setStatement(statement value);

  /**
   * Returns the value of the '<em><b>Verbatim block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Verbatim block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Verbatim block</em>' containment reference.
   * @see #setVerbatim_block(verbatim_block)
   * @see org.ddmore.mdl.mdl.MdlPackage#getblock_statement_Verbatim_block()
   * @model containment="true"
   * @generated
   */
  verbatim_block getVerbatim_block();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.block_statement#getVerbatim_block <em>Verbatim block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Verbatim block</em>' containment reference.
   * @see #getVerbatim_block()
   * @generated
   */
  void setVerbatim_block(verbatim_block value);

} // block_statement
