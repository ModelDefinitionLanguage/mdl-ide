package org.ddmore.mdl.ui.handler;

import org.apache.log4j.Logger;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.validation.Utils;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.parser.ParseException;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.ddmore.mdl.generator.Mdl2PharmMLWrapper;
import eu.ddmore.mdl.generator.Preferences;


/**
 * Base abstract implementation of {@link IHandler} used by both the MDL->PharmML generation
 * invoked within the active editor window, and the MDL->PharmML generation invoked from
 * the Project Explorer tree view (these are implemented slightly differently).
 */
abstract class AbstractConvertToPharmMLHandler extends AbstractHandler implements IHandler {

    private static final Logger LOGGER = Logger.getLogger(AbstractConvertToPharmMLHandler.class);

    @Inject
    private Mdl2PharmMLWrapper generator;

    @Inject
    private Provider<JavaIoFileSystemAccess> fileAccessProvider;

    @Inject
    protected IResourceSetProvider resourceSetProvider;

    /**
     * Generate the PharmML and display any errors to the user.
     * Creates the output folder first if it does not already exist.
     * <p>
     * @param file - {@link IFile} referencing the MDL file in the workspace to be converted
     * @param source - {@link Resource} referencing the Xtext parsed version of the MDL file
     * @return the result of the execution, is ignored according to {@link IHandler#execute(org.eclipse.core.commands.ExecutionEvent)}
     */
    protected final Boolean doGenerateHandlingErrors(final IFile file, final Resource source) {

        final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

        final IProject project = file.getProject();
        final IFolder srcGenFolder = project.getFolder(Preferences.SRC_GEN_PREFIX);
        if (!srcGenFolder.exists() && (Preferences.SRC_GEN_PREFIX.length() > 0)) {
            try {
                srcGenFolder.create(true, true, new NullProgressMonitor());
            } catch (CoreException e) {
                LOGGER.error("Unable to create output folder for PharmML generation: " + srcGenFolder, e);
                MessageDialog.openError(shell, "Error",
                        "Internal error in attempting to create output folder for PharmML generation; see the log output for details.");
                return false;
            }
        }

        final JavaIoFileSystemAccess fsa = fileAccessProvider.get();
        fsa.setOutputPath(srcGenFolder.getRawLocation().toString());

        if (generator != null) {
            Mcl mcl = (Mcl) source.getContents().get(0);
            if (mcl != null && Utils.getMOGs(mcl).size() == 0) { // No MOGs defined
                MessageDialog.openError(shell, "Error", "PharmML generation error: no MOG found!");
                return false;
            }
            try {
                generator.doGenerate(source, fsa);
            } catch (ParseException pex) {
                MessageDialog.openError(shell, "Error", "PharmML generation error: " + pex.getMessage());
                return false;
            }
            try {
                srcGenFolder.refreshLocal(IResource.DEPTH_INFINITE, null);
            } catch (CoreException e) {
                LOGGER.error("CoreException thrown while trying to convert to PharmML", e);
                MessageDialog.openError(shell, "Error",
                        "Internal error in attempting to convert to PharmML; see the log output for details.");
                return false;
            }

//            MessageDialog.openInformation(shell, "Information", "PharmML successfully generated to "
//                + srcGenFolder.getFile(file.getName().replaceAll("\\.mdl$", ".xml")).getFullPath());
            return true;
        }
        return false;
    }

    /**
     * The MDL->PharmML handlers are always enabled.
     * @return true
     */
    public boolean isEnabled() {
        return true;
    }

}
