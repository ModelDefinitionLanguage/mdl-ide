package eu.ddmore.mdl.ui.wizard;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.parser.ParseException;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import eu.ddmore.converter.mdl2pharmml.Mdl2Pharmml;
import eu.ddmore.mdl.MdlStandaloneSetup;
import eu.ddmore.mdl.mdl.Mcl;
import eu.ddmore.mdl.mdl.MclObject;
import eu.ddmore.mdl.utils.MdlUtils;

public class PharmMLExporter {

//    private static final int DEFAULT_BUFFER_SIZE = 16*1024;
	
    /**
     *  Creates the specified file system directory at <code>destinationPath</code>.
     *  This creates a new file system directory.
     *  
     *  @param destinationPath location to which files will be written
     */
    protected void createFolder(IPath destinationPath) {
        new File(destinationPath.toOSString()).mkdir();
    }

    /**
     * Convert a MDL file to PharmML and write it to the specified file.
     * 
     * @param src - {@link IFile} identifying the MDL file to convert
     * @param dest - {@link IPath} specifying the output file to which to write out the converted PharmML
     * @throws IOException if an error occurred writing to the output file
     */
    private void performConvert(IFile src, IPath dest) throws IOException {

        final Injector injector = new MdlStandaloneSetup().createInjectorAndDoEMFRegistration();
        final XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);

        final URI uri = URI.createURI(src.getRawLocationURI().toString());
        final Resource resource = resourceSet.getResource(uri, true);


        // TODO: Check for syntax errors and warnings, and semantic errors and warnings,
        // and presumably display them to the user. Similar to MDLToPharmMLConverter
        // and MDLValidator from converter.mdl project, but not sure if we can actually
        // share the code between the two; the latter uses Converter Toolbox classes.

        if (!resource.getErrors().isEmpty()) {
            throw new ParseException("Unable to parse MDL file, it has syntax errors.");
        }
//        
//        final EList<Diagnostic> errors = resource.getErrors();
//        final EList<Diagnostic> warnings = resource.getWarnings();
//        if (!warnings.isEmpty()) {
//            LOG.warn(warnings.size() + " warning(s) encountered in parsing MDL file " + src.fullPath);
//            for (Diagnostic w : warnings) {
//                LOG.warn(w);
//            }
//        }
//        if (!errors.isEmpty()) {
//            LOG.error(errors.size() + " errors encountered in parsing MDL file " + src.fullPath);
//            for (Diagnostic e : errors) {
//                LOG.error(e);
//            }
//            throw new ParseException(String.format("Unable to parse MDL file %1$s; %2$d error(s) encountered; see the log output.",
//                src.fullPath, errors.size()));
//        }
//
       // validate the resource
//        final IResourceValidator validator = injector.getInstance(IResourceValidator.class);
//       List<Issue> list = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);


        final Mcl mcl = (Mcl) resource.getContents().get(0);
        
        final Iterable<MclObject> mogs = new MdlUtils().getMogObjects(mcl);
        
        // TODO: We're currently making an assumption that there will be a single MOG
        // in the provided file.  This should be fine for Product 4.
        // This will be addressed under DDMORE-1221
        Iterator<MclObject> mogsIt = mogs.iterator();
        if (!mogsIt.hasNext()) {
            throw new IllegalStateException("Must be (at least) one MOG defined in the provided MCL file: " + src); 
        }
        final MclObject mog = mogsIt.next();
        

        final Mdl2Pharmml xtendConverter = new Mdl2Pharmml();
        
        final CharSequence converted = xtendConverter.convertToPharmML(mog);
        final IPath newDest = dest.removeFileExtension().addFileExtension("xml");
        writeOutputFile(newDest, converted.toString());

    }
    
    private void writeOutputFile(IPath outputDirectory, String text) throws IOException {
        final File outputFile = outputDirectory.toFile();
        FileUtils.writeStringToFile(outputFile, text);
    }

    /**
     *  Writes the passed resource to the specified location recursively.
     *  
     *  @param resource the resource to write out to the file system
     *  @param destinationPath location where the resource will be written
     *  @exception CoreException if the operation fails 
     *  @exception IOException if an I/O error occurs when writing files
     */
    protected void write(IResource resource, IPath destinationPath)
            throws CoreException, IOException {
        if (resource.getType() == IResource.FILE) {
//			writeFile((IFile) resource, destinationPath);
			performConvert((IFile) resource, destinationPath);
		} else {
			writeChildren((IContainer) resource, destinationPath);
		}
    }

    /**
     *  Exports the passed container's children
     */
    private void writeChildren(IContainer folder, IPath destinationPath)
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
    private void writeResource(IResource resource, IPath destinationPath)
            throws CoreException, IOException {
        if (resource.getType() == IResource.FILE) {
			performConvert((IFile) resource, destinationPath);
		} else {
            createFolder(destinationPath);
            writeChildren((IContainer) resource, destinationPath);
        }
    }
    
}
