/**
 * 
 */
package org.ddmore.mdl.ui.preference;

import org.ddmore.mdl.ui.internal.MdlActivator;
import org.eclipse.jface.preference.DirectoryFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.mango.mif.utils.encrypt.DesEncrypter;
import com.mango.mif.utils.encrypt.EncryptionException;

/**
 * Preference page for the TES
 * 
 * @author jcarr
 */
public class TESPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    private StringFieldEditor passwordField;
    
    public TESPreferencePage() {
        super(GRID);
        setPreferenceStore(MdlActivator.getInstance().getPreferenceStore());
        setDescription("Task Execution Service preferences");
    }

    public void init(IWorkbench workbench) {
        // no workbench customisations
    }

    @Override
    protected void createFieldEditors() {
        addField(new StringFieldEditor(MDLPreferenceConstants.TES_HOST, "TES &Host", getFieldEditorParent()));
        addField(new IntegerFieldEditor(MDLPreferenceConstants.TES_PORT, "TES &Port", getFieldEditorParent()));
        addField(new StringFieldEditor(MDLPreferenceConstants.TES_SERVICE, "TES Service &Resource", getFieldEditorParent()));

        addField(new StringFieldEditor(MDLPreferenceConstants.TES_UNAME, "TES &Username", getFieldEditorParent()));
        addField(createPasswordInputField(getFieldEditorParent()));

        addField(new DirectoryFieldEditor(MDLPreferenceConstants.TES_CLIENT_SHARED_DIR, "TES Client Shared Location",
                getFieldEditorParent()));
        addField(new StringFieldEditor(MDLPreferenceConstants.TES_SHARED_DIR, "TES Shared Location", getFieldEditorParent()));
        addField(new StringFieldEditor(MDLPreferenceConstants.TES_TOOL_SHARED_DIR, "TES Tool Shared Location", getFieldEditorParent()));
    }

    private FieldEditor createPasswordInputField(Composite parent) {
        passwordField = new StringFieldEditor(MDLPreferenceConstants.TES_PWORD, "TES &Password", parent);
        passwordField.setEmptyStringAllowed(false);
        passwordField.getTextControl(parent).setEchoChar('*');
        return passwordField;
    }
    
    @Override
    public boolean performOk() {
        String oldPasswd = this.getPreferenceStore().getString(MDLPreferenceConstants.TES_PWORD);
        if(!passwordField.getStringValue().equals(oldPasswd)) {
            try {
                passwordField.setStringValue(getDesEncrypter().encrypt(passwordField.getStringValue()));
            } catch (EncryptionException e) {
                passwordField.setErrorMessage("Could not encrypt password");
                return false;
            }
        }
        return super.performOk();
    }
    


    private static final String MIF_ENCRYPTION_KEY_PROP = "mif.encryption.key";

    private static DesEncrypter desEncrypter;
    public static synchronized DesEncrypter getDesEncrypter() throws EncryptionException {
        if (desEncrypter != null) {
            return desEncrypter;
        }
        if (System.getProperties().containsKey(MIF_ENCRYPTION_KEY_PROP)) {
            desEncrypter = new DesEncrypter(System.getProperty(MIF_ENCRYPTION_KEY_PROP));
        } else {
            desEncrypter = new DesEncrypter();
        }
        return desEncrypter;
    }
}
