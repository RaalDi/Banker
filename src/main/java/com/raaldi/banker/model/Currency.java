package com.raaldi.banker.model;

import com.raaldi.banker.util.model.AbstractModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.math.BigDecimal;

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
@Table(name = "currency")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Currency")
@NamedQueries({ @NamedQuery(name = "Currency.findAll", query = "SELECT c FROM Currency c") })
@Data
@EqualsAndHashCode(callSuper = true)
public class Currency extends AbstractModel {

  private static final long serialVersionUID = 7121744068113817683L;

  @Id
  @SequenceGenerator(name = "currency-seq-gen", sequenceName = "currency_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency-seq-gen")
  private long id;

  @NonNull
  @NotNull
  @Column(name = "value")
  private BigDecimal value;
}
