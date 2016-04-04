package eu.ddmore.mdl.ui.outline;

/**
 * 
 * Image name constants.
 *
 */
public class Images {
	private static final String ICONS_PATH="diagona-icons-1.0/icons/16/";
	
//	public static final String MDL = "019-mdl.png";
	public static final String MDL = "023.png";
	public static final String TASK_OBJ ="152-task.png";
	public static final String PARAMETER_OBJ ="153-parameter.png";
	public static final String MODEL_OBJ ="154-model.png";
	public static final String DESIGN_OBJ ="155.png";
	public static final String DATA_OBJ ="157-data.png";
	public static final String MOG_OBJ ="160-tel.png";

	public static final String BLOCK_STMT ="188.png";

	
	public static final String ATTRIBUTE ="012-attribute.png";
	public static final String DISTRIBUTION_ATTRIBUTE = "011-distribution-attribute.png";
	public static final String CONDITION ="044-question.png";
	public static final String PARENT ="046-parent.png";
	public static final String FUNCTION ="054-function.png";
	public static final String PARAMETER_DECLARATION ="055-lambda.png";
	public static final String LOG ="059-log.png";
	public static final String ODE ="083-ode.png";
	public static final String EXPRESSION ="089-expression.png";
	public static final String EXPRESSION_AND ="089-expression.png";
	public static final String EXPRESSION_OR ="089-expression.png";
	public static final String EXPRESSION_CONDITION ="089-expression.png";
	public static final String FALSE ="089-expression.png";
	public static final String TRUE ="089-expression.png";
	public static final String ADD ="103-add.png";
	public static final String REMOVE ="104-remove.png";
	public static final String IGNORE ="101-no.png";
	public static final String ACCEPT ="102-accept.png";
	public static final String MIXTURE ="124-mixture.png";
	public static final String REFERENCE ="183-reference.png";
	public static final String ATTRIBUTE_REFERENCE ="065-attr-reference.png";
	public static final String LIST ="187-list.png";
	public static final String TARGET ="196-target.png";
//	public static final String SYMBOL_DECLARATION = "175-variable.png";
	public static final String SYMBOL_DECLARATION = "055-lambda.png";
	public static final String FUNCTION_CALL_STATEMENT = "176-variable-mod.png";
	public static final String RANDOM ="092-random.png";
	public static final String DROP ="104-remove.png";
	
	public static final String STRUCTURAL_BLOCK = LIST;
	public static final String VARIABILITY_BLOCK = LIST;

	public static final String DATA_INPUT_BLOCK = LIST;

	public static final String COVARIATE_DEFINITION_BLOCK = LIST;
	public static final String VARIABILITY_DEFINITION_BLOCK = LIST;
	public static final String STRUCTURAL_PARAMETERS_BLOCK = LIST;
	public static final String VARIABILITY_PARAMETERS_BLOCK = LIST;
	public static final String MODEL_PREDICTION_BLOCK = LIST;
	public static final String OBSERVATIONS_BLOCK = LIST;
	public static final String OUTPUT_VARIABLES_BLOCK = LIST;
	public static final String GROUP_VARIABLES_BLOCK = LIST;
	public static final String RANDOM_VARIABLES_BLOCK = LIST;
	public static final String INDIVIDUAL_VARIABLES_BLOCK = LIST;
	public static final String DATA_BLOCK = LIST;

	public static final String DISTRIBUTION_TYPE = "071-distribution-type.png";
	public static final String CC_TYPE ="072-var-type.png";
	public static final String USE_TYPE = "073-use-type.png";
	public static final String TARGET_LANGUAGE = "074-target-language.png";
	public static final String VARIABILITY_TYPE = "075-variability-type.png";
	public static final String TRIAL_TYPE= "076-interpolation-type.png";
	
	public static final String TASK_FUNCTION_DECLARATION = "146-task.png";
	public static final String ESTIMATE_TASK = "127-task-estimate.png";
	public static final String SIMULATE_TASK = "126-task-simulate.png";
	public static final String EXECUTE_TASK = "125-task-execute.png";

	public static final String ARGUMENT_NAME = ATTRIBUTE;
	public static final String PRIMARY = EXPRESSION;
	public static final String VECTOR = "186.png";
	
	public static final String getPath(String path) {
		return ICONS_PATH + path;
	}
}
