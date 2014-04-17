package org.ddmore.mdl.ui;

import org.eclipse.jface.text.IDocument;
import org.eclipse.xtext.ui.editor.autoedit.DefaultAutoEditStrategyProvider;

public class MDLAutoEditStartegyProvider extends DefaultAutoEditStrategyProvider {

	@Override
	protected void configureCompoundBracesBlocks(IEditStrategyAcceptor acceptor) {
		System.out.println("I was called!");
		acceptor.accept(compoundMultiLineTerminals.newInstanceFor("[", "]").and("(", ")"), 
				IDocument.DEFAULT_CONTENT_TYPE);
	}

}
