package de.htwg.towerdefence.util;

import org.apache.log4j.Logger;
import de.htwg.towerdefence.model.IPlayer;
import de.htwg.towerdefence.model.IPlayingField;
import de.htwg.towerdefence.model.way.ICheckWay;

/**
 * <b>Mob Class</b>
 * <br>
 * Implements IGameContext
 * @author Christoph Knetschke and Martin Hulkkonen
 */

public class GameContext {

	/************************************************************
	 * Private variables
	 ***********************************************************/
	
	/** Logger for log4j connection */
    private static Logger log = Logger.getLogger("TowerDefence.Model.GameContext");
    
    /** Current player */
    private static IPlayer player;
    
    /** Current playing field */
    private static IPlayingField playingField;
    
    /** Current instance of check way */
    private static ICheckWay checkWay;
    
	
	/************************************************************
	 * Public constructor
	 ***********************************************************/
	
	/**
	 * Default private constructor
	 */
	private GameContext() { }
	
	public static IPlayer getPlayer() {
		return player;
	}

	public static void setPlayer(IPlayer p) {
		log.info("Set new Player in GameContext...");
		player = p;
	}

	public static IPlayingField getPlayingField() {
		return playingField;
	}

	public static void setPlayingfield(IPlayingField pField) {
		log.info("Set new PlayingField in GameContext...");
		playingField = pField;
	}

	public static ICheckWay getCheckWay() {
		return checkWay;
	}

	public static void setCheckWay(ICheckWay cWay) {
		log.info("Set new CheckWay instance in GameContext...");
		checkWay = cWay;
	}

}
