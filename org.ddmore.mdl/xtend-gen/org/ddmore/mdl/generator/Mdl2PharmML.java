package org.ddmore.mdl.generator;

import org.ddmore.mdl.generator.Mdl2Nonmem;
import org.ddmore.mdl.mdl.any_expression;
import org.ddmore.mdl.mdl.argument;
import org.ddmore.mdl.mdl.block;
import org.ddmore.mdl.mdl.block_statement;
import org.ddmore.mdl.mdl.data_obj;
import org.ddmore.mdl.mdl.expression;
import org.ddmore.mdl.mdl.formal_arguments;
import org.ddmore.mdl.mdl.function_body;
import org.ddmore.mdl.mdl.function_call;
import org.ddmore.mdl.mdl.function_declaration;
import org.ddmore.mdl.mdl.mcl;
import org.ddmore.mdl.mdl.mcl_obj;
import org.ddmore.mdl.mdl.model_obj;
import org.ddmore.mdl.mdl.param_obj;
import org.ddmore.mdl.mdl.primary;
import org.ddmore.mdl.mdl.random_list;
import org.ddmore.mdl.mdl.task_obj;
import org.ddmore.mdl.mdl.variable_declaration;
import org.ddmore.mdl.mdl.variable_name;
import org.eclipse.emf.common.util.EList;
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
      _builder.append("\uFFFDinfo.print_XS_Comment\uFFFD");
      _builder.newLine();
      _builder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
      _builder.newLine();
      _builder.append("<PharmML ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("name=\"\uFFFDm.eResource.fileName\uFFFD\"");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("independentVar=\"t\" ");
      _builder.newLine();
      _builder.append("\t");
      _builder.append("writtenVersion=\"0.1\">");
      _builder.newLine();
      _builder.append("  \t\t\t");
      _builder.append("\uFFFDFOR o:m.objects\uFFFD\uFFFDo.convertToPharmML\uFFFD\uFFFDENDFOR\uFFFD");
      _builder.newLine();
      _builder.append("</PharmML>");
      _builder.newLine();
      _xblockexpression = (_builder);
    }
    return _xblockexpression;
  }
  
  public CharSequence convertToPharmML(final mcl_obj o) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\uFFFDIF o.model_obj != null\uFFFD\uFFFDo.model_obj.convertToPharmML\uFFFD\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDIF o.data_obj != null\uFFFD\uFFFDo.data_obj.convertToPharmML\uFFFD\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDIF o.param_obj != null\uFFFD\uFFFDo.param_obj.convertToPharmML\uFFFD\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDIF o.task_obj != null\uFFFD\uFFFDo.task_obj.convertToPharmML\uFFFD\uFFFDENDIF\uFFFD");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence convertToPharmML(final model_obj o) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<ModelDefinition>");
    _builder.newLine();
    _builder.append("\uFFFDFOR b:o.blocks\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDIF b.individual_model_obj_block != null\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TODO: Process individual model block");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDIF\tb.model_prediction_obj_block != null\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TODO: Process model prediction block");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDIF\tb.random_variable_definition_block != null\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TODO: Process random variable definition block");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.newLine();
    _builder.append("\uFFFDIF\tb.group_variables != null\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TODO: Process group variables");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDIF\tb.observation_block != null\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TODO: Process observation block");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDIF\tb.estimation_block != null\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDIF b.estimation_block.block != null\uFFFD");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("\uFFFDb.estimation_block.block.print_msteps_EstimationStep\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDIF\tb.simulation_block != null\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDIF b.simulation_block.block != null\uFFFD");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("\uFFFDb.simulation_block.block.print_msteps_SimulationStep\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDENDFOR\uFFFD");
    _builder.newLine();
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
    _builder.append("<!--\uFFFDtext\uFFFD-->");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_ct_AnnotationType(final String text) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Description>\uFFFDtext\uFFFD</Description>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_ct_VariableDefinitionType(final variable_declaration v) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Variable \uFFFDv.identifier.print_ct_SymbId\uFFFD>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDv.identifier.toStr.print_ct_AnnotationType\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDIF v.expression != null\uFFFD");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("\uFFFDv.expression.print_ct_printRhsType\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDENDIF\uFFFD\t");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDIF v.random_list != null\uFFFD");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("\uFFFDv.random_list.print_uncert_DistributionType\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDENDIF\uFFFD\t");
    _builder.newLine();
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
    _builder.append("\uFFFDIF e.expression != null\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDe.expression.print_ct_printRhsType\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDIF e.list != null\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TODO: print list");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD\t");
    _builder.newLine();
    _builder.append("\uFFFDIF e.ode_list != null\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TODO: print ode list");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD\t");
    _builder.newLine();
    _builder.append("\uFFFDIF e.random_list != null\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDe.random_list.print_uncert_DistributionType\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_ct_printRhsType(final expression e) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\uFFFDIF e.conditional_expression != null\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDIF e.string_expression != null\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDe.string_expression.print_MathStringType\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
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
    _builder.append("\uFFFDIF st.variable_declaration != null\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDst.variable_declaration.print_ct_VariableDefinitionType\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDIF st.function_call != null\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDst.function_call.print_Math_FunctionCallType\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDIF st.statement != null\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TODO: parse MDL block");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_Math_ScalarType(final primary p) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\uFFFDIF p.number != null\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("<Scalar value = \"\uFFFDp.number\uFFFD\"/>");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD\t");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_ct_SymbId(final primary p) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\uFFFDIF p.identifier != null\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDp.identifier.print_ct_SymbId\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_ct_SymbId(final variable_name name) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("symbId = \"\uFFFDname.toStr\uFFFD\"");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence printXML(final function_declaration f) {
    StringConcatenation _builder = new StringConcatenation();
    return _builder;
  }
  
  public CharSequence printXML(final formal_arguments args) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\uFFFDFOR a: args.identifiers\uFFFD");
    _builder.newLine();
    _builder.append("\uFFFDENDFOR\uFFFD");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence printXML(final function_body body) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("\uFFFDFOR b: body.blocks\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDIF b.estimate_defn != null\uFFFD");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("\uFFFDb.estimate_defn.print_msteps_SimulationStep\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDIF b.simulate_defn != null\uFFFD");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("\uFFFDb.simulate_defn.print_msteps_EstimationStep\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDENDIF\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.newLine();
    _builder.append("\uFFFDENDFOR\uFFFD");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_msteps_EstimationStep(final block b) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<EstimationStep>");
    _builder.newLine();
    _builder.append("\uFFFDFOR st: b.statements\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TODO: print estimation steps");
    _builder.newLine();
    _builder.append("\uFFFDENDFOR\uFFFD");
    _builder.newLine();
    _builder.append("</EstimationStep>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_msteps_SimulationStep(final block b) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<SimulationStep>");
    _builder.newLine();
    _builder.append("\uFFFDFOR st: b.statements\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("TODO: print simulation steps");
    _builder.newLine();
    _builder.append("\uFFFDENDFOR\uFFFD");
    _builder.newLine();
    _builder.append("</SimulationStep>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_Math_FunctionCallType(final function_call call) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<FunctionCall>");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDcall.funct_name.print_Math_VarType\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDFOR arg: call.arguments.arguments\uFFFD");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("\uFFFDarg.print_Math_FunctionArgumentType\uFFFD");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("\uFFFDENDFOR\uFFFD");
    _builder.newLine();
    _builder.append("</FunctionCall>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_Math_VarType(final String str) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<Var \uFFFDstr.print_Math_symbId\uFFFD/>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_Math_FunctionArgumentType(final argument arg) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("<FunctionArgument ");
    _builder.newLine();
    _builder.append("\uFFFDIF arg.identifier != null\uFFFD\uFFFDarg.identifier.print_Math_symbId\uFFFD\uFFFDENDIF\uFFFD>");
    _builder.newLine();
    _builder.append("</FunctionArgument>");
    _builder.newLine();
    return _builder;
  }
  
  public CharSequence print_Math_symbId(final String str) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("symbId = \"\uFFFDstr\uFFFD\"");
    _builder.newLine();
    return _builder;
  }
}
