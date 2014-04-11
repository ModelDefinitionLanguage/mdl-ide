package org.ddmore.mdl.ui.preference;

public class MDLPreferences {

	public static String getNONMEMHome() {
		return MDLPreferenceStore.getString(MDLPreferenceConstants.NONMEM_HOME);	
	}
	
	public static void setNONMEMHome(String value) {
		MDLPreferenceStore.setValue(MDLPreferenceConstants.NONMEM_HOME, value);
	}
	
	public static String getRHome() {
		return MDLPreferenceStore.getString(MDLPreferenceConstants.R_HOME);	
	}
	
	public static void setRHome(String value) {
		MDLPreferenceStore.setValue(MDLPreferenceConstants.R_HOME, value);
	}

	public static String getMLXTRANHome() {
		return MDLPreferenceStore.getString(MDLPreferenceConstants.MLXTRAN_HOME);	
	}
	
	public static void setMLXTRANHome(String value) {
		MDLPreferenceStore.setValue(MDLPreferenceConstants.MLXTRAN_HOME, value);
	}

	public static String getMATHLABHome() {
		return MDLPreferenceStore.getString(MDLPreferenceConstants.MATHLAB_HOME);	
	}
	
	public static void setMATHLABHome(String value) {
		MDLPreferenceStore.setValue(MDLPreferenceConstants.MATHLAB_HOME, value);
	}

	public static String getLogin() {
		return MDLPreferenceStore.getString(MDLPreferenceConstants.LOGIN);	
	}
	
	public static void setLoginHome(String value) {
		MDLPreferenceStore.setValue(MDLPreferenceConstants.LOGIN, value);
	}

	public static String getPasswordHome() {
		return MDLPreferenceStore.getString(MDLPreferenceConstants.PASSWORD);	
	}
	
	public static void setPasswordHome(String value) {
		MDLPreferenceStore.setValue(MDLPreferenceConstants.PASSWORD, value);
	}
	
	
}
