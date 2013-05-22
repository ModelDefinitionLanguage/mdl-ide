/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Group Variables Block Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.GroupVariablesBlockStatement#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.GroupVariablesBlockStatement#getMixtureBlock <em>Mixture Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getGroupVariablesBlockStatement()
 * @model
 * @generated
 */
public interface GroupVariablesBlockStatement extends EObject
{
  /**
   * Returns the value of the '<em><b>Statement</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Statement</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Statement</em>' containment reference.
   * @see #setStatement(BlockStatement)
   * @see org.ddmore.mdl.mdl.MdlPackage#getGroupVariablesBlockStatement_Statement()
   * @model containment="true"
   * @generated
   */
  BlockStatement getStatement();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.GroupVariablesBlockStatement#getStatement <em>Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Statement</em>' containment reference.
   * @see #getStatement()
   * @generated
   */
  void setStatement(BlockStatement value);

  /**
   * Returns the value of the '<em><b>Mixture Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mixture Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mixture Block</em>' containment reference.
   * @see #setMixtureBlock(MixtureBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getGroupVariablesBlockStatement_MixtureBlock()
   * @model containment="true"
   * @generated
   */
  MixtureBlock getMixtureBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.GroupVariablesBlockStatement#getMixtureBlock <em>Mixture Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mixture Block</em>' containment reference.
   * @see #getMixtureBlock()
   * @generated
   */
  void setMixtureBlock(MixtureBlock value);

} // GroupVariablesBlockStatement
