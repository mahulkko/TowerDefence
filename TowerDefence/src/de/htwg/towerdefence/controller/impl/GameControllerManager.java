package de.htwg.towerdefence.controller.impl;

import java.util.List;

import de.htwg.towerdefence.util.control.IControllableComponent;

/** 
 * @author Christoph Knetschke and Martin Hulkkonen
 * <br>
 * <b>GameControllerManager</b>
 */
public class GameControllerManager {
	
	/**
	 * List of all controllableComponents saved in the GameControllData
	 */
	private List<GameControllerData> controllableComponents;
	
	/**
	 * Standard constructor from the game controller manager
	 */
	public GameControllerManager() {
		// Nothing to do in here right now
	}
	
	/**
	 * Register new component for updates
	 * @param component - Component where should be updated
	 */
	public void registerComponent(IControllableComponent component) {
		GameControllerData data = new GameControllerData();
		data.setComponent(component);
		data.setLastTime(System.currentTimeMillis());
		this.controllableComponents.add(data);
	}
	
	/**
	 * Unregister component for stop updating
	 * @param component - Component where the updates should stop
	 */
	public void unregisterComponent(IControllableComponent component) {
		for (GameControllerData data: controllableComponents) {
			   if (data.getComponent() == component) {
				   controllableComponents.remove(data);
			   }
		}
	}
	
	/**
	 * Runs the update cycles from every registered component
	 */
	private void run() {
		while (true) {
			for (GameControllerData data: controllableComponents) {
				long dt = System.currentTimeMillis() - data.getLastTime();
				data.getComponent().update(dt);
			}
		}
	}
}
