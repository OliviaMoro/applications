package main;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;


@SuppressWarnings("serial")
public class BaseGraph extends BlankPage{
	protected Float[] baseX = {0f, 0.2f, 0.4f, 0.6f, 0.8f, 1f};
	protected Float[] baseY = {0f, 0.2f, 0.4f, 0.6f, 0.8f, 1f};
	protected Float xScale, yScale;
	protected int largeur = width-2*padding-labelPadding;
	protected int hauteur = height-2*padding-labelPadding;
	
	
	protected int numberYDivisions = 5;
	protected final Stroke GRAPH_STROKE = new BasicStroke(2f);
	protected int pointWidth = 5;
	//protected Color lineColor = new Color(255,255,254);
	protected Color gridColor = new Color(200, 200, 200, 200);
	
	protected int fontSize = 12;
	protected int fontType = Font.PLAIN;
	protected String fontName = "Serif";
	protected Font font = new Font(fontName,fontType,fontSize);
	protected String labelX = "x";
	protected String labelY = "y";
	protected String title = "Un titre";
	private Graphics2D g2; 

	
	public BaseGraph() {
		super();
	}
	
	public BaseGraph(CustomPanel parent) {
		super(parent);
		this.xScale = (float) (largeur)/(getMax(baseX)-getMin(baseX));
		this.yScale = (float) (hauteur)/(getMax(baseY)-getMin(baseY));
	}
	
	protected Float getMin(Float[] array) {
		Float minScore = Float.MAX_VALUE;
		for (Float val : array) {
			minScore = Math.min(minScore, val);
		}
		return minScore;
	}

	protected Float getMax(Float[] array) {
		Float maxScore = Float.MIN_VALUE;
		for (Float val : array) {
			maxScore = Math.max(maxScore, val);
		}
		return maxScore;
	}
	
	
	protected void drawComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		int width = super.getWidth();
		int height = super.getHeight();
		
		g2.setFont(font);
	
		drawHoritalLineAndLabel(g2,width,height); 
		drawVerticalLineAndLabel(g2,width,height);	
		
		drawLabelX(labelX,g2,width,height);
		drawLabelY(labelY,g2,width,height);
		drawTitle(title,g2,width,height);
	}
	
	protected void drawTitle(String title,Graphics2D g2,int width,int height) {
		FontMetrics metrics = g2.getFontMetrics();
		int labelWidth = metrics.stringWidth(title);
		int x0 = (int)((width-labelWidth)/2.);
		int y0 = 2*labelPadding;
		g2.drawString(title,x0,y0);
		setGraphics2D(g2);
	}
	
	protected void drawLabelX(String labelX,Graphics2D g2,int width,int height){
		int x0 = (int)(width/2.);
		int y0 = height-padding+2*labelPadding;
		g2.drawString(labelX,x0,y0);
		setGraphics2D(g2);
	}
	
	protected void drawLabelY(String labelY,Graphics2D g2,int width,int height){
		int x0 = labelPadding;
		int y0 = (int)(height/2.);
		AffineTransform affineTransform = new AffineTransform();
		affineTransform.rotate(Math.toRadians(-90), 0, 0);
		Font rotatedFont = font.deriveFont(affineTransform);
		g2.setFont(rotatedFont);
		g2.drawString(labelY,x0,y0);
		g2.setFont(font);
		setGraphics2D(g2);
	}
	
	protected void drawHoritalLineAndLabel(Graphics2D g2,int width,int height) {
		for (int i = 0; i < numberYDivisions+1 ; i++) {
		//for (int i = 0; i < arrayX.length; i++) {
			int x0 = padding + labelPadding;
			int x1 = pointWidth + padding + labelPadding;
			int h = height - padding * 2 - labelPadding;
			int y0 = height - padding - labelPadding - i*h/numberYDivisions;
			int y1 = y0;
			if (baseX.length > 0) {
				g2.setColor(gridColor);
				g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, width - padding, y1);
				g2.setColor(Color.BLACK);
				Float y = getMin(baseY)+i*(getMax(baseY)-getMin(baseY))/(numberYDivisions);
				String yLabel = y.toString();
				//System.out.println("yLabel :"+yLabel);
				FontMetrics metrics = g2.getFontMetrics();
				int labelWidth = metrics.stringWidth(yLabel);
				g2.drawString(yLabel, x0 - labelWidth - 6, y0 + (metrics.getHeight() / 2) - 3);
			}
			g2.drawLine(x0, y0, x1, y1);
		}
		g2.drawLine(padding+labelPadding, height-padding-labelPadding, width-padding, height-padding-labelPadding);
		setGraphics2D(g2);
	}
	
	protected void drawVerticalLineAndLabel(Graphics2D g2,int width,int height) {
		 for (int i = 0; i < numberYDivisions +1; i++) {
		//for (int i = 0; i < arrayX.length; i++) {
			if (baseX.length > 1) {
				int l = width-padding*2-labelPadding;
				int x0 = i*l/(numberYDivisions)+padding+labelPadding;
				int x1 = x0;
				int y0 = height-padding-labelPadding;
				int y1 = y0 - pointWidth;
				if ((i % ((int) (baseX.length/numberYDivisions))) == 0) { 
					g2.setColor(gridColor);
					g2.drawLine(x0, height - padding - labelPadding - 1 - pointWidth, x1, padding);
					g2.setColor(Color.BLACK);
					Float x = getMin(baseX) +i*(getMax(baseX)-getMin(baseX))/(numberYDivisions);
					String xLabel = x.toString();
					//System.out.println("xLabel :"+xLabel);
					FontMetrics metrics = g2.getFontMetrics();
					int labelWidth = metrics.stringWidth(xLabel);
					g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
				}
				g2.drawLine(x0, y0, x1, y1);
			}
		}
		g2.drawLine(padding+labelPadding, height-padding-labelPadding, padding+labelPadding, padding);
		setGraphics2D(g2);
	}
	
	/*** ACCESSEURS ET MUTATEURS ***/
	public Graphics2D getGraphics2D() {
		return this.g2;
	}
	
	public String getFontName() {
		return this.fontName;
	}
	
	public int getFontSize() {
		return this.fontSize;
	}
	
	public int getFontType() {
		return this.fontType;
	}
	
	public Float[] getBaseX() {
		return this.baseX;
	}
	
	public Float[] getBaseY() {
		return this.baseY;
	}
	
	public void setBaseX(Float[] baseX) {
		this.baseX = baseX;
		setXScale((float) (largeur)/(getMax(baseX)-getMin(baseX)));
	}
	
	public void setBaseY(Float[] baseY) {
		this.baseY = baseY;
		setYScale((float) (largeur)/(getMax(baseY)-getMin(baseY)));
	}
	
	public void setGraphics2D(Graphics2D g2) {
		this.g2=g2;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
		this.font = new Font(fontName,getFontType(),getFontSize());
	}
	
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
		this.font = new Font(getFontName(),getFontType(),fontSize);
	}
	
	public void setFontType(int fontType) {
		this.fontType = fontType;
		this.font = new Font(getFontName(),fontType,getFontSize());
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setlabelX(String labelX) {
		this.labelX = labelX;
	}
	
	public void setlabelY(String labelY) {
		this.labelY = labelY;
	}
	
    public Float getXScale() {
    	return this.xScale;
    }
    
    public void setXScale(Float xScale) {
    	this.xScale = xScale;
    }
    
    public Float getYScale() {
    	return this.yScale;
    }
    
    public void setYScale(Float yScale) {
    	this.yScale = yScale;
    }

}
