package io.main;

public class Admin extends ApplicationUser implements IAdmin{

	/*Constructor*/
	public Admin(String username, String email, String password, String mobileNumber, String keyType) {
		super(username, email, password, mobileNumber, keyType);
	}
	
	/*Methods*/
	
	//Verification Part
	public void listUnapprovedDrivers() {
		persistence.listUnapprovedDrivers();
	}
	
	public boolean verifyDriver(String driverUserName) {
		return persistence.verifyDriver(driverUserName);
	}
	
	//Suspension Part
	public void listSuspendedUsers() {
		persistence.listSuspendedUsers();
	}
	
	public boolean suspend(String username) {
		return persistence.suspend(username);
	}
	
	public boolean unsuspend(String username) {
		return persistence.unsuspend(username);
	}
	
	public String toString() {
		return "User Name: " + getUsername() + "-----" + "Type: " + keyType(); 
	}

}
