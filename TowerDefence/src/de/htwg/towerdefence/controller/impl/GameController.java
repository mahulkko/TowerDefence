package de.htwg.towerdefence.controller.impl;

import de.htwg.towerdefence.controller.IGameController;
import de.htwg.towerdefence.model.IMob;
import de.htwg.towerdefence.model.ITower;
import de.htwg.towerdefence.model.impl.Mob;
import de.htwg.towerdefence.model.impl.Player;
import de.htwg.towerdefence.model.impl.PlayingField;
import de.htwg.towerdefence.model.impl.Tower;
import de.htwg.towerdefence.model.way.impl.CheckWay;
import de.htwg.towerdefence.util.GameContext.GameContext;
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
		
	IMob mob;
	ITower tower;
	
	
	public GameController() {
		manager = new GameControllerManager();
		GameContext.setPlayer(new Player());
		GameContext.setPlayingfield(new PlayingField(11, 11));
		GameContext.setCheckWay(new CheckWay());
		GameContext.getCheckWay().initWayPoints(11, 11);
	}

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
	 * Run method
	 ***********************************************************/
	
	public boolean pauseOrStartGame() {
		return manager.changeRunningState();
	}
	
	/************************************************************
	 * Playingfield methods
	 ***********************************************************/

	@Override
	public int getSizeXOfPlayingField() {
		return GameContext.getPlayingField().getSizeX();
	}

	@Override
	public int getSizeYOfPlayingField() {
		return GameContext.getPlayingField().getSizeY();
	}
	
	@Override
	public FieldType getTypeOfPlayingField(Coord coord) {
		return GameContext.getPlayingField().getTypeOf(coord);
	}
	
	@Override
	public boolean isTowerOnField(int x, int y) {
		FieldType fiedltype = GameContext.getPlayingField().getTypeOf(new Coord(x, y));
		if (FieldType.TOWER == fiedltype) {
			return true;
		}
		return false;
	}


	@Override
	public boolean isMobOnField(int x, int y) {
		FieldType fiedltype = GameContext.getPlayingField().getTypeOf(new Coord(x, y));
		if (FieldType.MOB == fiedltype) {
			return true;
		}
		return false;
	}
	
	@Override
	public void setTowerToPostion(int x, int y) {
		tower = new Tower(new Coord(x, y));
		tower.setDamage(0);
		// Check if Player has money for building 
		if (GameContext.getPlayer().getMoney() >=  tower.getCost()) {
			GameContext.getCheckWay().deleteWayPoint(x, y);
			if (GameContext.getCheckWay().existWay(0, 0,GameContext.getPlayingField().getSizeX()-1, GameContext.getPlayingField().getSizeY()-1)) {
				GameContext.getPlayingField().setTower(new Coord(x, y), tower);
				manager.registerComponent((IControllableComponent)tower);
				// Reduce Player Money for building one tower
				GameContext.getPlayer().setMoney(GameContext.getPlayer().getMoney() - tower.getCost());
			} else {
				GameContext.getCheckWay().addWayPoint(x, y);
			}
		}
	}

	@Override
	public void sendNewMobFromStart() {
		mob = new Mob(new Coord(0, 0));
		GameContext.getPlayingField().setMob(new Coord(0, 0), mob);
		manager.registerComponent((IControllableComponent)mob);
	}

	
	/************************************************************
	 * Player methods
	 ***********************************************************/

	@Override
	public String setPlayerName(String playerName) {
		GameContext.getPlayer().setName(playerName);
		return GameContext.getPlayer().getName();
	}

	@Override
	public int setPlayerMoney(int playerMoney) {
		GameContext.getPlayer().setMoney(playerMoney);
		return GameContext.getPlayer().getMoney();
	}

	@Override
	public int setPlayerLife(int playerLife) {
		GameContext.getPlayer().setLife(playerLife);
		return GameContext.getPlayer().getLife();
	}
	
	@Override
	public String getPlayerName() {
		return GameContext.getPlayer().getName();
	}

	@Override
	public int getPlayerMoney() {
		return GameContext.getPlayer().getMoney();
	}

	@Override
	public int getPlayerLife() {
		return GameContext.getPlayer().getLife();
	}
	
	/************************************************************
	 * Tower methods
	 ***********************************************************/
	
	@Override
	public int getTowerSpeed(int x, int y) {
		ITower tower = GameContext.getPlayingField().getTower(new Coord(x, y));
		return tower.getSpeed();
	}

	@Override
	public int getTowerRange(int x, int y) {
		ITower tower = GameContext.getPlayingField().getTower(new Coord(x, y));
		return tower.getRange();
	}

	@Override
	public int getTowerDamage(int x, int y) {
		ITower tower = GameContext.getPlayingField().getTower(new Coord(x, y));
		return tower.getDamage();
	}

}
