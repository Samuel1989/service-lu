package com.sam.service.lookup.registry;

public class Service {
	private String groupID;
	private String endpoint;
	
	public Service() { };
	
	public Service(String groupID, String endpoint) {
		this.groupID = groupID;
		this.endpoint = endpoint;
	}
	
	public String getGroupID() {
		return groupID;
	}
	
	public String getEndpoint() {
		return endpoint;
	}
	
}
