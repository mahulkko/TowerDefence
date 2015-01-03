package de.htwg.towerdefence.controller.impl;

import junit.framework.TestCase;
import java.io.IOException;
import de.htwg.towerdefence.util.control.IControllableComponent;

public class GameControllerManagerTest extends TestCase {
	
	GameControllerManager manager;	
	IControllableComponent component;
	IControllableComponent component2;
	
	public void setUp() throws IOException {
		manager = new GameControllerManager(true);
		component = new ControllableComponentTest(1, "Test");
		component2 = new ControllableComponentTest(1, "Test");
	}
	
	public void testDoc() {
		manager.registerComponent(component);
		manager.registerComponent(component2);
		manager.unregisterComponent(component);
		manager.unregisterComponent(component);
	}
}
