package de.htwg.towerdefence.TowerDefence;

import org.apache.log4j.BasicConfigurator;

import de.htwg.towerdefence.controller.impl.GameController;
import de.htwg.towerdefence.gui.TowerdefenceGui;
import de.htwg.towerdefence.tui.TowerdefenceTui;

/**
 * @author Christoph Knetschke and Martin Hulkkonen
 * <br>
 * <b>TowerDefence - Main Class</b>
 */
public class TowerDefence {
	
	public TowerDefence() {
		// Nothing to do in here
	}

	/************************************************************
	 * Main function
	 ***********************************************************/
	
	/**
	 * <b>Main function of the tower defense game</b>
	 * @param args - not used at the moment
	 */
	public static void main(String[] args) {
		
		/**
	     * Logger
	     */
	    BasicConfigurator.configure();
	    
	    /**
	     * GameController
	     */
		GameController controller;
		
		/**
		 * TUI
		 */
		TowerdefenceTui tui;
		
		/**
		 * GUI
		 */
		TowerdefenceGui gui;
		
		/**
		 * GUI
		 */
		TowerdefenceGui gui2;
		
		/**
		 * Set it true for a second test gui
		 */
		boolean secondGui = false;
		
		
		
		//---------------------------------
		controller = new GameController(true);
		tui = new TowerdefenceTui(controller);
		gui = new TowerdefenceGui(controller);
		controller.addObserver(tui);
		controller.addObserver(gui);
		
		if (secondGui) {
			gui2 = new TowerdefenceGui(controller);
			controller.addObserver(gui2);
		}
	}
}

