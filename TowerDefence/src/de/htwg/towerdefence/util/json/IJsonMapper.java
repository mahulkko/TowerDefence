package de.htwg.towerdefence.util.json;

import java.util.List;

public interface IJsonMapper {

	/**
	 * Create a Java Object from a String that contains a JsonObject
	 * @param jsonString - a String that contains a JsonObject
	 * @param jsonClass - the Class of the mapped Object 
	 * @return object from Class
	 */
	public abstract <T> T createObjectFromJsonString(String jsonString,
			Class<T> jsonClass);

	/**
	 * Convert a Object to String that contains a JsonObject
	 * @param object - the object that mapped to a JsonObject.
	 * @return a String that contains a JsonObject
	 */
	public abstract String convertObjectToJsonString(Object object);

	/**
	 * Create a List of Objects from a String that contains a JsonArray
	 * @param jsonString - String that contains a JsonArray
	 * @param jsonClass - the class of the mapped objects
	 * @return a List with Objects from the Class
	 */
	public abstract List<?> createListFromJsonString(String jsonString,
			Class<?> jsonClass);

	/**
	 * Convert a List of Objects to a String that contains a JsonArray
	 * @param jsonObjectList - a List that mapped to a JsonArray
	 * @return a String that contains a JsonArray
	 */
	public abstract String convertListToJsonString(List<?> jsonObjectList);

}