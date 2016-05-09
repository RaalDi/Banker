package com.raaldi.banker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@EqualsAndHashCode(callSuper = false)
public final class RestrictPlayLottery implements Serializable {

  private static final long serialVersionUID = -751469974986603513L;

  @NonNull
  @NotNull
  @OneToOne
  @JoinColumn(name = "lottery_id", nullable = false)
  private Lottery lottery;

}
