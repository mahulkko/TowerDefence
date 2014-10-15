package de.htwg.towerdefence.model.way.impl;

import junit.framework.TestCase;
import java.io.IOException;
import java.util.List;
import de.htwg.towerdefence.model.way.IUndirectedGraph;
import de.htwg.towerdefence.model.way.impl.AdjacencyListUndirectedGraph;
import de.htwg.towerdefence.util.way.Edge;


public class AdjacencyListUndirectedGraphTest extends TestCase {
	
	private IUndirectedGraph<Integer> g;
	
	public void setUp() throws IOException {
		this.g =  new AdjacencyListUndirectedGraph<Integer>();
	}
	
	public void testDoc() {
		
		// Add Vertex
		assertEquals(true,this.g.addVertex(1));
		assertEquals(true,this.g.addVertex(2));
		assertEquals(false,this.g.addVertex(1));
		
		// Add Edge
		assertEquals(true,this.g.addEdge(1, 2));
		assertEquals(false,this.g.addEdge(1, 2));
		assertEquals(false,this.g.addEdge(1, 3));
		assertEquals(false,this.g.addEdge(3, 2));
		
		// Delete all Edges on Vertex
		assertEquals(true,this.g.deleteAllEdgeOn(1));
		assertEquals(false,this.g.deleteAllEdgeOn(3));
		
		// Delete Edge
		assertEquals(true,this.g.addVertex(3));
		assertEquals(true,this.g.addVertex(4));
		assertEquals(true,this.g.addEdge(1, 2));
		assertEquals(true,this.g.addEdge(1, 3));
		assertEquals(true,this.g.addEdge(2, 3));
		assertEquals(true,this.g.addEdge(3, 4));
		assertEquals(false,this.g.delteEdge(1, 5));
		assertEquals(true,this.g.delteEdge(1, 2));
		
		// Contains Vertex
		assertEquals(true,this.g.containsVertex(1));
		assertEquals(false,this.g.containsVertex(6));
		
		// Get weight
		assertEquals(true,this.g.addEdge(2, 2));
		assertEquals(1.0,this.g.getWeight(1, 3));
		assertEquals(0.0,this.g.getWeight(1, 5));
		assertEquals(0.0,this.g.getWeight(5, 1));
		assertEquals(0.0,this.g.getWeight(5, 5));
		assertEquals(0.0,this.g.getWeight(2, 2));
		assertEquals(0.0,this.g.getWeight(1, 2));
		assertEquals(0.0,this.g.getWeight(2, 2));
		
		// Get Number of Vertex
		assertEquals(4,this.g.getNumberOfVertexes());
		
		// Get Vertex List
		List<Integer> list = this.g.getVertexList();
		assertEquals(list,this.g.getVertexList());
		
		// Get Edge List
		List<Edge<Integer>> edge = this.g.getEdgeList();
		assertEquals(edge,this.g.getEdgeList());
		
		// Get Adjacent Vertex List
		list = this.g.getAdjacentVertexList(10);
		assertEquals(list,this.g.getAdjacentVertexList(10));
		
		// Get getIncidentEdgeList
		edge = this.g.getIncidentEdgeList(10);
		assertEquals(edge,this.g.getIncidentEdgeList(10));
		edge = this.g.getIncidentEdgeList(1);
		assertEquals(edge,this.g.getIncidentEdgeList(1));
		
		// Get Degree
		assertEquals(1,this.g.getDegree(1));
		assertEquals(-1,this.g.getDegree(10));
		
		
		
	}
}
