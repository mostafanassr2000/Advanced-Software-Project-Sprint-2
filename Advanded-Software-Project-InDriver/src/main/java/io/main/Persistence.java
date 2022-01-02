package io.main;
import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Component
public class Persistence implements IPersistence{

	public ArrayList<ApplicationUser> applicationUsers;
	public ArrayList<FavArea> favAreas;
	public ArrayList<IRide> rides;
	public ArrayList<String> discountDests;
	
	Persistence(){
		applicationUsers = new ArrayList<ApplicationUser>();
		favAreas = new ArrayList<FavArea>();
		rides = new ArrayList<IRide>();
		discountDests = new ArrayList<String>();
	}
	
	
	/*Authorization part*/
	@PostMapping("/register")
	public boolean register(@RequestBody ApplicationUser AU) {

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
	public void listUnapprovedDrivers() {
		for(int i = 0; i < applicationUsers.size(); i++) {
			
			if(applicationUsers.get(i) instanceof Driver) {
				Driver driver = (Driver) applicationUsers.get(i); //Down Casting
				if(!driver.isApproved()) {
					System.out.println("-----------------------------------");
					System.out.println("Driver " + (i+1) + " info\n");
					System.out.println(driver);	
				}	
			}
		}
	}
	
	public boolean verifyDriver(String driverUsername) {

		for(int i = 0; i < applicationUsers.size(); i++) {

			if(driverUsername.equals(applicationUsers.get(i).getUsername())) {
				Driver driver = (Driver) applicationUsers.get(i); //Down Casting
				driver.setApproval();	//Setting approval to true.
				return true;
			}
		}
		
		return false; //Driver was not found in the database
	}
	
	//Suspension
	public void listSuspendedUsers() {
		
		for(int i = 0; i < applicationUsers.size(); i++) {
			if(applicationUsers.get(i).isSuspended()) {	//This user was suspended
				System.out.println("-----------------------------------");
				System.out.println("User" + (i+1) + " info");
				System.out.println(applicationUsers.get(i));
			}	
		}
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
	
	/*User Part*/
	
	
	/*Driver Part*/

	public void addFavArea(FavArea favArea){
		favAreas.add(favArea);
	}
	
	public boolean listDriverRatings(IDriver driver) {
		boolean found = false;
		for(int i = 0; i < rides.size(); i++) {
			if(rides.get(i).getDriver().getUsername().equals(driver.getUsername())) {
				 rides.get(i).listRideRating();
				 found = true;
			}
		}
		return found;
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
				if(!driver.getUsername().equals(ride.getDriver().getUsername()) && driver.getRide().getUser().getUsername().equals(ride.getUser().getUsername())) {
					driver.removeRide();
				}
			}
		}
	}
	
	public void removeAllRides(IRide ride) {
		for(int i = 0; i < applicationUsers.size(); i++) {
			if(applicationUsers.get(i) instanceof Driver) {
				Driver driver = (Driver) applicationUsers.get(i); //Down Casting
				//Remove this ride from the other drivers who were notified
				if(driver.getRide().getUser().getUsername().equals(ride.getUser().getUsername())) {
					driver.removeRide();
				}
			}
		}
	}

	
	@GetMapping("/get/users")
	public ArrayList<ApplicationUser> printUsers() {
		return applicationUsers;
	}
	

	/*Discount Part*/
	public void addDiscountDest(String Destination) {
		for (String d : discountDests) {
			if (d == Destination)
				return;
			
			discountDests.add(Destination);
		}
	}
	
	public boolean searchDiscountDest(String Destination) {
		for (String d : discountDests) {
			if (d == Destination)
				return true;
			
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
}
