package eu.ddmore.converter.mdl2pharmml

import java.util.ArrayList
import org.ddmore.mdl.validation.AttributeValidator
import org.apache.commons.io.FilenameUtils
import org.ddmore.mdl.types.InputFormatType
import org.ddmore.mdl.types.DefaultValues
import org.ddmore.mdl.mdl.SymbolDeclaration
import static extension eu.ddmore.converter.mdl2pharmml.Constants.*

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
		<Column columnId="«columnId»" columnType="«columnType»" valueType="«columnId.getValueType»" columnNum="«columnNum»"/>
	'''
	
	protected def print_ds_ColumnMapping(String columnId, String symbId)'''
		<ColumnMapping>
			<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«columnId»"/>
			«symbId.print_ct_SymbolRef»
		</ColumnMapping>
	'''
		
	 //+ Print data set
	protected def print_ds_TargetDataSet(String mObjName, String dObjName){
		val mObj = getModelObject(mObjName);
		val dObj = getDataObject(dObjName);
		if (dObj == null || mObj == null) return "";
		var res = "";
		for (b: dObj.blocks)	{
			if (b.sourceBlock != null){
				if (b.sourceBlock.list!=null){
					val inputFormat = b.sourceBlock.list.arguments.getAttribute(AttributeValidator::attr_inputformat.name);
					if (inputFormat.equals(InputFormatType::FORMAT_NONMEM)){
						res  = res + print_ds_NONMEM_DataSet(mObjName, dObjName);
					} else {
						res = res + print_ds_Objective_DataSet(mObjName, dObjName);
					}					
				}
			}
		}
		return res;
	}
	

	protected def getColumnType(SymbolDeclaration modelVar){
		var columnType = DefaultValues::USE_VAR;
		if (modelVar.expression != null){
			if (modelVar.expression.list != null){
				val useValue = modelVar.expression.list.arguments.getAttribute(AttributeValidator::attr_use.name);
				if (useValue.length > 0)  columnType = useValue;
			}
		}
		return columnType;
	}
	
	protected def print_ds_Objective_DataSet(String mObjName, String dObjName){
		var res = print_ds_DataSet(mObjName, dObjName);
		'''
		<ObjectiveDataSet>
			«res»
		</ObjectiveDataSet>
		'''
	}
	
	protected def print_ds_NONMEM_DataSet(String mObjName, String dObjName){
		val mObj = getModelObject(mObjName);
		val dObj = getDataObject(dObjName);
		if (dObj == null || mObj == null) return "";
		var res = "";
		for (b: dObj.blocks){
			if (b.dataInputBlock != null){
				for (s: b.dataInputBlock.variables){
					var columnId = s.symbolName.name;
					val modelVar = mObj.getModelInputVariable(columnId);
					if (modelVar != null){
						res = res + print_ds_ColumnMapping(columnId, modelVar.symbolName.name);
					}
				}
			}
			if (b.dataDerivedBlock != null){
				var derivedVars = b.dataDerivedBlock.getDerivedVariables;
				for (s: derivedVars){
					val modelVar = mObj.getModelInputVariable(s);
					if (modelVar != null){
						res = res + print_ds_ColumnMapping(s, modelVar.symbolName.name);
					}
				}
			}
		}
		res = res + print_ds_DataSet(mObjName, dObjName);
		'''
		<NONMEMdataSet oid="«BLK_DS_NONMEM_DATASET + dObjName»">
			«res»
		</NONMEMdataSet>
		'''
	}
	
	protected def print_ds_DataSet(String mObjName, String dObjName){
		val mObj = getModelObject(mObjName);
		val dObj = getDataObject(dObjName);
		if (dObj == null || mObj == null) return "";
		var columnNames = newHashSet;
		var columnTypes = new ArrayList<String>();
		for (b: dObj.blocks){
			if (b.dataInputBlock != null){
				for (s: b.dataInputBlock.variables){
					var columnId = s.symbolName.name;
					val modelVar = mObj.getModelInputVariable(columnId);
					if (modelVar != null){
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
					val modelVar = mObj.getModelInputVariable(s);
					if (modelVar != null){
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
				«print_ds_Definition(new ArrayList<String>(columnNames), columnTypes)»
				«dObjName.print_ds_ImportData»
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
	
	protected def print_ds_ImportData(String dObjName){
		val dObj = getDataObject(dObjName);
		if (dObj == null) return "";
		var res = "";
		for (b: dObj.blocks){
			if (b.sourceBlock != null){
				if (b.sourceBlock.inlineBlock == null){
					val file = b.sourceBlock.list.arguments.getAttribute(AttributeValidator::attr_file.name);
					if (file.length > 0){
						var delimiter = b.sourceBlock.list.arguments.getAttribute(AttributeValidator::attr_delimiter.name);
						if (delimiter.length == 0) delimiter = AttributeValidator::attr_delimiter.defaultValue;
						val fileName = FilenameUtils::getBaseName(file);
						val fileExtension = FilenameUtils::getExtension(file);
						res = res +
						'''				
							<ImportData oid="«BLK_DS_IMPORT_DATA + dObjName»">
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
	
	protected def print_ds_TargetTool(String dObjName){
		val dObj = getDataObject(dObjName);
		if (dObj == null) return "";
		val source = dObj.getScriptFile;
		if (source.length == 0) return "";
		val fileExtension = FilenameUtils::getExtension(source);
		if (fileExtension.length == 0) return "";
		'''
		<TargetTool oid="«BLK_DS_TARGET_TOOL + dObjName»">
			<TargetToolName>«fileExtension.convertID»</TargetToolName>
			«dObjName.print_ds_TargetToolData(source)»
		</TargetTool>
		'''
	}
	
	protected def print_mdef_TargetToolReference(String dObjName)
	'''
		<TargetToolReference>
			<ct:OidRef oidRef="«BLK_DS_TARGET_TOOL + dObjName»"/>
		</TargetToolReference>
	'''
}
