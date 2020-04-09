package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ScaleDialog extends JDialog{
	private Dimension sizePanel, sizeField;
	private JFormattedTextField fieldMin, fieldMax;
	private JButton valider, annuler;
	private Float[] base;

	public ScaleDialog(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setSize(450, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		sizePanel = new Dimension(this.getWidth(),30);
		sizeField = new Dimension(this.getWidth()/3,25);
		this.initComponent();
		this.setVisible(true);
	}

	private void initComponent() {
		this.setLayout(new GridLayout(3,1));

		//Panel pour indiquer le minimum de x
		JPanel panMin = new JPanel();
		JLabel labelMin = new JLabel("Minimum : ");
		fieldMin = new JFormattedTextField(NumberFormat.getNumberInstance());
		panMin.setPreferredSize(sizePanel);
		fieldMin.setPreferredSize(sizeField);
		panMin.add(labelMin);
		panMin.add(fieldMin);

		//Panel pour indiquer le maximum de x
		JPanel panMax = new JPanel();
		JLabel labelMax = new JLabel("Maximum : ");
		fieldMax = new JFormattedTextField(NumberFormat.getNumberInstance());
		panMax.setPreferredSize(sizePanel);
		fieldMax.setPreferredSize(sizeField);
		panMax.add(labelMax);
		panMax.add(fieldMax);

		//Panel pour valider ou annuler la saisie
		JPanel panButton = new JPanel();
		valider = new JButton("Valider"); //enable quand tout les champs sont pleins
		annuler = new JButton("Annuler"); //sort de la saisie sans rien renvoyer
		panButton.setPreferredSize(sizePanel);
		valider.setPreferredSize(sizeField);
		valider.addActionListener(new ActionListener() {

			@SuppressWarnings("static-access")
			@Override
			public void actionPerformed(ActionEvent e) {
				int minLen = fieldMin.getText().length();
				int maxLen = fieldMax.getText().length();

				if (minLen != 0 && maxLen != 0) {
					 Float min = Float.valueOf(fieldMin.getText());
					 Float max = Float.valueOf(fieldMax.getText());
					 
					 PythonLambda pl = new PythonLambda();
					 base = pl.createBoundedArray(min,max,6);
					 
					 setVisible(false);
				}
				else {
					JOptionPane jop = new JOptionPane();
					jop.showMessageDialog(null, 
							"Veuillez remplir tout les champs.", 
							"Attention", 
							JOptionPane.WARNING_MESSAGE);
				}
			}
			
		});
		annuler.setPreferredSize(sizeField);
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		panButton.add(valider);
		panButton.add(annuler);
		
		this.add(panMin);
		this.add(panMax);
		this.add(panButton);
	}
	
	public Float[] getBase() {
		return this.base;
	}


}
