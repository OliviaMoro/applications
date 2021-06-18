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
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class FunctionDialog extends JDialog{
	private JFormattedTextField fieldMin, fieldMax, fieldPoint;
	private JTextField fieldExpr, fieldNomX, fieldNomY;
	private JButton valider, annuler;
	private Dimension sizePanel, sizeField;
	private Float[] arrayX, arrayY;
	private String nomX, nomY;
	
	public FunctionDialog(JFrame parent, String title, boolean modal) {
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
		this.setLayout(new GridLayout(7,1));

		//Panel pour introduire la formule
		JPanel panExpr = new JPanel();
		JLabel labelExpr = new JLabel("Expression :");
		fieldExpr = new JTextField("");
		panExpr.setPreferredSize(sizePanel);
		fieldExpr.setPreferredSize(sizeField);
		panExpr.add(labelExpr);
		panExpr.add(fieldExpr);
		
		//Panel pour le nom des vecteurs
		JPanel panNomX = new JPanel();
		JLabel labelNomX = new JLabel("Nom X :");
		fieldNomX = new JTextField("");
		panNomX.setPreferredSize(sizePanel);
		fieldNomX.setPreferredSize(sizeField);
		panNomX.add(labelNomX);
		panNomX.add(fieldNomX);
		
		JPanel panNomY = new JPanel();
		JLabel labelNomY = new JLabel("Nom Y :");
		fieldNomY = new JTextField("");
		panNomY.setPreferredSize(sizePanel);
		fieldNomY.setPreferredSize(sizeField);
		panNomY.add(labelNomY);
		panNomY.add(fieldNomY);
		
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
		
		//Panel pour indiquer le nombre de point pour tracer la fonction
		JPanel panPoint = new JPanel();
		JLabel labelPoint = new JLabel("Echantillonage : ");
		fieldPoint = new JFormattedTextField(NumberFormat.getIntegerInstance());
		panPoint.setPreferredSize(sizePanel);
		fieldPoint.setPreferredSize(sizeField);
		panPoint.add(labelPoint);
		panPoint.add(fieldPoint);
		
		//Panel pour valider ou annuler la saisie
		JPanel panButton = new JPanel();
		valider = new JButton("Valider"); //enable quand tout les champs sont pleins
		annuler = new JButton("Annuler"); //sort de la saisie sans rien renvoyer
		panButton.setPreferredSize(sizePanel);
		valider.setPreferredSize(sizeField);
		annuler.setPreferredSize(sizeField);
		annuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		//valider.setEnabled(false);
		valider.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				int exprLen = fieldExpr.getText().length();
				int minLen = fieldMin.getText().length();
				int maxLen = fieldMax.getText().length();
				int pointLen = fieldPoint.getText().length();
				int nomXLen = fieldNomX.getText().length();
				int nomYLen = fieldNomY.getText().length();
		
				if (exprLen != 0 && minLen != 0 && maxLen != 0 && pointLen != 0 && nomXLen != 0 && nomYLen != 0) {
					 Float min = Float.valueOf(fieldMin.getText());
					 Float max = Float.valueOf(fieldMax.getText());
					 int points = Integer.valueOf(fieldPoint.getText());
					 String expr = fieldExpr.getText().trim();
					 
					 PythonLambda pl = new PythonLambda();
					 arrayX = pl.createBoundedArray(min,max,points);
					 arrayY = pl.arrayPython(arrayX,expr);
					 System.out.print("arrayX : ");
					 for (int i = 0; i < arrayX.length; i++) {
						  System.out.print(arrayX[i]+" ");
					 }
					 System.out.println(" ");
					 System.out.print("arrayY : ");
					 for (int i = 0; i < arrayY.length; i++) {
						  System.out.print(arrayY[i]+" ");
					 }

					 nomX = fieldNomX.getText();
					 nomY = fieldNomY.getText();
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
		panButton.add(valider);
		panButton.add(annuler);
		
		this.add(panExpr);
		this.add(panNomX);
		this.add(panNomY);
		this.add(panMin);
		this.add(panMax);
		this.add(panPoint);
		this.add(panButton);
	}
	
	public Float[] getArrayX() {
		return this.arrayX;
	}
	
	public Float[] getArrayY() {
		return this.arrayY;
	}
	
	public String getNomX() {
		return this.nomX;
	}
	
	public String getNomY() {
		return this.nomY;
	}
	
}
