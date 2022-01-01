package io.main;

import org.springframework.web.bind.annotation.RestController;

@RestController


public class Driver extends ApplicationUser implements IDriver {

	/* Attributes */
	private String drivingLicense;
	private String nationalID;

	private boolean approved;
	private float avgRating;

	private IRide ride;

	/*Constructors*/
	public Driver (){
		avgRating = 0;
		approved = false;
	}
	
	/* Methods */

	public void setApproval() {
		approved = true;
	}

	public boolean isApproved() {
		return approved;
	}
	
	public void update(IRide ride) {
		this.ride = ride;
	}
	
	public void removeRide() { // Canceling the ride.
		ride = null;
	}	
	
	public void setDriverAvgRating(float avgRating) {
		this.avgRating = avgRating;
	}
	
	/* Getters */
	public String getNationalID() {
		return nationalID;
	}

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public float getDriverAvgRating() {
		return avgRating;
	}

	public IRide getDriverRide() {
		return this.ride;
	}

}
