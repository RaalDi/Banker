package com.raaldi.banker.model;

import com.raaldi.banker.util.model.AbstractModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bk_play")
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "Play")
@NamedQueries({ @NamedQuery(name = "Play.findAll", query = "SELECT c FROM Play c") })
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Play extends AbstractModel {

  private static final long serialVersionUID = -7420839110305072161L;

  @Id
  @SequenceGenerator(name = "bk-play-seq-gen", sequenceName = "bk_play_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bk-play-seq-gen")
  @Column(name = "play_id")
  private long playId;

  @NotNull
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @NotNull
  @Column(name = "short_name", nullable = false, unique = true)
  private String shortName;

  @NotNull
  @Column(name = "active", nullable = false, columnDefinition = "boolean default false")
  private boolean active;

}
