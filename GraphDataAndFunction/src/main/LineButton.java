package main;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class LineButton extends JButton{
	private float[] dash;
	private BasicStroke shape = new BasicStroke(2f);
	private int cap = BasicStroke.CAP_BUTT;
	
	public LineButton(){
		super();
	}
	
	public LineButton(float[] dash){
		super();
		this.dash = dash;
		this.repaint();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		shape = new BasicStroke(2f,cap,BasicStroke.JOIN_MITER,1.0f,dash,0.0f);		
		g2d.setStroke(shape);			  
	    g2d.drawLine(4,12,145,12);
	    
	}
	
	public float[] getDash() {
		return this.dash;
	}
	
	public void setDash(float[] dash) {
		this.dash = dash;
	}

}
