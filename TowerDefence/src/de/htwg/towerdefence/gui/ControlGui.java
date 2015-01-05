package de.htwg.towerdefence.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import de.htwg.towerdefence.controller.IGameController;
import de.htwg.towerdefence.gui.GuiState.State;

/**
 * <b>ControlGui - Control Buttons for the Gui</b>
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public class ControlGui extends JPanel {
	
	/************************************************************
	 * Private variables
	 ***********************************************************/

	/** Serial Version UID */
	private static final long serialVersionUID = -4061801421456135727L;
	
	/** Instance of the GameController */
	private IGameController controller;
	
	/** Instance of the GuiState */
	private GuiState guiState;
	
	/** JButton for SendMob */
	private JButton btnSendMob;
	
	/** JButton for Tower */
	private JButton btnTower;
	
	/** JButton for Pause */
	private JButton btnPause;
	
	/** JButton for Pause */
	private JButton btnInfo;
	
	
	/************************************************************
	 * Public constructor
	 ***********************************************************/
	
	/**
	 * Default constructor of the controllGui
	 * @param controller - Current GameController
	 * @param guiState 
	 */
	public ControlGui(IGameController controller, GuiState guiState) {
		this.controller = controller;
		this.guiState = guiState;
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
		btnSendMob.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.sendNewMobFromStart();
			}
		});
		add(btnSendMob);
		
		btnTower = new JButton("Set Tower");
		btnTower.setBounds(10, 11, 107, 23);
		btnTower.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guiState.setState(State.SETTOWER);
			}
		});
		add(btnTower);
		
		btnPause = new JButton("Pause");
		btnPause.setBounds(352, 11, 89, 23);
		btnPause.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.pauseOrStartGame();
			}
		});
		add(btnPause);
		
		
		btnInfo = new JButton("Show Info");
		btnInfo.setBounds(352, 45, 89, 23);
		btnInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				guiState.setState(State.SHOWINFOS);
			}
		});
		add(btnInfo);
	}
}
