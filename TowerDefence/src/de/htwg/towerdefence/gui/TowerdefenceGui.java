package de.htwg.towerdefence.gui;

import javax.swing.JFrame;

import de.htwg.towerdefence.controller.IGameController;
import de.htwg.towerdefence.util.control.IObserver;

public class TowerdefenceGui implements IObserver {
	
	private PlayingFieldGui pgui;
	private IGameController controller;
	
	public TowerdefenceGui(IGameController controller) {
		this.controller = controller;
		pgui = new PlayingFieldGui(controller);
		JFrame frame = new JFrame("Towerdefence");
		frame.setSize(700, 500);
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.add(pgui);
		frame.setVisible(true);
	}

	@Override
	public void update() {
		pgui.repaint();
	}
}
