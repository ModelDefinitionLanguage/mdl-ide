package org.ddmore.mdl.validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.ddmore.mdl.domain.Attribute;
import org.ddmore.mdl.mdl.DataObject;
import org.ddmore.mdl.mdl.DataObjectBlock;
import org.ddmore.mdl.mdl.InputFormatType;
import org.ddmore.mdl.mdl.ListDeclaration;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.PropertyDeclaration;
import org.ddmore.mdl.mdl.SourceBlock;
import org.ddmore.mdl.mdl.TaskObjectBlock;
import org.ddmore.mdl.mdl.impl.EstimateTaskImpl;
import org.ddmore.mdl.mdl.impl.SimulateTaskImpl;
import org.ddmore.mdl.mdl.impl.SourceBlockImpl;
import org.ddmore.mdl.types.DefaultValues;
import org.ddmore.mdl.types.MdlDataType;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;

import com.google.inject.Inject;

import eu.ddmore.converter.mdlprinting.MdlPrinter;

public class PropertyValidator extends AbstractDeclarativeValidator{

	///////////////////////////////////////////////////////////////////////////////////////////////
	//Properties
	///////////////////////////////////////////////////////////////////////////////////////////////

	@Override
    @Inject
    public void register(EValidatorRegistrar registrar) {}
	
    public final static String MSG_PROPERTY_UNKNOWN = "Unknown property";
	public final static String MSG_PROPERTY_MISSING = "Required property is not set";
	public final static String MSG_PROPERTY_DEFINED = "Property defined more than once";
	
	public final static String MSG_WRONG_TYPE = "Type error";

	public final static String MSG_DATA_FILE_NOT_FOUND = "Cannot find data file: path may be incorrect";
	public final static String MSG_SCRIPT_NOT_FOUND    = "Cannot find script file: path may be incorrect";
	public final static String MSG_DATA_HEADERS_MISMATCH  = "Data file headers so not match data variables";
	public final static String MSG_DATA_COLUMNS_MISMATCH  = "Number of columns does not match the number of data variables";

	public final static String MSG_TARGET_LOCATION = "Target code block is not used inline, please specify location";

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//Task object
	//SIMUATE, ESTIMATE, EVALUATE, OPTIMIZE
	final public static Attribute attr_task_algo = new Attribute("algo", MdlDataType.TYPE_VECTOR_STRING, false);
	final public static Attribute attr_task_max = new Attribute("max", MdlDataType.TYPE_NAT, false);
	final public static Attribute attr_task_sig = new Attribute("sig", MdlDataType.TYPE_NAT, false);
	final public static Attribute attr_task_cov = new Attribute("cov", MdlDataType.TYPE_BOOLEAN, false);
	final public static Attribute attr_task_simopt = new Attribute("simopt", MdlDataType.TYPE_VECTOR_STRING, false);
	final public static Attribute attr_task_target = new Attribute("target", MdlDataType.TYPE_TARGET, false);
	final public static Attribute attr_task_version = new Attribute("version", MdlDataType.TYPE_STRING, false);
		
	final public static List<Attribute> attrs_task_simulate = Arrays.asList(
			attr_task_algo, attr_task_max, attr_task_sig, attr_task_cov, attr_task_simopt, attr_task_target, attr_task_version);
	final public static List<Attribute> attrs_task_estimate = Arrays.asList(
			attr_task_algo, attr_task_max, attr_task_sig, attr_task_cov, attr_task_simopt, attr_task_target, attr_task_version);
//	final public static List<Attribute> attrs_task_evaluate = Arrays.asList();
//	final public static List<Attribute> attrs_task_optimise = Arrays.asList();
	
//	//DATA
//	final public static Attribute attr_data_ignore = new Attribute("ignore", MdlDataType.TYPE_BOOLEAN, false);
//	final public static Attribute attr_data_accept = new Attribute("accept", MdlDataType.TYPE_BOOLEAN, false);
//	final public static Attribute attr_data_drop = new Attribute("drop", MdlDataType.TYPE_VECTOR_REF, false);
	
//	final public static List<Attribute> attrs_task_data = Arrays.asList(attr_data_ignore, attr_data_accept, attr_data_drop);
	
//	//MODEL
//	final public static Attribute attr_model_tolrel = new Attribute("tolrel", MdlDataType.TYPE_NAT, false);
//	final public static Attribute attr_model_add = new Attribute("add", MdlDataType.TYPE_VECTOR_REF, false);
//	final public static Attribute attr_model_remove = new Attribute("remove", MdlDataType.TYPE_VECTOR_REF, false);
//	
//	final public static List<Attribute> attrs_task_model = Arrays.asList(attr_model_tolrel, attr_model_add, attr_model_remove);
	
	/*SOURCE*/
	final public static Attribute attr_ignore = new Attribute("ignore", MdlDataType.TYPE_STRING, false);
	final public static Attribute attr_inputformat = new Attribute("inputformat", MdlDataType.TYPE_INPUT_FORMAT, true, InputFormatType.NONMEM_FORMAT.toString());
	final public static Attribute attr_delimiter = new Attribute("delimiter", MdlDataType.TYPE_STRING, false, ",");
	final public static Attribute attr_header = new Attribute("header", MdlDataType.TYPE_BOOLEAN, false, "false");
	final public static Attribute attr_file = new Attribute("file", MdlDataType.TYPE_STRING, true, DefaultValues.FILE_NAME);
	final public static Attribute attr_script = new Attribute("script", MdlDataType.TYPE_STRING, true, DefaultValues.FILE_NAME);
	final public static Attribute attr_skip = new Attribute("skip", MdlDataType.TYPE_NAT, false);
	final public static Attribute attr_nrows = new Attribute("nrows", MdlDataType.TYPE_NAT, false);

	final public static List<Attribute> attrs_source = Arrays.asList(attr_inputformat, attr_ignore, 
			attr_delimiter, attr_file, attr_script, attr_header, attr_skip, attr_nrows);

	/*TARGET*/
//	final public static Attribute attr_req_target = new Attribute("target", MdlDataType.TYPE_TARGET, true, TargetType.NMTRAN_CODE.toString());
//	final public static Attribute attr_location = new Attribute("location", MdlDataType.TYPE_STRING, false);
//	final public static Attribute attr_first = new Attribute("first", MdlDataType.TYPE_BOOLEAN, false);
//	final public static Attribute attr_last = new Attribute("last", MdlDataType.TYPE_BOOLEAN, false);
//	final public static Attribute attr_sameline = new Attribute("sameline", MdlDataType.TYPE_BOOLEAN, false);
//
//	final public static List<Attribute> attrs_target = Arrays.asList(attr_req_target, attr_location, attr_first, attr_last, attr_sameline);
	
	/*Design block*/
//	final public static Attribute attr_totalSize = new Attribute("totalSize", MdlDataType.TYPE_NAT, true);
//	final public static Attribute attr_numberSamples = new Attribute("numberSamples", MdlDataType.TYPE_NAT, true);
//	final public static Attribute attr_covariates = new Attribute("covariates", MdlDataType.TYPE_LIST, false);
//	
//	final public static List<Attribute> attrs_populationFeatures = Arrays.asList(
//			attr_totalSize, attr_numberSamples, attr_covariates);
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	HashMap<String, String> exclusive_attrs = new HashMap<String, String>(){
		private static final long serialVersionUID = 4646663049359441357L;
		{
//			put(attr_first.getName(), attr_last.getName());
//			put(attr_last.getName(), attr_first.getName());
			put(attr_script.getName(), attr_file.getName());
			put(attr_file.getName(), attr_script.getName());
		}
	};
	
	@Check
	//Check required properties in Task object
	public void checkRequiredProperties(TaskObjectBlock tob){
		List<PropertyDeclaration> statements = null;
		List<String> attrNames = null;
		EReference ref = null;
		if (tob.getSimulateBlock() != null){
			statements = tob.getSimulateBlock().getStatements();
			attrNames = getRequiredPropertyNames(tob.getSimulateBlock());
			ref = MdlPackage.Literals.TASK_OBJECT_BLOCK__SIMULATE_BLOCK;
		}
		if (tob.getEstimateBlock() != null){
			statements = tob.getEstimateBlock().getStatements();
			attrNames = getRequiredPropertyNames(tob.getEstimateBlock());
			ref = MdlPackage.Literals.TASK_OBJECT_BLOCK__ESTIMATE_BLOCK;
		}
//		if (tob.getDataBlock() != null){
//			statements = tob.getDataBlock().getStatements(); 
//			attrNames = getRequiredPropertyNames(tob.getDataBlock());
//			ref = MdlPackage.Literals.TASK_OBJECT_BLOCK__DATA_BLOCK;
//		}
		checkRequiredProperties(statements, attrNames, ref);
	}
	
//	@Check
	//Check required properties in TargetBlock
//	public void checkRequiredProperties(TargetBlock t){
//		checkRequiredProperties(t.getStatements(), getRequiredPropertyNames(t), 
//			MdlPackage.Literals.TARGET_BLOCK__STATEMENTS);
//	}

	@Check
	//Check required properties in SourceBlock
	public void checkRequiredProperties(SourceBlock t){
		checkRequiredProperties(t.getStatements(), getRequiredPropertyNames(t), 
			MdlPackage.Literals.SOURCE_BLOCK__STATEMENTS);
	}
	
	public void checkRequiredProperties(List<PropertyDeclaration> statements, List<String> attrNames, EReference ref){
		HashSet<String> properties = new HashSet<String>();
		for (PropertyDeclaration p: statements){
			if (p.getPropertyName() != null){
				if (!properties.contains(p.getPropertyName().getName())){
					properties.add(p.getPropertyName().getName());
				} else {
					warning(MSG_PROPERTY_DEFINED + ": " + p.getPropertyName().getName(), 
						ref,
						MSG_PROPERTY_DEFINED, p.getPropertyName().getName());
				}
			}
		}	
		for (String attrName: attrNames){
			if (!properties.contains(attrName)) {
				if (!exclusive_attrs.containsKey(attrName) || !attrNames.contains(exclusive_attrs.get(attrName))){
					warning(MSG_PROPERTY_MISSING + ": " + attrName, 
						ref, MSG_PROPERTY_MISSING, attrName);
				}				
			}
		}
		for (String p: properties){
			if (exclusive_attrs.containsKey(p)){
				String exclusive = exclusive_attrs.get(p);
				if (properties.contains(exclusive)){
					warning("Property '" + p + "' cannot be used together with '" + 
						exclusive + "'", 
						ref, MSG_PROPERTY_DEFINED, 
						p);				
				}
			}
		}
	}
	

	@Check
	public void checkAllProperties(PropertyDeclaration p){
		EObject container = p.eContainer();
		if (isPropertyContainer(container)){
			//check that an argument is recognized
			List<Attribute> knownAttributes = getAllProperties(container);
			if (knownAttributes != null){
				List<String> attributeNames = Utils.getAllNames(knownAttributes);
				if (!attributeNames.contains(p.getPropertyName().getName())){
					error(MSG_PROPERTY_UNKNOWN + ": " + p.getPropertyName().getName(), 
					MdlPackage.Literals.PROPERTY_DECLARATION__PROPERTY_NAME,
					MSG_PROPERTY_UNKNOWN, p.getPropertyName().getName());		
				}
				for (Attribute x: knownAttributes){
					if (x.getName().equals(p.getPropertyName().getName())){
						boolean isValid = false;
						for (MdlDataType type: x.getTypes())
							isValid = isValid || MdlDataType.validateType(type, p.getExpression());
						if (!isValid){
							warning(MSG_WRONG_TYPE + 
								": property \"" + p.getPropertyName().getName() + "\" expects value of type " + 
								Utils.printList(x.getTypeNames()), 
								MdlPackage.Literals.PROPERTY_DECLARATION__PROPERTY_NAME,
								MSG_WRONG_TYPE, p.getPropertyName().getName());		
						}
					}
				}
			}
		}
	}
	
	@Check
	//Check that data file exists in the project
	public void checkSourceFiles(PropertyDeclaration p){
		EObject container = p.eContainer();
		if (container instanceof SourceBlockImpl){
			SourceBlock b = (SourceBlock) container;
//			if (b.getInlineBlock() != null) return;
			if (p.getPropertyName().getName().equals(attr_file.getName()) || 
				p.getPropertyName().getName().equals(attr_script.getName())) {
				String dataPath = MdlPrinter.getInstance().toStr(p.getExpression());
				IFile dataFile = Utils.getFile(b, dataPath);
				if (!dataFile.exists()){
					if (p.getPropertyName().getName().equals(attr_file.getName())){
						warning(MSG_DATA_FILE_NOT_FOUND, 
							MdlPackage.Literals.PROPERTY_DECLARATION__EXPRESSION,
							MSG_DATA_FILE_NOT_FOUND, dataPath);
					}
					if (p.getPropertyName().getName().equals(attr_script.getName())){
						warning(MSG_SCRIPT_NOT_FOUND, 
							MdlPackage.Literals.PROPERTY_DECLARATION__EXPRESSION,
							MSG_SCRIPT_NOT_FOUND, dataPath);
					}
				} else {
					//Data file found, check columns
					checkData(p, dataFile);
				}
			}
		}
	}	
	
	private void checkData(PropertyDeclaration p, IFile dataFile){
		MclObject mcl = Utils.getMclObject(p);
		if (mcl.getDataObject() != null){
			DataObject dObj = mcl.getDataObject();
			Boolean header = false;
			String delimiter = ",";
			//int skip = 0; 
			for (DataObjectBlock b: dObj.getBlocks()){
				if (b.getSourceBlock() != null){
					for (PropertyDeclaration pp: b.getSourceBlock().getStatements()){
						if (pp.getPropertyName().getName().equals(attr_header.getName()))
							header = MdlPrinter.getInstance().isTrue(pp.getExpression());
						if (pp.getPropertyName().getName().equals(attr_delimiter.getName()))
							delimiter = MdlPrinter.getInstance().toStr(pp.getExpression());
						/*if (pp.getPropertyName().getName().equals(attr_skip.getName())){
							String value = MdlPrinter.getInstance().toStr(pp.getExpression());
							try{
								skip = Integer.parseInt(value);
							} catch (NumberFormatException e){}
						}*/
					}
				}
			}
			//Read first line in the file
			try {
				InputStream is = dataFile.getContents();
				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String headers =  reader.readLine();
				/*if (skip > 0) 
					for (int i = 0; i < skip; i++) headers = reader.readLine();
				*/
				is.close();
				String[] columns =  headers.split(delimiter);

				if (header){ //Match variable names
					List<String> notFound = new ArrayList<String>();
					//Iterate over data variables and match
					for (DataObjectBlock b: dObj.getBlocks()){
						if (b.getDataInputBlock() != null){
							for (ListDeclaration s: b.getDataInputBlock().getVariables()){
								if (s.getSymbolName() != null){
									boolean isFound = false;
									for (String column: columns)
										if (column.equals(s.getSymbolName().getName())) isFound = true;
									if (!isFound) notFound.add(s.getSymbolName().getName());
								}
							}
						}
					}
					if (notFound.size() > 0){
						String problemVars = Utils.printList(notFound);
						warning(MSG_DATA_HEADERS_MISMATCH + 
							" - headers not found for: " + problemVars, 
							MdlPackage.Literals.PROPERTY_DECLARATION__EXPRESSION,
							MSG_DATA_HEADERS_MISMATCH, dataFile.getName());
					}					
				} else {//Check number of columns
					int numDataVars = 0;
					for (DataObjectBlock b: dObj.getBlocks()){
						if (b.getDataInputBlock() != null)
							numDataVars += b.getDataInputBlock().getVariables().size();
					}
					if (columns.length != numDataVars){
						warning(MSG_DATA_COLUMNS_MISMATCH, 
							MdlPackage.Literals.PROPERTY_DECLARATION__EXPRESSION,
							MSG_DATA_COLUMNS_MISMATCH, dataFile.getName());
					}
				}
			} catch (CoreException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}		
	}
	
//	@Check
//	//External target blocks should have location defined
//	public void checkTargetLocation(TargetBlock t){
//		for (PropertyDeclaration p: t.getStatements())
//			if (p.getPropertyName().getName().equals(attr_location.getName())) return;
//		warning(MSG_TARGET_LOCATION, 
//			MdlPackage.Literals.TARGET_BLOCK__STATEMENTS,
//				MSG_TARGET_LOCATION, t.getIdentifier());
//	}
	
	Boolean isPropertyContainer(EObject container){
		return (
		container instanceof EstimateTaskImpl ||
		container instanceof SimulateTaskImpl ||
//		container instanceof EvaluateTaskImpl ||
//		container instanceof OptimiseTaskImpl ||
//		container instanceof ModelBlockImpl ||
//		container instanceof DataBlockImpl ||
		container instanceof SourceBlockImpl //||
//		container instanceof TargetBlockImpl ||
//		container instanceof PopulationFeaturesBlockImpl
		); 
	}	
	
	List<Attribute> getAllProperties(EObject obj){
		if (obj instanceof EstimateTaskImpl)
			return attrs_task_estimate;
		if (obj instanceof SimulateTaskImpl)
			return attrs_task_simulate;
//		if (obj instanceof DataBlockImpl)
//			return attrs_task_data;
//		if (obj instanceof ModelBlockImpl)
//			return attrs_task_model;
//		if (obj instanceof EvaluateTaskImpl)
//			return attrs_task_evaluate;
//		if (obj instanceof OptimiseTaskImpl)
//			return attrs_task_optimise;
		if (obj instanceof SourceBlockImpl)
			return attrs_source;
//		if (obj instanceof TargetBlockImpl)
//			return attrs_target;
//		if (obj instanceof PopulationFeaturesBlockImpl)
//			return attrs_populationFeatures;
		return null;
	}
	
	List<String> getRequiredPropertyNames(EObject obj){
		List<Attribute> allProperties = getAllProperties(obj);
		if (allProperties != null)
			return Utils.getRequiredNames(getAllProperties(obj));
		return null;
	}	
}
