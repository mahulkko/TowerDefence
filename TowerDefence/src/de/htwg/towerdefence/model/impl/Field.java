package de.htwg.towerdefence.model.impl;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import de.htwg.towerdefence.model.IMob;
import de.htwg.towerdefence.model.ITower;
import de.htwg.towerdefence.util.enums.FieldType;

/**
 * <b>Field class</b>
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public class Field {
	
	/************************************************************
	 * Private variables
	 ***********************************************************/
	
	/** Logger for log4j connection */
    private static Logger log = Logger.getLogger("TowerDefence.Model.Field");
    
	/** Instance of the tower for the field */
	private ITower tower;
	
	/** List of all mobs on the field */
	private List<IMob> mobs;
	
	
	/************************************************************
	 * Public constructor
	 ***********************************************************/
	
	/**
	 * Default constructor of the field
	 */
	public Field() {
		log.info("Initialize the field");
		this.tower = null;
		this.mobs = new LinkedList<IMob>();
	}

	
	/************************************************************
	 * Public methods
	 ***********************************************************/
	
	/**
	 * <b>Set a tower on the playing field</b>
	 * @param tower - Instance of the tower
	 * @return Returns true when the tower is set otherwise false
	 */
	public boolean setTower(ITower tower) {
		if (!this.isSetTower() && this.getNumberOfMobs() == 0) {
			this.tower = tower;
			log.info("Added a new tower on the field");
			return true;
		}
		log.info("Can't add a new tower on the field - Field is not empty");
		return false;
	}

	/**
	 * <b>Returns the tower instance of the field</b>
	 * @return - Returns the tower instance
	 */
	public ITower getTower() {
		return this.tower;
	}
	
	/**
	 * <b>Deletes the tower of the field</b>
	 * @return Returns the deleted tower instance
	 */
	public ITower deleteTower() {
		if (this.tower != null) {
			ITower tmp = this.tower;
			this.tower = null;
			log.info("Deleted tower on the field");
			return tmp;
		}
		log.info("Can't delete the tower on the field - There is no tower instance");
		return null;
	}
	
	/**
	 * <b>Checks if there is standing a tower on the field</b>
	 * @return Returns true when there is a tower otherwise false
	 */
	public boolean isSetTower() {
		if (this.tower == null) {
			return false;
		}
		return true;
	}

	/**
	 * <b>Set a mob on the field</b>
	 * @param mob - Mob instance where to set
	 * @return Returns true when the mob is set otherwise false
	 */
	public boolean setMob(IMob mob) {
		if (!this.isSetTower()) {
			this.mobs.add(mob);
			log.info("Set a new mob on the field");
			return true;
		}
		log.info("Can't set a new mob on the field - Field is not empty");
		return false;
	}
	
	//----------------------------------------------------
	
 	/**
	 * <b>Returns the mob on the field</b>
	 * @return Returns the mob instance or null pointer
	 */
	public IMob getMob(IMob mob) {
 		// implement me
 		return null;
 	}
	
	/**
	 * <b>Delete the mob on the field</b>
	 * @return Returns the mob instance or null pointer
	 */
	public IMob deleteMob(IMob mob) {
 		// implement me
 		return null;
 	}
 	
	/**
	 * <b>Checks if there is set the mob in the field</b>
	 * @return Returns true when there is set the mob otherwise false 
	 */
	public boolean isSetMob(IMob mob) {
 		// implement me
 		return false;
 	}
	
	//----------------------------------------------------
	
	/**
	 * <b>Set a whole list of mobs on the field</b>
	 * @param mobs - List of all the mobs where to set
	 * @return Returns true when the mobs can be set otherwise false 
	 */
	public boolean setListMob(List<IMob> mobs) {
		if (!this.isSetTower()) {
			this.mobs.addAll(mobs);
			log.info("Set a new list of mobs on the field");
			return true;
		}
		log.info("Can't set a new list mob on the field - Field is not empty");
		return false;
	}

	/**
	 * <b>Get a list of all mobs on these field</b>
	 * @return Returns a copied list of all mobs on these field
	 */
	public List<IMob> getMobs() {
		return new LinkedList<IMob>(this.mobs);
	}
	
	/**
	 * @return Returns the number of mobs on the field
	 */
	public int getNumberOfMobs() {
		return this.mobs.size();
	}
	
	/**
	 * <b>Deletes all mobs on the field</b>
	 * @return Returns true when the mobs can be deleted otherwise false
	 */
	public boolean deleteAllMobs() {
		if (!this.mobs.isEmpty()) {
			this.mobs.clear();
			log.info("Delete all mobs on the field");
			return true;
		}
		log.info("Can't delete mobs on the field - No mobs exist");
		return false;
	}
	
	/**
	 * <b>Deletes all dead mobs on the field</b>
	 * @return Returns true when there was something to delete otherwise false
	 */
	public boolean deleteDeadMobs() {
		boolean del = false;
		for (int i = 0; i < this.mobs.size(); ++i) {
			IMob m = this.mobs.get(i);
			if (m.isDead()) {
				this.mobs.remove(i);
				del = true;
			}
		}
		log.info("Delete all dead mobs on the field");
		return del;
	}

	/**
	 * @return Returns the type of the field
	 */
	public FieldType getTypeOf() {
		if (this.isSetTower()) {
			return FieldType.TOWER;
		} else if (this.getNumberOfMobs() != 0) {
			return FieldType.MOB;
		} else {
			return FieldType.NONE;
		}
	}
}
