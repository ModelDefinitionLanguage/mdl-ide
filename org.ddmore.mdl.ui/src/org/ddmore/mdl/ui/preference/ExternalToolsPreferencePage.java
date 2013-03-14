package org.ddmore.mdl.ui.preference;

import org.ddmore.mdl.ui.internal.MdlActivator;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;


public class ExternalToolsPreferencePage extends FieldEditorPreferencePage implements
		IWorkbenchPreferencePage {

	public ExternalToolsPreferencePage() {
		super(GRID);
		setPreferenceStore(MdlActivator.getInstance().getPreferenceStore());
		setDescription("External program preferences");
	}

	public void init(IWorkbench workbench) {
		setPreferenceStore(MdlActivator.getInstance().getPreferenceStore());
	}		

	public void createFieldEditors() {
		addField(new DirectoryFieldEditor(MDLPreferenceConstants.NONMEM_HOME, "&NONMEM home", getFieldEditorParent()));
		addField(new DirectoryFieldEditor(MDLPreferenceConstants.MLXTRAN_HOME, "M&LXTRAN home", getFieldEditorParent()));
		addField(new DirectoryFieldEditor(MDLPreferenceConstants.R_HOME, "&R home", getFieldEditorParent()));
		addField(new DirectoryFieldEditor(MDLPreferenceConstants.MATHLAB_HOME, "&Mathlab home", getFieldEditorParent()));
		
		addField(new StringFieldEditor(MDLPreferenceConstants.LOGIN, "Login", getFieldEditorParent()));
		StringFieldEditor passwordField = new StringFieldEditor(MDLPreferenceConstants.PASSWORD, "Password", getFieldEditorParent());
		passwordField.getTextControl(getFieldEditorParent()).setEchoChar('*');
		addField(passwordField);		

		//addField(new StringFieldEditor(MDLPreferenceConstants.USER_NAME, "", getFieldEditorParent()));
		//addField(new FileFieldEditor(MDLPreferenceConstants.PATH, "", getFieldEditorParent()));
		//addField(new StringFieldEditor(MDLPreferenceConstants.URL, "", getFieldEditorParent()));
	}
}
