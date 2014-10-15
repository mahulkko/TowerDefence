package de.htwg.towerdefence.model;

import de.htwg.towerdefence.model.way.ICheckWay;

/**
 * <b>Interface IGameContext</b>
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public interface IGameContext {
	
	/**
	 * @return Returns the Player from the game
	 */
	IPlayer getPlayer();
	
	/**
	 * @param player Set the current player from the game
	 */
	void setPlayer(IPlayer player);
	
	/**
	 * @return Returns the complete playing field
	 */
	IPlayingField getPlayingField();
	
	/**
	 * @param playingField Set the current playing field
	 */
	void setPlayingfield(IPlayingField playingField);
	
	/**
	 * @return Returns the checkWay instance
	 */
	ICheckWay getCheckWay();
	
	/**
	 * @param checkWay Set the checkWay instance
	 */
	void setCheckWay(ICheckWay checkWay);	
}
