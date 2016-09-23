package com.example.hp.waverrmerchant;

import java.sql.Date;
import java.sql.Time;


public class Deal {
	
	String ID;
	String details;
	int percentageDiscount;
	int amountDiscount;
	String freebie;
	String canvasText;
	int minimumAmount;

	Date startDate;
	Date endDate;
	Time startTime;
	Time endTime;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}


	String imageURL;
	
	public String getImageURL() {
		return imageURL;
	}
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}


}
