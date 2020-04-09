package main;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

@SuppressWarnings("serial")
class GraphTreeRenderer extends DefaultTreeCellRenderer {
	ImageIcon sheet = new ImageIcon("images/icone_feuille.jpg"),
			graph = new ImageIcon("images/icone_BaseGraphe.jpg"),
			axe = new ImageIcon("images/icone_BaseGrapheAxis.jpg"),
			scatter = new ImageIcon("images/icone_Scatterplot.jpg"),
			//function = new ImageIcon("images/icone_Functionplot.jpg"),
			root = new ImageIcon("images/icone_root.jpg");
	
	public GraphTreeRenderer() {
		
	}


    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value,
        boolean sel, boolean exp, boolean leaf, int row, boolean hasFocus) {
		DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) value;
		if (nodo.getLevel() == 0) {
			setOpenIcon(root);
			setClosedIcon(root);
			setLeafIcon(root);
		}
		else if (nodo.getLevel() == 1) {
			setOpenIcon(sheet);
			setClosedIcon(sheet);
			setLeafIcon(sheet);
		} else if (nodo.getLevel() == 2) {
			setOpenIcon(graph);
			setClosedIcon(graph);
			setLeafIcon(graph);
		}  else if (nodo.getLevel() == 3) {
			setOpenIcon(axe);
			setClosedIcon(axe);
			setLeafIcon(axe);				
		}
		else if (nodo.getLevel() == 4) {
			setOpenIcon(scatter);
			setClosedIcon(scatter);
			setLeafIcon(scatter);				
		}
        super.getTreeCellRendererComponent(
            tree, value, sel, exp, leaf, row, hasFocus);
        return this;
    }
}