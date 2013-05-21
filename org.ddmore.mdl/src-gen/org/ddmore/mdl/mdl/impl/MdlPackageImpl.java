/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.AcceptList;
import org.ddmore.mdl.mdl.AddList;
import org.ddmore.mdl.mdl.AdditiveExpression;
import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.Block;
import org.ddmore.mdl.mdl.BlockBlock;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.Categorical;
import org.ddmore.mdl.mdl.ConditionalExpression;
import org.ddmore.mdl.mdl.ConditionalStatement;
import org.ddmore.mdl.mdl.Continuous;
import org.ddmore.mdl.mdl.Covariate;
import org.ddmore.mdl.mdl.DataBlock;
import org.ddmore.mdl.mdl.DataBlockStatement;
import org.ddmore.mdl.mdl.DataObject;
import org.ddmore.mdl.mdl.DataObjectBlock;
import org.ddmore.mdl.mdl.DesignBlock;
import org.ddmore.mdl.mdl.DesignBlockStatement;
import org.ddmore.mdl.mdl.DiagBlock;
import org.ddmore.mdl.mdl.Distribution;
import org.ddmore.mdl.mdl.DropList;
import org.ddmore.mdl.mdl.EnumType;
import org.ddmore.mdl.mdl.EstimateTask;
import org.ddmore.mdl.mdl.EstimationBlock;
import org.ddmore.mdl.mdl.ExecuteTask;
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.FileBlock;
import org.ddmore.mdl.mdl.FileBlockStatement;
import org.ddmore.mdl.mdl.FormalArguments;
import org.ddmore.mdl.mdl.FullyQualifiedArgumentName;
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.GroupVariablesBlock;
import org.ddmore.mdl.mdl.GroupVariablesBlockStatement;
import org.ddmore.mdl.mdl.HeaderBlock;
import org.ddmore.mdl.mdl.IgnoreList;
import org.ddmore.mdl.mdl.IndividualVariablesBlock;
import org.ddmore.mdl.mdl.InlineBlock;
import org.ddmore.mdl.mdl.InputVariablesBlock;
import org.ddmore.mdl.mdl.LevelType;
import org.ddmore.mdl.mdl.LibraryBlock;
import org.ddmore.mdl.mdl.Likelyhood;
import org.ddmore.mdl.mdl.List;
import org.ddmore.mdl.mdl.LogicalExpression;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.MdlFactory;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.Missing;
import org.ddmore.mdl.mdl.MixtureBlock;
import org.ddmore.mdl.mdl.ModelBlock;
import org.ddmore.mdl.mdl.ModelBlockStatement;
import org.ddmore.mdl.mdl.ModelObject;
import org.ddmore.mdl.mdl.ModelObjectBlock;
import org.ddmore.mdl.mdl.ModelPredictionBlock;
import org.ddmore.mdl.mdl.ModelPredictionBlockStatement;
import org.ddmore.mdl.mdl.MultiplicativeExpression;
import org.ddmore.mdl.mdl.ObjectName;
import org.ddmore.mdl.mdl.ObservationBlock;
import org.ddmore.mdl.mdl.OdeBlock;
import org.ddmore.mdl.mdl.OdeList;
import org.ddmore.mdl.mdl.OrExpression;
import org.ddmore.mdl.mdl.OutputVariablesBlock;
import org.ddmore.mdl.mdl.ParExpression;
import org.ddmore.mdl.mdl.ParameterBlock;
import org.ddmore.mdl.mdl.ParameterDeclaration;
import org.ddmore.mdl.mdl.ParameterObject;
import org.ddmore.mdl.mdl.ParameterObjectBlock;
import org.ddmore.mdl.mdl.PowerExpression;
import org.ddmore.mdl.mdl.Primary;
import org.ddmore.mdl.mdl.PriorParametersBlock;
import org.ddmore.mdl.mdl.RScriptBlock;
import org.ddmore.mdl.mdl.RScriptBlockStatement;
import org.ddmore.mdl.mdl.RandomList;
import org.ddmore.mdl.mdl.RandomVariableDefinitionBlock;
import org.ddmore.mdl.mdl.RemoveList;
import org.ddmore.mdl.mdl.SameBlock;
import org.ddmore.mdl.mdl.SimulateTask;
import org.ddmore.mdl.mdl.SimulationBlock;
import org.ddmore.mdl.mdl.StructuralBlock;
import org.ddmore.mdl.mdl.StructuralParametersBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.SymbolList;
import org.ddmore.mdl.mdl.SymbolModification;
import org.ddmore.mdl.mdl.TELObject;
import org.ddmore.mdl.mdl.TargetBlock;
import org.ddmore.mdl.mdl.TaskFunctionBlock;
import org.ddmore.mdl.mdl.TaskFunctionBody;
import org.ddmore.mdl.mdl.TaskFunctionDeclaration;
import org.ddmore.mdl.mdl.TaskObject;
import org.ddmore.mdl.mdl.TaskObjectBlock;
import org.ddmore.mdl.mdl.UnaryExpression;
import org.ddmore.mdl.mdl.VariabilityBlock;
import org.ddmore.mdl.mdl.VariabilityBlockStatement;
import org.ddmore.mdl.mdl.VariabilityParametersBlock;
import org.ddmore.mdl.mdl.VariableList;
import org.ddmore.mdl.mdl.Vector;
import org.ddmore.mdl.mdl.VerbatimBlock;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MdlPackageImpl extends EPackageImpl implements MdlPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mclEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mclObjectEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modelObjectEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parameterObjectEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass dataObjectEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass taskObjectEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass telObjectEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modelObjectBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass individualVariablesBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modelPredictionBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass randomVariableDefinitionBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass inputVariablesBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass structuralParametersBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variabilityParametersBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass outputVariablesBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass groupVariablesBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass observationBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass estimationBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass simulationBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parameterObjectBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass structuralBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variabilityBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass priorParametersBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass dataObjectBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass headerBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fileBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass taskObjectBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parameterBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass dataBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass dataBlockStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ignoreListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass acceptListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass dropListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modelBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modelBlockStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass addListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass removeListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass symbolListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modelPredictionBlockStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass libraryBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass odeBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass groupVariablesBlockStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass mixtureBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variabilityBlockStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass blockBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass diagBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass sameBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fileBlockStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass inlineBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass designBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass designBlockStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variableListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass rScriptBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass rScriptBlockStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass taskFunctionDeclarationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass taskFunctionBodyEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass taskFunctionBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass estimateTaskEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass simulateTaskEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass executeTaskEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass formalArgumentsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass functionCallEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass blockStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass verbatimBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass targetBlockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass symbolModificationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parameterDeclarationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass symbolDeclarationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enumTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass missingEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass likelyhoodEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass levelTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass categoricalEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass continuousEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass covariateEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass distributionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass anyExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass expressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass listEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass odeListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass randomListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass argumentsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass argumentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass conditionalStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass blockEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass conditionalExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass orExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass andExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass logicalExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass additiveExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass multiplicativeExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass powerExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass unaryExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass primaryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass vectorEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fullyQualifiedSymbolNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass fullyQualifiedArgumentNameEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass objectNameEClass = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.ddmore.mdl.mdl.MdlPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private MdlPackageImpl()
  {
    super(eNS_URI, MdlFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link MdlPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static MdlPackage init()
  {
    if (isInited) return (MdlPackage)EPackage.Registry.INSTANCE.getEPackage(MdlPackage.eNS_URI);

    // Obtain or create and register package
    MdlPackageImpl theMdlPackage = (MdlPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MdlPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new MdlPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theMdlPackage.createPackageContents();

    // Initialize created meta-data
    theMdlPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theMdlPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(MdlPackage.eNS_URI, theMdlPackage);
    return theMdlPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMcl()
  {
    return mclEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMcl_Objects()
  {
    return (EReference)mclEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMclObject()
  {
    return mclObjectEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMclObject_ModelObject()
  {
    return (EReference)mclObjectEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMclObject_ParameterObject()
  {
    return (EReference)mclObjectEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMclObject_DataObject()
  {
    return (EReference)mclObjectEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMclObject_TaskObject()
  {
    return (EReference)mclObjectEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMclObject_TelObject()
  {
    return (EReference)mclObjectEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModelObject()
  {
    return modelObjectEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelObject_Identifier()
  {
    return (EReference)modelObjectEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelObject_Blocks()
  {
    return (EReference)modelObjectEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParameterObject()
  {
    return parameterObjectEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getParameterObject_Identifier()
  {
    return (EReference)parameterObjectEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getParameterObject_Blocks()
  {
    return (EReference)parameterObjectEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDataObject()
  {
    return dataObjectEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDataObject_Identifier()
  {
    return (EReference)dataObjectEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDataObject_Blocks()
  {
    return (EReference)dataObjectEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTaskObject()
  {
    return taskObjectEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTaskObject_Identifier()
  {
    return (EReference)taskObjectEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTaskObject_Blocks()
  {
    return (EReference)taskObjectEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTELObject()
  {
    return telObjectEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTELObject_Identifier()
  {
    return (EReference)telObjectEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTELObject_Statements()
  {
    return (EReference)telObjectEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModelObjectBlock()
  {
    return modelObjectBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelObjectBlock_IndividualVariablesBlock()
  {
    return (EReference)modelObjectBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelObjectBlock_ModelPredictionBlock()
  {
    return (EReference)modelObjectBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelObjectBlock_RandomVariableDefinitionBlock()
  {
    return (EReference)modelObjectBlockEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelObjectBlock_InputVariablesBlock()
  {
    return (EReference)modelObjectBlockEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelObjectBlock_StructuralParametersBlock()
  {
    return (EReference)modelObjectBlockEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelObjectBlock_VariabilityParametersBlock()
  {
    return (EReference)modelObjectBlockEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelObjectBlock_OutputVariablesBlock()
  {
    return (EReference)modelObjectBlockEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelObjectBlock_GroupVariablesBlock()
  {
    return (EReference)modelObjectBlockEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelObjectBlock_ObservationBlock()
  {
    return (EReference)modelObjectBlockEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelObjectBlock_EstimationBlock()
  {
    return (EReference)modelObjectBlockEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelObjectBlock_SimulationBlock()
  {
    return (EReference)modelObjectBlockEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelObjectBlock_VerbatimBlock()
  {
    return (EReference)modelObjectBlockEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIndividualVariablesBlock()
  {
    return individualVariablesBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIndividualVariablesBlock_Identifier()
  {
    return (EAttribute)individualVariablesBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIndividualVariablesBlock_Statements()
  {
    return (EReference)individualVariablesBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModelPredictionBlock()
  {
    return modelPredictionBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getModelPredictionBlock_Identifier()
  {
    return (EAttribute)modelPredictionBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelPredictionBlock_Statements()
  {
    return (EReference)modelPredictionBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRandomVariableDefinitionBlock()
  {
    return randomVariableDefinitionBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRandomVariableDefinitionBlock_Identifier()
  {
    return (EAttribute)randomVariableDefinitionBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRandomVariableDefinitionBlock_Variables()
  {
    return (EReference)randomVariableDefinitionBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getInputVariablesBlock()
  {
    return inputVariablesBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getInputVariablesBlock_Indentifier()
  {
    return (EAttribute)inputVariablesBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getInputVariablesBlock_Variables()
  {
    return (EReference)inputVariablesBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStructuralParametersBlock()
  {
    return structuralParametersBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getStructuralParametersBlock_Identifier()
  {
    return (EAttribute)structuralParametersBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getStructuralParametersBlock_Parameters()
  {
    return (EReference)structuralParametersBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVariabilityParametersBlock()
  {
    return variabilityParametersBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVariabilityParametersBlock_Identifier()
  {
    return (EAttribute)variabilityParametersBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getVariabilityParametersBlock_Parameters()
  {
    return (EReference)variabilityParametersBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOutputVariablesBlock()
  {
    return outputVariablesBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOutputVariablesBlock_Identifier()
  {
    return (EAttribute)outputVariablesBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOutputVariablesBlock_Variables()
  {
    return (EReference)outputVariablesBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGroupVariablesBlock()
  {
    return groupVariablesBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGroupVariablesBlock_Identifier()
  {
    return (EAttribute)groupVariablesBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGroupVariablesBlock_Statements()
  {
    return (EReference)groupVariablesBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getObservationBlock()
  {
    return observationBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getObservationBlock_Identifier()
  {
    return (EAttribute)observationBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getObservationBlock_Statements()
  {
    return (EReference)observationBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEstimationBlock()
  {
    return estimationBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEstimationBlock_Identifier()
  {
    return (EAttribute)estimationBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEstimationBlock_Statements()
  {
    return (EReference)estimationBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSimulationBlock()
  {
    return simulationBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSimulationBlock_Identifier()
  {
    return (EAttribute)simulationBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSimulationBlock_Statements()
  {
    return (EReference)simulationBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParameterObjectBlock()
  {
    return parameterObjectBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getParameterObjectBlock_StructuralBlock()
  {
    return (EReference)parameterObjectBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getParameterObjectBlock_VariabilityBlock()
  {
    return (EReference)parameterObjectBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getParameterObjectBlock_PriorBlock()
  {
    return (EReference)parameterObjectBlockEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getParameterObjectBlock_VerbatimBlock()
  {
    return (EReference)parameterObjectBlockEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStructuralBlock()
  {
    return structuralBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getStructuralBlock_Identifier()
  {
    return (EAttribute)structuralBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getStructuralBlock_Parameters()
  {
    return (EReference)structuralBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVariabilityBlock()
  {
    return variabilityBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVariabilityBlock_Identifier()
  {
    return (EAttribute)variabilityBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getVariabilityBlock_Statements()
  {
    return (EReference)variabilityBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPriorParametersBlock()
  {
    return priorParametersBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPriorParametersBlock_Identifier()
  {
    return (EAttribute)priorParametersBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPriorParametersBlock_Statements()
  {
    return (EReference)priorParametersBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDataObjectBlock()
  {
    return dataObjectBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDataObjectBlock_HeaderBlock()
  {
    return (EReference)dataObjectBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDataObjectBlock_FileBlock()
  {
    return (EReference)dataObjectBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDataObjectBlock_VerbatimBlock()
  {
    return (EReference)dataObjectBlockEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getHeaderBlock()
  {
    return headerBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getHeaderBlock_Identifier()
  {
    return (EAttribute)headerBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getHeaderBlock_Variables()
  {
    return (EReference)headerBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFileBlock()
  {
    return fileBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFileBlock_Identifier()
  {
    return (EAttribute)fileBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFileBlock_Statements()
  {
    return (EReference)fileBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTaskObjectBlock()
  {
    return taskObjectBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTaskObjectBlock_FunctionDeclaration()
  {
    return (EReference)taskObjectBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTaskObjectBlock_ParameterBlock()
  {
    return (EReference)taskObjectBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTaskObjectBlock_DataBlock()
  {
    return (EReference)taskObjectBlockEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTaskObjectBlock_ModelBlock()
  {
    return (EReference)taskObjectBlockEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTaskObjectBlock_VerbatimBlock()
  {
    return (EReference)taskObjectBlockEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParameterBlock()
  {
    return parameterBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getParameterBlock_Identifier()
  {
    return (EAttribute)parameterBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getParameterBlock_Parameters()
  {
    return (EReference)parameterBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDataBlock()
  {
    return dataBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDataBlock_Identifier()
  {
    return (EAttribute)dataBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDataBlock_Statements()
  {
    return (EReference)dataBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDataBlockStatement()
  {
    return dataBlockStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDataBlockStatement_IgnoreList()
  {
    return (EReference)dataBlockStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDataBlockStatement_AcceptList()
  {
    return (EReference)dataBlockStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDataBlockStatement_DropList()
  {
    return (EReference)dataBlockStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIgnoreList()
  {
    return ignoreListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIgnoreList_Identifier()
  {
    return (EAttribute)ignoreListEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIgnoreList_Expression()
  {
    return (EReference)ignoreListEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAcceptList()
  {
    return acceptListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAcceptList_Identifier()
  {
    return (EAttribute)acceptListEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAcceptList_Expression()
  {
    return (EReference)acceptListEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDropList()
  {
    return dropListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDropList_Identifier()
  {
    return (EAttribute)dropListEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDropList_List()
  {
    return (EReference)dropListEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModelBlock()
  {
    return modelBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getModelBlock_Identifier()
  {
    return (EAttribute)modelBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelBlock_Statements()
  {
    return (EReference)modelBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModelBlockStatement()
  {
    return modelBlockStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelBlockStatement_Statement()
  {
    return (EReference)modelBlockStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelBlockStatement_AddList()
  {
    return (EReference)modelBlockStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelBlockStatement_RemoveList()
  {
    return (EReference)modelBlockStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAddList()
  {
    return addListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAddList_List()
  {
    return (EReference)addListEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRemoveList()
  {
    return removeListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRemoveList_List()
  {
    return (EReference)removeListEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSymbolList()
  {
    return symbolListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSymbolList_Symbols()
  {
    return (EReference)symbolListEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModelPredictionBlockStatement()
  {
    return modelPredictionBlockStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelPredictionBlockStatement_Statement()
  {
    return (EReference)modelPredictionBlockStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelPredictionBlockStatement_OdeBlock()
  {
    return (EReference)modelPredictionBlockStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getModelPredictionBlockStatement_LibraryBlock()
  {
    return (EReference)modelPredictionBlockStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLibraryBlock()
  {
    return libraryBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLibraryBlock_Identifier()
  {
    return (EAttribute)libraryBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLibraryBlock_Statements()
  {
    return (EReference)libraryBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOdeBlock()
  {
    return odeBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOdeBlock_Identifier()
  {
    return (EAttribute)odeBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOdeBlock_Statements()
  {
    return (EReference)odeBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGroupVariablesBlockStatement()
  {
    return groupVariablesBlockStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGroupVariablesBlockStatement_Statement()
  {
    return (EReference)groupVariablesBlockStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGroupVariablesBlockStatement_MixtureBlock()
  {
    return (EReference)groupVariablesBlockStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMixtureBlock()
  {
    return mixtureBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMixtureBlock_Identifier()
  {
    return (EAttribute)mixtureBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMixtureBlock_Statements()
  {
    return (EReference)mixtureBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVariabilityBlockStatement()
  {
    return variabilityBlockStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getVariabilityBlockStatement_Parameter()
  {
    return (EReference)variabilityBlockStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getVariabilityBlockStatement_BlockBlock()
  {
    return (EReference)variabilityBlockStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getVariabilityBlockStatement_DiagBlock()
  {
    return (EReference)variabilityBlockStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getVariabilityBlockStatement_SameBlock()
  {
    return (EReference)variabilityBlockStatementEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBlockBlock()
  {
    return blockBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getBlockBlock_Identifier()
  {
    return (EAttribute)blockBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBlockBlock_Arguments()
  {
    return (EReference)blockBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBlockBlock_Parameters()
  {
    return (EReference)blockBlockEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDiagBlock()
  {
    return diagBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDiagBlock_Identifier()
  {
    return (EAttribute)diagBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDiagBlock_Arguments()
  {
    return (EReference)diagBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDiagBlock_Parameters()
  {
    return (EReference)diagBlockEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSameBlock()
  {
    return sameBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSameBlock_Identifier()
  {
    return (EAttribute)sameBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSameBlock_Arguments()
  {
    return (EReference)sameBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSameBlock_Parameters()
  {
    return (EReference)sameBlockEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFileBlockStatement()
  {
    return fileBlockStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFileBlockStatement_Variable()
  {
    return (EReference)fileBlockStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFileBlockStatement_InlineBlock()
  {
    return (EReference)fileBlockStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFileBlockStatement_DesignBlock()
  {
    return (EReference)fileBlockStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFileBlockStatement_RscriptBlock()
  {
    return (EReference)fileBlockStatementEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getInlineBlock()
  {
    return inlineBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getInlineBlock_Identifier()
  {
    return (EAttribute)inlineBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getInlineBlock_Variables()
  {
    return (EReference)inlineBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getInlineBlock_Values()
  {
    return (EAttribute)inlineBlockEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDesignBlock()
  {
    return designBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDesignBlock_Identifier()
  {
    return (EAttribute)designBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDesignBlock_Statements()
  {
    return (EReference)designBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDesignBlockStatement()
  {
    return designBlockStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDesignBlockStatement_Identifier()
  {
    return (EReference)designBlockStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDesignBlockStatement_Arguments()
  {
    return (EReference)designBlockStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getDesignBlockStatement_Expression()
  {
    return (EReference)designBlockStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVariableList()
  {
    return variableListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getVariableList_Identifiers()
  {
    return (EReference)variableListEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRScriptBlock()
  {
    return rScriptBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRScriptBlock_Identifier()
  {
    return (EAttribute)rScriptBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRScriptBlock_Variables()
  {
    return (EReference)rScriptBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRScriptBlockStatement()
  {
    return rScriptBlockStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRScriptBlockStatement_Identifier()
  {
    return (EAttribute)rScriptBlockStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRScriptBlockStatement_Value()
  {
    return (EAttribute)rScriptBlockStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRScriptBlockStatement_Object()
  {
    return (EReference)rScriptBlockStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTaskFunctionDeclaration()
  {
    return taskFunctionDeclarationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTaskFunctionDeclaration_Identifier()
  {
    return (EAttribute)taskFunctionDeclarationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTaskFunctionDeclaration_FormalArguments()
  {
    return (EReference)taskFunctionDeclarationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTaskFunctionDeclaration_FunctionBody()
  {
    return (EReference)taskFunctionDeclarationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTaskFunctionBody()
  {
    return taskFunctionBodyEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTaskFunctionBody_Blocks()
  {
    return (EReference)taskFunctionBodyEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTaskFunctionBlock()
  {
    return taskFunctionBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTaskFunctionBlock_EstimateBlock()
  {
    return (EReference)taskFunctionBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTaskFunctionBlock_SimulateBlock()
  {
    return (EReference)taskFunctionBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTaskFunctionBlock_ExecuteBlock()
  {
    return (EReference)taskFunctionBlockEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEstimateTask()
  {
    return estimateTaskEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEstimateTask_Identifier()
  {
    return (EAttribute)estimateTaskEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEstimateTask_Statements()
  {
    return (EReference)estimateTaskEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSimulateTask()
  {
    return simulateTaskEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSimulateTask_Identifier()
  {
    return (EAttribute)simulateTaskEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSimulateTask_Statements()
  {
    return (EReference)simulateTaskEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExecuteTask()
  {
    return executeTaskEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExecuteTask_Identifier()
  {
    return (EAttribute)executeTaskEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExecuteTask_Statements()
  {
    return (EReference)executeTaskEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFormalArguments()
  {
    return formalArgumentsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFormalArguments_Identifiers()
  {
    return (EAttribute)formalArgumentsEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFunctionCall()
  {
    return functionCallEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFunctionCall_Identifier()
  {
    return (EReference)functionCallEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFunctionCall_Arguments()
  {
    return (EReference)functionCallEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBlockStatement()
  {
    return blockStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBlockStatement_Symbol()
  {
    return (EReference)blockStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBlockStatement_FunctionCall()
  {
    return (EReference)blockStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBlockStatement_Statement()
  {
    return (EReference)blockStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBlockStatement_VerbatimBlock()
  {
    return (EReference)blockStatementEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVerbatimBlock()
  {
    return verbatimBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVerbatimBlock_Identifier()
  {
    return (EAttribute)verbatimBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getVerbatimBlock_Block()
  {
    return (EReference)verbatimBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVerbatimBlock_ExternalCode()
  {
    return (EAttribute)verbatimBlockEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTargetBlock()
  {
    return targetBlockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTargetBlock_Identifier()
  {
    return (EAttribute)targetBlockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTargetBlock_Arguments()
  {
    return (EReference)targetBlockEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTargetBlock_ExternalCode()
  {
    return (EAttribute)targetBlockEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSymbolModification()
  {
    return symbolModificationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSymbolModification_Identifier()
  {
    return (EReference)symbolModificationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSymbolModification_List()
  {
    return (EReference)symbolModificationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParameterDeclaration()
  {
    return parameterDeclarationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getParameterDeclaration_Identifier()
  {
    return (EAttribute)parameterDeclarationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getParameterDeclaration_List()
  {
    return (EReference)parameterDeclarationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSymbolDeclaration()
  {
    return symbolDeclarationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSymbolDeclaration_Identifier()
  {
    return (EAttribute)symbolDeclarationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSymbolDeclaration_Expression()
  {
    return (EReference)symbolDeclarationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSymbolDeclaration_RandomList()
  {
    return (EReference)symbolDeclarationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSymbolDeclaration_Function()
  {
    return (EAttribute)symbolDeclarationEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEnumType()
  {
    return enumTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumType_Categorical()
  {
    return (EReference)enumTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumType_Continuous()
  {
    return (EReference)enumTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumType_Covariate()
  {
    return (EReference)enumTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumType_Distribution()
  {
    return (EReference)enumTypeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumType_Level()
  {
    return (EReference)enumTypeEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumType_Likelyhood()
  {
    return (EReference)enumTypeEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getEnumType_Missing()
  {
    return (EReference)enumTypeEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMissing()
  {
    return missingEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMissing_Identifier()
  {
    return (EAttribute)missingEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLikelyhood()
  {
    return likelyhoodEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLikelyhood_Identifier()
  {
    return (EAttribute)likelyhoodEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLevelType()
  {
    return levelTypeEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLevelType_Mdv()
  {
    return (EAttribute)levelTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLevelType_Id()
  {
    return (EAttribute)levelTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLevelType_Dv()
  {
    return (EAttribute)levelTypeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLevelType_Idv()
  {
    return (EAttribute)levelTypeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCategorical()
  {
    return categoricalEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCategorical_Identifier()
  {
    return (EAttribute)categoricalEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCategorical_Arguments()
  {
    return (EReference)categoricalEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getContinuous()
  {
    return continuousEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getContinuous_Identifier()
  {
    return (EAttribute)continuousEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCovariate()
  {
    return covariateEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCovariate_Identifier()
  {
    return (EAttribute)covariateEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getDistribution()
  {
    return distributionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDistribution_Normal()
  {
    return (EAttribute)distributionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDistribution_Binomial()
  {
    return (EAttribute)distributionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDistribution_Poisson()
  {
    return (EAttribute)distributionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDistribution_Student_t()
  {
    return (EAttribute)distributionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getDistribution_Mvnormal()
  {
    return (EAttribute)distributionEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAnyExpression()
  {
    return anyExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAnyExpression_Expression()
  {
    return (EReference)anyExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAnyExpression_List()
  {
    return (EReference)anyExpressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAnyExpression_OdeList()
  {
    return (EReference)anyExpressionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAnyExpression_Type()
  {
    return (EReference)anyExpressionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExpression()
  {
    return expressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExpression_ConditionalExpression()
  {
    return (EReference)expressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getList()
  {
    return listEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getList_Arguments()
  {
    return (EReference)listEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOdeList()
  {
    return odeListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOdeList_Arguments()
  {
    return (EReference)odeListEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRandomList()
  {
    return randomListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRandomList_Arguments()
  {
    return (EReference)randomListEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getArguments()
  {
    return argumentsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getArguments_Arguments()
  {
    return (EReference)argumentsEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getArgument()
  {
    return argumentEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getArgument_Identifier()
  {
    return (EAttribute)argumentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getArgument_Expression()
  {
    return (EReference)argumentEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConditionalStatement()
  {
    return conditionalStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConditionalStatement_ParExpression()
  {
    return (EReference)conditionalStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConditionalStatement_IfStatement()
  {
    return (EReference)conditionalStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConditionalStatement_IfBlock()
  {
    return (EReference)conditionalStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConditionalStatement_ElseStatement()
  {
    return (EReference)conditionalStatementEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConditionalStatement_ElseBlock()
  {
    return (EReference)conditionalStatementEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBlock()
  {
    return blockEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBlock_Statements()
  {
    return (EReference)blockEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParExpression()
  {
    return parExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getParExpression_Expression()
  {
    return (EReference)parExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConditionalExpression()
  {
    return conditionalExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConditionalExpression_Expression()
  {
    return (EReference)conditionalExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConditionalExpression_Expression1()
  {
    return (EReference)conditionalExpressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConditionalExpression_Expression2()
  {
    return (EReference)conditionalExpressionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOrExpression()
  {
    return orExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOrExpression_Expression()
  {
    return (EReference)orExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getOrExpression_Operator()
  {
    return (EAttribute)orExpressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAndExpression()
  {
    return andExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAndExpression_Expression()
  {
    return (EReference)andExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAndExpression_Operator()
  {
    return (EAttribute)andExpressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLogicalExpression()
  {
    return logicalExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLogicalExpression_Negation()
  {
    return (EAttribute)logicalExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLogicalExpression_Boolean()
  {
    return (EAttribute)logicalExpressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLogicalExpression_Expression()
  {
    return (EReference)logicalExpressionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLogicalExpression_Operator()
  {
    return (EAttribute)logicalExpressionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAdditiveExpression()
  {
    return additiveExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAdditiveExpression_Expression()
  {
    return (EReference)additiveExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAdditiveExpression_Operator()
  {
    return (EAttribute)additiveExpressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAdditiveExpression_String()
  {
    return (EAttribute)additiveExpressionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getMultiplicativeExpression()
  {
    return multiplicativeExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getMultiplicativeExpression_Expression()
  {
    return (EReference)multiplicativeExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getMultiplicativeExpression_Operator()
  {
    return (EAttribute)multiplicativeExpressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPowerExpression()
  {
    return powerExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPowerExpression_Expression()
  {
    return (EReference)powerExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPowerExpression_Operator()
  {
    return (EAttribute)powerExpressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getUnaryExpression()
  {
    return unaryExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getUnaryExpression_Operator()
  {
    return (EAttribute)unaryExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getUnaryExpression_Expression()
  {
    return (EReference)unaryExpressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getUnaryExpression_ParExpression()
  {
    return (EReference)unaryExpressionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getUnaryExpression_Primary()
  {
    return (EReference)unaryExpressionEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPrimary()
  {
    return primaryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPrimary_FunctionCall()
  {
    return (EReference)primaryEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPrimary_Number()
  {
    return (EAttribute)primaryEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPrimary_Symbol()
  {
    return (EReference)primaryEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPrimary_Attribute()
  {
    return (EReference)primaryEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPrimary_Vector()
  {
    return (EReference)primaryEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVector()
  {
    return vectorEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVector_Identifier()
  {
    return (EAttribute)vectorEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getVector_Values()
  {
    return (EReference)vectorEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFullyQualifiedSymbolName()
  {
    return fullyQualifiedSymbolNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFullyQualifiedSymbolName_Object()
  {
    return (EReference)fullyQualifiedSymbolNameEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFullyQualifiedSymbolName_Identifier()
  {
    return (EAttribute)fullyQualifiedSymbolNameEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFullyQualifiedArgumentName()
  {
    return fullyQualifiedArgumentNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFullyQualifiedArgumentName_Parent()
  {
    return (EReference)fullyQualifiedArgumentNameEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFullyQualifiedArgumentName_Identifier()
  {
    return (EAttribute)fullyQualifiedArgumentNameEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getObjectName()
  {
    return objectNameEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getObjectName_Name()
  {
    return (EAttribute)objectNameEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MdlFactory getMdlFactory()
  {
    return (MdlFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    mclEClass = createEClass(MCL);
    createEReference(mclEClass, MCL__OBJECTS);

    mclObjectEClass = createEClass(MCL_OBJECT);
    createEReference(mclObjectEClass, MCL_OBJECT__MODEL_OBJECT);
    createEReference(mclObjectEClass, MCL_OBJECT__PARAMETER_OBJECT);
    createEReference(mclObjectEClass, MCL_OBJECT__DATA_OBJECT);
    createEReference(mclObjectEClass, MCL_OBJECT__TASK_OBJECT);
    createEReference(mclObjectEClass, MCL_OBJECT__TEL_OBJECT);

    modelObjectEClass = createEClass(MODEL_OBJECT);
    createEReference(modelObjectEClass, MODEL_OBJECT__IDENTIFIER);
    createEReference(modelObjectEClass, MODEL_OBJECT__BLOCKS);

    parameterObjectEClass = createEClass(PARAMETER_OBJECT);
    createEReference(parameterObjectEClass, PARAMETER_OBJECT__IDENTIFIER);
    createEReference(parameterObjectEClass, PARAMETER_OBJECT__BLOCKS);

    dataObjectEClass = createEClass(DATA_OBJECT);
    createEReference(dataObjectEClass, DATA_OBJECT__IDENTIFIER);
    createEReference(dataObjectEClass, DATA_OBJECT__BLOCKS);

    taskObjectEClass = createEClass(TASK_OBJECT);
    createEReference(taskObjectEClass, TASK_OBJECT__IDENTIFIER);
    createEReference(taskObjectEClass, TASK_OBJECT__BLOCKS);

    telObjectEClass = createEClass(TEL_OBJECT);
    createEReference(telObjectEClass, TEL_OBJECT__IDENTIFIER);
    createEReference(telObjectEClass, TEL_OBJECT__STATEMENTS);

    modelObjectBlockEClass = createEClass(MODEL_OBJECT_BLOCK);
    createEReference(modelObjectBlockEClass, MODEL_OBJECT_BLOCK__INDIVIDUAL_VARIABLES_BLOCK);
    createEReference(modelObjectBlockEClass, MODEL_OBJECT_BLOCK__MODEL_PREDICTION_BLOCK);
    createEReference(modelObjectBlockEClass, MODEL_OBJECT_BLOCK__RANDOM_VARIABLE_DEFINITION_BLOCK);
    createEReference(modelObjectBlockEClass, MODEL_OBJECT_BLOCK__INPUT_VARIABLES_BLOCK);
    createEReference(modelObjectBlockEClass, MODEL_OBJECT_BLOCK__STRUCTURAL_PARAMETERS_BLOCK);
    createEReference(modelObjectBlockEClass, MODEL_OBJECT_BLOCK__VARIABILITY_PARAMETERS_BLOCK);
    createEReference(modelObjectBlockEClass, MODEL_OBJECT_BLOCK__OUTPUT_VARIABLES_BLOCK);
    createEReference(modelObjectBlockEClass, MODEL_OBJECT_BLOCK__GROUP_VARIABLES_BLOCK);
    createEReference(modelObjectBlockEClass, MODEL_OBJECT_BLOCK__OBSERVATION_BLOCK);
    createEReference(modelObjectBlockEClass, MODEL_OBJECT_BLOCK__ESTIMATION_BLOCK);
    createEReference(modelObjectBlockEClass, MODEL_OBJECT_BLOCK__SIMULATION_BLOCK);
    createEReference(modelObjectBlockEClass, MODEL_OBJECT_BLOCK__VERBATIM_BLOCK);

    individualVariablesBlockEClass = createEClass(INDIVIDUAL_VARIABLES_BLOCK);
    createEAttribute(individualVariablesBlockEClass, INDIVIDUAL_VARIABLES_BLOCK__IDENTIFIER);
    createEReference(individualVariablesBlockEClass, INDIVIDUAL_VARIABLES_BLOCK__STATEMENTS);

    modelPredictionBlockEClass = createEClass(MODEL_PREDICTION_BLOCK);
    createEAttribute(modelPredictionBlockEClass, MODEL_PREDICTION_BLOCK__IDENTIFIER);
    createEReference(modelPredictionBlockEClass, MODEL_PREDICTION_BLOCK__STATEMENTS);

    randomVariableDefinitionBlockEClass = createEClass(RANDOM_VARIABLE_DEFINITION_BLOCK);
    createEAttribute(randomVariableDefinitionBlockEClass, RANDOM_VARIABLE_DEFINITION_BLOCK__IDENTIFIER);
    createEReference(randomVariableDefinitionBlockEClass, RANDOM_VARIABLE_DEFINITION_BLOCK__VARIABLES);

    inputVariablesBlockEClass = createEClass(INPUT_VARIABLES_BLOCK);
    createEAttribute(inputVariablesBlockEClass, INPUT_VARIABLES_BLOCK__INDENTIFIER);
    createEReference(inputVariablesBlockEClass, INPUT_VARIABLES_BLOCK__VARIABLES);

    structuralParametersBlockEClass = createEClass(STRUCTURAL_PARAMETERS_BLOCK);
    createEAttribute(structuralParametersBlockEClass, STRUCTURAL_PARAMETERS_BLOCK__IDENTIFIER);
    createEReference(structuralParametersBlockEClass, STRUCTURAL_PARAMETERS_BLOCK__PARAMETERS);

    variabilityParametersBlockEClass = createEClass(VARIABILITY_PARAMETERS_BLOCK);
    createEAttribute(variabilityParametersBlockEClass, VARIABILITY_PARAMETERS_BLOCK__IDENTIFIER);
    createEReference(variabilityParametersBlockEClass, VARIABILITY_PARAMETERS_BLOCK__PARAMETERS);

    outputVariablesBlockEClass = createEClass(OUTPUT_VARIABLES_BLOCK);
    createEAttribute(outputVariablesBlockEClass, OUTPUT_VARIABLES_BLOCK__IDENTIFIER);
    createEReference(outputVariablesBlockEClass, OUTPUT_VARIABLES_BLOCK__VARIABLES);

    groupVariablesBlockEClass = createEClass(GROUP_VARIABLES_BLOCK);
    createEAttribute(groupVariablesBlockEClass, GROUP_VARIABLES_BLOCK__IDENTIFIER);
    createEReference(groupVariablesBlockEClass, GROUP_VARIABLES_BLOCK__STATEMENTS);

    observationBlockEClass = createEClass(OBSERVATION_BLOCK);
    createEAttribute(observationBlockEClass, OBSERVATION_BLOCK__IDENTIFIER);
    createEReference(observationBlockEClass, OBSERVATION_BLOCK__STATEMENTS);

    estimationBlockEClass = createEClass(ESTIMATION_BLOCK);
    createEAttribute(estimationBlockEClass, ESTIMATION_BLOCK__IDENTIFIER);
    createEReference(estimationBlockEClass, ESTIMATION_BLOCK__STATEMENTS);

    simulationBlockEClass = createEClass(SIMULATION_BLOCK);
    createEAttribute(simulationBlockEClass, SIMULATION_BLOCK__IDENTIFIER);
    createEReference(simulationBlockEClass, SIMULATION_BLOCK__STATEMENTS);

    parameterObjectBlockEClass = createEClass(PARAMETER_OBJECT_BLOCK);
    createEReference(parameterObjectBlockEClass, PARAMETER_OBJECT_BLOCK__STRUCTURAL_BLOCK);
    createEReference(parameterObjectBlockEClass, PARAMETER_OBJECT_BLOCK__VARIABILITY_BLOCK);
    createEReference(parameterObjectBlockEClass, PARAMETER_OBJECT_BLOCK__PRIOR_BLOCK);
    createEReference(parameterObjectBlockEClass, PARAMETER_OBJECT_BLOCK__VERBATIM_BLOCK);

    structuralBlockEClass = createEClass(STRUCTURAL_BLOCK);
    createEAttribute(structuralBlockEClass, STRUCTURAL_BLOCK__IDENTIFIER);
    createEReference(structuralBlockEClass, STRUCTURAL_BLOCK__PARAMETERS);

    variabilityBlockEClass = createEClass(VARIABILITY_BLOCK);
    createEAttribute(variabilityBlockEClass, VARIABILITY_BLOCK__IDENTIFIER);
    createEReference(variabilityBlockEClass, VARIABILITY_BLOCK__STATEMENTS);

    priorParametersBlockEClass = createEClass(PRIOR_PARAMETERS_BLOCK);
    createEAttribute(priorParametersBlockEClass, PRIOR_PARAMETERS_BLOCK__IDENTIFIER);
    createEReference(priorParametersBlockEClass, PRIOR_PARAMETERS_BLOCK__STATEMENTS);

    dataObjectBlockEClass = createEClass(DATA_OBJECT_BLOCK);
    createEReference(dataObjectBlockEClass, DATA_OBJECT_BLOCK__HEADER_BLOCK);
    createEReference(dataObjectBlockEClass, DATA_OBJECT_BLOCK__FILE_BLOCK);
    createEReference(dataObjectBlockEClass, DATA_OBJECT_BLOCK__VERBATIM_BLOCK);

    headerBlockEClass = createEClass(HEADER_BLOCK);
    createEAttribute(headerBlockEClass, HEADER_BLOCK__IDENTIFIER);
    createEReference(headerBlockEClass, HEADER_BLOCK__VARIABLES);

    fileBlockEClass = createEClass(FILE_BLOCK);
    createEAttribute(fileBlockEClass, FILE_BLOCK__IDENTIFIER);
    createEReference(fileBlockEClass, FILE_BLOCK__STATEMENTS);

    taskObjectBlockEClass = createEClass(TASK_OBJECT_BLOCK);
    createEReference(taskObjectBlockEClass, TASK_OBJECT_BLOCK__FUNCTION_DECLARATION);
    createEReference(taskObjectBlockEClass, TASK_OBJECT_BLOCK__PARAMETER_BLOCK);
    createEReference(taskObjectBlockEClass, TASK_OBJECT_BLOCK__DATA_BLOCK);
    createEReference(taskObjectBlockEClass, TASK_OBJECT_BLOCK__MODEL_BLOCK);
    createEReference(taskObjectBlockEClass, TASK_OBJECT_BLOCK__VERBATIM_BLOCK);

    parameterBlockEClass = createEClass(PARAMETER_BLOCK);
    createEAttribute(parameterBlockEClass, PARAMETER_BLOCK__IDENTIFIER);
    createEReference(parameterBlockEClass, PARAMETER_BLOCK__PARAMETERS);

    dataBlockEClass = createEClass(DATA_BLOCK);
    createEAttribute(dataBlockEClass, DATA_BLOCK__IDENTIFIER);
    createEReference(dataBlockEClass, DATA_BLOCK__STATEMENTS);

    dataBlockStatementEClass = createEClass(DATA_BLOCK_STATEMENT);
    createEReference(dataBlockStatementEClass, DATA_BLOCK_STATEMENT__IGNORE_LIST);
    createEReference(dataBlockStatementEClass, DATA_BLOCK_STATEMENT__ACCEPT_LIST);
    createEReference(dataBlockStatementEClass, DATA_BLOCK_STATEMENT__DROP_LIST);

    ignoreListEClass = createEClass(IGNORE_LIST);
    createEAttribute(ignoreListEClass, IGNORE_LIST__IDENTIFIER);
    createEReference(ignoreListEClass, IGNORE_LIST__EXPRESSION);

    acceptListEClass = createEClass(ACCEPT_LIST);
    createEAttribute(acceptListEClass, ACCEPT_LIST__IDENTIFIER);
    createEReference(acceptListEClass, ACCEPT_LIST__EXPRESSION);

    dropListEClass = createEClass(DROP_LIST);
    createEAttribute(dropListEClass, DROP_LIST__IDENTIFIER);
    createEReference(dropListEClass, DROP_LIST__LIST);

    modelBlockEClass = createEClass(MODEL_BLOCK);
    createEAttribute(modelBlockEClass, MODEL_BLOCK__IDENTIFIER);
    createEReference(modelBlockEClass, MODEL_BLOCK__STATEMENTS);

    modelBlockStatementEClass = createEClass(MODEL_BLOCK_STATEMENT);
    createEReference(modelBlockStatementEClass, MODEL_BLOCK_STATEMENT__STATEMENT);
    createEReference(modelBlockStatementEClass, MODEL_BLOCK_STATEMENT__ADD_LIST);
    createEReference(modelBlockStatementEClass, MODEL_BLOCK_STATEMENT__REMOVE_LIST);

    addListEClass = createEClass(ADD_LIST);
    createEReference(addListEClass, ADD_LIST__LIST);

    removeListEClass = createEClass(REMOVE_LIST);
    createEReference(removeListEClass, REMOVE_LIST__LIST);

    symbolListEClass = createEClass(SYMBOL_LIST);
    createEReference(symbolListEClass, SYMBOL_LIST__SYMBOLS);

    modelPredictionBlockStatementEClass = createEClass(MODEL_PREDICTION_BLOCK_STATEMENT);
    createEReference(modelPredictionBlockStatementEClass, MODEL_PREDICTION_BLOCK_STATEMENT__STATEMENT);
    createEReference(modelPredictionBlockStatementEClass, MODEL_PREDICTION_BLOCK_STATEMENT__ODE_BLOCK);
    createEReference(modelPredictionBlockStatementEClass, MODEL_PREDICTION_BLOCK_STATEMENT__LIBRARY_BLOCK);

    libraryBlockEClass = createEClass(LIBRARY_BLOCK);
    createEAttribute(libraryBlockEClass, LIBRARY_BLOCK__IDENTIFIER);
    createEReference(libraryBlockEClass, LIBRARY_BLOCK__STATEMENTS);

    odeBlockEClass = createEClass(ODE_BLOCK);
    createEAttribute(odeBlockEClass, ODE_BLOCK__IDENTIFIER);
    createEReference(odeBlockEClass, ODE_BLOCK__STATEMENTS);

    groupVariablesBlockStatementEClass = createEClass(GROUP_VARIABLES_BLOCK_STATEMENT);
    createEReference(groupVariablesBlockStatementEClass, GROUP_VARIABLES_BLOCK_STATEMENT__STATEMENT);
    createEReference(groupVariablesBlockStatementEClass, GROUP_VARIABLES_BLOCK_STATEMENT__MIXTURE_BLOCK);

    mixtureBlockEClass = createEClass(MIXTURE_BLOCK);
    createEAttribute(mixtureBlockEClass, MIXTURE_BLOCK__IDENTIFIER);
    createEReference(mixtureBlockEClass, MIXTURE_BLOCK__STATEMENTS);

    variabilityBlockStatementEClass = createEClass(VARIABILITY_BLOCK_STATEMENT);
    createEReference(variabilityBlockStatementEClass, VARIABILITY_BLOCK_STATEMENT__PARAMETER);
    createEReference(variabilityBlockStatementEClass, VARIABILITY_BLOCK_STATEMENT__BLOCK_BLOCK);
    createEReference(variabilityBlockStatementEClass, VARIABILITY_BLOCK_STATEMENT__DIAG_BLOCK);
    createEReference(variabilityBlockStatementEClass, VARIABILITY_BLOCK_STATEMENT__SAME_BLOCK);

    blockBlockEClass = createEClass(BLOCK_BLOCK);
    createEAttribute(blockBlockEClass, BLOCK_BLOCK__IDENTIFIER);
    createEReference(blockBlockEClass, BLOCK_BLOCK__ARGUMENTS);
    createEReference(blockBlockEClass, BLOCK_BLOCK__PARAMETERS);

    diagBlockEClass = createEClass(DIAG_BLOCK);
    createEAttribute(diagBlockEClass, DIAG_BLOCK__IDENTIFIER);
    createEReference(diagBlockEClass, DIAG_BLOCK__ARGUMENTS);
    createEReference(diagBlockEClass, DIAG_BLOCK__PARAMETERS);

    sameBlockEClass = createEClass(SAME_BLOCK);
    createEAttribute(sameBlockEClass, SAME_BLOCK__IDENTIFIER);
    createEReference(sameBlockEClass, SAME_BLOCK__ARGUMENTS);
    createEReference(sameBlockEClass, SAME_BLOCK__PARAMETERS);

    fileBlockStatementEClass = createEClass(FILE_BLOCK_STATEMENT);
    createEReference(fileBlockStatementEClass, FILE_BLOCK_STATEMENT__VARIABLE);
    createEReference(fileBlockStatementEClass, FILE_BLOCK_STATEMENT__INLINE_BLOCK);
    createEReference(fileBlockStatementEClass, FILE_BLOCK_STATEMENT__DESIGN_BLOCK);
    createEReference(fileBlockStatementEClass, FILE_BLOCK_STATEMENT__RSCRIPT_BLOCK);

    inlineBlockEClass = createEClass(INLINE_BLOCK);
    createEAttribute(inlineBlockEClass, INLINE_BLOCK__IDENTIFIER);
    createEReference(inlineBlockEClass, INLINE_BLOCK__VARIABLES);
    createEAttribute(inlineBlockEClass, INLINE_BLOCK__VALUES);

    designBlockEClass = createEClass(DESIGN_BLOCK);
    createEAttribute(designBlockEClass, DESIGN_BLOCK__IDENTIFIER);
    createEReference(designBlockEClass, DESIGN_BLOCK__STATEMENTS);

    designBlockStatementEClass = createEClass(DESIGN_BLOCK_STATEMENT);
    createEReference(designBlockStatementEClass, DESIGN_BLOCK_STATEMENT__IDENTIFIER);
    createEReference(designBlockStatementEClass, DESIGN_BLOCK_STATEMENT__ARGUMENTS);
    createEReference(designBlockStatementEClass, DESIGN_BLOCK_STATEMENT__EXPRESSION);

    variableListEClass = createEClass(VARIABLE_LIST);
    createEReference(variableListEClass, VARIABLE_LIST__IDENTIFIERS);

    rScriptBlockEClass = createEClass(RSCRIPT_BLOCK);
    createEAttribute(rScriptBlockEClass, RSCRIPT_BLOCK__IDENTIFIER);
    createEReference(rScriptBlockEClass, RSCRIPT_BLOCK__VARIABLES);

    rScriptBlockStatementEClass = createEClass(RSCRIPT_BLOCK_STATEMENT);
    createEAttribute(rScriptBlockStatementEClass, RSCRIPT_BLOCK_STATEMENT__IDENTIFIER);
    createEAttribute(rScriptBlockStatementEClass, RSCRIPT_BLOCK_STATEMENT__VALUE);
    createEReference(rScriptBlockStatementEClass, RSCRIPT_BLOCK_STATEMENT__OBJECT);

    taskFunctionDeclarationEClass = createEClass(TASK_FUNCTION_DECLARATION);
    createEAttribute(taskFunctionDeclarationEClass, TASK_FUNCTION_DECLARATION__IDENTIFIER);
    createEReference(taskFunctionDeclarationEClass, TASK_FUNCTION_DECLARATION__FORMAL_ARGUMENTS);
    createEReference(taskFunctionDeclarationEClass, TASK_FUNCTION_DECLARATION__FUNCTION_BODY);

    taskFunctionBodyEClass = createEClass(TASK_FUNCTION_BODY);
    createEReference(taskFunctionBodyEClass, TASK_FUNCTION_BODY__BLOCKS);

    taskFunctionBlockEClass = createEClass(TASK_FUNCTION_BLOCK);
    createEReference(taskFunctionBlockEClass, TASK_FUNCTION_BLOCK__ESTIMATE_BLOCK);
    createEReference(taskFunctionBlockEClass, TASK_FUNCTION_BLOCK__SIMULATE_BLOCK);
    createEReference(taskFunctionBlockEClass, TASK_FUNCTION_BLOCK__EXECUTE_BLOCK);

    estimateTaskEClass = createEClass(ESTIMATE_TASK);
    createEAttribute(estimateTaskEClass, ESTIMATE_TASK__IDENTIFIER);
    createEReference(estimateTaskEClass, ESTIMATE_TASK__STATEMENTS);

    simulateTaskEClass = createEClass(SIMULATE_TASK);
    createEAttribute(simulateTaskEClass, SIMULATE_TASK__IDENTIFIER);
    createEReference(simulateTaskEClass, SIMULATE_TASK__STATEMENTS);

    executeTaskEClass = createEClass(EXECUTE_TASK);
    createEAttribute(executeTaskEClass, EXECUTE_TASK__IDENTIFIER);
    createEReference(executeTaskEClass, EXECUTE_TASK__STATEMENTS);

    formalArgumentsEClass = createEClass(FORMAL_ARGUMENTS);
    createEAttribute(formalArgumentsEClass, FORMAL_ARGUMENTS__IDENTIFIERS);

    functionCallEClass = createEClass(FUNCTION_CALL);
    createEReference(functionCallEClass, FUNCTION_CALL__IDENTIFIER);
    createEReference(functionCallEClass, FUNCTION_CALL__ARGUMENTS);

    blockStatementEClass = createEClass(BLOCK_STATEMENT);
    createEReference(blockStatementEClass, BLOCK_STATEMENT__SYMBOL);
    createEReference(blockStatementEClass, BLOCK_STATEMENT__FUNCTION_CALL);
    createEReference(blockStatementEClass, BLOCK_STATEMENT__STATEMENT);
    createEReference(blockStatementEClass, BLOCK_STATEMENT__VERBATIM_BLOCK);

    verbatimBlockEClass = createEClass(VERBATIM_BLOCK);
    createEAttribute(verbatimBlockEClass, VERBATIM_BLOCK__IDENTIFIER);
    createEReference(verbatimBlockEClass, VERBATIM_BLOCK__BLOCK);
    createEAttribute(verbatimBlockEClass, VERBATIM_BLOCK__EXTERNAL_CODE);

    targetBlockEClass = createEClass(TARGET_BLOCK);
    createEAttribute(targetBlockEClass, TARGET_BLOCK__IDENTIFIER);
    createEReference(targetBlockEClass, TARGET_BLOCK__ARGUMENTS);
    createEAttribute(targetBlockEClass, TARGET_BLOCK__EXTERNAL_CODE);

    symbolModificationEClass = createEClass(SYMBOL_MODIFICATION);
    createEReference(symbolModificationEClass, SYMBOL_MODIFICATION__IDENTIFIER);
    createEReference(symbolModificationEClass, SYMBOL_MODIFICATION__LIST);

    parameterDeclarationEClass = createEClass(PARAMETER_DECLARATION);
    createEAttribute(parameterDeclarationEClass, PARAMETER_DECLARATION__IDENTIFIER);
    createEReference(parameterDeclarationEClass, PARAMETER_DECLARATION__LIST);

    symbolDeclarationEClass = createEClass(SYMBOL_DECLARATION);
    createEAttribute(symbolDeclarationEClass, SYMBOL_DECLARATION__IDENTIFIER);
    createEReference(symbolDeclarationEClass, SYMBOL_DECLARATION__EXPRESSION);
    createEReference(symbolDeclarationEClass, SYMBOL_DECLARATION__RANDOM_LIST);
    createEAttribute(symbolDeclarationEClass, SYMBOL_DECLARATION__FUNCTION);

    enumTypeEClass = createEClass(ENUM_TYPE);
    createEReference(enumTypeEClass, ENUM_TYPE__CATEGORICAL);
    createEReference(enumTypeEClass, ENUM_TYPE__CONTINUOUS);
    createEReference(enumTypeEClass, ENUM_TYPE__COVARIATE);
    createEReference(enumTypeEClass, ENUM_TYPE__DISTRIBUTION);
    createEReference(enumTypeEClass, ENUM_TYPE__LEVEL);
    createEReference(enumTypeEClass, ENUM_TYPE__LIKELYHOOD);
    createEReference(enumTypeEClass, ENUM_TYPE__MISSING);

    missingEClass = createEClass(MISSING);
    createEAttribute(missingEClass, MISSING__IDENTIFIER);

    likelyhoodEClass = createEClass(LIKELYHOOD);
    createEAttribute(likelyhoodEClass, LIKELYHOOD__IDENTIFIER);

    levelTypeEClass = createEClass(LEVEL_TYPE);
    createEAttribute(levelTypeEClass, LEVEL_TYPE__MDV);
    createEAttribute(levelTypeEClass, LEVEL_TYPE__ID);
    createEAttribute(levelTypeEClass, LEVEL_TYPE__DV);
    createEAttribute(levelTypeEClass, LEVEL_TYPE__IDV);

    categoricalEClass = createEClass(CATEGORICAL);
    createEAttribute(categoricalEClass, CATEGORICAL__IDENTIFIER);
    createEReference(categoricalEClass, CATEGORICAL__ARGUMENTS);

    continuousEClass = createEClass(CONTINUOUS);
    createEAttribute(continuousEClass, CONTINUOUS__IDENTIFIER);

    covariateEClass = createEClass(COVARIATE);
    createEAttribute(covariateEClass, COVARIATE__IDENTIFIER);

    distributionEClass = createEClass(DISTRIBUTION);
    createEAttribute(distributionEClass, DISTRIBUTION__NORMAL);
    createEAttribute(distributionEClass, DISTRIBUTION__BINOMIAL);
    createEAttribute(distributionEClass, DISTRIBUTION__POISSON);
    createEAttribute(distributionEClass, DISTRIBUTION__STUDENT_T);
    createEAttribute(distributionEClass, DISTRIBUTION__MVNORMAL);

    anyExpressionEClass = createEClass(ANY_EXPRESSION);
    createEReference(anyExpressionEClass, ANY_EXPRESSION__EXPRESSION);
    createEReference(anyExpressionEClass, ANY_EXPRESSION__LIST);
    createEReference(anyExpressionEClass, ANY_EXPRESSION__ODE_LIST);
    createEReference(anyExpressionEClass, ANY_EXPRESSION__TYPE);

    expressionEClass = createEClass(EXPRESSION);
    createEReference(expressionEClass, EXPRESSION__CONDITIONAL_EXPRESSION);

    listEClass = createEClass(LIST);
    createEReference(listEClass, LIST__ARGUMENTS);

    odeListEClass = createEClass(ODE_LIST);
    createEReference(odeListEClass, ODE_LIST__ARGUMENTS);

    randomListEClass = createEClass(RANDOM_LIST);
    createEReference(randomListEClass, RANDOM_LIST__ARGUMENTS);

    argumentsEClass = createEClass(ARGUMENTS);
    createEReference(argumentsEClass, ARGUMENTS__ARGUMENTS);

    argumentEClass = createEClass(ARGUMENT);
    createEAttribute(argumentEClass, ARGUMENT__IDENTIFIER);
    createEReference(argumentEClass, ARGUMENT__EXPRESSION);

    conditionalStatementEClass = createEClass(CONDITIONAL_STATEMENT);
    createEReference(conditionalStatementEClass, CONDITIONAL_STATEMENT__PAR_EXPRESSION);
    createEReference(conditionalStatementEClass, CONDITIONAL_STATEMENT__IF_STATEMENT);
    createEReference(conditionalStatementEClass, CONDITIONAL_STATEMENT__IF_BLOCK);
    createEReference(conditionalStatementEClass, CONDITIONAL_STATEMENT__ELSE_STATEMENT);
    createEReference(conditionalStatementEClass, CONDITIONAL_STATEMENT__ELSE_BLOCK);

    blockEClass = createEClass(BLOCK);
    createEReference(blockEClass, BLOCK__STATEMENTS);

    parExpressionEClass = createEClass(PAR_EXPRESSION);
    createEReference(parExpressionEClass, PAR_EXPRESSION__EXPRESSION);

    conditionalExpressionEClass = createEClass(CONDITIONAL_EXPRESSION);
    createEReference(conditionalExpressionEClass, CONDITIONAL_EXPRESSION__EXPRESSION);
    createEReference(conditionalExpressionEClass, CONDITIONAL_EXPRESSION__EXPRESSION1);
    createEReference(conditionalExpressionEClass, CONDITIONAL_EXPRESSION__EXPRESSION2);

    orExpressionEClass = createEClass(OR_EXPRESSION);
    createEReference(orExpressionEClass, OR_EXPRESSION__EXPRESSION);
    createEAttribute(orExpressionEClass, OR_EXPRESSION__OPERATOR);

    andExpressionEClass = createEClass(AND_EXPRESSION);
    createEReference(andExpressionEClass, AND_EXPRESSION__EXPRESSION);
    createEAttribute(andExpressionEClass, AND_EXPRESSION__OPERATOR);

    logicalExpressionEClass = createEClass(LOGICAL_EXPRESSION);
    createEAttribute(logicalExpressionEClass, LOGICAL_EXPRESSION__NEGATION);
    createEAttribute(logicalExpressionEClass, LOGICAL_EXPRESSION__BOOLEAN);
    createEReference(logicalExpressionEClass, LOGICAL_EXPRESSION__EXPRESSION);
    createEAttribute(logicalExpressionEClass, LOGICAL_EXPRESSION__OPERATOR);

    additiveExpressionEClass = createEClass(ADDITIVE_EXPRESSION);
    createEReference(additiveExpressionEClass, ADDITIVE_EXPRESSION__EXPRESSION);
    createEAttribute(additiveExpressionEClass, ADDITIVE_EXPRESSION__OPERATOR);
    createEAttribute(additiveExpressionEClass, ADDITIVE_EXPRESSION__STRING);

    multiplicativeExpressionEClass = createEClass(MULTIPLICATIVE_EXPRESSION);
    createEReference(multiplicativeExpressionEClass, MULTIPLICATIVE_EXPRESSION__EXPRESSION);
    createEAttribute(multiplicativeExpressionEClass, MULTIPLICATIVE_EXPRESSION__OPERATOR);

    powerExpressionEClass = createEClass(POWER_EXPRESSION);
    createEReference(powerExpressionEClass, POWER_EXPRESSION__EXPRESSION);
    createEAttribute(powerExpressionEClass, POWER_EXPRESSION__OPERATOR);

    unaryExpressionEClass = createEClass(UNARY_EXPRESSION);
    createEAttribute(unaryExpressionEClass, UNARY_EXPRESSION__OPERATOR);
    createEReference(unaryExpressionEClass, UNARY_EXPRESSION__EXPRESSION);
    createEReference(unaryExpressionEClass, UNARY_EXPRESSION__PAR_EXPRESSION);
    createEReference(unaryExpressionEClass, UNARY_EXPRESSION__PRIMARY);

    primaryEClass = createEClass(PRIMARY);
    createEReference(primaryEClass, PRIMARY__FUNCTION_CALL);
    createEAttribute(primaryEClass, PRIMARY__NUMBER);
    createEReference(primaryEClass, PRIMARY__SYMBOL);
    createEReference(primaryEClass, PRIMARY__ATTRIBUTE);
    createEReference(primaryEClass, PRIMARY__VECTOR);

    vectorEClass = createEClass(VECTOR);
    createEAttribute(vectorEClass, VECTOR__IDENTIFIER);
    createEReference(vectorEClass, VECTOR__VALUES);

    fullyQualifiedSymbolNameEClass = createEClass(FULLY_QUALIFIED_SYMBOL_NAME);
    createEReference(fullyQualifiedSymbolNameEClass, FULLY_QUALIFIED_SYMBOL_NAME__OBJECT);
    createEAttribute(fullyQualifiedSymbolNameEClass, FULLY_QUALIFIED_SYMBOL_NAME__IDENTIFIER);

    fullyQualifiedArgumentNameEClass = createEClass(FULLY_QUALIFIED_ARGUMENT_NAME);
    createEReference(fullyQualifiedArgumentNameEClass, FULLY_QUALIFIED_ARGUMENT_NAME__PARENT);
    createEAttribute(fullyQualifiedArgumentNameEClass, FULLY_QUALIFIED_ARGUMENT_NAME__IDENTIFIER);

    objectNameEClass = createEClass(OBJECT_NAME);
    createEAttribute(objectNameEClass, OBJECT_NAME__NAME);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes

    // Initialize classes and features; add operations and parameters
    initEClass(mclEClass, Mcl.class, "Mcl", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getMcl_Objects(), this.getMclObject(), null, "objects", null, 0, -1, Mcl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(mclObjectEClass, MclObject.class, "MclObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getMclObject_ModelObject(), this.getModelObject(), null, "modelObject", null, 0, 1, MclObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMclObject_ParameterObject(), this.getParameterObject(), null, "parameterObject", null, 0, 1, MclObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMclObject_DataObject(), this.getDataObject(), null, "dataObject", null, 0, 1, MclObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMclObject_TaskObject(), this.getTaskObject(), null, "taskObject", null, 0, 1, MclObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMclObject_TelObject(), this.getTELObject(), null, "telObject", null, 0, 1, MclObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(modelObjectEClass, ModelObject.class, "ModelObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getModelObject_Identifier(), this.getObjectName(), null, "identifier", null, 0, 1, ModelObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelObject_Blocks(), this.getModelObjectBlock(), null, "blocks", null, 0, -1, ModelObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(parameterObjectEClass, ParameterObject.class, "ParameterObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getParameterObject_Identifier(), this.getObjectName(), null, "identifier", null, 0, 1, ParameterObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getParameterObject_Blocks(), this.getParameterObjectBlock(), null, "blocks", null, 0, -1, ParameterObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(dataObjectEClass, DataObject.class, "DataObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDataObject_Identifier(), this.getObjectName(), null, "identifier", null, 0, 1, DataObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDataObject_Blocks(), this.getDataObjectBlock(), null, "blocks", null, 0, -1, DataObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(taskObjectEClass, TaskObject.class, "TaskObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTaskObject_Identifier(), this.getObjectName(), null, "identifier", null, 0, 1, TaskObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTaskObject_Blocks(), this.getTaskObjectBlock(), null, "blocks", null, 0, -1, TaskObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(telObjectEClass, TELObject.class, "TELObject", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTELObject_Identifier(), this.getObjectName(), null, "identifier", null, 0, 1, TELObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTELObject_Statements(), this.getBlockStatement(), null, "statements", null, 0, -1, TELObject.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(modelObjectBlockEClass, ModelObjectBlock.class, "ModelObjectBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getModelObjectBlock_IndividualVariablesBlock(), this.getIndividualVariablesBlock(), null, "individualVariablesBlock", null, 0, 1, ModelObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelObjectBlock_ModelPredictionBlock(), this.getModelPredictionBlock(), null, "modelPredictionBlock", null, 0, 1, ModelObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelObjectBlock_RandomVariableDefinitionBlock(), this.getRandomVariableDefinitionBlock(), null, "randomVariableDefinitionBlock", null, 0, 1, ModelObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelObjectBlock_InputVariablesBlock(), this.getInputVariablesBlock(), null, "inputVariablesBlock", null, 0, 1, ModelObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelObjectBlock_StructuralParametersBlock(), this.getStructuralParametersBlock(), null, "structuralParametersBlock", null, 0, 1, ModelObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelObjectBlock_VariabilityParametersBlock(), this.getVariabilityParametersBlock(), null, "variabilityParametersBlock", null, 0, 1, ModelObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelObjectBlock_OutputVariablesBlock(), this.getOutputVariablesBlock(), null, "outputVariablesBlock", null, 0, 1, ModelObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelObjectBlock_GroupVariablesBlock(), this.getGroupVariablesBlock(), null, "groupVariablesBlock", null, 0, 1, ModelObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelObjectBlock_ObservationBlock(), this.getObservationBlock(), null, "observationBlock", null, 0, 1, ModelObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelObjectBlock_EstimationBlock(), this.getEstimationBlock(), null, "estimationBlock", null, 0, 1, ModelObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelObjectBlock_SimulationBlock(), this.getSimulationBlock(), null, "simulationBlock", null, 0, 1, ModelObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelObjectBlock_VerbatimBlock(), this.getVerbatimBlock(), null, "verbatimBlock", null, 0, 1, ModelObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(individualVariablesBlockEClass, IndividualVariablesBlock.class, "IndividualVariablesBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getIndividualVariablesBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, IndividualVariablesBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIndividualVariablesBlock_Statements(), this.getBlockStatement(), null, "statements", null, 0, -1, IndividualVariablesBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(modelPredictionBlockEClass, ModelPredictionBlock.class, "ModelPredictionBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getModelPredictionBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, ModelPredictionBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelPredictionBlock_Statements(), this.getModelPredictionBlockStatement(), null, "statements", null, 0, -1, ModelPredictionBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(randomVariableDefinitionBlockEClass, RandomVariableDefinitionBlock.class, "RandomVariableDefinitionBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRandomVariableDefinitionBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, RandomVariableDefinitionBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRandomVariableDefinitionBlock_Variables(), this.getSymbolDeclaration(), null, "variables", null, 0, -1, RandomVariableDefinitionBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(inputVariablesBlockEClass, InputVariablesBlock.class, "InputVariablesBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getInputVariablesBlock_Indentifier(), ecorePackage.getEString(), "indentifier", null, 0, 1, InputVariablesBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getInputVariablesBlock_Variables(), this.getSymbolDeclaration(), null, "variables", null, 0, -1, InputVariablesBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(structuralParametersBlockEClass, StructuralParametersBlock.class, "StructuralParametersBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getStructuralParametersBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, StructuralParametersBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getStructuralParametersBlock_Parameters(), this.getFullyQualifiedSymbolName(), null, "parameters", null, 0, -1, StructuralParametersBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variabilityParametersBlockEClass, VariabilityParametersBlock.class, "VariabilityParametersBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getVariabilityParametersBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, VariabilityParametersBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getVariabilityParametersBlock_Parameters(), this.getFullyQualifiedSymbolName(), null, "parameters", null, 0, -1, VariabilityParametersBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(outputVariablesBlockEClass, OutputVariablesBlock.class, "OutputVariablesBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getOutputVariablesBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, OutputVariablesBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getOutputVariablesBlock_Variables(), this.getFullyQualifiedSymbolName(), null, "variables", null, 0, -1, OutputVariablesBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(groupVariablesBlockEClass, GroupVariablesBlock.class, "GroupVariablesBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGroupVariablesBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, GroupVariablesBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGroupVariablesBlock_Statements(), this.getGroupVariablesBlockStatement(), null, "statements", null, 0, -1, GroupVariablesBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(observationBlockEClass, ObservationBlock.class, "ObservationBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getObservationBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, ObservationBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getObservationBlock_Statements(), this.getBlockStatement(), null, "statements", null, 0, -1, ObservationBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(estimationBlockEClass, EstimationBlock.class, "EstimationBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEstimationBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, EstimationBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEstimationBlock_Statements(), this.getBlockStatement(), null, "statements", null, 0, -1, EstimationBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(simulationBlockEClass, SimulationBlock.class, "SimulationBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSimulationBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, SimulationBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSimulationBlock_Statements(), this.getBlockStatement(), null, "statements", null, 0, -1, SimulationBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(parameterObjectBlockEClass, ParameterObjectBlock.class, "ParameterObjectBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getParameterObjectBlock_StructuralBlock(), this.getStructuralBlock(), null, "structuralBlock", null, 0, 1, ParameterObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getParameterObjectBlock_VariabilityBlock(), this.getVariabilityBlock(), null, "variabilityBlock", null, 0, 1, ParameterObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getParameterObjectBlock_PriorBlock(), this.getPriorParametersBlock(), null, "priorBlock", null, 0, 1, ParameterObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getParameterObjectBlock_VerbatimBlock(), this.getVerbatimBlock(), null, "verbatimBlock", null, 0, 1, ParameterObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(structuralBlockEClass, StructuralBlock.class, "StructuralBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getStructuralBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, StructuralBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getStructuralBlock_Parameters(), this.getParameterDeclaration(), null, "parameters", null, 0, -1, StructuralBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variabilityBlockEClass, VariabilityBlock.class, "VariabilityBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getVariabilityBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, VariabilityBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getVariabilityBlock_Statements(), this.getVariabilityBlockStatement(), null, "statements", null, 0, -1, VariabilityBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(priorParametersBlockEClass, PriorParametersBlock.class, "PriorParametersBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPriorParametersBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, PriorParametersBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPriorParametersBlock_Statements(), this.getBlockStatement(), null, "statements", null, 0, -1, PriorParametersBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(dataObjectBlockEClass, DataObjectBlock.class, "DataObjectBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDataObjectBlock_HeaderBlock(), this.getHeaderBlock(), null, "headerBlock", null, 0, 1, DataObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDataObjectBlock_FileBlock(), this.getFileBlock(), null, "fileBlock", null, 0, 1, DataObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDataObjectBlock_VerbatimBlock(), this.getVerbatimBlock(), null, "verbatimBlock", null, 0, 1, DataObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(headerBlockEClass, HeaderBlock.class, "HeaderBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getHeaderBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, HeaderBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getHeaderBlock_Variables(), this.getSymbolModification(), null, "variables", null, 0, -1, HeaderBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(fileBlockEClass, FileBlock.class, "FileBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getFileBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, FileBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFileBlock_Statements(), this.getFileBlockStatement(), null, "statements", null, 0, -1, FileBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(taskObjectBlockEClass, TaskObjectBlock.class, "TaskObjectBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTaskObjectBlock_FunctionDeclaration(), this.getTaskFunctionDeclaration(), null, "functionDeclaration", null, 0, 1, TaskObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTaskObjectBlock_ParameterBlock(), this.getParameterBlock(), null, "parameterBlock", null, 0, 1, TaskObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTaskObjectBlock_DataBlock(), this.getDataBlock(), null, "dataBlock", null, 0, 1, TaskObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTaskObjectBlock_ModelBlock(), this.getModelBlock(), null, "modelBlock", null, 0, 1, TaskObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTaskObjectBlock_VerbatimBlock(), this.getVerbatimBlock(), null, "verbatimBlock", null, 0, 1, TaskObjectBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(parameterBlockEClass, ParameterBlock.class, "ParameterBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getParameterBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, ParameterBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getParameterBlock_Parameters(), this.getSymbolModification(), null, "parameters", null, 0, -1, ParameterBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(dataBlockEClass, DataBlock.class, "DataBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDataBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, DataBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDataBlock_Statements(), this.getDataBlockStatement(), null, "statements", null, 0, -1, DataBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(dataBlockStatementEClass, DataBlockStatement.class, "DataBlockStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDataBlockStatement_IgnoreList(), this.getIgnoreList(), null, "ignoreList", null, 0, 1, DataBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDataBlockStatement_AcceptList(), this.getAcceptList(), null, "acceptList", null, 0, 1, DataBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDataBlockStatement_DropList(), this.getDropList(), null, "dropList", null, 0, 1, DataBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(ignoreListEClass, IgnoreList.class, "IgnoreList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getIgnoreList_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, IgnoreList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIgnoreList_Expression(), this.getOrExpression(), null, "expression", null, 0, 1, IgnoreList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(acceptListEClass, AcceptList.class, "AcceptList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAcceptList_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, AcceptList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAcceptList_Expression(), this.getAndExpression(), null, "expression", null, 0, 1, AcceptList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(dropListEClass, DropList.class, "DropList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDropList_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, DropList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDropList_List(), this.getSymbolList(), null, "list", null, 0, 1, DropList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(modelBlockEClass, ModelBlock.class, "ModelBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getModelBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, ModelBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelBlock_Statements(), this.getModelBlockStatement(), null, "statements", null, 0, -1, ModelBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(modelBlockStatementEClass, ModelBlockStatement.class, "ModelBlockStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getModelBlockStatement_Statement(), this.getBlockStatement(), null, "statement", null, 0, 1, ModelBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelBlockStatement_AddList(), this.getAddList(), null, "addList", null, 0, 1, ModelBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelBlockStatement_RemoveList(), this.getRemoveList(), null, "removeList", null, 0, 1, ModelBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(addListEClass, AddList.class, "AddList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAddList_List(), this.getSymbolList(), null, "list", null, 0, 1, AddList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(removeListEClass, RemoveList.class, "RemoveList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getRemoveList_List(), this.getSymbolList(), null, "list", null, 0, 1, RemoveList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(symbolListEClass, SymbolList.class, "SymbolList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSymbolList_Symbols(), this.getFullyQualifiedSymbolName(), null, "symbols", null, 0, -1, SymbolList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(modelPredictionBlockStatementEClass, ModelPredictionBlockStatement.class, "ModelPredictionBlockStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getModelPredictionBlockStatement_Statement(), this.getBlockStatement(), null, "statement", null, 0, 1, ModelPredictionBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelPredictionBlockStatement_OdeBlock(), this.getOdeBlock(), null, "odeBlock", null, 0, 1, ModelPredictionBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getModelPredictionBlockStatement_LibraryBlock(), this.getLibraryBlock(), null, "libraryBlock", null, 0, 1, ModelPredictionBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(libraryBlockEClass, LibraryBlock.class, "LibraryBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLibraryBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, LibraryBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLibraryBlock_Statements(), this.getBlockStatement(), null, "statements", null, 0, -1, LibraryBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(odeBlockEClass, OdeBlock.class, "OdeBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getOdeBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, OdeBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getOdeBlock_Statements(), this.getBlockStatement(), null, "statements", null, 0, -1, OdeBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(groupVariablesBlockStatementEClass, GroupVariablesBlockStatement.class, "GroupVariablesBlockStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getGroupVariablesBlockStatement_Statement(), this.getBlockStatement(), null, "statement", null, 0, 1, GroupVariablesBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGroupVariablesBlockStatement_MixtureBlock(), this.getMixtureBlock(), null, "mixtureBlock", null, 0, 1, GroupVariablesBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(mixtureBlockEClass, MixtureBlock.class, "MixtureBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMixtureBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, MixtureBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getMixtureBlock_Statements(), this.getBlockStatement(), null, "statements", null, 0, -1, MixtureBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variabilityBlockStatementEClass, VariabilityBlockStatement.class, "VariabilityBlockStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getVariabilityBlockStatement_Parameter(), this.getParameterDeclaration(), null, "parameter", null, 0, 1, VariabilityBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getVariabilityBlockStatement_BlockBlock(), this.getBlockBlock(), null, "blockBlock", null, 0, 1, VariabilityBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getVariabilityBlockStatement_DiagBlock(), this.getDiagBlock(), null, "diagBlock", null, 0, 1, VariabilityBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getVariabilityBlockStatement_SameBlock(), this.getSameBlock(), null, "sameBlock", null, 0, 1, VariabilityBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(blockBlockEClass, BlockBlock.class, "BlockBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getBlockBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, BlockBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBlockBlock_Arguments(), this.getArguments(), null, "arguments", null, 0, 1, BlockBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBlockBlock_Parameters(), this.getArguments(), null, "parameters", null, 0, 1, BlockBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(diagBlockEClass, DiagBlock.class, "DiagBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDiagBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, DiagBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDiagBlock_Arguments(), this.getArguments(), null, "arguments", null, 0, 1, DiagBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDiagBlock_Parameters(), this.getArguments(), null, "parameters", null, 0, 1, DiagBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(sameBlockEClass, SameBlock.class, "SameBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSameBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, SameBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSameBlock_Arguments(), this.getArguments(), null, "arguments", null, 0, 1, SameBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSameBlock_Parameters(), this.getArguments(), null, "parameters", null, 0, 1, SameBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(fileBlockStatementEClass, FileBlockStatement.class, "FileBlockStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFileBlockStatement_Variable(), this.getSymbolDeclaration(), null, "variable", null, 0, 1, FileBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFileBlockStatement_InlineBlock(), this.getInlineBlock(), null, "inlineBlock", null, 0, 1, FileBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFileBlockStatement_DesignBlock(), this.getDesignBlock(), null, "designBlock", null, 0, 1, FileBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFileBlockStatement_RscriptBlock(), this.getRScriptBlock(), null, "rscriptBlock", null, 0, 1, FileBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(inlineBlockEClass, InlineBlock.class, "InlineBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getInlineBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, InlineBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getInlineBlock_Variables(), this.getFullyQualifiedSymbolName(), null, "variables", null, 0, -1, InlineBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getInlineBlock_Values(), ecorePackage.getEString(), "values", null, 0, -1, InlineBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(designBlockEClass, DesignBlock.class, "DesignBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDesignBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, DesignBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDesignBlock_Statements(), this.getDesignBlockStatement(), null, "statements", null, 0, -1, DesignBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(designBlockStatementEClass, DesignBlockStatement.class, "DesignBlockStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getDesignBlockStatement_Identifier(), this.getFullyQualifiedSymbolName(), null, "identifier", null, 0, 1, DesignBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDesignBlockStatement_Arguments(), this.getVariableList(), null, "arguments", null, 0, 1, DesignBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getDesignBlockStatement_Expression(), this.getAnyExpression(), null, "expression", null, 0, 1, DesignBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(variableListEClass, VariableList.class, "VariableList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getVariableList_Identifiers(), this.getFullyQualifiedSymbolName(), null, "identifiers", null, 0, -1, VariableList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(rScriptBlockEClass, RScriptBlock.class, "RScriptBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRScriptBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, RScriptBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRScriptBlock_Variables(), this.getRScriptBlockStatement(), null, "variables", null, 0, -1, RScriptBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(rScriptBlockStatementEClass, RScriptBlockStatement.class, "RScriptBlockStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRScriptBlockStatement_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, RScriptBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRScriptBlockStatement_Value(), ecorePackage.getEString(), "value", null, 0, 1, RScriptBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRScriptBlockStatement_Object(), this.getObjectName(), null, "object", null, 0, 1, RScriptBlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(taskFunctionDeclarationEClass, TaskFunctionDeclaration.class, "TaskFunctionDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getTaskFunctionDeclaration_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, TaskFunctionDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTaskFunctionDeclaration_FormalArguments(), this.getFormalArguments(), null, "formalArguments", null, 0, 1, TaskFunctionDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTaskFunctionDeclaration_FunctionBody(), this.getTaskFunctionBody(), null, "functionBody", null, 0, 1, TaskFunctionDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(taskFunctionBodyEClass, TaskFunctionBody.class, "TaskFunctionBody", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTaskFunctionBody_Blocks(), this.getTaskFunctionBlock(), null, "blocks", null, 0, -1, TaskFunctionBody.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(taskFunctionBlockEClass, TaskFunctionBlock.class, "TaskFunctionBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getTaskFunctionBlock_EstimateBlock(), this.getEstimateTask(), null, "estimateBlock", null, 0, 1, TaskFunctionBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTaskFunctionBlock_SimulateBlock(), this.getSimulateTask(), null, "simulateBlock", null, 0, 1, TaskFunctionBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTaskFunctionBlock_ExecuteBlock(), this.getExecuteTask(), null, "executeBlock", null, 0, 1, TaskFunctionBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(estimateTaskEClass, EstimateTask.class, "EstimateTask", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getEstimateTask_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, EstimateTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEstimateTask_Statements(), this.getBlockStatement(), null, "statements", null, 0, -1, EstimateTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(simulateTaskEClass, SimulateTask.class, "SimulateTask", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSimulateTask_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, SimulateTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSimulateTask_Statements(), this.getBlockStatement(), null, "statements", null, 0, -1, SimulateTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(executeTaskEClass, ExecuteTask.class, "ExecuteTask", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getExecuteTask_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, ExecuteTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getExecuteTask_Statements(), this.getBlockStatement(), null, "statements", null, 0, -1, ExecuteTask.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(formalArgumentsEClass, FormalArguments.class, "FormalArguments", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getFormalArguments_Identifiers(), ecorePackage.getEString(), "identifiers", null, 0, -1, FormalArguments.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(functionCallEClass, FunctionCall.class, "FunctionCall", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFunctionCall_Identifier(), this.getFullyQualifiedSymbolName(), null, "identifier", null, 0, 1, FunctionCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getFunctionCall_Arguments(), this.getArguments(), null, "arguments", null, 0, 1, FunctionCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(blockStatementEClass, BlockStatement.class, "BlockStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getBlockStatement_Symbol(), this.getSymbolDeclaration(), null, "symbol", null, 0, 1, BlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBlockStatement_FunctionCall(), this.getFunctionCall(), null, "functionCall", null, 0, 1, BlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBlockStatement_Statement(), this.getConditionalStatement(), null, "statement", null, 0, 1, BlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBlockStatement_VerbatimBlock(), this.getVerbatimBlock(), null, "verbatimBlock", null, 0, 1, BlockStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(verbatimBlockEClass, VerbatimBlock.class, "VerbatimBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getVerbatimBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, VerbatimBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getVerbatimBlock_Block(), this.getTargetBlock(), null, "block", null, 0, 1, VerbatimBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getVerbatimBlock_ExternalCode(), ecorePackage.getEString(), "externalCode", null, 0, 1, VerbatimBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(targetBlockEClass, TargetBlock.class, "TargetBlock", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getTargetBlock_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, TargetBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getTargetBlock_Arguments(), this.getArguments(), null, "arguments", null, 0, 1, TargetBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getTargetBlock_ExternalCode(), ecorePackage.getEString(), "externalCode", null, 0, 1, TargetBlock.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(symbolModificationEClass, SymbolModification.class, "SymbolModification", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSymbolModification_Identifier(), this.getFullyQualifiedSymbolName(), null, "identifier", null, 0, 1, SymbolModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSymbolModification_List(), this.getList(), null, "list", null, 0, 1, SymbolModification.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(parameterDeclarationEClass, ParameterDeclaration.class, "ParameterDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getParameterDeclaration_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, ParameterDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getParameterDeclaration_List(), this.getList(), null, "list", null, 0, 1, ParameterDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(symbolDeclarationEClass, SymbolDeclaration.class, "SymbolDeclaration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSymbolDeclaration_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, SymbolDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSymbolDeclaration_Expression(), this.getAnyExpression(), null, "expression", null, 0, 1, SymbolDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSymbolDeclaration_RandomList(), this.getRandomList(), null, "randomList", null, 0, 1, SymbolDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getSymbolDeclaration_Function(), ecorePackage.getEString(), "function", null, 0, 1, SymbolDeclaration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(enumTypeEClass, EnumType.class, "EnumType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getEnumType_Categorical(), this.getCategorical(), null, "categorical", null, 0, 1, EnumType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEnumType_Continuous(), this.getContinuous(), null, "continuous", null, 0, 1, EnumType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEnumType_Covariate(), this.getCovariate(), null, "covariate", null, 0, 1, EnumType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEnumType_Distribution(), this.getDistribution(), null, "distribution", null, 0, 1, EnumType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEnumType_Level(), this.getLevelType(), null, "level", null, 0, 1, EnumType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEnumType_Likelyhood(), this.getLikelyhood(), null, "likelyhood", null, 0, 1, EnumType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getEnumType_Missing(), this.getMissing(), null, "missing", null, 0, 1, EnumType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(missingEClass, Missing.class, "Missing", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getMissing_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, Missing.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(likelyhoodEClass, Likelyhood.class, "Likelyhood", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLikelyhood_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, Likelyhood.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(levelTypeEClass, LevelType.class, "LevelType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLevelType_Mdv(), ecorePackage.getEString(), "mdv", null, 0, 1, LevelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLevelType_Id(), ecorePackage.getEString(), "id", null, 0, 1, LevelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLevelType_Dv(), ecorePackage.getEString(), "dv", null, 0, 1, LevelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLevelType_Idv(), ecorePackage.getEString(), "idv", null, 0, 1, LevelType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(categoricalEClass, Categorical.class, "Categorical", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getCategorical_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, Categorical.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCategorical_Arguments(), this.getArguments(), null, "arguments", null, 0, 1, Categorical.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(continuousEClass, Continuous.class, "Continuous", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getContinuous_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, Continuous.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(covariateEClass, Covariate.class, "Covariate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getCovariate_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, Covariate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(distributionEClass, Distribution.class, "Distribution", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getDistribution_Normal(), ecorePackage.getEString(), "normal", null, 0, 1, Distribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDistribution_Binomial(), ecorePackage.getEString(), "binomial", null, 0, 1, Distribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDistribution_Poisson(), ecorePackage.getEString(), "poisson", null, 0, 1, Distribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDistribution_Student_t(), ecorePackage.getEString(), "student_t", null, 0, 1, Distribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getDistribution_Mvnormal(), ecorePackage.getEString(), "mvnormal", null, 0, 1, Distribution.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(anyExpressionEClass, AnyExpression.class, "AnyExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAnyExpression_Expression(), this.getExpression(), null, "expression", null, 0, 1, AnyExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAnyExpression_List(), this.getList(), null, "list", null, 0, 1, AnyExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAnyExpression_OdeList(), this.getOdeList(), null, "odeList", null, 0, 1, AnyExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAnyExpression_Type(), this.getEnumType(), null, "type", null, 0, 1, AnyExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(expressionEClass, Expression.class, "Expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getExpression_ConditionalExpression(), this.getConditionalExpression(), null, "conditionalExpression", null, 0, 1, Expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(listEClass, List.class, "List", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getList_Arguments(), this.getArguments(), null, "arguments", null, 0, 1, List.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(odeListEClass, OdeList.class, "OdeList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOdeList_Arguments(), this.getArguments(), null, "arguments", null, 0, 1, OdeList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(randomListEClass, RandomList.class, "RandomList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getRandomList_Arguments(), this.getArguments(), null, "arguments", null, 0, 1, RandomList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(argumentsEClass, Arguments.class, "Arguments", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getArguments_Arguments(), this.getArgument(), null, "arguments", null, 0, -1, Arguments.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(argumentEClass, Argument.class, "Argument", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getArgument_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, Argument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArgument_Expression(), this.getAnyExpression(), null, "expression", null, 0, 1, Argument.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(conditionalStatementEClass, ConditionalStatement.class, "ConditionalStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getConditionalStatement_ParExpression(), this.getParExpression(), null, "parExpression", null, 0, 1, ConditionalStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConditionalStatement_IfStatement(), this.getBlockStatement(), null, "ifStatement", null, 0, 1, ConditionalStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConditionalStatement_IfBlock(), this.getBlock(), null, "ifBlock", null, 0, 1, ConditionalStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConditionalStatement_ElseStatement(), this.getBlockStatement(), null, "elseStatement", null, 0, 1, ConditionalStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConditionalStatement_ElseBlock(), this.getBlock(), null, "elseBlock", null, 0, 1, ConditionalStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(blockEClass, Block.class, "Block", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getBlock_Statements(), this.getBlockStatement(), null, "statements", null, 0, -1, Block.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(parExpressionEClass, ParExpression.class, "ParExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getParExpression_Expression(), this.getExpression(), null, "expression", null, 0, 1, ParExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(conditionalExpressionEClass, ConditionalExpression.class, "ConditionalExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getConditionalExpression_Expression(), this.getOrExpression(), null, "expression", null, 0, 1, ConditionalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConditionalExpression_Expression1(), this.getExpression(), null, "expression1", null, 0, 1, ConditionalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getConditionalExpression_Expression2(), this.getExpression(), null, "expression2", null, 0, 1, ConditionalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(orExpressionEClass, OrExpression.class, "OrExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOrExpression_Expression(), this.getAndExpression(), null, "expression", null, 0, -1, OrExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getOrExpression_Operator(), ecorePackage.getEString(), "operator", null, 0, -1, OrExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(andExpressionEClass, AndExpression.class, "AndExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAndExpression_Expression(), this.getLogicalExpression(), null, "expression", null, 0, -1, AndExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAndExpression_Operator(), ecorePackage.getEString(), "operator", null, 0, -1, AndExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(logicalExpressionEClass, LogicalExpression.class, "LogicalExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getLogicalExpression_Negation(), ecorePackage.getEString(), "negation", null, 0, 1, LogicalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLogicalExpression_Boolean(), ecorePackage.getEString(), "boolean", null, 0, 1, LogicalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLogicalExpression_Expression(), this.getAdditiveExpression(), null, "expression", null, 0, -1, LogicalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLogicalExpression_Operator(), ecorePackage.getEString(), "operator", null, 0, -1, LogicalExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(additiveExpressionEClass, AdditiveExpression.class, "AdditiveExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAdditiveExpression_Expression(), this.getMultiplicativeExpression(), null, "expression", null, 0, -1, AdditiveExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAdditiveExpression_Operator(), ecorePackage.getEString(), "operator", null, 0, -1, AdditiveExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAdditiveExpression_String(), ecorePackage.getEString(), "string", null, 0, -1, AdditiveExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(multiplicativeExpressionEClass, MultiplicativeExpression.class, "MultiplicativeExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getMultiplicativeExpression_Expression(), this.getPowerExpression(), null, "expression", null, 0, -1, MultiplicativeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getMultiplicativeExpression_Operator(), ecorePackage.getEString(), "operator", null, 0, -1, MultiplicativeExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(powerExpressionEClass, PowerExpression.class, "PowerExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPowerExpression_Expression(), this.getUnaryExpression(), null, "expression", null, 0, -1, PowerExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPowerExpression_Operator(), ecorePackage.getEString(), "operator", null, 0, -1, PowerExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(unaryExpressionEClass, UnaryExpression.class, "UnaryExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getUnaryExpression_Operator(), ecorePackage.getEString(), "operator", null, 0, 1, UnaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getUnaryExpression_Expression(), this.getUnaryExpression(), null, "expression", null, 0, 1, UnaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getUnaryExpression_ParExpression(), this.getParExpression(), null, "parExpression", null, 0, 1, UnaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getUnaryExpression_Primary(), this.getPrimary(), null, "primary", null, 0, 1, UnaryExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(primaryEClass, Primary.class, "Primary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPrimary_FunctionCall(), this.getFunctionCall(), null, "functionCall", null, 0, 1, Primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPrimary_Number(), ecorePackage.getEString(), "number", null, 0, 1, Primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPrimary_Symbol(), this.getFullyQualifiedSymbolName(), null, "symbol", null, 0, 1, Primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPrimary_Attribute(), this.getFullyQualifiedArgumentName(), null, "attribute", null, 0, 1, Primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPrimary_Vector(), this.getVector(), null, "vector", null, 0, 1, Primary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(vectorEClass, Vector.class, "Vector", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getVector_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, Vector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getVector_Values(), this.getExpression(), null, "values", null, 0, -1, Vector.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(fullyQualifiedSymbolNameEClass, FullyQualifiedSymbolName.class, "FullyQualifiedSymbolName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFullyQualifiedSymbolName_Object(), this.getObjectName(), null, "object", null, 0, 1, FullyQualifiedSymbolName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getFullyQualifiedSymbolName_Identifier(), ecorePackage.getEString(), "identifier", null, 0, 1, FullyQualifiedSymbolName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(fullyQualifiedArgumentNameEClass, FullyQualifiedArgumentName.class, "FullyQualifiedArgumentName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFullyQualifiedArgumentName_Parent(), this.getFullyQualifiedSymbolName(), null, "parent", null, 0, 1, FullyQualifiedArgumentName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getFullyQualifiedArgumentName_Identifier(), ecorePackage.getEString(), "identifier", null, 0, -1, FullyQualifiedArgumentName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(objectNameEClass, ObjectName.class, "ObjectName", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getObjectName_Name(), ecorePackage.getEString(), "name", null, 0, 1, ObjectName.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Create resource
    createResource(eNS_URI);
  }

} //MdlPackageImpl
