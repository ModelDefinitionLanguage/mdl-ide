package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.utils.MdlUtils
import java.io.File
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.Path
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class DataFileValidator extends AbstractMdlValidator  {
	
	extension MdlUtils mu = new MdlUtils
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
					DATA_FILE_NOT_FOUND, dataPath)
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
	
    /**
     * Locate data/script file in the MDL project.
     * Such file references are resolved against the directory in which the MDL file is present.
     */
    // The validation code is called from both the MDL IDE and from the command-line
    // MDL->PharmML converter, each of which represents a file differently, hence
    // the ugly need to derive the java.io.File object representing the MDL file
    // in two different ways.
    def File getFile(EObject b, String filePath) {
    	val resource = b.eResource()
    	
        var File modelFile
    	if (resource.URI.isPlatform) { // Triggered from 'internal' MDL IDE code
            val platformString = resource.URI.toPlatformString(true)
            val modelFileAsIFile = ResourcesPlugin.getWorkspace().root.getFile(Path.fromOSString(platformString))
            modelFile = new File(modelFileAsIFile.locationURI.path) // Convert org.eclipse.core.resources.IFile to java.io.File
    	} else { // Triggered from 'external' converter code
            modelFile = new File(resource.URI.toFileString())
    	}
    	val java.nio.file.Path pf = if(modelFile.parentFile == null) java.nio.file.Paths.get(".") else modelFile.parentFile.toPath()
//    	val dataFile = modelFile.parentFile.toPath().resolve(filePath).toFile().canonicalFile
    	val dataFile = pf.resolve(filePath).toFile().canonicalFile
    	return dataFile
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