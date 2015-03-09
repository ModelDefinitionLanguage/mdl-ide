/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * A class to parse unit expression (may be extended later to perform ontology-based unit validation and/or conversion)
 */
package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
import java.util.List;
import java.util.Stack;

import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.MdlPackage;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;
//import org.eclipse.uomo.units.SI;
//import org.unitsofmeasurement.unit.Unit;

import com.google.inject.Inject;

import eu.ddmore.converter.mdlprinting.MdlPrinter;

public class UnitValidator extends AbstractDeclarativeValidator{

	@Override
    @Inject
    public void register(EValidatorRegistrar registrar) {}
	
	public final static String MSG_UNIT_UNKNOWN = "Failed to recognize a unit value";	
	//public final static String MSG_UNIT_UNDEFINED = "Unknown unit metric";	

	/////////////////////////////////////////////////////////////////////////////////////////////////
	//Validation via external resource (string comparison)
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	//static Map<String, String> validUnits = loadUnits();
	
	public static List<String> getUnitNames(){
		List<String> unitNames = new ArrayList<String>();
		for (String unit: validUnits){
			unitNames.add("\"" + unit +"\"");
		}
		return unitNames;
	}
	
	/*
	static Map<String, String> loadUnits(){
		Map<String, String> validUnits = new HashMap<String, String>();
		try {
		    URL url = new URL("platform:/plugin/org.ddmore.mdl/runtime/Units.csv");
		    InputStream inputStream = url.openConnection().getInputStream();
		    BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		    String inputLine;
		    while ((inputLine = in.readLine()) != null) {
		    	String[] tokens = inputLine.split(",");
		    	if (tokens.length > 1){
			    	if (!validUnits.containsKey(tokens[1])){
			    		validUnits.put(tokens[1], tokens[0]);
			    	}
		    	}
		    }
		    in.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		return validUnits;
	}
	*/
	
	//Check whether the attribute "units" defines a correct unit measurement
	@Check
	public void checkUnitValue(Argument arg){
		if (arg.getArgumentName() != null && arg.getExpression() != null){
			if (arg.getArgumentName().getName().equals(AttributeValidator.attr_units.getName())){
				String unitValue = MdlPrinter.getInstance().toStr(arg.getExpression());
				if (unitValue.length() > 0) {
					unitValue = unitValue.replaceAll("\\s+",""); // Remove any whitespace
					/*
					if (validUnits.size() > 0){
						if (!validUnits.containsKey(unitValue))
							warning(MSG_UNIT_UNDEFINED + ": " + unitValue, 
							MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME,
							MSG_UNIT_UNDEFINED, arg.getArgumentName().getName());
					} else {//syntactic validation
						*/
						String wrongToken = parseUnitExpression(unitValue);
						if (wrongToken != null){
							warning(MSG_UNIT_UNKNOWN + ": " + wrongToken + " is not in the set " + Utils.printList(unitAliases), 
							MdlPackage.Literals.ARGUMENT__ARGUMENT_NAME,
							MSG_UNIT_UNKNOWN, arg.getArgumentName().getName());
						}
					//}
				}
			}
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	//Validation via parsing
    /////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*@SuppressWarnings("rawtypes")
	final static Map<String, Unit> unitAliases = new HashMap<String, Unit>(){
		private static final long serialVersionUID = 7075148739253345344L;
		{
			//Time
			put("s", SI.SECOND); 
			put("ms", NonSI.MILLISECOND); 
			put("us", NonSI.MICROSECOND); put("mcs", NonSI.MICROSECOND); 
			put("ns", NonSI.NANOSECOND); 
			put("ps", NonSI.PICOSECOND); 
			put("fs", NonSI.FEMTOSECOND); 
			put("min", NonSI.MINUTE); 
			put("h", NonSI.HOUR);
			put("day", NonSI.DAY); 
			put("week", NonSI.WEEK);
			put("y", NonSI.YEAR); put("year", NonSI.YEAR);
			//Volume
			put("L", NonSI.LITER);
			put("dL", NonSI.DECILITER);
			put("mL", NonSI.MILLILITER);
			put("uL", NonSI.MICROLITER); put("mcL", NonSI.MICROLITER);
			put("nL", NonSI.NANOLITER);
			put("pL", NonSI.PICOLITER);
			put("fL", NonSI.FEMTOLITER); 
			//Mass
			put("kg", SI.KILOGRAM); 
			put("g", SI.GRAM); 
			put("mg", NonSI.MILLIGRAM);
			put("ug", NonSI.MICROGRAM); put("mcg", NonSI.MICROGRAM);
			put("ng", NonSI.NANOGRAM);
			put("pg", NonSI.PICOGRAM);
			put("fg", NonSI.FEMTOGRAM);
			//Amount of subtance
			put("M", SI.MOLE); put("mole", SI.MOLE); 
			put("mM", NonSI.MILLIMOLE); put("mmol", NonSI.MILLIMOLE); put("millimole", NonSI.MILLIMOLE);
			put("uM", NonSI.MICROMOLE); put("mcM", NonSI.MICROMOLE); 
				put("umol", NonSI.MICROMOLE); put("mcmol", NonSI.MICROMOLE); put("micromole", NonSI.MICROMOLE);
			put("nM", NonSI.NANOMOLE); put("nmol", NonSI.NANOMOLE); put("nanomole", NonSI.NANOMOLE); 
			put("pM", NonSI.PICOMOLE); put("pmol", NonSI.PICOMOLE); put("picomole", NonSI.PICOMOLE);
			put("fM", NonSI.FEMTOMOLE); put("fmol", NonSI.FEMTOMOLE); put("femtomole", NonSI.FEMTOMOLE);
			//Length
			put("m", SI.METRE);
			put("km", NonSI.KILOMETRE);
			put("cm", NonSI.SANTIMETRE);
			put("mm", NonSI.MILLIMETRE);
			put("um", NonSI.MICROMETRE); put("mcm", NonSI.MICROMETRE);
			put("nm", NonSI.NANOMETRE); 
			put("pm", NonSI.PICOMETRE); 
			put("fm", NonSI.FEMTOMETRE); 
			//Area
			put("m2", SI.SQUARE_METRE);
		}
	};*/

	final static List<String> unitAliases = new ArrayList<String>(Arrays.asList(
		"s", "ms", "us", "ns", "ps", "fs", "min", "h", "day", "week", "y", "year", 
		"L", "dL", "mL", "uL", "mcL", "nL", "pL", "fL", 
		"kg", "g", "mg", "ug", "mcg", "ng", "pg", "fg", 
		"M", "mole", "mM", "mmol", "millimole", "uM", "mcM", 
		"umol", "mcmol", "micromole", "nM", "nmol", "nanomole", "pM", "pmol", "picomole", "fM", "fmol", "femtomole", 
		"m", "km", "cm", "mm", "um", "mcm", "nm", "pm", "fm", "m2"));

	final static List<String> validUnits = getAllUnitNames();
	
	public static List<String> getAllUnitNames(){
		List<String> unitNames = new ArrayList<String>();
		unitNames.addAll(unitAliases);
		return unitNames;
	}

	final int ID = 1;
	final int NUMBER = 2;
	final int OPEN_BRACKET = 3;
	final int CLOSE_BRACKET = 4;
	final int MULT_OP = 5;
	final int POWER_OP = 6;
		
	//Check the unit measurement format
	//Returns an incorrect token or null otherwise
	String parseUnitExpression(String str){
		String[] tokens = str.split("(?<=[*/^\\(\\)])|(?=[*/^\\(\\)])");
		//TEST: return Arrays.toString(tokens);
		Stack<Integer> stack = new Stack<Integer>(); 
		for (int i = 0; i < tokens.length; i++){
			String token = tokens[i];
			if (token.length() > 0){
				int lexem = -1;
				if (validUnits.contains(token)) lexem = ID; //ID
				else if (token.equals("(")) lexem = OPEN_BRACKET;
				else if (token.equals(")")) lexem = CLOSE_BRACKET; 
				else if (token.equals("*") || token.equals("/")) lexem = MULT_OP; 
				else if (token.equals("^")) lexem = POWER_OP; 	
				else if (token.matches("-?\\d+(\\.\\d+)?")) lexem = NUMBER;
				
				if (lexem < 0) return token; //unrecognized symbol
				
				if (lexem == ID){
					if (!stack.isEmpty()){
						if ((stack.peek() != MULT_OP) && (stack.peek() != OPEN_BRACKET)) return token;
					}
					stack.push(ID);
				}
				if (lexem == OPEN_BRACKET){
					if (!stack.isEmpty()) 
						if ((stack.peek() != MULT_OP) && (stack.peek() != OPEN_BRACKET)) return token;					
					stack.push(OPEN_BRACKET);
				}
				if (lexem == CLOSE_BRACKET){
					if (stack.isEmpty()) return token;
					if ((stack.peek() != ID) && (stack.peek() != OPEN_BRACKET)) return token;	
					while (stack.peek() != OPEN_BRACKET) {
						stack.pop();
						if (stack.isEmpty()) return token;
					}
					stack.pop();
					stack.push(ID);
				}
				if (lexem == MULT_OP){
					if (stack.isEmpty()) return token;
					if (stack.peek() != ID) return token;
					if (i == tokens.length - 1) return token; //can't be the last character
					stack.push(MULT_OP);
				}
				if (lexem == POWER_OP){
					if (stack.isEmpty()) return token;
					if (stack.peek() != ID) return token;					
					if (i == tokens.length - 1) return token; //can't be the last character
					stack.push(POWER_OP);
				}
				if (lexem == NUMBER){
					if (!stack.isEmpty()) 
						if ((stack.peek() != POWER_OP) && 
							(stack.peek() != MULT_OP) 
							&& (stack.peek() != OPEN_BRACKET)) return token;					
					stack.pop();
				}
			}
		}
		while (!stack.isEmpty()){
			if (stack.pop() == OPEN_BRACKET) return "(";
		}
		return null;
	}
}
