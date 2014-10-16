package eu.ddmore.converter.mdl2pharmml

import java.util.ArrayList
import org.ddmore.mdl.validation.AttributeValidator
import org.apache.commons.io.FilenameUtils
import org.ddmore.mdl.mdl.SymbolDeclaration
import static extension eu.ddmore.converter.mdl2pharmml.Constants.*
import org.ddmore.mdl.validation.PropertyValidator
import org.ddmore.mdl.mdl.InputFormatType
import org.ddmore.mdl.mdl.UseType
import org.ddmore.mdl.mdl.MclObject
import org.ddmore.mdl.mdl.ModelObject

class DataSetPrinter {
	protected extension MathPrinter mathPrinter = null;
	protected extension ReferenceResolver resolver=null;
	
	new(MathPrinter mathPrinter, ReferenceResolver resolver){
		this.mathPrinter = mathPrinter;
		this.resolver = resolver;
	}	
	
	protected def print_ds_Table(ArrayList<String> rows)'''
		<ds:Table>
			«FOR i: 0..rows.size-1»
				«rows.get(i)»
			«ENDFOR»
		<ds:Table>
	'''
	
	protected def print_ds_Row(ArrayList<String> values){
		return '''<Row>«FOR i: 0..values.size-1»«values.get(i).print_ct_Value»«ENDFOR»</Row>''';
	}
	
	protected def print_ds_Column(String columnId, String columnType, String columnNum)'''
		<Column columnId="«columnId»" columnType="«columnType.convertColumnType»" valueType="«columnId.getValueType»" columnNum="«columnNum»"/>
	'''
	
	protected def print_ds_ColumnMapping(String columnId, String symbId)'''
		<ColumnMapping>
			<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«columnId»"/>
			«symbId.print_ct_SymbolRef»
		</ColumnMapping>
	'''
		
	 //+ Print data set
	protected def print_ds_TargetDataSet(MclObject mObj, MclObject dObj){
		if (dObj.dataObject == null || mObj.modelObject == null) return "";
		var res = "";
		for (b: dObj.dataObject.blocks)	{
			if (b.sourceBlock != null){
				for (s: b.sourceBlock.statements){
					if (s.propertyName.name.equals(PropertyValidator::attr_inputformat.name) && s.expression != null){
						if (s.expression.toStr.equals(InputFormatType::NONMEM_FORMAT.toString)){
							res  = res + print_ds_NONMEM_DataSet(mObj, dObj);
						} else {
							res = res + print_ds_Objective_DataSet(mObj, dObj);
						}					
					}
				}
			}
		}
		return res;
	}
	
	protected def getColumnType(SymbolDeclaration modelVar){
		var columnType = UseType::ID.toString;
		if (modelVar.list != null){
			val useValue = modelVar.list.arguments.getAttribute(AttributeValidator::attr_use.name);
			if (useValue.length > 0)  columnType = useValue;
		}
		return columnType;
	}
	
	protected def print_ds_Objective_DataSet(MclObject mObj, MclObject dObj){
		var res = print_ds_DataSet(mObj, dObj);
		'''
		<ObjectiveDataSet>
			«res»
		</ObjectiveDataSet>
		'''
	}
	
	//Return a model variable (matched by name or by alias name!)
	protected def getModelInputVariable(ModelObject mObj, String name){
		for (b: mObj.blocks){
			if (b.inputVariablesBlock != null){
				for (s: b.inputVariablesBlock.variables){
					if (s.symbolName != null && s.symbolName.name.equals(name)){
						return s;
					}
					if (s.list != null){
						var alias = s.list.arguments.getAttribute(AttributeValidator::attr_alias.name);
						if (alias.length > 0){
							if (alias.equals(name)) return s;
						}
					}
				}
			}
		}
		return null;
	}
	
	protected def print_ds_NONMEM_DataSet(MclObject mObj, MclObject dObj){
		if (dObj.dataObject == null || mObj.modelObject == null) return "";
		var res = "";
		for (b: dObj.dataObject.blocks){
			if (b.dataInputBlock != null){
				for (s: b.dataInputBlock.variables){
					var columnId = s.symbolName.name;
					val modelVar = mObj.modelObject.getModelInputVariable(columnId);
					if (modelVar != null){
						res = res + print_ds_ColumnMapping(columnId, modelVar.symbolName.name);
					}
				}
			}
			if (b.dataDerivedBlock != null){
				var derivedVars = b.dataDerivedBlock.getDerivedVariables;
				for (s: derivedVars){
					val modelVar = mObj.modelObject.getModelInputVariable(s);
					if (modelVar != null){
						res = res + print_ds_ColumnMapping(s, modelVar.symbolName.name);
					}
				}
			}
		}
		res = res + print_ds_DataSet(mObj, dObj);
		'''
		<NONMEMdataSet oid="«BLK_DS_NONMEM_DATASET + dObj.objectName.name»">
			«res»
		</NONMEMdataSet>
		'''
	}
	
	protected def print_ds_DataSet(MclObject mObj, MclObject dObj){
		if (dObj.dataObject == null || mObj.modelObject == null) return "";
		var columnNames = new ArrayList<String>();
		var columnTypes = new ArrayList<String>();
		for (b: dObj.dataObject.blocks){
			if (b.dataInputBlock != null){
				for (s: b.dataInputBlock.variables){
					var columnId = s.symbolName.name;
					val modelVar = mObj.modelObject.getModelInputVariable(columnId);
					if (modelVar != null && modelVar.symbolName != null){
						if (!columnNames.contains(modelVar.symbolName.name)){
							columnNames.add(modelVar.symbolName.name);
							columnTypes.add(modelVar.getColumnType);
						}
					}
				}
			}
			if (b.dataDerivedBlock != null){
				var derivedVars = b.dataDerivedBlock.getDerivedVariables;
				for (s: derivedVars){
					val modelVar = mObj.modelObject.getModelInputVariable(s);
					if (modelVar != null && modelVar.symbolName != null){
						if (!columnNames.contains(modelVar.symbolName.name)){
							columnNames.add(modelVar.symbolName.name);
							columnTypes.add(modelVar.getColumnType);
						}
					}
				}
			}	
		}	
		'''
			<DataSet xmlns="«xmlns_ds»">
				«print_ds_Definition(columnNames, columnTypes)»
				«dObj.print_ds_ImportData»
			</DataSet>
		'''
	}
	
	protected def print_ds_Definition(ArrayList<String> columnNames, ArrayList<String> columnTypes)'''
		<Definition>
			«FOR i: 0..columnNames.size-1»
				«var columnId = columnNames.get(i)»
				«print_ds_Column(columnId, columnTypes.get(i), (i+1).toString)»
			«ENDFOR»
		</Definition>
	'''
	
	protected def print_ds_ImportData(MclObject dObj){
		if (dObj.dataObject == null) return "";
		var res = "";
		for (b: dObj.dataObject.blocks){
			if (b.sourceBlock != null){
				if (b.sourceBlock.inlineBlock == null){
					var file = "";
					var delimiter = "";
					for (s: b.sourceBlock.statements){
						if (s.propertyName.name.equals(PropertyValidator::attr_file.name) && s.expression != null){
							file = s.expression.toStr;
						}
						if (s.propertyName.name.equals(PropertyValidator::attr_delimiter.name) && s.expression != null){
							delimiter = s.expression.toStr;							
						}
					}
					if (file.length > 0){
						if (delimiter.length == 0) delimiter = PropertyValidator::attr_delimiter.defaultValue;
						val fileName = FilenameUtils::getBaseName(file);
						val fileExtension = FilenameUtils::getExtension(file);
						res = res +
						'''				
							<ImportData oid="«BLK_DS_IMPORT_DATA + dObj.objectName.name»">
								<name>«fileName»</name>
								<url>«file»</url>
								<format>«fileExtension.convertFileFormat»</format>
								<delimiter>«delimiter.convertDelimiter»</delimiter>
							</ImportData>
						'''
					}
				} else {
					//Process INLINE block
					var rows = new ArrayList<String>();
					var columns = new ArrayList<String>();
					if (b.sourceBlock.inlineBlock.variables != null){
						for (value: b.sourceBlock.inlineBlock.variables.identifiers){
							columns.add(value.name);
						}
					}
					var rowLength = columns.size;
					if (rowLength > 0){
						rows.add(columns.print_ds_Row);
						var values = new ArrayList<String>();
						if (b.sourceBlock.inlineBlock.values != null){
							var i = 0;
							for (value: b.sourceBlock.inlineBlock.values.values){
								if (!value.equals(".")) 
									values.add(value)
								else values.add("0");	
								i = i + 1;
								if (i == rowLength){
									rows.add(values.print_ds_Row);
									values.clear;
									i = 0;
								}
							}
						}
						res  = res + rows.print_ds_Table;
					}
				}
			}
		}
		return res;
	}
	
	protected def print_ds_TargetToolData(String dObjName, String source){		
		val fileName = FilenameUtils::getBaseName(source);
		val filePath = FilenameUtils::getFullPath(source);
		'''
		<ds:TargetToolData>
			<ds:ImportTargetData oid="«BLK_DS_TARGET_TOOL_DATA + dObjName»">
				<ds:name>«fileName»</ds:name>
				<ds:url>«filePath»</ds:url>
			</ds:ImportTargetData>
		</ds:TargetToolData>
		'''
	}
	
	protected def print_ds_TargetTool(MclObject dObj){
		if (dObj.dataObject == null) return "";
		val source = dObj.dataObject.getScriptFile;
		if (source.length == 0) return "";
		val fileExtension = FilenameUtils::getExtension(source);
		if (fileExtension.length == 0) return "";
		'''
		<TargetTool oid="«BLK_DS_TARGET_TOOL + dObj.objectName.name»">
			<TargetToolName>«fileExtension.convertID»</TargetToolName>
			«dObj.objectName.name.print_ds_TargetToolData(source)»
		</TargetTool>
		'''
	}
	
	protected def print_mdef_TargetToolReference(MclObject dObj)
	'''
		<TargetToolReference>
			<ct:OidRef oidRef="«BLK_DS_TARGET_TOOL + dObj.objectName.name»"/>
		</TargetToolReference>
	'''
}
