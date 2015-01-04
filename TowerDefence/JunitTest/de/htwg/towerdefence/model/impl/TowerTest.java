package de.htwg.towerdefence.model.impl;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.JsonNode;

import de.htwg.towerdefence.model.ITower;
import de.htwg.towerdefence.model.way.impl.CheckWay;
import de.htwg.towerdefence.util.GameContext.GameContext;
import de.htwg.towerdefence.util.GameContext.GameData;
import de.htwg.towerdefence.util.control.IControllableComponent;
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
	
	/** Tower instance three */
	ITower tower3;
	
	/** Set up the test */
	public void setUp() throws IOException {
		GameContext.setPlayer(new Player());
		GameContext.setPlayingfield(new PlayingField(10, 10));
		
		tower = new Tower(new Coord(0,0), 1, 1, 1, 1, 1.0, 100);
		tower2 = new Tower(new Coord(0,0));
		tower3 = new Tower();
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
		
		GameContext.setPlayer(new Player());
		GameContext.setPlayingfield(new PlayingField(5, 5));
		GameContext.setCheckWay(new CheckWay());
		GameContext.getCheckWay().initWayPoints(5, 5);
		GameContext.setGameData(new LinkedList<GameData>());
		
		JsonNode node = tower.serialize();
		tower.deserialize(node);
		
		GameData data = new GameData();
		data.setComponent((IControllableComponent)tower2);
		data.setLastTime(System.currentTimeMillis());
		GameContext.getGameData().add(data);
		
		JsonNode node2 = tower2.serialize();
		tower2.deserialize(node2);
		
		ControllableComponent component = (ControllableComponent) tower;
		component.update(0);
	}
}