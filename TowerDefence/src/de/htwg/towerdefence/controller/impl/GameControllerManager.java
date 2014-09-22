package de.htwg.towerdefence.controller.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import de.htwg.towerdefence.util.control.IControllableComponent;

/** 
 * @author Christoph Knetschke and Martin Hulkkonen
 * <br>
 * <b>GameControllerManager</b>
 */
public class GameControllerManager {
	
	/** Logger for log4j connection */
    private static Logger log = Logger.getLogger("TowerDefence.Controller.GameControllerManager");
	
	/**
	 * List of all controllableComponents saved in the GameControllData
	 */
	private List<GameControllerData> controllableComponents;
	
	/**
	 * Standard constructor from the game controller manager
	 */
	public GameControllerManager() {
		log.info("Started the GameControllerManager...");
		controllableComponents = new LinkedList<GameControllerData>();
		Thread update = new Thread(new updateComponents());
		update.start();
	}

	
	/**
	 * Register new component for updates
	 * @param component - Component where should be updated
	 */
	public void registerComponent(IControllableComponent component) {
		log.info("Registered new ControllableComponent...");
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
				log.info("Unregistered a ControllableComponent...");
				controllableComponents.remove(data);
			}
		}
	}
	
	public class updateComponents implements Runnable {
		
		/**
		 * Runs the update cycles from every registered component
		 */
		public void run() {
			log.info("Started the update prozess of the registered components");
			while (true) {				
				for (int i = 0; i < controllableComponents.size(); i++) {
					long dt = System.currentTimeMillis() - controllableComponents.get(i).getLastTime();
	                controllableComponents.get(i).getComponent().update(dt);
	                controllableComponents.get(i).setLastTime(System.currentTimeMillis());
	            }
			}
		}
	}
}
