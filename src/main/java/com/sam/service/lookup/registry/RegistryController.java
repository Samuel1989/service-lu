package com.sam.service.lookup.registry;

import javax.annotation.PostConstruct;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistryController {
	ServiceRegistry serviceRegistry;
	
	@PostConstruct
	private void init() {
		serviceRegistry = new ServiceRegistry();
	}
	
	@RequestMapping(value = "rest/servicegroups", method = RequestMethod.GET)
	public String getServiceGroups() {
		return serviceRegistry.getServiceGroups();
	}
	
	@RequestMapping(value = "rest/register", method = RequestMethod.POST)
	public String registerService(@RequestBody Service service) {
		return serviceRegistry.addService(service);
	}
	
	@RequestMapping(value = "rest/service/{groupID}", method = RequestMethod.POST)
	public String invokeService(@PathVariable String groupID, @RequestBody String json) {
		return serviceRegistry.routeRequest(groupID, json);
	}

}
