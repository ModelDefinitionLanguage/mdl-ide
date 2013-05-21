/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>File Block Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.FileBlockStatement#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.FileBlockStatement#getInlineBlock <em>Inline Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.FileBlockStatement#getDesignBlock <em>Design Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.FileBlockStatement#getRscriptBlock <em>Rscript Block</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getFileBlockStatement()
 * @model
 * @generated
 */
public interface FileBlockStatement extends EObject
{
  /**
   * Returns the value of the '<em><b>Variable</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variable</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Variable</em>' containment reference.
   * @see #setVariable(SymbolDeclaration)
   * @see org.ddmore.mdl.mdl.MdlPackage#getFileBlockStatement_Variable()
   * @model containment="true"
   * @generated
   */
  SymbolDeclaration getVariable();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.FileBlockStatement#getVariable <em>Variable</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Variable</em>' containment reference.
   * @see #getVariable()
   * @generated
   */
  void setVariable(SymbolDeclaration value);

  /**
   * Returns the value of the '<em><b>Inline Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Inline Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Inline Block</em>' containment reference.
   * @see #setInlineBlock(InlineBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getFileBlockStatement_InlineBlock()
   * @model containment="true"
   * @generated
   */
  InlineBlock getInlineBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.FileBlockStatement#getInlineBlock <em>Inline Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Inline Block</em>' containment reference.
   * @see #getInlineBlock()
   * @generated
   */
  void setInlineBlock(InlineBlock value);

  /**
   * Returns the value of the '<em><b>Design Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Design Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Design Block</em>' containment reference.
   * @see #setDesignBlock(DesignBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getFileBlockStatement_DesignBlock()
   * @model containment="true"
   * @generated
   */
  DesignBlock getDesignBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.FileBlockStatement#getDesignBlock <em>Design Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Design Block</em>' containment reference.
   * @see #getDesignBlock()
   * @generated
   */
  void setDesignBlock(DesignBlock value);

  /**
   * Returns the value of the '<em><b>Rscript Block</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Rscript Block</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rscript Block</em>' containment reference.
   * @see #setRscriptBlock(RScriptBlock)
   * @see org.ddmore.mdl.mdl.MdlPackage#getFileBlockStatement_RscriptBlock()
   * @model containment="true"
   * @generated
   */
  RScriptBlock getRscriptBlock();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.FileBlockStatement#getRscriptBlock <em>Rscript Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Rscript Block</em>' containment reference.
   * @see #getRscriptBlock()
   * @generated
   */
  void setRscriptBlock(RScriptBlock value);

} // FileBlockStatement
