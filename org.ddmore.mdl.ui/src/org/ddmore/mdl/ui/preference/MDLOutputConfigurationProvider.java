package org.ddmore.mdl.ui.preference;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.generator.OutputConfiguration;

import eu.ddmore.mdl.generator.Preferences;

public class MDLOutputConfigurationProvider implements IOutputConfigurationProvider {

public final static String CUSTOM_OUTPUT = "CUSTOM_OUTPUT";

	/**
	* @return a set of {@link OutputConfiguration} available for the generator
	*/
	public Set<OutputConfiguration> getOutputConfigurations() {
		HashSet<OutputConfiguration> res = new HashSet<OutputConfiguration>();

		//default configuration
		/*
		OutputConfiguration defaultOutput = new OutputConfiguration(IFileSystemAccess.DEFAULT_OUTPUT);
		defaultOutput.setDescription("Output Folder");
		defaultOutput.setOutputDirectory("./src-gen");
		defaultOutput.setOverrideExistingResources(true);
		defaultOutput.setCreateOutputDirectory(true);
		defaultOutput.setCleanUpDerivedResources(true);
		defaultOutput.setSetDerivedProperty(true);
		res.add(defaultOutput);*/
		
		//current configuration
		OutputConfiguration customOutput = new OutputConfiguration(CUSTOM_OUTPUT);
		customOutput.setDescription("Output Folder");
		customOutput.setOutputDirectory(Preferences.SRC_GEN_PREFIX);
		customOutput.setOverrideExistingResources(true);
		customOutput.setCreateOutputDirectory(true);
		customOutput.setCleanUpDerivedResources(true);
		customOutput.setSetDerivedProperty(true);
		res.add(customOutput);

		return res;
	}

}