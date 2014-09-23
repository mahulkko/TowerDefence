package de.htwg.towerdefence.model.impl;

import org.apache.log4j.Logger;
import de.htwg.towerdefence.gameSettings.GameSettings;
import de.htwg.towerdefence.model.IMob;
import de.htwg.towerdefence.util.control.impl.ControllableComponent;

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
    private static Logger log = Logger.getLogger("TowerDefence.Model.Tower");
    
    /** Current health of the mob */
	private int health;	
	
	/** Speed of the mob */
	private int speed;
	
	
	/************************************************************
	 * Public constructor
	 ***********************************************************/
	
	/**
	 * Default constructor - initialize a mob with the default values
	 */
	public Mob() {
		this.speed = GameSettings.getMobSpeed();
		this.health = GameSettings.getMobHealth();
		log.info("Added new mob with default values from GameSettings");
	}
	
	/**
	 * <b>Mob constructor - initialize a mob with the given values</b>
	 * @param health - Health of the mob
	 * @param speed - speed of the mob
	 */
	public Mob(int health, int speed) {
		this.health = health;
		this.speed = speed;
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
	public void update(long dt) {
		// TODO Auto-generated method stub
	}
}
