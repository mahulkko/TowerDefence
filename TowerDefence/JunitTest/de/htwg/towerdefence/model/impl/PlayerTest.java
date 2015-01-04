package de.htwg.towerdefence.model.impl;

import java.io.IOException;

import org.codehaus.jackson.JsonNode;

import de.htwg.towerdefence.model.IPlayer;
import de.htwg.towerdefence.model.impl.Player;
import de.htwg.towerdefence.util.control.impl.ControllableComponent;
import junit.framework.TestCase;

/**
 * TestCase for Class player
 */
public class PlayerTest extends TestCase {
	
	/** Player instance1 */
	IPlayer player1;
	
	/** Player instance2 */
	IPlayer player2;
	
	/** Set up the test */
	public void setUp() throws IOException {
		player1 = new Player();
		player2 = new Player("test", 10, 100);
	}
	
	/** Tests for the player */
	public void testDoc() {
		
		// Test for player1
		// Set and Get Name 
		player1.setName("Rudolf");
		assertEquals("Rudolf", player1.getName());
		
		// Set and Get Life
		player1.setLife(13);
		assertEquals(13, player1.getLife());
		
		// Set and Get Money
		player1.setMoney(50);
		assertEquals(50, player1.getMoney());
		
		// Test for player2
		// Get name
		assertEquals("test", player2.getName());
		
		// Get life
		assertEquals(10, player2.getLife());
		
		// Get money
		assertEquals(100, player2.getMoney());
		
		JsonNode node = player1.serialize();
		player1.deserialize(node);
		
		ControllableComponent component = (ControllableComponent) player1;
		component.update(0);
	}
}
