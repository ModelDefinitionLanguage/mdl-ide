/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Prediction Block Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.ModelPredictionBlockStatement#getStatement <em>Statement</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.ModelPredictionBlockStatement#getOdeBlock <em>Ode Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.ModelPredictionBlockStatement#getLibraryBlock <em>Library Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getModelPredictionBlockStatement()
 * @model
 * @generated
 */
public interface ModelPredictionBlockStatement extends EObject
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
   * @see org.ddmore.mdl.mdl.MdlPackage#getModelPredictionBlockStatement_Statement()
   * @model containment="true"
   * @generated
   */
  BlockStatement getStatement();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.ModelPredictionBlockStatement#getStatement <em>Statement</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Statement</em>' containment reference.
   * @see #getStatement()
   * @generated
   */
  void setStatement(BlockStatement value);

  /**
   * Returns the value of the '<em><b>Ode Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ode Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ode Block</em>' containment reference.
   * @see #setOdeBlock(OdeBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getModelPredictionBlockStatement_OdeBlock()
   * @model containment="true"
   * @generated
   */
  OdeBlock getOdeBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.ModelPredictionBlockStatement#getOdeBlock <em>Ode Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ode Block</em>' containment reference.
   * @see #getOdeBlock()
   * @generated
   */
  void setOdeBlock(OdeBlock value);

  /**
   * Returns the value of the '<em><b>Library Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Library Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Library Block</em>' containment reference.
   * @see #setLibraryBlock(LibraryBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getModelPredictionBlockStatement_LibraryBlock()
   * @model containment="true"
   * @generated
   */
  LibraryBlock getLibraryBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.ModelPredictionBlockStatement#getLibraryBlock <em>Library Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Library Block</em>' containment reference.
   * @see #getLibraryBlock()
   * @generated
   */
  void setLibraryBlock(LibraryBlock value);

} // ModelPredictionBlockStatement
