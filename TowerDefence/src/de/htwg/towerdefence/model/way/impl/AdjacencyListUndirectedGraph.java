package de.htwg.towerdefence.model.way.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import de.htwg.towerdefence.model.way.IUndirectedGraph;
import de.htwg.towerdefence.util.way.Edge;

/**
 * <b>AdjacencyListUndirectedGraph class</b>
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public class AdjacencyListUndirectedGraph<V> implements IUndirectedGraph<V>{

	/************************************************************
	 * Private variables
	 ***********************************************************/
	
	/** Logger for log4j connection */
    private static final Logger LOG = Logger.getLogger("TowerDefence.Model.Way.AdjacencyListUndirectedGraph");
    
	/** Adjacency List */
	private Map<V,HashMap<V,Double>> adjacencyList = new HashMap<V,HashMap<V,Double>>();
	
	/**	Edge list */
	private List<Edge<V>> edgeList = new LinkedList<Edge<V>>();

	
	/************************************************************
	 * Public methods
	 ***********************************************************/
	
	@Override
	public boolean addVertex(V v) {
		if (!adjacencyList.containsKey(v)) {
			LOG.info("Add a new vertex to list");
			adjacencyList.put(v, new HashMap<V, Double>());
			return true;
		}
		LOG.info("Can't add a vertex to list - List contains already this vertex");
		return false;
	}

	@Override
	public boolean addEdge(V v, V w) {
		return addEdge(v, w, 1.0);
	}

	@Override
	public boolean addEdge(V v, V w, double weight) {
		if (adjacencyList.containsKey(v) && adjacencyList.containsKey(w)) {

			// Edge already in the list
			if (adjacencyList.get(v).containsKey(w)) {
				LOG.info("Can't add a new Edge with weight: " + weight + " - Edge already exists");
				return false;
			}
			
			// Put in hashmap of v
			adjacencyList.get(v).put(w, weight);
			
			// Put in hashmap of w
			adjacencyList.get(w).put(v, weight);
			
			// Add new edge in edgelist
			edgeList.add(new Edge<V>(v ,w));
			edgeList.add(new Edge<V>(w, v));
			
			LOG.info("Added new Edge with weight: " + weight);
			return true;
		}
		LOG.info("Can't add a new Edge with weight: " + weight + " - List didn't contains these vertexes");
		return false;				
	}
	
	@Override
	public boolean deleteAllEdgeOn(V v) {
		if (this.adjacencyList.containsKey(v)) {
			List<V> l = this.getAdjacentVertexList(v);
			for (Iterator<V> i = l.iterator(); i.hasNext();) {
				 this.delteEdge(v, i.next());
			   }
			LOG.info("Deleted all edges on these vertex");
			return true;
		}
		LOG.info("Can't delete all edges on vertex - List didn't contain this vertex");
		return false;
	}
	
	@Override
	public boolean delteEdge(V v, V w){
		if (this.containsEdge(v, w)) {
			this.adjacencyList.get(v).remove(w);
			this.adjacencyList.get(w).remove(v);
			
			for (int i = 0; i < this.getNumberOfEdges(); ++i) {
				Edge<V> e = this.edgeList.get(i);
				 if (e.getSource() == v && e.getTarget() == w) {
					 this.edgeList.remove(i);
				 }
			}
			for (int i = 0; i < this.getNumberOfEdges(); ++i) {
				Edge<V> e = this.edgeList.get(i);
				 if (e.getSource() == w && e.getTarget() == v) {
					 this.edgeList.remove(i);
				 }
			}
			LOG.info("Edge successful deleted");
			return true;
		}
		LOG.info("Can't delete edge - Edge didn't exists");
		return false;
	}
	
	@Override
	public boolean containsVertex(V v) {
		if (this.adjacencyList.containsKey(v)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean containsEdge(V v, V w) {
		if (this.adjacencyList.get(v).containsKey(w)) {
			return true;
		}
		return false;
	}

	@Override
	public double getWeight(V v, V w) {
		if (adjacencyList.containsKey(v) && adjacencyList.containsKey(w)) {
			if (!adjacencyList.get(v).containsKey(w) || v.equals(w)) {
				return 0.0;
			}
		return adjacencyList.get(v).get(w);
		}
		return 0.0;
	}

	@Override
	public int getNumberOfVertexes() {
		return adjacencyList.size();
	}

	@Override
	public int getNumberOfEdges() {
		return edgeList.size();
	}

	@Override
	public List<V> getVertexList() {
		List<V> vertices = new LinkedList<V>();
		for (Map.Entry<V, HashMap<V, Double>> e : adjacencyList.entrySet()) {
			vertices.add(e.getKey());
		}
		return vertices;
	}

	@Override
	public List<Edge<V>> getEdgeList() {
		return new LinkedList<Edge<V>>(edgeList);
	}

	@Override
	public List<V> getAdjacentVertexList(V v) {
		List<V> vertices = new LinkedList<V>();
		if (adjacencyList.containsKey(v)) {
			Map<V, Double> hm = adjacencyList.get(v);
			
			for (Map.Entry<V, Double> e : hm.entrySet()) {
				vertices.add(e.getKey());
			}
		}
	return vertices;
	}

	@Override
	public List<Edge<V>> getIncidentEdgeList(V v) {
		List<Edge<V>> edges = new LinkedList<Edge<V>>();
		if (adjacencyList.containsKey(v)) {
			for (Edge<V> e : edgeList ) {
				if (e.getSource().equals(v)) {
					edges.add(e);
				}
			}
		}
	return edges;
	}

	@Override
	public int getDegree(V v) {
		if (adjacencyList.containsKey(v)) {
			return adjacencyList.get(v).size();
		}
	return -1;
	}

}

