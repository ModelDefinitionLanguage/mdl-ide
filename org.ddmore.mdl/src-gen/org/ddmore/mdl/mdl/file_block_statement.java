/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>file block statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.file_block_statement#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.file_block_statement#getInline_block <em>Inline block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.file_block_statement#getDesign_block <em>Design block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.file_block_statement#getRsscript_block <em>Rsscript block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getfile_block_statement()
 * @model
 * @generated
 */
public interface file_block_statement extends EObject
{
  /**
   * Returns the value of the '<em><b>Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Statement</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Statement</em>' containment reference.
   * @see #setStatement(block_statement)
   * @see org.ddmore.mdl.mdl.MdlPackage#getfile_block_statement_Statement()
   * @model containment="true"
   * @generated
   */
  block_statement getStatement();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.file_block_statement#getStatement <em>Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Statement</em>' containment reference.
   * @see #getStatement()
   * @generated
   */
  void setStatement(block_statement value);

  /**
   * Returns the value of the '<em><b>Inline block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Inline block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Inline block</em>' containment reference.
   * @see #setInline_block(inline_block)
   * @see org.ddmore.mdl.mdl.MdlPackage#getfile_block_statement_Inline_block()
   * @model containment="true"
   * @generated
   */
  inline_block getInline_block();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.file_block_statement#getInline_block <em>Inline block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Inline block</em>' containment reference.
   * @see #getInline_block()
   * @generated
   */
  void setInline_block(inline_block value);

  /**
   * Returns the value of the '<em><b>Design block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Design block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Design block</em>' containment reference.
   * @see #setDesign_block(design_block)
   * @see org.ddmore.mdl.mdl.MdlPackage#getfile_block_statement_Design_block()
   * @model containment="true"
   * @generated
   */
  design_block getDesign_block();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.file_block_statement#getDesign_block <em>Design block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Design block</em>' containment reference.
   * @see #getDesign_block()
   * @generated
   */
  void setDesign_block(design_block value);

  /**
   * Returns the value of the '<em><b>Rsscript block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rsscript block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rsscript block</em>' containment reference.
   * @see #setRsscript_block(rsscript_block)
   * @see org.ddmore.mdl.mdl.MdlPackage#getfile_block_statement_Rsscript_block()
   * @model containment="true"
   * @generated
   */
  rsscript_block getRsscript_block();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.file_block_statement#getRsscript_block <em>Rsscript block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rsscript block</em>' containment reference.
   * @see #getRsscript_block()
   * @generated
   */
  void setRsscript_block(rsscript_block value);

} // file_block_statement
