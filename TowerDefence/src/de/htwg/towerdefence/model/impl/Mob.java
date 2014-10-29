package de.htwg.towerdefence.model.impl;

import java.util.List;

import org.apache.log4j.Logger;

import de.htwg.towerdefence.gameSettings.GameSettings;
import de.htwg.towerdefence.model.IGameContext;
import de.htwg.towerdefence.model.IMob;
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
	
	/** Context of the game */
	private IGameContext gameContext;
	

	/************************************************************
	 * Public constructor
	 ***********************************************************/
	
	/**
	 * Default constructor - initialize a mob with the default values
	 */
	public Mob(IGameContext gameContext, Coord position) {
		this.speed = GameSettings.getMobSpeed();
		this.health = GameSettings.getMobHealth();
		this.currendPos = position;
		this.gameContext = gameContext;
		this.tmpSpeed = this.speed;
		log.info("Added new mob with default values from GameSettings");
	}
	
	/**
	 * <b>Mob constructor - initialize a mob with the given values</b>
	 * @param health - Health of the mob
	 * @param speed - speed of the mob
	 */
	public Mob(IGameContext gameContext, Coord position, int health, int speed) {
		this.health = health;
		this.speed = speed;
		this.currendPos = position;
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
			gameContext.getCheckWay().existWay(this.currendPos.getX() , this.currendPos.getY(), this.gameContext.getPlayingField().getSizeX()-1, this.gameContext.getPlayingField().getSizeY()-1);
			List<Coord> way = gameContext.getCheckWay().getShortesWay();
			
			gameContext.getPlayingField().deleteMob(currendPos, this);
			gameContext.getPlayingField().setMob(way.get(1), this);
			this.currendPos.setX(way.get(1).getX());
			this.currendPos.setY(way.get(1).getY());
			
			this.tmpSpeed = this.speed;
			return true;
		}
		return false;
	}
}
