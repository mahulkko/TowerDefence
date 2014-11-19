package de.htwg.towerdefence.controller;

import de.htwg.towerdefence.util.control.IObserver;
import de.htwg.towerdefence.util.enums.FieldType;
import de.htwg.towerdefence.util.way.Coord;

/**
 * @author Christoph Knetschke and Martin Hulkkonen
 * <br>
 * <b>Interface of the GameController</b>
 */
public interface IGameController {
	
	// PlayingField
	FieldType getTypeOfPlayingField(Coord coord);
	
	/**
	 * @return Returns the X length of the current Playingfield
	 */
	int getSizeXOfPlayingField();
	
	/**
	 * @return Returns the Y length of the current Playingfield
	 */
	int getSizeYOfPlayingField();
	
	/**
	 * @param playerName - the new name of the Player
	 * @return Returns the name of the current Player
	 */
	String setPlayerName(String playerName);
	
	/**
	 * @param playerMoney - the new Money value of the Player
	 * @return Returns the current Money of the Player
	 */
	int setPlayerMoney(int playerMoney);
	
	/**
	 * @param playerLife - the new Life value of the Player
	 * @return Returns the current Life of the Player
	 */
	int setPlayerLife(int playerLife);
	
	/**
	 * @param x - the X position of new Tower on the Playingfield
	 * @param y - the Y position of new Tower on the Playingfield
	 */
	void setTowerToPostion(int x, int y);
	
	/**
	 * Creates a new Mob on the start of the Playingfield
	 */
	void sendNewMobFromStart();
	
	// Gui and tui
	/**
	 * @param s - add a View Object that implements IObserver to the Controller
	 */
	void addObserver(IObserver s);
	
	/**
	 * @param s - removes a View Object that implements IObserver from the Controller
	 */
	void removeObserver(IObserver s);
}
