package com.raaldi.banker.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Embeddable
@Data
@EqualsAndHashCode(callSuper=false)
public class RestrictPlayLottery {
	
	@NotNull
	@OneToOne
	@JoinColumn(name = "lottery_id", nullable = false)
	private Lottery lottery;
	
}
