/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>input variables block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.input_variables_block#getIndentifier <em>Indentifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.input_variables_block#getBlock <em>Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getinput_variables_block()
 * @model
 * @generated
 */
public interface input_variables_block extends EObject
{
  /**
   * Returns the value of the '<em><b>Indentifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Indentifier</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Indentifier</em>' attribute.
   * @see #setIndentifier(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getinput_variables_block_Indentifier()
   * @model
   * @generated
   */
  String getIndentifier();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.input_variables_block#getIndentifier <em>Indentifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Indentifier</em>' attribute.
   * @see #getIndentifier()
   * @generated
   */
  void setIndentifier(String value);

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
   * @see org.ddmore.mdl.mdl.MdlPackage#getinput_variables_block_Block()
   * @model containment="true"
   * @generated
   */
  block getBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.input_variables_block#getBlock <em>Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Block</em>' containment reference.
   * @see #getBlock()
   * @generated
   */
  void setBlock(block value);

} // input_variables_block
