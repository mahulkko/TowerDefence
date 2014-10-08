package de.htwg.towerdefence.model.impl;

import org.apache.log4j.Logger;
import de.htwg.towerdefence.gameSettings.GameSettings;
import de.htwg.towerdefence.model.IGameContext;
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
    private static Logger log = Logger.getLogger("TowerDefence.Model.Mob");
    
    /** Current health of the mob */
	private int health;	
	
	/** Current posx of the mob */
	private int posX;	
	
	/** Current posy of the mob */
	private int posY;	
	
	/** Speed of the mob */
	private int speed;
	
	/** Current tmpSpeed of the mob */
	private long tmpSpeed;
	
	/** Context of the game */
	private IGameContext gameContext;
	

	/************************************************************
	 * Public constructor
	 ***********************************************************/
	
	/**
	 * Default constructor - initialize a mob with the default values
	 */
	public Mob(IGameContext gameContext, int posX, int posY) {
		this.speed = GameSettings.getMobSpeed();
		this.health = GameSettings.getMobHealth();
		this.posX = posX;
		this.posY = posY;
		this.gameContext = gameContext;
		this.tmpSpeed = this.speed;
		log.info("Added new mob with default values from GameSettings");
	}
	
	/**
	 * <b>Mob constructor - initialize a mob with the given values</b>
	 * @param health - Health of the mob
	 * @param speed - speed of the mob
	 */
	public Mob(IGameContext gameContext, int posX, int posY, int health, int speed) {
		this.health = health;
		this.speed = speed;
		this.posX = posX;
		this.posY = posY;
		this.gameContext = gameContext;
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
			if (this.posX < this.gameContext.getPlayingField().getSizeX()-1) {
				log.info("long dt: " + dt);
				gameContext.getPlayingField().deleteMob(posX, posY, this);
				this.posX++;
				gameContext.getPlayingField().setMob(posX, posY, this);
				this.tmpSpeed = this.speed;
				return true;
			}
		}
		return false;
	}
}
