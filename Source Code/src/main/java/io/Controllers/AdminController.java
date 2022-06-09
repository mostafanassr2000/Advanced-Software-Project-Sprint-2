package io.Controllers;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.ApplicationUsers.ApplicationUser;
import io.ApplicationUsers.Driver;
import io.ApplicationUsers.IAdmin;
import io.Cores.Event;

@RestController
@RequestMapping("/admin")
public class AdminController extends ApplicationUser implements IAdmin{


	/*Constructors*/
	
	public AdminController() {
		super();
		//Fixing an error "Parameter 0 of constructor in io.main.Admin required a bean of type 'java.lang.String' that could not be found."
	}
	
	/*Methods*/
	
	//Verification Part

	@GetMapping("/list-unapproved-drivers")
	public ArrayList<Driver> listUnapprovedDrivers() {
		return persistence.listUnapprovedDrivers();
	}
	
	@PutMapping("/verify-driver")
	public boolean verifyDriver(@RequestBody String driverUserName) {
		return persistence.verifyDriver(driverUserName);
	}
	
	//Suspension Part
	@GetMapping("/list-suspended-users")
	public ArrayList<ApplicationUser> listSuspendedUsers() {
		return persistence.listSuspendedUsers();
	}
	
	@PutMapping("/suspend")
	public boolean suspend(@RequestBody String username) {
		return persistence.suspend(username);
	}
	
	@PutMapping("/unsuspend")
	public boolean unsuspend(@RequestBody String username) {
		return persistence.unsuspend(username);
	}
	
	@GetMapping("/show-events/{rideId}")
	public ArrayList<Event> showRideEvents(@PathVariable int rideId) {
		return persistence.showEvents(rideId);
	}
	
	@PostMapping("/add-discount-area")
	public boolean addDiscountArea(@RequestBody String area) {
		return persistence.addDiscountDest(area);
	}
	
}
