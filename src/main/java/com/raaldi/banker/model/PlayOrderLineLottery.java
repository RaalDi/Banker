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
public class PlayOrderLineLottery {

    // @NotNull
    // @OneToOne
    // @JoinColumn(name = "play_order_id", nullable = false, insertable = true,
    // updatable = false)
    // private PlayOrder playOrder;

    @NotNull
    @OneToOne
    @JoinColumn(name = "lottery_id", nullable = false, insertable = true, updatable = false)
    private Lottery lottery;
    /*
     * public PlayOrder getPlayOrder() { return playOrder; }
     * 
     * public void setPlayOrder(PlayOrder playOrder) { this.playOrder =
     * playOrder; }
     */

}
