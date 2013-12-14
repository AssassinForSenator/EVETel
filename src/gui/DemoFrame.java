package gui;

import java.awt.Dimension;

import javax.swing.*;

import dataStructures.Kill;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class DemoFrame extends JFrame {
	private JTextField textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	PieChart pieChart = new PieChart();
	JRadioButton systemBtn;
	JRadioButton weaponBtn;
	JRadioButton shipBtn;
	JLabel lblDownloading;
	
	ArrayList<Kill> killList;
	String character;
	
	public DemoFrame() {
		setTitle("TRGSA Intel demo by Xen 'n Exo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(700, 350));
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 37, 165, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnGo = new JButton("Go!");
		btnGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				character = textField.getText();
				
				lblDownloading.setText("Downloading...");
				killList = api.KillBoard.getKillMails(api.Eve.getEntityID(character));	
				lblDownloading.setText("");
				
				if(shipBtn.isSelected()){
					pieChart.paint(pieChart.getGraphics() ,killStats.Stats.getShipTypes(killList, character));	
				} else if(weaponBtn.isSelected()){
					pieChart.paint(pieChart.getGraphics() ,killStats.Stats.getWeaponTypes(killList, character));	
				} else {
					pieChart.paint(pieChart.getGraphics() ,killStats.Stats.getSystems(killList));	
				}
			}
		});
		btnGo.setBounds(86, 62, 89, 23);
		getContentPane().add(btnGo);
		
		JLabel lblNewLabel = new JLabel("Enter Player/Corp/alliance name");
		lblNewLabel.setBounds(10, 12, 207, 14);
		getContentPane().add(lblNewLabel);
		
		pieChart.setBounds(394, 11, 280, 291);
		pieChart.setVisible(true);
		getContentPane().add(pieChart);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(275, 11, 109, 182);
		getContentPane().add(panel_1);
		
		systemBtn = new JRadioButton("Systems");
		systemBtn.updateUI();
		systemBtn.setSelected(true);
		systemBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(killList != null){
					pieChart.paint(pieChart.getGraphics() ,killStats.Stats.getSystems(killList));
				}
			}
		});
		buttonGroup.add(systemBtn);
		panel_1.add(systemBtn);
		
		shipBtn = new JRadioButton("Ships");
		shipBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(killList != null){
					pieChart.paint(pieChart.getGraphics() ,killStats.Stats.getShipTypes(killList, character));
				}
			}
		});
		buttonGroup.add(shipBtn);
		panel_1.add(shipBtn);
		
		weaponBtn = new JRadioButton("Weapons");
		weaponBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(killList != null){
					pieChart.paint(pieChart.getGraphics() ,killStats.Stats.getWeaponTypes(killList, character));
				}
			}
		});
		buttonGroup.add(weaponBtn);
		panel_1.add(weaponBtn);
		
		lblDownloading = new JLabel();
		lblDownloading.setBounds(10, 287, 125, 14);
		lblDownloading.setVisible(true);
		getContentPane().add(lblDownloading);
		
		setVisible(true);

	}
}

