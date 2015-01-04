package de.htwg.towerdefence.controller.impl;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
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
	
	/** Wave Controll manager */
	private WaveControllManager waveManager;
	
	private boolean local;	
	
	public GameController(boolean local) {
		this.local = local;
		if(local) {
			createNewGame();
		}
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
		waveManager = new WaveControllManager(this);
		manager.registerComponent(waveManager);
		GameContext.setPlayer(new Player());
		GameContext.setPlayingfield(new PlayingField(5, 5));
		GameContext.setCheckWay(new CheckWay());
		GameContext.getCheckWay().initWayPoints(5, 5);
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
		manager.changeRunningState();
		//JsonNode game = GameContext.serialize();
		//GameContext.deserialize(game);
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
			Tower tower = new Tower(coord);
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
		Mob mob = new Mob(new Coord(0, 0));
		GameContext.getPlayingField().setMob(new Coord(0, 0), mob);
		manager.registerComponent((IControllableComponent)mob);
	}
	
	public void updateGameContext() {
		System.out.println("Call UpdateManger in GameController");
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
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 ***********************************************************/
	
	@Override
	public JsonNode updateGameContext(String currentGameContext) throws JsonProcessingException, IOException {
		// TODO Update Mob bewegungen usw.
		ObjectMapper mapper = new ObjectMapper();
		JsonNode currentGameContextNode = mapper.readTree(currentGameContext);
		GameContext.deserialize(currentGameContextNode);
		this.updateGameContext();
		System.out.println("Update in GameController");
		JsonNode newGameContextNode = GameContext.serialize();
		return newGameContextNode;
	}

	@Override
	public JsonNode setTowerToPostion(String currentGameContext, int x, int y) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode currentGameContextNode = mapper.readTree(currentGameContext);
		GameContext.deserialize(currentGameContextNode);
		setTowerToPostion(x, y);
		JsonNode newGameContextNode = GameContext.serialize();
		return newGameContextNode;
	}

	// TODO UNÖTIG
	@Override
	public JsonNode sendNewMobFromStart(String currentGameContext) throws JsonProcessingException, IOException {
		System.out.println("Send Mob in GameController");
		ObjectMapper mapper = new ObjectMapper();
		JsonNode currentGameContextNode = mapper.readTree(currentGameContext);
		GameContext.deserialize(currentGameContextNode);
		sendNewMobFromStart();
		JsonNode newGameContextNode = GameContext.serialize();
		return newGameContextNode;
	}

	@Override
	public JsonNode pauseOrStartGame(String currentGameContext) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
