package de.htwg.towerdefence.model;

import java.util.List;
import de.htwg.towerdefence.util.enums.FieldType;

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
     * @param x - X coordinate of the tower
     * @param y - Y coordinate of the tower
     * @param tower - Tower where to set
     * @return Returns <b>true</b> when the tower is set or <b>false</b> when the tower can't be set
     */
	boolean setTower(int x, int y, ITower tower);
	
	/**
	 * <b>Returns the tower on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns the tower instance or null pointer
	 */
	ITower getTower(int x, int y);
	
	/**
	 * <b>Delete the tower on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns the tower instance or null pointer
	 */
	ITower deleteTower(int x, int y);
	
	/**
	 * <b>Checks if there is set a tower in the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns true when there is set a tower otherwise false 
	 */
	boolean isSetTower(int x, int y);
	
	/**
	 * <b>Set a mob on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @param mob - Instance of the mob where to set
	 * @return Returns true when the mob is set on the playing field otherwise false
	 */
	boolean setMob(int x, int y, IMob mob);
	
	/**
	 * <b>Set a list of mobs on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @param mobs - List of mob instances where to set
	 * @return Returns true when the list of mob can be set on the playing field otherwise false
	 */
	boolean setListMob(int x, int y, List<IMob> mobs);
	
	/**
	 * <b>Returns a list of all mobs where are on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns a list of mobs
	 */
	List<IMob> getMobs(int x, int y);
	
	/**
	 * <b>Returns the number of mobs on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns the number of mobs
	 */
	int getNumberOfMobs(int x, int y);
	
	/**
	 * <b>Deletes all mobs on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns true when there was something to delete otherwise false
	 */
	boolean deleteAllMobs(int x, int y);
	
	/**
	 * <b>Deletes all dead mobs on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns true when there was something to delete otherwise false
	 */
	boolean deleteDeadMobs(int x, int y);
	
	/**
	 * <b>Returns the type of the selected playing field.</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns the type of the field and what's standing on it.
	 */
	FieldType getTypeOf(int x, int y);
	
	/**
	 * @return Returns the x size of the initialized playing field
	 */
	int getSizeX();
	
	/**
	 * @return Returns the y size of the initialized playing field
	 */
	int getSizeY();	
}
