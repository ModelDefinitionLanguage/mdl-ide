package org.ddmore.mdl.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import java.util.Iterator;
import org.ddmore.mdl.mdl.additive_expression;
import org.ddmore.mdl.mdl.any_expression;
import org.ddmore.mdl.mdl.argument;
import org.ddmore.mdl.mdl.arguments;
import org.ddmore.mdl.mdl.block;
import org.ddmore.mdl.mdl.block_statement;
import org.ddmore.mdl.mdl.conditional_and_expression;
import org.ddmore.mdl.mdl.conditional_expression;
import org.ddmore.mdl.mdl.conditional_or_expression;
import org.ddmore.mdl.mdl.data_obj;
import org.ddmore.mdl.mdl.data_obj_block;
import org.ddmore.mdl.mdl.expression;
import org.ddmore.mdl.mdl.file_block;
import org.ddmore.mdl.mdl.file_block_content;
import org.ddmore.mdl.mdl.file_block_statement;
import org.ddmore.mdl.mdl.function_call;
import org.ddmore.mdl.mdl.group_variables;
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
import org.ddmore.mdl.mdl.multiplicative_expression;
import org.ddmore.mdl.mdl.observation_block;
import org.ddmore.mdl.mdl.ode_block;
import org.ddmore.mdl.mdl.ode_list;
import org.ddmore.mdl.mdl.par_expression;
import org.ddmore.mdl.mdl.power_expression;
import org.ddmore.mdl.mdl.primary;
import org.ddmore.mdl.mdl.random_list;
import org.ddmore.mdl.mdl.relational_expression;
import org.ddmore.mdl.mdl.statement;
import org.ddmore.mdl.mdl.target_block;
import org.ddmore.mdl.mdl.unary_expression;
import org.ddmore.mdl.mdl.variable_declaration;
import org.ddmore.mdl.mdl.variable_name;
import org.ddmore.mdl.mdl.verbatim_block;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;

@SuppressWarnings("all")
public class MdlPrinting {
  public String fileNameUpperCase(final mcl m) {
    Resource _eResource = m.eResource();
    String _fileName = this.fileName(_eResource);
    String _upperCase = _fileName.toUpperCase();
    return _upperCase;
  }
  
  public String fileName(final Resource resource) {
    String _xblockexpression = null;
    {
      URI _uRI = resource.getURI();
      String fileName = _uRI.lastSegment();
      int _lastIndexOf = fileName.lastIndexOf(".");
      String _substring = fileName.substring(0, _lastIndexOf);
      _xblockexpression = (_substring);
    }
    return _xblockexpression;
  }
  
  public String getDataSource(final Resource resource) {
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterable<EObject> _iterable = IteratorExtensions.<EObject>toIterable(_allContents);
    Iterable<mcl> _filter = Iterables.<mcl>filter(_iterable, mcl.class);
    for (final mcl m : _filter) {
      EList<mcl_obj> _objects = m.getObjects();
      for (final mcl_obj obj : _objects) {
        data_obj _data_obj = obj.getData_obj();
        boolean _notEquals = (!Objects.equal(_data_obj, null));
        if (_notEquals) {
          data_obj _data_obj_1 = obj.getData_obj();
          EList<data_obj_block> _blocks = _data_obj_1.getBlocks();
          for (final data_obj_block b : _blocks) {
            file_block _file_block = b.getFile_block();
            boolean _notEquals_1 = (!Objects.equal(_file_block, null));
            if (_notEquals_1) {
              file_block _file_block_1 = b.getFile_block();
              file_block_content _block = _file_block_1.getBlock();
              EList<file_block_statement> _blocks_1 = _block.getBlocks();
              for (final file_block_statement s : _blocks_1) {
                block_statement _statement = s.getStatement();
                boolean _notEquals_2 = (!Objects.equal(_statement, null));
                if (_notEquals_2) {
                  block_statement _statement_1 = s.getStatement();
                  return this.getDataSource(_statement_1);
                }
              }
            }
          }
        }
      }
    }
    return "";
  }
  
  public String getDataSource(final block_statement s) {
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
            boolean _notEquals_3 = (!Objects.equal(data, null));
            if (_notEquals_3) {
              return data.toString();
            }
          }
        }
      }
    }
    return "";
  }
  
  public String getVariableAttribute(final list v, final String attr_name) {
    arguments _arguments = v.getArguments();
    boolean _notEquals = (!Objects.equal(_arguments, null));
    if (_notEquals) {
      arguments _arguments_1 = v.getArguments();
      return this.selectAttribute(_arguments_1, attr_name);
    }
    return null;
  }
  
  public String getVariableAttribute(final random_list v, final String attr_name) {
    arguments _arguments = v.getArguments();
    boolean _notEquals = (!Objects.equal(_arguments, null));
    if (_notEquals) {
      arguments _arguments_1 = v.getArguments();
      return this.selectAttribute(_arguments_1, attr_name);
    }
    return null;
  }
  
  public String getVariableAttribute(final ode_list v, final String attr_name) {
    arguments _arguments = v.getArguments();
    boolean _notEquals = (!Objects.equal(_arguments, null));
    if (_notEquals) {
      arguments _arguments_1 = v.getArguments();
      return this.selectAttribute(_arguments_1, attr_name);
    }
    return null;
  }
  
  public String selectAttribute(final arguments a, final String attr_name) {
    EList<argument> _arguments = a.getArguments();
    for (final argument arg : _arguments) {
      String _identifier = arg.getIdentifier();
      boolean _equalsIgnoreCase = _identifier.equalsIgnoreCase(attr_name);
      if (_equalsIgnoreCase) {
        any_expression _expression = arg.getExpression();
        return this.toStr(_expression);
      }
    }
    return null;
  }
  
  public boolean isArgumentExpression(final argument a) {
    any_expression _expression = a.getExpression();
    expression _expression_1 = _expression.getExpression();
    boolean _notEquals = (!Objects.equal(_expression_1, null));
    if (_notEquals) {
      any_expression _expression_2 = a.getExpression();
      expression _expression_3 = _expression_2.getExpression();
      conditional_expression _conditional_expression = _expression_3.getConditional_expression();
      boolean _notEquals_1 = (!Objects.equal(_conditional_expression, null));
      if (_notEquals_1) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isErrorNonEmpty(final model_obj o) {
    EList<model_obj_block> _blocks = o.getBlocks();
    for (final model_obj_block mob : _blocks) {
      {
        model_prediction_obj_block _model_prediction_obj_block = mob.getModel_prediction_obj_block();
        boolean _notEquals = (!Objects.equal(_model_prediction_obj_block, null));
        if (_notEquals) {
          model_prediction_obj_block _model_prediction_obj_block_1 = mob.getModel_prediction_obj_block();
          model_block _block = _model_prediction_obj_block_1.getBlock();
          EList<model_block_statement> _statements = _block.getStatements();
          for (final model_block_statement s : _statements) {
            block_statement _statement = s.getStatement();
            boolean _notEquals_1 = (!Objects.equal(_statement, null));
            if (_notEquals_1) {
              return true;
            }
          }
        }
        observation_block _observation_block = mob.getObservation_block();
        boolean _notEquals_2 = (!Objects.equal(_observation_block, null));
        if (_notEquals_2) {
          observation_block _observation_block_1 = mob.getObservation_block();
          block _block_1 = _observation_block_1.getBlock();
          EList<block_statement> _statements_1 = _block_1.getStatements();
          for (final block_statement s_1 : _statements_1) {
            statement _statement_1 = s_1.getStatement();
            boolean _notEquals_3 = (!Objects.equal(_statement_1, null));
            if (_notEquals_3) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }
  
  public boolean isODEDefined(final model_obj o) {
    EList<model_obj_block> _blocks = o.getBlocks();
    for (final model_obj_block mob : _blocks) {
      model_prediction_obj_block _model_prediction_obj_block = mob.getModel_prediction_obj_block();
      boolean _notEquals = (!Objects.equal(_model_prediction_obj_block, null));
      if (_notEquals) {
        model_prediction_obj_block _model_prediction_obj_block_1 = mob.getModel_prediction_obj_block();
        model_block b = _model_prediction_obj_block_1.getBlock();
        boolean _notEquals_1 = (!Objects.equal(b, null));
        if (_notEquals_1) {
          EList<model_block_statement> _statements = b.getStatements();
          for (final model_block_statement s : _statements) {
            ode_block _ode_block = s.getOde_block();
            boolean _notEquals_2 = (!Objects.equal(_ode_block, null));
            if (_notEquals_2) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }
  
  public boolean isPKDefined(final model_obj o) {
    EList<model_obj_block> _blocks = o.getBlocks();
    for (final model_obj_block mob : _blocks) {
      {
        group_variables _group_variables = mob.getGroup_variables();
        boolean _notEquals = (!Objects.equal(_group_variables, null));
        if (_notEquals) {
          group_variables _group_variables_1 = mob.getGroup_variables();
          block _block = _group_variables_1.getBlock();
          boolean _notEquals_1 = (!Objects.equal(_block, null));
          if (_notEquals_1) {
            return true;
          }
        }
        individual_model_obj_block _individual_model_obj_block = mob.getIndividual_model_obj_block();
        boolean _notEquals_2 = (!Objects.equal(_individual_model_obj_block, null));
        if (_notEquals_2) {
          individual_model_obj_block _individual_model_obj_block_1 = mob.getIndividual_model_obj_block();
          block _block_1 = _individual_model_obj_block_1.getBlock();
          boolean _notEquals_3 = (!Objects.equal(_block_1, null));
          if (_notEquals_3) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public boolean isLibraryDefined(final model_obj o) {
    EList<model_obj_block> _blocks = o.getBlocks();
    for (final model_obj_block mob : _blocks) {
      model_prediction_obj_block _model_prediction_obj_block = mob.getModel_prediction_obj_block();
      boolean _notEquals = (!Objects.equal(_model_prediction_obj_block, null));
      if (_notEquals) {
        model_prediction_obj_block _model_prediction_obj_block_1 = mob.getModel_prediction_obj_block();
        model_block _block = _model_prediction_obj_block_1.getBlock();
        EList<model_block_statement> _statements = _block.getStatements();
        for (final model_block_statement s : _statements) {
          library_block _library_block = s.getLibrary_block();
          boolean _notEquals_1 = (!Objects.equal(_library_block, null));
          if (_notEquals_1) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public String convertID(final String id) {
    return id;
  }
  
  public String convertOperator(final String op) {
    return op;
  }
  
  public String toStr(final variable_name name) {
    String res = "";
    EList<String> _identifier = name.getIdentifier();
    Iterator<String> iterator = _identifier.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      String _next = iterator.next();
      res = _next;
    }
    boolean _hasNext_1 = iterator.hasNext();
    boolean _while = _hasNext_1;
    while (_while) {
      String _plus = (res + ".");
      String _next_1 = iterator.next();
      String _plus_1 = (_plus + _next_1);
      res = _plus_1;
      boolean _hasNext_2 = iterator.hasNext();
      _while = _hasNext_2;
    }
    return res;
  }
  
  public String toStr(final variable_declaration v) {
    String res = "";
    variable_name _identifier = v.getIdentifier();
    boolean _notEquals = (!Objects.equal(_identifier, null));
    if (_notEquals) {
      variable_name _identifier_1 = v.getIdentifier();
      String _str = this.toStr(_identifier_1);
      String _plus = (res + _str);
      res = _plus;
    }
    any_expression _expression = v.getExpression();
    boolean _notEquals_1 = (!Objects.equal(_expression, null));
    if (_notEquals_1) {
      String _plus_1 = (res + " = ");
      any_expression _expression_1 = v.getExpression();
      String _str_1 = this.toStr(_expression_1);
      String _plus_2 = (_plus_1 + _str_1);
      res = _plus_2;
    }
    random_list _random_list = v.getRandom_list();
    boolean _notEquals_2 = (!Objects.equal(_random_list, null));
    if (_notEquals_2) {
      random_list _random_list_1 = v.getRandom_list();
      String _str_2 = this.toStr(_random_list_1);
      String _plus_3 = (res + _str_2);
      res = _plus_3;
    }
    return res;
  }
  
  public String toStr(final any_expression e) {
    String res = "";
    expression _expression = e.getExpression();
    boolean _notEquals = (!Objects.equal(_expression, null));
    if (_notEquals) {
      expression _expression_1 = e.getExpression();
      String _str = this.toStr(_expression_1);
      String _plus = (res + _str);
      res = _plus;
    }
    list _list = e.getList();
    boolean _notEquals_1 = (!Objects.equal(_list, null));
    if (_notEquals_1) {
      list _list_1 = e.getList();
      String _str_1 = this.toStr(_list_1);
      String _plus_1 = (res + _str_1);
      res = _plus_1;
    }
    random_list _random_list = e.getRandom_list();
    boolean _notEquals_2 = (!Objects.equal(_random_list, null));
    if (_notEquals_2) {
      random_list _random_list_1 = e.getRandom_list();
      String _str_2 = this.toStr(_random_list_1);
      String _plus_2 = (res + _str_2);
      res = _plus_2;
    }
    ode_list _ode_list = e.getOde_list();
    boolean _notEquals_3 = (!Objects.equal(_ode_list, null));
    if (_notEquals_3) {
      ode_list _ode_list_1 = e.getOde_list();
      String _str_3 = this.toStr(_ode_list_1);
      String _plus_3 = (res + _str_3);
      res = _plus_3;
    }
    return res;
  }
  
  public String toStr(final random_list l) {
    arguments _arguments = l.getArguments();
    boolean _notEquals = (!Objects.equal(_arguments, null));
    if (_notEquals) {
      String _plus = ("~" + "(");
      arguments _arguments_1 = l.getArguments();
      String _str = this.toStr(_arguments_1);
      String _plus_1 = (_plus + _str);
      return (_plus_1 + ")");
    }
    return "";
  }
  
  public String toStr(final list l) {
    arguments _arguments = l.getArguments();
    boolean _notEquals = (!Objects.equal(_arguments, null));
    if (_notEquals) {
      String _plus = ("list" + "(");
      arguments _arguments_1 = l.getArguments();
      String _str = this.toStr(_arguments_1);
      String _plus_1 = (_plus + _str);
      return (_plus_1 + ")");
    }
    return "";
  }
  
  public String toStr(final ode_list l) {
    arguments _arguments = l.getArguments();
    boolean _notEquals = (!Objects.equal(_arguments, null));
    if (_notEquals) {
      String _plus = ("ode" + "(");
      arguments _arguments_1 = l.getArguments();
      String _str = this.toStr(_arguments_1);
      String _plus_1 = (_plus + _str);
      return (_plus_1 + ")");
    }
    return null;
  }
  
  public String toStr(final expression e) {
    String res = "";
    conditional_expression _conditional_expression = e.getConditional_expression();
    boolean _notEquals = (!Objects.equal(_conditional_expression, null));
    if (_notEquals) {
      conditional_expression _conditional_expression_1 = e.getConditional_expression();
      String _str = this.toStr(_conditional_expression_1);
      String _plus = (res + _str);
      res = _plus;
    }
    EList<String> _string_expression = e.getString_expression();
    boolean _notEquals_1 = (!Objects.equal(_string_expression, null));
    if (_notEquals_1) {
      EList<String> _string_expression_1 = e.getString_expression();
      Iterator<String> iterator = _string_expression_1.iterator();
      boolean _hasNext = iterator.hasNext();
      if (_hasNext) {
        String _next = iterator.next();
        res = _next;
      }
      boolean _hasNext_1 = iterator.hasNext();
      boolean _while = _hasNext_1;
      while (_while) {
        String _plus_1 = (res + " + ");
        String _next_1 = iterator.next();
        String _plus_2 = (_plus_1 + _next_1);
        res = _plus_2;
        boolean _hasNext_2 = iterator.hasNext();
        _while = _hasNext_2;
      }
    }
    return res;
  }
  
  public String toStr(final conditional_expression e) {
    conditional_or_expression _conditional_or_expression = e.getConditional_or_expression();
    String res = this.toStr(_conditional_or_expression);
    expression _expression1 = e.getExpression1();
    boolean _notEquals = (!Objects.equal(_expression1, null));
    if (_notEquals) {
      String _plus = (res + "?");
      expression _expression1_1 = e.getExpression1();
      String _str = this.toStr(_expression1_1);
      String _plus_1 = (_plus + _str);
      String _plus_2 = (_plus_1 + ":");
      expression _expression2 = e.getExpression2();
      String _str_1 = this.toStr(_expression2);
      String _plus_3 = (_plus_2 + _str_1);
      res = _plus_3;
    }
    return res;
  }
  
  public String toStr(final conditional_or_expression e) {
    String res = "";
    EList<conditional_and_expression> _conditional_and_expression = e.getConditional_and_expression();
    Iterator<conditional_and_expression> iterator = _conditional_and_expression.iterator();
    EList<String> _operator = e.getOperator();
    Iterator<String> operatorIterator = _operator.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      conditional_and_expression _next = iterator.next();
      String _str = this.toStr(_next);
      res = _str;
    }
    boolean _and = false;
    boolean _hasNext_1 = iterator.hasNext();
    if (!_hasNext_1) {
      _and = false;
    } else {
      boolean _hasNext_2 = operatorIterator.hasNext();
      _and = (_hasNext_1 && _hasNext_2);
    }
    boolean _while = _and;
    while (_while) {
      String _next_1 = operatorIterator.next();
      String _convertOperator = this.convertOperator(_next_1);
      String _plus = (res + _convertOperator);
      conditional_and_expression _next_2 = iterator.next();
      String _str_1 = this.toStr(_next_2);
      String _plus_1 = (_plus + _str_1);
      res = _plus_1;
      boolean _and_1 = false;
      boolean _hasNext_3 = iterator.hasNext();
      if (!_hasNext_3) {
        _and_1 = false;
      } else {
        boolean _hasNext_4 = operatorIterator.hasNext();
        _and_1 = (_hasNext_3 && _hasNext_4);
      }
      _while = _and_1;
    }
    return res;
  }
  
  public String toStr(final conditional_and_expression e) {
    String res = "";
    EList<relational_expression> _relational_expression = e.getRelational_expression();
    Iterator<relational_expression> iterator = _relational_expression.iterator();
    EList<String> _operator = e.getOperator();
    Iterator<String> operatorIterator = _operator.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      relational_expression _next = iterator.next();
      String _str = this.toStr(_next);
      res = _str;
    }
    boolean _and = false;
    boolean _hasNext_1 = iterator.hasNext();
    if (!_hasNext_1) {
      _and = false;
    } else {
      boolean _hasNext_2 = operatorIterator.hasNext();
      _and = (_hasNext_1 && _hasNext_2);
    }
    boolean _while = _and;
    while (_while) {
      String _next_1 = operatorIterator.next();
      String _convertOperator = this.convertOperator(_next_1);
      String _plus = (res + _convertOperator);
      relational_expression _next_2 = iterator.next();
      String _str_1 = this.toStr(_next_2);
      String _plus_1 = (_plus + _str_1);
      res = _plus_1;
      boolean _and_1 = false;
      boolean _hasNext_3 = iterator.hasNext();
      if (!_hasNext_3) {
        _and_1 = false;
      } else {
        boolean _hasNext_4 = operatorIterator.hasNext();
        _and_1 = (_hasNext_3 && _hasNext_4);
      }
      _while = _and_1;
    }
    return res;
  }
  
  public String toStr(final relational_expression e) {
    String res = "";
    String _negation = e.getNegation();
    boolean _notEquals = (!Objects.equal(_negation, null));
    if (_notEquals) {
      String _negation_1 = e.getNegation();
      String _plus = (res + _negation_1);
      res = _plus;
    }
    String _boolean = e.getBoolean();
    boolean _notEquals_1 = (!Objects.equal(_boolean, null));
    if (_notEquals_1) {
      String _boolean_1 = e.getBoolean();
      String _string = _boolean_1.toString();
      String _plus_1 = (res + _string);
      res = _plus_1;
    }
    EList<additive_expression> _additive_expression = e.getAdditive_expression();
    Iterator<additive_expression> iterator = _additive_expression.iterator();
    EList<String> _relational_op = e.getRelational_op();
    Iterator<String> operatorIterator = _relational_op.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      additive_expression _next = iterator.next();
      String _str = this.toStr(_next);
      res = _str;
    }
    boolean _and = false;
    boolean _hasNext_1 = iterator.hasNext();
    if (!_hasNext_1) {
      _and = false;
    } else {
      boolean _hasNext_2 = operatorIterator.hasNext();
      _and = (_hasNext_1 && _hasNext_2);
    }
    boolean _while = _and;
    while (_while) {
      String _next_1 = operatorIterator.next();
      String _convertOperator = this.convertOperator(_next_1);
      String _plus_2 = (res + _convertOperator);
      additive_expression _next_2 = iterator.next();
      String _str_1 = this.toStr(_next_2);
      String _plus_3 = (_plus_2 + _str_1);
      res = _plus_3;
      boolean _and_1 = false;
      boolean _hasNext_3 = iterator.hasNext();
      if (!_hasNext_3) {
        _and_1 = false;
      } else {
        boolean _hasNext_4 = operatorIterator.hasNext();
        _and_1 = (_hasNext_3 && _hasNext_4);
      }
      _while = _and_1;
    }
    return res;
  }
  
  public String toStr(final additive_expression e) {
    String res = "";
    EList<multiplicative_expression> _multiplicative_expression = e.getMultiplicative_expression();
    Iterator<multiplicative_expression> iterator = _multiplicative_expression.iterator();
    EList<String> _additive_op = e.getAdditive_op();
    Iterator<String> operatorIterator = _additive_op.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      multiplicative_expression _next = iterator.next();
      String _str = this.toStr(_next);
      res = _str;
    }
    boolean _and = false;
    boolean _hasNext_1 = iterator.hasNext();
    if (!_hasNext_1) {
      _and = false;
    } else {
      boolean _hasNext_2 = operatorIterator.hasNext();
      _and = (_hasNext_1 && _hasNext_2);
    }
    boolean _while = _and;
    while (_while) {
      String _next_1 = operatorIterator.next();
      String _convertOperator = this.convertOperator(_next_1);
      String _plus = (res + _convertOperator);
      multiplicative_expression _next_2 = iterator.next();
      String _str_1 = this.toStr(_next_2);
      String _plus_1 = (_plus + _str_1);
      res = _plus_1;
      boolean _and_1 = false;
      boolean _hasNext_3 = iterator.hasNext();
      if (!_hasNext_3) {
        _and_1 = false;
      } else {
        boolean _hasNext_4 = operatorIterator.hasNext();
        _and_1 = (_hasNext_3 && _hasNext_4);
      }
      _while = _and_1;
    }
    return res;
  }
  
  public String toStr(final multiplicative_expression e) {
    String res = "";
    EList<power_expression> _power_expression = e.getPower_expression();
    Iterator<power_expression> iterator = _power_expression.iterator();
    EList<String> _multiplicative_op = e.getMultiplicative_op();
    Iterator<String> operatorIterator = _multiplicative_op.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      power_expression _next = iterator.next();
      String _str = this.toStr(_next);
      res = _str;
    }
    boolean _hasNext_1 = iterator.hasNext();
    boolean _while = _hasNext_1;
    while (_while) {
      String _next_1 = operatorIterator.next();
      String _convertOperator = this.convertOperator(_next_1);
      String _plus = (res + _convertOperator);
      power_expression _next_2 = iterator.next();
      String _str_1 = this.toStr(_next_2);
      String _plus_1 = (_plus + _str_1);
      res = _plus_1;
      boolean _hasNext_2 = iterator.hasNext();
      _while = _hasNext_2;
    }
    return res;
  }
  
  public String toStr(final power_expression e) {
    String res = "";
    EList<unary_expression> _unary_expression = e.getUnary_expression();
    Iterator<unary_expression> iterator = _unary_expression.iterator();
    EList<String> _power_op = e.getPower_op();
    Iterator<String> operatorIterator = _power_op.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      unary_expression _next = iterator.next();
      String _str = this.toStr(_next);
      res = _str;
    }
    boolean _hasNext_1 = iterator.hasNext();
    boolean _while = _hasNext_1;
    while (_while) {
      String _next_1 = operatorIterator.next();
      String _convertOperator = this.convertOperator(_next_1);
      String _plus = (res + _convertOperator);
      unary_expression _next_2 = iterator.next();
      String _str_1 = this.toStr(_next_2);
      String _plus_1 = (_plus + _str_1);
      res = _plus_1;
      boolean _hasNext_2 = iterator.hasNext();
      _while = _hasNext_2;
    }
    return res;
  }
  
  public String toStr(final unary_expression e) {
    String res = "";
    unary_expression _unary_expression = e.getUnary_expression();
    boolean _notEquals = (!Objects.equal(_unary_expression, null));
    if (_notEquals) {
      String _operator = e.getOperator();
      String _convertOperator = this.convertOperator(_operator);
      String _plus = (res + _convertOperator);
      unary_expression _unary_expression_1 = e.getUnary_expression();
      String _str = this.toStr(_unary_expression_1);
      String _plus_1 = (_plus + _str);
      res = _plus_1;
    }
    par_expression _par_expression = e.getPar_expression();
    boolean _notEquals_1 = (!Objects.equal(_par_expression, null));
    if (_notEquals_1) {
      par_expression _par_expression_1 = e.getPar_expression();
      String _str_1 = this.toStr(_par_expression_1);
      String _plus_2 = (res + _str_1);
      res = _plus_2;
    }
    function_call _function_call = e.getFunction_call();
    boolean _notEquals_2 = (!Objects.equal(_function_call, null));
    if (_notEquals_2) {
      function_call _function_call_1 = e.getFunction_call();
      String _str_2 = this.toStr(_function_call_1);
      String _plus_3 = (res + _str_2);
      res = _plus_3;
    }
    primary _primary = e.getPrimary();
    boolean _notEquals_3 = (!Objects.equal(_primary, null));
    if (_notEquals_3) {
      primary _primary_1 = e.getPrimary();
      String _str_3 = this.toStr(_primary_1);
      String _plus_4 = (res + _str_3);
      res = _plus_4;
    }
    return res;
  }
  
  public String toStr(final function_call call) {
    String _funct_name = call.getFunct_name();
    String _convertID = this.convertID(_funct_name);
    String _plus = (_convertID + "(");
    arguments _arguments = call.getArguments();
    String _str = this.toStr(_arguments);
    String _plus_1 = (_plus + _str);
    return (_plus_1 + ")");
  }
  
  public String toStr(final primary p) {
    String res = "";
    String _number = p.getNumber();
    boolean _notEquals = (!Objects.equal(_number, null));
    if (_notEquals) {
      String _number_1 = p.getNumber();
      String _plus = (res + _number_1);
      res = _plus;
    }
    variable_name _identifier = p.getIdentifier();
    boolean _notEquals_1 = (!Objects.equal(_identifier, null));
    if (_notEquals_1) {
      variable_name _identifier_1 = p.getIdentifier();
      String _str = this.toStr(_identifier_1);
      String _convertID = this.convertID(_str);
      String _plus_1 = (res + _convertID);
      res = _plus_1;
    }
    return res;
  }
  
  public String toStr(final par_expression e) {
    expression _expression = e.getExpression();
    String _str = this.toStr(_expression);
    String _plus = ("(" + _str);
    return (_plus + ")");
  }
  
  public String toStr(final arguments arg) {
    String res = "";
    EList<argument> _arguments = arg.getArguments();
    Iterator<argument> iterator = _arguments.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      argument a = iterator.next();
      String _identifier = a.getIdentifier();
      boolean _notEquals = (!Objects.equal(_identifier, null));
      if (_notEquals) {
        String _identifier_1 = a.getIdentifier();
        String _plus = (res + _identifier_1);
        String _plus_1 = (_plus + " = ");
        res = _plus_1;
      }
      any_expression _expression = a.getExpression();
      boolean _notEquals_1 = (!Objects.equal(_expression, null));
      if (_notEquals_1) {
        any_expression _expression_1 = a.getExpression();
        String _str = this.toStr(_expression_1);
        String _plus_2 = (res + _str);
        res = _plus_2;
      }
    }
    boolean _hasNext_1 = iterator.hasNext();
    boolean _while = _hasNext_1;
    while (_while) {
      {
        String _plus_3 = (res + ", ");
        res = _plus_3;
        argument a_1 = iterator.next();
        String _identifier_2 = a_1.getIdentifier();
        boolean _notEquals_2 = (!Objects.equal(_identifier_2, null));
        if (_notEquals_2) {
          String _identifier_3 = a_1.getIdentifier();
          String _plus_4 = (res + _identifier_3);
          String _plus_5 = (_plus_4 + " = ");
          res = _plus_5;
        }
        any_expression _expression_2 = a_1.getExpression();
        boolean _notEquals_3 = (!Objects.equal(_expression_2, null));
        if (_notEquals_3) {
          any_expression _expression_3 = a_1.getExpression();
          String _str_1 = this.toStr(_expression_3);
          String _plus_6 = (res + _str_1);
          res = _plus_6;
        }
      }
      boolean _hasNext_2 = iterator.hasNext();
      _while = _hasNext_2;
    }
    return res;
  }
  
  public CharSequence print(final verbatim_block b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _external_code = b.getExternal_code();
      boolean _notEquals = (!Objects.equal(_external_code, null));
      if (_notEquals) {
        String _external_code_1 = b.getExternal_code();
        String _external_code_2 = b.getExternal_code();
        int _length = _external_code_2.length();
        int _minus = (_length - 3);
        String printedCode = _external_code_1.substring(3, _minus);
        _builder.newLineIfNotEmpty();
        _builder.append(printedCode, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      target_block _block = b.getBlock();
      boolean _notEquals_1 = (!Objects.equal(_block, null));
      if (_notEquals_1) {
        target_block _block_1 = b.getBlock();
        CharSequence _print = this.print(_block_1);
        _builder.append(_print, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print(final target_block b) {
    StringConcatenation _builder = new StringConcatenation();
    String _external_code = b.getExternal_code();
    String _external_code_1 = b.getExternal_code();
    int _length = _external_code_1.length();
    int _minus = (_length - 3);
    String printedCode = _external_code.substring(3, _minus);
    _builder.newLineIfNotEmpty();
    _builder.append(printedCode, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print(final block b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<block_statement> _statements = b.getStatements();
      for(final block_statement st : _statements) {
        CharSequence _print = this.print(st);
        _builder.append(_print, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence printVariables(final block b, final String separator) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<block_statement> _statements = b.getStatements();
      boolean _hasElements = false;
      for(final block_statement s : _statements) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(separator, "");
        }
        {
          variable_declaration _variable_declaration = s.getVariable_declaration();
          boolean _notEquals = (!Objects.equal(_variable_declaration, null));
          if (_notEquals) {
            variable_declaration _variable_declaration_1 = s.getVariable_declaration();
            CharSequence _print = this.print(_variable_declaration_1);
            _builder.append(_print, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence printVariableNames(final block b, final String separator) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<block_statement> _statements = b.getStatements();
      boolean _hasElements = false;
      for(final block_statement s : _statements) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(separator, "");
        }
        {
          variable_declaration _variable_declaration = s.getVariable_declaration();
          boolean _notEquals = (!Objects.equal(_variable_declaration, null));
          if (_notEquals) {
            variable_declaration _variable_declaration_1 = s.getVariable_declaration();
            variable_name _identifier = _variable_declaration_1.getIdentifier();
            String _str = this.toStr(_identifier);
            String _convertID = this.convertID(_str);
            _builder.append(_convertID, "");
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print(final block_statement st) {
    StringConcatenation _builder = new StringConcatenation();
    {
      variable_declaration _variable_declaration = st.getVariable_declaration();
      boolean _notEquals = (!Objects.equal(_variable_declaration, null));
      if (_notEquals) {
        variable_declaration _variable_declaration_1 = st.getVariable_declaration();
        CharSequence _print = this.print(_variable_declaration_1);
        _builder.append(_print, "");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      function_call _function_call = st.getFunction_call();
      boolean _notEquals_1 = (!Objects.equal(_function_call, null));
      if (_notEquals_1) {
        function_call _function_call_1 = st.getFunction_call();
        CharSequence _print_1 = this.print(_function_call_1);
        _builder.append(_print_1, "");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      statement _statement = st.getStatement();
      boolean _notEquals_2 = (!Objects.equal(_statement, null));
      if (_notEquals_2) {
        statement _statement_1 = st.getStatement();
        CharSequence _print_2 = this.print(_statement_1);
        _builder.append(_print_2, "");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      verbatim_block _verbatim_block = st.getVerbatim_block();
      boolean _notEquals_3 = (!Objects.equal(_verbatim_block, null));
      if (_notEquals_3) {
        verbatim_block _verbatim_block_1 = st.getVerbatim_block();
        CharSequence _print_3 = this.print(_verbatim_block_1);
        _builder.append(_print_3, "");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print(final function_call call) {
    StringConcatenation _builder = new StringConcatenation();
    String _funct_name = call.getFunct_name();
    _builder.append(_funct_name, "");
    _builder.append("(");
    arguments _arguments = call.getArguments();
    CharSequence _print = this.print(_arguments);
    _builder.append(_print, "");
    _builder.append(")");
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
        _builder.append("if ");
        par_expression _par_expression_1 = s.getPar_expression();
        CharSequence _print_1 = this.print(_par_expression_1);
        _builder.append(_print_1, "");
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
            _builder.append("else ");
            _builder.newLine();
            _builder.append("\t");
            block_statement _else_statement_1 = s.getElse_statement();
            CharSequence _print_3 = this.print(_else_statement_1);
            _builder.append(_print_3, "	");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence print(final variable_name name) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(name);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final variable_declaration v) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(v);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final any_expression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final random_list l) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(l);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final list l) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(l);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final ode_list l) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(l);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final expression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final conditional_expression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final conditional_or_expression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final conditional_and_expression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final relational_expression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final additive_expression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final multiplicative_expression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final power_expression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final unary_expression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final primary p) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(p);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final par_expression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final arguments arg) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(arg);
    _builder.append(_str, "");
    return _builder;
  }
}
