package eu.ddmore.mdl.utils

import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.HashSet
import java.util.LinkedList

class CycleDetectionUtils {
	extension DependencyWalker dw = new DependencyWalker
	
	def boolean hasNonDerivCycle(SymbolDefinition it, (SymbolDefinition) => void foundLambda, (SymbolDefinition) => boolean isExcluded){
		var cycle = false
		if(isExcluded.apply(it)){
			val startVar = name
			val stack = new LinkedList<SymbolDefinition>
			val visited = new HashSet<String>
			it.getSymbolReferences.forEach[stack.push(it)]
//			var cycle = false
			while(!stack.isEmpty && !cycle){
				val currRef = stack.pop
				val alreadyVisited = !visited.add(currRef.name)
				if(!alreadyVisited && isExcluded.apply(currRef)){
					if(startVar == currRef.name){
						// cycle detected!
						cycle = true
						foundLambda.apply(currRef)
//						error("Symbol '" + name + "' contains an expression that refers to itself.",
//								MdlLibPackage::eINSTANCE.symbolDefinition_Name,
//								MdlValidator::INVALID_CYCLE, name)
					}
					else{
						val newRefs = currRef.getSymbolReferences
						// skip derivatives from cycle detection
						newRefs.forEach[
							stack.push(it)
						]
					}
				}
			}
		}
		cycle
	}


//    def isDerivativeDefinition(SymbolDefinition sd){
//    	val lstType = sd.typeFor
//    	lstType?.typeName == ListDefinitionTable::DERIV_TYPE.typeName
//    }
    
}