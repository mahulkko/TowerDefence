package de.htwg.towerdefence.gui;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.htwg.towerdefence.controller.IGameController;

public class PropertiesGui extends JPanel{

	private static final long serialVersionUID = -4061801421456135727L;
	private IGameController controller;
	
	public PropertiesGui(IGameController controller) {
		this.controller = controller;
		
		JLabel lblEigenschaften = new JLabel("Eigenschaften");
		lblEigenschaften.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEigenschaften.setBounds(10, 11, 171, 23);
		add(lblEigenschaften);
		
		JLabel lblSpeed = new JLabel("Speed: 500");
		lblSpeed.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSpeed.setBounds(20, 36, 179, 23);
		add(lblSpeed);
		
		JLabel lblDamage = new JLabel("Damage: 200");
		lblDamage.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDamage.setBounds(20, 61, 179, 23);
		add(lblDamage);
		
		JLabel lblRange = new JLabel("Range: 3");
		lblRange.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRange.setBounds(20, 86, 179, 23);
		add(lblRange);
		
		JButton btnUpgrade = new JButton("Upgrade");
		btnUpgrade.setBounds(110, 279, 89, 23);
		add(btnUpgrade);
		
		JLabel lblKostenTd = new JLabel("Kosten: 500 TD");
		lblKostenTd.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblKostenTd.setBounds(10, 283, 90, 14);
		add(lblKostenTd);
	}
}
