/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Primary</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.Primary#getFunctionCall <em>Function Call</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.Primary#getNumber <em>Number</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.Primary#getSymbol <em>Symbol</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.Primary#getAttribute <em>Attribute</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.Primary#getVector <em>Vector</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getPrimary()
 * @model
 * @generated
 */
public interface Primary extends EObject
{
  /**
   * Returns the value of the '<em><b>Function Call</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function Call</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function Call</em>' containment reference.
   * @see #setFunctionCall(FunctionCall)
   * @see org.ddmore.mdl.mdl.MdlPackage#getPrimary_FunctionCall()
   * @model containment="true"
   * @generated
   */
  FunctionCall getFunctionCall();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.Primary#getFunctionCall <em>Function Call</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Function Call</em>' containment reference.
   * @see #getFunctionCall()
   * @generated
   */
  void setFunctionCall(FunctionCall value);

  /**
   * Returns the value of the '<em><b>Number</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Number</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Number</em>' attribute.
   * @see #setNumber(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getPrimary_Number()
   * @model
   * @generated
   */
  String getNumber();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.Primary#getNumber <em>Number</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Number</em>' attribute.
   * @see #getNumber()
   * @generated
   */
  void setNumber(String value);

  /**
   * Returns the value of the '<em><b>Symbol</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symbol</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symbol</em>' containment reference.
   * @see #setSymbol(FullyQualifiedSymbolName)
   * @see org.ddmore.mdl.mdl.MdlPackage#getPrimary_Symbol()
   * @model containment="true"
   * @generated
   */
  FullyQualifiedSymbolName getSymbol();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.Primary#getSymbol <em>Symbol</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symbol</em>' containment reference.
   * @see #getSymbol()
   * @generated
   */
  void setSymbol(FullyQualifiedSymbolName value);

  /**
   * Returns the value of the '<em><b>Attribute</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Attribute</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Attribute</em>' containment reference.
   * @see #setAttribute(FullyQualifiedArgumentName)
   * @see org.ddmore.mdl.mdl.MdlPackage#getPrimary_Attribute()
   * @model containment="true"
   * @generated
   */
  FullyQualifiedArgumentName getAttribute();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.Primary#getAttribute <em>Attribute</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Attribute</em>' containment reference.
   * @see #getAttribute()
   * @generated
   */
  void setAttribute(FullyQualifiedArgumentName value);

  /**
   * Returns the value of the '<em><b>Vector</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Vector</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Vector</em>' containment reference.
   * @see #setVector(Vector)
   * @see org.ddmore.mdl.mdl.MdlPackage#getPrimary_Vector()
   * @model containment="true"
   * @generated
   */
  Vector getVector();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.Primary#getVector <em>Vector</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Vector</em>' containment reference.
   * @see #getVector()
   * @generated
   */
  void setVector(Vector value);

} // Primary
