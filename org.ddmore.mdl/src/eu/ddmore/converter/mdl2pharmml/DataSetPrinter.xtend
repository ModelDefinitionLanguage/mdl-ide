package eu.ddmore.converter.mdl2pharmml

import java.util.ArrayList
import org.ddmore.mdl.validation.AttributeValidator
import org.apache.commons.io.FilenameUtils
import org.ddmore.mdl.mdl.SymbolDeclaration
import static extension eu.ddmore.converter.mdl2pharmml.Constants.*
import org.ddmore.mdl.validation.PropertyValidator
import org.ddmore.mdl.mdl.InputFormatType
import org.ddmore.mdl.mdl.UseType
import org.ddmore.mdl.mdl.ModelObject
import org.ddmore.mdl.mdl.DataObject
import eu.ddmore.converter.mdlprinting.MdlPrinter
import org.ddmore.mdl.mdl.MOGObject
import org.ddmore.mdl.validation.Utils
import org.ddmore.mdl.mdl.impl.SymbolDeclarationImpl
import org.ddmore.mdl.mdl.impl.DataInputBlockImpl

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

	protected def print_ds_DvMapping(SymbolDeclaration dvColumn, DataObject dObj) {
		val define = dvColumn.list.arguments.getAttributeExpression(AttributeValidator::attr_define.name);
		val columnId = dvColumn.symbolName.name;
		var res = '';
		if (define != null) {
			// Reference or piecewise
			if (define.expression != null)
				res = columnId.print_ds_ColumnMapping(null, define.expression.print_Math_Expr.toString).toString
			else { // Vector of pairs
				val pairs = define.getAttributePairs(AttributeValidator::attr_pred.name,
					AttributeValidator::attr_predid.name);
				val dvidColId = dObj.getUseDvidColumn
				res = '''
					<MultipleDVMapping>
					    <ColumnRef xmlns="«xmlns_ds»" columnIdRef="«columnId»"/>
						<Piecewise xmlns="«xmlns_mstep»">
						    «FOR p : pairs»
						    	<math:Piece>
						    	   	«p.key.expression.print_Math_Expr»
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
		} else {
			// this is a bug as the language will be invalid if this is true.
		}
		res
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
								var content = dObj.print_ds_DataSet;
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
					val modelVar = getDefaultMatchingVariable(mog, column, mObj);
					if (modelVar != null && blockContainer instanceof DataInputBlockImpl) {
						// } || blockContainer instanceof DataDerivedBlockImpl){
						val use = column.list.arguments.getAttribute(AttributeValidator::attr_use.name);
						val colType = column.list.arguments.getAttributeExpression(AttributeValidator::attr_type.name);
						if (use.equals(UseType::ID.toString) || use.equals(UseType::IDV.toString) ||
							use.equals(UseType::VARLEVEL.toString) || (use.equals(UseType::COVARIATE.toString) &&
								!colType.isCategorical())) {
								// use magic mapping with no conditions 
								res = res + column.symbolName.name.print_ds_MagicMapping(modelVar);
							} else if (use.equals(UseType::COVARIATE.toString) && colType.isCategorical()) {
								// categorical covariates
								var categoricalMapping = column.print_ds_CategoricalMapping;
								res = res + column.symbolName.name.print_ds_ColumnMapping(modelVar, categoricalMapping);
							} else if (use.equals(UseType::AMT.toString)) {
								// handle amount mapping
								res = res + column.print_ds_AmtMapping(dObj)
							} else if (use.equals(UseType::DV.toString)) {
								// handle amount mapping
								res = res + column.print_ds_DvMapping(dObj)
							}
						}
					}
				}
			}
			res = res + dObj.print_ds_DataSet;
		}

		protected def print_ds_AmtMapping(SymbolDeclaration amtColumn, DataObject dObj) {
			val define = amtColumn.list.arguments.getAttributeExpression(AttributeValidator::attr_define.name);
			val columnId = amtColumn.symbolName.name;
			var res = '';
			if (define != null) {
				// Reference or piecewise
				if (define.expression != null)
					res = columnId.print_ds_ColumnMapping(null, define.expression.print_Math_Expr.toString).toString
				else { // Vector of pairs
					val pairs = define.getAttributePairs(AttributeValidator::attr_modelCmt.name,
						AttributeValidator::attr_dataCmt.name);
					val cmtColId = dObj.getUseCmtColumn
					res = '''
						<ColumnMapping>
						    <ColumnRef xmlns="«xmlns_ds»" columnIdRef="«columnId»"/>
							<Piecewise xmlns="«xmlns_ds»">
							    «FOR p : pairs»
							    	<math:Piece>
							    	   	«p.key.expression.print_Math_Expr»
							    	   	<math:Condition>
							    	   		<math:LogicBinop op="eq">
							    	   			<ColumnRef columnIdRef="«cmtColId»"/>
							    	   			«p.value.expression.print_Math_Expr»
							    	   		</math:LogicBinop>
							    	   	</math:Condition>
							    	</math:Piece>
							«ENDFOR» 
							</Piecewise>
						</ColumnMapping>
					  '''
				}
			} else {
				// this is a bug as the language will be invalid if this is true.
			}
			res
		}

		protected def print_ds_MagicMapping(String columnId, String symbId) {
			columnId.print_ds_ColumnMapping(symbId, "").toString
		}

//	protected def print_ds_AttributeMapping(SymbolDeclaration column){
//		if (column.symbolName != null && column.list != null){
//			var columnId = column.symbolName.name;
//			val use = column.list.arguments.getAttribute(AttributeValidator::attr_use.name);
//			var define = column.list.arguments.getAttributeExpression(AttributeValidator::attr_define.name);
//			if (define != null){
//				if (use.equals(UseType::AMT.toString)){
//					//Reference or piecewise
//					if (define.expression != null)
//						return columnId.print_ds_ColumnMapping(null, define.expression.print_Math_Expr.toString)
//					else {//Vector of pairs
//						var pairs = define.getAttributePairs(AttributeValidator::attr_modelCmt.name, AttributeValidator::attr_dataCmt.name);
//						for (pair: pairs){
//							// @TODO:
//						}
//					}
//				}	
//				if (use.equals(UseType::DV.toString)){
//					//Piecewise function
//					if (define.expression != null && define.expression.whenBranches != null)
//						//return columnId.print_ds_ColumnMapping(null, define.expression.print_Math_Expr.toString)
//						return columnId.print_ds_MultipleDVMapping(define.expression)
//					else {//Vector of pairs
//						var pairs = define.getAttributePairs(AttributeValidator::attr_pred.name, AttributeValidator::attr_predid.name);
//						for (pair: pairs){
//							// @TODO:
//						}
//					}						
//				}
//			}
//		}
//	}	
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

		// Return a model variable (matched by name or in the MOG MAPPING block)
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
									return s.symbolName.name;
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

		protected def print_ds_DataSet(DataObject dObj) {
			var res = "";
			var k = 1;
			for (b : dObj.blocks) {
				if (b.dataInputBlock != null) {
					for (i : 0 .. b.dataInputBlock.variables.size - 1) {
						val column = b.dataInputBlock.variables.get(i);
						val columnId = column.symbolName.name;
						val columnType = column.getColumnType
						var convertedColType = "undefined"
						if (columnType != null) {
							convertedColType = columnType.convertEnum;
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
