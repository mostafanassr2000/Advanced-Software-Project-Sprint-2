package io.Actions;

import java.text.SimpleDateFormat;
import java.util.Date;

import io.Persistence.IPersistence;

public class Discount implements IDiscount {

	/* Attributes */
	IPersistence persistence;

	/* Constructor */
	public Discount(IPersistence persistence) {
		this.persistence = persistence;
	}

	/* Methods */
	public double calculateDiscount(IRide ride) {
		double rate = 0.0;

		
		String pattern = "dd/MM";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String today = simpleDateFormat.format(new Date());		
		
		// First Ride
		if (ride.getUser().isFirstRide())
			rate += 0.10;

		// Location selected by admin
		if (persistence.searchDiscountDest(ride.getDestination()))
			rate += 0.10;

		// Two passengers
		if (ride.getPassengersNum() >= 2)
			rate += 0.05;

		// Holiday
		if (persistence.isHoliday(today)) 
			rate += 0.05;		
		
		// Birthday
		if (today.equals(ride.getUser().getBirthDate()))
			rate += 0.10;

		return rate;
	}

}
