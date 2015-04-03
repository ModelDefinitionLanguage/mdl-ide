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
import org.ddmore.mdl.types.DefaultValues
import eu.ddmore.converter.mdlprinting.MdlPrinter
import org.ddmore.mdl.mdl.MOGObject
import org.ddmore.mdl.validation.Utils
import org.ddmore.mdl.mdl.impl.SymbolDeclarationImpl

class DataSetPrinter {
	protected extension MdlPrinter mdlPrinter = MdlPrinter::getInstance();
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
	
	
	protected def print_ds_ColumnMapping(String columnId, String symbId, String categoricalMapping)'''
		<ColumnMapping>
			<ColumnRef xmlns="«xmlns_ds»" columnIdRef="«columnId»"/>
			«symbId.print_ct_SymbolRef»
			«IF categoricalMapping.length > 0»
				«categoricalMapping»
			«ENDIF»
		</ColumnMapping>
	'''
		
	protected def print_ds_TargetDataSet(MOGObject mog){
		var objects = Utils::getMOGObjects(mog);
		var mObj = Utils::getModelObject(objects);
		var dObj = Utils::getDataObject(objects);
		
		var res = "";
		if (dObj != null || mObj != null) {
			for (b: dObj.blocks)	{
				if (b.sourceBlock != null){
					for (s: b.sourceBlock.statements){
						if (s.propertyName.name.equals(PropertyValidator::attr_inputformat.name) && s.expression != null){
							if (s.expression.toStr.equals(InputFormatType::NONMEM_FORMAT.toString)){
								res  = res + mog.print_ds_NONMEM_DataSet(mObj, dObj);
							} else {
								res = res + dObj.print_ds_Objective_DataSet;
							}					
						}
					}
				}
			}
		}
		return res;
	}
	
	protected def getColumnType(SymbolDeclaration dataColumn){
		var columnType = UseType::ID.toString;
		if (dataColumn.list != null){
			val useValue = dataColumn.list.arguments.getAttribute(AttributeValidator::attr_use.name);
			if (useValue.length > 0) columnType = useValue;
		}
		return columnType;
	}
	
	protected def getValueType(SymbolDeclaration modelVar){
		var variableType = Constants::TYPE_REAL;
		if (modelVar.list != null){
			val type = modelVar.list.arguments.getAttributeExpression(AttributeValidator::attr_type.name);
			if (type.isCategorical) 
				variableType = Constants::TYPE_INT;
			
		}
		return variableType;
	}
	
	
	protected def print_ds_Objective_DataSet(DataObject dObj)'''
		<ObjectiveDataSet>
			« dObj.print_ds_DataSet»
		</ObjectiveDataSet>
	'''
	
	protected def print_ds_NONMEM_DataSet(MOGObject mog, ModelObject mObj, DataObject dObj){
		var res = "";
		var symbolIterator = dObj.eAllContents();
		while (symbolIterator.hasNext()) {
			var container = symbolIterator.next();
			if (container instanceof SymbolDeclarationImpl) {
				var column = container as SymbolDeclaration;
				if (column != null && column.symbolName != null){
					var modelVar = Utils::getMatchingVariable(mog, column.symbolName);
					if (modelVar == null) //Default mapping
						modelVar = getDefaultMatchingVariable(mog, column, mObj);
					//Categorical
					var categoricalMapping = "";
					if (column.list != null){
						val type = column.list.arguments.getAttributeExpression(AttributeValidator::attr_type.name);
						val define = column.list.arguments.getAttributeExpression(AttributeValidator::attr_define.name);
						if (type.isCategorical){
							if (type.type.type.categories != null && type.type.type.categories.size > 0){
								for (i: 0..type.type.type.categories.size - 1){
									var value = "";
									val c = type.type.type.categories.get(i);
									if (define.list != null)
										value = define.list.arguments.getAttribute(c.categoryName.name);
									if (value.length == 0){//unnamed list?
										if (define.list.arguments.unnamedArguments != null &&
											define.list.arguments.unnamedArguments.arguments.size == type.type.type.categories.size)
											value = define.list.arguments.unnamedArguments.arguments.get(i).toStr;	
									}	
									categoricalMapping = categoricalMapping + '''
										<ds:Map«IF value.length > 0» dataSymbol="«value»"«ENDIF» modelSymbol="«c.categoryName.name»"/>
									''';
								}
							}
							if (categoricalMapping.length > 0)
								categoricalMapping = '''
									<ds:CategoryMapping>
										«categoricalMapping»
									</ds:CategoryMapping>
								'''
						}
					}	
					if (modelVar != null){
						res = res + print_ds_ColumnMapping(column.symbolName.name, modelVar, categoricalMapping);
					}
				}
			}
		}		
		res = res + print_ds_DataSet(dObj);
		'''
		<NONMEMdataSet oid="«BLK_DS_NONMEM_DATASET»">
			«res»
		</NONMEMdataSet>
		'''
	}
	
	//Return a model variable (matched by name or in the MOG MAPPING block)
	protected def getDefaultMatchingVariable(MOGObject mog, SymbolDeclaration column, ModelObject mObj){
		if (column.list != null){
			var columnId = column.symbolName.name;
			/*Default implicit mapping*/
			val use = column.list.arguments.getAttribute(AttributeValidator::attr_use.name);
			//Individual variable
			if (use.equals(UseType::IDV.toString)){
				return DefaultValues::INDEPENDENT_VAR;
			}
			//Covariate mapping
			if (use.equals(UseType::COVARIATE.toString)){
				for (b: mObj.blocks){
					if (b.covariateBlock != null){
						for (s: b.covariateBlock.variables){
							if (s.symbolName != null && s.symbolName.name.equals(columnId))
								return s.symbolName.name;
						}
					}
				}
			}	
			//Dosing
			if (use.equals(UseType::AMT.toString)){
				//Search where??
			}
			//ID, DV
			if (use.equals(UseType::ID.toString) || use.equals(UseType::DV.toString)){
				for (b: mObj.blocks){
					if (b.variabilityBlock != null){
						for (s: b.variabilityBlock.variables){
							if (s.symbolName != null && s.symbolName.name.equals(columnId))
								return s.symbolName.name;
						}
					}
				}
			}
		}
		return null;
	}
	
	protected def print_ds_DataSet(DataObject dObj){
		var res = "";
		var k = 1;
		for (b: dObj.blocks){
			if (b.dataInputBlock != null){
				for (i: 0..b.dataInputBlock.variables.size - 1){
					val column = b.dataInputBlock.variables.get(i);
					val columnId = column.symbolName.name;
					val columnType = column.getColumnType.convertEnum;
					val valueType = column.getValueType
					res  = res + '''
						<Column columnId="«columnId»" columnType="«columnType»" valueType="«valueType»" columnNum="«k»"/>
					'''
					k  = k + 1;
				}
			}
		}	
		'''
			<DataSet xmlns="«xmlns_ds»">
				<Definition>
					«res»
				</Definition>
				«dObj.print_ds_ImportData»
			</DataSet>
		'''
	}
	
	protected def print_ds_ImportData(DataObject dObj){
		var res = "";
		for (b: dObj.blocks){
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
						val fileExtension = FilenameUtils::getExtension(file);
						res = res +
						'''				
							<ImportData oid="«BLK_DS_IMPORT_DATA»">
								<path>«file»</path>
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
								if (value.value != null) 
									values.add(value.toStr)
								else {
									values.add("0");	
								}
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
	
	protected def print_ds_TargetToolData(String source){		
		val fileName = FilenameUtils::getBaseName(source);
		val filePath = FilenameUtils::getFullPath(source);
		'''
			<ds:TargetToolData>
				<ds:ImportTargetData oid="«BLK_DS_TARGET_TOOL_DATA»">
					<ds:name>«fileName»</ds:name>
					<ds:url>«filePath»</ds:url>
				</ds:ImportTargetData>
			</ds:TargetToolData>
		'''
	}
	
	protected def print_ds_TargetTool(DataObject dObj){
		val source = dObj.getScriptFile;
		if (source.length == 0) return "";
		val fileExtension = FilenameUtils::getExtension(source);
		if (fileExtension.length == 0) return "";
		'''
			<TargetTool oid="«BLK_DS_TARGET_TOOL»">
				<TargetToolName>«fileExtension.convertID»</TargetToolName>
				«print_ds_TargetToolData(source)»
			</TargetTool>
		'''
	}
}
