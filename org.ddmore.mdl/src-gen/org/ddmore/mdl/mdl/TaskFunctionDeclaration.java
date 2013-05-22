/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task Function Declaration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.TaskFunctionDeclaration#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.TaskFunctionDeclaration#getFormalArguments <em>Formal Arguments</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.TaskFunctionDeclaration#getFunctionBody <em>Function Body</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getTaskFunctionDeclaration()
 * @model
 * @generated
 */
public interface TaskFunctionDeclaration extends EObject
{
  /**
   * Returns the value of the '<em><b>Identifier</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Identifier</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Identifier</em>' attribute.
   * @see #setIdentifier(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getTaskFunctionDeclaration_Identifier()
   * @model
   * @generated
   */
  String getIdentifier();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.TaskFunctionDeclaration#getIdentifier <em>Identifier</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Identifier</em>' attribute.
   * @see #getIdentifier()
   * @generated
   */
  void setIdentifier(String value);

  /**
   * Returns the value of the '<em><b>Formal Arguments</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Formal Arguments</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Formal Arguments</em>' containment reference.
   * @see #setFormalArguments(FormalArguments)
   * @see org.ddmore.mdl.mdl.MdlPackage#getTaskFunctionDeclaration_FormalArguments()
   * @model containment="true"
   * @generated
   */
  FormalArguments getFormalArguments();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.TaskFunctionDeclaration#getFormalArguments <em>Formal Arguments</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Formal Arguments</em>' containment reference.
   * @see #getFormalArguments()
   * @generated
   */
  void setFormalArguments(FormalArguments value);

  /**
   * Returns the value of the '<em><b>Function Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Function Body</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Function Body</em>' containment reference.
   * @see #setFunctionBody(TaskFunctionBody)
   * @see org.ddmore.mdl.mdl.MdlPackage#getTaskFunctionDeclaration_FunctionBody()
   * @model containment="true"
   * @generated
   */
  TaskFunctionBody getFunctionBody();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.TaskFunctionDeclaration#getFunctionBody <em>Function Body</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Function Body</em>' containment reference.
   * @see #getFunctionBody()
   * @generated
   */
  void setFunctionBody(TaskFunctionBody value);

} // TaskFunctionDeclaration
