package com.sam.service.lookup.registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sam.service.lookup.http.ServiceDispatcher;
import com.sam.service.lookup.utils.JSONBuilder;

public class ServiceRegistry {
	private HashMap<String, List<Service>> serviceMap = new HashMap<String, List<Service>>();
	private ServiceDispatcher dispatcher;
	private JSONBuilder jsonBuilder;
	
	public ServiceRegistry() {
		dispatcher = new ServiceDispatcher();
		jsonBuilder = new JSONBuilder();
	}
	
	public String addService(Service service) {
		String groupID = service.getGroupID();
		String response = "";
		boolean addService = true;
		List<Service> serviceGroup;
		
		if (serviceMap.containsKey(groupID)) {
			serviceGroup = serviceMap.get(groupID);
			if (endpointExists(serviceGroup, service.getEndpoint())) {
				response = jsonBuilder.buildResponse("message", "service is already registered.");
				addService = false;
			} 
		} else {
			serviceGroup = new ArrayList<Service>();
		}
		
		if (addService) {
			serviceGroup.add(service);
			serviceMap.put(groupID, serviceGroup);
			response = jsonBuilder.buildResponse("message", "service has been registered.");
		}		
		
		return response.toString();
	}
	
	public String getServiceGroups() {
		ObjectMapper mapper = new ObjectMapper();
		String response = "";
		
		List<String> keys = new ArrayList<String>();
		for (String k : serviceMap.keySet()) {
			keys.add(k);
		}
		
		try {
			response = mapper.writeValueAsString(keys);
		} catch (JsonProcessingException e) {
			System.out.println(e.getMessage());
		}
		
		return response;
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
		String response = "";
		if (serviceMap.containsKey(groupID)) {
			response = dispatcher.post(serviceMap.get(groupID).get(0).getEndpoint(), json);		
		} else {
			response = jsonBuilder.buildResponse("message", "groupID: " + groupID + " not found.");
		}
		return response;
	}

}
