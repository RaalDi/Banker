package com.raaldi.banker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

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
@NamedQueries({@NamedQuery(name = "Payment.findAll", query = "SELECT c FROM Payment c")})
@Data
@EqualsAndHashCode(callSuper = true)
public final class Payment extends AbstractModel {

    private static final long serialVersionUID = 1017925264098582407L;

    @Id
    @SequenceGenerator(name = "payment-seq-gen", sequenceName = "payment_seq_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment-seq-gen")
    private long id;

    @NonNull
    @NotNull
    @Column(name = "type", nullable = false, unique = true)
    private String type;

    @NotNull
    @Column(name = "active")
    private boolean active;
}
