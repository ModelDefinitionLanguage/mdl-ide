package org.ddmore.mdl.validation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.ddmore.mdl.domain.Attribute;
import org.ddmore.mdl.mdl.InputFormatEnum;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.PropertyDeclaration;
import org.ddmore.mdl.mdl.SourceBlock;
import org.ddmore.mdl.mdl.TargetBlock;
import org.ddmore.mdl.mdl.TargetEnum;
import org.ddmore.mdl.mdl.TaskObjectBlock;
import org.ddmore.mdl.mdl.impl.DataBlockImpl;
import org.ddmore.mdl.mdl.impl.EstimateTaskImpl;
import org.ddmore.mdl.mdl.impl.EvaluateTaskImpl;
import org.ddmore.mdl.mdl.impl.ModelBlockImpl;
import org.ddmore.mdl.mdl.impl.OptimiseTaskImpl;
import org.ddmore.mdl.mdl.impl.SimulateTaskImpl;
import org.ddmore.mdl.mdl.impl.SourceBlockImpl;
import org.ddmore.mdl.mdl.impl.TargetBlockImpl;
import org.ddmore.mdl.mdl.impl.TaskObjectBlockImpl;
import org.ddmore.mdl.types.DefaultValues;
import org.ddmore.mdl.types.MdlDataType;
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

	public final static String MSG_TARGET_LOCATION = "Target code block is not used inline, please specify location";

	//SIMULATE, ESTIMATE
	final public static Attribute attr_task_algo = new Attribute("algo", MdlDataType.TYPE_VECTOR_STRING, false);
	final public static Attribute attr_task_max = new Attribute("max", MdlDataType.TYPE_NAT, false);
	final public static Attribute attr_task_sig = new Attribute("sig", MdlDataType.TYPE_NAT, false);
	final public static Attribute attr_task_cov = new Attribute("cov", MdlDataType.TYPE_BOOLEAN, false);
	final public static Attribute attr_task_simopt = new Attribute("simopt", MdlDataType.TYPE_VECTOR_STRING, false);
		
	final public static List<Attribute> attrs_task_simulate = Arrays.asList(attr_task_algo, attr_task_max, attr_task_sig, attr_task_cov, attr_task_simopt);
	final public static List<Attribute> attrs_task_estimate = Arrays.asList(attr_task_algo, attr_task_max, attr_task_sig, attr_task_cov, attr_task_simopt);

	//DATA
	final public static Attribute attr_data_ignore = new Attribute("ignore", MdlDataType.TYPE_BOOLEAN, false);
	final public static Attribute attr_data_accept = new Attribute("accept", MdlDataType.TYPE_BOOLEAN, false);
	final public static Attribute attr_data_drop = new Attribute("drop", MdlDataType.TYPE_VECTOR_REF, false);
	
	final public static List<Attribute> attrs_task_data = Arrays.asList(attr_data_ignore, attr_data_accept, attr_data_drop);
	
	//MODEL
	final public static Attribute attr_model_tolrel = new Attribute("tolrel", MdlDataType.TYPE_NAT, false);
	final public static Attribute attr_model_add = new Attribute("add", MdlDataType.TYPE_VECTOR_REF, false);
	final public static Attribute attr_model_remove = new Attribute("remove", MdlDataType.TYPE_VECTOR_REF, false);
	
	final public static List<Attribute> attrs_task_model = Arrays.asList(attr_model_tolrel, attr_model_add, attr_model_remove);
	
	/*SOURCE*/
	final public static Attribute attr_ignore = new Attribute("ignore", MdlDataType.TYPE_STRING, false);
	final public static Attribute attr_inputformat = new Attribute("inputformat", MdlDataType.TYPE_INPUT_FORMAT, true, InputFormatEnum.NONMEM_FORMAT.toString());
	final public static Attribute attr_delimiter = new Attribute("delimiter", MdlDataType.TYPE_STRING, false, ",");
	final public static Attribute attr_header = new Attribute("header", MdlDataType.TYPE_BOOLEAN, false, "false");
	final public static Attribute attr_file = new Attribute("file", MdlDataType.TYPE_STRING, true, DefaultValues.FILE_NAME);
	final public static Attribute attr_script = new Attribute("script", MdlDataType.TYPE_STRING, true, DefaultValues.FILE_NAME);

	final public static List<Attribute> attrs_source = Arrays.asList(attr_inputformat, attr_ignore, 
			attr_delimiter, attr_file, attr_script, attr_header);

	/*TARGET*/
	final public static Attribute attr_req_target = new Attribute("target", MdlDataType.TYPE_TARGET, true, TargetEnum.NMTRAN.toString());
	final public static Attribute attr_location = new Attribute("location", MdlDataType.TYPE_STRING, false);
	final public static Attribute attr_first = new Attribute("first", MdlDataType.TYPE_BOOLEAN, false);
	final public static Attribute attr_last = new Attribute("last", MdlDataType.TYPE_BOOLEAN, false);
	final public static Attribute attr_sameline = new Attribute("sameline", MdlDataType.TYPE_BOOLEAN, false);

	final public static List<Attribute> attrs_target = Arrays.asList(attr_req_target, attr_location, attr_first, attr_last, attr_sameline);
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	HashMap<String, String> exclusive_attrs = new HashMap<String, String>(){
		private static final long serialVersionUID = 4646663049359441357L;
		{
			put(attr_first.getName(), attr_last.getName());
			put(attr_last.getName(), attr_first.getName());
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
		if (tob.getDataBlock() != null){
			statements = tob.getDataBlock().getStatements(); 
			attrNames = getRequiredPropertyNames(tob.getDataBlock());
			ref = MdlPackage.Literals.TASK_OBJECT_BLOCK__DATA_BLOCK;
		}
		checkRequiredProperties(statements, attrNames, ref);
	}
	
	@Check
	//Check required properties in TargetBlock
	public void checkRequiredProperties(TargetBlock t){
		checkRequiredProperties(t.getStatements(), getRequiredPropertyNames(t), 
			MdlPackage.Literals.TARGET_BLOCK__STATEMENTS);
	}

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
					warning(MSG_PROPERTY_UNKNOWN + ": " + p.getPropertyName().getName(), 
					MdlPackage.Literals.PROPERTY_DECLARATION__PROPERTY_NAME,
					MSG_PROPERTY_UNKNOWN, p.getPropertyName().getName());		
				}
				for (Attribute x: knownAttributes){
					if (x.getName().equals(p.getPropertyName().getName())){
						boolean isValid = MdlDataType.validateType(x.getType(), p.getExpression());
						if (!isValid){
							warning(MSG_WRONG_TYPE + 
								": property \"" + p.getPropertyName().getName() + "\" expects value of type " + x.getType().name(), 
								MdlPackage.Literals.PROPERTY_DECLARATION__PROPERTY_NAME,
								MSG_WRONG_TYPE, p.getPropertyName().getName());		
						}
						break;
					}
				}
			}
			return;
		}
	}
	
	@Check
	//Check that data file exists in the project
	public void checkSourceFiles(PropertyDeclaration p){
		EObject container = p.eContainer();
		if (container instanceof SourceBlockImpl){
			SourceBlock b = (SourceBlock) container;
			if (b.getInlineBlock() != null) return;
			if (p.getPropertyName().getName().equals(attr_file.getName()) || 
				p.getPropertyName().getName().equals(attr_script.getName())) {
				String dataPath = MdlPrinter.getInstance().toStr(p.getExpression());
				if (!Utils.isFileExist(b, dataPath)){
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
				}
			}
		}
	}	
	
	@Check
	//External target blocks should have location defined
	public void checkTargetLocation(TargetBlock t){
		EObject container = t.eContainer();
		if (!(container instanceof TaskObjectBlockImpl)){
			for (PropertyDeclaration p: t.getStatements())
				if (p.getPropertyName().getName().equals(attr_location.getName())) return;
			warning(MSG_TARGET_LOCATION, 
				MdlPackage.Literals.TARGET_BLOCK__STATEMENTS,
					MSG_TARGET_LOCATION, t.getIdentifier());
		}
	}
	
	Boolean isPropertyContainer(EObject container){
		return (container instanceof EstimateTaskImpl ||
		container instanceof SimulateTaskImpl ||
		container instanceof ModelBlockImpl ||
		container instanceof EvaluateTaskImpl ||
		container instanceof OptimiseTaskImpl ||
		container instanceof DataBlockImpl ||
		container instanceof SourceBlockImpl ||
		container instanceof TargetBlockImpl); 
	}	
	
	List<Attribute> getAllProperties(EObject obj){
		if (obj instanceof EstimateTaskImpl)
			return attrs_task_estimate;
		if (obj instanceof SimulateTaskImpl)
			return attrs_task_simulate;
		if (obj instanceof DataBlockImpl)
			return attrs_task_data;
		if (obj instanceof ModelBlockImpl)
			return attrs_task_model;
		if (obj instanceof SourceBlockImpl)
			return attrs_source;
		if (obj instanceof TargetBlockImpl)
			return attrs_target;
		return null;
	}
	
	List<String> getRequiredPropertyNames(EObject obj){
		if (obj instanceof EstimateTaskImpl)
			return Utils.getRequiredNames(attrs_task_estimate);
		if (obj instanceof SimulateTaskImpl)
			return Utils.getRequiredNames(attrs_task_simulate);
		if (obj instanceof DataBlockImpl)
			return Utils.getRequiredNames(attrs_task_data);
		if (obj instanceof ModelBlockImpl)
			return Utils.getRequiredNames(attrs_task_model);
		if (obj instanceof SourceBlockImpl)
			return Utils.getRequiredNames(attrs_source);
		if (obj instanceof TargetBlockImpl)
			return Utils.getRequiredNames(attrs_target);
		return null;
	}	
}
