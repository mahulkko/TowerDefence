package de.htwg.towerdefence.model.impl;

import java.io.IOException;

import de.htwg.towerdefence.model.IPlayer;
import de.htwg.towerdefence.model.IPlayingField;
import de.htwg.towerdefence.util.GameContext;
import junit.framework.TestCase;

/**
 * TestCase for Class GameContext
 */
public class GameContextTest extends TestCase {
	
	/** Current player */
    private IPlayer player;
    
    /** Current playing field */
    private IPlayingField playingField;
    
	
	/**
	 * Set up the test
	 */
	public void setUp() throws IOException {
		player = new Player();
		playingField = new PlayingField();
	}
	
	/**
	 * Tests for the GameContext
	 */
	public void testDoc() {
		
		// Getter and Setter for player context
		GameContext.setPlayer(player);
		assertEquals(player, GameContext.getPlayer());
		
		// Getter and Setter for playing field context
		GameContext.setPlayingfield(playingField);
		assertEquals(playingField, GameContext.getPlayingField());
	}
}
