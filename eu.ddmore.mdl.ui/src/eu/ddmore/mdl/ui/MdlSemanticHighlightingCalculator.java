package eu.ddmore.mdl.ui;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration;
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultSemanticHighlightingCalculator;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightedPositionAcceptor;

import eu.ddmore.mdl.mdl.BlockStatement;
import eu.ddmore.mdl.mdl.EnumExpression;
import eu.ddmore.mdl.mdl.MclObject;
import eu.ddmore.mdl.mdl.MdlPackage;
import eu.ddmore.mdl.mdl.ValuePair;

public class MdlSemanticHighlightingCalculator extends DefaultSemanticHighlightingCalculator{
	
	@Override
	protected void doProvideHighlightingFor(XtextResource resource,
			IHighlightedPositionAcceptor acceptor) {
		EObject rootObject = resource.getParseResult().getRootASTElement();
		
		for (BlockStatement g : EcoreUtil2.getAllContentsOfType(rootObject, BlockStatement.class)) {
			for (INode node : NodeModelUtils.findNodesForFeature(g, MdlPackage.Literals.BLOCK_STATEMENT__BLK_ID)) {
				
				acceptor.addPosition(node.getOffset(), node.getLength(), DefaultHighlightingConfiguration.KEYWORD_ID);
			}
		}
		for (EnumExpression g : EcoreUtil2.getAllContentsOfType(rootObject, EnumExpression.class)) {
			for (INode node : NodeModelUtils.findNodesForFeature(g, MdlPackage.Literals.ENUM_EXPRESSION__ENUM_VALUE)) {
				
				acceptor.addPosition(node.getOffset(), node.getLength(), DefaultHighlightingConfiguration.TASK_ID);
			}
		}
		for (ValuePair g : EcoreUtil2.getAllContentsOfType(rootObject, ValuePair.class)) {
			for (INode node : NodeModelUtils.findNodesForFeature(g, MdlPackage.Literals.VALUE_PAIR__ARGUMENT_NAME)) {
				
				acceptor.addPosition(node.getOffset(), node.getLength(), DefaultHighlightingConfiguration.KEYWORD_ID);
			}
		}
		for (MclObject g : EcoreUtil2.getAllContentsOfType(rootObject, MclObject.class)) {
			for (INode node : NodeModelUtils.findNodesForFeature(g, MdlPackage.Literals.MCL_OBJECT__OBJ_ID)) {
				
				acceptor.addPosition(node.getOffset(), node.getLength(), DefaultHighlightingConfiguration.KEYWORD_ID);
			}
		}
		super.doProvideHighlightingFor(resource, acceptor);
	}
	
}