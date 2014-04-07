package eu.ddmore.mdl.generator;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.ddmore.mdl.MdlStandaloneSetup;
import org.ddmore.mdl.mdl.Mcl;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import eu.ddmore.converter.mdlprinting.MdlPrinter;

public class Mdl2NonmemWrapper extends MdlPrinter implements IGenerator {
    private static final Logger LOGGER = Logger.getLogger(Mdl2NonmemWrapper.class);

    //TODO: This is actually a property of the runtime eclipse that the MDLEditor user can change. We need to find that by some Preference class.
    private static final String SRC_GEN_PREFIX = "/src-gen";

    public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
        String relativeResourcePath = resource.getURI().toPlatformString(true);
        String[] uriParts = relativeResourcePath.split("/");
        String projectName = uriParts[1];
        String projectPath = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).getLocation().toString();

        String sourcePath = relativeResourcePath.replace('/' + projectName, projectPath);
        File source = new File(sourcePath);
        File targetDir = new File(projectPath + SRC_GEN_PREFIX);
        
        performConvert(source, targetDir);
        /*
        //TODO: How do we pass that info to the user?
        try {
//            Converter converter = converterManager.getConverter(mdl, target);
//            converter.convert(source, targetDir);
        } catch (ConverterNotFoundException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        */
    }
    
    public void performConvert(File src, File outputDirectory) {
        Injector injector = new MdlStandaloneSetup().createInjectorAndDoEMFRegistration();
        XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
        resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);

        Resource resource = resourceSet.getResource(URI.createURI("file:///" + src.getAbsolutePath()), true);
        Mcl mcl = (Mcl) resource.getContents().get(0);

        eu.ddmore.converter.mdl2nonmem.Mdl2Nonmem xtendConverter = new eu.ddmore.converter.mdl2nonmem.Mdl2Nonmem();
        CharSequence converted = xtendConverter.convertToNMTRAN(mcl);

        printOutputFile(src, outputDirectory, converted.toString(), ".ctl");
    }

    public void printOutputFile(File src, File outputDirectory, String text, String fileEnding) {
        String outputFileName = src.getName().replace(".mdl", fileEnding);
        File outputFile = new File(outputDirectory.getAbsolutePath() +'/'+ outputFileName);

        try {
            FileUtils.writeStringToFile(outputFile, text);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
