package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.DefaultMutableTreeNode;


public class IconListener implements ActionListener{
	private DisplayPanel displayPane;
	private GraphTreeEditorPanel treePane;
	private MainWindow parent;

	public IconListener(MainWindow parent, DisplayPanel displayPane, GraphTreeEditorPanel treePane){
		this.parent = parent;
		this.displayPane = displayPane;
		this.treePane = treePane;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == parent.sheet) {
			displayPane.addPage();
			treePane.updateTree("sheet");

		}
		else if (arg0.getSource() == parent.graph) {
			DefaultMutableTreeNode parentNode = treePane.getCurrentNode();
			String chaine = parentNode.toString().split(" ")[0]; 
			System.out.println("lastNode :"+chaine);
			if (chaine.equals("Page")) {
				treePane.updateTree("graph");
				displayPane.addBaseGraph();				
			}

		}
		else if (arg0.getSource() == parent.scatter) {
			SelectionPanel selectPane = parent.getSelectionPanel();
			DefaultMutableTreeNode parentNode = treePane.getCurrentNode();
			String chaine = parentNode.toString().split(" ")[0]; 
			System.out.println("lastNode :"+chaine);
			if (selectPane.getDataX() != null && selectPane.getDataY() != null && chaine.equals("Graphe")) {
				Float[] dataX = selectPane.getDataX();
				Float[] dataY = selectPane.getDataY();
		
				treePane.updateTree("scatter");
				displayPane.addScatterPlot(dataX,dataY);				
			}
		}
		
	}

}
