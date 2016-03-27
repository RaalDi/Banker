package com.raaldi.banker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@EqualsAndHashCode(callSuper = false)
public class RestrictPlayNumber {

    @NotNull
    @Column(name = "restricted_number", nullable = false)
    private String number;
}
