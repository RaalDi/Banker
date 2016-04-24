package com.raaldi.banker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.validator.constraints.NotEmpty;

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

@Entity
@Table(name = "play_order_line")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "PlayOrderLine.findAll", query = "SELECT c FROM PlayOrderLine c"), })
@Data
@EqualsAndHashCode(callSuper = false)
public final class PlayOrderLine extends Model {

    private static final long serialVersionUID = 3148027909146756391L;

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
    @CollectionTable(name = "play_order_line_lottery", joinColumns = {
            @JoinColumn(name = "play_order_line_id") })
    private Set<PlayOrderLineLottery> lotteries = new HashSet<PlayOrderLineLottery>();

    @NotEmpty
    @ElementCollection
    @CollectionTable(name = "play_order_line_number", joinColumns = {
            @JoinColumn(name = "play_order_line_id") })
    private List<PlayOrderLineNumber> numbers = new ArrayList<PlayOrderLineNumber>();

    @NotNull
    @Column(name = "amount", insertable = true, updatable = false)
    private BigDecimal amount;

    @Column(name = "winner", insertable = false, updatable = true)
    private boolean winner;

    @NotNull
    @Column(name = "canceled")
    private boolean canceled;
}
