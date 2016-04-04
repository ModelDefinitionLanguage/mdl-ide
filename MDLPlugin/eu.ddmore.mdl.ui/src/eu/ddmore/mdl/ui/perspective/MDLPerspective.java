package eu.ddmore.mdl.ui.perspective;

import java.io.PrintStream;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleConstants;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class MDLPerspective implements IPerspectiveFactory {
	MessageConsole myConsole = null;
	public void createInitialLayout(IPageLayout layout) {
		myConsole = findConsole("Console");
	    MessageConsoleStream stream = myConsole.newMessageStream();
	    PrintStream myS = new PrintStream(stream);
	    System.setOut(myS); 
	    System.setErr(myS); 
	    
	    defineActions(layout);
	    defineLayout(layout);
	}
	
	 private MessageConsole findConsole(String name) {
	      ConsolePlugin plugin = ConsolePlugin.getDefault();
	      IConsoleManager conMan = plugin.getConsoleManager();
	      IConsole[] existing = conMan.getConsoles();
	      for (int i = 0; i < existing.length; i++)
	         if (name.equals(existing[i].getName()))
	            return (MessageConsole) existing[i];
	      //no console found, so create a new one
	      MessageConsole myConsole = new MessageConsole(name, null);
	      conMan.addConsoles(new IConsole[]{myConsole});
	      return myConsole;
	   }
	
	public void defineActions(IPageLayout layout) {
        // Add "new wizards".
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.file");

        // Add "show views".
        layout.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);
        layout.addShowViewShortcut(IPageLayout.ID_EDITOR_AREA);
        layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
//        layout.addShowViewShortcut(IPageLayout.ID_PROGRESS_VIEW);
        layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
        layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
	}
	
	public void defineLayout(IPageLayout layout) {
        String editorArea = layout.getEditorArea();
        layout.setEditorAreaVisible(true);
        layout.setFixed(false);

        IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, .20f, editorArea);
        left.addView(IPageLayout.ID_PROJECT_EXPLORER);
        IFolderLayout leftBottom = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, .55f, "left");
        leftBottom.addView(IPageLayout.ID_PROP_SHEET);

        IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, .75f, editorArea);
//        bottom.addView(IPageLayout.ID_PROGRESS_VIEW);
        bottom.addView(IPageLayout.ID_PROBLEM_VIEW);
        bottom.addView(IConsoleConstants.ID_CONSOLE_VIEW);
        
        IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT, .65f, editorArea);
        right.addView(IPageLayout.ID_OUTLINE);
        
        IWorkbench wb = PlatformUI.getWorkbench();
        IWorkbenchWindow win = wb.getActiveWorkbenchWindow();
        IWorkbenchPage page = win.getActivePage();
		try {
			IConsoleView view = (IConsoleView) page.showView(IConsoleConstants.ID_CONSOLE_VIEW);
	        view.display(myConsole);
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
