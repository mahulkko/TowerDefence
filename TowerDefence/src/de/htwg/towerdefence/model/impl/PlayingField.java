package de.htwg.towerdefence.model.impl;

import java.util.List;

import org.apache.log4j.Logger;

import de.htwg.towerdefence.model.IMob;
import de.htwg.towerdefence.model.IPlayingField;
import de.htwg.towerdefence.model.ITower;
import de.htwg.towerdefence.util.enums.FieldType;
import de.htwg.towerdefence.util.way.Coord;

/**
 * <b>PlayingField Class</b>
 * <br>
 * Implements IPlayingField Interface
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public class PlayingField implements IPlayingField {
	
	/************************************************************
	 * Private variables
	 ***********************************************************/
	
	/** Logger for log4j connection */
    private static Logger log = Logger.getLogger("TowerDefence.Model.PlayingField");
    
    /** Playing field array */
 	private Field field[][];  
 	
 	/** Size x of the playing field */
 	private int sizeX;
 	
 	/** Size y of the playing field */
 	private int sizeY;
 	
 	
 	/************************************************************
	 * Public constructor
	 ***********************************************************/
 	
 	/**
 	 * Default constructor of the playing field
 	 */
 	public PlayingField() {
 		log.info("Created a new PlayingField without initializing");
 		this.sizeX = 0;
 		this.sizeY = 0;
 	}
 	
 	/**
 	 * <b>Constructor - initialize the playing field with the given values</b>
 	 * @param sizeX
 	 * @param sizeY
 	 */
 	public PlayingField(int sizeX, int sizeY) {
 		log.info("Created a new PlayingField with initializing");
 		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.init();
 	}
    
 	
 	/************************************************************
	 * Public methods
	 ***********************************************************/
 	
 	@Override
	/**
	 * <b>Initialize the playing field</b>
	 * @param sizeX - Size of the playing field on x
	 * @param sizeY - Size of the playing field on y
	 */
    public void initPlayingField(int sizeX, int sizeY) {
 		log.info("Initialized the PlayingField with size-X: " + sizeX + "and size-y: " + sizeY);
 		this.sizeX = sizeX;
		this.sizeY = sizeY;
    	this.init();
    }
    
 	@Override
    /**
     * <b>Set a new tower on the playing field</b>
     * @param x - X coordinate of the tower
     * @param y - Y coordinate of the tower
     * @param tower - Tower where to set
     * @return Returns <b>true</b> when the tower is set or <b>false</b> when the tower can't be set
     */
	public boolean setTower(Coord coord, ITower tower) {
 		if (checkSize(coord.getX(), coord.getY())) {
 			log.info("Added a new tower on field: " + coord.getX() + " | " + coord.getY());
 			return this.field[coord.getY()][coord.getX()].setTower(tower);
		}
 		log.error("Can't add a new tower on field: " + coord.getX() + " | " + coord.getY() + " - Size is out of the playing field");
	    return false;
	}
	
 	@Override
	/**
	 * <b>Returns the tower on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns the tower instance or null pointer
	 */
	public ITower getTower(Coord coord) {
 		if (checkSize(coord.getX(), coord.getY())) {
		    return this.field[coord.getY()][coord.getX()].getTower();
		}
		return null;
	}
	
 	@Override
	/**
	 * <b>Delete the tower on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns the tower instance or null pointer
	 */
	public ITower deleteTower(Coord coord) {
 		if (checkSize(coord.getX(), coord.getY())) {
 			log.info("Deleted tower on field: " + coord.getX() + " | " + coord.getY());
		    return this.field[coord.getY()][coord.getX()].deleteTower();
		}
 		log.error("Can't delete tower on field: " + coord.getX() + " | " + coord.getY() + " - Size is out of the playing field");
		return null;
	}
	
 	@Override
	/**
	 * <b>Checks if there is set a tower in the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns true when there is set a tower otherwise false 
	 */
	public boolean isSetTower(Coord coord) {
 		if (checkSize(coord.getX(), coord.getY())) {
 			  return this.field[coord.getY()][coord.getX()].isSetTower();
 		}
 		return false;
	}
	
 	@Override
	/**
	 * <b>Set a mob on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @param mob - Instance of the mob where to set
	 * @return Returns true when the mob is set on the playing field otherwise false
	 */
	public boolean setMob(Coord coord, IMob mob) {
 		if (checkSize(coord.getX(), coord.getY())) {
 			log.info("Added a new mob on field: " + coord.getX() + " | " + coord.getY());
		    return this.field[coord.getY()][coord.getX()].setMob(mob);
		}
 		log.error("Can't add a new mob on field: " + coord.getX() + " | " + coord.getY() + " - Size is out of the playing field");
		return false;
	}
 	
 	@Override
 	/**
	 * <b>Returns the mob on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns the mob instance or null pointer
	 */
	public IMob getMob(Coord coord, IMob mob) {
 		if (checkSize(coord.getX(), coord.getY())) {
 			log.info("Get mob on field: " + coord.getX() + " | " + coord.getY());
		    return this.field[coord.getY()][coord.getX()].getMob(mob);
		}
 		log.error("Can't get mob on field: " + coord.getX() + " | " + coord.getY() + " - Size is out of the playing field");
		return null;
 	}
	
 	@Override
	/**
	 * <b>Delete the mob on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns the mob instance or null pointer
	 */
	public IMob deleteMob(Coord coord, IMob mob) {
 		if (checkSize(coord.getX(), coord.getY())) {
 			log.info("Delete mob on field: " + coord.getX() + " | " + coord.getY());
		    return this.field[coord.getY()][coord.getX()].deleteMob(mob);
		}
 		log.error("Can't delete mob on field: " + coord.getX() + " | " + coord.getY() + " - Size is out of the playing field");
		return null;
 	}
 	
 	@Override
	/**
	 * <b>Checks if there is set the mob in the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns true when there is set the mob otherwise false 
	 */
	public boolean isSetMob(Coord coord, IMob mob) {
 		if (checkSize(coord.getX(), coord.getY())) {
 			log.info("Search mob on field: " + coord.getX() + " | " + coord.getY());
		    return this.field[coord.getY()][coord.getX()].isSetMob(mob);
		}
 		log.error("Can't find mob on field: " + coord.getX() + " | " + coord.getY() + " - Size is out of the playing field");
		return false;
 	}
	
 	@Override
	/**
	 * <b>Set a list of mobs on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @param mobs - List of mob instances where to set
	 * @return Returns true when the list of mob can be set on the playing field otherwise false
	 */
	public boolean setListMob(Coord coord, List<IMob> mobs) {
 		if (checkSize(coord.getX(), coord.getY())) {
 			log.info("Added a new list of mobs on field: " + coord.getX() + " | " + coord.getY());
 			return this.field[coord.getY()][coord.getX()].setListMob(mobs);
 		}
 		log.error("Can't add a new list of mobs on field: " + coord.getX() + " | " + coord.getY() + " - Size is out of the playing field");
 		return false;
	}
	
 	@Override
	/**
	 * <b>Returns a list of all mobs where are on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns a list of mobs
	 */
	public List<IMob> getMobs(Coord coord) {
 		if (checkSize(coord.getX(), coord.getY())) {
		    return this.field[coord.getY()][coord.getX()].getMobs();
		}
		return null;
	}
	
 	@Override
	/**
	 * <b>Returns the number of mobs on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns the number of mobs
	 */
	public int getNumberOfMobs(Coord coord) {
 		if (checkSize(coord.getX(), coord.getY())) {
 			  return this.field[coord.getY()][coord.getX()].getNumberOfMobs();
 		}
 		return -1;
	}
	
 	@Override
	/**
	 * <b>Deletes all mobs on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns true when there was something to delete otherwise false
	 */
	public boolean deleteAllMobs(Coord coord) {
 		if (checkSize(coord.getX(), coord.getY())) {
 			log.info("Delete all mobs on field: " + coord.getX() + " | " + coord.getY());
 			return this.field[coord.getY()][coord.getX()].deleteAllMobs();
 		}
 		log.error("Can't delete all mobs on field: " + coord.getX() + " | " + coord.getY() + " - Size is out of the playing field");
 		return false;
	}
	
 	@Override
	/**
	 * <b>Deletes all dead mobs on the selected playing field</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns true when there was something to delete otherwise false
	 */
	public boolean deleteDeadMobs(Coord coord) {
 		if (checkSize(coord.getX(), coord.getY())) {
 			log.info("Delete all dead mobs on field: " + coord.getX() + " | " + coord.getY());
 			return this.field[coord.getY()][coord.getX()].deleteDeadMobs();
 		}
 		log.error("Can't delete all dead mobs on field: " + coord.getX() + " | " + coord.getY() + " - Size is out of the playing field");
 		return false;
	}
	
 	@Override
	/**
	 * <b>Returns the type of the selected playing field.</b>
	 * @param x - X coordinate of the selected playing field
	 * @param y - Y coordinate of the selected playing field
	 * @return Returns the type of the field and what's standing on it.
	 */
	public FieldType getTypeOf(Coord coord) {
 		if (checkSize(coord.getX(), coord.getY())) {
 			return this.field[coord.getY()][coord.getX()].getTypeOf();
 		}
 		return FieldType.UNDEFINED;
	}
	
 	@Override
	/**
	 * @return Returns the x size of the initialized playing field
	 */
	public int getSizeX() {
		return this.sizeX;
	}
	
 	@Override
	/**
	 * @return Returns the y size of the initialized playing field
	 */
	public int getSizeY() {
		return this.sizeY;
	}
 	

 	/************************************************************
	 * Private methods
	 ***********************************************************/
 	
 	/**
 	 * Initialize the playing field
 	 */
 	private void init() {
 		log.info("Initialize the playing field");
		this.field = new Field[sizeY][sizeX];
		for (int i = 0; i < this.sizeY; ++i) {
			for (int j = 0; j < this.sizeX; ++j) {
				this.field[i][j] = new Field();
			}
		}
	}
 	
 	/**
 	 * <b>Checks the bounds of the playing field</b>
 	 * @param sizeX - X coordinate of the selected playing field
	 * @param sizeY - Y coordinate of the selected playing field
 	 * @return Returns true when the coordinates are in the playing field otherwise false
 	 */
 	private boolean checkSize(int sizeX, int sizeY) {
 		if (sizeX < this.getSizeX() && sizeY < this.getSizeY() && sizeX >= 0 && sizeY >= 0) {
 			return true;
 		}
 		return false;
 	}
}
