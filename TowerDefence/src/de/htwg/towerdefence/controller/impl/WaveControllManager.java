package de.htwg.towerdefence.controller.impl;
import de.htwg.towerdefence.controller.IGameController;
import de.htwg.towerdefence.util.control.impl.ControllableComponent;

public class WaveControllManager extends ControllableComponent {
	
	/************************************************************
	 * Private Variables
	 ***********************************************************/
	private long speed = 2000;
	private long tmpSpeed = 2000;
	private long firstWaitTime = 5000;
	private IGameController controller;
	
	/************************************************************
	 * Public constructor
	 ***********************************************************/
	
	public WaveControllManager(IGameController controller) {
		this.controller = controller;
	}
	
	
	/************************************************************
	 * Public ControllableComponent methods
	 ***********************************************************/
	
	@Override
	public boolean update(long dt) {
		
		if (this.firstWaitTime >= 0) {
			this.firstWaitTime = this.firstWaitTime - dt;
			return false;
		}
		this.tmpSpeed = this.tmpSpeed - dt;
		
		if (this.tmpSpeed <= 0) {
			controller.sendNewMobFromStart();
			this.tmpSpeed = this.speed;
			return true;
		}
		return false;
	}
}
