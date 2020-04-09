package test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import main.DataSets;

class TestDataSets {
	private DataSets dataSet = new DataSets();
	private Float[][] array = {{1F,0.1F},{2F,0.2F},{3F,0.4F},{4F,0.5F},{5F,0.6F}};
	private Float[] columnX = {1F, 2F, 3F, 4F, 5F};
	private Float[] columnY = {0.1F, 0.2F, 0.4F, 0.5F, 0.6F};
	private String[] noms = {"x", "y"};
	//private Map<String,Float[]> vectorSet = createTestVectorSet();
	
	public Map<String, Float[]> createTestVectorSet() {
		Map<String,Float[]> vectorSet = new HashMap<String,Float[]>();
		vectorSet.put("x", columnX);
		vectorSet.put("y", columnY);
		return vectorSet;
	}
	
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

	@Test
	void testGetColumn() {
		Float[] column = {1F, 2F, 3F, 4F, 5F};
		int i = 0;
		for (Float el : column) {
			assertEquals(el, DataSets.getColumn(array, 0)[i]);
			i++;
		}
	}
	
	@Test
	void testAddDataSet() {
		dataSet.addDataSet(array,noms);
		 //Parcours HashMap
		int i = 0;
		 for (Map.Entry<String,Float[]> mapentry : dataSet.getVectorSet().entrySet()) {
			 System.out.println("clé: "+mapentry.getKey());
			 System.out.println(mapentry.getValue().length);
			 assertEquals(noms[i],mapentry.getKey());
			 int j = 0;
			 for(Float val : mapentry.getValue()) {
				 System.out.println("valeur : "+val);
				 assertEquals(array[j][i], val);
				 j++;
			 }
			 i++;
		 }
	
	}


	/*@Test
	void testGetVectorSet() {
		fail("Not yet implemented");
	}

	@Test
	void testSetVectorSet() {
		fail("Not yet implemented");
	}*/

}
