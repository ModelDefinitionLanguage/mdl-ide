package org.ddmore.mdl.types;

import java.util.Arrays;
import java.util.List;

public class InputFormatType {
	
	final public static String FORMAT_NONMEM = "nonmemFormat";
	final public static String FORMAT_EVENT = "eventFormat"; 

	public final static List<String> FORMAT_VALUES = Arrays.asList(FORMAT_NONMEM, FORMAT_EVENT);
}
