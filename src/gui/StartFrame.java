package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import dataStructures.Kill;


@SuppressWarnings("serial")
public class StartFrame extends JFrame {
	private final JTextField txtCharId;
	private final JTextField txtParticipant;

	public StartFrame() {
		setTitle("TRGSA Intel v0.1 by Xen 'n Exo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(700, 350));

		JLabel lblCharidOfVictim = new JLabel("CharID of Victim");

		txtCharId = new JTextField();
		txtCharId.setColumns(10);

		JLabel lblParticpant = new JLabel("Particpant");

		txtParticipant = new JTextField();
		txtParticipant.setColumns(10);

		ButtonHandler BH = new ButtonHandler();

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(BH);

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblCharidOfVictim)
														.addComponent(
																lblParticpant))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																txtParticipant,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				txtCharId,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(105)
																		.addComponent(
																				btnSearch)))
										.addContainerGap(607, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblCharidOfVictim)
														.addComponent(
																txtCharId,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(btnSearch))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblParticpant)
														.addComponent(
																txtParticipant,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(611, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);

		setVisible(true);
	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			String character = txtCharId.getText(); // Changed due to found name to ID conversion.
			String participant = txtParticipant.getText();
			
			ArrayList<Kill> List = killStats.Stats.compaireCharacter(api.KillBoard.getKillMails(api.Eve.getCharacterID(character).getInteger()), participant);
			System.out.println(List.size());
		}

	}

}
