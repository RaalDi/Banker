package com.raaldi.banker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.raaldi.banker.util.EnumSessionState;

@Entity
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Session.findAll", query = "SELECT c FROM Session c"), })
public class Session extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "session-seq-gen", sequenceName = "session_seq_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "session-seq-gen")
	private Long id;

	@NotNull
	@OneToOne(optional = false)
	@JoinColumn(name = "user_id", nullable = false, insertable = true, updatable = false)
	private User user;
/*	
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cash_register_id", nullable = false, insertable = true, updatable = false)
	private CashRegister cashRegister;
*/
	// @NotNull
	// @OneToOne(cascade = CascadeType.ALL)
	// @JoinColumn(name = "state_id", nullable = false, insertable = true,
	// updatable = true)
	@Enumerated(EnumType.STRING)
	private EnumSessionState state;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "started", insertable = false, updatable = true)
	private Date started;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "ended", insertable = false, updatable = true)
	private Date ended;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
/*
	public CashRegister getCashRegister() {
		return cashRegister;
	}

	public void setCashRegister(CashRegister cashRegister) {
		this.cashRegister = cashRegister;
	}
*/
	public EnumSessionState getState() {
		return state;
	}

	public void setState(EnumSessionState state) {
		
		switch (state) {
		case STARTING:
			this.setCreated(new Date());
			break;
		case STARTED:
			this.setStarted(new Date());
			break;
		case ENDING:
				this.setUpdated(new Date());
			break;
		case ENDED:
			this.setEnded(new Date());
			break;
		}
		
		this.state = state;
	}

	public Date getStarted() {
		return started;
	}

	public void setStarted(Date started) {
		this.started = started;
	}

	public Date getEnded() {
		return ended;
	}

	public void setEnded(Date ended) {
		this.ended = ended;
	}
	
	@PrePersist
	public void onPersist(){
		this.setState(EnumSessionState.STARTING);
	}

	@Override
	public String toString() {
		return String.format("Session@%d [id=%d, shop=%s, state=%s, started=%s, ended=%s]", hashCode(), id, user, state,
				started, ended);
	}
}
