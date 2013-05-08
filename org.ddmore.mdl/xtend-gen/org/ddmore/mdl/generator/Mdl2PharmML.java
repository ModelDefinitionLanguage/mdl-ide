package org.ddmore.mdl.generator;

import com.google.common.base.Objects;
import org.ddmore.mdl.generator.Mdl2Nonmem;
import org.ddmore.mdl.mdl.any_expression;
import org.ddmore.mdl.mdl.argument;
import org.ddmore.mdl.mdl.arguments;
import org.ddmore.mdl.mdl.block;
import org.ddmore.mdl.mdl.block_statement;
import org.ddmore.mdl.mdl.conditional_expression;
import org.ddmore.mdl.mdl.data_obj;
import org.ddmore.mdl.mdl.estimation_block;
import org.ddmore.mdl.mdl.expression;
import org.ddmore.mdl.mdl.formal_arguments;
import org.ddmore.mdl.mdl.function_body;
import org.ddmore.mdl.mdl.function_call;
import org.ddmore.mdl.mdl.function_declaration;
import org.ddmore.mdl.mdl.function_subblock;
import org.ddmore.mdl.mdl.group_variables;
import org.ddmore.mdl.mdl.individual_model_obj_block;
import org.ddmore.mdl.mdl.list;
import org.ddmore.mdl.mdl.mcl;
import org.ddmore.mdl.mdl.mcl_obj;
import org.ddmore.mdl.mdl.model_obj;
import org.ddmore.mdl.mdl.model_obj_block;
import org.ddmore.mdl.mdl.model_prediction_obj_block;
import org.ddmore.mdl.mdl.observation_block;
import org.ddmore.mdl.mdl.ode_list;
import org.ddmore.mdl.mdl.param_obj;
import org.ddmore.mdl.mdl.primary;
import org.ddmore.mdl.mdl.random_list;
import org.ddmore.mdl.mdl.random_variable_definition_block;
import org.ddmore.mdl.mdl.simulation_block;
import org.ddmore.mdl.mdl.statement;
import org.ddmore.mdl.mdl.task_obj;
import org.ddmore.mdl.mdl.variable_declaration;
import org.ddmore.mdl.mdl.variable_name;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class Mdl2PharmML extends Mdl2Nonmem {
  public CharSequence convertToPharmML(final mcl m) {
    CharSequence _xblockexpression = null;
    {
      EList<mcl_obj> _objects = m.getObjects();
      for (final mcl_obj o : _objects) {
      }
      String version = "1.001";
      String date = "06.04.2013";
      String _plus = ("mdl2pharmML " + version);
      String _plus_1 = (_plus + " beta, last modification ");
      String _plus_2 = (_plus_1 + date);
      final String info = (_plus_2 + " Natallia Kokash (natallia.kokash@gmail.com)");
      StringConcatenation _builder = new StringConcatenation();
      CharSequence _print_XS_Comment = this.print_XS_Comment(info);
      _builder.append(_print_XS_Comment, "");
      _builder.newLineIfNotEmpty();
      _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      _builder.newLine();
      _builder.append("<PharmML ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("name=\"");
      Resource _eResource = m.eResource();
      String _fileName = this.fileName(_eResource);
      _builder.append(_fileName, "	");
      _builder.append("\"");
      _builder.newLineIfNotEmpty();
      _builder.append("\t");
      _builder.append("independentVar=\"t\" ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("writtenVersion=\"0.1\">");
      _builder.newLine();
      _builder.append("  \t\t\t");
      {
        EList<mcl_obj> _objects_1 = m.getObjects();
        for(final mcl_obj o_1 : _objects_1) {
          CharSequence _convertToPharmML = this.convertToPharmML(o_1);
          _builder.append(_convertToPharmML, "  			");
        }
      }
      _builder.newLineIfNotEmpty();
      _builder.append("</PharmML>");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence convertToPharmML(final mcl_obj o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      model_obj _model_obj = o.getModel_obj();
      boolean _notEquals = (!Objects.equal(_model_obj, null));
      if (_notEquals) {
        model_obj _model_obj_1 = o.getModel_obj();
        CharSequence _convertToPharmML = this.convertToPharmML(_model_obj_1);
        _builder.append(_convertToPharmML, "");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      data_obj _data_obj = o.getData_obj();
      boolean _notEquals_1 = (!Objects.equal(_data_obj, null));
      if (_notEquals_1) {
        data_obj _data_obj_1 = o.getData_obj();
        Object _convertToPharmML_1 = this.convertToPharmML(_data_obj_1);
        _builder.append(_convertToPharmML_1, "");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      param_obj _param_obj = o.getParam_obj();
      boolean _notEquals_2 = (!Objects.equal(_param_obj, null));
      if (_notEquals_2) {
        param_obj _param_obj_1 = o.getParam_obj();
        Object _convertToPharmML_2 = this.convertToPharmML(_param_obj_1);
        _builder.append(_convertToPharmML_2, "");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      task_obj _task_obj = o.getTask_obj();
      boolean _notEquals_3 = (!Objects.equal(_task_obj, null));
      if (_notEquals_3) {
        task_obj _task_obj_1 = o.getTask_obj();
        Object _convertToPharmML_3 = this.convertToPharmML(_task_obj_1);
        _builder.append(_convertToPharmML_3, "");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence convertToPharmML(final model_obj o) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ModelDefinition>");
    _builder.newLine();
    {
      EList<model_obj_block> _blocks = o.getBlocks();
      for(final model_obj_block b : _blocks) {
        {
          individual_model_obj_block _individual_model_obj_block = b.getIndividual_model_obj_block();
          boolean _notEquals = (!Objects.equal(_individual_model_obj_block, null));
          if (_notEquals) {
            _builder.append("TODO: Process individual model block");
            _builder.newLine();
          }
        }
        {
          model_prediction_obj_block _model_prediction_obj_block = b.getModel_prediction_obj_block();
          boolean _notEquals_1 = (!Objects.equal(_model_prediction_obj_block, null));
          if (_notEquals_1) {
            _builder.append("TODO: Process model prediction block");
            _builder.newLine();
          }
        }
        {
          random_variable_definition_block _random_variable_definition_block = b.getRandom_variable_definition_block();
          boolean _notEquals_2 = (!Objects.equal(_random_variable_definition_block, null));
          if (_notEquals_2) {
            _builder.append("TODO: Process random variable definition block");
            _builder.newLine();
          }
        }
        _builder.newLine();
        {
          group_variables _group_variables = b.getGroup_variables();
          boolean _notEquals_3 = (!Objects.equal(_group_variables, null));
          if (_notEquals_3) {
            _builder.append("TODO: Process group variables");
            _builder.newLine();
          }
        }
        {
          observation_block _observation_block = b.getObservation_block();
          boolean _notEquals_4 = (!Objects.equal(_observation_block, null));
          if (_notEquals_4) {
            _builder.append("TODO: Process observation block");
            _builder.newLine();
          }
        }
        {
          estimation_block _estimation_block = b.getEstimation_block();
          boolean _notEquals_5 = (!Objects.equal(_estimation_block, null));
          if (_notEquals_5) {
            {
              estimation_block _estimation_block_1 = b.getEstimation_block();
              block _block = _estimation_block_1.getBlock();
              boolean _notEquals_6 = (!Objects.equal(_block, null));
              if (_notEquals_6) {
                estimation_block _estimation_block_2 = b.getEstimation_block();
                block _block_1 = _estimation_block_2.getBlock();
                CharSequence _print_msteps_EstimationStep = this.print_msteps_EstimationStep(_block_1);
                _builder.append(_print_msteps_EstimationStep, "");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
        {
          simulation_block _simulation_block = b.getSimulation_block();
          boolean _notEquals_7 = (!Objects.equal(_simulation_block, null));
          if (_notEquals_7) {
            {
              simulation_block _simulation_block_1 = b.getSimulation_block();
              block _block_2 = _simulation_block_1.getBlock();
              boolean _notEquals_8 = (!Objects.equal(_block_2, null));
              if (_notEquals_8) {
                simulation_block _simulation_block_2 = b.getSimulation_block();
                block _block_3 = _simulation_block_2.getBlock();
                CharSequence _print_msteps_SimulationStep = this.print_msteps_SimulationStep(_block_3);
                _builder.append(_print_msteps_SimulationStep, "");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    _builder.append("</ModelDefinition>");
    _builder.newLine();
    return _builder;
  }
  
  public Object convertToPharmML(final data_obj obj) {
    return null;
  }
  
  public Object convertToPharmML(final param_obj obj) {
    return null;
  }
  
  public Object convertToPharmML(final task_obj obj) {
    return null;
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
  
  public CharSequence print_ct_VariableDefinitionType(final variable_declaration v) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Variable ");
    variable_name _identifier = v.getIdentifier();
    CharSequence _print_ct_SymbId = this.print_ct_SymbId(_identifier);
    _builder.append(_print_ct_SymbId, "");
    _builder.append(">");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    variable_name _identifier_1 = v.getIdentifier();
    String _str = this.toStr(_identifier_1);
    CharSequence _print_ct_AnnotationType = this.print_ct_AnnotationType(_str);
    _builder.append(_print_ct_AnnotationType, "	");
    _builder.newLineIfNotEmpty();
    {
      any_expression _expression = v.getExpression();
      boolean _notEquals = (!Objects.equal(_expression, null));
      if (_notEquals) {
        _builder.append("\t");
        any_expression _expression_1 = v.getExpression();
        CharSequence _print_ct_printRhsType = this.print_ct_printRhsType(_expression_1);
        _builder.append(_print_ct_printRhsType, "	");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      random_list _random_list = v.getRandom_list();
      boolean _notEquals_1 = (!Objects.equal(_random_list, null));
      if (_notEquals_1) {
        _builder.append("\t");
        random_list _random_list_1 = v.getRandom_list();
        CharSequence _print_uncert_DistributionType = this.print_uncert_DistributionType(_random_list_1);
        _builder.append(_print_uncert_DistributionType, "	");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("</Variable>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_uncert_DistributionType(final random_list list) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("TODO: print random list (distribution)\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_ct_printRhsType(final any_expression e) {
    StringConcatenation _builder = new StringConcatenation();
    {
      expression _expression = e.getExpression();
      boolean _notEquals = (!Objects.equal(_expression, null));
      if (_notEquals) {
        expression _expression_1 = e.getExpression();
        CharSequence _print_ct_printRhsType = this.print_ct_printRhsType(_expression_1);
        _builder.append(_print_ct_printRhsType, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      list _list = e.getList();
      boolean _notEquals_1 = (!Objects.equal(_list, null));
      if (_notEquals_1) {
        _builder.append("TODO: print list");
        _builder.newLine();
      }
    }
    {
      ode_list _ode_list = e.getOde_list();
      boolean _notEquals_2 = (!Objects.equal(_ode_list, null));
      if (_notEquals_2) {
        _builder.append("TODO: print ode list");
        _builder.newLine();
      }
    }
    {
      random_list _random_list = e.getRandom_list();
      boolean _notEquals_3 = (!Objects.equal(_random_list, null));
      if (_notEquals_3) {
        random_list _random_list_1 = e.getRandom_list();
        CharSequence _print_uncert_DistributionType = this.print_uncert_DistributionType(_random_list_1);
        _builder.append(_print_uncert_DistributionType, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print_ct_printRhsType(final expression e) {
    StringConcatenation _builder = new StringConcatenation();
    {
      conditional_expression _conditional_expression = e.getConditional_expression();
      boolean _notEquals = (!Objects.equal(_conditional_expression, null));
      if (_notEquals) {
      }
    }
    {
      EList<String> _string_expression = e.getString_expression();
      boolean _notEquals_1 = (!Objects.equal(_string_expression, null));
      if (_notEquals_1) {
        EList<String> _string_expression_1 = e.getString_expression();
        CharSequence _print_MathStringType = this.print_MathStringType(_string_expression_1);
        _builder.append(_print_MathStringType, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print_MathStringType(final EList<String> list) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence printXML(final block_statement st) {
    StringConcatenation _builder = new StringConcatenation();
    {
      variable_declaration _variable_declaration = st.getVariable_declaration();
      boolean _notEquals = (!Objects.equal(_variable_declaration, null));
      if (_notEquals) {
        variable_declaration _variable_declaration_1 = st.getVariable_declaration();
        CharSequence _print_ct_VariableDefinitionType = this.print_ct_VariableDefinitionType(_variable_declaration_1);
        _builder.append(_print_ct_VariableDefinitionType, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      function_call _function_call = st.getFunction_call();
      boolean _notEquals_1 = (!Objects.equal(_function_call, null));
      if (_notEquals_1) {
        function_call _function_call_1 = st.getFunction_call();
        CharSequence _print_Math_FunctionCallType = this.print_Math_FunctionCallType(_function_call_1);
        _builder.append(_print_Math_FunctionCallType, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      statement _statement = st.getStatement();
      boolean _notEquals_2 = (!Objects.equal(_statement, null));
      if (_notEquals_2) {
        _builder.append("TODO: parse MDL block");
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence print_Math_ScalarType(final primary p) {
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
  
  public CharSequence print_ct_SymbId(final primary p) {
    StringConcatenation _builder = new StringConcatenation();
    {
      variable_name _identifier = p.getIdentifier();
      boolean _notEquals = (!Objects.equal(_identifier, null));
      if (_notEquals) {
        variable_name _identifier_1 = p.getIdentifier();
        CharSequence _print_ct_SymbId = this.print_ct_SymbId(_identifier_1);
        _builder.append(_print_ct_SymbId, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print_ct_SymbId(final variable_name name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("symbId = \"");
    String _str = this.toStr(name);
    _builder.append(_str, "");
    _builder.append("\"");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence printXML(final function_declaration f) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  public CharSequence printXML(final formal_arguments args) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<String> _identifiers = args.getIdentifiers();
      for(final String a : _identifiers) {
      }
    }
    return _builder;
  }
  
  public CharSequence printXML(final function_body body) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<function_subblock> _blocks = body.getBlocks();
      for(final function_subblock b : _blocks) {
        {
          block _estimate_defn = b.getEstimate_defn();
          boolean _notEquals = (!Objects.equal(_estimate_defn, null));
          if (_notEquals) {
            block _estimate_defn_1 = b.getEstimate_defn();
            CharSequence _print_msteps_SimulationStep = this.print_msteps_SimulationStep(_estimate_defn_1);
            _builder.append(_print_msteps_SimulationStep, "");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          block _simulate_defn = b.getSimulate_defn();
          boolean _notEquals_1 = (!Objects.equal(_simulate_defn, null));
          if (_notEquals_1) {
            block _simulate_defn_1 = b.getSimulate_defn();
            CharSequence _print_msteps_EstimationStep = this.print_msteps_EstimationStep(_simulate_defn_1);
            _builder.append(_print_msteps_EstimationStep, "");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence print_msteps_EstimationStep(final block b) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<EstimationStep>");
    _builder.newLine();
    {
      EList<block_statement> _statements = b.getStatements();
      for(final block_statement st : _statements) {
        _builder.append("TODO: print estimation steps");
        _builder.newLine();
      }
    }
    _builder.append("</EstimationStep>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_msteps_SimulationStep(final block b) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<SimulationStep>");
    _builder.newLine();
    {
      EList<block_statement> _statements = b.getStatements();
      for(final block_statement st : _statements) {
        _builder.append("TODO: print simulation steps");
        _builder.newLine();
      }
    }
    _builder.append("</SimulationStep>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_Math_FunctionCallType(final function_call call) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<FunctionCall>");
    _builder.newLine();
    _builder.append("\t");
    String _funct_name = call.getFunct_name();
    CharSequence _print_Math_VarType = this.print_Math_VarType(_funct_name);
    _builder.append(_print_Math_VarType, "	");
    _builder.newLineIfNotEmpty();
    {
      arguments _arguments = call.getArguments();
      EList<argument> _arguments_1 = _arguments.getArguments();
      for(final argument arg : _arguments_1) {
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
  
  public CharSequence print_Math_VarType(final String str) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Var ");
    CharSequence _print_Math_symbId = this.print_Math_symbId(str);
    _builder.append(_print_Math_symbId, "");
    _builder.append("/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print_Math_FunctionArgumentType(final argument arg) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<FunctionArgument ");
    _builder.newLine();
    {
      String _identifier = arg.getIdentifier();
      boolean _notEquals = (!Objects.equal(_identifier, null));
      if (_notEquals) {
        String _identifier_1 = arg.getIdentifier();
        CharSequence _print_Math_symbId = this.print_Math_symbId(_identifier_1);
        _builder.append(_print_Math_symbId, "");
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
