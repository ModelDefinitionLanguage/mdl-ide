package org.ddmore.mdl.types;

import java.util.Arrays;
import java.util.List;

public class ConstantType {

	final public static String T = "T";
	final public static String INF = "INF";
	final public static String NEWIND = "NEWIND";
	final public static String IREP = "IREP";
	final public static String ICALL = "ICALL";  
	final public static String MIXEST = "MIXEST"; 
	final public static String MIXNUM = "MIXNUM";
	
	public final static List<String> CONSTANT_VALUES = 
			Arrays.asList(T, INF, NEWIND, IREP, ICALL, MIXEST, MIXNUM);
}
