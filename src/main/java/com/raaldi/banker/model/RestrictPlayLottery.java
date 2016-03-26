package com.raaldi.banker.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;


@Embeddable
public class RestrictPlayLottery {
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "lottery_id", nullable = false)
	private Lottery lottery;

	public Lottery getLottery() {
		return lottery;
	}

	public void setLottery(Lottery lottery) {
		this.lottery = lottery;
	}
}
