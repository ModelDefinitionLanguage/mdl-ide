package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.utils.MclUtils
import java.io.File
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.Path
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class DataFileValidation extends AbstractMdlValidator  {
	
	extension MclUtils mu = new MclUtils
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	
	override register(EValidatorRegistrar registrar){}
	
	public static val DATA_FILE_NOT_FOUND = "eu.ddmore.mdl.validation.source.file.unknown"
	
	static val MSG_DATA_FILE_NOT_FOUND = "Cannot find data file: path may be incorrect."


	@Check
	//Check that data file exists in the project
	def checkSourceFiles(ValuePair p){
		val blk = p.owningBlock
		val lst = p.owningListDefinition
		if(blk != null && blk.isDataSourceBlock && lst != null && p.argumentName == 'file'){
			val dataPath = p.expression.stringValue
			val dataFile = getFile(p, dataPath);
			if (dataFile == null || !dataFile.exists()){
				warning(MSG_DATA_FILE_NOT_FOUND, 
					MdlPackage.eINSTANCE.valuePair_Expression,
					DATA_FILE_NOT_FOUND, dataPath);
			} else {
				//Data file found, check columns
//					checkData(p, dataFile);
			}
//			if (p.getPropertyName().getArgName().equals(attr_file.getName()) || 
//				p.getPropertyName().getArgName().equals(attr_script.getName())) {
//				String dataPath = MdlPrinter.getInstance().toStr(p.getExpression());
//				IFile dataFile = getFile(b, dataPath);
//				if (!dataFile.exists()){
//					if (p.getPropertyName().getArgName().equals(attr_file.getName())){
//						warning(MSG_DATA_FILE_NOT_FOUND, 
//							MdlPackage.Literals.PROPERTY_DECLARATION__EXPRESSION,
//							MSG_DATA_FILE_NOT_FOUND, dataPath);
//					}
//					if (p.getPropertyName().getArgName().equals(attr_script.getName())){
//						warning(MSG_SCRIPT_NOT_FOUND, 
//							MdlPackage.Literals.PROPERTY_DECLARATION__EXPRESSION,
//							MSG_SCRIPT_NOT_FOUND, dataPath);
//					}
//				} else {
//					//Data file found, check columns
//					checkData(p, dataFile);
//				}
//			}
		}
	}
	
    //Locate data/script file in the MDL project
    def File getFile(EObject b, String filePath) {
    	val resource = b.eResource()
        val platformString = resource.getURI().toPlatformString(true);
        if(platformString != null){
	        val modelFile = ResourcesPlugin.getWorkspace().getRoot().getFile(Path.fromOSString(platformString));
	        val project = modelFile.getProject();
	        var parent = modelFile.getParent();
	        var p = filePath;
	        while (p.startsWith("../") && parent != null){
	            parent = parent.getParent();
	            p = p.substring(3);
	        }
	        val dataFile = project.getFile(parent.getProjectRelativePath() + "/" + p);
	        return new File(dataFile.locationURI.getPath)
	    }
	    else{
	    	val url = this.class.getResource(filePath)
	    	if(url != null){
		        return new File(url.path);
	    	}
	    }
	    null
    }
	
//	private void checkData(PropertyDeclaration p, IFile dataFile){
//		MclObject mcl = Utils.getMclObject(p);
//		if (mcl.getDataObject() != null){
//			DataObject dObj = mcl.getDataObject();
//			Boolean header = false;
//			String delimiter = ",";
//			//int skip = 0; 
//			for (DataObjectBlock b: dObj.getBlocks()){
//				if (b.getSourceBlock() != null){
//					for (PropertyDeclaration pp: b.getSourceBlock().getStatements()){
//						if (pp.getPropertyName().getArgName().equals(attr_header.getName()))
//							header = MdlPrinter.getInstance().isTrue(pp.getExpression());
//						if (pp.getPropertyName().getArgName().equals(attr_delimiter.getName()))
//							delimiter = MdlPrinter.getInstance().toStr(pp.getExpression());
//						/*if (pp.getPropertyName().getArgName().equals(attr_skip.getName())){
//							String value = MdlPrinter.getInstance().toStr(pp.getExpression());
//							try{
//								skip = Integer.parseInt(value);
//							} catch (NumberFormatException e){}
//						}*/
//					}
//				}
//			}
//			//Read first line in the file
//			try {
//				InputStream is = dataFile.getContents();
//				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//				String headers =  reader.readLine();
//				/*if (skip > 0) 
//					for (int i = 0; i < skip; i++) headers = reader.readLine();
//				*/
//				is.close();
//				String[] columns =  headers.split(delimiter);
//
//				if (header){ //Match variable names
//					List<String> notFound = new ArrayList<String>();
//					//Iterate over data variables and match
//					for (DataObjectBlock b: dObj.getBlocks()){
//						if (b.getDataInputBlock() != null){
//							for (ListDeclaration s: b.getDataInputBlock().getVariables()){
//								if (s.getName() != null){
//									boolean isFound = false;
//									for (String column: columns)
//										if (column.equals(s.getName())) isFound = true;
//									if (!isFound) notFound.add(s.getName());
//								}
//							}
//						}
//					}
//					if (notFound.size() > 0){
//						String problemVars = Utils.printList(notFound);
//						warning(MSG_DATA_HEADERS_MISMATCH + 
//							" - headers not found for: " + problemVars, 
//							MdlPackage.Literals.PROPERTY_DECLARATION__EXPRESSION,
//							MSG_DATA_HEADERS_MISMATCH, dataFile.getName());
//					}					
//				} else {//Check number of columns
//					int numDataVars = 0;
//					for (DataObjectBlock b: dObj.getBlocks()){
//						if (b.getDataInputBlock() != null)
//							numDataVars += b.getDataInputBlock().getVariables().size();
//					}
//					if (columns.length != numDataVars){
//						warning(MSG_DATA_COLUMNS_MISMATCH, 
//							MdlPackage.Literals.PROPERTY_DECLARATION__EXPRESSION,
//							MSG_DATA_COLUMNS_MISMATCH, dataFile.getName());
//					}
//				}
//			} catch (CoreException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}		
//		}		
//	}
}