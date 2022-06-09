package io.ApplicationUsers;

import io.Actions.IRide;

public interface IDriver {
	public void setApproval();
	public boolean isApproved();
	
	public void update(IRide ride);
	public void removeRide();
	
	public IRide getDriverRide();
	
	public void setDriverAvgRating(double avgRating);
	public double getDriverAvgRating();	
	
	public double getBalance();
	public void setBalance(double value);
	public String getUsername();
	
	public void setAvailability(boolean ava);
	public boolean isAvailable();
}
	

