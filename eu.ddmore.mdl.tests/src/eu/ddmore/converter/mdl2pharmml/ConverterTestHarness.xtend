package eu.ddmore.converter.mdl2pharmml

import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.utils.MclUtils
import java.io.BufferedWriter
import java.io.FileReader
import java.io.FileWriter

class ConverterTestHarness {

	extension MclUtils mu = new MclUtils
	extension Mdl2Pharmml mpc = new Mdl2Pharmml


	def readFile(String fileName){
		var FileReader in = null
		try{
			in = new FileReader(fileName)
			var char[] buf = newCharArrayOfSize(2048)
			var mdlStr = ""
			var c = 0
			while((c = in.read(buf, 0, buf.length)) != -1){
				mdlStr = mdlStr.concat(new String(buf, 0, c))
			}
			mdlStr
		}
		finally{
			in?.close
		}
	}
	
	
	def void convertTo(Mcl mcl, String destFile){
		val mogObj = mcl.mogObject
		val output = mogObj.convertToPharmML
		var out = new BufferedWriter(new FileWriter(destFile))
		out.write(output.toString, 0, output.length)
		out.close
	}
	
}