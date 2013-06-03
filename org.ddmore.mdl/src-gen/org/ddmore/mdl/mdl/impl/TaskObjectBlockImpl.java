/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.DataBlock;
import org.ddmore.mdl.mdl.ImportBlock;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.ModelBlock;
import org.ddmore.mdl.mdl.ParameterBlock;
import org.ddmore.mdl.mdl.TargetBlock;
import org.ddmore.mdl.mdl.TaskFunctionDeclaration;
import org.ddmore.mdl.mdl.TaskObjectBlock;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Task Object Block</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.ddmore.mdl.mdl.impl.TaskObjectBlockImpl#getFunctionDeclaration <em>Function Declaration</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.TaskObjectBlockImpl#getParameterBlock <em>Parameter Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.TaskObjectBlockImpl#getDataBlock <em>Data Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.TaskObjectBlockImpl#getModelBlock <em>Model Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.TaskObjectBlockImpl#getTargetBlock <em>Target Block</em>}</li>
 *   <li>{@link org.ddmore.mdl.mdl.impl.TaskObjectBlockImpl#getImportBlock <em>Import Block</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TaskObjectBlockImpl extends MinimalEObjectImpl.Container implements TaskObjectBlock
{
  /**
   * The cached value of the '{@link #getFunctionDeclaration() <em>Function Declaration</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFunctionDeclaration()
   * @generated
   * @ordered
   */
  protected TaskFunctionDeclaration functionDeclaration;

  /**
   * The cached value of the '{@link #getParameterBlock() <em>Parameter Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameterBlock()
   * @generated
   * @ordered
   */
  protected ParameterBlock parameterBlock;

  /**
   * The cached value of the '{@link #getDataBlock() <em>Data Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDataBlock()
   * @generated
   * @ordered
   */
  protected DataBlock dataBlock;

  /**
   * The cached value of the '{@link #getModelBlock() <em>Model Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelBlock()
   * @generated
   * @ordered
   */
  protected ModelBlock modelBlock;

  /**
   * The cached value of the '{@link #getTargetBlock() <em>Target Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetBlock()
   * @generated
   * @ordered
   */
  protected TargetBlock targetBlock;

  /**
   * The cached value of the '{@link #getImportBlock() <em>Import Block</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getImportBlock()
   * @generated
   * @ordered
   */
  protected ImportBlock importBlock;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TaskObjectBlockImpl()
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
    return MdlPackage.Literals.TASK_OBJECT_BLOCK;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TaskFunctionDeclaration getFunctionDeclaration()
  {
    return functionDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFunctionDeclaration(TaskFunctionDeclaration newFunctionDeclaration, NotificationChain msgs)
  {
    TaskFunctionDeclaration oldFunctionDeclaration = functionDeclaration;
    functionDeclaration = newFunctionDeclaration;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJECT_BLOCK__FUNCTION_DECLARATION, oldFunctionDeclaration, newFunctionDeclaration);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFunctionDeclaration(TaskFunctionDeclaration newFunctionDeclaration)
  {
    if (newFunctionDeclaration != functionDeclaration)
    {
      NotificationChain msgs = null;
      if (functionDeclaration != null)
        msgs = ((InternalEObject)functionDeclaration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJECT_BLOCK__FUNCTION_DECLARATION, null, msgs);
      if (newFunctionDeclaration != null)
        msgs = ((InternalEObject)newFunctionDeclaration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJECT_BLOCK__FUNCTION_DECLARATION, null, msgs);
      msgs = basicSetFunctionDeclaration(newFunctionDeclaration, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJECT_BLOCK__FUNCTION_DECLARATION, newFunctionDeclaration, newFunctionDeclaration));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParameterBlock getParameterBlock()
  {
    return parameterBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParameterBlock(ParameterBlock newParameterBlock, NotificationChain msgs)
  {
    ParameterBlock oldParameterBlock = parameterBlock;
    parameterBlock = newParameterBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJECT_BLOCK__PARAMETER_BLOCK, oldParameterBlock, newParameterBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParameterBlock(ParameterBlock newParameterBlock)
  {
    if (newParameterBlock != parameterBlock)
    {
      NotificationChain msgs = null;
      if (parameterBlock != null)
        msgs = ((InternalEObject)parameterBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJECT_BLOCK__PARAMETER_BLOCK, null, msgs);
      if (newParameterBlock != null)
        msgs = ((InternalEObject)newParameterBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJECT_BLOCK__PARAMETER_BLOCK, null, msgs);
      msgs = basicSetParameterBlock(newParameterBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJECT_BLOCK__PARAMETER_BLOCK, newParameterBlock, newParameterBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DataBlock getDataBlock()
  {
    return dataBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDataBlock(DataBlock newDataBlock, NotificationChain msgs)
  {
    DataBlock oldDataBlock = dataBlock;
    dataBlock = newDataBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJECT_BLOCK__DATA_BLOCK, oldDataBlock, newDataBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDataBlock(DataBlock newDataBlock)
  {
    if (newDataBlock != dataBlock)
    {
      NotificationChain msgs = null;
      if (dataBlock != null)
        msgs = ((InternalEObject)dataBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJECT_BLOCK__DATA_BLOCK, null, msgs);
      if (newDataBlock != null)
        msgs = ((InternalEObject)newDataBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJECT_BLOCK__DATA_BLOCK, null, msgs);
      msgs = basicSetDataBlock(newDataBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJECT_BLOCK__DATA_BLOCK, newDataBlock, newDataBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelBlock getModelBlock()
  {
    return modelBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetModelBlock(ModelBlock newModelBlock, NotificationChain msgs)
  {
    ModelBlock oldModelBlock = modelBlock;
    modelBlock = newModelBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJECT_BLOCK__MODEL_BLOCK, oldModelBlock, newModelBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModelBlock(ModelBlock newModelBlock)
  {
    if (newModelBlock != modelBlock)
    {
      NotificationChain msgs = null;
      if (modelBlock != null)
        msgs = ((InternalEObject)modelBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJECT_BLOCK__MODEL_BLOCK, null, msgs);
      if (newModelBlock != null)
        msgs = ((InternalEObject)newModelBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJECT_BLOCK__MODEL_BLOCK, null, msgs);
      msgs = basicSetModelBlock(newModelBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJECT_BLOCK__MODEL_BLOCK, newModelBlock, newModelBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TargetBlock getTargetBlock()
  {
    return targetBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTargetBlock(TargetBlock newTargetBlock, NotificationChain msgs)
  {
    TargetBlock oldTargetBlock = targetBlock;
    targetBlock = newTargetBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJECT_BLOCK__TARGET_BLOCK, oldTargetBlock, newTargetBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetBlock(TargetBlock newTargetBlock)
  {
    if (newTargetBlock != targetBlock)
    {
      NotificationChain msgs = null;
      if (targetBlock != null)
        msgs = ((InternalEObject)targetBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJECT_BLOCK__TARGET_BLOCK, null, msgs);
      if (newTargetBlock != null)
        msgs = ((InternalEObject)newTargetBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJECT_BLOCK__TARGET_BLOCK, null, msgs);
      msgs = basicSetTargetBlock(newTargetBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJECT_BLOCK__TARGET_BLOCK, newTargetBlock, newTargetBlock));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ImportBlock getImportBlock()
  {
    return importBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetImportBlock(ImportBlock newImportBlock, NotificationChain msgs)
  {
    ImportBlock oldImportBlock = importBlock;
    importBlock = newImportBlock;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJECT_BLOCK__IMPORT_BLOCK, oldImportBlock, newImportBlock);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setImportBlock(ImportBlock newImportBlock)
  {
    if (newImportBlock != importBlock)
    {
      NotificationChain msgs = null;
      if (importBlock != null)
        msgs = ((InternalEObject)importBlock).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJECT_BLOCK__IMPORT_BLOCK, null, msgs);
      if (newImportBlock != null)
        msgs = ((InternalEObject)newImportBlock).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MdlPackage.TASK_OBJECT_BLOCK__IMPORT_BLOCK, null, msgs);
      msgs = basicSetImportBlock(newImportBlock, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MdlPackage.TASK_OBJECT_BLOCK__IMPORT_BLOCK, newImportBlock, newImportBlock));
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
      case MdlPackage.TASK_OBJECT_BLOCK__FUNCTION_DECLARATION:
        return basicSetFunctionDeclaration(null, msgs);
      case MdlPackage.TASK_OBJECT_BLOCK__PARAMETER_BLOCK:
        return basicSetParameterBlock(null, msgs);
      case MdlPackage.TASK_OBJECT_BLOCK__DATA_BLOCK:
        return basicSetDataBlock(null, msgs);
      case MdlPackage.TASK_OBJECT_BLOCK__MODEL_BLOCK:
        return basicSetModelBlock(null, msgs);
      case MdlPackage.TASK_OBJECT_BLOCK__TARGET_BLOCK:
        return basicSetTargetBlock(null, msgs);
      case MdlPackage.TASK_OBJECT_BLOCK__IMPORT_BLOCK:
        return basicSetImportBlock(null, msgs);
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
      case MdlPackage.TASK_OBJECT_BLOCK__FUNCTION_DECLARATION:
        return getFunctionDeclaration();
      case MdlPackage.TASK_OBJECT_BLOCK__PARAMETER_BLOCK:
        return getParameterBlock();
      case MdlPackage.TASK_OBJECT_BLOCK__DATA_BLOCK:
        return getDataBlock();
      case MdlPackage.TASK_OBJECT_BLOCK__MODEL_BLOCK:
        return getModelBlock();
      case MdlPackage.TASK_OBJECT_BLOCK__TARGET_BLOCK:
        return getTargetBlock();
      case MdlPackage.TASK_OBJECT_BLOCK__IMPORT_BLOCK:
        return getImportBlock();
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
      case MdlPackage.TASK_OBJECT_BLOCK__FUNCTION_DECLARATION:
        setFunctionDeclaration((TaskFunctionDeclaration)newValue);
        return;
      case MdlPackage.TASK_OBJECT_BLOCK__PARAMETER_BLOCK:
        setParameterBlock((ParameterBlock)newValue);
        return;
      case MdlPackage.TASK_OBJECT_BLOCK__DATA_BLOCK:
        setDataBlock((DataBlock)newValue);
        return;
      case MdlPackage.TASK_OBJECT_BLOCK__MODEL_BLOCK:
        setModelBlock((ModelBlock)newValue);
        return;
      case MdlPackage.TASK_OBJECT_BLOCK__TARGET_BLOCK:
        setTargetBlock((TargetBlock)newValue);
        return;
      case MdlPackage.TASK_OBJECT_BLOCK__IMPORT_BLOCK:
        setImportBlock((ImportBlock)newValue);
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
      case MdlPackage.TASK_OBJECT_BLOCK__FUNCTION_DECLARATION:
        setFunctionDeclaration((TaskFunctionDeclaration)null);
        return;
      case MdlPackage.TASK_OBJECT_BLOCK__PARAMETER_BLOCK:
        setParameterBlock((ParameterBlock)null);
        return;
      case MdlPackage.TASK_OBJECT_BLOCK__DATA_BLOCK:
        setDataBlock((DataBlock)null);
        return;
      case MdlPackage.TASK_OBJECT_BLOCK__MODEL_BLOCK:
        setModelBlock((ModelBlock)null);
        return;
      case MdlPackage.TASK_OBJECT_BLOCK__TARGET_BLOCK:
        setTargetBlock((TargetBlock)null);
        return;
      case MdlPackage.TASK_OBJECT_BLOCK__IMPORT_BLOCK:
        setImportBlock((ImportBlock)null);
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
      case MdlPackage.TASK_OBJECT_BLOCK__FUNCTION_DECLARATION:
        return functionDeclaration != null;
      case MdlPackage.TASK_OBJECT_BLOCK__PARAMETER_BLOCK:
        return parameterBlock != null;
      case MdlPackage.TASK_OBJECT_BLOCK__DATA_BLOCK:
        return dataBlock != null;
      case MdlPackage.TASK_OBJECT_BLOCK__MODEL_BLOCK:
        return modelBlock != null;
      case MdlPackage.TASK_OBJECT_BLOCK__TARGET_BLOCK:
        return targetBlock != null;
      case MdlPackage.TASK_OBJECT_BLOCK__IMPORT_BLOCK:
        return importBlock != null;
    }
    return super.eIsSet(featureID);
  }

} //TaskObjectBlockImpl
