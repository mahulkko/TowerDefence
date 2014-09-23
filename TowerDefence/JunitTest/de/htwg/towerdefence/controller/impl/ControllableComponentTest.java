package de.htwg.towerdefence.controller.impl;

import de.htwg.towerdefence.util.control.IControllableComponent;

public class ControllableComponentTest implements IControllableComponent {

	private int seconds;
	private double currentSeconds;
	private String message;
	
	public ControllableComponentTest(int seconds, String message) {
		this.seconds = seconds;
		this.currentSeconds = 0.0;
		this.message = message;
	}
	
	@Override
	public void update(long dt) {
		// Nothing to do in here
		// Only for testing
	}
}