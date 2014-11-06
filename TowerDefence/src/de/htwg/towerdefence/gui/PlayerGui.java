package de.htwg.towerdefence.gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.htwg.towerdefence.controller.IGameController;

public class PlayerGui extends JPanel{

	private static final long serialVersionUID = -4061801421456135727L;
	private IGameController controller;
	
	public PlayerGui(IGameController controller) {
		this.controller = controller;
		
		JLabel lblPlayerMartin = new JLabel("Player: Martin");
		lblPlayerMartin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPlayerMartin.setBounds(10, 11, 189, 22);
		add(lblPlayerMartin);
		
		JLabel lblLife = new JLabel("Life: 10");
		lblLife.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLife.setBounds(10, 27, 189, 22);
		add(lblLife);
		
		JLabel lblMoneyTd = new JLabel("Money: 1000 TD");
		lblMoneyTd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMoneyTd.setBounds(10, 44, 189, 22);
		add(lblMoneyTd);
	}
}
