/**
 * 
 */
package org.ddmore.mdl.ui.preference;

import org.ddmore.mdl.ui.internal.MdlActivator;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preference page for the TES
 * 
 * @author jcarr
 */
public class TESPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    public TESPreferencePage() {
        super(GRID);
        setPreferenceStore(MdlActivator.getInstance().getPreferenceStore());
        setDescription("Task Execution Service preferences");
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
        // no workbench customisations
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
     */
    @Override
    protected void createFieldEditors() {
        addField(new StringFieldEditor(MDLPreferenceConstants.TES_HOST, "TES &Host", getFieldEditorParent()));
        addField(new IntegerFieldEditor(MDLPreferenceConstants.TES_PORT, "TES &Port", getFieldEditorParent()));

        addField(new StringFieldEditor(MDLPreferenceConstants.TES_UNAME, "TES &Username", getFieldEditorParent()));
        addField(new StringFieldEditor(MDLPreferenceConstants.TES_PWORD, "TES &Password", getFieldEditorParent()));

        addField(new DirectoryFieldEditor(MDLPreferenceConstants.TES_SHARED_DIR, "TES &Shared Base Path", getFieldEditorParent()));
        addField(new StringFieldEditor(MDLPreferenceConstants.TES_SHARED_DIR_INPUT, "TES Shared &Input Folder", getFieldEditorParent()));
        addField(new StringFieldEditor(MDLPreferenceConstants.TES_SHARED_DIR_OUTPUT, "TES Shared &Output Folder", getFieldEditorParent()));
    }
}
