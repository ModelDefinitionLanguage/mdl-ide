package eu.ddmore.converter.mdl2pharmml

import eu.ddmore.converter.mdlprinting.MdlPrinter
import java.util.ArrayList
import java.util.Collections
import org.apache.commons.io.FilenameUtils
import org.ddmore.mdl.mdl.AdditiveExpression
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.Arguments
import org.ddmore.mdl.mdl.CovariateDefinitionBlock
import org.ddmore.mdl.mdl.DataObject
import org.ddmore.mdl.mdl.Expression
import org.ddmore.mdl.mdl.InputFormatType
import org.ddmore.mdl.mdl.List
import org.ddmore.mdl.mdl.MOGObject
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.ModelPredictionBlockStatement
import org.ddmore.mdl.mdl.NonContinuousType
import org.ddmore.mdl.mdl.OrExpression
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.SymbolName
import org.ddmore.mdl.mdl.UnaryExpression
import org.ddmore.mdl.mdl.UseType
import org.ddmore.mdl.mdl.Vector
import org.ddmore.mdl.mdl.impl.DataInputBlockImpl
import org.ddmore.mdl.mdl.impl.SymbolDeclarationImpl
import org.ddmore.mdl.types.DistributionType
import org.ddmore.mdl.validation.AttributeValidator
import org.ddmore.mdl.validation.PropertyValidator
import org.ddmore.mdl.validation.Utils
import org.eclipse.xtext.EcoreUtil2

import static eu.ddmore.converter.mdl2pharmml.Constants.*

class DataSetPrinter {
	protected extension MdlPrinter mdlPrinter = MdlPrinter::getInstance();
	protected extension MathPrinter mathPrinter = null;
	protected extension ReferenceResolver resolver = null;

	new(MathPrinter mathPrinter, ReferenceResolver resolver) {
		this.mathPrinter = mathPrinter;
		this.resolver = resolver;
	}

	protected def print_ds_Table(ArrayList<String> rows) '''
		<ds:Table>
			«FOR i : 0..rows.size-1»
				«rows.get(i)»
			«ENDFOR»
		<ds:Table>
	'''

	protected def print_ds_Row(ArrayList<String> values) {
		return '''<Row>«FOR i : 0..values.size-1»«values.get(i).print_ct_Value»«ENDFOR»</Row>''';
	}

	protected def print_ds_ColumnMapping(String columnId, String symbId, String complexMapping) '''
		<ColumnMapping>
			<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«columnId»"/>
			«IF symbId != null»
				«symbId.print_ct_SymbolRef»
			«ENDIF»
			«IF complexMapping.length > 0»
				«complexMapping»
			«ENDIF»
		</ColumnMapping>
	'''


	def print_ds_DvMapping(SymbolDeclaration dvColumn, DataObject dObj, ModelObject mObj){
		val define = dvColumn.list.arguments.getAttributeExpression(AttributeValidator::attr_define.name);
		val columnId = dvColumn.symbolName.name;
		if (define != null) {
			// Reference or mapped to data
			if (define.expression != null)
				return '''
					<ColumnMapping>
					    <ColumnRef xmlns="«xmlns_ds»" columnIdRef="«columnId»"/>
						«define.expression.print_Math_Expr»
							«IF define.expression.isCategoricalObs(mObj)»
							«define.expression.printCategoricalObsMapping(mObj)»
			    	   	«ELSEIF define.expression.isDiscreteBernoulliObs(mObj)»
							«printDiscreteBernoulliObsMapping»
			    	   	«ENDIF»
					</ColumnMapping>
				  '''
			else { 
				val pairs = define.getAttributePairs(AttributeValidator::attr_pred.name,
					AttributeValidator::attr_predid.name);
				val dvidColId = dObj.getUseDvidColumn
				return '''
					<MultipleDVMapping>
						<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«columnId»"/>
						<Piecewise xmlns="«xmlns_mstep»">
							«FOR p : pairs»
							<math:Piece>
							   	«p.key.expression.print_Math_Expr»
							   	«IF p.key.expression.isCategoricalObs(mObj)»
							   		«p.key.expression.printCategoricalObsMapping(mObj)»
							   	«ELSEIF p.key.expression.isDiscreteBernoulliObs(mObj)»
							   		«printDiscreteBernoulliObsMapping»
							   	«ENDIF»
							   	<math:Condition>
							   		<math:LogicBinop op="eq">
							   			<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«dvidColId»"/>
							   			«p.value.expression.print_Math_Expr»
							   		</math:LogicBinop>
							   	</math:Condition>
							</math:Piece>
					«ENDFOR» 
						</Piecewise>
					</MultipleDVMapping>
				  '''
			}
		}
	}
	
	def print_ds_TargetMapping(SymbolDeclaration amtColumn, DataObject dObj, ModelObject mObj){
		val define = amtColumn.list.arguments.getAttributeExpression(AttributeValidator::attr_define.name);
		val columnId = amtColumn.symbolName.name;
		var toolMappingDefn = '''''';
		if (define != null) {
			// There really must be define in this case.
			if (define.expression != null){}
				// ERROR: Can only do PK Macro mapping with define pairs
			else { 
				var pairs = define.getAttributePairs(AttributeValidator::attr_modelCmt.name,
					AttributeValidator::attr_dataCmt.name);
				toolMappingDefn = '''
				    «FOR p : pairs»
			    	   	«IF p.key.expression.isCompartmentVar(mObj)»
			    	   		«p.key.expression.printTargetMapping(p.value.expression, mObj)»
			    	   	«ENDIF»
					«ENDFOR» 
				  '''
			}
		} else {
			// this is a bug as the language will be invalid if this is true.
		}
		'''
			«IF toolMappingDefn.length > 0»
			<ColumnMapping>
				<ds:ColumnRef columnIdRef="«columnId»"/>
				<ds:TargetMapping blkIdRef="sm">
					«toolMappingDefn»
				</ds:TargetMapping>
			</ColumnMapping>
			«ENDIF»
		'''
	}
	
	def printTargetMapping(Expression expression, Expression valExpr, ModelObject mObj)'''
		«FOR block : mObj.blocks»
			«IF block.modelPredictionBlock != null»
				«FOR ModelPredictionBlockStatement stmt : block.modelPredictionBlock.statements»
					«IF stmt.pkMacroBlock != null »
						«FOR pkstmt : stmt.pkMacroBlock.statements»
							«IF pkstmt.variable != null && pkstmt.variable.isAdministrationMacro»
								<ds:Map dataSymbol="«valExpr.toStr»" admNumber="«getAdmValue(pkstmt.variable.list)»"/>
							«ENDIF»
						«ENDFOR»
					«ENDIF»
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	'''
	
	def boolean isAdministrationMacro(SymbolDeclaration cmtDefn){
		val type = cmtDefn.list.arguments.namedArguments.arguments.getAttribute(AttributeValidator::attr_type_macro.name)
		return type == 'depot'
	}
	
	def getAdmValue(List cptList){
		val retVal = cptList.arguments.namedArguments.arguments.getAttribute(AttributeValidator::attr_modelCmt.name)
		retVal
	}
	
	def boolean isCompartmentVar(Expression expression, ModelObject mObj){
		for(block : mObj.blocks){
			if(block.modelPredictionBlock != null){
				for(ModelPredictionBlockStatement stmt : block.modelPredictionBlock.statements){
					if(stmt.pkMacroBlock != null){
						for(pkstmt : stmt.pkMacroBlock.statements){
							if(pkstmt.variable != null){
								val pkVar = pkstmt.variable.symbolName.name.toStr
								val dataVar = expression.toStr
								return pkVar == dataVar
							}
						}
					}
				}
			}
		}				
	}
	
	protected def print_ds_TargetDataSet(MOGObject mog) {
		var objects = Utils::getMOGObjects(mog);
		var mObj = Utils::getModelObject(objects);
		var dObj = Utils::getDataObject(objects);

		var res = "";
		if (dObj != null || mObj != null) {
			for (b : dObj.blocks) {
				if (b.sourceBlock != null) {
					for (s : b.sourceBlock.statements) {
						if (s.propertyName.name.equals(PropertyValidator::attr_inputformat.name) &&
							s.expression != null) {
							if (s.expression.toStr.equals(InputFormatType::NONMEM_FORMAT.toString)) {
								var content = mog.print_ds_NONMEM_DataSet(mObj, dObj);
								res = res + print_ds_ExternalDataSet(content, "NONMEM", BLK_DS_NONMEM_DATASET);
							} else {
								var content = dObj.print_ds_DataSet(mObj, mog);
								res = res + print_ds_ExternalDataSet(content, "Monolix", BLK_DS_MONOLIX_DATASET);
							}

						}
					}
				}
			}
		}
		return res;
	}

	protected def getColumnType(SymbolDeclaration dataColumn) {
		var columnType = null as String;
		if (dataColumn.list != null) {
			val useValue = dataColumn.list.arguments.getAttribute(AttributeValidator::attr_use.name);
			if(useValue.length > 0) columnType = useValue;
		}
		return columnType;
	}

	protected def getValueType(SymbolDeclaration dataColumn) {
		val useValue = dataColumn.list.arguments.getAttribute(AttributeValidator::attr_use.name);
		val type = dataColumn.list.arguments.getAttributeExpression(AttributeValidator::attr_type.name);

		switch useValue {
			case 'id': Constants::TYPE_INT
			case 'covariate': if(type.isCategorical) Constants::TYPE_INT else Constants::TYPE_REAL
			case 'dvid': Constants::TYPE_INT
			case 'mdv': Constants::TYPE_INT
			case 'cmt': Constants::TYPE_INT
			case 'varlevel': Constants::TYPE_INT
			default: Constants::TYPE_REAL
		}
	}

	protected def print_ds_ExternalDataSet(String content, String toolName, String oid) '''
		<ExternalDataSet toolName="«toolName»" oid="«oid»">
			«content»
		</ExternalDataSet>
	'''

	protected def getUseCmtColumn(DataObject dObj) {
		var symbolIterator = dObj.eAllContents();
		while (symbolIterator.hasNext()) {
			var eObj = symbolIterator.next();
			if (eObj instanceof SymbolDeclarationImpl) {
				var column = eObj as SymbolDeclaration;
				if (column != null && column.symbolName != null) {
					var blockContainer = eObj.eContainer;
					if (blockContainer instanceof DataInputBlockImpl) {
						val use = column.list.arguments.getAttribute(AttributeValidator::attr_use.name);
						if (use.equals(UseType::CMT.toString)) {
							return column.symbolName.name;
						}
					}
				}
			}
		}
	}

	protected def getColumnByUse(DataObject dObj, UseType useType) {
		var symbolIterator = dObj.eAllContents();
		while (symbolIterator.hasNext()) {
			var eObj = symbolIterator.next();
			if (eObj instanceof SymbolDeclarationImpl) {
				var column = eObj as SymbolDeclaration;
				if (column != null && column.symbolName != null) {
					var blockContainer = eObj.eContainer;
					if (blockContainer instanceof DataInputBlockImpl) {
						val use = column.list.arguments.getAttribute(AttributeValidator::attr_use.name);
						if (use.equals(useType.toString)) {
							return column.symbolName.name;
						}
					}
				}
			}
		}
	}

	protected def getUseDvidColumn(DataObject dObj) {
		getColumnByUse(dObj, UseType::DVID)
	}

	protected def print_ds_NONMEM_DataSet(MOGObject mog, ModelObject mObj, DataObject dObj) {
		var res = "";
		var symbolIterator = dObj.eAllContents();
		while (symbolIterator.hasNext()) {
			var eObj = symbolIterator.next();
			if (eObj instanceof SymbolDeclarationImpl) {
				var column = eObj as SymbolDeclaration;
				if (column != null && column.symbolName != null) {
					var blockContainer = eObj.eContainer;
					// Map only columns, not DECLARED_VARAIBLES
					if(blockContainer instanceof DataInputBlockImpl) {
						// } || blockContainer instanceof DataDerivedBlockImpl){
						val use = column.list.arguments.getAttribute(AttributeValidator::attr_use.name);
						val colType = column.list.arguments.getAttributeExpression(AttributeValidator::attr_type.name);
						val modelVar = getDefaultMatchingVariable(mog, column, mObj);
						if (modelVar != null && (use.equals(UseType::ID.toString) || use.equals(UseType::IDV.toString) ||
							use.equals(UseType::VARLEVEL.toString) || (use.equals(UseType::COVARIATE.toString) &&
								!colType.isCategorical()))) {
								// use magic mapping with no conditions 
							res = res + column.symbolName.name.print_ds_MagicMapping(modelVar);
						} else if (modelVar != null && use.equals(UseType::COVARIATE.toString) && colType.isCategorical()) {
							// categorical covariates
							var categoricalMapping = column.print_ds_CategoricalMapping;
							res = res + column.symbolName.name.print_ds_ColumnMapping(modelVar, categoricalMapping);
						}
						else if (use.equals(UseType::AMT.toString)) {
							// handle amount mapping
							res = res + column.print_ds_AmtMapping(dObj, mObj)
						} else if (use.equals(UseType::DV.toString)) {
							// handle amount mapping
							res = res + column.print_ds_DvMapping(dObj, mObj)
						}
					}
				}
			}
		}
		res = res + dObj.print_ds_DataSet(mObj, mog);
	}

		def print_ds_AmtMapping(SymbolDeclaration amtColumn, DataObject dObj, ModelObject mObj) {
			amtColumn.print_ds_StandardAmtMapping(dObj, mObj)
			+ amtColumn.print_ds_TargetMapping(dObj, mObj)
		}


		protected def print_ds_StandardAmtMapping(SymbolDeclaration amtColumn, DataObject dObj, ModelObject mObj) {
			val define = amtColumn.list.arguments.getAttributeExpression(AttributeValidator::attr_define.name);
			val columnId = amtColumn.symbolName.name;
			var res = '''''';
			if (define != null) {
				// Reference or piecewise
				if (define.expression != null)
//					res = columnId.print_ds_ColumnMapping(define.expression.toStr, '''
					res = '''
						<ColumnMapping>
							<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«columnId»"/>
							<Piecewise xmlns="«xmlns_ds»">
								<math:Piece>
									«IF define.expression != null»
										«define.expression.toStr.print_ct_SymbolRef»
									«ENDIF»
									<math:Condition>
										<math:LogicBinop op="gt">
											<ColumnRef columnIdRef="«columnId»"/>
											<ct:Int>0</ct:Int>
										</math:LogicBinop>
									</math:Condition>
								</math:Piece>
							</Piecewise>
						</ColumnMapping>
						'''
				else { // Vector of pairs
					val pairs = define.getAttributePairs(AttributeValidator::attr_modelCmt.name,
						AttributeValidator::attr_dataCmt.name);
					val cmtColId = dObj.getUseCmtColumn
					val colMapping = '''
						«FOR p : pairs»
						«IF !p.key.expression.isCompartmentVar(mObj)»
							<math:Piece>
								«p.key.expression.print_Math_Expr»
							   	<math:Condition>
							   		<math:LogicBinop op="and">
							   			<math:LogicBinop op="eq">
											<ColumnRef columnIdRef="«cmtColId»"/>
								   			«p.value.expression.print_Math_Expr»
										</math:LogicBinop>
							   			<math:LogicBinop op="gt">
											<ColumnRef columnIdRef="«columnId»"/>
								   			<ct:Int>0</ct:Int>
										</math:LogicBinop>
									</math:LogicBinop>
							   	</math:Condition>
							</math:Piece>
						«ENDIF»
						«ENDFOR» 
					  '''
					 res = '''
						«IF colMapping.length > 0»
						<ColumnMapping>
						    <ColumnRef xmlns="«xmlns_ds»" columnIdRef="«columnId»"/>
							<Piecewise xmlns="«xmlns_ds»">
								«colMapping»
							</Piecewise>
						</ColumnMapping>
						«ENDIF»
					 '''
				}
			} else {
				// this is a bug as the language will be invalid if this is true.
			}
			res
		}
	
	def printCategoricalObsMapping(Expression expression, ModelObject object){
		val obsVar = expression.getSymbolReference
		val obsExpr = object.getMatchingObservationExpression(obsVar.name)
		// assume we have tested that this caregorical
		val catsExpr = obsExpr.list.arguments.getAttributeExpression(AttributeValidator::attr_categories.name) 
		'''
		<ds:CategoryMapping>
		«FOR cat : catsExpr.vector.expression.expressions»
			<ds:Map dataSymbol="«cat.toStr»" modelSymbol="c«cat.toStr»"/>
		«ENDFOR»
		</ds:CategoryMapping>
		'''
	}
	
	
	def printDiscreteBernoulliObsMapping(){
		val cat = "cat1"
		val catDataValue = 1 
		'''
		<ds:CategoryMapping>
		<ds:Map dataSymbol="«catDataValue»" modelSymbol="«cat»"/>
		</ds:CategoryMapping>
		'''
	}
	
	
	def getMatchingObservationExpression(ModelObject mObj, String symbName){
		for(block : mObj.blocks){
			if(block.observationBlock != null){
				for(symbDefn : block.observationBlock.variables){
					if(symbName == symbDefn.symbolName.name){
						return symbDefn
					}
				}
			}
		}				
	}
	
	
	def SymbolName getSymbolReference(Expression expression){
		if(expression.expression != null &&
			expression.expression.expression != null &&
			expression.expression.expression.size > 0 &&
			expression.expression.expression.head.expression.size > 0 &&
			expression.expression.expression.head.expression.head.expression1 != null &&
			expression.expression.expression.head.expression.head.expression1.expression.size >0 &&
			expression.expression.expression.head.expression.head.expression1.expression.head.expression.size > 0 &&
			expression.expression.expression.head.expression.head.expression1.expression.head.expression.head.expression.size > 0 &&
				expression.expression.expression.head.expression.head.expression1.expression.head.expression.head.expression.head.symbol != null){
			return expression.expression.expression.head.expression.head.expression1.expression.head.expression.head.expression.head.symbol;
		}
	}
	
	def boolean isCategoricalObs(Expression expression, ModelObject mObj){
		val symbRef = expression.getSymbolReference
		if(symbRef != null){
			val symbName = 	symbRef.name;
			val symbDefn = mObj.getMatchingObservationExpression(symbName)
			return 	symbDefn != null && symbDefn.list != null && symbDefn.list.arguments != null &&
						symbDefn.list.arguments.getAttribute(AttributeValidator::attr_type.name) == "categorical" 
		}
		return false
	}

	def boolean isDiscreteBernoulliObs(Expression expression, ModelObject mObj){
		val symbRef = expression.getSymbolReference
		if(symbRef != null){
			val symbName = 	symbRef.name;
			val symbDefn = mObj.getMatchingObservationExpression(symbName)
			if(symbDefn != null && symbDefn.list != null && symbDefn.list.arguments != null){
				val typeName = symbDefn.list.arguments.getAttribute(AttributeValidator::attr_type.name)
				val distName = symbDefn.list.arguments.getAttributeRandomList(AttributeValidator::attr_distrib.name)
				return typeName == NonContinuousType.DISCRETE.toString && distName.type.name  == DistributionType.Bernoulli.toString
			}
		}
		return false
	}

		protected def print_ds_MagicMapping(String columnId, String symbId) {
			columnId.print_ds_ColumnMapping(symbId, "").toString
		}

		protected def print_ds_CategoricalMapping(SymbolDeclaration column) {
			var res = "";
			if (column.list != null) {
				val type = column.list.arguments.getAttributeExpression(AttributeValidator::attr_type.name);
				if(!type.isCategorical) return "";
				val define = column.list.arguments.getAttributeExpression(AttributeValidator::attr_define.name);
				if (define != null) {
					var pairs = define.getAttributePairs(AttributeValidator::attr_category.name,
						AttributeValidator::attr_value.name);
					for (pair : pairs)
						res = res + '''
							<ds:Map modelSymbol="«pair.key.toStr»" dataSymbol="«pair.value.toStr»"/>
						''';
				}
			}
			if (res.length > 0)
				res = '''
					<ds:CategoryMapping>
						«res»
					</ds:CategoryMapping>
				'''
			return res;
		}

		// Return a model variable (matched by name)
		// not only valid matched are returned. So for example
		// no covariate with an RHS is returned as this does not match the data column.
		protected def getDefaultMatchingVariable(MOGObject mog, SymbolDeclaration column, ModelObject mObj) {
			if (column.symbolName != null && column.list != null) {
				var columnId = column.symbolName.name;
				/*Default implicit mapping*/
				val use = column.list.arguments.getAttribute(AttributeValidator::attr_use.name);
				// Individual variable
				if (use.equals(UseType::IDV.toString)) {
					return independentVar;
				}
				// Covariates (ise = covariate)
				if (use.equals(UseType::COVARIATE.toString)) {
					for (b : mObj.blocks) {
						if (b.covariateBlock != null) {
							for (s : b.covariateBlock.variables) {
								if (s.symbolName != null && s.symbolName.name.equals(columnId))
									return if(s.expression == null) s.symbolName.name else null;
							}
						}
					}
				}
				// Variability levels (use = id, use = dv)
				if (use.equals(UseType::ID.toString) || use.equals(UseType::DV.toString) ||
					use.equals(UseType::VARLEVEL.toString)) {
					for (b : mObj.blocks) {
						if (b.variabilityBlock != null) {
							for (s : b.variabilityBlock.variables) {
								if (s.symbolName != null && s.symbolName.name.equals(columnId))
									return s.symbolName.name;
							}
						}
					}
				}
			}
			return null;
		}

		def getCovariateFromModel(ModelObject mObj, SymbolDeclaration sd){
			for(blk : mObj.blocks){
				for(symbDec : blk.covariateBlock?.variables ?: Collections::emptyList){
					if(symbDec.symbolName.name == sd.symbolName.name) return symbDec
				}
			}			
		}

		def java.util.List<List> getSubLists(AnyExpression expr){
			val retVal = new ArrayList<List>
			if(expr.list != null){
				retVal.add(expr.list)
			}
			else{
				expr.vector?.expression?.lists.forEach[retVal.add(it)]
			}
			retVal
		}

		def isUsedAsCovariateFixEff(SymbolDeclaration sd, ModelObject mObj){
			// go through model object and see if we can identify 			
			for(blk : mObj.blocks){
				if(blk.individualVariablesBlock != null){
					for(symbDec : blk.individualVariablesBlock.variables){
						if(symbDec.list != null){
							for(arg : symbDec.list.arguments?.namedArguments?.arguments ?: Collections::emptyList){
								if(AttributeValidator::attr_fixEff.name == arg?.argumentName?.name){
									// fixEff found
									val subLists = arg.expression?.expression?.getSubLists ?: Collections::emptyList
									for(subList : subLists){
										// now look a matching cov
										for(subListArg : subList?.arguments?.namedArguments?.arguments ?: Collections::emptyList){
											if(AttributeValidator::attr_cov.name == subListArg?.argumentName?.name){
												// match the cov att so lets see if it uses this var name
												if(subListArg?.expression?.expression?.toStr?:"" == sd.symbolName.name) return true
											}
										}
									}
								}
							}
						}
					}					
				}
			}
			return false			
		}

		def isARegressor(SymbolDeclaration sd, ModelObject mObj){
			// need to check if there are transforming covs and then test them
			val transCovs = findTransformedCovariates(sd, mObj)
			for(tc : transCovs){
				if(isUsedAsCovariateFixEff(tc, mObj)) return false
			}
			// if no transformations found or none are covs then test this symbol itself
			return !isUsedAsCovariateFixEff(sd, mObj)
		}

		def java.util.List<SymbolDeclaration> findTransformedCovariates(SymbolDeclaration sd, ModelObject mObj){
			val retVal = new ArrayList<SymbolDeclaration>
			val modelCovSymb = mObj.getCovariateFromModel(sd)
			if(modelCovSymb != null){
				val covBlk = EcoreUtil2.getContainerOfType(modelCovSymb, CovariateDefinitionBlock)
				// store vars for dep checking
//				val covMap = new HashMap<String, SymbolDeclaration>
//				covBlk.variables.forEach[covMap.put(symbolName.name, it)]
				// now go through each covariate and check whether it uses the search cov on the RHS. If
				// so then it is a transforming covariate.
				for(covDefn : covBlk.variables){
					if(modelCovSymb.symbolName.name != covDefn.symbolName.name){
						// check deps
						if(covDefn.expression?.isSymbolFoundInCovExpression(modelCovSymb)){
							// just check expressions
							retVal.add(covDefn)
						}
						else if(covDefn.list?.isSymbolFoundInCovExpression(modelCovSymb)){
							retVal.add(covDefn)
						}
					}
				}
			}
			retVal
		}

		def boolean isSymbolFoundInCovExpression(OrExpression orExpr, SymbolDeclaration srchSymb){
			for(andExpr : orExpr.expression){
				for(logExpr : andExpr.expression){
					if(logExpr.expression1.isSymbolFoundInCovExpression(srchSymb)) return true		
					else if(logExpr.expression2?.isSymbolFoundInCovExpression(srchSymb)) return true		
				}
			}
			false
		}

		def boolean isSymbolFoundInCovExpression(AnyExpression anyExpr, SymbolDeclaration srchSymb){
			if(anyExpr.expression?.isSymbolFoundInCovExpression(srchSymb)) return true
			if(anyExpr.list?.isSymbolFoundInCovExpression(srchSymb)) return true
			if(anyExpr.vector?.isSymbolFoundInCovExpression(srchSymb)) return true
			false
		}

		def boolean isSymbolFoundInCovExpression(List listExpr, SymbolDeclaration srchSymb){
			if(listExpr.arguments?.isSymbolFoundInCovExpression(srchSymb)) return true
			false
		}

		def boolean isSymbolFoundInCovExpression(Vector vectExpr, SymbolDeclaration srchSymb){
			for(vExpr : vectExpr.expression?.expressions ?: Collections::emptyList){
				if(vExpr.isSymbolFoundInCovExpression(srchSymb)) return true
			}
			for(vExpr : vectExpr.expression?.lists ?: Collections::emptyList){
				if(vExpr.isSymbolFoundInCovExpression(srchSymb)) return true
			}
			false
		}

		def boolean isSymbolFoundInCovExpression(Expression expr, SymbolDeclaration srchSymb){
			var retVal = false
			if(isSymbolFoundInCovExpression(expr.expression, srchSymb)){
				retVal = true
			}
			else if(expr.whenBranches != null){
				val iter = expr.whenBranches.iterator
				while(iter.hasNext && !retVal){
					val wb = iter.next
					if(isSymbolFoundInCovExpression(wb.expression, srchSymb)){
						retVal = true
					}
				}
			}
			retVal
		}

		def boolean isSymbolFoundInCovExpression(AdditiveExpression addExpr, SymbolDeclaration srchSymb){
			for(mulExpr : addExpr.expression){
				for(powExpr : mulExpr.expression){
					for(unExpr : powExpr.expression){
						if(unExpr.isSymbolFoundInCovExpression(srchSymb)) return true
					}
				}
			}
			false
		}
		
		def boolean isSymbolFoundInCovExpression(UnaryExpression unExpr, SymbolDeclaration srchSymb){
			if(unExpr.expression?.isSymbolFoundInCovExpression(srchSymb)) return true
			if(unExpr.parExpression?.expression?.isSymbolFoundInCovExpression(srchSymb)) return true
			if(srchSymb.symbolName.name == unExpr.symbol?.name) return true							
			if(unExpr.functionCall?.arguments?.isSymbolFoundInCovExpression(srchSymb)) return true
			false
		}

		def boolean isSymbolFoundInCovExpression(Arguments argsExpr, SymbolDeclaration srchSymb){
			for(arg : argsExpr.namedArguments?.arguments ?: Collections::emptyList){
				if(arg.expression?.expression?.isSymbolFoundInCovExpression(srchSymb)) return true
			}
			for(arg : argsExpr.unnamedArguments?.arguments ?: Collections::emptyList){
				if(arg.expression?.expression?.isSymbolFoundInCovExpression(srchSymb)) return true
			}

			false
		}

		protected def print_ds_DataSet(DataObject dObj, ModelObject mObj, MOGObject mog) {
			var res = "";
			var k = 1;
			for (b : dObj.blocks) {
				var dosingToCompartmentMacro = false;
				if (b.dataInputBlock != null) {
					for (i : 0 .. b.dataInputBlock.variables.size - 1) {
						val column = b.dataInputBlock.variables.get(i);
						val columnId = column.symbolName.name;
						val columnType = column.getColumnType
						if(UseType::AMT.toString == columnType){
							dosingToCompartmentMacro = column.isDosingToCompartmentMacro(mObj)
						}
						var convertedColType = "undefined"
						if (columnType != null){
							if(UseType::ID.toString == columnType || column.isUsedInModel(mog, mObj)) {
								
								convertedColType = column.convertEnum(dosingToCompartmentMacro, if(UseType::COVARIATE.toString == columnType) isARegressor(column, mObj) else false);
							}
						}
						val valueType = column.getValueType
						res = res +
							'''
								<Column columnId="«columnId»" columnType="«convertedColType»" valueType="«valueType»" columnNum="«k»"/>
							'''
						k = k + 1;
					}
				}
			}
			return '''
				<DataSet xmlns="«xmlns_ds»">
					<Definition>
						«res»
					</Definition>
					«dObj.print_ds_ExternalFile»
				</DataSet>
			'''
		}
	
	def boolean isUsedInModel(SymbolDeclaration column, MOGObject mog, ModelObject mObj){
		val type = column.list.arguments.namedArguments.arguments.getAttribute(AttributeValidator::attr_use.name);
		switch(type){
			case UseType::IDV.toString,
			case UseType::VARLEVEL.toString,
			case UseType::COVARIATE.toString,
			case UseType::ID.toString: getDefaultMatchingVariable(mog, column, mObj) != null
			default: true
		}
	}
	
	//use option names
	def convertEnum(SymbolDeclaration columnDefn, boolean isDosingToCompartmentMacro, boolean isRegressor) {
		val type = columnDefn.getColumnType
		
		switch (type) {
			case UseType::AMT.toString     : "dose"
			case UseType::DVID.toString   : "dvid"
			case UseType::COVARIATE.toString: if(isRegressor) "reg" else "covariate"
//			case UseType::CENS.toString    : "censoring"
			case UseType::VARLEVEL.toString: "occasion"
			//case UseType::ITYPE.toString   : "dvid"		//case UseType::OCC.toString     : "occasion"
			//case UseType::TINF.toString    : "duration"
			case UseType::CMT.toString : if(isDosingToCompartmentMacro) 'adm' else 'cmt'
			default: type
		}
	}
	
	def boolean isDosingToCompartmentMacro(SymbolDeclaration amtColumn, ModelObject mObj){
		val define = amtColumn.list.arguments.getAttributeExpression(AttributeValidator::attr_define.name);
	
		var retVal = false
		if (define != null) {
			if (define.expression == null){
				var pairs = define.getAttributePairs(AttributeValidator::attr_modelCmt.name,
					AttributeValidator::attr_dataCmt.name);
					for(p : pairs){
			    	   	if(p.key.expression.isCompartmentVar(mObj)){
			    	   		retVal = true
			    	   	}
					} 
			}
		}
		return retVal
	}
	
		protected def print_ds_ExternalFile(DataObject dObj) {
			var res = "";
			for (b : dObj.blocks) {
				if (b.sourceBlock != null) {
					var file = "";
					var delimiter = "";
					for (s : b.sourceBlock.statements) {
						if (s.propertyName.name.equals(PropertyValidator::attr_file.name) && s.expression != null)
							file = s.expression.toStr;
						if (s.propertyName.name.equals(PropertyValidator::attr_delimiter.name) && s.expression != null)
							delimiter = s.expression.toStr;
					}
					if (file.length > 0) {
						if(delimiter.length == 0) delimiter = PropertyValidator::attr_delimiter.defaultValue;
						val fileExtension = FilenameUtils::getExtension(file);
						res = res + '''				
							<ExternalFile oid="«BLK_DS_IMPORT_DATA»">
								<path>«file»</path>
								<format>«fileExtension.convertFileFormat»</format>
								<delimiter>«delimiter.convertDelimiter»</delimiter>
							</ExternalFile>
						'''
					}
				}
			}
			return res;
		}

		protected def print_mdef_ExternalDataSetReference(DataObject dObj) {
			var oidRef = "";
			if (dObj != null) {
				for (b : dObj.blocks) {
					if (b.sourceBlock != null) {
						for (s : b.sourceBlock.statements) {
							if (s.propertyName.name.equals(PropertyValidator::attr_inputformat.name) &&
								s.expression != null) {
								if (s.expression.toStr.equals(InputFormatType::NONMEM_FORMAT.toString))
									oidRef = BLK_DS_NONMEM_DATASET;
							}
						}
					}
				}
			}
			if (oidRef.length > 0) '''
				<ExternalDataSetReference>
					<ct:OidRef oidRef="«oidRef»"/>
				</ExternalDataSetReference>
			'''
		}

	}
