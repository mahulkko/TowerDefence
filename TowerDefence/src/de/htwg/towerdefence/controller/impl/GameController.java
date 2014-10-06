package de.htwg.towerdefence.controller.impl;

import de.htwg.towerdefence.controller.IGameController;
import de.htwg.towerdefence.model.IGameContext;
import de.htwg.towerdefence.model.IMob;
import de.htwg.towerdefence.model.impl.GameContext;
import de.htwg.towerdefence.model.impl.Mob;
import de.htwg.towerdefence.model.impl.Player;
import de.htwg.towerdefence.model.impl.PlayingField;
import de.htwg.towerdefence.util.control.IControllableComponent;
import de.htwg.towerdefence.util.control.IObserver;
import de.htwg.towerdefence.util.enums.FieldType;

/**
 * @author Christoph Knetschke and Martin Hulkkonen
 * <br>
 * <b>GameController</b>
 */
public class GameController implements IGameController {
	
	/************************************************************
	 * Private variables
	 ***********************************************************/
	
	/** Game controller manager */
	private GameControllerManager manager;
	
	/** GameContext - includes playing field and player */
	IGameContext gameContext;
	
	IMob mob;
	
	
	public GameController() {
		manager = new GameControllerManager();
		gameContext = new GameContext();
		gameContext.setPlayer(new Player());
		gameContext.setPlayingfield(new PlayingField(10, 10));
		mob = new Mob();
		manager.registerComponent((IControllableComponent)mob);
		gameContext.getPlayingField().setMob(0, 0, mob);
	}


	/************************************************************
	 * Public methods
	 ***********************************************************/
	
	@Override
	public FieldType getTypeOfPlayingField(int x, int y) {
		return gameContext.getPlayingField().getTypeOf(x, y);
	}

	@Override
	public int getSizeXOfPlayingField() {
		return gameContext.getPlayingField().getSizeX();
	}

	@Override
	public int getSizeYOfPlayingField() {
		return gameContext.getPlayingField().getSizeY();
	}

	@Override
	public void addObserver(IObserver s) {
		manager.addObserver(s);
	}

	@Override
	public void removeObserver(IObserver s) {
		manager.removeObserver(s);
	}
}
