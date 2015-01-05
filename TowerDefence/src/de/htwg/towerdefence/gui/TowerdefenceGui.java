package de.htwg.towerdefence.gui;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import de.htwg.towerdefence.controller.IGameController;
import de.htwg.towerdefence.util.control.IObserver;
import java.awt.Color;

public class TowerdefenceGui extends JFrame implements IObserver{

	private static final long serialVersionUID = -8113018678873313054L;
	private PlayingFieldGui playingFieldGui;
	private PropertiesGui propertiesGui;
	private ControlGui controlGui;
	private PlayerGui playerGui;
	
	private JPanel contentPane;
	private JPanel playingFieldPanel;
	private JPanel propertiesPanel;
	private JPanel controlPanel;
	private JPanel playerPanel;
	private GuiState guiState;
	
	private static final int WIDTH = 450;
	private static final int HEIGHT = 310;
	private static final int BORDER = 5;
	private static final int BOUNDS = 0;

	/**
	 * Create the frame.
	 */
	public TowerdefenceGui(IGameController controller) {
		guiState = new GuiState(); 
		
		propertiesGui = new PropertiesGui(controller, guiState);
		playingFieldGui = new PlayingFieldGui(controller, guiState, propertiesGui, WIDTH, HEIGHT);
		controlGui = new ControlGui(controller, guiState);
		playerGui = new PlayerGui(controller);
		
		// Default Settings GUI
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 447);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(70, 130, 180));
		contentPane.setBorder(new EmptyBorder(BORDER, BORDER, BORDER, BORDER));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// PlayingField
		playingFieldPanel = playingFieldGui;
		playingFieldPanel.setBackground(new Color(255, 255, 255));
		playingFieldPanel.setBounds(BOUNDS, BOUNDS, WIDTH, HEIGHT);
		contentPane.add(playingFieldPanel);
		playingFieldPanel.setLayout(null);
		
		
		// Properties
		propertiesPanel = propertiesGui;
		propertiesPanel.setBackground(new Color(224, 255, 255));
		propertiesPanel.setBounds(457, 0, 209, 310);
		contentPane.add(propertiesPanel);
		propertiesPanel.setLayout(null);
		
		
		// Controll
		controlPanel = controlGui;
		controlPanel.setBackground(new Color(224, 255, 255));
		controlPanel.setBounds(0, 319, 451, 82);
		contentPane.add(controlPanel);
		controlPanel.setLayout(null);
		
		//Player
		playerPanel = playerGui;
		playerPanel.setBackground(new Color(224, 255, 255));
		playerPanel.setBounds(457, 319, 209, 82);
		contentPane.add(playerPanel);
		playerPanel.setLayout(null);
		
		setVisible(true);
	}

	@Override
	public void update() {
		playingFieldGui.repaint();
		playerGui.updatePlayerData();
	}
}
