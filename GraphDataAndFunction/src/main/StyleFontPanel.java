package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class StyleFontPanel extends JPanel{
	private MainWindow parent;
	//Paramètres de la fonte utilisée pour les légendes des graphes
	private List<String> fontStyles = new ArrayList<String>();
	private int[] fontSizes = getAvailableSizes();
	private List<String> fontNames = getAvailableStyles();
	private JComboBox<String> comboFonts,comboStyles;
	private JComboBox<Integer> comboSizes; 
	private JTextField current = new JTextField();


	public StyleFontPanel(MainWindow parent,Dimension size) {
		this.parent = parent;
		this.setPreferredSize(size);
		this.setBackground(Color.white);
		initComponent(size);
		initListener(parent);
	}

	private void initListener(MainWindow parent) {
		StyleListener sl = new StyleListener(parent);
		current.getDocument().addDocumentListener(sl);
	}

	private void initComponent(Dimension size) {
		GridLayout gl = new GridLayout(4,1);
		this.setLayout(gl);

		//Panel de controle
		JPanel controle = new JPanel();
		controle.setPreferredSize(new Dimension(150,50));	
		controle.setBackground(Color.white);
		JLabel labelCurrent = new JLabel("Graphe :");
		current.setPreferredSize(new Dimension(100,25));
		controle.add(labelCurrent);
		controle.add(current);
		this.add(controle);


		//Panel de selection de la fonte
		JPanel panFont = new JPanel();
		panFont.setPreferredSize(new Dimension(150,50));	
		panFont.setBackground(Color.white);
		comboFonts = new JComboBox<String>();
		FontListener fl = new FontListener(parent,current);
		comboFonts.addItemListener(fl);
		fillCombo(fontNames,comboFonts);
		comboFonts.setPreferredSize(new Dimension(100,25));
		JLabel labelFont = new JLabel("Nom de la fonte :");
		panFont.add(labelFont);
		panFont.add(comboFonts);
		this.add(panFont);


		//Panel de sélection de la taille :
		JPanel panSize = new JPanel();
		panSize.setPreferredSize(new Dimension(150,50));	
		panSize.setBackground(Color.white);
		comboSizes = new JComboBox<Integer>();
		SizeListener sizel = new SizeListener(parent,current);
		comboSizes.addItemListener(sizel);
		fillCombo(fontSizes,comboSizes);
		comboSizes.setPreferredSize(new Dimension(50,25));		
		JLabel labelSize = new JLabel("Taille de la fonte :");
		panSize.add(labelSize);
		panSize.add(comboSizes);
		this.add(panSize);


		//Panel de sélection du style :
		JPanel panStyle = new JPanel();
		panStyle.setPreferredSize(new Dimension(150,50));	
		panStyle.setBackground(Color.white);
		comboStyles = new JComboBox<String>();
		TypeListener typel = new TypeListener(parent,current);
		comboStyles.addItemListener(typel);
		fontStyles.add("bold");
		fontStyles.add("italic");
		fontStyles.add("plain");
		fillCombo(fontStyles,comboStyles);
		comboStyles.setPreferredSize(new Dimension(50,25));		
		JLabel labelStyle = new JLabel("Style de la fonte :");
		panStyle.add(labelStyle);
		panStyle.add(comboStyles);
		this.add(panStyle);
	}

	private void fillCombo(List<String> liste, JComboBox<String> combo) {
		for(String el : liste) {
			combo.addItem(el);
		}
	}

	private void fillCombo(int[] liste, JComboBox<Integer> combo) {
		for(int el : liste) {
			combo.addItem(el);
		}
	}

	private int[] getAvailableSizes() {
		int[] sizes = new int[30];
		for (int i = 0; i<30; i++) {
			sizes[i] = i+1;
		}
		return sizes;
	}

	private List<String> getAvailableStyles(){
		List<String> styles = new ArrayList<String>();
		GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fonts = e.getAvailableFontFamilyNames();
		for (String f : fonts){
			styles.add(f);
		}
		return styles;
	}

	public JTextField getCurrent() {
		return this.current;
	}

}
