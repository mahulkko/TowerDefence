package de.htwg.towerdefence.model.way.impl;

import java.io.IOException;
import java.util.List;

import de.htwg.towerdefence.model.way.IGraph;
import de.htwg.towerdefence.model.way.impl.AdjacencyListUndirectedGraph;
import de.htwg.towerdefence.model.way.impl.DijkstraShortestPath;
import junit.framework.TestCase;

public class DijkstraShortestPathTest extends TestCase {
	
	DijkstraShortestPath<Integer> path;
	IGraph<Integer> g;
	
	public void setUp() throws IOException {
		this.g = new AdjacencyListUndirectedGraph<Integer>();
		this.path = new DijkstraShortestPath<Integer>(this.g);
		int sizeX = 7;
		int sizeY = 6;
		
		for(int i = 1; i<=(sizeX*sizeY); i++) {
			  g.addVertex(i);
		}
		for(int i = 1; i<=(sizeX*sizeY); i++) {
		   if((i%sizeX) != 0) {
			 g.addEdge(i,i+1);
		   }
		   if(i<=(sizeX*sizeY)-sizeX) {
			 g.addEdge(i,i+sizeX);
		   }
		}
		
	}
	
	public void testDoc() {
		
		assertEquals(true,this.path.searchShortestPath(1, 7));
		assertEquals(false,this.path.searchShortestPath(1, 1));
		assertEquals(true,this.path.searchAllShortestPaths(1));
		assertEquals(10.0,this.path.getDistance());
		assertEquals(6.0,this.path.getDistanceTo(7));
		List<Integer> list = this.path.getShortestPath();
		assertEquals(list,this.path.getShortestPath());
		list = this.path.getShortestPathTo(7);
		assertEquals(list,this.path.getShortestPathTo(7));
		
		g.deleteAllEdgeOn(8);
		g.deleteAllEdgeOn(2);
		
		assertEquals(false,this.path.searchShortestPath(1, 7));
		assertEquals(false,this.path.searchAllShortestPaths(1));
		assertEquals(-1.0,this.path.getDistanceTo(7));
		assertEquals(null,this.path.getShortestPath());
		assertEquals(null,this.path.getShortestPath());
		assertEquals(null,this.path.getShortestPathTo(7));
	}
}