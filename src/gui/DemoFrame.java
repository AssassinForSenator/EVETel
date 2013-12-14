package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;


@SuppressWarnings("serial")
public class DemoFrame extends JFrame {
	private JTextField textField;

	public DemoFrame() {
		setTitle("TRGSA Intel demo by Xen 'n Exo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(700, 350));
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 37, 165, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Weapons");
		rdbtnNewRadioButton.setBounds(279, 10, 109, 23);
		getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Ships");
		rdbtnNewRadioButton_1.setBounds(279, 36, 109, 23);
		getContentPane().add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Systems");
		rdbtnNewRadioButton_2.setBounds(279, 62, 109, 23);
		getContentPane().add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_3.setBounds(279, 88, 109, 23);
		getContentPane().add(rdbtnNewRadioButton_3);
		
		JButton btnGo = new JButton("Go!");
		btnGo.setBounds(86, 62, 89, 23);
		getContentPane().add(btnGo);
		
		JLabel lblNewLabel = new JLabel("Enter Player/Corp/alliance name");
		lblNewLabel.setBounds(10, 12, 165, 14);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new PieChart();
		panel.setBounds(394, 10, 280, 291);
		panel.setVisible(true);
		getContentPane().add(panel);

	}
}

