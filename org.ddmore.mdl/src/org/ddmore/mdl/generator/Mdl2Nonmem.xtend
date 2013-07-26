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
import org.ddmore.mdl.mdl.ExecuteTask

class Mdl2Nonmem extends MdlPrinting{		
	
	val TARGET = "NMTRAN_CODE";

	var eta_vars = newHashMap	//ETAs
  	var eps_vars = newHashMap   //EPSs
	var theta_vars = newHashMap //THETAs
	var dadt_vars = newHashMap  //DADT	
	var init_vars = newHashMap  //A		
	
	//Print file name and analyse all MCL objects in the source file
  	def convertToNonmem(Mcl m){
  		//Prepare external functions  		
  		m.prepareExternals;
  		
  		//Create a map of variables
  		m.prepareCollections;

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
  			«IF o.taskObject != null» «o.taskObject.printIGNORE»«ENDIF»
  		«ENDFOR»
  		«FOR o:m.objects»
	  		«IF o.modelObject != null»«o.modelObject.printSUBR_MODEL_PK_ERROR_PRES_DES»«ENDIF»
  		«ENDFOR»
  		«FOR o:m.objects»
			«IF o.parameterObject != null»«o.parameterObject.printTHETA_OMEGA_SIGMA»«ENDIF»
  		«ENDFOR»
  		«FOR o:m.objects»
			«IF o.taskObject != null»«o.taskObject.printEST_SIM_EXEC»«ENDIF»
  		«ENDFOR»
		'''
	}
	
	 //Prepare a list of external function declarations to define their NMTRAN names 
	 override void prepareExternalFunctions(ImportBlock b, String objName){
		for (ImportedFunction f: b.functions){
			var args = new HashMap<String, String>();
			var target = f.list.getAttribute("target");
		 	if (target != null){ 
				if (target.equals(TARGET)) {
					for (Argument arg: f.list.arguments.arguments){
						if (arg.identifier != null)
							args.put(arg.identifier, arg.expression.toStr)
					}
					externalFunctions.put(objName + "$" + f.identifier, args);
				}
			}
		}
	}	
	
	 //Prepare a map of section with corresponding target blocks
	 override void prepareExternalCode(TargetBlock b){
		val target = b.arguments.selectAttribute("target");
		if (target != null){ 
			if (target.equals(TARGET)) {
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
	def prepareCollections(Mcl m){
		init_vars.clear;
		dadt_vars.clear;
		theta_vars.clear;
		eta_vars.clear;
    	eps_vars.clear; 
    	for (o:m.objects){
	  		if (o.modelObject != null){
	  			setRandomVariables(o.modelObject);
	  			setStructuralParameters(o.modelObject);
	  			setModelPredictionVariables(o.modelObject);
	  			setInitialConditions(o.modelObject);
	  		} 
  		}
	}
	
	//Collect initial conditions from ODE list, init attribute
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
					if (theta_vars.get(id.toStr) == null){
						theta_vars.put(id.toStr, i);
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
								val dataSource = s.getDataSource;
								if (dataSource.length > 0) return dataSource;
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
	
	//Get task object name 
	def getTaskObjectName(Resource resource){
		for(m: resource.allContents.toIterable.filter(typeof(Mcl))) {
			for (obj: m.objects){
	  			if (obj.taskObject != null)
	  				return obj.identifier.name;
	  		}
		}		
		return "";
	}
	
	//Find reference to a data file 
	def getTOL(Resource resource){
		for(m: resource.allContents.toIterable.filter(typeof(Mcl))) {
			for (obj: m.objects){
	  			if (obj.taskObject != null){
	  				val tol = obj.taskObject.getTOL;
					if (tol.length > 0) return tol;
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
		return "";
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
	
	//Processing GROUP_VARIABLES, INDIVIDUAL_VARIABLES, MODEL_PREDICTION (init conditions) for $PK
	def printPK(ModelObject o){
		'''
		«FOR b:o.blocks»
		«IF	b.groupVariablesBlock != null»
			«b.groupVariablesBlock.printExcludingLists»
		«ENDIF»
		«IF b.individualVariablesBlock != null»
			«FOR s: b.individualVariablesBlock.statements SEPARATOR ' '»
				«s.printExcludingLists»
			«ENDFOR»
		«ENDIF»
		«IF b.modelPredictionBlock != null»

			«IF init_vars.entrySet.size > 0»
				;initial conditions
			
				«FOR e: init_vars.entrySet»
					A_0(«e.key») = «e.value»
				«ENDFOR»
			«ENDIF»
		«ENDIF»
		«ENDFOR»
		'''
	}
	
	//Processing GROUP_VARIABLES, MIXTURE for $PK	 
	def printExcludingLists(GroupVariablesBlock block)'''
		«FOR st: block.statements»
			«IF st.statement != null»
				«st.statement.printExcludingLists»
			«ENDIF»
			«IF st.mixtureBlock != null»
				«st.mixtureBlock.printExcludingLists»
			«ENDIF»	
		«ENDFOR»
	'''	
	
	//Processing MODEL_PREDICTION, OBSERVATION for $ERROR
	def printError(ModelObject o)'''
		«FOR mob:o.blocks»
			«IF mob.modelPredictionBlock != null»
				«FOR s: mob.modelPredictionBlock.statements»
					«IF s.statement != null»
						«s.statement.printExcludingLists»
					«ENDIF»
				«ENDFOR»
			«ENDIF»
			«IF mob.observationBlock != null»
				«mob.observationBlock.printExcludingLists»
			«ENDIF»
		«ENDFOR»
	'''	

	//Processing MODEL_PREDICTION for $MODEL
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

	//Processing MODEL_PREDICTION for $DES
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
    
	//Processing MODEL_PREDICTION for $SUBR
	def printSUBR(ModelObject o)'''
	«FOR b:o.blocks»
		«IF b.modelPredictionBlock != null»
			«b.modelPredictionBlock.printSUBR»
		«ENDIF»
	«ENDFOR»
    ''' 
    
    //Processing MODEL_PREDICTION for $SUBR
    //Find an imported function name and attributes "model", "trans"
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
					val tol = b.eResource.getTOL;
					return '''$SUBR «IF !model.equals("")»«library.toUpperCase()»«model»«ENDIF» «IF !trans.equals("")»TRANS«trans»«ENDIF» «IF !tol.equals("")»TOL = «tol»«ENDIF»'''
				}
			}
		}
	}
		
	//Processing OUTPUT_VARIABLES for $TABLE
	def printTable(ModelObject o)'''
	«FOR b:o.blocks»
		«IF b.outputVariablesBlock != null»
			«var bb = b.outputVariablesBlock»
			«IF bb.variables.size > 0»
				$TABLE «FOR st: bb.variables SEPARATOR ' '»«st.toStr»«ENDFOR»
				«val file = o.eResource.getTaskObjectName»
				ONEHEADER NOPRINT «IF !file.equals("")»FILE=«file».fit«ENDIF» 
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

	//Processing STRUCTURAL for $THETA
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
	
	//Processing VARIABILITY for $OMEGA
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

	//Processing VARIABILITY for $SIGMA
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
	
	//We print $THETA only if STRUCTURAL is not empty
	def isThetaNonEmpty(ParameterObject obj){
		for (b:obj.blocks){
			if (b.structuralBlock != null){
				if (b.structuralBlock.parameters.size > 0)
				 	return true;
			}
		}
		return false;
	}

	//We print $OMEGA if VARIABILITY block or its subblocks are not empty
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

	//We print $OMEGA if VARIABILITY block or its subblocks are not empty
	def isVariabilitySubBlocksNonEmpty(ParameterObject obj){
		for (b:obj.blocks){
			if (b.variabilityBlock != null){
				for (bb: b.variabilityBlock.statements){
					if ((bb.diagBlock != null) || (bb.blockBlock != null) || (bb.sameBlock != null))
				 		return true;
				}
			}
		}
		return false;
	}
	
	
////////////////////////////////////	
//convertToNonmem DATA OBJECT
////////////////////////////////////

	//Processing data object for $INPUT, $DATA
	def printINPUT_DATA(DataObject o){
		'''
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
	}

	//Processing HEADER block for $INPUT
	def printInput(HeaderBlock b){
		'''
		$INPUT «FOR st: b.variables SEPARATOR ' '»«st.identifier.toStr»«ENDFOR»
		«getExternalCode("$INPUT")»
		'''
	}
	
	//Processing FILE block for $DATA
	def printData(FileBlock b){
		'''
		«FOR s: b.statements»
			«s.printData»
		«ENDFOR»
		«getExternalCode("$DATA")»
		'''
	}
	//if (st.inlineBlock != null) st.inlineBlock.convertToNonmem
	//if (st.designBlock != null) st.designBlock.convertToNonmem
	//if (st.rscriptBlock != null) st.rscriptBlock.convertToNonmem
	
	//Processing FILE block statement for $DATA
	def printData(FileBlockStatement s){
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

	//Processing task object for $EST and $SIM
	def printEST_SIM_EXEC(TaskObject o)'''
		«FOR b:o.blocks»
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
					«IF bb.executeBlock != null»
						«bb.executeBlock.printExecute»
					«ENDIF»
				«ENDFOR»				
			«ENDIF»
			«ENDIF»
		«ENDFOR»
	'''
	
	//Processing DATA block	for NMTRAN IGNORE=... statements
	def printIGNORE(TaskObject obj)'''
		«FOR b: obj.blocks»
			«IF b.dataBlock !=  null»
				«FOR DataBlockStatement block: b.dataBlock.statements»
				«IF block.ignoreList != null»
					«block.ignoreList.identifier» («block.ignoreList.expression.toStr»)
				«ENDIF»
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	''' 
	
	//Processing SIMULATE block for $SIM 
	def printSimulate(SimulateTask b){
		var isTargetDefined = TARGET.isTargetDefined(b.statements);
		'''
		«IF !isTargetDefined»
		$SIM 
		«FOR s: b.statements»
		«IF s.symbol != null»«s.symbol.printDefaultSimulate»«ENDIF»
		«ENDFOR»
			NOABORT
		«ELSE»
		«FOR s: b.statements»
		«IF s.targetBlock != null»«s.targetBlock.print»«ENDIF»
		«ENDFOR»
		«ENDIF» 
		«getExternalCode("$SIM")»
		«getExternalCode("$SIMULATION")»
		'''
	}
	
	//Processing ESTIMATE block for $EST
	def printEstimate(EstimateTask b){
		var isTargetDefined = TARGET.isTargetDefined(b.statements);
		'''
		
		«IF !isTargetDefined»
		$EST 
		«FOR s: b.statements»
		«IF s.symbol != null»«s.symbol.printDefaultEstimate»«ENDIF»
		«ENDFOR»
			NOABORT
		«ELSE»
		«FOR s: b.statements»
		«IF s.targetBlock != null»«s.targetBlock.print»«ENDIF»
		«ENDFOR»
		«ENDIF» 
		«getExternalCode("$EST")»
		«getExternalCode("$ESTIMATION")»
		«FOR s: b.statements»«IF s.symbol != null»«s.symbol.printCovariance»«ENDIF»«ENDFOR»
		«getExternalCode("$COV")»
		«getExternalCode("$COVARIANCE")»
		'''
	}
	
	//Print attributes for default $EST record
	def printDefaultEstimate(SymbolDeclaration s) { 
		if (s.identifier.equals("algo")){
			if (s.expression.expression != null)
				''' METHOD=«s.expression.expression.toStr»'''
			else {
				//print first attribute of the list!?
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
	
	//Print attributes for default $SIM record
	def printDefaultSimulate(SymbolDeclaration s) { 
		'''
		'''
	}
	
	//Print "cov" attribute for $COVARIATE record 
	def printCovariance(SymbolDeclaration s) { 
		if (s.identifier.equals("cov")){
			if (s.expression != null){
				if (s.expression.toStr.replaceAll("\\s","").equals(""))
				'''$COV «s.expression.print»'''
			}
		}
	}
	
	def printExecute(ExecuteTask b) { 
		'''
		
		«FOR s: b.statements»
		«IF s.targetBlock != null»«s.targetBlock.print»«ENDIF»
		«ENDFOR»
		'''
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
	
	//Find attributes in STRUCTURAL_VARIABLES and form an NMTRAN statement
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
	
	override print(TargetBlock b){
		var target = "";
		if (b.arguments != null) target = b.arguments.selectAttribute("target");
		if (target.equals(TARGET)) {
		'''
		«var printedCode = b.externalCode.substring(3, b.externalCode.length - 3)»
		«printedCode»
		'''
		}
	}
		
	//Override statement printing to substitute MDL conditional operators with NM-TRAN operators
	override print(ConditionalStatement s)'''
		«IF s.parExpression != null»
			IF «s.parExpression.print» THEN
				«IF s.ifStatement != null»
					«s.ifStatement.printExcludingLists»
				«ENDIF»
				«IF s.ifBlock != null»
					«s.ifBlock.printExcludingLists»
				«ENDIF»
			«IF s.elseStatement != null || s.elseBlock != null»
			ELSE 
				«IF s.elseStatement != null»
					«s.elseStatement.printExcludingLists»
				«ENDIF»
				«IF s.elseBlock != null»
					«s.elseBlock.printExcludingLists»
				«ENDIF»
			«ENDIF»
			ENDIF
		«ENDIF»
	'''
}
