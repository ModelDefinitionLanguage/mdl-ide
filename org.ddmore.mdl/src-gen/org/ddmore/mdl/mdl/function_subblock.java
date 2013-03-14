/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>function subblock</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.function_subblock#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.function_subblock#getEstimate_defn <em>Estimate defn</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.function_subblock#getSimulate_defn <em>Simulate defn</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getfunction_subblock()
 * @model
 * @generated
 */
public interface function_subblock extends EObject
{
  /**
   * Returns the value of the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Identifier</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Identifier</em>' attribute.
   * @see #setIdentifier(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getfunction_subblock_Identifier()
   * @model
   * @generated
   */
  String getIdentifier();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.function_subblock#getIdentifier <em>Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Identifier</em>' attribute.
   * @see #getIdentifier()
   * @generated
   */
  void setIdentifier(String value);

  /**
   * Returns the value of the '<em><b>Estimate defn</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Estimate defn</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Estimate defn</em>' containment reference.
   * @see #setEstimate_defn(block)
   * @see org.ddmore.mdl.mdl.MdlPackage#getfunction_subblock_Estimate_defn()
   * @model containment="true"
   * @generated
   */
  block getEstimate_defn();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.function_subblock#getEstimate_defn <em>Estimate defn</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Estimate defn</em>' containment reference.
   * @see #getEstimate_defn()
   * @generated
   */
  void setEstimate_defn(block value);

  /**
   * Returns the value of the '<em><b>Simulate defn</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Simulate defn</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Simulate defn</em>' containment reference.
   * @see #setSimulate_defn(block)
   * @see org.ddmore.mdl.mdl.MdlPackage#getfunction_subblock_Simulate_defn()
   * @model containment="true"
   * @generated
   */
  block getSimulate_defn();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.function_subblock#getSimulate_defn <em>Simulate defn</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Simulate defn</em>' containment reference.
   * @see #getSimulate_defn()
   * @generated
   */
  void setSimulate_defn(block value);

} // function_subblock
