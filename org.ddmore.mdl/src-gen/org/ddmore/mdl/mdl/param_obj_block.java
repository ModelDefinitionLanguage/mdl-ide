/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>param obj block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.param_obj_block#getStructural_block <em>Structural block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.param_obj_block#getVariability_block <em>Variability block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getparam_obj_block()
 * @model
 * @generated
 */
public interface param_obj_block extends EObject
{
  /**
   * Returns the value of the '<em><b>Structural block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Structural block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Structural block</em>' containment reference.
   * @see #setStructural_block(structural_block)
   * @see org.ddmore.mdl.mdl.MdlPackage#getparam_obj_block_Structural_block()
   * @model containment="true"
   * @generated
   */
  structural_block getStructural_block();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.param_obj_block#getStructural_block <em>Structural block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Structural block</em>' containment reference.
   * @see #getStructural_block()
   * @generated
   */
  void setStructural_block(structural_block value);

  /**
   * Returns the value of the '<em><b>Variability block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variability block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variability block</em>' containment reference.
   * @see #setVariability_block(variability_block)
   * @see org.ddmore.mdl.mdl.MdlPackage#getparam_obj_block_Variability_block()
   * @model containment="true"
   * @generated
   */
  variability_block getVariability_block();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.param_obj_block#getVariability_block <em>Variability block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Variability block</em>' containment reference.
   * @see #getVariability_block()
   * @generated
   */
  void setVariability_block(variability_block value);

} // param_obj_block
