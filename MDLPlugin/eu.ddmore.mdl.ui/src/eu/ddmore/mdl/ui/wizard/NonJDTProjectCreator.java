package eu.ddmore.mdl.ui.wizard;

import java.util.List;

import org.eclipse.xtext.ui.XtextProjectHelper;

import com.google.common.collect.ImmutableList;


public class NonJDTProjectCreator extends MdlProjectCreator {
	protected static final String SRC_ROOT = "models";
	protected static final String SRC_GEN_ROOT = "scripts";
	protected final List<String> SRC_FOLDER_LIST = ImmutableList.of(SRC_ROOT, SRC_GEN_ROOT);

	protected String getModelFolderName() {
		return SRC_ROOT;
	}
	
	@Override
	protected List<String> getAllFolders() {
        return SRC_FOLDER_LIST;
    }

	@Override
	protected String[] getProjectNatures(){
		return new String[] {
				"org.eclipse.pdr.PluginNature",
				"org.eclipse.xtext.ui.shared.xtextNature"
		};
	}
	
	@Override
	protected String[] getBuilders(){
		return new String[] {
				"org.eclipse.pde.ManifestBuilder",
				"org.eclipse.pde.SchemaBuilder",
				XtextProjectHelper.BUILDER_ID
		};
	}
	
}
