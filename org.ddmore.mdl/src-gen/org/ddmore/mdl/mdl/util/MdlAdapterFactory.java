/**
 */
package org.ddmore.mdl.mdl.util;

import org.ddmore.mdl.mdl.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.ddmore.mdl.mdl.MdlPackage
 * @generated
 */
public class MdlAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static MdlPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MdlAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = MdlPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MdlSwitch<Adapter> modelSwitch =
    new MdlSwitch<Adapter>()
    {
      @Override
      public Adapter caseMcl(Mcl object)
      {
        return createMclAdapter();
      }
      @Override
      public Adapter caseMclObject(MclObject object)
      {
        return createMclObjectAdapter();
      }
      @Override
      public Adapter caseModelObject(ModelObject object)
      {
        return createModelObjectAdapter();
      }
      @Override
      public Adapter caseParameterObject(ParameterObject object)
      {
        return createParameterObjectAdapter();
      }
      @Override
      public Adapter caseDataObject(DataObject object)
      {
        return createDataObjectAdapter();
      }
      @Override
      public Adapter caseTaskObject(TaskObject object)
      {
        return createTaskObjectAdapter();
      }
      @Override
      public Adapter caseTELObject(TELObject object)
      {
        return createTELObjectAdapter();
      }
      @Override
      public Adapter caseModelObjectBlock(ModelObjectBlock object)
      {
        return createModelObjectBlockAdapter();
      }
      @Override
      public Adapter caseIndividualVariablesBlock(IndividualVariablesBlock object)
      {
        return createIndividualVariablesBlockAdapter();
      }
      @Override
      public Adapter caseModelPredictionBlock(ModelPredictionBlock object)
      {
        return createModelPredictionBlockAdapter();
      }
      @Override
      public Adapter caseRandomVariableDefinitionBlock(RandomVariableDefinitionBlock object)
      {
        return createRandomVariableDefinitionBlockAdapter();
      }
      @Override
      public Adapter caseInputVariablesBlock(InputVariablesBlock object)
      {
        return createInputVariablesBlockAdapter();
      }
      @Override
      public Adapter caseStructuralParametersBlock(StructuralParametersBlock object)
      {
        return createStructuralParametersBlockAdapter();
      }
      @Override
      public Adapter caseVariabilityParametersBlock(VariabilityParametersBlock object)
      {
        return createVariabilityParametersBlockAdapter();
      }
      @Override
      public Adapter caseOutputVariablesBlock(OutputVariablesBlock object)
      {
        return createOutputVariablesBlockAdapter();
      }
      @Override
      public Adapter caseGroupVariablesBlock(GroupVariablesBlock object)
      {
        return createGroupVariablesBlockAdapter();
      }
      @Override
      public Adapter caseObservationBlock(ObservationBlock object)
      {
        return createObservationBlockAdapter();
      }
      @Override
      public Adapter caseEstimationBlock(EstimationBlock object)
      {
        return createEstimationBlockAdapter();
      }
      @Override
      public Adapter caseSimulationBlock(SimulationBlock object)
      {
        return createSimulationBlockAdapter();
      }
      @Override
      public Adapter caseParameterObjectBlock(ParameterObjectBlock object)
      {
        return createParameterObjectBlockAdapter();
      }
      @Override
      public Adapter caseStructuralBlock(StructuralBlock object)
      {
        return createStructuralBlockAdapter();
      }
      @Override
      public Adapter caseVariabilityBlock(VariabilityBlock object)
      {
        return createVariabilityBlockAdapter();
      }
      @Override
      public Adapter casePriorParametersBlock(PriorParametersBlock object)
      {
        return createPriorParametersBlockAdapter();
      }
      @Override
      public Adapter caseDataObjectBlock(DataObjectBlock object)
      {
        return createDataObjectBlockAdapter();
      }
      @Override
      public Adapter caseHeaderBlock(HeaderBlock object)
      {
        return createHeaderBlockAdapter();
      }
      @Override
      public Adapter caseFileBlock(FileBlock object)
      {
        return createFileBlockAdapter();
      }
      @Override
      public Adapter caseTaskObjectBlock(TaskObjectBlock object)
      {
        return createTaskObjectBlockAdapter();
      }
      @Override
      public Adapter caseParameterBlock(ParameterBlock object)
      {
        return createParameterBlockAdapter();
      }
      @Override
      public Adapter caseDataBlock(DataBlock object)
      {
        return createDataBlockAdapter();
      }
      @Override
      public Adapter caseDataBlockStatement(DataBlockStatement object)
      {
        return createDataBlockStatementAdapter();
      }
      @Override
      public Adapter caseIgnoreList(IgnoreList object)
      {
        return createIgnoreListAdapter();
      }
      @Override
      public Adapter caseAcceptList(AcceptList object)
      {
        return createAcceptListAdapter();
      }
      @Override
      public Adapter caseDropList(DropList object)
      {
        return createDropListAdapter();
      }
      @Override
      public Adapter caseModelBlock(ModelBlock object)
      {
        return createModelBlockAdapter();
      }
      @Override
      public Adapter caseModelBlockStatement(ModelBlockStatement object)
      {
        return createModelBlockStatementAdapter();
      }
      @Override
      public Adapter caseAddList(AddList object)
      {
        return createAddListAdapter();
      }
      @Override
      public Adapter caseRemoveList(RemoveList object)
      {
        return createRemoveListAdapter();
      }
      @Override
      public Adapter caseSymbolList(SymbolList object)
      {
        return createSymbolListAdapter();
      }
      @Override
      public Adapter caseModelPredictionBlockStatement(ModelPredictionBlockStatement object)
      {
        return createModelPredictionBlockStatementAdapter();
      }
      @Override
      public Adapter caseLibraryBlock(LibraryBlock object)
      {
        return createLibraryBlockAdapter();
      }
      @Override
      public Adapter caseFunctionCallStatement(FunctionCallStatement object)
      {
        return createFunctionCallStatementAdapter();
      }
      @Override
      public Adapter caseOdeBlock(OdeBlock object)
      {
        return createOdeBlockAdapter();
      }
      @Override
      public Adapter caseGroupVariablesBlockStatement(GroupVariablesBlockStatement object)
      {
        return createGroupVariablesBlockStatementAdapter();
      }
      @Override
      public Adapter caseMixtureBlock(MixtureBlock object)
      {
        return createMixtureBlockAdapter();
      }
      @Override
      public Adapter caseVariabilityBlockStatement(VariabilityBlockStatement object)
      {
        return createVariabilityBlockStatementAdapter();
      }
      @Override
      public Adapter caseBlockBlock(BlockBlock object)
      {
        return createBlockBlockAdapter();
      }
      @Override
      public Adapter caseDiagBlock(DiagBlock object)
      {
        return createDiagBlockAdapter();
      }
      @Override
      public Adapter caseSameBlock(SameBlock object)
      {
        return createSameBlockAdapter();
      }
      @Override
      public Adapter caseFileBlockStatement(FileBlockStatement object)
      {
        return createFileBlockStatementAdapter();
      }
      @Override
      public Adapter caseInlineBlock(InlineBlock object)
      {
        return createInlineBlockAdapter();
      }
      @Override
      public Adapter caseDesignBlock(DesignBlock object)
      {
        return createDesignBlockAdapter();
      }
      @Override
      public Adapter caseDesignBlockStatement(DesignBlockStatement object)
      {
        return createDesignBlockStatementAdapter();
      }
      @Override
      public Adapter caseVariableList(VariableList object)
      {
        return createVariableListAdapter();
      }
      @Override
      public Adapter caseRScriptBlock(RScriptBlock object)
      {
        return createRScriptBlockAdapter();
      }
      @Override
      public Adapter caseRScriptBlockStatement(RScriptBlockStatement object)
      {
        return createRScriptBlockStatementAdapter();
      }
      @Override
      public Adapter caseTaskFunctionDeclaration(TaskFunctionDeclaration object)
      {
        return createTaskFunctionDeclarationAdapter();
      }
      @Override
      public Adapter caseTaskFunctionBody(TaskFunctionBody object)
      {
        return createTaskFunctionBodyAdapter();
      }
      @Override
      public Adapter caseTaskFunctionBlock(TaskFunctionBlock object)
      {
        return createTaskFunctionBlockAdapter();
      }
      @Override
      public Adapter caseEstimateTask(EstimateTask object)
      {
        return createEstimateTaskAdapter();
      }
      @Override
      public Adapter caseSimulateTask(SimulateTask object)
      {
        return createSimulateTaskAdapter();
      }
      @Override
      public Adapter caseExecuteTask(ExecuteTask object)
      {
        return createExecuteTaskAdapter();
      }
      @Override
      public Adapter caseFormalArguments(FormalArguments object)
      {
        return createFormalArgumentsAdapter();
      }
      @Override
      public Adapter caseFormalArgument(FormalArgument object)
      {
        return createFormalArgumentAdapter();
      }
      @Override
      public Adapter caseFunctionCall(FunctionCall object)
      {
        return createFunctionCallAdapter();
      }
      @Override
      public Adapter caseBlockStatement(BlockStatement object)
      {
        return createBlockStatementAdapter();
      }
      @Override
      public Adapter caseTargetBlock(TargetBlock object)
      {
        return createTargetBlockAdapter();
      }
      @Override
      public Adapter caseImportBlock(ImportBlock object)
      {
        return createImportBlockAdapter();
      }
      @Override
      public Adapter caseImportedFunction(ImportedFunction object)
      {
        return createImportedFunctionAdapter();
      }
      @Override
      public Adapter caseSymbolModification(SymbolModification object)
      {
        return createSymbolModificationAdapter();
      }
      @Override
      public Adapter caseParameterDeclaration(ParameterDeclaration object)
      {
        return createParameterDeclarationAdapter();
      }
      @Override
      public Adapter caseSymbolDeclaration(SymbolDeclaration object)
      {
        return createSymbolDeclarationAdapter();
      }
      @Override
      public Adapter caseEnumType(EnumType object)
      {
        return createEnumTypeAdapter();
      }
      @Override
      public Adapter caseMissing(Missing object)
      {
        return createMissingAdapter();
      }
      @Override
      public Adapter caseLikelyhood(Likelyhood object)
      {
        return createLikelyhoodAdapter();
      }
      @Override
      public Adapter caseLevelType(LevelType object)
      {
        return createLevelTypeAdapter();
      }
      @Override
      public Adapter caseCategorical(Categorical object)
      {
        return createCategoricalAdapter();
      }
      @Override
      public Adapter caseContinuous(Continuous object)
      {
        return createContinuousAdapter();
      }
      @Override
      public Adapter caseCovariate(Covariate object)
      {
        return createCovariateAdapter();
      }
      @Override
      public Adapter caseDistribution(Distribution object)
      {
        return createDistributionAdapter();
      }
      @Override
      public Adapter caseAnyExpression(AnyExpression object)
      {
        return createAnyExpressionAdapter();
      }
      @Override
      public Adapter caseExpression(Expression object)
      {
        return createExpressionAdapter();
      }
      @Override
      public Adapter caseList(List object)
      {
        return createListAdapter();
      }
      @Override
      public Adapter caseOdeList(OdeList object)
      {
        return createOdeListAdapter();
      }
      @Override
      public Adapter caseRandomList(RandomList object)
      {
        return createRandomListAdapter();
      }
      @Override
      public Adapter caseArguments(Arguments object)
      {
        return createArgumentsAdapter();
      }
      @Override
      public Adapter caseArgument(Argument object)
      {
        return createArgumentAdapter();
      }
      @Override
      public Adapter caseConditionalStatement(ConditionalStatement object)
      {
        return createConditionalStatementAdapter();
      }
      @Override
      public Adapter caseBlock(Block object)
      {
        return createBlockAdapter();
      }
      @Override
      public Adapter caseParExpression(ParExpression object)
      {
        return createParExpressionAdapter();
      }
      @Override
      public Adapter caseConditionalExpression(ConditionalExpression object)
      {
        return createConditionalExpressionAdapter();
      }
      @Override
      public Adapter caseOrExpression(OrExpression object)
      {
        return createOrExpressionAdapter();
      }
      @Override
      public Adapter caseAndExpression(AndExpression object)
      {
        return createAndExpressionAdapter();
      }
      @Override
      public Adapter caseLogicalExpression(LogicalExpression object)
      {
        return createLogicalExpressionAdapter();
      }
      @Override
      public Adapter caseAdditiveExpression(AdditiveExpression object)
      {
        return createAdditiveExpressionAdapter();
      }
      @Override
      public Adapter caseMultiplicativeExpression(MultiplicativeExpression object)
      {
        return createMultiplicativeExpressionAdapter();
      }
      @Override
      public Adapter casePowerExpression(PowerExpression object)
      {
        return createPowerExpressionAdapter();
      }
      @Override
      public Adapter caseUnaryExpression(UnaryExpression object)
      {
        return createUnaryExpressionAdapter();
      }
      @Override
      public Adapter casePrimary(Primary object)
      {
        return createPrimaryAdapter();
      }
      @Override
      public Adapter caseVector(Vector object)
      {
        return createVectorAdapter();
      }
      @Override
      public Adapter caseFullyQualifiedSymbolName(FullyQualifiedSymbolName object)
      {
        return createFullyQualifiedSymbolNameAdapter();
      }
      @Override
      public Adapter caseFullyQualifiedArgumentName(FullyQualifiedArgumentName object)
      {
        return createFullyQualifiedArgumentNameAdapter();
      }
      @Override
      public Adapter caseSelector(Selector object)
      {
        return createSelectorAdapter();
      }
      @Override
      public Adapter caseObjectName(ObjectName object)
      {
        return createObjectNameAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.Mcl <em>Mcl</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.Mcl
   * @generated
   */
  public Adapter createMclAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.MclObject <em>Mcl Object</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.MclObject
   * @generated
   */
  public Adapter createMclObjectAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ModelObject <em>Model Object</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ModelObject
   * @generated
   */
  public Adapter createModelObjectAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ParameterObject <em>Parameter Object</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ParameterObject
   * @generated
   */
  public Adapter createParameterObjectAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.DataObject <em>Data Object</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.DataObject
   * @generated
   */
  public Adapter createDataObjectAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.TaskObject <em>Task Object</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.TaskObject
   * @generated
   */
  public Adapter createTaskObjectAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.TELObject <em>TEL Object</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.TELObject
   * @generated
   */
  public Adapter createTELObjectAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ModelObjectBlock <em>Model Object Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ModelObjectBlock
   * @generated
   */
  public Adapter createModelObjectBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.IndividualVariablesBlock <em>Individual Variables Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.IndividualVariablesBlock
   * @generated
   */
  public Adapter createIndividualVariablesBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ModelPredictionBlock <em>Model Prediction Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ModelPredictionBlock
   * @generated
   */
  public Adapter createModelPredictionBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.RandomVariableDefinitionBlock <em>Random Variable Definition Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.RandomVariableDefinitionBlock
   * @generated
   */
  public Adapter createRandomVariableDefinitionBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.InputVariablesBlock <em>Input Variables Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.InputVariablesBlock
   * @generated
   */
  public Adapter createInputVariablesBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.StructuralParametersBlock <em>Structural Parameters Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.StructuralParametersBlock
   * @generated
   */
  public Adapter createStructuralParametersBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.VariabilityParametersBlock <em>Variability Parameters Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.VariabilityParametersBlock
   * @generated
   */
  public Adapter createVariabilityParametersBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.OutputVariablesBlock <em>Output Variables Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.OutputVariablesBlock
   * @generated
   */
  public Adapter createOutputVariablesBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.GroupVariablesBlock <em>Group Variables Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.GroupVariablesBlock
   * @generated
   */
  public Adapter createGroupVariablesBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ObservationBlock <em>Observation Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ObservationBlock
   * @generated
   */
  public Adapter createObservationBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.EstimationBlock <em>Estimation Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.EstimationBlock
   * @generated
   */
  public Adapter createEstimationBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.SimulationBlock <em>Simulation Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.SimulationBlock
   * @generated
   */
  public Adapter createSimulationBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ParameterObjectBlock <em>Parameter Object Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ParameterObjectBlock
   * @generated
   */
  public Adapter createParameterObjectBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.StructuralBlock <em>Structural Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.StructuralBlock
   * @generated
   */
  public Adapter createStructuralBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.VariabilityBlock <em>Variability Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.VariabilityBlock
   * @generated
   */
  public Adapter createVariabilityBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.PriorParametersBlock <em>Prior Parameters Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.PriorParametersBlock
   * @generated
   */
  public Adapter createPriorParametersBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.DataObjectBlock <em>Data Object Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.DataObjectBlock
   * @generated
   */
  public Adapter createDataObjectBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.HeaderBlock <em>Header Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.HeaderBlock
   * @generated
   */
  public Adapter createHeaderBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.FileBlock <em>File Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.FileBlock
   * @generated
   */
  public Adapter createFileBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.TaskObjectBlock <em>Task Object Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.TaskObjectBlock
   * @generated
   */
  public Adapter createTaskObjectBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ParameterBlock <em>Parameter Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ParameterBlock
   * @generated
   */
  public Adapter createParameterBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.DataBlock <em>Data Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.DataBlock
   * @generated
   */
  public Adapter createDataBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.DataBlockStatement <em>Data Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.DataBlockStatement
   * @generated
   */
  public Adapter createDataBlockStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.IgnoreList <em>Ignore List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.IgnoreList
   * @generated
   */
  public Adapter createIgnoreListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.AcceptList <em>Accept List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.AcceptList
   * @generated
   */
  public Adapter createAcceptListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.DropList <em>Drop List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.DropList
   * @generated
   */
  public Adapter createDropListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ModelBlock <em>Model Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ModelBlock
   * @generated
   */
  public Adapter createModelBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ModelBlockStatement <em>Model Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ModelBlockStatement
   * @generated
   */
  public Adapter createModelBlockStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.AddList <em>Add List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.AddList
   * @generated
   */
  public Adapter createAddListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.RemoveList <em>Remove List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.RemoveList
   * @generated
   */
  public Adapter createRemoveListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.SymbolList <em>Symbol List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.SymbolList
   * @generated
   */
  public Adapter createSymbolListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ModelPredictionBlockStatement <em>Model Prediction Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ModelPredictionBlockStatement
   * @generated
   */
  public Adapter createModelPredictionBlockStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.LibraryBlock <em>Library Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.LibraryBlock
   * @generated
   */
  public Adapter createLibraryBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.FunctionCallStatement <em>Function Call Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.FunctionCallStatement
   * @generated
   */
  public Adapter createFunctionCallStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.OdeBlock <em>Ode Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.OdeBlock
   * @generated
   */
  public Adapter createOdeBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.GroupVariablesBlockStatement <em>Group Variables Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.GroupVariablesBlockStatement
   * @generated
   */
  public Adapter createGroupVariablesBlockStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.MixtureBlock <em>Mixture Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.MixtureBlock
   * @generated
   */
  public Adapter createMixtureBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.VariabilityBlockStatement <em>Variability Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.VariabilityBlockStatement
   * @generated
   */
  public Adapter createVariabilityBlockStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.BlockBlock <em>Block Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.BlockBlock
   * @generated
   */
  public Adapter createBlockBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.DiagBlock <em>Diag Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.DiagBlock
   * @generated
   */
  public Adapter createDiagBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.SameBlock <em>Same Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.SameBlock
   * @generated
   */
  public Adapter createSameBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.FileBlockStatement <em>File Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.FileBlockStatement
   * @generated
   */
  public Adapter createFileBlockStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.InlineBlock <em>Inline Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.InlineBlock
   * @generated
   */
  public Adapter createInlineBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.DesignBlock <em>Design Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.DesignBlock
   * @generated
   */
  public Adapter createDesignBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.DesignBlockStatement <em>Design Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.DesignBlockStatement
   * @generated
   */
  public Adapter createDesignBlockStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.VariableList <em>Variable List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.VariableList
   * @generated
   */
  public Adapter createVariableListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.RScriptBlock <em>RScript Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.RScriptBlock
   * @generated
   */
  public Adapter createRScriptBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.RScriptBlockStatement <em>RScript Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.RScriptBlockStatement
   * @generated
   */
  public Adapter createRScriptBlockStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.TaskFunctionDeclaration <em>Task Function Declaration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.TaskFunctionDeclaration
   * @generated
   */
  public Adapter createTaskFunctionDeclarationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.TaskFunctionBody <em>Task Function Body</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.TaskFunctionBody
   * @generated
   */
  public Adapter createTaskFunctionBodyAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.TaskFunctionBlock <em>Task Function Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.TaskFunctionBlock
   * @generated
   */
  public Adapter createTaskFunctionBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.EstimateTask <em>Estimate Task</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.EstimateTask
   * @generated
   */
  public Adapter createEstimateTaskAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.SimulateTask <em>Simulate Task</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.SimulateTask
   * @generated
   */
  public Adapter createSimulateTaskAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ExecuteTask <em>Execute Task</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ExecuteTask
   * @generated
   */
  public Adapter createExecuteTaskAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.FormalArguments <em>Formal Arguments</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.FormalArguments
   * @generated
   */
  public Adapter createFormalArgumentsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.FormalArgument <em>Formal Argument</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.FormalArgument
   * @generated
   */
  public Adapter createFormalArgumentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.FunctionCall <em>Function Call</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.FunctionCall
   * @generated
   */
  public Adapter createFunctionCallAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.BlockStatement <em>Block Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.BlockStatement
   * @generated
   */
  public Adapter createBlockStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.TargetBlock <em>Target Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.TargetBlock
   * @generated
   */
  public Adapter createTargetBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ImportBlock <em>Import Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ImportBlock
   * @generated
   */
  public Adapter createImportBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ImportedFunction <em>Imported Function</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ImportedFunction
   * @generated
   */
  public Adapter createImportedFunctionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.SymbolModification <em>Symbol Modification</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.SymbolModification
   * @generated
   */
  public Adapter createSymbolModificationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ParameterDeclaration <em>Parameter Declaration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ParameterDeclaration
   * @generated
   */
  public Adapter createParameterDeclarationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.SymbolDeclaration <em>Symbol Declaration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.SymbolDeclaration
   * @generated
   */
  public Adapter createSymbolDeclarationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.EnumType <em>Enum Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.EnumType
   * @generated
   */
  public Adapter createEnumTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.Missing <em>Missing</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.Missing
   * @generated
   */
  public Adapter createMissingAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.Likelyhood <em>Likelyhood</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.Likelyhood
   * @generated
   */
  public Adapter createLikelyhoodAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.LevelType <em>Level Type</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.LevelType
   * @generated
   */
  public Adapter createLevelTypeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.Categorical <em>Categorical</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.Categorical
   * @generated
   */
  public Adapter createCategoricalAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.Continuous <em>Continuous</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.Continuous
   * @generated
   */
  public Adapter createContinuousAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.Covariate <em>Covariate</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.Covariate
   * @generated
   */
  public Adapter createCovariateAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.Distribution <em>Distribution</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.Distribution
   * @generated
   */
  public Adapter createDistributionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.AnyExpression <em>Any Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.AnyExpression
   * @generated
   */
  public Adapter createAnyExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.Expression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.Expression
   * @generated
   */
  public Adapter createExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.List <em>List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.List
   * @generated
   */
  public Adapter createListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.OdeList <em>Ode List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.OdeList
   * @generated
   */
  public Adapter createOdeListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.RandomList <em>Random List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.RandomList
   * @generated
   */
  public Adapter createRandomListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.Arguments <em>Arguments</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.Arguments
   * @generated
   */
  public Adapter createArgumentsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.Argument <em>Argument</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.Argument
   * @generated
   */
  public Adapter createArgumentAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ConditionalStatement <em>Conditional Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ConditionalStatement
   * @generated
   */
  public Adapter createConditionalStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.Block <em>Block</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.Block
   * @generated
   */
  public Adapter createBlockAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ParExpression <em>Par Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ParExpression
   * @generated
   */
  public Adapter createParExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ConditionalExpression <em>Conditional Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ConditionalExpression
   * @generated
   */
  public Adapter createConditionalExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.OrExpression <em>Or Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.OrExpression
   * @generated
   */
  public Adapter createOrExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.AndExpression <em>And Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.AndExpression
   * @generated
   */
  public Adapter createAndExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.LogicalExpression <em>Logical Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.LogicalExpression
   * @generated
   */
  public Adapter createLogicalExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.AdditiveExpression <em>Additive Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.AdditiveExpression
   * @generated
   */
  public Adapter createAdditiveExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.MultiplicativeExpression <em>Multiplicative Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.MultiplicativeExpression
   * @generated
   */
  public Adapter createMultiplicativeExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.PowerExpression <em>Power Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.PowerExpression
   * @generated
   */
  public Adapter createPowerExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.UnaryExpression <em>Unary Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.UnaryExpression
   * @generated
   */
  public Adapter createUnaryExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.Primary <em>Primary</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.Primary
   * @generated
   */
  public Adapter createPrimaryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.Vector <em>Vector</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.Vector
   * @generated
   */
  public Adapter createVectorAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.FullyQualifiedSymbolName <em>Fully Qualified Symbol Name</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.FullyQualifiedSymbolName
   * @generated
   */
  public Adapter createFullyQualifiedSymbolNameAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.FullyQualifiedArgumentName <em>Fully Qualified Argument Name</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.FullyQualifiedArgumentName
   * @generated
   */
  public Adapter createFullyQualifiedArgumentNameAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.Selector <em>Selector</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.Selector
   * @generated
   */
  public Adapter createSelectorAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.ddmore.mdl.mdl.ObjectName <em>Object Name</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.ddmore.mdl.mdl.ObjectName
   * @generated
   */
  public Adapter createObjectNameAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //MdlAdapterFactory
