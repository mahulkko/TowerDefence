package de.htwg.towerdefence.model;

/**
 * <b>Interface IMob</b>
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public interface IMob {
	
	/**
	 * @return Returns the current health state of the mob
	 */
	int getHealth();
	
	/**
	 * @param health - Set the health of the mob
	 */
	void setHealth(int health);
	
	/**
	 * @return Returns the speed of the mob
	 */
	int getSpeed();
	
	/**
	 * @param speed - Set the speed of the mob
	 */
	void setSpeed(int speed);
	
	/**
	 * @return Returns true when the mob is dead
	 */
	boolean isDead();	
}
