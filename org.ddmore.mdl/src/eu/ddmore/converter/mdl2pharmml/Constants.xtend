package eu.ddmore.converter.mdl2pharmml

class Constants {
	public val xsi="http://www.w3.org/2001/XMLSchema-instance"; 
	public val xsi_schemaLocation="http://www.pharmml.org/2013/03/PharmML http://www.pharmml.org/2013/03/PharmML";
	public val xmlns_pharmML="http://www.pharmml.org/2013/03/PharmML";
	public val xmlns_math="http://www.pharmml.org/2013/03/Maths";
	public val xmlns_ct="http://www.pharmml.org/2013/03/CommonTypes";
	public val xmlns_mdef="http://www.pharmml.org/2013/03/ModelDefinition";
	public val xmlns_mstep="http://www.pharmml.org/2013/03/ModellingSteps";
	public val xmlns_design="http://www.pharmml.org/2013/03/TrialDesign";
	public val xmlns_ds="http://www.pharmml.org/2013/08/Dataset";
	public val xmlns_uncert="http://www.uncertml.org/3.0"; 
	public val definition = "http://www.uncertml.org/distributions/";	

	public val writtenVersion = "0.1";
	
	public val nVal  = "nVal";
	public val pnVal = "pnVal";
	public val rVal  = "rVal";
	public val prVal = "prVal";
	public val pVal  = "pVal";	
	
	public val TYPE_INT  = "int";
	public val TYPE_REAL = "real";
	public val OPERATION_ESTIMATE = "estim";	
	public val OPERATION_SIMULATE = "simul";
	
	public val VAR_TYPE_PARAMETER = "variabilityParameter";
	public val VAR_TYPE_ERROR = "residualError";

}