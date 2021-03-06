package de.htwg.towerdefence.model.impl;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import de.htwg.towerdefence.gameSettings.GameSettings;
import de.htwg.towerdefence.model.IPlayer;
import de.htwg.towerdefence.util.control.impl.ControllableComponent;

/**
 * <b>Player Class</b>
 * <br>
 * Implements IPlayer Interface
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public class Player extends ControllableComponent implements IPlayer {

	/************************************************************
	 * Private variables
	 ***********************************************************/
	
	/** Name of the player */
	private String name;
	
	/** Money of the player */
	private int money;
	
	/** Life of the player */
	private int life;
	
	/** Email adress of the player */
	private String email;
	
	/** Logger for log4j connection */
    private static final Logger LOG = Logger.getLogger("TowerDefence.Model.Player");
	
    
    /************************************************************
	 * Public constructor
	 ***********************************************************/
    
    /**
     * Constructor Player - Uses the default configuration from the GameSettings
     */
	public Player() {
		this.name = GameSettings.getPlayerName(); 
		this.life = GameSettings.getPlayerLife();
		this.money = GameSettings.getPlayerMoney();
		this.email = "";
		LOG.info("Added new Player with default values from GameSettings");
	}
	
	/**
	 * <b>Constructor Player - For other configuration</b>
	 * @param playerName - Name of the player
	 * @param life - Life of the player
	 * @param money - Money of the player 
	 */
	public Player(String playerName, int life, int money, String email) {
		this.name = playerName;
		this.life = life;
		this.money = money;
		this.email = email;
		LOG.info("Added new Player with name: " + this.name + " | Life: " + this.life + " | Money: " + this.money + " | Email: " + this.email);
	}
	
	
	/************************************************************
	 * Public methods
	 ***********************************************************/
	
	@Override
	/**
	 * @return Returns the name of the player
	 */
	public String getName() {
		GameSettings.getPlayerName();
		return this.name;
	}
	
	@Override
	/**
	 * @param name - Set the name of the player
	 */
	public void setName(String name) {
		LOG.info("Set Player: "+ this.name + " to " + name);
		this.name = name;
	}
	
	@Override
	/**
	 * @return Returns the money of the player
	 */
	public int getMoney() {
		return this.money;
	}
	
	@Override
	/**
	 * @param money - Set the money of the player
	 */
	public void setMoney(int money) {
		LOG.info("Set Money: "+ this.money + " to " + money + " | Player: " + this.name);
		this.money = money;
	}
	
	@Override
	/**
	 * @return Returns the current life of the player
	 */
	public int getLife() {
		return this.life;
	}
	
	@Override
	/**
	 * @param life - Set the life of the player
	 */
	public void setLife(int life) {
		LOG.info("Set Life: "+ this.life + " to " + life + " | Player: " + this.name);
		this.life = life;
	}
	
	@Override
	public String getEmailAdress() {
		return this.email;
	}

	@Override
	public void setEmailAdress(String email) {
		LOG.info("Set Email: "+ this.email + " to " + email + " | Player: " + this.name);
		this.email = email;
	}

	
	/************************************************************
	 * Public ControllableComponent methods
	 ***********************************************************/
	
	@Override
	public boolean update(long dt) {
		return false;
		// TODO Auto-generated method stub
	}
	
	
	/************************************************************
	 * Public Serialize methods
	 ***********************************************************/
	
	@Override
	public JsonNode serialize() {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.createObjectNode();
		
		((ObjectNode)root).put("name", name);
		((ObjectNode)root).put("money", money);
		((ObjectNode)root).put("life", life);
		((ObjectNode)root).put("email", email);
		return root;
	}

	@Override
	public void deserialize(JsonNode node) {
		
		JsonNode nameNode = node.path("name");
		JsonNode moneyNode = node.path("money");
		JsonNode lifeNode = node.path("life");
		JsonNode emailNode = node.path("email");
		
		this.name = nameNode.getTextValue();
		this.money = moneyNode.getIntValue();
		this.life = lifeNode.getIntValue();
		this.email = emailNode.asText();
	}
}
