package de.htwg.towerdefence.gui;

import javax.swing.JButton;
import javax.swing.JPanel;
import de.htwg.towerdefence.controller.IGameController;

/**
 * <b>ControlGui - Control Buttons for the Gui</b>
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public class ControlGui extends JPanel{
	
	/************************************************************
	 * Private variables
	 ***********************************************************/

	/** Serial Version UID */
	private static final long serialVersionUID = -4061801421456135727L;
	
	/** Instance of the GameController */
	private IGameController controller;
	
	/** JButton for SendMob */
	private JButton btnSendMob;
	
	/** JButton for Tower */
	private JButton btnTower;
	
	/** JButton for Pause */
	private JButton btnPause;
	
	
	/************************************************************
	 * Public constructor
	 ***********************************************************/
	
	/**
	 * Default constructor of the controllGui
	 * @param controller - Current GameController
	 */
	public ControlGui(IGameController controller) {
		this.controller = controller;
		init();
	}
	
	
	/************************************************************
	 * Private Methods
	 ***********************************************************/
	
	/**
	 * Initials the Buttons for the Control
	 */
	private void init() {
		btnSendMob = new JButton("Send Mob");
		btnSendMob.setBounds(10, 45, 107, 23);
		add(btnSendMob);
		
		btnTower = new JButton("Set Tower");
		btnTower.setBounds(10, 11, 107, 23);
		add(btnTower);
		
		btnPause = new JButton("Pause");
		btnPause.setBounds(352, 11, 89, 23);
		add(btnPause);
	}
}
