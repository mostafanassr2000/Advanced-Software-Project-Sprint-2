package io.main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/user")
public class User extends ApplicationUser implements IUser{
	
	/*Attributes*/
	private IRide ride;
	private double receivedOffer;
	/*Constructors*/
	
	public User() {
		super();
	}
	
	/*Methods*/

	public void receiveOffer(IRide ride) {	//Receives the offer from the driver
		receivedOffer = ride.getOffer();
		this.ride = ride;
	}
	
	public void removeRide() {
		this.ride = null;
	}
	
	/*Getters*/
	
	public double getUserOffer() {
		return receivedOffer;
	}
	
	public IRide getRide() {
		return ride;
	}
	
}
