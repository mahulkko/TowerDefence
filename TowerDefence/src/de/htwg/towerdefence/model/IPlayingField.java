package de.htwg.towerdefence.model;

import java.util.List;
import de.htwg.towerdefence.util.enums.FieldType;
import de.htwg.towerdefence.util.way.Coord;

/**
 * <b>Interface IPlayingField</b>
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public interface IPlayingField {
	
	/**
	 * <b>Initialize the playing field</b>
	 * @param sizeX - Size of the playing field on x
	 * @param sizeY - Size of the playing field on y
	 */
    void initPlayingField(int sizeX, int sizeY);
    
    /**
     * <b>Set a new tower on the playing field</b>
     * @param coord - Coordinates of the tower
     * @param tower - Tower where to set
     * @return Returns <b>true</b> when the tower is set or <b>false</b> when the tower can't be set
     */
	boolean setTower(Coord coord, ITower tower);
	
	/**
	 * <b>Returns the tower on the selected playing field</b>
     * @param coord - Coordinates of the tower
	 * @return Returns the tower instance or null pointer
	 */
	ITower getTower(Coord coord);
	
	/**
	 * <b>Delete the tower on the selected playing field</b>
     * @param coord - Coordinates of the tower
	 * @return Returns the tower instance or null pointer
	 */
	ITower deleteTower(Coord coord);
	
	/**
	 * <b>Checks if there is set a tower in the selected playing field</b>
     * @param coord - Coordinates of the tower
	 * @return Returns true when there is set a tower otherwise false 
	 */
	boolean isSetTower(Coord coord);
	
	/**
	 * <b>Set a mob on the selected playing field</b>
     * @param coord - Coordinates of the tower
	 * @param mob - Instance of the mob where to set
	 * @return Returns true when the mob is set on the playing field otherwise false
	 */
	boolean setMob(Coord coord, IMob mob);
	
	/**
	 * <b>Returns the mob on the selected playing field</b>
     * @param coord - Coordinates of the tower
	 * @return Returns the mob instance or null pointer
	 */
	IMob getMob(Coord coord, IMob mob);
	
	/**
	 * <b>Delete the mob on the selected playing field</b>
     * @param coord - Coordinates of the tower
	 * @return Returns the mob instance or null pointer
	 */
	IMob deleteMob(Coord coord, IMob mob);
	
	/**
	 * <b>Checks if there is set the mob in the selected playing field</b>
     * @param coord - Coordinates of the tower
	 * @return Returns true when there is set the mob otherwise false 
	 */
	boolean isSetMob(Coord coord, IMob mob);
	
	/**
	 * <b>Set a list of mobs on the selected playing field</b>
     * @param coord - Coordinates of the tower
	 * @param mobs - List of mob instances where to set
	 * @return Returns true when the list of mob can be set on the playing field otherwise false
	 */
	boolean setListMob(Coord coord, List<IMob> mobs);
	
	/**
	 * <b>Returns a list of all mobs where are on the selected playing field</b>
     * @param coord - Coordinates of the tower
	 * @return Returns a list of mobs
	 */
	List<IMob> getMobs(Coord coord);
	
	/**
	 * <b>Returns the number of mobs on the selected playing field</b>
     * @param coord - Coordinates of the tower
	 * @return Returns the number of mobs
	 */
	int getNumberOfMobs(Coord coord);
	
	/**
	 * <b>Deletes all mobs on the selected playing field</b>
     * @param coord - Coordinates of the tower
	 * @return Returns true when there was something to delete otherwise false
	 */
	boolean deleteAllMobs(Coord coord);
	
	/**
	 * <b>Deletes all dead mobs on the selected playing field</b>
     * @param coord - Coordinates of the tower
	 * @return Returns true when there was something to delete otherwise false
	 */
	boolean deleteDeadMobs(Coord coord);
	
	/**
	 * <b>Returns the type of the selected playing field.</b>
     * @param coord - Coordinates of the tower
	 * @return Returns the type of the field and what's standing on it.
	 */
	FieldType getTypeOf(Coord coord);
	
	/**
	 * @return Returns the x size of the initialized playing field
	 */
	int getSizeX();
	
	/**
	 * @return Returns the y size of the initialized playing field
	 */
	int getSizeY();	
}
