package eu.ddmore.converter.mdl2pharmml

import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MappingExpression
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.SymbolDefinition
import eu.ddmore.mdl.utils.MclUtils
import eu.ddmore.mdl.validation.ListDefinitionProvider
import eu.ddmore.mdl.validation.MogValidator

import static eu.ddmore.converter.mdl2pharmml.Constants.*

import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.BuiltinFunctionCall
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.validation.BuiltinFunctionProvider
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.validation.SublistDefinitionProvider
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.CategoryValueReference
import eu.ddmore.mdl.mdl.MappingPair
import java.util.HashSet
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.CategoricalDefinitionExpr
import eu.ddmore.mdl.mdl.CatValRefMappingExpression
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.validation.PropertyDefinitionProvider
import eu.ddmore.mdl.mdl.MdlFactory

class ModellingStepsPrinter { 
	
	extension MclUtils mu = new MclUtils 
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension PropertyDefinitionProvider pdp = new PropertyDefinitionProvider
	extension SublistDefinitionProvider sldp = new SublistDefinitionProvider
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
//	extension PKMacrosPrinter bmp = new PKMacrosPrinter

	////////////////////////////////////////////////
	// III Modelling Steps
	////////////////////////////////////////////////
	def print_msteps_ModellingSteps(MogValidator validator){
		var mObj = validator.mdlObj
		var pObj = validator.paramObj
		var dObj = validator.dataObj
		var tObj = validator.taskObj

		var res = "";
		var dependencies = ""; 
		if (mObj != null && dObj != null && pObj != null && tObj != null) {
//			res = res + dObj.print_ds_TargetTool;
			res = res + print_ds_TargetDataSet(mObj, dObj);
			var index = 1;

			for (b: tObj.blocks){
//				if ((b.estimateBlock != null) || (b.simulateBlock != null)){
					var stepType = BLK_ESTIM_STEP;
//					if (b.simulateBlock != null) stepType = BLK_SIMUL_STEP;
//					if (stepType.equals(BLK_ESTIM_STEP)){
						res = res + print_msteps_EstimationStep(stepType + index, index, mObj, dObj, pObj, tObj);
//					} else {
//						res = res + print_msteps_SimulationStep(stepType + index, index, mObj, dObj, pObj, tObj);
//					}
					dependencies  = dependencies +
					'''
					<mstep:Step>
						<ct:OidRef oidRef="«stepType + index»"/>
					</mstep:Step>
					'''
					index  = index + 1;
//				}
			}
		}
		'''
		<ModellingSteps xmlns="«xmlns_mstep»">
			«res»
			«IF dependencies.length > 0»
				<mstep:StepDependencies>
					«dependencies»
				</mstep:StepDependencies>
			«ENDIF»
		</ModellingSteps>		
		'''	
	}

	////////////////////////////////////////////////
	// III.a Estimation Step
	////////////////////////////////////////////////
	protected def print_msteps_EstimationStep(String stepId, Integer order, MclObject mObj, MclObject dObj, MclObject pObj, MclObject tObj)'''
		<EstimationStep oid="«stepId»">
			«dObj.print_mdef_ExternalDataSetReference»
			«pObj.print_msteps_ParametersToEstimate»
			«tObj.print_msteps_EstimateOperations(order)»
		</EstimationStep>
	'''
		
		
	protected def print_mdef_ExternalDataSetReference(MclObject dObj)'''
		<ExternalDataSetReference>
			<ct:OidRef oidRef="«BLK_DS_NONMEM_DATASET»"/>
		</ExternalDataSetReference>
	'''

	def writeParameterEstimate(Statement s, MclObject pObj){
		val stmt = s
		switch(stmt){
			ListDefinition:{
				val paramVar = pObj.findMdlSymbolDefn(stmt.name)
				'''
				<ParameterEstimation>
					«paramVar.getSymbolReference»
					<InitialEstimate fixed="«stmt.list.getAttributeExpression('fix')?.convertToString ?: 'false'»">
						«stmt.list.getAttributeExpression('value').expressionAsEquation»
					</InitialEstimate>
					«IF stmt.list.getAttributeExpression('lo') != null»
						<LowerBound>
							«stmt.list.getAttributeExpression('lo').expressionAsEquation»
						</LowerBound>
					«ENDIF»
					«IF stmt.list.getAttributeExpression('hi') != null»
						<UpperBound>
							«stmt.list.getAttributeExpression('hi').expressionAsEquation»
						</UpperBound>
					«ENDIF»
				</ParameterEstimation>
				'''
			}
			default:''''''
		}
	}
		
	protected def print_msteps_ParametersToEstimate(MclObject pObj)'''
		<ParametersToEstimate>
			«FOR stmt: pObj.paramStructuralParams»
				«stmt.writeParameterEstimate(pObj)»
			«ENDFOR»
			«FOR stmt: pObj.paramVariabilityParams»
				«IF (stmt as ListDefinition).list.getAttributeEnumValue('type') != 'corr' && (stmt as ListDefinition).list.getAttributeEnumValue('type') != 'cov'»
					«stmt.writeParameterEstimate(pObj)»
				«ENDIF»
			«ENDFOR»
		</ParametersToEstimate>
	'''	

	protected def print_ds_TargetDataSet(MclObject mObj, MclObject dObj) {

		var res = "";
		if (dObj != null || mObj != null) {
			val s = dObj.dataSourceStmt
				// get first statement
			if (s != null){
				if(s.list.getAttributeEnumValue('inputFormat') == 'nonmemFormat') {
					var content = print_ds_NONMEM_DataSet(mObj, dObj);
					res = res + print_ds_ExternalDataSet(content, "NONMEM", BLK_DS_NONMEM_DATASET);
				}
			}
		}
		return res;
	}

	protected def print_ds_ExternalDataSet(String content, String toolName, String oid) '''
		<ExternalDataSet toolName="«toolName»" oid="«oid»">
			«content»
		</ExternalDataSet>
	'''

	var mappedColumns = new HashSet<String>
	
	private def saveMappedColumn(String colName){
		mappedColumns.add(colName)
	} 

	private def isColumnMapped(String colName){
		mappedColumns.contains(colName)
	}

	def print_ds_NONMEM_DataSet(MclObject mObj, MclObject dObj) {
		var res = "";
		for (column : dObj.dataColumnDefinitions) {
			val use = column.list.getAttributeEnumValue(ListDefinitionProvider::USE_ATT);
			switch(use){
				case(ListDefinitionProvider::ID_USE_VALUE),
				case(ListDefinitionProvider::VARLVL_USE_VALUE):{
					if(mObj.mdlVariabilityLevels.exists[name == column.name]){
						// only generate mapping if equivalent variable exists in model
						res += mObj.print_ds_MagicMapping(column)
						// record that mapping to model found
						saveMappedColumn(column.name)
					}
				}
				case(ListDefinitionProvider::IDV_USE_VALUE):{
					if(mObj.mdlIdv != null){
						res += mObj.writeIdvMapping(column)
						// record that mapping to model found
						saveMappedColumn(column.name)
					}
				}
				case(ListDefinitionProvider::COV_USE_VALUE):{
					if(isCovariateUsedInModel(column, mObj)){
						res = res + mObj.print_ds_MagicMapping(column)
						// record that mapping to model found
						saveMappedColumn(column.name)
					}
				}
				case(ListDefinitionProvider::CATCOV_USE_VALUE):{
					if(isCovariateUsedInModel(column, mObj)){
						res = res + mObj.print_ds_CategoricalMagicMapping(column)
						// record that mapping to model found
						saveMappedColumn(column.name)
					}
				}
				case(ListDefinitionProvider::AMT_USE_VALUE):{
					if(mObj.findMdlSymbolDefn(column.name) != null){
						res = res + column.print_ds_AmtMapping(dObj, mObj)
						// record that mapping to model found
						saveMappedColumn(column.name)
					}
				}
				case(ListDefinitionProvider::OBS_USE_VALUE):{
					res = res + column.print_ds_DvMapping(dObj, mObj)
				}
			}
		}
		for (column : dObj.dataDerivedColumnDefinitions) {
			val use = column.list.getAttributeEnumValue(ListDefinitionProvider::USE_ATT);
			switch(use){
				case(ListDefinitionProvider::DOSE_TIME_USE_VALUE):{
					res += column.writeDoseTimeMapping(dObj, mObj)
				}
			}
		}
		res += dObj.print_ds_DataSet(mObj);
	}


	def CharSequence writeDoseTimeMapping(ListDefinition column, MclObject dObj, MclObject mObj){
		var idvCol = column.list.getAttributeExpression(ListDefinitionProvider::IDV_COL_ATT)
		var amtCol = column.list.getAttributeExpression(ListDefinitionProvider::AMT_COL_ATT)
		var mdlDtSymb = mObj.findMdlSymbolDefn(column.name)
		'''
		<ColumnMapping>
			<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«idvCol.convertToString»"/>
			<Piecewise xmlns="«xmlns_ds»">
				<math:Piece>
					«mdlDtSymb.symbolReference»
					<math:Condition>
						<math:LogicBinop op="gt">
							<ColumnRef columnIdRef="«amtCol.convertToString»"/>
							<ct:Int>0</ct:Int>
						</math:LogicBinop>
					</math:Condition>
				</math:Piece>
			</Piecewise>
		</ColumnMapping>
		'''
	}

	def print_ds_AmtMapping(ListDefinition amtColumn, MclObject dObj, MclObject mObj)'''
		«amtColumn.print_ds_StandardAmtMapping(dObj, mObj)»
		«amtColumn.print_ds_TargetMapping(dObj, mObj)»
	'''

	def writeSingleDoseMapping(MclObject mObj, ListDefinition column, Expression dataVariable){
		var mdlSymb = mObj.findMdlSymbolDefn(dataVariable.convertToString)
		'''
		<ColumnMapping>
			<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«column.name»"/>
			<Piecewise xmlns="«xmlns_ds»">
				<math:Piece>
					«mdlSymb.symbolReference»
					<math:Condition>
						<math:LogicBinop op="gt">
							<ColumnRef columnIdRef="«column.name»"/>
							<ct:Int>0</ct:Int>
						</math:LogicBinop>
					</math:Condition>
				</math:Piece>
			</Piecewise>
		</ColumnMapping>
		'''
	}

	def writeMultiDoseMapping(MclObject mObj, ListDefinition column, Expression dataDefine){
		switch(dataDefine){
			MappingExpression:
				'''
				<ColumnMapping>
				    <ColumnRef xmlns="«xmlns_ds»" columnIdRef="«column.name»"/>
					<Piecewise xmlns="«xmlns_ds»">
						«FOR p : dataDefine.attList»
							<math:Piece>
								«mObj.findMdlSymbolDefn(p.mappedSymbol.ref.name).symbolReference»
							   	<math:Condition>
							   		<math:LogicBinop op="and">
							   			<math:LogicBinop op="eq">
											<ColumnRef columnIdRef="«p.srcColumn.ref.name»"/>
								   			«p.leftOperand.pharmMLExpr»
										</math:LogicBinop>
							   			<math:LogicBinop op="gt">
											<ColumnRef columnIdRef="«column.name»"/>
								   			<ct:Int>0</ct:Int>
										</math:LogicBinop>
									</math:LogicBinop>
							   	</math:Condition>
							</math:Piece>
						«ENDFOR» 
					</Piecewise>
				</ColumnMapping>
				'''
		  default: ''''''
		}
	}
	
	def hasNonCompartmentDosing(MclObject mdlObj, MappingExpression me){
		me.attList.exists[
			val mdlSymb = mdlObj.findMdlSymbolDefn(mappedSymbol.ref.name)
			mdlSymb instanceof ListDefinition && (mdlSymb as ListDefinition).isAdministrationMacro
		]
		false
	}
	
	protected def print_ds_StandardAmtMapping(ListDefinition amtColumn, MclObject dObj, MclObject mObj) {
		val define = amtColumn.list.getAttributeExpression('define');
		if (define == null) {
			val varDefn = amtColumn.list.getAttributeExpression('variable');
			writeSingleDoseMapping(mObj, amtColumn, varDefn)
		}
		else if(mObj.hasNonCompartmentDosing(define as MappingExpression)){
			writeMultiDoseMapping(mObj, amtColumn, define)
		}
	}

	def print_ds_TargetMapping(ListDefinition amtColumn, MclObject dObj, MclObject mObj){
		val define = amtColumn.list.getAttributeExpression('define');
		var toolMappingDefn = '''''';
		if (define != null) {
			// There really must be define in this case.
			switch(define){
				MappingExpression:
					toolMappingDefn += '''
						«FOR me : define.attList»
							«IF mObj.isCompartmentInput(me.mappedSymbol.ref)»
								«mObj.printTargetMapping(me)»
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
				<ds:ColumnRef columnIdRef="«amtColumn.name»"/>
				<ds:TargetMapping blkIdRef="sm">
					«toolMappingDefn»
				</ds:TargetMapping>
			</ColumnMapping>
			«ENDIF»
		'''
	}
	
	def boolean isAdministrationMacro(ListDefinition cmtDefn){
		val type = cmtDefn.list.getAttributeEnumValue(ListDefinitionProvider::CMT_TYPE_ATT)
		return type == 'depot' || type == 'input' || type == 'direct'
	}

	def printTargetMapping(MclObject it, MappingPair expression){
		val mdlDefn = findMdlSymbolDefn(expression.mappedSymbol.ref.name)
		'''
			<ds:Map dataSymbol="«expression.leftOperand.convertToString»" admNumber="«(mdlDefn as ListDefinition).list.getAttributeExpression('modelCmt').convertToString»"/>
«««		<ds:Map dataSymbol="«expression.leftOperand.convertToString»" admNumber="«expression.leftOperand.convertToString»"/>
		'''
	}
	
	private def print_ds_CategoricalMapping(ListDefinition column) {
		var res = "";
			val define = column.list.getAttributeExpression(ListDefinitionProvider::USE_ATT);
			// get an EnumExpression here - use this to get the categories.
			switch(define){
				EnumExpression:{
					val catDefnExpr = define.catDefn as CategoricalDefinitionExpr
					for(catVal : catDefnExpr.categories){
					res = res + '''
						<ds:Map modelSymbol="«catVal.name»" dataSymbol="«catVal.mappedTo.convertToString»"/>
						'''
					}
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

	protected def print_ds_ColumnMapping(ListDefinition column, SymbolDefinition mdlSymb, String complexMapping) '''
		<ColumnMapping>
			<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«column.name»"/>
			«mdlSymb.symbolReference»
			«IF complexMapping.length > 0»
				«complexMapping»
			«ENDIF»
		</ColumnMapping>
	'''

	def print_ds_MagicMapping(MclObject mdlObj, ListDefinition column) {
		var mdlSymb = mdlObj.findMdlSymbolDefn(column.name)
		print_ds_ColumnMapping(column, mdlSymb, "").toString
	}
	
	def writeIdvMapping(MclObject mdlObj, ListDefinition column) {
		var mdlIdvSymb = mdlObj.mdlIdv
		print_ds_ColumnMapping(column, mdlIdvSymb, "").toString
	}
	
	
	def print_ds_CategoricalMagicMapping(MclObject mdlObj, ListDefinition column){
		var mdlSymb = mdlObj.findMdlSymbolDefn(column.name)
		var categoricalMapping = column.print_ds_CategoricalMapping
		print_ds_ColumnMapping(column, mdlSymb, categoricalMapping)
	}

	def writeSingleObsMapping(MclObject mObj, ListDefinition column, Expression dataVariable){
		var mdlSymb = mObj.findMdlSymbolDefn(dataVariable.convertToString)
		'''
			<ColumnMapping>
			    <ColumnRef xmlns="«xmlns_ds»" columnIdRef="«column.name»"/>
				«mdlSymb.symbolReference»
			</ColumnMapping>
		'''
	}

	def isCategoricalObs(SymbolDefinition symb){
		if(symb instanceof ListDefinition){
			(symb as ListDefinition).list.getAttributeEnumValue(ListDefinitionProvider::OBS_TYPE_ATT) == ListDefinitionProvider::CATEGORICAL_OBS_VALUE
		}
		else false
	}

	def isDiscreteBernoulliObs(SymbolDefinition symb){
		if(symb instanceof ListDefinition){
			if((symb as ListDefinition).list.getAttributeEnumValue(ListDefinitionProvider::OBS_TYPE_ATT) == ListDefinitionProvider::DISCRETE_OBS_VALUE){
				val distnExpr =  (symb as ListDefinition).list.getAttributeExpression('distn')
				distnExpr instanceof BuiltinFunctionCall && (distnExpr as BuiltinFunctionCall).func == 'Bernoulli'
			}
		}
		else false
	}

	def writeMultipleObsMapping(MclObject mObj, ListDefinition column, Expression dataDefine){
//		var mdlSymb = mObj.getMdlObservationVariableFromCatValRef(dataDefine as CatValRefMappingExpression)
		switch(dataDefine){
			MappingExpression:
				'''
				<MultipleDVMapping>
					<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«column.name»"/>
					<Piecewise xmlns="«xmlns_mstep»">
						«FOR p : dataDefine.attList»
							«IF p.rightOperand instanceof SymbolReference»
								«IF mObj.isDefinedInMdlObservations(p.mappedSymbol)»
									<math:Piece>
									   	«mObj.findMdlSymbolDefn(p.mappedSymbol.ref.name).symbolReference»
										<math:Condition>
											<math:LogicBinop op="eq">
											<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«p.srcColumn.ref.name»"/>
												«p.leftOperand.pharmMLExpr»
									   		</math:LogicBinop>
									   	</math:Condition>
									</math:Piece>
								«ENDIF»
							«ELSEIF p.rightOperand instanceof CatValRefMappingExpression»
								«IF mObj.isDefinedInMdlObservations((p.rightOperand as CatValRefMappingExpression).attLists.head.catRef)»
									<math:Piece>
										«IF mObj.findMdlSymbolDefn((p.rightOperand as CatValRefMappingExpression).attLists.head.catRef.symbolDefnFromCatValRef.name).isCategoricalObs»
											«mObj.findMdlSymbolDefn((p.rightOperand as CatValRefMappingExpression).attLists.head.catRef.symbolDefnFromCatValRef.name).symbolReference»
											«printCategoricalObsMapping(p.rightOperand)»
«««												«ELSEIF mObj.findMdlSymbolDefn(cm.mappedTo.convertToString).isDiscreteBernoulliObs»
«««													«printDiscreteBernoulliObsMapping»
										«ENDIF»
										<math:Condition>
											<math:LogicBinop op="eq">
											<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«p.srcColumn.ref.name»"/>
												«p.leftOperand.pharmMLExpr»
									   		</math:LogicBinop>
									   	</math:Condition>
									</math:Piece>
								«ENDIF»
							«ENDIF»
						«ENDFOR» 
					</Piecewise>
				</MultipleDVMapping>
				'''
			CatValRefMappingExpression:{
				val dataCatValRef = dataDefine.attLists.head.catRef
				val mdlObsSymb =  mObj.findMdlSymbolDefn(dataCatValRef.symbolDefnFromCatValRef.name)
				'''
				«IF mObj.isDefinedInMdlObservations(dataCatValRef)»
					<ColumnMapping>
						<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«column.name»"/>
							«mdlObsSymb.symbolReference»
							«IF mdlObsSymb.isCategoricalObs»
								«printCategoricalObsMapping(dataDefine)»
«««							«ELSEIF mdlObsSymb.isDiscreteBernoulliObs»
«««								«printDiscreteBernoulliObsMapping»
							«ENDIF»
					</ColumnMapping>
				«ENDIF»
				'''
			}
			default: ''''''
		}
	}

//	def print_ds_DvMapping(ListDeclaration dvColumn, DataObject dObj, ModelObject mObj){
//		val define = dvColumn.list.arguments.getAttributeExpression(AttributeValidator::attr_define.name);
//		val columnId = dvColumn.name;
//		if (define != null) {
//			// Reference or mapped to data
//			if (define.expression != null)
//				return '''
//					<ColumnMapping>
//					    <ColumnRef xmlns="«xmlns_ds»" columnIdRef="«columnId»"/>
//						«define.expression.print_Math_Expr»
//							«IF define.expression.isCategoricalObs(mObj)»
//							«define.expression.printCategoricalObsMapping(mObj)»
//			    	   	«ELSEIF define.expression.isDiscreteBernoulliObs(mObj)»
//							«printDiscreteBernoulliObsMapping»
//			    	   	«ENDIF»
//					</ColumnMapping>
//				  '''
//			else { 
//				val pairs = define.getAttributePairs(AttributeValidator::attr_pred.name,
//					AttributeValidator::attr_predid.name);
//				val dvidColId = dObj.getUseDvidColumn
//				return '''
//					<MultipleDVMapping>
//						<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«columnId»"/>
//						<Piecewise xmlns="«xmlns_mstep»">
//							«FOR p : pairs»
//							<math:Piece>
//							   	«p.key.expression.print_Math_Expr»
//							   	«IF p.key.expression.isCategoricalObs(mObj)»
//							   		«p.key.expression.printCategoricalObsMapping(mObj)»
//							   	«ELSEIF p.key.expression.isDiscreteBernoulliObs(mObj)»
//							   		«printDiscreteBernoulliObsMapping»
//							   	«ENDIF»
//							   	<math:Condition>
//							   		<math:LogicBinop op="eq">
//							   			<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«dvidColId»"/>
//							   			«p.value.expression.print_Math_Expr»
//							   		</math:LogicBinop>
//							   	</math:Condition>
//							</math:Piece>
//					«ENDFOR» 
//						</Piecewise>
//					</MultipleDVMapping>
//				  '''
//			}
//		}
//	}

	def printCategoricalObsMapping(Expression expression){
//			val define = column.list.getAttributeExpression(ListDefinitionProvider::USE_ATT);
//			// get an EnumExpression here - use this to get the categories.
		switch(expression){
			CatValRefMappingExpression:{
				return '''
				<ds:CategoryMapping>
					«FOR catVal : expression.attLists»
						<ds:Map modelSymbol="«catVal.catRef.ref.name»" dataSymbol="«catVal.mappedTo.convertToString»"/>
					«ENDFOR»
				</ds:CategoryMapping>
				'''
			}
		}
		
//		val obsVar = expression.getSymbolReference
//		val obsExpr = object.getMatchingObservationExpression(obsVar.symbolRef.name)
//		// assume we have tested that this caregorical
//		val catsExpr = obsExpr.list.arguments.getAttributeExpression(AttributeValidator::attr_categories.name) 
//		'''
//		<ds:CategoryMapping>
//		«FOR cat : catsExpr.vector.expression.expressions»
//			<ds:Map dataSymbol="«cat.toStr»" modelSymbol="c«cat.toStr»"/>
//		«ENDFOR»
//		</ds:CategoryMapping>
//		'''
	}
	
	
//	def printDiscreteBernoulliObsMapping(){
//		val cat = "cat1"
//		val catDataValue = 1 
//		'''
//		<ds:CategoryMapping>
//			<ds:Map dataSymbol="«catDataValue»" modelSymbol="«cat»"/>
//		</ds:CategoryMapping>
//		'''
//	}

	private def isDefinedInMdlObservations(MclObject it, Expression testExpr){
		switch(testExpr){
			SymbolReference:
				mdlObservations.exists[name == testExpr?.ref.name]
			CategoryValueReference:
				mdlObservations.exists[name == testExpr?.symbolDefnFromCatValRef?.name]
			default: false
		} 
	}
	
	private def isMultiObsMappingDefinedInMdlObs(MclObject it, Expression testExpr){
		switch(testExpr){
			MappingExpression:
				for(p : testExpr.attList){
					if(mdlObservations.exists[name == p.mappedSymbol.ref.name])
						return true
				}
			CatValRefMappingExpression:
				for(p : testExpr.attLists){
					if(mdlObservations.exists[name == p.catRef.getSymbolDefnFromCatValRef.name])
						return true
				}
			default: false
		}
	}
	
	def print_ds_DvMapping(ListDefinition dvColumn, MclObject dObj, MclObject mObj){
		var CharSequence retVal = ''''''
		val variable = dvColumn.list.getAttributeExpression('variable');
		if (variable != null && mObj.isDefinedInMdlObservations(variable)) {
			// Reference or mapped to data
			retVal = writeSingleObsMapping(mObj, dvColumn, variable)
			saveMappedColumn(dvColumn.name)
		}
		else { 
			val define = dvColumn.list.getAttributeExpression(ListDefinitionProvider::DEFINE_ATT);
			if(mObj.isMultiObsMappingDefinedInMdlObs(define)){
				retVal = writeMultipleObsMapping(mObj, dvColumn, define)
				saveMappedColumn(dvColumn.name)
			}
		}
		retVal
	}

	def boolean isCovariateUsedInSublist(SubListExpression it, String covName){
		val cov = getAttributeExpression('cov') as SymbolReference
		cov != null && cov.ref.name == covName
	}

	def boolean isCatCovUsedInSublist(SubListExpression it, String covName){
		val catVal = getAttributeExpression('catCov') as CategoryValueReference
		val cov = catVal?.symbolDefnFromCatValRef
		cov != null && cov.name == covName
	}

	def isCovUsedInIndivParams(ListDefinition it, MclObject mObj){
		for(stmt : mObj.mdlIndvParams){
			switch(stmt){
				EquationTypeDefinition:{
					if(stmt.expression instanceof BuiltinFunctionCall){
						val funcExpr = stmt.expression as BuiltinFunctionCall
						if(funcExpr.func == 'linear' || funcExpr.func == 'general'){
							var namedArgList = funcExpr.argList as NamedFuncArguments 
							val fixEff = namedArgList.getArgumentExpression('fixEff') as VectorLiteral
							if(fixEff != null && !fixEff.expressions.isEmpty){
								for(e : fixEff.expressions){
									switch(e){
										VectorElement:{
											if(e.element instanceof SubListExpression &&
												((e.element as SubListExpression).isCovariateUsedInSublist(name) ||
												(e.element as SubListExpression).isCatCovUsedInSublist(name)))
													return true
										}
									}
								}
							}
						}
					}
				}
			}
		}
		false
	}
	
	def print_ds_DataSet(MclObject dObj, MclObject mObj) {
		var res = "";
		var k = 1;
		val dosingToCompartmentMacro = dObj.dataColumnDefinitions.exists[
				list.getAttributeEnumValue(ListDefinitionProvider::USE_ATT) == ListDefinitionProvider::AMT_USE_VALUE &&
				isDosingToCompartmentMacro(mObj)
		]
		for (column : dObj.dataColumnDefinitions) {
			val columnType = column.list.getAttributeEnumValue(ListDefinitionProvider::USE_ATT);
			val columnId = column.name;
			val convertedColType = switch(columnType){
				case(ListDefinitionProvider::COV_USE_VALUE),
				case(ListDefinitionProvider::CATCOV_USE_VALUE):
					if(isColumnMapped(column.name)) convertEnum(columnType, dosingToCompartmentMacro, !column.isCovUsedInIndivParams(mObj)) else "undefined"
				default:
					if(isColumnMapped(column.name)) convertEnum(columnType, dosingToCompartmentMacro, false) else 'undefined'
			}
			val valueType = column.getValueType
			res = res +
				'''
					<Column columnId="«columnId»" columnType="«convertedColType»" valueType="«valueType»" columnNum="«k»"/>
				'''
			k = k + 1;
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
	
	def isCovariateUsedInModel(ListDefinition col, MclObject mdlObj){
		mdlObj.mdlCovariateDefns.exists[name == col.name]
	}
	
	
	def convertEnum(String type, boolean isDosingToCompartmentMacro, boolean isRegressor) {
		switch (type) {
			case ListDefinitionProvider::AMT_USE_VALUE     : "dose"
			case ListDefinitionProvider::DVID_USE_VALUE   : "dvid"
			case ListDefinitionProvider::VARLVL_USE_VALUE: "occasion"
			case ListDefinitionProvider::COV_USE_VALUE,
			case ListDefinitionProvider::CATCOV_USE_VALUE:
				if(isRegressor) "reg" else "covariate"
			case ListDefinitionProvider::CMT_USE_VALUE : if(isDosingToCompartmentMacro) 'adm' else 'cmt'
			case ListDefinitionProvider::IGNORE_USE_VALUE: 'undefined'
			default: type
		}
	}
	
	def boolean isDosingToCompartmentMacro(ListDefinition amtColumn, MclObject mObj){
		val define = amtColumn.list.getAttributeExpression('define');
	
		val mappedSymbol = define.getMappedSymbolRef
		for(symb : mappedSymbol){
			if(mObj.isCompartmentInput(symb)) return true
		}
		false
	}
	

	def isCompartmentInput(MclObject it, SymbolDefinition symb){
		mdlCompartmentStatements.exists[s|
			switch(s){
				ListDefinition:{
					s.name == symb.name && s.isAdministrationMacro
				}
					
				default: false
			}
			
		]
	}

	protected def getValueType(ListDefinition dataColumn) {
		val useValue = dataColumn.list.getAttributeEnumValue(ListDefinitionProvider::USE_ATT);

		switch useValue {
			case ListDefinitionProvider::ID_USE_VALUE: Constants::TYPE_INT
			case ListDefinitionProvider::COV_USE_VALUE: Constants::TYPE_REAL
			case ListDefinitionProvider::CATCOV_USE_VALUE: Constants::TYPE_INT
			case ListDefinitionProvider::DVID_USE_VALUE: Constants::TYPE_INT
			case 'mdv': Constants::TYPE_INT
			case 'cmt': Constants::TYPE_INT
			case ListDefinitionProvider::VARLVL_USE_VALUE: Constants::TYPE_INT
			default: Constants::TYPE_REAL
		}
	}

	protected def print_ds_ExternalFile(MclObject dObj) {
		var res = "";
		val s = dObj.getDataSourceStmt
		var file = "";
		file = s.list.getAttributeExpression('file').convertToString
		if (file.length > 0) {
			res = res + '''				
				<ExternalFile oid="«BLK_DS_IMPORT_DATA»">
					<path>«file»</path>
					<format>CSV</format>
					<delimiter>COMMA</delimiter>
				</ExternalFile>
			'''
		}
		return res;
	}


	def convertTargetEnum(String enumValue){
		switch(enumValue){
			case("monolix"): "MLXTRAN_CODE"
			case("nonmem"): "NMTRAN_CODE"
			default: "ERROR!"
		}
	}

	def convertAlgoEnum(String enumValue){
		switch(enumValue){
			case("saem"): "SAEM"
			case("foce"): "FOCE"
			case("fo"): "FO"
			default: "ERROR!"
		}
	}

	def writeConfiguration(Statement stmt){
		switch(stmt){
			ListDefinition:{
				val targetExpr = stmt.list.getAttributeExpression('target')
				val versionExpr = stmt.list.getAttributeExpression('version')
				val algoExpr = stmt.list.getAttributeExpression('algo')
				val tolExpr = stmt.list.getAttributeExpression('tol')
				'''
				«IF targetExpr != null»
					«writeProperty('target', targetExpr)»
				«ENDIF»
				«IF versionExpr != null»
					«writeProperty('version', versionExpr)»
				«ENDIF»
				«IF tolExpr != null»
					«writeProperty('tol', tolExpr)»
				«ENDIF»
				«IF algoExpr != null»
					<Algorithm definition="«algoExpr.convertToString»"/>
				«ENDIF»
				'''
			}
			PropertyStatement:{
				val targetExpr = stmt.getPropertyEnumValue('target').convertTargetEnum
				val versionExpr = stmt.getPropertyExpression('version')
				val algoExpr = stmt.getPropertyEnumValue('algo').convertAlgoEnum
//				val tolExpr = stmt.getPropertyExpression('tol')
				'''
				«IF targetExpr != null»
					«writeProperty('target', targetExpr)»
				«ENDIF»
				«IF versionExpr != null»
					«writeProperty('version', versionExpr)»
				«ENDIF»
«««				«IF tolExpr != null»
«««					«writeProperty('tol', tolExpr)»
«««				«ENDIF»
				«IF algoExpr != null»
					<Algorithm definition="«algoExpr»"/>
				«ENDIF»
				'''
			}
		}
	}
	
	def writeProperty(String propName, Expression value)'''
		<Property name="«propName»">
			<ct:Assign>
				«value.pharmMLExpr»
			</ct:Assign>	
		</Property>
	'''
	
	def writeProperty(String propName, String exprStr){
		val strExpr = MdlFactory::eINSTANCE.createStringLiteral
		strExpr.value = exprStr
		writeProperty(propName, strExpr) 
	}

	protected def print_msteps_EstimateOperations(MclObject tObj, Integer order)'''
		«FOR b: tObj.blocks»
			«IF b.identifier == "ESTIMATE"»
				<Operation order="«order»" opType="«OPERATION_EST_POP»">
					«FOR s: b.nonBlockStatements»
						«writeConfiguration(s)»
					«ENDFOR»
				</Operation>
			«ENDIF»
		«ENDFOR»
	'''



	///////////////////////////////////////////////
	// III.b Simulation Step
	////////////////////////////////////////////////
//	protected def print_msteps_SimulationStep(String stepId, Integer order, MclObject mObj, MclObject dObj, MclObject pObj, MclObject tObj)'''
//		<SimulationStep  oid="«stepId»">
//			«dObj.print_mdef_ExternalDataSetReference»
//«««			«mObj.print_ct_variableAssignment»
//		</SimulationStep>
//	'''
	
//	///////////////////////////////////////////////
//	//General
//	///////////////////////////////////////////////	
//	protected def print_ct_variableAssignment(MclObject mObj){
//		//For covariates that are not transformations but have expression
//		var res = "";
//		for (b: mObj.blocks){
//			if (b.covariateBlock != null){
//				for (s: b.covariateBlock.variables){
//					if (s.symbolName != null && s.expression != null){
//						if (cm_assigned_vars.contains(s.symbolName.name)){
//							res = '''
//								<ct:VariableAssignment>
//									«s.symbolName.print_ct_SymbolName»
//									«s.expression.print_Assign»
//								</ct:VariableAssignment>
//							'''
//						}
//					}
//				}
//			}
//		}
//		return res;
//	}
	
}