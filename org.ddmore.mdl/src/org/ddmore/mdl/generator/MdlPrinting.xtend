/*
 * MDL converter toolbox
 * @DDMoRe
 * Author: Natallia Kokash, LIACS, 2012
 */
package org.ddmore.mdl.generator

import org.ddmore.mdl.mdl.Mcl
import org.ddmore.mdl.mdl.FunctionCall
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.Block
import org.ddmore.mdl.mdl.BlockStatement
import org.ddmore.mdl.mdl.List
import org.ddmore.mdl.mdl.RandomList
import org.ddmore.mdl.mdl.OdeList
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.Primary
import org.ddmore.mdl.mdl.ConditionalStatement
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.OrExpression
import org.ddmore.mdl.mdl.AndExpression
import org.ddmore.mdl.mdl.LogicalExpression
import org.ddmore.mdl.mdl.ConditionalExpression
import org.ddmore.mdl.mdl.AdditiveExpression
import org.ddmore.mdl.mdl.MultiplicativeExpression
import org.ddmore.mdl.mdl.PowerExpression
import org.ddmore.mdl.mdl.UnaryExpression
import org.ddmore.mdl.mdl.ParExpression
import org.ddmore.mdl.mdl.Arguments
import org.eclipse.emf.ecore.resource.Resource
import org.ddmore.mdl.mdl.TargetBlock
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName
import org.ddmore.mdl.mdl.Vector
import org.ddmore.mdl.mdl.SymbolModification
import org.ddmore.mdl.mdl.ObservationBlock
import org.ddmore.mdl.mdl.EnumType
import org.ddmore.mdl.mdl.Distribution
import org.ddmore.mdl.mdl.UseType
import org.ddmore.mdl.mdl.FullyQualifiedArgumentName
import org.ddmore.mdl.mdl.Selector
import java.util.HashMap
import org.ddmore.mdl.mdl.ModelObjectBlock
import org.ddmore.mdl.mdl.ParameterObjectBlock
import org.ddmore.mdl.mdl.ImportBlock
import org.ddmore.mdl.mdl.DataObjectBlock
import org.ddmore.mdl.mdl.TaskObjectBlock
import java.util.ArrayList
import org.eclipse.emf.common.util.EList
import org.ddmore.mdl.mdl.ParameterObject
import org.ddmore.mdl.mdl.EstimateTask
import org.ddmore.mdl.mdl.DataObject
import org.ddmore.mdl.mdl.FormalArguments
import org.ddmore.mdl.mdl.SimulationBlock
import org.ddmore.mdl.mdl.EstimationBlock
import org.ddmore.mdl.mdl.FileBlockStatement

class MdlPrinting {

	protected var externalFunctions = new HashMap<String, HashMap<String, String>>() //list of external functions 
	protected var externalCodeStart = new HashMap<String, ArrayList<String>>() //external code per target language section,
	protected var externalCodeEnd = new HashMap<String, ArrayList<String>>() //external code per target language section,
	
	protected var eta_vars = newHashMap	//ETAs
  	protected var eps_vars = newHashMap   //EPSs
	protected var theta_vars = newHashMap //THETAs
	protected var dadt_vars = newHashMap  //DADT	
	protected var init_vars = newHashMap  //A		
	
	//These maps are used to print same blocks (NM-TRAN SAME opetion in $OMEGA and $SIGMA)
	protected var namedOmegaBlocks = new HashMap<String, Integer>() //Collection of names of $OMEGA records with dimensions
	protected var namedSigmaBlocks = new HashMap<String, Integer>() //Collection of names of $SIGMA records with dimensions
	
	
	///////////////////////////////////////////////
	//Prepares variable maps
	///////////////////////////////////////////////
	def protected clearCollections(){
		init_vars.clear;
		dadt_vars.clear;
		theta_vars.clear;
		eta_vars.clear;
    	eps_vars.clear; 
    	namedOmegaBlocks.clear;
    	namedSigmaBlocks.clear;	
	}
	
	//Collect variables from the MDL file
	def protected prepareCollections(Mcl m){
    	clearCollections();
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
	def protected setInitialConditions(ModelObject o){
		var i = 1;
		for (b:o.blocks){
			if (b.modelPredictionBlock != null)
				for (s: b.modelPredictionBlock.statements){
					if (s.odeBlock != null)
						for (ss: s.odeBlock.statements){
							if (ss.symbol != null)
								if (ss.symbol.expression != null)
									if (ss.symbol.expression.odeList != null){
										val initCond = ss.symbol.expression.odeList.arguments.getAttribute("init");
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
	def protected setModelPredictionVariables(ModelObject o) { 
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

	
	//Assign indices to THETAs
	def protected setStructuralParameters(ModelObject o){
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
	
	//Assign indices to variability parameters ($ETA, $ESP)
	def protected setRandomVariables(ModelObject o){
    	var i = 1; var j = 1; 
		for (b: o.blocks){
	  		if (b.randomVariableDefinitionBlock != null){
				for (s: b.randomVariableDefinitionBlock.variables) {
					if (s.randomList != null){	
						var level = s.randomList.arguments.getAttribute("level");
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
	
	//Find NM-TRAN functions
	def protected prepareExternals(Mcl mcl) {
		externalFunctions.clear;	
		externalCodeStart.clear;	
		externalCodeEnd.clear;	
		for (o:mcl.objects){
  			if (o.modelObject != null){
  				for (ModelObjectBlock block: o.modelObject.blocks){
  					if (block.importBlock != null)
  						block.importBlock.prepareExternalFunctions(o.identifier.name);
  					if (block.targetBlock != null)
  						block.targetBlock.prepareExternalCode;
  				}
  			}
  			if (o.parameterObject != null){
  				for (ParameterObjectBlock block: o.parameterObject.blocks){
  					if (block.importBlock != null)
  						block.importBlock.prepareExternalFunctions(o.identifier.name);
  					if (block.targetBlock != null)
  						block.targetBlock.prepareExternalCode;
  				}
  	  		}
   			if (o.dataObject != null){
  				for (DataObjectBlock block: o.dataObject.blocks){
  					if (block.importBlock != null)
  						block.importBlock.prepareExternalFunctions(o.identifier.name);
  					if (block.targetBlock != null)
  						block.targetBlock.prepareExternalCode;
  				}
  			}
  			if (o.taskObject != null){
  				for (TaskObjectBlock block: o.taskObject.blocks){
  					if (block.importBlock != null)
  						block.importBlock.prepareExternalFunctions(o.identifier.name);
  					if (block.targetBlock != null)
  						block.targetBlock.prepareExternalCode;
  				}
  			}
  			if (o.telObject != null){
  				for (BlockStatement block: o.telObject.statements){
  					if (block.targetBlock != null)
  						block.targetBlock.prepareExternalCode;
  				}
  			}
  		}
	}
	
	def protected void prepareExternalCode(TargetBlock block) { }	
	def protected void prepareExternalFunctions(ImportBlock block, String string) { }
	
	//Print a list of external code snippets: beginning of section
	def protected getExternalCodeStart(String sectionName){
		var res = "";		
		val snippets = externalCodeStart.get(sectionName) 
		if (snippets != null){
			for (x: snippets){
				res = res + "\n" + x;
			}
		}
		return res;
	}
	
	//Print a list of external code snippets: end of section
	def protected getExternalCodeEnd(String sectionName){
		var res = "";		
		val snippets = externalCodeEnd.get(sectionName) 
		if (snippets != null){
			for (x: snippets){
				res = res + "\n" + x;
			}
		}
		return res;
	}
	
	//Return attributes of an external function	from the collection
	def protected getExternalFunctionAttributes(FullyQualifiedSymbolName ref) { 
		if (ref.object != null)				
			return externalFunctions.get(ref.object.name + "$" + ref.identifier)
		else {
			//match the short name
			for (pair: externalFunctions.entrySet){
				val str = pair.key as String;
				if (str != null){
					val functID = str.substring(str.indexOf("$") + 1);
					if (functID.equals(ref.identifier)) return pair.value; 
				}
			}
		}	
	}

	//Get MDL file name in upper-case
	def fileNameUpperCase(Mcl m){
		m.eResource.fileName.toUpperCase()
	}	
	
	//Get MDL file name
	def fileName(Resource resource){
		var fileName = resource.getURI().lastSegment
		fileName.substring(0, fileName.lastIndexOf('.'))
	}
	
	def isTrue(AnyExpression e){
		if (e.expression != null){
			if (e.expression.conditionalExpression != null){
				val orExpr = e.expression.conditionalExpression.expression;
				val andExpr = orExpr.expression.get(0);
				val logicalExpr = andExpr.expression.get(0);	
				if (logicalExpr.boolean != null){	
					if ((logicalExpr.negation == null) && logicalExpr.boolean.equals("true")) return true;
					if ((logicalExpr.negation != null) && logicalExpr.boolean.equals("false")) return true;
				}
			}
		}
		return (e.toStr.equalsIgnoreCase("yes") || e.toStr.equalsIgnoreCase("true") || e.toStr.equals("1"));
	}
	
	
	def isAttributeTrue(Arguments a, String attrName){
		for (arg: a.arguments)
			if (arg.identifier.equals(attrName)){
				return arg.expression.isTrue;
			}
		return false;
	}
	
	//Return value of an attribute with a given name
	def getAttribute(Arguments a, String attrName){
		for (arg: a.arguments)
			if (arg.identifier != null &&
			    arg.identifier.equals(attrName)
			)
				return arg.expression.toStr
		return "";
	}	
	
	//Return value of an attribute with a given name
	def getAttributeExpression(Arguments a, String attrName){
		for (arg: a.arguments)
			if (arg.identifier != null &&
			    arg.identifier.equals(attrName)
			)
				return arg.expression
		return null;
	}	
	
	//Find reference to a data file 
	def getDataSource(Mcl m){
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
		return "";
	}
	
	//Find reference to a data file 
	def getDataSource(Resource resource){
		for(m: resource.allContents.toIterable.filter(typeof(Mcl))) {
			var source = m.getDataSource;
			if (source.length > 0) return source;
		}		
		return "";
	}
	
	def getDataSource(FileBlockStatement s){
		if (s.variable != null){
			if (s.variable.identifier.equals("data")){
				if (s.variable.expression != null){
					if (s.variable.expression.list != null)
						return s.variable.expression.list.arguments.getAttribute("source");
				}
			}
		}
		return "";
	}
	
	
	///////////////////////////////////////////////////////////////////////////////
	//Check whether MDL blocks are defined and non empty
	///////////////////////////////////////////////////////////////////////////////
	//Check that VARIABILITY block or its subblocks are not empty
	def isHeaderDefined(DataObject obj){
		for (b:obj.blocks){
			if (b.headerBlock != null){
				if (b.headerBlock.variables.size > 0){
				 	return true;
				}
			}
		}
		return false;
	}

	def isPriorDefined(ParameterObject obj){
		for (b:obj.blocks){
			if (b.priorBlock != null){
				if (b.priorBlock.statements.size > 0)
				 	return true;
			}
		}
		return false;
	}
	
	//Check that STRUCTURAL is not empty
	def isStructuralDefined(ParameterObject obj){
		for (b:obj.blocks){
			if (b.structuralBlock != null){
				if (b.structuralBlock.parameters.size > 0)
				 	return true;
			}
		}
		return false;
	}

	//Check that VARIABILITY block or its subblocks are not empty
	def isVariabilityDefined(ParameterObject obj){
		for (b:obj.blocks){
			if (b.variabilityBlock != null){
				if (b.variabilityBlock.statements.size > 0){
				 	return true;
				}
			}
		}
		return false;
	}

	//Check that VARIABILITY subblocks are not empty
	def isVariabilitySubBlocksDefined(ParameterObject obj){
		for (b:obj.blocks){
			if (b.variabilityBlock != null){
				for (bb: b.variabilityBlock.statements){
					if ((bb.diagBlock != null) || (bb.matrixBlock != null) || (bb.sameBlock != null))
				 		return true;
				}
			}
		}
		return false;
	}
	
	//Return true if there are definitions in MODEL PREDICTION or OBSERVATION blocks,
	def isErrorDefined(ModelObject o){
    	for (mob:o.blocks){
	    	if (mob.modelPredictionBlock != null){
				for (s: mob.modelPredictionBlock.statements){
					if (s.statement != null) return true;
				}
	    	}
	    	if (mob.observationBlock != null){
				if (mob.observationBlock.statements.size > 0){
					return true;
				}
	    	}
	    	if (mob.simulationBlock != null){
				if (mob.simulationBlock.statements.size > 0){
					return true;
				}
	    	}
	    	if (mob.estimationBlock != null){
				if (mob.estimationBlock.statements.size > 0){
					return true;
				}
	    	}	    	
		}
    	return false;
    } 
	
    //Check if there are definitions in ODE block
    def isODEDefined(ModelObject o){
		for (mob: o.blocks){
			if (mob.modelPredictionBlock != null){
				var b = mob.modelPredictionBlock;
		    	for (s: b.statements){
		    		if (s.odeBlock != null) return true;
		    	}
		    }
		}
    	return false;
    }

	//Check if GROUP or INDIVIDUAL variables are defined
	def isGroupOrIndividualDefined(ModelObject o){
		for (mob: o.blocks){
			if (mob.groupVariablesBlock != null){
				if (mob.groupVariablesBlock != null){
					for (st: mob.groupVariablesBlock.statements)
						if (st.statement != null)
							return true;
				}
			}
			if (mob.individualVariablesBlock != null){
				if (mob.individualVariablesBlock.statements.size > 0){
					return true;
				}
			}
		}
		return false;
	}
	
	/*def isIndividualDefined(Mcl m){
		for (o: m.objects){
			if (o.modelObject != null){
				for (mob: o.modelObject.blocks){
					if (mob.individualVariablesBlock != null){
						if (mob.individualVariablesBlock.statements.size > 0){
							return true;
						}
					}
				}				
			}
		}
		return false;
	}*/
	
	//Check if MIXTURE block is defined 
	def isMixDefined(ModelObject o){
		for (mob: o.blocks){
			if (mob.groupVariablesBlock != null){
				if (mob.groupVariablesBlock != null){
					for (st: mob.groupVariablesBlock.statements){
						if (st.mixtureBlock != null){
							return true;
						}
					}
				}
			}
		}
		return false;
	}

    //Check if LIBRARY block is defined
    def isLibraryDefined(ModelObject o){
    	for (mob: o.blocks){
			if (mob.modelPredictionBlock != null){
				for (s: mob.modelPredictionBlock.statements){
				    if (s.libraryBlock != null) return true;
				}
			}
    	}
    	return false;
    }
    
    //Check if LIBRARY block is defined
    def getNumberOfCompartments(ModelObject o){
    	for (mob: o.blocks){
			if (mob.modelPredictionBlock != null){
				for (s: mob.modelPredictionBlock.statements){
				    if (s.libraryBlock != null) {
				    	for (ss: s.libraryBlock.statements){
				    		var nmct = ss.expression.arguments.getAttribute("ncmt");
				    		if (!nmct.equals("")) {
				    			try{
						    		return Integer::parseInt(nmct);
				    			}
				    			catch(NumberFormatException e){
				    				return 0;		
				    			}
				    		}
				    	}
				    }
				}
			}
    	}
    	return 0;
    }
    
    
    //Check if OUTPUT_VARIABLES block is defined and has content
	def isOutputVariablesDefined(ModelObject o){
		for (b: o.blocks){
			if ( b.outputVariablesBlock != null){
				if ( b.outputVariablesBlock.variables.size > 0) return true;
			}
		}
		return false;
	}
	
	//Check if attribute cov is define in ESTIMATE block
	def isCovarianceDefined(EstimateTask b){
		for (s: b.statements)
			if (s.symbol != null){
				if (s.symbol.identifier.equals("cov"))
					if (s.symbol.expression != null) return true;
			}
		return false;		
	}
	
	
	def isTargetDefined(String sectionName){
		return externalCodeStart.containsKey(sectionName) || externalCodeEnd.containsKey(sectionName);
	}
    
    //Check whether there is a target block in a list of block statements			
    def isInlineTargetDefined(String targetName, EList<BlockStatement> list){
		for (s: list){
			if (s.targetBlock != null){
				val target = s.targetBlock.arguments.getAttribute("target");
		 		if (target != null) 
					if (target.equals(targetName)) {
						return true;
					}
			}
		}
		return false;
	}
	
	/////////////////////////////////////////////////////////////

    //Map variableDeclaration identifiers, identity for MDL printing, to be rewritten in super classes 
	def convertID(String id){
		return id;
	}
	
	//Map operators, identity for MDL printing, to be rewritten in super classes 
	def convertOperator(String op){
		return op;
	}
	
	def toStr(FullyQualifiedSymbolName name){
		var res = ""; 
		if (name.object != null){
			res  = name.object.name + "$" ;
		}
		res = res + name.identifier.convertID;
		return res;
	}
	
	def toStr(SymbolDeclaration v){
		var res = "";
		if (v.function != null){
			res = res + v.function.convertID + '(' 
		}
		if (v.identifier != null) {
			res = res + v.identifier.convertID;
		}
		if (v.function != null){
			res = res + ')' 
		}
		var expr = ""; //First make sure that expression is not empty, than print "=" 
		if (v.expression != null){
			expr = v.expression.toStr;
		}
		if (v.randomList != null){
			expr = v.randomList.toStr;
		}
		if (!expr.trim().equals(""))
			res = res + " = " + expr;
		return res;
	}
	
	def toStr(SymbolModification v){
		var res = "";
		if (v.identifier != null){
			v.identifier.toStr;
		}
		if (v.list != null){	
			res = res + " = " + v.list.toStr;
		}
		return res;
	}	
	 
	def toStr(AnyExpression e){
		var res = "";
		if (e.expression != null){
			res = res + e.expression.toStr;
		}
		if (e.list != null){
			res  = res + e.list.toStr;
		}
		if (e.odeList != null){
			res = res + e.odeList.toStr;
		}
		if (e.type != null){
			res = res + e.type.toStr;
		}
		return res;
	}
	
	def toStr(EnumType type) {
		if (type.categorical != null){
			var res = "";
			if (type.categorical.arguments != null){
				res = type.categorical.arguments.toStr;
			}
			return type.categorical.identifier + '(' + res + ')'
		} 
		if (type.continuous != null){
			return type.continuous.identifier
		} 
		if (type.covariate != null){
			return type.covariate.identifier
		} 
		if (type.distribution != null){
			return type.distribution.toStr
		} 
		if (type.use != null){
			return type.use.toStr
		} 
		if (type.likelyhood != null){
			return type.likelyhood.identifier	
		} 
		//if (type.missing != null){
		//	return type.missing.identifier
		//} 
		if (type.target != null){
			return type.target.identifier 
		} 
	}
	
	
	def String toStr(Distribution d) { 
		return d.identifier
	}

	def String toStr(UseType l) { 
		return l.identifier
	}
	
	def toStr(RandomList l){
		if (l.arguments != null){
			return "~" + "(" + l.arguments.toStr + ")";
		}
		return "";
	}

	def toStr(List l){
		if (l.arguments != null){
			return "list" + "(" + l.arguments.toStr + ")";
		}
		return "";
	}
	
	def toStr(OdeList l){
		if (l.arguments != null){ 
			return "ode" + "(" + l.arguments.toStr + ")"
		}
	}
	
	def toStr(Expression e){
		return e.conditionalExpression.toStr
	}
	
	def toStr(ConditionalExpression e){ 
		var res = e.expression.toStr;
		if (e.expression1 != null){
			res  = res + "?" + e.expression1.toStr + ":" + e.expression2.toStr
		}
		return res;
	}

	def toStr(OrExpression e){
		var res = "";
		var iterator = e.expression.iterator();
		var operatorIterator = e.operator.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext && operatorIterator.hasNext){
			res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
		}
		return res;
	}
	
	def toStr(AndExpression e){
		var res = "";
		var iterator = e.expression.iterator();
		var operatorIterator = e.operator.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext && operatorIterator.hasNext){
			res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
		}
		return res;	
	}
	
	//(==, !=, <, > etc.)
	def toStr(LogicalExpression e){
		var res = "";
		if (e.negation != null){
			res = res + e.negation;
		}
		if (e.boolean != null){
			res = res + e.boolean.toString;
		}
		var iterator = e.expression.iterator();
		var operatorIterator = e.operator.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext && operatorIterator.hasNext){
			res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
		}
		return res;
	}
	
	def toStr(AdditiveExpression e){
		var res  = "";
		if (e.expression != null)
		{ 
			var iterator = e.expression.iterator();
			var operatorIterator  = e.operator.iterator();
			if (iterator.hasNext ) {
				res = iterator.next.toStr;
			}
			while (iterator.hasNext && operatorIterator.hasNext){
				res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
			}
		}
		if (e.string != null){		
			var iterator = e.string.iterator();
			//var operatorIterator  = e.operator.iterator();
			if (iterator.hasNext ) {
				res = iterator.next;
			}
			while (iterator.hasNext){ // && operatorIterator.hasNext){
				//res  = res + operatorIterator.next.convertOperator + iterator.next;
				res  = res + iterator.next; //concatenation
			}
		}
		return res;
	}
	
	def toStr(MultiplicativeExpression e){
		var res = "";
		var iterator = e.expression.iterator();
		var operatorIterator  = e.operator.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext){
			res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
		}
		return res;
	}
	
	def String toStr(PowerExpression e) { 
		var res = "";
		var iterator = e.expression.iterator();
		var operatorIterator  = e.operator.iterator();
		if (iterator.hasNext ) {
			res = iterator.next.toStr;
		}
		while (iterator.hasNext){
			res  = res + operatorIterator.next.convertOperator + iterator.next.toStr;
		}
		return res;
	}
	
	def toStr(UnaryExpression e){
		var res = "";
		if (e.expression != null){
			res = res + e.operator.convertOperator + e.expression.toStr
		}
		if (e.parExpression != null){
			res = res + e.parExpression.toStr;
		}
		if (e.primary != null) {
			res = res + e.primary.toStr
		}
		return res;
	}	
	
	def toStr(FunctionCall call){
		return call.identifier.toStr + "(" + call.arguments.toStr + ")";
	}
	
	def toStr(Primary p){
		if (p.number != null){
			return  p.number;
		}
		if (p.symbol != null){
			return p.symbol.toStr; 
		}
		if (p.functionCall != null) {
			return p.functionCall.toStr
		}
		if (p.vector != null) {
			return p.vector.toStr
		}
		if (p.attribute != null) {
			return p.attribute.toStr
		}
	}
	
	def toStr(FullyQualifiedArgumentName name) { 
		var res = name.parent.toStr;
		for (s: name.selectors){
			res = res + s.toStr
		}
		return res;
	}
	
	def toStr(Selector s) { 
		if (s.identifier != null)
			return "." + s.identifier.identifier;
		if (s.selector != null)
			return "[" + s.selector + "]";
	}
	
	def toStr(Vector v) { 
		var res  = v.identifier + '(';
		var iterator = v.values.iterator();
		if (iterator.hasNext) {
			res = res + iterator.next.toStr;
		}
		while (iterator.hasNext){
			res  = res + ', ';
			res = res + iterator.next.toStr;
		}
		return res + ')';
	}
	
	def toStr(ParExpression e){
		return "(" + e.expression.toStr + ")";
	}
	
	def toStr(Arguments arg){
		var res  = "";
		var iterator = arg.arguments.iterator();
		if (iterator.hasNext ) {
			var a = iterator.next; 
			if (a.identifier != null){
				res  = res + a.identifier + " = ";
			}
			if (a.expression != null){
				res = res + a.expression.toStr;
			}
		}
		while (iterator.hasNext){
			res  = res + ', ';
			var a = iterator.next; 
			if (a.identifier != null){
				res  = res + a.identifier + " = ";
			}
			if (a.expression != null){
				res = res + a.expression.toStr;
			}
		}
		return res;
	}	
	
	def toStr(FormalArguments arg) { 
		var res  = "";
		var iterator = arg.arguments.iterator();
		if (iterator.hasNext ) {
			var a = iterator.next; 
			if (a.identifier != null){
				res  = res + a.identifier;
			}
		}
		while (iterator.hasNext){
			res  = res + ', ';
			var a = iterator.next; 
			if (a.identifier != null){
				res  = res + a.identifier;
			}
		}
		return res;
	}
	
	
	def toStr(TargetBlock b){
		return b.externalCode.substring(3, b.externalCode.length - 3)
	}

	//////////////////////////////////////////////////////////////////////////
	//Printing
    //////////////////////////////////////////////////////////////////////////	
	 
	def print(TargetBlock b)'''
	«IF b.externalCode != null»
		«var printedCode = b.externalCode.substring(3, b.externalCode.length - 3)»
		«printedCode»
	«ENDIF»
	'''	
	
	def print(Block b)'''
		«FOR st: b.statements»
			«st.print»
		«ENDFOR»
	'''
	
	def print(ObservationBlock b)'''
		«FOR s: b.statements»
			«s.print»
		«ENDFOR»
	'''		
	
	def print(SimulationBlock b)'''
		«FOR s: b.statements»
			«s.print»
		«ENDFOR»
	'''	

	def print(EstimationBlock b)'''
		«FOR s: b.statements»
			«s.print»
		«ENDFOR»
	'''	
		
	def print(BlockStatement st)'''
		«IF st.symbol != null»«st.symbol.print»«ENDIF»
		«IF st.functionCall != null»«st.functionCall.print»«ENDIF»
		«IF st.statement != null»«st.statement.print»«ENDIF»
		«IF st.targetBlock != null»«st.targetBlock.print»«ENDIF»
		'''

	def print(ConditionalStatement s)'''
		«IF s.parExpression != null»
			if «s.parExpression.print»
				«IF s.ifStatement != null»
					«s.ifStatement.print»
				«ENDIF»
				«IF s.ifBlock != null»
					«s.ifBlock.print»
				«ENDIF»
			«IF s.elseStatement != null || s.elseBlock != null»
			else 
				«IF s.elseStatement != null»
					«s.elseStatement.print»
				«ENDIF»
				«IF s.elseBlock != null»
					«s.elseBlock.print»
				«ENDIF»
			«ENDIF»
		«ENDIF»
	'''
	
	def print(FunctionCall call)'''«call.toStr»'''
		
    def print(SymbolDeclaration v)'''«v.toStr»'''

    def print(SymbolModification v)'''«v.toStr»'''

	def print(AnyExpression e)'''«e.toStr»'''
		
	def print(RandomList l)'''«l.toStr»'''

	def print(List l)'''«l.toStr»'''
	
	def print(OdeList l)'''«l.toStr»'''

	def print(Expression e)'''«e.toStr»'''
	
	def print(ConditionalExpression e)'''«e.toStr»'''

	def print(OrExpression e)'''«e.toStr»'''
	
	def print(AndExpression e)'''«e.toStr»'''
	
	def print(LogicalExpression e)'''«e.toStr»'''
	
	def print(AdditiveExpression e)'''«e.toStr»'''
	
	def print(MultiplicativeExpression e)'''«e.toStr»'''
	
	def print(PowerExpression e)'''«e.toStr»'''
	
	def print(UnaryExpression e)'''«e.toStr»'''

	def print(Primary p)'''«p.toStr»'''
	
	def print(ParExpression e)'''«e.toStr»'''
	
	def print(Arguments arg)'''«arg.toStr»'''
}
