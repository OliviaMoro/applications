package main;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DisplayPanel extends JPanel{
	protected CardLayout cl = new CardLayout();
	
	//Listes de commposants
	protected ArrayList<CustomPanel> content = new ArrayList<CustomPanel>();
	//protected List<ArrayList<JComponent>> compList;
	protected ArrayList<BlankPage> pages;
	protected ArrayList<BaseGraph> bases;
	protected ArrayList<ScatterPlot> scatters;
	protected String current; 
	

	public DisplayPanel() {
		//On veut une pile de custom Panel
		this.setLayout(cl);
		
		//On créer notre premierélement de la pile
		Dimension size = new Dimension(350,350);
		CustomPanel panel = new CustomPanel("Page 1",size);
	
		//On créer une indexation des panels
		this.current = "Page 1";
		pages = new ArrayList<BlankPage>();
		bases = new ArrayList<BaseGraph>();
		scatters = new ArrayList<ScatterPlot>();
		
		BlankPage page = new BlankPage(panel);
		pages.add(page);
		panel.addCustomComponent(page);//,1,0);
		BaseGraph graph = new BaseGraph(panel);
		bases.add(graph);
		panel.addCustomComponent(graph);//,2,0);
		
		content.add(panel);
		this.add(panel, "Page 1");	
	}
	
	public CustomPanel getCurrentPanel() {
		int index = Integer.valueOf(this.current.split(" ")[1])-1;
		//System.out.println(index);
		return content.get(index);
	}
	
	public void setCurrentPanel(String current) {
		this.cl.show(this,current);
	}
	
	public String getCurrent() {
		return this.current;
	}
	
	public void setCurrent(String current) {
		this.current = current;
	}

	public void addPage() {
		System.out.println("On ajoute une page");
		String name = "Page ";
		int number = Integer.valueOf(this.current.split(" ")[1])+1;
		name += number;
		CustomPanel newPanel = new CustomPanel(name, new Dimension(400,400));
		BlankPage page = new BlankPage(newPanel);
		pages.add(page);
		newPanel.addCustomComponent(page);
		this.add(newPanel, name);
		this.current = name;
		content.add(newPanel);
	}
	
	public void addBaseGraph() {
        CustomPanel currentPanel = this.getCurrentPanel();
		BaseGraph newGraph = new BaseGraph(currentPanel);
		bases.add(newGraph);
		getCurrentPanel().addCustomComponent(newGraph);
	}
	
	public void addScatterPlot(Float[] arrayX, Float[] arrayY) {
		CustomPanel currentPanel = this.getCurrentPanel();
		int number = Integer.valueOf(this.current.split(" ")[1])-1;
		BaseGraph bg = getBaseGraph(number);
		System.out.println("hasScatterPlot :"+currentPanel.getHasPlot());
		ScatterPlot newPlot = new ScatterPlot(currentPanel,arrayX,arrayY);	
		
		bg.setBaseX(newPlot.getBaseX());
        bg.setBaseY(newPlot.getBaseY());
        if(currentPanel.getHasPlot()) {
        	for (int i = 2; i<currentPanel.getComps().size(); i++) {
        		/*
        		((ScatterPlot) currentPanel.getComps().get(i)).setXScale(newPlot.getXScale());
        		((ScatterPlot) currentPanel.getComps().get(i)).setYScale(newPlot.getYScale());
        		*/
        		((ScatterPlot) currentPanel.getComps().get(i)).setBaseX(newPlot.getBaseX());
        		((ScatterPlot) currentPanel.getComps().get(i)).setBaseY(newPlot.getBaseY());
        	}
        }
		
		scatters.add(newPlot);
		currentPanel.addCustomComponent(newPlot);
	}
	
	
	public BlankPage getBlankPage(int i) {
		return pages.get(i);
	}
	
	public BaseGraph getBaseGraph(int i) {
		return bases.get(i);
	}
	
	public ScatterPlot getScatterPlot(int i) {
		return scatters.get(i);
	}
	
	public ArrayList<ScatterPlot> getScatters(){
		return this.scatters;
	}
	
	
}
