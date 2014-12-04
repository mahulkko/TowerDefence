package de.htwg.towerdefence.model.impl;

import junit.framework.TestCase;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import de.htwg.towerdefence.model.IMob;
import de.htwg.towerdefence.model.ITower;
import de.htwg.towerdefence.util.GameContext;
import de.htwg.towerdefence.util.enums.FieldType;
import de.htwg.towerdefence.util.way.Coord;

/**
 * TestCase for Class field
 */
public class FieldTest extends TestCase {
	
	/** Instance of the field */
	Field field;
	
	/** Instance of the tower */
	ITower tower;
	
	/** Mob instance 1 */
	IMob mob;
	
	/** Mob instance 2 */
	IMob mob2;
	
	/** mob instance 3 */
	IMob mob3;
	
	/**
	 * Set up the test
	 */
	public void setUp() throws IOException {
		
		GameContext.setPlayer(new Player());
		GameContext.setPlayingfield(new PlayingField(10, 10));
		
		field = new Field();
		tower = new Tower(new Coord(0,0), 1, 1, 1, 1, 1.0, 100);
		mob = new Mob(new Coord(0,0));
		mob2 = new Mob(new Coord(0,0));
		mob3 = new Mob(new Coord(0,0));
	}
	
	/**
	 * Tests for the field
	 */
	public void testDoc() {
		
		// Check type of the field
		assertEquals(FieldType.NONE, field.getTypeOf());
		
		// Set and get a  tower
		assertEquals(true, field.setTower(tower));
		assertEquals(false, field.setTower(tower));
		assertEquals(tower, field.getTower());
		
		// Set a mob
		assertEquals(false, field.setMob(mob));
		
		// Check type of the field
		assertEquals(FieldType.TOWER, field.getTypeOf());
		
		// Delete tower
		assertEquals(tower, field.deleteTower());
		assertEquals(null, field.deleteTower());
		
		// Set a mob
		assertEquals(true, field.setMob(mob));
		
		// Check type of the field
		assertEquals(FieldType.MOB, field.getTypeOf());
		
		// Delete a mob
		assertEquals(true, field.deleteAllMobs());
		assertEquals(false, field.deleteAllMobs());
		
		// Set a mob
		assertEquals(true, field.setMob(mob));
		assertEquals(true, field.setMob(mob2));
		mob.setHealth(0);
		
		// Delete a dead mob
		assertEquals(true, field.deleteDeadMobs());
		assertEquals(false, field.deleteDeadMobs());
		
		// Delete a mob
		assertEquals(true, field.deleteAllMobs());
		mob.setHealth(30);
		
		// Add a list
		List<IMob> list = new LinkedList<IMob>();
		list.add(mob);
		list.add(mob2);
		list.add(mob3);
		
		assertEquals(true, field.setTower(tower));
		assertEquals(false, field.setListMob(list));
		assertEquals(tower, field.deleteTower());
		
		assertEquals(true, field.setListMob(list));
		assertEquals(list, field.getMobs());
		
		assertEquals(true, field.deleteAllMobs());
		
		assertEquals(true, field.setMob(mob));
		assertEquals(true, field.setMob(mob2));
		assertEquals(true, field.isSetMob(mob2));
		
		assertEquals(mob2, field.getMob(mob2));
		assertEquals(mob2, field.deleteMob(mob2));
		assertEquals(null, field.deleteMob(mob2));
		assertEquals(false, field.isSetMob(mob2));
		assertEquals(null, field.getMob(mob2));
		assertEquals(mob, field.getMob(mob));
		
	}
}
