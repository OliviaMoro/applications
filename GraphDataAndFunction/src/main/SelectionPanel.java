package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;


@SuppressWarnings("serial")
public class SelectionPanel extends JPanel{
	private MainWindow parent;
	DataSets data;
	JComboBox<String> splitX;
	JComboBox<String> splitY;	
	JTextField title = new JTextField("");
	JTextField axeX = new JTextField("");
	JTextField axeY = new JTextField("");

	//Ajouter ou modifer un dataset
	private JButton function = new JButton("Créer dataset d'une fonction");  //enable : function icon selected

	//Modifier l'affichage sur les axes : changement d'échelle
	private JButton visibility = new JButton("Cacher");//
	private JButton erase = new JButton("Effacer");

	private JButton scaleX = new JButton("axe X");
	private JButton scaleY = new JButton("axe Y");

	public SelectionPanel(MainWindow parent,Dimension size) {
		this.parent = parent;
		this.setPreferredSize(size);
		this.setBackground(Color.white);
		initComponent(size);
	}

	public void updateComponent(DataSets data) {
		//On vide au préalable les comboBox :
		splitX.removeAllItems();
		splitY.removeAllItems();

		//Mise à jour des variables disponibles : les étiquettes
		for (Map.Entry<String,Float[]> mapentry : data.getVectorSet().entrySet()) {
			splitX.addItem(mapentry.getKey());
			splitY.addItem(mapentry.getKey());

		}
		this.data = data;
		//System.out.println("Test ajout dataset : "+data.getVectorSet().size());
	}

	public void initComponent(Dimension size) {
		JPanel panDataX = new JPanel();
		panDataX.setPreferredSize(new Dimension(size.width,30));
		splitX = new JComboBox<String>();
		splitX.setPreferredSize(new Dimension((int)(size.width/2),25));
		JLabel labelComboX = new JLabel("Axe x : ");
		panDataX.add(labelComboX);
		panDataX.add(splitX);

		JPanel panDataY = new JPanel();
		panDataY.setPreferredSize(new Dimension(size.width,30));
		splitY = new JComboBox<String>();
		splitY.setPreferredSize(new Dimension((int)(size.width/2),25));
		JLabel labelComboY = new JLabel("Axe y : ");
		panDataY.add(labelComboY);
		panDataY.add(splitY);

		LabelListener ll = new LabelListener(parent,this);

		JPanel panTitre = new JPanel();
		panTitre.setPreferredSize(new Dimension(size.width,30));
		title.setPreferredSize(new Dimension((int)(size.width/2),25));
		title.addActionListener(ll);
		JLabel labelTitre = new JLabel("Titre : ");
		panTitre.add(labelTitre);
		panTitre.add(title);

		JPanel panX = new JPanel();
		panX.setPreferredSize(new Dimension(size.width,30));
		axeX.setPreferredSize(new Dimension((int)(size.width/2),25));
		axeX.addActionListener(ll);
		JLabel labelX = new JLabel("Label x : ");
		panX.add(labelX);
		panX.add(axeX);

		JPanel panY = new JPanel();
		panY.setPreferredSize(new Dimension(size.width,30));
		axeY.setPreferredSize(new Dimension((int)(size.width/2),25));
		axeY.addActionListener(ll);
		JLabel labelY = new JLabel("Label y : ");
		panY.add(labelY);
		panY.add(axeY);

		JPanel panExpr = new JPanel();
		panExpr.setPreferredSize(new Dimension(size.width,30));
		function.setPreferredSize(new Dimension((int)(size.width/1.2),25));
		function.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FunctionDialog fd = new FunctionDialog(null, "Création de dataset", true);
				Float[] arrayX = fd.getArrayX();
				Float[] arrayY = fd.getArrayY();
				String nomX = fd.getNomX();
				String nomY = fd.getNomY();

				data = parent.getDataSets();
				data.addVectorSet(arrayX,nomX);
				data.addVectorSet(arrayY, nomY);
				updateComponent(data);	
			}
		});
		panExpr.add(function);

		JPanel panVisibility = new JPanel();
		panVisibility.setPreferredSize(new Dimension(size.width,30));
		visibility.setPreferredSize(new Dimension((int)(size.width/2.5),25));
		visibility.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Un ScatterPlot doit être sélectionné :
				int nodeLevel = parent.getTreePanel().getCurrentNode().getLevel();

				if(nodeLevel==4 && ((JButton) e.getSource()).getText()=="Cacher") {
					CustomPanel panel =  parent.getDisplayPanel().getCurrentPanel();
					int index = parent.getTreePanel().getCurrentPlotIndex();
					ScatterPlot plot = (ScatterPlot) panel.getComps().get(index+2);
					plot.hide();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					panel.repaint();
					panel.revalidate();
					((JButton)e.getSource()).setText("Montrer");
				}
				else if(nodeLevel==4 && ((JButton) e.getSource()).getText()=="Montrer") {
					CustomPanel panel =  parent.getDisplayPanel().getCurrentPanel();
					int index = parent.getTreePanel().getCurrentPlotIndex();
					ScatterPlot plot = (ScatterPlot) panel.getComps().get(index+2);
					plot.show();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					panel.repaint();
					panel.revalidate();
					((JButton)e.getSource()).setText("Cacher");
				}

			}

		});

		erase.setPreferredSize(new Dimension((int)(size.width/2.5),25));
		erase.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Un ScatterPlot doit être sélectionné :
				int nodeLevel = parent.getTreePanel().getCurrentNode().getLevel();

				if(nodeLevel==4) {
					CustomPanel panel =  parent.getDisplayPanel().getCurrentPanel();
					int index = parent.getTreePanel().getCurrentPlotIndex();
					panel.getComps().remove(2+index);

					//Retirer noeud de l'arbre :
					JTree arbre = parent.getTreePanel().getArbre();
					DefaultMutableTreeNode node = parent.getTreePanel().getCurrentNode();
					DefaultMutableTreeNode node0 = (DefaultMutableTreeNode) node.getParent();

					DefaultTreeModel model = (DefaultTreeModel) arbre.getModel();
					model.removeNodeFromParent(node);
					model.nodeChanged(node0);	

					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					panel.repaint();
					panel.revalidate();
				}

			}
		});
		panVisibility.add(visibility);
		panVisibility.add(erase);


		JPanel panScale = new JPanel();
		panScale.setPreferredSize(new Dimension(size.width,30));
		scaleX.setPreferredSize(new Dimension((int)(size.width/2.5),25));
		scaleX.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ScaleDialog sd = new ScaleDialog(null, "Modification de l'axe X", true);
				Float[] base = sd.getBase();

				CustomPanel panel = parent.getDisplayPanel().getCurrentPanel();
				BaseGraph bg = (BaseGraph) panel.getComps().get(1);
				bg.setBaseX(base);
				if(panel.getHasPlot()) {
		        	for (int i = 2; i<panel.getComps().size(); i++) {
		        		((ScatterPlot) panel.getComps().get(i)).setBaseX(base);
		        	}
		        }
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				panel.repaint();
				panel.revalidate();

			}
		});
		scaleY.setPreferredSize(new Dimension((int)(size.width/2.5),25));
		scaleY.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ScaleDialog sd = new ScaleDialog(null, "Modification de l'axe Y", true);
				Float[] base = sd.getBase();

				CustomPanel panel = parent.getDisplayPanel().getCurrentPanel();
				BaseGraph bg = (BaseGraph) panel.getComps().get(1);
				bg.setBaseY(base);
				if(panel.getHasPlot()) {
		        	for (int i = 2; i<panel.getComps().size(); i++) {
		        		((ScatterPlot) panel.getComps().get(i)).setBaseY(base);
		        	}
		        }
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				panel.repaint();
				panel.revalidate();
			}

		});
		panScale.add(scaleX);
		panScale.add(scaleY);



		GridLayout gl = new GridLayout(8, 1);
		this.setLayout(gl);
		this.add(panDataX);
		this.add(panDataY);
		this.add(panTitre);
		this.add(panX);
		this.add(panY);
		this.add(panExpr);
		this.add(panVisibility);
		this.add(panScale);
	}



	/* Méthode retournant le vecteur 1D associé à l'étiquette sélectionnée dans splitX 
	 * test possibles avec des mocks 
	 * gestion des cas où aucun jeu de donnée n'est chargé */
	public Float[] getDataX() {
		Float[] vector = null;
		if (this.splitX.getSelectedObjects().length != 0) {
			//récupérer l'étiquette du jeu de donnée
			String name = (String) this.splitX.getSelectedItem();
			//chercher le vecteur correspondant dans data
			vector = this.data.getVectorSet().get(name);
		}
		return vector;
	}


	/* Méthode retournant le vecteur 1D associé à l'étiquette sélectionnée dans splitY 
	 * test possible avec des mocks 
	 * gestion des cas où aucun jeu de donnée n'est chargé */
	public Float[] getDataY() {
		Float[] vector = null; 
		if (this.splitY.getSelectedObjects().length != 0) {
			String name = (String) this.splitY.getSelectedItem();
			vector = this.data.getVectorSet().get(name);			
		}
		return vector;
	}


	/* retourne le nom de l'axe X pour la légende */
	public String getAxeX() {
		//Nom de l'étiquette du vecteur 1D sélectionné dans splitX par défaut
		String nomX = (String) this.splitX.getSelectedItem();
		if(this.axeX.getText().length() != 0) {
			nomX = this.axeX.getText();
		}
		return nomX;
	}

	/* retourne le nom de l'axe Y pour la légende */
	public String getAxeY() {
		//Nom de l'étiquette du vecteur 1D sélectionné dans splitY par défaut 
		String nomY = (String) this.splitY.getSelectedItem();
		if(this.axeY.getText().length() != 0) {
			nomY = this.axeY.getText();
		}
		return nomY;
	}

	/* retourne le titre du graphe */
	public String getTitle() {
		//Titre par défaut si non renseigné
		String chaine = "";
		if(this.title.getText().length() != 0) {
			chaine = this.title.getText();
		}
		return chaine;
	}

	public JTextField getTitleJTF() {
		return this.title;
	}

	public JTextField getAxeXJTF() {
		return this.axeX;
	}

	public JTextField getAxeYJTF() {
		return this.axeY;
	}

	public JButton getVisibility() {
		return this.visibility;
	}

	public JButton getErase() {
		return this.erase;
	}

	public JButton getScaleX() {
		return this.scaleX;
	}

	public JButton getScaleY() {
		return this.scaleY;
	}
}
