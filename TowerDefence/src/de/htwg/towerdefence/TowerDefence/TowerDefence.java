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
		
		
		//---------------------------------
		controller = new GameController();
		tui = new TowerdefenceTui(controller);
		gui = new TowerdefenceGui(controller);
		controller.addObserver(tui);
		controller.addObserver(gui);
		
		controller.setTowerToPostion(5, 0);
		controller.setTowerToPostion(0, 6);
		controller.setTowerToPostion(1, 6);
		controller.setTowerToPostion(2, 6);
		controller.setTowerToPostion(3, 6);
		controller.setTowerToPostion(4, 6);
		controller.setTowerToPostion(5, 6);
		controller.setTowerToPostion(6, 6);
		controller.setTowerToPostion(7, 6);
		controller.setTowerToPostion(8, 6);
		controller.setTowerToPostion(10, 6);
		controller.setTowerToPostion(9, 6);
		
		controller.sendNewMobFromStart();
	}
}

