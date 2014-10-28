package de.htwg.towerdefence.TowerDefence;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class PlayingFieldGui extends JPanel implements MouseListener{

	private static final long serialVersionUID = 4166754566794976199L;

	@Override
	  protected void paintComponent( Graphics g )
	  {
	    super.paintComponent( g );
	    for (int i = 1; i < 11; ++i) {
	    	g.drawLine( 20*i, 20, 20*i, 200 );
	    }
	    
	    for (int i = 1; i < 11; ++i) {
	    	g.drawLine( 20, 20*i, 200, 20*i );
	    }
	    
	  }

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("Mouse X: " + arg0.getX());
		System.out.println("Mouse Y: " + arg0.getY());
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
