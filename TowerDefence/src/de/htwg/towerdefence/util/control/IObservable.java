package de.htwg.towerdefence.util.control;

/**
* <b>Interface - IObservable</b>
* @author Christoph Knetschke and Martin Hulkkonen
*/
public interface IObservable {
	
	/**
	 * Add a new Observer to the observable list
	 * @param s - Object who wants observe
	 */
	void addObserver(IObserver s);
	
	/**
	 * Deletes a Observer from the observable list
	 * @param s - Object who wants observe
	 */
	void removeObserver(IObserver s);
	
	/**
	 * Removes all observable from the list
	 */
	void removeAllObservers();
	
	/**
	 * Notifies all Observer and updates it
	 */
	void notifyObservers();
		
}
