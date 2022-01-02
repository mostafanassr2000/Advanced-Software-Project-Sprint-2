package io.Actions;

import io.ApplicationUsers.IDriver;
import io.ApplicationUsers.IUser;

public interface IRide {
	public boolean requestRide();
	
	public int getPassengersNum();

	public void setAcceptance(boolean acceptance);
	public boolean isAccepted();
	
	public void setArrival();
	public boolean isArrived();
	
	public void setTermination();
	public boolean isTerminated();
	
	public void setRate(double rate);
	public double getRate();
	
	public void removeOtherRides();
	public void removeAllRides();
	
	public void setDriverOffer(float offer, IDriver driver);
	public double getOffer();
	
	public IUser getUser();
	public IDriver getDriver();
	
	public String getPassengerUsername();
	public String getDriverUsername();
	
	public String getSource();
	public String getDestination();
	
	public int getRideId();
}
