package org.ddmore.mdl.ui.preference;

import org.ddmore.mdl.ui.internal.MdlActivator;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

public class PreferenceInitializer extends AbstractPreferenceInitializer {

    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore store = MdlActivator.getInstance().getPreferenceStore();
        store.setDefault(MDLPreferenceConstants.TES_SERVICE, "MIFServer/REST/services");
    }

}
