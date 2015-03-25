package com.sam.service.lookup.utils;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JSONBuilder {
	private JsonNodeFactory factory;
	
	public JSONBuilder() {
		factory = JsonNodeFactory.instance;
	}
	
	public String buildResponse(String key, String value) {
		ObjectNode response = new ObjectNode(factory);
		response.put(key, value);
		return response.toString();
	}

}
