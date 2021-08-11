package jcc.api.database.sqloader;

import java.util.HashMap;

class EntityLoader {
	public static HashMap<String,EntityValues<?>> entities = new HashMap<>();
	
	public static HashMap<String,EntityValues<?>> getEntitiesMap(){
		return entities;
	}
}
