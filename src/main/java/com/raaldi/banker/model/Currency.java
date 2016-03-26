package com.raaldi.banker.model;

import java.math.BigDecimal;

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
@NamedQueries({ 
	@NamedQuery(name = "Currency.findAll", query = "SELECT c FROM Currency c"),
})
public class Currency extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "currency-seq-gen", sequenceName = "currency_seq_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "currency-seq-gen")
	private Long id;

	@NotNull
	@Column(name = "value")
	private BigDecimal value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return String.format("Currency@%d [id=%d, value=%f]", hashCode(), id, value);
	}

}
