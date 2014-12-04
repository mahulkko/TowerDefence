package de.htwg.towerdefence.model.impl;

import java.io.IOException;
import java.util.LinkedList;
import de.htwg.towerdefence.model.IMob;
import de.htwg.towerdefence.model.IPlayingField;
import de.htwg.towerdefence.model.ITower;
import de.htwg.towerdefence.util.GameContext;
import de.htwg.towerdefence.util.enums.FieldType;
import de.htwg.towerdefence.util.way.Coord;
import junit.framework.TestCase;

public class PlayingFieldTest extends TestCase {
	
	IPlayingField f;
	IPlayingField field;
	ITower tower;
	IMob mob1;
	IMob mob2;
	IMob mob3;
	
	public void setUp() throws IOException {
		
		GameContext.setPlayer(new Player());
		GameContext.setPlayingfield(new PlayingField(10, 10));
		
		this.f = new PlayingField();
		this.field = new PlayingField(10,10);
		this.f.initPlayingField(10, 10);
		this.tower = new Tower(new Coord(0,0), 1, 1, 1, 1, 1.0, 100);
		this.mob1 = new Mob(new Coord(0,0));
		this.mob1.setHealth(100);
		this.mob1.setSpeed(2);
		this.mob2 = new Mob(new Coord(0,0));
		this.mob2.setHealth(100);
		this.mob2.setSpeed(2);
		this.mob3 = new Mob(new Coord(0,0));
		this.mob3.setHealth(100);
		this.mob3.setSpeed(2);
	}
	
	public void testDoc() {
		
		// Set Tower
		assertEquals(false, this.f.setTower(new Coord(11,11), this.tower));
		assertEquals(false, this.f.setTower(new Coord(8,11), this.tower));
		assertEquals(false, this.f.setTower(new Coord(11,8), this.tower));
		assertEquals(true, this.f.setTower(new Coord(9,9), this.tower));
		
		
		// Get Tower
		assertEquals(null,this.f.getTower(new Coord(11,11)));
		assertEquals(null,this.f.getTower(new Coord(8,11)));
		assertEquals(null,this.f.getTower(new Coord(11,8)));
		assertEquals(this.tower,this.f.getTower(new Coord(9,9)));
		
		
		// Is Set Tower
		assertEquals(false,this.f.isSetTower(new Coord(11,11)));
		assertEquals(false,this.f.isSetTower(new Coord(8,11)));
		assertEquals(false,this.f.isSetTower(new Coord(11,8)));
		assertEquals(true,this.f.isSetTower(new Coord(9,9)));
		
		// Delete Tower
		assertEquals(null,this.f.deleteTower(new Coord(11,11)));
		assertEquals(null,this.f.deleteTower(new Coord(8,11)));
		assertEquals(null,this.f.deleteTower(new Coord(11,8)));
		assertEquals(this.tower,this.f.deleteTower(new Coord(9,9)));
		
		// Set Mob
		assertEquals(false,this.f.setMob(new Coord(11,11), this.mob1));
		assertEquals(false,this.f.setMob(new Coord(8,11), this.mob1));
		assertEquals(false,this.f.setMob(new Coord(11,8), this.mob1));
		assertEquals(true,this.f.setMob(new Coord(8,8), this.mob1));
		assertEquals(true,this.f.setMob(new Coord(8,8), this.mob2));
		
		// Get Mob
		assertEquals(null,this.f.getMobs(new Coord(11,11)));
		assertEquals(null,this.f.getMobs(new Coord(8,11)));
		assertEquals(null,this.f.getMobs(new Coord(11,8)));
		LinkedList<IMob> mobs = new LinkedList<IMob>();
		assertEquals(mobs,this.f.getMobs(new Coord(1,1)));
		
		// Set List mob
		mobs.add(mob3);
		assertEquals(false,this.f.setListMob(new Coord(11,11), mobs));
		assertEquals(false,this.f.setListMob(new Coord(8,11), mobs));
		assertEquals(false,this.f.setListMob(new Coord(11,8), mobs));
		assertEquals(true,this.f.setListMob(new Coord(2,2), mobs));
		
		// Get Number of mobs
		assertEquals(-1,this.f.getNumberOfMobs(new Coord(11,11)));
		assertEquals(-1,this.f.getNumberOfMobs(new Coord(8,11)));
		assertEquals(-1,this.f.getNumberOfMobs(new Coord(11,8)));
		assertEquals(2,this.f.getNumberOfMobs(new Coord(8,8)));
		
		// Delete all mobs
		assertEquals(false,this.f.deleteAllMobs(new Coord(11,11)));
		assertEquals(false,this.f.deleteAllMobs(new Coord(1,11)));
		assertEquals(false,this.f.deleteAllMobs(new Coord(11,1)));
		assertEquals(true,this.f.deleteAllMobs(new Coord(2,2)));
		
		// Delete Dead Mobs
		assertEquals(false,this.f.deleteDeadMobs(new Coord(11,11)));
		assertEquals(false,this.f.deleteDeadMobs(new Coord(1,11)));
		assertEquals(false,this.f.deleteDeadMobs(new Coord(11,1)));
		assertEquals(false,this.f.deleteDeadMobs(new Coord(8,8)));
		
		// Get Type of
		assertEquals(FieldType.UNDEFINED ,this.f.getTypeOf(new Coord(11,11)));
		assertEquals(FieldType.UNDEFINED ,this.f.getTypeOf(new Coord(1,11)));
		assertEquals(FieldType.UNDEFINED ,this.f.getTypeOf(new Coord(11,1)));
		assertEquals(FieldType.NONE ,this.f.getTypeOf(new Coord(1,1)));
		
		assertEquals(true, this.f.setMob(new Coord(1,1), mob1));
		assertEquals(false, this.f.isSetMob(new Coord(1,1), mob2));
		assertEquals(false, this.f.isSetMob(new Coord(11,1), mob2));
		assertEquals(false, this.f.isSetMob(new Coord(1,11), mob2));
		assertEquals(true, this.f.setMob(new Coord(1,1), mob2));
		assertEquals(true, this.f.isSetMob(new Coord(1,1), mob2));
		
		assertEquals(mob2, this.f.getMob(new Coord(1,1), mob2));
		assertEquals(null, this.f.getMob(new Coord(11,1), mob2));
		assertEquals(null, this.f.getMob(new Coord(1,11), mob2));
		
		assertEquals(mob2, this.f.deleteMob(new Coord(1,1), mob2));
		assertEquals(null, this.f.deleteMob(new Coord(11,1), mob2));
		assertEquals(null, this.f.deleteMob(new Coord(1,11), mob2));
	}
}

