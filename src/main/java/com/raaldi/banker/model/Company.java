package com.raaldi.banker.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@XmlRootElement
@NamedQueries({ 
	@NamedQuery(name = "Company.findAll", query = "SELECT c FROM Company c"),
	@NamedQuery(name = "Company.findByName", query = "SELECT c FROM Company c WHERE c.name = :name"),
})
@Data
@EqualsAndHashCode(callSuper=false)
public class Company extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "company-seq-gen", sequenceName = "company_seq_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company-seq-gen")
	private Long id;

	@NotNull
	@Column(name = "name")
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	// @PrimaryKeyJoinColumn
	@JoinColumn(name = "address_id")
	private Address address;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
//	@JoinColumn(name="company_id")
	private List<User> users;
	
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
//	@JoinColumn(name="company_id")
	private List<Shop> shops;
}
