package eu.ddmore.mdl.provider

//class PropertyDefinitionTable {
	//	static val TARGET_TYPE = new BuiltinEnumTypeInfo('target', #{'monolix', 'nonmem' })
//	static val TARGET_ATT = new AttributeDefn('target', null, true, TARGET_TYPE)
//	static val VERSION_ATT = new AttributeDefn('version', null, false, MclTypeProvider::STRING_TYPE)
//	public static val ALGO_TYPE = new BuiltinEnumTypeInfo('estAlgo', #{'saem', 'foce', 'fo', 'focei'})
//	static val ALGO_ATT = new AttributeDefn('algo', true, ALGO_TYPE)
//	static val EST_OP_TYPE = new BuiltinEnumTypeInfo('estOp', #{'fim', 'estPop', 'estIndiv' })
//	static val EST_OP_ATT = new AttributeDefn('operation', null, false, EST_OP_TYPE)
//	public static val SOLVER_TYPE = new BuiltinEnumTypeInfo('solver', #{'stiff', 'nonStiff' })
//	public static val SOLVER_ATT = new AttributeDefn('solver', false, SOLVER_TYPE)
//	public static val ARM_SIZE_ATT = new AttributeDefn('armSize', false, TypeSystemProvider::INT_TYPE)
//	public static val TOTAL_SIZE_ATT = new AttributeDefn('totalSize', false, TypeSystemProvider::INT_TYPE)
//	public static val NUM_SAMPLES_ATT = new AttributeDefn('numberSamples', false, TypeSystemProvider::INT_TYPE)
//	public static val TOTAL_COST_ATT = new AttributeDefn('totalCost', false, TypeSystemProvider::REAL_TYPE)
//	public static val NUM_ARMS_ATT = new AttributeDefn('numberArms', false, TypeSystemProvider::INT_TYPE)

//	public static val Map<String, List<AttributeDefn>> propertyDefns = #{ 
//		BlockDefinitionTable::ESTIMATE_BLK -> #[ALGO_ATT],
//		BlockDefinitionTable::SIMULATE_BLK -> #[SOLVER_ATT],
//		BlockDefinitionTable::DES_STUDY_DESIGN -> #[ARM_SIZE_ATT, TOTAL_SIZE_ATT, NUM_SAMPLES_ATT, TOTAL_COST_ATT, NUM_ARMS_ATT]
//	}
	
/* the following was commented out inside propertyDefns declaration
 * 		BlockDefinitionProvider::ESTIMATE_BLK -> #[TARGET_ATT, EST_OP_ATT, VERSION_ATT, ALGO_ATT],
* 		BlockDefinitionProvider::SIMULATE_BLK -> #[SOLVER_ATT, VERSION_ATT, TARGET_ATT]
*/
	
//}