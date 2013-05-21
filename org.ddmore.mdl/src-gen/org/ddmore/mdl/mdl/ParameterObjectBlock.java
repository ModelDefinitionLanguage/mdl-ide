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
 *   <li>{@link org.ddmore.mdl.mdl.ParameterObjectBlock#getVerbatimBlock <em>Verbatim Block</em>}</li>
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
   * Returns the value of the '<em><b>Verbatim Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Verbatim Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Verbatim Block</em>' containment reference.
   * @see #setVerbatimBlock(VerbatimBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getParameterObjectBlock_VerbatimBlock()
   * @model containment="true"
   * @generated
   */
  VerbatimBlock getVerbatimBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.ParameterObjectBlock#getVerbatimBlock <em>Verbatim Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Verbatim Block</em>' containment reference.
   * @see #getVerbatimBlock()
   * @generated
   */
  void setVerbatimBlock(VerbatimBlock value);

} // ParameterObjectBlock
