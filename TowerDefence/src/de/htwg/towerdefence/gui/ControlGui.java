package de.htwg.towerdefence.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.htwg.towerdefence.controller.IGameController;

public class ControlGui extends JPanel{

	private static final long serialVersionUID = -4061801421456135727L;
	private IGameController controller;
	
	public ControlGui(IGameController controller) {
		this.controller = controller;
		
		JButton btnSendMob = new JButton("Send Mob");
		btnSendMob.setBounds(10, 45, 107, 23);
		add(btnSendMob);
		
		JButton btnTower = new JButton("Set Tower");
		btnTower.setBounds(10, 11, 107, 23);
		add(btnTower);
		
		JButton btnPause = new JButton("Pause");
		btnPause.setBounds(352, 11, 89, 23);
		add(btnPause);
	}
}
