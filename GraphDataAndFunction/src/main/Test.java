package main;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Test extends JFrame{
	CustomPanel panel = new CustomPanel("Test");
	
	public Test() {
		this.setTitle("Test");
        this.setSize(480, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.add(panel);
	}
	
	public CustomPanel getPanel() {
		return this.panel;
	}
	
	public static void main(String[] args) {
		Test test = new Test();
        CustomPoint a = new CustomPoint(10,20);
        CustomPoint b = new CustomPoint(400,20);
        Line line1 = new Line(a,b);
        float[] dash1 = {10f, 4f};  //dash
        line1.setDashingPattern(dash1);

        CustomPoint c = new CustomPoint(10,40);
        CustomPoint d = new CustomPoint(400,40);
        Line line2 = new Line(c,d);
        float[] dash2 = {2f, 2f};
        line2.setDashingPattern(dash2);//dot
        
        CustomPoint e = new CustomPoint(10,60);
        CustomPoint f = new CustomPoint(400,60);
        Line line3 = new Line(e,f);
        float[] dash3 = {8f,2f,1f,2f,1f,2f};
        line3.setDashingPattern(dash3);//dash dot dot
        
        CustomPoint g = new CustomPoint(10,80);
        CustomPoint h = new CustomPoint(400,80);
        Line line4 = new Line(g,h);
        float[] dash4 = {10f, 6f, 2f, 6f};
        line4.setDashingPattern(dash4);//dash dot
        
        CustomPoint i = new CustomPoint(10,100);
        CustomPoint j = new CustomPoint(400,100);
        Line line5 = new Line(i,j);
        float[] dash5 = {2f, 10f, 2f, 10f};
        line5.setDashingPattern(dash5);//spaced dots
        
        
        CustomPoint k = new CustomPoint(10,120);
        CustomPoint l = new CustomPoint(400,120);
        Line line6 = new Line(k,l);
        float[] dash6 = {10f, 4f, 4f, 4f};
        line6.setDashingPattern(dash6);//dash little dash
        
        CustomPoint m = new CustomPoint(10,140);
        CustomPoint n = new CustomPoint(400,140);
        Line line7 = new Line(m,n);
        //float[] dash7 = {10f, 0f};
        float[] dash8 = {0f,1f};
        line7.setDashingPattern(dash8);//straight
        
        c.setPointSize(50);
        //test.getPanel().addCustomComponent(c);
        
        test.getPanel().addCustomComponent(line1);
        test.getPanel().addCustomComponent(line2);
        test.getPanel().addCustomComponent(line3);
        test.getPanel().addCustomComponent(line4);
        test.getPanel().addCustomComponent(line5);
        test.getPanel().addCustomComponent(line6);
        test.getPanel().addCustomComponent(line7);
        
	}

}
