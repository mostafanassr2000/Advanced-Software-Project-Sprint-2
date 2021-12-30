package io.main;
public class ApplicationUser {
	/*Attributes*/
	protected String username;
	protected String mobileNumber;
	protected String email;
	protected String password;
	protected String keyType;
	protected boolean suspended;
	
	protected IPersistence persistence;
	protected IAuthorize authorizeObj;
	
	public ApplicationUser(String username, String email, String password, String mobileNumber, String keyType) {
		this.username = username;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
		this.keyType = keyType;
		this.suspended = false;
	}
	
	/*Methods*/
	
	public boolean register(ApplicationUser AU) {
		return authorizeObj.register(AU, persistence);
	}
	
	public ApplicationUser login(String username, String password) {
		return authorizeObj.login(username, password, persistence);
	}
	
	public void setSuspension(boolean suspend) {
		this.suspended = suspend;
	}
	
	public boolean isSuspended() {
		return this.suspended;
	}
	
	public void setAuthorization(IAuthorize authorizeObj) {
		this.authorizeObj = authorizeObj;
	}
	
	public void setPersistence(IPersistence persistence) {
		this.persistence = persistence;
	}
	
	/*Getters*/
		
	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	
	public String keyType() {
		return keyType;
	}
}
