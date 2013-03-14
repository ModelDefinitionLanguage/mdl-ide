/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>model block statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.model_block_statement#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.model_block_statement#getOde_block <em>Ode block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.model_block_statement#getLibrary_block <em>Library block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getmodel_block_statement()
 * @model
 * @generated
 */
public interface model_block_statement extends EObject
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
   * @see org.ddmore.mdl.mdl.MdlPackage#getmodel_block_statement_Statement()
   * @model containment="true"
   * @generated
   */
  block_statement getStatement();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.model_block_statement#getStatement <em>Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Statement</em>' containment reference.
   * @see #getStatement()
   * @generated
   */
  void setStatement(block_statement value);

  /**
   * Returns the value of the '<em><b>Ode block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ode block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ode block</em>' containment reference.
   * @see #setOde_block(ode_block)
   * @see org.ddmore.mdl.mdl.MdlPackage#getmodel_block_statement_Ode_block()
   * @model containment="true"
   * @generated
   */
  ode_block getOde_block();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.model_block_statement#getOde_block <em>Ode block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ode block</em>' containment reference.
   * @see #getOde_block()
   * @generated
   */
  void setOde_block(ode_block value);

  /**
   * Returns the value of the '<em><b>Library block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Library block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Library block</em>' containment reference.
   * @see #setLibrary_block(library_block)
   * @see org.ddmore.mdl.mdl.MdlPackage#getmodel_block_statement_Library_block()
   * @model containment="true"
   * @generated
   */
  library_block getLibrary_block();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.model_block_statement#getLibrary_block <em>Library block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Library block</em>' containment reference.
   * @see #getLibrary_block()
   * @generated
   */
  void setLibrary_block(library_block value);

} // model_block_statement
