package main;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;



@SuppressWarnings("serial")
public class CustomPoint extends CustomComponent{
	private Color color = Color.red;
	private int size = 10;
	//Position sur l'axe X/Y : initialisé au dehors du conteneur
	private int x = -10;
	private int y = -10;
	
	//Motifs
	private static List<String> shapes = setShapes();
	private String type = "\u2219";
	

	public CustomPoint() {}

	public CustomPoint(int x,int y) {
		this.setOpaque(true);
		this.x = x;
		this.y = y;
	}

	public CustomPoint(int x, int y, int size, Color color, String type){
		this.size = size;
		this.x = x;
		this.y = y;
		this.color = color;
		this.type = type;
	}

	protected void drawComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);
		//Couleur
		g.setColor(this.color);
		
		//Pour la taille du point 
		Font font = g2.getFont().deriveFont((float)size);
		g.setFont(font);
		
		//Attention à l'offset
		FontMetrics metrics = g2.getFontMetrics();
		int width = metrics.stringWidth(type);
		g.drawString(type, x-width/2, y+width/2);
		
		//Test 
		g.setColor(Color.black);
		g.drawLine(x-size/2,y-size/2,x+size/2,y-size/2);
		g.drawLine(x-size/2,y-size/2,x-size/2,y+size/2);
		g.drawLine(x-size/2,y+size/2,x+size/2,y+size/2);
		g.drawLine(x+size/2,y-size/2,x+size/2,y+size/2);
		g.fillOval(x,y,2,2);
		
	}
	
	//UNUSED NOW
	protected void drawShapes(Graphics g) {
		if(this.type.equals("circle")){
			g.fillOval(x,y,size,size); 
		}
		else if(this.type.equals("square")){
			g.fillRect(x,y,size,size);
		}
		else if(this.type.equals("triangle")){         
			int[] ptsX = regularPolygon(3)[0];
			int[] ptsY = regularPolygon(3)[1];  
			g.fillPolygon(ptsX, ptsY, 3);
		}
		else if (this.type.equals("diamond")) {
			int[] ptsX = regularPolygon(4)[0];
			int[] ptsY = regularPolygon(4)[1];  
			g.fillPolygon(ptsX, ptsY, 4);
		}
		else if (this.type.equals("pentagon")) {
			int[] ptsX = regularPolygon(5)[0];
			int[] ptsY = regularPolygon(5)[1];  
			g.fillPolygon(ptsX, ptsY, 5);
		}
		else if (this.type.equals("hexagon")) {
			int[] ptsX = regularPolygon(6)[0];
			int[] ptsY = regularPolygon(6)[1];  
			g.fillPolygon(ptsX, ptsY, 6);
		}
		else if (this.type.equals("heptagon")) {
			int[] ptsX = regularPolygon(7)[0];
			int[] ptsY = regularPolygon(7)[1];  
			g.fillPolygon(ptsX, ptsY, 7);
		}
		else if (this.type.equals("octogon")) {
			int[] ptsX = regularPolygon(8)[0];
			int[] ptsY = regularPolygon(8)[1];  
			g.fillPolygon(ptsX, ptsY, 8);
		}
		else if (this.type.equals("nanogon")) {
			int[] ptsX = regularPolygon(9)[0];
			int[] ptsY = regularPolygon(9)[1];  
			g.fillPolygon(ptsX, ptsY, 9);
		}
		else if (this.type.equals("decagon")) {
			int[] ptsX = regularPolygon(10)[0];
			int[] ptsY = regularPolygon(10)[1];  
			g.fillPolygon(ptsX, ptsY, 10);
		}
	}
	
	public static List<String> getShapes(){
		return shapes;
	}
	
	public static List<String> setShapes(){
		List<String> shapes = new ArrayList<String>();
		shapes.add("\u2B24");//point
		shapes.add("\u2B25");//diamond
		shapes.add("\u2B26");//diamondhole
		shapes.add("\u2BC0");//square
		shapes.add("\u2B1F");//pentagon
		shapes.add("\u2B20");//pentagonhole
		shapes.add("\u2BC2");//pentagoninvert
		shapes.add("\u2B27");//losange
		shapes.add("\u2B28");//losangevert
		shapes.add("\u2B2C");//ellipsehorz
		shapes.add("\u2B2D");//ellipsehorzhole
		shapes.add("\u2B2E");//ellipsevert
		shapes.add("\u2B2F");//ellipseverthole
		shapes.add("\u2B22");//hexagon
		shapes.add("\u2B21");//hexagonrot
		shapes.add("\u2B23");//hegagonhole
		shapes.add("\u2BC3");//octogon
		shapes.add("\u2BC4");//octogonrot
		shapes.add("\u2BC5");//triangleup
		shapes.add("\u2BC6");//triangledown
		shapes.add("\u2BC7");//triangleright
		shapes.add("\u2BC8");//triangleleft
		shapes.add("\u2BCC");//star4round
		shapes.add("\u2BCE");//star4holeround
		shapes.add("\u2BCD");//star4rotround
		shapes.add("\u2BCF");//star4rotholeround
		shapes.add("\u2605");//star5
		shapes.add("\u2606");//star5hole
		shapes.add("\u2219");//dot
		shapes.add("\uD83D\uDFC0");//star3
		shapes.add("\uD83D\uDFC4");//star4
		//shapes.add("\uD83D\uDFCB");//star6-1
		shapes.add("\uD83D\uDFCC");//star6-2
		shapes.add("\uD83D\uDFCE");//star8-1
		//shapes.add("\uD83D\uDFCF");//star8-2
		shapes.add("\uD83D\uDFD0");//star6-3
		shapes.add("\uD83D\uDFA8");//cross-1
		shapes.add("\uD83D\uDFA9");//cross-2
		shapes.add("\uD83D\uDFAA");//cross-3
		shapes.add("\uD83D\uDFAB");//cross-4
		shapes.add("\uD83D\uDFAC");//cross-5
		shapes.add("\uD83D\uDFAD");//cross-6
		shapes.add("\uD83D\uDFAE");//cross-7
		shapes.add("\uD83D\uDFA1");//plus-1
		shapes.add("\uD83D\uDFA2");//plus-2
		shapes.add("\uD83D\uDFA3");//plus-3
		shapes.add("\uD83D\uDFA4");//plus-4
		shapes.add("\uD83D\uDFA5");//plus-5
		shapes.add("\uD83D\uDFA6");//plus-6
		shapes.add("\uD83D\uDFA7");//plus-7
		shapes.add("\uD83D\uDFAF");//asterisk5-1
		shapes.add("\uD83D\uDFB0");//asterisk5-2
		shapes.add("\uD83D\uDFB1");//asterisk5-3
		shapes.add("\uD83D\uDFB2");//asterisk5-4
		shapes.add("\uD83D\uDFB3");//asterisk5-5
		shapes.add("\uD83D\uDFB4");//asterisk5-6
		shapes.add("\uD83D\uDFB5");//asterisk6-1
		shapes.add("\uD83D\uDFB6");//asterisk6-2
		shapes.add("\uD83D\uDFB7");//asterisk6-3
		shapes.add("\uD83D\uDFB8");//asterisk6-4
		shapes.add("\uD83D\uDFB9");//asterisk6-5
		shapes.add("\uD83D\uDFBA");//asterisk6-6
		shapes.add("\uD83D\uDFBB");//asterisk8-1
		shapes.add("\uD83D\uDFBC");//asterisk8-1
		shapes.add("\uD83D\uDFBD");//asterisk8-2
		shapes.add("\uD83D\uDFBE");//asterisk8-3
		shapes.add("\uD83D\uDFBF");//asterisk8-4
		return shapes;
	}

	public int[][] regularPolygon(int corner){
		int[] ptsX = new int[corner];
		int[] ptsY = new int[corner];
		
		for (int i=0; i<corner; i++) {
			ptsX[i] = (int) (x + size*Math.cos(2*Math.PI*i/corner)/2.);
			ptsY[i] = (int) (y + size*Math.sin(2*Math.PI*i/corner)/2.);	
		}
		int[][] result = {ptsX, ptsY};

		return result;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getPointSize() {
		return size;
	}

	public void setPointSize(int size) {
		this.size = size;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


}
