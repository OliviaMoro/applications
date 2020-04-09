package main;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("unused")
public class Data {
	private Float[][] dataTab;
	private List<List<Float>> dataList;
	private File file;
	
	public Data() {
		
	}
	
	public Data(File f) {
		this.file = f;
		this.dataList = dataFromFile(f); 
		this.dataTab = listToArray(this.dataList);
	}
	
	public List<List<Float>> dataFromFile(File file){
		List<List<Float>> dataList = new ArrayList<>();
		Scanner inputStream = null;
		try {
			//Data retrieve
			inputStream = new Scanner(file);
			while(inputStream.hasNext()){
				String line = inputStream.next();
				String[] values = line.split(",");
				dataList.addAll(Arrays.asList(listToFloat(values)));
			}
			inputStream.close();

		} catch(Exception error) {
			error.printStackTrace();
		}	
		return dataList;
	}
	
	public Float[][] listToArray(List<List<Float>> liste){
		Float[][] newListe = new Float[liste.size()][liste.get(0).size()];
		int iRow = 0;
		for (List<Float> ligne : liste) {
			int iCol = 0;
			for (Float el : ligne) {
				newListe[iRow][iCol] = el;
				iCol += 1;
			}
			iRow += 1;
		}
		return newListe;
	}
	
	public List<Float> listToFloat(String[] liste) {
		final List<Float> floats = new ArrayList<Float>();
		for (String el : liste) {
			floats.add(Float.valueOf(el));
		}	
		return floats;
	}

	public Float[][] getDataTab() {
		return dataTab;
	}

	public void setDataTab(Float[][] dataTab) {
		this.dataTab = dataTab;
	}

	public List<List<Float>> getDataList() {
		return dataList;
	}

	public void setDataList(List<List<Float>> dataList) {
		this.dataList = dataList;
	}
	
	/*public static void main(String[] args) {
		File file = new File("src/test/testFile.txt");
		System.out.println("file exist : "+ file.exists());
		
		List<List<Float>> liste = new ArrayList<>();
		liste.add((List<Float>) Arrays.asList(1F,0.1F));
		liste.add((List<Float>) Arrays.asList(2F,0.2F));
		liste.add((List<Float>) Arrays.asList(3F,0.4F));
		liste.add((List<Float>) Arrays.asList(4F,0.5F));
		liste.add((List<Float>) Arrays.asList(5F,0.6F));
		System.out.println("qqch : "+liste.size());
		
		for(List<Float> line : liste) {
			for(Float el : line) {
				System.out.print(el);
			}
			System.out.print("\n");
		}
		
		Data data = new Data();
		Float[][] array = data.listToArray(liste);
		Float[][] arrayO = {{1F,0.1F},{2F,0.2F},{3F,0.4F},{4F,0.5F},{5F,0.6F}};
		Float[][] newListe = new Float[liste.size()][liste.get(0).size()];
		System.out.println("qqch : "+ liste.get(0).size());
		
	}*/

}
