package io.ApplicationUsers;

import io.Actions.IRide;

public class User extends ApplicationUser implements IUser{
	
	/*Attributes*/
	private IRide ride;
	private boolean firstRide;
	private double receivedOffer;
	
	/*Constructor*/

	public User() {
		super();
		firstRide = true;
	}
	
	/*Methods*/
	public void receiveOffer(IRide ride) {	//Receives the offer from the driver
		receivedOffer = ride.getOffer();
		this.ride = ride;
	}
	
	public void removeRide() {
		this.ride = null;
	}
	
	/*Getters*/
	public void terminateFirstRide(){
		firstRide = false;
	}
	
	public boolean isFirstRide() {
		return firstRide;
	}
	
	public double getUserOffer() {
		return receivedOffer;
	}
	
	public IRide getRide() {
		return ride;
	}

	
}
