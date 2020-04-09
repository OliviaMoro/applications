package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class StylePointPanel extends JPanel{
	private MainWindow parent;
	//Paramètres des points des graphes
	private int[] fontSizes = getAvailableSizes();
	private JComboBox<Integer> pointSizes;
	private JComboBox<String> pointShapes;
	private ColorButton colorButton = new ColorButton(Color.green);

	public StylePointPanel(MainWindow parent,Dimension size) {
		this.parent = parent;
		this.setPreferredSize(size);
		this.setBackground(Color.white);
		initComponent(size);
	}

	private void initComponent(Dimension size) {
		GridLayout gl = new GridLayout(3,1);
		this.setLayout(gl);

		//Panel de sélection de la taille des points d'un graphe
		JPanel panPointSize = new JPanel();
		panPointSize.setPreferredSize(new Dimension(150,50));
		panPointSize.setBackground(Color.white);
		JLabel labelPointSize = new JLabel("Taille des points : ");
		pointSizes = new JComboBox<Integer>();
		pointSizes.setPreferredSize(new Dimension(50,25));
		fillCombo(fontSizes,pointSizes);
		pointSizes.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				if(parent.getTreePanel().getCurrentNode().getLevel() == 4) {
					CustomPanel panel = parent.getDisplayPanel().getCurrentPanel();
					int index =  parent.getTreePanel().getCurrentPlotIndex();
					ScatterPlot plot = (ScatterPlot) panel.getComps().get(index+2);

					plot.setPointWidth((int) e.getItem());
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					panel.repaint();
					panel.revalidate();
				}
			}
		});
		panPointSize.add(labelPointSize);
		panPointSize.add(pointSizes);
		this.add(panPointSize);


		//Panel de sélection de la forme des points d'un graphe TODO
		JPanel panPointShape = new JPanel();
		panPointShape.setPreferredSize(new Dimension(150,50));	
		panPointShape.setBackground(Color.white);
		JLabel labelPointShape = new JLabel("Forme des points :");
		pointShapes = new JComboBox<String>();
		pointShapes.setPreferredSize(new Dimension(50,25));
		for(String shape : CustomPoint.getShapes()) {
			pointShapes.addItem(shape);
		}
		pointShapes.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(parent.getTreePanel().getCurrentNode().getLevel() == 4) {
					CustomPanel panel = parent.getDisplayPanel().getCurrentPanel();
					int index =  parent.getTreePanel().getCurrentPlotIndex();
					ScatterPlot plot = (ScatterPlot) panel.getComps().get(index+2);

					plot.setShape((String) e.getItem());
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					panel.repaint();
					panel.revalidate();
				}
			}

		});
		panPointShape.add(labelPointShape);
		panPointShape.add(pointShapes);
		this.add(panPointShape);
		
		
		//Panel de sélection de la couleur :
		JPanel panColor = new JPanel();
		panColor.setPreferredSize(new Dimension(150,50));	
		panColor.setBackground(Color.white);
		colorButton.setPreferredSize(new Dimension(50,25));
		colorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(parent,"Choisir une couleur",Color.green);
				if (color != null) {
					//System.out.println("Nouvelle couleur :"+ color.toString());
					colorButton.setColor(color);					
				}
				if(parent.getDisplayPanel().getScatters().size()!=0 && color != null) {
					String node = parent.getTreePanel().getCurrentNode().toString();
					if(node.split(" ")[0].equals("Plot")) {
						int index = Integer.valueOf(node.split(" ")[1])-1;
						parent.getDisplayPanel().getScatterPlot(index).setColor(color);
						try {
							Thread.sleep(10);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						parent.getDisplayPanel().getCurrentPanel().repaint();
						parent.getDisplayPanel().getCurrentPanel().revalidate();
					}
				}
			}		
		});		
		JLabel labelColor = new JLabel("Couleur des points :");	
		panColor.add(labelColor);
		panColor.add(colorButton);
		this.add(panColor);
	}
	
	private int[] getAvailableSizes() {
		int[] sizes = new int[30];
		for (int i = 0; i<30; i++) {
			sizes[i] = i+1;
		}
		return sizes;
	}

	private void fillCombo(int[] liste, JComboBox<Integer> combo) {
		for(int el : liste) {
			combo.addItem(el);
		}
	}

}
