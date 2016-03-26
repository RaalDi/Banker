package com.raaldi.banker.model;

import java.math.BigDecimal;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.raaldi.banker.util.CashRegisterState;

import lombok.ToString;

@Entity
@Table(name = "cash_register")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "CashRegister.findAll", query = "SELECT c FROM CashRegister c"), })
@ToString
public class CashRegister extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "cash-register-seq-gen", sequenceName = "cash_register_seq_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cash-register-seq-gen")
	private Long id;
	
	@NotNull
	@OneToOne(optional = false)
	@JoinColumn(name = "session_id", nullable = false, insertable = true, updatable = false)
	private Session session;

	@Enumerated(EnumType.STRING)
	private CashRegisterState state;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "opened", insertable = false, updatable = true)
	private Date opened;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "closed", insertable = false, updatable = true)
	private Date closed;

	@Column(name = "opened_amount", insertable = false, updatable = true)
	private BigDecimal openedAmount;

	@Column(name = "closed_amount", insertable = false, updatable = true)
	private BigDecimal closedAmount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public CashRegisterState getState() {
		return state;
	}

	public void setState(CashRegisterState state) {
		
		switch (state) {
		case OPENING:
			this.setCreated(new Date());
			break;
		case OPENED:
			this.setOpened(new Date());
			break;
		case CLOSING:
				this.setUpdated(new Date());
			break;
		case CLOSED:
			this.setClosed(new Date());
			break;
		}
		
		this.state = state;
	}

	public Date getOpened() {
		return opened;
	}

	public void setOpened(Date opened) {

		if (openedAmount == null) {
			throw new NullPointerException("CashRegister.openedAmount may not be null when opening the cash register");
		}

		this.opened = opened;
	}

	public Date getClosed() {
		return closed;
	}

	public void setClosed(Date closed) {

		if (closedAmount == null) {
			throw new NullPointerException("CashRegister.closedAmount may not be null when closing the cash register");
		}

		this.closed = closed;
	}

	public BigDecimal getOpenedAmount() {
		return openedAmount;
	}

	public void setOpenedAmount(BigDecimal openedAmount) {
		this.openedAmount = openedAmount;
	}

	public BigDecimal getClosedAmount() {
		return closedAmount;
	}

	public void setClosedAmount(BigDecimal closedAmount) {
		this.closedAmount = closedAmount;
	}

	@PrePersist
	public void onPersist() {
		this.setState(CashRegisterState.OPENING);
	}

	@Override
	public String toString() {
		return String.format(
				"CashRegister@%d [id=%d, state=%s, opened=%s, openedAmount=%f, closed=%s, closedAmount=%f]", hashCode(),
				id, state, opened, openedAmount, closed, closedAmount);
	}
}
