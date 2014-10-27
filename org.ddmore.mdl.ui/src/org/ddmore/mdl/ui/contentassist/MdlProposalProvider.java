/*
* generated by Xtext
*/
package org.ddmore.mdl.ui.contentassist;

import java.util.ArrayList;
import java.util.List;

import org.ddmore.mdl.domain.Attribute;
import org.ddmore.mdl.mdl.AnyExpression;
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.DistributionType;
import org.ddmore.mdl.mdl.IndividualVarType;
import org.ddmore.mdl.mdl.InputFormatType;
import org.ddmore.mdl.mdl.PropertyDeclaration;
import org.ddmore.mdl.mdl.RandomList;
import org.ddmore.mdl.mdl.TargetType;
import org.ddmore.mdl.mdl.TrialType;
import org.ddmore.mdl.mdl.UseType;
import org.ddmore.mdl.mdl.VariabilityType;
import org.ddmore.mdl.mdl.impl.AdministrationBlockImpl;
import org.ddmore.mdl.mdl.impl.ArgumentImpl;
import org.ddmore.mdl.mdl.impl.DiagBlockImpl;
import org.ddmore.mdl.mdl.impl.IndividualVariablesBlockImpl;
import org.ddmore.mdl.mdl.impl.ListImpl;
import org.ddmore.mdl.mdl.impl.MatrixBlockImpl;
import org.ddmore.mdl.mdl.impl.PropertyDeclarationImpl;
import org.ddmore.mdl.mdl.impl.RandomListImpl;
import org.ddmore.mdl.mdl.impl.SameBlockImpl;
import org.ddmore.mdl.mdl.impl.VariabilityBlockImpl;
import org.ddmore.mdl.services.MdlGrammarAccess;
import org.ddmore.mdl.types.VariableType;
import org.ddmore.mdl.ui.contentassist.AbstractMdlProposalProvider;
import org.ddmore.mdl.ui.outline.Images;
import org.ddmore.mdl.validation.AttributeValidator;
import org.ddmore.mdl.validation.DistributionValidator;
import org.ddmore.mdl.validation.PropertyValidator;
import org.ddmore.mdl.validation.UnitValidator;
import org.ddmore.mdl.validation.Utils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.ui.IImageHelper;
import org.eclipse.xtext.ui.editor.contentassist.ConfigurableCompletionProposal;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;

import com.google.inject.Inject;

import eu.ddmore.converter.mdlprinting.MdlPrinter;
/**
 * see http://www.eclipse.org/Xtext/documentation/latest/xtext.html#contentAssist on how to customize content assistant
 */

public class MdlProposalProvider extends AbstractMdlProposalProvider {
		
	@Inject MdlGrammarAccess grammarAccess;
	@Inject IImageHelper imageHelper;
	
	@Override
	public void completeArgument_ArgumentName(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		if (model.eContainer() instanceof ListImpl){
			EObject container = AttributeValidator.findAttributeContainer(model);
			if (container != null){
				Image img = imageHelper.getImage(Images.getPath(Images.ATTRIBUTE));
				List<String> attributes = Utils.getAllNames(AttributeValidator.getAllAttributes(container));
				addProposals(context, acceptor, attributes, img);
			}
		}
		if (model.eContainer() instanceof RandomListImpl){
			RandomList randomList = (RandomList)model.eContainer();
			AnyExpression type = MdlPrinter.getInstance().getAttributeExpression(randomList.getArguments(), DistributionValidator.attr_type.getName());
			Image img = imageHelper.getImage(Images.getPath(Images.DISTRIBUTION_ATTRIBUTE));
			if (type != null && type.getType() != null && type.getType().getDistribution() != DistributionType.NO_DISTRIBUTION){
				List<Attribute> recognized_attrs = DistributionValidator.distr_attrs.get(type.getType().getDistribution());
				if (recognized_attrs != null){
					List<String> attributes = Utils.getAllNames(recognized_attrs);
					addProposals(context, acceptor, attributes, img);
				}
			} else {
				//suggest attribute type
				List<String> attributes = new ArrayList<String>();
				attributes.add(DistributionValidator.attr_type.getName());
				addProposals(context, acceptor, attributes, img);
			}
		}		
	}
	
	@Override
	public void completeArgument_Expression(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		if (model instanceof ArgumentImpl){
			Argument arg = (Argument)model;
			//use
			if (arg.getArgumentName().getName().equals(AttributeValidator.attr_use.getName())){
				List<String> attributes = new ArrayList<String>();
				for (UseType value: UseType.VALUES)
					if (value != UseType.NO_USE) attributes.add(value.toString());
				Image img = imageHelper.getImage(Images.getPath(Images.USE_TYPE));				
				addProposals(context, acceptor, attributes, img); return;
			} else		
			//type (distribution)
			if (arg.eContainer().eContainer() instanceof RandomListImpl){
				if (arg.getArgumentName().getName().equals(DistributionValidator.attr_type.getName())){
					List<String> attributes = new ArrayList<String>();
					for (DistributionType value: DistributionType.VALUES)
						if (value != DistributionType.NO_DISTRIBUTION)	
							attributes.add(value.toString()); 
					Image img = imageHelper.getImage(Images.getPath(Images.DISTRIBUTION_TYPE));
					addProposals(context, acceptor, attributes, img);			
				}
			} else
			//type			
			if (arg.getArgumentName().getName().equals(AttributeValidator.attr_req_type.getName())){
				EObject container = AttributeValidator.findAttributeContainer(arg);
				List<String> attributes = new ArrayList<String>();
				if (container instanceof VariabilityBlockImpl || container instanceof MatrixBlockImpl || 
					container instanceof DiagBlockImpl || container instanceof SameBlockImpl){
					for (VariabilityType value: VariabilityType.VALUES)
						if (value != VariabilityType.NO_VARIABILITY) 
							attributes.add(value.toString());
					Image img = imageHelper.getImage(Images.getPath(Images.VARIABILITY_TYPE));				
					addProposals(context, acceptor, attributes, img); return;
				} else
				if (container instanceof IndividualVariablesBlockImpl){
					for (IndividualVarType value: IndividualVarType.VALUES)
						if (value != IndividualVarType.NO_INDIVIDUAL_VAR) 
							attributes.add(value.toString());
					Image img = imageHelper.getImage(Images.getPath(Images.VARIABILITY_TYPE));				
					addProposals(context, acceptor, attributes, img); 
				} else
				if (container instanceof AdministrationBlockImpl){
					for (TrialType value: TrialType.VALUES)
						if (value != TrialType.NO_TRIAL) 
							attributes.add(value.toString());
					Image img = imageHelper.getImage(Images.getPath(Images.TRIAL_TYPE));				
					addProposals(context, acceptor, attributes, img);
				} else {
					attributes.addAll(VariableType.CC_VALUES);
					Image img = imageHelper.getImage(Images.getPath(Images.CC_TYPE));				
					addProposals(context, acceptor, attributes, img);
				}
			} else
			//units
			if (arg.getArgumentName().getName().equals(AttributeValidator.attr_units.getName())){
				List<String> attributes = new ArrayList<String>();
				attributes.addAll(UnitValidator.getUnitNames());
				Image img = imageHelper.getImage(Images.getPath(Images.EXPRESSION));				
				addProposals(context, acceptor, attributes, img);
			}
		}
	}	
	
	@Override
	public void completePropertyDeclaration_Expression(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor){
		if (model instanceof PropertyDeclarationImpl){
			PropertyDeclaration property = (PropertyDeclaration)model;
			//inputformat
			if (property.getPropertyName().getName().equals(PropertyValidator.attr_inputformat.getName())){
				List<String> values = new ArrayList<String>();
				for (InputFormatType value: InputFormatType.VALUES)
					if (value != InputFormatType.NO_INPUT_FORMAT)
						values.add(value.toString());
				Image img = imageHelper.getImage(Images.getPath(Images.TARGET_LANGUAGE));				
				addProposals(context, acceptor, values, img);
			}
			//target
			if (property.getPropertyName().getName().equals(PropertyValidator.attr_req_target.getName())){
				List<String> attributes = new ArrayList<String>();
				for (TargetType value: TargetType.VALUES)
					if (value != TargetType.NO_TARGET)
						attributes.add(value.toString());
				Image img = imageHelper.getImage(Images.getPath(Images.TARGET_LANGUAGE));				
				addProposals(context, acceptor, attributes, img); return;
			}
		}
	} 
		
	private void addProposals(ContentAssistContext context, ICompletionProposalAcceptor acceptor, 
			List<String> attributes, Image img){
		for (String proposal: attributes){
			StyledString displayedString = new StyledString();
			displayedString.append(proposal);
			ConfigurableCompletionProposal p = doCreateProposal(proposal, displayedString, img, 1000, context);
			acceptor.accept(p);
		}
	}
}
