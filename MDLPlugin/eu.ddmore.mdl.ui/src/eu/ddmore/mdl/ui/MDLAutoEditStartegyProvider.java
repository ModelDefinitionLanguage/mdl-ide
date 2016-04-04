package eu.ddmore.mdl.ui;

import org.eclipse.xtext.ui.editor.autoedit.DefaultAutoEditStrategyProvider;

public class MDLAutoEditStartegyProvider extends DefaultAutoEditStrategyProvider {

	@Override
	protected void configureCompoundBracesBlocks(IEditStrategyAcceptor acceptor) {
//		acceptor.accept(compoundMultiLineTerminals.newInstanceFor("[", "]").and("(", ")"), 
//				IDocument.DEFAULT_CONTENT_TYPE);
	}

}
