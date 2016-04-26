package com.raaldi.banker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

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
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Payment")
@NamedQueries({@NamedQuery(name = "Payment.findAll", query = "SELECT c FROM Payment c"),})
@Data
@EqualsAndHashCode(callSuper = false)
public final class Payment extends Model {

    private static final long serialVersionUID = 1017925264098582407L;

    @Id
    @SequenceGenerator(name = "payment-seq-gen", sequenceName = "payment_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment-seq-gen")
    private Long id;

    @NotNull
    @Column(name = "type", nullable = false, unique = true)
    private String type;

    @NotNull
    @Column(name = "active")
    private boolean active = true;
}
