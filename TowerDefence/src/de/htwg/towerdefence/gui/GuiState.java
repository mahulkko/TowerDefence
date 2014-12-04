package de.htwg.towerdefence.gui;

public class GuiState {
	
	public enum State {
		SETTOWER, SHOWINFOS
	}
	
	private State state;
	
	public GuiState() {
		this.state = State.SHOWINFOS;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public State getState() {
		return this.state;
	}
}
