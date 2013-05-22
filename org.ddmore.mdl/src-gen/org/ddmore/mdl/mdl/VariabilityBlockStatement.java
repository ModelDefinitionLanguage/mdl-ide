/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Variability Block Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.VariabilityBlockStatement#getParameter <em>Parameter</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.VariabilityBlockStatement#getBlockBlock <em>Block Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.VariabilityBlockStatement#getDiagBlock <em>Diag Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.VariabilityBlockStatement#getSameBlock <em>Same Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getVariabilityBlockStatement()
 * @model
 * @generated
 */
public interface VariabilityBlockStatement extends EObject
{
  /**
   * Returns the value of the '<em><b>Parameter</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameter</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter</em>' containment reference.
   * @see #setParameter(ParameterDeclaration)
   * @see org.ddmore.mdl.mdl.MdlPackage#getVariabilityBlockStatement_Parameter()
   * @model containment="true"
   * @generated
   */
  ParameterDeclaration getParameter();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.VariabilityBlockStatement#getParameter <em>Parameter</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameter</em>' containment reference.
   * @see #getParameter()
   * @generated
   */
  void setParameter(ParameterDeclaration value);

  /**
   * Returns the value of the '<em><b>Block Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Block Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Block Block</em>' containment reference.
   * @see #setBlockBlock(BlockBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getVariabilityBlockStatement_BlockBlock()
   * @model containment="true"
   * @generated
   */
  BlockBlock getBlockBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.VariabilityBlockStatement#getBlockBlock <em>Block Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Block Block</em>' containment reference.
   * @see #getBlockBlock()
   * @generated
   */
  void setBlockBlock(BlockBlock value);

  /**
   * Returns the value of the '<em><b>Diag Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Diag Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Diag Block</em>' containment reference.
   * @see #setDiagBlock(DiagBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getVariabilityBlockStatement_DiagBlock()
   * @model containment="true"
   * @generated
   */
  DiagBlock getDiagBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.VariabilityBlockStatement#getDiagBlock <em>Diag Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Diag Block</em>' containment reference.
   * @see #getDiagBlock()
   * @generated
   */
  void setDiagBlock(DiagBlock value);

  /**
   * Returns the value of the '<em><b>Same Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Same Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Same Block</em>' containment reference.
   * @see #setSameBlock(SameBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getVariabilityBlockStatement_SameBlock()
   * @model containment="true"
   * @generated
   */
  SameBlock getSameBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.VariabilityBlockStatement#getSameBlock <em>Same Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Same Block</em>' containment reference.
   * @see #getSameBlock()
   * @generated
   */
  void setSameBlock(SameBlock value);

} // VariabilityBlockStatement
