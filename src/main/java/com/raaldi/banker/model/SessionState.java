package com.raaldi.banker.model;

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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "session_state")
@XmlRootElement
@NamedQueries({ 
	@NamedQuery(name = "SessionState.findAll", query = "SELECT c FROM SessionState c"),
})
public class SessionState extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "session-state-seq-gen", sequenceName = "session_state_seq_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session-state-seq-gen")
	private Long id;

	@NotNull
	@Column(name = "name", nullable = false, unique = true)
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
