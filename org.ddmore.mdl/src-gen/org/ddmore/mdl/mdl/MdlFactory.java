/**
 */
package org.ddmore.mdl.mdl;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.ddmore.mdl.mdl.MdlPackage
 * @generated
 */
public interface MdlFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  MdlFactory eINSTANCE = org.ddmore.mdl.mdl.impl.MdlFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Mcl</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mcl</em>'.
   * @generated
   */
  Mcl createMcl();

  /**
   * Returns a new object of class '<em>Mcl Object</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mcl Object</em>'.
   * @generated
   */
  MclObject createMclObject();

  /**
   * Returns a new object of class '<em>Model Object</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model Object</em>'.
   * @generated
   */
  ModelObject createModelObject();

  /**
   * Returns a new object of class '<em>Parameter Object</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Parameter Object</em>'.
   * @generated
   */
  ParameterObject createParameterObject();

  /**
   * Returns a new object of class '<em>Data Object</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Data Object</em>'.
   * @generated
   */
  DataObject createDataObject();

  /**
   * Returns a new object of class '<em>Task Object</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Task Object</em>'.
   * @generated
   */
  TaskObject createTaskObject();

  /**
   * Returns a new object of class '<em>TEL Object</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>TEL Object</em>'.
   * @generated
   */
  TELObject createTELObject();

  /**
   * Returns a new object of class '<em>Model Object Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model Object Block</em>'.
   * @generated
   */
  ModelObjectBlock createModelObjectBlock();

  /**
   * Returns a new object of class '<em>Individual Variables Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Individual Variables Block</em>'.
   * @generated
   */
  IndividualVariablesBlock createIndividualVariablesBlock();

  /**
   * Returns a new object of class '<em>Model Prediction Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model Prediction Block</em>'.
   * @generated
   */
  ModelPredictionBlock createModelPredictionBlock();

  /**
   * Returns a new object of class '<em>Random Variable Definition Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Random Variable Definition Block</em>'.
   * @generated
   */
  RandomVariableDefinitionBlock createRandomVariableDefinitionBlock();

  /**
   * Returns a new object of class '<em>Input Variables Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Input Variables Block</em>'.
   * @generated
   */
  InputVariablesBlock createInputVariablesBlock();

  /**
   * Returns a new object of class '<em>Structural Parameters Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Structural Parameters Block</em>'.
   * @generated
   */
  StructuralParametersBlock createStructuralParametersBlock();

  /**
   * Returns a new object of class '<em>Variability Parameters Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Variability Parameters Block</em>'.
   * @generated
   */
  VariabilityParametersBlock createVariabilityParametersBlock();

  /**
   * Returns a new object of class '<em>Output Variables Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Output Variables Block</em>'.
   * @generated
   */
  OutputVariablesBlock createOutputVariablesBlock();

  /**
   * Returns a new object of class '<em>Group Variables Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Group Variables Block</em>'.
   * @generated
   */
  GroupVariablesBlock createGroupVariablesBlock();

  /**
   * Returns a new object of class '<em>Observation Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Observation Block</em>'.
   * @generated
   */
  ObservationBlock createObservationBlock();

  /**
   * Returns a new object of class '<em>Estimation Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Estimation Block</em>'.
   * @generated
   */
  EstimationBlock createEstimationBlock();

  /**
   * Returns a new object of class '<em>Simulation Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Simulation Block</em>'.
   * @generated
   */
  SimulationBlock createSimulationBlock();

  /**
   * Returns a new object of class '<em>Parameter Object Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Parameter Object Block</em>'.
   * @generated
   */
  ParameterObjectBlock createParameterObjectBlock();

  /**
   * Returns a new object of class '<em>Structural Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Structural Block</em>'.
   * @generated
   */
  StructuralBlock createStructuralBlock();

  /**
   * Returns a new object of class '<em>Variability Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Variability Block</em>'.
   * @generated
   */
  VariabilityBlock createVariabilityBlock();

  /**
   * Returns a new object of class '<em>Prior Parameters Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Prior Parameters Block</em>'.
   * @generated
   */
  PriorParametersBlock createPriorParametersBlock();

  /**
   * Returns a new object of class '<em>Data Object Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Data Object Block</em>'.
   * @generated
   */
  DataObjectBlock createDataObjectBlock();

  /**
   * Returns a new object of class '<em>Header Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Header Block</em>'.
   * @generated
   */
  HeaderBlock createHeaderBlock();

  /**
   * Returns a new object of class '<em>File Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>File Block</em>'.
   * @generated
   */
  FileBlock createFileBlock();

  /**
   * Returns a new object of class '<em>Task Object Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Task Object Block</em>'.
   * @generated
   */
  TaskObjectBlock createTaskObjectBlock();

  /**
   * Returns a new object of class '<em>Parameter Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Parameter Block</em>'.
   * @generated
   */
  ParameterBlock createParameterBlock();

  /**
   * Returns a new object of class '<em>Data Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Data Block</em>'.
   * @generated
   */
  DataBlock createDataBlock();

  /**
   * Returns a new object of class '<em>Data Block Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Data Block Statement</em>'.
   * @generated
   */
  DataBlockStatement createDataBlockStatement();

  /**
   * Returns a new object of class '<em>Ignore List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ignore List</em>'.
   * @generated
   */
  IgnoreList createIgnoreList();

  /**
   * Returns a new object of class '<em>Accept List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Accept List</em>'.
   * @generated
   */
  AcceptList createAcceptList();

  /**
   * Returns a new object of class '<em>Drop List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Drop List</em>'.
   * @generated
   */
  DropList createDropList();

  /**
   * Returns a new object of class '<em>Model Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model Block</em>'.
   * @generated
   */
  ModelBlock createModelBlock();

  /**
   * Returns a new object of class '<em>Model Block Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model Block Statement</em>'.
   * @generated
   */
  ModelBlockStatement createModelBlockStatement();

  /**
   * Returns a new object of class '<em>Add List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Add List</em>'.
   * @generated
   */
  AddList createAddList();

  /**
   * Returns a new object of class '<em>Remove List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Remove List</em>'.
   * @generated
   */
  RemoveList createRemoveList();

  /**
   * Returns a new object of class '<em>Symbol List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Symbol List</em>'.
   * @generated
   */
  SymbolList createSymbolList();

  /**
   * Returns a new object of class '<em>Model Prediction Block Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model Prediction Block Statement</em>'.
   * @generated
   */
  ModelPredictionBlockStatement createModelPredictionBlockStatement();

  /**
   * Returns a new object of class '<em>Library Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Library Block</em>'.
   * @generated
   */
  LibraryBlock createLibraryBlock();

  /**
   * Returns a new object of class '<em>Function Call Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Function Call Statement</em>'.
   * @generated
   */
  FunctionCallStatement createFunctionCallStatement();

  /**
   * Returns a new object of class '<em>Ode Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ode Block</em>'.
   * @generated
   */
  OdeBlock createOdeBlock();

  /**
   * Returns a new object of class '<em>Group Variables Block Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Group Variables Block Statement</em>'.
   * @generated
   */
  GroupVariablesBlockStatement createGroupVariablesBlockStatement();

  /**
   * Returns a new object of class '<em>Mixture Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Mixture Block</em>'.
   * @generated
   */
  MixtureBlock createMixtureBlock();

  /**
   * Returns a new object of class '<em>Variability Block Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Variability Block Statement</em>'.
   * @generated
   */
  VariabilityBlockStatement createVariabilityBlockStatement();

  /**
   * Returns a new object of class '<em>Block Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Block Block</em>'.
   * @generated
   */
  BlockBlock createBlockBlock();

  /**
   * Returns a new object of class '<em>Diag Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Diag Block</em>'.
   * @generated
   */
  DiagBlock createDiagBlock();

  /**
   * Returns a new object of class '<em>Same Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Same Block</em>'.
   * @generated
   */
  SameBlock createSameBlock();

  /**
   * Returns a new object of class '<em>File Block Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>File Block Statement</em>'.
   * @generated
   */
  FileBlockStatement createFileBlockStatement();

  /**
   * Returns a new object of class '<em>Inline Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Inline Block</em>'.
   * @generated
   */
  InlineBlock createInlineBlock();

  /**
   * Returns a new object of class '<em>Design Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Design Block</em>'.
   * @generated
   */
  DesignBlock createDesignBlock();

  /**
   * Returns a new object of class '<em>Design Block Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Design Block Statement</em>'.
   * @generated
   */
  DesignBlockStatement createDesignBlockStatement();

  /**
   * Returns a new object of class '<em>Variable List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Variable List</em>'.
   * @generated
   */
  VariableList createVariableList();

  /**
   * Returns a new object of class '<em>RScript Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>RScript Block</em>'.
   * @generated
   */
  RScriptBlock createRScriptBlock();

  /**
   * Returns a new object of class '<em>RScript Block Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>RScript Block Statement</em>'.
   * @generated
   */
  RScriptBlockStatement createRScriptBlockStatement();

  /**
   * Returns a new object of class '<em>Task Function Declaration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Task Function Declaration</em>'.
   * @generated
   */
  TaskFunctionDeclaration createTaskFunctionDeclaration();

  /**
   * Returns a new object of class '<em>Task Function Body</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Task Function Body</em>'.
   * @generated
   */
  TaskFunctionBody createTaskFunctionBody();

  /**
   * Returns a new object of class '<em>Task Function Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Task Function Block</em>'.
   * @generated
   */
  TaskFunctionBlock createTaskFunctionBlock();

  /**
   * Returns a new object of class '<em>Estimate Task</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Estimate Task</em>'.
   * @generated
   */
  EstimateTask createEstimateTask();

  /**
   * Returns a new object of class '<em>Simulate Task</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Simulate Task</em>'.
   * @generated
   */
  SimulateTask createSimulateTask();

  /**
   * Returns a new object of class '<em>Execute Task</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Execute Task</em>'.
   * @generated
   */
  ExecuteTask createExecuteTask();

  /**
   * Returns a new object of class '<em>Formal Arguments</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Formal Arguments</em>'.
   * @generated
   */
  FormalArguments createFormalArguments();

  /**
   * Returns a new object of class '<em>Formal Argument</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Formal Argument</em>'.
   * @generated
   */
  FormalArgument createFormalArgument();

  /**
   * Returns a new object of class '<em>Function Call</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Function Call</em>'.
   * @generated
   */
  FunctionCall createFunctionCall();

  /**
   * Returns a new object of class '<em>Block Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Block Statement</em>'.
   * @generated
   */
  BlockStatement createBlockStatement();

  /**
   * Returns a new object of class '<em>Target Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Target Block</em>'.
   * @generated
   */
  TargetBlock createTargetBlock();

  /**
   * Returns a new object of class '<em>Import Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Import Block</em>'.
   * @generated
   */
  ImportBlock createImportBlock();

  /**
   * Returns a new object of class '<em>Imported Function</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Imported Function</em>'.
   * @generated
   */
  ImportedFunction createImportedFunction();

  /**
   * Returns a new object of class '<em>Symbol Modification</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Symbol Modification</em>'.
   * @generated
   */
  SymbolModification createSymbolModification();

  /**
   * Returns a new object of class '<em>Parameter Declaration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Parameter Declaration</em>'.
   * @generated
   */
  ParameterDeclaration createParameterDeclaration();

  /**
   * Returns a new object of class '<em>Symbol Declaration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Symbol Declaration</em>'.
   * @generated
   */
  SymbolDeclaration createSymbolDeclaration();

  /**
   * Returns a new object of class '<em>Enum Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Enum Type</em>'.
   * @generated
   */
  EnumType createEnumType();

  /**
   * Returns a new object of class '<em>Missing</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Missing</em>'.
   * @generated
   */
  Missing createMissing();

  /**
   * Returns a new object of class '<em>Likelyhood</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Likelyhood</em>'.
   * @generated
   */
  Likelyhood createLikelyhood();

  /**
   * Returns a new object of class '<em>Level Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Level Type</em>'.
   * @generated
   */
  LevelType createLevelType();

  /**
   * Returns a new object of class '<em>Categorical</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Categorical</em>'.
   * @generated
   */
  Categorical createCategorical();

  /**
   * Returns a new object of class '<em>Continuous</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Continuous</em>'.
   * @generated
   */
  Continuous createContinuous();

  /**
   * Returns a new object of class '<em>Covariate</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Covariate</em>'.
   * @generated
   */
  Covariate createCovariate();

  /**
   * Returns a new object of class '<em>Distribution</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Distribution</em>'.
   * @generated
   */
  Distribution createDistribution();

  /**
   * Returns a new object of class '<em>Any Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Any Expression</em>'.
   * @generated
   */
  AnyExpression createAnyExpression();

  /**
   * Returns a new object of class '<em>Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Expression</em>'.
   * @generated
   */
  Expression createExpression();

  /**
   * Returns a new object of class '<em>List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>List</em>'.
   * @generated
   */
  List createList();

  /**
   * Returns a new object of class '<em>Ode List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Ode List</em>'.
   * @generated
   */
  OdeList createOdeList();

  /**
   * Returns a new object of class '<em>Random List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Random List</em>'.
   * @generated
   */
  RandomList createRandomList();

  /**
   * Returns a new object of class '<em>Arguments</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Arguments</em>'.
   * @generated
   */
  Arguments createArguments();

  /**
   * Returns a new object of class '<em>Argument</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Argument</em>'.
   * @generated
   */
  Argument createArgument();

  /**
   * Returns a new object of class '<em>Conditional Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Conditional Statement</em>'.
   * @generated
   */
  ConditionalStatement createConditionalStatement();

  /**
   * Returns a new object of class '<em>Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Block</em>'.
   * @generated
   */
  Block createBlock();

  /**
   * Returns a new object of class '<em>Par Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Par Expression</em>'.
   * @generated
   */
  ParExpression createParExpression();

  /**
   * Returns a new object of class '<em>Conditional Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Conditional Expression</em>'.
   * @generated
   */
  ConditionalExpression createConditionalExpression();

  /**
   * Returns a new object of class '<em>Or Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Or Expression</em>'.
   * @generated
   */
  OrExpression createOrExpression();

  /**
   * Returns a new object of class '<em>And Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>And Expression</em>'.
   * @generated
   */
  AndExpression createAndExpression();

  /**
   * Returns a new object of class '<em>Logical Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Logical Expression</em>'.
   * @generated
   */
  LogicalExpression createLogicalExpression();

  /**
   * Returns a new object of class '<em>Additive Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Additive Expression</em>'.
   * @generated
   */
  AdditiveExpression createAdditiveExpression();

  /**
   * Returns a new object of class '<em>Multiplicative Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Multiplicative Expression</em>'.
   * @generated
   */
  MultiplicativeExpression createMultiplicativeExpression();

  /**
   * Returns a new object of class '<em>Power Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Power Expression</em>'.
   * @generated
   */
  PowerExpression createPowerExpression();

  /**
   * Returns a new object of class '<em>Unary Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Unary Expression</em>'.
   * @generated
   */
  UnaryExpression createUnaryExpression();

  /**
   * Returns a new object of class '<em>Primary</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Primary</em>'.
   * @generated
   */
  Primary createPrimary();

  /**
   * Returns a new object of class '<em>Vector</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Vector</em>'.
   * @generated
   */
  Vector createVector();

  /**
   * Returns a new object of class '<em>Fully Qualified Symbol Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Fully Qualified Symbol Name</em>'.
   * @generated
   */
  FullyQualifiedSymbolName createFullyQualifiedSymbolName();

  /**
   * Returns a new object of class '<em>Fully Qualified Argument Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Fully Qualified Argument Name</em>'.
   * @generated
   */
  FullyQualifiedArgumentName createFullyQualifiedArgumentName();

  /**
   * Returns a new object of class '<em>Selector</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Selector</em>'.
   * @generated
   */
  Selector createSelector();

  /**
   * Returns a new object of class '<em>Object Name</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Object Name</em>'.
   * @generated
   */
  ObjectName createObjectName();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  MdlPackage getMdlPackage();

} //MdlFactory
