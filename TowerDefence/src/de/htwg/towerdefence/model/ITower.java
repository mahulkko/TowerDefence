package de.htwg.towerdefence.model;

/**
 * <b>Interface ITower</b>
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public interface ITower {
	
	/**
	 * @return Returns the max damage that the tower can make
	 */
    int getDamage();
    
    /**
     * @param damage - Set the max damage that the tower can make
     */
    void setDamage(int damage);
    
    /**
     * @return Returns the hitrate of the tower. Hitrate is the change to deal a hit with max damage.
     */
    double getHitRate();
    
    /**
     * @param hitrate - Set the hitrate of the tower. Hitrate is the change to deal a hit with max damage.
     */
    void setHitRate(double hitrate);
    
    /**
     * @return Returns the number of shoots. With this parameter the tower can deal splash damage on each round.
     */
    int getNumberOfShoot();
    
    /**
     * @param numbershoot - Set the number of shoots of the tower. With this parameter the tower can deal splash damage on each round.
     */
    void setNumberOfShoot(int numbershoot);
    
    /**
     * @return Returns the shooting range of the tower
     */
    int getRange(); 
    
    /**
     * @param range - Set the shooting range of the tower
     */
    void setRange(int range);
    
    /**
     * @return Returns the attacking speed of the tower
     */
    int getSpeed(); 
    
    /**
     * @param speed - Set the attacking speed of the tower
     */
    void setSpeed(int speed);
    
    /**
     * @return Returns a calculated damage from all parameters
     */
    int calcDamage();
}
