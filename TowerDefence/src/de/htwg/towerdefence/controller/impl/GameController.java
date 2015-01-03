package de.htwg.towerdefence.controller.impl;

import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

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
	
	private boolean local;
		
	IMob mob;
	ITower tower;
	
	
	public GameController(boolean local) {
		this.local = local;
		manager = new GameControllerManager(local);
		GameContext.setPlayer(new Player());
		GameContext.setPlayingfield(new PlayingField(11, 11));
		GameContext.setCheckWay(new CheckWay());
		GameContext.getCheckWay().initWayPoints(11, 11);
		
		PlayingField p = new PlayingField(5,5);
		PlayingField pp = new PlayingField(1,1);
		p.setTower(new Coord(2,2), new Tower());

		
		System.out.println(p.serialize());
		
		pp.deserialize(p.serialize());
		
		System.out.println(p.serialize());
		
		if (p.serialize().toString().equals(pp.serialize().toString() ))
			System.out.println("!!!!!!!!!!!!GLEICH!!!!!!!!");
		
		try {
			Thread.sleep(0);
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
	
	public JsonNode createNewGame() {
		manager = new GameControllerManager(local);
		GameContext.setPlayer(new Player());
		GameContext.setPlayingfield(new PlayingField(11, 11));
		GameContext.setCheckWay(new CheckWay());
		GameContext.getCheckWay().initWayPoints(11, 11);
		JsonNode game = GameContext.serialize();
		return game;
	}
	
	public JsonNode createNewGame(String playerName, int life, int money, int playingfieldSizeX,  int playingfieldSizeY) {
		manager = new GameControllerManager(local);
		GameContext.setPlayer(new Player(playerName, life, money));
		GameContext.setPlayingfield(new PlayingField(playingfieldSizeX, playingfieldSizeY));
		GameContext.setCheckWay(new CheckWay());
		GameContext.getCheckWay().initWayPoints(playingfieldSizeX, playingfieldSizeY);
		JsonNode game = GameContext.serialize();
		return game;
	}
	
	public boolean pauseOrStartGame() {
		// Test for the serialization and deserialization
//		manager.changeRunningState();
//		JsonNode game = GameContext.serialize();
//		GameContext.deserialize(game);
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
	
	public void updateGameContext() {
		this.manager.update();
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
	
	/************************************************************
	 * Wui methods
	 ***********************************************************/
	
//	String gameContextEmpty = "{\"player\":{\"name\":\"Player\",\"money\":1000,\"life\":10,\"updateStatus\":true},\"playingField\":{\"sizeX\":5,\"sizeY\":5,\"fieldArray\":{\"row0\":{\"0\":{},\"1\":{},\"2\":{},\"3\":{},\"4\":{}},\"row1\":{\"0\":{},\"1\":{},\"2\":{},\"3\":{},\"4\":{}},\"row2\":{\"0\":{},\"1\":{},\"2\":{},\"3\":{},\"4\":{}},\"row3\":{\"0\":{},\"1\":{},\"2\":{},\"3\":{},\"4\":{}},\"row4\":{\"0\":{},\"1\":{},\"2\":{},\"3\":{},\"4\":{}}}}}";
//	String gameContextWithTowerOn2_2 = "{\"player\":{\"name\":\"Player\",\"money\":1000,\"life\":10,\"updateStatus\":true},\"playingField\":{\"sizeX\":5,\"sizeY\":5,\"fieldArray\":{\"row0\":{\"0\":{},\"1\":{},\"2\":{},\"3\":{},\"4\":{}},\"row1\":{\"0\":{},\"1\":{},\"2\":{},\"3\":{},\"4\":{}},\"row2\":{\"0\":{},\"1\":{},\"2\":{\"tower\":{\"damage\":10,\"range\":3,\"tmpSpeed\":0,\"speed\":1000,\"numberShoot\":1,\"cost\":100,\"position\":{\"x\":0,\"y\":0},\"tmpCoord\":{\"x\":0,\"y\":0},\"hitrate\":1.0}},\"3\":{},\"4\":{}},\"row3\":{\"0\":{},\"1\":{},\"2\":{},\"3\":{},\"4\":{}},\"row4\":{\"0\":{},\"1\":{},\"2\":{},\"3\":{},\"4\":{}}}}}";
//	String gameContextWithTowerAndMobs = "{\"player\":{\"name\":\"Player\",\"money\":1000,\"life\":10,\"updateStatus\":true},\"playingField\":{\"sizeX\":5,\"sizeY\":5,\"fieldArray\":{\"row0\":{\"0\":{},\"1\":{},\"2\":{},\"3\":{},\"4\":{}},\"row1\":{\"0\":{},\"1\":{\"mobList\":{\"0\":{\"health\":100,\"speed\":500,\"tmpSpeed\":500,\"coord\":{\"x\":0,\"y\":0},\"money\":50}},\"sizeMobList\":1},\"2\":{\"tower\":{\"damage\":10,\"range\":3,\"tmpSpeed\":0,\"speed\":1000,\"numberShoot\":1,\"cost\":100,\"position\":{\"x\":0,\"y\":0},\"tmpCoord\":{\"x\":0,\"y\":0},\"hitrate\":1.0}},\"3\":{\"tower\":{\"damage\":10,\"range\":3,\"tmpSpeed\":0,\"speed\":1000,\"numberShoot\":1,\"cost\":100,\"position\":{\"x\":0,\"y\":0},\"tmpCoord\":{\"x\":0,\"y\":0},\"hitrate\":1.0}},\"4\":{}},\"row2\":{\"0\":{},\"1\":{\"tower\":{\"damage\":10,\"range\":3,\"tmpSpeed\":0,\"speed\":1000,\"numberShoot\":1,\"cost\":100,\"position\":{\"x\":0,\"y\":0},\"tmpCoord\":{\"x\":0,\"y\":0},\"hitrate\":1.0}},\"2\":{\"mobList\":{\"0\":{\"health\":100,\"speed\":500,\"tmpSpeed\":500,\"coord\":{\"x\":0,\"y\":0},\"money\":50}},\"sizeMobList\":1},\"3\":{},\"4\":{}},\"row3\":{\"0\":{},\"1\":{\"tower\":{\"damage\":10,\"range\":3,\"tmpSpeed\":0,\"speed\":1000,\"numberShoot\":1,\"cost\":100,\"position\":{\"x\":0,\"y\":0},\"tmpCoord\":{\"x\":0,\"y\":0},\"hitrate\":1.0}},\"2\":{},\"3\":{\"mobList\":{\"0\":{\"health\":100,\"speed\":500,\"tmpSpeed\":500,\"coord\":{\"x\":0,\"y\":0},\"money\":50}},\"sizeMobList\":1},\"4\":{}},\"row4\":{\"0\":{},\"1\":{},\"2\":{},\"3\":{},\"4\":{}}}}}";
	
	@Override
	public JsonNode updateGameContext(JsonNode currentGameContext) {
		// TODO Update Mob bewegungen usw.
		
		GameContext.deserialize(currentGameContext);
		this.updateGameContext();
		JsonNode newGameContext = GameContext.serialize();
		
		return newGameContext;
	}

	@Override
	public JsonNode pauseOrStartGame(JsonNode currentGameContext) {
		// TODO Update Mob bewegungen usw.
		GameContext.deserialize(currentGameContext);
		
		pauseOrStartGame();
		
		JsonNode newGameContext = GameContext.serialize();
		
		return newGameContext;
	}

	@Override
	public JsonNode setTowerToPostion(JsonNode currentGameContext, int x, int y) {
	
		GameContext.deserialize(currentGameContext);
		
		setTowerToPostion(x, y);
		
		JsonNode newGameContext = GameContext.serialize();
		
		return newGameContext;
	}

	// TODO UNÖTIG
	@Override
	public JsonNode sendNewMobFromStart(JsonNode currentGameContext) {
		
		GameContext.deserialize(currentGameContext);
		
		JsonNode newGameContext = GameContext.serialize();
		
		return newGameContext;
	}
}
