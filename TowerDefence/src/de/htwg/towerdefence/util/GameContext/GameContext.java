package de.htwg.towerdefence.util.GameContext;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import de.htwg.towerdefence.model.IPlayer;
import de.htwg.towerdefence.model.IPlayingField;
import de.htwg.towerdefence.model.way.ICheckWay;
import de.htwg.towerdefence.model.way.impl.CheckWay;
import de.htwg.towerdefence.util.control.IControllableComponent;
import de.htwg.towerdefence.util.way.Coord;

/**
 * <b>Mob Class</b>
 * <br>
 * Implements IGameContext
 * @author Christoph Knetschke and Martin Hulkkonen
 */

public class GameContext {

	/************************************************************
	 * Private variables
	 ***********************************************************/
	
	/** Logger for log4j connection */
    private static final Logger LOG = Logger.getLogger("TowerDefence.Model.GameContext");
    
    /** Current player */
    private static IPlayer player;
    
    /** Current playing field */
    private static IPlayingField playingField;
    
    /** Current instance of check way */
    private static ICheckWay checkWay;
    
    /** Instance of the WaveControllManager */
    private static WaveControllManager controllManager;
    
    /** List of all controllableComponents saved in the GameControllData */
	private static List<GameData> controllableComponents;
    
	
	/************************************************************
	 * Public constructor
	 ***********************************************************/
	
	/**
	 * Default private constructor
	 */
	private GameContext() { 
		
	}
	
	public static IPlayer getPlayer() {
		return player;
	}

	public static void setPlayer(IPlayer p) {
		LOG.info("Set new Player in GameContext...");
		player = p;
	}

	public static IPlayingField getPlayingField() {
		return playingField;
	}

	public static void setPlayingfield(IPlayingField pField) {
		LOG.info("Set new PlayingField in GameContext...");
		playingField = pField;
	}

	public static ICheckWay getCheckWay() {
		return checkWay;
	}

	public static void setCheckWay(ICheckWay cWay) {
		LOG.info("Set new CheckWay instance in GameContext...");
		checkWay = cWay;
	}
	
	public static WaveControllManager getControllManager() {
		return controllManager;
	}

	public static void setControllManager(WaveControllManager controllManager) {
		GameContext.controllManager = controllManager;
		GameData data = new GameData();
		data.setComponent(GameContext.controllManager);
		data.setLastTime(System.currentTimeMillis());
		GameContext.getGameData().add(data);
	}

	public static List<GameData> getGameData() {
		return controllableComponents;
	}

	public static void setGameData(List<GameData> components) {
		controllableComponents = components;
	}
	
	public static GameData getGameData(IControllableComponent component) {
		for (int i = 0; i < controllableComponents.size(); ++i) {
			if (controllableComponents.get(i).getComponent() == component) {
				return controllableComponents.get(i);
			}
		}
		return null;
	}
	
	public static boolean isComponentInGameData(IControllableComponent component) {
		for (int i = 0; i < controllableComponents.size(); ++i) {
			if (controllableComponents.get(i).getComponent() == component) {
				return true;
			}
		}
		return false;
	}
	
	/************************************************************
	 * Public Serialize methods
	 ***********************************************************/
	
	public static JsonNode serialize() {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.createObjectNode();
		((ObjectNode)root).put("player", player.serialize());
		((ObjectNode)root).put("playingField", playingField.serialize());
		((ObjectNode)root).put("WaveControllManager", controllManager.serialize());
		
		JsonNode way = mapper.createObjectNode();
		GameContext.getCheckWay().existWay(0 , 0, GameContext.getPlayingField().getSizeX()-1, GameContext.getPlayingField().getSizeY()-1);
		List<Coord> shortestWay = GameContext.checkWay.getShortesWay();
		
		for (int i = 0; i < shortestWay.size(); ++i) {
			((ObjectNode)way).put("WayPoint" + i, shortestWay.get(i).serialize());
		}
		
		((ObjectNode)root).put("ShortestWay", way);
		
		return root;
	}

	public static void deserialize(JsonNode node) {
		GameContext.setGameData(new LinkedList<GameData>());
		JsonNode player = node.path("player");
		JsonNode playingField = node.path("playingField");
		JsonNode waveControll = node.path("WaveControllManager");
		GameContext.player.deserialize(player);
		GameContext.playingField.deserialize(playingField);
		GameContext.controllManager.deserialize(waveControll);
		
		checkWay = new CheckWay();
		checkWay.initCheckWayWithPlayingField(GameContext.playingField);
	}
}
