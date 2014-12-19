package de.htwg.towerdefence.model.way;

import java.util.List;

import de.htwg.towerdefence.model.IPlayingField;
import de.htwg.towerdefence.util.way.Coord;

/**
* <b>Interface ICheckWay</b>
* @author Christoph Knetschke and Martin Hulkkonen
*/
public interface ICheckWay {
	
	/**
	 * <b>Initialize the way points with the given size</b>
	 * @param sizeX - X Coordinates
	 * @param sizeY - Y Coordinates
	 */
	void initWayPoints(int sizeX, int sizeY);
	
	/**
	 * <b>Deletes one way point at the position</b>
	 * @param x - X Coordinates
	 * @param y - Y Coordinates
	 * @return Returns <b>true</b> when the way point is deleted, otherwise <b>false</b>
	 */
	boolean deleteWayPoint(int x, int y);
	
	/**
	 * <b>Returns the number of the vertex</b>
	 * @param x - X Coordinates
	 * @param y - Y Coordinates
	 * @return Returns the number of the vertex, otherwise 0
	 */
	int getNumberofVertex(int x, int y);
	
	/**
	 * <b>Checks if there exists a way between these two coordinates</b>
	 * @param startX - X Begin coordinate
	 * @param startY - Y Begin coordinate
	 * @param endX - X End coordinate
	 * @param endY - Y End coordinate
	 * @return Returns <b>true</b> when there is a way, otherwise <b>false</b>
	 */
	boolean existWay(int startX, int startY, int endX, int endY);
	
	/**
	 * <b>Returns the coordinates from a given vertex</b>
	 * @param vertex - Vertex for calculating the coordinate
	 * @return Returns the coordinates of the vertex
	 */
	Coord getCoordOfVertex(int vertex);
	
	/**
	 * <b>Get a list of coordinates with the shortest way between these points</b>
	 * @return Returns a list of coordinates from the shortest way
	 */
	List<Coord> getShortesWay();
	
	/**
	 * <b>Adds a new way point to the list</b>
	 * @param x - X Coordinates
	 * @param y - Y Coordinates
	 * @return Returns <b>true</b> when the new way point can be added, otherwise <b>false</b>
	 */
	boolean addWayPoint(int x, int y);
	
	/**
	 * <b>Initialize the CheckWay Context with the values of a playing field</b>
	 * @param field - Playingfield with the state
	 */
	void initCheckWayWithPlayingField(IPlayingField field);
}
