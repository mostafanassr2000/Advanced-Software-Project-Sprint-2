package io.main;

public interface IRide {
	public boolean requestRide();

	public void setAcceptance(boolean acceptance);
	public boolean isAccepted();
	
	public void setRate(int rate);
	public int getRate();
	
	public void removeOtherRides();
	public void removeAllRides();
	
	public void setDriverOffer(float offer, IDriver driver);
	public double getOffer();
	
	public IUser getUser();
	public String getPassengerUsername();
	public IDriver getDriver();
	public String getDriverUsername();
	
	public String getSource();
	public String getDestination();
	
	public void listRideRating();
	public String toString();
}
