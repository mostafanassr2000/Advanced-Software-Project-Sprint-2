package io.main;

import java.util.Date;

public class User extends ApplicationUser implements IUser{
	
	/*Attributes*/
	private float receivedOffer;
	private IRide ride;
	private Date birthDate;
	private boolean firstRide;
	
	/*Constructor*/
	public User(String username, String email, String password, String mobileNumber, String keyType, Date birthDate) {
		super(username, email, password, mobileNumber, keyType);
		this.birthDate = birthDate;
	}

	/*Methods*/
	public boolean requestRide(String source, String destination, int passengersNum) {
		Ride newRide = new Ride(source, destination, this, persistence, passengersNum);
		return newRide.requestRide();
	}
	
	public void receiveOffer(IRide ride) {	//Receives the offer from the driver
		receivedOffer = ride.getOffer();
		this.ride = ride;
	}
	
	public void setRideStatus(boolean status) {
		ride.setAcceptance(status);

		if(status) { //true: Remove this ride from the other drivers
			ride.removeOtherRides();
		}
		else {	//false: Remove this ride from all drivers
			ride.removeAllRides();
			removeRide();
		}
	}
	
	public void rateRide(int rate) {
		ride.setRate(rate);
		persistence.addRide(ride);	//Saving this ride in the database
		removeRide();
	}

	public void removeRide() {
		this.ride = null;
	}
	

	/*Getters*/
	public Date getBirthDate() {		
		return birthDate;
	}

	public float getOffer() {
		return receivedOffer;
	}
	
	public boolean getFirstRide() {
		return firstRide;
	}
	
	public String toString() {
		return ("User's Username: " + getUsername() + "\n")+           
				("User's Mobile Number: " + getMobileNumber() + "\n")+     
				("User's Email: " + getEmail() + "\n")+         
				("-----------------------------------\n");      
	}

	
}
