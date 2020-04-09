package main;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class PythonLambda {
	
	public PythonLambda() {
		
	}
	
	public Float[] createBoundedArray(Float min,Float max,int points) {
		Float[] array = new Float[points];
		for(int i=0; i<points; i++) {
			array[i] = min + (max-min)*i/(float)(points-1);
		}
		return array;
	}


	public Float[] arrayPython(Float[] array,String expr) {
		ScriptEngineManager mgr = new ScriptEngineManager();
		ScriptEngine pyEngine = mgr.getEngineByName("python");
		Float[] result = new Float[array.length]; 
		try {
			pyEngine.eval("from math import *");
			pyEngine.eval("f = lambda x : "+expr);
			for(int i=0; i<array.length; i++) {
				Double nb = (Double) pyEngine.eval("f("+array[i]+")");
				String chaine = nb.toString();
				Float nbF = Float.valueOf(chaine);
				result[i] = nbF;
			}

		} catch (ScriptException ex) {
			//ex.printStackTrace();
			//ex.getCause();
			ex.getMessage();
			//ex.getClass();
		}    
		finally {
			
		}
		return result;
	}
	
	public static void main(String[] args) {
		Float[] array;
		Float[] result;
		PythonLambda pl = new PythonLambda();
		array = pl.createBoundedArray(0f,9f,10);
		result = pl.arrayPython(array,"x*x");
		for(Float f : result) {
			System.out.println(f);
		}
	}
}
