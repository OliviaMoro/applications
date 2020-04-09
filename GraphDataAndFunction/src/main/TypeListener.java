package main;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JTextField;

public class TypeListener implements ItemListener{
	private int[] styles = {Font.BOLD,Font.ITALIC,Font.PLAIN};
	private MainWindow parent;
	private JTextField jtf;

	
	public TypeListener(MainWindow parent,JTextField jtf) {
		this.parent = parent;
		this.jtf = jtf;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		DisplayPanel displayPane = parent.getDisplayPanel();
		if(jtf.getText().split(" ")[0].contentEquals("Graphe")) {
			int index = Integer.valueOf(jtf.getText().split(" ")[1])-1;
			BaseGraph base = displayPane.getBaseGraph(index);

			int style = 0;
			switch(e.getItem().toString()) {
				case "bold":
					style = styles[0];
					break;
				case "italic":
					style = styles[1];
					break;
				case "plain":
					style = styles[2];
					break;
			}
			base.setFontType(style);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			displayPane.getCurrentPanel().repaint();
			displayPane.getCurrentPanel().revalidate();
		}
	}

}
