/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>task obj block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.task_obj_block#getFunction_declaration <em>Function declaration</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.task_obj_block#getParameters_block <em>Parameters block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.task_obj_block#getData_block <em>Data block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#gettask_obj_block()
 * @model
 * @generated
 */
public interface task_obj_block extends EObject
{
  /**
   * Returns the value of the '<em><b>Function declaration</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function declaration</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function declaration</em>' containment reference.
   * @see #setFunction_declaration(function_declaration)
   * @see org.ddmore.mdl.mdl.MdlPackage#gettask_obj_block_Function_declaration()
   * @model containment="true"
   * @generated
   */
  function_declaration getFunction_declaration();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.task_obj_block#getFunction_declaration <em>Function declaration</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Function declaration</em>' containment reference.
   * @see #getFunction_declaration()
   * @generated
   */
  void setFunction_declaration(function_declaration value);

  /**
   * Returns the value of the '<em><b>Parameters block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameters block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameters block</em>' containment reference.
   * @see #setParameters_block(parameters_block)
   * @see org.ddmore.mdl.mdl.MdlPackage#gettask_obj_block_Parameters_block()
   * @model containment="true"
   * @generated
   */
  parameters_block getParameters_block();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.task_obj_block#getParameters_block <em>Parameters block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameters block</em>' containment reference.
   * @see #getParameters_block()
   * @generated
   */
  void setParameters_block(parameters_block value);

  /**
   * Returns the value of the '<em><b>Data block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Data block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data block</em>' containment reference.
   * @see #setData_block(data_block)
   * @see org.ddmore.mdl.mdl.MdlPackage#gettask_obj_block_Data_block()
   * @model containment="true"
   * @generated
   */
  data_block getData_block();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.task_obj_block#getData_block <em>Data block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data block</em>' containment reference.
   * @see #getData_block()
   * @generated
   */
  void setData_block(data_block value);

} // task_obj_block
