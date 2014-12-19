package de.htwg.towerdefence.model.way.impl;

import java.util.LinkedList;
import java.util.List;

import de.htwg.towerdefence.model.IPlayingField;
import de.htwg.towerdefence.model.way.ICheckWay;
import de.htwg.towerdefence.model.way.IGraph;
import de.htwg.towerdefence.util.way.Coord;

public class CheckWay implements ICheckWay {
	
	// Saves the List for the Calculation
	private IGraph<Integer> g; 
	// Calculate the shortest way
	private DijkstraShortestPath<Integer> path; 
	private int sizeX;
	private int sizeY;

	public CheckWay() { 
		this.g = new AdjacencyListUndirectedGraph<Integer>();
		this.path = new DijkstraShortestPath<Integer>(this.g);
	}
	
	// Initialize the Graph with the Way
	public void initWayPoints(int sizeX, int sizeY) {
		// X Field Size
		this.sizeX = sizeX; 
		// Y Field Size
		this.sizeY = sizeY;       
		
		// Add Fields to the List
		for(int i = 1; i <= (sizeX * sizeY); ++i) {
			  g.addVertex(i);
		}
		for(int i = 1; i <= (sizeX * sizeY); ++i) {
		   if((i%sizeX) != 0) {
			 g.addEdge(i, i + 1);
		   }
		   if(i <= (sizeX*sizeY)-sizeX) {
			 g.addEdge(i, i + this.sizeX);
		   }
		}
	}
	
	// Deletes one Point on the List
	public boolean deleteWayPoint(int x, int y) {
		return this.g.deleteAllEdgeOn(this.getNumberofVertex(x, y));
	}
	
	// Adds a wayPoint to the list
	public boolean addWayPoint(int x, int y) {
		int vertex = this.getNumberofVertex(x, y);
		boolean check = false;
		if((vertex % sizeX) != 0) {
		   g.addEdge(vertex,vertex+1);
		   check = true;
		}
		if(vertex<=(sizeX * sizeY) - sizeX) {
		   g.addEdge(vertex, vertex + this.sizeX);
		   check = true;
		}
		if(vertex - this.sizeX > 0) {
			g.addEdge(vertex, vertex - this.sizeX);
			check = true;
		}
		if((vertex - 1) % this.sizeX != 0) {
			g.addEdge(vertex, vertex - 1);
			check = true;
		}
		return check;
	}
	
	// Returns the Number of the Vertex
	public int getNumberofVertex(int x, int y) {
		if(this.sizeX > x && this.sizeY > y) {
		return ((this.sizeX) * (y + 1)) - ((this.sizeX - 1) - x);
		}
		return 0;
	}	
	
	// Returns the Coord of the Vertex
	public Coord getCoordOfVertex(int vertex) {
		Coord c = new Coord();
		if((this.sizeX * this.sizeY >= vertex) && (vertex > 0)) {
		  c.setY((vertex - 1) / this.sizeX);
		  c.setX(((vertex - 1) - this.sizeX * c.getY()));
		  return c;
		}
		return null;
	}
	
	// Proof if there is a way
	public boolean existWay(int startX, int startY, int endX, int endY) {
		int vertex1 = this.getNumberofVertex(startX, startY);
		int vertex2 = this.getNumberofVertex(endX, endY);
		return this.path.searchShortestPath(vertex1, vertex2);
	}
	
	// Gets a List of Coords with the shortest way
	public List<Coord> getShortesWay() {
		List<Integer> l = this.path.getShortestPath();
		List<Coord> c = new LinkedList<Coord>();
		if(l != null) {
			for(int i = 0; i < l.size(); ++i) {
				c.add(this.getCoordOfVertex(l.get(i)));
			}
			return c;
		}
		return null;
	}

	@Override
	public void initCheckWayWithPlayingField(IPlayingField field) {
		this.initWayPoints(field.getSizeX(), field.getSizeY());
		Coord coord = new Coord();
		for(int y = 0; y < field.getSizeY(); ++y) {
			for(int x = 0; x < field.getSizeX(); ++x) {
				coord.setX(x);
				coord.setY(y);
				if(field.isSetTower(coord)) {
					this.deleteWayPoint(x, y);
				}
			}
		}
	}
}
