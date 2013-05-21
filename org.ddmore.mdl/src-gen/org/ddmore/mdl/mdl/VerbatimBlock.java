/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Verbatim Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.VerbatimBlock#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.VerbatimBlock#getBlock <em>Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.VerbatimBlock#getExternalCode <em>External Code</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getVerbatimBlock()
 * @model
 * @generated
 */
public interface VerbatimBlock extends EObject
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
   * @see org.ddmore.mdl.mdl.MdlPackage#getVerbatimBlock_Identifier()
   * @model
   * @generated
   */
  String getIdentifier();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.VerbatimBlock#getIdentifier <em>Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Identifier</em>' attribute.
   * @see #getIdentifier()
   * @generated
   */
  void setIdentifier(String value);

  /**
   * Returns the value of the '<em><b>Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Block</em>' containment reference.
   * @see #setBlock(TargetBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getVerbatimBlock_Block()
   * @model containment="true"
   * @generated
   */
  TargetBlock getBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.VerbatimBlock#getBlock <em>Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Block</em>' containment reference.
   * @see #getBlock()
   * @generated
   */
  void setBlock(TargetBlock value);

  /**
   * Returns the value of the '<em><b>External Code</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>External Code</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>External Code</em>' attribute.
   * @see #setExternalCode(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getVerbatimBlock_ExternalCode()
   * @model
   * @generated
   */
  String getExternalCode();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.VerbatimBlock#getExternalCode <em>External Code</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>External Code</em>' attribute.
   * @see #getExternalCode()
   * @generated
   */
  void setExternalCode(String value);

} // VerbatimBlock
