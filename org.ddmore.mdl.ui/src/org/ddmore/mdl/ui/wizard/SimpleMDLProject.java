package org.ddmore.mdl.ui.wizard;

import java.net.URI;

//import org.ddmore.mdl.ui.MDLProjectNature;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
//import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.wizards.newresource.BasicNewProjectResourceWizard;

public class SimpleMDLProject extends Wizard implements INewWizard, IExecutableExtension{
	private WizardNewProjectCreationPage mainPage;
	private IConfigurationElement _configurationElement;
	
	public SimpleMDLProject() {
		super();
	}

	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("New MDL Project");
	}
	
	public void addPages() {
		mainPage = new WizardNewProjectCreationPage("basicNewProjectPage");
		mainPage.setTitle("MDL Project");
		mainPage.setDescription("Create a new MDL project.");
		addPage(mainPage);
	}
	
	public IProject createProject(String projectName, URI location) {
		Assert.isNotNull(projectName);
		Assert.isTrue(projectName.trim().length() >= 0);

	    IProject project = createBaseProject(projectName, location);
	    try {
	        addNature(project);	
	        String[] paths = { "src/models", "src/data", "src-gen" }; 
	        addToProjectStructure(project, paths);
	    } catch (CoreException e) {
	        e.printStackTrace();
	        project = null;
	    }
	
	    return project;
	}

	/**
	 * Just do the basics: create a basic project.
	 *
	 * @param location
	 * @param projectName
	 */
	private IProject createBaseProject(String projectName, URI location) {
	    IProject newProject = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
	    if (!newProject.exists()) {
	        URI projectLocation = location;
	        IProjectDescription desc = newProject.getWorkspace().newProjectDescription(newProject.getName());
	        if (location != null && ResourcesPlugin.getWorkspace().getRoot().getLocationURI().equals(location)) {
	            projectLocation = null;
	        }
	
	        desc.setLocationURI(projectLocation);
	        try {
	            newProject.create(desc, null);
	            if (!newProject.isOpen()) {
	                newProject.open(null);
	            }
	        } catch (CoreException e) {
	            e.printStackTrace();
	        }
	    }	
	    return newProject;
	}
	
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
		_configurationElement = config;
	}

	private void createFolder(IFolder folder) throws CoreException {
	    IContainer parent = folder.getParent();
	    if (parent instanceof IFolder) {
	        createFolder((IFolder) parent);
	    }
	    if (!folder.exists()) {
	        folder.create(false, true, null);
	    }
	}

	/**
	 * Create a folder structure with a parent root, overlay, and a few child
	 * folders.
	 *
	 * @param newProject
	 * @param paths
	 * @throws CoreException
	 */
	private void addToProjectStructure(IProject newProject, String[] paths) throws CoreException {
	    for (String path : paths) {
	        IFolder etcFolders = newProject.getFolder(path);
	        createFolder(etcFolders);
	    }
	}

	//SetDescription does not work!
	private void addNature(IProject project) throws CoreException {
	    /*if (!project.hasNature(MDLProjectNature.NATURE_ID)) {
	        IProjectDescription description = project.getDescription();
	        String[] prevNatures = description.getNatureIds();
	        String[] newNatures = new String[prevNatures.length + 1];
	        System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
	        newNatures[prevNatures.length] = MDLProjectNature.NATURE_ID;
	        description.setNatureIds(newNatures);
	
	        IProgressMonitor monitor = null;
	        project.setDescription(description, monitor);
	    }*/
	}

	public boolean performFinish() {
		 String name = mainPage.getProjectName();
		 URI location = null;
		 if (!mainPage.useDefaults()) {
			 location = mainPage.getLocationURI();
		 } // else location == null
		 createProject(name, location);
		 BasicNewProjectResourceWizard.updatePerspective(_configurationElement);
		 return true;
	}

}
