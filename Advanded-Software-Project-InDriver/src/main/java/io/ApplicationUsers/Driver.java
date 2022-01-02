package io.ApplicationUsers;

import io.Actions.IRide;

public class Driver extends ApplicationUser implements IDriver {

	/* Attributes */
	private String drivingLicense;
	private String nationalID;
	
	private double balance;
	private double avgRating;
	
	private boolean approved;
	private boolean available;

	private IRide ride;


	/*Constructors*/
	public Driver (){

		avgRating = 0;
		balance = 0;
		
		approved = false;
		available = true;
	}
	
	/* Methods */
	public void update(IRide ride) {
		this.ride = ride;
	}
	
	public void removeRide() { // Canceling the ride.
		ride = null;
	}	
	
	/*Setters*/
	public void setApproval() {
		approved = true;
	}
	
	public void setDriverAvgRating(double avgRating) {
		this.avgRating = avgRating;
	}
	
	public void setAvailability(boolean ava) {
		this.available = ava;
	}	
	
	public void setBalance(double value) {
		balance = value;
	}
	
	/* Getters */
	public String getNationalID() {
		return nationalID;
	}

	public String getDrivingLicense() {
		return drivingLicense;
	}
	public IRide getDriverRide() {
		return this.ride;
	}

	public double getDriverAvgRating() {
		return avgRating;
	}
	
	public boolean isAvailable() {
		return available;
	}

	public boolean isApproved() {
		return approved;
	}	

	public double getBalance() {
		return balance;
	}

}
