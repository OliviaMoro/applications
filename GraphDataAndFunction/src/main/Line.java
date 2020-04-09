package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Line extends CustomComponent{
	//Position sur l'axe X/Y : initialisé au dehors du conteneur
	private CustomPoint a = new CustomPoint(-10,-10);
	private CustomPoint b = new CustomPoint(-20,-20);
	
	//Propriétés de la ligne
	private Color color = Color.red;
	private Float width = 2f;
	private int cap = BasicStroke.CAP_BUTT;
	private float[] dashingPattern = {2f,2f};
	private static List<float[]> dashes = setDashPattern();
	private BasicStroke shape = new BasicStroke(width);
	
	public Line() {}
	
	//Une ligne relie deux points 
	public Line(CustomPoint a,CustomPoint b) {
		this.a = a;
		this.b = b;
	}
	
	public Line(CustomPoint a,CustomPoint b,Float width,Color color){
		this.width = width;
		this.a = a;
		this.b = b;
		this.color = color;
	}
	
	protected void drawComponent(Graphics g) {
		  Graphics2D g2d = (Graphics2D) g;
		  
		  shape = new BasicStroke(width,cap,BasicStroke.JOIN_MITER,1.0f,
				  dashingPattern,0.0f);		
		  g2d.setStroke(shape);			  
	      g2d.drawLine(a.getX(),a.getY(),b.getX(),b.getY());
	}
	
	public static List<float[]> getDashPattern(){
		return dashes;
	}
	
	public static List<float[]> setDashPattern(){
		List<float[]> dashes = new ArrayList<float[]>();
		float[] dash1 = {10f, 0f};//straight line
		dashes.add(dash1);
        float[] dash2 = {10f, 4f};  //dash
        dashes.add(dash2);
        float[] dash3 = {2f, 2f};//dot
        dashes.add(dash3);
        float[] dash4 = {8f,2f,1f,2f,1f,2f};//dash dot dot
        dashes.add(dash4);
        float[] dash5 = {10f, 6f, 2f, 6f};//dash dot
        dashes.add(dash5);
        float[] dash6 = {2f, 10f, 2f, 10f};//spaced dots
        dashes.add(dash6);
        float[] dash7 = {10f, 4f, 4f, 4f};//dash little dash
        dashes.add(dash7);
        float[] dash8 = {0f,1f};//no line
        dashes.add(dash8);

		return dashes;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Float getLineWidth() {
		return width;
	}

	public void setLineWidth(Float width) {
		this.width = width;
	}

	public int getCap() {
		//BasicStroke.CAP_BUTT,BasicStroke.CAP_SQUARE,BasicStroke.CAP_ROUND
		return cap;
	}
 	
	public void setCap(int cap) {
		this.cap = cap;
	}

	public float[] getDashingPattern() {
		return dashingPattern;
	}

	public void setDashingPattern(float[] dashingPattern) {
		this.dashingPattern = dashingPattern;
	}

	public BasicStroke getShape() {
		return shape;
	}

	public void setShape(BasicStroke shape) {
		this.shape = shape;
	}
	
}
