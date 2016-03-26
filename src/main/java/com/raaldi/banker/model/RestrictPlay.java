package com.raaldi.banker.model;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "restrict_play")
@XmlRootElement
@NamedQueries({ 
	@NamedQuery(name = "RestrictPlay.findAll", query = "SELECT c FROM RestrictPlay c"),
})
public class RestrictPlay extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "restrict-play-seq-gen", sequenceName = "restrict_play_seq_id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restrict-play-seq-gen")
	private Long id;

	@NotNull
	@OneToOne
	@JoinColumn(name = "play_id", insertable = true, updatable = false)
	private Play play;

	@NotEmpty
	@ElementCollection
	@CollectionTable(name = "restrict_play_lottery", joinColumns = { @JoinColumn(name = "restrict_play_id") })
	private Set<RestrictPlayLottery> lotteries = new HashSet<RestrictPlayLottery>();

	@NotEmpty
	@ElementCollection
	@CollectionTable(name = "restrict_play_number", joinColumns = { @JoinColumn(name = "restrict_play_id") }, uniqueConstraints = @UniqueConstraint(columnNames = {
			"restrict_play_id", "restricted_number" }) )
	private List<RestrictPlayNumber> numbers = new ArrayList<RestrictPlayNumber>();

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date", nullable = false)
	private Date startDate;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date", nullable = false)
	private Date endDate;

	@NotNull
	@Column(name = "active")
	private boolean active = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Play getPlay() {
		return play;
	}

	public void setPlay(Play play) {
		this.play = play;
	}

	public Set<RestrictPlayLottery> getLotteries() {
		return lotteries;
	}

	public void setLotteries(Set<RestrictPlayLottery> lotteries) {
		this.lotteries = lotteries;
	}

	public List<RestrictPlayNumber> getNumbers() {
		return numbers;
	}

	public void setNumbers(List<RestrictPlayNumber> numbers) {
		this.numbers = numbers;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public String toString() {
		return String.format("RestricPlay@%d [id=%d, play=%s, lotteries=%s, numbers=%s, startDate=%s, endDate=%s]", hashCode(), id, play, lotteries, numbers, startDate, endDate);
	}

}
