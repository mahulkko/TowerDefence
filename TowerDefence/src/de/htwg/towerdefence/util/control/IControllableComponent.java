package de.htwg.towerdefence.util.control;

/**
* <b>Interface - ControllableComponent</b>
* @author Christoph Knetschke and Martin Hulkkonen
*/
public interface IControllableComponent {
	
	/**
	 * @return Gets the update status of this component
	 */
	public boolean getUpdateStatus();
	
	/**
	 * Unregister this component for the update cycles
	 */
	public void unregisterSelf();
	
	/**
	 * Update the Component
	 */
	public boolean update(long dt);
}
