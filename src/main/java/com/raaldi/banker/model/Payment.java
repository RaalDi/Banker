package com.raaldi.banker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Payment.findAll", query = "SELECT c FROM Payment c"), })
public class Payment extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "payment-seq-gen", sequenceName = "payment_seq_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment-seq-gen")
	private Long id;

	@NotNull
	@Column(name = "type", nullable = false, unique = true)
	private String type;

	@NotNull
	@Column(name = "active")
	private boolean active = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return String.format("Payment@%d [id=%d, type=%s, active=%s]", hashCode(), id, type, active);
	}

}
