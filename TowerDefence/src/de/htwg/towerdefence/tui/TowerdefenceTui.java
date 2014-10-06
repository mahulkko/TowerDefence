package de.htwg.towerdefence.tui;
import de.htwg.towerdefence.controller.IGameController;
import de.htwg.towerdefence.util.control.IObserver;

public class TowerdefenceTui implements IObserver{
	
	IGameController controller;
	
	public TowerdefenceTui (IGameController controller) {
		this.controller = controller;
	}

	@Override
	public void update() {
		
	}
}
