package de.htwg.towerdefence.gui;

public class GuiState {
	
	public enum State {
		SETTOWER, SHOWTOWER, UPGRADETOWER
	}
	
	private State state;
	
	public GuiState() {
		this.state = State.SHOWTOWER;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public State getState() {
		return this.state;
	}
}
