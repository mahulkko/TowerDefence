package de.htwg.towerdefence.util.control.impl;
import de.htwg.towerdefence.util.control.IControllableComponent;

/**
* <b>Class ControllableComponent</b>
* @author Christoph Knetschke and Martin Hulkkonen
*/
public abstract class ControllableComponent implements IControllableComponent {
	
	/************************************************************
	 * Private variables
	 ***********************************************************/
	
	/**
	 * Update status of this component
	 */
	private boolean update = true;
	
	
	/************************************************************
	 * Public methods
	 ***********************************************************/
	
	/**
	 * @return Gets the update status of this component
	 */
	public boolean getUpdateStatus() {
		return this.update;
	}
	
	/**
	 * Unregister this component for the update cycles
	 */
	public void unregisterSelf() {
		this.update = false;
	}
	
	
	/************************************************************
	 * Public abstract methods
	 ***********************************************************/
	
	/**
	 * Update the Component
	 */
	public abstract boolean update(long dt);
}
