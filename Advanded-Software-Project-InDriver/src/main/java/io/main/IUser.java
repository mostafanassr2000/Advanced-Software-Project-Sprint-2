package io.main;

import java.util.Date;

public interface IUser {


	public boolean requestRide(String s, String d, String username, int passengersNum);

	public void receiveOffer(IRide ride);
	public void removeRide();
	
	public double getUserOffer();
	public String getUsername();
	public IRide getRide();
	
	public Date getBirthDate();
	public boolean getFirstRide();
}
