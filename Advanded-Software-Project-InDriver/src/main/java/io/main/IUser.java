package io.main;

public interface IUser {
	//public boolean requestRide(String s, String d, String username);
	public void receiveOffer(IRide ride);
	
	//public void setRideStatus(boolean status);
	
	//public void rateRide(int rate);
	public void removeRide();
	
	public double getUserOffer();
	public String getUsername();
	public IRide getRide();
	
	public String toString();
}
