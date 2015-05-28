package org.ddmore.mdl.ui.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

/**
 * {@link org.eclipse.core.commands.IHandler} implementation for MDL->PharmML generation invoked within the active editor window.
 */
public class ConvertToPharmMLHandler extends AbstractConvertToPharmMLHandler {

    public Object execute(final ExecutionEvent event) throws ExecutionException {

        final IEditorPart activeEditor = HandlerUtil.getActiveEditor(event);

        final IFile file = (IFile) activeEditor.getEditorInput().getAdapter(IFile.class);

        if (file != null) {

            if (activeEditor instanceof XtextEditor) {
                ((XtextEditor) activeEditor).getDocument().readOnly(
                    new IUnitOfWork<Boolean, XtextResource>() {

                        public Boolean exec(final XtextResource source) throws Exception {
                            return doGenerateHandlingErrors(file, source);
                        }
                    }
                );
            }
        }
        return null;
    }

}

