/*
 
* generated by Xtext
 */
package eu.ddmore.mdl.ui.contentassist

import com.google.common.base.Predicate
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.Expression
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.mdllib.mdllib.SymbolDefinition
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.PropertyDefinitionProvider
import eu.ddmore.mdl.type.BuiltinEnumTypeInfo
import eu.ddmore.mdl.type.PrimitiveType
import eu.ddmore.mdl.type.TypeSystemProvider
import java.util.ArrayList
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.jface.viewers.StyledString
import org.eclipse.swt.graphics.Image
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.CrossReference
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.RuleCall
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor

import static extension org.eclipse.xtext.EcoreUtil2.getContainerOfType

/**
 * see http://www.eclipse.org/Xtext/documentation.html#contentAssist on how to customize content assistant
 */
class MdlProposalProvider extends AbstractMdlProposalProvider {

	extension ListDefinitionProvider listHelper = new ListDefinitionProvider
	extension BuiltinFunctionProvider bfc = new BuiltinFunctionProvider
	extension PropertyDefinitionProvider pdp = new PropertyDefinitionProvider
	extension TypeSystemProvider mtp = new TypeSystemProvider

	 public override void completeSymbolReference_Ref(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	 	val owningObj = model.getContainerOfType(MclObject)
	 	val expectedType = switch(model){
	 							ValuePair:
	 								model.attributeType
	 							EquationTypeDefinition:
	 								model.typeFor
	 							Expression:
	 								model.typeFor
	 							default:
	 								TypeSystemProvider::UNDEFINED_TYPE
							}
	 	val booleanFilter = new Predicate<IEObjectDescription>()
	 	{
				override apply(IEObjectDescription input) {
					val s = input.EObjectOrProxy
					if(s instanceof SymbolDefinition){
						val sOwningObj = s.eContainer.getContainerOfType(MclObject)
						if(sOwningObj != null && sOwningObj == owningObj){
							return s.typeFor.isCompatible(expectedType)
						}
					}
					false
				}
				
	 	}
		lookupCrossReference((assignment.getTerminal() as CrossReference), context, acceptor, booleanFilter)
	}

	public override void completeCategoryValueReference_Ref(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
	 	val booleanFilter = new Predicate<IEObjectDescription>()
	 	{
				override apply(IEObjectDescription input) {
					false
				}
				
	 	}
		lookupCrossReference((assignment.getTerminal() as CrossReference), context, acceptor, booleanFilter);
	}
	
	
 
	override complete_IS(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		var node = context.lastCompleteNode
		val owningBlock = EcoreUtil2.getContainerOfType(model, BlockStatement) 
		while(node != null && owningBlock != null){
			val nodeTxt = node.text
			val matchingAtts = owningBlock.getAllMatchingListDefns(nodeTxt)
			if(matchingAtts.exists[attType.theType == PrimitiveType.Enum ]){
				addProposals(context, acceptor, #['is'], null)
				node = null
			}
			else
				node = node.nextSibling
		}
	}
	
	
	override complete_ASSIGN(EObject model, RuleCall ruleCall, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		var node = context.lastCompleteNode
		val owningBlock = EcoreUtil2.getContainerOfType(model, BlockStatement) 
		while(node != null && owningBlock != null){
			val nodeTxt = node.text
			val matchingAtts = owningBlock.getAllMatchingListDefns(nodeTxt)
			if(matchingAtts.exists[attType.theType != PrimitiveType.Enum ]){
				addProposals(context, acceptor, #['='], null)
				node = null
			}
			else
				node = node.nextSibling
		}
	}

//	private def createFuncEnumProposal(BuiltinFunctionCall fCall, EnumPair model, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
//		val enumType = bfc.getNamedArgumentType(model)
//		val attributes = new ArrayList<String>
//		if(enumType instanceof BuiltinEnumTypeInfo){
//			attributes.addAll((enumType as BuiltinEnumTypeInfo).expectedValues)
//		}
//		addProposals(context, acceptor, attributes, null);
//	} 


	private def createListEnumProposal(EnumPair model, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		val parentBlock = model.getContainerOfType(BlockStatement)
		val enumType = getTypeOfEnumAttribute(parentBlock.identifier, model.argumentName)
		val attributes = new ArrayList<String>
		if(enumType instanceof BuiltinEnumTypeInfo){
			attributes.addAll((enumType as BuiltinEnumTypeInfo).expectedValues)
		}
		addProposals(context, acceptor, attributes, null);
	} 

	private def createPropertyEnumProposal(EnumPair model, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		val enumType = model.typeForProperty
		val attributes = new ArrayList<String>
		if(enumType instanceof BuiltinEnumTypeInfo){
			attributes.addAll((enumType as BuiltinEnumTypeInfo).expectedValues)
		}
		addProposals(context, acceptor, attributes, null);
	} 

	override completeEnumPair_Expression(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		val listParent = model.getContainerOfType(AttributeList)
		if(model instanceof EnumPair){
			if(listParent != null){
				createListEnumProposal(model, context, acceptor)
			}
			else{
//				val funcParent = model.getContainerOfType(BuiltinFunctionCall)
//				if(funcParent != null){
//						createFuncEnumProposal(funcParent, model, context, acceptor)
//				}
//				else{
					val propParent = model.getContainerOfType(PropertyStatement)
					if(propParent != null){
						createPropertyEnumProposal(model, context, acceptor)
					}
				}
//			}
		}
	}
	
	def addProposals(ContentAssistContext context, ICompletionProposalAcceptor acceptor, List<String> attributes, Image img){
		for (String proposal: attributes){
			val displayedString = new StyledString();
			displayedString.append(proposal);
			val p = createCompletionProposal(proposal, displayedString, img, context)
//			val p = doCreateProposal(proposal, displayedString, img, 1000, context)
			acceptor.accept(p);
		}
	}
}
