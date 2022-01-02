package io.main;

public interface IDriver {
	public void setApproval();
	public boolean isApproved();
	
	public void update(IRide ride);
	public void removeRide();
	
	public IRide getDriverRide();
	
	public void setDriverAvgRating(float avgRating);
	public float getDriverAvgRating();	
	

	public double getBalance();
	public void setBalance(float value);
	public String getUsername();
}
	

