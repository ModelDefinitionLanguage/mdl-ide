package eu.ddmore.converter.mdl2pharmml

import org.ddmore.mdl.mdl.Mcl
import java.util.ArrayList
import java.io.FileReader
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.File

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
	
	//
	def print_ds_Columns(String[] names, String[] types)
	'''
		«IF names.size == types.size»
			«FOR i: 0..names.size-1»
				<ds:Column columnId="«names.get(i)»" valueType="«types.get(i)»" columnNum="«i»"/>
			«ENDFOR»
		«ELSE»
			«FOR i: 0..names.size-1»
				<ds:Column columnId="«names.get(i)»" columnNum="«i»"/>
			«ENDFOR»
		«ENDIF»
	'''
	
	//
	def print_ds_Row(String[] atoms)'''
	<ds:Row>
		«FOR row: atoms»
			«print_ct_Value(row)»
		«ENDFOR»
	</ds:Row>
	'''
	
	// May need to skip first line (repeated column names) 
	// TODO: Do we need to check actual types against types deduced from MDL???
	def print_DataSet(ArrayList<String> names, ArrayList<String> types, ArrayList<String[]> values)
	'''
	<ds:DataSet>
		<ds:Definition>
			«print_ds_Columns(names, types)»
		</ds:Definition>
		<ds:Table>	
			«FOR row: values»
				«row.print_ds_Row»
			«ENDFOR»
		</ds:Table>
	</ds:DataSet>
	'''
	
	def getDataFileReader(String fileName){
		var BufferedReader fileReader = null;
		var file = new File(fileName);
		if (file.isAbsolute()) {
			try{			
				fileReader = new BufferedReader(new FileReader(fileName)); 
			} 
			catch(FileNotFoundException e1){
				return null;
			}		
		} else {
			//The fileName contains relative path to the data file, try to find it in the project folder
			var modelPath = mcl.eResource.getURI.toPlatformString(true);
			if (modelPath != null){
				var dataPath = file.getParent + "\\" + fileName;	
				System::out.println("Looking for a file:" + dataPath);	
				try{			
					fileReader = new BufferedReader(new FileReader(dataPath)); //First try the path as it is
				}		
				catch(FileNotFoundException e){
					dataPath = file.getParent + "\\data\\" + fileName; //If not found, try to look in the folder "data"
					System::out.println("Looking for a file:" + dataPath);	
					try{
						fileReader = new BufferedReader(new FileReader(dataPath));
					}
					catch(FileNotFoundException e1){
						return null;
					}
				}
			}
		}
		return fileReader;
	}
	
	def getDataFileContent(String fileName){
		var values = new ArrayList<String[]>();
		var fileReader = getDataFileReader(fileName);
		if (fileReader.ready()){ 
			var line = "";
			while ((line = fileReader.readLine()) != null) {
				val atoms = line.split("\\s{1,}|,|;");
	        	values.add(atoms);
	        }
	    	fileReader.close();			
		}		
		return values;
	}	
	
	def print_ds_ExternalSource(String oid, String fileName, String filePath, String format, String delimiter)'''				
		<ds:ImportData oid="«oid»">
			<ds:name>«fileName»</ds:name>
			<ds:url>«filePath»</ds:url>
			<ds:format>«format»</ds:format>
			<ds:delimiter>«delimiter»</ds:delimiter>
		</ds:ImportData>
			
	'''	
}