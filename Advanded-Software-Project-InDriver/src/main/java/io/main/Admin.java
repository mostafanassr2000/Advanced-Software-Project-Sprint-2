package io.main;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/admin")
public class Admin extends ApplicationUser implements IAdmin{


	/*Constructors*/
	
	public Admin() {
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
}
