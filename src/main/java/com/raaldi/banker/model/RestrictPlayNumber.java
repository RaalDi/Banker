package com.raaldi.banker.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class RestrictPlayNumber {
	
	@NotNull
	@Column(name = "restricted_number", nullable = false)
	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
