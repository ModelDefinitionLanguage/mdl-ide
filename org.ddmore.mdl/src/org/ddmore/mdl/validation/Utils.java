package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.HashMap;

import org.ddmore.mdl.mdl.AdditiveExpression;
import org.ddmore.mdl.mdl.AndExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.ArgumentName;
import org.ddmore.mdl.mdl.Arguments;
import org.ddmore.mdl.mdl.BlockStatement;
import org.ddmore.mdl.mdl.ConditionalStatement;
import org.ddmore.mdl.mdl.FormalArguments;
import org.ddmore.mdl.mdl.FullyQualifiedSymbolName;
import org.ddmore.mdl.mdl.ImportBlock;
import org.ddmore.mdl.mdl.ImportedFunction;
import org.ddmore.mdl.mdl.LogicalExpression;
import org.ddmore.mdl.mdl.ObjectName;
import org.ddmore.mdl.mdl.OrExpression;
import org.ddmore.mdl.mdl.impl.FullyQualifiedSymbolNameImpl;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

public class Utils {
	
	//Checks whether the symbol is declared
	static boolean isSymbolDeclared(HashMap<String, ArrayList<String>> map, String id, ObjectName objName){
		if (objName != null)
			if (map.get(objName.getName()).contains(id)) return true;
		for (String key: map.keySet()){
			if (map.get(key).contains(id)) return true;
		} 
		return false;
	}
	
	//Checks whether the symbol is declared more than once
	static boolean isSymbolDeclaredMoreThanOnce(HashMap<String, ArrayList<String>> map, String id){
		int i = 0;
		for (String key: map.keySet()){
			ArrayList<String> functions = map.get(key); 
			for (String func: functions){
				if (func.equals(id)) i++;
				if (i > 1) return true;
			}
		} 
		return false;
	}
	
	//Check whether the list of attributes contains a given attribute
	static boolean containsAttribute(Arguments args, String attrName){
		for (Argument arg: args.getArguments()){
			if (arg.getArgumentName() != null){
				if (arg.getArgumentName().getName().equals(attrName)) return true;
			}
		}
		return false;
	}

	//Add a symbol to a list of known symbols
	static void addSymbol(ArrayList<String> list, BlockStatement st){
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
	static void addSymbolNoRepeat(ArrayList<String> list, BlockStatement st){
		if (st != null){
			if (st.getSymbol() != null)
				if (!list.contains(st.getSymbol().getSymbolName().getName())) 
					list.add(st.getSymbol().getSymbolName().getName());
			if (st.getStatement() != null)
				addSymbol(list, st.getStatement());
		}
	}
	
	//Weak validation of conditionally declared references - a variable is declared if it is declared in some branch 
	static void addSymbol(ArrayList<String> list, ConditionalStatement e){
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
	static void addSymbol(ArrayList<String> list, Arguments args){
		if (args != null){
			if (args.getArguments() != null){	
				for (Argument arg: args.getArguments()){
					if (arg.getArgumentName() != null)
						list.add(arg.getArgumentName().getName());
				}
			}
		}
	}

	static void addSymbol(ArrayList<String> list, FormalArguments args){
		if (args != null){
			for (ArgumentName id: args.getArguments()){
				list.add(id.getName());
			}
		}
	}
	
	static void addSymbol(ArrayList<String> list, ImportBlock block){
		if (block != null){
			for (ImportedFunction id: block.getFunctions()){
				list.add(id.getFunctionName().getName());
			}
		}
	}
	
	//Evaluate STRING expression
	static String getAttributeValue(Arguments a, String attrName){
		for (Argument arg: a.getArguments()){
			if (arg.getArgumentName().getName().equals(attrName))
				return getAttributeValue(arg);
		}
		return "";
	}	
	
	//Evaluate STRING expression
	static String getAttributeValue(Argument arg){
		String res = "";	
		if (arg.getExpression() != null){
			if (arg.getExpression().getExpression() != null){
				if (arg.getExpression().getExpression().getConditionalExpression() != null){
					OrExpression orExpr = arg.getExpression().getExpression().getConditionalExpression().getExpression();
					AndExpression andExpr = orExpr.getExpression().get(0);
					LogicalExpression logicalExpr = andExpr.getExpression().get(0);	
					if (logicalExpr.getExpression1() != null){	
						AdditiveExpression addExpr = logicalExpr.getExpression1();
						if (addExpr.getString() != null) return addExpr.getString();
					}
				}
			}
		}	
		return res;
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
									if (paramObj instanceof FullyQualifiedSymbolNameImpl) {
										FullyQualifiedSymbolName foundParam = (FullyQualifiedSymbolName) paramObj;
										params.add(foundParam.getSymbol().getName());
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
}
