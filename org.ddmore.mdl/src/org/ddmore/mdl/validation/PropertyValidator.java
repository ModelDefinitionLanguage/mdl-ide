package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.TaskObjectBlock;
import org.ddmore.mdl.mdl.impl.BlockStatementImpl;
import org.ddmore.mdl.mdl.impl.DataBlockImpl;
import org.ddmore.mdl.mdl.impl.EstimateTaskImpl;
import org.ddmore.mdl.mdl.impl.ExecuteTaskImpl;
import org.ddmore.mdl.mdl.impl.ModelBlockImpl;
import org.ddmore.mdl.mdl.impl.SimulateTaskImpl;
import org.ddmore.mdl.types.MdlDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;

import com.google.inject.Inject;

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

	//SIMULATE, ESTIMATE
	final public static Attribute attr_task_algo = new Attribute("algo", MdlDataType.TYPE_VECTOR_STRING, false);
	final public static Attribute attr_task_max = new Attribute("max", MdlDataType.TYPE_NAT, false);
	final public static Attribute attr_task_sig = new Attribute("sig", MdlDataType.TYPE_NAT, false);
	final public static Attribute attr_task_cov = new Attribute("cov", MdlDataType.TYPE_BOOLEAN, false);
	final public static Attribute attr_task_simopt = new Attribute("simopt", MdlDataType.TYPE_VECTOR_STRING, false);
		
	final public static List<Attribute> attrs_task_simulate = Arrays.asList(attr_task_algo, attr_task_max, attr_task_sig, attr_task_cov, attr_task_simopt);
	final public static List<Attribute> attrs_task_estimate = Arrays.asList(attr_task_algo, attr_task_max, attr_task_sig, attr_task_cov, attr_task_simopt);

	//EXECUTE
	final public static Attribute attr_task_command = new Attribute("command", MdlDataType.TYPE_STRING, true);
	final public static List<Attribute> attrs_exec_task = Arrays.asList(attr_task_command);
	
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
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	@Check
	public void checkRequiredProperties(TaskObjectBlock tob){
		List<SymbolDeclaration> statements = null;
		List<String> attrNames = null;
		if (tob.getExecuteBlock() != null){
			statements = tob.getExecuteBlock().getStatements();
			attrNames = getRequiredAttributeNames(tob.getExecuteBlock());
		}
		if (tob.getSimulateBlock() != null){
			statements = tob.getSimulateBlock().getStatements();
			attrNames = getRequiredAttributeNames(tob.getSimulateBlock());
		}
		if (tob.getEstimateBlock() != null){
			statements = tob.getEstimateBlock().getStatements();
			attrNames = getRequiredAttributeNames(tob.getEstimateBlock());
		}
		if (tob.getDataBlock() != null){
			statements = tob.getDataBlock().getStatements(); 
			attrNames = getRequiredAttributeNames(tob.getDataBlock());
		}
		
		HashSet<String> properties = new HashSet<String>();
		for (SymbolDeclaration b: statements){
			if (b.getSymbolName() != null){
				if (!properties.contains(b.getSymbolName().getName())){
					properties.add(b.getSymbolName().getName());
				} else {
					warning(MSG_PROPERTY_DEFINED + ": " + b.getSymbolName().getName(), 
							MdlPackage.Literals.ESTIMATE_TASK__IDENTIFIER,
							MSG_PROPERTY_DEFINED, b.getSymbolName().getName());
				}
			}
		}	
		for (String attrName: attrNames){
			if (!properties.contains(attrName)) 
				warning(MSG_PROPERTY_MISSING + ": " + attrName, 
				MdlPackage.Literals.ESTIMATE_TASK__IDENTIFIER, MSG_PROPERTY_MISSING, attrName);
		}
	}

	@Check
	public void checkAllProperties(SymbolDeclaration s){
		EObject container = s.eContainer();
		while (container instanceof BlockStatementImpl)
			container = container.eContainer();
		if (isTaskObjectBlock(container)){
			//check that an argument is recognized
			List<Attribute> knownAttributes = getAllAttributes(container);
			if (knownAttributes != null){
				List<String> attributeNames = Utils.getAllNames(knownAttributes);
				if (!attributeNames.contains(s.getSymbolName().getName())){
					warning(MSG_PROPERTY_UNKNOWN + ": " + s.getSymbolName().getName(), 
					MdlPackage.Literals.SYMBOL_DECLARATION__SYMBOL_NAME,
					MSG_PROPERTY_UNKNOWN, s.getSymbolName().getName());		
				}
				for (Attribute x: knownAttributes){
					if (x.getName().equals(s.getSymbolName().getName())){
						boolean isValid = false;
						if (s.getExpression() != null) 
							isValid = MdlDataType.validateType(x.getType(), s.getExpression());
						if (!isValid){
							warning(MSG_WRONG_TYPE + 
								": property \"" + s.getSymbolName().getName() + "\" expects value of type " + x.getType().name(), 
								MdlPackage.Literals.SYMBOL_DECLARATION__SYMBOL_NAME,
								MSG_WRONG_TYPE, s.getSymbolName().getName());		
						}
						break;
					}
				}
			}
			return;
		}
	}
	
	Boolean isTaskObjectBlock(EObject container){
		return (container instanceof EstimateTaskImpl ||
		container instanceof SimulateTaskImpl ||
		container instanceof ExecuteTaskImpl ||
		container instanceof DataBlockImpl ||
		container instanceof ModelBlockImpl); 
	}
	
	
	ArrayList<String> getParamNames(List<FunctionParameter> params){
		ArrayList<String> names = new ArrayList<String>();
		if (params != null){
			for (FunctionParameter param: params){
				names.add(param.getName());
			}
		}
		return names;
	}

	List<Attribute> getAllAttributes(EObject obj){
		if (obj instanceof EstimateTaskImpl)
			return attrs_task_estimate;
		if (obj instanceof SimulateTaskImpl)
			return attrs_task_simulate;
		if (obj instanceof ExecuteTaskImpl)
			return attrs_exec_task;
		if (obj instanceof DataBlockImpl)
			return attrs_task_data;
		if (obj instanceof ModelBlockImpl)
			return attrs_task_model;
		return null;
	}
	
	List<String> getRequiredAttributeNames(EObject obj){
		if (obj instanceof EstimateTaskImpl)
			return Utils.getRequiredNames(attrs_task_estimate);
		if (obj instanceof SimulateTaskImpl)
			return Utils.getRequiredNames(attrs_task_simulate);
		if (obj instanceof ExecuteTaskImpl)
			return Utils.getRequiredNames(attrs_exec_task);
		if (obj instanceof DataBlockImpl)
			return Utils.getRequiredNames(attrs_task_data);
		if (obj instanceof ModelBlockImpl)
			return Utils.getRequiredNames(attrs_task_model);
		return null;
	}	

}
