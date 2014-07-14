package org.ddmore.mdl.types;

import java.util.Arrays;
import java.util.List;

public class VariableType {
	public final static String CC_CONTINUOUS = "continuous";
	public final static String CC_CATEGORICAL = "categorical";
	public final static String CC_LIKELIHOOD = "likelihood";
	public final static String CC_M2LL = "M2LL";

	public final static List<String> CC_VALUES = Arrays.asList(CC_CONTINUOUS, CC_CATEGORICAL, CC_LIKELIHOOD, CC_M2LL);
}
