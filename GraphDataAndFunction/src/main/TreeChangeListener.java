package main;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

public class TreeChangeListener implements TreeModelListener{

    public TreeChangeListener() {
    	
    }
	
	public void treeNodesChanged(TreeModelEvent evt) {
		System.out.println("Changement dans l'arbre");
	}   
	public void treeNodesInserted(TreeModelEvent event) {
		System.out.println("Un noeud a été inséré !");            
	}
	public void treeNodesRemoved(TreeModelEvent event) {
		System.out.println("Un noeud a été retiré !");
	}
	public void treeStructureChanged(TreeModelEvent event) {
		System.out.println("La structure d'un noeud a changé !");
	}

}
