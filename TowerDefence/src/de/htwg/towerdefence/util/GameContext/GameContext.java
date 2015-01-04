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
    private static Logger log = Logger.getLogger("TowerDefence.Model.GameContext");
    
    /** Current player */
    private static IPlayer player;
    
    /** Current playing field */
    private static IPlayingField playingField;
    
    /** Current instance of check way */
    private static ICheckWay checkWay;
    
    /** List of all controllableComponents saved in the GameControllData */
	private static List<GameData> controllableComponents;
    
	
	/************************************************************
	 * Public constructor
	 ***********************************************************/
	
	/**
	 * Default private constructor
	 */
	private GameContext() { }
	
	public static IPlayer getPlayer() {
		return player;
	}

	public static void setPlayer(IPlayer p) {
		log.info("Set new Player in GameContext...");
		player = p;
	}

	public static IPlayingField getPlayingField() {
		return playingField;
	}

	public static void setPlayingfield(IPlayingField pField) {
		log.info("Set new PlayingField in GameContext...");
		playingField = pField;
	}

	public static ICheckWay getCheckWay() {
		return checkWay;
	}

	public static void setCheckWay(ICheckWay cWay) {
		log.info("Set new CheckWay instance in GameContext...");
		checkWay = cWay;
	}

	public static List<GameData> getGameData() {
		return controllableComponents;
	}

	public static void setGameData(List<GameData> Components) {
		controllableComponents = Components;
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
		
		return root;
	}

	public static void deserialize(JsonNode node) {
		GameContext.setGameData(new LinkedList<GameData>());
		JsonNode player = node.path("player");
		JsonNode playingField = node.path("playingField");
		GameContext.player.deserialize(player);
		GameContext.playingField.deserialize(playingField);	
		checkWay = new CheckWay();
		checkWay.initCheckWayWithPlayingField(GameContext.playingField);
	}
}
