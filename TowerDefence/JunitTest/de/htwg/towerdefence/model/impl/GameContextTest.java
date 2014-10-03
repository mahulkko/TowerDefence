package de.htwg.towerdefence.model.impl;

import java.io.IOException;
import de.htwg.towerdefence.model.IPlayer;
import de.htwg.towerdefence.model.IPlayingField;
import junit.framework.TestCase;

/**
 * TestCase for Class GameContext
 */
public class GameContextTest extends TestCase {
	
	/** Current player */
    private IPlayer player;
    
    /** Current playing field */
    private IPlayingField playingField;
    
    /** GameContext */
    private GameContext context;
	
	/**
	 * Set up the test
	 */
	public void setUp() throws IOException {
		context = new GameContext();
		player = new Player();
		playingField = new PlayingField();
	}
	
	/**
	 * Tests for the GameContext
	 */
	public void testDoc() {
		
		// Getter and Setter for player context
		context.setPlayer(player);
		assertEquals(player, context.getPlayer());
		
		// Getter and Setter for playing field context
		context.setPlayingfield(playingField);
		assertEquals(playingField, context.getPlayingField());
	}
}
