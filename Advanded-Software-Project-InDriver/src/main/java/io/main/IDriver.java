package io.main;

public interface IDriver {
	public void setApproval();
	public boolean isApproved();
	public void addFavArea(String favArea);
	
	public void update(IRide ride);
	public void offer(float s);
	
	public boolean listRides();
	public void listDriverRatings();

	public void removeRide();
	
	public String getUsername();
	public float getDriverAvgRating();	
	
	public IRide getRide();
	
	public String toString();
	public float getBalance();
	public void setBalance(float value);
	
}
	

