package de.htwg.towerdefence.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.htwg.towerdefence.controller.IGameController;

public class PropertiesGui extends JPanel{

	private static final long serialVersionUID = -4061801421456135727L;
	
	/** Instance of the GuiState */
	private IGameController controller;
	JLabel lblProperties;
	JLabel lblAttribute1;
	JLabel lblAttribute2;
	JLabel lblAttribute3;
	JLabel lblCost;
	JButton btnUpgrade;
	
	private int xCord;
	private int yCord;
	
	public PropertiesGui(IGameController controller, GuiState guiState) {
		this.controller = controller;
		init();
	}
	
	private void init() {
		lblProperties = new JLabel();
		lblProperties.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProperties.setBounds(10, 11, 171, 23);
		lblProperties.setText("Eigenschaften:");
		add(lblProperties);
		
		lblAttribute1 = new JLabel();
		lblAttribute1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAttribute1.setBounds(20, 36, 179, 23);
		add(lblAttribute1);
		
		lblAttribute2 = new JLabel();
		lblAttribute2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAttribute2.setBounds(20, 61, 179, 23);
		add(lblAttribute2);
		
		lblAttribute3 = new JLabel();
		lblAttribute3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAttribute3.setBounds(20, 86, 179, 23);
		add(lblAttribute3);
		
		lblCost = new JLabel();
		lblCost.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCost.setBounds(10, 283, 90, 14);
		add(lblCost);
		
		btnUpgrade = new JButton("Upgrade");
		btnUpgrade.setBounds(110, 279, 89, 23);
		btnUpgrade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.upgradeTower(xCord, yCord);
				updateTower(xCord, yCord);
			}
		});
		add(btnUpgrade);
	}
	
	public void updateTower(int x, int y) {
		this.xCord = x;
		this.yCord = y;
		lblProperties.setText("Tower Eigenschaften:");
		lblAttribute1.setText("Speed: " + String.valueOf(controller.getTowerSpeed(x, y)));
		lblAttribute2.setText("Damage: " + String.valueOf(controller.getTowerDamage(x, y)));
		lblAttribute3.setText("Range: " + String.valueOf(controller.getTowerRange(x, y)));
		lblCost.setText("Cost: " + String.valueOf(controller.getTowerCoast(x, y)));
	}
	
	public void updateMob(int x, int y) {
		lblProperties.setText("Mob Eigenschaften:");
		lblAttribute1.setText("Health: " + String.valueOf(controller.getMobHealth(x, y)));
		lblAttribute2.setText("Speed: " + String.valueOf(controller.getMobSpeed(x, y)));
		lblAttribute3.setText("Money: " + String.valueOf(controller.getMobMoney(x, y)));
		lblCost.setText("");
	}
}
