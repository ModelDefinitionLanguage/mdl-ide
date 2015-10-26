/**
 * 
 */
package org.ddmore.mdl.validation;

import org.ddmore.mdl.domain.Attribute;
import org.ddmore.mdl.mdl.InputFormatType;
import org.ddmore.mdl.types.DefaultValues;
import org.ddmore.mdl.types.MdlDataType;

/**
 * Extracted from {@link PropertyValidator} only to circumvent this error: http://jira.mango.local/browse/DDMORE-1506
 */
public class SourceBlockAttributes {

    final public static Attribute attr_ignore = new Attribute("ignore", MdlDataType.TYPE_STRING, false);
    final public static Attribute attr_inputformat = new Attribute("inputformat", MdlDataType.TYPE_INPUT_FORMAT, true, InputFormatType.NONMEM_FORMAT.toString());
    final public static Attribute attr_delimiter = new Attribute("delimiter", MdlDataType.TYPE_STRING, false, ",");
    final public static Attribute attr_header = new Attribute("header", MdlDataType.TYPE_BOOLEAN, false, "false");
    final public static Attribute attr_file = new Attribute("file", MdlDataType.TYPE_STRING, true, DefaultValues.FILE_NAME);
    final public static Attribute attr_script = new Attribute("script", MdlDataType.TYPE_STRING, true, DefaultValues.FILE_NAME);
    final public static Attribute attr_skip = new Attribute("skip", MdlDataType.TYPE_NAT, false);
    final public static Attribute attr_nrows = new Attribute("nrows", MdlDataType.TYPE_NAT, false);

}
