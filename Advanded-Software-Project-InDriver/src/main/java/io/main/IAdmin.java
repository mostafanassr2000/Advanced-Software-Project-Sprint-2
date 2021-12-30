package io.main;

public interface IAdmin{

	public void listUnapprovedDrivers();
	public boolean verifyDriver(String driverUserName);
	
	public void listSuspendedUsers();
	public boolean suspend(String username);
	public boolean unsuspend(String username);
}
