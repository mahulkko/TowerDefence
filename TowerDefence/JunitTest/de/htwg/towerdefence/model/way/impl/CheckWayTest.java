package de.htwg.towerdefence.model.way.impl;

import java.io.IOException;

import de.htwg.towerdefence.model.IPlayingField;
import de.htwg.towerdefence.model.impl.PlayingField;
import de.htwg.towerdefence.model.way.ICheckWay;
import de.htwg.towerdefence.util.way.Coord;
import junit.framework.TestCase;

public class CheckWayTest extends TestCase {
	
	ICheckWay w;
	IPlayingField field;
	
	public void setUp() throws IOException {
		this.w = new CheckWay();
		this.w.initWayPoints(10, 10);
		this.field = new PlayingField(10,10);
	}
	
	public void testDoc() {
		
		// Init with playingfield
		this.w.initCheckWayWithPlayingField(field);
		
		// Delete Edges
		assertEquals(false,this.w.deleteWayPoint(10, 120));
		assertEquals(true,this.w.deleteWayPoint(9, 9));
		assertEquals(true,this.w.deleteWayPoint(0, 0));
		
		// Add Waypoint
		assertEquals(true,this.w.addWayPoint(9, 9));
		assertEquals(true,this.w.addWayPoint(0, 0));
		
		// Get number of Vertex
		assertEquals(0,this.w.getNumberofVertex(11, 0));
		assertEquals(0,this.w.getNumberofVertex(0, 11));
		assertEquals(0,this.w.getNumberofVertex(11, 11));
		assertEquals(1,this.w.getNumberofVertex(0, 0));
		
		// get number of Coord
		assertEquals(null,this.w.getCoordOfVertex(-0));
		assertEquals(null,this.w.getCoordOfVertex(1000));
		@SuppressWarnings( "all" )
		Coord c = this.w.getCoordOfVertex(1);
		
		// Exists way;
		assertEquals(true,this.w.existWay(0, 0, 9, 9));
		
		// Get Shortest path
		assertEquals(false,this.w.existWay(0, 0, 22, 22));
		assertEquals(true,this.w.existWay(0, 0, 9, 9));
		this.w.getShortesWay();
		
		
	}
}