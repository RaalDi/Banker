package com.raaldi.banker.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Embeddable
@Data
@EqualsAndHashCode(callSuper=false)
public class RestrictPlayNumber {
	
	@NotNull
	@Column(name = "restricted_number", nullable = false)
	private String number;
}
