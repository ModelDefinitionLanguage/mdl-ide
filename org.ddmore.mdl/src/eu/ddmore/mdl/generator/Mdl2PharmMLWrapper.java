package eu.ddmore.mdl.generator;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
//import org.apache.log4j.Logger;
import org.ddmore.mdl.MdlStandaloneSetup;
import org.ddmore.mdl.mdl.MOGObject;
import org.ddmore.mdl.mdl.Mcl;
import org.ddmore.mdl.validation.Utils;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.parser.ParseException;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import eu.ddmore.converter.mdlprinting.MdlPrinter;


public class Mdl2PharmMLWrapper extends MdlPrinter implements IGenerator {
   private static final Logger LOGGER = Logger.getLogger(Mdl2PharmMLWrapper.class);

   public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
        String relativeResourcePath = resource.getURI().toPlatformString(true);
        String[] uriParts = relativeResourcePath.split("/");
        String projectName = uriParts[1];
        String projectPath = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).getLocation().toString();

        String sourcePath = relativeResourcePath.replace('/' + projectName, projectPath);
        File source = new File(sourcePath);
        File targetDir = new File(projectPath + Preferences.SRC_GEN_PREFIX);
        
        performConvert(source, targetDir);
    }
    
   public void performConvert(File src, File outputDirectory) {
       Injector injector = new MdlStandaloneSetup().createInjectorAndDoEMFRegistration();
       XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
       resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);

       Resource resource = resourceSet.getResource(URI.createURI("file:///" + src.getAbsolutePath()), true);
       Mcl mcl = (Mcl) resource.getContents().get(0);
       
       EList<Diagnostic> errors = resource.getErrors();
       EList<Diagnostic> warnings = resource.getWarnings();
       if (!warnings.isEmpty()) {
           LOGGER.warn(warnings.size() + " warning(s) encountered in parsing MDL file " + src.getAbsolutePath());
           for (Diagnostic w : warnings) {
               LOGGER.warn(w);
           }
       }
       if (!errors.isEmpty()) {
           LOGGER.error(errors.size() + " errors encountered in parsing MDL file " + src.getAbsolutePath());
           for (Diagnostic e : errors) {
               LOGGER.error(e);
           }
           throw new ParseException(String.format("Unable to parse MDL file %1$s; %2$d error(s) encountered; see the log output.",
               src.getAbsolutePath(), errors.size()));
       }
       
       eu.ddmore.converter.mdl2pharmml.Mdl2PharmML xtendConverter = new eu.ddmore.converter.mdl2pharmml.Mdl2PharmML();
       List<MOGObject> mogs = Utils.getMOGs(mcl); 
	   String baseName = src.getName().replace(".mcl", ".xml");
	   //If .mdl extension is used
	   baseName = baseName.replace(".mdl", ".xml");
       for (int i = 0; i < mogs.size(); i++){
    	   MOGObject mog = mogs.get(i);
    	   String fileName = baseName;
    	   if (i > 0) fileName = fileName + Utils.getObjectName(mog); 
           CharSequence converted = xtendConverter.convertToPharmML(mog, fileName);
           
           printOutputFile(fileName, outputDirectory, converted.toString());
//           File targetDirectory = new File(outputDirectory.getPath() + "/target");
//           CharSequence externalCode = xtendConverter.extractTargetCode(mog);
//           if (externalCode.length() > 0)
//        	   printOutputFile(fileName, targetDirectory, externalCode.toString());
       }
   }
   
   public void printOutputFile(String outputFileName, File outputDirectory, String text) {
       File outputFile = new File(outputDirectory.getAbsolutePath() +'/'+ outputFileName);
       try {
           FileUtils.writeStringToFile(outputFile, text);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }

}
