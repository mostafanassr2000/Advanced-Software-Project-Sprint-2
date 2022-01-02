package io.main;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo (use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes ({@Type (value = Admin.class, name = "admin"), @Type (value = User.class, name = "user"), 
				@Type (value = Driver.class, name = "driver")})
public class ApplicationUser {
	/*Attributes*/
	protected String username;
	protected String mobileNumber;
	protected String email;
	protected Date birthDate;
	

	protected String password;
	protected String keyType;
	protected boolean suspended;
	
	@Autowired
	protected IPersistence persistence;
	
	/*Constructor*/
	public ApplicationUser() {
		
	}
	
	/*Methods*/
	
	public void setSuspension(boolean suspend) {
		this.suspended = suspend;
	}
	
	public boolean isSuspended() {
		return this.suspended;
	}
	
	/*Getters*/
		
	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}
	
	//@JsonIgnore
	public String getPassword() {
		return password;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}
	
	public Date getBirthDate() {
		return birthDate;
	}
	
	public String getKeyType() {
		return keyType;
	}
	
	
}
