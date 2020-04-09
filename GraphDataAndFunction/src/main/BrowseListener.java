package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class BrowseListener implements ActionListener {
	private JFileChooser fileChooser;
	private JTextArea table;
	private JTextField nom;
	private File file;
	private int returnValue;
	private int maxRow = 20;
	private int maxCol = 10;

	public BrowseListener(JTextArea jta, JTextField jtf) {
		this.table = jta;
		this.nom = jtf;
	}

	public void actionPerformed(ActionEvent arg0) {
		fileChooser = new JFileChooser();
		fileChooser.showOpenDialog(null);
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			file = fileChooser.getSelectedFile();
			String chemin = file.getPath();
			nom.setText(chemin);

			//Read File : preview
			Data dataReader = new Data(file);
			List<List<Float>> data = dataReader.getDataList();
			data = trimListArray(data,maxRow,maxCol);

			//Preview Display
			for (List<Float> line : data) {
				for (Float value : line) {
					table.append(value + "\t");
				}
				table.append("\n");
			}

		}
	}

	private List<List<Float>> trimListArray(List<List<Float>> data, int maxRow, int maxCol) {
		//On redimensionne data s'il est trop grand pour le cadre
		List<List<Float>> dataTab = data;
		//System.out.println("Rows : "+dataTab.size());
		if (data.size() > maxRow) {
			dataTab.subList(0, maxRow);
		}
		//System.out.println("Col : "+data.get(0).size());
		if (data.get(0).size() > maxCol) {
			for(List<Float> line : dataTab) {
				line.subList(0, maxCol);
			}
		}
		return dataTab;		
	}


}
