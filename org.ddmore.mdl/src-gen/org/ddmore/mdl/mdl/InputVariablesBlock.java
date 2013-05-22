/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Input Variables Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.InputVariablesBlock#getIndentifier <em>Indentifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.InputVariablesBlock#getVariables <em>Variables</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getInputVariablesBlock()
 * @model
 * @generated
 */
public interface InputVariablesBlock extends EObject
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
   * @see org.ddmore.mdl.mdl.MdlPackage#getInputVariablesBlock_Indentifier()
   * @model
   * @generated
   */
  String getIndentifier();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.InputVariablesBlock#getIndentifier <em>Indentifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Indentifier</em>' attribute.
   * @see #getIndentifier()
   * @generated
   */
  void setIndentifier(String value);

  /**
   * Returns the value of the '<em><b>Variables</b></em>' containment reference list.
   * The list contents are of type {@link org.ddmore.mdl.mdl.SymbolDeclaration}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variables</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variables</em>' containment reference list.
   * @see org.ddmore.mdl.mdl.MdlPackage#getInputVariablesBlock_Variables()
   * @model containment="true"
   * @generated
   */
  EList<SymbolDeclaration> getVariables();

} // InputVariablesBlock
