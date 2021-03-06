package de.htwg.towerdefence.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import de.htwg.towerdefence.controller.IGameController;
import de.htwg.towerdefence.util.enums.FieldType;
import de.htwg.towerdefence.util.way.Coord;

public class PlayingFieldGui extends JPanel implements MouseListener {

	private static final long serialVersionUID = 4166754566794976199L;
	
	/** Instance of the GuiState */
	private GuiState guiState;
	private PropertiesGui propertiesGui;
	
	private IGameController controller;
	private Coord coord;
    private Coord coord2;
    private int width;
    private int height;
    private int spaceHeight;
    private int spaceWidth;
	
	public PlayingFieldGui(IGameController controller, GuiState guiState, PropertiesGui propertiesGui, int width, int height) {
		this.controller = controller;
		this.guiState = guiState;
		this.propertiesGui = propertiesGui;
		coord = new Coord();
		coord2 = new Coord();
		this.width = width;
		this.height = height;
		spaceHeight = this.height / controller.getSizeYOfPlayingField();
		spaceWidth = this.width / controller.getSizeXOfPlayingField();
		addMouseListener(this);
	}
	
	public void paintFilledRec(Graphics g, Coord coord, Color color) {
		g.setColor(color);
	    g.fillRect(spaceWidth*(coord.getX()-1), spaceHeight*(coord.getY()-1), spaceWidth, spaceHeight);
	    g.setColor(Color.black);
	}
	
	public void paintPlayingField(Graphics g) {
		
		for (int i = 1; i <= controller.getSizeYOfPlayingField()+1; ++i) {
	    	g.drawLine( spaceWidth * i, 0, spaceWidth * i, spaceHeight * (controller.getSizeYOfPlayingField()));
	    }
		
		for (int i = 1; i <= controller.getSizeXOfPlayingField()+1; ++i) {
	    	g.drawLine( 0, spaceHeight * i, spaceWidth * (controller.getSizeXOfPlayingField()), spaceHeight * i );
	    }
	}

	@Override
	  protected void paintComponent( Graphics g ) {
	    super.paintComponent( g );
	    paintPlayingField(g);
	    
	    for (int y = 0; y < controller.getSizeYOfPlayingField(); ++y) {
	    	for (int x = 0; x < controller.getSizeXOfPlayingField(); ++x) {
	    		coord.setX(x);
	    		coord.setY(y);
	    		coord2.setX(x+1);
	    		coord2.setY(y+1);
	    		if (controller.getTypeOfPlayingField(coord) == FieldType.MOB) {
	    			paintFilledRec(g, coord2, Color.blue);
	    		} else if (controller.getTypeOfPlayingField(coord) == FieldType.TOWER) {
	    			paintFilledRec(g, coord2, Color.red);
	    		}
	    	}
	    }  
	  }

	@Override
	public void mouseClicked(MouseEvent arg0) {	
		int cordx = arg0.getX() / spaceWidth;
		int cordy = arg0.getY() / spaceHeight;
		
		switch(guiState.getState()) {
			case SETTOWER:
				controller.setTowerToPostion(cordx, cordy);
				break;
			case SHOWINFOS:
				showInfos(cordx, cordy);
				break;
			default:
				// Do nothing
				break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// Nothing do do in here
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// Nothing do do in here
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// Nothing do do in here
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// Nothing do do in here
	}
	
	private void showInfos(int cordx, int cordy) {
		if (controller.isTowerOnField(cordx, cordy)) {
			propertiesGui.updateTower(cordx, cordy);
		} else if (controller.isMobOnField(cordx, cordy)) {
			propertiesGui.updateMob(cordx, cordy);
		}
	}
}
