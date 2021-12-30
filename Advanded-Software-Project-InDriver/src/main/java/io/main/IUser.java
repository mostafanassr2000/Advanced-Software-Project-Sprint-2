package io.main;

public interface IUser {
	public boolean requestRide(String s, String d);
	public void receiveOffer(IRide ride);
	
	public void setRideStatus(boolean status);
	
	public void rateRide(int rate);
	public void removeRide();
	
	public float getOffer();
	public String getUsername();
	
	public String toString();
}
