package org.ddmore.mdl.types;

import java.util.Arrays;
import java.util.List;

public class UseType {
	public final static String USE_ID = "id";
	public final static String USE_AMT  = "amt";
	public final static String USE_DVID = "dvid";
	public final static String USE_IDV  = "idv";
	public final static String USE_DV  = "dv";
	public final static String USE_YTYPE  = "ytype";
	public final static String USE_ITYPE  = "itype";
	public final static String USE_COVARIATE = "covariate";	
	public final static String USE_REG = "reg";	
	public final static String USE_TIME = "time";	
	public final static String USE_DOSE = "dose";	
	public final static String USE_CAT = "cat";
	public final static String USE_OCC = "occ";
	public final static String USE_OCCASION = "occasion"; 
	public final static String USE_ADM = "adm"; 
	public final static String USE_CENS = "cens"; 
	public final static String USE_LIMIT = "limit"; 
	public final static String USE_RATE = "rate"; 
	public final static String USE_TINF = "tinf"; 
	public final static String USE_SS = "ss"; 
	public final static String USE_ADDL = "addl";
	public final static String USE_II = "ii"; 
	public final static String USE_TAU = "tau"; 
	public final static String USE_MDV = "mdv"; 
	public final static String USE_EVID = "evid"; 
	public final static String USE_CMT = "cmt";

	public final static List<String> USE_VALUES = 
		Arrays.asList(USE_ID, USE_IDV, USE_AMT, USE_DV, USE_DVID, USE_YTYPE, USE_ITYPE, 
			USE_COVARIATE, USE_REG, USE_TIME, USE_DOSE, 
			USE_CAT, USE_OCC, USE_OCCASION, USE_ADM, USE_CENS, USE_LIMIT, 
			USE_RATE, USE_TINF, USE_SS, USE_ADDL, USE_II, USE_TAU, USE_MDV, USE_EVID, USE_CMT);

}
