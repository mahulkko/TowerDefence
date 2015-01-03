package de.htwg.towerdefence.controller.impl;

import junit.framework.TestCase;
import java.io.IOException;

public class GameControllerTest extends TestCase {
	
	GameController controller;	

	public void setUp() throws IOException {
		controller = new GameController(true);
	}
	
	public void testDoc() {
		// Nothing to do in here right now
	}
}
