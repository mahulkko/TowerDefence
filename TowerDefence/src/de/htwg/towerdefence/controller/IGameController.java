package de.htwg.towerdefence.controller;

import de.htwg.towerdefence.util.control.IObserver;
import de.htwg.towerdefence.util.enums.FieldType;

/**
 * @author Christoph Knetschke and Martin Hulkkonen
 * <br>
 * <b>Interface of the GameController</b>
 */
public interface IGameController {
	
	// TODO: Make some comments and refactoring
	// PlayingField
	FieldType getTypeOfPlayingField(int x, int y);
	int getSizeXOfPlayingField();
	int getSizeYOfPlayingField();
	
	// Gui and tui
	void addObserver(IObserver s);
	void removeObserver(IObserver s);
	// TODO: Make some comments and refactoring

}
