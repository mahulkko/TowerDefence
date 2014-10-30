package de.htwg.towerdefence.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.htwg.towerdefence.controller.IGameController;
import de.htwg.towerdefence.util.control.IObserver;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTable;
import java.awt.Panel;
import java.awt.Label;
import java.awt.Color;

public class TowerdefenceGui extends JFrame implements IObserver{

	private IGameController controller;
	private PlayingFieldGui playingField;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public TowerdefenceGui(IGameController controller) {
		
		playingField = new PlayingFieldGui(controller);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 482);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//JPanel panel = playingField;
		Panel panel = new Panel();
		panel.setBounds(0, 0, 451, 313);
		contentPane.add(panel);
		
		Panel panel_1 = new Panel();
		panel_1.setBounds(457, 0, 209, 313);
		contentPane.add(panel_1);
		
		Panel panel_2 = new Panel();
		panel_2.setBounds(0, 319, 451, 115);
		contentPane.add(panel_2);
		
		Panel panel_3 = new Panel();
		panel_3.setBounds(457, 319, 209, 115);
		contentPane.add(panel_3);
		setVisible(true);
	}

	@Override
	public void update() {
		playingField.repaint();		
	}

}
