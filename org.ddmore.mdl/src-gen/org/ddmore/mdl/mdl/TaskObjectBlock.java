/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task Object Block</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.TaskObjectBlock#getFunctionDeclaration <em>Function Declaration</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.TaskObjectBlock#getParameterBlock <em>Parameter Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.TaskObjectBlock#getDataBlock <em>Data Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.TaskObjectBlock#getModelBlock <em>Model Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.TaskObjectBlock#getVerbatimBlock <em>Verbatim Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getTaskObjectBlock()
 * @model
 * @generated
 */
public interface TaskObjectBlock extends EObject
{
  /**
   * Returns the value of the '<em><b>Function Declaration</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function Declaration</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function Declaration</em>' containment reference.
   * @see #setFunctionDeclaration(TaskFunctionDeclaration)
   * @see org.ddmore.mdl.mdl.MdlPackage#getTaskObjectBlock_FunctionDeclaration()
   * @model containment="true"
   * @generated
   */
  TaskFunctionDeclaration getFunctionDeclaration();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.TaskObjectBlock#getFunctionDeclaration <em>Function Declaration</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Function Declaration</em>' containment reference.
   * @see #getFunctionDeclaration()
   * @generated
   */
  void setFunctionDeclaration(TaskFunctionDeclaration value);

  /**
   * Returns the value of the '<em><b>Parameter Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameter Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter Block</em>' containment reference.
   * @see #setParameterBlock(ParameterBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getTaskObjectBlock_ParameterBlock()
   * @model containment="true"
   * @generated
   */
  ParameterBlock getParameterBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.TaskObjectBlock#getParameterBlock <em>Parameter Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameter Block</em>' containment reference.
   * @see #getParameterBlock()
   * @generated
   */
  void setParameterBlock(ParameterBlock value);

  /**
   * Returns the value of the '<em><b>Data Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Data Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data Block</em>' containment reference.
   * @see #setDataBlock(DataBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getTaskObjectBlock_DataBlock()
   * @model containment="true"
   * @generated
   */
  DataBlock getDataBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.TaskObjectBlock#getDataBlock <em>Data Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data Block</em>' containment reference.
   * @see #getDataBlock()
   * @generated
   */
  void setDataBlock(DataBlock value);

  /**
   * Returns the value of the '<em><b>Model Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Model Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Block</em>' containment reference.
   * @see #setModelBlock(ModelBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getTaskObjectBlock_ModelBlock()
   * @model containment="true"
   * @generated
   */
  ModelBlock getModelBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.TaskObjectBlock#getModelBlock <em>Model Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Model Block</em>' containment reference.
   * @see #getModelBlock()
   * @generated
   */
  void setModelBlock(ModelBlock value);

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
   * @see org.ddmore.mdl.mdl.MdlPackage#getTaskObjectBlock_VerbatimBlock()
   * @model containment="true"
   * @generated
   */
  VerbatimBlock getVerbatimBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.TaskObjectBlock#getVerbatimBlock <em>Verbatim Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Verbatim Block</em>' containment reference.
   * @see #getVerbatimBlock()
   * @generated
   */
  void setVerbatimBlock(VerbatimBlock value);

} // TaskObjectBlock
