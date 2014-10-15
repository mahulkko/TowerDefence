package de.htwg.towerdefence.util.way;

import junit.framework.TestCase;
import java.io.IOException;

public class CoordTest extends TestCase {
	
	Coord c;
	Coord c2;
	
	public void setUp() throws IOException {
		c = new Coord(1,2);
		c2 = new Coord();
	}
	
	public void testDoc() {
		
		assertEquals(true,c2.setX(1));
		assertEquals(true,c2.setY(2));
		assertEquals(false,c2.setX(-1));
		assertEquals(false,c2.setY(-1));
		assertEquals(1,c2.getX());
		assertEquals(2,c2.getY());	
	}
}
