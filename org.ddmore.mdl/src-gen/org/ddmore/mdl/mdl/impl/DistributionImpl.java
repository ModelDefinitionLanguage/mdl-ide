/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.Distribution;
import org.ddmore.mdl.mdl.MdlPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Distribution</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.DistributionImpl#getNormal <em>Normal</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.DistributionImpl#getBinomial <em>Binomial</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.DistributionImpl#getPoisson <em>Poisson</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.DistributionImpl#getStudent_t <em>Student t</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.DistributionImpl#getMvnormal <em>Mvnormal</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DistributionImpl extends MinimalEObjectImpl.Container implements Distribution
{
  /**
   * The default value of the '{@link #getNormal() <em>Normal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNormal()
   * @generated
   * @ordered
   */
  protected static final String NORMAL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNormal() <em>Normal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNormal()
   * @generated
   * @ordered
   */
  protected String normal = NORMAL_EDEFAULT;

  /**
   * The default value of the '{@link #getBinomial() <em>Binomial</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBinomial()
   * @generated
   * @ordered
   */
  protected static final String BINOMIAL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getBinomial() <em>Binomial</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBinomial()
   * @generated
   * @ordered
   */
  protected String binomial = BINOMIAL_EDEFAULT;

  /**
   * The default value of the '{@link #getPoisson() <em>Poisson</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPoisson()
   * @generated
   * @ordered
   */
  protected static final String POISSON_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPoisson() <em>Poisson</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPoisson()
   * @generated
   * @ordered
   */
  protected String poisson = POISSON_EDEFAULT;

  /**
   * The default value of the '{@link #getStudent_t() <em>Student t</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStudent_t()
   * @generated
   * @ordered
   */
  protected static final String STUDENT_T_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getStudent_t() <em>Student t</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getStudent_t()
   * @generated
   * @ordered
   */
  protected String student_t = STUDENT_T_EDEFAULT;

  /**
   * The default value of the '{@link #getMvnormal() <em>Mvnormal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMvnormal()
   * @generated
   * @ordered
   */
  protected static final String MVNORMAL_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMvnormal() <em>Mvnormal</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMvnormal()
   * @generated
   * @ordered
   */
  protected String mvnormal = MVNORMAL_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DistributionImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return MdlPackage.Literals.DISTRIBUTION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNormal()
  {
    return normal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNormal(String newNormal)
  {
    String oldNormal = normal;
    normal = newNormal;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.DISTRIBUTION__NORMAL, oldNormal, normal));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getBinomial()
  {
    return binomial;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBinomial(String newBinomial)
  {
    String oldBinomial = binomial;
    binomial = newBinomial;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.DISTRIBUTION__BINOMIAL, oldBinomial, binomial));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPoisson()
  {
    return poisson;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPoisson(String newPoisson)
  {
    String oldPoisson = poisson;
    poisson = newPoisson;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.DISTRIBUTION__POISSON, oldPoisson, poisson));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getStudent_t()
  {
    return student_t;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setStudent_t(String newStudent_t)
  {
    String oldStudent_t = student_t;
    student_t = newStudent_t;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.DISTRIBUTION__STUDENT_T, oldStudent_t, student_t));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getMvnormal()
  {
    return mvnormal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMvnormal(String newMvnormal)
  {
    String oldMvnormal = mvnormal;
    mvnormal = newMvnormal;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.DISTRIBUTION__MVNORMAL, oldMvnormal, mvnormal));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case MdlPackage.DISTRIBUTION__NORMAL:
        return getNormal();
      case MdlPackage.DISTRIBUTION__BINOMIAL:
        return getBinomial();
      case MdlPackage.DISTRIBUTION__POISSON:
        return getPoisson();
      case MdlPackage.DISTRIBUTION__STUDENT_T:
        return getStudent_t();
      case MdlPackage.DISTRIBUTION__MVNORMAL:
        return getMvnormal();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case MdlPackage.DISTRIBUTION__NORMAL:
        setNormal((String)newValue);
        return;
      case MdlPackage.DISTRIBUTION__BINOMIAL:
        setBinomial((String)newValue);
        return;
      case MdlPackage.DISTRIBUTION__POISSON:
        setPoisson((String)newValue);
        return;
      case MdlPackage.DISTRIBUTION__STUDENT_T:
        setStudent_t((String)newValue);
        return;
      case MdlPackage.DISTRIBUTION__MVNORMAL:
        setMvnormal((String)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case MdlPackage.DISTRIBUTION__NORMAL:
        setNormal(NORMAL_EDEFAULT);
        return;
      case MdlPackage.DISTRIBUTION__BINOMIAL:
        setBinomial(BINOMIAL_EDEFAULT);
        return;
      case MdlPackage.DISTRIBUTION__POISSON:
        setPoisson(POISSON_EDEFAULT);
        return;
      case MdlPackage.DISTRIBUTION__STUDENT_T:
        setStudent_t(STUDENT_T_EDEFAULT);
        return;
      case MdlPackage.DISTRIBUTION__MVNORMAL:
        setMvnormal(MVNORMAL_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case MdlPackage.DISTRIBUTION__NORMAL:
        return NORMAL_EDEFAULT == null ? normal != null : !NORMAL_EDEFAULT.equals(normal);
      case MdlPackage.DISTRIBUTION__BINOMIAL:
        return BINOMIAL_EDEFAULT == null ? binomial != null : !BINOMIAL_EDEFAULT.equals(binomial);
      case MdlPackage.DISTRIBUTION__POISSON:
        return POISSON_EDEFAULT == null ? poisson != null : !POISSON_EDEFAULT.equals(poisson);
      case MdlPackage.DISTRIBUTION__STUDENT_T:
        return STUDENT_T_EDEFAULT == null ? student_t != null : !STUDENT_T_EDEFAULT.equals(student_t);
      case MdlPackage.DISTRIBUTION__MVNORMAL:
        return MVNORMAL_EDEFAULT == null ? mvnormal != null : !MVNORMAL_EDEFAULT.equals(mvnormal);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (normal: ");
    result.append(normal);
    result.append(", binomial: ");
    result.append(binomial);
    result.append(", poisson: ");
    result.append(poisson);
    result.append(", student_t: ");
    result.append(student_t);
    result.append(", mvnormal: ");
    result.append(mvnormal);
    result.append(')');
    return result.toString();
  }

} //DistributionImpl
