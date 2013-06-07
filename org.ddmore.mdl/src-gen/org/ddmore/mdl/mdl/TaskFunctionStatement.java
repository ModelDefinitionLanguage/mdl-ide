/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task Function Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.TaskFunctionStatement#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.TaskFunctionStatement#getTargetBlock <em>Target Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getTaskFunctionStatement()
 * @model
 * @generated
 */
public interface TaskFunctionStatement extends EObject
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
   * @see org.ddmore.mdl.mdl.MdlPackage#getTaskFunctionStatement_Statement()
   * @model containment="true"
   * @generated
   */
  BlockStatement getStatement();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.TaskFunctionStatement#getStatement <em>Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Statement</em>' containment reference.
   * @see #getStatement()
   * @generated
   */
  void setStatement(BlockStatement value);

  /**
   * Returns the value of the '<em><b>Target Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target Block</em>' containment reference.
   * @see #setTargetBlock(TargetBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getTaskFunctionStatement_TargetBlock()
   * @model containment="true"
   * @generated
   */
  TargetBlock getTargetBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.TaskFunctionStatement#getTargetBlock <em>Target Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target Block</em>' containment reference.
   * @see #getTargetBlock()
   * @generated
   */
  void setTargetBlock(TargetBlock value);

} // TaskFunctionStatement
