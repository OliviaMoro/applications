package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


@SuppressWarnings("serial")
public class BlankPage extends CustomComponent{
	protected static int labelPadding = 12;
	protected static int padding = 30;
	protected int width = 0;
	protected int height = 0;
	
	public BlankPage() {
		
	}

	public BlankPage(CustomPanel parent) {
		this.width = parent.getSize().width;
		this.height = parent.getSize().height;
		System.out.println("width :"+ width);
		System.out.println("height :"+ height);
	}
	
	
	protected void drawComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setColor(Color.WHITE);
		//fill the rect
		g2.fillRect(padding+labelPadding, 
				padding,
				width-2*padding-labelPadding, 
				height-2*padding-labelPadding);
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	
}
