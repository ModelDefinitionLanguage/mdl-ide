package org.ddmore.mdl.types;

import java.util.Arrays;
import java.util.List;

public class InterpolationType {

	final public static String INTERP_CONSTANT = "constant";
	final public static String INTERP_LINEAR = "linear"; 
	final public static String INTERP_NEAREST = "nearest"; 
	final public static String INTERP_SPLINE = "spline"; 
	final public static String INTERP_PCHIP = "pchip"; 
	final public static String INTERP_CUBIC = "cubic";
	
	public final static List<String> INTERP_VALUES = 
			Arrays.asList(INTERP_CONSTANT, INTERP_LINEAR, INTERP_NEAREST, INTERP_SPLINE, INTERP_PCHIP, INTERP_CUBIC);
}
