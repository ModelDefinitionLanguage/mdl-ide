package org.ddmore.mdl.types;

import java.util.Arrays;
import java.util.List;

public class VariabilityType {
	public final static String CC_CONTINUOUS = "continuous";
	public final static String CC_CATEGORICAL = "categorical";
	public final static String CC_LIKELIHOOD = "LIKELIHOOD";

	public final static List<String> CC_VALUES = Arrays.asList(CC_CONTINUOUS, CC_CATEGORICAL, CC_LIKELIHOOD);
}
