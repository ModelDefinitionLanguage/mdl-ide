/*
 * MDL converter toolbox
 * @DDMoRe
 * Author: Natallia Kokash, LIACS, 2012
 */
package org.ddmore.mdl.generator

import org.ddmore.mdl.mdl.DataObject
import org.ddmore.mdl.mdl.FileBlock
import org.ddmore.mdl.mdl.HeaderBlock
import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.MclObject
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.ParameterObject
import org.ddmore.mdl.mdl.ConditionalStatement
import org.ddmore.mdl.mdl.TaskObject
import org.ddmore.mdl.mdl.TaskObjectBlock
import org.ddmore.mdl.mdl.VariabilityBlockStatement
import org.ddmore.mdl.mdl.TargetBlock
import org.ddmore.mdl.mdl.ModelPredictionBlock
import org.ddmore.mdl.mdl.ParameterDeclaration
import org.ddmore.mdl.mdl.EstimateTask
import org.ddmore.mdl.mdl.SimulateTask
import org.ddmore.mdl.mdl.GroupVariablesBlock
import org.ddmore.mdl.mdl.FileBlockStatement
import org.eclipse.emf.ecore.resource.Resource
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.DataBlockStatement
import org.ddmore.mdl.mdl.ImportBlock
import org.ddmore.mdl.mdl.ImportedFunction
import org.ddmore.mdl.mdl.Argument
import java.util.HashMap
import java.util.ArrayList

class Mdl2Nonmem extends MdlPrinting{		

	var tol = ""; //Find the value of tol
	var file = ""; //Find the file name
	
	var eta_vars = newHashMap	//ETAs
  	var eps_vars = newHashMap   //EPSs
	var theta_vars = newHashMap //THETAs
	var dadt_vars = newHashMap  //DADT	
	var init_vars = newHashMap  //A	
	
	
	//Print file name and analyse all MCL objects in the source file
  	def convertToNonmem(Mcl m){
  		//Prepare external functions  		
  		
  		//Create a map of variables
  		init_vars.clear;
		dadt_vars.clear;
		theta_vars.clear;
		eta_vars.clear;
    	eps_vars.clear;  
  		for (o:m.objects){
  			prepareCollections(o);
  			if (o.taskObject != null){
  				tol = o.taskObject.getTOL;
  				file = o.taskObject.identifier.name + ".fit";
  			}
  		}
  		
  		m.prepareExternals;
		
  		var version = "1.008";
  		var date = "02.06.2013"
		'''
		;mdl2nt «version» beta, last modification «date», Natallia Kokash (natallia.kokash@gmail.com)  
			
		$PROB «m.fileNameUpperCase»
		«getExternalCode("$PROBLEM")»
		«getExternalCode("$PROB")»
  		«FOR o:m.objects»
	  		«IF o.dataObject != null»«o.dataObject.printINPUT_DATA»«ENDIF»
  		«ENDFOR»
  		«FOR o:m.objects»
  			«IF o.taskObject != null» «o.taskObject.printIgnoreStatements»«ENDIF»
  		«ENDFOR»
  		«FOR o:m.objects»
	  		«IF o.modelObject != null»«o.modelObject.printSUBR_MODEL_PK_ERROR_PRES_DES»«ENDIF»
  		«ENDFOR»
  		«FOR o:m.objects»
			«IF o.parameterObject != null»«o.parameterObject.printTHETA_OMEGA_SIGMA»«ENDIF»
  		«ENDFOR»
  		«FOR o:m.objects»
			«IF o.taskObject != null»«o.taskObject.printFunctions»«ENDIF»
  		«ENDFOR»
		'''
	}
	
	 override void prepareExternalFunctions(ImportBlock b, String objName){
		for (ImportedFunction f: b.functions){
			var args = new HashMap<String, String>();
			var target = f.list.getAttribute("target");
		 	if (target != null){ 
				if (target.equalsIgnoreCase("NMTRAN_CODE")) {
					for (Argument arg: f.list.arguments.arguments){
						if (arg.identifier != null)
							args.put(arg.identifier, arg.expression.toStr)
					}
					externalFunctions.put(objName + "$" + f.identifier, args);
				}
			}
		}
	}	
	
	 override void prepareExternalCode(TargetBlock b){
		val target = b.arguments.selectAttribute("target");
		if (target != null){ 
			if (target.equalsIgnoreCase("NMTRAN_CODE")) {
				val location = b.arguments.selectAttribute("location");
				var codeSnippets = externalCode.get(location);
				if (codeSnippets == null) codeSnippets = new ArrayList<String>();
				codeSnippets.add(b.externalCodeToStr);
				externalCode.put(location, codeSnippets);
			}
		}
	}	
	
	//Sections with external code in examples: $ERROR, $PROBLEM, $ABBREVIATED, $ESTIMATION, $SIMULATION
	

 	///////////////////////////////////////////////
	//Prepares variable maps
	///////////////////////////////////////////////
	
	//Collect variables from the MDL file
	def prepareCollections(MclObject o){
  		if (o.modelObject != null){
  			setRandomVariables(o.modelObject);
  			setStructuralParameters(o.modelObject);
  			setModelPredictionVariables(o.modelObject);
  			setInitialConditions(o.modelObject);
  		} 
	}
	
	def setInitialConditions(ModelObject o){
		var i = 1;
		for (b:o.blocks){
			if (b.modelPredictionBlock != null)
				for (s: b.modelPredictionBlock.statements){
					if (s.odeBlock != null)
						for (ss: s.odeBlock.statements){
							if (ss.symbol != null)
								if (ss.symbol.expression != null)
									if (ss.symbol.expression.odeList != null){
										val initCond = ss.symbol.expression.odeList.getAttribute("init");
										if (!initCond.equals("")){
											init_vars.put(i, initCond);
										} else init_vars.put(i, "0");
										i = i + 1;
									}
						}
				}
		}
	}
	
	//Assign indices to MODEL variables and expressions
	def setModelPredictionVariables(ModelObject o) { 
		var i = 1;
		for (b:o.blocks){
			if (b.modelPredictionBlock != null){
				for (s: b.modelPredictionBlock.statements){
					if (s.odeBlock != null){
						for (ss: s.odeBlock.statements){
							var x = ss.symbol;
							if (x != null){
								if (x.expression != null){
									if (x.expression.odeList != null){
										var id = x.identifier;
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
	def setRandomVariables(ModelObject o){
    	var i = 1; var j = 1; 
		for (b: o.blocks){
	  		if (b.randomVariableDefinitionBlock != null){
				for (s: b.randomVariableDefinitionBlock.variables) {
					if (s.randomList != null){	
						var level = s.randomList.getAttribute("level");
						val id = s.identifier;
						if (level.equals("ID"))
							if (eta_vars.get(id) == null){
								eta_vars.put(id, i);
								i = i + 1;
							}
						if (level.equals("DV"))
							if (eps_vars.get(id) == null){
								eps_vars.put(id, j);
								j = j + 1;
							}	
						}
	  			}
	  		}
  		}
	}
	
	//Assign indices to THETAs
	def setStructuralParameters(ModelObject o){
    	var i = 1; 
		for (b: o.blocks){
	  		if (b.structuralParametersBlock != null){
				for (id: b.structuralParametersBlock.parameters) {
					if (theta_vars.get(id) == null){
						theta_vars.put(id, i);
						i = i + 1;
					}
				}
	  		}	  				
  		}
	}
	
	//Find reference to a data file 
	def getDataSource(Resource resource){
		for(m: resource.allContents.toIterable.filter(typeof(Mcl))) {
			for (obj: m.objects){
				if (obj.dataObject != null){
					for (b: obj.dataObject.blocks){
						if (b.fileBlock != null){
							for (s: b.fileBlock.statements){
								s.getDataSource;
							}
						} 
					}
				}
			}
		}		
		return "";
	}
	
	def getDataSource(FileBlockStatement s){
		if (s.variable != null){
			if (s.variable.identifier.equals("data")){
				if (s.variable.expression != null){
					if (s.variable.expression.list != null)
						return s.variable.expression.list.getAttribute("source");
				}
			}
		}
		return "";
	}
	
	//Get $TOL attribute
	def getTOL(TaskObject obj){
		for (TaskObjectBlock b: obj.blocks){
			if (b.modelBlock != null){
				for (ss: b.modelBlock.statements){
					var x = ss.statement.symbol;
					if (x != null){
						if (x.identifier.equals("tolrel")){
							if (x.expression != null)
								return x.expression.toStr;
						}
					}
				}
			}
		}
	}	
	
////////////////////////////////////	
//convertToNonmem MODEL OBJECT
////////////////////////////////////
	def printSUBR_MODEL_PK_ERROR_PRES_DES(ModelObject o){
	val isLibraryDefined = o.isLibraryDefined;
	val isPKDefined = o.isPKDefined;
	val isErrorNonEmpty = o.isErrorNonEmpty;
	val isODEDefined = o.isODEDefined; 
	'''
	«IF isLibraryDefined»
	«IF isPKDefined»
	
	«o.printSUBR»
	«getExternalCode("$SUBR")»
	«ENDIF»
	«ENDIF»
	«IF isODEDefined»
	
	$MODEL
		«o.printModel»
		«getExternalCode("$MODEL")»
	«ENDIF»
	
	«IF isLibraryDefined»
	«IF isPKDefined»
		
	$PK 
		«o.printPK»
		«getExternalCode("$PK")»
	«ENDIF»
	«IF isErrorNonEmpty»		

	$ERROR
		«o.printError»
		«getExternalCode("$ERROR")»
	«ENDIF»
	«ELSE» 
	«IF isPKDefined || isErrorNonEmpty»		

	$PRED
		«o.printPK»
		«getExternalCode("$PRED")»
		«o.printError»
		«getExternalCode("$ERROR")»
	«ENDIF»
	«ENDIF»
	«IF isODEDefined»
	
	$DES
		«o.printDES»
		«getExternalCode("$DES")»
	«ENDIF»
	
	«o.printTable»
	'''
	}
	
	//GROUP VARIABLES, INDIVIDUAL VARIABLES -> $PK
	def printPK(ModelObject o)'''
	«FOR b:o.blocks»
	«IF	b.groupVariablesBlock != null»
		«b.groupVariablesBlock.print»
	«ENDIF»
	«IF b.individualVariablesBlock != null»
		«var bb = b.individualVariablesBlock»
		«FOR s: bb.statements SEPARATOR ' '»
			«s.print»
		«ENDFOR»
	«ENDIF»
	«IF b.modelPredictionBlock != null»

		;initial conditions
		«FOR e: init_vars.entrySet»
			A_0(«e.key») = «e.value»
		«ENDFOR»
	«ENDIF»
	«ENDFOR»
	'''
			 
	def print(GroupVariablesBlock block)'''
		«FOR st: block.statements»
			«IF st.statement != null»
				«st.statement.print»
			«ENDIF»
			«IF st.mixtureBlock != null»
				«st.mixtureBlock.print»
			«ENDIF»	
		«ENDFOR»
	'''	
	
    //MODEL_PREDICTION, OBSERVATION -> $ERROR
	def printError(ModelObject o)'''
		«FOR mob:o.blocks»
			«IF mob.modelPredictionBlock != null»
				«FOR s: mob.modelPredictionBlock.statements»
					«IF s.statement != null»
						«var x = s.statement.symbol»
						«IF x != null»«IF x.expression != null»«IF x.expression.expression != null»«x.print»«ENDIF»«ENDIF»«ENDIF»
						«IF s.statement.statement != null»«s.statement.statement.print»«ENDIF»
					«ENDIF»
				«ENDFOR»
			«ENDIF»
			«IF mob.observationBlock != null»
				«mob.observationBlock.print»
			«ENDIF»
		«ENDFOR»
	'''	

	//Print $MODEL
	def printModel(ModelObject o)'''
		«FOR b:o.blocks»
			«IF b.modelPredictionBlock != null»
				«var bb = b.modelPredictionBlock»
				«FOR s: bb.statements»
					«IF s.odeBlock != null»
						«FOR ss: s.odeBlock.statements»
							«var x = ss.symbol»
							«IF x != null»
								«IF x.expression != null»
									«IF x.expression.odeList != null»
										COMP(«x.identifier»)
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
	def printDES(ModelObject o)'''
		«FOR b:o.blocks»
			«IF b.modelPredictionBlock != null»
				«var bb = b.modelPredictionBlock»
				«FOR s: bb.statements»
					«IF s.odeBlock != null»
						«FOR ss: s.odeBlock.statements»
							«var x = ss.symbol»
							«IF x != null»
								«IF x.expression != null»
									«IF x.expression.expression != null»
										«x.print»
									«ENDIF»
									«IF x.expression.odeList != null»
										«var deriv = x.expression.odeList.getAttribute("deriv")»
										«IF !deriv.equals("")»
											«var id = x.identifier»
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
	def printSUBR(ModelObject o)'''
	«FOR b:o.blocks»
		«IF b.modelPredictionBlock != null»
			«b.modelPredictionBlock.printSUBR»
		«ENDIF»
	«ENDFOR»
    ''' 
    
    //Extract source file from VARIABLE DECLARATION
    //NONMEM $SUBR section with attributes LIBRARY, MODEL, TRANS
	def printSUBR(ModelPredictionBlock b){
		for (ss: b.statements){
			if (ss.libraryBlock != null){
					for (st: ss.libraryBlock.statements){
							var libraryRef = st.expression.identifier;
							var attributes = libraryRef.getExternalFunctionAttributes();
							var library = libraryRef.identifier;
							if (attributes != null){
								var name = attributes.get("name");
								if (name != null) library = name;
							}
							val model = st.expression.arguments.selectAttribute("model");
							val trans = st.expression.arguments.selectAttribute("trans");
							return '''$SUBR «IF !model.equals("")»«library.toUpperCase()»«model»«ENDIF» TOL = «tol» «IF !trans.equals("")»TRANS«trans»«ENDIF»'''
					}
				}
			}
		}
		
	//Print $TABLE
	def printTable(ModelObject o)'''
	«FOR b:o.blocks»
		«IF b.outputVariablesBlock != null»
			«var bb = b.outputVariablesBlock»
			«IF bb.variables.size > 0»
				$TABLE «FOR st: bb.variables SEPARATOR ' '»«st.toStr»«ENDFOR»
				ONEHEADER NOPRINT «IF !file.equals("")»FILE=«file»«ENDIF» 
				«getExternalCode("$TABLE")»
			«ENDIF»
		«ENDIF»	
	«ENDFOR»
	'''
   
////////////////////////////////////
//convertToNonmem PARAMETER OBJECT
/////////////////////////////////////	
	//Process parameter object
	def printTHETA_OMEGA_SIGMA(ParameterObject obj)'''
	«obj.printTheta»
	«obj.printSigma»
	«obj.printOmega»
	'''

	def printTheta(ParameterObject obj)'''
	«IF obj.isThetaNonEmpty»
	
	$THETA
		«FOR b:obj.blocks»			
			«IF b.structuralBlock != null»
				«FOR st: b.structuralBlock.parameters»
					«st.printTheta»
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	«ENDIF»
	«getExternalCode("$THETA")»
	'''
	
	//VARIABILITY -> $OMEGA
	def printOmega(ParameterObject obj)'''
	«IF (obj.isVariabilityNonEmpty && !eta_vars.empty) || obj.isVariabilitySubBlocksNonEmpty»
	
	$OMEGA
		«FOR b:obj.blocks»			
			«IF b.variabilityBlock != null»
				«FOR c: b.variabilityBlock.statements»
					«IF c.parameter != null»
						«c.parameter.printOmega»
					«ENDIF»
					«c.printVariabilitySubBlock»
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	«ENDIF»
	«externalCode.get("$OMEGA")»
	'''

	//VARIABILITY.BLOCK -> $SIGMA
	def printSigma(ParameterObject obj)'''
	«IF obj.isVariabilityNonEmpty && !eps_vars.empty»
	
	$SIGMA
		«FOR b:obj.blocks»			
			«IF b.variabilityBlock != null»
				«FOR c: b.variabilityBlock.statements»
					«IF c.parameter != null»
						«c.parameter.printSigma»
					«ENDIF»
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	«ENDIF»
	«getExternalCode("$SIGMA")»
	'''
	
	def isThetaNonEmpty(ParameterObject obj){
		for (b:obj.blocks){
			if (b.structuralBlock != null){
				if (b.structuralBlock.parameters.size > 0)
				 	return true;
			}
		}
		return false;
	}

	def isVariabilityNonEmpty(ParameterObject obj){
		for (b:obj.blocks){
			if (b.variabilityBlock != null){
				if (b.variabilityBlock.statements.size > 0){
				 	return true;
				}
			}
		}
		return false;
	}
	
	def isVariabilitySubBlocksNonEmpty(ParameterObject obj){
		for (b:obj.blocks){
			if (b.variabilityBlock != null){
				for (bb: b.variabilityBlock.statements){
					if ((bb.diagBlock != null) || (bb.blockBlock != null))
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
	def printINPUT_DATA(DataObject o)'''
	«FOR b:o.blocks»
		«IF b.headerBlock != null»
			«b.headerBlock.printInput»
		«ENDIF»
	«ENDFOR»	
	«FOR b:o.blocks»
		«IF b.fileBlock != null» 
			«b.fileBlock.printData»
		«ENDIF»
	«ENDFOR»
	'''
	
	//HEADER -> $INPUT
	//NONMEM INPUT line - copy all variable names from the MDL HEADER block
	def printInput(HeaderBlock b)'''
	$INPUT «FOR st: b.variables SEPARATOR ' '»«st.identifier.toStr»«ENDFOR»
	«getExternalCode("$INPUT")»
	'''

	//FILE sub-block -> $DATA
	def printData(FileBlock b)'''
	«FOR s: b.statements»
		«s.printDataSource»
	«ENDFOR»
	«getExternalCode("$DATA")»
	'''
	//if (st.inlineBlock != null) st.inlineBlock.convertToNonmem
	//if (st.designBlock != null) st.designBlock.convertToNonmem
	//if (st.rscriptBlock != null) st.rscriptBlock.convertToNonmem
	
	//NONMEM: Extract source from variable data
	def printDataSource(FileBlockStatement s){
		if (s.variable != null)
			if (s.variable.identifier.equals("data")){
				if (s.variable.expression != null){
					if (s.variable.expression.list != null){
						var data = s.variable.expression.list.getAttribute("source");
						var ignore = s.variable.expression.list.getAttribute("ignore");
						return '''«IF !data.equals("")»$DATA «data»«ENDIF» «IF !ignore.equals("")»IGNORE=«ignore»«ENDIF»'''
					}
				}
			}
	}
	
/////////////////////////////////////////////////
//convertToNonmem TASK OBJECT
/////////////////////////////////////////////////

	def printFunctions(TaskObject o)'''
		«FOR b:o.blocks»
			«b.printFunctions»
		«ENDFOR»
	'''
	
	def printFunctions(TaskObjectBlock b)'''
		«IF b.functionDeclaration != null»
			«val body = b.functionDeclaration.functionBody»
			«IF body != null»
				«FOR bb: body.blocks»
					«IF bb.estimateBlock != null»
						«bb.estimateBlock.printEstimate»
					«ENDIF»
					«IF bb.simulateBlock != null»
						«bb.simulateBlock.printSimulate»
					«ENDIF»
				«ENDFOR»				
			«ENDIF»
		«ENDIF»
	'''
	//PARAMETER block 
	//DATA block	
	def printIgnoreStatements(TaskObject obj)'''
		«FOR b: obj.blocks»
			«IF b.dataBlock !=  null»
				«FOR DataBlockStatement block: b.dataBlock.statements»
				«IF block.ignoreList != null»
					«block.ignoreList.identifier» = («block.ignoreList.expression.toStr» )
				«ENDIF»
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	''' 
	
	//Print $SIM block
	def printSimulate(SimulateTask b)'''
	
	$SIM «FOR s: b.statements»
	«IF s.statement != null»«IF s.statement.symbol != null»«s.statement.symbol.printSimulate»«ENDIF»«ENDIF»«ENDFOR» NOABORT
	«getExternalCode("$SIM")»
	«getExternalCode("$SIMULATION")»
	'''
	
	//Print $EST block
	def printEstimate(EstimateTask b)'''
	
	$EST«FOR s: b.statements»«IF s.statement != null»«IF s.statement.symbol != null»«s.statement.symbol.printEstimate»«ENDIF»«ENDIF»«ENDFOR» NOABORT
	«getExternalCode("$EST")»
	«getExternalCode("$ESTIMATION")»
	«FOR s: b.statements»»«IF s.statement != null»«IF s.statement.symbol != null»«s.statement.symbol.printEstimateCov»«ENDIF»«ENDIF»«ENDFOR»
	«getExternalCode("$COV")»		
	'''
	
	//Print attributes of the $EST block 
	def printEstimate(SymbolDeclaration s) { 
		if (s.identifier.equals("algo")){
			if (s.expression.expression != null)
				''' METHOD=«s.expression.expression.toStr»'''
			else
				{
					//print first attribute of the list
					if (s.expression.list != null){
						var args = s.expression.list.arguments;
						if (args != null){
							if (args.arguments.size > 0)
								''' METHOD=«args.arguments.get(0).expression.toStr»'''
						}
					}
				}	
		}
		else
			if (s.identifier.equals("max"))
			''' MAX=«s.expression.print»'''
		else
			if (s.identifier.equals("sig"))
			''' SIG=«s.expression.print»'''
	}
	
	def printSimulate(SymbolDeclaration s) { 
		'''#The MDL to NM-TRAN converter does not support yet simulation tasks'''
	}
	
	//Print $COV block
	def printEstimateCov(SymbolDeclaration s) { 
		if (s.identifier.equals("cov")){
			if (s.expression != null){
				if (s.expression.toStr.replaceAll("\\s","").equals(""))
				'''$COV «s.expression.print»'''
			}
		}
	}
	
	/////////////////////////////////////////////////////////////
	//NM-TRAN specific printing
	/////////////////////////////////////////////////////////////

	//Print diag{} and block{} subblocks of VARIABILITY	
	def printVariabilitySubBlock(VariabilityBlockStatement v)
	{
		var result = "";
		if (v.diagBlock != null){
			var printFix = false;
			var k = 0;
			for (a: v.diagBlock.arguments.arguments){
				if (a.identifier != null){ 
					if (a.identifier.equals("fix")){ 
						if (a.expression != null){
							printFix = a.expression.isTrue	
						}
					}				
				}
			}	
			if (v.diagBlock.parameters != null)		
				for (p: v.diagBlock.parameters.arguments) {
					if (p.expression != null){
						var  i = 0;
						while (i < k){
							result = result + "0 ";
							i = i + 1;
						}
						k = k + 1;
						result = result + p.expression.toStr + " ";
						if (p.identifier != null)
							result = result + "; " + p.identifier + "\n";
					}
				}		
			if (printFix) result = result + "FIX\n";
		}
		
		if (v.blockBlock != null){
			var printFix = false;
			for (a: v.blockBlock.arguments.arguments){
				if (a.identifier != null){ 
					if (a.identifier.equals("fix")) 
						if (a.expression != null){
							printFix = a.expression.isTrue		
						}
				}
			}
			if (v.blockBlock.parameters != null)
				for (p: v.blockBlock.parameters.arguments) {
					if (p.expression != null){
						result = result + p.expression.toStr + " ";
						if (p.identifier != null)
							result = result + "; " + p.identifier + "\n";
					}
				}
			if (printFix) result = result + "FIX\n";
		}		
		return '''«result»'''; 
	}

	//Print $SIGMA
	def printSigma(ParameterDeclaration s){
			if (s.list != null)
			{
				var name = s.identifier;
				//SIGMA <=> EPS?	
				if (eps_vars.get("eps_" + name) != null)
			 	{
					val value = s.list.getAttribute("value");
					val printFix = s.list.getAttribute("fix").isTrue;
					if (value.equals("")) return "";							
					'''«value»«IF printFix» FIX«ENDIF» ; «name»'''
				}
			}
	}
	
	//Print $OMEGA
	def printOmega(ParameterDeclaration s){
		if (s.list != null){		
			var name = s.identifier;
			//OMEGA <=> ETA?	
			if (eta_vars.get("eta_" + name) != null){
				val value = s.list.getAttribute("value");
				var printFix = s.list.getAttribute("fix").isTrue;
				if (value.equals("")) return "";								
				'''«value»«IF printFix» FIX«ENDIF» ; «name»'''
			}
		}
	}
	

	//Find attributes in STRUCTURAL_VARIABLES and form an NM-TRAN statement
	def printTheta(ParameterDeclaration s){
		if (s.list != null){		
			var name = s.identifier;
			val value = s.list.getAttribute("value");
			val lo = s.list.getAttribute("lo");
			val hi = s.list.getAttribute("hi");
			val printFix = s.list.getAttribute("fix").isTrue;
			if (value.equals("")) return "";
			if (lo.equals("") && hi.equals("")) return '''«value»«IF printFix» FIX«ENDIF» ; «name»'''
			if (lo.equals("")) return '''(-INF, «value», «hi»)«IF printFix» FIX«ENDIF» ; «name»'''
			if (hi.equals("")) return '''(«lo», «value», INF)«IF printFix» FIX«ENDIF» ; «name»'''
			return '''(«lo», «value», «hi»)«IF printFix» FIX«ENDIF» ; «name»'''
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
	
	override print(TargetBlock b)'''
	«IF b.identifier.equalsIgnoreCase("NMTRAN")»
	«var printedCode = b.externalCode.substring(3, b.externalCode.length - 3)»
	«printedCode»
	«ENDIF»'''
		
	//Override statement printing to substitute MDL conditional operators with NM-TRAN operators
	override print(ConditionalStatement s)'''
		«IF s.parExpression != null»
			IF «s.parExpression.print» THEN
				«IF s.ifStatement != null»
					«s.ifStatement.print»
				«ENDIF»
				«IF s.ifBlock != null»
					«s.ifBlock.print»
				«ENDIF»
			«IF s.elseStatement != null || s.elseBlock != null»
			ELSE 
				«IF s.elseStatement != null»
					«s.elseStatement.print»
				«ENDIF»
				«IF s.elseBlock != null»
					«s.elseBlock.print»
				«ENDIF»
			«ENDIF»
		«ENDIF»
	'''
}
