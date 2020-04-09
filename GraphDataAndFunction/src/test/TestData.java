package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import main.Data;

class TestData {
	private File file = new File("src/test/testFile.txt");;
	private Data data = new Data(file); 
	private List<List<Float>> liste = createTestList();
	private Float[][] array = {{1F,0.1F},{2F,0.2F},{3F,0.4F},{4F,0.5F},{5F,0.6F}};
	private String[] arrayString = {"1","0.1"};
	
	
	@BeforeClass
	public void setUpBeforeClass() throws Exception {
		System.out.print("test");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	public List<List<Float>> createTestList(){
		List<List<Float>> liste = new ArrayList<>();
		liste.add((List<Float>) Arrays.asList(1F,0.1F));
		liste.add((List<Float>) Arrays.asList(2F,0.2F));
		liste.add((List<Float>) Arrays.asList(3F,0.4F));
		liste.add((List<Float>) Arrays.asList(4F,0.5F));
		liste.add((List<Float>) Arrays.asList(5F,0.6F));
		return liste;
	}

	/* teste getDataList() et dataFromFile(file) */
	@Test
	void testDataFromFile() {
		assertEquals(liste,data.dataFromFile(file));
	}
	
	/* teste getDataTab() et listToArray(liste) */
	@Test
	void testListToArray() {
		for(int i=0; i<array.length; i++) {
			for(int j=0; j<array[i].length; j++) {
				//System.out.print(array[i][j].equals(data.getDataTab()[i][j])+"\t");
				assertEquals(array[i][j],data.getDataTab()[i][j]);
			}
			//System.out.print("\n");
		}
	
	}

	@Test
	void testListToFloat() {
		List<Float> listeFloat = new ArrayList<Float>();
		listeFloat.add(1F);
		listeFloat.add(0.1F);
		assertEquals(listeFloat,data.listToFloat(arrayString));
	}


	@Test
	void testSetDataTab() {
		Float[][] newDataTab = {};
		data.setDataTab(newDataTab);
		assertEquals(newDataTab,data.getDataTab());
	}


	@Test
	void testSetDataList() {
		List<List<Float>> newDataList = new ArrayList<>();
		data.setDataList(newDataList);
		assertEquals(newDataList,data.getDataList());
	}

}
