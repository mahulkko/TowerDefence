package de.htwg.towerdefence.model.impl;

import org.apache.log4j.Logger;
import de.htwg.towerdefence.gameSettings.GameSettings;
import de.htwg.towerdefence.model.ITower;
import de.htwg.towerdefence.util.GameHelper;
import de.htwg.towerdefence.util.control.impl.ControllableComponent;

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

	/** Number of shoots from the tower. With this parameter the tower can deal splash damage on each round. */
	private int numberShoot;

	/** Hitrate of the tower. Hitrate is the change to deal a hit with max damage. */
	private double hitrate; 
	
	
	/************************************************************
	 * Public constructor
	 ***********************************************************/
	
	/**
	 * Default constructor - initialize a tower with the default values
	 */
	public Tower() {
		this.damage = GameSettings.getTowerDamage();
		this.range = GameSettings.getTowerRange();
		this.speed = GameSettings.getTowerSpeed();
		this.numberShoot = GameSettings.getTowerNumberOfShoot();
		this.hitrate = GameSettings.getTowerHitRate();
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
	public Tower(int damage, int range, int speed, int numberShoot, double hitrate) {
		this.damage = damage;
		this.range = range;
		this.speed = speed;
		this.numberShoot = numberShoot;
		this.hitrate = hitrate;
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
	
	
	/************************************************************
	 * Public ControllableComponent methods
	 ***********************************************************/
	
	@Override
	public boolean update(long dt) {
		return false;
		// TODO Auto-generated method stub
	}
}
