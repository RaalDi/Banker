package com.raaldi.banker.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Embeddable
@Data
@EqualsAndHashCode(callSuper=false)
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
}
