package de.htwg.towerdefence.model.impl;

import java.io.IOException;
import de.htwg.towerdefence.model.IMob;
import de.htwg.towerdefence.util.GameContext;
import de.htwg.towerdefence.util.control.impl.ControllableComponent;
import de.htwg.towerdefence.util.way.Coord;
import junit.framework.TestCase;

/**
 * TestCase for Class mob
 */
public class MobTest extends TestCase {
	
	/** Mob instance 1 */
	IMob mob;
	
	/** Mob instance 2 */
	IMob mob2;
	
	/**
	 * Set up the test
	 */
	public void setUp() throws IOException {
		GameContext.setPlayer(new Player());
		GameContext.setPlayingfield(new PlayingField(10, 10));
		
		mob = new Mob(new Coord(0,0));
		mob2 = new Mob(new Coord(0,0), 100, 10, 50);
	}
	
	/**
	 * Tests for the mob
	 */
	public void testDoc() {
		
		// Get and set health
		mob.setHealth(10);
		assertEquals(10, mob.getHealth());
		
		// get and set speed
		mob.setSpeed(2);
		assertEquals(2, mob.getSpeed());
		
		// get and set speed
		mob.setMoneyValue(100);;
		assertEquals(100, mob.getMoneyValue());
		
		
		// Check the function isDead
		assertEquals(false, mob.isDead());
		mob.setHealth(0);
		assertEquals(true, mob.isDead());
		
		ControllableComponent component = (ControllableComponent) mob;
		component.update(0);
	}
}
