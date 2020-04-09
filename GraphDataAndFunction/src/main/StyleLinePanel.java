package main;

import java.awt.BasicStroke;
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
public class StyleLinePanel extends JPanel{
	private MainWindow parent;
	//Paramètres des lignes reliant les points des graphes
	private float[] lineWidths = getAvailableWidth();
	private String[] caps = {"carré", "net", "arrondi"};
	JComboBox<Integer> lineShapes;
	JComboBox<Float> comboWidths;
	JComboBox<String> lineCaps;
	private ColorButton colorButton = new ColorButton(Color.red);

	public StyleLinePanel(MainWindow parent,Dimension size) {
		this.parent = parent;
		this.setPreferredSize(size);
		this.setBackground(Color.white);
		initComponent(size);
	}

	@SuppressWarnings("unchecked")
	private void initComponent(Dimension size) {
		GridLayout gl = new GridLayout(4,1);
		this.setLayout(gl);
		
		//Panel de sélection de la forme de la ligne TODO
		JPanel panLineShape = new JPanel();
		panLineShape.setPreferredSize(new Dimension(150,50));	
		JLabel labelLineShape = new JLabel("Type de ligne :");
		Integer[] intArray = new Integer[Line.getDashPattern().size()];
		for(int i = 0; i < Line.getDashPattern().size(); i++) {
			intArray[i] = new Integer(i);
		}
		lineShapes = new JComboBox<Integer>(intArray);
		lineShapes.setPreferredSize(new Dimension(150,30));
		ComboBoxRenderer renderer = new ComboBoxRenderer(Line.getDashPattern());
		lineShapes.setRenderer(renderer);
		lineShapes.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(parent.getTreePanel().getCurrentNode().getLevel() == 4) {
					CustomPanel panel = parent.getDisplayPanel().getCurrentPanel();
					int index =  parent.getTreePanel().getCurrentPlotIndex();
					ScatterPlot plot = (ScatterPlot) panel.getComps().get(index+2);
					
					int dash = (int) e.getItem();
					plot.setDashingPattern(Line.getDashPattern().get(dash));
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
		panLineShape.setBackground(Color.white);
		panLineShape.add(labelLineShape);
		panLineShape.add(lineShapes);
		this.add(panLineShape);

		//Panel de sélection de l'épaisseur de la ligne TODO
		JPanel panLineWidth = new JPanel();
		panLineWidth.setPreferredSize(new Dimension(150,50));
		JLabel labelLineWidth = new JLabel("Epaisseur de la ligne :");
		comboWidths = new JComboBox<Float>();
		comboWidths.setPreferredSize(new Dimension(50,25));
		for(float width : lineWidths) {
			comboWidths.addItem(width);
		}
		comboWidths.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(parent.getTreePanel().getCurrentNode().getLevel() == 4) {
					CustomPanel panel = parent.getDisplayPanel().getCurrentPanel();
					int index =  parent.getTreePanel().getCurrentPlotIndex();
					ScatterPlot plot = (ScatterPlot) panel.getComps().get(index+2);

					plot.setLineWidth((Float) e.getItem());
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
		panLineWidth.setBackground(Color.white);
		panLineWidth.add(labelLineWidth);
		panLineWidth.add(comboWidths);
		this.add(panLineWidth);
		
		//Panel de sélection de l'épaisseur de la ligne TODO
		JPanel panCaps = new JPanel();
		panCaps.setPreferredSize(new Dimension(150,50));
		JLabel labelCap = new JLabel("Bout de la ligne :");
		lineCaps = new JComboBox<String>(caps);
		lineCaps.setPreferredSize(new Dimension(50,25));
		lineCaps.addItemListener(new ItemListener() {
			int[] cap = {BasicStroke.CAP_SQUARE,BasicStroke.CAP_BUTT,BasicStroke.CAP_ROUND};	

			public void itemStateChanged(ItemEvent e) {
				if(parent.getTreePanel().getCurrentNode().getLevel() == 4) {
					CustomPanel panel = parent.getDisplayPanel().getCurrentPanel();
					int index =  parent.getTreePanel().getCurrentPlotIndex();
					ScatterPlot plot = (ScatterPlot) panel.getComps().get(index+2);

					String x = (String) e.getItem();
					int i = x.equals(caps[0])?0:(x.equals(caps[1])?1:2);
					plot.setCap(cap[i]);
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
		panCaps.setBackground(Color.white);
		panCaps.add(labelCap);
		panCaps.add(lineCaps);
		this.add(panCaps);



		//Panel de sélection de la couleur :
		JPanel panColor = new JPanel();
		panColor.setPreferredSize(new Dimension(150,50));	
		panColor.setBackground(Color.white);
		colorButton.setPreferredSize(new Dimension(50,25));
		colorButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(parent,"Choisir une couleur",Color.red);
				if (color != null) {
					//System.out.println("Nouvelle couleur :"+ color.toString());
					colorButton.setColor(color);					
				}
				if(parent.getDisplayPanel().getScatters().size()!=0 && color != null) {
					String node = parent.getTreePanel().getCurrentNode().toString();
					if(node.split(" ")[0].equals("Plot")) {
						int index = Integer.valueOf(node.split(" ")[1])-1;
						parent.getDisplayPanel().getScatterPlot(index).setLineColor(color);
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
	
	private float[] getAvailableWidth() {
		float[] widths = new float[30];
		for (int i = 0; i<30; i++) {
			widths[i] = i+1;
		}
		return widths;
	}

}
