package gui;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PieChart extends JPanel{
	
	Color[] optimalList = new Color[] { new Color(102,164,165), new Color(252,141,98), new Color(141,160,203), new Color(231,138,195), new Color(166,216,84), new Color(255,217,47)};
	int n = 0;
	boolean drawOther;
	
	TreeMap<String, Integer> list;
	TreeMap<Integer, String> output;
	
	public TreeMap<Integer, String> update(TreeMap<String, Integer> list, boolean drawOther){
		this.list = list;
		this.drawOther = drawOther;
		paintComponent(getGraphics());
		
		return output;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;

		if(list != null && list.size() > 0){
			g2d.setColor(getBackground());
			g2d.fillRect(0, 0, getWidth(), getHeight());
			n = 1;
			
			output = drawSegments(g2d, list);
		}
	}
	
	private TreeMap<Integer, String> drawSegments(Graphics2D g2d, TreeMap<String, Integer> list){
		ArrayList<Map.Entry<String, Integer>> sortedList = new ArrayList<Map.Entry<String, Integer>>();
		TreeMap<Integer, String> output = new TreeMap<Integer, String>();
		
		int i = 0;
		int deg = 0;
		int total = 0;
		int k = 0;
		
		for(int j : list.values()){
			total = total + j;
		}
		
		sortedList.addAll(list.entrySet());
		
		Collections.sort(sortedList, new Comparator<Map.Entry<String, Integer>>(){
			@Override
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2){
				return o2.getValue().compareTo(o1.getValue());
			}
		});
		
		for(Map.Entry<String, Integer> md : sortedList){
			int x = md.getValue();
			k = k + x;
			if(deg < 340 && (double) x/total > 0.01d || !drawOther){
				int angle = (int) Math.round((double) x/total*360);
				drawArc(g2d, optimalList[i%6], deg, angle);
				deg = deg + angle;
				i++;
			} else if(k == total){
				//"Other"
				drawArc(g2d, Color.gray, deg, 360-deg);
			}
		}
		
		k = 0;
		deg = 0;
		for(Map.Entry<String, Integer> md : sortedList){
			int x = md.getValue();
			k = k + x;
			if(deg < 340 && (double) x/total > 0.01d || !drawOther){
				int angle = (int) Math.round((double) x/total*360);
				output.put(n, drawText(g2d, md.getKey(), deg, angle));
				deg = deg + angle;
			} else if(k == total){
				drawText(g2d, "Other", deg, 360-deg);
			}
		}

		return output;
	}
	
	private String drawText(Graphics2D g2d, String text, int startAngle, int angle){
		StringBuilder output = new StringBuilder();
		output.append(n + " : " + text);
		
		drawString(g2d, text, startAngle, angle);
		output.append(" " + drawValue(g2d, text, startAngle, angle) + "%");	
		return output.toString();
	}
	private void drawArc(Graphics2D g2d, Color c, int start, int amount){
		g2d.setColor(c);
		g2d.fillArc(40, 40, 200, 200, (start + 90)%360, amount);
	}
	
	private void drawString(Graphics2D g2d, String text, int startAngle, int angle){
		double percent = (double) Math.round((double) angle/360*100*1000)/1000;
		if(percent > 5 || text.equalsIgnoreCase("Other")){
			g2d.setColor(Color.BLACK);
			g2d.setFont(getFont().deriveFont(9.0f));
			g2d.drawString(text, (float) Math.cos(Math.toRadians(-(angle/2 + startAngle) - 90))*80 + 140 - text.length()*2, (float) Math.sin(Math.toRadians(-(angle/2 + startAngle) - 90))*80 + 140);
		} else {
			g2d.setColor(Color.BLACK);
			g2d.setFont(getFont().deriveFont(9.0f));
			g2d.drawString(String.valueOf(n), (float) Math.cos(Math.toRadians(-(angle/2 + startAngle) - 90))*90 + 140, (float) Math.sin(Math.toRadians(-(angle/2 + startAngle) - 90))*90 + 140);
			n++;
		}
	}
	private double drawValue(Graphics2D g2d, String text0, int startAngle, int angle){
		double percent = (double) Math.round((double) angle/360*100*1000)/1000;
		if(percent > 5 || text0.equalsIgnoreCase("Other")){
			String text = String.valueOf(percent) + "%";
			g2d.setColor(Color.BLACK);
			g2d.setFont(getFont().deriveFont(9.0f));
			g2d.drawString(text, (float) Math.cos(Math.toRadians(-(angle/2 + startAngle) - 90))*80 + 140 - text.length()*2, (float) Math.sin(Math.toRadians(-(angle/2 + startAngle) - 90))*80 + 140 + 15);
		} else {
			return percent;
		}
		return 0;
	}

}
