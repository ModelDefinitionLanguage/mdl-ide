/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Block Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.ModelBlockStatement#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.ModelBlockStatement#getAddList <em>Add List</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.ModelBlockStatement#getRemoveList <em>Remove List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getModelBlockStatement()
 * @model
 * @generated
 */
public interface ModelBlockStatement extends EObject
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
   * @see org.ddmore.mdl.mdl.MdlPackage#getModelBlockStatement_Statement()
   * @model containment="true"
   * @generated
   */
  BlockStatement getStatement();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.ModelBlockStatement#getStatement <em>Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Statement</em>' containment reference.
   * @see #getStatement()
   * @generated
   */
  void setStatement(BlockStatement value);

  /**
   * Returns the value of the '<em><b>Add List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Add List</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Add List</em>' containment reference.
   * @see #setAddList(AddList)
   * @see org.ddmore.mdl.mdl.MdlPackage#getModelBlockStatement_AddList()
   * @model containment="true"
   * @generated
   */
  AddList getAddList();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.ModelBlockStatement#getAddList <em>Add List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Add List</em>' containment reference.
   * @see #getAddList()
   * @generated
   */
  void setAddList(AddList value);

  /**
   * Returns the value of the '<em><b>Remove List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Remove List</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Remove List</em>' containment reference.
   * @see #setRemoveList(RemoveList)
   * @see org.ddmore.mdl.mdl.MdlPackage#getModelBlockStatement_RemoveList()
   * @model containment="true"
   * @generated
   */
  RemoveList getRemoveList();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.ModelBlockStatement#getRemoveList <em>Remove List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Remove List</em>' containment reference.
   * @see #getRemoveList()
   * @generated
   */
  void setRemoveList(RemoveList value);

} // ModelBlockStatement
