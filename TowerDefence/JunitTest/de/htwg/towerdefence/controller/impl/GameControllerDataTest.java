package de.htwg.towerdefence.controller.impl;

import junit.framework.TestCase;
import java.io.IOException;
import de.htwg.towerdefence.util.control.IControllableComponent;

public class GameControllerDataTest extends TestCase {
	
	GameControllerData data;
	IControllableComponent component;
	
	
	public void setUp() throws IOException {
		data = new GameControllerData();
		component = new ControllableComponentTest(1, "Test");
	}
	
	public void testDoc() {
		data.setLastTime(1);
		assertEquals(1, data.getLastTime());
		data.setComponent(component);
		assertEquals(component, data.getComponent());
	}
}
