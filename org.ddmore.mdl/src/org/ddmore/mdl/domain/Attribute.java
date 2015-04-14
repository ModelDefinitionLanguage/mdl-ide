/*
 * MDL IDE, @DDMoRe
 * Author: Natallia Kokash, LIACS, 2014
 * 
 * Container for specifying requirements for distribution attributes
 */
package org.ddmore.mdl.domain;

import java.util.ArrayList;
import java.util.List;

import org.ddmore.mdl.mdl.IndividualVarType;
import org.ddmore.mdl.mdl.InputFormatType;
import org.ddmore.mdl.mdl.LevelType;
import org.ddmore.mdl.mdl.PkMacroType;
import org.ddmore.mdl.mdl.PkParameterType;
import org.ddmore.mdl.mdl.TargetType;
import org.ddmore.mdl.mdl.TrialType;
import org.ddmore.mdl.mdl.UseType;
import org.ddmore.mdl.mdl.VariabilityType;
import org.ddmore.mdl.types.DefaultValues;
import org.ddmore.mdl.types.MdlDataType;

public class Attribute {

	String name = "undefined";
	List<MdlDataType> types = new ArrayList<MdlDataType>();
	Boolean mandatory = false;
	String defaultValue = "";
	AttributeDependency dependency = null;
	
	public Attribute(String name, MdlDataType type, Boolean mandatory){
		this.name = name;
		this.types.add(type);
		this.mandatory = mandatory;
	}	
	
	public Attribute(String name, MdlDataType type, Boolean mandatory, AttributeDependency dependency){
		this(name, type, mandatory);
		this.dependency = dependency;
	}	
	
	public Attribute(String name, List<MdlDataType> types, Boolean mandatory){
		this.name = name;
		this.types = types;
		this.mandatory = mandatory;
	}	
	
	public Attribute(String name, List<MdlDataType> types, Boolean mandatory, AttributeDependency dependency){
		this(name, types, mandatory);
		this.dependency = dependency;
	}	
	
	public Attribute(String name, MdlDataType type, Boolean mandatory, String defaultValue){
		this(name, type, mandatory);
		this.defaultValue = defaultValue;
	}	
	
	public Attribute(String name, MdlDataType type, Boolean mandatory, String defaultValue, AttributeDependency dependency){
		this(name, type, mandatory, defaultValue);
		this.dependency = dependency;	
	}	
	
	public Attribute(String name, List<MdlDataType> types, Boolean mandatory, String defaultValue){
		this(name, types, mandatory);
		this.defaultValue = defaultValue;
	}
	
	public Attribute(String name, List<MdlDataType> types, Boolean mandatory, String defaultValue, AttributeDependency dependency){
		this(name, types, mandatory, defaultValue);
		this.dependency = dependency;
	}	
	
	public String getName(){
		return name;
	}
	
	public MdlDataType getType(){
		if (types.size() > 0)
			return types.get(0);
		return MdlDataType.TYPE_UNDEFINED;
	}
	
	public List<MdlDataType> getTypes(){
		return types;
	}
	
	public List<String> getTypeNames(){
		List<String> typeNames = new ArrayList<String>();
		for (MdlDataType type: types)
			typeNames.add(type.name());
		return typeNames;
	}

	public Boolean isMandatory(){
		return mandatory;
	}
	
	public String getDefaultValue(){
		if (defaultValue.length() > 0)
			return defaultValue;
		if (types.size() > 0){
			MdlDataType type = types.get(0);
			switch (type){
				//Basic
				case TYPE_REAL: case TYPE_PROBABILITY: case TYPE_NAT: case TYPE_INT:      
									return "0";
				case TYPE_PREAL: case TYPE_PNAT:     
									return "1";
				case TYPE_BOOLEAN:  return Boolean.TRUE.toString();
				case TYPE_REF:      return DefaultValues.VAR_NAME;
				case TYPE_CONTINUOUS: case TYPE_VAR_TYPE: 
									return DefaultValues.CC_CONTINUOUS;
				//Enumerations
				case TYPE_TARGET:   return TargetType.NMTRAN_CODE.toString();
				case TYPE_USE:      return UseType.ID.toString();
				case TYPE_RANDOM_EFFECT:  return VariabilityType.SD.toString();
				case TYPE_INPUT_FORMAT:   return InputFormatType.NONMEM_FORMAT.toString();
				case TYPE_INDIVIDUAL_VAR: return IndividualVarType.GENERAL.toString();
				case TYPE_LEVEL:          return LevelType.PARAMETER.toString();
				case TYPE_PK_PARAMETER:   return PkParameterType.VCL.toString();
				case TYPE_PK_MACRO:       return PkMacroType.COMPARTMENT.toString();
				case TYPE_TRIAL:          return TrialType.SIMPLE.toString();
				default: return "";
			}
		}
		return "";
	}
	
	public AttributeDependency getDependency(){
		return dependency;
	}
}
