package de.htwg.towerdefence.model.impl;

import org.apache.log4j.Logger;
import de.htwg.towerdefence.model.IGameContext;
import de.htwg.towerdefence.model.IPlayer;
import de.htwg.towerdefence.model.IPlayingField;

/**
 * <b>Mob Class</b>
 * <br>
 * Implements IGameContext
 * @author Christoph Knetschke and Martin Hulkkonen
 */

public class GameContext implements IGameContext {

	/************************************************************
	 * Private variables
	 ***********************************************************/
	
	/** Logger for log4j connection */
    private static Logger log = Logger.getLogger("TowerDefence.Model.Tower");
    
    /** Current player */
    private IPlayer player;
    
    /** Current playing field */
    private IPlayingField playingField;
    
	
	/************************************************************
	 * Public constructor
	 ***********************************************************/
	
	/**
	 * Default constructor
	 */
	public GameContext() {
		// Nothing to do in here right now
	}
	
	@Override
	public IPlayer getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(IPlayer player) {
		this.player = player;
	}

	@Override
	public IPlayingField getPlayingField() {
		return playingField;
	}

	@Override
	public void setPlayingfield(IPlayingField playingField) {
		this.playingField = playingField;
	}

}
