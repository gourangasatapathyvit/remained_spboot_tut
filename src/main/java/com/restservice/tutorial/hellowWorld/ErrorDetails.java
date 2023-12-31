package com.restservice.tutorial.hellowWorld;

import java.time.LocalDate;

public class ErrorDetails {
	private LocalDate timeStamp;
	private String medssage;
	private String details;

	public ErrorDetails(LocalDate timeStamp, String medssage, String details) {
		super();
		this.timeStamp = timeStamp;
		this.medssage = medssage;
		this.details = details;
	}

	public LocalDate getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDate timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMedssage() {
		return medssage;
	}

	public void setMedssage(String medssage) {
		this.medssage = medssage;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
