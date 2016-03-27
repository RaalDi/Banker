package com.raaldi.banker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@EqualsAndHashCode(callSuper = false)
public class RestrictPlayLottery {

    @NotNull
    @OneToOne
    @JoinColumn(name = "lottery_id", nullable = false)
    private Lottery lottery;

}
