package io.main;

import org.springframework.stereotype.Component;

@Component 
public interface IPersistence {
	/*Authorization*/
	public boolean register(ApplicationUser AU);
	public ApplicationUser login(String un, String pw);
	
	/*Admin Part*/
	public void listUnapprovedDrivers();
	public boolean verifyDriver(String driverUsername);
	
	public void listSuspendedUsers();
	public boolean suspend(String username);
	public boolean unsuspend(String username);
	
	/*User Part*/
	public void removeOtherRides(IRide ride);
	public void removeAllRides(IRide ride);
	
	/*Driver Part*/
	public void addFavArea(FavArea favArea);
	public boolean listDriverRatings(IDriver driver);
	public float calcDriverAvgRating(IDriver driver);
	
	/*Ride Part*/
	public boolean notify(String source, IRide ride);
	public void addRide(IRide ride);
	
	/*Discount Part*/
	public void addDiscountDest(String Destination);
	public boolean searchDiscountDest(String Destination);
}
