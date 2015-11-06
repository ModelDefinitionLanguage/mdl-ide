/*
 * generated by Xtext
 */
package eu.ddmore.mdl.generator

import eu.ddmore.converter.mdl2pharmml.Mdl2Pharmml
import eu.ddmore.mdl.MdlStandaloneSetup
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.utils.MclUtils
import java.io.File
import java.io.FileWriter
import java.io.IOException
import org.apache.log4j.Logger
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.parser.ParseException;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;

/**
 * Generates code from your model files on save.
 * 
 * see http://www.eclipse.org/Xtext/documentation.html#TutorialCodeGeneration
 */
class MdlGenerator implements IGenerator {
   private static final Logger LOG = Logger.getLogger(MdlGenerator);
	
	extension MclUtils mu = new MclUtils
	
//	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
////		fsa.generateFile('greetings.txt', 'People to greet: ' + 
////			resource.allContents
////				.filter(typeof(Greeting))
////				.map[name]
////				.join(', '))
//	}


   override void doGenerate(Resource resource, IFileSystemAccess fsa) {
        val String relativeResourcePath = resource.getURI().toPlatformString(true);
        val String[] uriParts = relativeResourcePath.split("/");
        val String projectName = uriParts.get(1);
        val String projectPath = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName).location.toString();

        val String sourcePath = relativeResourcePath.replace('/' + projectName, projectPath);
        val File source = new File(sourcePath);
        val File targetDir = new File(projectPath + Preferences.SRC_GEN_PREFIX);
        
        performConvert(source, targetDir);
    }
    
   def void performConvert(File src, File outputDirectory) {
       val injector = new MdlStandaloneSetup().createInjectorAndDoEMFRegistration()
       val XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet)
       resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE)

       val Resource resource = resourceSet.getResource(URI.createURI("file:///" + src.absolutePath), true);
       val Mcl mcl = resource.getContents().get(0) as Mcl;
       
       val errors = resource.getErrors();
       val warnings = resource.getWarnings();
       if (!warnings.isEmpty()) {
           LOG.warn(warnings.size() + " warning(s) encountered in parsing MDL file " + src.getAbsolutePath());
           for (Diagnostic w : warnings) {
               LOG.warn(w);
           }
       }
       if (!errors.isEmpty()) {
           LOG.error(errors.size() + " errors encountered in parsing MDL file " + src.getAbsolutePath());
           for (Diagnostic e : errors) {
               LOG.error(e);
           }
           throw new ParseException(String.format("Unable to parse MDL file %1$s; %2$d error(s) encountered; see the log output.",
               src.getAbsolutePath(), errors.size()));
       }
       
       val xtendConverter = new Mdl2Pharmml
	   var String baseName = src.getName().replace(".mcl", ".xml");
	   //If .mdl extension is used
	   baseName = baseName.replace(".mdl", ".xml");
       for (mog : mcl.getMogObjects){
    	   val fileName = baseName; 
           val converted = xtendConverter.convertToPharmML(mog);
           
           printOutputFile(fileName, outputDirectory, converted.toString());
//           File targetDirectory = new File(outputDirectory.getPath() + "/target");
//           CharSequence externalCode = xtendConverter.extractTargetCode(mog);
//           if (externalCode.length() > 0)
//        	   printOutputFile(fileName, targetDirectory, externalCode.toString());
       }
   }
   
   def void printOutputFile(String outputFileName, File outputDirectory, String text) {
       val outputFile = new File(outputDirectory.getAbsolutePath() +'/'+ outputFileName);
       var FileWriter of = null
       try {
//           FileUtils.writeStringToFile(outputFile, text);
			of = new FileWriter(outputFile)
			of.write(text, 0, text.length)
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
       finally{
			if(of != null) of.close
       }
   }
}
