/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Object Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.ParameterObjectBlock#getStructuralBlock <em>Structural Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.ParameterObjectBlock#getVariabilityBlock <em>Variability Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.ParameterObjectBlock#getPriorBlock <em>Prior Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.ParameterObjectBlock#getTargetBlock <em>Target Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.ParameterObjectBlock#getImportBlock <em>Import Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getParameterObjectBlock()
 * @model
 * @generated
 */
public interface ParameterObjectBlock extends EObject
{
  /**
   * Returns the value of the '<em><b>Structural Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Structural Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Structural Block</em>' containment reference.
   * @see #setStructuralBlock(StructuralBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getParameterObjectBlock_StructuralBlock()
   * @model containment="true"
   * @generated
   */
  StructuralBlock getStructuralBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.ParameterObjectBlock#getStructuralBlock <em>Structural Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Structural Block</em>' containment reference.
   * @see #getStructuralBlock()
   * @generated
   */
  void setStructuralBlock(StructuralBlock value);

  /**
   * Returns the value of the '<em><b>Variability Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variability Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variability Block</em>' containment reference.
   * @see #setVariabilityBlock(VariabilityBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getParameterObjectBlock_VariabilityBlock()
   * @model containment="true"
   * @generated
   */
  VariabilityBlock getVariabilityBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.ParameterObjectBlock#getVariabilityBlock <em>Variability Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Variability Block</em>' containment reference.
   * @see #getVariabilityBlock()
   * @generated
   */
  void setVariabilityBlock(VariabilityBlock value);

  /**
   * Returns the value of the '<em><b>Prior Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Prior Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Prior Block</em>' containment reference.
   * @see #setPriorBlock(PriorParametersBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getParameterObjectBlock_PriorBlock()
   * @model containment="true"
   * @generated
   */
  PriorParametersBlock getPriorBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.ParameterObjectBlock#getPriorBlock <em>Prior Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Prior Block</em>' containment reference.
   * @see #getPriorBlock()
   * @generated
   */
  void setPriorBlock(PriorParametersBlock value);

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
   * @see org.ddmore.mdl.mdl.MdlPackage#getParameterObjectBlock_TargetBlock()
   * @model containment="true"
   * @generated
   */
  TargetBlock getTargetBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.ParameterObjectBlock#getTargetBlock <em>Target Block</em>}' containment reference.
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
   * @see org.ddmore.mdl.mdl.MdlPackage#getParameterObjectBlock_ImportBlock()
   * @model containment="true"
   * @generated
   */
  ImportBlock getImportBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.ParameterObjectBlock#getImportBlock <em>Import Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Import Block</em>' containment reference.
   * @see #getImportBlock()
   * @generated
   */
  void setImportBlock(ImportBlock value);

} // ParameterObjectBlock
