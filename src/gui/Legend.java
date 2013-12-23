package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Legend extends JPanel {
	
	TreeMap<Integer, String> list;
	
	public Legend(TreeMap<Integer, String> list){
		setPreferredSize(new Dimension(374, list.size()*12));
		setSize(new Dimension(374, list.size()*6));
		this.list = list;
	}
	
	public Legend(){

	}
	
	public void update(TreeMap<Integer, String> list){
		setPreferredSize(new Dimension(374, list.size()*12));
		setSize(new Dimension(374, list.size()*6));
		this.list = list;
		paintComponent(getGraphics());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(getBackground());		
		g2d.fillRect(0, 0, getWidth(), getHeight());

		if(list != null && list.size() > 0){
		
			drawList(g2d, list);
		}
	}
	
	private void drawList(Graphics2D g2d, TreeMap<Integer, String> list){
		for(Map.Entry<Integer, String> mp : list.entrySet()){
			drawnString(g2d, mp.getKey(), mp.getValue());
		}
	}

	private void drawnString(Graphics2D g2d, Integer key, String value) {
		g2d.setColor(Color.BLACK);
		g2d.drawString(value, 10, key*12);
	}
}
