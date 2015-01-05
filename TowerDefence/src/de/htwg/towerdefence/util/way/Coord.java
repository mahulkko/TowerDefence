package de.htwg.towerdefence.util.way;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import de.htwg.towerdefence.util.serialize.Serialize;

/**
* <b>Class Coord</b>
* @author Christoph Knetschke and Martin Hulkkonen
*/
public class Coord implements Serialize {
	
	/************************************************************
	 * Private variables
	 ***********************************************************/
	
	/** Logger for log4j connection */
    private static final Logger LOG = Logger.getLogger("TowerDefence.Util.Coord");
    
	/** X - Coordinate */
    private int x;
    
    /** Y - Coordinate */
    private int y;
    
    
    /************************************************************
	 * Public constructor
	 ***********************************************************/
    
    /**
     * Default constructor of the coordinate
     */
	public Coord() {
		LOG.info("Created a new coordinate with x: 0 and y: 0");
		this.x = 0;
		this.y = 0;
	}
	
	/**
	 * Constructor of the coordinate
	 * @param x - X Coordinate
	 * @param y - Y Coordinate
	 */
	public Coord(int x, int y) {
		LOG.info("Created a new coordinate with x: "+ x +" and y: " + y);
		this.x = x;
		this.y = y;
	}
	
	
	/************************************************************
	 * Public methods
	 ***********************************************************/
	
	/**
	 * <b>Get the X-Coordinate</b>
	 * @return Returns the x coordinate
	 */
	public int getX() {
		return this.x;
	}
	 
	/**
	 * <b>Set the X-Coordinate</b>
	 * @param x - X Coordinate
	 * @return Returns <b>true</b> when the coordinate can be set, otherwise <b>false</b> 
	 */
	public boolean setX(int x) {
		if (x >= 0) {
			this.x = x;
			return true;
		}
		LOG.info("Can't change coordinate x from " + this.x + " to " + x + " - Value must be positive");
		return false;
	}
	 
	/**
	 * <b>Get the Y-Coordinate</b>
	 * @return Returns the y coordinate
	 */
	public int getY() {
		return this.y;
	}
	     
	/**
	 * <b>Set the Y-Coordinate</b>
	 * @param y - Y Coordinate
	 * @return Returns <b>true</b> when the coordinate can be set, otherwise <b>false</b> 
	 */
	public boolean setY(int y) {
		if (y >= 0) {
			this.y = y;
			return true;
		}
		LOG.info("Can't change coordinate y from " + this.y + " to " + y + " - Value must be positive");
		return false;
	}
	

	/************************************************************
	 * Public Serialize methods
	 ***********************************************************/
	
	@Override
	public JsonNode serialize() {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.createObjectNode();
		((ObjectNode)root).put("x", x);
		((ObjectNode)root).put("y", y);
		
		return root;
	}

	@Override
	public void deserialize(JsonNode node) {
		JsonNode xNode = node.path("x");
		JsonNode yNode = node.path("y");
		this.x = xNode.getIntValue();
		this.y = yNode.getIntValue();
	}
}
