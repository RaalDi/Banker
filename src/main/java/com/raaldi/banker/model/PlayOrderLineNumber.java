package com.raaldi.banker.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class PlayOrderLineNumber {
/*	
	@NotNull
	@OneToOne
	@JoinColumn(name = "play_order_id", nullable = false, insertable = true, updatable = false)
	private PlayOrder playOrder;
*/	
	@NotNull
	@Column(name = "played_number", nullable = false, insertable = true, updatable = false)
	private String number;
/*
	public PlayOrder getPlayOrder() {
		return playOrder;
	}

	public void setPlayOrder(PlayOrder playOrder) {
		this.playOrder = playOrder;
	}
*/
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
