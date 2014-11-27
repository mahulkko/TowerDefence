package de.htwg.towerdefence.util.json;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Maps JsonData to Objects and Objects to JsonString
 * @author Christoph Knetschke and Martin Hulkkonen
 */
public class JsonMapper implements IJsonMapper {
	
	private ObjectMapper mapper;			//Instance of Json Mapper
	 
	/**
	 * Constructor
	 */
	public JsonMapper() {
		this.mapper = new ObjectMapper();
	}
	
	@Override
	public <T> T createObjectFromJsonString(String jsonString, Class<T> jsonClass) {
		try {
			return mapper.readValue(jsonString, jsonClass);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
		return null;
	}
	

	@Override
	public String convertObjectToJsonString(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
		return null;
	}
	

	@Override
	public List<?> createListFromJsonString(String jsonString, Class<?> jsonClass) {
		try {
			return (List<?>) mapper.readValue(jsonString, jsonClass);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public String convertListToJsonString(List<?> jsonObjectList) {
		try {
			return mapper.writeValueAsString(jsonObjectList);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
