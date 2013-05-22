/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Design Block Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.DesignBlockStatement#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.DesignBlockStatement#getArguments <em>Arguments</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.DesignBlockStatement#getExpression <em>Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getDesignBlockStatement()
 * @model
 * @generated
 */
public interface DesignBlockStatement extends EObject
{
  /**
   * Returns the value of the '<em><b>Identifier</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Identifier</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Identifier</em>' containment reference.
   * @see #setIdentifier(FullyQualifiedSymbolName)
   * @see org.ddmore.mdl.mdl.MdlPackage#getDesignBlockStatement_Identifier()
   * @model containment="true"
   * @generated
   */
  FullyQualifiedSymbolName getIdentifier();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.DesignBlockStatement#getIdentifier <em>Identifier</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Identifier</em>' containment reference.
   * @see #getIdentifier()
   * @generated
   */
  void setIdentifier(FullyQualifiedSymbolName value);

  /**
   * Returns the value of the '<em><b>Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Arguments</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Arguments</em>' containment reference.
   * @see #setArguments(VariableList)
   * @see org.ddmore.mdl.mdl.MdlPackage#getDesignBlockStatement_Arguments()
   * @model containment="true"
   * @generated
   */
  VariableList getArguments();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.DesignBlockStatement#getArguments <em>Arguments</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Arguments</em>' containment reference.
   * @see #getArguments()
   * @generated
   */
  void setArguments(VariableList value);

  /**
   * Returns the value of the '<em><b>Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Expression</em>' containment reference.
   * @see #setExpression(AnyExpression)
   * @see org.ddmore.mdl.mdl.MdlPackage#getDesignBlockStatement_Expression()
   * @model containment="true"
   * @generated
   */
  AnyExpression getExpression();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.DesignBlockStatement#getExpression <em>Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Expression</em>' containment reference.
   * @see #getExpression()
   * @generated
   */
  void setExpression(AnyExpression value);

} // DesignBlockStatement
