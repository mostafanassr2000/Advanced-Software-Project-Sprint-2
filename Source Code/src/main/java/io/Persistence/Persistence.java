package io.Persistence;

import io.Actions.IRide;
import io.ApplicationUsers.ApplicationUser;
import io.ApplicationUsers.Driver;
import io.ApplicationUsers.IDriver;
import io.Cores.Event;
import io.Cores.FavArea;
import io.Cores.Holiday;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component //Singleton Object
public class Persistence implements IPersistence{
	
	private int rideId;
	public ArrayList<ApplicationUser> applicationUsers;
	public ArrayList<FavArea> favAreas;
	public ArrayList<IRide> rides;
	public ArrayList<Event> events;
	public ArrayList<String> discountDests;
	public ArrayList<Holiday> holidays;
	
	
	Persistence() {
		applicationUsers = new ArrayList<ApplicationUser>();
		favAreas = new ArrayList<FavArea>();
		rides = new ArrayList<IRide>();
		events = new ArrayList<Event>();
		discountDests = new ArrayList<String>();
		holidays = new ArrayList<Holiday>();
		rideId = 0;
		
		holidays();
	}
	
	public ArrayList<ApplicationUser> getUsers() {
		return applicationUsers;
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

	/*Authorization Part*/
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
	
	public double calcDriverAvgRating(IDriver driver) {
		double sumOfRates = 0.0;
		double numOfRates = 0.0;

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
	public boolean notify(IRide ride) {

		boolean found = false;
		
		//notify all drivers who have the source area as their favorite area while they are available
		for(FavArea favArea : favAreas) {	
			if(favArea.getFavArea().equals(ride.getSource()) && favArea.getDriver().isAvailable()) {
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
	
	public int generateRideId() {	//Generating new id for each ride
		return ++rideId;
	}
	
	public int getNumOfRides() {
		return rides.size();
	}
	
	/*Events Part*/
	public void addEvent(Event event) {
		events.add(event);
	}
	
	//Used by admin
	public ArrayList<Event> showEvents(int rideId) {
		
		ArrayList<Event> rideEvents = new ArrayList<Event>();
		
		for(Event r : events) {
			if(r.getRideId() == rideId) {
				rideEvents.add(r);
			}
		}
		return rideEvents;
	}
	
	/*Discount Part*/
	public boolean addDiscountDest(String destination) {
		if(searchDiscountDest(destination))	//Duplicate destination
			return false;
		
		discountDests.add(destination);
		return true;
	}
	
	public boolean searchDiscountDest(String destination) {
		for (String d : discountDests) {
			if (d.equals(destination))
				return true;
		}
		return false;
	}
	
	/*Holiday Part*/
	public void holidays() {
		
		Holiday holiday0 = new Holiday("Check Day!!", "02/01");
		holidays.add(holiday0);
		
		Holiday holiday1 = new Holiday("Coptic Christmas Day", "07/01");
		holidays.add(holiday1);
		
		Holiday holiday2 = new Holiday("Revolution Day", "25/01");
		holidays.add(holiday2);
		
		Holiday holiday3 = new Holiday("Coptic Good Friday", "22/04");
		holidays.add(holiday3);
		
		Holiday holiday4 = new Holiday("Coptic Holy Saturday", "23/04");
		holidays.add(holiday4);
		
		Holiday holiday5 = new Holiday("Coptic Easter Sunday", "24/04");
		holidays.add(holiday5);
		
		Holiday holiday6 = new Holiday("Spring Festival", "25/04");
		holidays.add(holiday6);
		
		Holiday holiday7 = new Holiday("Labor Day", "01/05");
		holidays.add(holiday7);
		
		Holiday holiday8 = new Holiday("Eid el Fitr Day 1", "03/05");
		holidays.add(holiday8);
		
		Holiday holiday9 = new Holiday("Eid el Fitr Day 2", "04/05");
		holidays.add(holiday9);
		
		Holiday holiday10 = new Holiday("Eid el Fitr Day 3", "05/05");
		holidays.add(holiday10);
		
		Holiday holiday11 = new Holiday("June 30 Revolution", "30/06");
		holidays.add(holiday11);
		
		Holiday holiday12 = new Holiday("Arafat Day", "09/07");
		holidays.add(holiday12);
		
		Holiday holiday13 = new Holiday("Eid al-Adha Day 1", "10/07");
		holidays.add(holiday13);

		Holiday holiday14 = new Holiday("Eid al-Adha Day 2", "11/07");
		holidays.add(holiday14);
		
		Holiday holiday15 = new Holiday("Eid al-Adha Day 3", "12/07");
		holidays.add(holiday15);
		
		Holiday holiday16 = new Holiday("Eid al-Adha Day 4", "13/07");
		holidays.add(holiday16);
		
		Holiday holiday17 = new Holiday("Revolution Day July 23", "23/07");
		holidays.add(holiday17);
		
		Holiday holiday18 = new Holiday("Muharram", "30/07");
		holidays.add(holiday18);
		
		Holiday holiday19 = new Holiday("Ashura", "08/08");
		holidays.add(holiday19);
		
		Holiday holiday20 = new Holiday("Flooding of the Nile", "15/08");
		holidays.add(holiday20);
		
		Holiday holiday21 = new Holiday("Sandra's Birthday", "01/09");
		holidays.add(holiday21);
		
		Holiday holiday22 = new Holiday("Nayrouz", "11/09");
		holidays.add(holiday22);
		
		Holiday holiday23 = new Holiday("Armed Forced Day", "06/10");
		holidays.add(holiday23);

		Holiday holiday24 = new Holiday("Prophet Mohamed's Birthday", "08/10");
		holidays.add(holiday24);
		
		Holiday holiday25 = new Holiday("Mostafa's Birthday", "30/11");
		holidays.add(holiday25);
		
	}
	
	public boolean isHoliday(String date) {
		for(Holiday h : holidays) {
			if(date.equals(h.getHolidayDate())) {
				return true;
			}
		}
		return false;
	}
	
}
