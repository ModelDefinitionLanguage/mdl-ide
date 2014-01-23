/*
 * Interface for the standalone execution of MDL converters
 */
package org.ddmore.mdl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.JavaIoFileSystemAccess;
import org.eclipse.xtext.resource.XtextResourceSet;

import com.google.inject.Injector;

import eu.ddmore.mdl.generator.Mdl2Nonmem;

public class Main {
	
	//args: 1) MDL file name, 2) output folder
	public static void main(String[] args){
		System.out.println("Hello! I am the standalone MDL converter toolbox!");	
		if (args.length > 0){
			new org.eclipse.emf.mwe.utils.StandaloneSetup().setPlatformUri("../");
			Injector injector = new MdlStandaloneSetup().createInjectorAndDoEMFRegistration();
			XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
			URI fileURI = URI.createURI("./model.mdl");
			Resource resource = resourceSet.createResource(fileURI);
			InputStream in;
		    try {
		    	in = new FileInputStream(args[0]);
				resource.load(in, resourceSet.getLoadOptions());
				System.out.println("Converting file to NM-TRAN...");
		        Mdl2Nonmem generator = injector.getInstance(Mdl2Nonmem.class);
		        JavaIoFileSystemAccess fsa = injector.getInstance(JavaIoFileSystemAccess.class);
		        String outputFolder = "C://Temp/";
		        if (args.length > 1){
		        	outputFolder = args[1];
		        }
		        fsa.setOutputPath(outputFolder);
		        if ((generator != null) && (fsa != null)){	
		        	System.out.println("Called Mdl2Nonmem converter...");
		        	generator.doGenerate(resource, fsa);
					System.out.println("Code generation completed!");
		        }
		        in.close(); 
		    } catch (FileNotFoundException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	}
}
