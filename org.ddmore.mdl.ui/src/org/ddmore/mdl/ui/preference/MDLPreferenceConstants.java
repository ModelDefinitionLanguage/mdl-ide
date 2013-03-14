package org.ddmore.mdl.ui.preference;
import java.util.HashMap;

public class MDLPreferenceConstants {
	public static final String NONMEM_HOME = "nonmemHome";
	public static final String MLXTRAN_HOME = "mlxtranHome";
	public static final String R_HOME = "rHome";
	public static final String MATHLAB_HOME = "mathlabHome";

	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";	

	// Default values.
	public static HashMap<String,Object> DEFAULTS;

	static {
		DEFAULTS = new HashMap<String, Object>();
		DEFAULTS.put(NONMEM_HOME, "C://Programs/NONMEM");
		DEFAULTS.put(MLXTRAN_HOME, "C://Programs/MLXTRAN");
		DEFAULTS.put(R_HOME, "C://Programs/RStudio");
		DEFAULTS.put(MATHLAB_HOME, "C://Programs/MATHLAB");

		DEFAULTS.put(LOGIN, "login");
		DEFAULTS.put(PASSWORD, "password");
	}

}
