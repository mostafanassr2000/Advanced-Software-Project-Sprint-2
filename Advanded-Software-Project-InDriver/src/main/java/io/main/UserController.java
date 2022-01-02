package io.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user/{username}")
public class UserController {

	/*Attributes*/
	@Autowired
	IPersistence persistence;
	
	
	/*Default Constructor*/
	public UserController() {
		
	}
	
	/*Methods*/
	
	@PostMapping("/request-ride/{source}/{destination}")
	public boolean requestRide(@PathVariable String source, @PathVariable String destination, @PathVariable String username) {
		
		IUser user = (User) persistence.getObj(username);
		Ride newRide = new Ride(source, destination, user, persistence);
		return newRide.requestRide();
	}
	
	@GetMapping("/list-ride")
	public IRide showRide(@PathVariable String username) {
		IUser user = (User) persistence.getObj(username);

		return user.getRide();
	}
	
	@PutMapping("/set-ride-status")
	public void setRideStatus(@RequestBody boolean status, @PathVariable String username) {
		
		IUser user = (User) persistence.getObj(username);
		user.getRide().setAcceptance(status);

		if(status) { //true: Remove this ride from the other drivers
			user.getRide().removeOtherRides();
		}
		else {	//false: Remove this ride from all drivers
			user.getRide().removeAllRides();
			user.removeRide();
		}
	}
	
	@PutMapping("/rate-ride")
	public void rateRide(@RequestBody int rate, @PathVariable String username) {
		
		IUser user = (User) persistence.getObj(username);
		IDriver driver = user.getRide().getDriver();	//Driver of this ride
		
		user.getRide().setRate(rate);
		persistence.addRide(user.getRide());	//Saving this ride in the database

		driver.setDriverAvgRating(persistence.calcDriverAvgRating(driver));	//Recalculate the new average rating for the driver
		
		user.getRide().removeAllRides();
		user.removeRide();
	}
}





