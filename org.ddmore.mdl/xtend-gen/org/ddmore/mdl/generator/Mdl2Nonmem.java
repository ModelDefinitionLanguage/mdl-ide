package org.ddmore.mdl.generator;

import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.HashMap;
import org.ddmore.mdl.generator.MdlPrinting;
import org.ddmore.mdl.mdl.any_expression;
import org.ddmore.mdl.mdl.argument;
import org.ddmore.mdl.mdl.arguments;
import org.ddmore.mdl.mdl.block;
import org.ddmore.mdl.mdl.block_statement;
import org.ddmore.mdl.mdl.block_subblock;
import org.ddmore.mdl.mdl.data_block;
import org.ddmore.mdl.mdl.data_obj;
import org.ddmore.mdl.mdl.data_obj_block;
import org.ddmore.mdl.mdl.diag_subblock;
import org.ddmore.mdl.mdl.expression;
import org.ddmore.mdl.mdl.file_block;
import org.ddmore.mdl.mdl.file_block_content;
import org.ddmore.mdl.mdl.file_block_statement;
import org.ddmore.mdl.mdl.function_body;
import org.ddmore.mdl.mdl.function_declaration;
import org.ddmore.mdl.mdl.function_subblock;
import org.ddmore.mdl.mdl.group_variables;
import org.ddmore.mdl.mdl.header_block;
import org.ddmore.mdl.mdl.individual_model_obj_block;
import org.ddmore.mdl.mdl.library_block;
import org.ddmore.mdl.mdl.list;
import org.ddmore.mdl.mdl.mcl;
import org.ddmore.mdl.mdl.mcl_obj;
import org.ddmore.mdl.mdl.model_block;
import org.ddmore.mdl.mdl.model_block_statement;
import org.ddmore.mdl.mdl.model_obj;
import org.ddmore.mdl.mdl.model_obj_block;
import org.ddmore.mdl.mdl.model_prediction_obj_block;
import org.ddmore.mdl.mdl.observation_block;
import org.ddmore.mdl.mdl.ode_block;
import org.ddmore.mdl.mdl.ode_list;
import org.ddmore.mdl.mdl.output_variables_block;
import org.ddmore.mdl.mdl.par_expression;
import org.ddmore.mdl.mdl.param_obj;
import org.ddmore.mdl.mdl.param_obj_block;
import org.ddmore.mdl.mdl.random_list;
import org.ddmore.mdl.mdl.random_variable_definition_block;
import org.ddmore.mdl.mdl.statement;
import org.ddmore.mdl.mdl.structural_block;
import org.ddmore.mdl.mdl.structural_parameters_block;
import org.ddmore.mdl.mdl.target_block;
import org.ddmore.mdl.mdl.task_obj;
import org.ddmore.mdl.mdl.task_obj_block;
import org.ddmore.mdl.mdl.variability_block;
import org.ddmore.mdl.mdl.variability_block_content;
import org.ddmore.mdl.mdl.variability_block_statement;
import org.ddmore.mdl.mdl.variability_subblock;
import org.ddmore.mdl.mdl.variable_declaration;
import org.ddmore.mdl.mdl.variable_name;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Functions.Function0;

@SuppressWarnings("all")
public class Mdl2Nonmem extends MdlPrinting {
  private task_obj task_object = null;
  
  public CharSequence convertToNonmem(final mcl m) {
    CharSequence _xblockexpression = null;
    {
      EList<mcl_obj> _objects = m.getObjects();
      for (final mcl_obj o : _objects) {
        {
          this.prepareCollections(o);
          task_obj _task_obj = o.getTask_obj();
          boolean _notEquals = (!Objects.equal(_task_obj, null));
          if (_notEquals) {
            task_obj _task_obj_1 = o.getTask_obj();
            this.task_object = _task_obj_1;
          }
        }
      }
      String version = "1.005";
      String date = "14.03.2013";
      StringConcatenation _builder = new StringConcatenation();
      _builder.append(";mdl2nt ");
      _builder.append(version, "");
      _builder.append(" beta, last modification ");
      _builder.append(date, "");
      _builder.append(", Natallia Kokash (natallia.kokash@gmail.com)  ");
      _builder.newLineIfNotEmpty();
      _builder.newLine();
      _builder.append("$PROB ");
      String _fileNameUpperCase = this.fileNameUpperCase(m);
      _builder.append(_fileNameUpperCase, "");
      _builder.newLineIfNotEmpty();
      _builder.append("  \t\t");
      {
        EList<mcl_obj> _objects_1 = m.getObjects();
        for(final mcl_obj o_1 : _objects_1) {
          CharSequence _convertToNonmem = this.convertToNonmem(o_1);
          _builder.append(_convertToNonmem, "  		");
        }
      }
      _builder.newLineIfNotEmpty();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence convertToNonmem(final mcl_obj o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      data_obj _data_obj = o.getData_obj();
      boolean _notEquals = (!Objects.equal(_data_obj, null));
      if (_notEquals) {
        data_obj _data_obj_1 = o.getData_obj();
        CharSequence _convertToNonmem = this.convertToNonmem(_data_obj_1);
        _builder.append(_convertToNonmem, "");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      model_obj _model_obj = o.getModel_obj();
      boolean _notEquals_1 = (!Objects.equal(_model_obj, null));
      if (_notEquals_1) {
        model_obj _model_obj_1 = o.getModel_obj();
        CharSequence _convertToNonmem_1 = this.convertToNonmem(_model_obj_1);
        _builder.append(_convertToNonmem_1, "");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      param_obj _param_obj = o.getParam_obj();
      boolean _notEquals_2 = (!Objects.equal(_param_obj, null));
      if (_notEquals_2) {
        param_obj _param_obj_1 = o.getParam_obj();
        CharSequence _convertToNonmem_2 = this.convertToNonmem(_param_obj_1);
        _builder.append(_convertToNonmem_2, "");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      task_obj _task_obj = o.getTask_obj();
      boolean _notEquals_3 = (!Objects.equal(_task_obj, null));
      if (_notEquals_3) {
        task_obj _task_obj_1 = o.getTask_obj();
        CharSequence _convertToNonmem_3 = this.convertToNonmem(_task_obj_1);
        _builder.append(_convertToNonmem_3, "");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence convertToNonmem(final model_obj o) {
    CharSequence _xblockexpression = null;
    {
      final boolean isLibraryDefined = this.isLibraryDefined(o);
      final boolean isPKDefined = this.isPKDefined(o);
      final boolean isErrorNonEmpty = this.isErrorNonEmpty(o);
      final boolean isODEDefined = this.isODEDefined(o);
      StringConcatenation _builder = new StringConcatenation();
      {
        if (isODEDefined) {
          _builder.newLine();
          _builder.append("$MODEL");
          _builder.newLine();
          _builder.append("\t");
          CharSequence _printModel = this.printModel(o);
          _builder.append(_printModel, "	");
          _builder.newLineIfNotEmpty();
        }
      }
      _builder.newLine();
      {
        if (isLibraryDefined) {
          {
            if (isPKDefined) {
              _builder.newLine();
              CharSequence _printSUBR = this.printSUBR(o);
              _builder.append(_printSUBR, "");
              _builder.newLineIfNotEmpty();
              _builder.append("\t");
              _builder.newLine();
              _builder.append("$PK ");
              _builder.newLine();
              _builder.append("\t");
              CharSequence _printPK = this.printPK(o);
              _builder.append(_printPK, "	");
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
              _builder.newLine();
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
              CharSequence _printError_1 = this.printError(o);
              _builder.append(_printError_1, "	");
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
  
  public CharSequence printPK(final model_obj o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<model_obj_block> _blocks = o.getBlocks();
      for(final model_obj_block b : _blocks) {
        {
          group_variables _group_variables = b.getGroup_variables();
          boolean _notEquals = (!Objects.equal(_group_variables, null));
          if (_notEquals) {
            group_variables _group_variables_1 = b.getGroup_variables();
            block _block = _group_variables_1.getBlock();
            CharSequence _print = this.print(_block);
            _builder.append(_print, "");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          individual_model_obj_block _individual_model_obj_block = b.getIndividual_model_obj_block();
          boolean _notEquals_1 = (!Objects.equal(_individual_model_obj_block, null));
          if (_notEquals_1) {
            individual_model_obj_block bb = b.getIndividual_model_obj_block();
            _builder.newLineIfNotEmpty();
            {
              block _block_1 = bb.getBlock();
              EList<block_statement> _statements = _block_1.getStatements();
              boolean _hasElements = false;
              for(final block_statement s : _statements) {
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
      }
    }
    return _builder;
  }
  
  public CharSequence printError(final model_obj o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<model_obj_block> _blocks = o.getBlocks();
      for(final model_obj_block mob : _blocks) {
        {
          model_prediction_obj_block _model_prediction_obj_block = mob.getModel_prediction_obj_block();
          boolean _notEquals = (!Objects.equal(_model_prediction_obj_block, null));
          if (_notEquals) {
            {
              model_prediction_obj_block _model_prediction_obj_block_1 = mob.getModel_prediction_obj_block();
              model_block _block = _model_prediction_obj_block_1.getBlock();
              EList<model_block_statement> _statements = _block.getStatements();
              for(final model_block_statement s : _statements) {
                {
                  block_statement _statement = s.getStatement();
                  boolean _notEquals_1 = (!Objects.equal(_statement, null));
                  if (_notEquals_1) {
                    block_statement _statement_1 = s.getStatement();
                    variable_declaration x = _statement_1.getVariable_declaration();
                    _builder.newLineIfNotEmpty();
                    {
                      boolean _notEquals_2 = (!Objects.equal(x, null));
                      if (_notEquals_2) {
                        {
                          any_expression _expression = x.getExpression();
                          boolean _notEquals_3 = (!Objects.equal(_expression, null));
                          if (_notEquals_3) {
                            {
                              any_expression _expression_1 = x.getExpression();
                              expression _expression_2 = _expression_1.getExpression();
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
                      block_statement _statement_2 = s.getStatement();
                      statement _statement_3 = _statement_2.getStatement();
                      boolean _notEquals_5 = (!Objects.equal(_statement_3, null));
                      if (_notEquals_5) {
                        block_statement _statement_4 = s.getStatement();
                        statement _statement_5 = _statement_4.getStatement();
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
          observation_block _observation_block = mob.getObservation_block();
          boolean _notEquals_6 = (!Objects.equal(_observation_block, null));
          if (_notEquals_6) {
            observation_block _observation_block_1 = mob.getObservation_block();
            block _block_1 = _observation_block_1.getBlock();
            CharSequence _print_2 = this.print(_block_1);
            _builder.append(_print_2, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence printModel(final model_obj o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<model_obj_block> _blocks = o.getBlocks();
      for(final model_obj_block b : _blocks) {
        {
          model_prediction_obj_block _model_prediction_obj_block = b.getModel_prediction_obj_block();
          boolean _notEquals = (!Objects.equal(_model_prediction_obj_block, null));
          if (_notEquals) {
            model_prediction_obj_block bb = b.getModel_prediction_obj_block();
            _builder.newLineIfNotEmpty();
            {
              model_block _block = bb.getBlock();
              EList<model_block_statement> _statements = _block.getStatements();
              for(final model_block_statement s : _statements) {
                {
                  ode_block _ode_block = s.getOde_block();
                  boolean _notEquals_1 = (!Objects.equal(_ode_block, null));
                  if (_notEquals_1) {
                    {
                      ode_block _ode_block_1 = s.getOde_block();
                      block _block_1 = _ode_block_1.getBlock();
                      EList<block_statement> _statements_1 = _block_1.getStatements();
                      for(final block_statement ss : _statements_1) {
                        variable_declaration x = ss.getVariable_declaration();
                        _builder.newLineIfNotEmpty();
                        {
                          boolean _notEquals_2 = (!Objects.equal(x, null));
                          if (_notEquals_2) {
                            {
                              any_expression _expression = x.getExpression();
                              boolean _notEquals_3 = (!Objects.equal(_expression, null));
                              if (_notEquals_3) {
                                {
                                  any_expression _expression_1 = x.getExpression();
                                  ode_list _ode_list = _expression_1.getOde_list();
                                  boolean _notEquals_4 = (!Objects.equal(_ode_list, null));
                                  if (_notEquals_4) {
                                    _builder.append("COMP(");
                                    variable_name _identifier = x.getIdentifier();
                                    String _str = this.toStr(_identifier);
                                    _builder.append(_str, "");
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
  
  public CharSequence printDES(final model_obj o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<model_obj_block> _blocks = o.getBlocks();
      for(final model_obj_block b : _blocks) {
        {
          model_prediction_obj_block _model_prediction_obj_block = b.getModel_prediction_obj_block();
          boolean _notEquals = (!Objects.equal(_model_prediction_obj_block, null));
          if (_notEquals) {
            model_prediction_obj_block _model_prediction_obj_block_1 = b.getModel_prediction_obj_block();
            model_block bb = _model_prediction_obj_block_1.getBlock();
            _builder.newLineIfNotEmpty();
            {
              EList<model_block_statement> _statements = bb.getStatements();
              for(final model_block_statement s : _statements) {
                {
                  ode_block _ode_block = s.getOde_block();
                  boolean _notEquals_1 = (!Objects.equal(_ode_block, null));
                  if (_notEquals_1) {
                    {
                      ode_block _ode_block_1 = s.getOde_block();
                      block _block = _ode_block_1.getBlock();
                      EList<block_statement> _statements_1 = _block.getStatements();
                      for(final block_statement ss : _statements_1) {
                        variable_declaration x = ss.getVariable_declaration();
                        _builder.newLineIfNotEmpty();
                        {
                          boolean _notEquals_2 = (!Objects.equal(x, null));
                          if (_notEquals_2) {
                            {
                              any_expression _expression = x.getExpression();
                              boolean _notEquals_3 = (!Objects.equal(_expression, null));
                              if (_notEquals_3) {
                                {
                                  any_expression _expression_1 = x.getExpression();
                                  expression _expression_2 = _expression_1.getExpression();
                                  boolean _notEquals_4 = (!Objects.equal(_expression_2, null));
                                  if (_notEquals_4) {
                                    CharSequence _print = this.print(x);
                                    _builder.append(_print, "");
                                    _builder.newLineIfNotEmpty();
                                  }
                                }
                                {
                                  any_expression _expression_3 = x.getExpression();
                                  ode_list _ode_list = _expression_3.getOde_list();
                                  boolean _notEquals_5 = (!Objects.equal(_ode_list, null));
                                  if (_notEquals_5) {
                                    any_expression _expression_4 = x.getExpression();
                                    ode_list _ode_list_1 = _expression_4.getOde_list();
                                    String deriv = this.getVariableAttribute(_ode_list_1, "deriv");
                                    _builder.newLineIfNotEmpty();
                                    {
                                      boolean _notEquals_6 = (!Objects.equal(deriv, null));
                                      if (_notEquals_6) {
                                        variable_name _identifier = x.getIdentifier();
                                        String id = this.toStr(_identifier);
                                        _builder.newLineIfNotEmpty();
                                        {
                                          Object _get = this.dadt_vars.get(id);
                                          boolean _notEquals_7 = (!Objects.equal(_get, null));
                                          if (_notEquals_7) {
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
                          statement _statement = ss.getStatement();
                          boolean _notEquals_8 = (!Objects.equal(_statement, null));
                          if (_notEquals_8) {
                            statement _statement_1 = ss.getStatement();
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
  
  public CharSequence printSUBR(final model_obj o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<model_obj_block> _blocks = o.getBlocks();
      for(final model_obj_block b : _blocks) {
        {
          model_prediction_obj_block _model_prediction_obj_block = b.getModel_prediction_obj_block();
          boolean _notEquals = (!Objects.equal(_model_prediction_obj_block, null));
          if (_notEquals) {
            model_prediction_obj_block _model_prediction_obj_block_1 = b.getModel_prediction_obj_block();
            model_block _block = _model_prediction_obj_block_1.getBlock();
            CharSequence _printSUBR = this.printSUBR(_block);
            _builder.append(_printSUBR, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence printSUBR(final model_block b) {
    EList<model_block_statement> _statements = b.getStatements();
    for (final model_block_statement ss : _statements) {
      library_block _library_block = ss.getLibrary_block();
      boolean _notEquals = (!Objects.equal(_library_block, null));
      if (_notEquals) {
        library_block _library_block_1 = ss.getLibrary_block();
        block _block = _library_block_1.getBlock();
        boolean _notEquals_1 = (!Objects.equal(_block, null));
        if (_notEquals_1) {
          library_block _library_block_2 = ss.getLibrary_block();
          block _block_1 = _library_block_2.getBlock();
          EList<block_statement> _statements_1 = _block_1.getStatements();
          for (final block_statement s : _statements_1) {
            variable_declaration _variable_declaration = s.getVariable_declaration();
            boolean _notEquals_2 = (!Objects.equal(_variable_declaration, null));
            if (_notEquals_2) {
              variable_declaration _variable_declaration_1 = s.getVariable_declaration();
              variable_name _identifier = _variable_declaration_1.getIdentifier();
              String _str = this.toStr(_identifier);
              boolean _equalsIgnoreCase = _str.equalsIgnoreCase("amount");
              if (_equalsIgnoreCase) {
                variable_declaration _variable_declaration_2 = s.getVariable_declaration();
                any_expression _expression = _variable_declaration_2.getExpression();
                boolean _notEquals_3 = (!Objects.equal(_expression, null));
                if (_notEquals_3) {
                  variable_declaration _variable_declaration_3 = s.getVariable_declaration();
                  any_expression _expression_1 = _variable_declaration_3.getExpression();
                  list _list = _expression_1.getList();
                  boolean _notEquals_4 = (!Objects.equal(_list, null));
                  if (_notEquals_4) {
                    variable_declaration _variable_declaration_4 = s.getVariable_declaration();
                    any_expression _expression_2 = _variable_declaration_4.getExpression();
                    list _list_1 = _expression_2.getList();
                    String _variableAttribute = this.getVariableAttribute(_list_1, "library");
                    String library = _variableAttribute.toString();
                    String _substring = library.substring(0, 2);
                    boolean _equalsIgnoreCase_1 = _substring.equalsIgnoreCase("nm");
                    if (_equalsIgnoreCase_1) {
                      String _substring_1 = library.substring(2);
                      library = _substring_1;
                    }
                    variable_declaration _variable_declaration_5 = s.getVariable_declaration();
                    any_expression _expression_3 = _variable_declaration_5.getExpression();
                    list _list_2 = _expression_3.getList();
                    String _variableAttribute_1 = this.getVariableAttribute(_list_2, "model");
                    final String model = _variableAttribute_1.toString();
                    variable_declaration _variable_declaration_6 = s.getVariable_declaration();
                    any_expression _expression_4 = _variable_declaration_6.getExpression();
                    list _list_3 = _expression_4.getList();
                    final String trans = this.getVariableAttribute(_list_3, "trans");
                    String tolStr = "";
                    ArrayList<Object> tol = this.getTOL(b);
                    int _size = tol.size();
                    boolean _greaterThan = (_size > 0);
                    if (_greaterThan) {
                      boolean _and = false;
                      boolean _equalsIgnoreCase_2 = model.equalsIgnoreCase("9");
                      if (!_equalsIgnoreCase_2) {
                        _and = false;
                      } else {
                        boolean _equalsIgnoreCase_3 = library.equalsIgnoreCase("advan");
                        _and = (_equalsIgnoreCase_2 && _equalsIgnoreCase_3);
                      }
                      if (_and) {
                        tolStr = "\n$TOL";
                        for (final Object tolEl : tol) {
                          String _plus = (tolStr + " NRD(");
                          String _plus_1 = (_plus + tolEl);
                          String _plus_2 = (_plus_1 + ")");
                          tolStr = _plus_2;
                        }
                      } else {
                        Object _get = tol.get(0);
                        String _plus_3 = (" TOL=" + _get);
                        tolStr = _plus_3;
                      }
                    }
                    StringConcatenation _builder = new StringConcatenation();
                    _builder.append("$SUBR ");
                    {
                      boolean _notEquals_5 = (!Objects.equal(model, null));
                      if (_notEquals_5) {
                        String _upperCase = library.toUpperCase();
                        _builder.append(_upperCase, "");
                        _builder.append(model, "");
                      }
                    }
                    _builder.append(tolStr, "");
                    _builder.append(" ");
                    {
                      boolean _notEquals_6 = (!Objects.equal(trans, null));
                      if (_notEquals_6) {
                        _builder.append("TRANS");
                        _builder.append(trans, "");
                      }
                    }
                    return _builder;
                  }
                }
              }
            }
          }
        }
      }
    }
    return null;
  }
  
  public ArrayList<Object> getTOL(final model_block b) {
    ArrayList<Object> tol = CollectionLiterals.<Object>newArrayList();
    EList<model_block_statement> _statements = b.getStatements();
    for (final model_block_statement s : _statements) {
      ode_block _ode_block = s.getOde_block();
      boolean _notEquals = (!Objects.equal(_ode_block, null));
      if (_notEquals) {
        ode_block _ode_block_1 = s.getOde_block();
        block _block = _ode_block_1.getBlock();
        EList<block_statement> _statements_1 = _block.getStatements();
        for (final block_statement ss : _statements_1) {
          {
            variable_declaration x = ss.getVariable_declaration();
            boolean _notEquals_1 = (!Objects.equal(x, null));
            if (_notEquals_1) {
              any_expression _expression = x.getExpression();
              boolean _notEquals_2 = (!Objects.equal(_expression, null));
              if (_notEquals_2) {
                any_expression _expression_1 = x.getExpression();
                ode_list _ode_list = _expression_1.getOde_list();
                boolean _notEquals_3 = (!Objects.equal(_ode_list, null));
                if (_notEquals_3) {
                  any_expression _expression_2 = x.getExpression();
                  ode_list _ode_list_1 = _expression_2.getOde_list();
                  String tolEl = this.getVariableAttribute(_ode_list_1, "tolrel");
                  boolean _notEquals_4 = (!Objects.equal(tolEl, null));
                  if (_notEquals_4) {
                    tol.add(tolEl);
                  }
                }
              }
            }
          }
        }
      }
    }
    return tol;
  }
  
  public CharSequence printTable(final model_obj o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<model_obj_block> _blocks = o.getBlocks();
      for(final model_obj_block b : _blocks) {
        {
          output_variables_block _output_variables_block = b.getOutput_variables_block();
          boolean _notEquals = (!Objects.equal(_output_variables_block, null));
          if (_notEquals) {
            output_variables_block bb = b.getOutput_variables_block();
            _builder.newLineIfNotEmpty();
            {
              block _block = bb.getBlock();
              EList<block_statement> _statements = _block.getStatements();
              int _size = _statements.size();
              boolean _greaterThan = (_size > 0);
              if (_greaterThan) {
                _builder.append("$TABLE ");
                {
                  block _block_1 = bb.getBlock();
                  EList<block_statement> _statements_1 = _block_1.getStatements();
                  boolean _hasElements = false;
                  for(final block_statement st : _statements_1) {
                    if (!_hasElements) {
                      _hasElements = true;
                    } else {
                      _builder.appendImmediate(" ", "");
                    }
                    {
                      variable_declaration _variable_declaration = st.getVariable_declaration();
                      boolean _notEquals_1 = (!Objects.equal(_variable_declaration, null));
                      if (_notEquals_1) {
                        variable_declaration _variable_declaration_1 = st.getVariable_declaration();
                        variable_name _identifier = _variable_declaration_1.getIdentifier();
                        String _str = this.toStr(_identifier);
                        String _convertID = this.convertID(_str);
                        _builder.append(_convertID, "");
                      }
                    }
                  }
                }
                _builder.newLineIfNotEmpty();
                _builder.append("ONEHEADER NOPRINT ");
                {
                  boolean _notEquals_2 = (!Objects.equal(this.task_object, null));
                  if (_notEquals_2) {
                    _builder.append("FILE=");
                    String _identifier_1 = this.task_object.getIdentifier();
                    _builder.append(_identifier_1, "");
                    _builder.append(".fit");
                  }
                }
                _builder.append(" ");
                _builder.newLineIfNotEmpty();
              }
            }
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence convertToNonmem(final param_obj obj) {
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
  
  public CharSequence printTheta(final param_obj obj) {
    StringConcatenation _builder = new StringConcatenation();
    {
      boolean _isThetaNonEmpty = this.isThetaNonEmpty(obj);
      if (_isThetaNonEmpty) {
        _builder.newLine();
        _builder.append("$THETA");
        _builder.newLine();
        {
          EList<param_obj_block> _blocks = obj.getBlocks();
          for(final param_obj_block b : _blocks) {
            {
              structural_block _structural_block = b.getStructural_block();
              boolean _notEquals = (!Objects.equal(_structural_block, null));
              if (_notEquals) {
                {
                  structural_block _structural_block_1 = b.getStructural_block();
                  block _block = _structural_block_1.getBlock();
                  EList<block_statement> _statements = _block.getStatements();
                  for(final block_statement st : _statements) {
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
    return _builder;
  }
  
  public CharSequence printOmega(final param_obj obj) {
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
          EList<param_obj_block> _blocks = obj.getBlocks();
          for(final param_obj_block b : _blocks) {
            {
              variability_block _variability_block = b.getVariability_block();
              boolean _notEquals = (!Objects.equal(_variability_block, null));
              if (_notEquals) {
                {
                  variability_block _variability_block_1 = b.getVariability_block();
                  variability_block_content _block = _variability_block_1.getBlock();
                  EList<variability_block_statement> _blocks_1 = _block.getBlocks();
                  for(final variability_block_statement c : _blocks_1) {
                    {
                      block_statement _block_statement = c.getBlock_statement();
                      boolean _notEquals_1 = (!Objects.equal(_block_statement, null));
                      if (_notEquals_1) {
                        _builder.append("\t");
                        block_statement _block_statement_1 = c.getBlock_statement();
                        String _printOmega = this.printOmega(_block_statement_1);
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
    return _builder;
  }
  
  public CharSequence printSigma(final param_obj obj) {
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
          EList<param_obj_block> _blocks = obj.getBlocks();
          for(final param_obj_block b : _blocks) {
            {
              variability_block _variability_block = b.getVariability_block();
              boolean _notEquals = (!Objects.equal(_variability_block, null));
              if (_notEquals) {
                {
                  variability_block _variability_block_1 = b.getVariability_block();
                  variability_block_content _block = _variability_block_1.getBlock();
                  EList<variability_block_statement> _blocks_1 = _block.getBlocks();
                  for(final variability_block_statement c : _blocks_1) {
                    {
                      block_statement _block_statement = c.getBlock_statement();
                      boolean _notEquals_1 = (!Objects.equal(_block_statement, null));
                      if (_notEquals_1) {
                        _builder.append("\t");
                        block_statement _block_statement_1 = c.getBlock_statement();
                        String _printSigma = this.printSigma(_block_statement_1);
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
    return _builder;
  }
  
  public boolean isThetaNonEmpty(final param_obj obj) {
    EList<param_obj_block> _blocks = obj.getBlocks();
    for (final param_obj_block b : _blocks) {
      structural_block _structural_block = b.getStructural_block();
      boolean _notEquals = (!Objects.equal(_structural_block, null));
      if (_notEquals) {
        structural_block _structural_block_1 = b.getStructural_block();
        block _block = _structural_block_1.getBlock();
        EList<block_statement> _statements = _block.getStatements();
        int _size = _statements.size();
        boolean _greaterThan = (_size > 0);
        if (_greaterThan) {
          return true;
        }
      }
    }
    return false;
  }
  
  public boolean isVariabilityNonEmpty(final param_obj obj) {
    EList<param_obj_block> _blocks = obj.getBlocks();
    for (final param_obj_block b : _blocks) {
      variability_block _variability_block = b.getVariability_block();
      boolean _notEquals = (!Objects.equal(_variability_block, null));
      if (_notEquals) {
        variability_block _variability_block_1 = b.getVariability_block();
        variability_block_content _block = _variability_block_1.getBlock();
        EList<variability_block_statement> _blocks_1 = _block.getBlocks();
        for (final variability_block_statement bb : _blocks_1) {
          block_statement _block_statement = bb.getBlock_statement();
          boolean _notEquals_1 = (!Objects.equal(_block_statement, null));
          if (_notEquals_1) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public boolean isVariabilitySubBlocksNonEmpty(final param_obj obj) {
    EList<param_obj_block> _blocks = obj.getBlocks();
    for (final param_obj_block b : _blocks) {
      variability_block _variability_block = b.getVariability_block();
      boolean _notEquals = (!Objects.equal(_variability_block, null));
      if (_notEquals) {
        variability_block _variability_block_1 = b.getVariability_block();
        variability_block_content _block = _variability_block_1.getBlock();
        EList<variability_block_statement> _blocks_1 = _block.getBlocks();
        for (final variability_block_statement bb : _blocks_1) {
          boolean _or = false;
          diag_subblock _diag_block = bb.getDiag_block();
          boolean _notEquals_1 = (!Objects.equal(_diag_block, null));
          if (_notEquals_1) {
            _or = true;
          } else {
            block_subblock _block_block = bb.getBlock_block();
            boolean _notEquals_2 = (!Objects.equal(_block_block, null));
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
  
  public CharSequence convertToNonmem(final data_obj o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<data_obj_block> _blocks = o.getBlocks();
      for(final data_obj_block b : _blocks) {
        {
          header_block _header_block = b.getHeader_block();
          boolean _notEquals = (!Objects.equal(_header_block, null));
          if (_notEquals) {
            header_block _header_block_1 = b.getHeader_block();
            CharSequence _printInput = this.printInput(_header_block_1);
            _builder.append(_printInput, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    {
      EList<data_obj_block> _blocks_1 = o.getBlocks();
      for(final data_obj_block b_1 : _blocks_1) {
        {
          file_block _file_block = b_1.getFile_block();
          boolean _notEquals_1 = (!Objects.equal(_file_block, null));
          if (_notEquals_1) {
            file_block _file_block_1 = b_1.getFile_block();
            CharSequence _printData = this.printData(_file_block_1);
            _builder.append(_printData, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    {
      boolean _notEquals_2 = (!Objects.equal(this.task_object, null));
      if (_notEquals_2) {
        CharSequence _printIgnoreStatements = this.printIgnoreStatements(this.task_object);
        _builder.append(_printIgnoreStatements, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence printInput(final header_block b) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("$INPUT ");
    {
      block _block = b.getBlock();
      EList<block_statement> _statements = _block.getStatements();
      boolean _hasElements = false;
      for(final block_statement st : _statements) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(" ", "");
        }
        {
          variable_declaration _variable_declaration = st.getVariable_declaration();
          boolean _notEquals = (!Objects.equal(_variable_declaration, null));
          if (_notEquals) {
            variable_declaration _variable_declaration_1 = st.getVariable_declaration();
            variable_name _identifier = _variable_declaration_1.getIdentifier();
            CharSequence _print = this.print(_identifier);
            _builder.append(_print, "");
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence printData(final file_block b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      file_block_content _block = b.getBlock();
      EList<file_block_statement> _blocks = _block.getBlocks();
      for(final file_block_statement s : _blocks) {
        {
          block_statement _statement = s.getStatement();
          boolean _notEquals = (!Objects.equal(_statement, null));
          if (_notEquals) {
            block_statement _statement_1 = s.getStatement();
            CharSequence _printDataSource = this.printDataSource(_statement_1);
            _builder.append(_printDataSource, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence printDataSource(final block_statement s) {
    variable_declaration _variable_declaration = s.getVariable_declaration();
    boolean _notEquals = (!Objects.equal(_variable_declaration, null));
    if (_notEquals) {
      variable_declaration _variable_declaration_1 = s.getVariable_declaration();
      variable_name _identifier = _variable_declaration_1.getIdentifier();
      String _str = this.toStr(_identifier);
      boolean _equalsIgnoreCase = _str.equalsIgnoreCase("data");
      if (_equalsIgnoreCase) {
        variable_declaration _variable_declaration_2 = s.getVariable_declaration();
        any_expression _expression = _variable_declaration_2.getExpression();
        boolean _notEquals_1 = (!Objects.equal(_expression, null));
        if (_notEquals_1) {
          variable_declaration _variable_declaration_3 = s.getVariable_declaration();
          any_expression _expression_1 = _variable_declaration_3.getExpression();
          list _list = _expression_1.getList();
          boolean _notEquals_2 = (!Objects.equal(_list, null));
          if (_notEquals_2) {
            variable_declaration _variable_declaration_4 = s.getVariable_declaration();
            any_expression _expression_2 = _variable_declaration_4.getExpression();
            list _list_1 = _expression_2.getList();
            final String data = this.getVariableAttribute(_list_1, "source");
            variable_declaration _variable_declaration_5 = s.getVariable_declaration();
            any_expression _expression_3 = _variable_declaration_5.getExpression();
            list _list_2 = _expression_3.getList();
            final String ignore = this.getVariableAttribute(_list_2, "ignore");
            StringConcatenation _builder = new StringConcatenation();
            {
              boolean _notEquals_3 = (!Objects.equal(data, null));
              if (_notEquals_3) {
                _builder.append("$DATA ");
                _builder.append(data, "");
              }
            }
            _builder.append(" ");
            {
              boolean _notEquals_4 = (!Objects.equal(ignore, null));
              if (_notEquals_4) {
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
  
  public CharSequence convertToNonmem(final task_obj o) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<task_obj_block> _blocks = o.getBlocks();
      for(final task_obj_block b : _blocks) {
        CharSequence _printFunctions = this.printFunctions(b);
        _builder.append(_printFunctions, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence printFunctions(final task_obj_block b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      function_declaration _function_declaration = b.getFunction_declaration();
      boolean _notEquals = (!Objects.equal(_function_declaration, null));
      if (_notEquals) {
        function_declaration _function_declaration_1 = b.getFunction_declaration();
        final function_body body = _function_declaration_1.getFunction_body();
        _builder.newLineIfNotEmpty();
        {
          boolean _notEquals_1 = (!Objects.equal(body, null));
          if (_notEquals_1) {
            {
              EList<function_subblock> _blocks = body.getBlocks();
              for(final function_subblock bb : _blocks) {
                {
                  block _estimate_defn = bb.getEstimate_defn();
                  boolean _notEquals_2 = (!Objects.equal(_estimate_defn, null));
                  if (_notEquals_2) {
                    block _estimate_defn_1 = bb.getEstimate_defn();
                    CharSequence _printEstimate = this.printEstimate(_estimate_defn_1);
                    _builder.append(_printEstimate, "");
                    _builder.newLineIfNotEmpty();
                  }
                }
                {
                  block _simulate_defn = bb.getSimulate_defn();
                  boolean _notEquals_3 = (!Objects.equal(_simulate_defn, null));
                  if (_notEquals_3) {
                    block _simulate_defn_1 = bb.getSimulate_defn();
                    CharSequence _printSimulate = this.printSimulate(_simulate_defn_1);
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
  
  public CharSequence printIgnoreStatements(final task_obj obj) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<task_obj_block> _blocks = obj.getBlocks();
      for(final task_obj_block b : _blocks) {
        {
          data_block _data_block = b.getData_block();
          boolean _notEquals = (!Objects.equal(_data_block, null));
          if (_notEquals) {
            {
              data_block _data_block_1 = b.getData_block();
              block _block = _data_block_1.getBlock();
              EList<block_statement> _statements = _block.getStatements();
              for(final block_statement s : _statements) {
                {
                  variable_declaration _variable_declaration = s.getVariable_declaration();
                  boolean _notEquals_1 = (!Objects.equal(_variable_declaration, null));
                  if (_notEquals_1) {
                    {
                      variable_declaration _variable_declaration_1 = s.getVariable_declaration();
                      variable_name _identifier = _variable_declaration_1.getIdentifier();
                      String _str = this.toStr(_identifier);
                      boolean _equalsIgnoreCase = _str.equalsIgnoreCase("ignore");
                      if (_equalsIgnoreCase) {
                        variable_declaration _variable_declaration_2 = s.getVariable_declaration();
                        CharSequence _print = this.print(_variable_declaration_2);
                        _builder.append(_print, "");
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
    return _builder;
  }
  
  public CharSequence printSimulate(final block b) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("$SIM ;Conversion for the simulation block has not been implemented in the converter");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence printEstimate(final block b) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.newLine();
    _builder.append("$EST");
    {
      EList<block_statement> _statements = b.getStatements();
      for(final block_statement s : _statements) {
        CharSequence _printEstimate = this.printEstimate(s);
        _builder.append(_printEstimate, "");
      }
    }
    _builder.append(" NOABORT");
    _builder.newLineIfNotEmpty();
    {
      EList<block_statement> _statements_1 = b.getStatements();
      for(final block_statement s_1 : _statements_1) {
        CharSequence _printEstimateCov = this.printEstimateCov(s_1);
        _builder.append(_printEstimateCov, "");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence printEstimate(final block_statement s) {
    CharSequence _xifexpression = null;
    variable_declaration _variable_declaration = s.getVariable_declaration();
    boolean _notEquals = (!Objects.equal(_variable_declaration, null));
    if (_notEquals) {
      CharSequence _xifexpression_1 = null;
      variable_declaration _variable_declaration_1 = s.getVariable_declaration();
      any_expression _expression = _variable_declaration_1.getExpression();
      boolean _notEquals_1 = (!Objects.equal(_expression, null));
      if (_notEquals_1) {
        CharSequence _xifexpression_2 = null;
        variable_declaration _variable_declaration_2 = s.getVariable_declaration();
        variable_name _identifier = _variable_declaration_2.getIdentifier();
        String _str = this.toStr(_identifier);
        boolean _equalsIgnoreCase = _str.equalsIgnoreCase("algo");
        if (_equalsIgnoreCase) {
          CharSequence _xifexpression_3 = null;
          variable_declaration _variable_declaration_3 = s.getVariable_declaration();
          any_expression _expression_1 = _variable_declaration_3.getExpression();
          expression _expression_2 = _expression_1.getExpression();
          boolean _notEquals_2 = (!Objects.equal(_expression_2, null));
          if (_notEquals_2) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append(" ");
            _builder.append("METHOD=");
            variable_declaration _variable_declaration_4 = s.getVariable_declaration();
            any_expression _expression_3 = _variable_declaration_4.getExpression();
            expression _expression_4 = _expression_3.getExpression();
            String _str_1 = this.toStr(_expression_4);
            _builder.append(_str_1, " ");
            _xifexpression_3 = _builder;
          } else {
            CharSequence _xifexpression_4 = null;
            variable_declaration _variable_declaration_5 = s.getVariable_declaration();
            any_expression _expression_5 = _variable_declaration_5.getExpression();
            list _list = _expression_5.getList();
            boolean _notEquals_3 = (!Objects.equal(_list, null));
            if (_notEquals_3) {
              CharSequence _xblockexpression = null;
              {
                variable_declaration _variable_declaration_6 = s.getVariable_declaration();
                any_expression _expression_6 = _variable_declaration_6.getExpression();
                list _list_1 = _expression_6.getList();
                arguments args = _list_1.getArguments();
                CharSequence _xifexpression_5 = null;
                boolean _notEquals_4 = (!Objects.equal(args, null));
                if (_notEquals_4) {
                  CharSequence _xifexpression_6 = null;
                  EList<argument> _arguments = args.getArguments();
                  int _size = _arguments.size();
                  boolean _greaterThan = (_size > 0);
                  if (_greaterThan) {
                    StringConcatenation _builder_1 = new StringConcatenation();
                    _builder_1.append(" ");
                    _builder_1.append("METHOD=");
                    EList<argument> _arguments_1 = args.getArguments();
                    argument _get = _arguments_1.get(0);
                    any_expression _expression_7 = _get.getExpression();
                    String _str_2 = this.toStr(_expression_7);
                    _builder_1.append(_str_2, " ");
                    _xifexpression_6 = _builder_1;
                  }
                  _xifexpression_5 = _xifexpression_6;
                }
                _xblockexpression = (_xifexpression_5);
              }
              _xifexpression_4 = _xblockexpression;
            }
            _xifexpression_3 = _xifexpression_4;
          }
          _xifexpression_2 = _xifexpression_3;
        } else {
          CharSequence _xifexpression_5 = null;
          variable_declaration _variable_declaration_6 = s.getVariable_declaration();
          variable_name _identifier_1 = _variable_declaration_6.getIdentifier();
          String _str_2 = this.toStr(_identifier_1);
          boolean _equalsIgnoreCase_1 = _str_2.equalsIgnoreCase("max");
          if (_equalsIgnoreCase_1) {
            StringConcatenation _builder_1 = new StringConcatenation();
            _builder_1.append(" ");
            _builder_1.append("MAX=");
            variable_declaration _variable_declaration_7 = s.getVariable_declaration();
            any_expression _expression_6 = _variable_declaration_7.getExpression();
            CharSequence _print = this.print(_expression_6);
            _builder_1.append(_print, " ");
            _xifexpression_5 = _builder_1;
          } else {
            CharSequence _xifexpression_6 = null;
            variable_declaration _variable_declaration_8 = s.getVariable_declaration();
            variable_name _identifier_2 = _variable_declaration_8.getIdentifier();
            String _str_3 = this.toStr(_identifier_2);
            boolean _equalsIgnoreCase_2 = _str_3.equalsIgnoreCase("sig");
            if (_equalsIgnoreCase_2) {
              StringConcatenation _builder_2 = new StringConcatenation();
              _builder_2.append(" ");
              _builder_2.append("SIG=");
              variable_declaration _variable_declaration_9 = s.getVariable_declaration();
              any_expression _expression_7 = _variable_declaration_9.getExpression();
              CharSequence _print_1 = this.print(_expression_7);
              _builder_2.append(_print_1, " ");
              _xifexpression_6 = _builder_2;
            }
            _xifexpression_5 = _xifexpression_6;
          }
          _xifexpression_2 = _xifexpression_5;
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public CharSequence printEstimateCov(final block_statement s) {
    CharSequence _xifexpression = null;
    variable_declaration _variable_declaration = s.getVariable_declaration();
    boolean _notEquals = (!Objects.equal(_variable_declaration, null));
    if (_notEquals) {
      CharSequence _xifexpression_1 = null;
      variable_declaration _variable_declaration_1 = s.getVariable_declaration();
      variable_name _identifier = _variable_declaration_1.getIdentifier();
      String _str = this.toStr(_identifier);
      boolean _equalsIgnoreCase = _str.equalsIgnoreCase("cov");
      if (_equalsIgnoreCase) {
        CharSequence _xifexpression_2 = null;
        variable_declaration _variable_declaration_2 = s.getVariable_declaration();
        any_expression _expression = _variable_declaration_2.getExpression();
        boolean _notEquals_1 = (!Objects.equal(_expression, null));
        if (_notEquals_1) {
          CharSequence _xifexpression_3 = null;
          variable_declaration _variable_declaration_3 = s.getVariable_declaration();
          any_expression _expression_1 = _variable_declaration_3.getExpression();
          String _str_1 = this.toStr(_expression_1);
          String _replaceAll = _str_1.replaceAll("\\s", "");
          boolean _equalsIgnoreCase_1 = _replaceAll.equalsIgnoreCase("");
          if (_equalsIgnoreCase_1) {
            StringConcatenation _builder = new StringConcatenation();
            _builder.append("$COV ");
            variable_declaration _variable_declaration_4 = s.getVariable_declaration();
            any_expression _expression_2 = _variable_declaration_4.getExpression();
            CharSequence _print = this.print(_expression_2);
            _builder.append(_print, "");
            _xifexpression_3 = _builder;
          }
          _xifexpression_2 = _xifexpression_3;
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
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
  
  public void prepareCollections(final mcl_obj o) {
    model_obj _model_obj = o.getModel_obj();
    boolean _notEquals = (!Objects.equal(_model_obj, null));
    if (_notEquals) {
      model_obj _model_obj_1 = o.getModel_obj();
      this.setRandomVariables(_model_obj_1);
      model_obj _model_obj_2 = o.getModel_obj();
      this.setStructuralVariables(_model_obj_2);
      model_obj _model_obj_3 = o.getModel_obj();
      this.setModelPredictionVariables(_model_obj_3);
    }
  }
  
  public void setModelPredictionVariables(final model_obj o) {
    this.dadt_vars.clear();
    int i = 1;
    EList<model_obj_block> _blocks = o.getBlocks();
    for (final model_obj_block b : _blocks) {
      model_prediction_obj_block _model_prediction_obj_block = b.getModel_prediction_obj_block();
      boolean _notEquals = (!Objects.equal(_model_prediction_obj_block, null));
      if (_notEquals) {
        model_prediction_obj_block _model_prediction_obj_block_1 = b.getModel_prediction_obj_block();
        model_block _block = _model_prediction_obj_block_1.getBlock();
        EList<model_block_statement> _statements = _block.getStatements();
        for (final model_block_statement s : _statements) {
          ode_block _ode_block = s.getOde_block();
          boolean _notEquals_1 = (!Objects.equal(_ode_block, null));
          if (_notEquals_1) {
            ode_block _ode_block_1 = s.getOde_block();
            block _block_1 = _ode_block_1.getBlock();
            EList<block_statement> _statements_1 = _block_1.getStatements();
            for (final block_statement ss : _statements_1) {
              {
                variable_declaration x = ss.getVariable_declaration();
                boolean _notEquals_2 = (!Objects.equal(x, null));
                if (_notEquals_2) {
                  any_expression _expression = x.getExpression();
                  boolean _notEquals_3 = (!Objects.equal(_expression, null));
                  if (_notEquals_3) {
                    any_expression _expression_1 = x.getExpression();
                    ode_list _ode_list = _expression_1.getOde_list();
                    boolean _notEquals_4 = (!Objects.equal(_ode_list, null));
                    if (_notEquals_4) {
                      variable_name _identifier = x.getIdentifier();
                      String id = this.toStr(_identifier);
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
  
  public void setRandomVariables(final model_obj o) {
    this.eta_vars.clear();
    this.eps_vars.clear();
    int i = 1;
    int j = 1;
    EList<model_obj_block> _blocks = o.getBlocks();
    for (final model_obj_block b : _blocks) {
      random_variable_definition_block _random_variable_definition_block = b.getRandom_variable_definition_block();
      boolean _notEquals = (!Objects.equal(_random_variable_definition_block, null));
      if (_notEquals) {
        random_variable_definition_block _random_variable_definition_block_1 = b.getRandom_variable_definition_block();
        block block = _random_variable_definition_block_1.getBlock();
        boolean _notEquals_1 = (!Objects.equal(block, null));
        if (_notEquals_1) {
          EList<block_statement> _statements = block.getStatements();
          for (final block_statement s : _statements) {
            variable_declaration _variable_declaration = s.getVariable_declaration();
            boolean _notEquals_2 = (!Objects.equal(_variable_declaration, null));
            if (_notEquals_2) {
              String level = "";
              variable_declaration _variable_declaration_1 = s.getVariable_declaration();
              random_list _random_list = _variable_declaration_1.getRandom_list();
              boolean _notEquals_3 = (!Objects.equal(_random_list, null));
              if (_notEquals_3) {
                variable_declaration _variable_declaration_2 = s.getVariable_declaration();
                random_list _random_list_1 = _variable_declaration_2.getRandom_list();
                String _variableAttribute = this.getVariableAttribute(_random_list_1, "level");
                String _string = _variableAttribute.toString();
                level = _string;
              } else {
                variable_declaration _variable_declaration_3 = s.getVariable_declaration();
                any_expression _expression = _variable_declaration_3.getExpression();
                boolean _notEquals_4 = (!Objects.equal(_expression, null));
                if (_notEquals_4) {
                  variable_declaration _variable_declaration_4 = s.getVariable_declaration();
                  any_expression _expression_1 = _variable_declaration_4.getExpression();
                  random_list _random_list_2 = _expression_1.getRandom_list();
                  boolean _notEquals_5 = (!Objects.equal(_random_list_2, null));
                  if (_notEquals_5) {
                    variable_declaration _variable_declaration_5 = s.getVariable_declaration();
                    any_expression _expression_2 = _variable_declaration_5.getExpression();
                    random_list _random_list_3 = _expression_2.getRandom_list();
                    String _variableAttribute_1 = this.getVariableAttribute(_random_list_3, "level");
                    String _string_1 = _variableAttribute_1.toString();
                    level = _string_1;
                  } else {
                    variable_declaration _variable_declaration_6 = s.getVariable_declaration();
                    any_expression _expression_3 = _variable_declaration_6.getExpression();
                    list _list = _expression_3.getList();
                    boolean _notEquals_6 = (!Objects.equal(_list, null));
                    if (_notEquals_6) {
                      variable_declaration _variable_declaration_7 = s.getVariable_declaration();
                      any_expression _expression_4 = _variable_declaration_7.getExpression();
                      list _list_1 = _expression_4.getList();
                      String _variableAttribute_2 = this.getVariableAttribute(_list_1, "level");
                      String _string_2 = _variableAttribute_2.toString();
                      level = _string_2;
                    }
                  }
                }
              }
              variable_declaration _variable_declaration_8 = s.getVariable_declaration();
              variable_name _identifier = _variable_declaration_8.getIdentifier();
              final String id = this.toStr(_identifier);
              boolean _equalsIgnoreCase = level.equalsIgnoreCase("ID");
              if (_equalsIgnoreCase) {
                Object _get = this.eta_vars.get(id);
                boolean _equals = Objects.equal(_get, null);
                if (_equals) {
                  this.eta_vars.put(id, Integer.valueOf(i));
                  int _plus = (i + 1);
                  i = _plus;
                }
              }
              boolean _equalsIgnoreCase_1 = level.equalsIgnoreCase("DV");
              if (_equalsIgnoreCase_1) {
                Object _get_1 = this.eps_vars.get(id);
                boolean _equals_1 = Objects.equal(_get_1, null);
                if (_equals_1) {
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
  }
  
  public void setStructuralVariables(final model_obj o) {
    this.theta_vars.clear();
    int i = 1;
    EList<model_obj_block> _blocks = o.getBlocks();
    for (final model_obj_block b : _blocks) {
      structural_parameters_block _structural_parameters_block = b.getStructural_parameters_block();
      boolean _notEquals = (!Objects.equal(_structural_parameters_block, null));
      if (_notEquals) {
        structural_parameters_block _structural_parameters_block_1 = b.getStructural_parameters_block();
        block block = _structural_parameters_block_1.getBlock();
        boolean _notEquals_1 = (!Objects.equal(block, null));
        if (_notEquals_1) {
          EList<block_statement> _statements = block.getStatements();
          for (final block_statement st : _statements) {
            variable_declaration _variable_declaration = st.getVariable_declaration();
            boolean _notEquals_2 = (!Objects.equal(_variable_declaration, null));
            if (_notEquals_2) {
              variable_declaration _variable_declaration_1 = st.getVariable_declaration();
              variable_name _identifier = _variable_declaration_1.getIdentifier();
              final String id = this.toStr(_identifier);
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
    }
  }
  
  public CharSequence printVariabilitySubBlock(final variability_block_statement v) {
    String result = "";
    diag_subblock _diag_block = v.getDiag_block();
    boolean _notEquals = (!Objects.equal(_diag_block, null));
    if (_notEquals) {
      boolean printFix = false;
      int k = 0;
      diag_subblock _diag_block_1 = v.getDiag_block();
      variability_subblock _block = _diag_block_1.getBlock();
      boolean _notEquals_1 = (!Objects.equal(_block, null));
      if (_notEquals_1) {
        diag_subblock _diag_block_2 = v.getDiag_block();
        variability_subblock _block_1 = _diag_block_2.getBlock();
        arguments _arguments = _block_1.getArguments();
        EList<argument> _arguments_1 = _arguments.getArguments();
        for (final argument a : _arguments_1) {
          String _identifier = a.getIdentifier();
          boolean _notEquals_2 = (!Objects.equal(_identifier, null));
          if (_notEquals_2) {
            boolean _isArgumentExpression = this.isArgumentExpression(a);
            if (_isArgumentExpression) {
              any_expression _expression = a.getExpression();
              boolean _notEquals_3 = (!Objects.equal(_expression, null));
              if (_notEquals_3) {
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
                any_expression _expression_1 = a.getExpression();
                String _str = this.toStr(_expression_1);
                String _plus_1 = (result + _str);
                String _plus_2 = (_plus_1 + " ; ");
                String _identifier_1 = a.getIdentifier();
                String _plus_3 = (_plus_2 + _identifier_1);
                String _plus_4 = (_plus_3 + "\n");
                result = _plus_4;
              }
            }
            String _identifier_2 = a.getIdentifier();
            boolean _equalsIgnoreCase = _identifier_2.equalsIgnoreCase("fix");
            if (_equalsIgnoreCase) {
              printFix = true;
            }
          } else {
            any_expression _expression_2 = a.getExpression();
            boolean _notEquals_4 = (!Objects.equal(_expression_2, null));
            if (_notEquals_4) {
              any_expression _expression_3 = a.getExpression();
              String _str_1 = this.toStr(_expression_3);
              String _plus_5 = (result + _str_1);
              String _plus_6 = (_plus_5 + " ");
              result = _plus_6;
            }
          }
        }
        if (printFix) {
          String _plus_7 = (result + "\nFIX\n");
          result = _plus_7;
        }
      }
    }
    block_subblock _block_block = v.getBlock_block();
    boolean _notEquals_5 = (!Objects.equal(_block_block, null));
    if (_notEquals_5) {
      boolean printFix_1 = false;
      block_subblock _block_block_1 = v.getBlock_block();
      variability_subblock _block_2 = _block_block_1.getBlock();
      boolean _notEquals_6 = (!Objects.equal(_block_2, null));
      if (_notEquals_6) {
        block_subblock _block_block_2 = v.getBlock_block();
        variability_subblock _block_3 = _block_block_2.getBlock();
        arguments _arguments_2 = _block_3.getArguments();
        EList<argument> _arguments_3 = _arguments_2.getArguments();
        for (final argument a_1 : _arguments_3) {
          String _identifier_3 = a_1.getIdentifier();
          boolean _notEquals_7 = (!Objects.equal(_identifier_3, null));
          if (_notEquals_7) {
            boolean _isArgumentExpression_1 = this.isArgumentExpression(a_1);
            if (_isArgumentExpression_1) {
              any_expression _expression_4 = a_1.getExpression();
              boolean _notEquals_8 = (!Objects.equal(_expression_4, null));
              if (_notEquals_8) {
                any_expression _expression_5 = a_1.getExpression();
                String _str_2 = this.toStr(_expression_5);
                String _plus_8 = (result + _str_2);
                String _plus_9 = (_plus_8 + " ; ");
                String _identifier_4 = a_1.getIdentifier();
                String _plus_10 = (_plus_9 + _identifier_4);
                String _plus_11 = (_plus_10 + "\n");
                result = _plus_11;
              }
            }
            String _identifier_5 = a_1.getIdentifier();
            boolean _equalsIgnoreCase_1 = _identifier_5.equalsIgnoreCase("fix");
            if (_equalsIgnoreCase_1) {
              printFix_1 = true;
            }
          } else {
            any_expression _expression_6 = a_1.getExpression();
            boolean _notEquals_9 = (!Objects.equal(_expression_6, null));
            if (_notEquals_9) {
              any_expression _expression_7 = a_1.getExpression();
              String _str_3 = this.toStr(_expression_7);
              String _plus_12 = (result + _str_3);
              String _plus_13 = (_plus_12 + " ");
              result = _plus_13;
            }
          }
        }
        if (printFix_1) {
          String _plus_14 = (result + "\nFIX\n");
          result = _plus_14;
        }
      }
    }
    StringConcatenation _builder = new StringConcatenation();
    _builder.append(result, "");
    return _builder;
  }
  
  public String printSigma(final block_statement s) {
    String _xifexpression = null;
    variable_declaration _variable_declaration = s.getVariable_declaration();
    boolean _notEquals = (!Objects.equal(_variable_declaration, null));
    if (_notEquals) {
      String _xifexpression_1 = null;
      variable_declaration _variable_declaration_1 = s.getVariable_declaration();
      any_expression _expression = _variable_declaration_1.getExpression();
      boolean _notEquals_1 = (!Objects.equal(_expression, null));
      if (_notEquals_1) {
        String _xifexpression_2 = null;
        variable_declaration _variable_declaration_2 = s.getVariable_declaration();
        any_expression _expression_1 = _variable_declaration_2.getExpression();
        list _list = _expression_1.getList();
        boolean _notEquals_2 = (!Objects.equal(_list, null));
        if (_notEquals_2) {
          String _xblockexpression = null;
          {
            variable_declaration _variable_declaration_3 = s.getVariable_declaration();
            variable_name _identifier = _variable_declaration_3.getIdentifier();
            String name = this.toStr(_identifier);
            String _xifexpression_3 = null;
            String _plus = ("eps_" + name);
            Object _get = this.eps_vars.get(_plus);
            boolean _notEquals_3 = (!Objects.equal(_get, null));
            if (_notEquals_3) {
              String _xblockexpression_1 = null;
              {
                variable_declaration _variable_declaration_4 = s.getVariable_declaration();
                any_expression _expression_2 = _variable_declaration_4.getExpression();
                list _list_1 = _expression_2.getList();
                String _variableAttribute = this.getVariableAttribute(_list_1, "value");
                final String value = _variableAttribute.toString();
                variable_declaration _variable_declaration_5 = s.getVariable_declaration();
                any_expression _expression_3 = _variable_declaration_5.getExpression();
                list _list_2 = _expression_3.getList();
                final String fixed = this.getVariableAttribute(_list_2, "fix");
                boolean printFix = false;
                boolean _notEquals_4 = (!Objects.equal(fixed, null));
                if (_notEquals_4) {
                  boolean _or = false;
                  boolean _or_1 = false;
                  String _string = fixed.toString();
                  boolean _equalsIgnoreCase = _string.equalsIgnoreCase("yes");
                  if (_equalsIgnoreCase) {
                    _or_1 = true;
                  } else {
                    String _string_1 = fixed.toString();
                    boolean _equalsIgnoreCase_1 = _string_1.equalsIgnoreCase("true");
                    _or_1 = (_equalsIgnoreCase || _equalsIgnoreCase_1);
                  }
                  if (_or_1) {
                    _or = true;
                  } else {
                    String _string_2 = fixed.toString();
                    boolean _equalsIgnoreCase_2 = _string_2.equalsIgnoreCase("1");
                    _or = (_or_1 || _equalsIgnoreCase_2);
                  }
                  printFix = _or;
                }
                boolean _equals = Objects.equal(value, null);
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
              _xifexpression_3 = _xblockexpression_1;
            }
            _xblockexpression = (_xifexpression_3);
          }
          _xifexpression_2 = _xblockexpression;
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public String printOmega(final block_statement s) {
    String _xifexpression = null;
    variable_declaration _variable_declaration = s.getVariable_declaration();
    boolean _notEquals = (!Objects.equal(_variable_declaration, null));
    if (_notEquals) {
      String _xifexpression_1 = null;
      variable_declaration _variable_declaration_1 = s.getVariable_declaration();
      any_expression _expression = _variable_declaration_1.getExpression();
      boolean _notEquals_1 = (!Objects.equal(_expression, null));
      if (_notEquals_1) {
        String _xifexpression_2 = null;
        variable_declaration _variable_declaration_2 = s.getVariable_declaration();
        any_expression _expression_1 = _variable_declaration_2.getExpression();
        list _list = _expression_1.getList();
        boolean _notEquals_2 = (!Objects.equal(_list, null));
        if (_notEquals_2) {
          String _xblockexpression = null;
          {
            variable_declaration _variable_declaration_3 = s.getVariable_declaration();
            variable_name _identifier = _variable_declaration_3.getIdentifier();
            String name = this.toStr(_identifier);
            String _xifexpression_3 = null;
            String _plus = ("eta_" + name);
            Object _get = this.eta_vars.get(_plus);
            boolean _notEquals_3 = (!Objects.equal(_get, null));
            if (_notEquals_3) {
              String _xblockexpression_1 = null;
              {
                variable_declaration _variable_declaration_4 = s.getVariable_declaration();
                any_expression _expression_2 = _variable_declaration_4.getExpression();
                list _list_1 = _expression_2.getList();
                String _variableAttribute = this.getVariableAttribute(_list_1, "value");
                final String value = _variableAttribute.toString();
                variable_declaration _variable_declaration_5 = s.getVariable_declaration();
                any_expression _expression_3 = _variable_declaration_5.getExpression();
                list _list_2 = _expression_3.getList();
                String fixed = this.getVariableAttribute(_list_2, "fix");
                boolean printFix = false;
                boolean _notEquals_4 = (!Objects.equal(fixed, null));
                if (_notEquals_4) {
                  boolean _or = false;
                  boolean _or_1 = false;
                  String _string = fixed.toString();
                  boolean _equalsIgnoreCase = _string.equalsIgnoreCase("yes");
                  if (_equalsIgnoreCase) {
                    _or_1 = true;
                  } else {
                    String _string_1 = fixed.toString();
                    boolean _equalsIgnoreCase_1 = _string_1.equalsIgnoreCase("true");
                    _or_1 = (_equalsIgnoreCase || _equalsIgnoreCase_1);
                  }
                  if (_or_1) {
                    _or = true;
                  } else {
                    String _string_2 = fixed.toString();
                    boolean _equalsIgnoreCase_2 = _string_2.equalsIgnoreCase("1");
                    _or = (_or_1 || _equalsIgnoreCase_2);
                  }
                  printFix = _or;
                }
                boolean _equals = Objects.equal(value, null);
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
              _xifexpression_3 = _xblockexpression_1;
            }
            _xblockexpression = (_xifexpression_3);
          }
          _xifexpression_2 = _xblockexpression;
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public CharSequence printTheta(final block_statement s) {
    variable_declaration _variable_declaration = s.getVariable_declaration();
    boolean _notEquals = (!Objects.equal(_variable_declaration, null));
    if (_notEquals) {
      variable_declaration _variable_declaration_1 = s.getVariable_declaration();
      any_expression _expression = _variable_declaration_1.getExpression();
      boolean _notEquals_1 = (!Objects.equal(_expression, null));
      if (_notEquals_1) {
        variable_declaration _variable_declaration_2 = s.getVariable_declaration();
        any_expression _expression_1 = _variable_declaration_2.getExpression();
        list _list = _expression_1.getList();
        boolean _notEquals_2 = (!Objects.equal(_list, null));
        if (_notEquals_2) {
          variable_declaration _variable_declaration_3 = s.getVariable_declaration();
          variable_name _identifier = _variable_declaration_3.getIdentifier();
          String name = this.toStr(_identifier);
          variable_declaration _variable_declaration_4 = s.getVariable_declaration();
          any_expression _expression_2 = _variable_declaration_4.getExpression();
          list _list_1 = _expression_2.getList();
          String _variableAttribute = this.getVariableAttribute(_list_1, "value");
          final String value = _variableAttribute.toString();
          variable_declaration _variable_declaration_5 = s.getVariable_declaration();
          any_expression _expression_3 = _variable_declaration_5.getExpression();
          list _list_2 = _expression_3.getList();
          final String lo = this.getVariableAttribute(_list_2, "lo");
          variable_declaration _variable_declaration_6 = s.getVariable_declaration();
          any_expression _expression_4 = _variable_declaration_6.getExpression();
          list _list_3 = _expression_4.getList();
          final String hi = this.getVariableAttribute(_list_3, "hi");
          variable_declaration _variable_declaration_7 = s.getVariable_declaration();
          any_expression _expression_5 = _variable_declaration_7.getExpression();
          list _list_4 = _expression_5.getList();
          final String fixed = this.getVariableAttribute(_list_4, "fix");
          boolean printFix = false;
          boolean _notEquals_3 = (!Objects.equal(fixed, null));
          if (_notEquals_3) {
            boolean _or = false;
            boolean _or_1 = false;
            String _string = fixed.toString();
            boolean _equalsIgnoreCase = _string.equalsIgnoreCase("yes");
            if (_equalsIgnoreCase) {
              _or_1 = true;
            } else {
              String _string_1 = fixed.toString();
              boolean _equalsIgnoreCase_1 = _string_1.equalsIgnoreCase("true");
              _or_1 = (_equalsIgnoreCase || _equalsIgnoreCase_1);
            }
            if (_or_1) {
              _or = true;
            } else {
              String _string_2 = fixed.toString();
              boolean _equalsIgnoreCase_2 = _string_2.equalsIgnoreCase("1");
              _or = (_or_1 || _equalsIgnoreCase_2);
            }
            printFix = _or;
          }
          boolean _equals = Objects.equal(value, null);
          if (_equals) {
            return "";
          }
          boolean _and = false;
          boolean _equals_1 = Objects.equal(lo, null);
          if (!_equals_1) {
            _and = false;
          } else {
            boolean _equals_2 = Objects.equal(hi, null);
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
          boolean _equals_3 = Objects.equal(lo, null);
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
          boolean _equals_4 = Objects.equal(hi, null);
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
      }
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
  
  public CharSequence print(final target_block b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _identifier = b.getIdentifier();
      boolean _equalsIgnoreCase = _identifier.equalsIgnoreCase("NMTRAN");
      if (_equalsIgnoreCase) {
        String _external_code = b.getExternal_code();
        String _external_code_1 = b.getExternal_code();
        int _length = _external_code_1.length();
        int _minus = (_length - 3);
        String printedCode = _external_code.substring(3, _minus);
        _builder.newLineIfNotEmpty();
        _builder.append(printedCode, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print(final statement s) {
    StringConcatenation _builder = new StringConcatenation();
    {
      block _block = s.getBlock();
      boolean _notEquals = (!Objects.equal(_block, null));
      if (_notEquals) {
        block _block_1 = s.getBlock();
        CharSequence _print = this.print(_block_1);
        _builder.append(_print, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      par_expression _par_expression = s.getPar_expression();
      boolean _notEquals_1 = (!Objects.equal(_par_expression, null));
      if (_notEquals_1) {
        _builder.append("IF ");
        par_expression _par_expression_1 = s.getPar_expression();
        CharSequence _print_1 = this.print(_par_expression_1);
        _builder.append(_print_1, "");
        _builder.append(" THEN");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        block_statement _if_statement = s.getIf_statement();
        CharSequence _print_2 = this.print(_if_statement);
        _builder.append(_print_2, "	");
        _builder.newLineIfNotEmpty();
        {
          block_statement _else_statement = s.getElse_statement();
          boolean _notEquals_2 = (!Objects.equal(_else_statement, null));
          if (_notEquals_2) {
            _builder.append("ELSE ");
            _builder.newLine();
            _builder.append("\t");
            block_statement _else_statement_1 = s.getElse_statement();
            CharSequence _print_3 = this.print(_else_statement_1);
            _builder.append(_print_3, "	");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.append("ENDIF");
        _builder.newLine();
      }
    }
    return _builder;
  }
}
