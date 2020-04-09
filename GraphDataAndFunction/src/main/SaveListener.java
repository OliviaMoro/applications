package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class SaveListener implements ActionListener {
	private MainWindow parent;
	private DisplayPanel panel;
	private JFileChooser jfc;
	private File file;
	private int returnValue;
	
	public SaveListener(MainWindow parent) {
		this.parent = parent;	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.panel = parent.getDisplayPanel();//.getCurrentPanel();
		try {
			SaveScreenShot(panel);
		} catch (Exception ex) {
			
		}
	}
	
	public BufferedImage getScreenShot(DisplayPanel panel) {
		BufferedImage image = new BufferedImage(panel.getWidth(),
				panel.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		panel.paint(image.getGraphics());
		return image;
	}

	public void SaveScreenShot(DisplayPanel panel) throws Exception {
		BufferedImage img = getScreenShot(panel);
		jfc = new JFileChooser();
		jfc.showOpenDialog(null);
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			file = jfc.getSelectedFile();
			//String chemin = file.getPath();
			ImageIO.write(img,"png",file);
		}
	}
}
