package io.main;

public class Ride implements IRide{
	/*Attributes*/
	private String source;
	private String destination;
	private float offer;
	private boolean accepted;
	private int rate;
	
	private IUser user;
	private IDriver driver;
	
	private IPersistence persistence;
	
	/*Constructor*/
	public Ride(String source, String destination, IUser user, IPersistence persistence) {
		this.source = source;
		this.destination = destination;
		this.persistence = persistence;
		this.user = user;
		this.accepted = false;
		
	}
	
	/*Methods*/
	
	public boolean requestRide() {
		return persistence.notify(source, this);
	}
	
	public void setDriverOffer(float offer, IDriver driver) {
		this.offer = offer;
		this.driver = driver;
		user.receiveOffer(this);
	}
	
	public void setRate(int rate) {
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
	}
	
	public boolean isAccepted() {
		return accepted;
	}
	
	/*Getters*/
	public IUser getUser() {
		return user;
	}
	
	public IDriver getDriver() {
		return driver;
	}

	public String getSource() {
		return source;
	}

	public String getDestination() {
		return destination;
	}
	
	public float getOffer() {
		return offer;
	}

	public int getRate() {
		return rate;
	}
	
	public void listRideRating() {
		System.out.println("--------------------------");
		System.out.println("Username: " + user.getUsername() );
		System.out.println("Rating: " + getRate());
		System.out.println("--------------------------");	
	}
	
	public String toString() {
		return "User: " + user.getUsername() + "\n" +
			   "Source: " + getSource() + "\n" + 
			   "Destination: " + getDestination() + "\n";
	}

}
