/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>variability block statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.variability_block_statement#getBlock_statement <em>Block statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.variability_block_statement#getBlock_block <em>Block block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.variability_block_statement#getDiag_block <em>Diag block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getvariability_block_statement()
 * @model
 * @generated
 */
public interface variability_block_statement extends EObject
{
  /**
   * Returns the value of the '<em><b>Block statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Block statement</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Block statement</em>' containment reference.
   * @see #setBlock_statement(block_statement)
   * @see org.ddmore.mdl.mdl.MdlPackage#getvariability_block_statement_Block_statement()
   * @model containment="true"
   * @generated
   */
  block_statement getBlock_statement();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.variability_block_statement#getBlock_statement <em>Block statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Block statement</em>' containment reference.
   * @see #getBlock_statement()
   * @generated
   */
  void setBlock_statement(block_statement value);

  /**
   * Returns the value of the '<em><b>Block block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Block block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Block block</em>' containment reference.
   * @see #setBlock_block(block_subblock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getvariability_block_statement_Block_block()
   * @model containment="true"
   * @generated
   */
  block_subblock getBlock_block();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.variability_block_statement#getBlock_block <em>Block block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Block block</em>' containment reference.
   * @see #getBlock_block()
   * @generated
   */
  void setBlock_block(block_subblock value);

  /**
   * Returns the value of the '<em><b>Diag block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Diag block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Diag block</em>' containment reference.
   * @see #setDiag_block(diag_subblock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getvariability_block_statement_Diag_block()
   * @model containment="true"
   * @generated
   */
  diag_subblock getDiag_block();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.variability_block_statement#getDiag_block <em>Diag block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Diag block</em>' containment reference.
   * @see #getDiag_block()
   * @generated
   */
  void setDiag_block(diag_subblock value);

} // variability_block_statement
