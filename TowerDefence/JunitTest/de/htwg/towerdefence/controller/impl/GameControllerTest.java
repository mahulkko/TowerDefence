package de.htwg.towerdefence.controller.impl;

import junit.framework.TestCase;

import java.io.IOException;

import de.htwg.towerdefence.util.way.Coord;

public class GameControllerTest extends TestCase {
	
	GameController controller;	
	GameController controller2;

	public void setUp() throws IOException {
		controller = new GameController(true);
		controller.createNewGame("test", 10, 1000, 10, 10);
	}
	
	public void testDoc() {
		
		controller.pauseOrStartGame();
		controller.pauseOrStartGame();
		controller.getSizeXOfPlayingField();
		controller.getSizeYOfPlayingField();
		controller.getTypeOfPlayingField(new Coord());
		controller.isTowerOnField(0, 0);
		controller.isMobOnField(0, 0);
		controller.setTowerToPostion(1, 1);
		controller.isTowerOnField(1, 1);
		
		controller.updateGameContext();
		controller.sendNewMobFromStart();
		
		controller.setPlayerName("sdfsdfg");
		controller.setPlayerMoney(1000);
		controller.setPlayerLife(10);
		
		controller.getPlayerName();
		controller.getPlayerMoney();
		controller.getPlayerLife();
	}
}
