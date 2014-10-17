package de.htwg.towerdefence.controller.impl;

import de.htwg.towerdefence.controller.IGameController;
import de.htwg.towerdefence.model.IGameContext;
import de.htwg.towerdefence.model.IMob;
import de.htwg.towerdefence.model.ITower;
import de.htwg.towerdefence.model.impl.GameContext;
import de.htwg.towerdefence.model.impl.Mob;
import de.htwg.towerdefence.model.impl.Player;
import de.htwg.towerdefence.model.impl.PlayingField;
import de.htwg.towerdefence.model.impl.Tower;
import de.htwg.towerdefence.model.way.impl.CheckWay;
import de.htwg.towerdefence.util.control.IControllableComponent;
import de.htwg.towerdefence.util.control.IObserver;
import de.htwg.towerdefence.util.enums.FieldType;
import de.htwg.towerdefence.util.way.Coord;

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
	ITower tower;
	
	
	public GameController() {
		manager = new GameControllerManager();
		gameContext = new GameContext();
		gameContext.setPlayer(new Player());
		gameContext.setPlayingfield(new PlayingField(10, 10));
		gameContext.setCheckWay(new CheckWay());
		gameContext.getCheckWay().initWayPoints(10, 10);
		
		mob = new Mob(gameContext, new Coord(0, 0));
		mob.setSpeed(2000);
		gameContext.getPlayingField().setMob(0, 0, mob);
		manager.registerComponent((IControllableComponent)mob);
		
		mob = new Mob(gameContext, new Coord(0, 1));
		gameContext.getPlayingField().setMob(0, 1, mob);
		manager.registerComponent((IControllableComponent)mob);
		
		mob = new Mob(gameContext, new Coord(0, 2));
		gameContext.getPlayingField().setMob(0, 2, mob);
		manager.registerComponent((IControllableComponent)mob);
		
		tower = new Tower(gameContext, new Coord(0, 2));
		tower.setRange(5);
		tower.setSpeed(2000);
		gameContext.getPlayingField().setTower(2, 2, tower);
		manager.registerComponent((IControllableComponent)tower);
	}


	/************************************************************
	 * Public methods
	 ***********************************************************/
	
	@Override
	public FieldType getTypeOfPlayingField(Coord coord) {
		return gameContext.getPlayingField().getTypeOf(coord.getX(), coord.getY());
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
