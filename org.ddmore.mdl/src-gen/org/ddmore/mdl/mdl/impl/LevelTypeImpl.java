/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.LevelType;
import org.ddmore.mdl.mdl.MdlPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Level Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.LevelTypeImpl#getMdv <em>Mdv</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.LevelTypeImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.LevelTypeImpl#getDv <em>Dv</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.LevelTypeImpl#getIdv <em>Idv</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LevelTypeImpl extends MinimalEObjectImpl.Container implements LevelType
{
  /**
   * The default value of the '{@link #getMdv() <em>Mdv</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMdv()
   * @generated
   * @ordered
   */
  protected static final String MDV_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMdv() <em>Mdv</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMdv()
   * @generated
   * @ordered
   */
  protected String mdv = MDV_EDEFAULT;

  /**
   * The default value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected static final String ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected String id = ID_EDEFAULT;

  /**
   * The default value of the '{@link #getDv() <em>Dv</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDv()
   * @generated
   * @ordered
   */
  protected static final String DV_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDv() <em>Dv</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDv()
   * @generated
   * @ordered
   */
  protected String dv = DV_EDEFAULT;

  /**
   * The default value of the '{@link #getIdv() <em>Idv</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdv()
   * @generated
   * @ordered
   */
  protected static final String IDV_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getIdv() <em>Idv</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIdv()
   * @generated
   * @ordered
   */
  protected String idv = IDV_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LevelTypeImpl()
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
    return MdlPackage.Literals.LEVEL_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getMdv()
  {
    return mdv;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMdv(String newMdv)
  {
    String oldMdv = mdv;
    mdv = newMdv;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.LEVEL_TYPE__MDV, oldMdv, mdv));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getId()
  {
    return id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setId(String newId)
  {
    String oldId = id;
    id = newId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.LEVEL_TYPE__ID, oldId, id));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDv()
  {
    return dv;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDv(String newDv)
  {
    String oldDv = dv;
    dv = newDv;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.LEVEL_TYPE__DV, oldDv, dv));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getIdv()
  {
    return idv;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setIdv(String newIdv)
  {
    String oldIdv = idv;
    idv = newIdv;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.LEVEL_TYPE__IDV, oldIdv, idv));
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
      case MdlPackage.LEVEL_TYPE__MDV:
        return getMdv();
      case MdlPackage.LEVEL_TYPE__ID:
        return getId();
      case MdlPackage.LEVEL_TYPE__DV:
        return getDv();
      case MdlPackage.LEVEL_TYPE__IDV:
        return getIdv();
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
      case MdlPackage.LEVEL_TYPE__MDV:
        setMdv((String)newValue);
        return;
      case MdlPackage.LEVEL_TYPE__ID:
        setId((String)newValue);
        return;
      case MdlPackage.LEVEL_TYPE__DV:
        setDv((String)newValue);
        return;
      case MdlPackage.LEVEL_TYPE__IDV:
        setIdv((String)newValue);
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
      case MdlPackage.LEVEL_TYPE__MDV:
        setMdv(MDV_EDEFAULT);
        return;
      case MdlPackage.LEVEL_TYPE__ID:
        setId(ID_EDEFAULT);
        return;
      case MdlPackage.LEVEL_TYPE__DV:
        setDv(DV_EDEFAULT);
        return;
      case MdlPackage.LEVEL_TYPE__IDV:
        setIdv(IDV_EDEFAULT);
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
      case MdlPackage.LEVEL_TYPE__MDV:
        return MDV_EDEFAULT == null ? mdv != null : !MDV_EDEFAULT.equals(mdv);
      case MdlPackage.LEVEL_TYPE__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case MdlPackage.LEVEL_TYPE__DV:
        return DV_EDEFAULT == null ? dv != null : !DV_EDEFAULT.equals(dv);
      case MdlPackage.LEVEL_TYPE__IDV:
        return IDV_EDEFAULT == null ? idv != null : !IDV_EDEFAULT.equals(idv);
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
    result.append(" (mdv: ");
    result.append(mdv);
    result.append(", id: ");
    result.append(id);
    result.append(", dv: ");
    result.append(dv);
    result.append(", idv: ");
    result.append(idv);
    result.append(')');
    return result.toString();
  }

} //LevelTypeImpl
