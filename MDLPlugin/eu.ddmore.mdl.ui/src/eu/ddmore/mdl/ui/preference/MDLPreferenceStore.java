package eu.ddmore.mdl.ui.preference;

import org.eclipse.core.runtime.preferences.ConfigurationScope;
import org.osgi.service.prefs.Preferences;

/**
 * Wrapper for the MDL preference store that does not
 * depend on JFace or the Eclipse UI plug-ins.
 * @author Natallia Kokash
 */
public class MDLPreferenceStore {

    private final static String packageID = "org.ddmore.mdl";

    private final static Preferences preferences;

    static {
        preferences = ConfigurationScope.INSTANCE.getNode(packageID);
    }

    public static String getString(String property) {
        return preferences.get(property, null);
    }

    public static void setValue(String property, String value) {
        preferences.put(property, value);
    }
}
