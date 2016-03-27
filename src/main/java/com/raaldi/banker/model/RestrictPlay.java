package com.raaldi.banker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.validator.constraints.NotEmpty;

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

@Entity
@Table(name = "restrict_play")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "RestrictPlay.findAll", query = "SELECT c FROM RestrictPlay c"), })
@Data
@EqualsAndHashCode(callSuper = false)
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
    @CollectionTable(name = "restrict_play_lottery", joinColumns = {
            @JoinColumn(name = "restrict_play_id") })
    private Set<RestrictPlayLottery> lotteries = new HashSet<RestrictPlayLottery>();

    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "restrict_play_number", joinColumns = {
            @JoinColumn(name = "restrict_play_id") }, uniqueConstraints = @UniqueConstraint(columnNames = {
                    "restrict_play_id", "restricted_number" }))
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

}
