package gui;

import java.awt.Dimension;

import javax.swing.*;

import dataStructures.Kill;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class DemoFrame extends JFrame {
	JTextField textField;
	PieChart pieChart = new PieChart();
	JLabel statusLabel;
	JList<String> list;
	Legend legend;
	
	ArrayList<Kill> killList;
	String character;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public DemoFrame() {
		setTitle("TRGSA Intel demo by Xen 'n Exo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(734, 382));
		getContentPane().setLayout(null);
		
		JTabbedPane statsScreen = new JTabbedPane(JTabbedPane.TOP);
		statsScreen.setBounds(0, 0, 718, 346);
		getContentPane().add(statsScreen);
		
		JPanel panel = new JPanel();
		statsScreen.addTab("Stats", null, panel, null);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(8, 36, 297, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnGo = new JButton("Go!");
		btnGo.setBounds(216, 68, 89, 23);
		panel.add(btnGo);
		pieChart.setBounds(423, 9, 280, 291);
		panel.add(pieChart);
		
		legend = new Legend();
		
		JScrollPane legendScrollPane = new JScrollPane(legend, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		legendScrollPane.setBounds(10, 102, 403, 198);
		panel.add(legendScrollPane);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(315, 9, 98, 81);
		panel.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		list = new JList<String>();
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(killList != null && killList.size() > 0){
					if(list.getSelectedValue().equalsIgnoreCase("Ships")){
						legend.update(pieChart.update(killStats.Stats.getShipTypes(killList, character), true));	
					} else if(list.getSelectedValue().equalsIgnoreCase("Weapons")){
						legend.update(pieChart.update(killStats.Stats.getWeaponTypes(killList, character), true));	
					} else if(list.getSelectedValue().equalsIgnoreCase("System")){
						legend.update(pieChart.update(killStats.Stats.getSystems(killList), true));	
					} else if(list.getSelectedValue().equalsIgnoreCase("TimeZone")){
						legend.update(pieChart.update(killStats.DemoStats.getTimeZone(killList), false));	
					} else if(list.getSelectedValue().equalsIgnoreCase("Dreadnought pilots")){
						//legend.update(pieChart.update(killStats.DemoStats.getDreadnoughtPilots(killList, character), false));	
					} else if(list.getSelectedValue().equalsIgnoreCase("Carrier pilots")){
						//legend.update(pieChart.update(killStats.DemoStats.getCarrierPilots(killList, character), false));	
					} else if(list.getSelectedValue().equalsIgnoreCase("SuperCarrier pilots")){
						//legend.update(pieChart.update(killStats.DemoStats.getSuperCarrierPilots(killList, character), false));	
					} else if(list.getSelectedValue().equalsIgnoreCase("Titan pilots")){
						//legend.update(pieChart.update(killStats.DemoStats.getTitanPilots(killList, character), false));
					} else if(list.getSelectedValue().equalsIgnoreCase("Logistic pilots")){
						//legend.update(pieChart.update(killStats.DemoStats.getLogisticsPilots(killList, character), false));
					}
				}
			}
		});
		scrollPane.setViewportView(list);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"System", "Ships", "Weapons", "TimeZone", "Recurring pilots", "--------------------"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectedIndex(0);
		
		statusLabel = new JLabel("");
		statusLabel.setBounds(8, 67, 208, 24);
		panel.add(statusLabel);
		
		JLabel textBoxLbl = new JLabel("Enter Player/Corp/alliance name");
		textBoxLbl.setBounds(10, -1, 241, 39);
		panel.add(textBoxLbl);
		
		JPanel panel_1 = new JPanel();
		statsScreen.addTab("Compairer", null, panel_1, null);
		panel_1.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 28, 157, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 76, 157, 20);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblC = new JLabel("Kill board to check");
		lblC.setBounds(10, 10, 157, 14);
		panel_1.add(lblC);
		
		JLabel lblNewLabel = new JLabel("What are you looking for");
		lblNewLabel.setBounds(10, 57, 157, 14);
		panel_1.add(lblNewLabel);
		
		JButton btnCompaire = new JButton("Compaire");
		btnCompaire.setBounds(177, 27, 89, 23);
		panel_1.add(btnCompaire);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(276, 10, 427, 297);
		panel_1.add(scrollPane_1);
		
		JList list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		pieChart.setVisible(true);
		btnGo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				character = textField.getText();
				
				statusLabel.setText("");
				statusLabel.update(statusLabel.getGraphics());
				
				statusLabel.setText("Downloading...");
				statusLabel.update(statusLabel.getGraphics());
				
				statusLabel.setText("Failed! name not found");
				killList = api.KillBoard.getKillMails(api.Eve.getEntityID(character));	
				
				statusLabel.setText("");
				statusLabel.update(statusLabel.getGraphics());
				
				if(list.getSelectedValue().equalsIgnoreCase("Ships")){
					legend.update(pieChart.update(killStats.Stats.getShipTypes(killList, character), true));	
				} else if(list.getSelectedValue().equalsIgnoreCase("Weapons")){
					legend.update(pieChart.update(killStats.Stats.getWeaponTypes(killList, character), true));	
				} else if(list.getSelectedValue().equalsIgnoreCase("System")){
					legend.update(pieChart.update(killStats.Stats.getSystems(killList), true));	
				} else if(list.getSelectedValue().equalsIgnoreCase("TimeZone")){
					legend.update(pieChart.update(killStats.DemoStats.getTimeZone(killList), false));	
				} else if(list.getSelectedValue().equalsIgnoreCase("Dreadnought pilots")){
					//legend.update(pieChart.update(killStats.DemoStats.getDreadnoughtPilots(killList, character), false));	
				} else if(list.getSelectedValue().equalsIgnoreCase("Carrier pilots")){
					//legend.update(pieChart.update(killStats.DemoStats.getCarrierPilots(killList, character), false));	
				} else if(list.getSelectedValue().equalsIgnoreCase("SuperCarrier pilots")){
					//legend.update(pieChart.update(killStats.DemoStats.getSuperCarrierPilots(killList, character), false));	
				} else if(list.getSelectedValue().equalsIgnoreCase("Titan pilots")){
					//legend.update(pieChart.update(killStats.DemoStats.getTitanPilots(killList, character), false));
				} else if(list.getSelectedValue().equalsIgnoreCase("Logistic pilots")){
					//legend.update(pieChart.update(killStats.DemoStats.getLogisticsPilots(killList, character), false));
				}
			}
		});
		
		/*JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 0, 0);
		getContentPane().add(scrollPane);*/
		
		setVisible(true);

	}
}

