package de.htwg.towerdefence.util.control.impl;
import de.htwg.towerdefence.util.control.IControllableComponent;

/**
* <b>Class ControllableComponent</b>
* @author Christoph Knetschke and Martin Hulkkonen
*/
public abstract class ControllableComponent implements IControllableComponent{
	
	/**
	 * Update the Component
	 */
	public abstract void update(long dt);
}
