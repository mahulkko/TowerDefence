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
	
	public PlayingFieldGui(IGameController controller) {
		this.controller = controller;
		addMouseListener(this);
	}
	
	public void paintFilledRec(Graphics g, Coord coord, Color color) {
		g.setColor(color);
	    g.fillRect(20*coord.getX(),20*coord.getY(),20,20);
	    g.setColor(Color.black);
	}

	@Override
	  protected void paintComponent( Graphics g )
	  {
	    super.paintComponent( g );
	    for (int i = 1; i < controller.getSizeYOfPlayingField(); ++i) {
	    	g.drawLine( 20*i, 20, 20*i, 200 );
	    }
	    
	    for (int i = 1; i < controller.getSizeXOfPlayingField(); ++i) {
	    	g.drawLine( 20, 20*i, 200, 20*i );
	    }
	    
	    Coord coord = new Coord();
	    for (int y = 0; y < controller.getSizeYOfPlayingField(); ++y) {
	    	for (int x = 0; x < controller.getSizeXOfPlayingField(); ++x) {
	    		coord.setX(x);
	    		coord.setY(y);
	    		if (controller.getTypeOfPlayingField(coord) == FieldType.MOB) {
	    			paintFilledRec(g, coord, Color.blue);
	    		} else if (controller.getTypeOfPlayingField(coord) == FieldType.TOWER) {
	    			paintFilledRec(g, coord, Color.red);
	    		}
	    	}
	    }
	    //paintFilledRec(g, new Coord(x,y), Color.blue);   
	  }

	@Override
	public void mouseClicked(MouseEvent arg0) {		
//		this.x = (arg0.getX())/20;
//		this.y = (arg0.getY())/20; 
//		this.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
