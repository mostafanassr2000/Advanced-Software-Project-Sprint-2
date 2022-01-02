package io.main;

import java.util.ArrayList;

public interface IAdmin{

	public ArrayList<Driver> listUnapprovedDrivers();
	public boolean verifyDriver(String driverUserName);
	
	public ArrayList<ApplicationUser> listSuspendedUsers();
	public boolean suspend(String username);
	public boolean unsuspend(String username);
	
	public boolean addDiscountArea(String area);
}
