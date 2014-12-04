package de.htwg.towerdefence.gui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import de.htwg.towerdefence.controller.IGameController;

/**
 * <b>PlayerGui - Player information for the Gui</b>
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public class PlayerGui extends JPanel{

	/************************************************************
	 * Private variables
	 ***********************************************************/
	
	/** Serial Version UID */
	private static final long serialVersionUID = -4061801421456135727L;
	
	/** Instance of the GameController */
	private IGameController controller;
	
	/** Label Player */
	private JLabel lblPlayer;
	
	/** Label Life */
	private JLabel lblLife;
	
	/** Label Money */
	private JLabel lblMoneyTd;
	
	
	/************************************************************
	 * Public constructor
	 ***********************************************************/

	/**
	 * Default constructor of the player Gui
	 * @param controller - Current GameController
	 */
	public PlayerGui(IGameController controller) {
		this.controller = controller;
		init();
	}
	
	
	/************************************************************
	 * Public Methods
	 ***********************************************************/
	
	/**
	 * Set Player Name in the GUI
	 * @param playerName - Name of the player
	 */
	public void setPlayerName(String playerName) {
		controller.setPlayerName(playerName);
		lblPlayer.setText("Player: " + playerName);
	}
	
	/**
	 * Set Player Life in the GUI
	 * @param playerLife - Life of the player
	 */
	public void setPlayerLife(int playerLife) {
		controller.setPlayerLife(playerLife);
		lblLife.setText("Life: " + String.valueOf(playerLife));
	}
	
	/**
	 * Set Player Money in the GUI
	 * @param playerMoney - Money of the player
	 */
	public void setPlayerMoney(int playerMoney) {
		controller.setPlayerMoney(playerMoney);
		lblMoneyTd.setText("Money: " + String.valueOf(playerMoney));
	}
	
	public void updatePlayerData() {
		lblPlayer.setText("Player: " + controller.getPlayerName());
		lblLife.setText("Life: " + String.valueOf(controller.getPlayerLife()));
		lblMoneyTd.setText("Money: " + String.valueOf(controller.getPlayerMoney()));
		if(controller.getPlayerLife() == 0) {
			controller.pauseOrStartGame();
			JOptionPane.showMessageDialog(null, "Game Over !!!");
		}
	}
	
	
	/************************************************************
	 * Private Methods
	 ***********************************************************/
	
	/**
	 * Initials the Label for the Player
	 */
	private void init() {
		lblPlayer = new JLabel();
		lblPlayer.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPlayer.setBounds(10, 11, 189, 22);
		lblPlayer.setText("Player: " + controller.getPlayerName());
		add(lblPlayer);
		
		lblLife = new JLabel();
		lblLife.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLife.setBounds(10, 27, 189, 22);
		lblLife.setText("Life: " + String.valueOf(controller.getPlayerLife()));
		add(lblLife);
		
		lblMoneyTd = new JLabel();
		lblMoneyTd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMoneyTd.setBounds(10, 44, 189, 22);
		lblMoneyTd.setText("Money: " + String.valueOf(controller.getPlayerMoney()));
		add(lblMoneyTd);
	}
}
