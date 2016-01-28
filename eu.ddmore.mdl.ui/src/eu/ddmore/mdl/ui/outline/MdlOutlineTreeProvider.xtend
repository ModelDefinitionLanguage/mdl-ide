/*
* generated by Xtext
*/
package eu.ddmore.mdl.ui.outline

import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.BlockTextBody
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.mdllib.mdllib.SymbolDefinition
import org.eclipse.xtext.ui.editor.outline.IOutlineNode
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider

/**
 * Customization of the default outline structure.
 *
 * see http://www.eclipse.org/Xtext/documentation.html#outline
 */
class MdlOutlineTreeProvider extends DefaultOutlineTreeProvider {
	
//	@Inject IImageHelper imageHelper;

// 	def Image _image(Mcl e) {
//        return imageHelper.getImage(getPath(MDL));
//    }
//    
//	def Image _image(MclObject e) {
//		return switch(e.mdlObjType){
//			case MdlValidator::MDLOBJ: imageHelper.getImage(getPath(MODEL_OBJ))
//			case MdlValidator::DATAOBJ: imageHelper.getImage(getPath(DATA_OBJ))
//			case MdlValidator::PARAMOBJ: imageHelper.getImage(getPath(PARAMETER_OBJ))
//			case MdlValidator::TASKOBJ: imageHelper.getImage(getPath(TASK_OBJ))
//			case MdlValidator::MOGOBJ: imageHelper.getImage(getPath(MOG_OBJ))
//			case MdlValidator::DESIGNOBJ: imageHelper.getImage(getPath(DESIGN_OBJ))
//			default: null			
//		}
//    }
//
//	def Image _image(SymbolDefinition s){
//		return imageHelper.getImage(getPath(SYMBOL_DECLARATION));
//	}
//
//	def Image _image(BlockStatement s){
//		return imageHelper.getImage(getPath(STRUCTURAL_BLOCK));
//	}
//
//	def Image _image(Expression e){
//		return imageHelper.getImage(getPath(EXPRESSION));
//	}
//
//	def Image _image(BuiltinFunctionCall s){
//		return imageHelper.getImage(getPath(FUNCTION));
//	}

	def _isLeaf(SymbolDefinition s) {
		true
	}

	def _isLeaf(AnonymousListStatement s) {
		true
	}

	def _isLeaf(ValuePair vp) { true }


//	def _createChildren(IOutlineNode parentNode, BuiltinFunctionCall fc){
//		val args = fc.argList
//		switch(args){
//			NamedFuncArguments:
//				args.arguments.forEach[createNode(parentNode, it)]
//			UnnamedFuncArguments:
//				args.args.forEach[createNode(parentNode, it)]
//		}
//	}

	def _createChildren(IOutlineNode parentNode, BlockStatement blk){
		val body = blk.body
		switch(body){
			BlockStatementBody:
				body.statements.forEach[s|
					if(s instanceof PropertyStatement){
						s.properties.forEach[createNode(parentNode, it)]
					}
					else createNode(parentNode, s)
				]
			BlockTextBody:
				createNode(parentNode, body)
		}
	}

	def _createChildren(IOutlineNode parentNode, ListDefinition list){
		list.list.attributes.forEach[createNode(parentNode, it)]
	}

}
