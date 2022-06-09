package io.ApplicationUsers;

import io.Actions.IRide;

public interface IUser {

	public void receiveOffer(IRide ride);
	public void removeRide();
	
	public double getUserOffer();
	public String getUsername();
	public IRide getRide();
	
	public String getBirthDate();
	public void terminateFirstRide();
	public boolean isFirstRide();
}
