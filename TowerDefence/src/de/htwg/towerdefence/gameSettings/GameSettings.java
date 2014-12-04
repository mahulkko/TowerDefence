package de.htwg.towerdefence.gameSettings;

/**
 * <b>GameSetting - Global position to edit values for the game play</b>
 * @author Christoph Knetschke and Martin Hulkkonen
 *
 */
public final class GameSettings {

	/************************************************************
	 * Player Values
	 ***********************************************************/
	
	/** Life of the player */
	private static final int PLAYERLIFE = 10;
	
	/** Money of the player */
	private static final int PLAYERMONEY = 1000;
	
	/**  Name of the player */
	private static final String PLAYERNAME = "Player";
	
	
	/************************************************************
	 * Tower Values
	 ***********************************************************/
	
	/** Damage of the tower */
	private static final int TOWERDAMAGE = 10;
	
	/** Range of the tower */
	private static final int TOWERRANGE = 3;
	
	/** Speed of the tower */
	private static final int TOWERSPEED = 1000;
	
	/** NumberOfShoot of the tower */
	private static final int TOWERNUMBEROFSHOOT = 1;
	
	/** Hitrate of the tower */
	private static final double TOWERHITRATE = 1.0;
	
	/** Coast of the tower */
	private static final int TOWERCOST = 100;
	
	
	/************************************************************
	 * Mob Values
	 ***********************************************************/
	
	/** Health of the mob */
	private static final int MOBHEALTH = 100;
	
	/** Speed of the mob */
	private static final int MOBSPEED = 500;
	
	/** Money the mob get*/
	private static final int MOBMONEY = 50;
	
	
	/************************************************************
	 * Private constructor
	 ***********************************************************/
	
	/**
	 * Private constructor - so there can't be an instance of it
	 */
	private GameSettings() { }
	
	
	/************************************************************
	 * Getter of the player values
	 ***********************************************************/
	
	/**
	 * @return Returns the default of the player name
	 */
	public static String getPlayerName() {
		return PLAYERNAME;
	}
	
	/**
	 * @return Returns the default life of the player
	 */
	public static int getPlayerLife() {
		return PLAYERLIFE;
	}
	
	/**
	 * @return Returns the default money of the player
	 */
	public static int getPlayerMoney() {
		return PLAYERMONEY;
	}
	
	
	/************************************************************
	 * Getter of the tower values
	 ************************************************************/
	
	/**
	 * @return - Returns the default damage of the tower
	 */
	public static int getTowerDamage() {
		return TOWERDAMAGE;
	}
	
	/**
	 * @return Returns the default range of the tower
	 */
	public static int getTowerRange() {
		return TOWERRANGE;
	}
	
	/**
	 * @return Returns the default speed of the tower
	 */
	public static int getTowerSpeed() {
		return TOWERSPEED;
	}
	
	/**
	 * @return Returns the default number of shoots of the tower. With this parameter the tower can deal splash damage on each round.
	 */
	public static int getTowerNumberOfShoot() {
		return TOWERNUMBEROFSHOOT;
	}
	
	/**
	 * @return Returns the default hitrate of the tower. Hitrate is the change to deal a hit with max damage.
	 */
	public static double getTowerHitRate() {
		return TOWERHITRATE;
	}
	
	/**
	 * @return Returns the default cost of the tower
	 */
	public static int getTowerCost() {
		return TOWERCOST;
	}
	
	
	/************************************************************
	 * Getter of the mob values
	 ************************************************************/
	
	 /**
	 * @return - Returns the default health of the mob
	 */
	public static int getMobHealth() {
		return MOBHEALTH;
	}
	
	 /**
	 * @return - Returns the default speed of the mob
	 */
	public static int getMobSpeed() {
		return MOBSPEED;
	}

	 /**
     * @return - Returns the default money the mob get
	 */
	public static int getMobMoney() {
		return MOBMONEY;
	}
}
