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
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;

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
		setBounds(100, 100, 688, 447);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(70, 130, 180));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = playingField;
		//Panel panel = new Panel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 451, 313);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(new Color(224, 255, 255));
		panel_1.setBounds(457, 0, 209, 313);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblEigenschaften = new JLabel("Eigenschaften");
		lblEigenschaften.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblEigenschaften.setBounds(10, 11, 171, 23);
		panel_1.add(lblEigenschaften);
		
		JLabel lblSpeed = new JLabel("Speed: 500");
		lblSpeed.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSpeed.setBounds(20, 36, 179, 23);
		panel_1.add(lblSpeed);
		
		JLabel lblDamage = new JLabel("Damage: 200");
		lblDamage.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDamage.setBounds(20, 61, 179, 23);
		panel_1.add(lblDamage);
		
		JLabel lblRange = new JLabel("Range: 3");
		lblRange.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblRange.setBounds(20, 86, 179, 23);
		panel_1.add(lblRange);
		
		JButton btnUpgrade = new JButton("Upgrade");
		btnUpgrade.setBounds(110, 279, 89, 23);
		panel_1.add(btnUpgrade);
		
		JLabel lblKostenTd = new JLabel("Kosten: 500 TD");
		lblKostenTd.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblKostenTd.setBounds(10, 283, 90, 14);
		panel_1.add(lblKostenTd);
		
		Panel panel_2 = new Panel();
		panel_2.setBackground(new Color(224, 255, 255));
		panel_2.setBounds(0, 319, 451, 82);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnSendMob = new JButton("Send Mob");
		btnSendMob.setBounds(10, 45, 107, 23);
		panel_2.add(btnSendMob);
		
		JButton btnTower = new JButton("Set Tower");
		btnTower.setBounds(10, 11, 107, 23);
		panel_2.add(btnTower);
		
		JButton btnPause = new JButton("Pause");
		btnPause.setBounds(352, 11, 89, 23);
		panel_2.add(btnPause);
		
		Panel panel_3 = new Panel();
		panel_3.setBackground(new Color(224, 255, 255));
		panel_3.setBounds(457, 319, 209, 82);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblPlayerMartin = new JLabel("Player: Martin");
		lblPlayerMartin.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblPlayerMartin.setBounds(10, 11, 189, 22);
		panel_3.add(lblPlayerMartin);
		
		JLabel lblLife = new JLabel("Life: 10");
		lblLife.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLife.setBounds(10, 27, 189, 22);
		panel_3.add(lblLife);
		
		JLabel lblMoneyTd = new JLabel("Money: 1000 TD");
		lblMoneyTd.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblMoneyTd.setBounds(10, 44, 189, 22);
		panel_3.add(lblMoneyTd);
		setVisible(true);
	}

	@Override
	public void update() {
		playingField.repaint();		
	}
}
