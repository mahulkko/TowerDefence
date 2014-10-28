package de.htwg.towerdefence.gui;

import javax.swing.JFrame;
import de.htwg.towerdefence.TowerDefence.PlayingFieldGui;

public class TowerdefenceGui {
	public TowerdefenceGui() {
		
		PlayingFieldGui pgui = new PlayingFieldGui();
		JFrame frame = new JFrame("Towerdefence");
		frame.setSize(700, 500);
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.add(pgui);
		frame.setVisible(true);
	}
}
