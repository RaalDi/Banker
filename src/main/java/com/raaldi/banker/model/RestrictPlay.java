package com.raaldi.banker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "RestrictPlay")
@Table(name = "restrict_play")
@NamedQueries({@NamedQuery(name = "RestrictPlay.findAll", query = "SELECT c FROM RestrictPlay c")})
@Data
@EqualsAndHashCode(callSuper = true)
public final class RestrictPlay extends AbstractModel {

  private static final long serialVersionUID = -3936838944769073574L;

  public RestrictPlay(final Play play, final Date startDate, final Date endDate) {
    this.setPlay(play);
    this.setStartDate(startDate);
    this.setEndDate(endDate);
  }

  @Id
  @SequenceGenerator(name = "restrict-play-seq-gen", sequenceName = "restrict_play_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restrict-play-seq-gen")
  private long id;

  @NonNull
  @NotNull
  @OneToOne
  @JoinColumn(name = "play_id", insertable = true, updatable = false)
  private Play play;

  @NotEmpty
  @ElementCollection
  @CollectionTable(name = "restrict_play_lottery", joinColumns = {
      @JoinColumn(name = "restrict_play_id")})
  private Set<RestrictPlayLottery> lotteries = new HashSet<RestrictPlayLottery>();

  @NotEmpty
  @ElementCollection
  @CollectionTable(name = "restrict_play_number", joinColumns = {
      @JoinColumn(name = "restrict_play_id")}, uniqueConstraints = @UniqueConstraint(columnNames = {
          "restrict_play_id", "restricted_number"}))
  private List<RestrictPlayNumber> numbers = new ArrayList<RestrictPlayNumber>();

  @NonNull
  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "start_date", nullable = false)
  private Date startDate;

  @NonNull
  @NotNull
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "end_date", nullable = false)
  private Date endDate;

  @NotNull
  @Column(name = "active")
  private boolean active;

  public void setStartDate(final Date startDate) {
    this.startDate = startDate == null ? null : new Date(startDate.getTime());
  }

  public Date getStartDate() {
    return startDate == null ? null : new Date(startDate.getTime());
  }

  public void setEndDate(final Date endDate) {
    this.endDate = endDate == null ? null : new Date(endDate.getTime());
  }

  public Date getEndDate() {
    return endDate == null ? null : new Date(endDate.getTime());
  }

}
