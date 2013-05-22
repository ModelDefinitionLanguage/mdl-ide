package org.ddmore.mdl.generator;

import com.google.common.base.Objects;
import java.util.Iterator;
import org.ddmore.mdl.mdl.AdditiveExpression;
import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.Block;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.Categorical;
import org.ddmore.mdl.mdl.ConditionalExpression;
import org.ddmore.mdl.mdl.ConditionalStatement;
import org.ddmore.mdl.mdl.Continuous;
import org.ddmore.mdl.mdl.Covariate;
import org.ddmore.mdl.mdl.Distribution;
import org.ddmore.mdl.mdl.EnumType;
import org.ddmore.mdl.mdl.Expression;
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.GroupVariablesBlock;
import org.ddmore.mdl.mdl.IndividualVariablesBlock;
import org.ddmore.mdl.mdl.LevelType;
import org.ddmore.mdl.mdl.LibraryBlock;
import org.ddmore.mdl.mdl.Likelyhood;
import org.ddmore.mdl.mdl.List;
import org.ddmore.mdl.mdl.LogicalExpression;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.Missing;
import org.ddmore.mdl.mdl.MixtureBlock;
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
import org.ddmore.mdl.mdl.ParExpression;
import org.ddmore.mdl.mdl.PowerExpression;
import org.ddmore.mdl.mdl.Primary;
import org.ddmore.mdl.mdl.RandomList;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.SymbolModification;
import org.ddmore.mdl.mdl.TargetBlock;
import org.ddmore.mdl.mdl.UnaryExpression;
import org.ddmore.mdl.mdl.Vector;
import org.ddmore.mdl.mdl.VerbatimBlock;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class MdlPrinting {
  public String fileNameUpperCase(final Mcl m) {
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
  
  public String getAttribute(final List v, final String attrName) {
    Arguments _arguments = v.getArguments();
    boolean _notEquals = (!Objects.equal(_arguments, null));
    if (_notEquals) {
      Arguments _arguments_1 = v.getArguments();
      return this.selectAttribute(_arguments_1, attrName);
    }
    return "";
  }
  
  public String getAttribute(final RandomList v, final String attrName) {
    Arguments _arguments = v.getArguments();
    boolean _notEquals = (!Objects.equal(_arguments, null));
    if (_notEquals) {
      Arguments _arguments_1 = v.getArguments();
      return this.selectAttribute(_arguments_1, attrName);
    }
    return "";
  }
  
  public String getAttribute(final OdeList v, final String attrName) {
    Arguments _arguments = v.getArguments();
    boolean _notEquals = (!Objects.equal(_arguments, null));
    if (_notEquals) {
      Arguments _arguments_1 = v.getArguments();
      return this.selectAttribute(_arguments_1, attrName);
    }
    return "";
  }
  
  public String selectAttribute(final Arguments a, final String attrName) {
    EList<Argument> _arguments = a.getArguments();
    for (final Argument arg : _arguments) {
      String _identifier = arg.getIdentifier();
      boolean _equalsIgnoreCase = _identifier.equalsIgnoreCase(attrName);
      if (_equalsIgnoreCase) {
        AnyExpression _expression = arg.getExpression();
        return this.toStr(_expression);
      }
    }
    return "";
  }
  
  public boolean isErrorNonEmpty(final ModelObject o) {
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock mob : _blocks) {
      {
        ModelPredictionBlock _modelPredictionBlock = mob.getModelPredictionBlock();
        boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
        if (_notEquals) {
          ModelPredictionBlock _modelPredictionBlock_1 = mob.getModelPredictionBlock();
          EList<ModelPredictionBlockStatement> _statements = _modelPredictionBlock_1.getStatements();
          for (final ModelPredictionBlockStatement s : _statements) {
            BlockStatement _statement = s.getStatement();
            boolean _notEquals_1 = (!Objects.equal(_statement, null));
            if (_notEquals_1) {
              return true;
            }
          }
        }
        ObservationBlock _observationBlock = mob.getObservationBlock();
        boolean _notEquals_2 = (!Objects.equal(_observationBlock, null));
        if (_notEquals_2) {
          ObservationBlock _observationBlock_1 = mob.getObservationBlock();
          EList<BlockStatement> _statements_1 = _observationBlock_1.getStatements();
          for (final BlockStatement s_1 : _statements_1) {
            ConditionalStatement _statement_1 = s_1.getStatement();
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
  
  public boolean isODEDefined(final ModelObject o) {
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock mob : _blocks) {
      ModelPredictionBlock _modelPredictionBlock = mob.getModelPredictionBlock();
      boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
      if (_notEquals) {
        ModelPredictionBlock b = mob.getModelPredictionBlock();
        EList<ModelPredictionBlockStatement> _statements = b.getStatements();
        for (final ModelPredictionBlockStatement s : _statements) {
          OdeBlock _odeBlock = s.getOdeBlock();
          boolean _notEquals_1 = (!Objects.equal(_odeBlock, null));
          if (_notEquals_1) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public boolean isPKDefined(final ModelObject o) {
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock mob : _blocks) {
      {
        GroupVariablesBlock _groupVariablesBlock = mob.getGroupVariablesBlock();
        boolean _notEquals = (!Objects.equal(_groupVariablesBlock, null));
        if (_notEquals) {
          GroupVariablesBlock _groupVariablesBlock_1 = mob.getGroupVariablesBlock();
          boolean _notEquals_1 = (!Objects.equal(_groupVariablesBlock_1, null));
          if (_notEquals_1) {
            return true;
          }
        }
        IndividualVariablesBlock _individualVariablesBlock = mob.getIndividualVariablesBlock();
        boolean _notEquals_2 = (!Objects.equal(_individualVariablesBlock, null));
        if (_notEquals_2) {
          IndividualVariablesBlock _individualVariablesBlock_1 = mob.getIndividualVariablesBlock();
          EList<BlockStatement> _statements = _individualVariablesBlock_1.getStatements();
          int _size = _statements.size();
          boolean _greaterThan = (_size > 0);
          if (_greaterThan) {
            return true;
          }
        }
      }
    }
    return false;
  }
  
  public boolean isLibraryDefined(final ModelObject o) {
    EList<ModelObjectBlock> _blocks = o.getBlocks();
    for (final ModelObjectBlock mob : _blocks) {
      ModelPredictionBlock _modelPredictionBlock = mob.getModelPredictionBlock();
      boolean _notEquals = (!Objects.equal(_modelPredictionBlock, null));
      if (_notEquals) {
        ModelPredictionBlock _modelPredictionBlock_1 = mob.getModelPredictionBlock();
        EList<ModelPredictionBlockStatement> _statements = _modelPredictionBlock_1.getStatements();
        for (final ModelPredictionBlockStatement s : _statements) {
          LibraryBlock _libraryBlock = s.getLibraryBlock();
          boolean _notEquals_1 = (!Objects.equal(_libraryBlock, null));
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
  
  public String toStr(final FullyQualifiedSymbolName name) {
    String res = "";
    ObjectName _object = name.getObject();
    boolean _notEquals = (!Objects.equal(_object, null));
    if (_notEquals) {
      ObjectName _object_1 = name.getObject();
      String _plus = (_object_1 + "$");
      res = _plus;
    }
    String _identifier = name.getIdentifier();
    String _convertID = this.convertID(_identifier);
    String _plus_1 = (res + _convertID);
    res = _plus_1;
    return res;
  }
  
  public String toStr(final SymbolDeclaration v) {
    String res = "";
    String _function = v.getFunction();
    boolean _notEquals = (!Objects.equal(_function, null));
    if (_notEquals) {
      String _function_1 = v.getFunction();
      String _plus = (res + _function_1);
      String _plus_1 = (_plus + "(");
      res = _plus_1;
    }
    String _identifier = v.getIdentifier();
    boolean _notEquals_1 = (!Objects.equal(_identifier, null));
    if (_notEquals_1) {
      String _identifier_1 = v.getIdentifier();
      String _convertID = this.convertID(_identifier_1);
      String _plus_2 = (res + _convertID);
      res = _plus_2;
    }
    String _function_2 = v.getFunction();
    boolean _notEquals_2 = (!Objects.equal(_function_2, null));
    if (_notEquals_2) {
      String _plus_3 = (res + ")");
      res = _plus_3;
    }
    AnyExpression _expression = v.getExpression();
    boolean _notEquals_3 = (!Objects.equal(_expression, null));
    if (_notEquals_3) {
      String _plus_4 = (res + " = ");
      AnyExpression _expression_1 = v.getExpression();
      String _str = this.toStr(_expression_1);
      String _plus_5 = (_plus_4 + _str);
      res = _plus_5;
    }
    RandomList _randomList = v.getRandomList();
    boolean _notEquals_4 = (!Objects.equal(_randomList, null));
    if (_notEquals_4) {
      String _plus_6 = (res + " = ");
      RandomList _randomList_1 = v.getRandomList();
      String _str_1 = this.toStr(_randomList_1);
      String _plus_7 = (_plus_6 + _str_1);
      res = _plus_7;
    }
    return res;
  }
  
  public String toStr(final SymbolModification v) {
    String res = "";
    FullyQualifiedSymbolName _identifier = v.getIdentifier();
    boolean _notEquals = (!Objects.equal(_identifier, null));
    if (_notEquals) {
      FullyQualifiedSymbolName _identifier_1 = v.getIdentifier();
      this.toStr(_identifier_1);
    }
    List _list = v.getList();
    boolean _notEquals_1 = (!Objects.equal(_list, null));
    if (_notEquals_1) {
      String _plus = (res + " = ");
      List _list_1 = v.getList();
      String _str = this.toStr(_list_1);
      String _plus_1 = (_plus + _str);
      res = _plus_1;
    }
    return res;
  }
  
  public String toStr(final AnyExpression e) {
    String res = "";
    Expression _expression = e.getExpression();
    boolean _notEquals = (!Objects.equal(_expression, null));
    if (_notEquals) {
      Expression _expression_1 = e.getExpression();
      String _str = this.toStr(_expression_1);
      String _plus = (res + _str);
      res = _plus;
    }
    List _list = e.getList();
    boolean _notEquals_1 = (!Objects.equal(_list, null));
    if (_notEquals_1) {
      List _list_1 = e.getList();
      String _str_1 = this.toStr(_list_1);
      String _plus_1 = (res + _str_1);
      res = _plus_1;
    }
    OdeList _odeList = e.getOdeList();
    boolean _notEquals_2 = (!Objects.equal(_odeList, null));
    if (_notEquals_2) {
      OdeList _odeList_1 = e.getOdeList();
      String _str_2 = this.toStr(_odeList_1);
      String _plus_2 = (res + _str_2);
      res = _plus_2;
    }
    EnumType _type = e.getType();
    boolean _notEquals_3 = (!Objects.equal(_type, null));
    if (_notEquals_3) {
      EnumType _type_1 = e.getType();
      String _str_3 = this.toStr(_type_1);
      String _plus_3 = (res + _str_3);
      res = _plus_3;
    }
    return res;
  }
  
  public String toStr(final EnumType type) {
    Categorical _categorical = type.getCategorical();
    boolean _notEquals = (!Objects.equal(_categorical, null));
    if (_notEquals) {
      String res = "";
      Categorical _categorical_1 = type.getCategorical();
      Arguments _arguments = _categorical_1.getArguments();
      boolean _notEquals_1 = (!Objects.equal(_arguments, null));
      if (_notEquals_1) {
        Categorical _categorical_2 = type.getCategorical();
        Arguments _arguments_1 = _categorical_2.getArguments();
        String _str = this.toStr(_arguments_1);
        res = _str;
      }
      Categorical _categorical_3 = type.getCategorical();
      String _identifier = _categorical_3.getIdentifier();
      String _plus = (_identifier + "(");
      String _plus_1 = (_plus + res);
      return (_plus_1 + ")");
    }
    Continuous _continuous = type.getContinuous();
    boolean _notEquals_2 = (!Objects.equal(_continuous, null));
    if (_notEquals_2) {
      Continuous _continuous_1 = type.getContinuous();
      return _continuous_1.getIdentifier();
    }
    Covariate _covariate = type.getCovariate();
    boolean _notEquals_3 = (!Objects.equal(_covariate, null));
    if (_notEquals_3) {
      Covariate _covariate_1 = type.getCovariate();
      return _covariate_1.getIdentifier();
    }
    Distribution _distribution = type.getDistribution();
    boolean _notEquals_4 = (!Objects.equal(_distribution, null));
    if (_notEquals_4) {
      Distribution _distribution_1 = type.getDistribution();
      return this.toStr(_distribution_1);
    }
    LevelType _level = type.getLevel();
    boolean _notEquals_5 = (!Objects.equal(_level, null));
    if (_notEquals_5) {
      LevelType _level_1 = type.getLevel();
      return this.toStr(_level_1);
    }
    Likelyhood _likelyhood = type.getLikelyhood();
    boolean _notEquals_6 = (!Objects.equal(_likelyhood, null));
    if (_notEquals_6) {
      Likelyhood _likelyhood_1 = type.getLikelyhood();
      return _likelyhood_1.getIdentifier();
    }
    Missing _missing = type.getMissing();
    boolean _notEquals_7 = (!Objects.equal(_missing, null));
    if (_notEquals_7) {
      Missing _missing_1 = type.getMissing();
      return _missing_1.getIdentifier();
    }
    return null;
  }
  
  public String toStr(final Distribution d) {
    String _normal = d.getNormal();
    boolean _notEquals = (!Objects.equal(_normal, null));
    if (_notEquals) {
      return d.getNormal();
    }
    String _binomial = d.getBinomial();
    boolean _notEquals_1 = (!Objects.equal(_binomial, null));
    if (_notEquals_1) {
      return d.getBinomial();
    }
    String _poisson = d.getPoisson();
    boolean _notEquals_2 = (!Objects.equal(_poisson, null));
    if (_notEquals_2) {
      return d.getPoisson();
    }
    String _student_t = d.getStudent_t();
    boolean _notEquals_3 = (!Objects.equal(_student_t, null));
    if (_notEquals_3) {
      return d.getStudent_t();
    }
    String _mvnormal = d.getMvnormal();
    boolean _notEquals_4 = (!Objects.equal(_mvnormal, null));
    if (_notEquals_4) {
      return d.getMvnormal();
    }
    return null;
  }
  
  public String toStr(final LevelType l) {
    String _mdv = l.getMdv();
    boolean _notEquals = (!Objects.equal(_mdv, null));
    if (_notEquals) {
      return l.getMdv();
    }
    String _id = l.getId();
    boolean _notEquals_1 = (!Objects.equal(_id, null));
    if (_notEquals_1) {
      return l.getId();
    }
    String _dv = l.getDv();
    boolean _notEquals_2 = (!Objects.equal(_dv, null));
    if (_notEquals_2) {
      return l.getDv();
    }
    String _idv = l.getIdv();
    boolean _notEquals_3 = (!Objects.equal(_idv, null));
    if (_notEquals_3) {
      return l.getIdv();
    }
    return null;
  }
  
  public String toStr(final RandomList l) {
    Arguments _arguments = l.getArguments();
    boolean _notEquals = (!Objects.equal(_arguments, null));
    if (_notEquals) {
      String _plus = ("~" + "(");
      Arguments _arguments_1 = l.getArguments();
      String _str = this.toStr(_arguments_1);
      String _plus_1 = (_plus + _str);
      return (_plus_1 + ")");
    }
    return "";
  }
  
  public String toStr(final List l) {
    Arguments _arguments = l.getArguments();
    boolean _notEquals = (!Objects.equal(_arguments, null));
    if (_notEquals) {
      String _plus = ("list" + "(");
      Arguments _arguments_1 = l.getArguments();
      String _str = this.toStr(_arguments_1);
      String _plus_1 = (_plus + _str);
      return (_plus_1 + ")");
    }
    return "";
  }
  
  public String toStr(final OdeList l) {
    Arguments _arguments = l.getArguments();
    boolean _notEquals = (!Objects.equal(_arguments, null));
    if (_notEquals) {
      String _plus = ("ode" + "(");
      Arguments _arguments_1 = l.getArguments();
      String _str = this.toStr(_arguments_1);
      String _plus_1 = (_plus + _str);
      return (_plus_1 + ")");
    }
    return null;
  }
  
  public String toStr(final Expression e) {
    ConditionalExpression _conditionalExpression = e.getConditionalExpression();
    return this.toStr(_conditionalExpression);
  }
  
  public String toStr(final ConditionalExpression e) {
    OrExpression _expression = e.getExpression();
    String res = this.toStr(_expression);
    Expression _expression1 = e.getExpression1();
    boolean _notEquals = (!Objects.equal(_expression1, null));
    if (_notEquals) {
      String _plus = (res + "?");
      Expression _expression1_1 = e.getExpression1();
      String _str = this.toStr(_expression1_1);
      String _plus_1 = (_plus + _str);
      String _plus_2 = (_plus_1 + ":");
      Expression _expression2 = e.getExpression2();
      String _str_1 = this.toStr(_expression2);
      String _plus_3 = (_plus_2 + _str_1);
      res = _plus_3;
    }
    return res;
  }
  
  public String toStr(final OrExpression e) {
    String res = "";
    EList<AndExpression> _expression = e.getExpression();
    Iterator<AndExpression> iterator = _expression.iterator();
    EList<String> _operator = e.getOperator();
    Iterator<String> operatorIterator = _operator.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      AndExpression _next = iterator.next();
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
      AndExpression _next_2 = iterator.next();
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
  
  public String toStr(final AndExpression e) {
    String res = "";
    EList<LogicalExpression> _expression = e.getExpression();
    Iterator<LogicalExpression> iterator = _expression.iterator();
    EList<String> _operator = e.getOperator();
    Iterator<String> operatorIterator = _operator.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      LogicalExpression _next = iterator.next();
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
      LogicalExpression _next_2 = iterator.next();
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
  
  public String toStr(final LogicalExpression e) {
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
    EList<AdditiveExpression> _expression = e.getExpression();
    Iterator<AdditiveExpression> iterator = _expression.iterator();
    EList<String> _operator = e.getOperator();
    Iterator<String> operatorIterator = _operator.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      AdditiveExpression _next = iterator.next();
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
      AdditiveExpression _next_2 = iterator.next();
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
  
  public String toStr(final AdditiveExpression e) {
    String res = "";
    EList<MultiplicativeExpression> _expression = e.getExpression();
    boolean _notEquals = (!Objects.equal(_expression, null));
    if (_notEquals) {
      EList<MultiplicativeExpression> _expression_1 = e.getExpression();
      Iterator<MultiplicativeExpression> iterator = _expression_1.iterator();
      EList<String> _operator = e.getOperator();
      Iterator<String> operatorIterator = _operator.iterator();
      boolean _hasNext = iterator.hasNext();
      if (_hasNext) {
        MultiplicativeExpression _next = iterator.next();
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
        MultiplicativeExpression _next_2 = iterator.next();
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
    }
    EList<String> _string = e.getString();
    boolean _notEquals_1 = (!Objects.equal(_string, null));
    if (_notEquals_1) {
      EList<MultiplicativeExpression> _expression_2 = e.getExpression();
      Iterator<MultiplicativeExpression> iterator_1 = _expression_2.iterator();
      EList<String> _operator_1 = e.getOperator();
      Iterator<String> operatorIterator_1 = _operator_1.iterator();
      boolean _hasNext_3 = iterator_1.hasNext();
      if (_hasNext_3) {
        MultiplicativeExpression _next_1 = iterator_1.next();
        String _str_1 = this.toStr(_next_1);
        res = _str_1;
      }
      boolean _and_1 = false;
      boolean _hasNext_4 = iterator_1.hasNext();
      if (!_hasNext_4) {
        _and_1 = false;
      } else {
        boolean _hasNext_5 = operatorIterator_1.hasNext();
        _and_1 = (_hasNext_4 && _hasNext_5);
      }
      boolean _while_1 = _and_1;
      while (_while_1) {
        String _next_2 = operatorIterator_1.next();
        String _convertOperator = this.convertOperator(_next_2);
        String _plus = (res + _convertOperator);
        MultiplicativeExpression _next_3 = iterator_1.next();
        String _str_2 = this.toStr(_next_3);
        String _plus_1 = (_plus + _str_2);
        res = _plus_1;
        boolean _and_2 = false;
        boolean _hasNext_6 = iterator_1.hasNext();
        if (!_hasNext_6) {
          _and_2 = false;
        } else {
          boolean _hasNext_7 = operatorIterator_1.hasNext();
          _and_2 = (_hasNext_6 && _hasNext_7);
        }
        _while_1 = _and_2;
      }
    }
    return res;
  }
  
  public String toStr(final MultiplicativeExpression e) {
    String res = "";
    EList<PowerExpression> _expression = e.getExpression();
    Iterator<PowerExpression> iterator = _expression.iterator();
    EList<String> _operator = e.getOperator();
    Iterator<String> operatorIterator = _operator.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      PowerExpression _next = iterator.next();
      String _str = this.toStr(_next);
      res = _str;
    }
    boolean _hasNext_1 = iterator.hasNext();
    boolean _while = _hasNext_1;
    while (_while) {
      String _next_1 = operatorIterator.next();
      String _convertOperator = this.convertOperator(_next_1);
      String _plus = (res + _convertOperator);
      PowerExpression _next_2 = iterator.next();
      String _str_1 = this.toStr(_next_2);
      String _plus_1 = (_plus + _str_1);
      res = _plus_1;
      boolean _hasNext_2 = iterator.hasNext();
      _while = _hasNext_2;
    }
    return res;
  }
  
  public String toStr(final PowerExpression e) {
    String res = "";
    EList<UnaryExpression> _expression = e.getExpression();
    Iterator<UnaryExpression> iterator = _expression.iterator();
    EList<String> _operator = e.getOperator();
    Iterator<String> operatorIterator = _operator.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      UnaryExpression _next = iterator.next();
      String _str = this.toStr(_next);
      res = _str;
    }
    boolean _hasNext_1 = iterator.hasNext();
    boolean _while = _hasNext_1;
    while (_while) {
      String _next_1 = operatorIterator.next();
      String _convertOperator = this.convertOperator(_next_1);
      String _plus = (res + _convertOperator);
      UnaryExpression _next_2 = iterator.next();
      String _str_1 = this.toStr(_next_2);
      String _plus_1 = (_plus + _str_1);
      res = _plus_1;
      boolean _hasNext_2 = iterator.hasNext();
      _while = _hasNext_2;
    }
    return res;
  }
  
  public String toStr(final UnaryExpression e) {
    String res = "";
    UnaryExpression _expression = e.getExpression();
    boolean _notEquals = (!Objects.equal(_expression, null));
    if (_notEquals) {
      String _operator = e.getOperator();
      String _convertOperator = this.convertOperator(_operator);
      String _plus = (res + _convertOperator);
      UnaryExpression _expression_1 = e.getExpression();
      String _str = this.toStr(_expression_1);
      String _plus_1 = (_plus + _str);
      res = _plus_1;
    }
    ParExpression _parExpression = e.getParExpression();
    boolean _notEquals_1 = (!Objects.equal(_parExpression, null));
    if (_notEquals_1) {
      ParExpression _parExpression_1 = e.getParExpression();
      String _str_1 = this.toStr(_parExpression_1);
      String _plus_2 = (res + _str_1);
      res = _plus_2;
    }
    Primary _primary = e.getPrimary();
    boolean _notEquals_2 = (!Objects.equal(_primary, null));
    if (_notEquals_2) {
      Primary _primary_1 = e.getPrimary();
      String _str_2 = this.toStr(_primary_1);
      String _plus_3 = (res + _str_2);
      res = _plus_3;
    }
    return res;
  }
  
  public String toStr(final FunctionCall call) {
    FullyQualifiedSymbolName _identifier = call.getIdentifier();
    String _str = this.toStr(_identifier);
    String _plus = (_str + "(");
    Arguments _arguments = call.getArguments();
    String _str_1 = this.toStr(_arguments);
    String _plus_1 = (_plus + _str_1);
    return (_plus_1 + ")");
  }
  
  public String toStr(final Primary p) {
    String res = "";
    String _number = p.getNumber();
    boolean _notEquals = (!Objects.equal(_number, null));
    if (_notEquals) {
      String _number_1 = p.getNumber();
      String _plus = (res + _number_1);
      res = _plus;
    }
    FullyQualifiedSymbolName _symbol = p.getSymbol();
    boolean _notEquals_1 = (!Objects.equal(_symbol, null));
    if (_notEquals_1) {
      FullyQualifiedSymbolName _symbol_1 = p.getSymbol();
      String _str = this.toStr(_symbol_1);
      String _convertID = this.convertID(_str);
      String _plus_1 = (res + _convertID);
      res = _plus_1;
    }
    FunctionCall _functionCall = p.getFunctionCall();
    boolean _notEquals_2 = (!Objects.equal(_functionCall, null));
    if (_notEquals_2) {
      FunctionCall _functionCall_1 = p.getFunctionCall();
      String _str_1 = this.toStr(_functionCall_1);
      String _plus_2 = (res + _str_1);
      res = _plus_2;
    }
    Vector _vector = p.getVector();
    boolean _notEquals_3 = (!Objects.equal(_vector, null));
    if (_notEquals_3) {
      Vector _vector_1 = p.getVector();
      String _str_2 = this.toStr(_vector_1);
      String _plus_3 = (res + _str_2);
      res = _plus_3;
    }
    return res;
  }
  
  public String toStr(final Vector v) {
    String _identifier = v.getIdentifier();
    String res = (_identifier + "(");
    EList<Expression> _values = v.getValues();
    Iterator<Expression> iterator = _values.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      Expression _next = iterator.next();
      String _str = this.toStr(_next);
      String _plus = (res + _str);
      res = _plus;
    }
    boolean _hasNext_1 = iterator.hasNext();
    boolean _while = _hasNext_1;
    while (_while) {
      {
        String _plus_1 = (res + ", ");
        res = _plus_1;
        Expression _next_1 = iterator.next();
        String _str_1 = this.toStr(_next_1);
        String _plus_2 = (res + _str_1);
        res = _plus_2;
      }
      boolean _hasNext_2 = iterator.hasNext();
      _while = _hasNext_2;
    }
    return (res + ")");
  }
  
  public String toStr(final ParExpression e) {
    Expression _expression = e.getExpression();
    String _str = this.toStr(_expression);
    String _plus = ("(" + _str);
    return (_plus + ")");
  }
  
  public String toStr(final Arguments arg) {
    String res = "";
    EList<Argument> _arguments = arg.getArguments();
    Iterator<Argument> iterator = _arguments.iterator();
    boolean _hasNext = iterator.hasNext();
    if (_hasNext) {
      Argument a = iterator.next();
      String _identifier = a.getIdentifier();
      boolean _notEquals = (!Objects.equal(_identifier, null));
      if (_notEquals) {
        String _identifier_1 = a.getIdentifier();
        String _plus = (res + _identifier_1);
        String _plus_1 = (_plus + " = ");
        res = _plus_1;
      }
      AnyExpression _expression = a.getExpression();
      boolean _notEquals_1 = (!Objects.equal(_expression, null));
      if (_notEquals_1) {
        AnyExpression _expression_1 = a.getExpression();
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
        Argument a_1 = iterator.next();
        String _identifier_2 = a_1.getIdentifier();
        boolean _notEquals_2 = (!Objects.equal(_identifier_2, null));
        if (_notEquals_2) {
          String _identifier_3 = a_1.getIdentifier();
          String _plus_4 = (res + _identifier_3);
          String _plus_5 = (_plus_4 + " = ");
          res = _plus_5;
        }
        AnyExpression _expression_2 = a_1.getExpression();
        boolean _notEquals_3 = (!Objects.equal(_expression_2, null));
        if (_notEquals_3) {
          AnyExpression _expression_3 = a_1.getExpression();
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
  
  public CharSequence print(final VerbatimBlock b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _externalCode = b.getExternalCode();
      boolean _notEquals = (!Objects.equal(_externalCode, null));
      if (_notEquals) {
        String _externalCode_1 = b.getExternalCode();
        String _externalCode_2 = b.getExternalCode();
        int _length = _externalCode_2.length();
        int _minus = (_length - 3);
        String printedCode = _externalCode_1.substring(3, _minus);
        _builder.newLineIfNotEmpty();
        _builder.append(printedCode, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      TargetBlock _block = b.getBlock();
      boolean _notEquals_1 = (!Objects.equal(_block, null));
      if (_notEquals_1) {
        TargetBlock _block_1 = b.getBlock();
        CharSequence _print = this.print(_block_1);
        _builder.append(_print, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print(final TargetBlock b) {
    StringConcatenation _builder = new StringConcatenation();
    String _externalCode = b.getExternalCode();
    String _externalCode_1 = b.getExternalCode();
    int _length = _externalCode_1.length();
    int _minus = (_length - 3);
    String printedCode = _externalCode.substring(3, _minus);
    _builder.newLineIfNotEmpty();
    _builder.append(printedCode, "");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print(final Block b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<BlockStatement> _statements = b.getStatements();
      for(final BlockStatement st : _statements) {
        CharSequence _print = this.print(st);
        _builder.append(_print, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence printSymbols(final Block b, final String separator) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<BlockStatement> _statements = b.getStatements();
      boolean _hasElements = false;
      for(final BlockStatement s : _statements) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(separator, "");
        }
        {
          SymbolDeclaration _symbol = s.getSymbol();
          boolean _notEquals = (!Objects.equal(_symbol, null));
          if (_notEquals) {
            SymbolDeclaration _symbol_1 = s.getSymbol();
            CharSequence _print = this.print(_symbol_1);
            _builder.append(_print, "");
            _builder.newLineIfNotEmpty();
          }
        }
      }
    }
    return _builder;
  }
  
  public CharSequence print(final ObservationBlock b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<BlockStatement> _statements = b.getStatements();
      for(final BlockStatement s : _statements) {
        CharSequence _print = this.print(s);
        _builder.append(_print, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print(final MixtureBlock b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<BlockStatement> _statements = b.getStatements();
      for(final BlockStatement s : _statements) {
        CharSequence _print = this.print(s);
        _builder.append(_print, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence printSymbolDeclarationNames(final Block b, final String separator) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<BlockStatement> _statements = b.getStatements();
      boolean _hasElements = false;
      for(final BlockStatement s : _statements) {
        if (!_hasElements) {
          _hasElements = true;
        } else {
          _builder.appendImmediate(separator, "");
        }
        {
          SymbolDeclaration _symbol = s.getSymbol();
          boolean _notEquals = (!Objects.equal(_symbol, null));
          if (_notEquals) {
            SymbolDeclaration _symbol_1 = s.getSymbol();
            String _identifier = _symbol_1.getIdentifier();
            String _convertID = this.convertID(_identifier);
            _builder.append(_convertID, "");
          }
        }
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence print(final BlockStatement st) {
    StringConcatenation _builder = new StringConcatenation();
    {
      SymbolDeclaration _symbol = st.getSymbol();
      boolean _notEquals = (!Objects.equal(_symbol, null));
      if (_notEquals) {
        SymbolDeclaration _symbol_1 = st.getSymbol();
        CharSequence _print = this.print(_symbol_1);
        _builder.append(_print, "");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      FunctionCall _functionCall = st.getFunctionCall();
      boolean _notEquals_1 = (!Objects.equal(_functionCall, null));
      if (_notEquals_1) {
        FunctionCall _functionCall_1 = st.getFunctionCall();
        CharSequence _print_1 = this.print(_functionCall_1);
        _builder.append(_print_1, "");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      ConditionalStatement _statement = st.getStatement();
      boolean _notEquals_2 = (!Objects.equal(_statement, null));
      if (_notEquals_2) {
        ConditionalStatement _statement_1 = st.getStatement();
        CharSequence _print_2 = this.print(_statement_1);
        _builder.append(_print_2, "");
      }
    }
    _builder.newLineIfNotEmpty();
    {
      VerbatimBlock _verbatimBlock = st.getVerbatimBlock();
      boolean _notEquals_3 = (!Objects.equal(_verbatimBlock, null));
      if (_notEquals_3) {
        VerbatimBlock _verbatimBlock_1 = st.getVerbatimBlock();
        CharSequence _print_3 = this.print(_verbatimBlock_1);
        _builder.append(_print_3, "");
      }
    }
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence print(final FunctionCall call) {
    StringConcatenation _builder = new StringConcatenation();
    FullyQualifiedSymbolName _identifier = call.getIdentifier();
    String _str = this.toStr(_identifier);
    _builder.append(_str, "");
    _builder.append("(");
    Arguments _arguments = call.getArguments();
    CharSequence _print = this.print(_arguments);
    _builder.append(_print, "");
    _builder.append(")");
    return _builder;
  }
  
  public CharSequence print(final ConditionalStatement s) {
    StringConcatenation _builder = new StringConcatenation();
    {
      ParExpression _parExpression = s.getParExpression();
      boolean _notEquals = (!Objects.equal(_parExpression, null));
      if (_notEquals) {
        _builder.append("if ");
        ParExpression _parExpression_1 = s.getParExpression();
        CharSequence _print = this.print(_parExpression_1);
        _builder.append(_print, "");
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
            _builder.append("else ");
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
  
  public CharSequence print(final SymbolDeclaration v) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(v);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final SymbolModification v) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(v);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final AnyExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final RandomList l) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(l);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final List l) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(l);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final OdeList l) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(l);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final Expression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final ConditionalExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final OrExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final AndExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final LogicalExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final AdditiveExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final MultiplicativeExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final PowerExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final UnaryExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final Primary p) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(p);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final ParExpression e) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(e);
    _builder.append(_str, "");
    return _builder;
  }
  
  public CharSequence print(final Arguments arg) {
    StringConcatenation _builder = new StringConcatenation();
    String _str = this.toStr(arg);
    _builder.append(_str, "");
    return _builder;
  }
}
