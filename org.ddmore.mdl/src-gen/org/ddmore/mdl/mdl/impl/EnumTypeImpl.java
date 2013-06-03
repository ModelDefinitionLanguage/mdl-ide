/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.Categorical;
import org.ddmore.mdl.mdl.Continuous;
import org.ddmore.mdl.mdl.Covariate;
import org.ddmore.mdl.mdl.Distribution;
import org.ddmore.mdl.mdl.EnumType;
import org.ddmore.mdl.mdl.LevelType;
import org.ddmore.mdl.mdl.Likelyhood;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.Missing;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Enum Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.EnumTypeImpl#getCategorical <em>Categorical</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.EnumTypeImpl#getContinuous <em>Continuous</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.EnumTypeImpl#getCovariate <em>Covariate</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.EnumTypeImpl#getDistribution <em>Distribution</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.EnumTypeImpl#getLevel <em>Level</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.EnumTypeImpl#getLikelyhood <em>Likelyhood</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.EnumTypeImpl#getMissing <em>Missing</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.EnumTypeImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnumTypeImpl extends MinimalEObjectImpl.Container implements EnumType
{
  /**
   * The cached value of the '{@link #getCategorical() <em>Categorical</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCategorical()
   * @generated
   * @ordered
   */
  protected Categorical categorical;

  /**
   * The cached value of the '{@link #getContinuous() <em>Continuous</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContinuous()
   * @generated
   * @ordered
   */
  protected Continuous continuous;

  /**
   * The cached value of the '{@link #getCovariate() <em>Covariate</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCovariate()
   * @generated
   * @ordered
   */
  protected Covariate covariate;

  /**
   * The cached value of the '{@link #getDistribution() <em>Distribution</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDistribution()
   * @generated
   * @ordered
   */
  protected Distribution distribution;

  /**
   * The cached value of the '{@link #getLevel() <em>Level</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLevel()
   * @generated
   * @ordered
   */
  protected LevelType level;

  /**
   * The cached value of the '{@link #getLikelyhood() <em>Likelyhood</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLikelyhood()
   * @generated
   * @ordered
   */
  protected Likelyhood likelyhood;

  /**
   * The cached value of the '{@link #getMissing() <em>Missing</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMissing()
   * @generated
   * @ordered
   */
  protected Missing missing;

  /**
   * The default value of the '{@link #getTarget() <em>Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected static final String TARGET_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
  protected String target = TARGET_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EnumTypeImpl()
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
    return MdlPackage.Literals.ENUM_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Categorical getCategorical()
  {
    return categorical;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCategorical(Categorical newCategorical, NotificationChain msgs)
  {
    Categorical oldCategorical = categorical;
    categorical = newCategorical;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.ENUM_TYPE__CATEGORICAL, oldCategorical, newCategorical);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCategorical(Categorical newCategorical)
  {
    if (newCategorical != categorical)
    {
      NotificationChain msgs = null;
      if (categorical != null)
        msgs = ((InternalEObject)categorical).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ENUM_TYPE__CATEGORICAL, null, msgs);
      if (newCategorical != null)
        msgs = ((InternalEObject)newCategorical).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ENUM_TYPE__CATEGORICAL, null, msgs);
      msgs = basicSetCategorical(newCategorical, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.ENUM_TYPE__CATEGORICAL, newCategorical, newCategorical));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Continuous getContinuous()
  {
    return continuous;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetContinuous(Continuous newContinuous, NotificationChain msgs)
  {
    Continuous oldContinuous = continuous;
    continuous = newContinuous;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.ENUM_TYPE__CONTINUOUS, oldContinuous, newContinuous);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContinuous(Continuous newContinuous)
  {
    if (newContinuous != continuous)
    {
      NotificationChain msgs = null;
      if (continuous != null)
        msgs = ((InternalEObject)continuous).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ENUM_TYPE__CONTINUOUS, null, msgs);
      if (newContinuous != null)
        msgs = ((InternalEObject)newContinuous).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ENUM_TYPE__CONTINUOUS, null, msgs);
      msgs = basicSetContinuous(newContinuous, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.ENUM_TYPE__CONTINUOUS, newContinuous, newContinuous));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Covariate getCovariate()
  {
    return covariate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCovariate(Covariate newCovariate, NotificationChain msgs)
  {
    Covariate oldCovariate = covariate;
    covariate = newCovariate;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.ENUM_TYPE__COVARIATE, oldCovariate, newCovariate);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCovariate(Covariate newCovariate)
  {
    if (newCovariate != covariate)
    {
      NotificationChain msgs = null;
      if (covariate != null)
        msgs = ((InternalEObject)covariate).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ENUM_TYPE__COVARIATE, null, msgs);
      if (newCovariate != null)
        msgs = ((InternalEObject)newCovariate).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ENUM_TYPE__COVARIATE, null, msgs);
      msgs = basicSetCovariate(newCovariate, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.ENUM_TYPE__COVARIATE, newCovariate, newCovariate));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Distribution getDistribution()
  {
    return distribution;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDistribution(Distribution newDistribution, NotificationChain msgs)
  {
    Distribution oldDistribution = distribution;
    distribution = newDistribution;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.ENUM_TYPE__DISTRIBUTION, oldDistribution, newDistribution);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDistribution(Distribution newDistribution)
  {
    if (newDistribution != distribution)
    {
      NotificationChain msgs = null;
      if (distribution != null)
        msgs = ((InternalEObject)distribution).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ENUM_TYPE__DISTRIBUTION, null, msgs);
      if (newDistribution != null)
        msgs = ((InternalEObject)newDistribution).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ENUM_TYPE__DISTRIBUTION, null, msgs);
      msgs = basicSetDistribution(newDistribution, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.ENUM_TYPE__DISTRIBUTION, newDistribution, newDistribution));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LevelType getLevel()
  {
    return level;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLevel(LevelType newLevel, NotificationChain msgs)
  {
    LevelType oldLevel = level;
    level = newLevel;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.ENUM_TYPE__LEVEL, oldLevel, newLevel);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLevel(LevelType newLevel)
  {
    if (newLevel != level)
    {
      NotificationChain msgs = null;
      if (level != null)
        msgs = ((InternalEObject)level).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ENUM_TYPE__LEVEL, null, msgs);
      if (newLevel != null)
        msgs = ((InternalEObject)newLevel).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ENUM_TYPE__LEVEL, null, msgs);
      msgs = basicSetLevel(newLevel, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.ENUM_TYPE__LEVEL, newLevel, newLevel));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Likelyhood getLikelyhood()
  {
    return likelyhood;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLikelyhood(Likelyhood newLikelyhood, NotificationChain msgs)
  {
    Likelyhood oldLikelyhood = likelyhood;
    likelyhood = newLikelyhood;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.ENUM_TYPE__LIKELYHOOD, oldLikelyhood, newLikelyhood);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLikelyhood(Likelyhood newLikelyhood)
  {
    if (newLikelyhood != likelyhood)
    {
      NotificationChain msgs = null;
      if (likelyhood != null)
        msgs = ((InternalEObject)likelyhood).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ENUM_TYPE__LIKELYHOOD, null, msgs);
      if (newLikelyhood != null)
        msgs = ((InternalEObject)newLikelyhood).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ENUM_TYPE__LIKELYHOOD, null, msgs);
      msgs = basicSetLikelyhood(newLikelyhood, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.ENUM_TYPE__LIKELYHOOD, newLikelyhood, newLikelyhood));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Missing getMissing()
  {
    return missing;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMissing(Missing newMissing, NotificationChain msgs)
  {
    Missing oldMissing = missing;
    missing = newMissing;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.ENUM_TYPE__MISSING, oldMissing, newMissing);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMissing(Missing newMissing)
  {
    if (newMissing != missing)
    {
      NotificationChain msgs = null;
      if (missing != null)
        msgs = ((InternalEObject)missing).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ENUM_TYPE__MISSING, null, msgs);
      if (newMissing != null)
        msgs = ((InternalEObject)newMissing).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.ENUM_TYPE__MISSING, null, msgs);
      msgs = basicSetMissing(newMissing, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.ENUM_TYPE__MISSING, newMissing, newMissing));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTarget()
  {
    return target;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTarget(String newTarget)
  {
    String oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.ENUM_TYPE__TARGET, oldTarget, target));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case MdlPackage.ENUM_TYPE__CATEGORICAL:
        return basicSetCategorical(null, msgs);
      case MdlPackage.ENUM_TYPE__CONTINUOUS:
        return basicSetContinuous(null, msgs);
      case MdlPackage.ENUM_TYPE__COVARIATE:
        return basicSetCovariate(null, msgs);
      case MdlPackage.ENUM_TYPE__DISTRIBUTION:
        return basicSetDistribution(null, msgs);
      case MdlPackage.ENUM_TYPE__LEVEL:
        return basicSetLevel(null, msgs);
      case MdlPackage.ENUM_TYPE__LIKELYHOOD:
        return basicSetLikelyhood(null, msgs);
      case MdlPackage.ENUM_TYPE__MISSING:
        return basicSetMissing(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case MdlPackage.ENUM_TYPE__CATEGORICAL:
        return getCategorical();
      case MdlPackage.ENUM_TYPE__CONTINUOUS:
        return getContinuous();
      case MdlPackage.ENUM_TYPE__COVARIATE:
        return getCovariate();
      case MdlPackage.ENUM_TYPE__DISTRIBUTION:
        return getDistribution();
      case MdlPackage.ENUM_TYPE__LEVEL:
        return getLevel();
      case MdlPackage.ENUM_TYPE__LIKELYHOOD:
        return getLikelyhood();
      case MdlPackage.ENUM_TYPE__MISSING:
        return getMissing();
      case MdlPackage.ENUM_TYPE__TARGET:
        return getTarget();
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
      case MdlPackage.ENUM_TYPE__CATEGORICAL:
        setCategorical((Categorical)newValue);
        return;
      case MdlPackage.ENUM_TYPE__CONTINUOUS:
        setContinuous((Continuous)newValue);
        return;
      case MdlPackage.ENUM_TYPE__COVARIATE:
        setCovariate((Covariate)newValue);
        return;
      case MdlPackage.ENUM_TYPE__DISTRIBUTION:
        setDistribution((Distribution)newValue);
        return;
      case MdlPackage.ENUM_TYPE__LEVEL:
        setLevel((LevelType)newValue);
        return;
      case MdlPackage.ENUM_TYPE__LIKELYHOOD:
        setLikelyhood((Likelyhood)newValue);
        return;
      case MdlPackage.ENUM_TYPE__MISSING:
        setMissing((Missing)newValue);
        return;
      case MdlPackage.ENUM_TYPE__TARGET:
        setTarget((String)newValue);
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
      case MdlPackage.ENUM_TYPE__CATEGORICAL:
        setCategorical((Categorical)null);
        return;
      case MdlPackage.ENUM_TYPE__CONTINUOUS:
        setContinuous((Continuous)null);
        return;
      case MdlPackage.ENUM_TYPE__COVARIATE:
        setCovariate((Covariate)null);
        return;
      case MdlPackage.ENUM_TYPE__DISTRIBUTION:
        setDistribution((Distribution)null);
        return;
      case MdlPackage.ENUM_TYPE__LEVEL:
        setLevel((LevelType)null);
        return;
      case MdlPackage.ENUM_TYPE__LIKELYHOOD:
        setLikelyhood((Likelyhood)null);
        return;
      case MdlPackage.ENUM_TYPE__MISSING:
        setMissing((Missing)null);
        return;
      case MdlPackage.ENUM_TYPE__TARGET:
        setTarget(TARGET_EDEFAULT);
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
      case MdlPackage.ENUM_TYPE__CATEGORICAL:
        return categorical != null;
      case MdlPackage.ENUM_TYPE__CONTINUOUS:
        return continuous != null;
      case MdlPackage.ENUM_TYPE__COVARIATE:
        return covariate != null;
      case MdlPackage.ENUM_TYPE__DISTRIBUTION:
        return distribution != null;
      case MdlPackage.ENUM_TYPE__LEVEL:
        return level != null;
      case MdlPackage.ENUM_TYPE__LIKELYHOOD:
        return likelyhood != null;
      case MdlPackage.ENUM_TYPE__MISSING:
        return missing != null;
      case MdlPackage.ENUM_TYPE__TARGET:
        return TARGET_EDEFAULT == null ? target != null : !TARGET_EDEFAULT.equals(target);
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
    result.append(" (target: ");
    result.append(target);
    result.append(')');
    return result.toString();
  }

} //EnumTypeImpl
