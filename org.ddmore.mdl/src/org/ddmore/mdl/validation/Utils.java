package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ddmore.mdl.domain.Attribute;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.ArgumentName;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.CompartmentBlock;
import org.ddmore.mdl.mdl.DataDerivedBlock;
import org.ddmore.mdl.mdl.DataInputBlock;
import org.ddmore.mdl.mdl.DeqBlock;
import org.ddmore.mdl.mdl.DiagBlock;
import org.ddmore.mdl.mdl.EstimationBlock;
import org.ddmore.mdl.mdl.FormalArguments;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.FunctionCallStatement;
import org.ddmore.mdl.mdl.IndividualVariablesBlock;
import org.ddmore.mdl.mdl.InputVariablesBlock;
import org.ddmore.mdl.mdl.LibraryBlock;
import org.ddmore.mdl.mdl.MOGObject;
import org.ddmore.mdl.mdl.MatrixBlock;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.ObjectName;
import org.ddmore.mdl.mdl.ObservationBlock;
import org.ddmore.mdl.mdl.OdeBlock;
import org.ddmore.mdl.mdl.SameBlock;
import org.ddmore.mdl.mdl.SimulationBlock;
import org.ddmore.mdl.mdl.StructuralBlock;
import org.ddmore.mdl.mdl.StructuralParametersBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.SymbolName;
import org.ddmore.mdl.mdl.SymbolNames;
import org.ddmore.mdl.mdl.TargetBlock;
import org.ddmore.mdl.mdl.VariabilityBlock;
import org.ddmore.mdl.mdl.VariabilityBlockStatement;
import org.ddmore.mdl.mdl.VariabilityParametersBlock;
import org.ddmore.mdl.mdl.impl.CompartmentBlockImpl;
import org.ddmore.mdl.mdl.impl.DataDerivedBlockImpl;
import org.ddmore.mdl.mdl.impl.DataInputBlockImpl;
import org.ddmore.mdl.mdl.impl.DeqBlockImpl;
import org.ddmore.mdl.mdl.impl.DiagBlockImpl;
import org.ddmore.mdl.mdl.impl.EstimationBlockImpl;
import org.ddmore.mdl.mdl.impl.FunctionCallImpl;
import org.ddmore.mdl.mdl.impl.FunctionCallStatementImpl;
import org.ddmore.mdl.mdl.impl.IndividualVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.InputVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.LibraryBlockImpl;
import org.ddmore.mdl.mdl.impl.MatrixBlockImpl;
import org.ddmore.mdl.mdl.impl.MclObjectImpl;
import org.ddmore.mdl.mdl.impl.ObservationBlockImpl;
import org.ddmore.mdl.mdl.impl.OdeBlockImpl;
import org.ddmore.mdl.mdl.impl.SameBlockImpl;
import org.ddmore.mdl.mdl.impl.SimulationBlockImpl;
import org.ddmore.mdl.mdl.impl.StructuralBlockImpl;
import org.ddmore.mdl.mdl.impl.StructuralParametersBlockImpl;
import org.ddmore.mdl.mdl.impl.SymbolDeclarationImpl;
import org.ddmore.mdl.mdl.impl.TargetBlockImpl;
import org.ddmore.mdl.mdl.impl.VariabilityBlockImpl;
import org.ddmore.mdl.mdl.impl.VariabilityBlockStatementImpl;
import org.ddmore.mdl.mdl.impl.VariabilityParametersBlockImpl;
import org.ddmore.mdl.types.MdlDataType;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import eu.ddmore.converter.mdlprinting.MdlPrinter;

public class Utils {
	
	//Checks whether a given identifier is declared
	static boolean isIdentifierDeclared(Map<String, List<String>> map, String id, ObjectName objName){
		if (objName != null) 
			if (map.containsKey(objName.getName()))
				if (map.get(objName.getName()).contains(id)) return true;
		return false; 
	}
	
	//Checks whether a given symbol is declared
	static boolean isSymbolDeclared(Map<String, List<String>> map, SymbolName ref){
		ObjectName objName = getObjectName(ref);
		if (objName != null) 
			if (map.containsKey(objName.getName()))
				if (map.get(objName.getName()).contains(ref.getName())) return true;		
		return false;
	}
		
	static boolean isSymbolDeclared(Map<String, List<String>> map, SymbolName ref, List<MOGObject> mogs){
		ObjectName objName = getObjectName(ref);
		if (objName != null){
			for (MOGObject mog: mogs){
				for (ObjectName o: mog.getObjects()){
					if (o.getName().equals(objName.getName()))
						return isIdentifierDeclared(map, ref.getName(), objName);
				}
			}
		}
		return false;
	}
	
	//Checks whether a function is declared more than once
	static boolean isSymbolDeclaredMoreThanOnce(Map<String, List<String>> map, SymbolName ref){
		int i = 0;
		ObjectName objName = Utils.getObjectName(ref);
		if (map.containsKey(objName.getName())){
			List<String> functions = map.get(objName.getName()); 
			for (String func: functions){
				if (func.equals(ref.getName())) i++;
				if (i > 1) return true;
			}
		}
		return false;
	}
	
	//Add symbol to a list of known symbols
	public static void addSymbol(List<String> list, BlockStatement st){
		TreeIterator<EObject> iterator = st.eAllContents();
		while (iterator.hasNext()){
		EObject obj = iterator.next();
		   	if (obj instanceof SymbolDeclarationImpl){
		   		SymbolDeclaration s = (SymbolDeclaration)obj;
				if (s.getSymbolName() != null){
					list.add(s.getSymbolName().getName());
				}
		   	}
		}
	}
	
	//The same as previous, but does not add repeated conditionally developed variables to avoid double declaration warning 
	public static void addSymbolNoRepeat(List<String> list, BlockStatement st){
		TreeIterator<EObject> iterator = st.eAllContents();
	    while (iterator.hasNext()){
	    	EObject obj = iterator.next();
	    	if (obj instanceof SymbolDeclarationImpl){
	    		SymbolDeclaration s = (SymbolDeclaration)obj;
				if (s.getSymbolName() != null){
					if (!list.contains(s.getSymbolName().getName())) 
						list.add(s.getSymbolName().getName());
				}
	    	}
	    }
	}
	

	public static List<String> getArgumentNames(Arguments args){
		List<String> argumentNames = new ArrayList<String>();	
		if (args.getArguments() != null)
			for (Argument arg: args.getArguments())
				if (arg.getArgumentName() != null)
					if (!argumentNames.contains(arg.getArgumentName().getName()))
						argumentNames.add(arg.getArgumentName().getName());
		return argumentNames;
	}
	
	public static List<String> getSymbolNames(SymbolNames names){
		List<String> symbolNames = new ArrayList<String>();	
		addSymbol(symbolNames, names);
		return symbolNames;
	}
	
	//Add a symbol to a list of known symbols
	private static void addSymbol(List<String> list, Arguments args){
		if (args.getArguments() != null)	
			for (Argument arg: args.getArguments())
				if (arg.getArgumentName() != null)
					list.add(arg.getArgumentName().getName());
	}
	
	static void addSymbol(List<String> list, FormalArguments args){
		if (args != null)
			for (ArgumentName id: args.getArguments())
				list.add(id.getName());
	}
	
	private static void addSymbol(List<String> list, SymbolNames args){
		if (args != null)
			for (SymbolName id: args.getSymbolNames())
				list.add(id.getName());
	}

	//Return value of an attribute with a given name
	static String getAttributeValue(Arguments a, String attrName){
		for (Argument arg: a.getArguments())
			if (arg.getArgumentName() != null && arg.getArgumentName().getName().equals(attrName)){
				if (arg.getExpression().getExpression() != null)
					return MdlPrinter.getInstance().toStr(arg.getExpression().getExpression());
			}
		return "";  
	}	
	
	//Prints value of an attribute with a given name
	static String getAttributeValue(Argument arg){
		if (arg.getExpression().getExpression() != null)
			return MdlPrinter.getInstance().toStr(arg.getExpression().getExpression());
		return "";
	}	
	
	//Note: don't use for reference checking
	static ObjectName getObjectName(EObject b){
		EObject container = b.eContainer();
		while (!(container instanceof MclObjectImpl)){
			container = container.eContainer();
		}
		MclObject obj = (MclObject)container;
		return obj.getObjectName();
	}
	
	//Print a given list (used for reporting errors and for testing)
	static String printList(List<String> list){
		String res = "{ ";
		for (String str: list) res += str + "; ";
		return res + "}";
	}
	
	public static List<String> getAllNames(List<Attribute> attrs){
		List<String> names = new ArrayList<String>();
		if (attrs != null){
			for (Attribute attr: attrs){
				names.add(attr.getName());
			}
		}
		return names;
	}

	public static List<String> getRequiredNames(List<Attribute> attrs){
		ArrayList<String> names = new ArrayList<String>();
		if (attrs != null){
			for (Attribute attr: attrs){
				if (attr.isMandatory())
					names.add(attr.getName());
			}
		}
		return names;
	}
	
	//Look for the parent block containing lists
	public static EObject findListContainer(EObject obj){
		EObject container = obj;
		while (!isListContainer(container)){
			if (container instanceof MclObjectImpl) return null;
			container = container.eContainer();
		}
		return container;
	}
	
	//Note: we do not count FunctionCalls as containers of arguments, function calls are validated separately 
	public static Boolean isListContainer(EObject obj){
		if (
			//Data object	
			obj instanceof DataInputBlockImpl ||
			obj instanceof DataDerivedBlockImpl ||
			//Model object
			obj instanceof InputVariablesBlockImpl ||
			obj instanceof IndividualVariablesBlockImpl ||
			obj instanceof LibraryBlockImpl ||
			obj instanceof OdeBlockImpl ||
			obj instanceof DeqBlockImpl ||
			obj instanceof CompartmentBlockImpl ||
			obj instanceof EstimationBlockImpl ||
			obj instanceof SimulationBlockImpl ||
			obj instanceof ObservationBlockImpl ||
			obj instanceof StructuralParametersBlockImpl || 
			obj instanceof VariabilityParametersBlockImpl ||
			//Parameter object
			obj instanceof StructuralBlockImpl ||
			obj instanceof VariabilityBlockImpl ||
			obj instanceof MatrixBlockImpl || obj instanceof DiagBlockImpl || obj instanceof SameBlockImpl ||
			//All objects
			obj instanceof TargetBlockImpl) return true;
		return false;	
	}

	public static String getBlockName(EObject obj){
		/*Data object*/
		if (obj instanceof DataInputBlockImpl) return ((DataInputBlock)obj).getIdentifier();
		if (obj instanceof DataDerivedBlockImpl) return ((DataDerivedBlock)obj).getIdentifier();
		/*Parameter object*/
		if (obj instanceof StructuralBlockImpl) return ((StructuralBlock)obj).getIdentifier();	
		if (obj instanceof VariabilityBlockImpl) return ((VariabilityBlock)obj).getIdentifier();
		if (obj instanceof MatrixBlockImpl) return ((MatrixBlock)obj).getIdentifier();	
		if (obj instanceof DiagBlockImpl) return ((DiagBlock)obj).getIdentifier() ;	
		if (obj instanceof SameBlockImpl) return ((SameBlock)obj).getIdentifier();
		/*Model object*/
		if (obj instanceof InputVariablesBlockImpl) return ((InputVariablesBlock)obj).getIdentifier();
		if (obj instanceof IndividualVariablesBlockImpl) return ((IndividualVariablesBlock)obj).getIdentifier();
		if (obj instanceof LibraryBlockImpl) return ((LibraryBlock)obj).getIdentifier() ;
		if (obj instanceof OdeBlockImpl) return ((OdeBlock)obj).getIdentifier() ;
		if (obj instanceof DeqBlockImpl) return ((DeqBlock)obj).getIdentifier() ;
		if (obj instanceof CompartmentBlockImpl) return ((CompartmentBlock)obj).getIdentifier() ;
		if (obj instanceof EstimationBlockImpl) return ((EstimationBlock)obj).getIdentifier() ;
		if (obj instanceof SimulationBlockImpl) return ((SimulationBlock)obj).getIdentifier() ;
		if (obj instanceof ObservationBlockImpl) return ((ObservationBlock)obj).getIdentifier() ;
		if (obj instanceof StructuralParametersBlockImpl) return ((StructuralParametersBlock)obj).getIdentifier() ;
		if (obj instanceof VariabilityParametersBlockImpl) return ((VariabilityParametersBlock)obj).getIdentifier() ;
		/*All objects*/
		if (obj instanceof TargetBlockImpl) return ((TargetBlock)obj).getIdentifier();
		return "";
	}
	
	public static boolean isPassedByName(Arguments args){
		if (args != null){
			int nNames = 0; 
			for (Argument arg: args.getArguments()){
				if (arg.getArgumentName() != null) nNames++;
			}
			int count = args.getArguments().size();
			return (nNames == count && count != 0);
		}
		return false;
	}
	
	public static boolean isPassedByPlace(Arguments args){
		if (args != null){
			int nNames = 0; 
			for (Argument arg: args.getArguments()){
				if (arg.getArgumentName() != null) nNames++;
			}
			return (nNames == 0);
		}
		return true;
	}
	
	//Locate data/script file in the MDL project
	public static boolean isFileExist(EObject b, String filePath) {
		String platformString = b.eResource().getURI().toPlatformString(true);
		IFile modelFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformString));
		IContainer parent = modelFile.getParent();
		String p = filePath;
		while (p.startsWith("../") && parent != null){
			parent = parent.getParent();
			p = p.substring(3);
		}
		IPath path = new Path(p);
		if (path.isValidPath(p) && parent != null) {
			IFile requestedFile = parent.getFile(path);
			if (requestedFile.exists()) return true;
		}
		return false;
	}
	
	public static Map<String, MdlDataType> getDeclaredObjects(Mcl mcl){
		Map<String, MdlDataType> declaredObjects = new HashMap<String, MdlDataType>();
		for (MclObject obj: mcl.getObjects()){
			MdlDataType objType = MdlDataType.TYPE_OBJ_REF;
			if (obj.getModelObject() != null){
				objType = MdlDataType.TYPE_OBJ_REF_MODEL;
			}
			if (obj.getDataObject() != null){
				objType = MdlDataType.TYPE_OBJ_REF_DATA;
			}
			if (obj.getParameterObject() != null){
				objType = MdlDataType.TYPE_OBJ_REF_PARAM;
			}
			if (obj.getTaskObject() != null){
				objType = MdlDataType.TYPE_OBJ_REF_TASK;
			}
			declaredObjects.put(obj.getObjectName().getName(), objType);
		}
		return declaredObjects;
	}
	
	public static List<MOGObject> getMOGs(Mcl mcl){
		List<MOGObject> mogs = new ArrayList<MOGObject>();
		for (MclObject obj: mcl.getObjects()){
			if (obj.getMogObject() != null)
				mogs.add(obj.getMogObject());
		}
		return mogs;
	}
	
	public static Map<String, List<String>> getDeclaredSymbols(Mcl mcl){
		Map<String, List<String>> declaredVariables = new HashMap<String, List<String>>();
		for (MclObject obj: mcl.getObjects()){
			List<String> varList = getDeclaredSymbols(obj);
			if (varList.size() > 0)
		    	declaredVariables.put(obj.getObjectName().getName(), varList);
		}
		return declaredVariables;
	}
	
	public static List<String> getDeclaredSymbols(MclObject obj){
		List<String> varList = new ArrayList<String>();
		TreeIterator<EObject> symbolIterator = obj.eAllContents();
		while (symbolIterator.hasNext()) {
			EObject container = symbolIterator.next();
			if (container instanceof SymbolDeclarationImpl) {
				SymbolDeclaration s = (SymbolDeclaration) container;
				if (s.getSymbolName() != null)
					varList.add(s.getSymbolName().getName());
			}
			if (container instanceof FunctionCallStatementImpl) {
				FunctionCallStatement s = (FunctionCallStatement) container;
				if (s.getSymbolName() != null)
					varList.add(s.getSymbolName().getName());
			}
			//ParameterObject -> VARIABILITY, matrix, diag, same
	    	if (container instanceof VariabilityBlockStatementImpl){
				VariabilityBlockStatement s = (VariabilityBlockStatement)container;
				if (s.getParameter() != null && s.getParameter().getSymbolName() != null)
					varList.add(s.getParameter().getSymbolName().getName());
				if (s.getMatrixBlock() != null)
					Utils.addSymbol(varList, s.getMatrixBlock().getParameters());
				if (s.getDiagBlock() != null)
					Utils.addSymbol(varList, s.getDiagBlock().getParameters());
				if (s.getSameBlock() != null)
					Utils.addSymbol(varList, s.getSameBlock().getParameters());
			}
	    	if (container instanceof FunctionCallImpl){
	    		FunctionCall functCall = (FunctionCall) container;
	    		String functName = functCall.getIdentifier().getName();
    			if (FunctionValidator.libraries.contains(functName))
    				varList.addAll(FunctionValidator.standardFunctions.get(functName).getReturnedVariableNames());
	    	}
		}
		return varList;
	}
}
