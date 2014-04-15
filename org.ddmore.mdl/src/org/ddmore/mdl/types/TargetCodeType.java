package org.ddmore.mdl.types;

import java.util.Arrays;
import java.util.List;

public class TargetCodeType {
	public final static String NMTRAN_CODE = "NMTRAN_CODE";	
	public final static String MLXTRAN_CODE = "MLXTRAN_CODE"; 
	public final static String PML_CODE = "PML_CODE";
	public final static String BUGS_CODE = "BUGS_CODE"; 
	public final static String R_CODE = "R_CODE"; 
	public final static String MATLAB_CODE = "MATLAB_CODE"; 	
	
	public final static List<String> TARGET_VALUES = 
		Arrays.asList(NMTRAN_CODE, MLXTRAN_CODE, PML_CODE, BUGS_CODE, R_CODE, MATLAB_CODE);
}
