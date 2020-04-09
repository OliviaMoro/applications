package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class DataDialog extends JDialog {
	private DataDialogInfo dataInfo = new DataDialogInfo();
	private JLabel fichier;
	private JTextField locationFichier = new JTextField();
	private JScrollPane dataPreview;
	private JButton browse;
	@SuppressWarnings("unused")
	private boolean sendData;

	public DataDialog(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponent();
		this.setVisible(true);
	}

	public DataDialogInfo showDialog(){
		this.sendData = false;
		this.setVisible(true);      
		return this.dataInfo;      
	}


	private void initComponent() {
		//Panel de selection des données
		JPanel panSelect = new JPanel();
		panSelect.setPreferredSize(new Dimension(600,50));	
		panSelect.setBackground(Color.white);

		fichier = new JLabel("Veuillez choisir un fichier :");
		locationFichier.setPreferredSize(new Dimension(200,25));

		browse = new JButton("Browse");
		browse.setPreferredSize(new Dimension(100,25));
		//browse.addActionListener(this);

		panSelect.add(fichier);
		panSelect.add(locationFichier);
		panSelect.add(browse);
		this.add(panSelect,BorderLayout.NORTH);

		//Panel de prévisualisation des données :
		JPanel panPreview = new JPanel();
		panPreview.setBackground(Color.white);

		JTextArea table = new JTextArea();
		table.setPreferredSize(new Dimension(550,250));
		dataPreview = new JScrollPane(table);

		panPreview.add(dataPreview);
		this.add(panPreview,BorderLayout.CENTER);

		//Ajout du listener personnalisé pour la prévisualisation
		BrowseListener bl = new BrowseListener(table,locationFichier);
		browse.addActionListener(bl);

		//Panel de sélection et nommage des données avec envoi au graphe
		JPanel panVariable = new JPanel();
		panVariable.setBackground(Color.white);
		panVariable.setPreferredSize(new Dimension(600,50));

		JTextField nomsVariables = new JTextField();
		nomsVariables.setPreferredSize(new Dimension(400,25));

		/* Gérer le cas où aucun jeu de données n'est sélectionné */
		JButton send = new JButton("OK");
		send.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {        
				dataInfo = new DataDialogInfo(getData(), getVariables());
				setVisible(false);
				//dataSets.addDataSet(getData(), getVariables());
			}

			public Float[][] getData(){
				Float[][] dataTab = null;
				if (locationFichier.getText().length() != 0) {
					File file = new File(locationFichier.getText());
					Data dataReader = new Data(file);
					dataTab = dataReader.getDataTab();
				}
				return dataTab;  
			}

			public String[] getVariables(){
				String[] variables = null;
				if (nomsVariables.getText().length() != 0) {
					variables = nomsVariables.getText().split(" ");
				}
				return variables;
			}      
		});
		panVariable.add(nomsVariables);
		panVariable.add(send);
		this.add(panVariable,BorderLayout.SOUTH);

	}


}
