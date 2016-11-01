package com.raaldi.banker.model;

import com.raaldi.banker.util.model.AbstractModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Collections;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "shop")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Shop")
@NamedQueries({ @NamedQuery(name = "Shop.findAll", query = "SELECT c FROM Shop c") })
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Shop extends AbstractModel {

  private static final long serialVersionUID = -6096997265649544980L;

  @Id
  @SequenceGenerator(name = "shop-seq-gen", sequenceName = "shop_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shop-seq-gen")
  private long id;

  @NotNull
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @NotNull
  @Column(name = "active", nullable = false, columnDefinition = "boolean default false")
  private boolean active;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  private Address address;

  @NotNull
  @ManyToOne(optional = false)
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;

  @OneToMany(mappedBy = "shopId", cascade = CascadeType.ALL)
  private Set<User> users = Collections.emptySet();

  // @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL)
  // private Iterable<PlayOrder> playOrders;
}
