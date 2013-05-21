/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task Function Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.TaskFunctionBlock#getEstimateBlock <em>Estimate Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.TaskFunctionBlock#getSimulateBlock <em>Simulate Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.TaskFunctionBlock#getExecuteBlock <em>Execute Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getTaskFunctionBlock()
 * @model
 * @generated
 */
public interface TaskFunctionBlock extends EObject
{
  /**
   * Returns the value of the '<em><b>Estimate Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Estimate Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Estimate Block</em>' containment reference.
   * @see #setEstimateBlock(EstimateTask)
   * @see org.ddmore.mdl.mdl.MdlPackage#getTaskFunctionBlock_EstimateBlock()
   * @model containment="true"
   * @generated
   */
  EstimateTask getEstimateBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.TaskFunctionBlock#getEstimateBlock <em>Estimate Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Estimate Block</em>' containment reference.
   * @see #getEstimateBlock()
   * @generated
   */
  void setEstimateBlock(EstimateTask value);

  /**
   * Returns the value of the '<em><b>Simulate Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Simulate Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Simulate Block</em>' containment reference.
   * @see #setSimulateBlock(SimulateTask)
   * @see org.ddmore.mdl.mdl.MdlPackage#getTaskFunctionBlock_SimulateBlock()
   * @model containment="true"
   * @generated
   */
  SimulateTask getSimulateBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.TaskFunctionBlock#getSimulateBlock <em>Simulate Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Simulate Block</em>' containment reference.
   * @see #getSimulateBlock()
   * @generated
   */
  void setSimulateBlock(SimulateTask value);

  /**
   * Returns the value of the '<em><b>Execute Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Execute Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Execute Block</em>' containment reference.
   * @see #setExecuteBlock(ExecuteTask)
   * @see org.ddmore.mdl.mdl.MdlPackage#getTaskFunctionBlock_ExecuteBlock()
   * @model containment="true"
   * @generated
   */
  ExecuteTask getExecuteBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.TaskFunctionBlock#getExecuteBlock <em>Execute Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Execute Block</em>' containment reference.
   * @see #getExecuteBlock()
   * @generated
   */
  void setExecuteBlock(ExecuteTask value);

} // TaskFunctionBlock
