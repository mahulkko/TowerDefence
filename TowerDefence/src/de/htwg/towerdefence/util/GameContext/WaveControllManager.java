package de.htwg.towerdefence.util.GameContext;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import de.htwg.towerdefence.model.impl.Mob;
import de.htwg.towerdefence.util.control.IControllableComponent;
import de.htwg.towerdefence.util.control.impl.ControllableComponent;
import de.htwg.towerdefence.util.serialize.Serialize;
import de.htwg.towerdefence.util.way.Coord;

public class WaveControllManager extends ControllableComponent implements Serialize{
	
	/************************************************************
	 * Private Variables
	 ***********************************************************/
	private long speed = 2000;
	private long tmpSpeed = 2000;
	private long firstWaitTime = 5000;
	
	/************************************************************
	 * Public constructor
	 ***********************************************************/
	
	public WaveControllManager() {
		
	}
	
	
	/************************************************************
	 * Public ControllableComponent methods
	 ***********************************************************/
	
	@Override
	public boolean update(long dt) {
		
		if (this.firstWaitTime >= 0) {
			this.firstWaitTime = this.firstWaitTime - dt;
			return false;
		}
		this.tmpSpeed = this.tmpSpeed - dt;
		
		if (this.tmpSpeed <= 0) {
			// Set new mob on playingfield
			Mob mob = new Mob(new Coord(0, 0));
			GameContext.getPlayingField().setMob(new Coord(0, 0), mob);
			GameData data = new GameData();
			data.setComponent(mob);
			data.setLastTime(System.currentTimeMillis());
			GameContext.getGameData().add(data);
			
			this.tmpSpeed = this.speed;
			return true;
		}
		return false;
	}

	
	/************************************************************
	 * Public Serialize methods
	 ***********************************************************/

	@Override
	public JsonNode serialize() {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.createObjectNode();
		
		((ObjectNode)root).put("speed", this.speed);
		((ObjectNode)root).put("tmpSpeed", this.tmpSpeed);
		((ObjectNode)root).put("firstWaitTime", this.firstWaitTime);
		
		if (GameContext.isComponentInGameData((IControllableComponent)this)) {
			((ObjectNode)root).put("controllable", true);
			((ObjectNode)root).put("lastTime", GameContext.getGameData((IControllableComponent)this).getLastTime());
		}
		
		return root;
	}


	@Override
	public  void deserialize(JsonNode node) {
		JsonNode speed = node.path("speed");
		JsonNode tmpSpeed = node.path("tmpSpeed");
		JsonNode firstWaitTime = node.path("firstWaitTime");
		JsonNode controllable = node.path("controllable");
		
		this.speed = speed.asLong();
		this.tmpSpeed = tmpSpeed.asLong();
		this.firstWaitTime = firstWaitTime.asLong();
		
		if (controllable.getBooleanValue()) {
			JsonNode lastTime = node.path("lastTime");
			GameData data = new GameData();
			data.setComponent((IControllableComponent)this);
			data.setLastTime(lastTime.asLong());
			GameContext.getGameData().add(data);
		}
	}
}
