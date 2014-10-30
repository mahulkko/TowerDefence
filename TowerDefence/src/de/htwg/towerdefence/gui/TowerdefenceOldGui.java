package de.htwg.towerdefence.gui;

import java.awt.GridLayout;

import javax.swing.JFrame;

import de.htwg.towerdefence.controller.IGameController;
import de.htwg.towerdefence.util.control.IObserver;

public class TowerdefenceOldGui implements IObserver {
	
	private PlayingFieldGui pgui;
	private IGameController controller;
	
	public TowerdefenceOldGui(IGameController controller) {
		this.controller = controller;
		pgui = new PlayingFieldGui(controller);
		
		JFrame frame = new JFrame("Towerdefence");
		frame.setSize(700, 500);
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.setLayout(new GridLayout(2,2));
		frame.add(pgui);
		frame.setVisible(true);
	}

	@Override
	public void update() {
		pgui.repaint();
	}
}
