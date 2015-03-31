package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.ddmore.mdl.domain.Attribute;
import org.ddmore.mdl.domain.Variable;
import org.ddmore.mdl.mdl.*;
import org.ddmore.mdl.mdl.impl.*;
import org.ddmore.mdl.types.MdlDataType;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

public class Utils {
	
	//Checks whether a given symbol is declared
	public static boolean isSymbolDeclared(Map<String, List<Variable>> map, SymbolName ref){
		ObjectName objName = getObjectName(ref);
		if (objName != null) 
			if (map.containsKey(objName.getName()))
				for (Variable var: map.get(objName.getName()))
					if (var.getName().equals(ref.getName())) return true;
		return false;
	}
		
	//Checks whether a function is declared more than once
	static boolean isSymbolDeclaredMoreThanOnce(List<Variable> vars, String ref){
		int i = 0;
		for (Variable var: vars){
			if (var.getName().equals(ref)) i++;
			if (i > 1) return true;
		}
		return false;
	}
	
	//Returns a list of attribute names 
	public static List<String> getArgumentNames(Arguments args){
		List<String> argumentNames = new ArrayList<String>();	
		if (args.getNamedArguments() != null)
			for (Argument arg: args.getNamedArguments().getArguments())
				if (!argumentNames.contains(arg.getArgumentName().getName()))
					argumentNames.add(arg.getArgumentName().getName());
		return argumentNames;
	}
	
	//Returns the name of an object containing a given element
	public static ObjectName getObjectName(EObject b){
		return getMclObject(b).getObjectName();
	}

	//Returns the object container for a given element
	public static MclObject getMclObject(EObject b){
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

	//Returns a list of all recognized attribute names
	public static List<String> getAllNames(List<Attribute> attrs){
		List<String> names = new ArrayList<String>();
		if (attrs != null){
			for (Attribute attr: attrs){
				names.add(attr.getName());
			}
		}
		return names;
	}

	//Returns a list of all required attribute names
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
	
	//Returns identifier of an MDL block
	public static String getBlockName(EObject obj){
		/*Data object*/
		if (obj instanceof DataInputBlockImpl) return ((DataInputBlock)obj).getIdentifier();
		if (obj instanceof SourceBlockImpl) return ((SourceBlock)obj).getIdentifier();
		if (obj instanceof DataDerivedBlockImpl) return ((DataDerivedBlockImpl)obj).getIdentifier();
		if (obj instanceof DeclaredVariablesBlockImpl) return ((DeclaredVariablesBlockImpl)obj).getIdentifier();
		/*Parameter object*/
		if (obj instanceof StructuralBlockImpl) return ((StructuralBlock)obj).getIdentifier();	
		if (obj instanceof VariabilityBlockImpl) return ((VariabilityBlock)obj).getIdentifier();
		if (obj instanceof PriorParametersBlockImpl) return ((PriorParametersBlock)obj).getIdentifier();
		/*Model object*/
		if (obj instanceof IndividualVariablesBlockImpl) return ((IndividualVariablesBlock)obj).getIdentifier();
		if (obj instanceof ModelPredictionBlockImpl) return ((ModelPredictionBlock)obj).getIdentifier();
		if (obj instanceof RandomVariableDefinitionBlockImpl) return ((RandomVariableDefinitionBlock)obj).getIdentifier() ;
		if (obj instanceof CovariateDefinitionBlockImpl) return ((CovariateDefinitionBlock)obj).getIdentifier();
		if (obj instanceof VariabilityDefinitionBlockImpl) return ((VariabilityDefinitionBlock)obj).getIdentifier();
		if (obj instanceof StructuralParametersBlockImpl) return ((StructuralParametersBlock)obj).getIdentifier() ;
		if (obj instanceof VariabilityParametersBlockImpl) return ((VariabilityParametersBlock)obj).getIdentifier() ;
		if (obj instanceof EstimationBlockImpl) return ((EstimationBlock)obj).getIdentifier() ;
		if (obj instanceof SimulationBlockImpl) return ((SimulationBlock)obj).getIdentifier() ;
		if (obj instanceof ObservationBlockImpl) return ((ObservationBlock)obj).getIdentifier() ;
		//if (obj instanceof OutputVariablesBlockImpl) return ((OutputVariablesBlock)obj).getIdentifier() ;
		//Subblocks
		if (obj instanceof LibraryBlockImpl) return ((LibraryBlock)obj).getIdentifier() ;
		if (obj instanceof OdeBlockImpl) return ((OdeBlock)obj).getIdentifier() ;
		if (obj instanceof PkMacroBlockImpl) return ((PkMacroBlock)obj).getIdentifier() ;
		/*Task object*/
		if (obj instanceof EstimateTaskImpl) return ((EstimateTask)obj).getIdentifier();	
		if (obj instanceof SimulateTaskImpl) return ((SimulateTask)obj).getIdentifier();	
		if (obj instanceof EvaluateTaskImpl) return ((EvaluateTask)obj).getIdentifier();	
		if (obj instanceof OptimiseTaskImpl) return ((OptimiseTask)obj).getIdentifier();	
		if (obj instanceof DataBlockImpl) return ((DataBlock)obj).getIdentifier();	
		if (obj instanceof ModelBlockImpl) return ((ModelBlock)obj).getIdentifier();	
		/*Design object*/
		if (obj instanceof CovariatesBlockImpl) return ((CovariatesBlock)obj).getIdentifier();  
		if (obj instanceof StudyDesignBlockImpl) return ((StudyDesignBlock)obj).getIdentifier();  
		if (obj instanceof AdministrationBlockImpl) return ((AdministrationBlock)obj).getIdentifier();
		if (obj instanceof ActionBlockImpl) return ((ActionBlock)obj).getIdentifier();        
		if (obj instanceof SamplingBlockImpl) return ((SamplingBlock)obj).getIdentifier();      
		if (obj instanceof DesignSpaceBlockImpl) return ((DesignSpaceBlock)obj).getIdentifier();   
		if (obj instanceof HyperSpaceBlockImpl) return ((HyperSpaceBlock)obj).getIdentifier(); 		
		if (obj instanceof PopulationFeaturesBlockImpl) return ((PopulationFeaturesBlock)obj).getIdentifier(); 	
		/*MOG object*/
		if (obj instanceof ImportObjectBlockImpl) return ((ImportObjectBlock)obj).getIdentifier();  
		if (obj instanceof MappingBlockImpl) return ((MappingBlock)obj).getIdentifier();  
		/*All objects*/
		if (obj instanceof TargetBlockImpl) return ((TargetBlock)obj).getIdentifier();
		return "";
	}
	
	//Locate data/script file in the MDL project
	public static boolean isFileExist(EObject b, String filePath) {
		return getFile(b, filePath).exists();
	}
	
	//Locate data/script file in the MDL project
	public static IFile getFile(EObject b, String filePath) {
		String platformString = b.eResource().getURI().toPlatformString(true);
		IFile modelFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformString));
	    IProject project = modelFile.getProject();
	    IContainer parent = modelFile.getParent();
    	String p = filePath;
		while (p.startsWith("../") && parent != null){
			parent = parent.getParent();
			p = p.substring(3);
		}
        IFile dataFile = project.getFile(parent.getProjectRelativePath() + "/" + p);
		return dataFile;
	}
	
	//Returns a list of MOGs declared in an MDL file
	public static List<MOGObject> getMOGs(Mcl mcl){
		List<MOGObject> mogs = new ArrayList<MOGObject>();
		for (MclObject obj: mcl.getObjects()){
			if (obj.getMogObject() != null)
				mogs.add(obj.getMogObject());
		}
		return mogs;
	}
	
	//Returns a list of objects in a given MOG
	public static List<MclObject> getMOGObjects(MOGObject mog){
		List<MclObject> objects = new ArrayList<MclObject>();
		for (MOGObjectBlock b: mog.getBlocks())
			if (b.getObjectBlock() != null){
				for (ImportObjectStatement st: b.getObjectBlock().getObjects()){
					if (st.getObjectName() != null){
						EObject container = st.getObjectName().eContainer();
						if (container instanceof MclObjectImpl){
							MclObject mclObject = (MclObject)container;
							objects.add(mclObject);
						}
					}
				}
		}
		return objects;
	}
	
	//Finds the first task object in a group of objects (MOG)
	public static TaskObject getTaskObject(List<MclObject> objects){
		for (MclObject mclObject: objects)
			if (mclObject.getTaskObject() != null) return mclObject.getTaskObject();
		return null;
	}	
	
	//Finds the first model object in a group of objects (MOG)
	public static ModelObject getModelObject(List<MclObject> objects){
		for (MclObject mclObject: objects)
			if (mclObject.getModelObject() != null) return mclObject.getModelObject();
		return null;
	}	

	//Finds the first parameter object in a group of objects (MOG)
	public static ParameterObject getParameterObject(List<MclObject> objects){
		for (MclObject mclObject: objects)
			if (mclObject.getParameterObject() != null) return mclObject.getParameterObject();
		return null;
	}	

	//Finds the first data object in a group of objects (MOG)
	public static DataObject getDataObject(List<MclObject> objects){
		for (MclObject mclObject: objects)
			if (mclObject.getDataObject() != null) return mclObject.getDataObject();
		return null;
	}	
	
	//Finds the first design object in a group of objects (MOG)
	public static DesignObject getDesignObject(List<MclObject> objects){
		for (MclObject mclObject: objects)
			if (mclObject.getDesignObject() != null) return mclObject.getDesignObject();
		return null;
	}

	//Creates a list of available MDL objects with types
	public static List<Variable> getDeclaredObjects(Mcl mcl){
		List<Variable> declaredObjects = new ArrayList<Variable>();
		for (MclObject obj: mcl.getObjects()){
			MdlDataType objType = MdlDataType.TYPE_OBJ_REF;
			if (obj.getModelObject() != null)     objType = MdlDataType.TYPE_OBJ_REF_MODEL;
			if (obj.getDataObject() != null)      objType = MdlDataType.TYPE_OBJ_REF_DATA;
			if (obj.getParameterObject() != null) objType = MdlDataType.TYPE_OBJ_REF_PARAM;
			if (obj.getTaskObject() != null)      objType = MdlDataType.TYPE_OBJ_REF_TASK;
			if (obj.getDesignObject() != null)    objType = MdlDataType.TYPE_OBJ_REF_DESIGN;
			if (obj.getMogObject() != null)	      objType = MdlDataType.TYPE_OBJ_REF_MOG;
			Variable newObj = new Variable(obj.getObjectName().getName(), objType);
			declaredObjects.add(newObj);
		}
		return declaredObjects;
	}

	public static Map<String, List<Variable>> getDeclaredVariables(Mcl mcl){
		Map<String, List<Variable>> declaredVariables = new HashMap<String, List<Variable>>();
		for (MclObject obj: mcl.getObjects()){
			List<Variable> varList = getDeclaredVariables(obj);
			if (varList.size() > 0)
		    	declaredVariables.put(obj.getObjectName().getName(), varList);
		}
		return declaredVariables;
	}
	
	public static List<Variable> getDeclaredVariables(MclObject obj){
		List<Variable> varList = new ArrayList<Variable>();
		TreeIterator<EObject> symbolIterator = obj.eAllContents();
		while (symbolIterator.hasNext()) {
			EObject container = symbolIterator.next();			
			if (container instanceof SymbolDeclarationImpl) {
				SymbolDeclaration s = (SymbolDeclaration) container;
				if (s.getSymbolName() != null)
					varList.add(new Variable(s.getSymbolName().getName(), MdlDataType.getExpectedType(s)));
			}
			if (container instanceof FunctionCallStatementImpl) {
				FunctionCallStatement s = (FunctionCallStatement) container;
				if (s.getSymbolName() != null)
					varList.add(new Variable(s.getSymbolName().getName(), MdlDataType.getDerivedType(s.getExpression())));
			}
	    	if (container instanceof FunctionCallImpl){
	    		FunctionCall functCall = (FunctionCall) container;
	    		String functName = functCall.getIdentifier().getName();
    			if (FunctionValidator.libraries.contains(functName))
    				varList.addAll(FunctionValidator.standardFunctions.get(functName).getReturnedVariables(functCall.getArguments()));
	    	}
			if (container instanceof CategoryImpl) {
				Category s = (Category) container;
				if (s.getCategoryName() != null)
					varList.add(new Variable(s.getCategoryName().getName(), MdlDataType.TYPE_INT));
			}
	    	if (container instanceof ImportObjectStatementImpl){
	    		ImportObjectStatement s = (ImportObjectStatement) container;
	    		if (s.getSymbolName() != null){
					varList.add(new Variable(s.getSymbolName().getName(), MdlDataType.getDerivedType(s)));
	    		}
	    	}
		}
		return varList;
	}
	
	public static Map<String, List<Variable>> getDeclaredVariables(MOGObject mog){
		Map<String, List<Variable>> declaredVariables = new HashMap<String, List<Variable>>();
		for (MclObject obj: getMOGObjects(mog)){
			List<Variable> varList = getDeclaredVariables(obj);
			if (varList.size() > 0)
		    	declaredVariables.put(obj.getObjectName().getName(), varList);
		}
		return declaredVariables;
	}
	
	public static List<String> getDependencies(Expression e){
		List<String> dependencies = new ArrayList<String>();
		TreeIterator<EObject> iterator = e.eResource().getAllContents();
	    while (iterator.hasNext()){
	    	EObject obj = iterator.next();
	    	if (obj instanceof SymbolNameImpl){
	    		SymbolName s = (SymbolName) obj;
	    		if (!dependencies.contains(s.getName())) dependencies.add(s.getName());
	    	}
	    }
	    return dependencies;
	}

	
	public static HashSet<String> getDerivativeVariables(ModelObject m){
		HashSet<String> deriv_vars = new HashSet<String>();
		for (ModelObjectBlock b: m.getBlocks()){
			if (b.getModelPredictionBlock() != null){
				for (ModelPredictionBlockStatement st: b.getModelPredictionBlock().getStatements()){
					if (st.getOdeBlock() != null){
						for (SymbolDeclaration s: st.getOdeBlock().getVariables()){
							if (s.getSymbolName() != null && s.getList() != null){
								if (s.getList().getArguments().getNamedArguments() != null){
					    			for (Argument arg: s.getList().getArguments().getNamedArguments().getArguments()){
					    				if (arg.getArgumentName().getName().equals(AttributeValidator.attr_deriv.getName())){
					    					if (!deriv_vars.contains(s.getSymbolName().getName()))
					    						deriv_vars.add(s.getSymbolName().getName());
					    				}
					    			}
				    			}
				    		}
						}
					}
				}
			}
		}
		return deriv_vars;
	}
	
	public static List<Variable> getExternalLibraryVariables(SymbolName ref){
		TreeIterator<EObject> iterator = ref.eResource().getAllContents();
	    while (iterator.hasNext()){
	    	EObject obj = iterator.next();
	    	if (obj instanceof FunctionCallStatementImpl){
	    		FunctionCallStatement s = (FunctionCallStatement) obj;
	    		if (s.getSymbolName().getName().equals(ref.getName())) {	    			
	    			//Compare reference with returned variables of functions or libraries
	    			String functName = s.getExpression().getIdentifier().getName();
	    			if (FunctionValidator.libraries.contains(functName))
	    				return FunctionValidator.standardFunctions.get(functName).getReturnedVariables(s.getExpression().getArguments());
	    		}
	    	}
	    }
	    return null;
	}
	
	private static MclObject getImportedObjectByAlias(SymbolName ref){
		MclObject obj = getMclObject(ref);
		if (obj.getMogObject() != null){
			for (MOGObjectBlock b: obj.getMogObject().getBlocks()){
				if (b.getObjectBlock() != null){
					for (ImportObjectStatement s: b.getObjectBlock().getObjects()){
						if (s.getSymbolName() != null && s.getSymbolName().getName().equals(ref.getName())) {	    			
			    			EObject container = s.getObjectName().eContainer();
			    			if (container instanceof MclObjectImpl){
			    				MclObject o = (MclObject)container;
			    				return o;
			    			}
			    		}
					}
				}
			}
		}
	    return null;
	}
	
	public static List<Variable> getImportedVariablesByObjectAlias(SymbolName ref){
		MclObject o = getImportedObjectByAlias(ref);
		if (o != null) return Utils.getDeclaredVariables(o);
	    return null;
	}
	
	/*
	public static List<Argument> getListArguments(FullyQualifiedArgumentName ref){
		List<Argument> args = new LinkedList<Argument>();
		TreeIterator<EObject> iterator = ref.eResource().getAllContents();
	    while (iterator.hasNext()){
	    	EObject obj = iterator.next();
	    	if (obj instanceof SymbolDeclarationImpl){
	    		SymbolDeclaration s = (SymbolDeclaration) obj;
	    		if (s.getSymbolName() != null){
		    		if (s.getSymbolName().getName().equals(ref.getParent().getName())) {
		    			if (s.getList() != null){
		    				Arguments arguments = s.getList().getArguments();
		       				if (arguments != null)
			       				for (Argument x: arguments.getArguments())
	           						args.add(x);
		    			}
		    			if (s.getRandomList() != null){
		    				Arguments arguments = s.getRandomList().getArguments();
		       				if (arguments != null)
			       				for (Argument x: arguments.getArguments())
	           						args.add(x);
		    			}
		    		}
	    		}
	    	}
	    }
	    return args;
	}*/
	
	public static <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
		  for (E e : enumClass.getEnumConstants()) {
		    if(e.name().equals(value)) { return true; }
		  }
		  return false;
	}

	public static <E extends Enum<E>> E getEnum(String value, Class<E> enumClass) {
		  for (E e : enumClass.getEnumConstants()) {
		    if(e.name().equals(value)) { return e; }
		  }
		  return null;
	}
	
	public static String getMatchingVariable(MOGObject mog, SymbolName s){
		/*Check explicit mapping in MOG*/
		if (mog != null){
			List<MclObject> objects = getMOGObjects(mog);
			ObjectName dObjName = ((MclObject)getDataObject(objects).eContainer()).getObjectName();
			ObjectName mObjName = ((MclObject)getModelObject(objects).eContainer()).getObjectName();
			/*Explicit mapping in the MOG*/
			for (MOGObjectBlock b: mog.getBlocks()){
				if (b.getMappingBlock() != null){
					for (MappingBlockStatement m: b.getMappingBlock().getMappings()) {
						MclObject o1 = getImportedObjectByAlias(m.getObj1().getParent());
						MclObject o2 = getImportedObjectByAlias(m.getObj2().getParent());
						if (o1 != null && o2 != null && m.getObj1().getSymbolName() != null && m.getObj2().getSymbolName() != null){
							String var1 = m.getObj1().getSymbolName().getName();
							String var2 = m.getObj2().getSymbolName().getName();
							if (o1.getObjectName().getName().equals(dObjName.getName()) 
								&& var1.equals(s.getName())
								&& o2.getObjectName().getName().equals(mObjName.getName())) 
								return var2;
							if (o2.getObjectName().getName().equals(dObjName.getName()) 
								&& var2.equals(s.getName())
								&& o1.getObjectName().getName().equals(mObjName.getName())) 
								return var1;
						}
					}
				}
			}
		}
		return null;
	}

}
