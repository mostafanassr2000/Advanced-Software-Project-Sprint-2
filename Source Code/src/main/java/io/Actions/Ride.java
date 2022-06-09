package io.Actions;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;

import io.ApplicationUsers.IDriver;
import io.ApplicationUsers.IUser;
import io.Cores.Event;
import io.Persistence.IPersistence;

public class Ride implements IRide{

	/*Attributes*/
	private String source;
	private String destination;
	private double discountedOffer; //after discounts
	private double offer; //before discounts
	
	private boolean accepted;
	private boolean arrival;
	private boolean terminated;
	
	private int rideId;
	private double rate;
	private int passengersNum;//set in constructor
	
	private IDiscount discount;
	
	private IUser user;
	private IDriver driver;

	IPersistence persistence;
	
	/*Constructor*/

	public Ride(String source, String destination, IUser user, IPersistence persistence
			,int passengersNum) {
		this.source = source;
		this.destination = destination;
		this.user = user;
		
		this.accepted = false;
		this.arrival = false;
		this.terminated = false;
		
		this.passengersNum = passengersNum;
		this.persistence = persistence;
		this.offer = 0.0;
		
		this.rideId = this.persistence.generateRideId();	//Generating new id for this ride
		this.discount = new Discount(persistence);
	}
	
	/*Methods*/
	
	public boolean requestRide() {
		return persistence.notify(this);
	}

	public void setDriverOffer(float offer, IDriver driver) {
		this.offer = offer;
		this.driver = driver;
		
		//New Event
		Event newEvent = new Event("Captain offer", LocalDateTime.now(), this);
		persistence.addEvent(newEvent);
		
		//Add Discount
		double rate = discount.calculateDiscount(this);
		this.discountedOffer = offer - (offer*rate);
		
		//Send the offer to the user
		user.receiveOffer(this);
	}
	
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	public void removeOtherRides() {
		persistence.removeOtherRides(this);
	}
	
	public void removeAllRides() {
		persistence.removeAllRides(this);
	}
	
	//Ride Acceptance
	public void setAcceptance(boolean acceptance) {
		this.accepted = acceptance;	
		
		if(acceptance) {	//New event occurs
			Event newEvent = new Event("Ride has been accepted", LocalDateTime.now(), this);
			persistence.addEvent(newEvent);
		}
		else {	//If the ride was rejected
			driver.setAvailability(true);	
		}
	}
	
	public boolean isAccepted() {
		return accepted;
	}
	
	public void setArrival() {
		this.arrival = true;
		
		//New Event
		Event newEvent = new Event("Driver has been arrived", LocalDateTime.now(), this);
		persistence.addEvent(newEvent);
		
	}
	
	public boolean isArrived() {
		return arrival;
	}
	
	//Ride Termination
	public void setTermination() {
		this.terminated = true;
		
		if(user.isFirstRide()) {
			user.terminateFirstRide();	//Setting the first ride to false
		}
		
		//New Event
		Event newEvent = new Event("Ride has been terminated", LocalDateTime.now(), this);
		persistence.addEvent(newEvent);
		
		//get the balance of the driver
		double driverBalance = this.getDriver().getBalance();
		driver.setBalance(driverBalance += offer);
		driver.removeRide();
	}
	
	public boolean isTerminated() {
		return terminated;
	}
	
	/*Getters*/
	@JsonIgnore
	public IUser getUser() {
		return user;
	}
	
	@JsonIgnore
	public IDriver getDriver() {
		return driver;
	}

	public String getPassengerUsername() {
		if(user == null) {
			return "";
		}
		else {
			return user.getUsername();
		}
	}
	
	public String getDriverUsername() {
		if(driver == null) {
			return "";
		}
		else {
			return driver.getUsername();
		}
	}
	
	public String getSource() {
		return source;
	}
	
	public int getPassengersNum() {
		return passengersNum;
	}

	public String getDestination() {
		return destination;
	}

	public double getOffer() {
		return discountedOffer;
	}

	public double getRate() {
		return rate;
	}

	public int getRideId() {
		return rideId;
	}

}
