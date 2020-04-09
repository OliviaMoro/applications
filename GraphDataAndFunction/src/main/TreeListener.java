package main;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreeListener implements TreeSelectionListener{
	private MainWindow parent;
	private JTree arbre;
	private GraphTreeEditorPanel treePane;
	
	public TreeListener(MainWindow parent,GraphTreeEditorPanel treePane,JTree arbre) {
		this.parent = parent;
		this.arbre = arbre;
		this.treePane = treePane;
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		if(arbre.getLastSelectedPathComponent() != null){
			DefaultMutableTreeNode currentNode = ((DefaultMutableTreeNode)arbre.getLastSelectedPathComponent());
			treePane.setCurrentNode(currentNode);
			parent.getFontPanel().getCurrent().setText(currentNode.toString());
			
			//Changement de page :
			if(currentNode.getLevel() == 1) {
				DisplayPanel displayPane = (DisplayPanel) parent.findComponentAt(350, 400).getParent();
				String current = arbre.getLastSelectedPathComponent().toString();
				displayPane.setCurrent(current);
				displayPane.setCurrentPanel(current);
			}
			
			//Modification des ScatterPlot :
			if(currentNode.getLevel() == 4) {
				parent.getSelectionPanel().getVisibility().setEnabled(true);
				parent.getSelectionPanel().getErase().setEnabled(true);
				
				int index = treePane.getCurrentPlotIndex();
				CustomPanel panel = parent.getDisplayPanel().getCurrentPanel();
				boolean showing = panel.getComps().get(index+2).isShowing();
				if(showing) {
					parent.getSelectionPanel().getVisibility().setText("Cacher");
				}
				else {
					parent.getSelectionPanel().getVisibility().setText("Montrer");
				}
			}
			else {
				parent.getSelectionPanel().getVisibility().setEnabled(false);
				parent.getSelectionPanel().getErase().setEnabled(false);
			}
			//Modification des axes des BaseGraph :
			if(currentNode.getLevel() == 2) {
				parent.getSelectionPanel().getScaleX().setEnabled(true);
				parent.getSelectionPanel().getScaleY().setEnabled(true);
			}
			else {
				parent.getSelectionPanel().getScaleX().setEnabled(false);
				parent.getSelectionPanel().getScaleY().setEnabled(false);
			}
		}
	}
	
	

}
