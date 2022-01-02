package io.main;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;


public class Discount {
	
	@Autowired
	IPersistence persistence;
	
	public Discount(IPersistence persistence) {
		this.persistence = persistence;
	}
	

	public double calculateDiscount(Ride ride) {
		double rate = 0;
		
		//birthday
		Date today = new Date();
		
		if (today.equals(ride.getUser().getBirthDate())) 
			rate +=0.10;
		
		//location selected by admin
		if(persistence.searchDiscountDest(ride.getDestination())) 
			rate += 0.10;
		
		//first Ride
		if(ride.getUser().isFirstRide())
			rate += 0.10;
		
		//two passengers
		if(ride.getPassengersNum() == 2)
			rate += 0.05;
		
		
		return rate;
	}

}
