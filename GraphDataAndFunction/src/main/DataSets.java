package main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataSets {
	private List<Float[][]> dataSets = new ArrayList<Float[][]>();
	private List<String[]> variablesSets = new ArrayList<String[]>();
	private Map<String,Float[]> vectorSet = new HashMap<String,Float[]>();
	
	public DataSets() {
		this.dataSets = new ArrayList<Float[][]>();
		this.variablesSets = new ArrayList<String[]>();
		this.vectorSet = new HashMap<String,Float[]>();
	}
	
	public void addDataSet(Float[][] data, String[] variables) {
		this.dataSets.add(data);
		this.variablesSets.add(variables);	
		Map<String,Float[]> vectors = mapDataSet(data, variables);
		vectorSet.putAll(vectors);
	}
	
	
	public Map<String,Float[]> mapDataSet(Float[][] data, String[] variables) {
		Map<String,Float[]> vectors = new HashMap<String,Float[]>();
		for (int i=0; i<variables.length; i++) {
			vectors.put(variables[i], getColumn(data,i));
		}
		return vectors;
	}
	
	public void addVectorSet(Float[] vecteur,String nom) {
		vectorSet.put(nom,vecteur);
	}
	

	public static Float[] getColumn(Float[][] array, int index){
		Float[] column = new Float[array.length]; 
		for(int i=0; i<column.length; i++){
			column[i] = array[i][index];
		}
		return column;
	}
	
	public Map<String, Float[]> getVectorSet() {
		return vectorSet;
	}
	
	public void setVectorSet(Map<String, Float[]> vectorSet) {
		this.vectorSet = vectorSet;
	}

}
