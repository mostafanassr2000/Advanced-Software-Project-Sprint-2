package io.Persistence;

import io.Actions.IRide;
import io.ApplicationUsers.ApplicationUser;
import io.ApplicationUsers.Driver;
import io.ApplicationUsers.IDriver;
import io.Cores.Event;
import io.Cores.FavArea;

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
	public double calcDriverAvgRating(IDriver driver);
	
	/*Ride Part*/
	public boolean notify(IRide ride);
	public void addRide(IRide ride);
	public int generateRideId();
	public int getNumOfRides();
	
	/*Event Part*/
	public void addEvent(Event event);
	public ArrayList<Event> showEvents(int rideId);

	/*Discount Part*/
	public boolean addDiscountDest(String Destination);
	public boolean searchDiscountDest(String Destination);
  
	/*Holiday Part*/
	public void holidays();
	public boolean isHoliday(String date);
	
	public ApplicationUser getObj(String username);
}
