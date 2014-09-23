package de.htwg.towerdefence.model;

/**
 * <b>Interface IPlayer</b>
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public interface IPlayer {
	
	/**
	 * @return Returns the name of the player
	 */
	String getName();
	
	/**
	 * @param name - Set the name of the player
	 */
	void setName(String name);
	
	/**
	 * @return Returns the money of the player
	 */
	int getMoney();
	
	/**
	 * @param money - Set the money of the player
	 */
	void setMoney(int money);
	
	/**
	 * @return Returns the current life of the player
	 */
	int getLife();
	
	/**
	 * @param life - Set the life of the player
	 */
	void setLife(int life);
}
