package de.htwg.towerdefence.util.json;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.htwg.towerdefence.model.impl.Player;
import de.htwg.towerdefence.model.impl.PlayingField;
import de.htwg.towerdefence.model.way.impl.CheckWay;
import de.htwg.towerdefence.util.GameContext.GameContext;

public class JsonMapperTest {
	
	private JsonMapper mapper;
	private List<GameContext> gameContextList;
	
	@Before
	public void setUp() {
		mapper = new JsonMapper();
		this.gameContextList = new ArrayList<GameContext>();
	}
	
	@Test
	public void createPlayer() {
		String jsonString = mapper.convertObjectToJsonString(new CheckWay());
		System.out.println(jsonString);
	}
	
	@Test
	public void createAndloadJsonObject() {
		// Serialize GameContext
		//String jsonString = mapper.convertObjectToJsonString(gameContext);
		//System.out.println(jsonString);
		
		// Deserialize GameContext
//		IGameContext loadedGameContext = mapper.createObjectFromJsonString(jsonString, GameContext.class);
//		System.out.println(loadedGameContext);
//		assertTrue(checkIfGameContextAreEquals(loadedGameContext, gameContext));
	}
	

	@SuppressWarnings("unchecked")
	@Test
	public void createAndloadJsonObjectArray() {
		// Serialize GameContextList
		String jsonArrayString = mapper.convertListToJsonString(gameContextList);
		System.out.println(jsonArrayString);
		
		// Deserialize GameContextList
//		List<GameContext> loadedGameContextList = (List<GameContext>) mapper.createListFromJsonString(jsonArrayString, GameContext.class);
//		System.out.println(loadedGameContextList);
//		assertTrue(checkIfGameContextAreEquals(loadedGameContextList.get(0), gameContext));
//		assertTrue(checkIfGameContextAreEquals(loadedGameContextList.get(1), gameContext));
//		assertTrue(checkIfGameContextAreEquals(loadedGameContextList.get(2), gameContext));
	}

	
//	private GameContext setUpGameContext() {
////		gameContext.setPlayer(new Player());
////		gameContext.setPlayingfield(new PlayingField(11, 11));
////		return gameContext;
//	}
	
//	private boolean checkIfGameContextAreEquals(IGameContext loadedGameContext, IGameContext gameContext2) {
//		boolean equal = true;
//		if (loadedGameContext.getPlayer().getName().equals(gameContext2.getPlayer().getName())) {
//			equal = false;
//		}
//		if (loadedGameContext.getPlayingField().getSizeX() != gameContext2.getPlayingField().getSizeX()) {
//			equal = false;
//		}
//		return equal;
//	}
}
