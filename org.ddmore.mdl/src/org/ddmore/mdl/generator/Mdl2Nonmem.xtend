/*
 * MDL converter toolbox
 * @DDMoRe
 * Author: Natallia Kokash, LIACS, 2012
 */
package org.ddmore.mdl.generator

import org.ddmore.mdl.mdl.FileBlock
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
import org.ddmore.mdl.mdl.DataObject
import org.ddmore.mdl.mdl.DiagBlock
import org.ddmore.mdl.mdl.BlockBlock
import org.ddmore.mdl.mdl.OrExpression
import org.ddmore.mdl.mdl.AndExpression

class Mdl2Nonmem extends MdlPrinting{		
	
	val TARGET = "NMTRAN_CODE";

	var eta_vars = newHashMap	//ETAs
  	var eps_vars = newHashMap   //EPSs
	var theta_vars = newHashMap //THETAs
	var dadt_vars = newHashMap  //DADT	
	var init_vars = newHashMap  //A		
	
	
	//Print file name and analyse MCL objects in the source file
  	def convertToNMTRAN(Mcl m){
  		//Prepare external functions  		
  		m.prepareExternals;
  		
  		//Create a map of variables
  		m.prepareCollections;

  		var version = "1.02";
  		var date = "24.08.2013"
		'''
		;mdl2nt «version» beta, last modification «date», Natallia Kokash (natallia.kokash@gmail.com)  
		«m.printSIZES»
		«m.printPROB»
		«FOR o:m.objects»
	  		«IF o.dataObject != null»«o.dataObject.convertToNMTRAN»«ENDIF»
  		«ENDFOR»
		«FOR o:m.objects»
			«IF o.taskObject != null»«o.taskObject.printIGNORE»«ENDIF»
  		«ENDFOR»
		«m.printABBREVIATED»
		«FOR o:m.objects»
	  		«IF o.modelObject != null»«o.modelObject.convertToNMTRAN»«ENDIF»
  		«ENDFOR»
		«m.printAES»
  		«FOR o:m.objects»
			«IF o.parameterObject != null»«o.parameterObject.convertToNMTRAN»«ENDIF»
  		«ENDFOR»
  		«FOR o:m.objects»
			«IF o.taskObject != null»«o.taskObject.convertToNMTRAN»«ENDIF»
  		«ENDFOR»
		'''
	}
	
////////////////////////////////////	
//convertToNonmem MCL
////////////////////////////////////
	//Print NM-TRAN record $SIZES
	def printSIZES(Mcl m)'''
	«IF "$SIZES".isTargetDefined»
	
	«getExternalCodeStart("$SIZES")»
	«getExternalCodeEnd("$SIZES")»
	«ENDIF»	
	'''

	//Print NM-TRAN record $PROB/$PROBLEM
	def printPROB(Mcl m)'''
	«IF "$PROBLEM".isTargetDefined || "$PROB".isTargetDefined»
	
	«getExternalCodeStart("$PROBLEM")»
	«getExternalCodeStart("$PROB")»
	«getExternalCodeEnd("$PROBLEM")»
	«getExternalCodeEnd("$PROB")»
	«ELSE»
	
	$PROB «m.fileNameUpperCase»
	«ENDIF»
	'''

	//Print NM-TRAN record $ABB/$ABBREVIATED
	def printABBREVIATED(Mcl m)'''
	«IF "$ABB".isTargetDefined || "$ABBREVIATED".isTargetDefined»

	«getExternalCodeStart("$ABB")»
	«getExternalCodeStart("$ABBREVIATED")»
	«getExternalCodeEnd("$ABB")»
	«getExternalCodeEnd("$ABBREVIATED")»
	«ENDIF»
	'''
	
	//Print NM-TRAN record $AES
	def printAES(Mcl m)'''
	«IF "$AES".isTargetDefined»
	
	«getExternalCodeStart("$AES")»
	«getExternalCodeEnd("$AES")»
	«ENDIF»
	'''

////////////////////////////////////	
//convertToNonmem Model Object
////////////////////////////////////	
	def convertToNMTRAN(ModelObject o){
		val isLibraryDefined = o.isLibraryDefined;
		val isPKDefined = o.isGroupOrIndividualDefined;
		val isErrorDefined = o.isErrorDefined;
		val isODEDefined = o.isODEDefined;
		'''
		«IF isLibraryDefined»
			
			«o.printSUBR(isPKDefined)»
		«ENDIF»
		«o.printMODEL(isODEDefined)»
		«IF isLibraryDefined»

			«o.printPK(isPKDefined)»
			«o.printMIX»
			«o.printERROR(isErrorDefined)»
		«ELSE» 
			«o.printPRED(isPKDefined, isErrorDefined)»
		«ENDIF»
		«o.printDES(isODEDefined)»
		«o.printTABLE»
		'''
	}
	
	//Print NM-TRAN record $PRED
	def printPRED(ModelObject o, Boolean isPKDefined, Boolean isErrorDefined)
		'''
		«IF isPKDefined || isErrorDefined»		
		
		$PRED
		«ENDIF»
		«getExternalCodeStart("$PRED")»
			«o.printPKContent»
			«o.printMIXContent»
			«o.printErrorContent»
		«getExternalCodeEnd("$PRED")»
		'''

	//Print NM-TRAN  record $PK
	def printPK(ModelObject o, Boolean isPKDefined)
		'''
		«IF isPKDefined»
		
		$PK
		«ENDIF»
		«getExternalCodeStart("$PK")»
			«o.printPKContent»
		«getExternalCodeEnd("$PK")»
		'''
	
	//Processing GROUP_VARIABLES, INDIVIDUAL_VARIABLES, MODEL_PREDICTION (init conditions) for $PK
	def printPKContent(ModelObject o)'''
		«FOR b:o.blocks»
		«IF	b.groupVariablesBlock != null»
			«FOR st: b.groupVariablesBlock.statements»
				«IF st.statement != null»
					«st.statement.printExcludingLists»
				«ENDIF»
			«ENDFOR»
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
	
	//Print NM-TRAN record $MIX 
	def printMIX(ModelObject o)'''
	«IF o.isMixDefined»
	
	$MIX
	«ENDIF»
	«getExternalCodeStart("$MIX")»
	«getExternalCodeStart("$MIXTURE")»
		«o.printMIXContent»
	«getExternalCodeEnd("$MIX")»
	«getExternalCodeEnd("$MIXTURE")»
	'''

	def printMIXContent(ModelObject o)'''
	«FOR b:o.blocks»
	«IF	b.groupVariablesBlock != null»
		«FOR st: b.groupVariablesBlock.statements»
			«IF st.mixtureBlock != null»
				«st.mixtureBlock.printExcludingLists»
			«ENDIF»
		«ENDFOR»
	«ENDIF»
	«ENDFOR»
	'''
		
	//Print NM-TRAN record $ERROR
	def printERROR(ModelObject o, Boolean isErrorDefined)'''
	«IF isErrorDefined»	
	
	$ERROR
	«ENDIF»
	«getExternalCodeStart("$ERROR")»
		«o.printErrorContent»
	«getExternalCodeEnd("$ERROR")»
	'''	
	
	//Processing MODEL_PREDICTION, OBSERVATION for $ERROR
	def printErrorContent(ModelObject o)'''
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
	def printMODEL(ModelObject o, Boolean isODEDefined)'''
	«IF isODEDefined»
	
	$MODEL
	«ENDIF»
	«getExternalCodeStart("$MODEL")»
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
	«getExternalCodeEnd("$MODEL")»
	'''

	//Processing MODEL_PREDICTION for $DES
	def printDES(ModelObject o, Boolean isODEDefined)'''
	«IF isODEDefined»

	$DES
	«ENDIF»
	«getExternalCodeStart("$DES")»
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
	«getExternalCodeEnd("$DES")»
	'''    
    
	//Processing MODEL_PREDICTION for $SUBR
	def printSUBR(ModelObject o, Boolean isPKDefined)'''
	«IF isPKDefined»

	$SUBR
	«ENDIF»
	«getExternalCodeStart("$SUBR")»
	«getExternalCodeStart("$SUBROUTINE")»
		«FOR b:o.blocks»
			«IF b.modelPredictionBlock != null»
				«b.modelPredictionBlock.printSUBR»
			«ENDIF»
		«ENDFOR»
	«getExternalCodeEnd("$SUBR")»
	«getExternalCodeEnd("$SUBROUTINE")»
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
					return '''«IF !model.equals("")»«library.toUpperCase()»«model»«ENDIF» «IF !trans.equals("")»TRANS«trans»«ENDIF» «IF !tol.equals("")»TOL = «tol»«ENDIF»'''
				}
			}
		}
	}
		
	//Print NM-TRAN record $TABLE
	def printTABLE(ModelObject o)'''
	«IF o.isOutputVariablesDefined»

	$TABLE 
	«ENDIF»
	
	«getExternalCodeStart("$TABLE")»
		«FOR b:o.blocks»
			«IF b.outputVariablesBlock != null»
				«var bb = b.outputVariablesBlock»
				«IF bb.variables.size > 0»
					«FOR st: bb.variables SEPARATOR ' '»«st.toStr»«ENDFOR»
					«val file = o.eResource.getTaskObjectName»
					ONEHEADER NOPRINT «IF !file.equals("")»FILE=«file».fit«ENDIF» 
				«ENDIF»
			«ENDIF»	
		«ENDFOR»
	«getExternalCodeEnd("$TABLE")»
	'''
	
 
////////////////////////////////////
//convertToNonmem PARAMETER OBJECT
/////////////////////////////////////	
	def convertToNMTRAN(ParameterObject o)'''
	«o.printPRIOR»
	«o.printTHETA»
	«o.printSIGMA»
	«o.printOMEGA»
	'''

	//Copy statements from PRIOR block to $PRIOR
	def printPRIOR(ParameterObject obj)'''
	«IF obj.isPriorDefined»
	
	$PRIOR
	«ENDIF»	
	«getExternalCodeStart("$PRIOR")»
		«FOR b:obj.blocks»			
			«IF b.priorBlock != null»
				«FOR st: b.priorBlock.statements»
					«st.printExcludingLists»
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	«getExternalCodeEnd("$PRIOR")»
	'''
		
	//Processing STRUCTURAL for $THETA
	def printTHETA(ParameterObject obj)'''
	«IF obj.isStructuralDefined»

	$THETA
	«ENDIF»
	«getExternalCodeStart("$THETA")»
		«FOR b:obj.blocks»			
		«IF b.structuralBlock != null»
			«FOR st: b.structuralBlock.parameters»
			«st.printTheta»
			«ENDFOR»
		«ENDIF»
		«ENDFOR»
	«getExternalCodeEnd("$THETA")»

	«externalCodeStart.get("$THETAI")»
	«externalCodeEnd.get("$THETAI")»

	«externalCodeStart.get("$THETAR")»
	«externalCodeEnd.get("$THETAR")»
	'''
	
	//Processing VARIABILITY for $OMEGA
	def printOMEGA(ParameterObject obj)'''
	«IF (obj.isVariabilityDefined && !eta_vars.empty) || obj.isVariabilitySubBlocksDefined»
	
	$OMEGA
	«ENDIF»
	«externalCodeStart.get("$OMEGA")»
		«FOR b:obj.blocks»			
			«IF b.variabilityBlock != null»
				«FOR c: b.variabilityBlock.statements»
					«IF c.parameter != null»
					«c.parameter.printOmega»
					«ENDIF»
					«c.printVariabilitySubblock("$OMEGA")»
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	«externalCodeEnd.get("$OMEGA")»
	'''

	//Processing VARIABILITY for $SIGMA
	def printSIGMA(ParameterObject obj)'''
	«IF (obj.isVariabilityDefined && !eps_vars.empty)»
	
	$SIGMA
	«ENDIF»	
	«getExternalCodeStart("$SIGMA")»
		«FOR b:obj.blocks»			
			«IF b.variabilityBlock != null»
				«FOR c: b.variabilityBlock.statements»
					«IF c.parameter != null»
					«c.parameter.printSigma»
					«ENDIF»
					 «c.printVariabilitySubblock("$SIGMA")»
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	«getExternalCodeEnd("$SIGMA")»
	'''
	
	//Print variability subblocks for $SIGMA and $OMEGA
	def printVariabilitySubblock(VariabilityBlockStatement c, String section)'''
	«IF c.diagBlock != null»«c.diagBlock.printDiag(section)»«ENDIF»
	«IF c.blockBlock != null»«c.blockBlock.printMatrix(section)»«ENDIF»
	'''
	
	//Print diag(...){...} subblock of VARIABILITY
	def printDiag(DiagBlock b, String section)
	{
		var result = "";
		var printFix = false;
		var k = 0;
		for (a: b.arguments.arguments){
			if (a.identifier != null){ 
				if (a.identifier.equals("fix")){ 
					if (a.expression != null){
						printFix = a.expression.isTrue	
					}
				}				
			}
		}	
		if (b.parameters != null)		
			for (p: b.parameters.arguments) {
				if (p.expression != null){
					var  i = 0;
					var tmpRes = "";
					while (i < k){
						tmpRes = tmpRes + "0 ";
						i = i + 1;
					}
					k = k + 1;
					if (p.identifier != null){
						if ((section.equals("$OMEGA") && eta_vars.get("eta_" + p.identifier) != null) ||
						(section.equals("$SIGMA") && eps_vars.get("eps_" + p.identifier) != null))
						{
							result = result + tmpRes + p.expression.toStr + " ";
							result = result + "; " + p.identifier + "\n";
						}
					} 
					else
						if (!result.equals("")) result = result + p.expression.toStr + " ";
				}
			}		
		if (printFix && !result.equals("")) result = result + "FIX\n";
		return '''«result»'''
	}
	
	//Print matrix(...){...} subblock of VARIABILITY
	def printMatrix(BlockBlock b, String section)
	{
		var result = "";
		var printFix = false;
		for (a: b.arguments.arguments){
			if (a.identifier != null){ 
				if (a.identifier.equals("fix")) 
					if (a.expression != null){
						printFix = a.expression.isTrue		
					}
			}
		}
		if (b.parameters != null)
			for (p: b.parameters.arguments) {
				if (p.expression != null){
					if (p.identifier != null){
						if ((section.equals("$OMEGA") && eta_vars.get("eta_" + p.identifier) != null) ||
						(section.equals("$SIGMA") && eps_vars.get("eps_" + p.identifier) != null)){
							result = result + p.expression.toStr + " ";
							result = result + "; " + p.identifier + "\n";
						}
					} 
					else
						if (!result.equals("")) result = result + p.expression.toStr + " ";
				}
			}
		if (printFix && !result.equals("")) result = result + "FIX\n";
		return '''«result»'''; 
	}
	

	//Print $SIGMA
	def printSigma(ParameterDeclaration s){
		if (s.list != null)	{
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
	
////////////////////////////////////	
//convertToNonmem DATA OBJECT
////////////////////////////////////
	def convertToNMTRAN(DataObject o)'''
	«o.printINPUT»
	«o.printDATA»
	'''

	//Print NM-TRAN record $INPUT
	def printINPUT(DataObject o)'''
	«IF o.isHeaderDefined»
		
	$INPUT
	«ENDIF»
	«getExternalCodeStart("$INPUT")»
		«FOR b:o.blocks»
			«IF b.headerBlock != null»
				«FOR st: b.headerBlock.variables SEPARATOR ' '»«st.identifier.toStr»«ENDFOR»
			«ENDIF»
		«ENDFOR»
	«getExternalCodeEnd("$INPUT")»
	'''
	
	//Print NM-TRAN record $DATA
	def printDATA(DataObject o)'''
	«FOR b:o.blocks»
		«IF b.fileBlock != null» 
			«var data = b.fileBlock.getData»
			«IF !data.equals("")»
			
			$DATA «data»
			«ENDIF»
			«getExternalCodeStart("$DATA")»
				«FOR s: b.fileBlock.statements»
					«s.printIGNORE»
				«ENDFOR»
			«getExternalCodeEnd("$DATA")»
		«ENDIF»
	«ENDFOR»
	'''
	
	//if (st.inlineBlock != null) st.inlineBlock.convertToNonmem
	//if (st.designBlock != null) st.designBlock.convertToNonmem
	//if (st.rscriptBlock != null) st.rscriptBlock.convertToNonmem
	
	//Processing FILE block statement for $DATA
	def printIGNORE(FileBlockStatement s){
		if (s.variable != null){
			if (s.variable.identifier.equals("data")){
				if (s.variable.expression != null){
					if (s.variable.expression.list != null){
						var ignore = s.variable.expression.list.getAttribute("ignore");
						return '''«IF !ignore.equals("")»IGNORE=«ignore»«ENDIF»'''
					}
				}
			}
		}
	}
	
	def getData(FileBlock b){
		for (s: b.statements){
			if (s.variable.identifier.equals("data")){
				if (s.variable.expression != null){
					if (s.variable.expression.list != null){
						var data = s.variable.expression.list.getAttribute("source");
						return data;
					}
				}
			}
		}
	}
	
	
/////////////////////////////////////////////////
//convertToNonmem TASK OBJECT
/////////////////////////////////////////////////

	//Processing task object for $EST and $SIM
	def convertToNMTRAN(TaskObject o)'''
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
	
	//Print NM-TRAN IGNORE=... statements
	def printIGNORE(TaskObject o)'''
	«FOR b: o.blocks»
		«IF b.dataBlock !=  null»
			«FOR DataBlockStatement block: b.dataBlock.statements»
			«IF block.ignoreList != null»
				«block.ignoreList.identifier» («block.ignoreList.expression.toCommaSeparatedStr»)
			«ENDIF»
			«ENDFOR»
		«ENDIF»
	«ENDFOR»
	''' 
	
	//Processing SIMULATE block for $SIM 
	def printSimulate(SimulateTask b){
	var isInlineTargetDefined = TARGET.isInlineTargetDefined(b.statements);
	'''
	
	«IF !isInlineTargetDefined»
	
	$SIM 
	«ENDIF»
	«getExternalCodeStart("$SIM")»
	«getExternalCodeStart("$SIMULATION")»
		«IF !isInlineTargetDefined»
			«FOR s: b.statements»
			«IF s.symbol != null»«s.symbol.printDefaultSimulate»«ENDIF»
			«ENDFOR»
			NOABORT
		«ELSE»
			«FOR s: b.statements»
			«IF s.targetBlock != null»«s.targetBlock.print»«ENDIF»
			«ENDFOR»
		«ENDIF» 
	«getExternalCodeEnd("$SIM")»
	«getExternalCodeEnd("$SIMULATION")»	
	'''
	}
	
	//Processing ESTIMATE block for $EST
	def printEstimate(EstimateTask b){
	var isInlineTargetDefined = TARGET.isInlineTargetDefined(b.statements);
	'''
	«IF !isInlineTargetDefined»
	
	$EST 
	«ENDIF»
	«getExternalCodeStart("$EST")»
	«getExternalCodeStart("$ESTIMATION")»
		«IF !isInlineTargetDefined»
			«FOR s: b.statements»
			«IF s.symbol != null»«s.symbol.printDefaultEstimate»«ENDIF»
			«ENDFOR»
			NOABORT 
			«b.printCovariance»
		«ELSE»
			«FOR s: b.statements»
			«IF s.targetBlock != null»«s.targetBlock.print»«ENDIF»
			«ENDFOR»
		«ENDIF»
	«getExternalCodeEnd("$EST")»
	«getExternalCodeEnd("$ESTIMATION")»
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
	
	//Print $COV 
	def printCovariance(EstimateTask b)'''
	«IF b.isCovarianceDefined»
	
	$COV 
	«ENDIF»
	«getExternalCodeStart("$COV")»
	«getExternalCodeStart("$COVARIANCE")»
		«FOR s: b.statements»«IF s.symbol != null»«s.symbol.printCovariance»«ENDIF»«ENDFOR»
	«getExternalCodeEnd("$COV")»
	«getExternalCodeEnd("$COVARIANCE")»
	'''	
	
	//Print "cov" attribute for $COVARIATE record 
	def printCovariance(SymbolDeclaration s) { 
		if (s.identifier.equals("cov")){
			if (s.expression != null){
				if (s.expression.toStr.replaceAll("\\s","").equals(""))
				'''«s.expression.print»'''
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
		if (id.equalsIgnoreCase("ln"))
			return "LOG";
		return id.toUpperCase();	
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
	
	//This is needed because of a bug in NONMEM x||y -> x, y for IGNORE statement
	//toStr OR expression
	def toCommaSeparatedStr(OrExpression e){
		var res = "";
		var iterator = e.expression.iterator();
		if (iterator.hasNext ) res = iterator.next.toStr;
		while (iterator.hasNext) res  = res + ', ' + iterator.next.toStr;	
		return res;
	}
	
	//This is needed because of a bug in NONMEM x&&y -> x, y for ACCEPT statement
	//toStr AND expression
	def toCommaSeparatedStr(AndExpression e){
		var res = "";
		var iterator = e.expression.iterator();
		if (iterator.hasNext ) res = iterator.next.toStr;
		while (iterator.hasNext)
			res  = res + ', ' + iterator.next.toStr;
		return res;	
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
				val isFirst = b.arguments.selectAttribute("first");
				if (isFirst.isTrue){
					var codeSnippets = externalCodeStart.get(location);
					if (codeSnippets == null) codeSnippets = new ArrayList<String>();
					codeSnippets.add(b.externalCodeToStr);
					externalCodeStart.put(location, codeSnippets);
				} else {
					var codeSnippets = externalCodeEnd.get(location);
					if (codeSnippets == null) codeSnippets = new ArrayList<String>();
					codeSnippets.add(b.externalCodeToStr);
					externalCodeEnd.put(location, codeSnippets);
				} 
			}
		}
	}	
	
}
