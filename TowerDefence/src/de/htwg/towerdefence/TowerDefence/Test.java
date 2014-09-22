package de.htwg.towerdefence.TowerDefence;

import de.htwg.towerdefence.util.control.IControllableComponent;

public class Test implements IControllableComponent {

	private int seconds;
	private double currentSeconds;
	private String message;
	
	public Test(int seconds, String message) {
		this.seconds = seconds;
		this.currentSeconds = 0.0;
		this.message = message;
	}
	
	@Override
	public void update(long dt) {
		this.currentSeconds = this.currentSeconds + dt;
		if (this.currentSeconds >= this.seconds) {
			this.currentSeconds = 0.0;
			System.out.println(message);
		}
	}
}