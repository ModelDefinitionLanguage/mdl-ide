package org.ddmore.mdl.generator;

import com.google.common.base.Objects;
import org.ddmore.mdl.generator.MdlPrinting;
import org.ddmore.mdl.mdl.any_expression;
import org.ddmore.mdl.mdl.argument;
import org.ddmore.mdl.mdl.arguments;
import org.ddmore.mdl.mdl.block;
import org.ddmore.mdl.mdl.block_statement;
import org.ddmore.mdl.mdl.expression;
import org.ddmore.mdl.mdl.formal_arguments;
import org.ddmore.mdl.mdl.function_body;
import org.ddmore.mdl.mdl.function_call;
import org.ddmore.mdl.mdl.function_declaration;
import org.ddmore.mdl.mdl.function_subblock;
import org.ddmore.mdl.mdl.primary;
import org.ddmore.mdl.mdl.statement;
import org.ddmore.mdl.mdl.variable_declaration;
import org.ddmore.mdl.mdl.variable_name;
import org.eclipse.emf.common.util.EList;
import org.eclipse.xtend2.lib.StringConcatenation;

@SuppressWarnings("all")
public class MDLXML extends MdlPrinting {
  public CharSequence printXML(final primary p) {
    StringConcatenation _builder = new StringConcatenation();
    {
      String _literal = p.getLiteral();
      boolean _notEquals = (!Objects.equal(_literal, null));
      if (_notEquals) {
        _builder.append("<Scalar value=");
        String _literal_1 = p.getLiteral();
        _builder.append(_literal_1, "");
        _builder.append(">");
        _builder.newLineIfNotEmpty();
        _builder.append("</Scalar>");
        _builder.newLine();
      }
    }
    {
      variable_name _identifier = p.getIdentifier();
      boolean _notEquals_1 = (!Objects.equal(_identifier, null));
      if (_notEquals_1) {
        variable_name _identifier_1 = p.getIdentifier();
        CharSequence _printXML = this.printXML(_identifier_1);
        _builder.append(_printXML, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence printXML(final variable_name name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Variable varName=\"");
    String _str = this.toStr(name);
    _builder.append(_str, "");
    _builder.append("\"/>");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
  
  public CharSequence printXML(final function_declaration f) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Description></Description>");
    _builder.newLine();
    _builder.append("<FunctionDefinition>");
    _builder.newLine();
    {
      formal_arguments _formal_arguments = f.getFormal_arguments();
      boolean _notEquals = (!Objects.equal(_formal_arguments, null));
      if (_notEquals) {
        _builder.append("            ");
        formal_arguments _formal_arguments_1 = f.getFormal_arguments();
        CharSequence _printXML = this.printXML(_formal_arguments_1);
        _builder.append(_printXML, "            ");
        _builder.newLineIfNotEmpty();
      }
    }
    _builder.append("\t");
    _builder.append("<Definition>");
    _builder.newLine();
    _builder.append("\t\t");
    function_body _function_body = f.getFunction_body();
    CharSequence _printXML_1 = this.printXML(_function_body);
    _builder.append(_printXML_1, "		");
    _builder.newLineIfNotEmpty();
    _builder.append("\t");
    _builder.append("</Definition>");
    _builder.newLine();
    _builder.append("</FunctionDefinition>\t\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence printXML(final formal_arguments args) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<String> _identifiers = args.getIdentifiers();
      for(final String a : _identifiers) {
        _builder.append("<FunctionParameter paramName=\"");
        _builder.append(a, "");
        _builder.append("\" symbolType=\"scalar\">");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("<Description></Description>");
        _builder.newLine();
        _builder.append("</FunctionParameter>");
        _builder.newLine();
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
            CharSequence _printXML = this.printXML(_estimate_defn_1);
            _builder.append(_printXML, "");
            _builder.newLineIfNotEmpty();
          }
        }
        {
          block _simulate_defn = b.getSimulate_defn();
          boolean _notEquals_1 = (!Objects.equal(_simulate_defn, null));
          if (_notEquals_1) {
            block _simulate_defn_1 = b.getSimulate_defn();
            CharSequence _printXML_1 = this.printXML(_simulate_defn_1);
            _builder.append(_printXML_1, "");
            _builder.newLineIfNotEmpty();
          }
        }
        _builder.newLine();
      }
    }
    return _builder;
  }
  
  public CharSequence printXML(final block b) {
    StringConcatenation _builder = new StringConcatenation();
    {
      EList<block_statement> _statements = b.getStatements();
      for(final block_statement st : _statements) {
        CharSequence _printXML = this.printXML(st);
        _builder.append(_printXML, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence printXML(final block_statement st) {
    StringConcatenation _builder = new StringConcatenation();
    {
      variable_declaration _variable_declaration = st.getVariable_declaration();
      boolean _notEquals = (!Objects.equal(_variable_declaration, null));
      if (_notEquals) {
        variable_declaration _variable_declaration_1 = st.getVariable_declaration();
        Object _printXML = this.printXML(_variable_declaration_1);
        _builder.append(_printXML, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      function_call _function_call = st.getFunction_call();
      boolean _notEquals_1 = (!Objects.equal(_function_call, null));
      if (_notEquals_1) {
        function_call _function_call_1 = st.getFunction_call();
        CharSequence _printXML_1 = this.printXML(_function_call_1);
        _builder.append(_printXML_1, "");
        _builder.newLineIfNotEmpty();
      }
    }
    {
      statement _statement = st.getStatement();
      boolean _notEquals_2 = (!Objects.equal(_statement, null));
      if (_notEquals_2) {
        statement _statement_1 = st.getStatement();
        Object _printXML_2 = this.printXML(_statement_1);
        _builder.append(_printXML_2, "");
        _builder.newLineIfNotEmpty();
      }
    }
    return _builder;
  }
  
  public CharSequence printXML(final function_call call) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Function functionName=\"");
    String _funct_name = call.getFunct_name();
    _builder.append(_funct_name, "");
    _builder.append("\">");
    _builder.newLineIfNotEmpty();
    {
      arguments _arguments = call.getArguments();
      EList<argument> _arguments_1 = _arguments.getArguments();
      for(final argument arg : _arguments_1) {
        _builder.append("<FunctionParameter varName=\"");
        String _identifier = arg.getIdentifier();
        _builder.append(_identifier, "");
        _builder.append("\">");
        _builder.newLineIfNotEmpty();
        _builder.append("\t");
        _builder.append("<Constant value=\"");
        any_expression _expression = arg.getExpression();
        String _str = this.toStr(_expression);
        _builder.append(_str, "	");
        _builder.append("\"/>");
        _builder.newLineIfNotEmpty();
        _builder.append("</FunctionParameter>");
        _builder.newLine();
      }
    }
    _builder.append("</Function>");
    _builder.newLine();
    return _builder;
  }
  
  public Object printXML(final variable_declaration declaration) {
    return null;
  }
  
  public Object printXML(final statement statement) {
    return null;
  }
  
  public CharSequence toXML(final expression expr) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("...");
    _builder.newLine();
    return _builder;
  }
}
