package main;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

//import javax.swing.JLayeredPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CustomPanel extends JPanel{
	private String name;
	private Dimension size;
	private ArrayList<CustomComponent> comps = new ArrayList<CustomComponent>();
	private boolean hasPlot;
	
	public CustomPanel(String name) {
		this.name = name;
		this.setPreferredSize(new Dimension(300,300));
		this.setOpaque(false);
		this.hasPlot = false;
	}
	
	public CustomPanel(String name, Dimension size) {
		this.name = name;
		this.size = size;
		this.setPreferredSize(size);
		this.setOpaque(false);
	}
	
	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		for (CustomComponent comp : this.comps) {
			comp.drawComponent(g);
		}
		//this.revalidate();
	}
	
	/* Faire attention a uniquement repeindre la zone concernée */
	public void addCustomComponent(CustomComponent comp) {
		this.add(comp);
		comps.add(comp);
		if(comp instanceof ScatterPlot) {
			setHasPlot(true);
		}
		this.repaint();
	}
	
	public void addCustomComponent(CustomComponent comp, int indexLayer, int indexEl) {
		this.add(comp,indexLayer,indexEl);
		comps.add(comp);
		this.repaint();
	}
	
	//Vérifier si un ScatterPlot est présent : si oui, redimensionnement de tout les graphes
	public boolean getHasPlot() {
		return this.hasPlot;
	}
	
	public void setHasPlot(boolean hasPlot) {
		this.hasPlot = hasPlot;
	}
	
	public ArrayList<CustomComponent> getComps(){
		return this.comps;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Dimension getSize() {
		return this.size;
	}
	
	public void setSize(Dimension size) {
		this.size = size;
		this.setSize(size);
	}

}
