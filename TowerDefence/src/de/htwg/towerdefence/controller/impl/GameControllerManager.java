package de.htwg.towerdefence.controller.impl;

import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;
import de.htwg.towerdefence.util.control.IControllableComponent;
import de.htwg.towerdefence.util.control.IObservable;
import de.htwg.towerdefence.util.control.IObserver;

/** 
 * @author Christoph Knetschke and Martin Hulkkonen
 * <br>
 * <b>GameControllerManager</b>
 */
public class GameControllerManager implements IObservable{
	
	/************************************************************
	 * Private variables
	 ***********************************************************/
	
	/** Logger for log4j connection */
    private static Logger log = Logger.getLogger("TowerDefence.Controller.GameControllerManager");
	
	/**
	 * List of all controllableComponents saved in the GameControllData
	 */
	private List<GameControllerData> controllableComponents;
	
	/**
	 * List of all Observer who wants to be updated
	 */
	private List<IObserver> observer;
	
	/**
	 * Object for synchronisation
	 */
	private Object sync;
	
	
	/************************************************************
	 * Public methods
	 ***********************************************************/
	
	/**
	 * Standard constructor from the game controller manager
	 */
	public GameControllerManager() {
		log.info("Started the GameControllerManager...");
		sync = new Object();
		controllableComponents = new LinkedList<GameControllerData>();
		observer = new LinkedList<IObserver>();
		Thread update = new Thread(new updateComponents());
		update.start();
	}

	
	/**
	 * Register new component for updates
	 * @param component - Component where should be updated
	 */
	public void registerComponent(IControllableComponent component) {
		synchronized(sync) {
			log.info("Registered new ControllableComponent...");
			GameControllerData data = new GameControllerData();
			data.setComponent(component);
			data.setLastTime(System.currentTimeMillis());
			this.controllableComponents.add(data);
		}
	}
	
	/**
	 * Unregister component for stop updating
	 * @param component - Component where the updates should stop
	 */
	public void unregisterComponent(IControllableComponent component) {
		synchronized(sync) {
			for (GameControllerData data: controllableComponents) {
				if (data.getComponent() == component) {
					log.info("Unregistered a ControllableComponent...");
					controllableComponents.remove(data);
				}
			}
		}
	}
	
	
	/************************************************************
	 * Inner Class for Thread
	 ***********************************************************/
	
	public class updateComponents implements Runnable {
		
		/** Check for changes */
		boolean check;
		
		/**
		 * Runs the update cycles from every registered component
		 */
		public void run() {
			log.info("Started the update prozess of the registered components");
			while (true) {
				
				check = false;
				synchronized(sync) {
					
					// Check for components that should be unregistered
					List<GameControllerData> copyControllableComponents = new LinkedList<GameControllerData>(controllableComponents);
					for (int i = 0; i < controllableComponents.size(); ++i) {
						if (controllableComponents.get(i).getComponent().getUpdateStatus() == false) {
							copyControllableComponents.remove(controllableComponents.get(i));
						}
					}
					controllableComponents = copyControllableComponents;
					
					
					// Update the components
					for (int i = 0; i < controllableComponents.size(); ++i) {
						long dt = System.currentTimeMillis() - controllableComponents.get(i).getLastTime();
		                if (controllableComponents.get(i).getComponent().update(dt)) {
		                	check = true;
		                }
		                controllableComponents.get(i).setLastTime(System.currentTimeMillis());
					}
				}
				
				if (check) {
					notifyObservers();
				}
			}
		}
	}

	
	/************************************************************
	 * Methods for the observable implementation
	 ***********************************************************/

	@Override
	public void addObserver(IObserver s) {
		observer.add(s);
	}


	@Override
	public void removeObserver(IObserver s) {
		observer.remove(s);
	}


	@Override
	public void removeAllObservers() {
		observer.clear();
	}


	@Override
	public void notifyObservers() {
		for (int i = 0; i < observer.size(); ++i) {
			observer.get(i).update();
		}
	}
}
