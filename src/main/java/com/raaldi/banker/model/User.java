package com.raaldi.banker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.raaldi.banker.util.model.AbstractModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "User")
@Table(name = "person")
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT c FROM User c"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT c FROM User c WHERE c.username = :username") })
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractModel {

  private static final long serialVersionUID = -8491559884264082143L;

  @Id
  @SequenceGenerator(name = "person-seq-gen", sequenceName = "person_seq_id", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person-seq-gen")
  private long id;

  @NonNull
  @NotNull
  @Size(min = 2, max = 25, message = "2-25 letters and spaces")
  @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
  @Column(name = "first_name")
  private String firstName;

  @NonNull
  @NotNull
  @Size(min = 2, max = 25, message = "2-25 letters and spaces")
  @Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
  @Column(name = "last_name")
  private String lastName;

  @NonNull
  @NotNull
  @Size(min = 8, max = 16, message = "8-16 letters and spaces")
  @Column(name = "user_name", unique = true)
  private String username;

  @NonNull
  @NotNull
  @Size(min = 10, max = 12, message = "10-12 Numbers")
  @Digits(fraction = 0, integer = 12, message = "Not valid")
  @Column(name = "phone_number")
  private String phoneNumber;

  @NonNull
  @NotNull
  @JsonIgnore
  @Column(name = "password")
  private String password;

  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "loggedin_date")
  private Date loggedinDate;

  @NotNull
  @Column(name = "active")
  private boolean active;

  @NonNull
  @NotNull
  @ManyToOne(optional = false)
  @JoinColumn(name = "company_id", nullable = false)
  private Company company;

  @OneToOne(cascade = CascadeType.ALL)
  private Address address;

  @OneToMany(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private Set<RolePermission> rolePermissions;

  @NonNull
  @NotNull
  @ManyToOne(optional = false)
  @JoinColumn(name = "shop_id", nullable = false)
  private Shop shop;

  public void setloggedinDate(final Date loggedinDate) {
    this.loggedinDate = loggedinDate == null ? null : new Date(loggedinDate.getTime());
  }

  public Date getloggedinDate() {
    return loggedinDate == null ? null : new Date(loggedinDate.getTime());
  }
}
