package de.htwg.towerdefence.model.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.map.ObjectMapper;

import de.htwg.towerdefence.gameSettings.GameSettings;
import de.htwg.towerdefence.model.IMob;
import de.htwg.towerdefence.util.GameContext.GameContext;
import de.htwg.towerdefence.util.GameContext.GameData;
import de.htwg.towerdefence.util.control.IControllableComponent;
import de.htwg.towerdefence.util.control.impl.ControllableComponent;
import de.htwg.towerdefence.util.way.Coord;

/**
 * <b>Mob Class</b>
 * <br>
 * Implements IMob Interface
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public class Mob extends ControllableComponent implements IMob {
	
	/************************************************************
	 * Private variables
	 ***********************************************************/
	
	/** Logger for log4j connection */
    private static Logger log = Logger.getLogger("TowerDefence.Model.Mob");
    
    /** Current health of the mob */
	private int health;	
	
	/** Current position of the mob */
	Coord currendPos;
	
	/** Speed of the mob */
	private int speed;
	
	/** Current tmpSpeed of the mob */
	private long tmpSpeed;
	
	/** Money the mob get*/
	private int money;
	

	/************************************************************
	 * Public constructor
	 ***********************************************************/
	
	/**
	 * Default constructor - initialize a mob with the default values
	 */
	public Mob() {
		this.speed = GameSettings.getMobSpeed();
		this.health = GameSettings.getMobHealth();
		this.money = GameSettings.getMobMoney();
		this.currendPos = new Coord(0,0);
		this.tmpSpeed = this.speed;
		log.info("Added new mob with default values from GameSettings");
	}
	
	/**
	 * Default constructor - initialize a mob with the default values
	 */
	public Mob(Coord position) {
		this.speed = GameSettings.getMobSpeed();
		this.health = GameSettings.getMobHealth();
		this.money = GameSettings.getMobMoney();
		this.currendPos = position;
		this.tmpSpeed = this.speed;
		log.info("Added new mob with default values from GameSettings");
	}
	
	/**
	 * <b>Mob constructor - initialize a mob with the given values</b>
	 * @param health - Health of the mob
	 * @param speed - speed of the mob
	 */
	public Mob(Coord position, int health, int speed, int money) {
		this.health = health;
		this.speed = speed;
		this.money = money;
		this.currendPos = position;
		this.tmpSpeed = this.speed;
		log.info("Added new mob with health: " + this.health + " | Speed: " + this.speed);
	}
	
	
	/************************************************************
	 * Public methods
	 ***********************************************************/
	
	@Override
	/**
	 * @return Returns the current health state of the mob
	 */
	public int getHealth() {
		return this.health;
	}
	
	@Override
	/**
	 * @param health - Set the health of the mob
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	@Override
	/**
	 * @return Returns the speed of the mob
	 */
	public int getSpeed() {
		return this.speed;
	}
	
	@Override
	/**
	 * @param speed - Set the speed of the mob
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/**
	 * @return Return the money that you get when the mob is dead
	 */
	public int getMoneyValue() {
		return money;
	}
	
	/**
	 * @param moneyValue - the money that the mob brings
	 */
	public void setMoneyValue(int moneyValue) {
		this.money = moneyValue;
	}
	
	@Override
	/**
	 * @return Returns true when the mob is dead
	 */
	public boolean isDead() {
		if (this.health <= 0) {
			return true;
		}
		return false;					
	}
	
	/************************************************************
	 * Public ControllableComponent methods
	 ***********************************************************/
	
	@Override
	public boolean update(long dt) {		
		
		this.tmpSpeed = this.tmpSpeed - dt;
		
		if (this.tmpSpeed <= 0) {
			GameContext.getCheckWay().existWay(this.currendPos.getX() , this.currendPos.getY(), GameContext.getPlayingField().getSizeX()-1, GameContext.getPlayingField().getSizeY()-1);
			List<Coord> way = GameContext.getCheckWay().getShortesWay();
			
			GameContext.getPlayingField().deleteMob(currendPos, this);
			GameContext.getPlayingField().setMob(way.get(1), this);
			this.currendPos.setX(way.get(1).getX());
			this.currendPos.setY(way.get(1).getY());
			// Check if Mob has reached end of Playingfield, delete him and lost life
			if (GameContext.getPlayingField().isMobAtEndOfPlayingfield(way.get(1), this)) {
				GameContext.getPlayingField().deleteMob(way.get(1), this);
				GameContext.getPlayer().setLife(GameContext.getPlayer().getLife() - 1);
				IControllableComponent component = (IControllableComponent)this;
				component.unregisterSelf();
			}
			
			this.tmpSpeed = this.speed;
			return true;
		}
		return false;
	}
	
	/************************************************************
	 * Public Serialize methods
	 ***********************************************************/		
	@Override
	public JsonNode serialize() {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.createObjectNode();
		
		((ObjectNode)root).put("health", health);
		((ObjectNode)root).put("speed", speed);
		((ObjectNode)root).put("tmpSpeed", tmpSpeed);
		((ObjectNode)root).put("coord", this.currendPos.serialize());
		((ObjectNode)root).put("money", money);
		
		if (GameContext.isComponentInGameData((IControllableComponent)this)) {
			((ObjectNode)root).put("controllable", true);
			((ObjectNode)root).put("lastTime", GameContext.getGameData((IControllableComponent)this).getLastTime());
		}
		return root;
	}

	@Override
	public void deserialize(JsonNode node) {
		
		JsonNode health = node.path("health");
		JsonNode speed = node.path("speed");
		JsonNode tmpSpeed = node.path("tmpSpeed");
		JsonNode coord = node.path("coord");
		JsonNode money = node.path("money");
		JsonNode controllable = node.path("controllable");
		
		this.health = health.getIntValue();
		this.speed = speed.getIntValue();
		this.tmpSpeed = tmpSpeed.getLongValue();
		this.currendPos.deserialize(coord);
		this.money = money.getIntValue();
		
		if (controllable.getBooleanValue()) {
			JsonNode lastTime = node.path("lastTime");
			GameData data = new GameData();
			data.setComponent((IControllableComponent)this);
			data.setLastTime(lastTime.asLong());
			GameContext.getGameData().add(data);
		}
	}
}
