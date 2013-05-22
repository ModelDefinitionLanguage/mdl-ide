/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Distribution</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.Distribution#getNormal <em>Normal</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.Distribution#getBinomial <em>Binomial</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.Distribution#getPoisson <em>Poisson</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.Distribution#getStudent_t <em>Student t</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.Distribution#getMvnormal <em>Mvnormal</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.ddmore.mdl.mdl.MdlPackage#getDistribution()
 * @model
 * @generated
 */
public interface Distribution extends EObject
{
  /**
   * Returns the value of the '<em><b>Normal</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Normal</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Normal</em>' attribute.
   * @see #setNormal(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getDistribution_Normal()
   * @model
   * @generated
   */
  String getNormal();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.Distribution#getNormal <em>Normal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Normal</em>' attribute.
   * @see #getNormal()
   * @generated
   */
  void setNormal(String value);

  /**
   * Returns the value of the '<em><b>Binomial</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Binomial</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Binomial</em>' attribute.
   * @see #setBinomial(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getDistribution_Binomial()
   * @model
   * @generated
   */
  String getBinomial();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.Distribution#getBinomial <em>Binomial</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Binomial</em>' attribute.
   * @see #getBinomial()
   * @generated
   */
  void setBinomial(String value);

  /**
   * Returns the value of the '<em><b>Poisson</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Poisson</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Poisson</em>' attribute.
   * @see #setPoisson(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getDistribution_Poisson()
   * @model
   * @generated
   */
  String getPoisson();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.Distribution#getPoisson <em>Poisson</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Poisson</em>' attribute.
   * @see #getPoisson()
   * @generated
   */
  void setPoisson(String value);

  /**
   * Returns the value of the '<em><b>Student t</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Student t</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Student t</em>' attribute.
   * @see #setStudent_t(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getDistribution_Student_t()
   * @model
   * @generated
   */
  String getStudent_t();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.Distribution#getStudent_t <em>Student t</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Student t</em>' attribute.
   * @see #getStudent_t()
   * @generated
   */
  void setStudent_t(String value);

  /**
   * Returns the value of the '<em><b>Mvnormal</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mvnormal</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mvnormal</em>' attribute.
   * @see #setMvnormal(String)
   * @see org.ddmore.mdl.mdl.MdlPackage#getDistribution_Mvnormal()
   * @model
   * @generated
   */
  String getMvnormal();

  /**
   * Sets the value of the '{@link org.ddmore.mdl.mdl.Distribution#getMvnormal <em>Mvnormal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mvnormal</em>' attribute.
   * @see #getMvnormal()
   * @generated
   */
  void setMvnormal(String value);

} // Distribution
