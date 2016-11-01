package com.raaldi.banker.model;

import com.raaldi.banker.util.model.AbstractModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "company")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Company")
@NamedQueries({ @NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
    @NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.name = :name") })
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Company extends AbstractModel {

  private static final long serialVersionUID = 1090990028819708077L;

  @Id
  @SequenceGenerator(name = "company-seq-gen", sequenceName = "company_seq_id", initialValue = 100, allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company-seq-gen")
  @Column(name = "company_id")
  private long companyId;

  @NotNull
  @Column(name = "name", nullable = false, unique = true)
  private String name;

  @Column(name = "gov_id")
  private String govId;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "address_id")
  private Address address;
}
