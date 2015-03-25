package com.sam.service.lookup.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.sam.service.lookup.http.ServiceDispatcher;

public class ServiceRegistry {
	private HashMap<String, List<Service>> serviceMap = new HashMap<String, List<Service>>();
	private ServiceDispatcher dispatcher;
	
	public ServiceRegistry() {
		dispatcher = new ServiceDispatcher();
	}
	
	public String addService(Service service) {
		String groupID = service.getGroupID();
		JsonNodeFactory factory = JsonNodeFactory.instance;
		ObjectNode response = new ObjectNode(factory);
		boolean addService = true;
		List<Service> serviceGroup;
		
		if (serviceMap.containsKey(groupID)) {
			serviceGroup = serviceMap.get(groupID);
			if (endpointExists(serviceGroup, service.getEndpoint())) {
				response.put("message", "service is already registered.");
				addService = false;
			} 
		} else {
			serviceGroup = new ArrayList<Service>();
		}
		
		if (addService) {
			serviceGroup.add(service);
			serviceMap.put(groupID, serviceGroup);
			response.put("message", "service has been registered.");
		}		
		
		return response.toString();
	}
	
	public String getServiceGroups() {
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		List<String> keys = new ArrayList<String>();
		for (String k : serviceMap.keySet()) {
			keys.add(k);
		}
		
		try {
			json = mapper.writeValueAsString(keys);
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
		}
		
		return json;
	}
	
	private boolean endpointExists(List<Service> group, String endpoint) {
		boolean result = false;
		for (Service s : group) {
			if (endpoint.equals(s.getEndpoint())) {
				result = true;
			}
		}
		return result;
	}
	
	public String routeRequest(String groupID, String json) {
		return dispatcher.post(serviceMap.get(groupID).get(0).getEndpoint(), json);
	}

}
