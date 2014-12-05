package de.htwg.towerdefence.model.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import de.htwg.towerdefence.gameSettings.GameSettings;
import de.htwg.towerdefence.model.IMob;
import de.htwg.towerdefence.model.ITower;
import de.htwg.towerdefence.util.GameHelper;
import de.htwg.towerdefence.util.GameContext.GameContext;
import de.htwg.towerdefence.util.control.IControllableComponent;
import de.htwg.towerdefence.util.control.impl.ControllableComponent;
import de.htwg.towerdefence.util.way.Coord;

/**
 * <b>Tower Class</b>
 * <br>
 * Implements ITower Interface
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public class Tower extends ControllableComponent implements ITower {
	
	/************************************************************
	 * Private Variables
	 ***********************************************************/
	
	/** 100 as a constant */
	private static final double HUNDRED = 100.0;

	/** 0.5 as a constant */
	private static final double HALF = 0.5;

	/** Logger for log4j connection */
    private static Logger log = Logger.getLogger("TowerDefence.Model.Tower");

	/** Damage of the tower */
	private int damage;

	/** Range of the tower */
	private int range;

	/** Speed of the tower */
	private int speed;
	
	/** Current tmpSpeed of the tower */
	private long tmpSpeed;

	/** Number of shoots from the tower. With this parameter the tower can deal splash damage on each round. */
	private int numberShoot;
	
	/** The coast to building one of the tower */
	private int cost;
	
	/** Position of the tower */
	private Coord position;
	
	/** Temporary Coord for request */
	private Coord tmpCoord;

	/** Hitrate of the tower. Hitrate is the change to deal a hit with max damage. */
	private double hitrate; 
	
	/** Init Tower **/
	boolean initTower;
	
	
	/************************************************************
	 * Public constructor
	 ***********************************************************/
	
	/**
	 * Default constructor - initialize a tower with the default values
	 */
	public Tower(Coord position) {
		this.damage = GameSettings.getTowerDamage();
		this.range = GameSettings.getTowerRange();
		this.speed = GameSettings.getTowerSpeed();
		this.numberShoot = GameSettings.getTowerNumberOfShoot();
		this.hitrate = GameSettings.getTowerHitRate();
		this.cost = GameSettings.getTowerCost();
		this.position = position;
		this.tmpCoord = new Coord();
		this.initTower = true;
		log.info("Added new tower with default values from GameSettings");
	}
	
	/**
	 * <b>Tower constructor - initialize a tower with the given values</b>
	 * @param damage - Damage of the tower
	 * @param range - Range of the tower 
	 * @param speed - Speed of the tower
	 * @param numberShoot - Number of shoot from the tower. With this parameter the tower can deal splash damage on each round.
	 * @param hitrate - Hitrate of the tower. Hitrate is the change to deal a hit with max damage.
	 */
	public Tower(Coord position, int damage, int range, int speed, int numberShoot, double hitrate, int cost) {
		this.damage = damage;
		this.range = range;
		this.speed = speed;
		this.numberShoot = numberShoot;
		this.hitrate = hitrate;
		this.cost = cost;
		this.position = position;
		this.tmpCoord = new Coord();
		log.info("Added new tower with damage: " + this.damage + " | Range: " + this.range + " | Speed: " 
				+ this.speed + " | Number of Shoot: " + this.numberShoot + " | Hitrate: " + this.hitrate);
	}
	
	
	/************************************************************
	 * Public methods
	 ***********************************************************/
	
	@Override
	/**
	 * @return Returns the max damage that the tower can make
	 */
    public int getDamage() {
    	return this.damage;
    }
    
	@Override
    /**
     * @param damage - Set the max damage that the tower can make
     */
    public void setDamage(int damage) {
    	this.damage = damage;
    }
    
	@Override
    /**
     * @return Returns the hitrate of the tower. Hitrate is the change to deal a hit with max damage.
     */
    public double getHitRate() {
    	return this.hitrate;
    }
    
	@Override
    /**
     * @param hitrate - Set the hitrate of the tower. Hitrate is the change to deal a hit with max damage.
     */
    public void setHitRate(double hitrate) {
    	this.hitrate = hitrate;
    }
    
	@Override
    /**
     * @return Returns the number of shoots. With this parameter the tower can deal splash damage on each round.
     */
    public int getNumberOfShoot() {
    	return this.numberShoot;
    }
    
	@Override
    /**
     * @param numbershoot - Set the number of shoots of the tower. With this parameter the tower can deal splash damage on each round.
     */
    public void setNumberOfShoot(int numbershoot) {
    	this.numberShoot = numbershoot;
    }
    
	@Override
    /**
     * @return Returns the shooting range of the tower
     */
    public int getRange() {
    	return this.range;
    }
    
	@Override
    /**
     * @param range - Set the shooting range of the tower
     */
    public void setRange(int range) {
    	this.range = range;
    }
    
	@Override
    /**
     * @return Returns the attacking speed of the tower
     */
    public int getSpeed() {
    	return this.speed;
    }
    
	@Override
    /**
     * @param speed - Set the attacking speed of the tower
     */
    public void setSpeed(int speed) {
    	this.speed = speed;
    }

	/**
     * @return Returns the Coast to building one tower
     */
	@Override
	public int getCost() {
		return this.cost;
	}

	/**
     * @param cost - Set the money that one tower coast
     */
	@Override
	public void setCost(int cost) {
		this.cost = cost;
	}
    
	@Override
    /**
     * @return Returns a calculated damage from all parameters
     */
    public int calcDamage() {
    	// Calculate a random integer number
    	int random = (int)(GameHelper.random(1.0, HUNDRED) + HALF);
    	
		// Random integer is bigger than the hitrate - so the tower has hit the target
		if (random <= this.hitrate * HUNDRED) {
			return this.damage;
			
		// Calculate the smaller damage of the tower
		} else {
			return (int)(this.damage * (random / HUNDRED));
		}
    }
	
	@Override
	public void upgrade() {
		this.damage = (int) (this.damage * 1.5);
		this.speed = (int) (this.speed * 1.2);
	}
	
	
	/************************************************************
	 * Public ControllableComponent methods
	 ***********************************************************/
	
	@Override
	public boolean update(long dt) {
		
		boolean check = false;
		
		if(this.initTower)  {
			this.initTower = false;
			check = true;
		}
			
		
		this.tmpSpeed = this.tmpSpeed - dt;
		if (this.tmpSpeed <= 0) {
			for(int i = 0; i < this.numberShoot; ++i) {
				if (shootOnMob())
				{
					check = true;
				}
			}
			this.tmpSpeed = this.speed;
			return check;
		}
		return false;
	}
	
	/************************************************************
	 * Private methods
	 ***********************************************************/
	
	/**
	 * Private Helper method
	 */
	private boolean shootOnMob() {
		for(int y = this.position.getY() - this.getRange(); y < this.position.getY() + this.getRange(); ++y) {
			for(int x = this.position.getX() - this.getRange(); x < this.position.getX() + this.getRange(); ++x) {
				
				if (x >= 0 && y >= 0) {
					this.tmpCoord.setX(x);
					this.tmpCoord.setY(y);
				}
				
				List<IMob> mobs = GameContext.getPlayingField().getMobs(this.tmpCoord);
				if (mobs != null && mobs.size() > 0) {
					if (mobs.get(0) != null) {
						mobs.get(0).setHealth(mobs.get(0).getHealth() - this.calcDamage());
						log.info("Found mob and reduced the health: Mob health are now " + mobs.get(0).getHealth() + "%");
						if (mobs.get(0).isDead()) {
							log.info("Mob is dead so delete it from the playingfield");
							GameContext.getPlayingField().deleteMob(this.tmpCoord, mobs.get(0));
							IControllableComponent component = (IControllableComponent)mobs.get(0);
							component.unregisterSelf();
							//  Player get Money for Mob
							GameContext.getPlayer().setMoney(GameContext.getPlayer().getMoney() + mobs.get(0).getMoneyValue());
						}
						return true;
					}
				}
			}
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
		
		((ObjectNode)root).put("damage", damage);
		((ObjectNode)root).put("range", range);
		((ObjectNode)root).put("tmpSpeed", tmpSpeed);
		((ObjectNode)root).put("speed", speed);
		((ObjectNode)root).put("numberShoot", numberShoot);
		((ObjectNode)root).put("cost", cost);
		((ObjectNode)root).put("position", position.serialize());
		((ObjectNode)root).put("tmpCoord", tmpCoord.serialize());
		((ObjectNode)root).put("hitrate", hitrate);
		
		return root;
	}

	@Override
	public void deserialize(JsonNode node) {
		
		JsonNode damage = node.path("damage");
		JsonNode range = node.path("range");
		JsonNode tmpSpeed = node.path("tmpSpeed");
		JsonNode speed = node.path("speed");
		JsonNode numberShoot = node.path("numberShoot");
		JsonNode cost = node.path("cost");
		JsonNode position = node.path("position");
		JsonNode tmpCoord = node.path("tmpCoord");
		JsonNode hitrate = node.path("hitrate");
		
		this.damage = damage.getIntValue();
		this.range = range.getIntValue();
		this.tmpSpeed = tmpSpeed.getLongValue();
		this.speed = speed.getIntValue();
		this.numberShoot = numberShoot.getIntValue();
		this.cost = cost.getIntValue();
		this.position.deserialize(position);
		this.tmpCoord.deserialize(tmpCoord);
		this.hitrate = hitrate.getDoubleValue();
		this.initTower = true;	
	}
}
