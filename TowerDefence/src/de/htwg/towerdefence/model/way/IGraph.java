package de.htwg.towerdefence.model.way;

import java.util.List;

import de.htwg.towerdefence.util.way.Edge;

/**
* <b>Interface IGraph</b>
* @author Christoph Knetschke and Martin Hulkkonen
*/
public interface IGraph<V> {
	
	/**
	 * <b>Add a node to the graph</b>
	 * @param v - Vertex to add
	 * @return Return <b>true</b> when the vertex can be add, otherwise <b>false</b>
	 */
	boolean addVertex(V v);
 
	/**
	 * <b>Adds a new edge to the graph with the weight of 1.0</b>
	 * @param v - Start vertex
	 * @param w - End vertex
	 * @return Returns <b>true</b> when the edge can be added, otherwise <b>false</b>
	 */
	boolean addEdge(V v, V w);
	 
	/**
	 * <b>Adds a new edge to the graph with the given weight</b>
	 * @param v - Start vertex
	 * @param w - End vertex
	 * @param weight - Weight of the edge
	 * @return Returns <b>true</b> when the edge can be added, otherwise <b>false</b>
	 */
	boolean addEdge(V v, V w, double weight);
	
	/**
	 * <b>Deletes a edge between two vertexes</b>
	 * @param v - Start vertex
	 * @param w - End vertex
	 * @return Returns <b>true</b> when the edge can be deleted, otherwise <b>false</b>
	 */
	boolean delteEdge(V v, V w); 
	 
	/**
	 * <b>Deletes all edges that references to this point</b>
	 * @param v - Vertex
	 * @return Returns <b>true</b> when the edges can be deleted, otherwise <b>false</b>
	 */
	boolean deleteAllEdgeOn(V v); 
	 
	/**
	 * <b>Check if the graph contains this vertex</b>
	 * @param v - Vertex
	 * @return Returns <b>true</b> when the vertex is in the graph, otherwise <b>false</b>
	 */
	boolean containsVertex(V v);  
	 
	/**
	 * <b>Check if the graph contains these edge between two vertexes</b>
	 * @param v - Start vertex
	 * @param w - End vertex
	 * @return Returns <b>true</b> when the edge exists, otherwise <b>false</b>
	 */
	boolean containsEdge(V v, V w);
	 
	/**
	 * <b>Get the weight of the edge between two vertexes</b>
	 * @param v - Start vertex
	 * @param w - End vertex
	 * @return Returns the weight of the vertex
	 */
	double getWeight(V v, V w);  
	 
	/**
	 * <b>Returns the number of vertexes in a graph</b>
	 * @return Returns a number of vertexes in the graph
	 */
	int getNumberOfVertexes(); 
	 
	/**
	 * <b>Get the number of edges in a graph</b>
	 * @return Returns the number of edges in one graph
	 */
	int getNumberOfEdges();  
	 
	/**
	 * <b>Get a list of all vertexes in the graph</b>
	 * @return Returns a list of vertexes
	 */
	List<V> getVertexList();  
	
	/**
	 * <b>Get a list of all edges in the graph</b>
	 * @return Returns a list of edges
	 */
	List<Edge<V>> getEdgeList();  
	 
	/**
	 * <b>Get all neighbors vertexes from one vertex</b>
	 * @param v - Vertex
	 * @return Returns a list of all neighbors vertexes
	 */
	List<V> getAdjacentVertexList(V v);  
	 
	/**
	 * <b>Get all neighbors edges from one vertex</b>
	 * @param v - Vertex
	 * @return Returns a list of all neighbors edges
	 */
	List<Edge<V>> getIncidentEdgeList(V v);
 
}

