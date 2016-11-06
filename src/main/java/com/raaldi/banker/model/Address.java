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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "Address")
@NamedQueries({ @NamedQuery(name = "Address.findAll", query = "SELECT c FROM Address c") })
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Address extends AbstractModel {

  private static final long serialVersionUID = -6530779330443333541L;

  @Id
  @SequenceGenerator(name = "address-seq-gen", sequenceName = "address_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address-seq-gen")
  @Column(name = "address_id")
  private long addressId;

  @NotNull
  @Size(min = 1, max = 50, message = "1-50 letters and spaces")
  @Column(name = "street")
  private String street;

  @NotNull
  @Size(min = 1, max = 25, message = "1-25 letters and spaces")
  @Column(name = "city")
  private String city;

  @NotNull
  @Size(min = 1, max = 25, message = "1-25 letters and spaces")
  @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
  @Column(name = "state")
  private String state;

  @NotNull
  @Size(min = 5, max = 5, message = "5 Numbers")
  @Digits(fraction = 0, integer = 5, message = "Not valid")
  @Column(name = "zipcode")
  private String zipcode;
}
