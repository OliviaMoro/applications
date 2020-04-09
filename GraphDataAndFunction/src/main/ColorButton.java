package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class ColorButton extends JButton{
	private Color color;
	
	public ColorButton(Color color){
		super();
		this.color = color;
		this.setForeground(color);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setColor(color);
	}     
	
	public Color getColor() {
		return this.color;
	}
	
	public void setColor(Color color) {
		this.color = color;
		this.setForeground(color);
	}
}
