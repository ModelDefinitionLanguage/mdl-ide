/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Object Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.DataObjectBlock#getHeaderBlock <em>Header Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.DataObjectBlock#getFileBlock <em>File Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.DataObjectBlock#getTargetBlock <em>Target Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.DataObjectBlock#getImportBlock <em>Import Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getDataObjectBlock()
 * @model
 * @generated
 */
public interface DataObjectBlock extends EObject
{
  /**
   * Returns the value of the '<em><b>Header Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Header Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Header Block</em>' containment reference.
   * @see #setHeaderBlock(HeaderBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getDataObjectBlock_HeaderBlock()
   * @model containment="true"
   * @generated
   */
  HeaderBlock getHeaderBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.DataObjectBlock#getHeaderBlock <em>Header Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Header Block</em>' containment reference.
   * @see #getHeaderBlock()
   * @generated
   */
  void setHeaderBlock(HeaderBlock value);

  /**
   * Returns the value of the '<em><b>File Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>File Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>File Block</em>' containment reference.
   * @see #setFileBlock(FileBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getDataObjectBlock_FileBlock()
   * @model containment="true"
   * @generated
   */
  FileBlock getFileBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.DataObjectBlock#getFileBlock <em>File Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>File Block</em>' containment reference.
   * @see #getFileBlock()
   * @generated
   */
  void setFileBlock(FileBlock value);

  /**
   * Returns the value of the '<em><b>Target Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Block</em>' containment reference.
   * @see #setTargetBlock(TargetBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getDataObjectBlock_TargetBlock()
   * @model containment="true"
   * @generated
   */
  TargetBlock getTargetBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.DataObjectBlock#getTargetBlock <em>Target Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Block</em>' containment reference.
   * @see #getTargetBlock()
   * @generated
   */
  void setTargetBlock(TargetBlock value);

  /**
   * Returns the value of the '<em><b>Import Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Import Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Import Block</em>' containment reference.
   * @see #setImportBlock(ImportBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getDataObjectBlock_ImportBlock()
   * @model containment="true"
   * @generated
   */
  ImportBlock getImportBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.DataObjectBlock#getImportBlock <em>Import Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Import Block</em>' containment reference.
   * @see #getImportBlock()
   * @generated
   */
  void setImportBlock(ImportBlock value);

} // DataObjectBlock
