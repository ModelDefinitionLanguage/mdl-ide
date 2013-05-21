/**
 */
package org.ddmore.mdl.mdl.util;

import org.ddmore.mdl.mdl.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.ddmore.mdl.mdl.MdlPackage
 * @generated
 */
public class MdlSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static MdlPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MdlSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = MdlPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case MdlPackage.MCL:
      {
        Mcl mcl = (Mcl)theEObject;
        T result = caseMcl(mcl);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.MCL_OBJECT:
      {
        MclObject mclObject = (MclObject)theEObject;
        T result = caseMclObject(mclObject);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.MODEL_OBJECT:
      {
        ModelObject modelObject = (ModelObject)theEObject;
        T result = caseModelObject(modelObject);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.PARAMETER_OBJECT:
      {
        ParameterObject parameterObject = (ParameterObject)theEObject;
        T result = caseParameterObject(parameterObject);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.DATA_OBJECT:
      {
        DataObject dataObject = (DataObject)theEObject;
        T result = caseDataObject(dataObject);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.TASK_OBJECT:
      {
        TaskObject taskObject = (TaskObject)theEObject;
        T result = caseTaskObject(taskObject);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.TEL_OBJECT:
      {
        TELObject telObject = (TELObject)theEObject;
        T result = caseTELObject(telObject);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.MODEL_OBJECT_BLOCK:
      {
        ModelObjectBlock modelObjectBlock = (ModelObjectBlock)theEObject;
        T result = caseModelObjectBlock(modelObjectBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.INDIVIDUAL_VARIABLES_BLOCK:
      {
        IndividualVariablesBlock individualVariablesBlock = (IndividualVariablesBlock)theEObject;
        T result = caseIndividualVariablesBlock(individualVariablesBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.MODEL_PREDICTION_BLOCK:
      {
        ModelPredictionBlock modelPredictionBlock = (ModelPredictionBlock)theEObject;
        T result = caseModelPredictionBlock(modelPredictionBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.RANDOM_VARIABLE_DEFINITION_BLOCK:
      {
        RandomVariableDefinitionBlock randomVariableDefinitionBlock = (RandomVariableDefinitionBlock)theEObject;
        T result = caseRandomVariableDefinitionBlock(randomVariableDefinitionBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.INPUT_VARIABLES_BLOCK:
      {
        InputVariablesBlock inputVariablesBlock = (InputVariablesBlock)theEObject;
        T result = caseInputVariablesBlock(inputVariablesBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.STRUCTURAL_PARAMETERS_BLOCK:
      {
        StructuralParametersBlock structuralParametersBlock = (StructuralParametersBlock)theEObject;
        T result = caseStructuralParametersBlock(structuralParametersBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.VARIABILITY_PARAMETERS_BLOCK:
      {
        VariabilityParametersBlock variabilityParametersBlock = (VariabilityParametersBlock)theEObject;
        T result = caseVariabilityParametersBlock(variabilityParametersBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.OUTPUT_VARIABLES_BLOCK:
      {
        OutputVariablesBlock outputVariablesBlock = (OutputVariablesBlock)theEObject;
        T result = caseOutputVariablesBlock(outputVariablesBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.GROUP_VARIABLES_BLOCK:
      {
        GroupVariablesBlock groupVariablesBlock = (GroupVariablesBlock)theEObject;
        T result = caseGroupVariablesBlock(groupVariablesBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.OBSERVATION_BLOCK:
      {
        ObservationBlock observationBlock = (ObservationBlock)theEObject;
        T result = caseObservationBlock(observationBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ESTIMATION_BLOCK:
      {
        EstimationBlock estimationBlock = (EstimationBlock)theEObject;
        T result = caseEstimationBlock(estimationBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.SIMULATION_BLOCK:
      {
        SimulationBlock simulationBlock = (SimulationBlock)theEObject;
        T result = caseSimulationBlock(simulationBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.PARAMETER_OBJECT_BLOCK:
      {
        ParameterObjectBlock parameterObjectBlock = (ParameterObjectBlock)theEObject;
        T result = caseParameterObjectBlock(parameterObjectBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.STRUCTURAL_BLOCK:
      {
        StructuralBlock structuralBlock = (StructuralBlock)theEObject;
        T result = caseStructuralBlock(structuralBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.VARIABILITY_BLOCK:
      {
        VariabilityBlock variabilityBlock = (VariabilityBlock)theEObject;
        T result = caseVariabilityBlock(variabilityBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.PRIOR_PARAMETERS_BLOCK:
      {
        PriorParametersBlock priorParametersBlock = (PriorParametersBlock)theEObject;
        T result = casePriorParametersBlock(priorParametersBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.DATA_OBJECT_BLOCK:
      {
        DataObjectBlock dataObjectBlock = (DataObjectBlock)theEObject;
        T result = caseDataObjectBlock(dataObjectBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.HEADER_BLOCK:
      {
        HeaderBlock headerBlock = (HeaderBlock)theEObject;
        T result = caseHeaderBlock(headerBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.FILE_BLOCK:
      {
        FileBlock fileBlock = (FileBlock)theEObject;
        T result = caseFileBlock(fileBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.TASK_OBJECT_BLOCK:
      {
        TaskObjectBlock taskObjectBlock = (TaskObjectBlock)theEObject;
        T result = caseTaskObjectBlock(taskObjectBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.PARAMETER_BLOCK:
      {
        ParameterBlock parameterBlock = (ParameterBlock)theEObject;
        T result = caseParameterBlock(parameterBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.DATA_BLOCK:
      {
        DataBlock dataBlock = (DataBlock)theEObject;
        T result = caseDataBlock(dataBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.DATA_BLOCK_STATEMENT:
      {
        DataBlockStatement dataBlockStatement = (DataBlockStatement)theEObject;
        T result = caseDataBlockStatement(dataBlockStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.IGNORE_LIST:
      {
        IgnoreList ignoreList = (IgnoreList)theEObject;
        T result = caseIgnoreList(ignoreList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ACCEPT_LIST:
      {
        AcceptList acceptList = (AcceptList)theEObject;
        T result = caseAcceptList(acceptList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.DROP_LIST:
      {
        DropList dropList = (DropList)theEObject;
        T result = caseDropList(dropList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.MODEL_BLOCK:
      {
        ModelBlock modelBlock = (ModelBlock)theEObject;
        T result = caseModelBlock(modelBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.MODEL_BLOCK_STATEMENT:
      {
        ModelBlockStatement modelBlockStatement = (ModelBlockStatement)theEObject;
        T result = caseModelBlockStatement(modelBlockStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ADD_LIST:
      {
        AddList addList = (AddList)theEObject;
        T result = caseAddList(addList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.REMOVE_LIST:
      {
        RemoveList removeList = (RemoveList)theEObject;
        T result = caseRemoveList(removeList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.SYMBOL_LIST:
      {
        SymbolList symbolList = (SymbolList)theEObject;
        T result = caseSymbolList(symbolList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.MODEL_PREDICTION_BLOCK_STATEMENT:
      {
        ModelPredictionBlockStatement modelPredictionBlockStatement = (ModelPredictionBlockStatement)theEObject;
        T result = caseModelPredictionBlockStatement(modelPredictionBlockStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.LIBRARY_BLOCK:
      {
        LibraryBlock libraryBlock = (LibraryBlock)theEObject;
        T result = caseLibraryBlock(libraryBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ODE_BLOCK:
      {
        OdeBlock odeBlock = (OdeBlock)theEObject;
        T result = caseOdeBlock(odeBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.GROUP_VARIABLES_BLOCK_STATEMENT:
      {
        GroupVariablesBlockStatement groupVariablesBlockStatement = (GroupVariablesBlockStatement)theEObject;
        T result = caseGroupVariablesBlockStatement(groupVariablesBlockStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.MIXTURE_BLOCK:
      {
        MixtureBlock mixtureBlock = (MixtureBlock)theEObject;
        T result = caseMixtureBlock(mixtureBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.VARIABILITY_BLOCK_STATEMENT:
      {
        VariabilityBlockStatement variabilityBlockStatement = (VariabilityBlockStatement)theEObject;
        T result = caseVariabilityBlockStatement(variabilityBlockStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.BLOCK_BLOCK:
      {
        BlockBlock blockBlock = (BlockBlock)theEObject;
        T result = caseBlockBlock(blockBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.DIAG_BLOCK:
      {
        DiagBlock diagBlock = (DiagBlock)theEObject;
        T result = caseDiagBlock(diagBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.SAME_BLOCK:
      {
        SameBlock sameBlock = (SameBlock)theEObject;
        T result = caseSameBlock(sameBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.FILE_BLOCK_STATEMENT:
      {
        FileBlockStatement fileBlockStatement = (FileBlockStatement)theEObject;
        T result = caseFileBlockStatement(fileBlockStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.INLINE_BLOCK:
      {
        InlineBlock inlineBlock = (InlineBlock)theEObject;
        T result = caseInlineBlock(inlineBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.DESIGN_BLOCK:
      {
        DesignBlock designBlock = (DesignBlock)theEObject;
        T result = caseDesignBlock(designBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.DESIGN_BLOCK_STATEMENT:
      {
        DesignBlockStatement designBlockStatement = (DesignBlockStatement)theEObject;
        T result = caseDesignBlockStatement(designBlockStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.VARIABLE_LIST:
      {
        VariableList variableList = (VariableList)theEObject;
        T result = caseVariableList(variableList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.RSCRIPT_BLOCK:
      {
        RScriptBlock rScriptBlock = (RScriptBlock)theEObject;
        T result = caseRScriptBlock(rScriptBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.RSCRIPT_BLOCK_STATEMENT:
      {
        RScriptBlockStatement rScriptBlockStatement = (RScriptBlockStatement)theEObject;
        T result = caseRScriptBlockStatement(rScriptBlockStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.TASK_FUNCTION_DECLARATION:
      {
        TaskFunctionDeclaration taskFunctionDeclaration = (TaskFunctionDeclaration)theEObject;
        T result = caseTaskFunctionDeclaration(taskFunctionDeclaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.TASK_FUNCTION_BODY:
      {
        TaskFunctionBody taskFunctionBody = (TaskFunctionBody)theEObject;
        T result = caseTaskFunctionBody(taskFunctionBody);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.TASK_FUNCTION_BLOCK:
      {
        TaskFunctionBlock taskFunctionBlock = (TaskFunctionBlock)theEObject;
        T result = caseTaskFunctionBlock(taskFunctionBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ESTIMATE_TASK:
      {
        EstimateTask estimateTask = (EstimateTask)theEObject;
        T result = caseEstimateTask(estimateTask);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.SIMULATE_TASK:
      {
        SimulateTask simulateTask = (SimulateTask)theEObject;
        T result = caseSimulateTask(simulateTask);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.EXECUTE_TASK:
      {
        ExecuteTask executeTask = (ExecuteTask)theEObject;
        T result = caseExecuteTask(executeTask);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.FORMAL_ARGUMENTS:
      {
        FormalArguments formalArguments = (FormalArguments)theEObject;
        T result = caseFormalArguments(formalArguments);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.FUNCTION_CALL:
      {
        FunctionCall functionCall = (FunctionCall)theEObject;
        T result = caseFunctionCall(functionCall);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.BLOCK_STATEMENT:
      {
        BlockStatement blockStatement = (BlockStatement)theEObject;
        T result = caseBlockStatement(blockStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.VERBATIM_BLOCK:
      {
        VerbatimBlock verbatimBlock = (VerbatimBlock)theEObject;
        T result = caseVerbatimBlock(verbatimBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.TARGET_BLOCK:
      {
        TargetBlock targetBlock = (TargetBlock)theEObject;
        T result = caseTargetBlock(targetBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.SYMBOL_MODIFICATION:
      {
        SymbolModification symbolModification = (SymbolModification)theEObject;
        T result = caseSymbolModification(symbolModification);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.PARAMETER_DECLARATION:
      {
        ParameterDeclaration parameterDeclaration = (ParameterDeclaration)theEObject;
        T result = caseParameterDeclaration(parameterDeclaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.SYMBOL_DECLARATION:
      {
        SymbolDeclaration symbolDeclaration = (SymbolDeclaration)theEObject;
        T result = caseSymbolDeclaration(symbolDeclaration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ENUM_TYPE:
      {
        EnumType enumType = (EnumType)theEObject;
        T result = caseEnumType(enumType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.MISSING:
      {
        Missing missing = (Missing)theEObject;
        T result = caseMissing(missing);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.LIKELYHOOD:
      {
        Likelyhood likelyhood = (Likelyhood)theEObject;
        T result = caseLikelyhood(likelyhood);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.LEVEL_TYPE:
      {
        LevelType levelType = (LevelType)theEObject;
        T result = caseLevelType(levelType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.CATEGORICAL:
      {
        Categorical categorical = (Categorical)theEObject;
        T result = caseCategorical(categorical);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.CONTINUOUS:
      {
        Continuous continuous = (Continuous)theEObject;
        T result = caseContinuous(continuous);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.COVARIATE:
      {
        Covariate covariate = (Covariate)theEObject;
        T result = caseCovariate(covariate);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.DISTRIBUTION:
      {
        Distribution distribution = (Distribution)theEObject;
        T result = caseDistribution(distribution);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ANY_EXPRESSION:
      {
        AnyExpression anyExpression = (AnyExpression)theEObject;
        T result = caseAnyExpression(anyExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.EXPRESSION:
      {
        Expression expression = (Expression)theEObject;
        T result = caseExpression(expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.LIST:
      {
        List list = (List)theEObject;
        T result = caseList(list);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ODE_LIST:
      {
        OdeList odeList = (OdeList)theEObject;
        T result = caseOdeList(odeList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.RANDOM_LIST:
      {
        RandomList randomList = (RandomList)theEObject;
        T result = caseRandomList(randomList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ARGUMENTS:
      {
        Arguments arguments = (Arguments)theEObject;
        T result = caseArguments(arguments);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ARGUMENT:
      {
        Argument argument = (Argument)theEObject;
        T result = caseArgument(argument);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.CONDITIONAL_STATEMENT:
      {
        ConditionalStatement conditionalStatement = (ConditionalStatement)theEObject;
        T result = caseConditionalStatement(conditionalStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.BLOCK:
      {
        Block block = (Block)theEObject;
        T result = caseBlock(block);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.PAR_EXPRESSION:
      {
        ParExpression parExpression = (ParExpression)theEObject;
        T result = caseParExpression(parExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.CONDITIONAL_EXPRESSION:
      {
        ConditionalExpression conditionalExpression = (ConditionalExpression)theEObject;
        T result = caseConditionalExpression(conditionalExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.OR_EXPRESSION:
      {
        OrExpression orExpression = (OrExpression)theEObject;
        T result = caseOrExpression(orExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.AND_EXPRESSION:
      {
        AndExpression andExpression = (AndExpression)theEObject;
        T result = caseAndExpression(andExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.LOGICAL_EXPRESSION:
      {
        LogicalExpression logicalExpression = (LogicalExpression)theEObject;
        T result = caseLogicalExpression(logicalExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.ADDITIVE_EXPRESSION:
      {
        AdditiveExpression additiveExpression = (AdditiveExpression)theEObject;
        T result = caseAdditiveExpression(additiveExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.MULTIPLICATIVE_EXPRESSION:
      {
        MultiplicativeExpression multiplicativeExpression = (MultiplicativeExpression)theEObject;
        T result = caseMultiplicativeExpression(multiplicativeExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.POWER_EXPRESSION:
      {
        PowerExpression powerExpression = (PowerExpression)theEObject;
        T result = casePowerExpression(powerExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.UNARY_EXPRESSION:
      {
        UnaryExpression unaryExpression = (UnaryExpression)theEObject;
        T result = caseUnaryExpression(unaryExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.PRIMARY:
      {
        Primary primary = (Primary)theEObject;
        T result = casePrimary(primary);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.VECTOR:
      {
        Vector vector = (Vector)theEObject;
        T result = caseVector(vector);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.FULLY_QUALIFIED_SYMBOL_NAME:
      {
        FullyQualifiedSymbolName fullyQualifiedSymbolName = (FullyQualifiedSymbolName)theEObject;
        T result = caseFullyQualifiedSymbolName(fullyQualifiedSymbolName);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.FULLY_QUALIFIED_ARGUMENT_NAME:
      {
        FullyQualifiedArgumentName fullyQualifiedArgumentName = (FullyQualifiedArgumentName)theEObject;
        T result = caseFullyQualifiedArgumentName(fullyQualifiedArgumentName);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MdlPackage.OBJECT_NAME:
      {
        ObjectName objectName = (ObjectName)theEObject;
        T result = caseObjectName(objectName);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Mcl</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mcl</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMcl(Mcl object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Mcl Object</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mcl Object</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMclObject(MclObject object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model Object</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Object</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModelObject(ModelObject object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Parameter Object</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Parameter Object</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseParameterObject(ParameterObject object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Data Object</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Data Object</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDataObject(DataObject object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Task Object</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Task Object</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTaskObject(TaskObject object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>TEL Object</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>TEL Object</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTELObject(TELObject object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model Object Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Object Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModelObjectBlock(ModelObjectBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Individual Variables Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Individual Variables Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIndividualVariablesBlock(IndividualVariablesBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model Prediction Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Prediction Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModelPredictionBlock(ModelPredictionBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Random Variable Definition Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Random Variable Definition Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRandomVariableDefinitionBlock(RandomVariableDefinitionBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Input Variables Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Input Variables Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseInputVariablesBlock(InputVariablesBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Structural Parameters Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Structural Parameters Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStructuralParametersBlock(StructuralParametersBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Variability Parameters Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Variability Parameters Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVariabilityParametersBlock(VariabilityParametersBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Output Variables Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Output Variables Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOutputVariablesBlock(OutputVariablesBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Group Variables Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Group Variables Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGroupVariablesBlock(GroupVariablesBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Observation Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Observation Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseObservationBlock(ObservationBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Estimation Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Estimation Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEstimationBlock(EstimationBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Simulation Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Simulation Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSimulationBlock(SimulationBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Parameter Object Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Parameter Object Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseParameterObjectBlock(ParameterObjectBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Structural Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Structural Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStructuralBlock(StructuralBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Variability Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Variability Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVariabilityBlock(VariabilityBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Prior Parameters Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Prior Parameters Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePriorParametersBlock(PriorParametersBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Data Object Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Data Object Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDataObjectBlock(DataObjectBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Header Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Header Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseHeaderBlock(HeaderBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>File Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>File Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFileBlock(FileBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Task Object Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Task Object Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTaskObjectBlock(TaskObjectBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Parameter Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Parameter Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseParameterBlock(ParameterBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Data Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Data Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDataBlock(DataBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Data Block Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Data Block Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDataBlockStatement(DataBlockStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ignore List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ignore List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIgnoreList(IgnoreList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Accept List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Accept List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAcceptList(AcceptList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Drop List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Drop List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDropList(DropList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModelBlock(ModelBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model Block Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Block Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModelBlockStatement(ModelBlockStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Add List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Add List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAddList(AddList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Remove List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Remove List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRemoveList(RemoveList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Symbol List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Symbol List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSymbolList(SymbolList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model Prediction Block Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Prediction Block Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModelPredictionBlockStatement(ModelPredictionBlockStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Library Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Library Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLibraryBlock(LibraryBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ode Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ode Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOdeBlock(OdeBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Group Variables Block Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Group Variables Block Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGroupVariablesBlockStatement(GroupVariablesBlockStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Mixture Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Mixture Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMixtureBlock(MixtureBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Variability Block Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Variability Block Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVariabilityBlockStatement(VariabilityBlockStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Block Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Block Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBlockBlock(BlockBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Diag Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Diag Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDiagBlock(DiagBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Same Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Same Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSameBlock(SameBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>File Block Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>File Block Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFileBlockStatement(FileBlockStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Inline Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Inline Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseInlineBlock(InlineBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Design Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Design Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDesignBlock(DesignBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Design Block Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Design Block Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDesignBlockStatement(DesignBlockStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Variable List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Variable List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVariableList(VariableList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>RScript Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>RScript Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRScriptBlock(RScriptBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>RScript Block Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>RScript Block Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRScriptBlockStatement(RScriptBlockStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Task Function Declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Task Function Declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTaskFunctionDeclaration(TaskFunctionDeclaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Task Function Body</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Task Function Body</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTaskFunctionBody(TaskFunctionBody object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Task Function Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Task Function Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTaskFunctionBlock(TaskFunctionBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Estimate Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Estimate Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEstimateTask(EstimateTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Simulate Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Simulate Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSimulateTask(SimulateTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Execute Task</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Execute Task</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExecuteTask(ExecuteTask object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Formal Arguments</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Formal Arguments</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFormalArguments(FormalArguments object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Function Call</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Function Call</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFunctionCall(FunctionCall object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Block Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Block Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBlockStatement(BlockStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Verbatim Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Verbatim Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVerbatimBlock(VerbatimBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Target Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Target Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTargetBlock(TargetBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Symbol Modification</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Symbol Modification</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSymbolModification(SymbolModification object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Parameter Declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Parameter Declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseParameterDeclaration(ParameterDeclaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Symbol Declaration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Symbol Declaration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSymbolDeclaration(SymbolDeclaration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Enum Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Enum Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEnumType(EnumType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Missing</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Missing</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMissing(Missing object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Likelyhood</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Likelyhood</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLikelyhood(Likelyhood object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Level Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Level Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLevelType(LevelType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Categorical</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Categorical</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCategorical(Categorical object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Continuous</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Continuous</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseContinuous(Continuous object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Covariate</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Covariate</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCovariate(Covariate object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Distribution</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Distribution</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDistribution(Distribution object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Any Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Any Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAnyExpression(AnyExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExpression(Expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseList(List object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ode List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ode List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOdeList(OdeList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Random List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Random List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRandomList(RandomList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Arguments</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Arguments</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseArguments(Arguments object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Argument</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Argument</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseArgument(Argument object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Conditional Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Conditional Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConditionalStatement(ConditionalStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBlock(Block object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Par Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Par Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseParExpression(ParExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Conditional Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Conditional Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConditionalExpression(ConditionalExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Or Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Or Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOrExpression(OrExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>And Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>And Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAndExpression(AndExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Logical Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Logical Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLogicalExpression(LogicalExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Additive Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Additive Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAdditiveExpression(AdditiveExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Multiplicative Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Multiplicative Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseMultiplicativeExpression(MultiplicativeExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Power Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Power Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePowerExpression(PowerExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Unary Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unary Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUnaryExpression(UnaryExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Primary</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Primary</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePrimary(Primary object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Vector</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Vector</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVector(Vector object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Fully Qualified Symbol Name</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fully Qualified Symbol Name</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFullyQualifiedSymbolName(FullyQualifiedSymbolName object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Fully Qualified Argument Name</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Fully Qualified Argument Name</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFullyQualifiedArgumentName(FullyQualifiedArgumentName object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Object Name</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Object Name</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseObjectName(ObjectName object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //MdlSwitch
