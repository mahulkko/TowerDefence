package de.htwg.towerdefence.controller.impl;

import java.util.List;

import org.codehaus.jackson.JsonNode;

import de.htwg.towerdefence.controller.IGameController;
import de.htwg.towerdefence.model.IMob;
import de.htwg.towerdefence.model.ITower;
import de.htwg.towerdefence.model.impl.Field;
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
		
		/*
		PlayingField p = new PlayingField(5,5);
		PlayingField pp = new PlayingField(1,1);
		p.setMob(new Coord(1,1), new Mob());
		p.setMob(new Coord(2,2), new Mob());
		p.setMob(new Coord(3,3), new Mob());
		
		p.setTower(new Coord(1,2), new Tower());
		p.setTower(new Coord(1,3), new Tower());
		p.setTower(new Coord(2,1), new Tower());
		p.setTower(new Coord(3,1), new Tower());
		
		System.out.println(p.serialize());
		
		pp.deserialize(p.serialize());
		
		System.out.println(p.serialize());
		
		if (p.serialize().toString().equals(pp.serialize().toString() ))
			System.out.println("!!!!!!!!!!!!GLEICH!!!!!!!!");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/*
		
		Field f = new Field();
		Field ff = new Field();
		f.setTower(new Tower());
		
		System.out.println(f.serialize());
		JsonNode node = f.serialize();
		ff.deserialize(node);
		
		System.out.println(ff.serialize());
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
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
	
	public String getGameContext(String oldGameContext) {
		String beispiel = "{\"player\":{\"name\":\"Player\",\"money\":1000,\"life\":10,\"updateStatus\":true},\"playingField\":{\"sizeX\":5,\"sizeY\":5,\"fieldArray\":{\"row0\":{\"0\":{},\"1\":{},\"2\":{},\"3\":{},\"4\":{}},\"row1\":{\"0\":{},\"1\":{\"mobList\":{\"0\":{\"health\":100,\"speed\":500,\"tmpSpeed\":500,\"coord\":{\"x\":0,\"y\":0},\"money\":50}},\"sizeMobList\":1},\"2\":{\"tower\":{\"damage\":10,\"range\":3,\"tmpSpeed\":0,\"speed\":1000,\"numberShoot\":1,\"cost\":100,\"position\":{\"x\":0,\"y\":0},\"tmpCoord\":{\"x\":0,\"y\":0},\"hitrate\":1.0}},\"3\":{\"tower\":{\"damage\":10,\"range\":3,\"tmpSpeed\":0,\"speed\":1000,\"numberShoot\":1,\"cost\":100,\"position\":{\"x\":0,\"y\":0},\"tmpCoord\":{\"x\":0,\"y\":0},\"hitrate\":1.0}},\"4\":{}},\"row2\":{\"0\":{},\"1\":{\"tower\":{\"damage\":10,\"range\":3,\"tmpSpeed\":0,\"speed\":1000,\"numberShoot\":1,\"cost\":100,\"position\":{\"x\":0,\"y\":0},\"tmpCoord\":{\"x\":0,\"y\":0},\"hitrate\":1.0}},\"2\":{\"mobList\":{\"0\":{\"health\":100,\"speed\":500,\"tmpSpeed\":500,\"coord\":{\"x\":0,\"y\":0},\"money\":50}},\"sizeMobList\":1},\"3\":{},\"4\":{}},\"row3\":{\"0\":{},\"1\":{\"tower\":{\"damage\":10,\"range\":3,\"tmpSpeed\":0,\"speed\":1000,\"numberShoot\":1,\"cost\":100,\"position\":{\"x\":0,\"y\":0},\"tmpCoord\":{\"x\":0,\"y\":0},\"hitrate\":1.0}},\"2\":{},\"3\":{\"mobList\":{\"0\":{\"health\":100,\"speed\":500,\"tmpSpeed\":500,\"coord\":{\"x\":0,\"y\":0},\"money\":50}},\"sizeMobList\":1},\"4\":{}},\"row4\":{\"0\":{},\"1\":{},\"2\":{},\"3\":{},\"4\":{}}}}}";
		System.out.println(beispiel);
		return beispiel;
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
	public boolean setTowerToPostion(int x, int y) {
		Coord coord = new Coord(x, y);
		if(!GameContext.getPlayingField().isSetTower(coord)) {
			tower = new Tower(coord);
			// Check if Player has money for building 
			if (GameContext.getPlayer().getMoney() >=  tower.getCost()) {
				GameContext.getCheckWay().deleteWayPoint(x, y);
				if (GameContext.getCheckWay().existWay(0, 0,GameContext.getPlayingField().getSizeX()-1, GameContext.getPlayingField().getSizeY()-1)) {
					GameContext.getPlayingField().setTower(coord, tower);
					manager.registerComponent((IControllableComponent)tower);
					// Reduce Player Money for building one tower
					GameContext.getPlayer().setMoney(GameContext.getPlayer().getMoney() - tower.getCost());
					return true;
				} else {
					GameContext.getCheckWay().addWayPoint(x, y);
				}
			}
		}
		return false;
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

	@Override
	public int getTowerCoast(int x, int y) {
		ITower tower = GameContext.getPlayingField().getTower(new Coord(x, y));
		return tower.getCost();
	}
	
	@Override
	public void upgradeTower(int x, int y) {
		ITower tower = GameContext.getPlayingField().getTower(new Coord(x, y));
		if (GameContext.getPlayer().getMoney() >=  tower.getCost()) {
			tower.upgrade();
			GameContext.getPlayer().setMoney(GameContext.getPlayer().getMoney() - tower.getCost());
		}
	}
	
	

	/************************************************************
	 * Mob methods
	 ***********************************************************/
	
	@Override
	public int getMobHealth(int x, int y) {
		List<IMob> mob = GameContext.getPlayingField().getMobs(new Coord(x, y));
		return mob.get(0).getHealth();
	}

	@Override
	public int getMobSpeed(int x, int y) {
		List<IMob> mob = GameContext.getPlayingField().getMobs(new Coord(x, y));
		return mob.get(0).getSpeed();
	}

	@Override
	public int getMobMoney(int x, int y) {
		List<IMob> mob = GameContext.getPlayingField().getMobs(new Coord(x, y));
		return mob.get(0).getMoneyValue();
	}

}
