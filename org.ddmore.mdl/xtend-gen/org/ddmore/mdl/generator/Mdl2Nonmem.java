package org.ddmore.mdl.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import org.ddmore.mdl.generator.MdlPrinting;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.Block;
import org.ddmore.mdl.mdl.BlockBlock;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.ConditionalStatement;
import org.ddmore.mdl.mdl.DataBlock;
import org.ddmore.mdl.mdl.DataBlockStatement;
import org.ddmore.mdl.mdl.DataObject;
import org.ddmore.mdl.mdl.DataObjectBlock;
import org.ddmore.mdl.mdl.DiagBlock;
import org.ddmore.mdl.mdl.EstimateTask;
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.FileBlock;
import org.ddmore.mdl.mdl.FileBlockStatement;
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.FunctionCallStatement;
import org.ddmore.mdl.mdl.GroupVariablesBlock;
import org.ddmore.mdl.mdl.GroupVariablesBlockStatement;
import org.ddmore.mdl.mdl.HeaderBlock;
import org.ddmore.mdl.mdl.IgnoreList;
import org.ddmore.mdl.mdl.ImportBlock;
import org.ddmore.mdl.mdl.ImportedFunction;
import org.ddmore.mdl.mdl.IndividualVariablesBlock;
import org.ddmore.mdl.mdl.LibraryBlock;
import org.ddmore.mdl.mdl.List;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.MixtureBlock;
import org.ddmore.mdl.mdl.ModelBlock;
import org.ddmore.mdl.mdl.ModelBlockStatement;
import org.ddmore.mdl.mdl.ModelObject;
import org.ddmore.mdl.mdl.ModelObjectBlock;
import org.ddmore.mdl.mdl.ModelPredictionBlock;
import org.ddmore.mdl.mdl.ModelPredictionBlockStatement;
import org.ddmore.mdl.mdl.ObjectName;
import org.ddmore.mdl.mdl.ObservationBlock;
import org.ddmore.mdl.mdl.OdeBlock;
import org.ddmore.mdl.mdl.OdeList;
import org.ddmore.mdl.mdl.OrExpression;
import org.ddmore.mdl.mdl.OutputVariablesBlock;
import org.ddmore.mdl.mdl.ParExpression;
import org.ddmore.mdl.mdl.ParameterDeclaration;
import org.ddmore.mdl.mdl.ParameterObject;
import org.ddmore.mdl.mdl.ParameterObjectBlock;
import org.ddmore.mdl.mdl.RandomList;
import org.ddmore.mdl.mdl.RandomVariableDefinitionBlock;
import org.ddmore.mdl.mdl.SimulateTask;
import org.ddmore.mdl.mdl.StructuralBlock;
import org.ddmore.mdl.mdl.StructuralParametersBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.SymbolModification;
import org.ddmore.mdl.mdl.TargetBlock;
import org.ddmore.mdl.mdl.TaskFunctionBlock;
import org.ddmore.mdl.mdl.TaskFunctionBody;
import org.ddmore.mdl.mdl.TaskFunctionDeclaration;
import org.ddmore.mdl.mdl.TaskObject;
import org.ddmore.mdl.mdl.TaskObjectBlock;
import org.ddmore.mdl.mdl.VariabilityBlock;
import org.ddmore.mdl.mdl.VariabilityBlockStatement;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function0;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class Mdl2Nonmem extends MdlPrinting {
  private String tol = "";
  
  private String file = "";
  
  private HashMap<Object,Object> eta_vars = new Function0<HashMap<Object,Object>>() {
    public HashMap<Object,Object> apply() {
      HashMap<Object,Object> _newHashMap = CollectionLiterals.<Object, Object>newHashMap();
      return _newHashMap;
    }
  }.apply();
  
  private HashMap<Object,Object> eps_vars = new Function0<HashMap<Object,Object>>() {
    public HashMap<Object,Object> apply() {
      HashMap<Object,Object> _newHashMap = CollectionLiterals.<Object, Object>newHashMap();
      return _newHashMap;
    }
  }.apply();
  
  private HashMap<Object,Object> theta_vars = new Function0<HashMap<Object,Object>>() {
    public HashMap<Object,Object> apply() {
      HashMap<Object,Object> _newHashMap = CollectionLiterals.<Object, Object>newHashMap();
      return _newHashMap;
    }
  }.apply();
  
  private HashMap<Object,Object> dadt_vars = new Function0<HashMap<Object,Object>>() {
    public HashMap<Object,Object> apply() {
      HashMap<Object,Object> _newHashMap = CollectionLiterals.<Object, Object>newHashMap();
      return _newHashMap;
    }
  }.apply();
  
  private HashMap<Object,Object> init_vars = new Function0<HashMap<Object,Object>>() {
    public HashMap<Object,Object> apply() {
      HashMap<Object,Object> _newHashMap = CollectionLiterals.<Object, Object>newHashMap();
      return _newHashMap;
    }
  }.apply();
  
  public CharSequence convertToNonmem(final Mcl m) {
    CharSequence _xblockexpression = null;
    {
      this.init_vars.clear();
      this.dadt_vars.clear();
      this.theta_vars.clear();
      this.eta_vars.clear();
      this.eps_vars.clear();
      EList<MclObject> _objects = m.getObjects();
      for (final MclObject o : _objects) {
        {
          this.prepareCollections(o);
          TaskObject _taskObject = o.getTaskObject();
          boolean _notEquals = (!Objects.equal(_taskObject, null));
          if (_notEquals) {
            TaskObject _taskObject_1 = o.getTaskObject();
            String _tOL = this.getTOL(_taskObject_1);
            this.tol = _tOL;
            TaskObject _taskObject_2 = o.getTaskObject();
            ObjectName _identifier = _taskObject_2.getIdentifier();
            String _name = _identifier.getName();
            String _plus = (_name + ".fit");
            this.file = _plus;
          }
        }
      }
      this.prepareExternals(m);
      String version = "1.008";
      String date = "02.06.2013";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(";mdl2nt ");
      _builder.append(version, "");
      _builder.append(" beta, last modification ");
      _builder.append(date, "");
      _builder.append(", Natallia Kokash (natallia.kokash@gmail.com)  ");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.newLine();
      _builder.append("$PROB ");
      String _fileNameUpperCase = this.fileNameUpperCase(m);
      _builder.append(_fileNameUpperCase, "");
      _builder.newLineIfNotEmpty();
      String _externalCode = this.getExternalCode("$PROBLEM");
      _builder.append(_externalCode, "");
      _builder.newLineIfNotEmpty();
      String _externalCode_1 = this.getExternalCode("$PROB");
      _builder.append(_externalCode_1, "");
      _builder.newLineIfNotEmpty();
      {
        EList<MclObject> _objects_1 = m.getObjects();
        for(final MclObject o_1 : _objects_1) {
          {
            DataObject _dataObject = o_1.getDataObject();
            boolean _notEquals = (!Objects.equal(_dataObject, null));
            if (_notEquals) {
              DataObject _dataObject_1 = o_1.getDataObject();
              CharSequence _printINPUT_DATA = this.printINPUT_DATA(_dataObject_1);
              _builder.append(_printINPUT_DATA, "");
            }
          }
          _builder.newLineIfNotEmpty();
        }
      }
      {
        EList<MclObject> _objects_2 = m.getObjects();
        for(final MclObject o_2 : _objects_2) {
          _builder.append("  \t\t");
          {
            TaskObject _taskObject = o_2.getTaskObject();
            boolean _notEquals_1 = (!Objects.equal(_taskObject, null));
            if (_notEquals_1) {
              _builder.append(" ");
              TaskObject _taskObject_1 = o_2.getTaskObject();
              CharSequence _printIgnoreStatements = this.printIgnoreStatements(_taskObject_1);
              _builder.append(_printIgnoreStatements, "  		");
            }
          }
          _builder.newLineIfNotEmpty();
        }
      }
      {
        EList<MclObject> _objects_3 = m.getObjects();
        for(final MclObject o_3 : _objects_3) {
          {
            ModelObject _modelObject = o_3.getModelObject();
            boolean _notEquals_2 = (!Objects.equal(_modelObject, null));
            if (_notEquals_2) {
              ModelObject _modelObject_1 = o_3.getModelObject();
              CharSequence _printSUBR_MODEL_PK_ERROR_PRES_DES = this.printSUBR_MODEL_PK_ERROR_PRES_DES(_modelObject_1);
              _builder.append(_printSUBR_MODEL_PK_ERROR_PRES_DES, "");
            }
          }
          _builder.newLineIfNotEmpty();
        }
      }
      {
        EList<MclObject> _objects_4 = m.getObjects();
        for(final MclObject o_4 : _objects_4) {
          {
            ParameterObject _parameterObject = o_4.getParameterObject();
            boolean _notEquals_3 = (!Objects.equal(_parameterObject, null));
            if (_notEquals_3) {
              ParameterObject _parameterObject_1 = o_4.getParameterObject();
              CharSequence _printTHETA_OMEGA_SIGMA = this.printTHETA_OMEGA_SIGMA(_parameterObject_1);
              _builder.append(_printTHETA_OMEGA_SIGMA, "");
            }
          }
          _builder.newLineIfNotEmpty();
        }
      }
      {
        EList<MclObject> _objects_5 = m.getObjects();
        for(final MclObject o_5 : _objects_5) {
          {
            TaskObject _taskObject_2 = o_5.getTaskObject();
            boolean _notEquals_4 = (!Objects.equal(_taskObject_2, null));
            if (_notEquals_4) {
              TaskObject _taskObject_3 = o_5.getTaskObject();
              CharSequence _printFunctions = this.printFunctions(_taskObject_3);
              _builder.append(_printFunctions, "");
            }
          }
          _builder.newLineIfNotEmpty();
        }
      }
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public void prepareExternalFunctions(final ImportBlock b, final String objName) {
    EList<ImportedFunction> _functions = b.getFunctions();
    for (final ImportedFunction f : _functions) {
      {
        HashMap<String,String> _hashMap = new HashMap<String,String>();
        HashMap<String,String> args = _hashMap;
        List _list = f.getList();
        String target = this.getAttribute(_list, "target");
        boolean _notEquals = (!Objects.equal(target, null));
        if (_notEquals) {
          boolean _equalsIgnoreCase = target.equalsIgnoreCase("NMTRAN_CODE");
          if (_equalsIgnoreCase) {
            List _list_1 = f.getList();
            Arguments _arguments = _list_1.getArguments();
            EList<Argument> _arguments_1 = _arguments.getArguments();
            for (final Argument arg : _arguments_1) {
              String _identifier = arg.getIdentifier();
              boolean _notEquals_1 = (!Objects.equal(_identifier, null));
              if (_notEquals_1) {
                String _identifier_1 = arg.getIdentifier();
                AnyExpression _expression = arg.getExpression();
                String _str = this.toStr(_expression);
                args.put(_identifier_1, _str);
              }
            }
            String _plus = (objName + "$");
            String _identifier_2 = f.getIdentifier();
            String _plus_1 = (_plus + _identifier_2);
            this.externalFunctions.put(_plus_1, args);
          }
        }
      }
    }
  }
  
  public void prepareExternalCode(final TargetBlock b) {
    Arguments _arguments = b.getArguments();
    final String target = this.selectAttribute(_arguments, "target");
    boolean _notEquals = (!Objects.equal(target, null));
    if (_notEquals) {
      boolean _equalsIgnoreCase = target.equalsIgnoreCase("NMTRAN_CODE");
      if (_equalsIgnoreCase) {
        Arguments _arguments_1 = b.getArguments();
        final String location = this.selectAttribute(_arguments_1, "location");
        ArrayList<String> codeSnippets = this.externalCode.get(location);
        boolean _equals = Objects.equal(codeSnippets, null);
        if (_equals) {
          ArrayList<String> _arrayList = new ArrayList<String>();
          codeSnippets = _arrayList;
        }
        String _externalCodeToStr = this.externalCodeToStr(b);
        codeSnippets.add(_externalCodeToStr);
        this.externalCode.put(location, codeSnippets);
      }
    }
  }
  
  public void prepareCollections(final MclObject o) {
    ModelObject _modelObject = o.getModelObject();
    boolean _notEquals = (!Objects.equal(_modelObject, null));
    if (_notEquals) {
      ModelObject _modelObject_1 = o.getModelObject();
      this.setRandomVariables(_modelObject_1);
      ModelObject _modelObject_2 = o.getModelObject();
      this.setStructuralParameters(_modelObject_2);
      ModelObject _modelObject_3 = o.getModelObject();
      this.setModelPredictionVariables(_modelObject_3);
      ModelObject _modelObject_4 = o.getModelObject();
      this.setInitialConditions(_modelObject_4);
    }
  }
  
  public void setInitialConditions(final ModelObject o) {
    int i = 1;
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock b : _blocks) {
      ModelPredictionBlock _modelPredictionBlock = b.getModelPredictionBlock();
      boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
      if (_notEquals) {
        ModelPredictionBlock _modelPredictionBlock_1 = b.getModelPredictionBlock();
        EList<ModelPredictionBlockStatement> _statements = _modelPredictionBlock_1.getStatements();
        for (final ModelPredictionBlockStatement s : _statements) {
          OdeBlock _odeBlock = s.getOdeBlock();
          boolean _notEquals_1 = (!Objects.equal(_odeBlock, null));
          if (_notEquals_1) {
            OdeBlock _odeBlock_1 = s.getOdeBlock();
            EList<BlockStatement> _statements_1 = _odeBlock_1.getStatements();
            for (final BlockStatement ss : _statements_1) {
              SymbolDeclaration _symbol = ss.getSymbol();
              boolean _notEquals_2 = (!Objects.equal(_symbol, null));
              if (_notEquals_2) {
                SymbolDeclaration _symbol_1 = ss.getSymbol();
                AnyExpression _expression = _symbol_1.getExpression();
                boolean _notEquals_3 = (!Objects.equal(_expression, null));
                if (_notEquals_3) {
                  SymbolDeclaration _symbol_2 = ss.getSymbol();
                  AnyExpression _expression_1 = _symbol_2.getExpression();
                  OdeList _odeList = _expression_1.getOdeList();
                  boolean _notEquals_4 = (!Objects.equal(_odeList, null));
                  if (_notEquals_4) {
                    SymbolDeclaration _symbol_3 = ss.getSymbol();
                    AnyExpression _expression_2 = _symbol_3.getExpression();
                    OdeList _odeList_1 = _expression_2.getOdeList();
                    final String initCond = this.getAttribute(_odeList_1, "init");
                    boolean _equals = initCond.equals("");
                    boolean _not = (!_equals);
                    if (_not) {
                      this.init_vars.put(Integer.valueOf(i), initCond);
                    } else {
                      this.init_vars.put(Integer.valueOf(i), "0");
                    }
                    int _plus = (i + 1);
                    i = _plus;
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  public void setModelPredictionVariables(final ModelObject o) {
    int i = 1;
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock b : _blocks) {
      ModelPredictionBlock _modelPredictionBlock = b.getModelPredictionBlock();
      boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
      if (_notEquals) {
        ModelPredictionBlock _modelPredictionBlock_1 = b.getModelPredictionBlock();
        EList<ModelPredictionBlockStatement> _statements = _modelPredictionBlock_1.getStatements();
        for (final ModelPredictionBlockStatement s : _statements) {
          OdeBlock _odeBlock = s.getOdeBlock();
          boolean _notEquals_1 = (!Objects.equal(_odeBlock, null));
          if (_notEquals_1) {
            OdeBlock _odeBlock_1 = s.getOdeBlock();
            EList<BlockStatement> _statements_1 = _odeBlock_1.getStatements();
            for (final BlockStatement ss : _statements_1) {
              {
                SymbolDeclaration x = ss.getSymbol();
                boolean _notEquals_2 = (!Objects.equal(x, null));
                if (_notEquals_2) {
                  AnyExpression _expression = x.getExpression();
                  boolean _notEquals_3 = (!Objects.equal(_expression, null));
                  if (_notEquals_3) {
                    AnyExpression _expression_1 = x.getExpression();
                    OdeList _odeList = _expression_1.getOdeList();
                    boolean _notEquals_4 = (!Objects.equal(_odeList, null));
                    if (_notEquals_4) {
                      String id = x.getIdentifier();
                      Object _get = this.dadt_vars.get(id);
                      boolean _equals = Objects.equal(_get, null);
                      if (_equals) {
                        this.dadt_vars.put(id, Integer.valueOf(i));
                        int _plus = (i + 1);
                        i = _plus;
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  
  public void setRandomVariables(final ModelObject o) {
    int i = 1;
    int j = 1;
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock b : _blocks) {
      RandomVariableDefinitionBlock _randomVariableDefinitionBlock = b.getRandomVariableDefinitionBlock();
      boolean _notEquals = (!Objects.equal(_randomVariableDefinitionBlock, null));
      if (_notEquals) {
        RandomVariableDefinitionBlock _randomVariableDefinitionBlock_1 = b.getRandomVariableDefinitionBlock();
        EList<SymbolDeclaration> _variables = _randomVariableDefinitionBlock_1.getVariables();
        for (final SymbolDeclaration s : _variables) {
          RandomList _randomList = s.getRandomList();
          boolean _notEquals_1 = (!Objects.equal(_randomList, null));
          if (_notEquals_1) {
            RandomList _randomList_1 = s.getRandomList();
            String level = this.getAttribute(_randomList_1, "level");
            final String id = s.getIdentifier();
            boolean _equals = level.equals("ID");
            if (_equals) {
              Object _get = this.eta_vars.get(id);
              boolean _equals_1 = Objects.equal(_get, null);
              if (_equals_1) {
                this.eta_vars.put(id, Integer.valueOf(i));
                int _plus = (i + 1);
                i = _plus;
              }
            }
            boolean _equals_2 = level.equals("DV");
            if (_equals_2) {
              Object _get_1 = this.eps_vars.get(id);
              boolean _equals_3 = Objects.equal(_get_1, null);
              if (_equals_3) {
                this.eps_vars.put(id, Integer.valueOf(j));
                int _plus_1 = (j + 1);
                j = _plus_1;
              }
            }
          }
        }
      }
    }
  }
  
  public void setStructuralParameters(final ModelObject o) {
    int i = 1;
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock b : _blocks) {
      StructuralParametersBlock _structuralParametersBlock = b.getStructuralParametersBlock();
      boolean _notEquals = (!Objects.equal(_structuralParametersBlock, null));
      if (_notEquals) {
        StructuralParametersBlock _structuralParametersBlock_1 = b.getStructuralParametersBlock();
        EList<FullyQualifiedSymbolName> _parameters = _structuralParametersBlock_1.getParameters();
        for (final FullyQualifiedSymbolName id : _parameters) {
          Object _get = this.theta_vars.get(id);
          boolean _equals = Objects.equal(_get, null);
          if (_equals) {
            this.theta_vars.put(id, Integer.valueOf(i));
            int _plus = (i + 1);
            i = _plus;
          }
        }
      }
    }
  }
  
  public String getDataSource(final Resource resource) {
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<Mcl> _filter = Iterables.<Mcl>filter(_iterable, Mcl.class);
    for (final Mcl m : _filter) {
      EList<MclObject> _objects = m.getObjects();
      for (final MclObject obj : _objects) {
        DataObject _dataObject = obj.getDataObject();
        boolean _notEquals = (!Objects.equal(_dataObject, null));
        if (_notEquals) {
          DataObject _dataObject_1 = obj.getDataObject();
          EList<DataObjectBlock> _blocks = _dataObject_1.getBlocks();
          for (final DataObjectBlock b : _blocks) {
            FileBlock _fileBlock = b.getFileBlock();
            boolean _notEquals_1 = (!Objects.equal(_fileBlock, null));
            if (_notEquals_1) {
              FileBlock _fileBlock_1 = b.getFileBlock();
              EList<FileBlockStatement> _statements = _fileBlock_1.getStatements();
              for (final FileBlockStatement s : _statements) {
                this.getDataSource(s);
              }
            }
          }
        }
      }
    }
    return "";
  }
  
  public String getDataSource(final FileBlockStatement s) {
    SymbolDeclaration _variable = s.getVariable();
    boolean _notEquals = (!Objects.equal(_variable, null));
    if (_notEquals) {
      SymbolDeclaration _variable_1 = s.getVariable();
      String _identifier = _variable_1.getIdentifier();
      boolean _equals = _identifier.equals("data");
      if (_equals) {
        SymbolDeclaration _variable_2 = s.getVariable();
        AnyExpression _expression = _variable_2.getExpression();
        boolean _notEquals_1 = (!Objects.equal(_expression, null));
        if (_notEquals_1) {
          SymbolDeclaration _variable_3 = s.getVariable();
          AnyExpression _expression_1 = _variable_3.getExpression();
          List _list = _expression_1.getList();
          boolean _notEquals_2 = (!Objects.equal(_list, null));
          if (_notEquals_2) {
            SymbolDeclaration _variable_4 = s.getVariable();
            AnyExpression _expression_2 = _variable_4.getExpression();
            List _list_1 = _expression_2.getList();
            return this.getAttribute(_list_1, "source");
          }
        }
      }
    }
    return "";
  }
  
  public String getTOL(final TaskObject obj) {
    EList<TaskObjectBlock> _blocks = obj.getBlocks();
    for (final TaskObjectBlock b : _blocks) {
      ModelBlock _modelBlock = b.getModelBlock();
      boolean _notEquals = (!Objects.equal(_modelBlock, null));
      if (_notEquals) {
        ModelBlock _modelBlock_1 = b.getModelBlock();
        EList<ModelBlockStatement> _statements = _modelBlock_1.getStatements();
        for (final ModelBlockStatement ss : _statements) {
          {
            BlockStatement _statement = ss.getStatement();
            SymbolDeclaration x = _statement.getSymbol();
            boolean _notEquals_1 = (!Objects.equal(x, null));
            if (_notEquals_1) {
              String _identifier = x.getIdentifier();
              boolean _equals = _identifier.equals("tolrel");
              if (_equals) {
                AnyExpression _expression = x.getExpression();
                boolean _notEquals_2 = (!Objects.equal(_expression, null));
                if (_notEquals_2) {
                  AnyExpression _expression_1 = x.getExpression();
                  return this.toStr(_expression_1);
                }
              }
            }
          }
        }
      }
    }
    return null;
  }
  
  public CharSequence printSUBR_MODEL_PK_ERROR_PRES_DES(final ModelObject o) {
    CharSequence _xblockexpression = null;
    {
      final boolean isLibraryDefined = this.isLibraryDefined(o);
      final boolean isPKDefined = this.isPKDefined(o);
      final boolean isErrorNonEmpty = this.isErrorNonEmpty(o);
      final boolean isODEDefined = this.isODEDefined(o);
      StringConcatenation _builder = new StringConcatenation();
      {
        if (isLibraryDefined) {
          {
            if (isPKDefined) {
              _builder.newLine();
              CharSequence _printSUBR = this.printSUBR(o);
              _builder.append(_printSUBR, "");
              _builder.newLineIfNotEmpty();
              String _externalCode = this.getExternalCode("$SUBR");
              _builder.append(_externalCode, "");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      {
        if (isODEDefined) {
          _builder.newLine();
          _builder.append("$MODEL");
          _builder.newLine();
          _builder.append("\t");
          CharSequence _printModel = this.printModel(o);
          _builder.append(_printModel, "	");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _externalCode_1 = this.getExternalCode("$MODEL");
          _builder.append(_externalCode_1, "	");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.newLine();
      {
        if (isLibraryDefined) {
          {
            if (isPKDefined) {
              _builder.append("\t");
              _builder.newLine();
              _builder.append("$PK ");
              _builder.newLine();
              _builder.append("\t");
              CharSequence _printPK = this.printPK(o);
              _builder.append(_printPK, "	");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              String _externalCode_2 = this.getExternalCode("$PK");
              _builder.append(_externalCode_2, "	");
              _builder.newLineIfNotEmpty();
            }
          }
          {
            if (isErrorNonEmpty) {
              _builder.newLine();
              _builder.append("$ERROR");
              _builder.newLine();
              _builder.append("\t");
              CharSequence _printError = this.printError(o);
              _builder.append(_printError, "	");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              String _externalCode_3 = this.getExternalCode("$ERROR");
              _builder.append(_externalCode_3, "	");
              _builder.newLineIfNotEmpty();
            }
          }
        } else {
          {
            boolean _or = false;
            if (isPKDefined) {
              _or = true;
            } else {
              _or = (isPKDefined || isErrorNonEmpty);
            }
            if (_or) {
              _builder.newLine();
              _builder.append("$PRED");
              _builder.newLine();
              _builder.append("\t");
              CharSequence _printPK_1 = this.printPK(o);
              _builder.append(_printPK_1, "	");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              String _externalCode_4 = this.getExternalCode("$PRED");
              _builder.append(_externalCode_4, "	");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              CharSequence _printError_1 = this.printError(o);
              _builder.append(_printError_1, "	");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              String _externalCode_5 = this.getExternalCode("$ERROR");
              _builder.append(_externalCode_5, "	");
              _builder.newLineIfNotEmpty();
            }
          }
        }
      }
      {
        if (isODEDefined) {
          _builder.newLine();
          _builder.append("$DES");
          _builder.newLine();
          _builder.append("\t");
          CharSequence _printDES = this.printDES(o);
          _builder.append(_printDES, "	");
          _builder.newLineIfNotEmpty();
          _builder.append("\t");
          String _externalCode_6 = this.getExternalCode("$DES");
          _builder.append(_externalCode_6, "	");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.newLine();
      CharSequence _printTable = this.printTable(o);
      _builder.append(_printTable, "");
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence printPK(final ModelObject o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ModelObjectBlock> _blocks = o.getBlocks();
      for(final ModelObjectBlock b : _blocks) {
        {
          GroupVariablesBlock _groupVariablesBlock = b.getGroupVariablesBlock();
          boolean _notEquals = (!Objects.equal(_groupVariablesBlock, null));
          if (_notEquals) {
            GroupVariablesBlock _groupVariablesBlock_1 = b.getGroupVariablesBlock();
            CharSequence _print = this.print(_groupVariablesBlock_1);
            _builder.append(_print, "");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          IndividualVariablesBlock _individualVariablesBlock = b.getIndividualVariablesBlock();
          boolean _notEquals_1 = (!Objects.equal(_individualVariablesBlock, null));
          if (_notEquals_1) {
            IndividualVariablesBlock bb = b.getIndividualVariablesBlock();
            _builder.newLineIfNotEmpty();
            {
              EList<BlockStatement> _statements = bb.getStatements();
              boolean _hasElements = false;
              for(final BlockStatement s : _statements) {
                if (!_hasElements) {
                  _hasElements = true;
                } else {
                  _builder.appendImmediate(" ", "");
                }
                CharSequence _print_1 = this.print(s);
                _builder.append(_print_1, "");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        {
          ModelPredictionBlock _modelPredictionBlock = b.getModelPredictionBlock();
          boolean _notEquals_2 = (!Objects.equal(_modelPredictionBlock, null));
          if (_notEquals_2) {
            _builder.newLine();
            _builder.append(";initial conditions");
            _builder.newLine();
            {
              Set<Entry<Object,Object>> _entrySet = this.init_vars.entrySet();
              for(final Entry<Object,Object> e : _entrySet) {
                _builder.append("A_0(");
                Object _key = e.getKey();
                _builder.append(_key, "");
                _builder.append(") = ");
                Object _value = e.getValue();
                _builder.append(_value, "");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence print(final GroupVariablesBlock block) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<GroupVariablesBlockStatement> _statements = block.getStatements();
      for(final GroupVariablesBlockStatement st : _statements) {
        {
          BlockStatement _statement = st.getStatement();
          boolean _notEquals = (!Objects.equal(_statement, null));
          if (_notEquals) {
            BlockStatement _statement_1 = st.getStatement();
            CharSequence _print = this.print(_statement_1);
            _builder.append(_print, "");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          MixtureBlock _mixtureBlock = st.getMixtureBlock();
          boolean _notEquals_1 = (!Objects.equal(_mixtureBlock, null));
          if (_notEquals_1) {
            MixtureBlock _mixtureBlock_1 = st.getMixtureBlock();
            CharSequence _print_1 = this.print(_mixtureBlock_1);
            _builder.append(_print_1, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence printError(final ModelObject o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ModelObjectBlock> _blocks = o.getBlocks();
      for(final ModelObjectBlock mob : _blocks) {
        {
          ModelPredictionBlock _modelPredictionBlock = mob.getModelPredictionBlock();
          boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
          if (_notEquals) {
            {
              ModelPredictionBlock _modelPredictionBlock_1 = mob.getModelPredictionBlock();
              EList<ModelPredictionBlockStatement> _statements = _modelPredictionBlock_1.getStatements();
              for(final ModelPredictionBlockStatement s : _statements) {
                {
                  BlockStatement _statement = s.getStatement();
                  boolean _notEquals_1 = (!Objects.equal(_statement, null));
                  if (_notEquals_1) {
                    BlockStatement _statement_1 = s.getStatement();
                    SymbolDeclaration x = _statement_1.getSymbol();
                    _builder.newLineIfNotEmpty();
                    {
                      boolean _notEquals_2 = (!Objects.equal(x, null));
                      if (_notEquals_2) {
                        {
                          AnyExpression _expression = x.getExpression();
                          boolean _notEquals_3 = (!Objects.equal(_expression, null));
                          if (_notEquals_3) {
                            {
                              AnyExpression _expression_1 = x.getExpression();
                              Expression _expression_2 = _expression_1.getExpression();
                              boolean _notEquals_4 = (!Objects.equal(_expression_2, null));
                              if (_notEquals_4) {
                                CharSequence _print = this.print(x);
                                _builder.append(_print, "");
                              }
                            }
                          }
                        }
                      }
                    }
                    _builder.newLineIfNotEmpty();
                    {
                      BlockStatement _statement_2 = s.getStatement();
                      ConditionalStatement _statement_3 = _statement_2.getStatement();
                      boolean _notEquals_5 = (!Objects.equal(_statement_3, null));
                      if (_notEquals_5) {
                        BlockStatement _statement_4 = s.getStatement();
                        ConditionalStatement _statement_5 = _statement_4.getStatement();
                        CharSequence _print_1 = this.print(_statement_5);
                        _builder.append(_print_1, "");
                      }
                    }
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
        {
          ObservationBlock _observationBlock = mob.getObservationBlock();
          boolean _notEquals_6 = (!Objects.equal(_observationBlock, null));
          if (_notEquals_6) {
            ObservationBlock _observationBlock_1 = mob.getObservationBlock();
            CharSequence _print_2 = this.print(_observationBlock_1);
            _builder.append(_print_2, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence printModel(final ModelObject o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ModelObjectBlock> _blocks = o.getBlocks();
      for(final ModelObjectBlock b : _blocks) {
        {
          ModelPredictionBlock _modelPredictionBlock = b.getModelPredictionBlock();
          boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
          if (_notEquals) {
            ModelPredictionBlock bb = b.getModelPredictionBlock();
            _builder.newLineIfNotEmpty();
            {
              EList<ModelPredictionBlockStatement> _statements = bb.getStatements();
              for(final ModelPredictionBlockStatement s : _statements) {
                {
                  OdeBlock _odeBlock = s.getOdeBlock();
                  boolean _notEquals_1 = (!Objects.equal(_odeBlock, null));
                  if (_notEquals_1) {
                    {
                      OdeBlock _odeBlock_1 = s.getOdeBlock();
                      EList<BlockStatement> _statements_1 = _odeBlock_1.getStatements();
                      for(final BlockStatement ss : _statements_1) {
                        SymbolDeclaration x = ss.getSymbol();
                        _builder.newLineIfNotEmpty();
                        {
                          boolean _notEquals_2 = (!Objects.equal(x, null));
                          if (_notEquals_2) {
                            {
                              AnyExpression _expression = x.getExpression();
                              boolean _notEquals_3 = (!Objects.equal(_expression, null));
                              if (_notEquals_3) {
                                {
                                  AnyExpression _expression_1 = x.getExpression();
                                  OdeList _odeList = _expression_1.getOdeList();
                                  boolean _notEquals_4 = (!Objects.equal(_odeList, null));
                                  if (_notEquals_4) {
                                    _builder.append("COMP(");
                                    String _identifier = x.getIdentifier();
                                    _builder.append(_identifier, "");
                                    _builder.append(")");
                                    _builder.newLineIfNotEmpty();
                                  }
                                }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence printDES(final ModelObject o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ModelObjectBlock> _blocks = o.getBlocks();
      for(final ModelObjectBlock b : _blocks) {
        {
          ModelPredictionBlock _modelPredictionBlock = b.getModelPredictionBlock();
          boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
          if (_notEquals) {
            ModelPredictionBlock bb = b.getModelPredictionBlock();
            _builder.newLineIfNotEmpty();
            {
              EList<ModelPredictionBlockStatement> _statements = bb.getStatements();
              for(final ModelPredictionBlockStatement s : _statements) {
                {
                  OdeBlock _odeBlock = s.getOdeBlock();
                  boolean _notEquals_1 = (!Objects.equal(_odeBlock, null));
                  if (_notEquals_1) {
                    {
                      OdeBlock _odeBlock_1 = s.getOdeBlock();
                      EList<BlockStatement> _statements_1 = _odeBlock_1.getStatements();
                      for(final BlockStatement ss : _statements_1) {
                        SymbolDeclaration x = ss.getSymbol();
                        _builder.newLineIfNotEmpty();
                        {
                          boolean _notEquals_2 = (!Objects.equal(x, null));
                          if (_notEquals_2) {
                            {
                              AnyExpression _expression = x.getExpression();
                              boolean _notEquals_3 = (!Objects.equal(_expression, null));
                              if (_notEquals_3) {
                                {
                                  AnyExpression _expression_1 = x.getExpression();
                                  Expression _expression_2 = _expression_1.getExpression();
                                  boolean _notEquals_4 = (!Objects.equal(_expression_2, null));
                                  if (_notEquals_4) {
                                    CharSequence _print = this.print(x);
                                    _builder.append(_print, "");
                                    _builder.newLineIfNotEmpty();
                                  }
                                }
                                {
                                  AnyExpression _expression_3 = x.getExpression();
                                  OdeList _odeList = _expression_3.getOdeList();
                                  boolean _notEquals_5 = (!Objects.equal(_odeList, null));
                                  if (_notEquals_5) {
                                    AnyExpression _expression_4 = x.getExpression();
                                    OdeList _odeList_1 = _expression_4.getOdeList();
                                    String deriv = this.getAttribute(_odeList_1, "deriv");
                                    _builder.newLineIfNotEmpty();
                                    {
                                      boolean _equals = deriv.equals("");
                                      boolean _not = (!_equals);
                                      if (_not) {
                                        String id = x.getIdentifier();
                                        _builder.newLineIfNotEmpty();
                                        {
                                          Object _get = this.dadt_vars.get(id);
                                          boolean _notEquals_6 = (!Objects.equal(_get, null));
                                          if (_notEquals_6) {
                                            _builder.append("DADT(");
                                            Object _get_1 = this.dadt_vars.get(id);
                                            _builder.append(_get_1, "");
                                            _builder.append(") = ");
                                            _builder.append(deriv, "");
                                            _builder.newLineIfNotEmpty();
                                          }
                                        }
                                      }
                                    }
                                  }
                                }
                              }
                            }
                          }
                        }
                        {
                          ConditionalStatement _statement = ss.getStatement();
                          boolean _notEquals_7 = (!Objects.equal(_statement, null));
                          if (_notEquals_7) {
                            ConditionalStatement _statement_1 = ss.getStatement();
                            CharSequence _print_1 = this.print(_statement_1);
                            _builder.append(_print_1, "");
                            _builder.newLineIfNotEmpty();
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence printSUBR(final ModelObject o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ModelObjectBlock> _blocks = o.getBlocks();
      for(final ModelObjectBlock b : _blocks) {
        {
          ModelPredictionBlock _modelPredictionBlock = b.getModelPredictionBlock();
          boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
          if (_notEquals) {
            ModelPredictionBlock _modelPredictionBlock_1 = b.getModelPredictionBlock();
            CharSequence _printSUBR = this.printSUBR(_modelPredictionBlock_1);
            _builder.append(_printSUBR, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence printSUBR(final ModelPredictionBlock b) {
    EList<ModelPredictionBlockStatement> _statements = b.getStatements();
    for (final ModelPredictionBlockStatement ss : _statements) {
      LibraryBlock _libraryBlock = ss.getLibraryBlock();
      boolean _notEquals = (!Objects.equal(_libraryBlock, null));
      if (_notEquals) {
        LibraryBlock _libraryBlock_1 = ss.getLibraryBlock();
        EList<FunctionCallStatement> _statements_1 = _libraryBlock_1.getStatements();
        for (final FunctionCallStatement st : _statements_1) {
          {
            FunctionCall _expression = st.getExpression();
            FullyQualifiedSymbolName libraryRef = _expression.getIdentifier();
            HashMap<String,String> attributes = this.getExternalFunctionAttributes(libraryRef);
            String library = libraryRef.getIdentifier();
            boolean _notEquals_1 = (!Objects.equal(attributes, null));
            if (_notEquals_1) {
              String name = attributes.get("name");
              boolean _notEquals_2 = (!Objects.equal(name, null));
              if (_notEquals_2) {
                library = name;
              }
            }
            FunctionCall _expression_1 = st.getExpression();
            Arguments _arguments = _expression_1.getArguments();
            final String model = this.selectAttribute(_arguments, "model");
            FunctionCall _expression_2 = st.getExpression();
            Arguments _arguments_1 = _expression_2.getArguments();
            final String trans = this.selectAttribute(_arguments_1, "trans");
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("$SUBR ");
            {
              boolean _equals = model.equals("");
              boolean _not = (!_equals);
              if (_not) {
                String _upperCase = library.toUpperCase();
                _builder.append(_upperCase, "");
                _builder.append(model, "");
              }
            }
            _builder.append(" TOL = ");
            _builder.append(this.tol, "");
            _builder.append(" ");
            {
              boolean _equals_1 = trans.equals("");
              boolean _not_1 = (!_equals_1);
              if (_not_1) {
                _builder.append("TRANS");
                _builder.append(trans, "");
              }
            }
            return _builder;
          }
        }
      }
    }
    return null;
  }
  
  public CharSequence printTable(final ModelObject o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<ModelObjectBlock> _blocks = o.getBlocks();
      for(final ModelObjectBlock b : _blocks) {
        {
          OutputVariablesBlock _outputVariablesBlock = b.getOutputVariablesBlock();
          boolean _notEquals = (!Objects.equal(_outputVariablesBlock, null));
          if (_notEquals) {
            OutputVariablesBlock bb = b.getOutputVariablesBlock();
            _builder.newLineIfNotEmpty();
            {
              EList<FullyQualifiedSymbolName> _variables = bb.getVariables();
              int _size = _variables.size();
              boolean _greaterThan = (_size > 0);
              if (_greaterThan) {
                _builder.append("$TABLE ");
                {
                  EList<FullyQualifiedSymbolName> _variables_1 = bb.getVariables();
                  boolean _hasElements = false;
                  for(final FullyQualifiedSymbolName st : _variables_1) {
                    if (!_hasElements) {
                      _hasElements = true;
                    } else {
                      _builder.appendImmediate(" ", "");
                    }
                    String _str = this.toStr(st);
                    _builder.append(_str, "");
                  }
                }
                _builder.newLineIfNotEmpty();
                _builder.append("ONEHEADER NOPRINT ");
                {
                  boolean _equals = this.file.equals("");
                  boolean _not = (!_equals);
                  if (_not) {
                    _builder.append("FILE=");
                    _builder.append(this.file, "");
                  }
                }
                _builder.append(" ");
                _builder.newLineIfNotEmpty();
                String _externalCode = this.getExternalCode("$TABLE");
                _builder.append(_externalCode, "");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence printTHETA_OMEGA_SIGMA(final ParameterObject obj) {
    StringConcatenation _builder = new StringConcatenation();
    CharSequence _printTheta = this.printTheta(obj);
    _builder.append(_printTheta, "");
    _builder.newLineIfNotEmpty();
    CharSequence _printSigma = this.printSigma(obj);
    _builder.append(_printSigma, "");
    _builder.newLineIfNotEmpty();
    CharSequence _printOmega = this.printOmega(obj);
    _builder.append(_printOmega, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence printTheta(final ParameterObject obj) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isThetaNonEmpty = this.isThetaNonEmpty(obj);
      if (_isThetaNonEmpty) {
        _builder.newLine();
        _builder.append("$THETA");
        _builder.newLine();
        {
          EList<ParameterObjectBlock> _blocks = obj.getBlocks();
          for(final ParameterObjectBlock b : _blocks) {
            {
              StructuralBlock _structuralBlock = b.getStructuralBlock();
              boolean _notEquals = (!Objects.equal(_structuralBlock, null));
              if (_notEquals) {
                {
                  StructuralBlock _structuralBlock_1 = b.getStructuralBlock();
                  EList<ParameterDeclaration> _parameters = _structuralBlock_1.getParameters();
                  for(final ParameterDeclaration st : _parameters) {
                    _builder.append("\t");
                    CharSequence _printTheta = this.printTheta(st);
                    _builder.append(_printTheta, "	");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
      }
    }
    String _externalCode = this.getExternalCode("$THETA");
    _builder.append(_externalCode, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence printOmega(final ParameterObject obj) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _or = false;
      boolean _and = false;
      boolean _isVariabilityNonEmpty = this.isVariabilityNonEmpty(obj);
      if (!_isVariabilityNonEmpty) {
        _and = false;
      } else {
        boolean _isEmpty = this.eta_vars.isEmpty();
        boolean _not = (!_isEmpty);
        _and = (_isVariabilityNonEmpty && _not);
      }
      if (_and) {
        _or = true;
      } else {
        boolean _isVariabilitySubBlocksNonEmpty = this.isVariabilitySubBlocksNonEmpty(obj);
        _or = (_and || _isVariabilitySubBlocksNonEmpty);
      }
      if (_or) {
        _builder.newLine();
        _builder.append("$OMEGA");
        _builder.newLine();
        {
          EList<ParameterObjectBlock> _blocks = obj.getBlocks();
          for(final ParameterObjectBlock b : _blocks) {
            {
              VariabilityBlock _variabilityBlock = b.getVariabilityBlock();
              boolean _notEquals = (!Objects.equal(_variabilityBlock, null));
              if (_notEquals) {
                {
                  VariabilityBlock _variabilityBlock_1 = b.getVariabilityBlock();
                  EList<VariabilityBlockStatement> _statements = _variabilityBlock_1.getStatements();
                  for(final VariabilityBlockStatement c : _statements) {
                    {
                      ParameterDeclaration _parameter = c.getParameter();
                      boolean _notEquals_1 = (!Objects.equal(_parameter, null));
                      if (_notEquals_1) {
                        _builder.append("\t");
                        ParameterDeclaration _parameter_1 = c.getParameter();
                        String _printOmega = this.printOmega(_parameter_1);
                        _builder.append(_printOmega, "	");
                        _builder.newLineIfNotEmpty();
                      }
                    }
                    _builder.append("\t");
                    CharSequence _printVariabilitySubBlock = this.printVariabilitySubBlock(c);
                    _builder.append(_printVariabilitySubBlock, "	");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
      }
    }
    ArrayList<String> _get = this.externalCode.get("$OMEGA");
    _builder.append(_get, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence printSigma(final ParameterObject obj) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _and = false;
      boolean _isVariabilityNonEmpty = this.isVariabilityNonEmpty(obj);
      if (!_isVariabilityNonEmpty) {
        _and = false;
      } else {
        boolean _isEmpty = this.eps_vars.isEmpty();
        boolean _not = (!_isEmpty);
        _and = (_isVariabilityNonEmpty && _not);
      }
      if (_and) {
        _builder.newLine();
        _builder.append("$SIGMA");
        _builder.newLine();
        {
          EList<ParameterObjectBlock> _blocks = obj.getBlocks();
          for(final ParameterObjectBlock b : _blocks) {
            {
              VariabilityBlock _variabilityBlock = b.getVariabilityBlock();
              boolean _notEquals = (!Objects.equal(_variabilityBlock, null));
              if (_notEquals) {
                {
                  VariabilityBlock _variabilityBlock_1 = b.getVariabilityBlock();
                  EList<VariabilityBlockStatement> _statements = _variabilityBlock_1.getStatements();
                  for(final VariabilityBlockStatement c : _statements) {
                    {
                      ParameterDeclaration _parameter = c.getParameter();
                      boolean _notEquals_1 = (!Objects.equal(_parameter, null));
                      if (_notEquals_1) {
                        _builder.append("\t");
                        ParameterDeclaration _parameter_1 = c.getParameter();
                        String _printSigma = this.printSigma(_parameter_1);
                        _builder.append(_printSigma, "	");
                        _builder.newLineIfNotEmpty();
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    String _externalCode = this.getExternalCode("$SIGMA");
    _builder.append(_externalCode, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public boolean isThetaNonEmpty(final ParameterObject obj) {
    EList<ParameterObjectBlock> _blocks = obj.getBlocks();
    for (final ParameterObjectBlock b : _blocks) {
      StructuralBlock _structuralBlock = b.getStructuralBlock();
      boolean _notEquals = (!Objects.equal(_structuralBlock, null));
      if (_notEquals) {
        StructuralBlock _structuralBlock_1 = b.getStructuralBlock();
        EList<ParameterDeclaration> _parameters = _structuralBlock_1.getParameters();
        int _size = _parameters.size();
        boolean _greaterThan = (_size > 0);
        if (_greaterThan) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean isVariabilityNonEmpty(final ParameterObject obj) {
    EList<ParameterObjectBlock> _blocks = obj.getBlocks();
    for (final ParameterObjectBlock b : _blocks) {
      VariabilityBlock _variabilityBlock = b.getVariabilityBlock();
      boolean _notEquals = (!Objects.equal(_variabilityBlock, null));
      if (_notEquals) {
        VariabilityBlock _variabilityBlock_1 = b.getVariabilityBlock();
        EList<VariabilityBlockStatement> _statements = _variabilityBlock_1.getStatements();
        int _size = _statements.size();
        boolean _greaterThan = (_size > 0);
        if (_greaterThan) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean isVariabilitySubBlocksNonEmpty(final ParameterObject obj) {
    EList<ParameterObjectBlock> _blocks = obj.getBlocks();
    for (final ParameterObjectBlock b : _blocks) {
      VariabilityBlock _variabilityBlock = b.getVariabilityBlock();
      boolean _notEquals = (!Objects.equal(_variabilityBlock, null));
      if (_notEquals) {
        VariabilityBlock _variabilityBlock_1 = b.getVariabilityBlock();
        EList<VariabilityBlockStatement> _statements = _variabilityBlock_1.getStatements();
        for (final VariabilityBlockStatement bb : _statements) {
          boolean _or = false;
          DiagBlock _diagBlock = bb.getDiagBlock();
          boolean _notEquals_1 = (!Objects.equal(_diagBlock, null));
          if (_notEquals_1) {
            _or = true;
          } else {
            BlockBlock _blockBlock = bb.getBlockBlock();
            boolean _notEquals_2 = (!Objects.equal(_blockBlock, null));
            _or = (_notEquals_1 || _notEquals_2);
          }
          if (_or) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public CharSequence printINPUT_DATA(final DataObject o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<DataObjectBlock> _blocks = o.getBlocks();
      for(final DataObjectBlock b : _blocks) {
        {
          HeaderBlock _headerBlock = b.getHeaderBlock();
          boolean _notEquals = (!Objects.equal(_headerBlock, null));
          if (_notEquals) {
            HeaderBlock _headerBlock_1 = b.getHeaderBlock();
            CharSequence _printInput = this.printInput(_headerBlock_1);
            _builder.append(_printInput, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    {
      EList<DataObjectBlock> _blocks_1 = o.getBlocks();
      for(final DataObjectBlock b_1 : _blocks_1) {
        {
          FileBlock _fileBlock = b_1.getFileBlock();
          boolean _notEquals_1 = (!Objects.equal(_fileBlock, null));
          if (_notEquals_1) {
            FileBlock _fileBlock_1 = b_1.getFileBlock();
            CharSequence _printData = this.printData(_fileBlock_1);
            _builder.append(_printData, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence printInput(final HeaderBlock b) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("$INPUT ");
    {
      EList<SymbolModification> _variables = b.getVariables();
      boolean _hasElements = false;
      for(final SymbolModification st : _variables) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(" ", "");
        }
        FullyQualifiedSymbolName _identifier = st.getIdentifier();
        String _str = this.toStr(_identifier);
        _builder.append(_str, "");
      }
    }
    _builder.newLineIfNotEmpty();
    String _externalCode = this.getExternalCode("$INPUT");
    _builder.append(_externalCode, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence printData(final FileBlock b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<FileBlockStatement> _statements = b.getStatements();
      for(final FileBlockStatement s : _statements) {
        CharSequence _printDataSource = this.printDataSource(s);
        _builder.append(_printDataSource, "");
        _builder.newLineIfNotEmpty();
      }
    }
    String _externalCode = this.getExternalCode("$DATA");
    _builder.append(_externalCode, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence printDataSource(final FileBlockStatement s) {
    SymbolDeclaration _variable = s.getVariable();
    boolean _notEquals = (!Objects.equal(_variable, null));
    if (_notEquals) {
      SymbolDeclaration _variable_1 = s.getVariable();
      String _identifier = _variable_1.getIdentifier();
      boolean _equals = _identifier.equals("data");
      if (_equals) {
        SymbolDeclaration _variable_2 = s.getVariable();
        AnyExpression _expression = _variable_2.getExpression();
        boolean _notEquals_1 = (!Objects.equal(_expression, null));
        if (_notEquals_1) {
          SymbolDeclaration _variable_3 = s.getVariable();
          AnyExpression _expression_1 = _variable_3.getExpression();
          List _list = _expression_1.getList();
          boolean _notEquals_2 = (!Objects.equal(_list, null));
          if (_notEquals_2) {
            SymbolDeclaration _variable_4 = s.getVariable();
            AnyExpression _expression_2 = _variable_4.getExpression();
            List _list_1 = _expression_2.getList();
            String data = this.getAttribute(_list_1, "source");
            SymbolDeclaration _variable_5 = s.getVariable();
            AnyExpression _expression_3 = _variable_5.getExpression();
            List _list_2 = _expression_3.getList();
            String ignore = this.getAttribute(_list_2, "ignore");
            StringConcatenation _builder = new StringConcatenation();
            {
              boolean _equals_1 = data.equals("");
              boolean _not = (!_equals_1);
              if (_not) {
                _builder.append("$DATA ");
                _builder.append(data, "");
              }
            }
            _builder.append(" ");
            {
              boolean _equals_2 = ignore.equals("");
              boolean _not_1 = (!_equals_2);
              if (_not_1) {
                _builder.append("IGNORE=");
                _builder.append(ignore, "");
              }
            }
            return _builder;
          }
        }
      }
    }
    return null;
  }
  
  public CharSequence printFunctions(final TaskObject o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<TaskObjectBlock> _blocks = o.getBlocks();
      for(final TaskObjectBlock b : _blocks) {
        CharSequence _printFunctions = this.printFunctions(b);
        _builder.append(_printFunctions, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence printFunctions(final TaskObjectBlock b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      TaskFunctionDeclaration _functionDeclaration = b.getFunctionDeclaration();
      boolean _notEquals = (!Objects.equal(_functionDeclaration, null));
      if (_notEquals) {
        TaskFunctionDeclaration _functionDeclaration_1 = b.getFunctionDeclaration();
        final TaskFunctionBody body = _functionDeclaration_1.getFunctionBody();
        _builder.newLineIfNotEmpty();
        {
          boolean _notEquals_1 = (!Objects.equal(body, null));
          if (_notEquals_1) {
            {
              EList<TaskFunctionBlock> _blocks = body.getBlocks();
              for(final TaskFunctionBlock bb : _blocks) {
                {
                  EstimateTask _estimateBlock = bb.getEstimateBlock();
                  boolean _notEquals_2 = (!Objects.equal(_estimateBlock, null));
                  if (_notEquals_2) {
                    EstimateTask _estimateBlock_1 = bb.getEstimateBlock();
                    CharSequence _printEstimate = this.printEstimate(_estimateBlock_1);
                    _builder.append(_printEstimate, "");
                    _builder.newLineIfNotEmpty();
                  }
                }
                {
                  SimulateTask _simulateBlock = bb.getSimulateBlock();
                  boolean _notEquals_3 = (!Objects.equal(_simulateBlock, null));
                  if (_notEquals_3) {
                    SimulateTask _simulateBlock_1 = bb.getSimulateBlock();
                    CharSequence _printSimulate = this.printSimulate(_simulateBlock_1);
                    _builder.append(_printSimulate, "");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence printIgnoreStatements(final TaskObject obj) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<TaskObjectBlock> _blocks = obj.getBlocks();
      for(final TaskObjectBlock b : _blocks) {
        {
          DataBlock _dataBlock = b.getDataBlock();
          boolean _notEquals = (!Objects.equal(_dataBlock, null));
          if (_notEquals) {
            {
              DataBlock _dataBlock_1 = b.getDataBlock();
              EList<DataBlockStatement> _statements = _dataBlock_1.getStatements();
              for(final DataBlockStatement block : _statements) {
                {
                  IgnoreList _ignoreList = block.getIgnoreList();
                  boolean _notEquals_1 = (!Objects.equal(_ignoreList, null));
                  if (_notEquals_1) {
                    IgnoreList _ignoreList_1 = block.getIgnoreList();
                    String _identifier = _ignoreList_1.getIdentifier();
                    _builder.append(_identifier, "");
                    _builder.append(" = (");
                    IgnoreList _ignoreList_2 = block.getIgnoreList();
                    OrExpression _expression = _ignoreList_2.getExpression();
                    String _str = this.toStr(_expression);
                    _builder.append(_str, "");
                    _builder.append(" )");
                    _builder.newLineIfNotEmpty();
                  }
                }
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence printSimulate(final SimulateTask b) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("$SIM ");
    {
      EList<BlockStatement> _statements = b.getStatements();
      for(final BlockStatement s : _statements) {
        {
          SymbolDeclaration _symbol = s.getSymbol();
          boolean _notEquals = (!Objects.equal(_symbol, null));
          if (_notEquals) {
            SymbolDeclaration _symbol_1 = s.getSymbol();
            CharSequence _printSimulate = this.printSimulate(_symbol_1);
            _builder.append(_printSimulate, "");
          }
        }
      }
    }
    _builder.append(" NOABORT");
    _builder.newLineIfNotEmpty();
    String _externalCode = this.getExternalCode("$SIM");
    _builder.append(_externalCode, "");
    _builder.newLineIfNotEmpty();
    String _externalCode_1 = this.getExternalCode("$SIMULATION");
    _builder.append(_externalCode_1, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence printEstimate(final EstimateTask b) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("$EST");
    {
      EList<BlockStatement> _statements = b.getStatements();
      for(final BlockStatement s : _statements) {
        {
          SymbolDeclaration _symbol = s.getSymbol();
          boolean _notEquals = (!Objects.equal(_symbol, null));
          if (_notEquals) {
            SymbolDeclaration _symbol_1 = s.getSymbol();
            CharSequence _printEstimate = this.printEstimate(_symbol_1);
            _builder.append(_printEstimate, "");
          }
        }
      }
    }
    _builder.append(" NOABORT");
    _builder.newLineIfNotEmpty();
    String _externalCode = this.getExternalCode("$EST");
    _builder.append(_externalCode, "");
    _builder.newLineIfNotEmpty();
    String _externalCode_1 = this.getExternalCode("$ESTIMATION");
    _builder.append(_externalCode_1, "");
    _builder.newLineIfNotEmpty();
    {
      EList<BlockStatement> _statements_1 = b.getStatements();
      for(final BlockStatement s_1 : _statements_1) {
        {
          SymbolDeclaration _symbol_2 = s_1.getSymbol();
          boolean _notEquals_1 = (!Objects.equal(_symbol_2, null));
          if (_notEquals_1) {
            SymbolDeclaration _symbol_3 = s_1.getSymbol();
            CharSequence _printEstimateCov = this.printEstimateCov(_symbol_3);
            _builder.append(_printEstimateCov, "");
          }
        }
      }
    }
    _builder.newLineIfNotEmpty();
    String _externalCode_2 = this.getExternalCode("$COV");
    _builder.append(_externalCode_2, "");
    _builder.append("\t\t");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence printEstimate(final SymbolDeclaration s) {
    CharSequence _xifexpression = null;
    String _identifier = s.getIdentifier();
    boolean _equals = _identifier.equals("algo");
    if (_equals) {
      CharSequence _xifexpression_1 = null;
      AnyExpression _expression = s.getExpression();
      Expression _expression_1 = _expression.getExpression();
      boolean _notEquals = (!Objects.equal(_expression_1, null));
      if (_notEquals) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(" ");
        _builder.append("METHOD=");
        AnyExpression _expression_2 = s.getExpression();
        Expression _expression_3 = _expression_2.getExpression();
        String _str = this.toStr(_expression_3);
        _builder.append(_str, " ");
        _xifexpression_1 = _builder;
      } else {
        CharSequence _xifexpression_2 = null;
        AnyExpression _expression_4 = s.getExpression();
        List _list = _expression_4.getList();
        boolean _notEquals_1 = (!Objects.equal(_list, null));
        if (_notEquals_1) {
          CharSequence _xblockexpression = null;
          {
            AnyExpression _expression_5 = s.getExpression();
            List _list_1 = _expression_5.getList();
            Arguments args = _list_1.getArguments();
            CharSequence _xifexpression_3 = null;
            boolean _notEquals_2 = (!Objects.equal(args, null));
            if (_notEquals_2) {
              CharSequence _xifexpression_4 = null;
              EList<Argument> _arguments = args.getArguments();
              int _size = _arguments.size();
              boolean _greaterThan = (_size > 0);
              if (_greaterThan) {
                StringConcatenation _builder_1 = new StringConcatenation();
                _builder_1.append(" ");
                _builder_1.append("METHOD=");
                EList<Argument> _arguments_1 = args.getArguments();
                Argument _get = _arguments_1.get(0);
                AnyExpression _expression_6 = _get.getExpression();
                String _str_1 = this.toStr(_expression_6);
                _builder_1.append(_str_1, " ");
                _xifexpression_4 = _builder_1;
              }
              _xifexpression_3 = _xifexpression_4;
            }
            _xblockexpression = (_xifexpression_3);
          }
          _xifexpression_2 = _xblockexpression;
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    } else {
      CharSequence _xifexpression_3 = null;
      String _identifier_1 = s.getIdentifier();
      boolean _equals_1 = _identifier_1.equals("max");
      if (_equals_1) {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append(" ");
        _builder_1.append("MAX=");
        AnyExpression _expression_5 = s.getExpression();
        CharSequence _print = this.print(_expression_5);
        _builder_1.append(_print, " ");
        _xifexpression_3 = _builder_1;
      } else {
        CharSequence _xifexpression_4 = null;
        String _identifier_2 = s.getIdentifier();
        boolean _equals_2 = _identifier_2.equals("sig");
        if (_equals_2) {
          StringConcatenation _builder_2 = new StringConcatenation();
          _builder_2.append(" ");
          _builder_2.append("SIG=");
          AnyExpression _expression_6 = s.getExpression();
          CharSequence _print_1 = this.print(_expression_6);
          _builder_2.append(_print_1, " ");
          _xifexpression_4 = _builder_2;
        }
        _xifexpression_3 = _xifexpression_4;
      }
      _xifexpression = _xifexpression_3;
    }
    return _xifexpression;
  }
  
  public CharSequence printSimulate(final SymbolDeclaration s) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("#The MDL to NM-TRAN converter does not support yet simulation tasks");
    return _builder;
  }
  
  public CharSequence printEstimateCov(final SymbolDeclaration s) {
    CharSequence _xifexpression = null;
    String _identifier = s.getIdentifier();
    boolean _equals = _identifier.equals("cov");
    if (_equals) {
      CharSequence _xifexpression_1 = null;
      AnyExpression _expression = s.getExpression();
      boolean _notEquals = (!Objects.equal(_expression, null));
      if (_notEquals) {
        CharSequence _xifexpression_2 = null;
        AnyExpression _expression_1 = s.getExpression();
        String _str = this.toStr(_expression_1);
        String _replaceAll = _str.replaceAll("\\s", "");
        boolean _equals_1 = _replaceAll.equals("");
        if (_equals_1) {
          StringConcatenation _builder = new StringConcatenation();
          _builder.append("$COV ");
          AnyExpression _expression_2 = s.getExpression();
          CharSequence _print = this.print(_expression_2);
          _builder.append(_print, "");
          _xifexpression_2 = _builder;
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public CharSequence printVariabilitySubBlock(final VariabilityBlockStatement v) {
    String result = "";
    DiagBlock _diagBlock = v.getDiagBlock();
    boolean _notEquals = (!Objects.equal(_diagBlock, null));
    if (_notEquals) {
      boolean printFix = false;
      int k = 0;
      DiagBlock _diagBlock_1 = v.getDiagBlock();
      Arguments _arguments = _diagBlock_1.getArguments();
      EList<Argument> _arguments_1 = _arguments.getArguments();
      for (final Argument a : _arguments_1) {
        String _identifier = a.getIdentifier();
        boolean _notEquals_1 = (!Objects.equal(_identifier, null));
        if (_notEquals_1) {
          String _identifier_1 = a.getIdentifier();
          boolean _equals = _identifier_1.equals("fix");
          if (_equals) {
            AnyExpression _expression = a.getExpression();
            boolean _notEquals_2 = (!Objects.equal(_expression, null));
            if (_notEquals_2) {
              AnyExpression _expression_1 = a.getExpression();
              boolean _isTrue = this.isTrue(_expression_1);
              printFix = _isTrue;
            }
          }
        }
      }
      DiagBlock _diagBlock_2 = v.getDiagBlock();
      Arguments _parameters = _diagBlock_2.getParameters();
      boolean _notEquals_3 = (!Objects.equal(_parameters, null));
      if (_notEquals_3) {
        DiagBlock _diagBlock_3 = v.getDiagBlock();
        Arguments _parameters_1 = _diagBlock_3.getParameters();
        EList<Argument> _arguments_2 = _parameters_1.getArguments();
        for (final Argument p : _arguments_2) {
          AnyExpression _expression_2 = p.getExpression();
          boolean _notEquals_4 = (!Objects.equal(_expression_2, null));
          if (_notEquals_4) {
            int i = 0;
            boolean _lessThan = (i < k);
            boolean _while = _lessThan;
            while (_while) {
              {
                String _plus = (result + "0 ");
                result = _plus;
                int _plus_1 = (i + 1);
                i = _plus_1;
              }
              boolean _lessThan_1 = (i < k);
              _while = _lessThan_1;
            }
            int _plus = (k + 1);
            k = _plus;
            AnyExpression _expression_3 = p.getExpression();
            String _str = this.toStr(_expression_3);
            String _plus_1 = (result + _str);
            String _plus_2 = (_plus_1 + " ");
            result = _plus_2;
            String _identifier_2 = p.getIdentifier();
            boolean _notEquals_5 = (!Objects.equal(_identifier_2, null));
            if (_notEquals_5) {
              String _plus_3 = (result + "; ");
              String _identifier_3 = p.getIdentifier();
              String _plus_4 = (_plus_3 + _identifier_3);
              String _plus_5 = (_plus_4 + "\n");
              result = _plus_5;
            }
          }
        }
      }
      if (printFix) {
        String _plus_6 = (result + "FIX\n");
        result = _plus_6;
      }
    }
    BlockBlock _blockBlock = v.getBlockBlock();
    boolean _notEquals_6 = (!Objects.equal(_blockBlock, null));
    if (_notEquals_6) {
      boolean printFix_1 = false;
      BlockBlock _blockBlock_1 = v.getBlockBlock();
      Arguments _arguments_3 = _blockBlock_1.getArguments();
      EList<Argument> _arguments_4 = _arguments_3.getArguments();
      for (final Argument a_1 : _arguments_4) {
        String _identifier_4 = a_1.getIdentifier();
        boolean _notEquals_7 = (!Objects.equal(_identifier_4, null));
        if (_notEquals_7) {
          String _identifier_5 = a_1.getIdentifier();
          boolean _equals_1 = _identifier_5.equals("fix");
          if (_equals_1) {
            AnyExpression _expression_4 = a_1.getExpression();
            boolean _notEquals_8 = (!Objects.equal(_expression_4, null));
            if (_notEquals_8) {
              AnyExpression _expression_5 = a_1.getExpression();
              boolean _isTrue_1 = this.isTrue(_expression_5);
              printFix_1 = _isTrue_1;
            }
          }
        }
      }
      BlockBlock _blockBlock_2 = v.getBlockBlock();
      Arguments _parameters_2 = _blockBlock_2.getParameters();
      boolean _notEquals_9 = (!Objects.equal(_parameters_2, null));
      if (_notEquals_9) {
        BlockBlock _blockBlock_3 = v.getBlockBlock();
        Arguments _parameters_3 = _blockBlock_3.getParameters();
        EList<Argument> _arguments_5 = _parameters_3.getArguments();
        for (final Argument p_1 : _arguments_5) {
          AnyExpression _expression_6 = p_1.getExpression();
          boolean _notEquals_10 = (!Objects.equal(_expression_6, null));
          if (_notEquals_10) {
            AnyExpression _expression_7 = p_1.getExpression();
            String _str_1 = this.toStr(_expression_7);
            String _plus_7 = (result + _str_1);
            String _plus_8 = (_plus_7 + " ");
            result = _plus_8;
            String _identifier_6 = p_1.getIdentifier();
            boolean _notEquals_11 = (!Objects.equal(_identifier_6, null));
            if (_notEquals_11) {
              String _plus_9 = (result + "; ");
              String _identifier_7 = p_1.getIdentifier();
              String _plus_10 = (_plus_9 + _identifier_7);
              String _plus_11 = (_plus_10 + "\n");
              result = _plus_11;
            }
          }
        }
      }
      if (printFix_1) {
        String _plus_12 = (result + "FIX\n");
        result = _plus_12;
      }
    }
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(result, "");
    return _builder;
  }
  
  public String printSigma(final ParameterDeclaration s) {
    String _xifexpression = null;
    List _list = s.getList();
    boolean _notEquals = (!Objects.equal(_list, null));
    if (_notEquals) {
      String _xblockexpression = null;
      {
        String name = s.getIdentifier();
        String _xifexpression_1 = null;
        String _plus = ("eps_" + name);
        Object _get = this.eps_vars.get(_plus);
        boolean _notEquals_1 = (!Objects.equal(_get, null));
        if (_notEquals_1) {
          String _xblockexpression_1 = null;
          {
            List _list_1 = s.getList();
            final String value = this.getAttribute(_list_1, "value");
            List _list_2 = s.getList();
            String _attribute = this.getAttribute(_list_2, "fix");
            final boolean printFix = this.isTrue(_attribute);
            boolean _equals = value.equals("");
            if (_equals) {
              return "";
            }
            StringConcatenation _builder = new StringConcatenation();
            _builder.append(value, "");
            {
              if (printFix) {
                _builder.append(" FIX");
              }
            }
            _builder.append(" ; ");
            _builder.append(name, "");
            _xblockexpression_1 = (_builder.toString());
          }
          _xifexpression_1 = _xblockexpression_1;
        }
        _xblockexpression = (_xifexpression_1);
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
  
  public String printOmega(final ParameterDeclaration s) {
    String _xifexpression = null;
    List _list = s.getList();
    boolean _notEquals = (!Objects.equal(_list, null));
    if (_notEquals) {
      String _xblockexpression = null;
      {
        String name = s.getIdentifier();
        String _xifexpression_1 = null;
        String _plus = ("eta_" + name);
        Object _get = this.eta_vars.get(_plus);
        boolean _notEquals_1 = (!Objects.equal(_get, null));
        if (_notEquals_1) {
          String _xblockexpression_1 = null;
          {
            List _list_1 = s.getList();
            final String value = this.getAttribute(_list_1, "value");
            List _list_2 = s.getList();
            String _attribute = this.getAttribute(_list_2, "fix");
            boolean printFix = this.isTrue(_attribute);
            boolean _equals = value.equals("");
            if (_equals) {
              return "";
            }
            StringConcatenation _builder = new StringConcatenation();
            _builder.append(value, "");
            {
              if (printFix) {
                _builder.append(" FIX");
              }
            }
            _builder.append(" ; ");
            _builder.append(name, "");
            _xblockexpression_1 = (_builder.toString());
          }
          _xifexpression_1 = _xblockexpression_1;
        }
        _xblockexpression = (_xifexpression_1);
      }
      _xifexpression = _xblockexpression;
    }
    return _xifexpression;
  }
  
  public CharSequence printTheta(final ParameterDeclaration s) {
    List _list = s.getList();
    boolean _notEquals = (!Objects.equal(_list, null));
    if (_notEquals) {
      String name = s.getIdentifier();
      List _list_1 = s.getList();
      final String value = this.getAttribute(_list_1, "value");
      List _list_2 = s.getList();
      final String lo = this.getAttribute(_list_2, "lo");
      List _list_3 = s.getList();
      final String hi = this.getAttribute(_list_3, "hi");
      List _list_4 = s.getList();
      String _attribute = this.getAttribute(_list_4, "fix");
      final boolean printFix = this.isTrue(_attribute);
      boolean _equals = value.equals("");
      if (_equals) {
        return "";
      }
      boolean _and = false;
      boolean _equals_1 = lo.equals("");
      if (!_equals_1) {
        _and = false;
      } else {
        boolean _equals_2 = hi.equals("");
        _and = (_equals_1 && _equals_2);
      }
      if (_and) {
        StringConcatenation _builder = new StringConcatenation();
        _builder.append(value, "");
        {
          if (printFix) {
            _builder.append(" FIX");
          }
        }
        _builder.append(" ; ");
        _builder.append(name, "");
        return _builder;
      }
      boolean _equals_3 = lo.equals("");
      if (_equals_3) {
        StringConcatenation _builder_1 = new StringConcatenation();
        _builder_1.append("(-INF, ");
        _builder_1.append(value, "");
        _builder_1.append(", ");
        _builder_1.append(hi, "");
        _builder_1.append(")");
        {
          if (printFix) {
            _builder_1.append(" FIX");
          }
        }
        _builder_1.append(" ; ");
        _builder_1.append(name, "");
        return _builder_1;
      }
      boolean _equals_4 = hi.equals("");
      if (_equals_4) {
        StringConcatenation _builder_2 = new StringConcatenation();
        _builder_2.append("(");
        _builder_2.append(lo, "");
        _builder_2.append(", ");
        _builder_2.append(value, "");
        _builder_2.append(", INF)");
        {
          if (printFix) {
            _builder_2.append(" FIX");
          }
        }
        _builder_2.append(" ; ");
        _builder_2.append(name, "");
        return _builder_2;
      }
      StringConcatenation _builder_3 = new StringConcatenation();
      _builder_3.append("(");
      _builder_3.append(lo, "");
      _builder_3.append(", ");
      _builder_3.append(value, "");
      _builder_3.append(", ");
      _builder_3.append(hi, "");
      _builder_3.append(")");
      {
        if (printFix) {
          _builder_3.append(" FIX");
        }
      }
      _builder_3.append(" ; ");
      _builder_3.append(name, "");
      return _builder_3;
    }
    return null;
  }
  
  public String convertID(final String id) {
    int _indexOf = id.indexOf("_");
    boolean _greaterThan = (_indexOf > 0);
    if (_greaterThan) {
      Object _get = this.eta_vars.get(id);
      boolean _notEquals = (!Objects.equal(_get, null));
      if (_notEquals) {
        Object _get_1 = this.eta_vars.get(id);
        String _plus = ("ETA(" + _get_1);
        return (_plus + ")");
      }
      Object _get_2 = this.eps_vars.get(id);
      boolean _notEquals_1 = (!Objects.equal(_get_2, null));
      if (_notEquals_1) {
        Object _get_3 = this.eps_vars.get(id);
        String _plus_1 = ("EPS(" + _get_3);
        return (_plus_1 + ")");
      }
      Object _get_4 = this.theta_vars.get(id);
      boolean _notEquals_2 = (!Objects.equal(_get_4, null));
      if (_notEquals_2) {
        Object _get_5 = this.theta_vars.get(id);
        String _plus_2 = ("THETA(" + _get_5);
        return (_plus_2 + ")");
      }
    }
    Object _get_6 = this.dadt_vars.get(id);
    boolean _notEquals_3 = (!Objects.equal(_get_6, null));
    if (_notEquals_3) {
      Object _get_7 = this.dadt_vars.get(id);
      String _plus_3 = ("A(" + _get_7);
      return (_plus_3 + ")");
    }
    boolean _equalsIgnoreCase = id.equalsIgnoreCase("exp");
    if (_equalsIgnoreCase) {
      return "EXP";
    }
    boolean _equalsIgnoreCase_1 = id.equalsIgnoreCase("ln");
    if (_equalsIgnoreCase_1) {
      return "LOG";
    }
    return id;
  }
  
  public String convertOperator(final String op) {
    boolean _equals = op.equals("<");
    if (_equals) {
      return ".LT.";
    }
    boolean _equals_1 = op.equals(">");
    if (_equals_1) {
      return ".GT.";
    }
    boolean _equals_2 = op.equals("<=");
    if (_equals_2) {
      return ".LE.";
    }
    boolean _equals_3 = op.equals(">=");
    if (_equals_3) {
      return ".GE.";
    }
    boolean _equals_4 = op.equals("==");
    if (_equals_4) {
      return ".EQ.";
    }
    boolean _equals_5 = op.equals("^");
    if (_equals_5) {
      return "**";
    }
    boolean _equals_6 = op.equals("||");
    if (_equals_6) {
      return ".OR.";
    }
    boolean _equals_7 = op.equals("&&");
    if (_equals_7) {
      return ".AND.";
    }
    return op;
  }
  
  public CharSequence print(final TargetBlock b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _identifier = b.getIdentifier();
      boolean _equalsIgnoreCase = _identifier.equalsIgnoreCase("NMTRAN");
      if (_equalsIgnoreCase) {
        String _externalCode = b.getExternalCode();
        String _externalCode_1 = b.getExternalCode();
        int _length = _externalCode_1.length();
        int _minus = (_length - 3);
        String printedCode = _externalCode.substring(3, _minus);
        _builder.newLineIfNotEmpty();
        _builder.append(printedCode, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print(final ConditionalStatement s) {
    StringConcatenation _builder = new StringConcatenation();
    {
      ParExpression _parExpression = s.getParExpression();
      boolean _notEquals = (!Objects.equal(_parExpression, null));
      if (_notEquals) {
        _builder.append("IF ");
        ParExpression _parExpression_1 = s.getParExpression();
        CharSequence _print = this.print(_parExpression_1);
        _builder.append(_print, "");
        _builder.append(" THEN");
        _builder.newLineIfNotEmpty();
        {
          BlockStatement _ifStatement = s.getIfStatement();
          boolean _notEquals_1 = (!Objects.equal(_ifStatement, null));
          if (_notEquals_1) {
            _builder.append("\t");
            BlockStatement _ifStatement_1 = s.getIfStatement();
            CharSequence _print_1 = this.print(_ifStatement_1);
            _builder.append(_print_1, "	");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          Block _ifBlock = s.getIfBlock();
          boolean _notEquals_2 = (!Objects.equal(_ifBlock, null));
          if (_notEquals_2) {
            _builder.append("\t");
            Block _ifBlock_1 = s.getIfBlock();
            CharSequence _print_2 = this.print(_ifBlock_1);
            _builder.append(_print_2, "	");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          boolean _or = false;
          BlockStatement _elseStatement = s.getElseStatement();
          boolean _notEquals_3 = (!Objects.equal(_elseStatement, null));
          if (_notEquals_3) {
            _or = true;
          } else {
            Block _elseBlock = s.getElseBlock();
            boolean _notEquals_4 = (!Objects.equal(_elseBlock, null));
            _or = (_notEquals_3 || _notEquals_4);
          }
          if (_or) {
            _builder.append("ELSE ");
            _builder.newLine();
            {
              BlockStatement _elseStatement_1 = s.getElseStatement();
              boolean _notEquals_5 = (!Objects.equal(_elseStatement_1, null));
              if (_notEquals_5) {
                _builder.append("\t");
                BlockStatement _elseStatement_2 = s.getElseStatement();
                CharSequence _print_3 = this.print(_elseStatement_2);
                _builder.append(_print_3, "	");
                _builder.newLineIfNotEmpty();
              }
            }
            {
              Block _elseBlock_1 = s.getElseBlock();
              boolean _notEquals_6 = (!Objects.equal(_elseBlock_1, null));
              if (_notEquals_6) {
                _builder.append("\t");
                Block _elseBlock_2 = s.getElseBlock();
                CharSequence _print_4 = this.print(_elseBlock_2);
                _builder.append(_print_4, "	");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
}
