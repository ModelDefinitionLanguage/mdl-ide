package org.ddmore.mdl.ui.preference;

import java.util.HashMap;

public class MDLPreferenceConstants {
	
    public static final String NONMEM_HOME = "nonmemHome";
    public static final String MLXTRAN_HOME = "mlxtranHome";
    public static final String R_HOME = "rHome";
    public static final String MATHLAB_HOME = "mathlabHome";

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";

    // TES
    public static final String TES_HOST = "tes.host";
    public static final String TES_PORT = "tes.port";
    public static final String TES_SERVICE = "tes.service";

    public static final String TES_UNAME = "tes.username";
    public static final String TES_PWORD = "tes.password";

    // TODO PROXY

    public static final String TES_CLIENT_SHARED_DIR = "tes.client.shared.dir";
    public static final String TES_SHARED_DIR = "tes.shared.dir";
    public static final String TES_TOOL_SHARED_DIR = "tes.tool.shared.dir";

    public static final String TES_NONMEM_EXECUTABLE = "tes.nonmem.exe";
    public static final String TES_R_EXECUTABLE = "tes.r.exe";
    public static final String TES_R_PREAMBLE = "tes.r.preamble";

    // Default values.
    public static HashMap<String, Object> DEFAULTS;

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
