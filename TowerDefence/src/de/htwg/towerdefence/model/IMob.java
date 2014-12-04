package de.htwg.towerdefence.model;

import de.htwg.towerdefence.util.serialize.Serialize;

/**
 * <b>Interface IMob</b>
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public interface IMob extends Serialize {
	
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
	 * @param moneyValue - the money that the mob brings
	 */
	void setMoneyValue(int moneyValue);
	
	/**
	 * @return Return the money that you get when the mob is dead
	 */
	public int getMoneyValue();
	
	/**
	 * @return Returns true when the mob is dead
	 */
	boolean isDead();	
}
