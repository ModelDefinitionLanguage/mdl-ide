
package eu.ddmore.mdl;

import eu.ddmore.mdl.MdlStandaloneSetupGenerated;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class MdlStandaloneSetup extends MdlStandaloneSetupGenerated{

	public static void doSetup() {
		new MdlStandaloneSetup().createInjectorAndDoEMFRegistration();
	}
}

