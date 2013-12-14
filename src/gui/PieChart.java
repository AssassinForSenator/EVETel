package gui;

import java.awt.*;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PieChart extends JPanel{
	
	public void paint (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.RED);
		g2d.drawArc(40, 40, 200, 200, 0, 90);
		g2d.setColor(Color.GREEN);
		g2d.drawArc(40, 40, 200, 200, 90, 180);
		g2d.setColor(Color.BLUE);
		g2d.drawArc(40, 40, 200, 200, 180, 360);
	}

}
