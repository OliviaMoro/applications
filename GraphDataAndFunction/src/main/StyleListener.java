package main;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class StyleListener implements DocumentListener{
   private MainWindow parent;
	
	public StyleListener(MainWindow parent) {
		this.parent = parent;
	}


	@Override
	public void insertUpdate(DocumentEvent e) {
		DisplayPanel displayPane = parent.getDisplayPanel();
		//System.out.println("current  : "  + displayPane.getCurrent());
		GraphTreeEditorPanel treePane = parent.getTreePanel();
		//System.out.println("arborescence : " + treePane.getCurrentNode());
	}



	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

}
