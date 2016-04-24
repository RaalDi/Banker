package com.raaldi.banker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@EqualsAndHashCode(callSuper = false)
public final class RestrictPlayNumber implements Serializable {

    private static final long serialVersionUID = -2499724899416829464L;

    @NotNull
    @Column(name = "restricted_number", nullable = false)
    private String number;
}
