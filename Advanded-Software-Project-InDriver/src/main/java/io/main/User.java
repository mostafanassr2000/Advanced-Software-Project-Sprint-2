package io.main;


import java.util.Date;



public class User extends ApplicationUser implements IUser{
	
	/*Attributes*/
	private IRide ride;

	private Date birthDate;
	private boolean firstRide;
	

	private double receivedOffer;
	/*Constructors*/
	
	public User() {
		super();

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
	public Date getBirthDate() {		
		return birthDate;
	}
	
	public boolean getFirstRide() {
		return firstRide;
	}
	
	public double getUserOffer() {
		return receivedOffer;
	}
	
	public IRide getRide() {
		return ride;
	}

	
}
