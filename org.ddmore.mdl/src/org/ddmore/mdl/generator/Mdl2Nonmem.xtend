/*
 * MDL converter toolbox
 * @DDMoRe
 * Author: Natallia Kokash, LIACS, 2012
 */
package org.ddmore.mdl.generator

import org.ddmore.mdl.mdl.block
import org.ddmore.mdl.mdl.block_statement
import org.ddmore.mdl.mdl.data_obj
import org.ddmore.mdl.mdl.file_block
import org.ddmore.mdl.mdl.header_block
import org.ddmore.mdl.mdl.mcl
import org.ddmore.mdl.mdl.mcl_obj
import org.ddmore.mdl.mdl.model_obj
import org.ddmore.mdl.mdl.param_obj
import org.ddmore.mdl.mdl.statement
import org.ddmore.mdl.mdl.task_obj
import org.ddmore.mdl.mdl.task_obj_block
import org.ddmore.mdl.mdl.variability_block_statement
import org.ddmore.mdl.mdl.model_block
import org.ddmore.mdl.mdl.target_block


class Mdl2Nonmem extends MdlPrinting{

var task_obj task_object = null; //Store the reference to task object
	
	//Print file name and analyse all MCL objects in the source file
  	def convertToNonmem(mcl m){
  		//Create a map of variables
  		for (o:m.objects){
  			prepareCollections(o);
  			if (o.task_obj != null){
  				task_object = o.task_obj;
  			}
  		}
  		var version = "1.005";
  		var date = "14.03.2013"
		'''
		;mdl2nt «version» beta, last modification «date», Natallia Kokash (natallia.kokash@gmail.com)  
		
		$PROB «m.fileNameUpperCase»
  		«FOR o:m.objects»«o.convertToNonmem»«ENDFOR»
		'''
	}
	
	//convertToNonmem MCL objects
	def convertToNonmem(mcl_obj o)'''
	«IF o.data_obj != null»«o.data_obj.convertToNonmem»«ENDIF»
	«IF o.model_obj != null»«o.model_obj.convertToNonmem»«ENDIF»
	«IF o.param_obj != null»«o.param_obj.convertToNonmem»«ENDIF»
	«IF o.task_obj != null»«o.task_obj.convertToNonmem»«ENDIF»
	'''	
		
////////////////////////////////////	
//convertToNonmem MODEL OBJECT
////////////////////////////////////
	def convertToNonmem(model_obj o){
	val isLibraryDefined = o.isLibraryDefined;
	val isPKDefined = o.isPKDefined;
	val isErrorNonEmpty = o.isErrorNonEmpty;
	val isODEDefined = o.isODEDefined; 
	'''
	«IF isODEDefined»
	
	$MODEL
		«o.printModel»
	«ENDIF»
	
	«IF isLibraryDefined»
	«IF isPKDefined»
	
	«o.printSUBR»
		
	$PK 
		«o.printPK»
	«ENDIF»
	«IF isErrorNonEmpty»		

	$ERROR
		«o.printError»
	
	«ENDIF»
	«ELSE» 
	«IF isPKDefined || isErrorNonEmpty»		

	$PRED
		«o.printPK»
		«o.printError»
	«ENDIF»
	«ENDIF»
	«IF isODEDefined»
	
	$DES
		«o.printDES»
	«ENDIF»
	
	«o.printTable»
	'''
	}
	
	//GROUP VARIABLES, INDIVIDUAL VARIABLES -> $PK
	def printPK(model_obj o)'''
	«FOR b:o.blocks»
	«IF	b.group_variables != null»
		«b.group_variables.block.print»
	«ENDIF»
	«IF b.individual_model_obj_block != null»
		«var bb = b.individual_model_obj_block»
		«FOR s: bb.block.statements SEPARATOR ' '»
			«s.print»
		«ENDFOR»
	«ENDIF»
	«ENDFOR»
	'''
	
    //MODEL_PREDICTION, OBSERVATION -> $ERROR
	def printError(model_obj o)'''
		«FOR mob:o.blocks»
			«IF mob.model_prediction_obj_block != null»
				«FOR s: mob.model_prediction_obj_block.block.statements»
					«IF s.statement != null»
						«var x = s.statement.variable_declaration»
						«IF x != null»«IF x.expression != null»«IF x.expression.expression != null»«x.print»«ENDIF»«ENDIF»«ENDIF»
						«IF s.statement.statement != null»«s.statement.statement.print»«ENDIF»
					«ENDIF»
				«ENDFOR»
			«ENDIF»
			«IF mob.observation_block != null»
				«mob.observation_block.block.print»
			«ENDIF»
		«ENDFOR»
	'''	

	//Print $MODEL
	def printModel(model_obj o)'''
		«FOR b:o.blocks»
			«IF b.model_prediction_obj_block != null»
				«var bb = b.model_prediction_obj_block»
				«FOR s: bb.block.statements»
					«IF s.ode_block != null»
						«FOR ss: s.ode_block.block.statements»
							«var x = ss.variable_declaration»
							«IF x != null»
								«IF x.expression != null»
									«IF x.expression.ode_list != null»
										COMP(«x.identifier.toStr»)
									«ENDIF»
								«ENDIF»
							«ENDIF»
						«ENDFOR»
					«ENDIF»
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	'''

	//Print $DES
	def printDES(model_obj o)'''
		«FOR b:o.blocks»
			«IF b.model_prediction_obj_block != null»
				«var bb = b.model_prediction_obj_block.block»
				«FOR s: bb.statements»
					«IF s.ode_block != null»
						«FOR ss: s.ode_block.block.statements»
							«var x = ss.variable_declaration»
							«IF x != null»
								«IF x.expression != null»
									«IF x.expression.expression != null»
										«x.print»
									«ENDIF»
									«IF x.expression.ode_list != null»
										«var deriv = x.expression.ode_list.getVariableAttribute("deriv")»
										«IF deriv != null»
											«var id = x.identifier.toStr»
											«IF dadt_vars.get(id) != null»
												DADT(«dadt_vars.get(id)») = «deriv»
											«ENDIF»	
										«ENDIF»
									«ENDIF»
								«ENDIF»
							«ENDIF»
							«IF ss.statement != null»
								«ss.statement.print»
							«ENDIF»
						«ENDFOR»
					«ENDIF»
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	'''    
    
	//Print $SUBR
	def printSUBR(model_obj o)'''
	«FOR b:o.blocks»
		«IF b.model_prediction_obj_block != null»
			«b.model_prediction_obj_block.block.printSUBR»
		«ENDIF»
	«ENDFOR»
    ''' 
    
    //Extract source file from VARIABLE DECLARATION
    //NONMEM $SUBR section with attributes LIBRARY, MODEL, TRANS
	def printSUBR(model_block b){
		for (ss: b.statements){
			if (ss.library_block != null){
				if (ss.library_block.block != null){
					for (s: ss.library_block.block.statements){
						if (s.variable_declaration != null)
							if (s.variable_declaration.identifier.toStr.equalsIgnoreCase("amount"))
								if (s.variable_declaration.expression != null)
									if (s.variable_declaration.expression.list != null){
										var library = s.variable_declaration.expression.list.getVariableAttribute("library").toString;
										if (library.substring(0,2).equalsIgnoreCase("nm")){
											library = library.substring(2);
										}						
										val model = s.variable_declaration.expression.list.getVariableAttribute("model").toString;
										val trans = s.variable_declaration.expression.list.getVariableAttribute("trans");
										var tolStr = "";
										var tol = b.getTOL();
										if (tol.size > 0){
											if (model.equalsIgnoreCase("9") && library.equalsIgnoreCase("advan")){
												tolStr = "\n$TOL"
												for (tolEl: tol){
													tolStr = tolStr + " NRD(" + tolEl + ")" ;
												}
											} else 
												tolStr = " TOL=" + tol.get(0);
										}
										return '''$SUBR «IF model != null»«library.toUpperCase()»«model»«ENDIF»«tolStr» «IF trans != null»TRANS«trans»«ENDIF»'''
									}
					}
				}
			}
		}
	}
	
	//Get $TOL attribute
	def getTOL(model_block b){
		var tol = newArrayList;
		for (s: b.statements){
			if (s.ode_block != null){
				for (ss: s.ode_block.block.statements){
					var x = ss.variable_declaration
					if (x != null){
						if (x.expression != null){
							if (x.expression.ode_list != null){
								var tolEl = x.expression.ode_list.getVariableAttribute("tolrel");
								if (tolEl != null) tol.add(tolEl);
							}
						}
					}
				}
			}
		}
		return tol;
	}	
    
	//Print $TABLE
	def printTable(model_obj o)'''
	«FOR b:o.blocks»
		«IF b.output_variables_block != null»
			«var bb = b.output_variables_block»
			«IF bb.block.statements.size > 0»
				$TABLE «FOR st: bb.block.statements SEPARATOR ' '»«IF st.variable_declaration != null»«st.variable_declaration.identifier.toStr.convertID»«ENDIF»«ENDFOR»
				ONEHEADER NOPRINT «IF task_object != null»FILE=«task_object.identifier».fit«ENDIF» 
			«ENDIF»
		«ENDIF»	
	«ENDFOR»
	'''
   
////////////////////////////////////
//convertToNonmem PARAMETER OBJECT
/////////////////////////////////////	
	//Process parameter object
	def convertToNonmem(param_obj obj)'''
	«obj.printTheta»
	«obj.printSigma»
	«obj.printOmega»
	'''

	def printTheta(param_obj obj)'''
	«IF obj.isThetaNonEmpty»
	
	$THETA
		«FOR b:obj.blocks»			
			«IF b.structural_block != null»
				«FOR st: b.structural_block.block.statements»
					«st.printTheta»
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	«ENDIF»
	'''
	
	//VARIABILITY -> $OMEGA
	def printOmega(param_obj obj)'''
	«IF (obj.isVariabilityNonEmpty && !eta_vars.empty) || obj.isVariabilitySubBlocksNonEmpty»
	
	$OMEGA
		«FOR b:obj.blocks»			
			«IF b.variability_block != null»
				«FOR c: b.variability_block.block.blocks»
					«IF c.block_statement != null»
						«c.block_statement.printOmega»
					«ENDIF»
					«c.printVariabilitySubBlock»
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	«ENDIF»
	'''

	//VARIABILITY.BLOCK -> $SIGMA
	def printSigma(param_obj obj)'''
	«IF obj.isVariabilityNonEmpty && !eps_vars.empty»
	
	$SIGMA
		«FOR b:obj.blocks»			
			«IF b.variability_block != null»
				«FOR c: b.variability_block.block.blocks»
					«IF c.block_statement != null»
						«c.block_statement.printSigma»
					«ENDIF»
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	«ENDIF»
	'''
	
	def isThetaNonEmpty(param_obj obj){
		for (b:obj.blocks){
			if (b.structural_block != null){
				if (b.structural_block.block.statements.size > 0)
				 	return true;
			}
		}
		return false;
	}

	def isVariabilityNonEmpty(param_obj obj){
		for (b:obj.blocks){
			if (b.variability_block != null){
				for (bb: b.variability_block.block.blocks){
					if (bb.block_statement != null)
				 		return true;
				}
			}
		}
		return false;
	}
	
	def isVariabilitySubBlocksNonEmpty(param_obj obj){
		for (b:obj.blocks){
			if (b.variability_block != null){
				for (bb: b.variability_block.block.blocks){
					if (bb.diag_block != null || bb.block_block != null)
				 		return true;
				}
			}
		}
		return false;
	}
	
	
////////////////////////////////////	
//convertToNonmem DATA OBJECT
////////////////////////////////////

	//Process data object
	def convertToNonmem(data_obj o)'''
	«FOR b:o.blocks»
		«IF b.header_block != null»
			«b.header_block.printInput»
		«ENDIF»
	«ENDFOR»	
	«FOR b:o.blocks»
		«IF b.file_block != null» 
			«b.file_block.printData»
		«ENDIF»
	«ENDFOR»
	«IF task_object != null»
		«task_object.printIgnoreStatements»
	«ENDIF»
	'''
	
	//HEADER -> $INPUT
	//NONMEM INPUT line - copy all variable names from the MDL HEADER block
	def printInput(header_block b)'''
	$INPUT «FOR st: b.block.statements SEPARATOR ' '»«IF st.variable_declaration != null»«st.variable_declaration.identifier.print»«ENDIF»«ENDFOR»'''
	
	//FILE sub-block -> $DATA
	def printData(file_block b)'''
	«FOR s: b.block.blocks»
		«IF s.statement != null»
			«s.statement.printDataSource»
		«ENDIF»
	«ENDFOR»
	'''
	//if (st.inline_block != null) st.inline_block.convertToNonmem
	//if (st.design_block != null) st.design_block.convertToNonmem
	//if (st.rsscript_block != null) st.rsscript_block.convertToNonmem
	
	//NONMEM: Extract source from variable data
	def printDataSource(block_statement s){
		if (s.variable_declaration != null)
			if (s.variable_declaration.identifier.toStr.equalsIgnoreCase("data"))
				if (s.variable_declaration.expression != null)
					if (s.variable_declaration.expression.list != null){
						val data = s.variable_declaration.expression.list.getVariableAttribute("source");
						val ignore = s.variable_declaration.expression.list.getVariableAttribute("ignore");
						return '''«IF data != null»$DATA «data»«ENDIF» «IF ignore != null»IGNORE=«ignore»«ENDIF»'''
					}
	}
	
/////////////////////////////////////////////////
//convertToNonmem TASK OBJECT
/////////////////////////////////////////////////

	def convertToNonmem(task_obj o)'''
		«FOR b:o.blocks»
			«b.printFunctions»
		«ENDFOR»
	'''
	
	def printFunctions(task_obj_block b)'''
		«IF b.function_declaration != null»
			«val body = b.function_declaration.function_body»
			«IF body != null»
				«FOR bb: body.blocks»
					«IF bb.estimate_defn != null»
						«bb.estimate_defn.printEstimate»
					«ENDIF»
					«IF bb.simulate_defn != null»
						«bb.simulate_defn.printSimulate»
					«ENDIF»
				«ENDFOR»				
			«ENDIF»
		«ENDIF»
	'''
	//PARAMETER block
	//DATA block
	
	def printIgnoreStatements(task_obj obj)'''
		«FOR b: obj.blocks»
			«IF b.data_block !=  null»
				«FOR s: b.data_block.block.statements»
					«IF s.variable_declaration != null»
						«IF s.variable_declaration.identifier.toStr.equalsIgnoreCase("ignore")»
							«s.variable_declaration.print»
						«ENDIF»	
					«ENDIF»
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	''' 
	
	//Print $SIM block
	def printSimulate(block b)'''
	
	$SIM ;Conversion for the simulation block has not been implemented in the converter
	'''
	
	//Print $EST block
	def printEstimate(block b)'''
	
	$EST«FOR s: b.statements»«s.printEstimate»«ENDFOR» NOABORT
	«FOR s: b.statements»«s.printEstimateCov»«ENDFOR»
	'''
	
	//Print attributes of the $EST block 
	def printEstimate(block_statement s) { 
		if (s.variable_declaration != null){
			if (s.variable_declaration.expression != null){
				if (s.variable_declaration.identifier.toStr.equalsIgnoreCase("algo")){
					if (s.variable_declaration.expression.expression != null)
						''' METHOD=«s.variable_declaration.expression.expression.toStr»'''
					else
						{
							//print first attribute of the list
							if (s.variable_declaration.expression.list != null){
								var args = s.variable_declaration.expression.list.arguments;
								if (args != null){
									if (args.arguments.size > 0)
										''' METHOD=«args.arguments.get(0).expression.toStr»'''
								}
							}
						}	
				}
				else
					if (s.variable_declaration.identifier.toStr.equalsIgnoreCase("max"))
					''' MAX=«s.variable_declaration.expression.print»'''
				else
					if (s.variable_declaration.identifier.toStr.equalsIgnoreCase("sig"))
					''' SIG=«s.variable_declaration.expression.print»'''
			}			
		}
	}
	
	//Print $COV block
	def printEstimateCov(block_statement s) { 
		if (s.variable_declaration != null){
			if (s.variable_declaration.identifier.toStr.equalsIgnoreCase("cov")){
				if (s.variable_declaration.expression != null){
					if (s.variable_declaration.expression.toStr.replaceAll("\\s","").equalsIgnoreCase(""))
					'''$COV «s.variable_declaration.expression.print»'''
				}
			}
		}	
	}
	
	///////////////////////////////////////////////
	//Prepares variable maps
	///////////////////////////////////////////////
	
  	var eta_vars = newHashMap	//ETAs
  	var eps_vars = newHashMap   //EPSs
	var theta_vars = newHashMap //THETAs
	var dadt_vars = newHashMap  //DADT	
	
	//Collect NM-TRAN variables from the MDL file
	def prepareCollections(mcl_obj o){
  		if (o.model_obj != null){
  			setRandomVariables(o.model_obj);
  			setStructuralVariables(o.model_obj);
  			setModelPredictionVariables(o.model_obj);
  		} 
	}
	
	//Assign indices to MODEL variables and expressions
	def setModelPredictionVariables(model_obj o) { 
		dadt_vars.clear;
		var i = 1;
		for (b:o.blocks){
			if (b.model_prediction_obj_block != null){
				for (s: b.model_prediction_obj_block.block.statements){
					if (s.ode_block != null){
						for (ss: s.ode_block.block.statements){
							var x = ss.variable_declaration;
							if (x != null){
								if (x.expression != null){
									if (x.expression.ode_list != null){
										var id = x.identifier.toStr;
										if (dadt_vars.get(id) == null){
											dadt_vars.put(id, i);
											i = i + 1;
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

	
	//Assign indices to variability parameters ($ETA, $ESP)
	def setRandomVariables(model_obj o){
    	eta_vars.clear;
    	eps_vars.clear;
    	var i = 1; var j = 1; 
		for (b: o.blocks){
	  		if (b.random_variable_definition_block != null){
	  			var block = b.random_variable_definition_block.block;
	  			if (block != null){
					for (s: block.statements) {
						if (s.variable_declaration != null){
							var level = "";
							if (s.variable_declaration.random_list != null){
								level = s.variable_declaration.random_list.getVariableAttribute("level").toString;
							} else {							
								if (s.variable_declaration.expression != null){
									if (s.variable_declaration.expression.random_list != null){
										level = s.variable_declaration.expression.random_list.getVariableAttribute("level").toString;
									}
									else { 
										if (s.variable_declaration.expression.list != null)
											level = s.variable_declaration.expression.list.getVariableAttribute("level").toString;
									}
								}
							}
							val id = s.variable_declaration.identifier.toStr;
							if (level.equalsIgnoreCase("ID"))
								if (eta_vars.get(id) == null){
									eta_vars.put(id, i);
									i = i + 1;
								}
							if (level.equalsIgnoreCase("DV"))
								if (eps_vars.get(id) == null){
									eps_vars.put(id, j);
									j = j + 1;
								}									
						}
					}
	  			}
	  		}
  		}
	}
	
	//Assign indices to THETAs
	def setStructuralVariables(model_obj o){
		theta_vars.clear;
    	var i = 1; 
		for (b: o.blocks){
	  		if (b.structural_parameters_block != null){
	  			var block = b.structural_parameters_block.block;
	  			if (block != null){
					for (st: block.statements) {
						if (st.variable_declaration != null){
							val id = st.variable_declaration.identifier.toStr;
							if (theta_vars.get(id) == null){
								theta_vars.put(id, i);
								i = i + 1;
							}
						}
					}
	  			}
	  		}	  				
  		}
	}	
			
	/////////////////////////////////////////////////////////////
	//NM-TRAN specific printing
	/////////////////////////////////////////////////////////////

	//Print diag{} and block{} subblocks of VARIABILITY	
	def printVariabilitySubBlock(variability_block_statement v)
	{
		var result = "";
		if (v.diag_block != null){
			var printFix = false;
			var k = 0;
			if (v.diag_block.block != null){
				for (a: v.diag_block.block.arguments.arguments){
					if (a.identifier != null){ 
						if (a.isArgumentExpression) {
							if (a.expression != null){
								var  i = 0;
								while (i < k){
									result = result + "0 ";
									i = i + 1;
								}
								k = k + 1;
								result = result + a.expression.toStr + " ; " + a.identifier + "\n";
							}
						}
						if (a.identifier.equalsIgnoreCase("fix")){ 
							printFix = true;
						}
					
					} else {
						if (a.expression != null)
							result = result + a.expression.toStr + " ";
					}
				}
				if (printFix) result = result + "\nFIX\n";
			}
		}
		
		if (v.block_block != null){
			var printFix = false;
			if (v.block_block.block != null){
				for (a: v.block_block.block.arguments.arguments){
					if (a.identifier != null){ 
						if (a.isArgumentExpression) {
							if (a.expression != null){
								result = result + a.expression.toStr + " ; " + a.identifier + "\n";
							}
						}
						if (a.identifier.equalsIgnoreCase("fix")) 
							printFix = true;
					} else {
						if (a.expression != null)
							result = result + a.expression.toStr + " ";
					}
				}
				if (printFix) result = result + "\nFIX\n";
			}
		}		
		return '''«result»'''; 
	}

	//Print $SIGMA
	def printSigma(block_statement s){
		if (s.variable_declaration != null)
			if (s.variable_declaration.expression != null){		
				if (s.variable_declaration.expression.list != null){
					var name = s.variable_declaration.identifier.toStr;
					//SIGMA <=> EPS?	
					if (eps_vars.get("eps_" + name) != null){
					val value = s.variable_declaration.expression.list.getVariableAttribute("value").toString;
						val fixed = s.variable_declaration.expression.list.getVariableAttribute("fix");
						var printFix = false;
						if (fixed != null){
							printFix = (fixed.toString.equalsIgnoreCase("yes") || fixed.toString.equalsIgnoreCase("true") ||
								fixed.toString.equalsIgnoreCase("1"));						
						}
						if (value == null) return "";
						'''«value»«IF printFix» FIX«ENDIF» ; «name»'''
					}
				}
			}
	}
	
	//Print $OMEGA
	def printOmega(block_statement s){
		if (s.variable_declaration != null)
			if (s.variable_declaration.expression != null){		
				if (s.variable_declaration.expression.list != null){
					var name = s.variable_declaration.identifier.toStr;
					//OMEGA <=> ETA?	
					if (eta_vars.get("eta_" + name) != null){
						val value = s.variable_declaration.expression.list.getVariableAttribute("value").toString;
						var fixed = s.variable_declaration.expression.list.getVariableAttribute("fix");
						var printFix = false;
						if (fixed != null){
							printFix = (fixed.toString.equalsIgnoreCase("yes") || fixed.toString.equalsIgnoreCase("true") 
								|| fixed.toString.equalsIgnoreCase("1"));						
						};
						if (value == null) return "";
						'''«value»«IF printFix» FIX«ENDIF» ; «name»'''
					}
				}
			}
	}
	
	
	//Find attributes in STRUCTURAL_VARIABLES and form an NM-TRAN statement
	def printTheta(block_statement s){
		if (s.variable_declaration != null)
			if (s.variable_declaration.expression != null){		
				if (s.variable_declaration.expression.list != null){
					var name = s.variable_declaration.identifier.toStr;
					val value = s.variable_declaration.expression.list.getVariableAttribute("value").toString;
					val lo = s.variable_declaration.expression.list.getVariableAttribute("lo");
					val hi = s.variable_declaration.expression.list.getVariableAttribute("hi");
					val fixed = s.variable_declaration.expression.list.getVariableAttribute("fix");
					var printFix = false;
					if (fixed != null){
						printFix = (fixed.toString.equalsIgnoreCase("yes") || fixed.toString.equalsIgnoreCase("true") ||
							fixed.toString.equalsIgnoreCase("1"));						
					}
					if (value == null) return "";
					if (lo == null && hi == null) return '''«value»«IF printFix» FIX«ENDIF» ; «name»'''
					if (lo == null) return '''(-INF, «value», «hi»)«IF printFix» FIX«ENDIF» ; «name»'''
					if (hi == null) return '''(«lo», «value», INF)«IF printFix» FIX«ENDIF» ; «name»'''
					return '''(«lo», «value», «hi»)«IF printFix» FIX«ENDIF» ; «name»'''
				}
			}
	}
		
	
	///////////////////////////////////////////////////////////////////////
	//Overwritten converter functions
	///////////////////////////////////////////////////////////////////////
	
	//Convert variable names to NM-TRAN versions
	override convertID(String id){
		if (id.indexOf('_') > 0){			

			if (eta_vars.get(id) != null){
				return "ETA(" + eta_vars.get(id) + ")";
			}
			if (eps_vars.get(id) != null){
				return "EPS(" + eps_vars.get(id) + ")";
			}
			if (theta_vars.get(id) != null){
				return "THETA(" + theta_vars.get(id) + ")"; 
			}
		}
		if (dadt_vars.get(id) != null){
			return "A(" + dadt_vars.get(id) + ")"; 
		}
		if (id.equalsIgnoreCase("exp"))
			return "EXP";
		if (id.equalsIgnoreCase("ln"))
			return "LOG";
		return id;	
	}
	

	//Override MDL operators with NM-TRAN operators
	override convertOperator(String op){
		if (op.equals("<")) return ".LT.";
		if (op.equals(">")) return ".GT.";
		if (op.equals("<=")) return ".LE.";
		if (op.equals(">=")) return ".GE.";
		if (op.equals("==")) return ".EQ.";
		if (op.equals("^")) return "**";
		if (op.equals("||")) return ".OR.";
		if (op.equals("&&")) return ".AND.";
		return op;	
	}
	
	//Print verbatim target specific code
	override print(target_block b)'''
	«IF b.identifier.equalsIgnoreCase("NMTRAN")»
	«var printedCode = b.external_code.substring(3, b.external_code.length - 3)»
	«printedCode»
	«ENDIF»
	'''	
	
	//Override statement printing to substitute MDL conditional operators with NM-TRAN operators
	override print(statement s)'''
		«IF s.block != null»
			«s.block.print»
		«ENDIF»
		«IF s.par_expression != null»
			IF «s.par_expression.print» THEN
				«s.if_statement.print»
			«IF s.else_statement != null»
			ELSE 
				«s.else_statement.print»
			«ENDIF»
			ENDIF
		«ENDIF»
	'''
}
