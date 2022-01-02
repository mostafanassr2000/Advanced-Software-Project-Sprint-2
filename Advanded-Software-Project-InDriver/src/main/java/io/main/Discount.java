package io.main;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;



public class Discount {
	
	@Autowired
	IPersistence persistence;

	public float calculateDiscount(Ride ride) {
		float rate = 0;
		
		//birthday
		Date today = new Date();
		today.setHours(0);
		if (today.compareTo(ride.getUser().getBirthDate()) ==0) 
			rate +=0.10;
		
		
		//location selected by admin
		if(persistence.searchDiscountDest(ride.getDestination())) 
			rate += 0.10;
		
		
		//first Ride
		if(ride.getUser().getFirstRide())
			rate += 0.10;
		
		//two passengers
		if(ride.getPassengersNum() == 2)
			rate += 0.05;
		
		
		return rate;
	}

}
