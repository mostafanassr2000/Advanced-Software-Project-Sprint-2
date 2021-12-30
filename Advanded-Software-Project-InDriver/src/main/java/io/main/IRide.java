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
	public float getOffer();
	
	public IUser getUser();
	public IDriver getDriver();
	public String getSource();
	public String getDestination();
	
	public void listRideRating();
	public String toString();
}
