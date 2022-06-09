package io.Cores;


import io.Actions.*;


import java.time.LocalDateTime;

public class Event {
	
	private int rideId;
	private String eventName;
	private LocalDateTime eventTime;
	private String driverUsername;
	private String passengerUsername;
	
	public Event(String eventName, LocalDateTime eventTime, IRide ride){
		this.eventName = eventName;
		this.eventTime = eventTime;
		this.rideId = ride.getRideId();
		this.driverUsername = ride.getDriverUsername();
		this.passengerUsername = ride.getPassengerUsername();
	}

	public int getRideId() {
		return rideId;
	}

	public LocalDateTime getEventTime() {
		return eventTime;
	}

	public String getPassengerUsername() {
		return passengerUsername;
	}
	
	public String getDriverUsername() {
		return driverUsername;
	}

	public String getEventName() {
		return eventName;
	}
	
}
