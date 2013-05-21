/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Block Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.DataBlockStatement#getIgnoreList <em>Ignore List</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.DataBlockStatement#getAcceptList <em>Accept List</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.DataBlockStatement#getDropList <em>Drop List</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getDataBlockStatement()
 * @model
 * @generated
 */
public interface DataBlockStatement extends EObject
{
  /**
   * Returns the value of the '<em><b>Ignore List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ignore List</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ignore List</em>' containment reference.
   * @see #setIgnoreList(IgnoreList)
   * @see org.ddmore.mdl.mdl.MdlPackage#getDataBlockStatement_IgnoreList()
   * @model containment="true"
   * @generated
   */
  IgnoreList getIgnoreList();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.DataBlockStatement#getIgnoreList <em>Ignore List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ignore List</em>' containment reference.
   * @see #getIgnoreList()
   * @generated
   */
  void setIgnoreList(IgnoreList value);

  /**
   * Returns the value of the '<em><b>Accept List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Accept List</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Accept List</em>' containment reference.
   * @see #setAcceptList(AcceptList)
   * @see org.ddmore.mdl.mdl.MdlPackage#getDataBlockStatement_AcceptList()
   * @model containment="true"
   * @generated
   */
  AcceptList getAcceptList();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.DataBlockStatement#getAcceptList <em>Accept List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Accept List</em>' containment reference.
   * @see #getAcceptList()
   * @generated
   */
  void setAcceptList(AcceptList value);

  /**
   * Returns the value of the '<em><b>Drop List</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Drop List</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Drop List</em>' containment reference.
   * @see #setDropList(DropList)
   * @see org.ddmore.mdl.mdl.MdlPackage#getDataBlockStatement_DropList()
   * @model containment="true"
   * @generated
   */
  DropList getDropList();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.DataBlockStatement#getDropList <em>Drop List</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Drop List</em>' containment reference.
   * @see #getDropList()
   * @generated
   */
  void setDropList(DropList value);

} // DataBlockStatement
