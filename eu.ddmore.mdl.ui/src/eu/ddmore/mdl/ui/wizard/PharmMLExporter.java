package eu.ddmore.mdl.ui.wizard;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.parser.ParseException;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import eu.ddmore.converter.mdl2pharmml.Mdl2Pharmml;
import eu.ddmore.mdl.generator.MdlGenerator;
import eu.ddmore.mdl.generator.Preferences;
import eu.ddmore.mdl.mdl.Mcl;
import eu.ddmore.mdl.utils.MclUtils;

public class PharmMLExporter {

//    @Inject
//    private MdlGenerator generator;

    @Inject
    protected IResourceSetProvider resourceSetProvider;
	
	
	private static final int DEFAULT_BUFFER_SIZE = 16*1024;
	
    /**
     *  Creates the specified file system directory at <code>destinationPath</code>.
     *  This creates a new file system directory.
     *  
     *  @param destinationPath location to which files will be written
     */
    public void createFolder(IPath destinationPath) {
        new File(destinationPath.toOSString()).mkdir();
    }

    /**
     *  Writes the passed resource to the specified location recursively.
     *  
     *  @param resource the resource to write out to the file system
     *  @param destinationPath location where the resource will be written
     *  @exception CoreException if the operation fails 
     *  @exception IOException if an I/O error occurs when writing files
     */
    public void write(IResource resource, IPath destinationPath)
            throws CoreException, IOException {
        if (resource.getType() == IResource.FILE) {
//			writeFile((IFile) resource, destinationPath);
        	MdlGenerator mg = new MdlGenerator();
			mg.performConvert((IFile) resource, destinationPath);
		} else {
			writeChildren((IContainer) resource, destinationPath);
		}
    }

    /**
     *  Exports the passed container's children
     */
    protected void writeChildren(IContainer folder, IPath destinationPath)
            throws CoreException, IOException {
        if (folder.isAccessible()) {
            IResource[] children = folder.members();
            for (int i = 0; i < children.length; i++) {
                IResource child = children[i];
                writeResource(child, destinationPath.append(child.getName()));
            }
        }
    }

//    protected final Boolean doGenerateHandlingErrors(final IPath file, final IFile source) {
//
//        final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
//
//        final URI uri = URI.createPlatformResourceURI(source.getFullPath().toString(), true);
//        final ResourceSet rs = resourceSetProvider.get(source.getProject());
//        final Resource r = rs.getResource(uri, true);
//        final IProject project = source.getProject();
//
////        if (generator != null) {
//            Mcl mcl = (Mcl) r.getContents().get(0);
//            MclUtils mu = new MclUtils();
//            if (mcl != null && mu.getMogObject(mcl) == null) { // No MOGs defined
//        		IDEWorkbenchPlugin.log("PharmML generation error: no MOG found!"); //$NON-NLS-1$
//                return false;
//            }
//            try {
//                generator.doGenerate(source, fsa);
//            } catch (ParseException pex) {
//            	IDEWorkbenchPlugin.log("PharmML generation error: " + pex.getMessage());
//                return false;
//            }
//            return true;
//        }
//        return false;
//    }

//    /**
//     *  Writes the passed file resource to the specified destination on the local
//     *  file system
//     */
//    protected void writeFile(IFile file, IPath destinationPath)
//            throws IOException, CoreException {
//        OutputStream output = null;
//        InputStream contentStream = null;
//
//        try {
//        	
//            contentStream = new BufferedInputStream(file.getContents(false));
//            output = new BufferedOutputStream(new FileOutputStream(destinationPath.toOSString()));
//            // for large files, need to make sure the chunk size can be handled by the VM
//            int available = contentStream.available();
//            available = available <= 0 ? DEFAULT_BUFFER_SIZE : available;
//            int chunkSize = Math.min(DEFAULT_BUFFER_SIZE, available);
//            byte[] readBuffer = new byte[chunkSize];
//            int n = contentStream.read(readBuffer);
//
//            while (n > 0) {
//            	// only write the number of bytes read
//                output.write(readBuffer, 0, n);
//                n = contentStream.read(readBuffer);
//            }
//        } finally {
//            if (contentStream != null) {
//            	// wrap in a try-catch to ensure attempt to close output stream
//            	try {
//            		contentStream.close();
//            	}
//            	catch(IOException e){
//            		IDEWorkbenchPlugin
//							.log(
//									"Error closing input stream for file: " + file.getLocation(), e); //$NON-NLS-1$
//            	}
//			}
//        	if (output != null) {
//        		// propogate this error to the user
//           		output.close();
//			}
//        }
//    }

    /**
     *  Writes the passed resource to the specified location recursively
     */
    protected void writeResource(IResource resource, IPath destinationPath)
            throws CoreException, IOException {
        if (resource.getType() == IResource.FILE) {
        	MdlGenerator mg = new MdlGenerator();
			mg.performConvert((IFile) resource, destinationPath);
		} else {
            createFolder(destinationPath);
            writeChildren((IContainer) resource, destinationPath);
        }
    }
}
