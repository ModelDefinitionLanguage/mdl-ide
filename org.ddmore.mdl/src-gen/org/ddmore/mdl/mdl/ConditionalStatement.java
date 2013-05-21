/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conditional Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.ConditionalStatement#getParExpression <em>Par Expression</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.ConditionalStatement#getIfStatement <em>If Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.ConditionalStatement#getIfBlock <em>If Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.ConditionalStatement#getElseStatement <em>Else Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.ConditionalStatement#getElseBlock <em>Else Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getConditionalStatement()
 * @model
 * @generated
 */
public interface ConditionalStatement extends EObject
{
  /**
   * Returns the value of the '<em><b>Par Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Par Expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Par Expression</em>' containment reference.
   * @see #setParExpression(ParExpression)
   * @see org.ddmore.mdl.mdl.MdlPackage#getConditionalStatement_ParExpression()
   * @model containment="true"
   * @generated
   */
  ParExpression getParExpression();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.ConditionalStatement#getParExpression <em>Par Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Par Expression</em>' containment reference.
   * @see #getParExpression()
   * @generated
   */
  void setParExpression(ParExpression value);

  /**
   * Returns the value of the '<em><b>If Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>If Statement</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>If Statement</em>' containment reference.
   * @see #setIfStatement(BlockStatement)
   * @see org.ddmore.mdl.mdl.MdlPackage#getConditionalStatement_IfStatement()
   * @model containment="true"
   * @generated
   */
  BlockStatement getIfStatement();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.ConditionalStatement#getIfStatement <em>If Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>If Statement</em>' containment reference.
   * @see #getIfStatement()
   * @generated
   */
  void setIfStatement(BlockStatement value);

  /**
   * Returns the value of the '<em><b>If Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>If Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>If Block</em>' containment reference.
   * @see #setIfBlock(Block)
   * @see org.ddmore.mdl.mdl.MdlPackage#getConditionalStatement_IfBlock()
   * @model containment="true"
   * @generated
   */
  Block getIfBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.ConditionalStatement#getIfBlock <em>If Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>If Block</em>' containment reference.
   * @see #getIfBlock()
   * @generated
   */
  void setIfBlock(Block value);

  /**
   * Returns the value of the '<em><b>Else Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Else Statement</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Else Statement</em>' containment reference.
   * @see #setElseStatement(BlockStatement)
   * @see org.ddmore.mdl.mdl.MdlPackage#getConditionalStatement_ElseStatement()
   * @model containment="true"
   * @generated
   */
  BlockStatement getElseStatement();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.ConditionalStatement#getElseStatement <em>Else Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Else Statement</em>' containment reference.
   * @see #getElseStatement()
   * @generated
   */
  void setElseStatement(BlockStatement value);

  /**
   * Returns the value of the '<em><b>Else Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Else Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Else Block</em>' containment reference.
   * @see #setElseBlock(Block)
   * @see org.ddmore.mdl.mdl.MdlPackage#getConditionalStatement_ElseBlock()
   * @model containment="true"
   * @generated
   */
  Block getElseBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.ConditionalStatement#getElseBlock <em>Else Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Else Block</em>' containment reference.
   * @see #getElseBlock()
   * @generated
   */
  void setElseBlock(Block value);

} // ConditionalStatement
