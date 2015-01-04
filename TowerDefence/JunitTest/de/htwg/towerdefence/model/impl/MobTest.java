package de.htwg.towerdefence.model.impl;

import java.io.IOException;
import java.util.LinkedList;

import org.codehaus.jackson.JsonNode;

import de.htwg.towerdefence.model.IMob;
import de.htwg.towerdefence.model.way.impl.CheckWay;
import de.htwg.towerdefence.util.GameContext.GameContext;
import de.htwg.towerdefence.util.GameContext.GameData;
import de.htwg.towerdefence.util.control.IControllableComponent;
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
	
	/** Mob instance 3 */
	IMob mob3;
	
	/**
	 * Set up the test
	 */
	public void setUp() throws IOException {
		GameContext.setPlayer(new Player());
		GameContext.setPlayingfield(new PlayingField(10, 10));
		
		mob = new Mob(new Coord(0,0));
		mob2 = new Mob(new Coord(0,0), 100, 10, 50);
		mob3 = new Mob();
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
		
		GameContext.setPlayer(new Player());
		GameContext.setPlayingfield(new PlayingField(5, 5));
		GameContext.setCheckWay(new CheckWay());
		GameContext.getCheckWay().initWayPoints(5, 5);
		GameContext.setGameData(new LinkedList<GameData>());
		
		JsonNode node = mob.serialize();
		mob.deserialize(node);
		
		GameData data = new GameData();
		data.setComponent((IControllableComponent)mob2);
		data.setLastTime(System.currentTimeMillis());
		GameContext.getGameData().add(data);
		
		JsonNode node2 = mob2.serialize();
		mob2.deserialize(node2);
		
		ControllableComponent component = (ControllableComponent) mob;
		component.update(0);
		component.update(10000);
	}
}
