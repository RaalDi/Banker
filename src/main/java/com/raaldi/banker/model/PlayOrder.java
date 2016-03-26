package com.raaldi.banker.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "play_order")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "PlayOrder.findAll", query = "SELECT c FROM PlayOrder c"), })
public class PlayOrder extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "play-order-seq-gen", sequenceName = "play_order_seq_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "play-order-seq-gen")
	private Long id;
	
	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "shop_id", nullable = false)
	private Shop shop;

	@NotNull
	@Column(name = "amount", insertable = true, updatable = false)
	private BigDecimal amount;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id", nullable = false, updatable = false)
	private Payment payment;

	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cash_register_id", nullable = false, updatable = false)
	private CashRegister cashRegister;

	@Column(name = "winner", insertable = false, updatable = true)
	private boolean winner;

	@NotNull
	@Column(name = "canceled")
	private boolean canceled = false;
	
	@NotNull
	@OneToMany(mappedBy = "playOrder", cascade = CascadeType.ALL)
	private List<PlayOrderLine> playOrderLines = new ArrayList<PlayOrderLine>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public CashRegister getCashRegister() {
		return cashRegister;
	}

	public void setCashRegister(CashRegister cashRegister) {
		this.cashRegister = cashRegister;
	}

	public boolean isWinner() {
		return winner;
	}

	public void setWinner(boolean winner) {
		this.winner = winner;
	}

	public boolean isCanceled() {
		return canceled;
	}

	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	public List<PlayOrderLine> getPlayOrderLines() {
		return playOrderLines;
	}

	public void setPlayOrderLines(List<PlayOrderLine> playOrderLines) {
		this.playOrderLines = playOrderLines;
	}
	
	@Override
	public String toString() {
		return String.format("PlayOrder@%d [id=%d, shop=%s, amount=%f, session=%s, winner=%s, canceled=%s]", hashCode(), id, shop, amount, cashRegister, winner, canceled);
	}
}
