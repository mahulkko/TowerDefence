package de.htwg.towerdefence.controller;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;

import de.htwg.towerdefence.util.control.IObserver;
import de.htwg.towerdefence.util.enums.FieldType;
import de.htwg.towerdefence.util.way.Coord;

/**
 * @author Christoph Knetschke and Martin Hulkkonen
 * <br>
 * <b>Interface of the GameController</b>
 */
public interface IGameController {
	
	JsonNode createNewGame();
	
	JsonNode createNewGame(String playerName, int life, int money, String email, int playingfieldSizeX,  int playingfieldSizeY);
	
	// Wui 
	
	/**
	 * @param currentGameContext - get the current GameContext of a WUI
	 * @return Returns the new GameContext as JsonString
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	JsonNode updateGameContext(String currentGameContext) throws JsonProcessingException, IOException;
	
	/**
	 * Stop or Starts the Game
	 * @param currentGameContext - get the current GameContext of a WUI
	 * @return Returns the new GameContext as JsonString
	 */
	JsonNode pauseOrStartGame(String currentGameContext);
	
	/**
	 * @param currentGameContext - get the current GameContext of a WUI
	 * @param x - the X position of new Tower on the Playingfield
	 * @param y - the Y position of new Tower on the Playingfield
	 * @return Returns the new GameContext as JsonString
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	JsonNode setTowerToPostion(String currentGameContext, int x, int y) throws JsonProcessingException, IOException;
	
	/**
	 * Creates a new Mob on the start of the Playingfield
	 * @param currentGameContext - get the current GameContext of a WUI
	 * @return Returns the new GameContext as JsonString
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	JsonNode sendNewMobFromStart(String currentGameContext) throws JsonProcessingException, IOException;
	
	/**
	 * @param x - the X position of specific Tower on the playingfield
	 * @param y - the Y position of specific Tower on the playingfield
	 */
	JsonNode upgradeTower(String currentGameContext, int x, int y) throws JsonProcessingException, IOException;
	
	
	// Gui and tui
	/**
	 * @param s - add a View Object that implements IObserver to the Controller
	 */
	void addObserver(IObserver s);
	
	/**
	 * @param s - removes a View Object that implements IObserver from the Controller
	 */
	void removeObserver(IObserver s);
	
	/**
	 * Stop or Starts the Game
	 * @return
	 */
	boolean pauseOrStartGame();
	
	void updateGameContext();
	
	// PlayingField
	/**
	 * @return Returns the X length of the current Playingfield
	 */
	int getSizeXOfPlayingField();
	
	/**
	 * @return Returns the Y length of the current Playingfield
	 */
	int getSizeYOfPlayingField();
	
	/**
	 * @param coord - coordinate of a specific field 
	 * @return Returns the FieldType of the field. Is there a tower or a mob
	 */
	FieldType getTypeOfPlayingField(Coord coord);
	
	/**
	 * @param x - the X position of specific field on the playingfield
	 * @param y - the Y position of specific field on the playingfield
	 */
	boolean isTowerOnField(int x, int y);
	
	/**
	 * @param x - the X position of specific field on the playingfield
	 * @param y - the Y position of specific field on the playingfield
	 */
	boolean isMobOnField(int x, int y);
	
	/**
	 * @param x - the X position of new Tower on the Playingfield
	 * @param y - the Y position of new Tower on the Playingfield
	 * @return Return true if the tower was set else false
	 */
	boolean setTowerToPostion(int x, int y);
	
	/**
	 * Creates a new Mob on the start of the Playingfield
	 */
	void sendNewMobFromStart();
	
	
	// Player
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
	 * @return Returns the name of the current Player
	 */
	String getPlayerName();
	
	/**
	 * @return Returns the current Money of the Player
	 */
	int getPlayerMoney();
	
	/**
	 * @return Returns the current Life of the Player
	 */
	int getPlayerLife();
	
	
	// Tower
	/**
	 * @param x - the X position of specific Tower on the playingfield
	 * @param y - the Y position of specific Tower on the playingfield
	 * @return Returns the Speed of the Tower
	 */
	int getTowerSpeed(int x, int y);
	
	/**
	 * @param x - the X position of specific Tower on the playingfield
	 * @param y - the Y position of specific Tower on the playingfield
	 * @return Returns the Range of the Tower
	 */
	int getTowerRange(int x, int y);
	
	/**
	 * @param x - the X position of specific Tower on the playingfield
	 * @param y - the Y position of specific Tower on the playingfield
	 * @return Returns the Damage of the Tower
	 */
	int getTowerDamage(int x, int y);
	
	/**
	 * @param x - the X position of specific Tower on the playingfield
	 * @param y - the Y position of specific Tower on the playingfield
	 * @return Returns the Coast of the Tower
	 */
	int getTowerCoast(int x, int y);
	
	/**
	 * @param x - the X position of specific Tower on the playingfield
	 * @param y - the Y position of specific Tower on the playingfield
	 */
	void upgradeTower(int x, int y);
	
	// Mob
	/**
	 * @param x - the X position of specific Mob on the playingfield
	 * @param y - the Y position of specific Mob on the playingfield
	 * @return Returns the health of the Mob
	 */
	int getMobHealth(int x, int y);
	
	/**
	 * @param x - the X position of specific Mob on the playingfield
	 * @param y - the Y position of specific Mob on the playingfield
	 * @return Returns the speed of the Mob
	 */
	int getMobSpeed(int x, int y);
	
	/**
	 * @param x - the X position of specific Mob on the playingfield
	 * @param y - the Y position of specific Mob on the playingfield
	 * @return Returns the money of the Mob
	 */
	int getMobMoney(int x, int y);
}
