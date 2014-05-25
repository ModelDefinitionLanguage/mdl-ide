package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.ArgumentName;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.ConditionalStatement;
import org.ddmore.mdl.mdl.DataDerivedBlock;
import org.ddmore.mdl.mdl.DataInputBlock;
import org.ddmore.mdl.mdl.DesignBlock;
import org.ddmore.mdl.mdl.DiagBlock;
import org.ddmore.mdl.mdl.EstimationBlock;
import org.ddmore.mdl.mdl.FormalArguments;
import org.ddmore.mdl.mdl.FullyQualifiedFunctionName;
import org.ddmore.mdl.mdl.FunctionName;
import org.ddmore.mdl.mdl.SymbolName;
import org.ddmore.mdl.mdl.ImportBlock;
import org.ddmore.mdl.mdl.ImportedFunction;
import org.ddmore.mdl.mdl.InputVariablesBlock;
import org.ddmore.mdl.mdl.LibraryBlock;
import org.ddmore.mdl.mdl.MatrixBlock;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.ObjectName;
import org.ddmore.mdl.mdl.ObservationBlock;
import org.ddmore.mdl.mdl.OdeBlock;
import org.ddmore.mdl.mdl.SameBlock;
import org.ddmore.mdl.mdl.SimulationBlock;
import org.ddmore.mdl.mdl.SourceBlock;
import org.ddmore.mdl.mdl.StructuralBlock;
import org.ddmore.mdl.mdl.StructuralParametersBlock;
import org.ddmore.mdl.mdl.Symbol;
import org.ddmore.mdl.mdl.SymbolNames;
import org.ddmore.mdl.mdl.Symbols;
import org.ddmore.mdl.mdl.TargetBlock;
import org.ddmore.mdl.mdl.VariabilityBlock;
import org.ddmore.mdl.mdl.VariabilityParametersBlock;
import org.ddmore.mdl.mdl.impl.DataDerivedBlockImpl;
import org.ddmore.mdl.mdl.impl.DataInputBlockImpl;
import org.ddmore.mdl.mdl.impl.DesignBlockImpl;
import org.ddmore.mdl.mdl.impl.DesignBlockStatementImpl;
import org.ddmore.mdl.mdl.impl.DiagBlockImpl;
import org.ddmore.mdl.mdl.impl.EstimationBlockImpl;
import org.ddmore.mdl.mdl.impl.ImportBlockImpl;
import org.ddmore.mdl.mdl.impl.ImportedFunctionImpl;
import org.ddmore.mdl.mdl.impl.InputVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.LibraryBlockImpl;
import org.ddmore.mdl.mdl.impl.MatrixBlockImpl;
import org.ddmore.mdl.mdl.impl.MclObjectImpl;
import org.ddmore.mdl.mdl.impl.ObservationBlockImpl;
import org.ddmore.mdl.mdl.impl.OdeBlockImpl;
import org.ddmore.mdl.mdl.impl.SameBlockImpl;
import org.ddmore.mdl.mdl.impl.SimulationBlockImpl;
import org.ddmore.mdl.mdl.impl.SourceBlockImpl;
import org.ddmore.mdl.mdl.impl.StructuralBlockImpl;
import org.ddmore.mdl.mdl.impl.StructuralParametersBlockImpl;
import org.ddmore.mdl.mdl.impl.SymbolNameImpl;
import org.ddmore.mdl.mdl.impl.TargetBlockImpl;
import org.ddmore.mdl.mdl.impl.VariabilityBlockImpl;
import org.ddmore.mdl.mdl.impl.VariabilityParametersBlockImpl;
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
	static boolean isIdentifierDeclared(HashMap<String, ArrayList<String>> map, String id, String objName){
		if (objName != null) 
			if (map.get(objName).contains(id)) return true;
		return false; 
	}

	
	//Checks whether a given symbol is declared
	static boolean isSymbolDeclared(HashMap<String, ArrayList<String>> map, SymbolName ref){
		ObjectName objName = getObjectName(ref);
		if (objName != null) 
			if (map.get(objName.getName()).contains(ref.getName())) return true;		
		return false;
	}
		
	static boolean isSymbolDeclared(HashMap<String, ArrayList<String>> map, SymbolName ref, ArrayList<ModellingObjectGroup> mogs){
		ObjectName objName = getObjectName(ref);
		if (objName != null){
			if (isIdentifierDeclared(map, ref.getName(), objName.getName())) return true;
			for (ModellingObjectGroup mog: mogs){
				if (mog.getMdlObjName().equals(objName.getName()))
					if (isIdentifierDeclared(map, ref.getName(), mog.getDataObjName()) || 
							isIdentifierDeclared(map, ref.getName(), mog.getParamObjName())) return true;
			}
		}
		return false;
	}
	
	//Checks whether a function is declared more than once
	static boolean isSymbolDeclaredMoreThanOnce(HashMap<String, ArrayList<String>> map, SymbolName ref){
		int i = 0;
		ObjectName objName = Utils.getObjectName(ref);
		ArrayList<String> functions = map.get(objName.getName()); 
		for (String func: functions){
			if (func.equals(ref.getName())) i++;
			if (i > 1) return true;
		}
		return false;
	}
	
	//Checks whether a given function is declared
	static boolean isFunctionDeclared(HashMap<String, ArrayList<String>> map, FullyQualifiedFunctionName ref){
		ObjectName objName = ref.getObject();
		if (objName == null) objName = getObjectName(ref);
		if (objName != null) 
			if (map.get(objName.getName()).contains(ref.getFunction().getName())) return true;		
		return false;
	}
	
	//Checks whether a given function is declared
	static boolean isFunctionDeclared(HashMap<String, ArrayList<String>> map, FunctionName ref){
		ObjectName objName = getObjectName(ref);
		if (objName != null) 
			if (map.get(objName.getName()).contains(ref.getName())) return true;		
		return false;
	}
	
	//Checks whether a function is declared more than once
	static boolean isFunctionDeclaredMoreThanOnce(HashMap<String, ArrayList<String>> map, FunctionName ref){
		int i = 0;
		ObjectName objName = Utils.getObjectName(ref);
		ArrayList<String> functions = map.get(objName.getName()); 
		for (String func: functions){
			if (func.equals(ref.getName())) i++;
			if (i > 1) return true;
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
	public static void addSymbol(ArrayList<String> list, BlockStatement st){
		if (st != null){
			if (st.getSymbol() != null){
				list.add(st.getSymbol().getSymbolName().getName());
			}
			//conditional declarations
			if (st.getStatement() != null){
				ConditionalStatement e = st.getStatement();
				addSymbol(list, e);
				//THE FOLLOWING CODE IS TO ACTIVATE STRONG VARIDATION OF CONDITIONALLY DECLARED VARIABLES or PARAMETERS
				//ArrayList<BlockStatement> ifBlocks = new ArrayList<BlockStatement>();
				//ArrayList<BlockStatement> elseBlocks = new ArrayList<BlockStatement>();
				//prepareConditionalBlocks(e, ifBlocks, elseBlocks);
				//addSymbol(list, ifBlocks, elseBlocks);
			}
		}
	}
	
	//The same as previous, but does not add repeated conditionally developed variables to avoid double declaration warning 
	public static void addSymbolNoRepeat(ArrayList<String> list, BlockStatement st){
		if (st != null){
			if (st.getSymbol() != null)
				if (!list.contains(st.getSymbol().getSymbolName().getName())) 
					list.add(st.getSymbol().getSymbolName().getName());
			if (st.getStatement() != null)
				addSymbol(list, st.getStatement());
		}
	}
	
	//Weak validation of conditionally declared references - a variable is declared if it is declared in some branch 
	public static void addSymbol(ArrayList<String> list, ConditionalStatement e){
		if (e.getIfStatement() != null){
			addSymbolNoRepeat(list, e.getIfStatement());
		}
		if (e.getElseStatement() != null){
			addSymbolNoRepeat(list, e.getElseStatement());
		}		
		if (e.getIfBlock() != null) {
			for (BlockStatement b: e.getIfBlock().getStatements()){
				addSymbolNoRepeat(list, b);
			}
		}
		if (e.getElseBlock() !=null){
			for (BlockStatement b: e.getElseBlock().getStatements())
				addSymbolNoRepeat(list, b);
		}
	}
	
	/*static void addSymbol(ArrayList<String> list, ArrayList<BlockStatement> ifBlocks, ArrayList<BlockStatement> elseBlocks){
		//Add symbols defined in both branches of nested conditional statements 
		ArrayList<String> ifSymbols = new ArrayList<String>();
		ArrayList<String> elseSymbols = new ArrayList<String>();
		for (BlockStatement b: ifBlocks){
			if (b.getStatement() != null){
				ConditionalStatement e = b.getStatement();
				ArrayList<BlockStatement> ifBlocks1 = new ArrayList<BlockStatement>();
				ArrayList<BlockStatement> elseBlocks1 = new ArrayList<BlockStatement>();
				prepareConditionalBlocks(e, ifBlocks1, elseBlocks1);
				addSymbol(ifSymbols, ifBlocks1, elseBlocks1);
			}
		}
		for (BlockStatement b: elseBlocks){
			if (b.getStatement() != null){
				ConditionalStatement e = b.getStatement();
				ArrayList<BlockStatement> ifBlocks1 = new ArrayList<BlockStatement>();
				ArrayList<BlockStatement> elseBlocks1 = new ArrayList<BlockStatement>();
				prepareConditionalBlocks(e, ifBlocks1, elseBlocks1);
				addSymbol(elseSymbols, ifBlocks1, elseBlocks1);
			}
		}
		for (String s: ifSymbols){
			if (isSymbolDefined(elseBlocks, s) || elseSymbols.contains(s)) list.add(s);
		}
		//BlockStatement is an unconditional symbol declaration
		for (BlockStatement b: ifBlocks){
			if (b.getSymbol() != null){
				String s = b.getSymbol().getIdentifier();
				if (isSymbolDefined(elseBlocks, s) || elseSymbols.contains(s)) {
					list.add(s);
				}				
			}
		}
	}*/
	
	/*static void prepareConditionalBlocks(ConditionalStatement e, ArrayList<BlockStatement> ifBlocks, ArrayList<BlockStatement> elseBlocks){
		if (e.getIfStatement() != null)
			ifBlocks.add(e.getIfStatement());	
		if (e.getIfBlock() != null) {
			for (BlockStatement b: e.getIfBlock().getStatements())
				ifBlocks.add(b);
		}
		if (e.getElseStatement() != null)
			elseBlocks.add(e.getElseStatement());
		if (e.getElseBlock() !=null){
			for (BlockStatement b: e.getElseBlock().getStatements())
				elseBlocks.add(b);
		}
	}*/
	
	/*private boolean isSymbolDefined(ArrayList<BlockStatement> blocks, String name){
		for (BlockStatement b: blocks){
			if (b.getSymbol() != null){
				if (b.getSymbol().getIdentifier().equals(name)) return true;				
			}
		}
		return false;
	}*/
	
	//Add a symbol to a list of known symbols
	public static void addSymbol(ArrayList<String> list, Arguments args){
		if (args != null){
			if (args.getArguments() != null){	
				for (Argument arg: args.getArguments()){
					if (arg.getArgumentName() != null)
						list.add(arg.getArgumentName().getName());
				}
			}
		}
	}

	public static void addSymbol(ArrayList<String> list, FormalArguments args){
		if (args != null){
			for (ArgumentName id: args.getArguments())
				list.add(id.getName());
		}
	}
	
	//Add a symbol to a list of known symbols
	public static void addSymbol(ArrayList<String> list, Symbols args){
		if (args != null){
			if (args.getSymbols() != null){	
				for (Symbol arg: args.getSymbols()){
					if (arg.getSymbolName() != null)
						list.add(arg.getSymbolName().getName());
				}
			}
		}
	}

	public static void addSymbol(ArrayList<String> list, SymbolNames args){
		if (args != null){
			for (SymbolName id: args.getSymbolNames())
				list.add(id.getName());
		}
	}

	
	public static void addSymbol(ArrayList<String> list, ImportBlock block){
		if (block != null){
			for (ImportedFunction id: block.getFunctions())
				list.add(id.getFunctionName().getName());
		}
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
	
	//Extracts references from mathematical expressions
	static ArrayList<String> extractSymbolNames(Arguments args, String attrName){
		ArrayList<String> params = new ArrayList<String>();	
		if (args != null){
			if (args.getArguments() != null){
				for (Argument x: args.getArguments()){
					if (x.getArgumentName().getName().equals(attrName)) {
						if (x.getExpression().getList() != null) {
							for (Argument paramArg : x.getExpression().getList().getArguments().getArguments()) {
								TreeIterator<EObject> paramIterator = paramArg.getExpression().eAllContents();
								while (paramIterator.hasNext()) {
									EObject paramObj = paramIterator.next();
									if (paramObj instanceof SymbolNameImpl) {
										SymbolName foundParam = (SymbolName) paramObj;
										params.add(foundParam.getName());
									}
								}
							}
						}
					}
				}
			}
		}
		return params;
	}
	
	//Print a given list (used for reporting errors and for testing)
	static String printList(ArrayList<String> list){
		String res = "{ ";
		for (String str: list) res += str + "; ";
		return res + "}";
	}
	
	public static ArrayList<String> getAllNames(List<Attribute> attrs){
		ArrayList<String> names = new ArrayList<String>();
		if (attrs != null){
			for (Attribute attr: attrs){
				names.add(attr.getName());
			}
		}
		return names;
	}

	public static ArrayList<String> getRequiredNames(List<Attribute> attrs){
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
			obj instanceof SourceBlockImpl ||
			obj instanceof DesignBlockStatementImpl ||
			//Model object
			obj instanceof InputVariablesBlockImpl ||
			obj instanceof LibraryBlockImpl ||
			obj instanceof OdeBlockImpl ||
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
			obj instanceof ImportedFunctionImpl ||
			obj instanceof TargetBlockImpl) return true;
		return false;	
	}

	public static String getBlockName(EObject obj){
		/*Data object*/
		if (obj instanceof DataInputBlockImpl) return ((DataInputBlock)obj).getIdentifier();
		if (obj instanceof DataDerivedBlockImpl) return ((DataDerivedBlock)obj).getIdentifier();
		if (obj instanceof SourceBlockImpl) return ((SourceBlock)obj).getIdentifier();
		if (obj instanceof DesignBlockImpl) return ((DesignBlock)obj).getIdentifier();
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
		if (obj instanceof EstimationBlockImpl) return ((EstimationBlock)obj).getIdentifier() ;
		if (obj instanceof SimulationBlockImpl) return ((SimulationBlock)obj).getIdentifier() ;
		if (obj instanceof ObservationBlockImpl) return ((ObservationBlock)obj).getIdentifier() ;
		if (obj instanceof StructuralParametersBlockImpl) return ((StructuralParametersBlock)obj).getIdentifier() ;
		if (obj instanceof VariabilityParametersBlockImpl) return ((VariabilityParametersBlock)obj).getIdentifier() ;
		/*All objects*/
		if (obj instanceof ImportBlockImpl) return ((ImportBlock)obj).getIdentifier();
		if (obj instanceof TargetBlockImpl) return ((TargetBlock)obj).getIdentifier();
		return "";
	}
	
	//Locate data/script file in the MDL project
	public static boolean isFileExist(EObject b, String filePath) {
		String platformString = b.eResource().getURI().toPlatformString(true);
		IFile modelFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(platformString));
		//Try path relatively to the model file
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
}
