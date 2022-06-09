package io.ApplicationUsers;

import java.util.ArrayList;
import org.springframework.web.bind.annotation.PathVariable;

import io.Cores.Event;

public interface IAdmin{

	public ArrayList<Driver> listUnapprovedDrivers();
	public boolean verifyDriver(String driverUserName);
	
	public ArrayList<ApplicationUser> listSuspendedUsers();
	public boolean suspend(String username);
	public boolean unsuspend(String username);
	
	public ArrayList<Event> showRideEvents(@PathVariable int rideId);
	public boolean addDiscountArea(String area);
}
