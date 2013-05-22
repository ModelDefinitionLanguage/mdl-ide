package org.ddmore.mdl.generator;

import com.google.common.base.Objects;
import java.util.HashMap;
import org.ddmore.mdl.generator.Mdl2Nonmem;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.ConditionalExpression;
import org.ddmore.mdl.mdl.EstimateTask;
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.GroupVariablesBlock;
import org.ddmore.mdl.mdl.IndividualVariablesBlock;
import org.ddmore.mdl.mdl.InputVariablesBlock;
import org.ddmore.mdl.mdl.List;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.ModelObject;
import org.ddmore.mdl.mdl.ModelObjectBlock;
import org.ddmore.mdl.mdl.ObservationBlock;
import org.ddmore.mdl.mdl.OdeList;
import org.ddmore.mdl.mdl.ParameterDeclaration;
import org.ddmore.mdl.mdl.ParameterObject;
import org.ddmore.mdl.mdl.ParameterObjectBlock;
import org.ddmore.mdl.mdl.Primary;
import org.ddmore.mdl.mdl.RandomList;
import org.ddmore.mdl.mdl.SimulateTask;
import org.ddmore.mdl.mdl.StructuralBlock;
import org.ddmore.mdl.mdl.StructuralParametersBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.TaskFunctionBlock;
import org.ddmore.mdl.mdl.TaskFunctionBody;
import org.ddmore.mdl.mdl.TaskFunctionDeclaration;
import org.ddmore.mdl.mdl.TaskObject;
import org.ddmore.mdl.mdl.TaskObjectBlock;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;

@SuppressWarnings("all")
public class Mdl2PharmML extends Mdl2Nonmem {
  public CharSequence convertToPharmML(final Mcl m) {
    CharSequence _xblockexpression = null;
    {
      String version = "1.001";
      String date = "06.06.2013";
      String _plus = ("mdl2pharmML " + version);
      String _plus_1 = (_plus + " beta, last modification ");
      String _plus_2 = (_plus_1 + date);
      final String info = (_plus_2 + " Natallia Kokash (natallia.kokash@gmail.com)");
      boolean printIndependent = this.isIndependentVariableDefined(m);
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _print_XS_Comment = this.print_XS_Comment(info);
      _builder.append(_print_XS_Comment, "");
      _builder.newLineIfNotEmpty();
      _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      _builder.newLine();
      _builder.append("<PharmML ");
      _builder.newLine();
      _builder.append("\t");
      CharSequence _print_PharmML_NameSpaces = this.print_PharmML_NameSpaces();
      _builder.append(_print_PharmML_NameSpaces, "	");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("name=\"");
      Resource _eResource = m.eResource();
      String _fileName = this.fileName(_eResource);
      _builder.append(_fileName, "	");
      _builder.append("\"");
      _builder.newLineIfNotEmpty();
      {
        if (printIndependent) {
          _builder.append("\t");
          _builder.append("independentVar=\"t\" ");
          _builder.newLine();
        }
      }
      _builder.append("\t");
      _builder.append("writtenVersion=\"0.1\">");
      _builder.newLine();
      _builder.append("  \t\t\t");
      {
        EList<MclObject> _objects = m.getObjects();
        for(final MclObject o : _objects) {
          CharSequence _print_mdef_ModelDefinition = this.print_mdef_ModelDefinition(o);
          _builder.append(_print_mdef_ModelDefinition, "  			");
        }
      }
      _builder.newLineIfNotEmpty();
      _builder.append("</PharmML>");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence print_PharmML_NameSpaces() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ");
    _builder.newLine();
    _builder.append("xmlns=\"http://www.pharmml.org/2013/03/PharmML\"");
    _builder.newLine();
    _builder.append("xsi:schemaLocation=\"http://www.pharmml.org/2013/03/PharmML http://www.pharmml.org/2013/03/PharmML\"");
    _builder.newLine();
    _builder.append("xmlns:math=\"http://www.pharmml.org/2013/03/Maths\"");
    _builder.newLine();
    _builder.append("xmlns:ct=\"http://www.pharmml.org/2013/03/CommonTypes\"");
    _builder.newLine();
    _builder.append("xmlns:mdef=\"http://www.pharmml.org/2013/03/ModelDefinition\"");
    _builder.newLine();
    _builder.append("xmlns:mstep=\"http://www.pharmml.org/2013/03/ModellingSteps\"");
    _builder.newLine();
    _builder.append("xmlns:design=\"http://www.pharmml.org/2013/03/TrialDesign\"");
    _builder.newLine();
    _builder.append("xmlns:uncert=\"http://www.pharmml.org/2013/03/Uncertainty\"");
    _builder.newLine();
    return _builder;
  }
  
  public boolean isIndependentVariableDefined(final Mcl m) {
    EList<MclObject> _objects = m.getObjects();
    for (final MclObject obj : _objects) {
      ModelObject _modelObject = obj.getModelObject();
      boolean _notEquals = (!Objects.equal(_modelObject, null));
      if (_notEquals) {
        ModelObject _modelObject_1 = obj.getModelObject();
        EList<ModelObjectBlock> _blocks = _modelObject_1.getBlocks();
        for (final ModelObjectBlock block : _blocks) {
          InputVariablesBlock _inputVariablesBlock = block.getInputVariablesBlock();
          boolean _notEquals_1 = (!Objects.equal(_inputVariablesBlock, null));
          if (_notEquals_1) {
            InputVariablesBlock _inputVariablesBlock_1 = block.getInputVariablesBlock();
            EList<SymbolDeclaration> _variables = _inputVariablesBlock_1.getVariables();
            for (final SymbolDeclaration s : _variables) {
              AnyExpression _expression = s.getExpression();
              boolean _notEquals_2 = (!Objects.equal(_expression, null));
              if (_notEquals_2) {
                AnyExpression _expression_1 = s.getExpression();
                List _list = _expression_1.getList();
                boolean _notEquals_3 = (!Objects.equal(_list, null));
                if (_notEquals_3) {
                  AnyExpression _expression_2 = s.getExpression();
                  List _list_1 = _expression_2.getList();
                  String use = this.getAttribute(_list_1, "use");
                  boolean _equalsIgnoreCase = use.equalsIgnoreCase("idv");
                  if (_equalsIgnoreCase) {
                    return true;
                  }
                }
              }
            }
          }
        }
      }
    }
    return false;
  }
  
  public CharSequence print_mdef_ModelDefinition(final MclObject o) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ModelDefinition>");
    _builder.newLine();
    _builder.append("\t");
    {
      ParameterObject _parameterObject = o.getParameterObject();
      boolean _notEquals = (!Objects.equal(_parameterObject, null));
      if (_notEquals) {
        ParameterObject _parameterObject_1 = o.getParameterObject();
        CharSequence _print_mdef_ParameterModel = this.print_mdef_ParameterModel(_parameterObject_1);
        _builder.append(_print_mdef_ParameterModel, "	");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      ModelObject _modelObject = o.getModelObject();
      boolean _notEquals_1 = (!Objects.equal(_modelObject, null));
      if (_notEquals_1) {
        _builder.append("\t");
        ModelObject _modelObject_1 = o.getModelObject();
        CharSequence _print_mdef_StructuralModel = this.print_mdef_StructuralModel(_modelObject_1);
        _builder.append(_print_mdef_StructuralModel, "	");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        ModelObject _modelObject_2 = o.getModelObject();
        CharSequence _print_mdef_ObservationModel = this.print_mdef_ObservationModel(_modelObject_2);
        _builder.append(_print_mdef_ObservationModel, "	");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</ModelDefinition>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_mml_FunctionDefinition(final TaskFunctionDeclaration f) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  public CharSequence print_design_Individuals(final IndividualVariablesBlock block) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Individuals>");
    _builder.newLine();
    _builder.append("</Individuals>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_design_Group(final GroupVariablesBlock variables) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Group>");
    _builder.newLine();
    _builder.append("</Group>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_uncert_DistributionType(final RandomList list) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  public CharSequence print_mdef_VariabilityLevel() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<VariabilityLevel id=\"indiv\"/>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_mdef_CovariateModel() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<CovariateModel>");
    _builder.newLine();
    _builder.append("</CovariateModel>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_mdef_ParameterModel(final ParameterObject obj) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ParameterObjectBlock> _blocks = obj.getBlocks();
      for(final ParameterObjectBlock b : _blocks) {
        {
          StructuralBlock _structuralBlock = b.getStructuralBlock();
          boolean _notEquals = (!Objects.equal(_structuralBlock, null));
          if (_notEquals) {
            final String paramModel = "p1";
            _builder.newLineIfNotEmpty();
            _builder.append("<ParameterModel id=");
            _builder.append(paramModel, "");
            _builder.append(">");
            _builder.newLineIfNotEmpty();
            {
              StructuralBlock _structuralBlock_1 = b.getStructuralBlock();
              EList<ParameterDeclaration> _parameters = _structuralBlock_1.getParameters();
              for(final ParameterDeclaration st : _parameters) {
                _builder.append("\t");
                CharSequence _print_mdef_Parameter = this.print_mdef_Parameter(st);
                _builder.append(_print_mdef_Parameter, "	");
                _builder.newLineIfNotEmpty();
              }
            }
            _builder.append("</ParameterModel>");
            _builder.newLine();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence print_mdef_Parameter(final ParameterDeclaration s) {
    CharSequence _xblockexpression = null;
    {
      String name = s.getIdentifier();
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<Parameter symbId = \\\"");
      _builder.append(name, "");
      _builder.append("\\\"/>");
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence print_mdef_StructuralModel(final ModelObject obj) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ModelObjectBlock> _blocks = obj.getBlocks();
      for(final ModelObjectBlock b : _blocks) {
        {
          StructuralParametersBlock _structuralParametersBlock = b.getStructuralParametersBlock();
          boolean _notEquals = (!Objects.equal(_structuralParametersBlock, null));
          if (_notEquals) {
            _builder.append("<StructuralModel>");
            _builder.newLine();
            _builder.append("</StructuralModel>");
            _builder.newLine();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence print_mdef_ObservationModel(final ModelObject obj) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ModelObjectBlock> _blocks = obj.getBlocks();
      for(final ModelObjectBlock b : _blocks) {
        {
          ObservationBlock _observationBlock = b.getObservationBlock();
          boolean _notEquals = (!Objects.equal(_observationBlock, null));
          if (_notEquals) {
            _builder.append("<ObservationModel>");
            _builder.newLine();
            _builder.append("</ObservationModel>");
            _builder.newLine();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence print_msteps_ModelingSteps(final MclObject o) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ModellingSteps>");
    _builder.newLine();
    _builder.append("\t");
    {
      ModelObject _modelObject = o.getModelObject();
      boolean _notEquals = (!Objects.equal(_modelObject, null));
      if (_notEquals) {
        ModelObject _modelObject_1 = o.getModelObject();
        CharSequence _print_msteps_Variable = this.print_msteps_Variable(_modelObject_1);
        _builder.append(_print_msteps_Variable, "	");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    {
      TaskObject _taskObject = o.getTaskObject();
      boolean _notEquals_1 = (!Objects.equal(_taskObject, null));
      if (_notEquals_1) {
        TaskObject _taskObject_1 = o.getTaskObject();
        CharSequence _print_msteps_ModelingStepsContent = this.print_msteps_ModelingStepsContent(_taskObject_1);
        _builder.append(_print_msteps_ModelingStepsContent, "	");
      }
    }
    _builder.newLineIfNotEmpty();
    _builder.append("</ModellingSteps>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_msteps_Variable(final ModelObject obj) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Variable>");
    _builder.newLine();
    _builder.append("\t");
    CharSequence _print_ct_DataSet = this.print_ct_DataSet(obj);
    _builder.append(_print_ct_DataSet, "	");
    _builder.newLineIfNotEmpty();
    _builder.append("</Variable>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_ct_DataSet(final ModelObject obj) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<DataSet>");
    _builder.newLine();
    {
      EList<ModelObjectBlock> _blocks = obj.getBlocks();
      for(final ModelObjectBlock b : _blocks) {
        {
          InputVariablesBlock _inputVariablesBlock = b.getInputVariablesBlock();
          boolean _notEquals = (!Objects.equal(_inputVariablesBlock, null));
          if (_notEquals) {
            _builder.append("\t");
            InputVariablesBlock _inputVariablesBlock_1 = b.getInputVariablesBlock();
            CharSequence _print_ct_Definition = this.print_ct_Definition(_inputVariablesBlock_1);
            _builder.append(_print_ct_Definition, "	");
            _builder.append(" ");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    _builder.append("</DataSet>\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_ct_Definition(final InputVariablesBlock block) {
    CharSequence _xblockexpression = null;
    {
      HashMap<Object,Object> input_vars = CollectionLiterals.<Object, Object>newHashMap();
      int i = 1;
      EList<SymbolDeclaration> _variables = block.getVariables();
      for (final SymbolDeclaration v : _variables) {
        {
          final String id = v.getIdentifier();
          Object _get = input_vars.get(id);
          boolean _equals = Objects.equal(_get, null);
          if (_equals) {
            input_vars.put(id, Integer.valueOf(i));
            int _plus = (i + 1);
            i = _plus;
          }
        }
      }
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("<Definition>");
      _builder.newLine();
      _builder.append("</Definition>\t");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence print_msteps_ModelingStepsContent(final TaskObject obj) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<TaskObjectBlock> _blocks = obj.getBlocks();
      for(final TaskObjectBlock b : _blocks) {
        {
          TaskFunctionDeclaration _functionDeclaration = b.getFunctionDeclaration();
          boolean _notEquals = (!Objects.equal(_functionDeclaration, null));
          if (_notEquals) {
            TaskFunctionDeclaration _functionDeclaration_1 = b.getFunctionDeclaration();
            TaskFunctionBody _functionBody = _functionDeclaration_1.getFunctionBody();
            CharSequence _print_msteps_ModelingStepsContent = this.print_msteps_ModelingStepsContent(_functionBody);
            _builder.append(_print_msteps_ModelingStepsContent, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence print_msteps_ModelingStepsContent(final TaskFunctionBody body) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<TaskFunctionBlock> _blocks = body.getBlocks();
      for(final TaskFunctionBlock b : _blocks) {
        {
          EstimateTask _estimateBlock = b.getEstimateBlock();
          boolean _notEquals = (!Objects.equal(_estimateBlock, null));
          if (_notEquals) {
            EstimateTask _estimateBlock_1 = b.getEstimateBlock();
            CharSequence _print_msteps_SimulationStep = this.print_msteps_SimulationStep(_estimateBlock_1);
            _builder.append(_print_msteps_SimulationStep, "");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          SimulateTask _simulateBlock = b.getSimulateBlock();
          boolean _notEquals_1 = (!Objects.equal(_simulateBlock, null));
          if (_notEquals_1) {
            SimulateTask _simulateBlock_1 = b.getSimulateBlock();
            CharSequence _print_msteps_EstimationStep = this.print_msteps_EstimationStep(_simulateBlock_1);
            _builder.append(_print_msteps_EstimationStep, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence print_msteps_EstimationStep(final SimulateTask task) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<SimulationStep>");
    _builder.newLine();
    _builder.append("</SimulationStep>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_msteps_SimulationStep(final EstimateTask task) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<EstimationStep>");
    _builder.newLine();
    _builder.append("</EstimationStep>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_XS_Comment(final String text) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<!--");
    _builder.append(text, "");
    _builder.append("-->");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print_ct_AnnotationType(final String text) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Description>");
    _builder.append(text, "");
    _builder.append("</Description>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print_ct_VariableDefinitionType(final SymbolDeclaration v) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Variable ");
    String _identifier = v.getIdentifier();
    CharSequence _print_ct_SymbId = this.print_ct_SymbId(_identifier);
    _builder.append(_print_ct_SymbId, "");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    String _identifier_1 = v.getIdentifier();
    CharSequence _print_ct_AnnotationType = this.print_ct_AnnotationType(_identifier_1);
    _builder.append(_print_ct_AnnotationType, "	");
    _builder.newLineIfNotEmpty();
    {
      AnyExpression _expression = v.getExpression();
      boolean _notEquals = (!Objects.equal(_expression, null));
      if (_notEquals) {
        _builder.append("\t");
        AnyExpression _expression_1 = v.getExpression();
        CharSequence _print_ct_printRhsType = this.print_ct_printRhsType(_expression_1);
        _builder.append(_print_ct_printRhsType, "	");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</Variable>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_ct_printRhsType(final AnyExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    {
      Expression _expression = e.getExpression();
      boolean _notEquals = (!Objects.equal(_expression, null));
      if (_notEquals) {
        Expression _expression_1 = e.getExpression();
        CharSequence _print_ct_printRhsType = this.print_ct_printRhsType(_expression_1);
        _builder.append(_print_ct_printRhsType, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      List _list = e.getList();
      boolean _notEquals_1 = (!Objects.equal(_list, null));
      if (_notEquals_1) {
      }
    }
    {
      OdeList _odeList = e.getOdeList();
      boolean _notEquals_2 = (!Objects.equal(_odeList, null));
      if (_notEquals_2) {
      }
    }
    return _builder;
  }
  
  public CharSequence print_ct_printRhsType(final Expression e) {
    StringConcatenation _builder = new StringConcatenation();
    {
      ConditionalExpression _conditionalExpression = e.getConditionalExpression();
      boolean _notEquals = (!Objects.equal(_conditionalExpression, null));
      if (_notEquals) {
      }
    }
    return _builder;
  }
  
  public CharSequence print_ct_SymbId(final Primary p) {
    StringConcatenation _builder = new StringConcatenation();
    {
      FullyQualifiedSymbolName _symbol = p.getSymbol();
      boolean _notEquals = (!Objects.equal(_symbol, null));
      if (_notEquals) {
        FullyQualifiedSymbolName _symbol_1 = p.getSymbol();
        CharSequence _print_ct_SymbId = this.print_ct_SymbId(_symbol_1);
        _builder.append(_print_ct_SymbId, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print_ct_SymbId(final String name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("symbId = \"");
    _builder.append(name, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print_ct_SymbId(final FullyQualifiedSymbolName name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("symbId = \"");
    String _str = this.toStr(name);
    _builder.append(_str, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print_MathStringType(final EList<String> list) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<String>");
    _builder.newLine();
    _builder.append("</String>\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_Math_ScalarType(final Primary p) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _number = p.getNumber();
      boolean _notEquals = (!Objects.equal(_number, null));
      if (_notEquals) {
        _builder.append("<Scalar value = \"");
        String _number_1 = p.getNumber();
        _builder.append(_number_1, "");
        _builder.append("\"/>");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print_Math_FunctionCallType(final FunctionCall call) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<FunctionCall>");
    _builder.newLine();
    _builder.append("\t");
    FullyQualifiedSymbolName _identifier = call.getIdentifier();
    CharSequence _print_Math_VarType = this.print_Math_VarType(_identifier);
    _builder.append(_print_Math_VarType, "	");
    _builder.newLineIfNotEmpty();
    {
      Arguments _arguments = call.getArguments();
      EList<Argument> _arguments_1 = _arguments.getArguments();
      for(final Argument arg : _arguments_1) {
        _builder.append("\t");
        CharSequence _print_Math_FunctionArgumentType = this.print_Math_FunctionArgumentType(arg);
        _builder.append(_print_Math_FunctionArgumentType, "	");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</FunctionCall>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_Math_VarType(final FullyQualifiedSymbolName name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Var ");
    String _identifier = name.getIdentifier();
    CharSequence _print_Math_symbId = this.print_Math_symbId(_identifier);
    _builder.append(_print_Math_symbId, "");
    _builder.append("/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print_Math_FunctionArgumentType(final Argument arg) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<FunctionArgument ");
    _builder.newLine();
    _builder.append("\t");
    {
      String _identifier = arg.getIdentifier();
      boolean _notEquals = (!Objects.equal(_identifier, null));
      if (_notEquals) {
        String _identifier_1 = arg.getIdentifier();
        CharSequence _print_Math_symbId = this.print_Math_symbId(_identifier_1);
        _builder.append(_print_Math_symbId, "	");
      }
    }
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    _builder.append("</FunctionArgument>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_Math_symbId(final String str) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("symbId = \"");
    _builder.append(str, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
