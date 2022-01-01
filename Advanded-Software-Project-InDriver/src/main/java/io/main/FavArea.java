package io.main;

public class FavArea {
	/*Attributes*/
	private String favArea;
	private IDriver driver;
	
	/*Constructor*/
	
	public FavArea(String favArea, IDriver driver) {
		this.favArea = favArea;
		this.driver = driver;
	}

	/*Methods*/

	/*Getters*/
	public String getFavArea(){
		return favArea;
	}
	
	public IDriver getDriver() {
		return driver;
	}
}
