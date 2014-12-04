package de.htwg.towerdefence.gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import de.htwg.towerdefence.controller.IGameController;
import de.htwg.towerdefence.gui.GuiState.State;

public class PropertiesGui extends JPanel{

	private static final long serialVersionUID = -4061801421456135727L;
	
	/** Instance of the GuiState */
	private GuiState guiState;
	private IGameController controller;
	JLabel lblProperties;
	JLabel lblSpeed;
	JLabel lblDamage;
	JLabel lblRange;
	JLabel lblCost;
	JButton btnUpgrade;
	
	public PropertiesGui(IGameController controller, GuiState guiState) {
		this.controller = controller;
		this.guiState = guiState;
		init();
		this.setPropertiesCost(200);
		this.setPropertiesDamage(100);
		this.setPropertiesRange(3);
		this.setPropertiesSpeed(500);
		this.setPropertiesText("Eigenschaften Tower");
	}
	
	public void setPropertiesText(String properties) {
		this.lblProperties.setText(properties);
	}
	
	public void setPropertiesSpeed(int speed) {
		this.lblSpeed.setText("Speed: " + String.valueOf(speed));
	}
	
	public void setPropertiesDamage(int damage) {
		this.lblDamage.setText("Damage: " + String.valueOf(damage));
	}
	
	public void setPropertiesRange(int range) {
		this.lblRange.setText("Range: " + String.valueOf(range));
	}
	
	public void setPropertiesCost(int cost) {
		this.lblCost.setText("Cost: " + String.valueOf(cost));
	}
	
	private void init() {
		lblProperties = new JLabel();
		lblProperties.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblProperties.setBounds(10, 11, 171, 23);
		add(lblProperties);
		
		lblSpeed = new JLabel();
		lblSpeed.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSpeed.setBounds(20, 36, 179, 23);
		add(lblSpeed);
		
		lblDamage = new JLabel();
		lblDamage.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDamage.setBounds(20, 61, 179, 23);
		add(lblDamage);
		
		lblRange = new JLabel();
		lblRange.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRange.setBounds(20, 86, 179, 23);
		add(lblRange);
		
		lblCost = new JLabel();
		lblCost.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCost.setBounds(10, 283, 90, 14);
		add(lblCost);
		
		btnUpgrade = new JButton("Upgrade");
		btnUpgrade.setBounds(110, 279, 89, 23);
		btnUpgrade.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guiState.setState(State.UPGRADETOWER);
			}
		});
		add(btnUpgrade);
	}
}
