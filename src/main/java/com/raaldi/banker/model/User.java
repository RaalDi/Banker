package com.raaldi.banker.model;

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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "person")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT c FROM User c"), })
public class User extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "person-seq-gen", sequenceName = "person_seq_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person-seq-gen")
	private Long id;

	@NotNull
	@Size(min = 2, max = 25, message = "2-25 letters and spaces")
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	@Column(name = "first_name")
	private String firstName;

	@NotNull
	@Size(min = 2, max = 25, message = "2-25 letters and spaces")
	@Pattern(regexp = "[^0-9]*", message = "Must not contain numbers")
	@Column(name = "last_name")
	private String lastName;

	@NotNull
	@Size(min = 8, max = 16, message = "8-16 letters and spaces")
	@Column(name = "user_name", unique = true)
	private String userName;

	@NotNull
	@Size(min = 10, max = 12, message = "10-12 Numbers")
	@Digits(fraction = 0, integer = 12, message = "Not valid")
	@Column(name = "phone_number")
	private String phoneNumber;

	@NotNull
	@Column(name = "password")
	private String password;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "loggedin_date")
	private Date loggedinDate;

	@NotNull
	@Column(name = "active")
	private boolean active = true;

	@NotNull
	// @Column(name = "company_id")
	// private Long companyId;
	@ManyToOne(optional = false)
	@JoinColumn(name = "company_id", nullable = false)
	private Company company;

	@OneToOne(cascade = CascadeType.ALL)
	// @PrimaryKeyJoinColumn
	// @JoinColumn(name = "id", nullable = false, insertable = true, updatable =
	// true)
	private Address address;
	
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	private Set<RolePermission> rolePermissions;
	
	@NotNull
	@ManyToOne(optional = false)
	// @PrimaryKeyJoinColumn
	@JoinColumn(name = "shop_id", nullable = false)
	private Shop shop;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLoggedinDate() {
		return loggedinDate;
	}

	public void setLoggedinDate(Date loggedinDate) {
		this.loggedinDate = loggedinDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Set<RolePermission> getRolePermissions() {
		return rolePermissions;
	}

	public void setRolePermissions(Set<RolePermission> rolePermissions) {
		this.rolePermissions = rolePermissions;
	}

	// @PreUpdate
	// @PrePersist
	public void onLoggedIn() {
		this.setLoggedinDate(new Date());
	}

	@Override
	public String toString() {
		return String.format("User@%d [id=%d, firstName=%s, lastName=%s, userName=%s, phoneNumber=%s, active=%s, company=%s, shop=%s]",
				hashCode(), id, firstName, lastName, userName, phoneNumber, active, company, shop);
	}

}
