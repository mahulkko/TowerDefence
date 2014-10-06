package de.htwg.towerdefence.controller.impl;

import de.htwg.towerdefence.util.control.IControllableComponent;

public class ControllableComponentTest implements IControllableComponent {
	
	public ControllableComponentTest(int seconds, String message) {
		// Nothing to do in here
		// Only for testing
	}
	
	@Override
	public boolean update(long dt) {
		return false;
		// Nothing to do in here
		// Only for testing
	}
}