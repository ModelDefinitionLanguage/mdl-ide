package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.Mcl
import java.util.ArrayList
import org.ddmore.mdl.validation.AttributeValidator
import org.apache.commons.io.FilenameUtils
import org.ddmore.mdl.mdl.impl.SymbolDeclarationImpl
import org.ddmore.mdl.types.InputFormatType
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.types.DefaultValues
import org.ddmore.mdl.mdl.SymbolModification

class DataSetPrinter {
	protected Mcl mcl = null;
	protected extension Constants constants = new Constants();
	protected extension MathPrinter mathPrinter = null;
	protected extension ReferenceResolver resolver=null;
	
	new(Mcl mcl, MathPrinter mathPrinter, ReferenceResolver resolver){
		this.mcl = mcl;
		this.mathPrinter = mathPrinter;
		this.resolver = resolver;
	}	
	
	def getModelObject(String name){
		for (o: mcl.objects){
			if (o.objectName.name.equals(name)) return o.modelObject;
		}
		return null;
	}
	
	def getDataObject(String name){
		for (o: mcl.objects){
			if (o.objectName.name.equals(name)) return o.dataObject;
		}
		return null;
	}
	
	def getTaskObject(String name){
		for (o: mcl.objects){
			if (o.objectName.name.equals(name)) return o.taskObject;
		}
		return null;
	}
	
	/* //Read the content of an external file
	def getDataFileReader(String filePath){
		var BufferedReader fileReader = null;		
		val platformString = mcl.eResource().getURI().toPlatformString(true);
		mcl.eResource.URI;
		val modelFile = ResourcesPlugin::getWorkspace().getRoot().getFile(new Path(platformString));
		//Try path relatively to the model file
		val parent = modelFile.getParent();
		try {
			for(member : parent.members()){
				if (member.getName().equals(filePath)) {
					fileReader = new BufferedReader(new FileReader(filePath)); 
				}
			}
		} catch (CoreException e) {
			e.printStackTrace();
		}
		//Try path relatively to the project root
		val proj = modelFile.getProject();
		val path = new Path(filePath);
		val requestedFile = proj.getFile(path);
		if (requestedFile.exists()) {
			fileReader = new BufferedReader(new FileReader(filePath)); 
		}
		return fileReader;
	}
	
	def getDataFileContent(String filePath){
		var values = new ArrayList<String[]>();
		var fileReader = getDataFileReader(filePath);
		if (fileReader.ready()){ 
			var line = "";
			while ((line = fileReader.readLine()) != null) {
				val atoms = line.split("\\s{1,}|\\t{1,}|,|;");
	        	values.add(atoms);
	        }
	    	fileReader.close();			
		}		
		return values;
	}	*/
	
	def print_ds_Table(ArrayList<String> rows)'''
		<ds:Table>
			«FOR i: 0..rows.size-1»
				«rows.get(i)»
			«ENDFOR»
		<ds:Table>
	'''
	
	def print_ds_Row(ArrayList<String> values){
		return '''<Row>«FOR i: 0..values.size-1»«values.get(i).print_ct_Value»«ENDFOR»</Row>''';
	}
	
	def print_ds_Definition(ArrayList<String> columnNames, ArrayList<String> columnTypes)'''
		<ds:Definition>
			«FOR i: 0..columnNames.size-1»
				«var columnId = columnNames.get(i)»
				«print_ds_Column(columnId, columnTypes.get(i), i.toString)»
			«ENDFOR»
		</ds:Definition>
	'''
	
	def print_ds_Column(String columnId, String columnType, String columnNum)'''
		<ds:Column columnId="«columnId»" columnType="«columnType»" valueType="«columnId.getValueType»" columnNum="«columnNum»"/>
	'''
	
	def print_ds_ColumnMapping(String columnId, String symbId, String blkIdRef)'''
		<ColumnMapping>
			<ds:ColumnRef columnIdRef="«columnId»"/>
			<ds:SymbRef «IF blkIdRef.length > 0» blkIdRef="«blkIdRef»" «ENDIF»symbIdRef="«symbId»"/>
		</ColumnMapping>
	'''
		
	 //+ Print data set
	def print_ds_DataSet(String dObjName, String mObjName){
		val dObj = getDataObject(dObjName);
		val mObj = getModelObject(mObjName);
		if (dObj == null || mObj == null) return "";
		var res = "";
		for (b: dObj.blocks)	{
			if (b.sourceBlock != null){
				if (b.sourceBlock.list!=null){
					val inputFormat = b.sourceBlock.list.arguments.getAttribute(AttributeValidator::attr_inputformat.name);
					if (inputFormat.equals(InputFormatType::FORMAT_NONMEM)){
						res  = res + print_ds_NONMEM_DataSet(dObjName, mObjName);
					} else {
						res = res + print_ds_General_DataSet(dObjName, mObjName);
					}					
				}
			}
		}
		return res;
	}
	
	def getColumnType(SymbolModification modelVar){
		var columnType = DefaultValues::USE_VAR;
		if (modelVar.expression != null){
			if (modelVar.expression.list != null){
				val useValue = modelVar.expression.list.arguments.getAttribute(AttributeValidator::attr_use.name);
				if (useValue.length > 0)  columnType = useValue;
			}
		}
		return columnType;
	}
	
	def print_ds_General_DataSet(String dObjName, String mObjName){
		val dObj = getDataObject(dObjName);
		val mObj = getModelObject(mObjName);
		if (dObj == null || mObj == null) return "";
		var res = "";
		var columnNames = newHashSet;
		var columnTypes = new ArrayList<String>();
		for (b: dObj.blocks){
			if (b.dataInputBlock != null){
				for (s: b.dataInputBlock.variables){
					var columnId = s.symbolName.name;
					val modelVar = mObj.getModelInputVariable(columnId);
					if (modelVar != null){
						columnNames.add(modelVar.symbolName.symbol.name);
						columnTypes.add(modelVar.getColumnType);
					}
				}
			}
			if (b.dataDerivedBlock != null){
				var derivedVars = newHashSet;
				for (s: b.dataDerivedBlock.statements){
					var iterator = s.eResource.getAllContents();
				    while (iterator.hasNext()){
	    				val obj = iterator.next();
	    				if (obj instanceof SymbolDeclarationImpl){
	    					val symbol = obj as SymbolDeclaration;
	    					if (!columnNames.contains(symbol.symbolName.name))
	    						derivedVars.add(symbol.symbolName.name);
						}
					}
				}
				for (s: derivedVars){
					val modelVar = mObj.getModelInputVariable(s);
					if (modelVar != null){
						columnNames.add(modelVar.symbolName.symbol.name);
						columnTypes.add(modelVar.getColumnType);
					}
				}
			}	
		}
		if (columnNames.size > 0){
			res  = res + print_ds_Definition(new ArrayList<String>(columnNames), columnTypes);
		}
		res = res + print_ds_ImportData(dObjName);
		'''
		<ds:DataSet oid="«BLK_DS_DATASET + dObjName»">
			«res»
		</ds:DataSet>
		'''
	}

	def print_ds_NONMEM_DataSet(String dObjName, String mObjName){
		val dObj = getDataObject(dObjName);
		val mObj = getModelObject(mObjName);
		if (dObj == null || mObj == null) return "";
		var res = "";
		for (b: dObj.blocks){
			if (b.dataInputBlock != null){
				for (s: b.dataInputBlock.variables){
					var columnId = s.symbolName.name;
					val modelVar = mObj.getModelInputVariable(columnId);
					if (modelVar != null){
						var blkIdRef = mObjName.getReferenceBlock(modelVar.symbolName.symbol.name);
						res = res + print_ds_ColumnMapping(columnId, modelVar.symbolName.symbol.name, blkIdRef);
					}
				}
			}
			if (b.dataDerivedBlock != null){
				var derivedVars = newHashSet;
				for (s: b.dataDerivedBlock.statements){
					var iterator = s.eResource.getAllContents();
				    while (iterator.hasNext()){
	    				val obj = iterator.next();
	    				if (obj instanceof SymbolDeclarationImpl){
	    					val symbol = obj as SymbolDeclaration;
	    					if (!derivedVars.contains(symbol.symbolName.name))
	    						derivedVars.add(symbol.symbolName.name);
						}
					}
				}
				for (s: derivedVars){
					val modelVar = mObj.getModelInputVariable(s);
					if (modelVar != null){
						var blkIdRef = mObjName.getReferenceBlock(modelVar.symbolName.symbol.name);
						res = res + print_ds_ColumnMapping(s, modelVar.symbolName.symbol.name, blkIdRef);
					}
				}
			}
		}
		res = res + print_ds_ImportData(dObjName);
		'''
		<NONMEMdataSet oid="«BLK_DS_NONMEM_DATASET + dObjName»">
			«res»
		</NONMEMdataSet>
		'''
	}
	
	def print_ds_ImportData(String dObjName){
		val dObj = getDataObject(dObjName);
		if (dObj == null) return "";
		for (b: dObj.blocks){
			if (b.sourceBlock != null){
				if (b.sourceBlock.inlineBlock == null){
					val file = b.sourceBlock.list.arguments.getAttribute(AttributeValidator::attr_file.name);
					if (file.length > 0){
						var delimiter = b.sourceBlock.list.arguments.getAttribute(AttributeValidator::attr_delimiter.name);
						if (delimiter.length == 0) delimiter = AttributeValidator::attr_delimiter.defaultValue;
						val fileName = FilenameUtils::getBaseName(file);
						val filePath = FilenameUtils::getPath(file);
						val fileExtension = FilenameUtils::getExtension(file);
						return					
						'''				
							<ds:ImportData oid="«BLK_DS_IMPORT_DATA + dObjName»">
								<ds:name>«fileName»</ds:name>
								<ds:url>«filePath»</ds:url>
								<ds:format>«fileExtension.convertFileFormat»</ds:format>
								<ds:delimiter>«delimiter.convertDelimiter»</ds:delimiter>
							</ds:ImportData>
						'''
					}
				} else {
					//Process INLINE block
					var rows = new ArrayList<String>();
					var columns = new ArrayList<String>();
					if (b.sourceBlock.inlineBlock.variables != null){
						for (value: b.sourceBlock.inlineBlock.variables.identifiers){
							columns.add(value.symbol.name);
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
						return rows.print_ds_Table;
					}
				}
			}
		}
		return "";
	}
	
	def print_ds_TargetToolData(String dObjName, String source){		
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
	
	def print_ds_TargetTool(String dObjName){
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
	
	def print_mdef_TargetToolReference(String dObjName)'''
		<TargetToolReference>
			<ct:OidRef oidRef="«BLK_DS_TARGET_TOOL + dObjName»"/>
		</TargetToolReference>
	'''
}