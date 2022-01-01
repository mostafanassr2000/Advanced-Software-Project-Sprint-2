package io.main;

import java.util.ArrayList;

public interface IPersistence {
	/*Authorization*/
	public boolean register(ApplicationUser AU);
	public ApplicationUser login(String un, String pw);
	
	/*Admin Part*/
	public ArrayList<Driver> listUnapprovedDrivers();
	public boolean verifyDriver(String driverUsername);
	
	public ArrayList<ApplicationUser> listSuspendedUsers();
	public boolean suspend(String username);
	public boolean unsuspend(String username);
	
	/*User Part*/
	public void removeOtherRides(IRide ride);
	public void removeAllRides(IRide ride);
	
	/*Driver Part*/
	public void addFavArea(FavArea favArea);
	public ArrayList<String> listFavoriteAreas(String driverUsername);
	
	public ArrayList<IRide> listDriverRatings(IDriver driver);
	public float calcDriverAvgRating(IDriver driver);
	
	/*Ride Part*/
	public boolean notify(String source, IRide ride);
	public void addRide(IRide ride);
	
	
	public ArrayList<ApplicationUser> getUsers();
	
	public ApplicationUser getObj(String username);
}
