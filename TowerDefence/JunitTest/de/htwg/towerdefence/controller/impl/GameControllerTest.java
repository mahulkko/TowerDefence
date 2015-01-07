package de.htwg.towerdefence.controller.impl;

import junit.framework.TestCase;

import java.io.IOException;

import de.htwg.towerdefence.gui.TowerdefenceGui;
import de.htwg.towerdefence.tui.TowerdefenceTui;
import de.htwg.towerdefence.util.way.Coord;

public class GameControllerTest extends TestCase {
	
	GameController controller;	
	GameController controller2;
	
	/**
	 * TUI
	 */
	TowerdefenceTui tui;
	
	/**
	 * GUI
	 */
	TowerdefenceGui gui;

	public void setUp() throws IOException {
		controller = new GameController(true);
		controller.createNewGame("test", 10, 1000, 10, 10);
		tui = new TowerdefenceTui(controller);
		gui = new TowerdefenceGui(controller);
	}
	
	public void testDoc() {
		tui.toString();
		tui.update();
		controller.addObserver(this.tui);
		controller.addObserver(gui);
		this.controller.removeObserver(this.tui);
		controller.pauseOrStartGame();
		controller.pauseOrStartGame();
		controller.getSizeXOfPlayingField();
		controller.getSizeYOfPlayingField();
		controller.getTypeOfPlayingField(new Coord());
		controller.isTowerOnField(0, 0);
		controller.isMobOnField(0, 0);
		controller.setTowerToPostion(1, 1);
		controller.setTowerToPostion(0, 0);
		controller.isTowerOnField(1, 1);
		
		this.controller.sendNewMobFromStart();
		controller.isMobOnField(0, 0);
		
		
		controller.updateGameContext();
		controller.sendNewMobFromStart();
		
		controller.setPlayerName("sdfsdfg");
		controller.setPlayerMoney(0);
		controller.setPlayerLife(10);
		controller.setTowerToPostion(0, 0);
		
		controller.setPlayerMoney(1000);
		controller.setTowerToPostion(2, 2);
		controller.setTowerToPostion(2, 2);
		
		controller.getPlayerName();
		controller.getPlayerMoney();
		controller.getPlayerLife();
		
		this.controller.getTowerSpeed(2,2);
		this.controller.getTowerRange(2,2);
		this.controller.getTowerDamage(2,2);
		this.controller.getTowerCoast(2,2);
		this.controller.upgradeTower(2, 2);
		
		controller.setPlayerMoney(0);
		this.controller.upgradeTower(2, 2);
		controller.setPlayerMoney(1000);
		
		this.controller.getMobHealth(0, 0);
		this.controller.getMobSpeed(0, 0);
		this.controller.getMobMoney(0, 0);
		
	}
}
