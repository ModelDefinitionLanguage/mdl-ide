/**
 */
package org.ddmore.mdl.mdl.impl;

import org.ddmore.mdl.mdl.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class MdlFactoryImpl extends EFactoryImpl implements MdlFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static MdlFactory init()
  {
    try
    {
      MdlFactory theMdlFactory = (MdlFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.ddmore.org/mdl/Mdl"); 
      if (theMdlFactory != null)
      {
        return theMdlFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new MdlFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MdlFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case MdlPackage.MCL: return createMcl();
      case MdlPackage.MCL_OBJECT: return createMclObject();
      case MdlPackage.MODEL_OBJECT: return createModelObject();
      case MdlPackage.PARAMETER_OBJECT: return createParameterObject();
      case MdlPackage.DATA_OBJECT: return createDataObject();
      case MdlPackage.TASK_OBJECT: return createTaskObject();
      case MdlPackage.TEL_OBJECT: return createTELObject();
      case MdlPackage.MODEL_OBJECT_BLOCK: return createModelObjectBlock();
      case MdlPackage.INDIVIDUAL_VARIABLES_BLOCK: return createIndividualVariablesBlock();
      case MdlPackage.MODEL_PREDICTION_BLOCK: return createModelPredictionBlock();
      case MdlPackage.RANDOM_VARIABLE_DEFINITION_BLOCK: return createRandomVariableDefinitionBlock();
      case MdlPackage.INPUT_VARIABLES_BLOCK: return createInputVariablesBlock();
      case MdlPackage.STRUCTURAL_PARAMETERS_BLOCK: return createStructuralParametersBlock();
      case MdlPackage.VARIABILITY_PARAMETERS_BLOCK: return createVariabilityParametersBlock();
      case MdlPackage.OUTPUT_VARIABLES_BLOCK: return createOutputVariablesBlock();
      case MdlPackage.GROUP_VARIABLES_BLOCK: return createGroupVariablesBlock();
      case MdlPackage.OBSERVATION_BLOCK: return createObservationBlock();
      case MdlPackage.ESTIMATION_BLOCK: return createEstimationBlock();
      case MdlPackage.SIMULATION_BLOCK: return createSimulationBlock();
      case MdlPackage.PARAMETER_OBJECT_BLOCK: return createParameterObjectBlock();
      case MdlPackage.STRUCTURAL_BLOCK: return createStructuralBlock();
      case MdlPackage.VARIABILITY_BLOCK: return createVariabilityBlock();
      case MdlPackage.PRIOR_PARAMETERS_BLOCK: return createPriorParametersBlock();
      case MdlPackage.DATA_OBJECT_BLOCK: return createDataObjectBlock();
      case MdlPackage.HEADER_BLOCK: return createHeaderBlock();
      case MdlPackage.FILE_BLOCK: return createFileBlock();
      case MdlPackage.TASK_OBJECT_BLOCK: return createTaskObjectBlock();
      case MdlPackage.PARAMETER_BLOCK: return createParameterBlock();
      case MdlPackage.DATA_BLOCK: return createDataBlock();
      case MdlPackage.DATA_BLOCK_STATEMENT: return createDataBlockStatement();
      case MdlPackage.IGNORE_LIST: return createIgnoreList();
      case MdlPackage.ACCEPT_LIST: return createAcceptList();
      case MdlPackage.DROP_LIST: return createDropList();
      case MdlPackage.ADD_LIST: return createAddList();
      case MdlPackage.REMOVE_LIST: return createRemoveList();
      case MdlPackage.MODEL_BLOCK: return createModelBlock();
      case MdlPackage.MODEL_BLOCK_STATEMENT: return createModelBlockStatement();
      case MdlPackage.SYMBOL_LIST: return createSymbolList();
      case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT: return createModelPredictionBlockStatement();
      case MdlPackage.LIBRARY_BLOCK: return createLibraryBlock();
      case MdlPackage.FUNCTION_CALL_STATEMENT: return createFunctionCallStatement();
      case MdlPackage.ODE_BLOCK: return createOdeBlock();
      case MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT: return createGroupVariablesBlockStatement();
      case MdlPackage.MIXTURE_BLOCK: return createMixtureBlock();
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT: return createVariabilityBlockStatement();
      case MdlPackage.BLOCK_BLOCK: return createBlockBlock();
      case MdlPackage.DIAG_BLOCK: return createDiagBlock();
      case MdlPackage.SAME_BLOCK: return createSameBlock();
      case MdlPackage.FILE_BLOCK_STATEMENT: return createFileBlockStatement();
      case MdlPackage.INLINE_BLOCK: return createInlineBlock();
      case MdlPackage.DESIGN_BLOCK: return createDesignBlock();
      case MdlPackage.DESIGN_BLOCK_STATEMENT: return createDesignBlockStatement();
      case MdlPackage.VARIABLE_LIST: return createVariableList();
      case MdlPackage.RSCRIPT_BLOCK: return createRScriptBlock();
      case MdlPackage.RSCRIPT_BLOCK_STATEMENT: return createRScriptBlockStatement();
      case MdlPackage.TASK_FUNCTION_DECLARATION: return createTaskFunctionDeclaration();
      case MdlPackage.TASK_FUNCTION_BODY: return createTaskFunctionBody();
      case MdlPackage.TASK_FUNCTION_BLOCK: return createTaskFunctionBlock();
      case MdlPackage.ESTIMATE_TASK: return createEstimateTask();
      case MdlPackage.SIMULATE_TASK: return createSimulateTask();
      case MdlPackage.EXECUTE_TASK: return createExecuteTask();
      case MdlPackage.TASK_FUNCTION_STATEMENT: return createTaskFunctionStatement();
      case MdlPackage.FORMAL_ARGUMENTS: return createFormalArguments();
      case MdlPackage.FORMAL_ARGUMENT: return createFormalArgument();
      case MdlPackage.FUNCTION_CALL: return createFunctionCall();
      case MdlPackage.BLOCK_STATEMENT: return createBlockStatement();
      case MdlPackage.TARGET_BLOCK: return createTargetBlock();
      case MdlPackage.TARGET_LANGUAGE: return createTargetLanguage();
      case MdlPackage.IMPORT_BLOCK: return createImportBlock();
      case MdlPackage.IMPORTED_FUNCTION: return createImportedFunction();
      case MdlPackage.SYMBOL_MODIFICATION: return createSymbolModification();
      case MdlPackage.PARAMETER_DECLARATION: return createParameterDeclaration();
      case MdlPackage.SYMBOL_DECLARATION: return createSymbolDeclaration();
      case MdlPackage.ENUM_TYPE: return createEnumType();
      case MdlPackage.MISSING: return createMissing();
      case MdlPackage.LIKELYHOOD: return createLikelyhood();
      case MdlPackage.LEVEL_TYPE: return createLevelType();
      case MdlPackage.CATEGORICAL: return createCategorical();
      case MdlPackage.CONTINUOUS: return createContinuous();
      case MdlPackage.COVARIATE: return createCovariate();
      case MdlPackage.DISTRIBUTION: return createDistribution();
      case MdlPackage.ANY_EXPRESSION: return createAnyExpression();
      case MdlPackage.EXPRESSION: return createExpression();
      case MdlPackage.LIST: return createList();
      case MdlPackage.ODE_LIST: return createOdeList();
      case MdlPackage.RANDOM_LIST: return createRandomList();
      case MdlPackage.ARGUMENTS: return createArguments();
      case MdlPackage.ARGUMENT: return createArgument();
      case MdlPackage.CONDITIONAL_STATEMENT: return createConditionalStatement();
      case MdlPackage.BLOCK: return createBlock();
      case MdlPackage.PAR_EXPRESSION: return createParExpression();
      case MdlPackage.CONDITIONAL_EXPRESSION: return createConditionalExpression();
      case MdlPackage.OR_EXPRESSION: return createOrExpression();
      case MdlPackage.AND_EXPRESSION: return createAndExpression();
      case MdlPackage.LOGICAL_EXPRESSION: return createLogicalExpression();
      case MdlPackage.ADDITIVE_EXPRESSION: return createAdditiveExpression();
      case MdlPackage.MULTIPLICATIVE_EXPRESSION: return createMultiplicativeExpression();
      case MdlPackage.POWER_EXPRESSION: return createPowerExpression();
      case MdlPackage.UNARY_EXPRESSION: return createUnaryExpression();
      case MdlPackage.PRIMARY: return createPrimary();
      case MdlPackage.VECTOR: return createVector();
      case MdlPackage.FULLY_QUALIFIED_SYMBOL_NAME: return createFullyQualifiedSymbolName();
      case MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME: return createFullyQualifiedArgumentName();
      case MdlPackage.SELECTOR: return createSelector();
      case MdlPackage.OBJECT_NAME: return createObjectName();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Mcl createMcl()
  {
    MclImpl mcl = new MclImpl();
    return mcl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MclObject createMclObject()
  {
    MclObjectImpl mclObject = new MclObjectImpl();
    return mclObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelObject createModelObject()
  {
    ModelObjectImpl modelObject = new ModelObjectImpl();
    return modelObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParameterObject createParameterObject()
  {
    ParameterObjectImpl parameterObject = new ParameterObjectImpl();
    return parameterObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DataObject createDataObject()
  {
    DataObjectImpl dataObject = new DataObjectImpl();
    return dataObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TaskObject createTaskObject()
  {
    TaskObjectImpl taskObject = new TaskObjectImpl();
    return taskObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TELObject createTELObject()
  {
    TELObjectImpl telObject = new TELObjectImpl();
    return telObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelObjectBlock createModelObjectBlock()
  {
    ModelObjectBlockImpl modelObjectBlock = new ModelObjectBlockImpl();
    return modelObjectBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IndividualVariablesBlock createIndividualVariablesBlock()
  {
    IndividualVariablesBlockImpl individualVariablesBlock = new IndividualVariablesBlockImpl();
    return individualVariablesBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelPredictionBlock createModelPredictionBlock()
  {
    ModelPredictionBlockImpl modelPredictionBlock = new ModelPredictionBlockImpl();
    return modelPredictionBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RandomVariableDefinitionBlock createRandomVariableDefinitionBlock()
  {
    RandomVariableDefinitionBlockImpl randomVariableDefinitionBlock = new RandomVariableDefinitionBlockImpl();
    return randomVariableDefinitionBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public InputVariablesBlock createInputVariablesBlock()
  {
    InputVariablesBlockImpl inputVariablesBlock = new InputVariablesBlockImpl();
    return inputVariablesBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StructuralParametersBlock createStructuralParametersBlock()
  {
    StructuralParametersBlockImpl structuralParametersBlock = new StructuralParametersBlockImpl();
    return structuralParametersBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VariabilityParametersBlock createVariabilityParametersBlock()
  {
    VariabilityParametersBlockImpl variabilityParametersBlock = new VariabilityParametersBlockImpl();
    return variabilityParametersBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OutputVariablesBlock createOutputVariablesBlock()
  {
    OutputVariablesBlockImpl outputVariablesBlock = new OutputVariablesBlockImpl();
    return outputVariablesBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupVariablesBlock createGroupVariablesBlock()
  {
    GroupVariablesBlockImpl groupVariablesBlock = new GroupVariablesBlockImpl();
    return groupVariablesBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ObservationBlock createObservationBlock()
  {
    ObservationBlockImpl observationBlock = new ObservationBlockImpl();
    return observationBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EstimationBlock createEstimationBlock()
  {
    EstimationBlockImpl estimationBlock = new EstimationBlockImpl();
    return estimationBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimulationBlock createSimulationBlock()
  {
    SimulationBlockImpl simulationBlock = new SimulationBlockImpl();
    return simulationBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParameterObjectBlock createParameterObjectBlock()
  {
    ParameterObjectBlockImpl parameterObjectBlock = new ParameterObjectBlockImpl();
    return parameterObjectBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StructuralBlock createStructuralBlock()
  {
    StructuralBlockImpl structuralBlock = new StructuralBlockImpl();
    return structuralBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VariabilityBlock createVariabilityBlock()
  {
    VariabilityBlockImpl variabilityBlock = new VariabilityBlockImpl();
    return variabilityBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PriorParametersBlock createPriorParametersBlock()
  {
    PriorParametersBlockImpl priorParametersBlock = new PriorParametersBlockImpl();
    return priorParametersBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DataObjectBlock createDataObjectBlock()
  {
    DataObjectBlockImpl dataObjectBlock = new DataObjectBlockImpl();
    return dataObjectBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HeaderBlock createHeaderBlock()
  {
    HeaderBlockImpl headerBlock = new HeaderBlockImpl();
    return headerBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FileBlock createFileBlock()
  {
    FileBlockImpl fileBlock = new FileBlockImpl();
    return fileBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TaskObjectBlock createTaskObjectBlock()
  {
    TaskObjectBlockImpl taskObjectBlock = new TaskObjectBlockImpl();
    return taskObjectBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParameterBlock createParameterBlock()
  {
    ParameterBlockImpl parameterBlock = new ParameterBlockImpl();
    return parameterBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DataBlock createDataBlock()
  {
    DataBlockImpl dataBlock = new DataBlockImpl();
    return dataBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DataBlockStatement createDataBlockStatement()
  {
    DataBlockStatementImpl dataBlockStatement = new DataBlockStatementImpl();
    return dataBlockStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IgnoreList createIgnoreList()
  {
    IgnoreListImpl ignoreList = new IgnoreListImpl();
    return ignoreList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AcceptList createAcceptList()
  {
    AcceptListImpl acceptList = new AcceptListImpl();
    return acceptList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DropList createDropList()
  {
    DropListImpl dropList = new DropListImpl();
    return dropList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AddList createAddList()
  {
    AddListImpl addList = new AddListImpl();
    return addList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RemoveList createRemoveList()
  {
    RemoveListImpl removeList = new RemoveListImpl();
    return removeList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelBlock createModelBlock()
  {
    ModelBlockImpl modelBlock = new ModelBlockImpl();
    return modelBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelBlockStatement createModelBlockStatement()
  {
    ModelBlockStatementImpl modelBlockStatement = new ModelBlockStatementImpl();
    return modelBlockStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SymbolList createSymbolList()
  {
    SymbolListImpl symbolList = new SymbolListImpl();
    return symbolList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelPredictionBlockStatement createModelPredictionBlockStatement()
  {
    ModelPredictionBlockStatementImpl modelPredictionBlockStatement = new ModelPredictionBlockStatementImpl();
    return modelPredictionBlockStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LibraryBlock createLibraryBlock()
  {
    LibraryBlockImpl libraryBlock = new LibraryBlockImpl();
    return libraryBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunctionCallStatement createFunctionCallStatement()
  {
    FunctionCallStatementImpl functionCallStatement = new FunctionCallStatementImpl();
    return functionCallStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OdeBlock createOdeBlock()
  {
    OdeBlockImpl odeBlock = new OdeBlockImpl();
    return odeBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GroupVariablesBlockStatement createGroupVariablesBlockStatement()
  {
    GroupVariablesBlockStatementImpl groupVariablesBlockStatement = new GroupVariablesBlockStatementImpl();
    return groupVariablesBlockStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MixtureBlock createMixtureBlock()
  {
    MixtureBlockImpl mixtureBlock = new MixtureBlockImpl();
    return mixtureBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VariabilityBlockStatement createVariabilityBlockStatement()
  {
    VariabilityBlockStatementImpl variabilityBlockStatement = new VariabilityBlockStatementImpl();
    return variabilityBlockStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BlockBlock createBlockBlock()
  {
    BlockBlockImpl blockBlock = new BlockBlockImpl();
    return blockBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DiagBlock createDiagBlock()
  {
    DiagBlockImpl diagBlock = new DiagBlockImpl();
    return diagBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SameBlock createSameBlock()
  {
    SameBlockImpl sameBlock = new SameBlockImpl();
    return sameBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FileBlockStatement createFileBlockStatement()
  {
    FileBlockStatementImpl fileBlockStatement = new FileBlockStatementImpl();
    return fileBlockStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public InlineBlock createInlineBlock()
  {
    InlineBlockImpl inlineBlock = new InlineBlockImpl();
    return inlineBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DesignBlock createDesignBlock()
  {
    DesignBlockImpl designBlock = new DesignBlockImpl();
    return designBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DesignBlockStatement createDesignBlockStatement()
  {
    DesignBlockStatementImpl designBlockStatement = new DesignBlockStatementImpl();
    return designBlockStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VariableList createVariableList()
  {
    VariableListImpl variableList = new VariableListImpl();
    return variableList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RScriptBlock createRScriptBlock()
  {
    RScriptBlockImpl rScriptBlock = new RScriptBlockImpl();
    return rScriptBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RScriptBlockStatement createRScriptBlockStatement()
  {
    RScriptBlockStatementImpl rScriptBlockStatement = new RScriptBlockStatementImpl();
    return rScriptBlockStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TaskFunctionDeclaration createTaskFunctionDeclaration()
  {
    TaskFunctionDeclarationImpl taskFunctionDeclaration = new TaskFunctionDeclarationImpl();
    return taskFunctionDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TaskFunctionBody createTaskFunctionBody()
  {
    TaskFunctionBodyImpl taskFunctionBody = new TaskFunctionBodyImpl();
    return taskFunctionBody;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TaskFunctionBlock createTaskFunctionBlock()
  {
    TaskFunctionBlockImpl taskFunctionBlock = new TaskFunctionBlockImpl();
    return taskFunctionBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EstimateTask createEstimateTask()
  {
    EstimateTaskImpl estimateTask = new EstimateTaskImpl();
    return estimateTask;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimulateTask createSimulateTask()
  {
    SimulateTaskImpl simulateTask = new SimulateTaskImpl();
    return simulateTask;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExecuteTask createExecuteTask()
  {
    ExecuteTaskImpl executeTask = new ExecuteTaskImpl();
    return executeTask;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TaskFunctionStatement createTaskFunctionStatement()
  {
    TaskFunctionStatementImpl taskFunctionStatement = new TaskFunctionStatementImpl();
    return taskFunctionStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FormalArguments createFormalArguments()
  {
    FormalArgumentsImpl formalArguments = new FormalArgumentsImpl();
    return formalArguments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FormalArgument createFormalArgument()
  {
    FormalArgumentImpl formalArgument = new FormalArgumentImpl();
    return formalArgument;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunctionCall createFunctionCall()
  {
    FunctionCallImpl functionCall = new FunctionCallImpl();
    return functionCall;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BlockStatement createBlockStatement()
  {
    BlockStatementImpl blockStatement = new BlockStatementImpl();
    return blockStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TargetBlock createTargetBlock()
  {
    TargetBlockImpl targetBlock = new TargetBlockImpl();
    return targetBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TargetLanguage createTargetLanguage()
  {
    TargetLanguageImpl targetLanguage = new TargetLanguageImpl();
    return targetLanguage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ImportBlock createImportBlock()
  {
    ImportBlockImpl importBlock = new ImportBlockImpl();
    return importBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ImportedFunction createImportedFunction()
  {
    ImportedFunctionImpl importedFunction = new ImportedFunctionImpl();
    return importedFunction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SymbolModification createSymbolModification()
  {
    SymbolModificationImpl symbolModification = new SymbolModificationImpl();
    return symbolModification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParameterDeclaration createParameterDeclaration()
  {
    ParameterDeclarationImpl parameterDeclaration = new ParameterDeclarationImpl();
    return parameterDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SymbolDeclaration createSymbolDeclaration()
  {
    SymbolDeclarationImpl symbolDeclaration = new SymbolDeclarationImpl();
    return symbolDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EnumType createEnumType()
  {
    EnumTypeImpl enumType = new EnumTypeImpl();
    return enumType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Missing createMissing()
  {
    MissingImpl missing = new MissingImpl();
    return missing;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Likelyhood createLikelyhood()
  {
    LikelyhoodImpl likelyhood = new LikelyhoodImpl();
    return likelyhood;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LevelType createLevelType()
  {
    LevelTypeImpl levelType = new LevelTypeImpl();
    return levelType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Categorical createCategorical()
  {
    CategoricalImpl categorical = new CategoricalImpl();
    return categorical;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Continuous createContinuous()
  {
    ContinuousImpl continuous = new ContinuousImpl();
    return continuous;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Covariate createCovariate()
  {
    CovariateImpl covariate = new CovariateImpl();
    return covariate;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Distribution createDistribution()
  {
    DistributionImpl distribution = new DistributionImpl();
    return distribution;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnyExpression createAnyExpression()
  {
    AnyExpressionImpl anyExpression = new AnyExpressionImpl();
    return anyExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression createExpression()
  {
    ExpressionImpl expression = new ExpressionImpl();
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List createList()
  {
    ListImpl list = new ListImpl();
    return list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OdeList createOdeList()
  {
    OdeListImpl odeList = new OdeListImpl();
    return odeList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RandomList createRandomList()
  {
    RandomListImpl randomList = new RandomListImpl();
    return randomList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Arguments createArguments()
  {
    ArgumentsImpl arguments = new ArgumentsImpl();
    return arguments;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Argument createArgument()
  {
    ArgumentImpl argument = new ArgumentImpl();
    return argument;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConditionalStatement createConditionalStatement()
  {
    ConditionalStatementImpl conditionalStatement = new ConditionalStatementImpl();
    return conditionalStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Block createBlock()
  {
    BlockImpl block = new BlockImpl();
    return block;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParExpression createParExpression()
  {
    ParExpressionImpl parExpression = new ParExpressionImpl();
    return parExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConditionalExpression createConditionalExpression()
  {
    ConditionalExpressionImpl conditionalExpression = new ConditionalExpressionImpl();
    return conditionalExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OrExpression createOrExpression()
  {
    OrExpressionImpl orExpression = new OrExpressionImpl();
    return orExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AndExpression createAndExpression()
  {
    AndExpressionImpl andExpression = new AndExpressionImpl();
    return andExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LogicalExpression createLogicalExpression()
  {
    LogicalExpressionImpl logicalExpression = new LogicalExpressionImpl();
    return logicalExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AdditiveExpression createAdditiveExpression()
  {
    AdditiveExpressionImpl additiveExpression = new AdditiveExpressionImpl();
    return additiveExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MultiplicativeExpression createMultiplicativeExpression()
  {
    MultiplicativeExpressionImpl multiplicativeExpression = new MultiplicativeExpressionImpl();
    return multiplicativeExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PowerExpression createPowerExpression()
  {
    PowerExpressionImpl powerExpression = new PowerExpressionImpl();
    return powerExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnaryExpression createUnaryExpression()
  {
    UnaryExpressionImpl unaryExpression = new UnaryExpressionImpl();
    return unaryExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Primary createPrimary()
  {
    PrimaryImpl primary = new PrimaryImpl();
    return primary;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Vector createVector()
  {
    VectorImpl vector = new VectorImpl();
    return vector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FullyQualifiedSymbolName createFullyQualifiedSymbolName()
  {
    FullyQualifiedSymbolNameImpl fullyQualifiedSymbolName = new FullyQualifiedSymbolNameImpl();
    return fullyQualifiedSymbolName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FullyQualifiedArgumentName createFullyQualifiedArgumentName()
  {
    FullyQualifiedArgumentNameImpl fullyQualifiedArgumentName = new FullyQualifiedArgumentNameImpl();
    return fullyQualifiedArgumentName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Selector createSelector()
  {
    SelectorImpl selector = new SelectorImpl();
    return selector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ObjectName createObjectName()
  {
    ObjectNameImpl objectName = new ObjectNameImpl();
    return objectName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MdlPackage getMdlPackage()
  {
    return (MdlPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static MdlPackage getPackage()
  {
    return MdlPackage.eINSTANCE;
  }

} //MdlFactoryImpl
