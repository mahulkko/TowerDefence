package de.htwg.towerdefence.model.way.impl;

import java.util.*;
import de.htwg.towerdefence.model.way.IGraph;



public class DijkstraShortestPath<V> {
	
	private IGraph<V> graph;
	private LinkedList<V> shortestpath;
	private double dist;
	private Map<V,List<V>> all;
	private Map<V, Double> distances;
	

	public DijkstraShortestPath(IGraph<V> g) {
		this.graph = g;
		shortestpath = new LinkedList<V>();
		all= new HashMap<V,List<V>>();
		distances = new HashMap<V,Double>();
	}
	
	
	public boolean searchShortestPath(V s, V g) {
		if(g.equals(s)) {
			return false;
		}
		shortestpath.clear();
		Set<V> kl = new TreeSet<V>();
		Map<V,Double> d = new HashMap<V,Double>();
		Map<V,V> p = new HashMap<V,V>();
		
		for(V v : graph.getVertexList()) {
			d.put(v, -1.0);
			p.put(v, null);
		}
		
		d.put(s, 0.0);
		kl.add(s);
		
		while(!kl.isEmpty()) {
			V v = getMinDist(d, kl);
			kl.remove(v);
			
			for(V w : graph.getAdjacentVertexList(v)) {
				if(d.get(w) < 0) {
					kl.add(w);
					d.put(w, d.get(v) + graph.getWeight(v,w)); 
					p.put(w,v);
					continue;
				}
				if(d.get(v) + graph.getWeight(v,w) < d.get(w) ) {
					d.put(w, d.get(v) + graph.getWeight(v,w));
					p.put(w,v);
				}
			}
		}
		
		shortestpath.offerFirst(g);
		while(shortestpath.getFirst() != s) {
			V next = p.get(shortestpath.getFirst());
			if(next == null) {
				shortestpath.clear();
				return false;
			}
			shortestpath.offerFirst(next);
		}
		dist= d.get(g);
		return true;
	}
	
	private V getMinDist(Map<V,Double> d, Set<V> kl) {
		V min = null;
		double minval = 0;
		
		for(V v : kl) {
			double tmp = d.get(v);
			if(tmp >= 0 && (min == null || tmp < minval) ) {
				min = v;
				minval = tmp;
			}
		}
		
		if(min == null) {
			return null;
		}
		return min;
	}
	
	public boolean searchAllShortestPaths(V s) {
		all.clear();
		distances.clear();
		for(V v : graph.getVertexList()) {
			if(searchShortestPath(s,v)) {
				all.put(v,new LinkedList<V>(shortestpath));
				distances.put(v,dist);
			}
		}
		return !all.isEmpty();
	}
	
	public double getDistance() {
		return dist;
	}
	
	public double getDistanceTo(V g) {
		if(all.isEmpty()) {
			return -1.0;
		}
		return distances.get(g);
	}
	
	public List<V> getShortestPath() {
		if(shortestpath.isEmpty()) {
			return new LinkedList<V>();
		}		
		return shortestpath;
	}
	
	public List<V> getShortestPathTo(V g) {
		if(all.isEmpty()) {
			return new LinkedList<V>();
		}		
		return all.get(g);
	}
}
