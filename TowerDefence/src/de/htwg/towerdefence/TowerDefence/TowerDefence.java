package de.htwg.towerdefence.TowerDefence;

import org.apache.log4j.BasicConfigurator;
import de.htwg.towerdefence.controller.impl.GameControllerManager;

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
	 * <b>Main function of the tower defence game</b>
	 * @param args - not used at the moment
	 */
	public static void main(String[] args) {
		
		/**
	     * Logger
	     */
	    BasicConfigurator.configure();
	    
	    GameControllerManager controller = new GameControllerManager();	    
	    
	}
}

