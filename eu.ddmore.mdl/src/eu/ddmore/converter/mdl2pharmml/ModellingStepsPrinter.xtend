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

class ModellingStepsPrinter { 
	
	extension MclUtils mu = new MclUtils 
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension ListDefinitionProvider ldp = new ListDefinitionProvider

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
«««			«dObj.print_mdef_ExternalDataSetReference»
			«pObj.print_msteps_ParametersToEstimate»
			«tObj.print_msteps_EstimateOperations(order)»
«««			«mObj.print_ct_variableAssignment»
		</EstimationStep>
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
				«stmt.writeParameterEstimate(pObj)»
			«ENDFOR»
«««				«IF b.structuralBlock != null»
«««					«FOR p: b.structuralBlock.parameters»
«««						«p.print_msteps_ParameterEstimation»
«««					«ENDFOR»
«««				«ENDIF»
«««				«IF b.variabilityBlock != null»
«««					«FOR p: b.variabilityBlock.parameters»
«««						«p.print_msteps_ParameterEstimation»
«««					«ENDFOR»
«««				«ENDIF»
«««			«ENDFOR»
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
//				} else {
//					var content = print_ds_DataSet(dObj, mObj);
//					res = res + print_ds_ExternalDataSet(content, "Monolix", BLK_DS_MONOLIX_DATASET);
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

	def print_ds_NONMEM_DataSet(MclObject mObj, MclObject dObj) {
		var res = "";
		for (column : dObj.dataColumnDefinitions) {
			val use = column.list.getAttributeEnumValue(ListDefinitionProvider::USE_ATT);
			switch(use){
				case(ListDefinitionProvider::ID_USE_VALUE),
				case(ListDefinitionProvider::IDV_USE_VALUE),
				case(ListDefinitionProvider::VARLVL_USE_VALUE):
					res = res + mObj.print_ds_MagicMapping(column)
				case(ListDefinitionProvider::COV_USE_VALUE):
					if(isUsedInModel(column, mObj))
						res = res + mObj.print_ds_MagicMapping(column)
				case(ListDefinitionProvider::CATCOV_USE_VALUE):{
					if(isUsedInModel(column, mObj))
						res = res + mObj.print_ds_CategoricalMagicMapping(column)
//					var categoricalMapping = column.print_ds_CategoricalMapping
//					// @TODO: fix this
//					res = res + mObj.print_ds_ColumnMapping(column, categoricalMapping)
				}
				case(ListDefinitionProvider::AMT_USE_VALUE):
					res = res + column.print_ds_AmtMapping(dObj, mObj)
				case(ListDefinitionProvider::OBS_USE_VALUE):
					res = res + column.print_ds_DvMapping(dObj, mObj)
			}
		}
		res = res + dObj.print_ds_DataSet(mObj);
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
//				'''
//				<MultipleDVMapping>
//					<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«column.name»"/>
//					<Piecewise xmlns="«xmlns_mstep»">
//						«FOR p : dataDefine.attList»
//							<math:Piece>
//							   	«mObj.findMdlSymbolDefn(p.mappedSymbol.ref.name).symbolRef»
//«««						   	«IF p.key.expression.isCategoricalObs(mObj)»
//«««						   		«p.key.expression.printCategoricalObsMapping(mObj)»
//«««						   	«ELSEIF p.key.expression.isDiscreteBernoulliObs(mObj)»
//«««						   		«printDiscreteBernoulliObsMapping»
//«««						   	«ENDIF»
//								<math:Condition>
//									<math:LogicBinop op="eq">
//									<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«p.srcColumn.ref.name»"/>
//										«p.leftOperand.pharmMLExpr»
//							   		</math:LogicBinop>
//							   	</math:Condition>
//							</math:Piece>
//						«ENDFOR» 
//					</Piecewise>
//				</MultipleDVMapping>
//				'''
		  default: ''''''
		}
	}
	protected def print_ds_StandardAmtMapping(ListDefinition amtColumn, MclObject dObj, MclObject mObj) {
		val define = amtColumn.list.getAttributeExpression('define');
//		val columnId = amtColumn.name;
//		var res = '''''';
//		var colMapping = ''''''
		if (define == null) {
			val varDefn = amtColumn.list.getAttributeExpression('variable');
			writeSingleDoseMapping(mObj, amtColumn, varDefn)
			// Reference or piecewise
//			res = '''
//				<ColumnMapping>
//					<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«columnId»"/>
//					<Piecewise xmlns="«xmlns_ds»">
//						<math:Piece>
//							«IF varDefn != null»
//								«varDefn.pharmMLExpr»
//							«ENDIF»
//							<math:Condition>
//								<math:LogicBinop op="gt">
//									<ColumnRef columnIdRef="«columnId»"/>
//									<ct:Int>0</ct:Int>
//								</math:LogicBinop>
//							</math:Condition>
//						</math:Piece>
//					</Piecewise>
//				</ColumnMapping>
//				'''
		}
		else { // Vector of pairs
			writeMultiDoseMapping(mObj, amtColumn, define)
//			colMapping = switch(define){
//				MappingExpression:{
//					'''
//					«FOR p : define.attList»
//						«IF !mObj.isCompartmentInput(p.mappedSymbol.ref)»
//							<math:Piece>
//								«p.rightOperand.pharmMLExpr»
//							   	<math:Condition>
//							   		<math:LogicBinop op="and">
//							   			<math:LogicBinop op="eq">
//											<ColumnRef columnIdRef="«p.srcColumn.ref.name»"/>
//								   			«p.leftOperand.pharmMLExpr»
//										</math:LogicBinop>
//							   			<math:LogicBinop op="gt">
//											<ColumnRef columnIdRef="«columnId»"/>
//								   			<ct:Int>0</ct:Int>
//										</math:LogicBinop>
//									</math:LogicBinop>
//							   	</math:Condition>
//							</math:Piece>
//						«ENDIF»
//					«ENDFOR» 
//					'''
//				}
//				default:''
			}
//			val colMapping = '''
//				«FOR p : pairs»
//				«IF !mObj.isCompartmentInput(p)»
//					<math:Piece>
//						«p.name.localSymbolReference»
//					   	<math:Condition>
//					   		<math:LogicBinop op="and">
//					   			<math:LogicBinop op="eq">
//									<ColumnRef columnIdRef="«cmtCols.head.name»"/>
//						   			«p.symbolRef»
//								</math:LogicBinop>
//					   			<math:LogicBinop op="gt">
//									<ColumnRef columnIdRef="«columnId»"/>
//						   			<ct:Int>0</ct:Int>
//								</math:LogicBinop>
//							</math:LogicBinop>
//					   	</math:Condition>
//					</math:Piece>
//				«ENDIF»
//				«ENDFOR» 
//			  '''
//			 res = '''
//				«IF colMapping.length > 0»
//				<ColumnMapping>
//				    <ColumnRef xmlns="«xmlns_ds»" columnIdRef="«columnId»"/>
//					<Piecewise xmlns="«xmlns_ds»">
//						«colMapping»
//					</Piecewise>
//				</ColumnMapping>
//				«ENDIF»
//			 '''
//		}
//		res
	}

	def print_ds_TargetMapping(ListDefinition amtColumn, MclObject dObj, MclObject mObj){
		// @TODO : fix this.
		''''''
//		val define = amtColumn.list.getAttributeExpression('define');
//		val columnId = amtColumn.name;
//		var toolMappingDefn = '''''';
//		if (define != null) {
//			// There really must be define in this case.
//			val pairs = define.mappedSymbolRef
//			toolMappingDefn = '''
//			    «FOR p : pairs»
//		    	   	«IF mObj.isCompartmentInput(p)»
//«««		    	   		«p.printTargetMapping(p.value.expression, mObj)»
//		    	   	«ENDIF»
//				«ENDFOR» 
//			'''
//		} else {
//			// this is a bug as the language will be invalid if this is true.
//		}
//		'''
//			«IF toolMappingDefn.length > 0»
//			<ColumnMapping>
//				<ds:ColumnRef columnIdRef="«columnId»"/>
//				<ds:TargetMapping blkIdRef="sm">
//					«toolMappingDefn»
//				</ds:TargetMapping>
//			</ColumnMapping>
//			«ENDIF»
//		'''
	}

	// @TODO: implement this	
	def printTargetMapping(Expression expression, Expression valExpr, MclObject mObj)'''
«««		«FOR block : mObj.getMdlCompartmentStatements»
«««			«IF block.modelPredictionBlock != null»
«««				«FOR ModelPredictionBlockStatement stmt : block.modelPredictionBlock.statements»
«««					«IF stmt.pkMacroBlock != null »
«««						«FOR pkstmt : stmt.pkMacroBlock.statements»
«««							«IF pkstmt.variable != null && pkstmt.variable.isAdministrationMacro»
«««								<ds:Map dataSymbol="«valExpr.toStr»" admNumber="«getAdmValue(pkstmt.variable.list)»"/>
«««							«ENDIF»
«««						«ENDFOR»
«««					«ENDIF»
«««				«ENDFOR»
«««			«ENDIF»
«««		«ENDFOR»
	'''
	
	protected def print_ds_CategoricalMapping(ListDefinition column) {
		var res = "";
		// @TODO implement this
//		if (column.list != null) {
//			val type = column.list.arguments.getAttributeExpression(AttributeValidator::attr_type.name);
//			if(!type.isCategorical) return "";
//			val define = column.list.arguments.getAttributeExpression(AttributeValidator::attr_define.name);
//			if (define != null) {
//				var pairs = define.getAttributePairs(AttributeValidator::attr_category.name,
//					AttributeValidator::attr_value.name);
//				for (pair : pairs)
//					res = res + '''
//						<ds:Map modelSymbol="«pair.key.toStr»" dataSymbol="«pair.value.toStr»"/>
//					''';
//			}
//		}
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
«««						«IF define.isCategoricalObs(mObj)»
«««							«define.expression.printCategoricalObsMapping(mObj)»
«««			    	   	«ELSEIF define.expression.isDiscreteBernoulliObs(mObj)»
«««							«printDiscreteBernoulliObsMapping»
«««			    	   	«ENDIF»
			</ColumnMapping>
		'''
	}

	def writeMultipleObsMapping(MclObject mObj, ListDefinition column, Expression dataDefine){
		switch(dataDefine){
			MappingExpression:
				'''
				<MultipleDVMapping>
					<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«column.name»"/>
					<Piecewise xmlns="«xmlns_mstep»">
						«FOR p : dataDefine.attList»
							<math:Piece>
							   	«mObj.findMdlSymbolDefn(p.mappedSymbol.ref.name).symbolReference»
«««						   	«IF p.key.expression.isCategoricalObs(mObj)»
«««						   		«p.key.expression.printCategoricalObsMapping(mObj)»
«««						   	«ELSEIF p.key.expression.isDiscreteBernoulliObs(mObj)»
«««						   		«printDiscreteBernoulliObsMapping»
«««						   	«ENDIF»
								<math:Condition>
									<math:LogicBinop op="eq">
									<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«p.srcColumn.ref.name»"/>
										«p.leftOperand.pharmMLExpr»
							   		</math:LogicBinop>
							   	</math:Condition>
							</math:Piece>
						«ENDFOR» 
					</Piecewise>
				</MultipleDVMapping>
				'''
		  default: ''''''
		}
	}

	def print_ds_DvMapping(ListDefinition dvColumn, MclObject dObj, MclObject mObj){
		val variable = dvColumn.list.getAttributeExpression('variable');
		if (variable != null) {
			// Reference or mapped to data
			writeSingleObsMapping(mObj, dvColumn, variable)
		}
		else { 
			val define = dvColumn.list.getAttributeExpression(ListDefinitionProvider::DEFINE_ATT);
			writeMultipleObsMapping(mObj, dvColumn, define)
		}
	}

	
	def print_ds_DataSet(MclObject dObj, MclObject mObj) {
		var res = "";
		var k = 1;
		for (column : dObj.dataColumnDefinitions) {
			val columnType = column.list.getAttributeEnumValue(ListDefinitionProvider::USE_ATT);
			var dosingToCompartmentMacro = false;
			val columnId = column.name;
			if(columnType == ListDefinitionProvider::AMT_USE_VALUE){
				dosingToCompartmentMacro = column.isDosingToCompartmentMacro(mObj)
			}
			var convertedColType = switch(columnType){
				case(ListDefinitionProvider::COV_USE_VALUE),
				case(ListDefinitionProvider::CATCOV_USE_VALUE):
					if(isUsedInModel(column, mObj)) convertEnum(columnType, dosingToCompartmentMacro) else "undefined"
				default:
					convertEnum(columnType, dosingToCompartmentMacro)
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
	
	def isUsedInModel(ListDefinition col, MclObject mdlObj){
		mdlObj.findMdlSymbolDefn(col.name) != null
	}
	
	
	def convertEnum(String type, boolean isDosingToCompartmentMacro) {
		switch (type) {
			case ListDefinitionProvider::AMT_USE_VALUE     : "dose"
			case ListDefinitionProvider::DVID_USE_VALUE   : "dvid"
			case ListDefinitionProvider::VARLVL_USE_VALUE: "occasion"
			case ListDefinitionProvider::CMT_USE_VALUE : if(isDosingToCompartmentMacro) 'adm' else 'cmt'
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

//	protected def print_msteps_ParameterEstimation(SymbolDeclaration s){
//		if (s.name != null && s.list != null) {
//			//Skip correlation definitions
//			val type = s.list.arguments.getAttribute(AttributeValidator::attr_type.name);
//			if (type.equals(VariabilityType::COV.toString) || type.equals(VariabilityType::CORR.toString)) return "";
//	
//			val fixed = s.list.arguments.isAttributeTrue(AttributeValidator::attr_fix.name);
//			var value = s.list.arguments.getAttributeExpression(AttributeValidator::attr_value.name);
//			var lo = s.list.arguments.getAttributeExpression(AttributeValidator::attr_lo.name);
//			var hi = s.list.arguments.getAttributeExpression(AttributeValidator::attr_hi.name);
////			var estimate = "0".print_ct_Value;
////			if (value != null)
////				estimate = value.print_Math_Expr.toString();
//			'''
//				<ParameterEstimation>
//					«print_ct_SymbolRef(s.name)»
//					<InitialEstimate fixed="«fixed»">
//							«value.print_Math_Equation»
//					</InitialEstimate>
//					«IF lo != null»
//						<LowerBound>
//							«lo.print_Math_Equation»
//						</LowerBound>
//					«ENDIF»
//					«IF hi != null»
//						<UpperBound>
//							«hi.print_Math_Equation»
//						</UpperBound>
//					«ENDIF»
//				</ParameterEstimation>
//			'''
//		}
//	}
//	
//	protected def print_msteps_EstimateOperations(MclObject tObj, Integer order)'''
//		«FOR b: tObj.blocks»
//			«IF b.estimateBlock != null»
//				<Operation order="«order»" opType="«OPERATION_EST_POP»">
//					«FOR s: b.estimateBlock.statements»
//						«s.print_msteps_Property»
//					«ENDFOR»
//					«FOR s: b.estimateBlock.statements»
//						«s.print_msteps_Algorithm»
//					«ENDFOR»
//				</Operation>
//			«ENDIF»
//		«ENDFOR»
//	'''

	def print_msteps_EstimateOperations(MclObject tObj, Integer order)'''
		<Operation order="1" opType="estPop">
			<Property name="target">
				<ct:Assign>
					<ct:String>MLXTRAN_CODE</ct:String>
				</ct:Assign>	
			</Property>
			<Property name="version">
				<ct:Assign>
					<ct:String>4.3.2</ct:String>
				</ct:Assign>	
			</Property>
			<Algorithm definition="SAEM"/>
		</Operation>
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
	
//	protected def print_msteps_Property(PropertyDeclaration s)'''
//		«IF s.propertyName != null && s.expression != null»
//			«IF !s.propertyName.argName.equals(PropertyValidator::attr_task_algo.name)»
//				<Property name="«s.propertyName.argName»">
//					«s.expression.print_Assign»
//				</Property>
//			«ENDIF»	
//		«ENDIF»
//	'''
//	
//	protected def print_msteps_Algorithm(PropertyDeclaration s)'''
//		«IF s.propertyName != null && s.expression != null»
//			«IF s.propertyName.argName.equals(PropertyValidator::attr_task_algo.name)»
//				«IF s.expression.vector != null && s.expression.vector.expression != null &&
//					s.expression.vector.expression.expressions != null »
//					«FOR algoName: s.expression.vector.expression.expressions»
//						«IF algoName != null»
//							<Algorithm definition="«algoName.toStr»"/>
//						«ENDIF»
//					«ENDFOR»
//				«ENDIF»
//			«ENDIF»	
//		«ENDIF»
//	'''
}