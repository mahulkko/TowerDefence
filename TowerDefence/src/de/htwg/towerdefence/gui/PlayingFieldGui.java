package de.htwg.towerdefence.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import de.htwg.towerdefence.controller.IGameController;
import de.htwg.towerdefence.util.enums.FieldType;
import de.htwg.towerdefence.util.way.Coord;

public class PlayingFieldGui extends JPanel implements MouseListener{

	private static final long serialVersionUID = 4166754566794976199L;
	
	private IGameController controller;
	Coord coord = new Coord();
    Coord coord2 = new Coord();
	
	public PlayingFieldGui(IGameController controller) {
		this.controller = controller;
		addMouseListener(this);
	}
	
	public void paintFilledRec(Graphics g, Coord coord, Color color) {
		g.setColor(color);
	    g.fillRect(20*coord.getX(),20*coord.getY(),20,20);
	    g.setColor(Color.black);
	}
	
	public void paintPlayingField(Graphics g) {
		for (int i = 1; i <= controller.getSizeYOfPlayingField()+1; ++i) {
	    	g.drawLine( 20 * i, 20, 20 * i, 20 * (controller.getSizeYOfPlayingField()+1));
	    }
		
		for (int i = 1; i <= controller.getSizeXOfPlayingField()+1; ++i) {
	    	g.drawLine( 20, 20 * i, 20 * (controller.getSizeXOfPlayingField()+1), 20 * i );
	    }
	}

	@Override
	  protected void paintComponent( Graphics g )
	  {
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

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
}
