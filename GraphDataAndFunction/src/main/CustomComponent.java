package main;
import java.awt.Graphics;

import javax.swing.JComponent;

@SuppressWarnings("serial")
public abstract class CustomComponent extends JComponent {

	protected abstract void drawComponent(Graphics g);
	
}
