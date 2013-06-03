/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enum Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.EnumType#getCategorical <em>Categorical</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.EnumType#getContinuous <em>Continuous</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.EnumType#getCovariate <em>Covariate</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.EnumType#getDistribution <em>Distribution</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.EnumType#getLevel <em>Level</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.EnumType#getLikelyhood <em>Likelyhood</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.EnumType#getMissing <em>Missing</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.EnumType#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getEnumType()
 * @model
 * @generated
 */
public interface EnumType extends EObject
{
  /**
   * Returns the value of the '<em><b>Categorical</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Categorical</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Categorical</em>' containment reference.
   * @see #setCategorical(Categorical)
   * @see org.ddmore.mdl.mdl.MdlPackage#getEnumType_Categorical()
   * @model containment="true"
   * @generated
   */
  Categorical getCategorical();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.EnumType#getCategorical <em>Categorical</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Categorical</em>' containment reference.
   * @see #getCategorical()
   * @generated
   */
  void setCategorical(Categorical value);

  /**
   * Returns the value of the '<em><b>Continuous</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Continuous</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Continuous</em>' containment reference.
   * @see #setContinuous(Continuous)
   * @see org.ddmore.mdl.mdl.MdlPackage#getEnumType_Continuous()
   * @model containment="true"
   * @generated
   */
  Continuous getContinuous();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.EnumType#getContinuous <em>Continuous</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Continuous</em>' containment reference.
   * @see #getContinuous()
   * @generated
   */
  void setContinuous(Continuous value);

  /**
   * Returns the value of the '<em><b>Covariate</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Covariate</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Covariate</em>' containment reference.
   * @see #setCovariate(Covariate)
   * @see org.ddmore.mdl.mdl.MdlPackage#getEnumType_Covariate()
   * @model containment="true"
   * @generated
   */
  Covariate getCovariate();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.EnumType#getCovariate <em>Covariate</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Covariate</em>' containment reference.
   * @see #getCovariate()
   * @generated
   */
  void setCovariate(Covariate value);

  /**
   * Returns the value of the '<em><b>Distribution</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Distribution</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Distribution</em>' containment reference.
   * @see #setDistribution(Distribution)
   * @see org.ddmore.mdl.mdl.MdlPackage#getEnumType_Distribution()
   * @model containment="true"
   * @generated
   */
  Distribution getDistribution();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.EnumType#getDistribution <em>Distribution</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Distribution</em>' containment reference.
   * @see #getDistribution()
   * @generated
   */
  void setDistribution(Distribution value);

  /**
   * Returns the value of the '<em><b>Level</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Level</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Level</em>' containment reference.
   * @see #setLevel(LevelType)
   * @see org.ddmore.mdl.mdl.MdlPackage#getEnumType_Level()
   * @model containment="true"
   * @generated
   */
  LevelType getLevel();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.EnumType#getLevel <em>Level</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Level</em>' containment reference.
   * @see #getLevel()
   * @generated
   */
  void setLevel(LevelType value);

  /**
   * Returns the value of the '<em><b>Likelyhood</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Likelyhood</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Likelyhood</em>' containment reference.
   * @see #setLikelyhood(Likelyhood)
   * @see org.ddmore.mdl.mdl.MdlPackage#getEnumType_Likelyhood()
   * @model containment="true"
   * @generated
   */
  Likelyhood getLikelyhood();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.EnumType#getLikelyhood <em>Likelyhood</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Likelyhood</em>' containment reference.
   * @see #getLikelyhood()
   * @generated
   */
  void setLikelyhood(Likelyhood value);

  /**
   * Returns the value of the '<em><b>Missing</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Missing</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Missing</em>' containment reference.
   * @see #setMissing(Missing)
   * @see org.ddmore.mdl.mdl.MdlPackage#getEnumType_Missing()
   * @model containment="true"
   * @generated
   */
  Missing getMissing();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.EnumType#getMissing <em>Missing</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Missing</em>' containment reference.
   * @see #getMissing()
   * @generated
   */
  void setMissing(Missing value);

  /**
   * Returns the value of the '<em><b>Target</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target</em>' attribute.
   * @see #setTarget(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getEnumType_Target()
   * @model
   * @generated
   */
  String getTarget();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.EnumType#getTarget <em>Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target</em>' attribute.
   * @see #getTarget()
   * @generated
   */
  void setTarget(String value);

} // EnumType
