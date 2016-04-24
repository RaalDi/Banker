package com.raaldi.banker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({@NamedQuery(name = "Lottery.findAll", query = "SELECT c FROM Lottery c"),})
@Data
@EqualsAndHashCode(callSuper = false)
public final class Lottery extends Model {

    private static final long serialVersionUID = -749127867337161764L;

    @Id
    @SequenceGenerator(name = "lottery-seq-gen", sequenceName = "lottery_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lottery-seq-gen")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "short_name", nullable = false, unique = true)
    private String shortName;

    @NotNull
    @Column(name = "active", nullable = false)
    private boolean active = true;
}
