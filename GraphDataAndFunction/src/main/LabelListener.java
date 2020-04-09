package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;


public class LabelListener implements ActionListener{
	private MainWindow parent;
	private SelectionPanel select;
	
	public LabelListener(MainWindow parent,SelectionPanel select) {
		this.parent = parent;
		this.select = select;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		DisplayPanel displayPane = parent.getDisplayPanel();
		GraphTreeEditorPanel treePane = parent.getTreePanel();
		
		JTextField jtf = ((JTextField)e.getSource());
		String text = jtf.getText();
		jtf.selectAll();
		
		String[] objet = treePane.getCurrentNode().toString().split(" ");
		if(objet[0].equals("Graphe") && jtf.getText().length()!=0) {
			int index = Integer.valueOf(objet[1])-1;
			BaseGraph graph = displayPane.getBaseGraph(index);
			
			if(e.getSource() == select.getTitleJTF()) {
				graph.setTitle(text);				
			}
			else if (e.getSource() == select.getAxeXJTF()) {
				graph.setlabelX(text);				
			}
			else if (e.getSource() == select.getAxeYJTF()) {
				graph.setlabelY(text);				
			}
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
