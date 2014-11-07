package de.htwg.towerdefence.gui;

import java.awt.Font;
import javax.swing.JLabel;
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
		this.setPlayerName("Martin");
		this.setPlayerLife(10);
		this.setPlayerMoney(9999);
	}
	
	
	/************************************************************
	 * Public Methods
	 ***********************************************************/
	
	/**
	 * Set Player Name in the GUI
	 * @param playerName - Name of the player
	 */
	public void setPlayerName(String playerName) {
		lblPlayer.setText("Player: " + playerName);
	}
	
	/**
	 * Set Player Life in the GUI
	 * @param playerLife - Life of the player
	 */
	public void setPlayerLife(int playerLife) {
		lblLife.setText("Life: " + String.valueOf(playerLife));
	}
	
	/**
	 * Set Player Money in the GUI
	 * @param playerMoney - Money of the player
	 */
	public void setPlayerMoney(int playerMoney) {
		this.lblMoneyTd.setText("Money: " + String.valueOf(playerMoney));
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
		add(lblPlayer);
		
		lblLife = new JLabel();
		lblLife.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLife.setBounds(10, 27, 189, 22);
		add(lblLife);
		
		lblMoneyTd = new JLabel();
		lblMoneyTd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMoneyTd.setBounds(10, 44, 189, 22);
		add(lblMoneyTd);
	}
}
