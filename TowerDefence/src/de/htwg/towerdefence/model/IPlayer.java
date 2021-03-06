package de.htwg.towerdefence.model;

import de.htwg.towerdefence.util.serialize.Serialize;

/**
 * <b>Interface IPlayer</b>
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public interface IPlayer extends Serialize {
	
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
	
	/**
	 * @return Returns the email adress of the player
	 */
	String getEmailAdress();
	
	/**
	 * @param email - Set the email adress of the player
	 */
	void setEmailAdress(String email);
}
