package com.raaldi.banker.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "play_order_line")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "PlayOrderLine.findAll", query = "SELECT c FROM PlayOrderLine c"), })
public class PlayOrderLine extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "play-order-line-seq-gen", sequenceName = "play_order_line_seq_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "play-order-line-seq-gen")
	private Long id;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "play_order_id", nullable = false)
	private PlayOrder playOrder;

	@NotNull
	@OneToOne(optional = false)
	@JoinColumn(name = "play_id", nullable = false, insertable = true, updatable = false)
	private Play play;

	@NotEmpty
	@ElementCollection
	@CollectionTable(name = "play_order_line_lottery", joinColumns = { @JoinColumn(name = "play_order_line_id") })
	private Set<PlayOrderLineLottery> lotteries = new HashSet<PlayOrderLineLottery>();

	@NotEmpty
	@ElementCollection
	@CollectionTable(name = "play_order_line_number", joinColumns = {
			@JoinColumn(name = "play_order_line_id") }/*, uniqueConstraints = @UniqueConstraint(columnNames = {
					"play_order_line_id", "played_number" })*/ )
	private List<PlayOrderLineNumber> numbers = new ArrayList<PlayOrderLineNumber>();

	@NotNull
	@Column(name = "amount", insertable = true, updatable = false)
	private BigDecimal amount;

	@Column(name = "winner", insertable = false, updatable = true)
	private boolean winner;

	@NotNull
	@Column(name = "canceled")
	private boolean canceled = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PlayOrder getPlayOrder() {
		return playOrder;
	}

	public void setPlayOrder(PlayOrder playOrder) {
		this.playOrder = playOrder;
	}

	public Play getPlay() {
		return play;
	}

	public void setPlay(Play play) {
		this.play = play;
	}

	public Set<PlayOrderLineLottery> getLotteries() {
		return lotteries;
	}

	public void setLotteries(Set<PlayOrderLineLottery> lotteries) {
		this.lotteries = lotteries;
	}

	public List<PlayOrderLineNumber> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<PlayOrderLineNumber> numbers) {
		this.numbers = numbers;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

	@Override
	public String toString() {
		return String.format(
				"PlayOrderLine@%d [id=%d, playOder=%s, play=%s, lotteries=%s, numbers=%s, amount=%f, winner=%s, canceled=%d]",
				hashCode(), id, playOrder, play, lotteries, numbers, amount, winner, canceled);
	}
}
