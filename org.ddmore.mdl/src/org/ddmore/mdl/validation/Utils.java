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
import org.ddmore.mdl.mdl.ConditionalStatement;
import org.ddmore.mdl.mdl.DataDerivedBlock;
import org.ddmore.mdl.mdl.DataInputBlock;
import org.ddmore.mdl.mdl.DeqBlock;
import org.ddmore.mdl.mdl.DiagBlock;
import org.ddmore.mdl.mdl.EstimationBlock;
import org.ddmore.mdl.mdl.FormalArguments;
import org.ddmore.mdl.mdl.FullyQualifiedFunctionName;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.FunctionCallStatement;
import org.ddmore.mdl.mdl.FunctionName;
import org.ddmore.mdl.mdl.InputVariablesBlock;
import org.ddmore.mdl.mdl.LibraryBlock;
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
import org.ddmore.mdl.mdl.Symbol;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.SymbolName;
import org.ddmore.mdl.mdl.SymbolNames;
import org.ddmore.mdl.mdl.Symbols;
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
import org.ddmore.mdl.mdl.impl.InputVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.LibraryBlockImpl;
import org.ddmore.mdl.mdl.impl.ListImpl;
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
	static boolean isIdentifierDeclared(Map<String, List<String>> map, String id, String objName){
		if (objName != null) 
			if (map.containsKey(objName))
				if (map.get(objName).contains(id)) return true;
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
		
	static boolean isSymbolDeclared(Map<String, List<String>> map, SymbolName ref, List<ModellingObjectGroup> mogs){
		ObjectName objName = getObjectName(ref);
		if (objName != null){
			if (isIdentifierDeclared(map, ref.getName(), objName.getName())) return true;
			for (ModellingObjectGroup mog: mogs){
				if (mog.getObjectNames().contains(objName.getName()))
					return 
						isIdentifierDeclared(map, ref.getName(), mog.getModelObjName()) || 
						isIdentifierDeclared(map, ref.getName(), mog.getDataObjName()) || 
						isIdentifierDeclared(map, ref.getName(), mog.getParamObjName()) ||
						isIdentifierDeclared(map, ref.getName(), mog.getTaskObjName()); 
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
	
	//Checks whether a given function is declared
	static boolean isFunctionDeclared(Map<String, List<String>> map, FullyQualifiedFunctionName ref){
		ObjectName objName = ref.getObject();
		if (objName == null) objName = getObjectName(ref);
		if (objName != null) 
			if (map.containsKey(objName.getName()))
				if (map.get(objName.getName()).contains(ref.getFunction().getName())) return true;	
		return false;
	}
	
	//Checks whether a given function is declared
	static boolean isFunctionDeclared(Map<String, List<String>> map, FunctionName ref){
		ObjectName objName = getObjectName(ref);
		if (objName != null) 
			if (map.containsKey(objName.getName()))
				if (map.get(objName.getName()).contains(ref.getName())) return true;
		return false;
	}
	
	//Checks whether a function is declared more than once
	static boolean isFunctionDeclaredMoreThanOnce(Map<String, List<String>> map, FunctionName ref){
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
	
	//Check whether the list of attributes contains a given attribute
	static boolean containsAttribute(Arguments args, String attrName){
		for (Argument arg: args.getArguments()){
			if (arg.getArgumentName() != null)
				if (arg.getArgumentName().getName().equals(attrName)) return true;
		}
		return false;
	}

	//Add a symbol to a list of known symbols
	public static void addSymbol(List<String> list, BlockStatement st){
		if (st != null){
			if (st.getSymbol() != null && st.getSymbol().getSymbolName() != null)
				list.add(st.getSymbol().getSymbolName().getName());
			//conditional declarations
			if (st.getStatement() != null){
				ConditionalStatement e = st.getStatement();
				addSymbol(list, e);
			}
		}
	}
	
	//The same as previous, but does not add repeated conditionally developed variables to avoid double declaration warning 
	public static void addSymbolNoRepeat(List<String> list, BlockStatement st){
		if (st != null){
			if (st.getSymbol() != null && st.getSymbol().getSymbolName() != null)
				if (!list.contains(st.getSymbol().getSymbolName().getName())) 
					list.add(st.getSymbol().getSymbolName().getName());
			if (st.getStatement() != null)
				addSymbol(list, st.getStatement());
		}
	}
	
	//Weak validation of conditionally declared references - a variable is declared if it is declared in some branch 
	public static void addSymbol(List<String> list, ConditionalStatement e){
		if (e.getIfStatement() != null)
			addSymbolNoRepeat(list, e.getIfStatement());
		if (e.getElseStatement() != null)
			addSymbolNoRepeat(list, e.getElseStatement());
		if (e.getIfBlock() != null) 
			for (BlockStatement b: e.getIfBlock().getStatements())
				addSymbolNoRepeat(list, b);
		if (e.getElseBlock() !=null)
			for (BlockStatement b: e.getElseBlock().getStatements())
				addSymbolNoRepeat(list, b);
	}	
	
	//Add a symbol to a list of known symbols
	public static void addSymbol(List<String> list, Arguments args){
		if (args != null)
			if (args.getArguments() != null)	
				for (Argument arg: args.getArguments())
					if (arg.getArgumentName() != null)
						list.add(arg.getArgumentName().getName());
	}
	
	public static void addSymbolNoRepeat(List<String> list, Arguments args){
		if (args != null)
			if (args.getArguments() != null)
				for (Argument arg: args.getArguments())
					if (arg.getArgumentName() != null)
						if (!list.contains(arg.getArgumentName().getName()))
							list.add(arg.getArgumentName().getName());
	}

	public static void addSymbol(List<String> list, FormalArguments args){
		if (args != null)
			for (ArgumentName id: args.getArguments())
				list.add(id.getName());
	}
	
	//Add a symbol to a list of known symbols
	public static void addSymbol(List<String> list, Symbols args){
		if (args != null)
			if (args.getSymbols() != null)	
				for (Symbol arg: args.getSymbols())
					if (arg.getSymbolName() != null)
						list.add(arg.getSymbolName().getName());
	}

	public static void addSymbol(List<String> list, SymbolNames args){
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
	
	public static List<ModellingObjectGroup> getMOGs(Mcl mcl){
		List<ModellingObjectGroup> mogs = new ArrayList<ModellingObjectGroup>();
		Map<String, MclObject> objects = new HashMap<String, MclObject>();
		for (MclObject o: mcl.getObjects()){
			objects.put(o.getObjectName().getName(), o);	
		}
		for (MclObject obj: mcl.getObjects()){
			if (obj.getMogObject() != null){
				String mObjName = null, dObjName = null, pObjName = null, tObjName = null;
				for (ObjectName o: obj.getMogObject().getObjects()){
					if (objects.containsKey(o.getName())){
						if (objects.get(o.getName()).getDataObject() != null) dObjName = o.getName();
						if (objects.get(o.getName()).getModelObject() != null) mObjName = o.getName();
						if (objects.get(o.getName()).getParameterObject() != null) pObjName = o.getName();
						if (objects.get(o.getName()).getTaskObject() != null) tObjName = o.getName();
					}
				}
				if ((mObjName != null) && (dObjName != null) && (pObjName != null) && (tObjName != null)){
					ModellingObjectGroup mog = new ModellingObjectGroup(mObjName, pObjName, dObjName, tObjName);
					mogs.add(mog);
				}
			}
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
	
	public static boolean isNestedList(Arguments args){
		return (args.eContainer() instanceof ListImpl);
	}
	
}
