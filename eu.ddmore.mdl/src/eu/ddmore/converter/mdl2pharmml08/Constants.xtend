package eu.ddmore.converter.mdl2pharmml08

final class Constants {
	public static val writtenVersion     = "0.7.3";
	public static val urlVersion     = "0.7";
	public static val baseURL            = "http://www.pharmml.org/pharmml/" + urlVersion;

	public static val xsi                = "http://www.w3.org/2001/XMLSchema-instance"; 
	public static val xsi_schemaLocation = baseURL + "/PharmML " + baseURL + "/PharmML";

	public static val xmlns_pharmML = baseURL + "/PharmML";
	public static val xmlns_math    = baseURL + "/Maths";
	public static val xmlns_ct      = baseURL + "/CommonTypes";
	public static val xmlns_mdef    = baseURL + "/ModelDefinition";
	public static val xmlns_mstep   = baseURL + "/ModellingSteps";
	public static val xmlns_design  = baseURL + "/TrialDesign";
	public static val xmlns_ds      = baseURL + "/Dataset";
	
	public static val xmlns_uncert  = "http://www.uncertml.org/3.0"; 
	public static val definition    = "http://www.uncertml.org/distributions/";	
	
	public static val nVal  = "nVal";
	public static val pnVal = "pnVal";
	public static val rVal  = "rVal";
	public static val prVal = "prVal";
	public static val pVal  = "pVal";	
	
	public static val TYPE_INT  = "int";
	public static val TYPE_REAL = "real";
	public static val TYPE_ID   = "id";
		
	public static val UNDEFINED  = "undefined";
	
	public static val VAR_TYPE_PARAMETER = "parameterVariability";
	public static val VAR_TYPE_ERROR = "residualError";
	
	public static val MATRIX_COV   = "CovMatrix";
	public static val MATRIX_VAR   = "CovMatrix"; 
	public static val MATRIX_STDEV = "StDevCorrMatrix";
	public static val MATRIX_CORR  = "CorrMatrix";
	
	public static val BLK_DS_TARGET_TOOL      = "tt";
	public static val BLK_DS_TARGET_TOOL_DATA = "ttd";
	public static val BLK_DS_IMPORT_DATA      = "id";
	public static val BLK_DS_NONMEM_DATASET   = "nm_ds";
	public static val BLK_DS_MONOLIX_DATASET  = "mx_ds";
	public static val BLK_DS_DATASET          = "ds";
	
	public static val BLK_ESTIM_STEP = "estimStep_";	
	public static val BLK_SIMUL_STEP = "simulStep_";
	
	public static val OPERATION_EST_POP   = "estPop";
	public static val OPERATION_EST_INDIV = "estIndiv";
	public static val OPERATION_EST_FIM   = "estFIM";
	
	public static val BLK_DESIGN_EPOCH    = "td_epoch";
	public static val BLK_DESIGN_CELL     = "td_cell";
	public static val BLK_DESIGN_ARM      = "td_arm";
	public static val BLK_DESIGN_SEGMENT  = "td_segment";
	public static val BLK_DESIGN_ACTIVITY = "td_activity";
	
	
}