package de.htwg.towerdefence.model.way;

/**
* <b>IUndirectedGraph</b>
* @author Christoph Knetschke and Martin Hulkkonen
*/
public interface IUndirectedGraph<V> extends IGraph<V> {
	
	/**
	 * <b>Get the value of the node</b>
	 * @param v - Node
	 * @return Returns the value of the node
	 */
    int getDegree(V v); 
}

