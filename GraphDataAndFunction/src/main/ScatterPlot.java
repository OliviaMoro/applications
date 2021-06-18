package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class ScatterPlot extends BaseGraph{
	protected Color pointColor = new Color(255,0,255);
	protected Color lineColor = Color.RED;
	private int cap = BasicStroke.CAP_BUTT;
	private float[] dashingPattern = {2f,2f};
	private BasicStroke stroke = new BasicStroke(width);

	private Float[] arrayX, arrayY;
	private List<Point> graphPoints;
	private boolean showing = true;
	private String shape = "\u2219";
	private Float lineWidth = 2f;

	public ScatterPlot(CustomPanel parent) {
		super(parent);
	}

	public ScatterPlot(CustomPanel parent, Float[] arrayX, Float[] arrayY) {
		super(parent);
		this.arrayX = arrayX;
		this.arrayY = arrayY;	
		//Changement d'échelle automatique :
		setBaseX(setScale(arrayX,getBaseX()));
		setBaseY(setScale(arrayY,getBaseY()));

		this.xScale = (float) (largeur)/(getMax(baseX)-getMin(baseX));
		this.yScale = (float) (hauteur)/(getMax(baseY)-getMin(baseY));
	}

	protected void drawComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);

		if(this.showing) {
			/*
			Float deltaX = Math.abs(getMin(arrayX)-getMin(baseX));
			Float deltaY = Math.abs(getMin(arrayY)-getMin(baseY));
			System.out.println("deltaX :"+deltaX);
			System.out.println("deltaY :"+deltaY);*/

			graphPoints = new ArrayList<>();
			for (int i = 0; i < arrayX.length; i++) {
				//int x1 = (int) (deltaX*xScale+(getMax(arrayX)-arrayX[i])*xScale + padding + labelPadding);
				//int y1 = (int) (height-padding-labelPadding-(getMax(arrayY)-arrayY[i])*yScale-deltaY*yScale);
				int x1 = (int) ((arrayX[i])*xScale + padding + labelPadding);
				int y1 = (int) (height-padding-labelPadding-(arrayY[i])*yScale);
				//On ajoute pas les points en dehors du domaine visible du graphe
				if(x1<=width-padding && y1>=padding) {
					graphPoints.add(new Point(x1, y1));
					/*System.out.println("x1 :"+x1+" x :"+arrayX[i]);
					System.out.println("y1 :"+y1+" y :"+arrayY[i]);*/
				}

			}

			//Tracer des lignes entre les points :
			stroke = new BasicStroke(lineWidth,cap,BasicStroke.JOIN_MITER,1.0f,
					dashingPattern,0.0f);		
			g2.setStroke(stroke);		
			Stroke oldStroke = g2.getStroke();
			g2.setColor(lineColor);
			//g2.setStroke(GRAPH_STROKE);
			for (int i = 0; i < graphPoints.size() - 1; i++) {
				int x1 = graphPoints.get(i).x;
				int y1 = graphPoints.get(i).y;
				int x2 = graphPoints.get(i + 1).x;
				int y2 = graphPoints.get(i + 1).y;
				g2.drawLine(x1, y1, x2, y2);
			}


			//Tracer des points de arrayX, arrayY sans ceux hors de baseX et baseY
			g2.setStroke(oldStroke);
			g2.setColor(pointColor);

			for (int i = 0; i < graphPoints.size(); i++) {
				//Pour la taille du point 
				Font font = g2.getFont().deriveFont((float)pointWidth);
				g.setFont(font);

				//Attention à l'offset
				FontMetrics metrics = g2.getFontMetrics();
				int width = metrics.stringWidth(shape);		
				int x = graphPoints.get(i).x - width / 2;
				int y = graphPoints.get(i).y + width / 2;

				g.drawString(shape,x,y);
			}

		}

	}

	public boolean isShowing() {
		return this.showing;
	}

	public void hide() {
		this.showing = false;
		this.graphPoints = new ArrayList<Point>();
		repaint();
	}

	public void show() {
		this.showing = true;
		repaint();
	}

	public Float[] getArrayX() {
		return this.arrayX;
	}

	public Float[] getArrayY() {
		return this.arrayY;
	}

	public void setArrayX(Float[] arrayX) {
		this.arrayX = arrayX;
	}

	public void setArrayY(Float[] arrayY) {
		this.arrayY = arrayY;
	}

	public Color getColor() {
		return this.pointColor;
	}

	public void setColor(Color color) {
		this.pointColor = color;
	}

	public int getPointWidth() {
		return this.pointWidth;
	}

	public void setPointWidth(int pointWidth) {
		this.pointWidth = pointWidth;
	}

	public Float[] setScale(Float[] array,Float[] base) {
		float oldMin = getMin(base);
		float oldMax = getMax(base);

		float min = getMin(array);
		float max = getMax(array);

		/*System.out.println("oldMin : "+ oldMin + " min : "+min);
		System.out.println("oldMax : "+ oldMax + " max : "+max);*/

		PythonLambda pl = new PythonLambda();
		Float[] newArray = base;
		//domaine d'arrayX à l'extérieur gauche
		if(min<oldMin && max<=oldMax) {
			newArray = pl.createBoundedArray(min,oldMax,6);
			//System.out.println("déborde à gauche");
		}
		//domaine d'arrayX à l'extérieur droit 
		else if(max>oldMax && min>=oldMin) {
			newArray = pl.createBoundedArray(oldMin,max,6);
			//System.out.println("déborde à droite");
		}
		//domaine d'arrayX compris dans celui de base
		else if(max>oldMax && min<oldMin) {
			newArray = pl.createBoundedArray(min,max,6);
			//System.out.println("domaine élargi des deux cotés");
		}
		//domaine d'arrayX contenu dans celui de base
		else if(min>=oldMin && max<=oldMax) {
			newArray = base;
			//System.out.println("domaine inclus");
		}
		return newArray;
	}

	public String getShape() {
		return shape;
	}

	public void setShape(String shape) {
		this.shape = shape;
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
	
	public Float getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(Float lineWidth) {
		this.lineWidth = lineWidth;
	}
	
	public Color getLineColor() {
		return this.lineColor;
	}
	
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}


}
