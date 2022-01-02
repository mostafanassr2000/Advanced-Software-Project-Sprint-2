package io.main;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/driver/{driverUsername}")
public class DriverController {

	/*Attributes*/
	@Autowired
	IPersistence persistence;
	
	/*Constructor*/
	public DriverController() {
		
	}

	/*Methods*/
	@PostMapping("/add-favorite-area")
	public boolean addFavArea(@RequestBody String favArea, @PathVariable String driverUsername) {
		
		IDriver driver = (Driver) persistence.getObj(driverUsername); // Getting the object of the driver by his username
		
		if (driver != null) {
			FavArea newFavArea = new FavArea(favArea, driver);
			persistence.addFavArea(newFavArea);
			return true;
		}
		return false; // Driver == null
	}
	
	@GetMapping("/list-favorite-areas")
	public ArrayList<String> listFavoriteAreas(@PathVariable String driverUsername) {
		return persistence.listFavoriteAreas(driverUsername); // Driver == null
	}

	@PostMapping("/offer")
	public void offer(@RequestBody float offer, @PathVariable String driverUsername) {
		IDriver driver = (Driver) persistence.getObj(driverUsername); // Getting the object of the driver by his username
		driver.getDriverRide().setDriverOffer(offer, driver);
	}

	@GetMapping("/list-ride")
	public IRide listRides(@PathVariable String driverUsername) {
		IDriver driver = (Driver) persistence.getObj(driverUsername); // Getting the object of the driver by his username
		return driver.getDriverRide();
	}
	
	@GetMapping("/list-driver-ratings")
	public ArrayList<IRide> listDriverRatings(@PathVariable String driverUsername) {
		IDriver driver = (Driver) persistence.getObj(driverUsername); // Getting the object of the driver by his username
		return persistence.listDriverRatings(driver);
	}
	
	@PutMapping("/end-ride")
	public void terminateRide(@PathVariable String driverUsername) {
		
		IDriver driver = (Driver) persistence.getObj(driverUsername); // Getting the object of the driver by his username
		driver.getDriverRide().setTermination(); //End ride
		
		persistence.addRide(driver.getDriverRide());	//Saving this ride in the database
		driver.getDriverRide().removeAllRides();
	}
	
	
}
