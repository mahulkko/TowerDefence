package de.htwg.towerdefence.model.impl;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import de.htwg.towerdefence.model.IGameContext;
import de.htwg.towerdefence.model.IPlayer;
import de.htwg.towerdefence.model.IPlayingField;
import de.htwg.towerdefence.model.way.ICheckWay;
import de.htwg.towerdefence.model.way.impl.CheckWay;

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
    private static Logger log = Logger.getLogger("TowerDefence.Model.GameContext");
    
    /** Current player */
    @JsonDeserialize(as=Player.class)
    private IPlayer player;
    
    /** Current playing field */
    @JsonDeserialize(as=PlayingField.class)
    private IPlayingField playingField;
    
    /** Current instance of check way */
    @JsonDeserialize(as=CheckWay.class)
    private ICheckWay checkWay;
    
	
	/************************************************************
	 * Public constructor
	 ***********************************************************/
	
	/**
	 * Default constructor
	 */
	public GameContext() {
		log.info("Created new GameContext...");
	}
	
	@Override
	public IPlayer getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(IPlayer player) {
		log.info("Set new Player in GameContext...");
		this.player = player;
	}

	@Override
	public IPlayingField getPlayingField() {
		return playingField;
	}

	@Override
	public void setPlayingfield(IPlayingField playingField) {
		log.info("Set new PlayingField in GameContext...");
		this.playingField = playingField;
	}

	@Override
	public ICheckWay getCheckWay() {
		return checkWay;
	}

	@Override
	public void setCheckWay(ICheckWay checkWay) {
		log.info("Set new CheckWay instance in GameContext...");
		this.checkWay = checkWay;
	}

}
