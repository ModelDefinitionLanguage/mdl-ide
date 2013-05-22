/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Block Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.BlockStatement#getSymbol <em>Symbol</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.BlockStatement#getFunctionCall <em>Function Call</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.BlockStatement#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.BlockStatement#getVerbatimBlock <em>Verbatim Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getBlockStatement()
 * @model
 * @generated
 */
public interface BlockStatement extends EObject
{
  /**
   * Returns the value of the '<em><b>Symbol</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symbol</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symbol</em>' containment reference.
   * @see #setSymbol(SymbolDeclaration)
   * @see org.ddmore.mdl.mdl.MdlPackage#getBlockStatement_Symbol()
   * @model containment="true"
   * @generated
   */
  SymbolDeclaration getSymbol();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.BlockStatement#getSymbol <em>Symbol</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symbol</em>' containment reference.
   * @see #getSymbol()
   * @generated
   */
  void setSymbol(SymbolDeclaration value);

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
   * @see org.ddmore.mdl.mdl.MdlPackage#getBlockStatement_FunctionCall()
   * @model containment="true"
   * @generated
   */
  FunctionCall getFunctionCall();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.BlockStatement#getFunctionCall <em>Function Call</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Function Call</em>' containment reference.
   * @see #getFunctionCall()
   * @generated
   */
  void setFunctionCall(FunctionCall value);

  /**
   * Returns the value of the '<em><b>Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Statement</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Statement</em>' containment reference.
   * @see #setStatement(ConditionalStatement)
   * @see org.ddmore.mdl.mdl.MdlPackage#getBlockStatement_Statement()
   * @model containment="true"
   * @generated
   */
  ConditionalStatement getStatement();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.BlockStatement#getStatement <em>Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Statement</em>' containment reference.
   * @see #getStatement()
   * @generated
   */
  void setStatement(ConditionalStatement value);

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
   * @see org.ddmore.mdl.mdl.MdlPackage#getBlockStatement_VerbatimBlock()
   * @model containment="true"
   * @generated
   */
  VerbatimBlock getVerbatimBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.BlockStatement#getVerbatimBlock <em>Verbatim Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Verbatim Block</em>' containment reference.
   * @see #getVerbatimBlock()
   * @generated
   */
  void setVerbatimBlock(VerbatimBlock value);

} // BlockStatement
