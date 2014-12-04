package de.htwg.towerdefence.util.serialize;

import org.codehaus.jackson.JsonNode;

public interface Serialize {
	public JsonNode serialize();
	public void deserialize(JsonNode node);
}
