package io.main;
public class Driver extends ApplicationUser implements IDriver {

	/* Attributes */
	private String drivingLicense;
	private String nationalID;

	private boolean approved;
	private float avgRating;

	private IRide ride;

	/* Constructor */
	public Driver(String userName, String email, String password, String mobileNumber, String keyType,
			String drivingLicense, String nationalID) {
		super(userName, email, password, mobileNumber, keyType);
		this.drivingLicense = drivingLicense;
		this.nationalID = nationalID;

		avgRating = 0;
		approved = false;
	}

	/* Methods */

	public void setApproval() {
		approved = true;
	}

	public boolean isApproved() {
		return approved;
	}

	public void addFavArea(String favArea) {
		FavArea newFavArea = new FavArea(favArea, this);
		persistence.addFavArea(newFavArea);
	}

	public void update(IRide ride) {
		this.ride = ride;
	}

	public void offer(float offer) {
		ride.setDriverOffer(offer, this);
	}

	public boolean listRides() {
		if (ride == null) {
			System.out.println("There is no nearby passengers");
			System.out.println("");
			return false;
		}

		System.out.println(this.ride);
		return true;
	}

	public void listDriverRatings() {

		this.avgRating = persistence.calcDriverAvgRating(this);
		System.out.println("Average Rating: " + this.avgRating);

		if (!persistence.listDriverRatings(this)) {
			System.out.println("You have no user ratings in the meantime.");
			System.out.println("");
		}
	}

	public void removeRide() { // Canceling the ride.
		ride = null;
	}

	/* Getters */
	public String getNationalID() {
		return nationalID;
	}

	public String getDrivingLicense() {
		return drivingLicense;
	}

	public float getDriverAvgRating() {
		return avgRating;
	}

	public IRide getRide() {
		return ride;
	}

	/*
	 * public String toString() { return "Driver Name: " + getUsername()+ "\n" +
	 * "Mobile Number: " + getMobileNumber() + "\n" + "Driver Rating: " +
	 * getDriverAvgRating(); }
	 */

	public String toString() {
		return ("Driver's Username: " + getUsername() + "\n") + ("Driver's Mobile Number: " + getMobileNumber() + "\n")
				+ ("Driver's National ID: " + getNationalID() + "\n")
				+ ("Driver's Driving License: " + getDrivingLicense() + "\n")
				+ ("-----------------------------------\n");
	}

}
