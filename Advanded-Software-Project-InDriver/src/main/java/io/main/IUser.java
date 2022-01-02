package io.main;

import java.util.Date;

public interface IUser {

	public void receiveOffer(IRide ride);
	public void removeRide();
	
	public double getUserOffer();
	public String getUsername();
	public IRide getRide();
	
	public Date getBirthDate();
	public void setFirstRide();
	public boolean isFirstRide();
}
