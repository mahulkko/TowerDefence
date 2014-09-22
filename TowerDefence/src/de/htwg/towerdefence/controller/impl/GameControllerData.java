package de.htwg.towerdefence.controller.impl;

import de.htwg.towerdefence.util.control.IControllableComponent;

/**
 * @author Christoph Knetschke and Martin Hulkkonen
 * <br>
 * <b>GameControllerData</b>
 */
public class GameControllerData {

	/**
	 * IControllableComponent
	 * Saves one component to update
	 */
	private IControllableComponent component;
	
	/**
	 * Time when the component was the last time updated
	 */
	private long lastTime; 
	
	/**
	 * Standard constructor from the game controller data
	 */
	public GameControllerData() {
		// Nothing to do in here right now
	}

	/**
	 * Gets the ControllableComponent back
	 * @return Returns the IControllableComponent
	 */
	public IControllableComponent getComponent() {
		return component;
	}

	/**
	 * Sets the ControllableComponent
	 * @param component Sets the ControllerComponent
	 */
	public void setComponent(IControllableComponent component) {
		this.component = component;
	}

	/**
	 * @return Returns the time when the ConntrollableComponent was called the last time
	 */
	public long getLastTime() {
		return lastTime;
	}

	/**
	 * @param lastTime Set the new time when calling the component
	 */
	public void setLastTime(long lastTime) {
		this.lastTime = lastTime;
	}
	
}
