package main;

public class DataDialogInfo {
	private String[] variables;
	private Float[][] dataTab;

	public DataDialogInfo(){}
	
	public DataDialogInfo(Float[][] dataTab, String[] variables){
		this.setDataTab(dataTab);
		this.variables = variables;
	}

	public String toString(){
		String str;
		if(this.getDataTab() != null && this.variables != null){
			str = "Nom(s) de(s) variable(s) choisie(s) :  \n";
			for (String nom : variables) {
				str += nom +"\t";
			}
			str += "\n";
		}
		else{
			str = "Aucune information !";
		}
		return str;
	}

	public Float[][] getDataTab() {
		return dataTab;
	}

	public void setDataTab(Float[][] dataTab) {
		this.dataTab = dataTab;
	}
	
	public String[] getVariables() {
		return variables;
	}
	
	public void setVariables(String[] variables) {
		this.variables = variables;
	}
}
