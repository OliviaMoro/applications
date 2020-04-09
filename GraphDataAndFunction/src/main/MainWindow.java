package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;


@SuppressWarnings("serial")
public class MainWindow extends JFrame{
	private DataSets data = new DataSets();
	private SelectionPanel selectPane = new SelectionPanel(this,new Dimension(250,300));
	private StyleFontPanel fontPane = new StyleFontPanel(this,new Dimension(250,300));
	private StylePointPanel pointPane = new StylePointPanel(this,new Dimension(250,300));
	private StyleLinePanel linePane = new StyleLinePanel(this,new Dimension(250,300));
	private DisplayPanel displayPane = new DisplayPanel();
	private GraphTreeEditorPanel treePane = new GraphTreeEditorPanel(this, new Dimension(200,250));
	private JSplitPane split;
	private JTabbedPane onglet = new JTabbedPane();
	
	
	//Les boutons de la barre d'outils
	JButton   sheet = new JButton(new ImageIcon("images/icone_feuille.jpg")),
			graph = new JButton(new ImageIcon("images/icone_BaseGraphe.jpg")),
			axe = new JButton(new ImageIcon("images/icone_BaseGrapheAxis.jpg")),
			scatter = new JButton(new ImageIcon("images/icone_Scatterplot.jpg"));
	
	//On créer les listeners:
	IconListener il = new IconListener(this,displayPane,treePane);
	
	public MainWindow(){
		initContent();
	}
	
	private void initContent() {
	    this.setTitle("Visualisation Données - acceuil");
	    this.pack();
	    this.setSize(800, 600);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		initMenu();
		initToolBar();
		onglet.add("Arbre",treePane);
		onglet.add("Données",selectPane);
		onglet.add("Style",fontPane);
		onglet.add("Points",pointPane);
		onglet.add("Lignes",linePane);
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, onglet, displayPane);
		this.add(split,BorderLayout.CENTER);
		this.setBackground(Color.white);
		this.setVisible(true); 
	}
	
	private void initMenu() {
		/****************** Déclaration barre de menu ******************/
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fichier = new JMenu("Fichier"),
				donnees = new JMenu("Données"),
				aide = new JMenu("Aide");
		
		JMenuItem effacer = new JMenuItem("Effacer"),
				quitter = new JMenuItem("Quitter"),
				sauvegarde = new JMenuItem("Sauvegarder"),
				charger = new JMenuItem("Charger"),
			    nouveau = new JMenuItem("Nouveau"),
				chargeDonnees = new JMenuItem("Charger données"),
				consigne = new JMenuItem("Consignes"),
				propos = new JMenuItem("A propos");
		
		/**** Section fichier ****/
		fichier.add(effacer);
		fichier.add(nouveau);
		fichier.add(sauvegarde);
		fichier.add(charger);
		fichier.addSeparator();
		quitter.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));
		
		//Pour quitter l'application
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				System.exit(0);
			}
		});
		fichier.add(quitter);
		fichier.setMnemonic('F');
		
		/**** Section Sauvegarder ****/
		sauvegarde.addActionListener(new SaveListener(this));
		
		/**** Section Données ****/
		chargeDonnees.addActionListener(new ActionListener(){
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				 DataDialog dd = new DataDialog(null, "Sélection des données", true);
				 DataDialogInfo dataInfo = dd.showDialog();
				 JOptionPane jop = new JOptionPane();
				 jop.showMessageDialog(null, 
						 dataInfo.toString(), 
						 "Informations données", 
						 JOptionPane.INFORMATION_MESSAGE);
				 if(dataInfo.getDataTab() != null && dataInfo.getVariables() != null) {
					 data.addDataSet(dataInfo.getDataTab(),dataInfo.getVariables());
					 selectPane.updateComponent(data);					 
				 }
			}
		});   
		donnees.add(chargeDonnees);
		donnees.setMnemonic('D');
		
		/**** Section Aide ****/
		aide.add(consigne);
		aide.add(propos);
		aide.setMnemonic('H');
		
		/**** Composition menu ****/
		menuBar.add(fichier);
		menuBar.add(donnees);
		menuBar.add(aide);
		this.setJMenuBar(menuBar);	
	}
	
	private void initToolBar() {
		//Création de notre barre d'outils
		JToolBar toolBar = new JToolBar();	
	    sheet.setBackground(Color.white);
	    graph.setBackground(Color.white);
	    axe.setBackground(Color.white);
	    scatter.setBackground(Color.white);
	    
	    //On ajoute les listeners aux boutons 
	    sheet.addActionListener(il);
	    graph.addActionListener(il);
	    scatter.addActionListener(il);

	    toolBar.add(sheet);
	    toolBar.add(graph);
	    toolBar.add(axe);
	    toolBar.addSeparator();
	    toolBar.add(scatter);
	    
	    this.add(toolBar, BorderLayout.NORTH);    
	}
	
	public SelectionPanel getSelectionPanel() {
		return this.selectPane;
	}
	
	public StyleFontPanel getFontPanel() {
		return this.fontPane;
	}
	
	public StylePointPanel getPointPanel() {
		return this.pointPane;
	}
	
	public StyleLinePanel getLinePanel() {
		return this.linePane;
	}
	
	
	public GraphTreeEditorPanel getTreePanel(){
		return this.treePane;
	}
	
	public DisplayPanel getDisplayPanel() {
		return this.displayPane;
	}

	public DataSets getDataSets() {
		return this.data;
	}

}
