package org.ddmore.mdl.validation;

import org.ddmore.mdl.domain.Attribute;
import org.ddmore.mdl.types.MdlDataType;

/**
 * Extracted from {@link PropertyValidator} only to circumvent this error: http://jira.mango.local/browse/DDMORE-1506
 */
public class TaskObjectAttributes {

    final public static Attribute attr_task_algo = new Attribute("algo", MdlDataType.TYPE_VECTOR_STRING, false);
    final public static Attribute attr_task_max = new Attribute("max", MdlDataType.TYPE_NAT, false);
    final public static Attribute attr_task_sig = new Attribute("sig", MdlDataType.TYPE_NAT, false);
    final public static Attribute attr_task_cov = new Attribute("cov", MdlDataType.TYPE_BOOLEAN, false);
    final public static Attribute attr_task_simopt = new Attribute("simopt", MdlDataType.TYPE_VECTOR_STRING, false);
    final public static Attribute attr_task_target = new Attribute("target", MdlDataType.TYPE_TARGET, false);
    final public static Attribute attr_task_version = new Attribute("version", MdlDataType.TYPE_STRING, false);

}
