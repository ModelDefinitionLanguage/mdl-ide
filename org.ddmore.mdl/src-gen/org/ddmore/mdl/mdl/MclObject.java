/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mcl Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.MclObject#getModelObject <em>Model Object</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.MclObject#getParameterObject <em>Parameter Object</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.MclObject#getDataObject <em>Data Object</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.MclObject#getTaskObject <em>Task Object</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.MclObject#getTelObject <em>Tel Object</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getMclObject()
 * @model
 * @generated
 */
public interface MclObject extends EObject
{
  /**
   * Returns the value of the '<em><b>Model Object</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Model Object</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Model Object</em>' containment reference.
   * @see #setModelObject(ModelObject)
   * @see org.ddmore.mdl.mdl.MdlPackage#getMclObject_ModelObject()
   * @model containment="true"
   * @generated
   */
  ModelObject getModelObject();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.MclObject#getModelObject <em>Model Object</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Model Object</em>' containment reference.
   * @see #getModelObject()
   * @generated
   */
  void setModelObject(ModelObject value);

  /**
   * Returns the value of the '<em><b>Parameter Object</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameter Object</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter Object</em>' containment reference.
   * @see #setParameterObject(ParameterObject)
   * @see org.ddmore.mdl.mdl.MdlPackage#getMclObject_ParameterObject()
   * @model containment="true"
   * @generated
   */
  ParameterObject getParameterObject();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.MclObject#getParameterObject <em>Parameter Object</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameter Object</em>' containment reference.
   * @see #getParameterObject()
   * @generated
   */
  void setParameterObject(ParameterObject value);

  /**
   * Returns the value of the '<em><b>Data Object</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Data Object</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Data Object</em>' containment reference.
   * @see #setDataObject(DataObject)
   * @see org.ddmore.mdl.mdl.MdlPackage#getMclObject_DataObject()
   * @model containment="true"
   * @generated
   */
  DataObject getDataObject();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.MclObject#getDataObject <em>Data Object</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Data Object</em>' containment reference.
   * @see #getDataObject()
   * @generated
   */
  void setDataObject(DataObject value);

  /**
   * Returns the value of the '<em><b>Task Object</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Task Object</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Task Object</em>' containment reference.
   * @see #setTaskObject(TaskObject)
   * @see org.ddmore.mdl.mdl.MdlPackage#getMclObject_TaskObject()
   * @model containment="true"
   * @generated
   */
  TaskObject getTaskObject();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.MclObject#getTaskObject <em>Task Object</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Task Object</em>' containment reference.
   * @see #getTaskObject()
   * @generated
   */
  void setTaskObject(TaskObject value);

  /**
   * Returns the value of the '<em><b>Tel Object</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tel Object</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tel Object</em>' containment reference.
   * @see #setTelObject(TELObject)
   * @see org.ddmore.mdl.mdl.MdlPackage#getMclObject_TelObject()
   * @model containment="true"
   * @generated
   */
  TELObject getTelObject();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.MclObject#getTelObject <em>Tel Object</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Tel Object</em>' containment reference.
   * @see #getTelObject()
   * @generated
   */
  void setTelObject(TELObject value);

} // MclObject
