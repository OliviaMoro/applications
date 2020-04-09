package main;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;



@SuppressWarnings("serial")
public class GraphTreeEditorPanel extends JPanel{
	MainWindow parent;
	ImageIcon sheet = new ImageIcon("images/icone_feuille.jpg"),
			graph = new ImageIcon("images/icone_BaseGraphe.jpg"),
			axe = new ImageIcon("images/icone_BaseGrapheAxis.jpg"),
			scatter = new ImageIcon("images/icone_Scatterplot.jpg");
			//function = new ImageIcon("images/icone_Functionplot.jpg");

	private JTree arbre;
	private DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Racine");
	private DefaultTreeModel model;
	private int indexPage = 1;
	private int indexGraph = 1;
	private int indexCourbe = 0;

	//On va créer notre modèle d'affichage
	private GraphTreeRenderer tCellRenderer = new  GraphTreeRenderer();
	private DefaultMutableTreeNode currentNode;

	public GraphTreeEditorPanel(MainWindow parent,Dimension size) {
		this.parent = parent;
		initComponent(size);
		initDefaultTree(size);
		this.currentNode = racine;
		this.arbre.setEditable(true);
		this.model = new DefaultTreeModel(this.racine);      
	    this.arbre.setModel(model);
	    /* Différentes action quand on ajoute un composant dans l'arbre */
	    TreeChangeListener tcl = new TreeChangeListener();
	    this.arbre.getModel().addTreeModelListener(tcl);
	    
	    /* Montre tous les graphes de la page sélectionnée quand celle-ci est 
	     * choisie dans l'arbre */
	    TreeListener tl = new TreeListener(parent,this,arbre);
	    arbre.addTreeSelectionListener(tl);
	}

	private void initComponent(Dimension size) {
		this.setPreferredSize(size);
		this.setBackground(Color.white);
	}

	/* Initialise l'arborescence en début de session */
	private void initDefaultTree(Dimension size) {
		//Nous allons ajouter des branches et des feuilles à notre racine
		DefaultMutableTreeNode pageNode = new DefaultMutableTreeNode("Page "+indexPage);
		//System.out.println("pageNode depth : "+pageNode.getLevel());

		//On rajoute 1 branche :  
		DefaultMutableTreeNode graphNode = new DefaultMutableTreeNode("Graphe "+indexGraph);
		pageNode.add(graphNode);
		//System.out.println("graphNode depth : "+graphNode.getLevel());

		//On rajoute les feuilles : axes & graphes
		DefaultMutableTreeNode axeXNode = new DefaultMutableTreeNode("axe");
		DefaultMutableTreeNode axeYNode = new DefaultMutableTreeNode("axe");
		graphNode.add(axeXNode);
		graphNode.add(axeYNode);
		//System.out.println("axeNode depth : "+axeYNode.getLevel());

		//On ajoute la feuille ou la branche à la racine
		racine.add(pageNode);

		//Nous créons, avec notre hiérarchie, un arbre
		arbre = new JTree(racine);

		//On définit le rendu pour cet arbre
		arbre.setCellRenderer(this.tCellRenderer);

		//Que nous plaçons sur le ContentPane de notre JFrame à l'aide d'un scroll 
		JScrollPane scrollTree = new JScrollPane(arbre);
		scrollTree.setPreferredSize(size);
		//scrollTree.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		this.add(scrollTree);
	}


	/* Gérer le cas où aucun noeud n'est selectionner avec un noeud par défaut */
	public void updateTree(String iconeName) {
		if (iconeName == "sheet") {
			indexPage++;
            DefaultMutableTreeNode parentNode = racine;
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode("Page "+indexPage);
            parentNode.add(childNode);
            model.insertNodeInto(childNode, parentNode, parentNode.getChildCount()-1);
            model.nodeChanged(parentNode);  
			//racine.add(newPageNode);
			System.out.println("indexPage :"+indexPage);
			System.out.println("on ajoute un noeud");
		}	
		else if (iconeName == "graph" ) {
			//Ajout de l'icone graphe
			//System.out.println("dernier noeud : "+arbre.getLastSelectedPathComponent());
			if (arbre.getLastSelectedPathComponent() == null) {
				//Préparer boite de dialogue
				System.out.println("Veuillez sélectionner la page sur laquelle ajouter le graphe!");
			}
			else {
				indexGraph++;
				DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent();
	            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode("Graphe "+indexGraph);
	            parentNode.add(childNode);
	            model.insertNodeInto(childNode, parentNode, parentNode.getChildCount()-1);
	            model.nodeChanged(parentNode);
	            
	            //Ajout des axes
	            parentNode = childNode;
	            DefaultMutableTreeNode axeXNode = new DefaultMutableTreeNode("axe");
	    		DefaultMutableTreeNode axeYNode = new DefaultMutableTreeNode("axe");
	    		parentNode.add(axeXNode);
	    		parentNode.add(axeYNode);
	            model.insertNodeInto(axeXNode, parentNode, parentNode.getChildCount()-1);
	            model.insertNodeInto(axeYNode, parentNode, parentNode.getChildCount()-1);
	            model.nodeChanged(parentNode);
			}
		}
		else if (iconeName == "scatter") {
			//Ajout de l'icone graphe
			DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent();
		    parentNode = (DefaultMutableTreeNode) arbre.getModel().getChild(parentNode, 1);
			if (parentNode == null) {
				//Préparer boite de dialogue
				System.out.println("Veuillez sélectionner la page sur laquelle ajouter le graphe!");
			}
			else {
				indexCourbe++;
				DefaultMutableTreeNode childNode = new DefaultMutableTreeNode("Plot "+indexCourbe);
				
				parentNode.add(childNode);
				model.insertNodeInto(childNode, parentNode, parentNode.getChildCount()-1);
				model.nodeChanged(parentNode);					
			}
		}			
	}
	
	public int getCurrentPlotIndex() {
		int index = getCurrentNode().getParent().getIndex(getCurrentNode());
		return index;
	}

	public DefaultMutableTreeNode getCurrentNode() {
		return this.currentNode;
	}
	
	public void setCurrentNode(DefaultMutableTreeNode currentNode) {
		this.currentNode = currentNode;
	}
	
	public JTree getArbre() {
		return this.arbre;
	}
	
	public DefaultTreeModel getModel() {
		return this.model;
	}


}
