package de.htwg.towerdefence.util.way;

import junit.framework.TestCase;
import java.io.IOException;

public class EdgeTest extends TestCase {
	
	Edge<Integer> e1;
	Edge<Integer> e2;
	
	public void setUp() throws IOException {
		e1 = new Edge<Integer>(1,2);
		e2 = new Edge<Integer>(1,2,2.0);
	}
	
	public void testDoc() {
		Integer s = e1.getSource();
		assertEquals(s,e1.getSource());	
		s = e1.getTarget();
		assertEquals(s,e1.getTarget());	
		assertEquals(1.0,e1.getWeight());
	}
}
