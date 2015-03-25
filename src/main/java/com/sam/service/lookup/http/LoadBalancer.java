package com.sam.service.lookup.http;

import java.util.Random;

public class LoadBalancer {
	
	public int getRandom(int groupSize) {
		int serviceID = 0;
		if (groupSize > 1) {
			Random rand = new Random();
			serviceID = rand.nextInt(groupSize);
		}
		return serviceID;
	}

}
