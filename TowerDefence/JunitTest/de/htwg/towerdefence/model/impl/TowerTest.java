package de.htwg.towerdefence.model.impl;

import java.io.IOException;

import de.htwg.towerdefence.model.ITower;
import de.htwg.towerdefence.util.GameContext.GameContext;
import de.htwg.towerdefence.util.control.impl.ControllableComponent;
import de.htwg.towerdefence.util.way.Coord;
import junit.framework.TestCase;

/**
 * TesCase for class Tower
 * @author Christoph Knetschke and Martin Hulkkonen
 *
 */
public class TowerTest extends TestCase {
		
	/** Tower instance one */
	ITower tower;
	
	/** Tower instance two */
	ITower tower2;
	
	/** Set up the test */
	public void setUp() throws IOException {
		GameContext.setPlayer(new Player());
		GameContext.setPlayingfield(new PlayingField(10, 10));
		
		tower = new Tower(new Coord(0,0), 1, 1, 1, 1, 1.0, 100);
		tower2 = new Tower(new Coord(0,0));
	}
	
	/**
	 * Test for the tower
	 */
	public void testDoc() {
		
		// Set and Get Damage
		tower.setDamage(1);
		assertEquals(1,tower.getDamage());
		
		// Set and Get Speed
	    tower.setSpeed(1);
		assertEquals(1,tower.getSpeed());
		
		// Set and Get Range
		tower.setRange(1);
		assertEquals(1,tower.getRange());
		
		// Set and Get HitRate
		tower.setHitRate(1.0);
		assertEquals(1.0,tower.getHitRate());
		
		// Set and Get Cost
		tower.setCost(100);;
	    assertEquals(100, tower.getCost());
		
		// CalcDamage 
		assertEquals(1,tower.calcDamage());
		tower.setHitRate(0.0);
		assertEquals(0,tower.calcDamage());
		
		// Set and Get NumberShoot
		tower.setNumberOfShoot(1);
		assertEquals(1,tower.getNumberOfShoot());
		
		// Upgrade Tower
		tower.setDamage(2);
		tower.upgrade();
		assertEquals(3,tower.getDamage());
		
		ControllableComponent component = (ControllableComponent) tower;
		component.update(0);
	}
}