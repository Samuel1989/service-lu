package com.sam.service.lookup.http;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ServiceDispatcher {
	private RestTemplate rest;
	private HttpHeaders headers;
	
	public ServiceDispatcher() {
		rest = new RestTemplate();
		headers = new HttpHeaders();
		headers.add("Content-Type", "application/json");
		headers.add("Accept", "*/*");
	}
	
	public String post(String uri, String json) {
		HttpEntity<String> request = new HttpEntity<String>(json, headers);
		ResponseEntity<String> response = rest.exchange(uri, HttpMethod.POST, request, String.class);
		return response.getBody();
	}

}
