package de.htwg.towerdefence.controller.impl;

import de.htwg.towerdefence.controller.IGameController;

/**
 * @author Christoph Knetschke and Martin Hulkkonen
 * <br>
 * <b>GameController</b>
 */
public class GameController implements IGameController {
	
	@SuppressWarnings("unused")
	private GameControllerManager manager;
	
	public GameController() {
		manager = new GameControllerManager();
	}
}
