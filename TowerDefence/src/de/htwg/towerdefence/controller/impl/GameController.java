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
		gameContext.setPlayingfield(new PlayingField(11, 11));
		gameContext.setCheckWay(new CheckWay());
		gameContext.getCheckWay().initWayPoints(11, 11);
	}


	/************************************************************
	 * Public methods
	 ***********************************************************/
	
	
	/************************************************************
	 * Observer methods
	 ***********************************************************/
	
	@Override
	public void addObserver(IObserver s) {
		manager.addObserver(s);
	}

	@Override
	public void removeObserver(IObserver s) {
		manager.removeObserver(s);
	}
	
	/************************************************************
	 * Playingfield methods
	 ***********************************************************/

	@Override
	public int getSizeXOfPlayingField() {
		return gameContext.getPlayingField().getSizeX();
	}

	@Override
	public int getSizeYOfPlayingField() {
		return gameContext.getPlayingField().getSizeY();
	}
	
	@Override
	public FieldType getTypeOfPlayingField(Coord coord) {
		return gameContext.getPlayingField().getTypeOf(coord);
	}
	
	@Override
	public boolean isTowerOnField(int x, int y) {
		FieldType fiedltype = gameContext.getPlayingField().getTypeOf(new Coord(x, y));
		if (FieldType.TOWER == fiedltype) {
			return true;
		}
		return false;
	}


	@Override
	public boolean isMobOnField(int x, int y) {
		FieldType fiedltype = gameContext.getPlayingField().getTypeOf(new Coord(x, y));
		if (FieldType.MOB == fiedltype) {
			return true;
		}
		return false;
	}
	
	@Override
	public void setTowerToPostion(int x, int y) {
		tower = new Tower(gameContext, new Coord(x, y));
		tower.setDamage(0);
		// Check if Player has money for building 
		if (gameContext.getPlayer().getMoney() >=  tower.getCost()) {
			gameContext.getCheckWay().deleteWayPoint(x, y);
			if (gameContext.getCheckWay().existWay(0, 0, this.gameContext.getPlayingField().getSizeX()-1, this.gameContext.getPlayingField().getSizeY()-1)) {
				gameContext.getPlayingField().setTower(new Coord(x, y), tower);
				manager.registerComponent((IControllableComponent)tower);
				// Reduce Player Money for building one tower
				gameContext.getPlayer().setMoney(gameContext.getPlayer().getMoney() - tower.getCost());
			} else {
				gameContext.getCheckWay().addWayPoint(x, y);
			}
		}
	}

	@Override
	public void sendNewMobFromStart() {
		mob = new Mob(gameContext, new Coord(0, 0));
		gameContext.getPlayingField().setMob(new Coord(0, 0), mob);
		manager.registerComponent((IControllableComponent)mob);
	}

	
	/************************************************************
	 * Player methods
	 ***********************************************************/

	@Override
	public String setPlayerName(String playerName) {
		gameContext.getPlayer().setName(playerName);
		return gameContext.getPlayer().getName();
	}

	@Override
	public int setPlayerMoney(int playerMoney) {
		gameContext.getPlayer().setMoney(playerMoney);
		return gameContext.getPlayer().getMoney();
	}

	@Override
	public int setPlayerLife(int playerLife) {
		gameContext.getPlayer().setLife(playerLife);
		return gameContext.getPlayer().getLife();
	}
	
	@Override
	public String getPlayerName() {
		return gameContext.getPlayer().getName();
	}

	@Override
	public int getPlayerMoney() {
		return gameContext.getPlayer().getMoney();
	}

	@Override
	public int getPlayerLife() {
		return gameContext.getPlayer().getLife();
	}
	
	/************************************************************
	 * Tower methods
	 ***********************************************************/
	
	@Override
	public int getTowerSpeed(int x, int y) {
		ITower tower = gameContext.getPlayingField().getTower(new Coord(x, y));
		return tower.getSpeed();
	}

	@Override
	public int getTowerRange(int x, int y) {
		ITower tower = gameContext.getPlayingField().getTower(new Coord(x, y));
		return tower.getRange();
	}

	@Override
	public int getTowerDamage(int x, int y) {
		ITower tower = gameContext.getPlayingField().getTower(new Coord(x, y));
		return tower.getDamage();
	}

}
