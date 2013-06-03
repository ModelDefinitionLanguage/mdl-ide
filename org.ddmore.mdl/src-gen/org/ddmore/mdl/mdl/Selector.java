/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Selector</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.Selector#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.Selector#getSelector <em>Selector</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getSelector()
 * @model
 * @generated
 */
public interface Selector extends EObject
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
   * @see org.ddmore.mdl.mdl.MdlPackage#getSelector_Identifier()
   * @model
   * @generated
   */
  String getIdentifier();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.Selector#getIdentifier <em>Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Identifier</em>' attribute.
   * @see #getIdentifier()
   * @generated
   */
  void setIdentifier(String value);

  /**
   * Returns the value of the '<em><b>Selector</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Selector</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Selector</em>' attribute.
   * @see #setSelector(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getSelector_Selector()
   * @model
   * @generated
   */
  String getSelector();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.Selector#getSelector <em>Selector</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Selector</em>' attribute.
   * @see #getSelector()
   * @generated
   */
  void setSelector(String value);

} // Selector
