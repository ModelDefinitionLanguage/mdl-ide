package org.ddmore.mdl.validation;

import java.util.ArrayList;
import java.util.List;

import org.ddmore.mdl.domain.Variable;
import org.ddmore.mdl.mdl.DataObject;
import org.ddmore.mdl.mdl.DataObjectBlock;
import org.ddmore.mdl.mdl.ImportObjectBlock;
import org.ddmore.mdl.mdl.ImportObjectStatement;
import org.ddmore.mdl.mdl.MOGObject;
import org.ddmore.mdl.mdl.MclObject;
import org.ddmore.mdl.mdl.MdlPackage;
import org.ddmore.mdl.mdl.ModelObject;
import org.ddmore.mdl.mdl.ModelObjectBlock;
import org.ddmore.mdl.mdl.ParameterObject;
import org.ddmore.mdl.mdl.ParameterObjectBlock;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.types.MdlDataType;
import org.eclipse.core.resources.IFile;
import org.eclipse.xtext.validation.AbstractDeclarativeValidator;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;

import com.google.inject.Inject;

public class MOGValidator extends AbstractDeclarativeValidator{

	public final static String MSG_MODEL_OBJ_MISSING = "MOG should include a model object";
	public final static String MSG_DATA_OBJ_MISSING  = "MOG should include a data object";
	public final static String MSG_PARAM_OBJ_MISSING = "MOG should include a parameter object";
	public final static String MSG_TASK_OBJ_MISSING  = "MOG should include a task object";
	public final static String MSG_OBJ_DEFINED          = "Cannot create a MOG";
	public final static String MSG_MODEL_DATA_MISMATCH  = "Inconsistent sets of model/data variables";
	public final static String MSG_STRUCTURAL_MISMATCH  = "Inconsistent sets of structural parameters";
	public final static String MSG_VARIABILITY_MISMATCH = "Inconsistent sets of variability parameters";
	public final static String MSG_MOG_FILE_NOT_FOUND   = "Cannot find MDL file: path may be incorrect";

	@Override
    @Inject
    public void register(EValidatorRegistrar registrar) {}

	/*Validate that MDL files are in the workspace*/
	@Check
	public void validateMOGObjectFiles(ImportObjectStatement s){
		if (s.getImportURI() != null){
		    IFile file = Utils.getFile(s, s.getImportURI());
		    if (!file.exists()){
				warning(MSG_MOG_FILE_NOT_FOUND, 
					MdlPackage.Literals.IMPORT_OBJECT_STATEMENT__IMPORT_URI,
					MSG_MOG_FILE_NOT_FOUND, s.getImportURI());
			} else {
				/* Check that the reffered object exists in the given file?
				Injector injector = new MdlStandaloneSetup().createInjectorAndDoEMFRegistration();
				XtextResourceSet resourceSet = injector.getInstance(XtextResourceSet.class);
				resourceSet.addLoadOption(XtextResource.OPTION_RESOLVE_ALL, Boolean.TRUE);
	            URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
				Resource resource = resourceSet.getResource(uri, true);
				Mcl mcl = (Mcl) resource.getContents().get(0);
				*/
			}
		}
	}
	
	/*Validate MOG object set*/
	@Check
	public void validateMOGObjectSet(ImportObjectBlock objBlock){
		Integer [] params = {0, 0, 0, 0};
		List<Variable> varList = new ArrayList<Variable>();
		for (ImportObjectStatement s: objBlock.getObjects()){
			if (s.getSymbolName() != null)
				varList.add(new Variable(s.getSymbolName().getName(), MdlDataType.getDerivedType(s)));
		}
		for (Variable var: varList){
			MdlDataType objType = var.getType();
			if (objType == MdlDataType.TYPE_OBJ_REF_MODEL) params[0] += 1;
			if (objType == MdlDataType.TYPE_OBJ_REF_PARAM) params[1] += 1;
			if (objType == MdlDataType.TYPE_OBJ_REF_DATA)  params[2] += 1;
			if (objType == MdlDataType.TYPE_OBJ_REF_TASK)  params[3] += 1;
		}
		if (params[0] == 0)
			warning(MSG_MODEL_OBJ_MISSING, 
				MdlPackage.Literals.IMPORT_OBJECT_BLOCK__OBJECTS,
				MSG_MODEL_OBJ_MISSING, objBlock.getIdentifier());
		if (params[1] == 0)
			warning(MSG_PARAM_OBJ_MISSING, 
					MdlPackage.Literals.IMPORT_OBJECT_BLOCK__OBJECTS,
					MSG_PARAM_OBJ_MISSING, objBlock.getIdentifier());
		if (params[2] == 0)
			warning(MSG_DATA_OBJ_MISSING, 
					MdlPackage.Literals.IMPORT_OBJECT_BLOCK__OBJECTS,
					MSG_DATA_OBJ_MISSING, objBlock.getIdentifier());
		if (params[3] == 0)
			warning(MSG_TASK_OBJ_MISSING, 
					MdlPackage.Literals.IMPORT_OBJECT_BLOCK__OBJECTS,
					MSG_TASK_OBJ_MISSING, objBlock.getIdentifier());
		String [] names = {"model", "parameter", "data", "task"};
		for (int i = 0; i < 4; i++){
			if (params[i] > 1)
				warning(MSG_OBJ_DEFINED + ": two or more " + names[i] + " objects selected!", 
					MdlPackage.Literals.IMPORT_OBJECT_BLOCK__OBJECTS,
					MSG_OBJ_DEFINED,  objBlock.getIdentifier());
		}			
		for (int i = 0; i < 4; i++)
			if (params[i] != 1) return;
	}
	
	/*Validate objects grouped to a MOG*/
	@Check
	public void validateMOG(MOGObject mog){
		List<MclObject> objects = Utils.getMOGObjects(mog);
		ModelObject mObj        = Utils.getModelObject(objects);
		ParameterObject pObj    = Utils.getParameterObject(objects);
		DataObject dObj         = Utils.getDataObject(objects);
		//Model object vs data object
		if (mObj != null && dObj != null)
			validateMOG_Model_vs_Data(mObj, dObj, mog);
		//Model object vs. parameter object
		if (mObj != null && pObj != null){
			validateMOG_Structural_Model_vs_Parameter(mObj, pObj, mog);
			validateMOG_Variability_Model_vs_Parameter(mObj, pObj, mog);
		}
	}	
	
	//MODEL_INPUT_VARIABLES \in DATA_INPUT_VARIABLES + DATA_DERIVED_VARIABLES
	private void validateMOG_Model_vs_Data(ModelObject mObj, DataObject dObj, MOGObject mog){
		List<String> dVars = new ArrayList<String>();
		for (DataObjectBlock b: dObj.getBlocks()){
			if (b.getDataInputBlock() != null)
				for (SymbolDeclaration s: b.getDataInputBlock().getVariables())
					if (s.getSymbolName() != null) dVars.add(s.getSymbolName().getName());
			if (b.getDataDerivedBlock() != null)
				for (SymbolDeclaration s: b.getDataDerivedBlock().getVariables())
					if (s.getSymbolName() != null) dVars.add(s.getSymbolName().getName());
		}
		for (ModelObjectBlock b: mObj.getBlocks()){
			if (b.getInputVariablesBlock() != null){
				for (SymbolDeclaration s: b.getInputVariablesBlock().getVariables()){
					if (s.getSymbolName() != null) {
						String varName = s.getSymbolName().getName();
						if (!dVars.contains(varName))
							warning(MSG_MODEL_DATA_MISMATCH + 
								": no mapping for model variable " + varName + " found in " + 
								Utils.getObjectName(dObj).getName() + " object", 
								MdlPackage.Literals.MOG_OBJECT__IDENTIFIER,
								MSG_MODEL_DATA_MISMATCH,  mog.getIdentifier());
					}
				}
			}
		}
	}
	
	//STRUCTURAL vs. STRUCTURAL_PARAMETERS
	private void validateMOG_Structural_Model_vs_Parameter(ModelObject mObj, ParameterObject pObj, MOGObject mog){
		List<String> structuralVars = new ArrayList<String>();
		//Get structural variables
		for (ModelObjectBlock b: mObj.getBlocks())
			if (b.getStructuralParametersBlock() != null)
				for (SymbolDeclaration s: b.getStructuralParametersBlock().getParameters())
					if (s.getSymbolName() != null) structuralVars.add(s.getSymbolName().getName());
		for (ParameterObjectBlock b: pObj.getBlocks()){
			if (b.getStructuralBlock() != null){
				for (SymbolDeclaration s: b.getStructuralBlock().getParameters()){
					if (s.getSymbolName() != null){
						String varName = s.getSymbolName().getName();
						if (varName.length() > 0){
							if (!structuralVars.contains(varName))
								warning(MSG_STRUCTURAL_MISMATCH + 
									": no mapping for parameter " + varName + " found in " + 
									Utils.getObjectName(mObj).getName() + " object", 
									MdlPackage.Literals.MOG_OBJECT__IDENTIFIER,
									MSG_STRUCTURAL_MISMATCH, mog.getIdentifier());
						}
					} 
				}
			}
		}
	}
	
	//VARIABILITY vs. VARIABILITY_PARAMETERS
	private void validateMOG_Variability_Model_vs_Parameter(ModelObject mObj, ParameterObject pObj, MOGObject mog){
		List<String> variabilityVars = new ArrayList<String>();
		//Get structural variables
		for (ModelObjectBlock b: mObj.getBlocks())
			if (b.getVariabilityParametersBlock() != null)
				for (SymbolDeclaration s: b.getVariabilityParametersBlock().getParameters())
					if (s.getSymbolName() != null) variabilityVars.add(s.getSymbolName().getName());
		for (ParameterObjectBlock b: pObj.getBlocks()){
			if (b.getVariabilityBlock() != null){
				for (SymbolDeclaration s: b.getVariabilityBlock().getParameters()){
					if (s.getSymbolName() != null){
						String varName = s.getSymbolName().getName();
						if (varName.length() > 0){
							if (!variabilityVars.contains(varName))
								warning(MSG_VARIABILITY_MISMATCH + 
									": no mapping for parameter " + varName + " found in " + 
									Utils.getObjectName(mObj).getName() + " object", 
									MdlPackage.Literals.MOG_OBJECT__IDENTIFIER,
									MSG_VARIABILITY_MISMATCH,  mog.getIdentifier());
						}
					} 
				}
			}
		}
	}
}
