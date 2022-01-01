package io.main;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component //Singleton Object
public class Persistence implements IPersistence{
	
	public ArrayList<ApplicationUser> applicationUsers;
	public ArrayList<FavArea> favAreas;
	public ArrayList<IRide> rides;
	
	Persistence() {
		applicationUsers = new ArrayList<ApplicationUser>();
		favAreas = new ArrayList<FavArea>();
		rides = new ArrayList<IRide>();
	}
	
	public ArrayList<ApplicationUser> getUsers() {
		return applicationUsers;
	}
	
	public boolean register(ApplicationUser AU) {
		
		for(int i = 0; i < applicationUsers.size(); i++) {
			String currentUsername = applicationUsers.get(i).getUsername();
			if(currentUsername.equals(AU.getUsername())) {	
				return false;	//If the new user name already exists in the database
			}
		}
		
		applicationUsers.add(AU);	//Adding the user to the database.
		return true;
	}
	
	public ApplicationUser login(String un, String pw) {
		
		for(int i = 0; i < applicationUsers.size(); i++) {
			String currentUsername = applicationUsers.get(i).getUsername();
			String currentPassword = applicationUsers.get(i).getPassword();
			
			if(currentUsername.equals(un) && currentPassword.equals(pw) && applicationUsers.get(i) instanceof Driver) {	//Driver Case
				Driver driver = (Driver) applicationUsers.get(i); //Down Casting
				if(driver.isApproved() && !driver.isSuspended()) {
					return applicationUsers.get(i);
				}
				
			}
			else if(currentUsername.equals(un) && currentPassword.equals(pw) && !applicationUsers.get(i).isSuspended()) {	
				return applicationUsers.get(i);
			}	
		}
		return null;
	}
	
	/*Admin Part*/
	//Verification
	public ArrayList<Driver> listUnapprovedDrivers() {
		
		
		ArrayList<Driver> unapprovedDrivers = new ArrayList<Driver>();
		
		for(int i = 0; i < applicationUsers.size(); i++) {
			
			if(applicationUsers.get(i) instanceof Driver) {
				//Driver driver = (Driver) applicationUsers.get(i); //Down Casting
				//((person)user).function()
				if(!((Driver)applicationUsers.get(i)).isApproved()) {
					unapprovedDrivers.add((Driver)applicationUsers.get(i));
				}
			}
		}
		
		return unapprovedDrivers;
	}
	
	public boolean verifyDriver(String driverUsername) {

		for(int i = 0; i < applicationUsers.size(); i++) {

			if(driverUsername.equals(applicationUsers.get(i).getUsername())) {
				//Driver driver = (Driver) applicationUsers.get(i); //Down Casting
				((Driver)applicationUsers.get(i)).setApproval(); //Setting approval to true.
				return true;
			}
		}
		
		return false; //Driver was not found in the database
	}
	
	//Suspension
	public ArrayList<ApplicationUser> listSuspendedUsers() {
		
		ArrayList<ApplicationUser> suspendedUsers = new ArrayList<ApplicationUser>();
		
		for(int i = 0; i < applicationUsers.size(); i++) {
			if(applicationUsers.get(i).isSuspended()) {	//This user was suspended
				suspendedUsers.add(applicationUsers.get(i));
			}	
		}
		
		return suspendedUsers;
	}
	
	public boolean suspend(String username) {
		for(int i = 0; i < applicationUsers.size(); i++) {
			if(username.equals(applicationUsers.get(i).getUsername())) {
				applicationUsers.get(i).setSuspension(true);
				return true;
			}
		}
		return false;	//User hasn't been found
	}
	
	public boolean unsuspend(String username) {
		for(int i = 0; i < applicationUsers.size(); i++) {
			if(username.equals(applicationUsers.get(i).getUsername())) {
				applicationUsers.get(i).setSuspension(false);
				return true;
			}
		}
		return false;	//User hasn't been found
	}
		
	
	/*Driver Part*/
	public void addFavArea(FavArea favArea){
		favAreas.add(favArea);
	}
	
	public ArrayList<String> listFavoriteAreas(String driverUsername) {
		
		ArrayList<String> driverFavAreas = new ArrayList<String>(); 
		
		for(FavArea fa : favAreas) {
			if(fa.getDriver().getUsername().equals(driverUsername)) {
				driverFavAreas.add(fa.getFavArea());
			}
		}
		
		return driverFavAreas;
	}
	
	public ArrayList<IRide> listDriverRatings(IDriver driver) {
		
		ArrayList<IRide> driverRides = new ArrayList<IRide>();
		
		for(int i = 0; i < rides.size(); i++) {
			if(rides.get(i).getDriver().getUsername().equals(driver.getUsername())) {
				driverRides.add(rides.get(i));
			}
		}
		
		return driverRides;
	}
	
	public float calcDriverAvgRating(IDriver driver) {
		int sumOfRates = 0;
		int numOfRates = 0;

		for(int i = 0; i < rides.size(); i++) {
			if(rides.get(i).getDriver().getUsername().equals(driver.getUsername())) {
				sumOfRates += rides.get(i).getRate();
				numOfRates++;
			}
		}
		
		if(numOfRates == 0) {
			return 0;
		}
		else {
			return sumOfRates/numOfRates;	//Average
		}
	}
	
	/*Ride Part*/
	public boolean notify(String source, IRide ride) {

		boolean found = false;
		
		//notify all drivers who have the source area as their favorite area
		for(FavArea favArea : favAreas) {	
			if(favArea.getFavArea().equals(source)) {
				favArea.getDriver().update(ride);
				found = true;
			}
		}
		
		return found;
	}

	public void addRide(IRide ride) {
		rides.add(ride);
	}

	public void removeOtherRides(IRide ride) {
		for(int i = 0; i < applicationUsers.size(); i++) {
			if(applicationUsers.get(i) instanceof Driver) {
				Driver driver = (Driver) applicationUsers.get(i); //Down Casting
				//Remove this ride from the other drivers who were notified
				if(driver.getDriverRide() != null) {
					if(!driver.getUsername().equals(ride.getDriver().getUsername()) && driver.getDriverRide().getUser().getUsername().equals(ride.getUser().getUsername())) {
						driver.removeRide();
					}
				}
			}
		}
	}
	
	public void removeAllRides(IRide ride) {
		for(int i = 0; i < applicationUsers.size(); i++) {
			if(applicationUsers.get(i) instanceof Driver) {
				Driver driver = (Driver) applicationUsers.get(i); //Down Casting
				//Remove this ride from the other drivers who were notified
				if(driver.getDriverRide() != null) {
					if(driver.getDriverRide().getUser().getUsername().equals(ride.getUser().getUsername())) {
						driver.removeRide();
					}
				}
			}
		}
	}
	

	//Get object by username
	public ApplicationUser getObj(String username) {
		for(ApplicationUser au : applicationUsers) {
			if(username.equals(au.getUsername())) {
				return au;
			}
		}
		return null;
	}


	
}
