package de.htwg.towerdefence.controller.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import de.htwg.towerdefence.util.GameContext.GameContext;
import de.htwg.towerdefence.util.GameContext.GameData;
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
	 * List of all Observer who wants to be updated
	 */
	private List<IObserver> observer;
	
	/**
	 * Shows the state of the running Thread
	 */
	private boolean run;
	
	/** Check for changes */
	boolean check;
	
	
	/************************************************************
	 * Public methods
	 ***********************************************************/
	
	/**
	 * Standard constructor from the game controller manager
	 */
	public GameControllerManager(boolean local) {
		log.info("Started the GameControllerManager...");
		this.run = local;
		GameContext.setGameData(new LinkedList<GameData>());
		observer = new LinkedList<IObserver>();
		Thread update = new Thread(new updateComponents());
		update.start();
	}

	
	/**
	 * Register new component for updates
	 * @param component - Component where should be updated
	 */
	public void registerComponent(IControllableComponent component) {
		log.info("Registered new ControllableComponent...");
		GameData data = new GameData();
		data.setComponent(component);
		data.setLastTime(System.currentTimeMillis());
		GameContext.getGameData().add(data);
	}
	
	/**
	 * Unregister component for stop updating
	 * @param component - Component where the updates should stop
	 */
	public void unregisterComponent(IControllableComponent component) {
		for (GameData data: GameContext.getGameData()) {
			if (data.getComponent() == component) {
				log.info("Unregistered a ControllableComponent...");
				GameContext.getGameData().remove(data);
			}
		}
	}
	
	/**
	 * Stop or Starts the running game
	 * @return the state of the running game
	 */
	public boolean changeRunningState() {
		return this.run = !this.run;
	}
	
	public void update() {
		
		check = false;
			// Check for components that should be unregistered
			List<GameData> copyControllableComponents = new LinkedList<GameData>(GameContext.getGameData());
			for (int i = 0; i < GameContext.getGameData().size(); ++i) {
				if (GameContext.getGameData().get(i).getComponent().getUpdateStatus() == false) {
					copyControllableComponents.remove(GameContext.getGameData().get(i));
				}
			}
			GameContext.setGameData(copyControllableComponents);
			
			
			// Update the components
			for (int i = 0; i < GameContext.getGameData().size(); ++i) {
				long dt = System.currentTimeMillis() - GameContext.getGameData().get(i).getLastTime();
                if (GameContext.getGameData().get(i).getComponent().update(dt)) {
                	check = true;
                }
                GameContext.getGameData().get(i).setLastTime(System.currentTimeMillis());
			}
		
		if (check) {
			notifyObservers();
		}
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/************************************************************
	 * Inner Class for Thread
	 ***********************************************************/
	
	public class updateComponents implements Runnable {
		
		/**
		 * Runs the update cycles from every registered component
		 */
		public void run() {
			log.info("Started the update prozess of the registered components");
			while (true) {
				
				if(run) {
					update();
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
