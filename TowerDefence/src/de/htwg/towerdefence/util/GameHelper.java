package de.htwg.towerdefence.util;

/**
 * <b>GameHelper Class - All the code that can be used on different places</b>
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public final class GameHelper {
	
	/************************************************************
	 * Private Constructor
	 ***********************************************************/
	
	/**
	 * Private GameHelper constructor - so there can't be an instance
	 */
	private GameHelper() { }
	
	
	/************************************************************
	 * Public static methods
	 ***********************************************************/
	
	/**
	 * <b> Random function - Calculates a random double between low and high</b>
	 * @param low - Lower bound of the random number
	 * @param high - Upper bound of the random number
	 * @return Returns a random value between the lower and upper bound
	 */
	public static double random(double low, double high) {
		return Math.random() * (high - low) + low;
	}
}
