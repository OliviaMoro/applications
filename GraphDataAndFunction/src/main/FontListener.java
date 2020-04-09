package main;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JTextField;

public class FontListener implements ItemListener{
	private MainWindow parent;
	private JTextField jtf;
	
	public FontListener(MainWindow parent,JTextField jtf) {
		this.parent = parent;
		this.jtf = jtf; 
		//this.nomGraphe = jtf.getText();
		//System.out.println("graphe :"+nomGraphe);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		DisplayPanel displayPane = parent.getDisplayPanel();
		if(jtf.getText().split(" ")[0].contentEquals("Graphe")) {
			int index = Integer.valueOf(jtf.getText().split(" ")[1])-1;
			BaseGraph base = displayPane.getBaseGraph(index);

			base.setFontName(e.getItem().toString());
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			displayPane.getCurrentPanel().repaint();
			displayPane.getCurrentPanel().revalidate();
		}
		//System.out.println("current  : "  + displayPane.getCurrent());
		
	}

}
