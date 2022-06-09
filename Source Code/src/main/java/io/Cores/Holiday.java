package io.Cores;

public class Holiday {
	String holidayDate;
	String holidayName;
	
	public Holiday(String holidayName, String holidayDate){	
		this.holidayName = holidayName;
		this.holidayDate = holidayDate;
	}
	
	public String getHolidayDate() {
		return holidayDate;
	}
	
	public String getHolidayName() {
		return holidayName;
	}
	
}
