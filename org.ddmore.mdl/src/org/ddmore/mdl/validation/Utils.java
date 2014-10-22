package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ddmore.mdl.domain.Attribute;
import org.ddmore.mdl.mdl.ActionBlock;
import org.ddmore.mdl.mdl.AdministrationBlock;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.ArgumentName;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.CompartmentBlock;
import org.ddmore.mdl.mdl.DataDerivedBlock;
import org.ddmore.mdl.mdl.DataInputBlock;
import org.ddmore.mdl.mdl.DeqBlock;
import org.ddmore.mdl.mdl.DesignSpaceBlock;
import org.ddmore.mdl.mdl.DiagBlock;
import org.ddmore.mdl.mdl.EstimationBlock;
import org.ddmore.mdl.mdl.FormalArguments;
import org.ddmore.mdl.mdl.FunctionCall;
import org.ddmore.mdl.mdl.FunctionCallStatement;
import org.ddmore.mdl.mdl.HyperSpaceBlock;
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
import org.ddmore.mdl.mdl.RandomVariableDefinitionBlock;
import org.ddmore.mdl.mdl.SameBlock;
import org.ddmore.mdl.mdl.SamplingBlock;
import org.ddmore.mdl.mdl.SimulationBlock;
import org.ddmore.mdl.mdl.SourceBlock;
import org.ddmore.mdl.mdl.StructuralBlock;
import org.ddmore.mdl.mdl.StructuralParametersBlock;
import org.ddmore.mdl.mdl.StudyDesignBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.mdl.SymbolName;
import org.ddmore.mdl.mdl.SymbolNames;
import org.ddmore.mdl.mdl.TargetBlock;
import org.ddmore.mdl.mdl.VariabilityBlock;
import org.ddmore.mdl.mdl.VariabilityBlockStatement;
import org.ddmore.mdl.mdl.VariabilityParametersBlock;
import org.ddmore.mdl.mdl.impl.ActionBlockImpl;
import org.ddmore.mdl.mdl.impl.AdministrationBlockImpl;
import org.ddmore.mdl.mdl.impl.CompartmentBlockImpl;
import org.ddmore.mdl.mdl.impl.DataDerivedBlockImpl;
import org.ddmore.mdl.mdl.impl.DataInputBlockImpl;
import org.ddmore.mdl.mdl.impl.DeqBlockImpl;
import org.ddmore.mdl.mdl.impl.DesignSpaceBlockImpl;
import org.ddmore.mdl.mdl.impl.DiagBlockImpl;
import org.ddmore.mdl.mdl.impl.EstimationBlockImpl;
import org.ddmore.mdl.mdl.impl.FunctionCallImpl;
import org.ddmore.mdl.mdl.impl.FunctionCallStatementImpl;
import org.ddmore.mdl.mdl.impl.HyperSpaceBlockImpl;
import org.ddmore.mdl.mdl.impl.IndividualVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.InputVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.LibraryBlockImpl;
import org.ddmore.mdl.mdl.impl.MatrixBlockImpl;
import org.ddmore.mdl.mdl.impl.MclObjectImpl;
import org.ddmore.mdl.mdl.impl.ObservationBlockImpl;
import org.ddmore.mdl.mdl.impl.OdeBlockImpl;
import org.ddmore.mdl.mdl.impl.RandomVariableDefinitionBlockImpl;
import org.ddmore.mdl.mdl.impl.SameBlockImpl;
import org.ddmore.mdl.mdl.impl.SamplingBlockImpl;
import org.ddmore.mdl.mdl.impl.SimulationBlockImpl;
import org.ddmore.mdl.mdl.impl.SourceBlockImpl;
import org.ddmore.mdl.mdl.impl.StructuralBlockImpl;
import org.ddmore.mdl.mdl.impl.StructuralParametersBlockImpl;
import org.ddmore.mdl.mdl.impl.StudyDesignBlockImpl;
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
		ObjectName objName = getObjectName(ref);
		if (map.containsKey(objName.getName())){
			List<String> vars = map.get(objName.getName()); 
			int i = 0;
			for (String var: vars){
				if (var.equals(ref.getName())) i++;
				if (i > 1) return true;
			}
		}
		return false;
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

	static ObjectName getObjectName(EObject b){
		return getMclObject(b).getObjectName();
	}

	static MclObject getMclObject(EObject b){
		EObject container = b.eContainer();
		while (!(container instanceof MclObjectImpl)){
			container = container.eContainer();
		}
		MclObject obj = (MclObject)container;
		return obj;
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
	
	public static String getBlockName(EObject obj){
		/*Data object*/
		if (obj instanceof DataInputBlockImpl) return ((DataInputBlock)obj).getIdentifier();
		if (obj instanceof DataDerivedBlockImpl) return ((DataDerivedBlock)obj).getIdentifier();
		if (obj instanceof SourceBlockImpl) return ((SourceBlock)obj).getIdentifier();
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
		if (obj instanceof RandomVariableDefinitionBlockImpl) return ((RandomVariableDefinitionBlock)obj).getIdentifier() ;
		/*Design object*/
		if (obj instanceof StudyDesignBlockImpl) return ((StudyDesignBlock)obj).getIdentifier();  
		if (obj instanceof AdministrationBlockImpl) return ((AdministrationBlock)obj).getIdentifier();
		if (obj instanceof ActionBlockImpl) return ((ActionBlock)obj).getIdentifier();        
		if (obj instanceof SamplingBlockImpl) return ((SamplingBlock)obj).getIdentifier();      
		if (obj instanceof DesignSpaceBlockImpl) return ((DesignSpaceBlock)obj).getIdentifier();   
		if (obj instanceof HyperSpaceBlockImpl) return ((HyperSpaceBlock)obj).getIdentifier(); 		
		/*All objects*/
		if (obj instanceof TargetBlockImpl) return ((TargetBlock)obj).getIdentifier();
		return "";
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
			if (obj.getDesignObject() != null){
				objType = MdlDataType.TYPE_OBJ_REF_DESIGN;
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
