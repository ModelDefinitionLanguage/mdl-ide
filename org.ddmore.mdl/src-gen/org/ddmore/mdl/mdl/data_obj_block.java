/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>data obj block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.data_obj_block#getHeader_block <em>Header block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.data_obj_block#getFile_block <em>File block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getdata_obj_block()
 * @model
 * @generated
 */
public interface data_obj_block extends EObject
{
  /**
   * Returns the value of the '<em><b>Header block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Header block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Header block</em>' containment reference.
   * @see #setHeader_block(header_block)
   * @see org.ddmore.mdl.mdl.MdlPackage#getdata_obj_block_Header_block()
   * @model containment="true"
   * @generated
   */
  header_block getHeader_block();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.data_obj_block#getHeader_block <em>Header block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Header block</em>' containment reference.
   * @see #getHeader_block()
   * @generated
   */
  void setHeader_block(header_block value);

  /**
   * Returns the value of the '<em><b>File block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>File block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>File block</em>' containment reference.
   * @see #setFile_block(file_block)
   * @see org.ddmore.mdl.mdl.MdlPackage#getdata_obj_block_File_block()
   * @model containment="true"
   * @generated
   */
  file_block getFile_block();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.data_obj_block#getFile_block <em>File block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>File block</em>' containment reference.
   * @see #getFile_block()
   * @generated
   */
  void setFile_block(file_block value);

} // data_obj_block
